package ggpk.scripts;

import ggpk.*;
import ggpk.generated.DatTypesV2;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Path;
import java.util.List;

public class DumpUniqueGemIds {
    public static void main(String[] args) throws IOException {
        Path ggpk = Path.of("G:\\Path of Exile 2\\Content.ggpk");

        GGPK gg = new GGPK(ggpk);

        List<DatTypesV2.BaseItemTypes> baseItemTypes = DatReader.dumpTable(gg, DatTypesV2.BaseItemTypes.class);
        List<DatTypesV2.SkillGems> skillGems = DatReader.dumpTable(gg, DatTypesV2.SkillGems.class);
        List<DatTypesV2.SkillGemsForUniqueStat> skillGemsForUniqueStats = DatReader.dumpTable(gg, DatTypesV2.SkillGemsForUniqueStat.class);

        PrintStream ps = new PrintStream("binary_wip/unique_gem_ids.txt");
        for (DatTypesV2.SkillGemsForUniqueStat skillGemsForUniqueStat : skillGemsForUniqueStats) {
            String name = skillGemsForUniqueStat.gems.getFirst().lookup(skillGems).BaseItemTypesKey.lookup(baseItemTypes).Name;
            double idx_1 = (skillGemsForUniqueStat.id + 1) / 2d;
            double idx_2 = (skillGemsForUniqueStat.id + 2) / 2d;
            double idx_3 = (skillGemsForUniqueStat.id + 3) / 2d;
            ps.printf("gem=%s, 1=%.01f, 2=%.01f, 3=%.01f\n", name, idx_1, idx_2, idx_3);
        }
        ps.close();


//        gg.index.forEach((k, v) -> {
//            if (!k.endsWith(".csd")) {
//                return;
//            }
//            try {
//                String data = new String(gg.bc.read(v), StandardCharsets.UTF_16LE);
//                for (String line : data.split("\n")) {
//                    if (line.toLowerCase().contains("specific_skill")) {
//                        System.out.printf("k=%s, line=%s\n", k, line);
//                    }
//                }
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        });

//        CSD.StatRenderer statRenderer = buildStatRenderer(gg, "metadata/statdescriptions/passive_skill_stat_descriptions.csd");

//        System.out.println("1 = " + 1);
    }
}
