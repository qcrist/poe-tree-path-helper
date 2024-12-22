import React, {useEffect, useRef, useState} from "react";
import styled from "styled-components";
import {SelectionType, subscribeAndRunNow} from "#src/ui/state/store";

const SelectedNodeContainer = styled.div`
    display: flex;
    justify-content: center;
    padding: .2em;
    user-select: none;
`;

const SelectedNodeBox = styled.div`
    display: inline-block;
    border: 1px solid rgb(175, 96, 37);
    background: rgb(100, 54, 22);
    color: rgb(210, 202, 196);
    padding: .3em;
    margin: .3em;
`;

function iter_count<T>(iter: IteratorObject<T>) {
    //todo there must be a better way
    return [...iter].length;
}

export function SelectedNodes() {
    const selectedRef = useRef<HTMLSpanElement>(null);
    const pathedRef = useRef<HTMLSpanElement>(null);

    useEffect(() => {
        return subscribeAndRunNow(({selection, pathNodes}) => {
            const sel = [...selection.values()];
            const root_count = sel.filter(x => x == SelectionType.ROOT).length;
            const sel_count = sel.filter(x => x == SelectionType.DIRECT).length;
            pathedRef.current!.innerText = String(pathNodes.size - root_count);
            selectedRef.current!.innerText = String(sel_count);
        });
    }, []);

    return <SelectedNodeContainer>
        <span>
            <SelectedNodeBox>Nodes</SelectedNodeBox>
            <SelectedNodeBox>Selected <span ref={selectedRef}>##</span></SelectedNodeBox>
            <SelectedNodeBox>Pathed <span ref={pathedRef}>##</span></SelectedNodeBox>
        </span>
    </SelectedNodeContainer>;
}
