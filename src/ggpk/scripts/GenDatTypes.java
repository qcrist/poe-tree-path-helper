package ggpk.scripts;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ClassUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GenDatTypes {
    private static class JPrintStream extends PrintStream {
        public JPrintStream(File file) throws FileNotFoundException {
            super(new BufferedOutputStream(new FileOutputStream(file)));
        }

        public interface IndentRunnable {
            void run() throws IOException;
        }

        int indent = 0;

        public void indent(IndentRunnable run) throws IOException {
            indent++;
            run.run();
            indent--;
        }

        public void indent(String start, String end, IndentRunnable run) throws IOException {
            printlnf(start);
            indent(run);
            printlnf(end);
        }

        public void printlnf(String format, Object... args) {
            this.print("    ".repeat(indent));
            this.println(String.format(format, args));
        }
    }

    private static String fieldTypeMap(String type, boolean isArray) {
        if (isArray) {
            String el = fieldTypeMap(type, false);
            if (el.equalsIgnoreCase("int")) return "List<Integer>";
            return String.format("List<%s%s>", el.substring(0, 1).toUpperCase(), el.substring(1));
        }

        return switch (type) {
            case "string" -> "String";
            case "foreignrow" -> "ForeignRow";
            case "row" -> "long";
            case "bool" -> "boolean";
            case "i32", "enumrow", "u32" -> "int";
            case "i16", "u16" -> "short";
            case "f32" -> "float";
//            case "array" -> "ARRAY_TODO";
            default -> throw new IllegalStateException("Unexpected value: " + type);
        };
    }

    private static int fieldSizeMap(String type, boolean is_array) {
        if (is_array)
            return 16;
        return switch (type) {
            case "string", "row" -> 8;
            case "foreignrow", "array" -> 16;
            case "bool" -> 1;
            case "i32", "enumrow", "f32", "u32" -> 4;
            case "i16", "u16" -> 2;
            default -> throw new IllegalStateException("Unexpected value: " + type);
        };
    }

    public static void main(String[] args) throws IOException {
        Path target_dir = Path.of("generated/ggpk/generated/");
        Files.createDirectories(target_dir);
        List<Integer> versions = List.of(1, 2);

        String schemaURL = "https://github.com/poe-tool-dev/dat-schema/releases/download/latest/schema.min.json";
        String schema = IOUtils.toString(URI.create(schemaURL), StandardCharsets.UTF_8);
        JSONObject obj = new JSONObject(schema);

        for (int v = 0; v < 2; v++) {
            int version = versions.get(v);
            String datTypes = "DatTypesV" + version;
            Path target = target_dir.resolve(datTypes + ".java");
            try (JPrintStream out = new JPrintStream(target.toFile())) {
                out.println("package ggpk.generated;\n");
                out.println("import java.lang.annotation.*;");
                out.println("import java.util.*;\n");
                out.println("import ggpk.DatReader.*;\n");
                out.indent("public class " + datTypes + " {", "}", () -> {
                    List<String> classes = new ArrayList<>();

//                    out.printlnf("@Retention(RetentionPolicy.RUNTIME)");
//                    out.printlnf("@Target(ElementType.FIELD)");
//                    out.printlnf("public @interface Offset {");
//                    out.printlnf("    int value();");
//                    out.printlnf("}");

//                    out.printlnf("public interface DatType {");
//                    out.printlnf("}");

                    if (obj.getInt("version") != 6)
                        throw new Error("unexpected schema version: " + obj.getInt("version"));

                    JSONArray tables = obj.getJSONArray("tables");
                    for (int i = 0; i < tables.length(); i++) {
                        JSONObject jso = tables.getJSONObject(i);
                        int vf = jso.getInt("validFor");
                        if ((vf & version) == 0) continue;
                        String tableName = jso.getString("name");

                        classes.add(tableName);
                        out.indent(String.format("public static class %s implements DatType{", tableName), "}", () -> {
                            JSONArray cols = jso.getJSONArray("columns");
                            int offset = 0;
                            for (int c = 0; c < cols.length(); c++) {
                                JSONObject col = cols.getJSONObject(c);
                                String rawType = col.getString("type");
                                boolean isArray = col.getBoolean("array");
                                String name = col.optString("name");
                                if (!name.isEmpty()) {
                                    String type = fieldTypeMap(rawType, isArray);
//                                name = String.format("unk%02d", c);
//                            boolean isArray = col.getBoolean("array");
                                    out.printlnf("@Offset(%d) public %s %s; //%s", offset, type, name, rawType);
                                }
                                offset += fieldSizeMap(rawType, isArray);
//                            System.out.println(1);
                            }
                        });
                    }

                    out.indent("public static final List<Class<? extends DatType>> TYPES = List.of(", ");", () -> {
                        Collections.sort(classes);
                        for (int i = 0; i < classes.size(); i++) {
                            String c = classes.get(i);
                            out.printlnf("%s.class%s", c, i + 1 == classes.size() ? "" : ",");
                        }
                    });
                });
            }
        }
    }
}
