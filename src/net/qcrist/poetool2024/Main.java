package net.qcrist.poetool2024;

import net.qcrist.poetool2024.simplify.Paths;
import net.qcrist.poetool2024.simplify.TreeSimplify1;
import net.qcrist.poetool2024.simplify.TreeSimplify2;
import net.qcrist.poetool2024.simplify.TreeSimplify3;
import net.qcrist.poetool2024.solver.*;
import net.qcrist.poetool2024.util.SimpleUI;
import net.qcrist.poetool2024.util.Util;
import org.apache.commons.lang3.tuple.Pair;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;
import java.util.List;
import java.util.function.*;

public class Main {
    private static final int target_size = 1024;
    private static double scale;
    private static SimpleUI ui;
    private static Graph tree;
    private static final Set<Integer> activeNodes = new HashSet<>();
    private static final Set<Integer> disabledNodes = new HashSet<>();
    private static final Set<Integer> goodNodes = new HashSet<>();
    private static final Set<Integer> pathNodes = new HashSet<>();
    private static final Set<Pair<String, String>> pathPairs = new HashSet<>();
    private static Function<Integer, Color> nodeColorFunction = node -> Color.CYAN;
    private static BiFunction<Integer, Integer, Color> lineColorFunction = (_, _) -> Color.MAGENTA;
    //    private static int[][] conn;
//    private static int[][] dist;
//    private static List<net.qcrist.poetool2024.SkillTree.Node> nodeIndexToNode;
//    private static Map<String, Integer> nodeIdToIndex;
    private static int node_count;

    private static int[][] trimConn;
    private static int[][] trimDist;
    private static boolean[] tree_simplify;

