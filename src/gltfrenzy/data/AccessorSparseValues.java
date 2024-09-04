package gltfrenzy.data;

import arc.util.Nullable;
import arc.util.serialization.Jval;

public final class AccessorSparseValues {

    public @Nullable Jval extensions;

    public @Nullable Jval extras;

    public int bufferView;

    public int byteOffset;

    private AccessorSparseValues() {
    }

    public static AccessorSparseValues create(Jval json) {
        AccessorSparseValues out = new AccessorSparseValues();
        out.extensions = json.get("extensions");
        out.extras = json.get("extras");
        bufferView: {
            Jval bufferView__data = json.get("bufferView");
            if(bufferView__data == null) {
                out.bufferView = -1;
                break bufferView;
            }
            int bufferView;
            bufferView = bufferView__data.asInt();
            out.bufferView = bufferView;
        }
        byteOffset: {
            Jval byteOffset__data = json.get("byteOffset");
            if(byteOffset__data == null) {
                out.byteOffset = 0;
                break byteOffset;
            }
            int byteOffset;
            byteOffset = byteOffset__data.asInt();
            out.byteOffset = byteOffset;
        }
        return out;
    }
}
