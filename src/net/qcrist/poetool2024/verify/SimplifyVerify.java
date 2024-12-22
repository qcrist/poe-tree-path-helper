package net.qcrist.poetool2024.verify;

import net.qcrist.poetool2024.Graph;
import net.qcrist.poetool2024.simplify.Paths;
import net.qcrist.poetool2024.simplify.TreeSimplify1;
import net.qcrist.poetool2024.simplify.TreeSimplify2;
import net.qcrist.poetool2024.solver.RandomPathSolver2;
import net.qcrist.poetool2024.solver.RandomPathSolver2b;
import net.qcrist.poetool2024.truth.Truth;

import java.io.IOException;
import java.util.Arrays;

public class SimplifyVerify extends VerifyBase<boolean[]> {
    public static void main(String[] args) throws IOException {
        new SimplifyVerify().verify();
    }

    private int[][][] paths;

    @Override
    protected void init(Graph g) {
        paths = Paths.makePaths(g.conn, g.dist);
    }

    @Override
    protected boolean[] calculate(Graph g, Truth t) {
//        return TreeSimplify2.simp(g.conn, t.target, paths);
        return null;
    }

    @Override
    protected boolean verify(Graph g, Truth t, boolean[] res) {
        boolean[] prev = TreeSimplify1.simp(g.conn, g.dist, t.target);
        if (!Arrays.equals(prev, res))
            return false;
//        for (int p : t.path) {
//            if (!res[p]) {
//                boolean b = Arrays.stream(t.path).anyMatch(i -> i == 1145);
//
//                System.out.printf("Failed verify match: source=%s [%s] (%b)\n", t.source, Arrays.toString(t.target), b);
//                return false;
//            }
//        }
        return true;
    }

    @Override
    protected int limit(int length) {
        return Integer.MAX_VALUE;
    }
}