    public static void main(String[] args) throws IOException {
        ui = new SimpleUI("PoeTool2024");

//        tree = net.qcrist.poetool2024.SkillTree.load();
//        tree = net.qcrist.poetool2024.util.TestTree.thoughtTree(6); //activate 4,3,2
        tree = Util.timeVE("loadTree", () -> Graph.load(Path.of("skill-tree-3.25-graph.json")));

//        Solver solver = new SteinerSolverFullOpt2S();
        Solver solver = new Solver() {
            @Override
            public int[] solve(int[][] conn, int[][] dist, int[] sel) {
                return new int[0];
            }
        };
//        Solver solver = new SolverAnneal();

//        List<Integer> toActivate = List.of(11420, 4011, 49605, 34906, 38048, 6230, 4568, 42668, 9505, 4977);
//        if (tree.conn.length < 100) {
//            toActivate = List.of(4, 3, 2);
//        }
//        for (int i = 0; i < tree.node_count; i++) {
//            int v = Integer.parseInt(tree.ids[i]);
//            if (toActivate.contains(v))
//                activeNodes.add(i);
//        }

        //
        activeNodes.clear();
//        activeNodes.addAll(List.of(587, 780, 1434));
        activeNodes.addAll(List.of(259, 1143, 2116, 1678, 355));


        node_count = tree.node_count;
        tree_simplify = new boolean[node_count];

//        nodeIndexToNode = tree.values().stream().toList();
//
//        nodeIdToIndex = new HashMap<>(tree.size());
//        for (int i = 0; i < nodeIndexToNode.size(); i++) {
//            nodeIdToIndex.put(nodeIndexToNode.get(i).id, i);
//        }

//        conn = new int[node_count][];
//        for (int i = 0; i < nodeIndexToNode.size(); i++) {
//            conn[i] = Arrays.stream(nodeIndexToNode.get(i).connections)
//                    .map(key -> nodeIdToIndex.getOrDefault(key, null))
//                    .filter(Objects::nonNull)
//                    .mapToInt(x -> x).toArray();
//        }
//
//        dist = net.qcrist.poetool2024.util.Util.timeV("distances", () -> net.qcrist.poetool2024.Graph.calcDistances(conn));

//        Map<String, net.qcrist.poetool2024.SkillTree.Node> tree = net.qcrist.poetool2024.util.TestTree.thoughtTree();

        double max_dim = Arrays.stream(tree.positions)
                .mapToDouble(n -> Math.max(Math.abs(n.getX()), Math.abs(n.getY())))
                .reduce(0, Math::max);
        scale = (target_size - 0) / (max_dim * 2);
        System.out.println("scale = " + scale);


        ui.setUiListener(new SimpleUI.UIListener() {
            @Override
            public void onClick(int button, int target) {
                if (target == 0) {
                    System.out.println("Clicked background!");
                    return;
                }
                target -= 1;
                switch (button) {
                    case MouseEvent.BUTTON3 -> {
                        System.out.printf("target = %d, (id=%s)\n", target, tree.ids[target]);
                    }
                    case MouseEvent.BUTTON1 -> {

                        if (activeNodes.contains(target))
                            activeNodes.remove(target);
                        else
                            activeNodes.add(target);

//                        net.qcrist.poetool2024.util.Util.time("trimTree", net.qcrist.poetool2024.Main::trimTree);
//                        System.out.println("activeNodes.size() = " + activeNodes.size());
//                        List<Pair<String, String>> solve = solver.solve(trimDist, trimConn, tree, activeNodes);
//                        var solve = net.qcrist.poetool2024.util.Util.timeV("solve", () -> solver.solve(tree.conn, tree.dist, activeNodes.stream().mapToInt(i -> i).toArray()));
//                        var solve = net.qcrist.poetool2024.util.Util.timeV("solve", () -> solver.solve(trimConn, trimDist, activeNodes.stream().mapToInt(i -> i).toArray()));
//                        pathNodes.clear();
//                        Arrays.stream(solve).forEach(pathNodes::add);
//                        pathPairs.clear();
//                        pathPairs.addAll(solve);
//                        ui.redraw();

                        run_solver(solver);
                    }
                }
            }

            @Override
            public void onKeyDown(KeyEvent e) {
                System.out.println(e);
            }
        });

//        int[] test = {501, 1697, 1255, 2149};
//        int[] test = {62, 908, 440, 1963, 2076, 506, 2221, 1099};
//        int[] o1 = new SteinerSolverFullOpt2().solve(tree.conn, tree.dist, test);
//        int[] o2 = new SteinerSolverFullOpt3().solve(tree.conn, tree.dist, test);
//
//        List<Integer> tt = Arrays.stream(test).boxed().toList();
//        List<Integer> o1z = Arrays.stream(o1).boxed().toList();
//        List<Integer> o2z = Arrays.stream(o2).boxed().toList();
//
//        System.out.println("o1.length = " + o1.length);
//        System.out.println("o2.length = " + o2.length);


//        int[] SA = activeNodes.stream().mapToInt(i -> i).toArray();
//        boolean[] test = Util.timeV("simp", () -> TreeSimplify1.simp(tree.conn, tree.dist, SA));
//        int[] solved = new SteinerSolverFull().solve(tree, SA);
//        System.out.println("solved.length = " + solved.length);
//        Set<Integer> solvedSet = new HashSet<>(Arrays.stream(solved).boxed().toList());

        Color disabled = new Color(20, 20, 20);

//        short[][][] paths = Paths.makePaths(
//                Arrays.stream(tree.conn).map(Util::intArrayToShortArray).toArray(short[][]::new),
//                Arrays.stream(tree.dist).map(Util::intArrayToByteArray).toArray(byte[][]::new)
//        );
        boolean[] ts2 = TreeSimplify3.simp(
                Arrays.stream(tree.conn).map(Util::intArrayToShortArray).toArray(short[][]::new),
                Arrays.stream(tree.dist).map(Util::intArrayToByteArray).toArray(byte[][]::new),
                Util.intArrayToShortArray(activeNodes.stream().mapToInt(i -> i).toArray()),
                0);

        nodeColorFunction = n -> {
            if (activeNodes.contains(n))
                return Color.GREEN;
            if (pathNodes.contains(n))
                return Color.BLUE;

            if (ts2[n] && tree_simplify[n])
                return Color.YELLOW;
            else if (ts2[n]) {
                return Color.RED;
            } else if (tree_simplify[n])
                return Color.MAGENTA;

//            if (ts2[n])
//                return Color.YELLOW;


            if (disabledNodes.contains(n))
                return disabled;
////            return Color.DARK_GRAY;
            return Color.DARK_GRAY;
        };

        lineColorFunction = (a, b) -> {
//            if (Arrays.stream(test.conn[a]).anyMatch(i -> i == b))
//            return Color.WHITE;
//            return null;


            Color cA = nodeColorFunction.apply(a);
            Color cB = nodeColorFunction.apply(b);
//            if (cA == null || cB == null)
//                return null;
//            if (cA == cB)
//                return cA;
            return Color.DARK_GRAY;
//            return Color.LIGHT_GRAY;
        };

        setupTreeRenderer();


//        net.qcrist.poetool2024.util.Util.time("trimTree", net.qcrist.poetool2024.Main::trimTree);
//        Util.time("trimTree2", Main::trimTree2);
//        ui.redraw();

//        pathPairs.addAll(solve_paths(dist, conn, tree, activeNodes));
//        List<Pair<String, String>> solve = solve_paths(trimDist, trimConn, tree, activeNodes);
//        List<Pair<String, String>> solve = solve_paths(dist, conn, tree, activeNodes);
//        pathPairs.addAll(solve);


        run_solver(solver);
    }

