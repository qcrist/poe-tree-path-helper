import {time, timeAsync, TimerObj} from "#src/util/timer";
import {handleErrors} from "#src/util/async";
import {proxy} from "comlink";
import type {BenchmarkData} from "#src/solver/benchmark";

export async function run_benchmark_js(benchmark_data: BenchmarkData) {
    const conn = benchmark_data.tree.map(x => new Uint16Array(x));

    console.log(conn.length);
    throw new Error("need to handle distances!");

    // await timeAsync("benchmark_js", async timer => {
    //     // const distances = timer.timeFn(calc_distances, conn); //243
    //     // const p = timer.timeFn(paths, conn, distances); //1215
    //
    //     //TODO need to bulk calculate distances !
    //
    //     await timer.timeAsync("benchmark", async () => {
    //         let acc = 0;
    //         const mistakes: { [key: number]: number } = {};
    //         for (let test of benchmark_data.tests) {
    //             const sel = new Uint16Array(test.target);
    //             const solve = await solve_random_2(conn, sel, [{
    //                 // final_step: true,
    //                 cap: 100,
    //                 acc: 0
    //             }]);
    //             if (solve.length < test.len) {
    //                 throw new Error("LT!");
    //             }
    //             if (solve.length === test.len)
    //                 acc++;
    //             else {
    //                 const k = solve.length - test.len;
    //                 mistakes[k] = (mistakes[k] ?? 0) + 1;
    //             }
    //         }
    //         console.log(`accuracy ${acc} / ${benchmark_data.tests.length}`);
    //         console.log("mistakes", mistakes);
    //     });
    //
    // });
}

export type solve_progress_fn = (data: {
    finished: boolean,
    best: number[]
}) => void;

export type solve_cancel_fn = () => void;
type abort_obj = {
    abort: boolean;
}

type solve_req = {
    required: number[],
    blocked: number[],
    // optional: number[]
}

export class TreeSolver {
    constructor() {
    }

    // async benchmark() {
    //     await run_benchmark_js(bm);
    // }

    private conn: Uint16Array[] | undefined;
    // private dist: Int8Array[] | undefined;
    // private path: Uint16Array[][] | undefined;

    // private path_block_map: [number, number][][] = [];

    async setup_tree(conn: Uint16Array[]) {
        await timeAsync("setup_tree", async timer => {
            this.conn = conn;
            // this.dist = timer.timeFn(calc_distances, conn);
            // this.path = timer.timeFn(paths, conn, this.dist);
            // timer.timeFn(calculate_block_map, this.path);
        });
    }

    solve(solve: solve_req, callback: solve_progress_fn): solve_cancel_fn {
        if (this.conn === undefined /*|| this.dist === undefined*/ /*|| this.path === undefined*/)
            throw new Error("setup first!");
        const abort: abort_obj = {
            abort: false
        };
        const conn = handle_blocked_nodes(this.conn!, solve);

        handleErrors((async () => {
            const s = new Uint16Array(solve.required);

            const result = await timeAsync("solve_random_2", t => solve_random_2(conn, s, [
                {acc: 0, cap: 200},
                {acc: 2, cap: 5000}
            ], callback, abort, t));

            if (!abort.abort)
                callback({
                    finished: true,
                    best: [...result]
                });
        })());
        return proxy(() => {
            abort.abort = true;
        });
    }

}

const yield_channel = new MessageChannel();
const yield_listeners: (() => void)[] = [];
yield_channel.port2.onmessage = ()=>{
    for (let l of yield_listeners) {
        l();
    }
    yield_listeners.length = 0;
}

async function yield_to_event_loop() {
    const p = new Promise<void>(cb => {
        yield_listeners.push(cb);
    });
    yield_channel.port1.postMessage(null);
    return p;
}

function handle_blocked_nodes(conn: Uint16Array[], {blocked}: solve_req) {
    return time("handle_blocked_nodes", timer => {
        const blocked_set = new Set(blocked);
        const new_conn = conn.map(x => new Uint16Array(x));
        timer.time("purge", () => {
            for (let i of blocked_set) {
                const c = new_conn[i];
                new_conn[i] = EMPTY_U16;
                for (let id of c) {
                    new_conn[id] = new_conn[id].filter(x => x !== i);
                }
            }
        });
        return new_conn;
    });
}

function calc_distances(conn: Uint16Array[], nodes: Uint16Array, out: Int8Array[]) {
    const node_count = conn.length;

    const seen = new Int16SetList(node_count);

    for (const src of nodes) {
        if (out[src] !== undefined) continue;
        out[src] = new Int8Array(conn.length).fill(-1);

        seen.clear();
        seen.add(src);

        for (let i = 0, dist = 0; i != seen.length; dist++) {
            const snap = seen.length;
            for (let x = i; x < snap; x++, i++) {
                const n = seen.data[x];
                out[src][n] = dist;
                for (const c of conn[n]) {
                    seen.add(c);
                }
            }
        }
    }
}

