package net.qcrist.poetool2024.solver;

import net.qcrist.poetool2024.Graph;
import net.qcrist.poetool2024.simplify.Paths;
import net.qcrist.poetool2024.simplify.TreeSimplify1;
import net.qcrist.poetool2024.structure.IntSetList;
import net.qcrist.poetool2024.util.Util;

import java.lang.ref.WeakReference;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class RandomPathSolver1 implements Solver {

    private WeakReference<int[][]> init_conn;
    private WeakReference<int[][]> init_dist;
    protected int[][][] paths;

    @Override
    public void init(Graph g) {
        init_conn = new WeakReference<>(g.conn);
        init_dist = new WeakReference<>(g.dist);

        int node_count = g.node_count;

        Runtime rt = Runtime.getRuntime();
        long mem = rt.totalMemory() - rt.freeMemory();

        this.paths = Paths.makePaths(g.conn, g.dist);

        long mem2 = rt.totalMemory() - rt.freeMemory();
        System.out.printf("Paths takes %.02fG\n", (mem2 - mem) / 1024d / 1024d / 1024d);
    }

    protected void verify_paths(int[][] conn, int[][] dist) {
        if (conn != init_conn.get() || dist != init_dist.get()) {
            throw new IllegalArgumentException("param should match init");
        }
    }

    protected int limit() {
        return 20_000;
    }

    @Override
    public int[] solve(int[][] conn, int[][] dist, int[] sel) {
        verify_paths(conn, dist);

        boolean[] important_nodes = TreeSimplify1.simp(conn, dist, sel);

        SplittableRandom r = new SplittableRandom(0);
        int[] order = sel.clone();
        Util.MinISingle<int[]> minPath = new Util.MinISingle<>();
        IntSetList connected = new IntSetList(conn.length);
        IntSetList important_connected = new IntSetList(conn.length);
        int[][][] p = paths;
        boolean[] added = connected.added;
//        Util.MinISingle<Integer> minConn = new Util.MinISingle<>();
        int cap = limit();
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
            }
            minPath.offer(connected.length, connected::toArray);
        }

        return minPath.get();
    }

    public static void shuffleArray(int[] ar, SplittableRandom r) {
        int c = ar.length;
        for (int i = c - 1; i > 0; i--) {
//            int index = (r.nextInt() & 0x7FFFFFFF) % i;
            int index = r.nextInt(i);
            // Simple swap
            int a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }


    public static void main(String[] args) {
        SplittableRandom r = new SplittableRandom();

        BiConsumer<String, Consumer<int[]>> test = (name, run) -> {
            Util.time(name, () -> {
                int[] arr = new int[4000];
                for (int i = 0; i < 400_000; i++) {
                    run.accept(arr);
                }
            });
        };

//        test.accept("shuffleArray", arr -> shuffleArray(arr, r));
    }
}
