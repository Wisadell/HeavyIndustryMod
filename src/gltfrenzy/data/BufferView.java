package gltfrenzy.data;

import arc.util.Nullable;
import arc.util.serialization.Jval;

public final class BufferView {

    public @Nullable Jval extensions;

    public @Nullable Jval extras;

    public String name;

    public int buffer;

    public int byteOffset;

    public int byteLength;

    public int byteStride;

    public int target;

    private BufferView() {}

    public static BufferView create(Jval json) {
        BufferView out = new BufferView();
        out.extensions = json.get("extensions");
        out.extras = json.get("extras");
        out.name = json.getString("name", "");
        buffer: {
            Jval buffer__data = json.get("buffer");
            if(buffer__data == null) {
                throw new IllegalArgumentException("Property `buffer` is required for `BufferView`.");
            }
            int buffer;
            buffer = buffer__data.asInt();
            out.buffer = buffer;
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
        byteLength: {
            Jval byteLength__data = json.get("byteLength");
            if(byteLength__data == null) {
                throw new IllegalArgumentException("Property `byteLength` is required for `BufferView`.");
            }
            int byteLength;
            byteLength = byteLength__data.asInt();
            out.byteLength = byteLength;
        }
        byteStride: {
            Jval byteStride__data = json.get("byteStride");
            if(byteStride__data == null) {
                out.byteStride = -1;
                break byteStride;
            }
            int byteStride;
            byteStride = byteStride__data.asInt();
            out.byteStride = byteStride;
        }
        target: {
            Jval target__data = json.get("target");
            if(target__data == null) {
                out.target = -1;
                break target;
            }
            int target;
            target = target__data.asInt();
            out.target = target;
        }
        return out;
    }
}
