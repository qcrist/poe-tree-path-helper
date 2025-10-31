import {Assets, Spritesheet, Texture, TextureSource} from "pixi.js";
import JSZip from "jszip";
import {Group, Node, SkillTreeExport} from "#src/ui/passive_tree/ggg_tree_export_types";
import {SpritesheetData} from "pixi.js/lib/spritesheet/Spritesheet";

export type passive_tree_json_node = {
    id: number,
    // position: number,
    // radius: number,
    icon: string,
    mastery_icon?: string;
    x: number,
    y: number,
    // skillType: number,
    isAscendancyStartingNode: boolean,
    isNotable?: boolean,
    isKeystone?: boolean,
    isMastery?: boolean,
    isWormhole?: boolean,
    // isMultipleChoice: boolean,
    isJewelSocket?: boolean,
    isRootNode?: boolean,
    isJustIcon: boolean,
    // connections: {
    //     id: number,
    //     radius: number
    // }[]
    connections: number[],
    name: string,
    stats: string[],
}

export type raw_frames_t = {
    "KeystoneFrameNormal": string,
    "NotableFrameNormal": string,
    "KeystoneFrameActive": string,
    "PassiveFrameNormal": string,
    "NotableFrameActive": string,
    "PassiveFrameActive": string
}

export type passive_tree_json_type = {
    root_passives: number[],
    nodes: { [k: number]: passive_tree_json_node };
    frames: raw_frames_t,
    scale: number
}


// import loadTreeData from "#data/tree-export-default.json";
// import loadTreeIconsData from "#data/art-extract-default.json";

const orbitAngle23 = [0, 30, 45, 60, 90, 120, 135, 150, 180, 210, 225, 240, 270, 300, 315, 330];

function node_pos(tree: SkillTreeExport, n: Node, g: Group) {
    const {orbitRadii, skillsPerOrbit} = tree.constants;

    const {orbitIndex, orbit} = n;

    if (orbitIndex === undefined || orbit === undefined) {
        return [0, 0];
    }

    let angle = Math.PI * 2 / skillsPerOrbit[orbit] * orbitIndex;
    if (orbit == 2 || orbit == 3) {
        angle = Math.PI / 180 * orbitAngle23[orbitIndex];
//                continue;
    }
    //TODO sin/cos are not right here...
    const ox = orbitRadii[orbit] * Math.sin(angle);
    const oy = orbitRadii[orbit] * -Math.cos(angle);

    const {x, y} = g;

    return [ox + x, oy + y];
}

export type LoadExportZipData = {
    tree: passive_tree_json_type,
    textures: { [index: string]: Texture }
}

function select_tree(){
    let url = new URL(location.href)
    url.searchParams

    return {
        tree_file: url.searchParams.get("tf") ?? "data.json",
        tree_export: url.searchParams.get("te") ?? "skilltree-export-3.27.0"
    }

}

