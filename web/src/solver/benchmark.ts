
import bm from "../../../bm-325.json";


export type BenchmarkData = {
    tree: number[][];
    tests: {
        len: number;
        target: number[];
    }[];
}

export const BM325_DATA = bm as BenchmarkData;