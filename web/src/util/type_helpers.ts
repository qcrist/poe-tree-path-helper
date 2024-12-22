type StringToChars<T extends string> =
    string extends T ? string[] :
        T extends `${infer C0}${infer C1}${infer C2}${infer C3}${infer C4}${infer C5}${infer R}` ? [C0, C1, C2, C3, C4, C5, ...StringToChars<R>] :
            T extends `${infer C0}${infer C1}${infer C2}${infer C3}${infer R}` ? [C0, C1, C2, C3, ...StringToChars<R>] :
                T extends `${infer C0}${infer R}` ? [C0, ...StringToChars<R>] : []