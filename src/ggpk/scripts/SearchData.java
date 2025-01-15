package ggpk.scripts;

import ggpk.BundleCache;
import ggpk.GGBundle;
import ggpk.GGPK;
import ggpk.generated.DatTypesV2;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

public class SearchData {
    public static void main(String[] args) throws IOException {
        Path ggpk = Path.of("G:\\Path of Exile 2\\Content.ggpk");

        GGPK gg = new GGPK(ggpk);

        System.out.println("index.size() = " + gg.index.size());

        String toFindRaw = "WaystoneTier".toLowerCase();
//        List<byte[]> toFind = new ArrayList<>();
//        toFind.add()

        String toFindBE = new String(toFindRaw.getBytes(StandardCharsets.UTF_16LE), StandardCharsets.US_ASCII);
        String toFindLE = new String(toFindRaw.getBytes(StandardCharsets.UTF_16BE), StandardCharsets.US_ASCII);

//        Arrays.mismatch()



        DatTypesV2.TYPES.parallelStream().forEach((v) -> {
            try {
                byte[] read = gg.bc.read(v);

                if (read == null) {
//                    System.out.printf("failed to find: %s\n", v.getSimpleName());
                    return;
                }

                String s = new String(read, StandardCharsets.US_ASCII).toLowerCase();
                if (s.contains(toFindBE) || s.contains(toFindLE)) {
                    System.out.println("found dssc! k = " + v.getSimpleName());
                }
            } catch (IOException ex) {
//                System.out.println("ex.getMessage() = " + ex.getMessage());
                throw new RuntimeException(ex);
            } catch (GGBundle.DecompressionFailedException | BundleCache.DatTypeNotFoundException dex) {
//                System.out.printf("failed to read: %s\n", v.getSimpleName());
            }

        });

    }
}