    static void run_solver(Solver solver) {
//        net.qcrist.poetool2024.util.Util.time("trimTree", net.qcrist.poetool2024.Main::trimTree);
        int[] terminals = activeNodes.stream().mapToInt(i -> i).toArray();
        int term_count = terminals.length;
        System.out.println("term_count = " + term_count);
        int[] res = Util.timeV("solve", () -> solver.solve(tree.conn, tree.dist, terminals));
        pathNodes.clear();
        Arrays.stream(res).forEach(pathNodes::add);
        tree_simplify = Util.timeV("simplify_view", () -> TreeSimplify1.simp(tree.conn, tree.dist, terminals));

//        Set<Integer> goodNodes = new TreeSet<>();
//        for (int a = 0; a < term_count; a++) {
//            for (int b = a + 1; b < term_count; b++) {
//                for (int c = b + 1; c < term_count; c++) {
//                    int minV = Integer.MAX_VALUE;
//                    List<Integer> minN = new ArrayList<>(100);
//                    for (int n = 0; n < node_count; n++) {
//
////                        if (conn[n].length <= 2)
////                            continue;
////                        if (disabledNodes.contains(nodeIndexToNode.get(n).id))
////                            continue;
//                        int sum = tree.dist[n][terminals[a]] + tree.dist[n][terminals[b]] + tree.dist[n][terminals[c]] + 1;
//                        if (sum == minV) {
//                            minN.add(n);
//                        } else if (sum < minV) {
//                            minV = sum;
//                            minN.clear();
//                            minN.add(n);
//                        }
//                    }
//                    goodNodes.addAll(minN);
////                    goodNodes.add(minN.get(0));
////                        System.out.println("minN = " + minN);
//                }
//            }
//        }
//        System.out.println("goodNodes.size() = " + goodNodes.size());
//        net.qcrist.poetool2024.Main.goodNodes.clear();
//        net.qcrist.poetool2024.Main.goodNodes.addAll(goodNodes);

        ui.redraw();
    }

    static void trimTree2() {

    }

    static void trimTree() {
//        disabledNodes.clear();
        int[] terminals = activeNodes.stream().mapToInt(i -> i).toArray();
        boolean[] isTerminal = new boolean[node_count];
        for (int t : terminals) {
            isTerminal[t] = true;
        }
        boolean[] disabled = new boolean[node_count];

        Function<Integer, Integer> maxDistTerminals = i ->
                Arrays.stream(terminals).map(n -> tree.dist[i][n]).max().orElseThrow();
        Function<Integer, Integer> minDistTerminals = i ->
                Arrays.stream(terminals).map(n -> tree.dist[i][n]).min().orElseThrow();
        Function<Integer, Integer> sumDistTerminals = i ->
                Arrays.stream(terminals).map(n -> tree.dist[i][n]).sum();

        int maxTDist = Arrays.stream(terminals)
                .map(maxDistTerminals::apply)
                .max().orElseThrow();
        int maxDist = Arrays.stream(terminals)
                .map(sumDistTerminals::apply)
                .max().orElseThrow();
        System.out.println("maxTDist = " + maxTDist);

        for (int i = 0; i < node_count; i++) {
//            boolean valid = maxDistTerminals.apply(i) <= maxTDist + 2 && minDistTerminals.apply(i) <= maxTDist;
//            boolean valid = minDistTerminals.apply(i) <= maxTDist;
            boolean valid = sumDistTerminals.apply(i) <= maxDist + Math.ceil(maxTDist / 2.0);
            if (!valid) {
//                disabledNodes.add(nodeIndexToNode.get(i).id);
                disabled[i] = true;
            }
        }

        for (int i = 0; i < node_count; i++) {
            for (int c : tree.conn[i]) {
                if (isSafeToCut(c, i, isTerminal)) {
//                    disabledNodes.add(nodeIndexToNode.get(c).id);
                    disabled[c] = true;
                }
            }
        }

//        if (null == null)
//            return;

        for (int t : terminals) {
            if (disabled[t]) {
                throw new Error("!");
            }
        }

        trimConn = new int[node_count][];
        int[] empty = new int[0];
        for (int i = 0; i < tree.conn.length; i++) {
            if (disabled[i]) {
                trimConn[i] = empty;
                continue;
            }
            trimConn[i] = Arrays.stream(tree.conn[i]).filter(x -> !disabled[x]).toArray();
        }

        trimDist = Graph.calcDistances(trimConn);
        disabledNodes.clear();
        for (int i = 0; i < node_count; i++) {
            if (trimDist[terminals[1]][i] == Graph.NOT_CONNECTED) {
                disabledNodes.add(i);
            }
        }
    }


