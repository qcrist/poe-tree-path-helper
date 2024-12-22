package net.qcrist.poetool2024.solver;

import net.qcrist.poetool2024.Graph;
import net.qcrist.poetool2024.simplify.TreeSimplify1;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.IntStream;

public class SteinerSolverFullOpt2S extends SteinerSolverFullOpt2 {
    //SteinerSolverFullOpt2 + simplify tree


    @Override
    protected int[] getUsefulNodes(int[][] conn, int[][] dist, int[] terminals) {
        boolean[] simp = TreeSimplify1.simp(conn, dist, terminals);
        return Arrays.stream(super.getUsefulNodes(conn, dist, terminals)).filter(i -> simp[i]).toArray();
    }

//    @Override
//    public int[] solve(int[][] conn, int[][] dist, int[] terminals) {
//        throw new Error("!");
//        Graph s = TreeSimplify1.simp(conn, dist, terminals);
//        return super.solve(s.conn, s.dist, terminals);
//    }
}


// (backtraceMask)\[([^\]]+)\]\[([^\]]+)\]
// $1[$3][$2]