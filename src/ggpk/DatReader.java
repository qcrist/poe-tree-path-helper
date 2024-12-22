package ggpk;

import ggpk.generated.DatTypesV1;
import ggpk.generated.DatTypesV2;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.beans.Visibility;
import java.io.IOException;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Stream;


@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class DatReader<T extends DatReader.DatType> {
    private static final Map<String, BiFunction<ByteBuffer, ByteBuffer, DatType>> tableHandlers = new HashMap<>();

    private final Class<T> cls;
    private final BiFunction<ByteBuffer, ByteBuffer, DatType> handler;

    @SuppressWarnings("unchecked")
    public T readRow(ByteBuffer row, ByteBuffer ref) {
        return cls.cast(handler.apply(row, ref));
    }

    private interface FieldHandler {
        Object handle(ByteBuffer row, ByteBuffer ref, int offset);
    }

    private interface FieldHandlerWrapped {
        void handle(Object obj, ByteBuffer row, ByteBuffer ref) throws ReflectiveOperationException;
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public @interface Offset {
        int value();
    }

    public interface DatType {
    }

    @SuppressWarnings("ClassCanBeRecord")
    @RequiredArgsConstructor
    public static class ForeignRow {
        public final long value;
        public final long unknown;

        public int asInt() {
            return Math.toIntExact(value);
        }

        public <T extends DatType> T lookup(List<T> stats) {
            if (value == 0xFEFEFEFEFEFEFEFEL)
                return null;
            return stats.get(asInt());
        }
    }

    private static int fieldSizeMap(Class<?> cls) {
        return switch (cls.getSimpleName().toLowerCase()) {
            case "string", "long" -> 8;
            case "int", "integer", "float" -> 4;
            case "boolean" -> 1;
            case "short" -> 2;
            case "list", "foreignrow" -> 16;
            default -> throw new Error("need to handle: " + cls.getSimpleName());
        };
    }

    private static FieldHandler handlerForClass(Class<?> cls, Type type) {
        return switch (cls.getSimpleName().toLowerCase()) {
            case "string" -> DatReader::readString;
            case "long" -> DatReader::readLong;
            case "int", "integer" -> DatReader::readInt;
            case "float" -> DatReader::readFloat;
            case "boolean" -> DatReader::readBool;
            case "short" -> DatReader::readShort;
            case "foreignrow" -> DatReader::readFR;
            case "list" -> {
                if (type instanceof ParameterizedType p) {
                    Class<?> clz = (Class<?>) p.getActualTypeArguments()[0];
                    FieldHandler h = handlerForClass(clz, null);
                    int size = fieldSizeMap(clz);
                    yield (a, b, c) -> readList(a, b, c, h, size);
                }
                throw new Error("unexpected type: " + type);
//                FieldHandler el =
            }
            default -> throw new Error("need to handle: " + cls.getSimpleName());
        };
    }

    static {
        System.out.println("Loading DatTypes...");
        List<Class<? extends DatType>> types = Stream.concat(
                Stream.of(DatTypesV1.TYPES), Stream.of(DatTypesV2.TYPES)
        ).flatMap(x -> x.stream()).toList();
        for (Class<? extends DatType> type : types) {
            List<FieldHandlerWrapped> handlers = new ArrayList<>();
            for (Field f : type.getFields()) {
                Offset anno = f.getAnnotation(Offset.class);
                if (anno == null) throw new Error("!");
                int off = anno.value();
                FieldHandler fh = handlerForClass(f.getType(), f.getGenericType());
                handlers.add(((obj, row, ref) -> {
                    f.set(obj, fh.handle(row, ref, off));
                }));
            }
            FieldHandlerWrapped[] arr = handlers.toArray(FieldHandlerWrapped[]::new);
            tableHandlers.put(type.getSimpleName().toLowerCase(), new BiFunction<ByteBuffer, ByteBuffer, DatType>() {
                @Override
                @SneakyThrows
                public DatType apply(ByteBuffer row, ByteBuffer ref) {
                    DatType o = type.getConstructor().newInstance();
                    for (FieldHandlerWrapped wr : arr) {
                        wr.handle(o, row, ref);
                    }
                    return o;
                }
            });
        }
    }

    private static String readString(ByteBuffer row, ByteBuffer ref, int offset) {
        int ref_offset = Math.toIntExact(row.getLong(offset));
        StringBuilder sb = new StringBuilder();
        while (true) {
            char read = ref.getChar(ref_offset);
            ref_offset += 2;
            if (read == '\0')
                break;
            sb.append(read);
        }
        return sb.toString();
    }

    private static Long readLong(ByteBuffer row, ByteBuffer ref, int offset) {
        return row.getLong(offset);
    }

    private static ForeignRow readFR(ByteBuffer row, ByteBuffer ref, int offset) {
        return new ForeignRow(
                row.getLong(offset),
                row.getLong(offset + 8)
        );
    }

    private static Boolean readBool(ByteBuffer row, ByteBuffer ref, int offset) {
        return row.get(offset) != 0;
    }

    private static Integer readInt(ByteBuffer row, ByteBuffer ref, int offset) {
        return row.getInt(offset);
    }

    private static Short readShort(ByteBuffer row, ByteBuffer ref, int offset) {
        return row.getShort(offset);
    }

    private static Float readFloat(ByteBuffer row, ByteBuffer ref, int offset) {
        return row.getFloat(offset);
    }

    private static List<?> readList(ByteBuffer row, ByteBuffer ref, int offset, FieldHandler elementHandler, int step) {
        int ref_length = Math.toIntExact(row.getLong(offset));
        int ref_offset = Math.toIntExact(row.getLong(offset + 8));
        List<Object> list = new ArrayList<>();
        for (int i = 0; i < ref_length; i++) {
            list.add(elementHandler.handle(ref, ref, ref_offset + i * step));
        }
        return list;
    }

    public static <T extends DatType> DatReader<T> forClass(Class<T> cls) {
        BiFunction<ByteBuffer, ByteBuffer, DatType> handler = tableHandlers.get(cls.getSimpleName().toLowerCase());
        return new DatReader<T>(cls, handler);
    }

    public static <T extends DatType> List<T> dumpTable(GGPK gg, Class<T> cls) throws IOException {
        DatReader<T> reader = forClass(cls);
        byte[] bytes = gg.bc.read(cls);
        ByteBuffer bytes_buff = ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN);

        int row_count = bytes_buff.getInt(0);
        System.out.println("row_count = " + row_count);
        int bb_split = 0;
        byte bb = (byte) 0xBB;
        byte[] bb_8 = {bb, bb, bb, bb, bb, bb, bb, bb};
        for (; bb_split < bytes.length; bb_split++) {
            if (bytes[bb_split] != bb) continue;
            if (Arrays.equals(bb_8, 0, bb_8.length, bytes, bb_split, bb_split + bb_8.length))
                break;
        }
        System.out.println("bb_split = " + bb_split);
        int data_len = bb_split - 4;
        if (data_len % row_count != 0)
            throw new Error("unexpected data length");
        int row_length = data_len / row_count;
        ByteBuffer ref = bytes_buff.slice(bb_split, bytes_buff.capacity() - bb_split).order(ByteOrder.LITTLE_ENDIAN);
        List<T> data = new ArrayList<>();
        for (int row = 0; row < row_count; row++) {
            int offset = 4 + row * row_length;
            ByteBuffer row_buff = bytes_buff.slice(offset, row_length).order(ByteOrder.LITTLE_ENDIAN);
            try {
                data.add(reader.readRow(row_buff, ref));
            } catch (Exception ex) {
                System.out.printf("Failed to generate data for type: %s\n", cls.getSimpleName());
                System.out.println("row_length = " + row_length);
                byte[] d = new byte[row_count * row_length];
                bytes_buff.get(4, d);
                Files.write(Path.of("debug.bin"), d);
            }
        }
        return data;
    }

    public static void main(String[] args) throws IOException {
//        DatReader reader = new DatReader(Path.of("schema.min.json"));
    }
}
