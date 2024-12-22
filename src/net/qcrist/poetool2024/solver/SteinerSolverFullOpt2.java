package net.qcrist.poetool2024.solver;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.IntStream;

public class SteinerSolverFullOpt2 implements Solver {
    //Cache optimization + memory optimization

    protected int[] getUsefulNodes(int[][] conn, int[][] dist, int[] terminals) {
        int node_count = conn.length;

        boolean[] is_sel = new boolean[node_count];
        for (int i : terminals) {
            is_sel[i] = true;
        }
        return IntStream.concat(
                        Arrays.stream(terminals),
                        IntStream.range(0, node_count)
                                .filter(i -> !is_sel[i] && conn[i].length > 2 && dist[terminals[0]][i] >= 0)
                )
                .toArray();
    }

    @Override
    public int[] solve(int[][] conn, int[][] dist, int[] terminals) {

        int term_count = terminals.length;

        int[] useful_nodes = getUsefulNodes(conn, dist, terminals);
//        System.out.println("useful_nodes.length = " + useful_nodes.length);

//        if (null == null)
//            return useful_nodes;


        int MAX = Integer.MAX_VALUE / 4;
        int mask_count = 1 << term_count;
        int useful_nodes_count = useful_nodes.length;
        int[][] minCost = new int[mask_count][useful_nodes_count];
        int[][] backtraceMask = new int[mask_count][useful_nodes_count];
        int[][] backtraceNode = new int[mask_count][useful_nodes_count];
        for (int[] m : minCost)
            Arrays.fill(m, MAX);
//        for (int[] z : backtraceNode) {
//            Arrays.fill(z, -1);
//        }
        for (int i = 0; i < term_count; i++) {
            int mask = 1 << i;
            minCost[mask][i] = 0;
            backtraceNode[mask][i] = -1;
        }

        //TODO unified dist + ?? unified cost??
        int[][] useful_dist = new int[useful_nodes_count][useful_nodes_count];
        for (int a = 0; a < useful_nodes_count; a++) {
            for (int b = 0; b < useful_nodes_count; b++) {
                useful_dist[a][b] = dist[useful_nodes[a]][useful_nodes[b]];
            }
        }

//        System.out.printf("Steiner Complexity: (nodes=%d) * (terminals=%d)\n", useful_nodes.length, 1 << term_count);
//        net.qcrist.poetool2024.util.Util.time("steinerBusy", () -> {
        for (int mask = 0; mask < mask_count; mask++) {
//                for (int i = 0; i < node_count; i++) {
            for (int i = 0; i < useful_nodes_count; i++) {
                for (int submask = mask; submask != 0; submask = (submask - 1) & mask) {
                    int v = minCost[submask][i] + minCost[mask ^ submask][i];
                    if (v < minCost[mask][i]) {
                        minCost[mask][i] = v;
                        backtraceNode[mask][i] = i;
                        backtraceMask[mask][i] = submask;
                    }
                }
                if (minCost[mask][i] < MAX) {
                    for (int j = 0; j < useful_nodes_count; j++) {
//                    for (int j : useful_nodes) {
                        int v = minCost[mask][i] + useful_dist[i][j];
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
        for (int i = 0; i < useful_nodes_count; i++) {
            if (minCost[max_term][i] < minFound) {
                minFound = minCost[max_term][i];
                minIdx = i;
            }
        }

//        System.out.println("minFound = " + minFound);

        //todo can def speed this bit up if needed
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
                    buildPath(useful_nodes[node], useful_nodes[src_node], test::add);
                    foo(src_node, src_mask, d + 1);
                }
            }
        }.foo(minIdx, max_term, 0);

//        System.out.println("test = " + test);
        if (test.size() != minFound + 1)
            System.out.printf("Failed to solve path... target=%d, found=%d\n", minFound + 1, test.size());

//        throw new Error("!z");
        return test.stream().mapToInt(i -> i).toArray();

//        return new int[minFound + 1];
    }
}


// (backtraceMask)\[([^\]]+)\]\[([^\]]+)\]
// $1[$3][$2]