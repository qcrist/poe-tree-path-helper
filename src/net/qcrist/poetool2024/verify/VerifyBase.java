package net.qcrist.poetool2024.verify;

import lombok.RequiredArgsConstructor;
import net.qcrist.poetool2024.Graph;
import net.qcrist.poetool2024.truth.Truth;
import net.qcrist.poetool2024.util.TableRenderer;
import net.qcrist.poetool2024.util.Util;
import net.qcrist.poetool2024.solver.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public abstract class VerifyBase<I> {


    protected abstract void init(Graph g);

    protected abstract I calculate(Graph g, Truth t);

    protected abstract boolean verify(Graph g, Truth t, I result);

    protected abstract int limit(int length);

    protected String notes(int length) {
        return "N/A";
    }

    public void verify() throws IOException {
        List<Truth> truths = Truth.loadTruths(Truth.TRUTHS_DIR);

//        Graph g = Graph.load(Path.of("skill-tree-3.25-graph.json"));
        Graph g = Graph.load(Path.of("skill-tree-3.25-graph.json"));

        Util.time("verify truth data", () -> {
            truths.forEach(t -> {
                if (!verifySolution(g, t.target, t.path)) {
                    System.out.println("!!! Invalid solution generated in TRUTH DATA!!!: " + t.source);
                }
            });
        });

        ArrayList<Truth> truthsShuffled = new ArrayList<>(truths);
        Collections.shuffle(truthsShuffled, new Random(0));

        Map<Integer, List<Truth>> partitions = truthsShuffled.stream().collect(Collectors.groupingBy(t -> t.target.length));
        List<Truth> active_truths = partitions.values().stream().flatMap(
                x -> x.stream()
                        .limit(limit(x.getFirst().length))
//                        .limit((int) Math.floor(Math.pow(150d / x.getFirst().length, 2d) / 10d))
        ).toList();

//        List<Truth> active_truths = new ArrayList<>(truthsShuffled.stream()
////                .filter(t -> t.target.length <= 8)
//                .limit(3000)
////                .filter(t -> t.target.length == 10)
//                .toList());
////        Collections.shuffle(active_truths, new Random(0));


        int hash = Arrays.hashCode(active_truths.stream().mapToInt(t -> Objects.hash(t.source.toString(), Arrays.hashCode(t.target))).toArray());
        System.out.printf("verify hash = %x\n", hash);

        System.out.println("active_truths.size() = " + active_truths.size());
        int maxTargetLength = active_truths.stream().mapToInt(t -> t.target.length).max().orElseThrow();

        AtomicInteger[] counts = new AtomicInteger[maxTargetLength + 1];
        AtomicLong[] times = new AtomicLong[counts.length];
        AtomicInteger[] accuracy = new AtomicInteger[counts.length];
        for (int i = 0; i < times.length; i++) {
            times[i] = new AtomicLong(0);
            counts[i] = new AtomicInteger(0);
            accuracy[i] = new AtomicInteger(0);
        }

        Util.time("init", () -> this.init(g));

        Util.time("verify", () ->
                active_truths.stream()
                        .parallel()
                        .map(Util.progressIndicator(active_truths.size(), 5000))
                        .forEach(t -> {
                            Instant start = Instant.now();
                            I i = this.calculate(g, t);
                            Instant end = Instant.now();
                            counts[t.target.length].incrementAndGet();
                            times[t.target.length].addAndGet(Duration.between(start, end).toNanos());
                            if (this.verify(g, t, i)) {
                                accuracy[t.target.length].incrementAndGet();
                            }
                        })
        );

        System.out.println("Average solve times:");
        List<Object[]> rows = new ArrayList<>();
        rows.add(new Object[]{"target", "count", "failed", "failed_pct", "average_ms", "total_ms", "notes"});

        for (int i = 0; i < counts.length; i++) {
            int count = counts[i].get();
            if (count == 0) continue;
            long took = times[i].get();
            int acc = accuracy[i].get();
            Duration dur = Duration.ofNanos(took / count);
            Object ms = dur.toMillis();
            if ((long) ms == 0) {
                ms = String.format("%.02f", dur.toNanos() / 1000000d);
            }
            long total_ms = Duration.ofNanos(took).toMillis();
            long failed = count - acc;
            String failed_pct = String.format("%.02f%%", failed * 100d / count);

//            System.out.printf("target=%d, count=%d, acc=%02d%% avg_ms=%dms total_ms=%dms\n", i, count, acc_pct, ms, total_ms);
            rows.add(new Object[]{i, count, failed, failed_pct, ms, total_ms, this.notes(i)});
        }

        TableRenderer.render(rows);
    }

    public static boolean verifySolution(Graph g, int[] targets, int[] path) {
        boolean[] isTarget = new boolean[g.node_count];
        for (int t : targets) {
            isTarget[t] = true;
        }

        boolean[] isInPath = new boolean[g.node_count];
        for (int t : path) {
            isInPath[t] = true;
        }

        boolean[] reached = new boolean[g.node_count];
        int[] todo = new int[g.node_count];
        int at = 0;
        int next = 1;
        todo[0] = targets[0];
        reached[targets[0]] = true;

        int targets_added = 1;

        while (at < next) {
            int read = todo[at++];
            for (int c : g.conn[read]) {
                if (isInPath[c] && !reached[c]) {
                    todo[next++] = c;
                    reached[c] = true;
                    if (isTarget[c]) {
                        targets_added++;
                        if (targets_added == targets.length)
                            return true;
                    }
                }
            }
        }

        return false;
    }
}
