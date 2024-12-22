package ggpk.scripts;

import ggpk.GGPK;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DumpFile {
    public static void main(String[] args) throws IOException {
        Path ggpk = Path.of("G:\\Path of Exile 2\\Content.ggpk");
        GGPK gg = new GGPK(ggpk);

        Path root = Path.of("binary_wip/dump_file/");
        String to_dump = "shaders/renderer/nodes/utilitynodes.ffx";
        Path tar = root.resolve(to_dump);
        Files.createDirectories(tar.getParent());
        byte[] b = gg.bc.read(gg.index.get(to_dump));
        Files.write(tar, b);
    }
}
