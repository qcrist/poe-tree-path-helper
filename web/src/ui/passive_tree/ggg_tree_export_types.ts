//https://app.quicktype.io/?l=ts

export type SkillTreeExport = {
    readonly tree:                  string;
    readonly classes:               Class[];
    readonly alternateAscendancies: AlternateAscendancyElement[];
    readonly groups:                { [key: string]: Group };
    readonly nodes:                 { [key: string]: Node };
    readonly extraImages:           { [key: string]: ExtraImage };
    readonly jewelSlots:            number[];
    readonly minX:                  number;
    readonly minY:                  number;
    readonly maxX:                  number;
    readonly maxY:                  number;
    readonly constants:             Constants;
    readonly sprites:               Sprites;
    readonly imageZoomLevels:       number[];
    readonly points:                Points;
}

export type AlternateAscendancyElement = {
    readonly id:                 string;
    readonly name:               string;
    readonly flavourText?:       string;
    readonly flavourTextColour?: string;
    readonly flavourTextRect?:   FlavourTextRect;
}

export type FlavourTextRect = {
    readonly x:      number;
    readonly y:      number;
    readonly width:  number;
    readonly height: number;
}

export type Class = {
    readonly name:         string;
    readonly baseStr:      number;
    readonly baseDex:      number;
    readonly baseInt:      number;
    readonly ascendancies: AlternateAscendancyElement[];
}

export type Constants = {
    readonly classes:              Classes;
    readonly characterAttributes:  CharacterAttributes;
    readonly pssCentreInnerRadius: number;
    readonly skillsPerOrbit:       number[];
    readonly orbitRadii:           number[];
}

export type CharacterAttributes = {
    readonly strength:     number;
    readonly dexterity:    number;
    readonly intelligence: number;
}

export type Classes = {
    readonly strDexIntClass: number;
    readonly strClass:       number;
    readonly dexClass:       number;
    readonly intClass:       number;
    readonly strDexClass:    number;
    readonly strIntClass:    number;
    readonly dexIntClass:    number;
}

export type ExtraImage = {
    readonly x:     number;
    readonly y:     number;
    readonly image: string;
}

export type Group = {
    readonly x:           number;
    readonly y:           number;
    readonly orbits:      number[];
    readonly nodes:       string[];
    readonly background?: GroupBackground;
    readonly isProxy?:    boolean;
}

export type GroupBackground = {
    readonly image:        Image;
    readonly isHalfImage?: boolean;
}

export type Image = "PSGroupBackground3" | "PSGroupBackground1" | "PSGroupBackground2";

export type Node = {
    readonly skill?:                  number;
    readonly name:                   string;
    readonly icon:                   string;
    readonly isNotable?:              boolean;
    readonly recipe?:                 Recipe[];
    readonly stats?:                  string[];
    readonly group?:                  number;
    readonly orbit?:                  number;
    readonly orbitIndex?:             number;
    readonly out?:                    string[];
    readonly in?:                     string[];
    readonly reminderText?:           string[];
    readonly isMastery?:              boolean;
    readonly inactiveIcon?:           string;
    readonly activeIcon?:             string;
    readonly activeEffectImage?:      string;
    readonly masteryEffects?:         MasteryEffect[];
    readonly grantedStrength?:        number;
    readonly ascendancyName?:         string;
    readonly isBlighted?:             boolean;
    readonly grantedDexterity?:       number;
    readonly isAscendancyStart?:      boolean;
    readonly isBloodline?:            boolean;
    readonly isMultipleChoice?:       boolean;
    readonly grantedIntelligence?:    number;
    readonly isJewelSocket?:          boolean;
    readonly expansionJewel?:         ExpansionJewel;
    readonly grantedPassivePoints?:   number;
    readonly isKeystone?:             boolean;
    readonly flavourText?:            string[];
    readonly isProxy?:                boolean;
    readonly isMultipleChoiceOption?: boolean;
    readonly classStartIndex?:        number;
}

export type ExpansionJewel = {
    readonly size:    number;
    readonly index:   number;
    readonly proxy:   string;
    readonly parent?: string;
}

export type MasteryEffect = {
    readonly effect:        number;
    readonly stats:         string[];
    readonly reminderText?: string[];
}

export type Recipe = "ClearOil" | "AzureOil" | "BlackOil" | "PrismaticOil" | "SilverOil" | "AmberOil" | "SepiaOil" | "IndigoOil" | "OpalescentOil" | "TealOil" | "VerdantOil" | "VioletOil" | "GoldenOil" | "CrimsonOil";

export type Points = {
    readonly totalPoints:      number;
    readonly ascendancyPoints: number;
}

export type SpritesDef = { [key: string]: SpriteSheet};

export type Sprites = {
    readonly background:              SpritesDef;
    readonly normalActive:            SpritesDef;
    readonly notableActive:           SpritesDef;
    readonly keystoneActive:          SpritesDef;
    readonly normalInactive:          SpritesDef;
    readonly notableInactive:         SpritesDef;
    readonly keystoneInactive:        SpritesDef;
    readonly mastery:                 SpritesDef;
    readonly masteryConnected:        SpritesDef;
    readonly masteryActiveSelected:   SpritesDef;
    readonly masteryInactive:         SpritesDef;
    readonly masteryActiveEffect:     SpritesDef;
    readonly tattooActiveEffect:      SpritesDef;
    readonly ascendancy:              SpritesDef;
    readonly azmeriBloodline:         SpritesDef;
    readonly trialmasterBloodline:    SpritesDef;
    readonly oshabiBloodline:         SpritesDef;
    readonly olrothBloodline:         SpritesDef;
    readonly lyciaBloodline:          SpritesDef;
    readonly kingInTheMistsBloodline: SpritesDef;
    readonly farrulBloodline:         SpritesDef;
    readonly deliriousBloodline:      SpritesDef;
    readonly catarinaBloodline:       SpritesDef;
    readonly breachlordBloodline:     SpritesDef;
    readonly aulBloodline:            SpritesDef;
    readonly startNode:               SpritesDef;
    readonly groupBackground:         SpritesDef;
    readonly frame:                   SpritesDef;
    readonly jewel:                   SpritesDef;
    readonly line:                    SpritesDef;
    readonly jewelRadius:             SpritesDef;
}

export type Coord = {
    readonly x: number;
    readonly y: number;
    readonly w: number;
    readonly h: number;
}

export type SpriteSheet = {
    readonly filename: string;
    readonly w:        number;
    readonly h:        number;
    readonly coords:   { [key: string]: Coord };
}