    static boolean isSafeToCut(int target, int without, boolean[] isTerminal) {
        if (isTerminal[target])
            return false;
        int[] todo = new int[node_count];
        boolean[] visited = new boolean[node_count];
        visited[without] = true;
        visited[target] = true;
        todo[0] = target;
        int end = 1;
        for (int i = 0; i != end; i++) {
            int n = todo[i];
            for (int c : tree.conn[n]) {
                if (!visited[c]) {
                    todo[end++] = c;
                    visited[c] = true;
                    if (isTerminal[c])
                        return false;
                }
            }
            if (end > 20) {
                return false;
            }
        }
        return true;
    }

//    static int[] calcDistancesWithout(int[][] conn, int target, int without) {
//        int[] result = new int[node_count];
//        Arrays.fill(result, net.qcrist.poetool2024.Graph.NOT_CONNECTED);
//        int[] todo = new int[node_count];
//        boolean[] visited = new boolean[node_count];
//        visited[without] = true;
//        visited[target] = true;
//        todo[0] = target;
//        int i = 0;
//        int end = 1;
//        for (int distance = 0; i != end; distance++) {
//            int dEnd = end;
//            for (; i < dEnd; i++) {
//                int n = todo[i];
//                result[n] = distance;
//                for (int c : conn[n]) {
//                    if (!visited[c]) {
//                        todo[end++] = c;
//                        visited[c] = true;
//                    }
//                }
//            }
//        }
//        return result;
//    }

