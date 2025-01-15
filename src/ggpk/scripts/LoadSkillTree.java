package ggpk.scripts;

import ggpk.*;
import ggpk.generated.DatTypesV2;
import lombok.SneakyThrows;
import magick.MagickException;
import org.json.JSONObject;

import javax.imageio.ImageIO;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.function.Consumer;

import static ggpk.CSD.buildStatRenderer;

public class LoadSkillTree {
    private record UIImageEntry(
            String name,
            String dds,
            Rectangle rect
    ) {
    }

    private static void parseUIImgLine(String line, Map<String, UIImageEntry> uiShopEntries) {
        List<String> parts = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        char[] chars = line.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char ch = chars[i];
            if (ch == ' ') {
                parts.add(builder.toString());
                builder.setLength(0);
            } else if (ch == '"') {
                while (chars[++i] != '"')
                    builder.append(chars[i]);
            } else {
                builder.append(ch);
            }
        }
        parts.add(builder.toString());
        uiShopEntries.put(parts.getFirst(),
                new UIImageEntry(parts.getFirst(), parts.get(1), new Rectangle(
                        Integer.parseInt(parts.get(2)),
                        Integer.parseInt(parts.get(3)),
                        Integer.parseInt(parts.get(4)),
                        Integer.parseInt(parts.get(5))
                ))
        );
    }


    public static void main(String[] args) throws IOException, MagickException {
        Path ggpk = Path.of("G:\\Path of Exile 2\\Content.ggpk");
        Path output = Path.of("data-extract");
        Files.createDirectories(output);

        GGPK gg = new GGPK(ggpk);

        List<DatTypesV2.PassiveSkillTrees> skillTrees = DatReader.dumpTable(gg, DatTypesV2.PassiveSkillTrees.class);
        List<DatTypesV2.PassiveSkills> passiveSkills = DatReader.dumpTable(gg, DatTypesV2.PassiveSkills.class);
        List<DatTypesV2.PassiveSkillTreeUIArt> treeArt = DatReader.dumpTable(gg, DatTypesV2.PassiveSkillTreeUIArt.class);
        List<DatTypesV2.Stats> stats = DatReader.dumpTable(gg, DatTypesV2.Stats.class);
        List<DatTypesV2.PassiveSkillMasteryGroups> masteryGroups = DatReader.dumpTable(gg, DatTypesV2.PassiveSkillMasteryGroups.class);

        Map<Integer, DatTypesV2.PassiveSkills> passiveSkillsLookupByGraphId = new HashMap<>();
        passiveSkills.forEach(s -> passiveSkillsLookupByGraphId.put(s.PassiveSkillGraphId & 0xFFFF, s));

        Path tree_wip = Path.of("tree_wip");
        Files.createDirectories(tree_wip);

        CSD.StatRenderer statRenderer = buildStatRenderer(gg, "metadata/statdescriptions/passive_skill_stat_descriptions.csd");

        Map<String, UIImageEntry> uiImages = new HashMap<>();
        for (String src : List.of("art/uishopimages.txt", "art/uidivinationimages.txt", "art/uiimages1.txt"))
            new String(gg.bc.read(gg.index.get(src)), StandardCharsets.UTF_16LE).lines().forEach(line -> {
                parseUIImgLine(line, uiImages);
            });

//        for (Map.Entry<String, GGBundleIndex.BundleIndexFile> e : gg.index.entrySet()) {
//            if (e.getValue().bundle.equalsIgnoreCase("Folders/art"))
//                System.out.println("e.getKey() = " + e.getKey());
//        }

//        Files.writeString(Path.of("file_list_export.txt"), String.join("\n", gg.index.keySet()));

//        for (String k : gg.index.keySet()) {
//            String l = k.toLowerCase();
//            if (l.startsWith("art/2dart/passivetree/4k") && l.endsWith(".dds")) {
//                System.out.println("k = " + k);
//                String name = Path.of(k).getFileName().toString();
//                Files.write(Path.of("binary_wip").resolve(name), gg.bc.read(gg.index.get(k)));
//            }
//        }


        Path magickPath = Path.of("C:\\Program Files\\ImageMagick-7.1.1-Q16-HDRI\\magick.exe");

        List<String> export = List.of("Default", "Atlas");

        for (DatTypesV2.PassiveSkillTrees tree : skillTrees) {
            Set<String> icons = new HashSet<>();
            String treeDataPath = tree.PassiveSkillGraph;

            if (!export.contains(tree.Id))
                continue;
            System.out.println("tree.Id = " + tree.Id);
            System.out.println("treeDataPath = " + treeDataPath);
            byte[] data = gg.bc.read(treeDataPath + ".psg");
            Files.write(Path.of("binary_wip", tree.Id + ".psg"), data);
            PSG.PSGRoot treeGraph = PSG.parse(data);

            DatTypesV2.PassiveSkillTreeUIArt art = tree.UIArt.lookup(treeArt);
            JSONObject framesObj = new JSONObject();
            framesObj.put("KeystoneFrameActive", art.KeystoneFrameActive);
            framesObj.put("KeystoneFrameNormal", art.KeystoneFrameNormal);
            framesObj.put("NotableFrameActive", art.NotableFrameActive);
            framesObj.put("NotableFrameNormal", art.NotableFrameNormal);
            framesObj.put("PassiveFrameActive", art.PassiveFrameActive);
            framesObj.put("PassiveFrameNormal", art.PassiveFrameNormal);
//            Files.writeString(Path.of("skill-tree-frame-icons.json"), artObj.toString(1));
            framesObj.toMap().values().forEach(x -> icons.add(x.toString()));

            JSONObject treeJson = treeGraph.toJson(new PSG.ToJsonHelper() {
                @Override
                public void onNode(PSG.PSGPassive p, JSONObject jso) {
//                    if (p.id == 38068)
//                        throw new Util.DebuggerBreakpoint();
                    DatTypesV2.PassiveSkills lookup = passiveSkillsLookupByGraphId.get(p.id & 0xFFFF);


                    List<String> statLines = statsForPassive(lookup, statRenderer, stats);


//                    lookup.Sta

                    jso.put("isNotable", lookup.IsNotable);
//                    jso.put("AscendancyKey", lookup.AscendancyKey);
                    jso.put("isAscendancyStartingNode", lookup.IsAscendancyStartingNode);
//                    jso.put("skillType", lookup.SkillType);
                    jso.put("icon", lookup.Icon_DDSFile);
//                    jso.put("MasteryGroup", lookup.MasteryGroup);
                    jso.put("isKeystone", lookup.IsKeystone);
//                    jso.put("isMultipleChoice", lookup.IsMultipleChoice);
                    jso.put("isJewelSocket", lookup.IsJewelSocket);
                    jso.put("isAnointmentOnly", lookup.IsAnointmentOnly);

                    jso.put("isRootNode", Arrays.stream(treeGraph.passives).anyMatch(i -> p.id == i));
//                    jso.put("isRootNode", lookup.IsRootOfAtlasTree);
                    jso.put("isJustIcon", lookup.IsJustIcon);
                    DatTypesV2.PassiveSkillMasteryGroups mastery = lookup.MasteryGroup.lookup(masteryGroups);
                    if (mastery != null) {
                        jso.put("mastery_icon", mastery.ActiveEffectImage);
                        icons.add(mastery.ActiveEffectImage);
                    }
//                    jso.put("isExpansion", lookup.IsExpansion);
                    jso.put("stats", statLines);
                    jso.put("name", lookup.Name);

                    icons.add(lookup.Icon_DDSFile);
                }
            });
            treeJson.put("frames", framesObj);

            String file_name_id = tree.Id.toLowerCase();
            Files.writeString(output.resolve("tree-export-" + file_name_id + ".json"), treeJson.toString());

//            String sf = Path.of(treeDataPath).getFileName().toString() + ".psg";
//            Files.write(tree_wip.resolve(sf), data);
//            System.out.println("data.length = " + data.length);


            JSONObject images = new JSONObject();
            List<Runnable> todo = new ArrayList<>();
            final Object $LOCK = new Object[0];
            for (String ico : icons) {
                UIImageEntry def = uiImages.get(ico);
                GGBundleIndex.BundleIndexFile z = gg.index.get(ico.toLowerCase());
                if (z == null) {
                    z = gg.index.get(def.dds.toLowerCase());
                }
                byte[] imgData = gg.bc.read(z);

                todo.add(() -> {
                    try {
                        Process process = new ProcessBuilder()
                                .command(List.of(
                                        magickPath.toAbsolutePath().toString(),
                                        "dds:-",
                                        "png:-"
                                ))
                                .start();
                        process.getOutputStream().write(imgData);
                        process.getOutputStream().close();
                        byte[] result = process.getInputStream().readAllBytes();

                        if (def != null) {
                            System.out.printf("cropping: %s\n", ico);
                            ByteArrayInputStream bin = new ByteArrayInputStream(result);
                            BufferedImage img = ImageIO.read(bin);
                            ByteArrayOutputStream bout = new ByteArrayOutputStream(result.length * 2);
                            ImageIO.write(img.getSubimage(
                                    def.rect.x,
                                    def.rect.y,
                                    def.rect.width,
                                    def.rect.height
                            ), "PNG", bout);
                            result = bout.toByteArray();
                        }


//                System.out.println("result.length = " + result.length);
                        String bImg64 = Base64.getEncoder().encodeToString(result);
                        synchronized ($LOCK) {
                            images.put(ico, bImg64);
                        }
                    } catch (IOException ioex) {
                        throw new Error(ioex);
                    }
                });
            }
            todo.parallelStream().forEach(Runnable::run);

            Files.writeString(output.resolve("art-extract-" + file_name_id + ".json"), images.toString(1));

        }


//        gg.bc.read()
    }

    public static List<String> statsForPassive(DatTypesV2.PassiveSkills passive, CSD.StatRenderer renderer, List<DatTypesV2.Stats> stats) {
        LinkedList<Integer> statValues = new LinkedList<>(List.of(
                passive.Stat1Value,
                passive.Stat2Value,
                passive.Stat3Value,
                passive.Stat4Value,
                passive.Stat5Value
        ));
        LinkedHashSet<String> statLines = new LinkedHashSet<>();
        for (int i = 0; i < passive.Stats.size(); i++) {
            DatTypesV2.Stats stat = passive.Stats.get(i).lookup(stats);
            statLines.addAll(renderer.render(stat.Id, statValues));
        }
        statLines.remove(passive.Name);
        return statLines.stream().toList();
    }
}
