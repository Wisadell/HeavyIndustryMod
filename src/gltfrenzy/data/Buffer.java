package gltfrenzy.data;

import arc.util.Nullable;
import arc.util.serialization.Jval;

public final class Buffer {

    public @Nullable Jval extensions;

    public @Nullable Jval extras;

    public String name;

    public @Nullable String uri;

    public int byteLength;

    private Buffer() {}

    public static Buffer create(Jval json) {
        Buffer out = new Buffer();
        out.extensions = json.get("extensions");
        out.extras = json.get("extras");
        out.name = json.getString("name", "");
        uri: {
            Jval uri__data = json.get("uri");
            if(uri__data == null) {
                out.uri = null;
                break uri;
            }
            String uri;
            uri = uri__data.asString();
            out.uri = uri;
        }
        byteLength: {
            Jval byteLength__data = json.get("byteLength");
            if(byteLength__data == null) {
                throw new IllegalArgumentException("Property `byteLength` is required for `Buffer`.");
            }
            int byteLength;
            byteLength = byteLength__data.asInt();
            out.byteLength = byteLength;
        }
        return out;
    }
}
