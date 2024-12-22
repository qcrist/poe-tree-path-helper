package ggpk;

import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.*;
import java.util.stream.IntStream;

public class CSD {
    @RequiredArgsConstructor
    public static class StatCommand {
        public final String id;
        public final List<String> lines;
    }

    public static class StatRenderer {
        private final Map<String, List<StatRenderLine>> map = new HashMap<>();

        public List<String> render(String name, LinkedList<Integer> values) {
            List<StatRenderLine> line = map.get(name);
            if (line == null) {
                return List.of("UNKNOWN STAT: " + name);
            }
            List<String> lines = new ArrayList<>();
            StatValueSourceImpl svs = new StatValueSourceImpl(values::get);
            for (StatRenderLine r : line) {
                Optional<String> result = r.render(svs);
                result.ifPresent(lines::add);
            }
            for (int i = 0; i < svs.total_used; i++) {
                values.removeFirst();
            }
            return lines;
        }

        private List<StatRenderLine> listFor(String name) {
            return map.computeIfAbsent(name, _ -> new ArrayList<>());
        }
    }

    private interface StatValueSource {
        int peek(int v);

        int use(int v);
    }


    @RequiredArgsConstructor
    private static class StatValueSourceImpl implements StatValueSource {
        int total_used = 0;
        final Function<Integer, Integer> supplied;

        public int peek(int v) {
            return supplied.apply(v);
        }

        public int use(int v) {
            total_used = Math.max(v + 1, total_used);
            return supplied.apply(v);
        }
    }

    private interface StatRenderLine {
        Optional<String> render(StatValueSource values);
    }

    private static class NoDescription implements StatRenderLine {
        private static final NoDescription INSTANCE = new NoDescription();

        @Override
        public Optional<String> render(StatValueSource values) {
            return Optional.empty();
        }
    }

    private static class WithDescription implements StatRenderLine {
        public final List<Predicate<Integer>> vars;
        public final String text;
        public final Map<String, String> extra;
        public final String raw;

        public WithDescription(String line) {
            this.raw = line;
            CSGScanner scanner = new CSGScanner(line.toCharArray(), true);

            vars = new ArrayList<>();
            extra = new HashMap<>();

            while (scanner.hasMore() && scanner.peekChar() != '"') {
                if (scanner.peek(scanner::readWord).equals("table_only")) {
                    extra.put(scanner.readWord(), "<no_data>");
                    continue;
                }
                vars.add(ValSpec.forWord(scanner.readWord()));
            }

            text = scanner.readStringLiteral();

            while (scanner.hasMore()) {
                String key = scanner.readWord();
                if (scanner.hasMore() && scanner.peekChar() == '1') {
                    extra.put(key, scanner.readWord());
                } else {
                    extra.put(key, "<no_data>");
                }
            }
        }

        private static final Set<String> tmp = new HashSet<>();

        private Number round(double v, int places) {
            double shift = Math.pow(10, places);
            return Math.round(v * shift) / shift;
        }

        private Number transformWithExtra(int i) {
            List<Number> transforms = new ArrayList<>();

            for (String k : extra.keySet()) {
                Number v = switch (k) {
                    case "milliseconds_to_seconds" -> i / 1000d;
                    case "canonical_line" -> null;
                    case "per_minute_to_per_second_2dp_if_required" -> round(i / 60d, 2);
                    case "negate" -> -i;
                    case "per_minute_to_per_second" -> i / 60d;
                    case "divide_by_one_hundred" -> i / 100d;
                    case "divide_by_ten_1dp_if_required" -> round(i / 10d, 1);
                    case "milliseconds_to_seconds_2dp_if_required" -> round(i / 1000d, 2);
                    default -> {
                        throw new Error("unknown extra: " + k);
                    }
                };
                if (v == null) continue;
                transforms.add(v);
            }

            if (transforms.isEmpty())
                return i;
            if (transforms.size() == 1)
                return transforms.getFirst();
            throw new Error("too many transforms!");
        }

