package gltfrenzy.data;

import arc.util.Nullable;
import arc.util.serialization.Jval;

public final class Image {

    public @Nullable Jval extensions;

    public @Nullable Jval extras;

    public String name;

    public @Nullable String uri;

    public @Nullable ImageType mimeType;

    public int bufferView;

    private Image() {
    }

    public static Image create(Jval json) {
        Image out = new Image();
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
        mimeType: {
            Jval mimeType__data = json.get("mimeType");
            if(mimeType__data == null) {
                out.mimeType = null;
                break mimeType;
            }
            ImageType mimeType;
            mimeType = ImageType.create(mimeType__data);
            out.mimeType = mimeType;
        }
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
        return out;
    }
}
