import ImageMagick from 'magickwand.js';
// ImageMagick will be either a native library
// (if called from a Node.js application)
// or a WASM bundle (when bundled by a web bundler)
const {Magick, MagickCore} = await ImageMagick;

import tree_raw_images from "../../skill-tree-images-dds.json";
import * as fs from "node:fs";
import * as path from "node:path";

type raw_images_t = {
    [key: string]: {
        data: string,
        bounds?: {
            x: number,
            y: number,
            w: number,
            h: number
        }
    }
}

async function convertIcons() {
    const result: { [k: string]: string } = {};

    await Promise.all(Object.entries(tree_raw_images as raw_images_t).map(async ([k, v]) => {
        const arr = Uint8Array.from(atob(v.data), c => c.charCodeAt(0));

        // const res = parseDDS(arr.buffer);
        // if (res.length !== 1)
        //     throw new Error("failed to load: " + k);
        // const x = new BaseTexture(res[0], {alphaMode: ALPHA_MODES.NPM});
        // Texture.from({})
        // iconTextures.set(k, t);
        // const blob = new Blob([atob(v)], {type: "image/dds"});
        // const src = URL.createObjectURL(blob);
        const blob = new Magick.Blob(arr.buffer);
        const img = new Magick.Image(blob);
        const outBlob = new Magick.Blob();
        if (v.bounds) {
            img.crop(new Magick.Geometry(v.bounds.w, v.bounds.h, v.bounds.x, v.bounds.y));
        }
        await img.writeAsync(outBlob, "PNG");

        const data = new Uint8Array(outBlob.data());

        result[k] = Buffer.from(data).toString("base64");
    }));

    fs.writeFileSync("skill-tree-images.json", JSON.stringify(result));
    console.log("finished!");
}

async function convertDebug() {
    const root = "binary_wip";
    path.join();
    const files = fs.readdirSync(root);
    await Promise.all(files.map(async (k) => {
        if (!k.endsWith(".dds"))
            return;
        const d = fs.readFileSync(path.join(root, k));
        const arr = Uint8Array.from(d);
        // const res = parseDDS(arr.buffer);
        // if (res.length !== 1)
        //     throw new Error("failed to load: " + k);
        // const x = new BaseTexture(res[0], {alphaMode: ALPHA_MODES.NPM});
        // Texture.from({})
        // iconTextures.set(k, t);
        // const blob = new Blob([atob(v)], {type: "image/dds"});
        // const src = URL.createObjectURL(blob);
        const blob = new Magick.Blob(arr.buffer);
        const img = new Magick.Image(blob);
        const outBlob = new Magick.Blob();
        await img.writeAsync(outBlob, "PNG");

        const data = new Uint8Array(outBlob.data());
        fs.writeFileSync(path.join(root, k + ".png"), data);
    }));
}

await convertIcons();
await convertDebug();

