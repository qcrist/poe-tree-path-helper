package net.qcrist.poetool2024;

import org.json.JSONArray;
import org.json.JSONObject;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class Graph {
    public final int node_count;
    public final int[][] conn;
    public final int[][] dist;
    public final Point2D.Double[] positions;
    public final String[] ids;

    public Graph(int[][] conn, Point2D.Double[] positions, String[] ids) {
        this.node_count = conn.length;
        this.conn = conn;
        this.dist = calcDistances(conn);
        this.positions = positions;
        this.ids = ids;
    }

    public void save(Path target) throws IOException {
        JSONArray obj = new JSONArray();
        for (int i = 0; i < node_count; i++) {
            JSONObject node = new JSONObject();
            Point2D.Double pos = positions[i];
            node.put("pos", new JSONArray(List.of(pos.getX(), pos.getY())));
            node.put("id", ids[i]);
            node.put("conn", conn[i]);
            obj.put(node);
        }
        Files.writeString(target, obj.toString(1));
    }

    public static Graph load(Path path) throws IOException {
        JSONArray arr = new JSONArray(Files.readString(path));

        int count = arr.length();

        int[][] conn = new int[count][count];
        Point2D.Double[] positions = new Point2D.Double[count];
        String[] ids = new String[count];

        for (int i = 0; i < count; i++) {
            JSONObject obj = arr.getJSONObject(i);
            ids[i] = obj.getString("id");
            JSONArray pos = obj.getJSONArray("pos");
            positions[i] = new Point2D.Double(pos.getDouble(0), pos.getDouble(1));
            conn[i] = obj.getJSONArray("conn").toList().stream().mapToInt(x -> (Integer) x).toArray();
        }

        return new Graph(conn, positions, ids);
    }

    public static final int NOT_CONNECTED = -1;

    public static int[][] calcDistances(int[][] conn) {
        int node_count = conn.length;
        int[][] result = new int[node_count][node_count];
        for (int[] d : result) {
            Arrays.fill(d, NOT_CONNECTED);
        }
        for (int a = 0; a < node_count; a++) {
            int[] todo = new int[node_count];
            boolean[] visited = new boolean[node_count];
            visited[a] = true;
            todo[0] = a;
            int i = 0;
            int end = 1;
            for (int distance = 0; i != end; distance++) {
                int dEnd = end;
                for (; i < dEnd; i++) {
                    int n = todo[i];
                    result[a][n] = distance;
                    for (int c : conn[n]) {
                        if (!visited[c]) {
                            todo[end++] = c;
                            visited[c] = true;
                        }
                    }
                }
            }
        }
        return result;
    }
}
