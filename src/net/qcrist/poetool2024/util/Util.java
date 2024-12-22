package net.qcrist.poetool2024.util;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.function.Supplier;

public class Util {

    public static void time(String name, Runnable run) {
        Instant start = Instant.now();
        run.run();
        Instant stop = Instant.now();
        Duration took = Duration.between(start, stop);
        System.out.printf("%s took %dms\n", name, took.toMillis());
    }

    public static <E extends Throwable> void timeE(String name, RunnableWithThrows<E> run) throws E {
        Instant start = Instant.now();
        run.run();
        Instant stop = Instant.now();
        Duration took = Duration.between(start, stop);
        System.out.printf("%s took %dms\n", name, took.toMillis());
    }

    public static <T> T timeV(String name, Supplier<T> run) {
        Instant start = Instant.now();
        T value = run.get();
        Instant stop = Instant.now();
        Duration took = Duration.between(start, stop);
        System.out.printf("%s took %dms\n", name, took.toMillis());
        return value;
    }

    public interface SupplierWithThrows<T, E extends Throwable> {
        T get() throws E;
    }

    public interface RunnableWithThrows<E extends Throwable> {
        void run() throws E;
    }

    public static <T, E extends Throwable> T timeVE(String name, SupplierWithThrows<T, E> run) throws E {
        Instant start = Instant.now();
        T value = run.get();
        Instant stop = Instant.now();
        Duration took = Duration.between(start, stop);
        System.out.printf("%s took %dms\n", name, took.toMillis());
        return value;
    }

    public static <T> Function<T, T> progressIndicator(int total, int update_ms) {
        return new Function<>() {
            final AtomicInteger count = new AtomicInteger();
            Instant last_update = Instant.now();

            @Override
            public synchronized T apply(T t) {
                int current = count.incrementAndGet();
                Instant now = Instant.now();
                if (Duration.between(last_update, now).toMillis() > update_ms) {
                    last_update = now;
                    System.out.printf("Progress: %.02f\n", 100.0 * current / total);
                }
                return t;
            }
        };
    }

    public static class MinISingle<T> {
        public int min = Integer.MAX_VALUE;
        public T value = null;

        public void offer(int size, T v) {
            if (size < min) {
                value = v;
                min = size;
            }
        }

        public void offer(int size, Supplier<T> s) {
            if (size < min) {
                value = s.get();
                min = size;
            }
        }

        public void offer(MinISingle<T> other) {
            if (other.min < min) {
                value = other.value;
                min = other.min;
            }
        }

        public T get() {
            return value;
        }

        public void reset() {
            min = Integer.MAX_VALUE;
            value = null;
        }
    }


    public static short[] intArrayToShortArray(int[] data) {
        short[] result = new short[data.length];
        for (int i = 0; i < data.length; i++) {
            result[i] = (short) data[i];
        }
        return result;
    }

    public static byte[] intArrayToByteArray(int[] data) {
        byte[] result = new byte[data.length];
        for (int i = 0; i < data.length; i++) {
            result[i] = (byte) data[i];
        }
        return result;
    }

    public static int[] byteArrayToIntArray(byte[] data) {
        int[] result = new int[data.length];
        for (int i = 0; i < data.length; i++) {
            result[i] = data[i];
        }
        return result;
    }

    public static int[] shortArrayToIntArray(short[] data) {
        int[] result = new int[data.length];
        for (int i = 0; i < data.length; i++) {
            result[i] = data[i];
        }
        return result;
    }
}
