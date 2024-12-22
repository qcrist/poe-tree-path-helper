package net.qcrist.poetool2024.verify;

import com.sun.source.tree.Tree;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import net.qcrist.poetool2024.Graph;
import net.qcrist.poetool2024.truth.Truth;
import net.qcrist.poetool2024.util.Util;
import net.qcrist.poetool2024.solver.*;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@RequiredArgsConstructor
public class SolverVerify extends VerifyBase<int[]> {


    @SneakyThrows(ReflectiveOperationException.class)
    public static Solver getSolver(String hint) {
//        List<Solver> solvers = List.of(
//                new SteinerSolverFullOpt1(),
//                new SteinerSolverFullOpt2(),
//                new SteinerSolverFullOpt2S(),
//                new SteinerSolverFullOpt3(),
//                new SteinerSolverFast(),
//                new SteinerSolverFull(),
//                new SolverAnneal(),
//                new SimpleSolver1(),
//                new RandomPathSolver1()
//        );
//        Solver solver = solvers.stream().filter(s -> s.getClass().getSimpleName().equalsIgnoreCase(hint)).findFirst().orElse(null);
        String cname = Solver.class.getPackage().getName() + "." + hint;
        Object instance = Class.forName(cname).getConstructor().newInstance();
        Solver solver;
        if (instance instanceof Solver s) {
            solver = s;
        } else if (instance instanceof Solver2 s) {
            solver = Solver2.wrap(s);
        } else {
            throw new Error("unexpected class: " + instance.getClass());
        }
        System.out.println("solver.getClass().getSimpleName() = " + solver.getClass().getSimpleName());

        return solver;
    }

    private final Solver solver;

    public static void main(String[] args) throws IOException {
        Solver solver = getSolver(args[0]);
        new SolverVerify(solver).verify();
    }

    @Override
    protected void init(Graph g) {
        Util.time("solver.init", () -> solver.init(g));
    }

    @Override
    protected int[] calculate(Graph g, Truth t) {
        return solver.solve(g, t.target);
    }

    Map<Integer, List<Integer>> distances = new ConcurrentHashMap<>();

    @Override
    protected boolean verify(Graph g, Truth t, int[] solved) {
        int distance = solved.length - t.path.length;
        distances.computeIfAbsent(t.length, k -> new ArrayList<>()).add(distance);
        if (solved.length != t.path.length) {
            if (solved.length < t.path.length) {
                System.out.printf("Failed length match (TRUTH IS WORSE!!!) : expected=%d, got=%d, source=%s [%s]\n", t.path.length, solved.length, t.source, Arrays.toString(t.target));
                throw new Error("!!!");
            }
            return false;
        }
        if (!verifySolution(g, t.target, solved)) {
//                            int[] full_solution = f.solve(g.conn, g.dist, t.target);
//                                        System.out.println("Invalid solution generated!!!");
            return false;
        }

        return true;
    }

    @Override
    protected String notes(int length) {
        Map<Integer, Integer> summary = new HashMap<>();
        for (Integer v : distances.getOrDefault(length, List.of())) {
            summary.merge(v, 1, Integer::sum);
        }
        return new TreeMap<>(summary).toString();
    }

    @Override
    protected int limit(int length) {
//        if (length < 11) return 0;
//        if (length > 15) return 0;
//        return 2;
        return 1000;
    }
}