class Int16SetList {
    added: Uint8Array;
    data: Uint16Array;
    length: number = 0;

    constructor(limit: number) {
        this.data = new Uint16Array(limit);
        this.added = new Uint8Array(limit);
    }

    add(value: number) {
        if (this.added[value] === 0) {
            this.added[value] = 1;
            this.data[this.length++] = value;
            return true;
        }
        return false;
    }

    clear() {
        this.length = 0;
        this.added.fill(0);
    }
}

class MinPath {
    value: Uint16Array;
    min: number;

    constructor() {
        this.min = 1e10;
        this.value = new Uint16Array(0);
    }

    offer(size: number, value: Int16SetList) {
        if (size < this.min) {
            this.min = size;
            this.value = value.data.slice(0, value.length);
            if (this.value.buffer === value.data.buffer)
                throw new Error("!");
        }
    }
}

function reachable(conn: Int16SetList[], start: number) {
    const seen = new Int16SetList(conn.length);
    let at = 0;
    seen.add(start);
    while (at < seen.data.length) {
        //todo iterator method?
        const arr = conn[seen.data[at++]];
        for (let i = 0; i < arr.length; i++) {
            seen.add(arr.data[i]);
        }
    }
    return seen.added;
}

const EMPTY_U16 = new Uint16Array(0);

type solve_params = {
    acc: number,
    cap: number,
}

//acc=0, cap=100 is pretty fast
//acc=2, cap=5000 is very good (and slow)
async function solve_random_2(conn: Uint16Array[], sel: Uint16Array,
                              params: solve_params[],
                              callback?: solve_progress_fn, abort?: abort_obj, timer?: TimerObj): Promise<Uint16Array> {
    // dist: Int8Array[],
    let min_callback_length = Infinity;
    if (sel.length == 0) return EMPTY_U16;
    if (sel.length == 1) return sel;

    const dist: Int8Array[] = [];
    timer?.time("initialDistances", () => calc_distances(conn, sel, dist));

    for (let a = 0; a < sel.length; a++) {
        for (let b = a + 1; b < sel.length; b++) {
            if (dist[sel[a]][sel[b]] === -1) {
                console.warn("disconnected tree!");
                return EMPTY_U16;
            }
        }
    }

    if (sel.length == 2) return paths_part(conn, dist[sel[1]], sel[0], sel[1]);

    const minPath = new MinPath();
    const connected = new Int16SetList(conn.length);
    const important = new Int16SetList(conn.length);

    // noinspection JSMismatchedCollectionQueryUpdate
    const paths: Uint16Array[][] = [];

    function path(a: number, b: number) {
        if (b < a) return path(b, a);

        //todo shouldn't bee too hard to check for any favorites and add them here
        //todo maximizing them on the other hand would be a pain

        const arr1 = (paths[a] ??= []);
        const rdB = arr1[b];
        if (rdB !== undefined)
            return rdB;
        return arr1[b] = paths_part(conn, dist[b], a, b);
    }

    function do_callback() {
        if (abort?.abort) return;
        if (minPath.value.length <= (min_callback_length ?? 1E99)) {
            callback?.({
                finished: false,
                best: [...minPath.value]
            });
            min_callback_length = minPath.value.length - 1;
        }
    }

    // let finished = false;
    // if (callback !== undefined)
    //     (async () => {
    //         let last: null | Uint16Array = null;
    //         while (true) {
    //             await wait(100);
    //             if (finished || abort?.abort) break;
    //             if (minPath.value !== last) {
    //                 last = minPath.value;
    //                 do_callback();
    //             }
    //         }
    //     })().catch(err => {
    //         console.error(err);
    //     });

    const order = new Uint16Array(sel.length + 1);

    let at = 0;

    for (let i = 0; i < params.length; i++) {
        const param = params[i];
        const {cap, acc} = param;
        const important_nodes = simp(conn, dist, sel, acc);
        const steiner_nodes = Uint16Array.from(
            {length: important_nodes.length},
            (_, i) => i)
            .filter(x => important_nodes[x] === 1);
        timer?.time(`param[${i}].distances`, () => calc_distances(conn, steiner_nodes, dist));

        let seed = rng(Date.now());

        for (const a of steiner_nodes) {
            await yield_to_event_loop();
            if (abort?.abort) {
                console.debug("aborted solve");
                // finished = true;
                return EMPTY_U16;
            }
            order.set(sel, 0);
            order[sel.length] = a;
            for (let x = 0; x < cap; x++) {
                if (at++ >= 10000) {
                    //todo time how long this triggers
                    await yield_to_event_loop();
                    do_callback();
                    at = 0;
                }

                seed = shuffle16(order, seed);
                connected.clear();
                important.clear();
                connected.add(order[0]);
                important.add(order[0]);
                for (const n of order) {
                    if (connected.added[n] === 1) continue;
                    let min_dist = 1E5;
                    let min_node = -1;

                    const dn = dist[n];
                    for (let i = 0; i < important.length; i++) {
                        const v = important.data[i];
                        const read = dn[v];
                        if (read < min_dist) {
                            min_dist = read;
                            min_node = v;
                        }
                    }

                    if (min_node === -1 || min_dist === -1) {
                        console.warn("no path, is the tree connected?");
                        //TODO verify tree connected before solving
                        // finished = true;
                        return EMPTY_U16;
                    }

                    for (const i of path(min_node, n)) {
                        connected.add(i);
                        if (important_nodes[i] === 1)
                            important.add(i);
                    }

                    if (connected.length >= minPath.min)
                        break;
                }
                minPath.offer(connected.length, connected);
            }
        }

        do_callback();
    }

    // finished = true;
    return minPath.value;
}

