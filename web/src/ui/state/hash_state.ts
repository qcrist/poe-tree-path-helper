import {Immutable} from "immer";


export class HashState<T extends object> {
    private readonly current_state: T;

    constructor(default_value: T) {
        this.current_state = Object.assign({}, default_value);
        if (location.hash.length > 1) {
            const data = JSON.parse(atob(location.hash.substring(1)));
            Object.assign(this.current_state, data);
        }
    }

    set(partial: Partial<T>) {
        Object.assign(this.current_state, partial);
        location.hash = btoa(JSON.stringify(this.current_state));
    }

    get state() {
        return this.current_state;
    }
}