package net.qcrist.poetool2024.structure;

import java.util.Arrays;
import java.util.Iterator;

public class IntSetList implements Iterable<Integer> {
    public final boolean[] added;
    public final int[] data;
    public int length = 0;

    public IntSetList(int max_length) {
        added = new boolean[max_length];
        data = new int[max_length];
    }

    public boolean add(int v) {
        if (!added[v]) {
            added[v] = true;
            data[length++] = v;
            return true;
        }
        return false;
    }

    public int[] toArray() {
        return Arrays.copyOf(data, length);
    }

    public void clear() {
        this.length = 0;
        Arrays.fill(this.added, false);
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<>() {
            private int nextIndex = 0;

            @Override
            public boolean hasNext() {
                return nextIndex < length;
            }

            @Override
            public Integer next() {
                return data[nextIndex++];
            }
        };
    }
}
