package net.qcrist.poetool2024.solver;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.IntStream;

public class SteinerSolverFullOpt1 implements Solver {
    //SteinerSolverFull + Cache optimization

    public static boolean[] is_terminal(int[][] conn, int[] terminals) {
        boolean[] is_sel = new boolean[conn.length];
        for (int i : terminals) {
            is_sel[i] = true;
        }
        return is_sel;
    }

    private int[] getUsefulNodes(int[][] conn, int[] terminals) {
        int node_count = conn.length;

        boolean[] is_sel = is_terminal(conn, terminals);
        return IntStream.range(0, node_count).filter(i -> is_sel[i] || conn[i].length > 2).toArray();
    }

    @Override
    public int[] solve(int[][] conn, int[][] dist, int[] terminals) {
        int term_count = terminals.length;

        int[] useful_nodes = getUsefulNodes(conn, terminals);

        int MAX = Integer.MAX_VALUE / 4;
        int mask_count = 1 << term_count;
        int[][] minCost = new int[mask_count][conn.length];
        int[][] backtraceMask = new int[mask_count][conn.length];
        int[][] backtraceNode = new int[mask_count][conn.length];
        for (int[] m : minCost)
            Arrays.fill(m, MAX);
        for (int i = 0; i < term_count; i++) {
            int mask = 1 << i;
            minCost[mask][terminals[i]] = 0;
            backtraceNode[mask][terminals[i]] = -1;
        }

//        System.out.printf("Steiner Complexity: (nodes=%d) * (terminals=%d)\n", useful_nodes.length, 1 << term_count);
//        net.qcrist.poetool2024.util.Util.time("steinerBusy", () -> {
        for (int mask = 0; mask < mask_count; mask++) {
            for (int i : useful_nodes) {
                for (int submask = mask; submask != 0; submask = (submask - 1) & mask) {
                    int v = minCost[submask][i] + minCost[mask ^ submask][i];
                    if (v < minCost[mask][i]) {
                        minCost[mask][i] = v;
                        backtraceNode[mask][i] = i;
                        backtraceMask[mask][i] = submask;
                    }
                }
                if (minCost[mask][i] < MAX) {
                    for (int j : useful_nodes) {
                        int v = minCost[mask][i] + dist[i][j];
                        if (v < minCost[mask][j]) {
                            minCost[mask][j] = v;
                            backtraceNode[mask][j] = i;
                            backtraceMask[mask][j] = mask;
                        }
                    }
                }
            }
        }

//        });

        int max_term = (mask_count) - 1;
//        System.out.println("term_count = " + term_count);
//        System.out.println("max_term = " + Integer.toBinaryString(max_term));

        int minFound = MAX;
        int minIdx = -1;
        for (int i = 0; i < conn.length; i++) {
            if (minCost[max_term][i] < minFound) {
                minFound = minCost[max_term][i];
                minIdx = i;
            }
        }

//        System.out.println("minFound = " + minFound);

        Set<Integer> test = new HashSet<>(minFound);
        new Object() {
            void buildPath(int a, int b, Consumer<Integer> list) {
                int d = dist[a][b];
                list.accept(a);
                if (a == b) return;
                for (int c : conn[a]) {
                    if (dist[c][b] < d) {
                        buildPath(c, b, list);
                        return;
                    }
                }
                throw new Error("illegal state");
            }

            void foo(int node, int mask, int d) {
                if (mask == 0) return;
                int src_node = backtraceNode[mask][node];
                int src_mask = backtraceMask[mask][node];

                if (src_node == -1) return;
                if (src_node == node) {
                    foo(src_node, src_mask, d + 1);
                    foo(src_node, mask ^ src_mask, d + 1);
                } else {
                    buildPath(node, src_node, test::add);
                    foo(src_node, src_mask, d + 1);
                }
            }
        }.foo(minIdx, max_term, 0);

//        System.out.println("test = " + test);
        if (test.size() != minFound + 1)
            throw new Error("!");

//        throw new Error("!z");
        return test.stream().mapToInt(i -> i).toArray();
    }
}


// (backtraceMask)\[([^\]]+)\]\[([^\]]+)\]
// $1[$3][$2]