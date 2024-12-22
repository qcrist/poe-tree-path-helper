package net.qcrist.poetool2024.solver;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.IntStream;

public class SteinerSolverFullOpt4 extends SteinerSolverFullOpt2S {
    //SteinerSolverFullOpt2S + array optimizations

    //attempt to try to shift instead of multidimensional array, seems to be a failure

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
        int shift = 32 - Integer.numberOfLeadingZeros(useful_nodes_count - 1);
//        System.out.printf("nc=%d, shift=%d\n", useful_nodes_count, shift);

        int[] minCost = new int[mask_count << shift];
        int[] backtraceMask = new int[mask_count << shift];
        int[] backtraceNode = new int[mask_count << shift];
        Arrays.fill(minCost, MAX);
//        for (int[] z : backtraceNode) {
//            Arrays.fill(z, -1);
//        }
        for (int i = 0; i < term_count; i++) {
            int mask = 1 << i;
            minCost[(mask << shift) | i] = 0;
            backtraceNode[(mask << shift) | i] = -1;
        }

        //TODO unified dist + ?? unified cost??
        int[][] useful_dist = new int[useful_nodes_count][useful_nodes_count];
        for (int a = 0; a < useful_nodes_count; a++) {
            for (int b = 0; b < useful_nodes_count; b++) {
                useful_dist[a][b] = dist[useful_nodes[a]][useful_nodes[b]];
            }
        }

        for (int mask = 0; mask < mask_count; mask++) {
//                for (int i = 0; i < node_count; i++) {
            for (int i = 0; i < useful_nodes_count; i++) {
                int index = ((mask) << shift) | (i);
                for (int submask = mask; submask != 0; submask = (submask - 1) & mask) {
                    int v = minCost[((submask) << shift) | (i)] + minCost[((mask ^ submask) << shift) | (i)];
                    if (v < minCost[index]) {
                        minCost[index] = v;
                        backtraceNode[index] = i;
                        backtraceMask[index] = submask;
                    }
                }
                if (minCost[index] < MAX) {
                    for (int j = 0; j < useful_nodes_count; j++) {
//                    for (int j : useful_nodes) {
                        int v = minCost[index] + useful_dist[i][j];
                        int jIndex = ((mask) << shift) | (j);
                        if (v < minCost[jIndex]) {
                            minCost[jIndex] = v;
                            backtraceNode[jIndex] = i;
                            backtraceMask[jIndex] = mask;
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
            if (minCost[((max_term) << shift) | (i)] < minFound) {
                minFound = minCost[((max_term) << shift) | (i)];
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
                int src_node = backtraceNode[((mask) << shift) | (node)];
                int src_mask = backtraceMask[((mask) << shift) | (node)];

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