package gltfrenzy.data;

import arc.util.Nullable;
import arc.util.serialization.Jval;

public final class MaterialOcclusionTexture {

    public @Nullable Jval extensions;

    public @Nullable Jval extras;

    public int index;

    public int texCoord;

    public float strength;

    private MaterialOcclusionTexture() {}

    public static MaterialOcclusionTexture create(Jval json) {
        MaterialOcclusionTexture out = new MaterialOcclusionTexture();
        out.extensions = json.get("extensions");
        out.extras = json.get("extras");
        index: {
            Jval index__data = json.get("index");
            if(index__data == null) {
                throw new IllegalArgumentException("Property `index` is required for `MaterialOcclusionTexture`.");
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
        strength: {
            Jval strength__data = json.get("strength");
            if(strength__data == null) {
                out.strength = 1f;
                break strength;
            }
            float strength;
            strength = strength__data.asFloat();
            out.strength = strength;
        }
        return out;
    }
}
