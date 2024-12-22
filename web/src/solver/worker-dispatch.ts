import * as comlink from "comlink";
import worker_entry_source from "worker://solver";
import {TreeSolver} from "#src/solver/solver";

export type RemoteSolver = comlink.Remote<TreeSolver>;

export async function init_solve_worker(): Promise<RemoteSolver> {
    // const url = new URL("../worker/solve-worker.js", import.meta.url);
    // console.log(url);
    const blob = new Blob([worker_entry_source], {type: "application/json"});
    const worker = new Worker(URL.createObjectURL(blob));

    const ctx = comlink.wrap<typeof TreeSolver>(
        worker
    );

    return await (new ctx());
}
