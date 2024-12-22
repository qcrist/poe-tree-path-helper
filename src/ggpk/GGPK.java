package ggpk;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.builder.HashCodeExclude;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.BiConsumer;

import static ggpk.GGBundleIndex.parseBundleIndex;

public class GGPK {
    private static final int TAG_GGPK = 0x4b504747;
    private static final int TAG_FREE = 0x45455246;
    private static final int TAG_PDIR = 0x52494450;
    private static final int TAG_FILE = 0x454C4946;

    public final Path path;
    public final Map<String, File> files;
    public final Map<String, GGBundleIndex.BundleIndexFile> index;
    public final BundleCache bc = new BundleCache(this);

    public GGPK(Path path) throws IOException {
        this.path = path;
        System.out.printf("Loading: %s...\n", path);
        try (SeekableByteChannel channel = Files.newByteChannel(path)) {
            files = new LinkedHashMap<>();

            GGPKEntry result = parseGGPKNode(0, channel);

            ArrayList<String> list = new ArrayList<>();
            unwrapGGPKNode(result, list, files);
        }

        System.out.println("Parsing index...");
        index = parseBundleIndex(this);
        System.out.println("Done");
    }

    public sealed abstract class GGPKEntry permits Root, File, Dir {
        public abstract String getName();

        public GGPK getGGPK() {
            return GGPK.this;
        }
    }

    @RequiredArgsConstructor
    public final class Root extends GGPKEntry {
        public final List<GGPKEntry> entries;
        @Getter
        public final String name = "ROOT";
    }

    @RequiredArgsConstructor
    @ToString
    @EqualsAndHashCode(callSuper = false)
    public final class File extends GGPKEntry {
        @Getter
        public final String name;
        public final long offset;
        public final long length;
    }

    @RequiredArgsConstructor
    public final class Dir extends GGPKEntry {
        @Getter
        public final String name;
        public final Map<String, GGPKEntry> entries;
    }

    private GGPKEntry parseGGPKNode(long offset, SeekableByteChannel channel) throws IOException {
        int pre_read = 8;
        int length, tag;
        {
            ByteBuffer buff = ByteBuffer.allocateDirect(pre_read).order(ByteOrder.LITTLE_ENDIAN);
//        synchronized ($LOCK) {
            channel.position(offset);
            channel.read(buff);
//        }
            length = buff.getInt(0);
            tag = buff.getInt(4);
        }
        if (tag == TAG_FILE) {
            ByteBuffer b = ByteBuffer.allocateDirect(4).order(ByteOrder.LITTLE_ENDIAN);
            channel.read(b);
            b.flip();
            int nameLength = b.getInt();
            channel.position(channel.position() + 32);
            ByteBuffer nameBuf = ByteBuffer.allocateDirect(nameLength * 2).order(ByteOrder.LITTLE_ENDIAN);
            channel.read(nameBuf);
            nameBuf.flip();
            char[] name = new char[nameLength];
            for (int i = 0; i < nameLength; i++) {
                name[i] = (char) (nameBuf.getShort() & 0xFFFF);
            }
            long fileOffset = channel.position();
            String nameString = new String(name, 0, nameLength - 1);
            return new File(nameString, fileOffset, offset + length - fileOffset);
        } else {
            ByteBuffer data;
            data = ByteBuffer.allocate(length - pre_read).order(ByteOrder.LITTLE_ENDIAN);
            channel.read(data);
            data.flip();

            switch (tag) {
                case TAG_GGPK -> {
                    int version = data.getInt();
                    if (version != 3)
                        throw new Error("unexpected GGPK version: " + version);
                    List<GGPKEntry> entries = new ArrayList<>();
                    while (data.hasRemaining()) {
                        GGPKEntry result = parseGGPKNode(data.getLong(), channel);
                        if (result != null)
                            entries.add(result);
                    }
                    return new Root(entries);
                }
                case TAG_FREE -> {
                    return null;
                }
                case TAG_PDIR -> {
                    int nameLength = data.getInt();
                    int totalEntries = data.getInt();

                    data.position(data.position() + 32); //sha256

                    char[] name = new char[nameLength];
                    for (int i = 0; i < nameLength; i++) {
                        name[i] = (char) (data.getShort() & 0xFFFF);
                    }

                    Map<String, GGPKEntry> entries = new LinkedHashMap<>();

                    for (int i = 0; i < totalEntries; i++) {
                        int entryNameHash = data.getInt();
                        long entryOffset = data.getLong();

                        GGPKEntry e = parseGGPKNode(entryOffset, channel);
                        if (e == null) continue;
                        if (entries.put(e.getName(), e) != null)
                            throw new Error("dup!");
                    }
                    return new Dir(new String(name, 0, nameLength - 1), entries);
                }
            }
        }
        throw new IllegalStateException("unexpected tag: " + tag);
    }

    private static void unwrapGGPKNode(GGPKEntry ggpkEntry, List<String> strings, Map<String, File> ggpkFiles) {
        if (ggpkEntry instanceof Root r) {
            for (GGPKEntry e : r.entries) {
                unwrapGGPKNode(e, strings, ggpkFiles);
            }
        } else if (ggpkEntry instanceof Dir d) {
            boolean goodName = !d.name.isEmpty();
            if (goodName)
                strings.add(d.name);
            for (GGPKEntry e : new TreeMap<>(d.entries).values()) {
                unwrapGGPKNode(e, strings, ggpkFiles);
            }
            if (goodName)
                strings.removeLast();
        } else if (ggpkEntry instanceof File f) {
            strings.add(f.name);
            if (ggpkFiles.put(String.join("/", strings), f) != null)
                throw new Error("dup!");
            strings.removeLast();
        }
    }

    public static void main(String[] args) throws IOException {
        Path ggpk = Path.of("G:\\PathOfExile\\Content.ggpk");

        GGPK gg = new GGPK(ggpk);

        System.out.println("gg = " + gg);
    }
}
