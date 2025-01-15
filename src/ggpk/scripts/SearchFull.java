package ggpk.scripts;

import ggpk.*;
import org.apache.commons.lang3.tuple.Pair;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.*;

public class SearchFull {
    private static final Set<String> ignoredExt = new HashSet<>();

    private static boolean shouldInspect(GGPK.File f) {
        String[] spl = f.name.split("\\.");
        String ext = spl[spl.length - 1].toLowerCase();
        return switch (ext) {
            case "png", "jpg", "wav", "bk2", "ggdh" -> false;
            case "bin" -> true;
            default -> {
                if (ignoredExt.add(ext)) {
                    System.out.printf("ignored unknown ext: %s (%s)\n", ext, f.name);
                }
                yield false;
            }
        };
    }

    public static void main(String[] args) throws IOException {
        Path ggpk = Path.of("G:\\Path of Exile 2\\Content.ggpk");

        GGPK gg = new GGPK(ggpk);

        System.out.println("index.size() = " + gg.index.size());

        //find __ALL__ of the below strings
        List<byte[]> search = List.of(
                "SetBorderColor".getBytes(StandardCharsets.UTF_16LE),
                "SetBorderColor".getBytes(StandardCharsets.US_ASCII)
//                toFindRaw.getBytes(StandardCharsets.UTF_16LE)
//                toFindRaw.getBytes(StandardCharsets.UTF_16BE),
//                toFindRaw.getBytes(StandardCharsets.UTF_8),
//                toFindRaw.getBytes(StandardCharsets.US_ASCII)
        );

        Map<String, List<Pair<String, GGBundleIndex.BundleIndexFile>>> file_lookup = new HashMap<>();

        gg.index.forEach((k, v) -> {
            String b = v.getBundleName();
            file_lookup.computeIfAbsent(b, _ -> new ArrayList<>())
                    .add(Pair.of(k, v));
        });

        gg.files.entrySet().parallelStream().forEach((e) -> {
            GGPK.File v = e.getValue();
            try {
                if (!shouldInspect(v)) return;
                byte[] read;
                if (v.name.contains(".bundle.")) {
                    GGBundle b = new GGBundle(DataView.of(v));
                    read = b.readAllBytes();
                } else {
                    read = new byte[Math.toIntExact(v.length)];
                    try (DataView dv = DataView.of(v)) {
                        dv.read(ByteBuffer.wrap(read));
                    }
                }

                if (read == null) {
//                    System.out.printf("failed to find: %s\n", v.getSimpleName());
                    return;
                }

                List<Pair<String, GGBundleIndex.BundleIndexFile>> look = file_lookup.get(e.getKey());
                if (look == null) {
                    System.out.printf("no files inside: %s\n", e.getKey());
                    return;
                }

                for (Pair<String, GGBundleIndex.BundleIndexFile> p : look) {
                    GGBundleIndex.BundleIndexFile f = p.getRight();
                    byte[] r = Arrays.copyOfRange(read, f.file_offset, f.file_offset + f.file_size);
                    int mm = handleMatch(r, search);
                    if (mm == -1) continue;
                    System.out.printf("found dssc! k = %s, matched_files=%s\n", v.name, p.getLeft());
                    return;

                }
            } catch (IOException ex) {
//                System.out.println("ex.getMessage() = " + ex.getMessage());
                throw new RuntimeException(ex);
            } catch (GGBundle.DecompressionFailedException | BundleCache.DatTypeNotFoundException dex) {
                System.out.printf("failed to read: %s\n", e.getKey());
            } catch (Throwable th) {
                System.out.printf("err[%s] = %s\n", v.name, th.getMessage());
                throw new Error(th);
            }

        });

    }

    private static int handleMatch(byte[] haystack, List<byte[]> needle) {
        int first = -2;
        for (byte[] bytes : needle) {
            int find = arrayIndexOf(haystack, bytes);
            if (find == -1)
                continue;
            if (first == -2)
                first = find;
        }
        if (first == -2)
            return -1;
        return first;
    }

    private static int arrayIndexOf(byte[] haystack, byte[] needle) {
        for (int i = 0; i < haystack.length - needle.length - 1; i++) {
            if (Arrays.equals(haystack, i, i + needle.length, needle, 0, needle.length))
                return i;
        }
        return -1;
    }
}
