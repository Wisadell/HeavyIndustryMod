package gltfrenzy.data;

import arc.util.Nullable;
import arc.util.serialization.Jval;

public final class MaterialNormalTexture {

    public @Nullable Jval extensions;

    public @Nullable Jval extras;

    public int index;

    public int texCoord;

    public float scale;

    private MaterialNormalTexture() {}

    public static MaterialNormalTexture create(Jval json) {
        MaterialNormalTexture out = new MaterialNormalTexture();
        out.extensions = json.get("extensions");
        out.extras = json.get("extras");
        index: {
            Jval index__data = json.get("index");
            if(index__data == null) {
                throw new IllegalArgumentException("Property `index` is required for `MaterialNormalTexture`.");
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
        scale: {
            Jval scale__data = json.get("scale");
            if(scale__data == null) {
                out.scale = 1f;
                break scale;
            }
            float scale;
            scale = scale__data.asFloat();
            out.scale = scale;
        }
        return out;
    }
}
