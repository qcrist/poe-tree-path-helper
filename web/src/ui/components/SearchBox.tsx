import styled from "styled-components";
import React from "react";
import {useStore} from "zustand";
import {store} from "#src/ui/state/store";

const SearchInputBox = styled.input`
    display: inline-block;
    border: 1px solid rgb(175, 96, 37);
    background: rgb(100, 54, 22);
    color: rgb(210, 202, 196);
    padding: .3em;
    margin: .3em;
    position: absolute;
    top: 0;
    right: 0;
    pointer-events: initial;
    cursor: pointer;
    user-select: none;
`;


export function SearchBox() {
    const setSearch = useStore(store, x => x.setSearch);

    return <SearchInputBox type="text" placeholder={"Search for nodes"} onChange={e => {
        setSearch(e.currentTarget.value);
    }} onKeyDown={e => {
        if (e.key === "Escape") {
            setSearch("");
            e.currentTarget.value = "";
            e.currentTarget.blur();
        }
    }}/>;
}