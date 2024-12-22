package net.qcrist.poetool2024.truth;

import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@RequiredArgsConstructor
public final class Truth {
    public final int length;
    public final int[] target;
    public final int[] path;
    public final Path source;

    public static Truth fromJSON(JSONObject jso, Path source) {
        return new Truth(
                jso.getInt("length"),
                jso.getJSONArray("targets").toList().stream().mapToInt(x -> (Integer) x).toArray(),
                jso.getJSONArray("result").toList().stream().mapToInt(x -> (Integer) x).toArray(),
                source
        );
    }

    public JSONObject toJSON() {
        JSONObject obj = new JSONObject();
        obj.put("length", length);
        obj.put("targets", target);
        obj.put("result", path);
        return obj;
    }

    public static final Path TRUTHS_DIR = Path.of("truth_data");

    public static List<Truth> loadTruths(Path dir) throws IOException {
        try (Stream<Path> list = Files.list(dir)) {
            List<Truth> truths = list
//                .filter(p -> p.toString().contains("-truth"))
                    .flatMap(p -> {
                        try {
                            String data = Files.readString(p);
                            JSONArray a;
                            if (data.startsWith("[")) {
                                a = new JSONArray(data);
                            } else {
                                JSONObject obj = new JSONObject(data);
                                a = obj.getJSONArray("data");
                            }
                            List<Truth> t = new ArrayList<>();
                            for (int i = 0; i < a.length(); i++) {
                                JSONObject jso = a.getJSONObject(i);
                                t.add(Truth.fromJSON(jso, p));
                            }
                            return t.stream();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }).toList();
            System.out.println("truths.size() = " + truths.size());
            return truths;
        }
    }
}