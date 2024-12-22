package net.qcrist.poetool2024.simplify;

public class TreeSimplify3 {
    public static boolean[] simp(short[][] conn, byte[][] dist, short[] sel, int acc) {
        boolean[] s = TreeSimplify1b.simp(conn, dist, sel);
        loop_i:
        for (int i = 0; i < s.length; i++) {
            if (s[i]) {
                byte[] di = dist[i];
                for (int a = 0; a < sel.length; a++) {
                    short sa = sel[a];
                    byte[] dsa = dist[sa];
                    for (int b = a + 1; b < sel.length; b++) {
                        short sb = sel[b];
                        if (di[sa] + di[sb] <= dsa[sb] + acc) {
                            continue loop_i;
                        }
                    }
                }
                s[i] = false;
            }
        }
        return s;
    }
}
