package net.qcrist.poetool2024.solver;

import net.qcrist.poetool2024.util.Util;
import net.qcrist.poetool2024.simplify.TreeSimplify1;
import net.qcrist.poetool2024.structure.FixedIntList;
import net.qcrist.poetool2024.structure.IntSetList;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;
import java.util.stream.IntStream;

public class SimpleSolver1 implements Solver {
    @Override
    public int[] solve(int[][] conn, int[][] dist, int[] sel) {
        boolean[] en = TreeSimplify1.simp(conn, dist, sel);
        boolean[] is_sel = new boolean[conn.length];
        for (int i : sel) {
            is_sel[i] = true;
        }
        int[] points_of_interest = IntStream.range(0, en.length).filter(i -> en[i] && !is_sel[i]).toArray();

        Util.MinISingle<int[]> minPath = new Util.MinISingle<>();

        int c = 0;
        for (int n = 0; n <= 3; n++) {
            int[][] perms = perm(points_of_interest.length, n);

            for (int[] perm : perms) {
                int[] interest = new int[perm.length + sel.length];
                for (int i = 0; i < perm.length; i++) {
                    interest[i] = points_of_interest[perm[i]];
                }
                System.arraycopy(sel, 0, interest, perm.length, sel.length);

                IntSetList path = new IntSetList(conn.length);
                path.add(sel[0]);
                IntSetList added = new IntSetList(conn.length);
                added.add(sel[0]);

                List<int[]> list = new ArrayList<>(interest.length * interest.length / 2 + 1);
                for (int a = 0; a < interest.length; a++) {
                    int aV = interest[a];
                    for (int b = a + 1; b < interest.length; b++) {
                        c++;
                        int bV = interest[b];
                        int d = dist[aV][bV];
                        int[] it = new int[]{d, aV, bV};
                        list.add(it);
                    }
                }
//                System.out.println("list.size() = " + list.size());

//
//                while (added.length != interest.length) {
//                    int minV = Integer.MAX_VALUE;
//                    int minA = -1;
//                    int minB = -1;
//                    for (int i = 0; i < added.length; i++) {
//                        int a = added.data[i];
//                        for (int b : interest) {
//                            if (added.added[b]) continue;
//                            if (dist[a][b] < minV) {
//                                minV = dist[a][b];
//                                minA = a;
//                                minB = b;
//                            }
//                        }
//                    }
//                    added.add(minB);
//                    int[] p = path(conn, dist, minA, minB);
//                    for (int i : p) {
//                        path.add(i);
//                    }
//                }
//
//                minPath.offer(path.length, path::toArray);
            }
        }

        System.out.println("c = " + c);
//        return minPath.get();
        return new int[0];
    }

    private static int[] findNext(int[][] conn, IntSetList path, boolean[] todo) {
        IntSetList seen = new IntSetList(conn.length);
        for (int i : path.toArray()) {
            seen.add(i);
        }
        int at = 0;
        int[] from = new int[conn.length];
        Arrays.fill(from, -1);
        while (at < seen.length) {
            int next = seen.data[at++];
            for (int c : conn[next]) {
                if (seen.add(c))
                    from[c] = next;
                if (todo[c]) {
                    FixedIntList p = new FixedIntList(at + 2);
                    p.add(c);
                    while (true) {
                        int last = p.last();
                        if (path.added[last])
                            return p.toArray();
                        p.add(from[last]);
                    }
                }
            }
        }
        throw new Error("not connected?");
    }

    private static int[] findNext2(int[][] conn, int[][] dist, IntSetList path, boolean[] todo) {
        Util.MinISingle<Pair<Integer, Integer>> closest = new Util.MinISingle<>();
        for (int i = 0; i < todo.length; i++) {
            if (todo[i]) {
                Util.MinISingle<Pair<Integer, Integer>> cPath = new Util.MinISingle<>();
                for (int x = 0; x < path.length; x++) {
                    int n = path.data[x];
                    cPath.offer(dist[i][n], Pair.of(n, i));
                }
                closest.offer(cPath);
            }
        }
        Pair<Integer, Integer> c = closest.get();
        return path(conn, dist, c.getLeft(), c.getRight());
    }

    private static int[] path(int[][] conn, int[][] dist, int a, int b) {
        int[] db = dist[b];
        int a_dist = db[a];
        int[] path = new int[a_dist + 1];
        path[0] = a;
        for (int i = 1; i < path.length; i++) {
            int prev = path[i - 1];
            int target_d = a_dist - i;
            for (int c : conn[prev]) {
                if (db[c] == target_d) {
                    path[i] = c;
                    break;
                }
            }
        }
        return path;
    }

    private static final Map<Pair<Integer, Integer>, int[][]> permMap = new HashMap<>();
    private static final Object PERM_MAP_LOCK = new Object[0];

    private static int[][] perm(int c, int n) {
        if (n <= 0)
            return new int[1][0];
//            throw new IllegalArgumentException("n must be > 0");

//        Pair<Integer, Integer> pair = Pair.of(c, n);
//
//        synchronized (PERM_MAP_LOCK) { //TODO terrible lock
//            if (permMap.containsKey(pair))
//                return permMap.get(pair);
//        }

//        int[][] array = Util.timeV(String.format("perm[c=%d,n=%d]", c, n), () -> {
        List<int[]> out = new ArrayList<>();
        int[] dst = new int[n];
        perm(c, n, 0, 0, dst, out);
//                int[][] array = out.toArray(int[][]::new);
        return out.toArray(int[][]::new);
//        });

//        synchronized (PERM_MAP_LOCK) { //TODO terrible lock
//            permMap.put(pair, array);
//
//            return array;
//        }
    }

    private static void perm(int c, int n, int at, int start, int[] dst, List<int[]> out) {
        if (at == n) {
            out.add(Arrays.copyOf(dst, n));
            return;
        }
        for (int i = start; i < c; i++) {
            dst[at] = i;
            perm(c, n, at + 1, i + 1, dst, out);
        }
    }

    public static void main(String[] args) {
        int[] x = {1, 2, 3, 4, 5};
        for (int[] ints : perm(5, 2)) {
            System.out.println(Arrays.toString(ints));
        }
    }
}
