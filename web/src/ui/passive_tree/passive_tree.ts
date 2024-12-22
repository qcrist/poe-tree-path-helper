import {Application, Assets, Container, Graphics, PointData, Sprite} from "pixi.js";

import {loadTree, loadTreeIcons, passive_tree_json_node, passive_tree_json_type} from "#src/ui/passive_tree/raw_tree";
import {SelectionType, store, subscribeAndRunNow} from "#src/ui/state/store";
import {new_timer, time, TimerObj} from "#src/util/timer";
import {proxy, transfer} from "comlink";
import {handleErrors, wait_obj} from "#src/util/async";
import {init_solve_worker, RemoteSolver} from "#src/solver/worker-dispatch";

export const pixi_app = new Application();

class TreeGraph {
    public readonly nodes: passive_tree_json_node[];
    public readonly node_ids: Uint16Array;
    public readonly disabled: ReadonlySet<number>;
    public id_to_idx_lookup: Map<number, number>;
    public readonly tree: passive_tree_json_type;

    constructor(tree: passive_tree_json_type, disabled: ReadonlySet<number>) {
        this.tree = tree;
        this.nodes = [...Object.values(tree.nodes).filter(x => !disabled.has(x.id))];
        this.node_ids = new Uint16Array(
            this.nodes.map(x => x.id)
        );
        this.disabled = disabled;
        this.id_to_idx_lookup = new Map();
        this.node_ids.forEach((v, i) => this.id_to_idx_lookup.set(v, i));
    }

    async transfer_conn(timer: TimerObj, solver: RemoteSolver) {
        const roots = this.nodes.filter(x => x.isRootNode).map(x => x.id);

        const conn = timer.time("buildConn", () => {
            return this.nodes.map(x => {
                const connections = new Set(x.connections.filter(n => !this.disabled.has(n))
                    .map(x => this.id_to_idx_lookup.get(x)!));

                if (x.isRootNode) {
                    for (let r of roots) {
                        if (r !== x.id)
                            connections.add(this.id_to_idx_lookup.get(r)!);
                    }
                }

                return new Uint16Array(connections);
            });
        });
        const buffers = conn.map(x => x.buffer);
        await solver.setup_tree(transfer(conn, buffers));
    }
}


const colors = [
    0xFF0000,
    0x00FF00,
    0x0000FF,
    0xFFFF00,
    0x00FFFF,
    0xFF00FF,
    0xFFFFFF,
    0x99FFFF,
    0xFF99FF
];

function listBadNodes(tree: passive_tree_json_type): ReadonlySet<number> {
    const disabled_nodes = new Set<number>();

    for (let node of Object.values(tree.nodes)) {
        if (node.isJustIcon)
            disabled_nodes.add(node.id);
        if (node.isAscendancyStartingNode) {
            const todo = [node.id];
            const added = new Set<number>(todo);
            let i = 0;
            for (i = 0; i < todo.length; i++) {
                disabled_nodes.add(todo[i]);
                const dat = tree.nodes[todo[i]];
                if (dat === undefined)
                    throw new Error(`no such node ${i}`);
                for (let c of dat.connections) {
                    const n = tree.nodes[c];
                    if (n.isRootNode) continue;
                    if (added.has(c)) continue;
                    todo.push(c);
                    added.add(c);
                }
            }
            // console.log("asc", node.id, i);
        }
    }

    // disabled_nodes.clear();

    return disabled_nodes;
}

type save_t = {
    version: number,
    x: number,
    y: number,
    s: number
}
const SAVE_POSITION_KEY = "save-position";
const SAVE_VERSION = 1;
const DEFAULT_SAVE: save_t = {
    x: 0,
    y: 0,
    s: 0.1,
    version: SAVE_VERSION
};

let _last_save = 0;

