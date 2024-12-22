package net.qcrist.poetool2024.simplify;

public class Paths {

    public static short[][][] makePaths(short[][] conn, byte[][] dist) {
        int node_count = conn.length;
        short[][][] paths = new short[node_count][node_count][];

        for (short a = 0; a < node_count; a++) {
            for (short b = (short) (a + 1); b < node_count; b++) {
                paths[a][b] = paths[b][a] = paths(conn, dist, a, b);
            }
        }

        return paths;
    }

    private static short[] paths(short[][] conn, byte[][] dist, short a, short b) {
        byte[] db = dist[b];
        int a_dist = db[a];
        short[] path = new short[a_dist + 1];
        path[0] = a;
        for (int i = 1; i < path.length; i++) {
            int prev = path[i - 1];
            int target_d = a_dist - i;
            for (short c : conn[prev]) {
                if (db[c] == target_d) {
                    path[i] = c;
                    break;
                }
            }
        }
        return path;
    }

    public static int[][][] makePaths(int[][] conn, int[][] dist) {
        int node_count = conn.length;
        int[][][] paths = new int[node_count][node_count][];

        for (int a = 0; a < node_count; a++) {
            for (int b = a + 1; b < node_count; b++) {
                paths[a][b] = paths[b][a] = paths(conn, dist, a, b);
            }
        }

        return paths;
    }

    private static int[] paths(int[][] conn, int[][] dist, int a, int b) {
        int[] db = dist[b];
        int a_dist = db[a];
        int[] path = new int[a_dist + 1];
        path[0] = a;
        for (int i = 1; i < path.length; i++) {
            int prev = path[i - 1];
            int target_d = a_dist - i;
            for (int c : conn[prev]) {
                if (db[c] == target_d) {
                    path[i] = c;
                    break;
                }
            }
        }
        return path;
    }
}
