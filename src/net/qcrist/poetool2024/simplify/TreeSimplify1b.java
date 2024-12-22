package net.qcrist.poetool2024.simplify;

import net.qcrist.poetool2024.structure.FixedIntList;
import net.qcrist.poetool2024.structure.FixedShortList;
import net.qcrist.poetool2024.structure.IntSetList;
import net.qcrist.poetool2024.structure.ShortSetList;

import java.util.Arrays;

public class TreeSimplify1b {

    //

    private static boolean[] reachable(FixedShortList[] map, short start) {
        ShortSetList seen = new ShortSetList(map.length);
        int at = 0;
        seen.add(start);
        while (at < seen.length) {
            int read = seen.data[at++];
            FixedShortList list = map[read];
            for (int i = 0; i < list.length; i++) {
                seen.add(list.data[i]);
            }
        }
        return seen.added;
    }

    public static boolean[] simp(short[][] conn, byte[][] dist, short[] sel) {
        FixedShortList[] map = new FixedShortList[conn.length];
        for (int a = 0; a < conn.length; a++) {
            map[a] = new FixedShortList(9);
        }

        for (short a = 0; a < conn.length; a++) {
            byte[] da = dist[a];
            for (short b : conn[a]) {
                if (a >= b) continue;
                byte[] db = dist[b];

                boolean haveAB = false;
                boolean haveBA = false;
                for (int i : sel) {
                    int rA = da[i];
                    int rB = db[i];
                    if (rA == rB) {
                        haveAB = haveBA = true;
                        break;
                    } else if (rA < rB)
                        haveAB = true;
                    else// if (rA > rB)
                        haveBA = true;
                }

                if (haveAB && haveBA) {
                    map[a].add(b);
                    map[b].add(a);
                }
            }
        }

        boolean[] reach = reachable(map, sel[0]);
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
                int c =0 ;
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
//
//        int[][] connNew = new int[conn.length][];
//        int[] EMPTY = {};
//        boolean changed = false;
//        for (int i = 0; i < connNew.length; i++) {
//            if (reach[i])
//                connNew[i] = map.get(i).stream().mapToInt(x -> x).toArray();
//            else {
//                connNew[i] = EMPTY;
//            }
//            if (connNew[i].length != conn[i].length)
//                changed = true;
//        }
//
//        Graph g = new Graph(connNew, null, null);
//        if (changed) {
////            return simp(g.conn, g.dist, sel);
//        }
//        return g;
    }

}
