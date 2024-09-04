package gltfrenzy.data;

import arc.graphics.Color;
import arc.util.Nullable;
import arc.util.serialization.Jval;

public final class MaterialMetallicRoughness {

    public @Nullable Jval extensions;

    public @Nullable Jval extras;

    public Color baseColorFactor;

    public @Nullable TextureInfo baseColorTexture;

    public float metallicFactor;

    public float roughnessFactor;

    public @Nullable TextureInfo metallicRoughnessTexture;

    private MaterialMetallicRoughness() {}

    public static MaterialMetallicRoughness create(Jval json) {
        MaterialMetallicRoughness out = new MaterialMetallicRoughness();
        out.extensions = json.get("extensions");
        out.extras = json.get("extras");
        baseColorFactor: {
            Jval baseColorFactor__data = json.get("baseColorFactor");
            if(baseColorFactor__data == null) {
                out.baseColorFactor = Color.white.cpy();
                break baseColorFactor;
            }
            Color baseColorFactor;
            Jval.JsonArray baseColorFactor__array = baseColorFactor__data.asArray();
            baseColorFactor = new Color(baseColorFactor__array.get(0).asFloat(), baseColorFactor__array.get(1).asFloat(), baseColorFactor__array.get(2).asFloat(), baseColorFactor__array.get(3).asFloat());
            out.baseColorFactor = baseColorFactor;
        }
        baseColorTexture: {
            Jval baseColorTexture__data = json.get("baseColorTexture");
            if(baseColorTexture__data == null) {
                out.baseColorTexture = null;
                break baseColorTexture;
            }
            TextureInfo baseColorTexture;
            baseColorTexture = TextureInfo.create(baseColorTexture__data);
            out.baseColorTexture = baseColorTexture;
        }
        metallicFactor: {
            Jval metallicFactor__data = json.get("metallicFactor");
            if(metallicFactor__data == null) {
                out.metallicFactor = 1f;
                break metallicFactor;
            }
            float metallicFactor;
            metallicFactor = metallicFactor__data.asFloat();
            out.metallicFactor = metallicFactor;
        }
        roughnessFactor: {
            Jval roughnessFactor__data = json.get("roughnessFactor");
            if(roughnessFactor__data == null) {
                out.roughnessFactor = 1f;
                break roughnessFactor;
            }
            float roughnessFactor;
            roughnessFactor = roughnessFactor__data.asFloat();
            out.roughnessFactor = roughnessFactor;
        }
        metallicRoughnessTexture: {
            Jval metallicRoughnessTexture__data = json.get("metallicRoughnessTexture");
            if(metallicRoughnessTexture__data == null) {
                out.metallicRoughnessTexture = null;
                break metallicRoughnessTexture;
            }
            TextureInfo metallicRoughnessTexture;
            metallicRoughnessTexture = TextureInfo.create(metallicRoughnessTexture__data);
            out.metallicRoughnessTexture = metallicRoughnessTexture;
        }
        return out;
    }
}