        @Override
        public Optional<String> render(StatValueSource src) {
            for (int i = 0; i < vars.size(); i++) {
                if (!vars.get(i).test(src.peek(i))) {
                    return Optional.empty();
                }
            }

            CSGScanner scanner = new CSGScanner(this.text.toCharArray(), false);
            StringBuilder sb = new StringBuilder(this.text.length() * 2);
            while (scanner.hasMore()) {
                switch (scanner.peekChar()) {
                    case '{' -> {
                        scanner.readMagic("{");
                        String contents = scanner.readWhile(ch -> ch != '}', true);
                        scanner.readMagic("}");
                        if (contents.isEmpty()) contents = "0";
                        String[] split = contents.split(":");
                        int arg = Integer.parseInt(split[0]);
                        Number read = transformWithExtra(src.use(arg));

                        if (split.length == 2) {
                            sb.append(switch (split[1]) {
                                case "+d", "d" -> String.format("%" + split[1], read.intValue());
                                default -> throw new Error("unexpected format flag: " + split[1]);
                            });
                        } else if (split.length == 1) {
                            sb.append(read);
                        } else {
                            throw new Error("unexpected split size!");
                        }
                    }
                    case '[' -> {
                        scanner.readMagic("[");
                        String contents = scanner.readWhile(ch -> ch != ']', false);
                        scanner.readMagic("]");
                        String[] spl = contents.split("\\|");
                        sb.append(spl[spl.length - 1]);
                    }
                    case '\\' -> {
                        scanner.readMagic("\\");
                        char control = scanner.readChar();
                        sb.append(switch (control) {
                            case 'n' -> '\n';
                            default -> throw new Error("unexpected escape: \\" + control);
                        });
                    }
                    case '<' -> {
                        scanner.readMagic("<");
                        String tag = scanner.readWhile(ch -> ch != '>', false);
                        scanner.readMagic(">{{");
                        //TODO do something with the tags??;
                    }
                    case '}' -> {
                        scanner.readMagic("}}");
                    }
                    default -> {
                        sb.append(scanner.readChar());
                    }
                }
            }

            return Optional.of(sb.toString());
        }
    }

    @SuppressWarnings("ClassCanBeRecord")
    @RequiredArgsConstructor
    @ToString
    private static class ValSpec implements Predicate<Integer> {
        private final int min, max;

        public static Predicate<Integer> forWord(String word) {
            if (word.startsWith("!")) {
                Predicate<Integer> res = forWord(word.substring(1));
                return Predicate.not(res);
            }

            int min = Integer.MIN_VALUE;
            int max = Integer.MAX_VALUE;

            if (word.equals("#"))
                return new ValSpec(min, max);

            if (!word.contains("|")) {
                int v = Integer.parseInt(word);
                return new ValSpec(v, v);
            }

            String[] parts = word.split("\\|");

            if (!parts[0].equals("#"))
                min = Integer.parseInt(parts[0]);

            if (!parts[1].equals("#"))
                max = Integer.parseInt(parts[1]);

            return new ValSpec(min, max);
        }

        @Override
        public boolean test(Integer i) {
            return i >= min && i <= max;
        }
    }

    public static void main(String[] args) throws IOException {
        Path ggpk = Path.of("G:\\Path of Exile 2\\Content.ggpk");
        GGPK gg = new GGPK(ggpk);

        StatRenderer r = buildStatRenderer(gg, "metadata/statdescriptions/passive_skill_stat_descriptions.csd");
        try (PrintStream out = new PrintStream("binary_wip/wip.csd.txt")) {
            r.map.forEach((k, v) -> {
                out.printf("STAT %s\n", k);
                for (StatRenderLine s : v) {
                    if (s instanceof WithDescription e)
                        out.printf("LINE: [%s] [%s] [%s] (raw=%s)\n", e.vars, e.text, e.extra, e.raw);
                }
                out.println();
            });
        }
    }

    public static StatRenderer buildStatRenderer(GGPK gg, String entry) throws IOException {
        StatRenderer renderer = new StatRenderer();
        LinkedList<String> todo = new LinkedList<>(List.of(entry));
        Set<String> already_done = new HashSet<>(todo);

        while (!todo.isEmpty()) {
            String next = todo.removeFirst();

            GGBundleIndex.BundleIndexFile f = gg.index.get(next.toLowerCase());
            if (f == null)
                throw new Error("unknown csd: " + next);

            byte[] bytes = gg.bc.read(f);
            char[] chars = new String(bytes, StandardCharsets.UTF_16LE).toCharArray();
            Files.writeString(Path.of("binary_wip", "wip.csd"), new String(chars));
            CSGScanner scanner = new CSGScanner(chars, true);
            scanner.skipBOM();
            while (scanner.hasMore()) {
                scanner.readCommand(renderer, path -> {
                    if (already_done.add(path)) {
                        todo.add(path);
                    }
                });
            }
        }

        return renderer;
    }


