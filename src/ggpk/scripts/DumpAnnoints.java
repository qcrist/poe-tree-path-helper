package ggpk.scripts;

import ggpk.CSD;
import ggpk.DatReader;
import ggpk.GGPK;
import ggpk.generated.DatTypesV2;
import org.apache.commons.lang3.tuple.Pair;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

import static ggpk.CSD.buildStatRenderer;

public class DumpAnnoints {
    public static void main(String[] args) throws IOException {
        Path ggpk = Path.of("G:\\Path of Exile 2\\Content.ggpk");
        Path output = Path.of("data-extract");
        Files.createDirectories(output);

        GGPK gg = new GGPK(ggpk);

        List<DatTypesV2.PassiveSkills> passiveSkills = DatReader.dumpTable(gg, DatTypesV2.PassiveSkills.class);
        List<DatTypesV2.Stats> stats = DatReader.dumpTable(gg, DatTypesV2.Stats.class);
        var blightCrafts = DatReader.dumpTable(gg, DatTypesV2.BlightCraftingRecipes.class);
        var blightResults = DatReader.dumpTable(gg, DatTypesV2.BlightCraftingResults.class);
        var blightCraftingItems = DatReader.dumpTable(gg, DatTypesV2.BlightCraftingItems.class);

        CSD.StatRenderer statRenderer = buildStatRenderer(gg, "metadata/statdescriptions/passive_skill_stat_descriptions.csd");

        TreeSet<Pair<Integer, String>> list = new TreeSet<>();

        for (DatTypesV2.BlightCraftingRecipes craft : blightCrafts) {
            List<DatTypesV2.BlightCraftingItems> parts = craft.BlightCraftingItemsKeys.stream().map(r -> r.lookup(blightCraftingItems)).toList();
            DatTypesV2.BlightCraftingResults result = craft.BlightCraftingResultsKey.lookup(blightResults);
            DatTypesV2.PassiveSkills passiveSkill = result.PassiveSkillsKey.lookup(passiveSkills);
            if (passiveSkill == null) continue;
            List<String> text = LoadSkillTree.statsForPassive(passiveSkill, statRenderer, stats);
            int cost = parts.stream().mapToInt(p -> (int) Math.max(1, Math.pow(3, p.Tier - 5))).sum();
            String partDesc = parts.stream().map(p -> p.NameShort).toList().toString();
            String nl = " | ";
            String textLine = text.stream().map(x -> x.replaceAll("\r?\n", nl)).collect(Collectors.joining(nl));
            String totalDesc = String.format("%s notable=%s desc=%s", partDesc, passiveSkill.Name, textLine);
            list.add(Pair.of(cost, totalDesc));
        }

        PrintStream pout = new PrintStream(output.resolve("annoints.txt").toFile());
        for (Pair<Integer, String> part : list) {
            pout.printf("cost=%s recipe=%s\n", part.getLeft(), part.getRight());
        }
        pout.close();
    }
}
