import {StoreApi} from "zustand";

export function subscribe_with_selector<T, S>(
    store: StoreApi<T>,
    selector: (t: T) => S,
    runner: (selection: S, state: T) => void) {
    let last: S;
    const handler = (state: T) => {
        const select = selector(state);
        if (last !== select) {
            last = select;
            runner(select, state);
        }
    };
    const destroy = store.subscribe(handler);
    const st = store.getState();
    runner(last = selector(st), st);
    return destroy;
}