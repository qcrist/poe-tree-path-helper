import * as esbuild from 'esbuild';
import wasmLoader from "esbuild-plugin-wasm";
import {BuildOptions} from "esbuild";
import * as path from "node:path";
import {handleErrors} from "../src/util/async";
import {build_data_t, packHTML} from "./pack";

const production = process.argv[process.argv.length - 1] === "production";

function strip_path_extension(p: string) {
    return p.replace(/\.tsx?$/, ".js");
}

function finished_helper(): [() => void, Promise<void>] {
    let cb: () => void = () => {
        throw new Error("!");
    };

    const p = new Promise<void>(e => {
        cb = e;
    });
    return [cb, p];
}

async function main() {
    const WORKER_ENTRY = {
        "solver": "web/src/solver/worker-entry.ts"
    } as { [k: string]: string };
    const common_options = {
        sourcemap: production ? undefined : "inline",
        bundle: true,
        minify: production,
        format: "iife",
        treeShaking: true
    } as const satisfies Partial<BuildOptions>;
    const build_data: build_data_t = {};
    const WORKER_NAMESPACE = "worker";
    const out_dir = path.resolve("PREFIX");
    const [on_clean_build, wait_for_clean_build] = finished_helper();
    const ctx = await esbuild.context({
        entryPoints: ['web/src/ui/main.tsx', ...Object.values(WORKER_ENTRY)],
        write: false,
        outdir: out_dir,
        outbase: ".",
        plugins: [{
            name: 'main-plugin',
            setup(build) {
                build.onEnd(result => {
                    let should_rebuild = false;
                    for (let f of result.outputFiles!) {
                        const key = path.normalize(path.relative(out_dir, f.path));
                        const read = build_data[key];
                        if (read === undefined || read.hash !== f.hash) {
                            console.log("changed", key, f.hash, read?.hash);
                            build_data[key] = {
                                hash: f.hash,
                                data: f.contents
                            };
                            should_rebuild = true;
                        }
                    }
                    if (should_rebuild)
                        setTimeout(() => {
                            handleErrors(ctx.rebuild());
                        }, 100);
                    else {
                        packHTML(build_data);
                        on_clean_build();
                    }
                    console.log("built!", should_rebuild);
                });
                build.onResolve({
                    filter: /^worker:\/\//,
                }, obj => {
                    // console.log("hit worker resolve", obj.path);
                    return {
                        path: obj.path,
                        namespace: WORKER_NAMESPACE,
                        watchFiles: []//TODO
                    };
                });
                // build.onResolve({
                //     filter: /.*/,
                // }, obj => {
                //     console.log("OR", obj);
                //     return {};
                // });
                build.onLoad({
                    filter: /.*/,
                    namespace: "worker"
                }, async obj => {
                    const id = obj.path.split("://")[1];
                    const e = WORKER_ENTRY[id];
                    if (e === undefined)
                        throw new Error("unknown worker: " + id);
                    const lookup = strip_path_extension(path.normalize(e));
                    const data = build_data[lookup];
                    console.log("worker_load", id, data !== undefined, lookup);

                    // console.log("onload hit", obj, id);
                    // const result = await ctxWorker.rebuild();
                    // if (result.outputFiles?.length !== 1)
                    //     throw new Error("!");

                    return {
                        contents: data?.data ?? "[TMP_LOADING]",
                        loader: "text",
                        pluginData: "foo",

                    } satisfies esbuild.OnLoadResult;
                });
            }
        } satisfies esbuild.Plugin],
        // external: ["electron", "node:*", "#poe-tree-solver/*"],
        packages: "bundle",
        ...common_options
    });

    if (production) {
        await ctx.rebuild();
        await wait_for_clean_build;
        await ctx.dispose();
    } else {
        await Promise.all([
            ctx.watch(),
            ctx.serve({
                servedir: ".",
            }),
        ]);
    }
}

main().catch(err =>
    console.log(err)
);