package net.qcrist.poetool2024.truth;

import net.qcrist.poetool2024.Graph;
import net.qcrist.poetool2024.solver.Solver;
import net.qcrist.poetool2024.solver.SteinerSolverFullOpt2S;
import net.qcrist.poetool2024.util.Util;
import net.qcrist.poetool2024.verify.VerifyBase;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import static net.qcrist.poetool2024.truth.Truth.TRUTHS_DIR;

public class GenerateTruthData {
    private static int[] genSelection(SplittableRandom r, int count, int limit) {
        boolean[] selected = new boolean[limit];
        int[] list = new int[count];
        int at = 0;
        while (at < count) {
            int gen = r.nextInt(0, limit);
            if (selected[gen]) continue;
            selected[gen] = true;
            list[at++] = gen;
        }
        return list;
    }

    public static void main(String[] args) throws IOException {
        Util.timeE("genData", GenerateTruthData::genData);
    }

    private static void genData() throws IOException {
        String tree_name = "skill-tree-3.25-graph";
        Graph tree = Util.timeVE("loadTree", () -> Graph.load(Path.of(tree_name + ".json")));

        Instant start = Instant.now();
        Instant limit = start.plus(5, ChronoUnit.MINUTES);

        Solver solver = new SteinerSolverFullOpt2S();

        ThreadLocal<SplittableRandom> randTL = ThreadLocal.withInitial((SplittableRandom::new));
        try (ExecutorService pool = Executors.newFixedThreadPool(30)) {
            List<Truth> result = IntStream.range(0, 500_000)
                    .parallel()
                    .mapToObj(i ->
                                    CompletableFuture.supplyAsync(() -> {
                                        if (Instant.now().isAfter(limit)) {
                                            return null;
                                        }

                                        SplittableRandom r = randTL.get();
                                        int length = r.nextInt(13, 16);
//                                        int length = 10;
                                        int[] ids = genSelection(r, length, tree.node_count);
                                        int[] res = solver.solve(tree.conn, tree.dist, ids);
                                        if (!VerifyBase.verifySolution(tree, ids, res)) {
                                            throw new Error(String.format("Bad solution! ids=%s, res=%s", Arrays.toString(ids), Arrays.toString(res)));
                                        }
                                        return new Truth(
                                                length,
                                                ids,
                                                res,
                                                null
                                        );
                                    }, pool)
                    )
                    .map(CompletableFuture::join)
                    .filter(Objects::nonNull)
                    .toList();

            TruthDataStats.printStatsFor(result);

            System.out.println("Writing results...");

            int at = 1;
            Path target;
            do {
                target = TRUTHS_DIR.resolve(String.format("%s-truth%02d.json", tree_name, at++));
            } while (Files.exists(target));
            try (PrintStream out = new PrintStream(Files.newOutputStream(target))) {
                JSONObject obj = new JSONObject();
                obj.put("solver", solver.getClass().getName());
                obj.put("data", new JSONArray(result.stream().map(Truth::toJSON).toList()));
                out.println(obj.toString(1));
            }
        }

        Instant end = Instant.now();
        System.out.printf("Took %dms\n", Duration.between(start, end).toMillis());
    }
}
