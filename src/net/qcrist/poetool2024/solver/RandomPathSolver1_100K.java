package net.qcrist.poetool2024.solver;

import net.qcrist.poetool2024.simplify.TreeSimplify1;
import net.qcrist.poetool2024.structure.IntSetList;
import net.qcrist.poetool2024.util.Util;

import java.util.Arrays;
import java.util.SplittableRandom;

public class RandomPathSolver1_100K extends RandomPathSolver1 {

    @Override
    protected int limit() {
        return 100_000;
    }
}
