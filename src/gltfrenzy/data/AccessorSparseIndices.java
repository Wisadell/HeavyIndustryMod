package gltfrenzy.data;

import arc.util.Nullable;
import arc.util.serialization.Jval;

public final class AccessorSparseIndices {

    public @Nullable Jval extensions;

    public @Nullable Jval extras;

    public int bufferView;

    public int byteOffset;

    public int componentType;

    private AccessorSparseIndices() {
    }

    public static AccessorSparseIndices create(Jval json) {
        AccessorSparseIndices out = new AccessorSparseIndices();
        out.extensions = json.get("extensions");
        out.extras = json.get("extras");
        bufferView: {
            Jval bufferView__data = json.get("bufferView");
            if(bufferView__data == null) {
                throw new IllegalArgumentException("Property `bufferView` is required for `AccessorSparseIndices`.");
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
        componentType: {
            Jval componentType__data = json.get("componentType");
            if(componentType__data == null) {
                throw new IllegalArgumentException("Property `componentType` is required for `AccessorSparseIndices`.");
            }
            int componentType;
            componentType = componentType__data.asInt();
            out.componentType = componentType;
        }
        return out;
    }
}
