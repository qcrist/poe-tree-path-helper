package ggpk;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.StandardException;
import org.json.JSONObject;

import java.awt.Point;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class PSG {
    private final ByteBuffer data;

    @StandardException
    private static class FailedRangeCheck extends RuntimeException {
    }

    private static void rangeCheck(int value, int min, int max) throws FailedRangeCheck {
        if (value < min || value > max)
            throw new FailedRangeCheck(String.format("range %d-%d, got: %d", min, max, value));
    }

    private static void rangeCheck(float value, float min, float max) throws FailedRangeCheck {
        if (value < min || value > max)
            throw new FailedRangeCheck(String.format("range %f-%f, got: %f", min, max, value));
    }

    private static void rangeCheck(long value, long min, long max) throws FailedRangeCheck {
        if (value < min || value > max)
            throw new FailedRangeCheck(String.format("range %d-%d, got: %d", min, max, value));
    }

    private int getIntRangeBounded(int min, int max) throws FailedRangeCheck {
        int v = data.getInt();
        rangeCheck(v, min, max);
        return v;
    }

    private float getF32RangeBounded(float min, float max) throws FailedRangeCheck {
        float v = data.getFloat();
        rangeCheck(v, min, max);
        return v;
    }

    private long getLongRangeBounded(long min, long max) throws FailedRangeCheck {
        long v = data.getLong();
        rangeCheck(v, min, max);
        return v;
    }

    private static final int MAX_POSITION = 100_000;
    public static final int NO_RADIUS_PRESENT = -100;

    public class PSGPassiveCon {
        public final int id;
        public final int radius;

        public PSGPassiveCon(PSGPassive passive) {
            id = getIntRangeBounded(0, Integer.MAX_VALUE);
            radius = data.getInt();
        }

        public JSONObject toJson() {
            JSONObject jso = new JSONObject();
            jso.put("id", id);
            jso.put("radius", radius);
            return jso;
        }
    }

    public class PSGPassive {
        public final int id, radius, position;
        public final List<PSGPassiveCon> connections;
        public final List<Integer> connectionsIn = new ArrayList<>();
        public final PSGGroup group;

        public final double x, y;

        private PSGPassive(PSGGroup group) {
            this.group = group;
            id = getIntRangeBounded(0, Integer.MAX_VALUE);
            radius = getIntRangeBounded(0, 100);
            position = getIntRangeBounded(0, 1000);
            int conn_length = getIntRangeBounded(0, 1000);
            connections = IntStream.range(0, conn_length).mapToObj(_ -> new PSGPassiveCon(this)).toList();

            double angle = Math.PI * 2 / group.root.orbits[radius] * position;
            this.x = group.x + orbitRadii[radius] * Math.sin(angle);
            this.y = group.y + orbitRadii[radius] * -Math.cos(angle);
        }

        public JSONObject toJson(ToJsonHelper h) {
            JSONObject jso = new JSONObject();
            jso.put("id", id);
//            jso.put("radius", radius);
//            jso.put("position", position);
            jso.put("connections", Stream.concat(connections.stream().map(x -> x.id), connectionsIn.stream()).sorted().toList());
            jso.put("x", x);
            jso.put("y", y);
            h.onNode(this, jso);
            return jso;
        }


        final int[] orbitRadii = {0, 82, 162, 335, 493, 662, 846, 251, 1080, 1322};
    }

    public class PSGGroup {
        public final float x, y;
        public final int flag;
        public final List<PSGPassive> passives;
        public final PSGRoot root;

        private PSGGroup(PSGRoot root) throws FailedRangeCheck {
            this.root = root;
            this.x = getF32RangeBounded(-MAX_POSITION, MAX_POSITION);
            this.y = getF32RangeBounded(-MAX_POSITION, MAX_POSITION);
            this.flag = data.getInt();
            data.getInt();
            data.get();
            int passive_length = getIntRangeBounded(1, 1000);
            passives = IntStream.range(0, passive_length).mapToObj(_ -> new PSGPassive(this)).toList();
        }

        public JSONObject toJson(ToJsonHelper h) {
            JSONObject jso = new JSONObject();
            jso.put("x", x);
            jso.put("y", y);
//            jso.put("flag", flag);
            jso.put("passives", passives.stream().map(p -> p.toJson(h)).toList());
            return jso;
        }
    }

    private static int VERSION_POE1 = 0x0103;
    private static int VERSION_POE2 = 0x0003;

    public class PSGRoot {
        public final int version;
        public final long[] passives;
        public final List<PSGGroup> groups;
        public final int orbit_count;
        public final int[] orbits;
        public final Map<Integer, PSGPassive> passiveMap = new LinkedHashMap<>();

        private PSGRoot() {
            version = data.getShort();
            if (version != VERSION_POE1 && version != VERSION_POE2)
                throw new Error("PSG Version Mismatch! got: " + version);
            orbit_count = data.get();
            rangeCheck(orbit_count, 1, 20);
            orbits = IntStream.range(0, orbit_count).map(i -> data.get() & 0xFF).toArray();
            int length = getIntRangeBounded(1, 100);
            passives = LongStream.range(0, length).map(_ -> getLongRangeBounded(0, Integer.MAX_VALUE)).toArray();
            int groupLength = getIntRangeBounded(100, 2000);
            groups = IntStream.range(0, groupLength).mapToObj(_ -> new PSGGroup(this)).toList();


            for (PSGGroup g : groups) {
                for (PSGPassive p : g.passives) {
                    passiveMap.put(p.id, p);
                }
            }
            for (PSGGroup g : groups) {
                for (PSGPassive p : g.passives) {
                    for (PSGPassiveCon c : p.connections) {
                        passiveMap.get(c.id).connectionsIn.add(p.id);
                    }
                }
            }
        }

        public JSONObject toJson(ToJsonHelper h) {
            JSONObject jso = new JSONObject();
            jso.put("skillsPerOrbit", orbits);
            jso.put("root_passives", passives);
//            jso.put("groups", groups.stream().map(psgGroup -> psgGroup.toJson(h)).toList());
            JSONObject nodes = new JSONObject();
            for (Map.Entry<Integer, PSGPassive> e : passiveMap.entrySet()) {
                nodes.put(e.getKey().toString(), e.getValue().toJson(h));
            }
            jso.put("nodes", nodes);
            return jso;
        }
    }

    public interface ToJsonHelper {
        void onNode(PSGPassive p, JSONObject jso);
    }

    @SneakyThrows
    public static PSGRoot parse(byte[] data) {
//        Files.write(Path.of("binary_wip/test.psg"), data);

        ByteBuffer bb = ByteBuffer.wrap(data).order(ByteOrder.LITTLE_ENDIAN);

        //        System.out.println("bb.remaining() = " + bb.remaining());

        return new PSG(bb).new PSGRoot();

//        int matched = 0;
//        for (int i = 0; i < 10000; i++) {
//            try {
//                bb.position(i);
//                new PSG(bb).new PSGRoot();
//                System.out.printf("Success! i=%d\n", i);
//                if (matched++ > 2)
//                    return null;
//            } catch (FailedRangeCheck e) {
//                continue;
//            }
//        }
//        throw new Error("failed to find start!");
    }
}
