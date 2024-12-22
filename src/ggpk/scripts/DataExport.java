package ggpk.scripts;

import ggpk.BundleCache;
import ggpk.GGBundleIndex;
import ggpk.GGPK;
import lombok.SneakyThrows;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.zip.CRC32;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static ggpk.GGBundleIndex.parseBundleIndex;

public class DataExport {
    private static long crc32(byte[] data) {
        CRC32 crc = new CRC32();
        crc.update(data);
        return crc.getValue();
    }

    public static void main(String[] args) throws IOException {
        Path ggpk = Path.of("G:\\Path of Exile 2\\Content.ggpk");

        GGPK gg = new GGPK(ggpk);

        System.out.println("index.size() = " + gg.index.size());

        try (ZipOutputStream zout = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream("structure.zip")))) {
            gg.index.forEach(new BiConsumer<String, GGBundleIndex.BundleIndexFile>() {
                @Override
                @SneakyThrows//(IOException.class)
                public void accept(String k, GGBundleIndex.BundleIndexFile v) {
                    if (!k.startsWith("data/") || !k.endsWith(".datc64"))
                        return;
                    if (k.split("data/")[1].indexOf('/') != -1)
                        return;

                    byte[] read = gg.bc.read(v);

                    ZipEntry ze = new ZipEntry(k);
                    ze.setMethod(ZipEntry.STORED);
                    ze.setSize(read.length);
                    ze.setCrc(crc32(read));
                    zout.putNextEntry(ze);
                    zout.write(read);
                    zout.closeEntry();
                }
            });
        }


    }
}
