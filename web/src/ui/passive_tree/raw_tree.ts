import {Assets} from "pixi.js";

export type passive_tree_json_node = {
    id: number,
    // position: number,
    // radius: number,
    icon: string,
    mastery_icon: string;
    x: number,
    y: number,
    // skillType: number,
    isAscendancyStartingNode: boolean,
    isNotable: boolean,
    isKeystone: boolean,
    // isMultipleChoice: boolean,
    isJewelSocket: boolean,
    isRootNode: boolean,
    isJustIcon: boolean,
    // connections: {
    //     id: number,
    //     radius: number
    // }[]
    connections: number[],
    name: string,
    stats: string[]
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
    frames: raw_frames_t
}

// import loadTreeData from "#data/tree-export-default.json";
// import loadTreeIconsData from "#data/art-extract-default.json";

export async function loadTree(): Promise<passive_tree_json_type> {
    const f = await fetch("tree-export-default.json");
    return await f.json() as passive_tree_json_type;
    // return loadTreeData as passive_tree_json_type;
}


export async function loadTreeIcons() {
    const resp = await fetch("art-extract-default.json");
    const tree_raw_images = await resp.json();
    // const tree_raw_images = loadTreeIconsData;

    await Promise.all(Object.entries(tree_raw_images).map(async ([k, v]) => {
        Assets.add({
            alias: k,
            src: `data:image/png;base64,${v}`,
        });
    }));
}