export async function load_export_zip(): Promise<LoadExportZipData> {
    const {tree_export, tree_file} = select_tree();

    let url = `data-exports/${tree_export}.zip`;
    const resp = await fetch(url);
    if (!resp.ok) {
        throw new Error("bad tree load: " + url);
    }
    console.log("loading zip");
    const zip = await JSZip.loadAsync(await resp.arrayBuffer());

    const target_tree_json = `${tree_export}/${tree_file}`;

    const tree: passive_tree_json_type = {
        frames: {} as any,
        root_passives: [],
        nodes: {},
        scale: 1
    };


    const assets: { [index: string]: TextureSource } = {};
    let raw_tree: SkillTreeExport = undefined as any;

    async function zip_entry([path, file]: [string, JSZip.JSZipObject]) {
        if (file.dir) return;

        console.log(path)

        if (path === target_tree_json) {
            raw_tree = JSON.parse(await file.async("text"));

            console.log(raw_tree);

            const nodes: Node[] = [];

            for (const g of Object.values(raw_tree.groups)) {
                for (let node_id of g.nodes) {
                    const node_idx = +node_id;
                    if (isNaN(node_idx)) {
                        throw new Error("nan id!");
                    }
                    const n = raw_tree.nodes[node_id];
                    nodes.push(n);
                    let isRootNode = false;
                    if (n.classStartIndex !== undefined || n.name.length == 0) {
                        tree.root_passives.push(node_idx);
                        console.log(n, node_id);
                        isRootNode = true;
                    }

                    if (n.ascendancyName !== undefined || n.isProxy || n.expansionJewel?.parent || n.isBlighted) {
                        continue;
                    }

                    const [x, y] = node_pos(raw_tree, n, g);

                    const isJustIcon = n.isMastery == true && n.activeEffectImage === undefined

                    // const x =
                    tree.nodes[node_idx] = {
                        ...n,
                        id: node_idx,
                        isAscendancyStartingNode: n.isAscendancyStart ?? false,
                        isRootNode,
                        connections: [],
                        x, y,
                        stats: n.stats ?? [],
                        mastery_icon: isJustIcon ? n.icon : n.activeEffectImage,
                        isJustIcon,
                    };

                    if (node_idx == 60170){
                        console.log(tree.nodes[60170]);
                    }
                }
            }

            function add_conn(a: number, b: number) {
                if (!tree.nodes[a] || !tree.nodes[b])
                    return;
                tree.nodes[a].connections.push(b);
                tree.nodes[b].connections.push(a);
            }

            for (const n of nodes) {
                for (let i of (n.in ?? [])) {
                    add_conn(+i, n.skill!);
                }
                for (let i of (n.in ?? [])) {
                    add_conn(+i, n.skill!);
                }
            }
        } else if (path.indexOf("/assets/") != -1) {
            const name = path.split("/").pop()!;
            let ext = name.split(".").pop()!;
            if (ext == "jpg") ext = "jpeg";
            const b64 = await file.async("base64");
            assets[name] = await Assets.load(`data:image/${ext};base64,${b64}`);
        }
    }

    console.log("reading zip entries");
    await Promise.all(Object.entries(zip.files).map(zip_entry));

    if (!raw_tree) {
        throw new Error("bad tree load");
    }

    console.log("loading textures");
    const zoom = raw_tree.imageZoomLevels[raw_tree.imageZoomLevels.length - 1];
    tree.scale = 1/zoom;

    const all_textures: { [index: string]: Texture } = {};

    loop:
        for (const [k, v] of Object.entries(raw_tree.sprites)) {
            const blacklisted_sprites = ["Inactive", "Bloodline"];
            for (const s of blacklisted_sprites) {
                if (k.includes(s))
                    continue loop;
            }

            let sheet = v[zoom];
            if (sheet === undefined) {
                if ("1" in v) {
                    sheet = v['1'];
                } else {
                    throw new Error("bad sprite!");
                }
            }
            const assetName = new URL(sheet.filename).pathname.split('/').pop()!;
            const texture = assets[assetName];
            // Assets.add({
            //     alias:
            // })

            const atlas: SpritesheetData = {
                animations: {},
                meta: {
                    scale: 1,
                },
                frames: {}
            };
            // console.log(k, sheet.filename, assetName, sheet);
            for (const [alias, coords] of Object.entries(sheet.coords)) {
                atlas.frames[alias] = {frame: coords};
            }
            const ss = new Spritesheet(texture, atlas);
            const textures = await ss.parse();
            for (const [name, texture] of Object.entries(textures)) {
                all_textures[name] = texture;
            }
        }

    console.log("finished loading zip");

    console.log("load test", all_textures["Art/2DArt/SkillIcons/passives/Chieftain/LifeRegenStrength.png"]);

    return {
        tree,
        textures: all_textures
    };
}

export async function loadTree(): Promise<passive_tree_json_type> {
    const f = await fetch("data-extract/tree-export-default.json");
    return await f.json() as passive_tree_json_type;
    // return loadTreeData as passive_tree_json_type;
}


export async function loadTreeIcons() {
    const resp = await fetch("data-extract/art-extract-default.json");
    const tree_raw_images = await resp.json();
    // const tree_raw_images = loadTreeIconsData;

    await Promise.all(Object.entries(tree_raw_images).map(async ([k, v]) => {
        Assets.add({
            alias: k,
            src: `data:image/png;base64,${v}`,
        });
    }));
}
