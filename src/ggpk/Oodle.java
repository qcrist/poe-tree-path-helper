package ggpk;

import com.sun.jna.Native;
import com.sun.jna.Pointer;

import java.nio.Buffer;

public class Oodle {
    static {
//        Native.register("oo2core_9_win64");
        Native.register("libooz");
    }

//    public static native void OodleLZ_Decompress(Buffer in, int insz, Buffer out, long dst_size,
//                                                 int fuzz, int crc, int verbose,
//                                                 Pointer dst_base, long e, Pointer cb, Pointer cb_ctx, Pointer scratch, long scratch_size, int threadPhase);

    public static native int Ooz_Decompress(Buffer in, int insz, Buffer out, int dst_size);

//    //dst_size might be int
//    public static native int Ooz_Decompress(Buffer src_buf, int src_len, Buffer dst, long dst_size,
//                       int, int, int, Buffer, size_t, void*, void*, void*, size_t, int) {
//        return Kraken_Decompress(src_buf, src_len, dst, dst_size);

    //    public static native long OodleLZ_Decompress(Pointer in);

//    public static native void OodleCore_Plugin_Printf_Default(int param_1,undefined8 param_2,undefined8 param_3,uint *param_4,undefined param_5)
}
