package net.qcrist.poetool2024.solver;

import net.qcrist.poetool2024.Graph;
import net.qcrist.poetool2024.simplify.TreeSimplify1;
import net.qcrist.poetool2024.structure.IntSetList;
import net.qcrist.poetool2024.util.Util;

import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.SplittableRandom;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.IntStream;

import static net.qcrist.poetool2024.solver.SteinerSolverFullOpt1.is_terminal;

public class RandomPathSolver2 extends RandomPathSolver1 {
    @Override
    protected int limit() {
        return 100_000;
    }

    @Override
    public int[] solve(int[][] conn, int[][] dist, int[] sel) {
        verify_paths(conn, dist);

        boolean[] important_nodes = TreeSimplify1.simp(conn, dist, sel);
        int[] steiner_nodes = IntStream.range(0, important_nodes.length).filter(x -> important_nodes[x]).toArray();

        SplittableRandom r = new SplittableRandom(0);
        Util.MinISingle<int[]> minPath = new Util.MinISingle<>();
        IntSetList connected = new IntSetList(conn.length);
        IntSetList important_connected = new IntSetList(conn.length);
        int[][][] p = paths;
        boolean[] added = connected.added;
//        Util.MinISingle<Integer> minConn = new Util.MinISingle<>();
        int cap = limit() / steiner_nodes.length;
        for (int a : steiner_nodes) {
            int[] order = Arrays.copyOf(sel, sel.length + 1);
            order[sel.length] = a;
            for (int x = 0; x < cap; x++) {
                shuffleArray(order, r);
                connected.clear();
                important_connected.clear();
                connected.add(order[0]);
                important_connected.add(order[0]);
                for (int n : order) {
                    if (added[n]) continue;
                    int minDist = Integer.MAX_VALUE;
                    int minNode = -1;
//                minConn.reset();
                    int[] dn = dist[n];
                    for (int v : important_connected) {
                        int d = dn[v];
//                    minConn.offer(d, v);
                        if (d < minDist) {
                            minDist = d;
                            minNode = v;
                        }
                    }
//                for (int i : paths[n][minConn.get()]) {
                    for (int i : p[n][minNode]) {
                        connected.add(i);
                        if (important_nodes[i])
                            important_connected.add(i);
                    }
//                    if (test + minDist != connected.length){
//                        int[] path = p[n][minNode];
//                        System.out.println(1);
//                    }
                    if (connected.length >= minPath.min)
                        break;
                }
                minPath.offer(connected.length, connected::toArray);
            }

        }

        return minPath.get();
    }

}
