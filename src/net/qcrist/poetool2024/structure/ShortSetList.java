package net.qcrist.poetool2024.structure;

import java.util.Arrays;
import java.util.Iterator;

public class ShortSetList implements Iterable<Short> {
    public final boolean[] added;
    public final short [] data;
    public int length = 0;

    public ShortSetList(int max_length) {
        added = new boolean[max_length];
        data = new short[max_length];
    }

    public boolean add(short v) {
        if (!added[v]) {
            added[v] = true;
            data[length++] = v;
            return true;
        }
        return false;
    }

    public short[] toArray() {
        return Arrays.copyOf(data, length);
    }

    public void clear() {
        this.length = 0;
        Arrays.fill(this.added, false);
    }

    @Override
    public Iterator<Short> iterator() {
        return new Iterator<>() {
            private int nextIndex = 0;

            @Override
            public boolean hasNext() {
                return nextIndex < length;
            }

            @Override
            public Short next() {
                return data[nextIndex++];
            }
        };
    }
}
