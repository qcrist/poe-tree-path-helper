import styled from "styled-components";
import React from "react";
import {useStore} from "zustand";
import {store} from "#src/ui/state/store";

const ToolTipContainer = styled.span`
    display: inline-block;
    border: 1px solid rgb(175, 96, 37);
    background: rgb(100, 54, 22);
    color: rgb(210, 202, 196);
    padding: .3em;
    margin: .3em;
    position: absolute;
    user-select: none;
`;


export function Tooltip() {
    const tooltip = useStore(store, x => x.tooltip);

    if (tooltip === null)
        return <></>;

    return <ToolTipContainer style={{left: tooltip.x, bottom: `calc(100% - ${tooltip.y}px)`}}>
        <div style={{fontWeight: "bold", fontSize: "1.2em"}}>{tooltip.title}</div>
        <hr/>
        {tooltip.lines.flatMap(line => line.split("\n")).map((data, i) => <div key={i}>{data}</div>)}
    </ToolTipContainer>;
}