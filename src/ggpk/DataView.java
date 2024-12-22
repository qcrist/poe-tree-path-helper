package ggpk;

import java.io.Closeable;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;

public interface DataView extends Closeable {

    static DataView of(Path path) throws IOException {
        return of(path, 0, Files.size(path));
    }

    static DataView of(Path path, long offset, long length) throws IOException {
        return new DataView() {
            private final SeekableByteChannel channel = Files.newByteChannel(path);

            {
                channel.position(offset);
            }

            @Override
            public long length() {
                return length;
            }

            @Override
            public long position() throws IOException {
                return channel.position() - offset;
            }

            @Override
            public void setPosition(long position) throws IOException {
                channel.position(position + offset);
            }

            @Override
            public void read(ByteBuffer buff) throws IOException {
                channel.read(buff);
            }

            @Override
            public void close() throws IOException {
                channel.close();
            }
        };
    }

    static DataView of(GGPK.File file) throws IOException {
        return of(file.getGGPK().path, file.offset, file.length);
    }

//    static DataView of(DataView view, long offset, long length) throws IOException {
//        if (offset + length > view.length()) {
//            throw new Error("bounds");
//        }
//        return new DataView() {
//            @Override
//            public long length() {
//                return length;
//            }
//
//            @Override
//            public long position() throws IOException {
//                return view.position() - offset;
//            }
//
//            @Override
//            public void setPosition(long position) throws IOException {
//                view.setPosition(position + offset);
//            }
//
//            @Override
//            public void read(ByteBuffer buff) throws IOException {
//                view.read(buff);
//            }
//
//            @Override
//            public void close() throws IOException {
//                view.close();
//            }
//        };
//    }

    static DataView of(ByteBuffer buff) throws IOException {
        ByteBuffer copy = buff.duplicate();
        return new DataView() {
            @Override
            public long length() {
                return copy.capacity();
            }

            @Override
            public long position() {
                return copy.position();
            }

            @Override
            public void setPosition(long position) {
                copy.position(Math.toIntExact(position));
            }

            @Override
            public void read(ByteBuffer buff) {
                while (buff.hasRemaining()) {
                    buff.put(copy.get());
                }
//                buff.put(copy.slice(buff.position(), buff.remaining()));
            }

            @Override
            public void close() {
            }
        };
    }

    long length();

    long position() throws IOException;

    void setPosition(long position) throws IOException;

//    int readInt32() throws IOException;

//    long readInt64() throws IOException;


    default int readInt32() throws IOException {
        ByteBuffer data = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN);
        read(data);
        data.flip();
        return data.getInt();
    }

    default long readInt64() throws IOException {
        ByteBuffer data = ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN);
        read(data);
        data.flip();
        return data.getLong();
    }

    void read(ByteBuffer buff) throws IOException;


}
