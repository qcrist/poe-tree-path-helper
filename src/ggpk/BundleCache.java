package ggpk;

import lombok.RequiredArgsConstructor;
import lombok.experimental.StandardException;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@RequiredArgsConstructor
public class BundleCache {
    private final Map<GGPK.File, GGBundle> bundles = new HashMap<GGPK.File, GGBundle>();

    //    Map<Pair<GGBundle, Integer>, byte[]> chunks = new LinkedHashMap<>();
    private final Map<GGBundle, byte[]> bundleData = new LinkedHashMap<>();
    private final GGPK ggpk;

//    private byte[] read(GGBundle bundle, int chunk) throws IOException {
//        Pair<GGBundle, Integer> key = Pair.of(bundle, chunk);
//        byte[] read = chunks.get(key);
//        if (read == null) {
//            ByteBuffer bb = bundle.readBlock(chunk);
//            byte[] res = new byte[bb.capacity()];
//            bb.get(0, res, 0, res.length);
//            chunks.put(key, read = res);
//        }
//        return read;
//    }

    private byte[] read(GGBundle bundle, int start, int length) throws IOException {
        if (bundle.uncompressed_size > 1024 * 1024 * 1024)
            throw new Error("refusing to read large file!");

        //TODO get this working?
//        byte[] result = new byte[length];
//        int at = start;
//        int end = start + length;
//
//        while (at < end) {
//            int chunkAt = at / GGBundle.BLOCK_GRANULARITY;
//            int prefix = at % GGBundle.BLOCK_GRANULARITY;
//            byte[] bytes = read(bundle, chunkAt);
//            int copy_length = Math.min(bytes.length, end - at);
////            System.out.printf("System.arraycopy(..., %d, ..., %s, %d)\n", 0, at, copy_length);
//            System.arraycopy(bytes, prefix, result, at - start, copy_length);
//            at += copy_length;
//        }

        byte[] read = bundleData.get(bundle);
        if (read == null) {
            bundleData.put(bundle, read = bundle.readAllBytes());
        }
        return Arrays.copyOfRange(read, start, start + length);
//        if (!Arrays.equals(, result))
//            throw new Error("!");

//        return result;
    }

    public byte[] read(GGBundleIndex.BundleIndexFile file) throws IOException {
        String bundle_path = file.getBundleName();
        GGPK.File f = ggpk.files.get(bundle_path);
        return read(f, file);
    }

    public byte[] read(GGPK.File bundleFile, GGBundleIndex.BundleIndexFile file) throws IOException {
        GGBundle b = bundles.get(bundleFile);
        if (b == null) {
            bundles.put(bundleFile, b = new GGBundle(DataView.of(bundleFile)));
        }

        return read(b, file.file_offset, file.file_size);
    }

    @StandardException
    public static class DatTypeNotFoundException extends RuntimeException {

    }

    public byte[] read(Class<? extends DatReader.DatType> cls) throws IOException, DatTypeNotFoundException {
        String name1 = String.format("data/%s.dat64", cls.getSimpleName().toLowerCase());
        String name2 = String.format("data/%s.datc64", cls.getSimpleName().toLowerCase());
        GGBundleIndex.BundleIndexFile index = ggpk.index.get(name1);
        if (index == null)
            index = ggpk.index.get(name2);
        if (index == null)
            throw new DatTypeNotFoundException("failed to find cls: " + cls.getSimpleName());
        return read(index);
    }

    public byte[] read(String path) throws IOException {
        GGBundleIndex.BundleIndexFile idx = ggpk.index.get(path.toLowerCase());
        if (idx == null) {
            throw new Error("no such path!");
        }
        return read(idx);
    }
}
