package net.qcrist.poetool2024.solver;

import net.qcrist.poetool2024.Graph;

public interface Solver {
    int[] solve(int[][] conn, int[][] dist, int[] sel);

    default int[] solve(Graph g, int[] sel) {
        return solve(g.conn, g.dist, sel);
    }

    default void init(Graph g) {
    }
}
