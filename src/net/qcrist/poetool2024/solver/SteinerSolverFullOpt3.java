package net.qcrist.poetool2024.solver;

import net.qcrist.poetool2024.structure.IntSetList;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.IntStream;

public class SteinerSolverFullOpt3 extends SteinerSolverFullOpt2 {
    //net.qcrist.poetool2024.solver.SteinerSolverFullOpt2 + more efficient getUsefulNodes

    //notes
    //seems worse than 2S, abandoned

    private void useFulNodesCalc(int start, int at, int[] sel, int term_count, int[] terminals, int[][] dist, Consumer<Integer> cons) {
        if (at < sel.length) {
            for (int i = start; i < term_count; i++) {
                sel[at] = terminals[i];
                useFulNodesCalc(i + 1, at + 1, sel, term_count, terminals, dist, cons);
            }
            return;
        }
//        System.out.printf("ACTUAL=%s\n", Arrays.toString(sel));

        int minV = Integer.MAX_VALUE;
        int minNLength = 0;
        int node_count = dist.length;
        int[] minN = new int[node_count];
        for (int n = 0; n < node_count; n++) {
//                        if (added[n]) continue;
            int sum = 0;
            for (int i : sel) {
                sum += dist[n][i];
            }
            if (sum == minV) {
                minN[minNLength++] = n;
            } else if (sum < minV) {
                minV = sum;
                minNLength = 0;
                minN[minNLength++] = n;
            }
        }
//        System.out.println("Result=" + Arrays.toString(Arrays.copyOf(minN, minNLength)));
        for (int i = 0; i < minNLength; i++) {
            cons.accept(minN[i]);
        }
    }

    private void useFulNodesCalc(int size, int[] terminals, int[][] dist, Consumer<Integer> cons) {
        useFulNodesCalc(0, 0, new int[size], terminals.length, terminals, dist, cons);
    }

    protected int[] getUsefulNodes(int[][] conn, int[][] dist, int[] terminals) {
        if (terminals.length < 9) {
            return super.getUsefulNodes(conn, dist, terminals);
        }

        int node_count = conn.length;

        IntSetList list = new IntSetList(node_count);
        for (int i : terminals) {
            list.add(i);
        }


//        int[] minN = new int[node_count];
//        for (int a = 0; a < term_count; a++) {
//            for (int b = a + 1; b < term_count; b++) {
//                for (int c = b + 1; c < term_count; c++) {
////                    System.out.printf("EXPECTED=%s\n", List.of(terminals[a], terminals[b], terminals[c]));
//                    int minV = Integer.MAX_VALUE;
//                    int minNLength = 0;
//                    for (int n = 0; n < node_count; n++) {
////                        if (added[n]) continue;
//                        int sum = dist[n][terminals[a]] + dist[n][terminals[b]] + dist[n][terminals[c]];
//                        if (sum == minV) {
//                            if (!added[n])
//                                minN[minNLength++] = n;
//                        } else if (sum < minV) {
//                            minV = sum;
//                            minNLength = 0;
//                            if (!added[n]) {
//                                minN[minNLength++] = n;
//                            }
//                        }
//                    }
////                    System.out.println("Result=" + Arrays.toString(Arrays.copyOf(minN, minNLength)));
//                    for (int i = 0; i < minNLength; i++) {
//                        int v = minN[i];
//                        goodNodes[goodNodesLength++] = v;
//                        added[v] = true;
//                    }
//                }
//            }
//        }

        int term_count = terminals.length;

        IntSetList tmp = new IntSetList(node_count);


        boolean[] disabled = new boolean[node_count];

        Function<Integer, Integer> maxDistTerminals = i ->
                Arrays.stream(terminals).map(n -> dist[i][n]).max().orElseThrow();
        Function<Integer, Integer> sumDistTerminals = i ->
                Arrays.stream(terminals).map(n -> dist[i][n]).sum();

        int maxTDist = Arrays.stream(terminals)
                .map(maxDistTerminals::apply)
                .max().orElseThrow();
        int maxDist = Arrays.stream(terminals)
                .map(sumDistTerminals::apply)
                .max().orElseThrow();
//        System.out.println("maxTDist = " + maxTDist);

        double d = Math.ceil(maxTDist / 2.0);
        for (int i = 0; i < node_count; i++) {
            boolean valid = sumDistTerminals.apply(i) <= maxDist + d;
            if (!valid) {
                disabled[i] = true;
            }
        }

        int[] allowed_nodes = IntStream.range(0, conn.length).filter(i -> conn[i].length > 2 && !disabled[i]).toArray();

//        System.out.println("allowed_nodes.length = " + allowed_nodes.length);

        Consumer<Function<Integer, Integer>> testFn = supp -> {
            int minV = Integer.MAX_VALUE;
            tmp.clear();
            for (int n : allowed_nodes) {
                int sum = supp.apply(n);
                if (sum == minV) {
                    tmp.add(n);
                } else if (sum < minV) {
                    minV = sum;
                    tmp.clear();
                    tmp.add(n);
                }
            }
            for (int i = 0; i < tmp.length; i++) {
                list.add(tmp.data[i]);
            }
        };
//
        for (int a = 0; a < list.length; a++) {
//            System.out.println("a = " + a);
            int ldA = list.data[a];
//            if (ldA == 778)
//                System.out.println("Added at: a =" + a);
            for (int b = Math.min(a - 1, term_count); b >= 0; b--) {
                int ldB = list.data[b];
                testFn.accept(n -> dist[n][ldA] + dist[n][ldB]);
                for (int c = b - 1; c >= 0; c--) {
                    int ldC = list.data[c];
                    testFn.accept(n -> dist[n][ldA] + dist[n][ldB] + dist[n][ldC]);
                }
            }
        }

//
//
//        int last_size;
//        int i = 0;
//
//        Consumer<Integer> test = x -> {
//            if (conn[x].length <= 2) return;
//            list.add(x);
//        };
//
//        do {
//            last_size = list.length;
//            i++;
//            int[] z = list.toArray();
//            System.out.println("z.length = " + z.length);
//            useFulNodesCalc(2, z, dist, test);
//            useFulNodesCalc(3, z, dist, test);
//            System.out.println(i);
//        } while (last_size != list.length);
//
//        System.out.println("i = " + i);


//        useFulNodesCalc(4, terminals, dist, list::add);

        //Failed length match: expected=40, got=41, source=.\skill-tree-3.25-graph-truth02.json [[501, 1697, 1255, 2149]]
        //Failed length match: expected=66, got=67, source=.\skill-tree-3.25-graph-truth07.json [[62, 908, 440, 1963, 2076, 506, 2221, 1099]]
//        useFulNodesCalc(2, terminals, dist, list::add);
//        useFulNodesCalc(3, terminals, dist, list::add);
//        useFulNodesCalc(4, terminals, dist, list::add);


        return list.toArray();
    }
}


// (backtraceMask)\[([^\]]+)\]\[([^\]]+)\]
// $1[$3][$2]