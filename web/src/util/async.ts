export function handleErrors(promise: Promise<any>) {
    promise.catch(err => {
        console.error(err);
    });
}

export async function wait_obj<T extends {
    [k: string]: Promise<any>
}>(obj: T): Promise<{ [K in keyof T]: Awaited<T[K]> }> {
    const result: any = {};

    await Promise.all(
        Object.entries(obj).map(async ([k, v]) => {
            result[k] = await v;
        }));

    return result;
}
