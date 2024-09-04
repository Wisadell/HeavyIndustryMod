package gltfrenzy.data;

import arc.util.Nullable;
import arc.util.serialization.Jval;

public final class TextureInfo {

    public @Nullable Jval extensions;

    public @Nullable Jval extras;

    public int index;

    public int texCoord;

    private TextureInfo() {
    }

    public static TextureInfo create(Jval json) {
        TextureInfo out = new TextureInfo();
        out.extensions = json.get("extensions");
        out.extras = json.get("extras");
        index: {
            Jval index__data = json.get("index");
            if(index__data == null) {
                throw new IllegalArgumentException("Property `index` is required for `TextureInfo`.");
            }
            int index;
            index = index__data.asInt();
            out.index = index;
        }
        texCoord: {
            Jval texCoord__data = json.get("texCoord");
            if(texCoord__data == null) {
                out.texCoord = 0;
                break texCoord;
            }
            int texCoord;
            texCoord = texCoord__data.asInt();
            out.texCoord = texCoord;
        }
        return out;
    }
}
