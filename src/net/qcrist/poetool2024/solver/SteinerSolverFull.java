package net.qcrist.poetool2024.solver;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.IntStream;

public class SteinerSolverFull implements Solver {
    @Override
    public int[] solve(int[][] conn, int[][] dist, int[] terminals) {
        int term_count = terminals.length;
        int node_count = conn.length;

        boolean[] is_sel = new boolean[node_count];
        for (int i : terminals) {
            is_sel[i] = true;
        }

        int[] useful_nodes = IntStream.range(0, node_count).filter(i -> is_sel[i] || conn[i].length > 2).toArray();

        int MAX = Integer.MAX_VALUE / 4;
        int[][] minCost = new int[node_count][1 << term_count];
        int[][] backtraceMask = new int[node_count][1 << term_count];
        int[][] backtraceNode = new int[node_count][1 << term_count];
        for (int[] m : minCost)
            Arrays.fill(m, MAX);
        for (int i = 0; i < term_count; i++) {
            int mask = 1 << i;
            minCost[terminals[i]][mask] = 0;
            backtraceNode[terminals[i]][mask] = -1;
        }

//        System.out.printf("Steiner Complexity: (nodes=%d) * (terminals=%d)\n", useful_nodes.length, 1 << term_count);
//        net.qcrist.poetool2024.util.Util.time("steinerBusy", () -> {
        for (int mask = 0; mask < (1 << term_count); mask++) {
//                for (int i = 0; i < node_count; i++) {
            for (int i : useful_nodes) {
                for (int submask = mask; submask != 0; submask = (submask - 1) & mask) {
                    int v = minCost[i][submask] + minCost[i][mask ^ submask];
                    if (v < minCost[i][mask]) {
                        minCost[i][mask] = v;
                        backtraceNode[i][mask] = i;
                        backtraceMask[i][mask] = submask;
                    }
                }
                if (minCost[i][mask] < MAX) {
                    for (int j : useful_nodes) {
//                        for (int j = 0; j < node_count; j++) {
                        int v = minCost[i][mask] + dist[i][j];
                        if (v < minCost[j][mask]) {
                            minCost[j][mask] = v;
                            backtraceNode[j][mask] = i;
                            backtraceMask[j][mask] = mask;
                        }
                    }
                }
            }
        }

//        });

        int max_term = (1 << term_count) - 1;
//        System.out.println("term_count = " + term_count);
//        System.out.println("max_term = " + Integer.toBinaryString(max_term));

        int minFound = MAX;
        int minIdx = -1;
        for (int i = 0; i < minCost.length; i++) {
            if (minCost[i][max_term] < minFound) {
                minFound = minCost[i][max_term];
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
                int src_node = backtraceNode[node][mask];
                int src_mask = backtraceMask[node][mask];

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
        return test.stream().mapToInt(i -> i).toArray();
    }
}
