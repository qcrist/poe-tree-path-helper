import styled from "styled-components";
import React, {useState} from "react";
import {useStore} from "zustand";
import {store} from "#src/ui/state/store";
import {StyledButton} from "#src/ui/components/ResetSelection";

const HelpContainer = styled.span`
    display: inline-block;
    border: 1px solid rgb(175, 96, 37);
    background: rgb(100, 54, 22);
    color: rgb(210, 202, 196);
`;

const HelpWrapper = styled.span`
    display: inline-flex;
    padding: .3em;
    margin: .3em;
    position: absolute;
    user-select: none;
    right: 0;
    bottom: 0;
    flex-direction: column-reverse;
`;


const HelpParagraph = styled.span`
    display: block;
    padding: .5em;
`;


export function Help() {
    const [showHelp, setShowHelp] = useState<boolean>(true);

    return <HelpWrapper>
        <StyledButton onClick={_ => {
            setShowHelp(!showHelp);
        }}>Toggle Help</StyledButton>
        {showHelp && <HelpContainer>
            <div>
                <HelpParagraph>Left click a node to select/deselect it</HelpParagraph>
                <HelpParagraph>Right click a node to block/unblock it</HelpParagraph>
                <HelpParagraph>Middle click a node to [TODO]</HelpParagraph>
                <HelpParagraph>Click a class start location to select a class</HelpParagraph>
            </div>
        </HelpContainer>}
    </HelpWrapper>;
}