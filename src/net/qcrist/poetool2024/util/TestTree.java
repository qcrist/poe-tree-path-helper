package net.qcrist.poetool2024.util;

import net.qcrist.poetool2024.SkillTree;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;

public class TestTree {
    public static Object thoughtTree(int z) {
        var o = new Object() {
            int next_id = 0;

            List<SkillTree.Node> nodes = new ArrayList<>();

            SkillTree.Node newNode(double r, double deg) {
                SkillTree.Node n = new SkillTree.Node(String.valueOf(++next_id),
                        r * Math.cos(Math.PI / 180 * -deg),
                        r * Math.sin(Math.PI / 180 * -deg)
                        , new String[]{}
                );
                nodes.add(n);
                return n;
            }

            List<Pair<SkillTree.Node, SkillTree.Node>> conns = new ArrayList<>();

            void connect(SkillTree.Node a, SkillTree.Node b) {
                conns.add(Pair.of(a, b));
            }

            Map<String, SkillTree.Node> toTree() {
                Map<String, SkillTree.Node> tree = new HashMap<>();
                for (SkillTree.Node n : nodes) {
                    String[] c = conns.stream()
                            .map(p -> {
                                if (p.getLeft() == n)
                                    return p.getRight().id;
                                if (p.getRight() == n)
                                    return p.getLeft().id;
                                return null;
                            }).filter(Objects::nonNull)
                            .toArray(String[]::new);
                    tree.put(n.id, new SkillTree.Node(n.id, n.x, n.y, c));
                }

                return tree;
            }

            void outerConnection(double r, double startDeg, double endDeg, int count, SkillTree.Node start, SkillTree.Node end) {
                SkillTree.Node last = start;

                for (int i = 1; i <= count; i++) {
                    SkillTree.Node next = newNode(r, startDeg + i * (endDeg - startDeg) / (count + 1));
                    connect(last, next);
                    last = next;
                }

                connect(last, end);
            }

            void innerConnection(double startR, double endR, double deg, int count, SkillTree.Node start, SkillTree.Node end) {
                SkillTree.Node last = start;

                for (int i = 1; i <= count; i++) {
                    SkillTree.Node next = newNode(startR + i * (endR - startR) / (count + 1), deg);
                    connect(last, next);
                    last = next;
                }

                connect(last, end);
            }
        };

        SkillTree.Node C = o.newNode(0, 0);
        SkillTree.Node up = o.newNode(30, 90);
        SkillTree.Node right = o.newNode(30, -45);
        SkillTree.Node left = o.newNode(30, -135);

        o.outerConnection(30, 90, -45, z * 2, up, right);
        o.outerConnection(30, -45, -135, z * 2, right, left);
        o.outerConnection(30, 225, 90, z * 2, left, up);
        o.innerConnection(0, 30, 90, z, C, up);
        o.innerConnection(0, 30, -45, z, C, right);
        o.innerConnection(0, 30, -135, z, C, left);

        return o.toTree();
    }
}
