package net.qcrist.poetool2024.benchmark;

import net.qcrist.poetool2024.Graph;
import net.qcrist.poetool2024.simplify.Paths;
import net.qcrist.poetool2024.solver.RandomPathSolver2b;
import net.qcrist.poetool2024.solver.Solver2;
import net.qcrist.poetool2024.util.Util;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static net.qcrist.poetool2024.util.Util.intArrayToByteArray;
import static net.qcrist.poetool2024.util.Util.intArrayToShortArray;

public class BenchmarkDataSet {
    public BenchmarkDataSet(Path src) throws IOException {
        JSONObject obj = new JSONObject(Files.readString(src));
        @SuppressWarnings({"unchecked", "rawtypes"})
        List<List<Integer>> treeRaw = (List) obj.getJSONArray("tree").toList();
        int[][] conn = treeRaw.stream().map(x -> x.stream().mapToInt(i -> i).toArray()).toArray(int[][]::new);
        Graph g = new Graph(conn, null, null);
        @SuppressWarnings({"unchecked", "rawtypes"})
        List<Map<String, Object>> tests = (List) obj.getJSONArray("tests").toList();
        List<int[]> targets = tests.stream().map(x -> {
            @SuppressWarnings({"unchecked", "rawtypes"})
            List<Integer> target = (List) x.get("target");
            return target.stream().mapToInt(i -> i).toArray();
        }).toList();
        List<short[]> sTargets = targets.stream().map(Util::intArrayToShortArray).toList();
        int[] lengths = tests.stream().mapToInt(x -> {
            return (Integer) x.get("len");
        }).toArray();
//        Solver s = new RandomPathSolver2();
        Solver2 s = new RandomPathSolver2b();
        Map<Integer, Integer> mistakes = new TreeMap<>();
        short[][] sConn = new short[conn.length][];
        byte[][] bDist = new byte[g.dist.length][g.dist.length];
        Util.time("reorder: " + src, () -> {
            for (int x = 0; x < conn.length; x++) {
                sConn[x] = intArrayToShortArray(conn[x]);
                bDist[x] = intArrayToByteArray(g.dist[x]);
            }
        });
        Util.time("benchmark: " + src, () -> {
            s.init(sConn, bDist);
            int acc = 0;
            for (int i = 0; i < lengths.length; i++) {
                short[] solve = s.solve(sConn, bDist, sTargets.get(i));
                if (solve.length < lengths[i]) {
                    throw new Error("more optimal solution found?: " + i);
                }
                if (solve.length == lengths[i])
                    acc++;
                else {
                    mistakes.merge(solve.length - lengths[i], 1, Integer::sum);
                }
            }
            System.out.printf("failed %d / %d (%.02f%%)\n", acc, lengths.length, acc * 100d / lengths.length);
        });
        System.out.println("mistakes = " + mistakes);
    }

    public static void main(String[] args) throws IOException {
        new BenchmarkDataSet(Path.of("bm-325.json"));
    }

}
