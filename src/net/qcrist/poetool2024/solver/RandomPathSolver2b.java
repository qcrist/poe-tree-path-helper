package net.qcrist.poetool2024.solver;

import net.qcrist.poetool2024.Graph;
import net.qcrist.poetool2024.simplify.*;
import net.qcrist.poetool2024.structure.IntSetList;
import net.qcrist.poetool2024.structure.ShortSetList;
import net.qcrist.poetool2024.util.Util;

import java.util.Arrays;
import java.util.SplittableRandom;
import java.util.stream.IntStream;

public class RandomPathSolver2b implements Solver2 {
    private static final int limit = 100_000;

    short[][][] paths = null;

    @Override
    public void init(short[][] conn, byte[][] dist) {
        paths = Paths.makePaths(conn, dist);
    }

    public short[] solve(short[][] conn, byte[][] dist, short[] sel) {
        boolean[] important_nodes = TreeSimplify3.simp(conn, dist, sel, 0);
//        boolean[] important_nodes = TreeSimplify1b.simp(conn, dist, sel);
        int[] steiner_nodes = IntStream.range(0, important_nodes.length).filter(x -> important_nodes[x]).toArray();

        SplittableRandom r = new SplittableRandom(0);
        Util.MinISingle<short[]> minPath = new Util.MinISingle<>();
        ShortSetList connected = new ShortSetList(conn.length);
        ShortSetList important_connected = new ShortSetList(conn.length);
        boolean[] added = connected.added;
//        Util.MinISingle<Integer> minConn = new Util.MinISingle<>();
//        int cap = limit / steiner_nodes.length;
        int cap = 100;
        short[][][] p = this.paths;

        for (int a : steiner_nodes) {
            short[] order = Arrays.copyOf(sel, sel.length + 1);
            order[sel.length] = (short) a;
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
                    byte[] dn = dist[n];
                    for (int v : important_connected) {
                        int d = dn[v];
//                    minConn.offer(d, v);
                        if (d < minDist) {
                            minDist = d;
                            minNode = v;
                        }
                    }
//                for (int i : paths[n][minConn.get()]) {
                    for (short i : p[n][minNode]) {
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

    public static void shuffleArray(short[] ar, SplittableRandom r) {
        int c = ar.length;
        for (int i = c - 1; i > 0; i--) {
//            int index = (r.nextInt() & 0x7FFFFFFF) % i;
            int index = r.nextInt(i);
            // Simple swap
            short a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }
}
