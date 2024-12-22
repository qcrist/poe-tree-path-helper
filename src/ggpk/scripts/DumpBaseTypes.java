package ggpk.scripts;

import ggpk.DatReader;
import ggpk.GGPK;
import ggpk.generated.DatTypesV2;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class DumpBaseTypes {
    public static void main(String[] args) throws IOException {
        Path ggpk = Path.of("G:\\Path of Exile 2\\Content.ggpk");

        GGPK gg = new GGPK(ggpk);

        List<DatTypesV2.ArmourTypes> armorTypes = DatReader.dumpTable(gg, DatTypesV2.ArmourTypes.class);
        List<DatTypesV2.BaseItemTypes> baseItemTypes = DatReader.dumpTable(gg, DatTypesV2.BaseItemTypes.class);
        List<DatTypesV2.ItemClasses> itemClasses = DatReader.dumpTable(gg, DatTypesV2.ItemClasses.class);

        List<JSONObject> baseItemTypesJSO = new ArrayList<>();
        Map<String, List<JSONObject>> map = new TreeMap<>();

        for (DatTypesV2.BaseItemTypes baseItemType : baseItemTypes) {
//            if (baseItemType.SiteVisibility == 0) continue;
            JSONObject jso = new JSONObject();
//            jso.put("id", baseItemType.Id);
//            jso.put("cls", itemClasses.get(Math.toIntExact(baseItemType.ItemClassesKey)).Name);

            if (!baseItemType.Name.isEmpty()) {
//                out.put(baseItemType.Name, jso);
                jso.put("name", baseItemType.Name);
                jso.put("dropLevel", baseItemType.DropLevel);
                DatTypesV2.ItemClasses baseClass = itemClasses.get(Math.toIntExact(baseItemType.ItemClassesKey.value));
                String name = baseClass.Name;
                if (name.isEmpty()) {
                    name = "#" + baseClass.Id;
                }
                map.computeIfAbsent(name, _ -> new ArrayList<>())
                        .add(jso);
            }
            baseItemTypesJSO.add(jso);
        }

        for (DatTypesV2.ArmourTypes at : armorTypes) {
            JSONObject o = baseItemTypesJSO.get((int) at.BaseItemTypesKey.value);
            JSONObject jso = new JSONObject();
            o.put("stats", jso);

            jso.put("armour_min", at.Armour);
//            jso.put("armour_max", at.ArmourMax);
            jso.put("evasion_min", at.Evasion);
//            jso.put("evasion_max", at.EvasionMax);
            jso.put("energy_shield_min", at.EnergyShield);
//            jso.put("energy_shield_max", at.EnergyShieldMax);
//            jso.put("ward_min", at.WardMin);
//            jso.put("ward_max", at.WardMax);
//            jso.put("ms", at.IncreasedMovementSpeed);
        }

        JSONObject jso = new JSONObject();
        map.forEach((k, v) -> {
            if (k.isEmpty()) throw new Error("!");
            JSONArray o = new JSONArray();
            Set<String> added = new HashSet<>();
            v.forEach(value -> {
                String key = value.toString();
                if (added.add(key))
                    o.put(value);
            });
            jso.put(k, o);
        });
//        baseItemTypesJSO.forEach(jso::put);

        String jss = jso.toString(1);
        Files.writeString(Path.of("base_types.json"), jss);

        String js = jss
                .replaceAll("\"(\\w+)\"(?=:\\s)", "$1")
                .replaceAll("\"([^\"]+)\"(?=:\\s)", "[\"$1\"]");
        String prefix = "export const BASE_TYPES_RAW = ";
        String suffix = " as const;\n";
        Files.writeString(Path.of("base_types.ts"), prefix + js + suffix);

        System.out.println("done");
    }
}