function rng(seed: number) {
    return seed * 16807 % 2147483647;
}

function shuffle16(arr: Uint16Array, seed: number) {
    for (let i = arr.length - 1; i > 0; i--) {
        const idx = (seed & 0x7FFFFFFF) % i;
        seed = rng(seed);

        const tmp = arr[idx];
        arr[idx] = arr[i];
        arr[i] = tmp;
    }
    return seed;
}

function simp(conn: Uint16Array[], dist: Int8Array[], sel: Uint16Array, acc: number): Uint8Array {
    const reduced_conn = Array.from({length: conn.length}, _ => new Int16SetList(conn.length));

    for (let a = 0; a < conn.length; a++) {
        for (let b of conn[a]) {
            if (a > b) continue;

            let have_ab = false;
            let have_ba = false;
            for (const i of sel) {
                let ra = dist[i][a];
                let rb = dist[i][a];
                if (ra == rb) {
                    have_ab = have_ba = true;
                } else if (ra < rb) {
                    have_ab = true;
                } else {
                    have_ba = true;
                }
                if (have_ab && have_ba) {
                    reduced_conn[a].add(b);
                    reduced_conn[b].add(a);
                }
            }
        }
    }

    let reach = reachable(reduced_conn, sel[0]);
    let reach2 = new Uint8Array(reach.length);
    for (const j of sel) {
        reach2[j] = 1;
    }
    for (let i = 0; i < reach.length; i++) {
        if (reach[i] === 1) {
            if (conn[i].length <= 2)
                continue;
            if (conn[i].filter(x => reach[x]).length <= 2)
                continue;
            let good = false;
            loop_a:
                for (let a = 0; a < sel.length; a++) {
                    for (let b = a + 1; b < sel.length; b++) {
                        if (dist[sel[a]][i] + dist[sel[b]][i] <= dist[sel[a]][sel[b]] + acc) {
                            good = true;
                            break loop_a;
                        }
                    }
                }
            if (!good) continue;

            reach2[i] = 1;
        }
    }

    return reach2;
}

function paths(conn: Uint16Array[], dist: Int8Array[]): Uint16Array[][] {
    const node_count = conn.length;
    const result: Uint16Array[][] = Array.from({length: node_count}, _ => {
        return Array.from({length: node_count}, _ => null as any);
    });

    for (let a = 0; a < node_count; a++) {
        for (let b = a + 1; b < node_count; b++) {
            result[a][b] = result[b][a] = paths_part(conn, dist[b], a, b);
        }
    }

    return result;
}

function paths_part(conn: Uint16Array[], dist_to_dst: Int8Array, src: number, dst: number): Uint16Array {
    if (dist_to_dst === undefined || dist_to_dst === null)
        throw new Error("npe");
    const a_dist = dist_to_dst[src];
    const path = new Uint16Array(a_dist + 1);
    path[0] = src;
    find:
        for (let i = 1; i < a_dist + 1; i++) {
            const prev = path[i - 1];
            const target_d = a_dist - i;
            for (const c of conn[prev]) {
                if (dist_to_dst[c] === target_d) {
                    path[i] = c;
                    // path.push(c);
                    continue find;
                }
            }
            throw new Error(`failed to resolve path ${src} => ${dst}, i=${i}, path=${path}`);
        }
    return path;
}
