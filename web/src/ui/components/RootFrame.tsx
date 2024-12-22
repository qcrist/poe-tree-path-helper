import React, {useEffect, useRef} from "react";
import {SelectedNodes} from "#src/ui/components/SelectedNodes";
import {ResetSelection} from "#src/ui/components/ResetSelection";
import {SearchBox} from "#src/ui/components/SearchBox";
import {Tooltip} from "#src/ui/components/Tooltip";
import {Help} from "#src/ui/components/Help";
import {ProgressBar} from "#src/ui/components/Progress";

export function RootFrame() {
    return <>
        <SelectedNodes/>
        <ResetSelection/>
        <SearchBox/>
        <Tooltip/>
        <Help/>
        <ProgressBar/>
    </>;
}
