import * as comlink from "comlink";
import {TreeSolver} from "#src/solver/solver";

if (self.constructor.name === "DedicatedWorkerGlobalScope") {
    console.log("READY!");
    comlink.expose(TreeSolver);
}

