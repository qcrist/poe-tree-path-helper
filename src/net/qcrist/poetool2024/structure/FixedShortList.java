package net.qcrist.poetool2024.structure;

import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
public class FixedShortList {
    public final int max_length;
    public int length = 0;
    public short[] data;

    public void add(short entry) {
        if (data == null)
            data = new short[max_length];
        data[length++] = entry;
    }

    public short[] toArray() {
        return Arrays.copyOf(data, length);
    }

    public void clear() {
        this.length = 0;
    }
}