function save_position() {
    const save_idx = ++_last_save;
    const save: save_t = {
        version: SAVE_VERSION,
        x: pixi_app.stage.x - pixi_app.screen.width / 2,
        y: pixi_app.stage.y - pixi_app.screen.height / 2,
        s: pixi_app.stage.scale.x
    };
    setTimeout(() => {
        if (_last_save !== save_idx) return;
        localStorage.setItem(SAVE_POSITION_KEY, JSON.stringify(save));
    }, 300);
}

function load_position() {
    const read = localStorage.getItem(SAVE_POSITION_KEY);
    let save: save_t = DEFAULT_SAVE;
    if (read !== null)
        save = JSON.parse(read);
    if (save.version !== SAVE_VERSION) {
        console.warn("old save ignored", save);
        save = DEFAULT_SAVE;
    }
    pixi_app.stage.x = pixi_app.screen.width / 2 + save.x;
    pixi_app.stage.y = pixi_app.screen.height / 2 + save.y;
    pixi_app.stage.scale = save.s;
}

export async function init_tree() {
    const timer = new_timer(init_tree);

    const {tree, solver} = await wait_obj({
        tree: timer.timeFnAsync(loadTree),
        icons_map: timer.timeFnAsync(loadTreeIcons),
        solver: timer.timeFnAsync(init_solve_worker)
    });

    await pixi_app.init({
        resizeTo: window,
        background: "#080B10",
    });

    const disabled_nodes = timer.timeFn(listBadNodes, tree);
    const graph = timer.time("tree_graph", () => new TreeGraph(tree, disabled_nodes));
    const graph_transfer_promise = timer.timeAsync("transfer_conn", t => graph.transfer_conn(t, solver));


    // store.getState().toggleNode(graph.nodes[4].id, SelectionType.BLOCK);
    // store.getState().toggleNode(graph.nodes[476].id, SelectionType.BLOCK);

    timer.time("listeners", () => {
        let mouse_prev = [0, 0];
        pixi_app.canvas.addEventListener("mousemove", e => {
            if (e.buttons == 1) {
                const dx = e.x - mouse_prev[0];
                const dy = e.y - mouse_prev[1];
                pixi_app.stage.x += dx;
                pixi_app.stage.y += dy;
            }

            mouse_prev = [e.x, e.y];
            save_position();
        }, {passive: true, capture: false,});
        pixi_app.canvas.addEventListener("mouseup", e => {
            save_position();
        }, {passive: true, capture: false});
        pixi_app.canvas.addEventListener("wheel", e => {
            // const pos = {
            //     x: pixi_app.screen.x + pixi_app.screen.width / 2,
            //     y: pixi_app.screen.y + pixi_app.screen.height / 2
            // };
            const pos: PointData = e;
            const p1 = pixi_app.stage.toLocal(pos);

            const sc = pixi_app.stage.scale.x;

            if (e.deltaY < 0) {
                pixi_app.stage.scale = Math.min(1, sc * 1.2);
            } else if (e.deltaY > 0) {
                pixi_app.stage.scale = Math.max(sc / 1.2, 0.02);
            }

            const p2 = pixi_app.stage.toLocal(pos);
            pixi_app.stage.position.x += (p2.x - p1.x) * pixi_app.stage.scale.x;
            pixi_app.stage.position.y += (p2.y - p1.y) * pixi_app.stage.scale.y;
            save_position();

        }, {passive: true});
        pixi_app.canvas.addEventListener("contextmenu", e => {
            e.preventDefault();
        }, {capture: false, passive: false});
    });


    await graph_transfer_promise;

    async function new_frame(p: passive_tree_json_node) {
        let tex_path: string;
        if (p.isNotable)
            tex_path = tree.frames.NotableFrameNormal;
        else if (p.isKeystone)
            tex_path = tree.frames.KeystoneFrameNormal;
        else
            tex_path = tree.frames.PassiveFrameNormal;

        let tex = await Assets.load(tex_path);
        if (tex === undefined)
            throw new Error("failed to find frame!");
        const sp = new Sprite(tex);
        sp.anchor.set(0.5);
        return sp;
    }

    function get_radius(p: passive_tree_json_node) {
        if (p.isNotable)
            return 70;
        else if (p.isKeystone)
            return 105;
        return 45;
    }

    const icons_container = new Container();
    pixi_app.stage.addChild(icons_container);
    const connections_container = new Container();
    pixi_app.stage.addChild(connections_container);
    const nodes_container = new Container();
    pixi_app.stage.addChild(nodes_container);
    const selections_container = new Container();
    pixi_app.stage.addChild(selections_container);

    function buildSelection(p: passive_tree_json_node) {
        const circle = new Graphics();
        const r = get_radius(p);
        const {x, y} = p;
        circle.circle(x, y, r);
        circle.stroke({width: 10, color: 0xFFFFFF});

        const circleLarge = new Graphics();
        circleLarge.circle(x, y, r + 15);
        circleLarge.stroke({width: 20, color: 0xFFFF00});

        const cross = new Graphics();
        cross.moveTo(-r, -r);
        cross.lineTo(r, r);
        cross.moveTo(r, -r);
        cross.lineTo(-r, r);
        cross.stroke({width: 20, color: 0xFFFFFF});
        cross.position.x = x;
        cross.position.y = y;

        selections_container.addChild(cross, circle, circleLarge);

        subscribeAndRunNow(({selection, pathNodes}) => {
            const get = selection.get(p.id);
            switch (get) {
                case SelectionType.DIRECT:
                    circle.tint = 0x00FF00;
                    circle.renderable = true;
                    cross.renderable = false;
                    break;
                case SelectionType.BLOCK:
                    circle.renderable = false;
                    cross.renderable = true;
                    cross.tint = 0xFF0000;
                    break;
                case SelectionType.OPTIONAL:
                    circle.tint = 0xFF00FF;
                    circle.renderable = true;
                    cross.renderable = false;
                    break;
                case SelectionType.ROOT:
                    circle.tint = 0xFFFFFF;
                    circle.renderable = true;
                    cross.renderable = false;
                    break;
                case undefined: {
                    if (pathNodes.has(p.id)) {
                        // circle.tint = 0x0077FF;
                        circle.tint = 0x0077FF;
                        circle.renderable = true;
                        cross.renderable = false;
                        break;
                    }
                    circle.renderable = false;
                    cross.renderable = false;
                    break;
                }
            }
        });
        const searchTerms = [p.name, ...p.stats].map(x => x.toLowerCase());

        subscribeAndRunNow(({search}) => {
            if (search.length == 0) {
                circleLarge.renderable = false;
                return;
            }
            find:
                for (let check of search.toLowerCase().split(" ")) {
                    for (let st of searchTerms) {
                        if (st.includes(check))
                            continue find;
                    }
                    circleLarge.renderable = false;
                    return;
                }
            circleLarge.renderable = true;
        });

    }

    function build_connection(a: number, b: number) {
        const graphics = new Graphics();

        const {x: x1, y: y1} = tree.nodes[a]!;
        const {x: x2, y: y2} = tree.nodes[b];

        graphics.moveTo(x1, y1);
        graphics.lineTo(x2, y2);
        graphics.stroke({width: 20, color: 0xFFFFFF});

        subscribeAndRunNow(({pathNodes}) => {
            const sA = pathNodes.has(a);
            const sB = pathNodes.has(b);
            if (sA && sB) {
                graphics.tint = 0x0077FF;
            } else {
                graphics.tint = 0x444444;
            }
        });

        connections_container.addChild(graphics);
    }

    async function build_node(container: Container, p: passive_tree_json_node) {
        const ico = await Assets.load(p.icon);
        const node = new Sprite(ico);
        node.anchor.set(0.5);
        const {x, y} = p;
        container.x = x;
        container.y = y;
        container.on("mousedown", e => {
            if (e.ctrlKey) {
                console.log(`CLICKED id=${p.id} idx=${graph.id_to_idx_lookup.get(p.id)}`, p);
                return;
            }
            if (p.isRootNode) {
                //TODO handle atlas with multi-root
                const obj: { [index: number]: SelectionType } = {};
                tree.root_passives.forEach(r => {
                    if (r !== p.id) {
                        obj[r] = SelectionType.BLOCK;
                    } else {
                        obj[r] = SelectionType.ROOT;
                    }
                });
                store.getState().bulkSet(obj);
                return;
            }
            switch (e.button) {
                case 0:
                    return store.getState().toggleNode(p.id, SelectionType.DIRECT);
                case 1:
                    return store.getState().toggleNode(p.id, SelectionType.OPTIONAL);
            }
        });
        container.on("rightdown", e => {
            store.getState().toggleNode(p.id, SelectionType.BLOCK);
        });
        container.on("mouseenter", e => {
            store.getState().setTooltip({
                title: p.name,
                lines: p.stats,
                ...container.parent.toGlobal(container.position)
            });
        });
        container.on("mouseleave", _ => {
            store.getState().setTooltip(null);
        });
        container.on("wheel", _ => {
            store.getState().setTooltip(null);
        });
        container.on("mousemove", e => {
            if (e.buttons != 0)
                store.getState().setTooltip(null);
        });
        container.eventMode = "static";
        container.cursor = "pointer";
        const f = await new_frame(p);

        const s = Math.min(f.width, f.height) * .65;
        node.width = node.height = s;

        // container.scale = 1.2;

        container.addChild(node);
        container.addChild(f);
    }

    async function build_icon_node(container: Container, p: passive_tree_json_node) {
        const ico = await Assets.load(p.mastery_icon);
        const node = new Sprite(ico);
        node.anchor.set(0.5);
        const {x, y} = p;
        node.x = x;
        node.y = y;
        node.alpha = 0.3;

        container.addChild(node);
    }

    timer.time("pixi_setup", () => {
        load_position();
        for (let p of Object.values(tree.nodes)) {
            if (p.mastery_icon) {
                build_icon_node(icons_container, p);
            }

            if (disabled_nodes.has(p.id)) continue;
            // if (p.radius > 1) continue;
            const container = new Container();
            nodes_container.addChild(container);

            handleErrors(build_node(container, p));

            buildSelection(p);

            if (!disabled_nodes.has(p.id)) {
                for (let c of p.connections) {
                    if (disabled_nodes.has(c)) continue;
                    build_connection(p.id, c);
                }
            }
        }
    });

    (function setup_solver() {
        let last: null | ReadonlyMap<number, SelectionType> = null;
        let last_solve_abort: null | (() => void) = null;
        subscribeAndRunNow(async state => {
            //todo implement and use subscribeAndRunNowWithSelector (and in other places) :O!
            const next = state.selection;
            if (next === last) return;
            if (last_solve_abort !== null) last_solve_abort();
            last = next;

            const selected = [...next.entries()]
                .filter(([k, v]) => v == SelectionType.DIRECT || v == SelectionType.ROOT)
                .map(([k]) => graph.id_to_idx_lookup.get(k)!);

            const blocked = [...next.entries()]
                .filter(([k, v]) => v == SelectionType.BLOCK)
                .map(([k]) => graph.id_to_idx_lookup.get(k)!);

            console.log("request solve", selected, blocked);

            let aborted_during_await = false;
            last_solve_abort = () => {
                aborted_during_await = true;
            };

            let cancel_solve = await solver.solve({
                required: selected,
                blocked: blocked
            }, proxy(update => {
                console.log("solve update", update);
                state.updatePathNodes(update.best.map(x => graph.node_ids[x]));
            }));

            if (aborted_during_await) {
                cancel_solve();
            } else {
                last_solve_abort = cancel_solve;
            }

        });
    })();

    timer.finished();

    return pixi_app.canvas;
}

