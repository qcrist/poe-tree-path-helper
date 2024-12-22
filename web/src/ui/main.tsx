import {createRoot} from "react-dom/client";
import {RootFrame} from "./components/RootFrame";
import React from "react";

import {enableMapSet} from "immer";
import {init_tree} from "#src/ui/passive_tree/passive_tree";
import "./main.css";

enableMapSet();

async function start() {
    const content_root = document.getElementById("content_root");
    if (!content_root) {
        throw new Error("missing root?");
    }
    const root = createRoot(content_root);
    root.render(<RootFrame/>);

    document.body.appendChild(await init_tree());
}

start().catch(err => {
    console.error("start error", err);
});
