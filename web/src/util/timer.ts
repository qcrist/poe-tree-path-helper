export type TimerObj = {
    finished(): void;
    print_current_dt(name: string): void;
    timeFn<T, A extends any[]>(run: (...a: A) => T, ...a: A): T;
    time<T>(name: string, run: (timer: TimerObj) => T): T;
    timeFnAsync<T, A extends any[]>(run: (...a: A) => Promise<T>, ...a: A): Promise<T>;
    timeAsync<T>(name: string, run: (timer: TimerObj) => Promise<T>): Promise<T>;
};

function chkName(fn: Function) {
    if (fn.name.length == 0)
        return "<anonymous>";
    return fn.name;
}

type named = string | { name: string };

function named_to_string(n: named) {
    return typeof n === "string" ? n : n.name;
}

function timer_impl(path: string[], impl_start: number): TimerObj {
    return {
        finished() {
            const end = performance.now();
            console.debug(`[${path.join(" > ")}] took ${Math.ceil(end - impl_start)}ms`);
        },
        time<T>(name: named, run: (timer: TimerObj) => T): T {
            const timer = timer_impl([...path, named_to_string(name)], performance.now());
            const result = run(timer);
            if (result instanceof Promise) {
                console.warn("passed promise to non async time");
            }
            timer.finished();
            return result;
        },
        timeFn<T, A extends any[]>(run: (...a: A) => T, ...a: A): T {
            return this.time(chkName(run), () => run(...a));
        },
        async timeAsync<T>(name: named, run: (timer: TimerObj) => Promise<T>): Promise<T> {
            const timer = timer_impl([...path, named_to_string(name)], performance.now());
            const result = await run(timer);
            if (result instanceof Promise) {
                console.warn("passed promise to non async time");
            }
            timer.finished();
            return result;
        },
        timeFnAsync<T, A extends any[]>(run: (...a: A) => Promise<T>, ...a: A): Promise<T> {
            return this.timeAsync(chkName(run), () => run(...a));
        },
        print_current_dt(name: named) {
            const end = performance.now();
            const n = named_to_string(name);
            console.debug(`[${path.join(" > ")}].${name} at ${Math.ceil(end - impl_start)}ms`);
        }
    };
}

export function new_timer(name: named): TimerObj {
    return timer_impl([named_to_string(name)], performance.now());
}

export function wait(ms: number): Promise<void> {
    return new Promise(cb => {
        setTimeout(cb, ms);
    });
}

export const time = ((...a: any[]) => {
    const t = timer_impl([], 0);
    return t.time.apply(t, a as any);
}) as TimerObj["time"];

export const timeAsync = ((...a: any[]) => {
    const t = timer_impl([], 0);
    return t.timeAsync.apply(t, a as any);
}) as TimerObj["timeAsync"];
