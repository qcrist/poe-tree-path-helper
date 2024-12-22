package net.qcrist.poetool2024.structure;

import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
public class FixedIntList {
    public final int max_length;
    public int length = 0;
    public int[] data;

    public void add(int entry) {
        if (data == null)
            data = new int[max_length];
        data[length++] = entry;
    }

    public int last() {
        return data[length - 1];
    }

    public int[] toArray() {
        return Arrays.copyOf(data, length);
    }

    public void clear() {
        this.length = 0;
    }
}
