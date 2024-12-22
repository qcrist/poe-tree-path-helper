package net.qcrist.poetool2024.verify;

import net.qcrist.poetool2024.Graph;
import net.qcrist.poetool2024.simplify.TreeSimplify1;
import net.qcrist.poetool2024.structure.IntSetList;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.stream.IntStream;

public class FullVerifySimplify3 {
    public static void main(String[] args) throws IOException {
        Graph g = Graph.load(Path.of("skill-tree-3.25-graph.json"));


        int node_count = g.conn.length;
        System.out.println("node_count = " + node_count);
//        long z = IntStream.range(0, c).boxed().flatMap(a -> IntStream.range(a + 1, c).boxed().flatMap(b -> {
//            return IntStream.range(b + 1, c).mapToObj(x -> Triple.of(a, b, x));
//        })).count();
//        long z = 0;

        int[] interesting = IntStream.range(0, node_count).filter(i -> g.conn[i].length > 2).toArray();
        int interesting_count = interesting.length;
        int[][] interesting_dist = new int[interesting_count][interesting_count];
        for (int a = 0; a < interesting_count; a++) {
            for (int b = 0; b < interesting_count; b++) {
                interesting_dist[a][b] = g.dist[interesting[a]][interesting[b]];
            }
        }

        System.out.println("interesting_count = " + interesting_count);
        //Failed minN a=178, b=225, c=404, n=323
//        int a = 178, b = 225, c = 404, n = 323;
//        System.out.println("a = " + interesting[a]);
//        System.out.println("b = " + interesting[b]);
//        System.out.println("c = " + interesting[c]);
//        System.out.println("n = " + interesting[n]);
//
//        System.exit(0);
//
        int[][] d = interesting_dist;
        for (int a = 0; a < interesting_count; a++) {
            int[] dA = d[a];
            int A = a;
            System.out.println("a = " + a);
            for (int b = a + 1; b < interesting_count; b++) {
                int[] dB = d[b];
                int B = b;
                IntStream.range(b + 1, interesting_count).parallel()
                        .forEach(c -> {
                            int minV = Integer.MAX_VALUE;
                            IntSetList minN = new IntSetList(interesting_count);

                            int[] dC = d[c];

                            for (int i = 0; i < interesting_count; i++) {
                                int v = dA[i] + dB[i] + dC[i];
                                if (v < minV) {
                                    minV = v;
                                    minN.clear();
                                    minN.add(i);
                                } else if (v == minV) {
                                    minN.add(i);
                                }
                            }

                            boolean[] simpl = TreeSimplify1.simp(g.conn, g.dist, new int[]{interesting[A], interesting[B], interesting[c]});

                            boolean found = false;
                            for (int i = 0; i < minN.length; i++) {
                                int n = interesting[minN.data[i]];
                                if (simpl[n]) {
                                    found = true;
                                    break;
                                }
                            }

                            if (!found) {
                                System.out.printf("Failed minN a=%d, b=%d, c=%d, n=%s\n", interesting[A], interesting[B], interesting[c],
                                        Arrays.toString(Arrays.stream(minN.toArray()).map(i -> interesting[i]).toArray()));
                            }
                        });
            }
        }

//        1,991,027,895
//        System.out.println("z = " + z);
    }
}
