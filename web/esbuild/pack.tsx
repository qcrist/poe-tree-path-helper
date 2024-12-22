import ReactDOMServer from 'react-dom/server';
import React from "react";
import * as fs from "node:fs";
import * as path from "node:path";

type build_data_e = { data: Uint8Array, hash: string };
export type build_data_t = { [k: string]: build_data_e | undefined };

const decoder = new TextDecoder();

function asString(data: build_data_t, p: string) {
    const read = data[path.normalize(p)];
    if (read === undefined)
        throw new Error("no data!");
    return {__html: decoder.decode(read.data)};
}

export function packHTML(data: build_data_t) {
    console.log(Object.keys(data));
    const html = <React.Fragment>
        <html lang="en">
        <head>
            <meta charSet={"UTF-8"}/>
            <style dangerouslySetInnerHTML={asString(data, "web/src/ui/main.css")}/>
            <title>TreeSolver</title>
        </head>
        <body>
        <div id="content_root"></div>
        <script dangerouslySetInnerHTML={asString(data, "web/src/ui/main.js")}/>
        </body>
        </html>
    </React.Fragment>;
    const output_dir = "dist";
    if (!fs.existsSync(output_dir)) fs.mkdirSync(output_dir);
    const rendered = "<!doctype html>" + ReactDOMServer.renderToString(html);
    fs.writeFileSync(path.join(output_dir, "index.html"), rendered);
}