    static void setupTreeRenderer() {
        final int node_size = 6;
        final int node_size_d2 = node_size / 2;
        final int click_node_size = 8;
        final int click_node_size_d2 = click_node_size / 2;
        ui.show(target_size, target_size, new BiConsumer<Graphics2D, Graphics2D>() {
                    Point resolveNodePosition(Point2D.Double pos) {
                        int x = (int) (pos.x * scale) + target_size / 2;
                        int y = (int) (pos.y * scale) + target_size / 2;
                        return new Point(x, y);
                    }

                    void drawNodes(Graphics2D gfx, Graphics2D mGfx) {

                        for (int i = 0; i < tree.positions.length; i++) {
                            Color color = nodeColorFunction.apply(i);
                            if (color == null)
                                continue;
                            gfx.setColor(color);
                            Point nodePos = resolveNodePosition(tree.positions[i]);
                            int x = nodePos.x;
                            int y = nodePos.y;
                            gfx.fillRect(x - node_size_d2, y - node_size_d2, node_size, node_size);
                            mGfx.setColor(new Color(i + 1));
                            mGfx.fillRect(x - click_node_size_d2, y - click_node_size_d2, click_node_size, click_node_size);
                        }
                    }

                    void drawConnections(Graphics2D gfx, Graphics2D mGfx) {


                        for (int a = 0; a < tree.positions.length; a++) {
                            Point p1 = resolveNodePosition(tree.positions[a]);
                            for (int b : tree.conn[a]) {
                                if (a >= b) continue;
                                Color c = lineColorFunction.apply(a, b);
                                if (c == null) continue;
                                gfx.setColor(c);
                                Point p2 = resolveNodePosition(tree.positions[b]);
                                gfx.drawLine(p1.x, p1.y, p2.x, p2.y);
                            }
                        }

                    }

                    @Override
                    public void accept(Graphics2D gfx, Graphics2D mGfx) {
                        drawNodes(gfx, mGfx);
                        drawConnections(gfx, mGfx);

//                        gfx.setColor(Color.MAGENTA);
//                        for (Pair<String, String> p : pathPairs) {
//                            Point p1 = resolveNodePosition(tree.get(p.getLeft()));
//                            Point p2 = resolveNodePosition(tree.get(p.getRight()));
//                            gfx.drawLine(p1.x, p1.y, p2.x, p2.y);
//                        }

                    }
                }
        );
    }

//    static List<Pair<String, String>> solve_paths(
//            int[][] dist, int[][] conn,
//            Map<String, net.qcrist.poetool2024.SkillTree.Node> tree, Set<String> activeNodes) {
//        int term_count = activeNodes.size();
//
//        int[] terminals = activeNodes.stream()
//                .map(n -> nodeIdToIndex.getOrDefault(n, null))
//                .filter(Objects::nonNull)
//                .mapToInt(x -> x).toArray();
//
//
//        int[] useful_nodes = tree.values().stream()
//                .filter(node -> {
//                    if (activeNodes.contains(node.id))
//                        return true;
//                    if (disabledNodes.contains(node.id))
//                        return false;
//                    return conn[nodeIdToIndex.get(node.id)].length > 2;
//                }).mapToInt(n -> nodeIdToIndex.get(n.id)).toArray();
//
//        //TODO might be more reliable/easy to calc?
//        Set<Integer> goodNodes = new TreeSet<>();
//        for (int a = 0; a < term_count; a++) {
//            for (int b = a + 1; b < term_count; b++) {
//                for (int c = b + 1; c < term_count; c++) {
//                    int minV = Integer.MAX_VALUE;
//                    List<Integer> minN = new ArrayList<>(100);
//                    for (int n = 0; n < node_count; n++) {
//
////                        if (conn[n].length <= 2)
////                            continue;
////                        if (disabledNodes.contains(nodeIndexToNode.get(n).id))
////                            continue;
//                        int sum = net.qcrist.poetool2024.Main.dist[n][a] + net.qcrist.poetool2024.Main.dist[n][b] + net.qcrist.poetool2024.Main.dist[n][c] + 1;
//                        if (sum == minV) {
//                            minN.add(n);
//                        } else if (sum < minV) {
//                            minV = sum;
//                            minN.clear();
//                            minN.add(n);
//                        }
//                    }
//                    goodNodes.addAll(minN);
////                    goodNodes.add(minN.get(0));
////                        System.out.println("minN = " + minN);
//                }
//            }
//        }
//        System.out.println("terminals = " + Arrays.toString(terminals));
//        for (int t : terminals) {
//            goodNodes.add(t);
//        }
//        System.out.println("goodNodes = " + goodNodes);
//        System.out.println("goodNodes.size() = " + goodNodes.size());
//
////        var useful_nodes = goodNodes.stream().mapToInt(i -> i).toArray();
//
//        for (int i : useful_nodes) {
//            if (dist[i][terminals[0]] == net.qcrist.poetool2024.Graph.NOT_CONNECTED)
//                throw new Error("!");
//        }
//
//        return net.qcrist.poetool2024.util.Util.timeV("steiner", () -> {
//
//            System.out.println(1);
//            int MAX = Integer.MAX_VALUE / 4;
//            int[][] minCost = new int[node_count][1 << term_count];
//            int[][][] backtrace = new int[node_count][1 << term_count][2];
//            System.out.println(2);
//            for (int[] m : minCost)
//                Arrays.fill(m, MAX);
//            System.out.println(3);
//            for (int i = 0; i < term_count; i++) {
//                int mask = 1 << i;
//                minCost[terminals[i]][mask] = 0;
//                backtrace[terminals[i]][mask][0] = -1;
//            }
//
//
//            System.out.printf("Steiner Complexity: (nodes=%d) * (terminals=%d)\n", useful_nodes.length, 1 << term_count);
//            net.qcrist.poetool2024.util.Util.time("steinerBusy", () -> {
//                for (int mask = 0; mask < (1 << term_count); mask++) {
////                for (int i = 0; i < node_count; i++) {
//                    for (int i : useful_nodes) {
//                        for (int submask = mask; submask != 0; submask = (submask - 1) & mask) {
//                            int v = minCost[i][submask] + minCost[i][mask ^ submask];
//                            if (v < minCost[i][mask]) {
//                                minCost[i][mask] = v;
//                                backtrace[i][mask][0] = i;
//                                backtrace[i][mask][1] = submask;
//                            }
//                        }
//                        if (minCost[i][mask] < MAX) {
//                            for (int j : useful_nodes) {
////                        for (int j = 0; j < node_count; j++) {
//                                int v = minCost[i][mask] + dist[i][j];
//                                if (v < minCost[j][mask]) {
//                                    minCost[j][mask] = v;
//                                    backtrace[j][mask][0] = i;
//                                    backtrace[j][mask][1] = mask;
//                                }
//                            }
//                        }
//                    }
//                }
//
//            });
//
//            int max_term = (1 << term_count) - 1;
//            System.out.println("term_count = " + term_count);
//            System.out.println("max_term = " + Integer.toBinaryString(max_term));
//
//            int minFound = MAX;
//            int minIdx = -1;
//            for (int i = 0; i < minCost.length; i++) {
//                if (minCost[i][max_term] < minFound) {
//                    minFound = minCost[i][max_term];
//                    minIdx = i;
//                }
//            }
//
//            System.out.println("minFound = " + minFound);
//
//            List<Pair<String, String>> touched = new ArrayList<>();
//            Set<Integer> test = new TreeSet<>();
//            new Object() {
//                void buildPath(int a, int b, List<Integer> list) {
//                    int d = dist[a][b];
//                    list.add(a);
//                    if (a == b) return;
//                    for (int c : conn[a]) {
//                        if (dist[c][b] < d) {
//                            buildPath(c, b, list);
//                            return;
//                        }
//                    }
//                    throw new Error("illegal state");
//                }
//
//                List<Integer> buildPath(int a, int b) {
//                    if (a > b)
//                        return buildPath(b, a);
//                    List<Integer> path = new ArrayList<>();
//                    buildPath(a, b, path);
//                    return path;
//                }
//
//                private String idxToId(int id) {
//                    return nodeIndexToNode.get(id).id;
//                }
//
//                void foo(int node, int mask, int d) {
//                    if (mask == 0) return;
//                    int src_node = backtrace[node][mask][0];
//                    int src_mask = backtrace[node][mask][1];
//
////                    String i = IntStream.range(0, d).mapToObj(x -> "    ").collect(Collectors.joining());
////                    System.out.printf("%snode=%s, mask=%s\n", i, idxToId(node), Integer.toBinaryString(mask));
////                    i += "- ";
//
//                    if (src_node == -1) return;
//                    if (src_node == node) {
//                        foo(src_node, src_mask, d + 1);
//                        foo(src_node, mask ^ src_mask, d + 1);
//                    } else {
////                        List<Integer> p = buildPath(node, src_node);
////                        List<String> ids = p.stream().map(x -> nodeIndexToNode.get(x).id).toList();
////                        System.out.printf("%spath a=%s, b=%s\n", i, idxToId(node), idxToId(src_node));
//
////                        touched.addAll(p);
//                        touched.add(Pair.of(
//                                idxToId(Math.min(node, src_node)),
//                                idxToId(Math.max(node, src_node))
//                        ));
//                        test.add(node);
//                        test.add(src_node);
//                        foo(src_node, src_mask, d + 1);
//                    }
////                    System.out.println("UP");
//                }
//            }.foo(minIdx, max_term, 0);
//
//            System.out.println("goodNodes.containsAll(test) = " + goodNodes.containsAll(test));
//
////            System.out.println("touched = " + touched);
//            System.out.println("test = " + test);
//            return touched;
//
////            List<String> list = touched.stream().map(x -> nodeIndexToNode.get(x).id).toList();
////            System.out.println("list.size() = " + list.size());
////            return list;
//        });
//    }

}