    public static class CSGScanner {
        private final char[] data;
        private int at = 0;
        private final boolean autoWS;

        public CSGScanner(char[] data, boolean autoWS) {
            this.autoWS = autoWS;
            this.data = data;
        }

        public CSGScanner skipWS() {
            readWhile(Character::isWhitespace, true);
            return this;
        }

        public <T> T peek(Supplier<T> fn) {
            int save = at;
            T res = fn.get();
            at = save;
            return res;
        }

        public CSGScanner skipBOM() {
            readWhile(ch -> ch == '\uFEFF', true);
            return this;
        }

        public String readWord() {
            if (this.autoWS) skipWS();
            return readWhile(ch -> !Character.isWhitespace(ch), false);
        }

        public String readStringLiteral() {
            if (this.autoWS) skipWS();
            if (data[at] != '"') throw new Error("not a string literal!");
            at++;
            String read = readWhile(ch -> ch != '"', true);
            if (data[at] != '"') throw new Error("missing end of string literal!");
            at++;
            return read;
        }

        public String readWhile(Predicate<Character> fn, boolean emptyAllowed) {
            int start = at;
            while (at < data.length && fn.test(data[at]))
                at++;
            if (at == start && !emptyAllowed)
                throw new Error("no data!");
            return new String(data, start, at - start);
        }


        public String readWhile(BiPredicate<char[], Integer> fn, boolean emptyAllowed) {
            int start = at;
            while (at < data.length && fn.test(data, at))
                at++;
            if (at == start && !emptyAllowed)
                throw new Error("no data!");
            return new String(data, start, at - start);
        }

        public int readInt() {
            return Integer.parseInt(readWord());
        }

        private boolean isWordChar(char c) {
            return (c >= 'A' && c <= 'Z')
                    || (c >= 'a' && c <= 'z')
                    || (c >= '0' && c <= '9')
                    || (c == '_');
        }

        public void readCommand(StatRenderer renderer, Consumer<String> handleInclude) {
            String command = readWord();
            switch (command) {
                case "include" -> {
                    String path = readStringLiteral();
                    handleInclude.accept(path);
                }
                case "no_identifiers" -> {
                    //no idea what this is
                }
                case "no_description" -> {
                    String id = readWord();
                    renderer.listFor(id).add(NoDescription.INSTANCE);
                }
                case "description" -> {
                    int count = readInt();
                    List<String> ids = IntStream.range(0, count).mapToObj(_ -> readWord()).toList();
                    List<String> lines = readDescriptionValue();
                    for (String id : ids) {
                        List<StatRenderLine> l = renderer.listFor(id);
                        for (String line : lines) {
                            l.add(new WithDescription(line));
                        }
                    }
//                    renderer.listFor(id).add(NoDescription.INSTANCE);
                    while (hasMore() && peek(this::readWord).equals("lang")) {
                        if (!readWord().equals("lang")) throw new IllegalStateException();
                        readStringLiteral(); //lang
                        readDescriptionValue(); //ignored
                    }
                }
                default -> throw new RuntimeException("unexpected command: " + command);
            }
        }

        public String readLine() {
            return readWhile(ch -> ch != '\r' && ch != '\n', false);
        }

        public List<String> readDescriptionValue() {
            int count = readInt();
            return IntStream.range(0, count)
                    .mapToObj(_ -> skipWS().readLine())
                    .toList();
        }

        public boolean hasMore() {
            if (this.autoWS) skipWS();
            return at < data.length;
        }

        public char peekChar() {
            if (this.autoWS) skipWS();
            return data[at];
        }

        public char readChar() {
            if (this.autoWS) skipWS();
            return data[at++];
        }

        public void readMagic(String data) {
            for (int i = 0; i < data.length(); i++) {
                char read = readChar();
                if (read != data.charAt(i))
                    throw new Error("magic mismatch!");
            }
        }
    }
}
