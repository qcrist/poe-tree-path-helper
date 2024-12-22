import styled from "styled-components";
import React from "react";
import {useStore} from "zustand";
import {store} from "#src/ui/state/store";

export const StyledButton = styled.button`
    display: inline-block;
    border: 1px solid rgb(175, 96, 37);
    background: rgb(100, 54, 22);
    color: rgb(210, 202, 196);
    padding: .3em;
    margin: .3em;
    pointer-events: initial;
    cursor: pointer;
    user-select: none;
`;

export const ResetButton = styled(StyledButton)`
    position: absolute;
    top: 0;
    left: 0;
`;


export function ResetSelection() {
    const clearSelection = useStore(store, x => x.clearSelection);

    return <ResetButton onClick={() => {
        clearSelection();
    }}>Reset Tree</ResetButton>;
}