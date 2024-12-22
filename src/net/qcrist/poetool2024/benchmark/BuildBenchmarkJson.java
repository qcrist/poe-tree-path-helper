package net.qcrist.poetool2024.benchmark;

import net.qcrist.poetool2024.Graph;
import net.qcrist.poetool2024.truth.Truth;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class BuildBenchmarkJson {
    public static void main(String[] args) throws IOException {
        List<Truth> truths = Truth.loadTruths(Truth.TRUTHS_DIR);
        Graph g = Graph.load(Path.of("skill-tree-3.25-graph.json"));
        JSONObject bm = new JSONObject();
        bm.put("tree", g.conn);
        JSONArray arr = new JSONArray();
        AtomicInteger[] counts = new AtomicInteger[30];
        for (int i = 0; i < counts.length; i++) {
            counts[i] = new AtomicInteger(0);
        }
        for (Truth t : truths) {
            JSONObject obj = new JSONObject();
            if (counts[t.target.length].incrementAndGet() > 200)
                continue;
            obj.put("len", t.path.length);
            obj.put("target", t.target);
            arr.put(obj);
        }
        bm.put("tests", arr);
        Path dir = Path.of("benchmark");
        Files.createDirectories(dir);
        Files.writeString(dir.resolve("bm-325.json"), bm.toString(1));
    }
}
