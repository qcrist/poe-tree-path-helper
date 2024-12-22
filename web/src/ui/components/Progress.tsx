import styled from "styled-components";
import React, {useEffect, useState} from "react";
import {useStore} from "zustand";
import {store} from "#src/ui/state/store";
import {StyledButton} from "#src/ui/components/ResetSelection";
import {DecoratedBox} from "#src/ui/components/SelectedNodes";

const HelpContainer = styled.span`
    display: inline-block;
    border: 1px solid rgb(175, 96, 37);
    background: rgb(100, 54, 22);
    color: rgb(210, 202, 196);
`;

const ProgressWrapper = styled.span`
    display: inline-flex;
    padding: .3em;
    margin: .3em;
    position: absolute;
    user-select: none;
    left: 0;
    bottom: 0;
    flex-direction: column;
`;

const ProgressBarOuter = styled.span`
    display: inline-block;
    height: 1em;
    width: 10em;
    border: 1px solid rgb(210, 202, 196);
`;

const ProgressBarInner = styled.span`
    display: inline-block;
    height: 100%;
    background: rgba(210, 202, 196, 0.8);
`;

type PBImplParams = {
    progress: number
}

export function PBImpl({progress}: PBImplParams) {
    return <ProgressBarOuter>
        <ProgressBarInner style={{
            width: (progress * 100) + "%"
        }}/>
    </ProgressBarOuter>;
}

export type ProgressBarCallbackObj = {
    progress: number,
    finished: boolean
}
export type ProgressBarCallback = (st: ProgressBarCallbackObj) => void;

export function ProgressBar() {
    const setProgressFunction = useStore(store, s => s.setProgressFunction);
    const [progress, setProgress] = useState<ProgressBarCallbackObj>({
        finished: true,
        progress: 0
    });

    useEffect(() => {
        setProgressFunction(setProgress);
        return () => {
            setProgressFunction(null);
        };
    }, []);

    if (progress.finished)
        return <React.Fragment/>;

    return <ProgressWrapper>
        <DecoratedBox>Solve Progress <PBImpl progress={progress.progress}/></DecoratedBox>
    </ProgressWrapper>;
}