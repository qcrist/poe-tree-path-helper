package net.qcrist.poetool2024.solver;

import net.qcrist.poetool2024.Graph;
import net.qcrist.poetool2024.util.Util;

import java.util.Arrays;

public interface Solver2 {
    short[] solve(short[][] conn, byte[][] dist, short[] sel);

    default void init(short[][] conn, byte[][] dist) {
    }

    static Solver2 wrap(Solver solver) {
        return new Solver2() {
            Graph g;

            @Override
            public short[] solve(short[][] conn, byte[][] dist, short[] sel) {
                int[] res = solver.solve(g.conn, g.dist, Util.shortArrayToIntArray(sel));
                return Util.intArrayToShortArray(res);
            }

            @Override
            public void init(short[][] conn, byte[][] _unused) {
                int[][] c = Arrays.stream(conn).map(Util::shortArrayToIntArray).toArray(int[][]::new);
                solver.init(g = new Graph(c, null, null));
            }
        };
    }

    static Solver wrap(Solver2 solver) {
        return new Solver() {
            short[][] conn;
            byte[][] dist;

            @Override
            public int[] solve(int[][] conn, int[][] dist, int[] sel) {
                short[] res = solver.solve(this.conn, this.dist, Util.intArrayToShortArray(sel));
                return Util.shortArrayToIntArray(res);
            }

            @Override
            public void init(Graph g) {
                conn = Arrays.stream(g.conn).map(Util::intArrayToShortArray).toArray(short[][]::new);
                dist = Arrays.stream(g.dist).map(Util::intArrayToByteArray).toArray(byte[][]::new);
                solver.init(conn, dist);
            }
        };
    }
}
