package net.qcrist.poetool2024.solver;

import net.qcrist.poetool2024.structure.IntSetList;

import java.util.*;
import java.util.stream.IntStream;

public class SolverAnneal extends SteinerSolverFullOpt3 {


    private static boolean[] reachable(int[][] conn, int start) {
        IntSetList seen = new IntSetList(conn.length);
        int at = 0;
        seen.add(start);
        while (at < seen.length) {
            int read = seen.data[at++];
            for (int c : conn[read]) {
                seen.add(c);
            }
        }
        return seen.added;
    }

    private int[] solve$(int[][] conn, int[][] dist, int[] sel) {
        //        if (null == null)
//            return simp(conn, dist, sel);

//        if (sel.length < 9) {
//            return super.solve(conn, dist, sel);
//        }

//        int[] useful = this.getUsefulNodes(conn, dist, sel);

        int node_count = conn.length;
        boolean[] is_selection = new boolean[node_count];
        boolean[] is_enabled = reachable(conn, sel[0]);

        Random r = new Random();

//        TODO simplify tree

        for (int i : sel) {
            is_selection[i] = true;
        }
//        Arrays.fill(is_enabled, true);

        for (int i = 0; i < 50; i++)
            while (true) {
                int random_node = r.nextInt(node_count);
                if (!is_enabled[random_node] || is_selection[random_node]) continue;

                is_enabled[random_node] = false;
                if (isConnected(conn, sel, is_selection, is_enabled))
                    continue;
                is_enabled[random_node] = true;
                break;
            }

        System.out.println("isConnected(conn, sel, is_selection, is_enabled) = " + isConnected(conn, sel, is_selection, is_enabled));

        return IntStream.range(0, node_count).filter(x -> is_enabled[x]).toArray();

//        return new int[0];
    }


    @Override
    public int[] solve(int[][] conn, int[][] dist, int[] sel) {
//        Graph g = TreeSimplify1.simp(conn, dist, sel);
//        return solve$(g.conn, g.dist, sel);

//        return solve$(conn, dist, sel);
        return new int[0];
    }

    public static boolean isConnected(int[][] conn, int[] sel, boolean[] is_sel, boolean[] enabled) {
        IntSetList seen = new IntSetList(conn.length);
        int at = 0;
        int sel_seen = 1;
        seen.add(sel[0]);
        while (at < seen.length) {
            int read = seen.data[at++];
            for (int c : conn[read]) {
                if (enabled[c] && seen.add(c)) {
                    if (is_sel[c] && ++sel_seen == sel.length) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
