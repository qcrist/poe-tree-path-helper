package ggpk;

import java.nio.ByteBuffer;

public class Util {
    public static byte[] byteBufferToBytes(ByteBuffer buff) {
        int len = buff.remaining();
        byte[] bytes = new byte[len];
        buff.get(bytes);
        return bytes;
    }

    public static class DebuggerBreakpoint extends RuntimeException {
        public DebuggerBreakpoint() {
            throw new Error("breakpoint here!");
        }
    }
}
