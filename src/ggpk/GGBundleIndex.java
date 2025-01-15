package ggpk;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.codec.digest.MurmurHash2;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class GGBundleIndex {
    private record PathSpec(
            long hash,
            int payload_offset,
            int payload_size,
            int payload_rec_size
    ) {
    }

    @SuppressWarnings("ClassCanBeRecord")
    @RequiredArgsConstructor
    public static class BundleIndexFile {
        public final String bundle;
        public final int file_offset;
        public final int file_size;

        private String bundleName = null;

        public String getBundleName() {
            if (bundleName != null) return bundleName;
            return bundleName = String.format("Bundles2/%s.bundle.bin", bundle);
        }
    }

    public static Map<String, BundleIndexFile> parseBundleIndex(GGPK gg) throws IOException {
        GGPK.File index = gg.files.get("Bundles2/_.index.bin");
        ByteBuffer data = GGBundle.readFully(DataView.of(index)).order(ByteOrder.LITTLE_ENDIAN);

        int bundlesCount = data.getInt();
        System.out.println("bundlesCount = " + bundlesCount);
        String[] bundles = new String[bundlesCount];
        for (int i = 0; i < bundlesCount; i++) {
            int nameLength = data.getInt();
            byte[] nameBytes = new byte[nameLength];
            data.get(nameBytes);
            String name = new String(nameBytes, 0, nameLength, StandardCharsets.US_ASCII);
            bundles[i] = name;
            int uncompressedLength = data.getInt();
//                System.out.printf("[%s] len=%d\n", name, uncompressedLength);
        }


        int file_count = data.getInt();
        Map<Long, BundleIndexFile> files = new HashMap<>(file_count);
        for (int i = 0; i < file_count; i++) {
            long hash = data.getLong();
            int bundle_index = data.getInt();
            int file_offset = data.getInt();
            int file_size = data.getInt();
            files.put(hash, new BundleIndexFile(bundles[bundle_index], file_offset, file_size));
        }

//        System.out.println("file_count = " + file_count);


        int path_rep_count = data.getInt();
        List<PathSpec> paths = new ArrayList<>(path_rep_count);
        for (int i = 0; i < path_rep_count; i++) {
            long hash = data.getLong();
            int payload_offset = data.getInt();
            int payload_size = data.getInt();
            int payload_rec_size = data.getInt();
            paths.add(new PathSpec(hash, payload_offset, payload_size, payload_rec_size));
        }

//        System.out.println("path_rep_count = " + path_rep_count);

        DataView dv = DataView.of(data.slice(data.position(), data.remaining()));
        ByteBuffer pathData = GGBundle.readFully(dv).order(ByteOrder.LITTLE_ENDIAN);

        Map<Long, String> hashSolved = new HashMap<>(file_count);

        for (PathSpec p : paths) {
            ByteBuffer slice = pathData.slice(p.payload_offset, p.payload_size).order(ByteOrder.LITTLE_ENDIAN);
            List<String> gen = generatePaths(slice);
            for (String s : gen) {
                long hash = murmur64A(s);
                hashSolved.put(hash, s);
            }
        }

        Map<String, BundleIndexFile> bundleIndexFiles = new LinkedHashMap<>(file_count);
        for (Map.Entry<Long, BundleIndexFile> e : files.entrySet()) {
            String path = hashSolved.remove(e.getKey());
            if (path == null) {
//                throw new Error("failed to resolve file");
                System.out.printf("failed to resolve file: %s\n", e.getValue());
                continue;
            }
            bundleIndexFiles.put(path, e.getValue());
        }
        if (!hashSolved.isEmpty()) {
            hashSolved.forEach((k, v) -> {
                System.out.printf("Failed to match path: %s\n", v);
            });
        }

        return bundleIndexFiles;
    }

    private static List<String> generatePaths(ByteBuffer buff) {
        int phase = 1;
        List<String> names = new ArrayList<>();
        List<String> output = new ArrayList<>();

        while (buff.hasRemaining()) {
            int n = buff.getInt();
            if (n == 0) {
                phase = 1 - phase;
                continue;
            }
            StringBuilder sb = new StringBuilder();
            while (true) {
                byte read = buff.get();
                if (read == 0) break;
                sb.append((char) read);
            }
            String str = sb.toString();
            if (n - 1 < names.size()) {
                str = names.get(n - 1) + str;
            }
            if (phase == 0) {
                names.add(str);
            } else {
                output.add(str);
            }
        }

        return output;
    }

    private static long murmur64A(String path) {
        byte[] data = path.getBytes(StandardCharsets.US_ASCII);
        ByteBuffer bb = ByteBuffer.wrap(data).order(ByteOrder.LITTLE_ENDIAN);
        long seed = 0x1337b33f;

        long m = 0xc6a4a7935bd1e995L;
        int r = 47;
        long h = seed ^ (data.length * m);

        while (bb.remaining() >= 8) {
            long k = bb.getLong();
            k *= m;
            k ^= k >>> r;
            k *= m;
            h ^= k;
            h *= m;
        }

        byte[] data2 = new byte[bb.remaining()];
        bb.get(data2);

        switch (data2.length & 7) {
            case 7:
                h ^= ((long) (data2[6] & 0xFF)) << 48;
            case 6:
                h ^= ((long) (data2[5] & 0xFF)) << 40;
            case 5:
                h ^= ((long) (data2[4] & 0xFF)) << 32;
            case 4:
                h ^= ((long) (data2[3] & 0xFF)) << 24;
            case 3:
                h ^= ((long) (data2[2] & 0xFF)) << 16;
            case 2:
                h ^= ((long) (data2[1] & 0xFF)) << 8;
            case 1:
                h ^= data2[0] & 0xFF;
                h *= m;
        }

        h ^= h >>> r;
        h *= m;
        h ^= h >>> r;

        return h;

    }

    public static void main(String[] args) throws IOException {

    }
}
