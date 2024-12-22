import {createStore, StateCreator} from "zustand";
import {Draft, Immutable, produce} from "immer";
import {Application, Container} from "pixi.js";

export const enum SelectionType {
    DIRECT,
    BLOCK,
    OPTIONAL,
    ROOT
}

export type TooltipData = {
    title: string,
    lines: string[],
    x: number,
    y: number
}

export type StoreType = {
    // x: number,
    // y: number,
    // s: number,
    selection: Map<number, SelectionType>,
    pathNodes: Set<number>,
    search: string;
    tooltip: TooltipData | null,
    // stageUpdated(app: Application): void;
    toggleNode(id: number, type: SelectionType): void;
    setNode(id: number, type: SelectionType): void;
    bulkSet(nodes: { [index: number]: SelectionType }): void;
    updatePathNodes(nodes: number[]): void;
    clearSelection(): void;
    setSearch(text: string): void;
    setTooltip(data: TooltipData | null): void;
}

type JSONSerializable = number | string | JSONSerializable[] | JSONSerializableDict;
type JSONSerializableDict = { [k: string]: JSONSerializable };

interface StoreSaveSerializer<T, J extends JSONSerializable> {
    toJsonValue(value: T): J;

    fromJsonValue(value: J): T;
}

class IdentitySerializer<T extends JSONSerializable> implements StoreSaveSerializer<T, T> {
    toJsonValue(value: T): T {
        return value;
    }

    fromJsonValue(value: T): T {
        return value;
    }
};

class SetSerializer<T, J extends JSONSerializable> implements StoreSaveSerializer<Set<T>, J[]> {
    constructor(
        private readonly serializer: StoreSaveSerializer<T, J>
    ) {
    }

    fromJsonValue(value: J[]): Set<T> {
        return new Set<T>(
            value.map(j => this.serializer.fromJsonValue(j))
        );
    }

    toJsonValue(value: Set<T>): J[] {
        return [...value].map(t => this.serializer.toJsonValue(t));
    }
}

type MapObj<VJ> = {
    [k: string]: VJ
};

class MapSerializer<K, V, VJ extends JSONSerializable> implements StoreSaveSerializer<Map<K, V>, MapObj<VJ>> {
    constructor(
        private readonly key_serializer: StoreSaveSerializer<K, string>,
        private readonly value_serializer: StoreSaveSerializer<V, VJ>,
    ) {
    }

    fromJsonValue(value: { [p: string]: VJ }): Map<K, V> {
        const map = new Map<K, V>();
        for (const [k, v] of Object.entries(value)) {
            map.set(this.key_serializer.fromJsonValue(k), this.value_serializer.fromJsonValue(v));
        }
        return map;
    }

    toJsonValue(value: Map<K, V>): { [p: string]: VJ } {
        const result: { [p: string]: VJ } = {};
        for (const [k, v] of value.entries()) {
            result[this.key_serializer.toJsonValue(k)] = this.value_serializer.toJsonValue(v);
        }
        return result;
    }
}

const NUMBER_SERIALIZER = new IdentitySerializer<number>();
const STRING_SERIALIZER = new IdentitySerializer<string>();

const NUMBER_TO_STRING_SERIALIZER: StoreSaveSerializer<number, string> = {
    fromJsonValue(value: string): number {
        return +value;
    }, toJsonValue(value: number): string {
        return value.toString();
    }
};


const STORE_SAVE_SERIALIZERS = {
    // x: NUMBER_SERIALIZER,
    // y: NUMBER_SERIALIZER,
    // s: NUMBER_SERIALIZER,
    selection: new MapSerializer(NUMBER_TO_STRING_SERIALIZER, NUMBER_SERIALIZER)
} as const satisfies { [K in keyof StoreType]?: StoreSaveSerializer<StoreType[K], any> };

let state_setter: ((r: StoreRecipe) => void) | null = null;

type StoreRecipe = (state: Draft<StoreType>) => void;

const initStore: StateCreator<Immutable<StoreType>> = (set_raw, get) => {
    function set(recipe: StoreRecipe) {
        set_raw(produce(recipe));
    }

    state_setter = set;

    const value: StoreType = {
        // x: 0,
        // y: 0,
        // s: 0.1,
        search: "",
        tooltip: null,
        selection: new Map(),
        pathNodes: new Set(),
        // stageUpdated(app: Application): void {
        //     set(state => {
        //         const {stage, screen} = app;
        //         state.x = stage.x - screen.width / 2;
        //         state.y = stage.y - screen.height / 2;
        //         state.s = stage.scale.x;
        //     });
        // },
        toggleNode(id, type) {
            set(state => {
                if (state.selection.has(id))
                    state.selection.delete(id);
                else
                    state.selection.set(id, type);
            });
        },
        setNode(id, type) {
            set(state => {
                state.selection.set(id, type);
            });
        },
        bulkSet(values) {
            set(state => {
                for (let [k, v] of Object.entries(values)) {
                    state.selection.set(+k, v);
                }
            });
        },
        updatePathNodes(nodes: number[]) {
            set(state => {
                state.pathNodes = new Set(nodes);
            });
        },
        clearSelection() {
            set(state => {
                state.selection.clear();
            });
        },
        setSearch(text: string) {
            set(state => {
                state.search = text;
            });
        },
        setTooltip(tooltip: TooltipData | null) {
            set(state => {
                state.tooltip = tooltip;
            });
        }
    };

    push_hash(value);

    return value;
};

export function subscribeAndRunNow(fn: (state: Immutable<StoreType>) => void) {
    store.subscribe(fn);
    fn(store.getState());
}

function push_hash(target: StoreType) {
    if (location.hash.length > 1) {
        const data = JSON.parse(atob(location.hash.substring(1)));
        for (const [k, s] of Object.entries(STORE_SAVE_SERIALIZERS)) {
            if (Object.hasOwn(data, k)) {
                target[k as keyof StoreType] = s.fromJsonValue(data[k]) as any;
            }
        }
    }
}

export const store = createStore(initStore);
const last_save: { [K in keyof typeof STORE_SAVE_SERIALIZERS]?: any } = {};

function update_hash(v: Immutable<StoreType>) {
    let changed = false;
    const fields = Object.keys(STORE_SAVE_SERIALIZERS) as (keyof typeof STORE_SAVE_SERIALIZERS)[];

    for (const k of fields) {
        if (last_save[k] !== v[k]) {
            changed = true;
            break;
        }
    }
    if (!changed) return;
    const obj = {} as JSONSerializableDict;
    for (const k of fields) {
        obj[k] = STORE_SAVE_SERIALIZERS[k].toJsonValue(v[k] as any);
    }
    // console.log("save", obj);
    const url = new URL(location.href);
    url.hash = btoa(JSON.stringify(obj));

    history.replaceState(null, "", url);
}

store.subscribe(v => {
    update_hash(v);
});
window.onhashchange = e => state_setter?.(state => {
    push_hash(state);
});