package net.qcrist.poetool2024.solver;

import net.qcrist.poetool2024.structure.IntSetList;

import java.util.function.Consumer;

public class SteinerSolverFast extends SteinerSolverFullOpt2 {
    //net.qcrist.poetool2024.solver.SteinerSolverFullOpt2 + getUsefulNodes(TOO FAST)

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
        int node_count = conn.length;

        IntSetList list = new IntSetList(node_count);
        for (int i : terminals) {
            list.add(i);
        }


        useFulNodesCalc(2, terminals, dist, list::add);
        useFulNodesCalc(3, terminals, dist, list::add);


        return list.toArray();
    }
}


// (backtraceMask)\[([^\]]+)\]\[([^\]]+)\]
// $1[$3][$2]