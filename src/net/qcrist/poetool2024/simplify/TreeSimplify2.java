package net.qcrist.poetool2024.simplify;

import net.qcrist.poetool2024.structure.FixedIntList;
import net.qcrist.poetool2024.structure.IntSetList;

import java.util.Arrays;

public class TreeSimplify2 {

    //todo seems worse than v1 in most cases, scaling and performance

    public static boolean[] simp(short[][] conn, byte[][] dist, short[] sel, int acc) {
        boolean[] reach = new boolean[conn.length];
        loop_i:
        for (int i = 0; i < conn.length; i++) {
            byte[] di = dist[i];
            for (int a = 0; a < sel.length; a++) {
                short sa = sel[a];
                byte[] dsa = dist[sa];
                for (int b = a + 1; b < sel.length; b++) {
                    short sb = sel[b];
                    if (di[sa] + di[sb] <= dsa[sb] + acc) {
                        reach[i] = true;
                        continue loop_i;
                    }
                }
            }
        }

//        if (null == null) return reach;

//        boolean[] reach = list.added;
        for (int j : sel) {
            if (!reach[j])
                throw new Error("!");
        }

        boolean[] reach2 = new boolean[reach.length];
        for (int j : sel) {
            reach2[j] = true;
        }

        for (int i = 0; i < reach.length; i++) {
            if (reach[i]) {
                if (conn[i].length <= 2)
                    continue;

                int c = 0;
                for (short v : conn[i]) {
                    if (reach[v])
                        c++;
                }
                if (c <= 2)
                    continue;

                reach2[i] = true;
            }
        }

        return reach2;
    }

}
