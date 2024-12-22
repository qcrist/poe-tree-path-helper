package net.qcrist.poetool2024;

import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class SkillTree {

    private static int[] jsonIntArray(JSONArray array) {
        return array.toList().stream().mapToInt(x -> (Integer) x).toArray();
    }

    private static String[] jsonStrArray(JSONArray array) {
        return array.toList().stream().map(Object::toString).toArray(String[]::new);
    }

    @RequiredArgsConstructor
    public static class Node {
        public final String id;
        public final double x;
        public final double y;
        public final String[] connections;
    }

    public static Graph load() throws IOException {
        String raw = Files.readString(Path.of("skill-tree-3.25.json"));
        JSONObject data = new JSONObject(raw);
        JSONObject groups = data.getJSONObject("groups");
        JSONObject nodes = data.getJSONObject("nodes");
        JSONObject constants = data.getJSONObject("constants");
        int[] skillsPerOrbit = jsonIntArray(constants.getJSONArray("skillsPerOrbit"));
        int[] orbitRadii = jsonIntArray(constants.getJSONArray("orbitRadii"));
        int[] orbitAngle23 = {0, 30, 45, 60, 90, 120, 135, 150, 180, 210, 225, 240, 270, 300, 315, 330};
        Map<String, Node> map = new HashMap<>();

        List<String> banned = List.of(/*"isMastery", */"ascendancyName", "isProxy", "isBlighted");

        for (String key : nodes.keySet()) {
            JSONObject node = nodes.getJSONObject(key);
            if (!node.has("group"))
                continue;

            int groupIndex = node.getInt("group");
            if (groupIndex == 0)
                continue;

            if (banned.stream().anyMatch(node::has))
                continue;

            if (node.has("expansionJewel") && node.getJSONObject("expansionJewel").getInt("size") != 2)
                continue;

            JSONObject group = groups.getJSONObject(String.valueOf(groupIndex));
            int orbit = node.getInt("orbit");
            int orbitIndex = node.getInt("orbitIndex");
            double gx = group.getDouble("x");
            double gy = group.getDouble("y");
            double angle = Math.PI * 2 / skillsPerOrbit[orbit] * orbitIndex;
            if (orbit == 2 || orbit == 3) {
                angle = Math.PI / 180 * orbitAngle23[orbitIndex];
//                continue;
            }
            //TODO sin/cos are not right here...
            double ox = orbitRadii[orbit] * Math.sin(angle);
            double oy = orbitRadii[orbit] * -Math.cos(angle);
            JSONArray connections = new JSONArray();
            connections.putAll(node.getJSONArray("out"));
            connections.putAll(node.getJSONArray("in"));
            String[] connections_str = jsonStrArray(connections);

            map.put(key, new Node(key, gx + ox, gy + oy, connections_str));
        }

        for (String a : map.keySet()) {
            for (String b : map.get(a).connections) {
                if (!map.containsKey(b))
                    continue;
                List<String> bConn = List.of(map.get(b).connections);
                if (!bConn.contains(a))
                    throw new Error("!");
            }
        }

        Node[] arr = map.values().toArray(Node[]::new);
        Map<String, Integer> idLookup = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            idLookup.put(arr[i].id, i);
        }

        int[][] conn = new int[arr.length][arr.length];
        String[] ids = new String[arr.length];
        Point2D.Double[] positions = new Point2D.Double[arr.length];

        for (int i = 0; i < arr.length; i++) {
            Node node = arr[i];
            ids[i] = node.id;
            positions[i] = new Point2D.Double(node.x, node.y);
            conn[i] = Arrays.stream(node.connections)
                    .filter(idLookup::containsKey)
                    .mapToInt(idLookup::get).toArray();
        }

        return new Graph(conn, positions, ids);
    }

    public static void main(String[] args) throws IOException {
        load().save(Path.of("skill-tree-3.25-graph.json"));
    }
}
