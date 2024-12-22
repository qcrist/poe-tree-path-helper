package ggpk;

import com.sun.jna.Pointer;
import lombok.experimental.StandardException;
import org.apache.commons.lang3.tuple.Pair;

import java.io.Closeable;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class GGBundle implements Closeable {
    private final DataView view;
    private final int[] block_lengths;
    private final long[] block_offsets;
    private final int block_count;
    public final int uncompressed_size;

    public static final int BLOCK_GRANULARITY = 256 * 1024;

    public GGBundle(DataView view) throws IOException {
        this.view = view;

        uncompressed_size = view.readInt32();
        int total_payload_size = view.readInt32();
        int head_payload_size = view.readInt32();
        if (head_payload_size + total_payload_size + 12 != view.length()) {
            throw new Error("length mismatch!");
        }
        view.readInt32(); //first_file_encode
        view.readInt32(); //unk10;
        view.readInt64(); //uncompressed_size2;
        view.readInt64(); //total_payload_size2;
        block_count = view.readInt32(); //block_count;
        int uncompressed_block_granularity = view.readInt32(); //uncompressed_block_granularity;
        if (uncompressed_block_granularity != BLOCK_GRANULARITY)
            throw new Error("uncompressed block granularity mismatch!");

        for (int i = 0; i < 4; i++)
            view.readInt32(); //unk28[4];

        block_lengths = new int[block_count];
        for (int i = 0; i < block_count; i++)
            block_lengths[i] = view.readInt32();

        long block_offset = view.position();
        if (block_offset != 12 + head_payload_size) {
            throw new Error("header mismatch!");
        }

        block_offsets = new long[block_count];
        block_offsets[0] = block_offset;
        for (int i = 1; i < block_count; i++)
            block_offsets[i] = block_offsets[i - 1] + block_lengths[i - 1];

        if (block_offsets[block_count - 1] + block_lengths[block_count - 1] != view.length()) {
            throw new Error("offset calculation mismatch!");
        }

        int sumBlocks = Arrays.stream(block_lengths).sum();
        if (sumBlocks != total_payload_size) {
            throw new Error("total_payload_size mismatch!");
        }
    }

    @StandardException
    public static class DecompressionFailedException extends RuntimeException {
    }

    public ByteBuffer readBlock(int idx) throws IOException {
        ByteBuffer buff = ByteBuffer.allocateDirect(block_lengths[idx]).order(ByteOrder.LITTLE_ENDIAN);
        view.setPosition(block_offsets[idx]);
        view.read(buff);

        buff.flip();
//        byte[] b = new byte[block_lengths[idx]];
//        buff.get(b);
//        Files.write(Path.of("test.bin"), b);


        ByteBuffer out = ByteBuffer.allocateDirect(BLOCK_GRANULARITY + 64);

        int uncompressed_block_length = idx < block_count - 1 ? BLOCK_GRANULARITY : uncompressed_size % BLOCK_GRANULARITY;

        int ret = Oodle.Ooz_Decompress(buff, block_lengths[idx], out, uncompressed_block_length);
        if (ret == -1)
            throw new DecompressionFailedException();
        return out.slice(0, uncompressed_block_length);
    }

    public ByteBuffer readFully() throws IOException {
        ByteBuffer buff = ByteBuffer.allocateDirect(uncompressed_size);
        for (int i = 0; i < block_count; i++) {
            buff.put(readBlock(i));
        }
        if (buff.remaining() != 0)
            throw new Error("full read failed");
        buff.flip();
        return buff;
    }

    public byte[] readAllBytes() throws IOException {
        byte[] data = new byte[uncompressed_size];
        ByteBuffer buff = ByteBuffer.wrap(data);
        for (int i = 0; i < block_count; i++) {
            buff.put(readBlock(i));
        }
        if (buff.remaining() != 0)
            throw new Error("full read failed");
        return data;
    }

    public static ByteBuffer readFully(DataView dv) throws IOException {
        try (GGBundle b = new GGBundle(dv)) {
            return b.readFully();
        }
    }

    @Override
    public void close() throws IOException {
        view.close();
    }

    public static void main(String[] args) throws IOException {

    }
}
