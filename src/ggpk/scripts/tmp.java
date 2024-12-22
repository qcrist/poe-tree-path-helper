package ggpk.scripts;

import ggpk.BundleCache;
import ggpk.DataView;
import ggpk.GGBundle;
import ggpk.GGPK;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

public class tmp {
    public static void main(String[] args) throws IOException {
        Path ggpk = Path.of("G:\\Path of Exile 2\\Content.ggpk");

        GGPK gg = new GGPK(ggpk);

        System.out.println("index.size() = " + gg.index.size());

        GGPK.File f = gg.files.get("Bundles2/Folders/art.bundle.bin");
        GGBundle b = new GGBundle(DataView.of(f));
        byte[] read = b.readAllBytes();
        StringBuilder sb = new StringBuilder();
        ByteBuffer bb = ByteBuffer.wrap(read).order(ByteOrder.LITTLE_ENDIAN);
        while (bb.remaining() > 0)
            sb.append(bb.getChar());

        Files.writeString(Path.of("binary_wip/art.bundle.bin.raw.txt"), sb.toString());
    }
}
