package gltfrenzy.data;

import arc.graphics.Color;
import arc.util.Nullable;
import arc.util.serialization.Jval;

public final class Material {

    public @Nullable Jval extensions;

    public @Nullable Jval extras;

    public String name;

    public @Nullable MaterialMetallicRoughness pbrMetallicRoughness;

    public @Nullable MaterialNormalTexture normalTexture;

    public @Nullable MaterialOcclusionTexture occlusionTexture;

    public @Nullable TextureInfo emissiveTexture;

    public Color emissiveFactor;

    public MaterialAlphaMode alphaMode;

    public float alphaCutoff;

    public boolean doubleSided;

    private Material() {}

    public static Material create(Jval json) {
        Material out = new Material();
        out.extensions = json.get("extensions");
        out.extras = json.get("extras");
        out.name = json.getString("name", "");
        pbrMetallicRoughness: {
            Jval pbrMetallicRoughness__data = json.get("pbrMetallicRoughness");
            if(pbrMetallicRoughness__data == null) {
                out.pbrMetallicRoughness = null;
                break pbrMetallicRoughness;
            }
            MaterialMetallicRoughness pbrMetallicRoughness;
            pbrMetallicRoughness = MaterialMetallicRoughness.create(pbrMetallicRoughness__data);
            out.pbrMetallicRoughness = pbrMetallicRoughness;
        }
        normalTexture: {
            Jval normalTexture__data = json.get("normalTexture");
            if(normalTexture__data == null) {
                out.normalTexture = null;
                break normalTexture;
            }
            MaterialNormalTexture normalTexture;
            normalTexture = MaterialNormalTexture.create(normalTexture__data);
            out.normalTexture = normalTexture;
        }
        occlusionTexture: {
            Jval occlusionTexture__data = json.get("occlusionTexture");
            if(occlusionTexture__data == null) {
                out.occlusionTexture = null;
                break occlusionTexture;
            }
            MaterialOcclusionTexture occlusionTexture;
            occlusionTexture = MaterialOcclusionTexture.create(occlusionTexture__data);
            out.occlusionTexture = occlusionTexture;
        }
        emissiveTexture: {
            Jval emissiveTexture__data = json.get("emissiveTexture");
            if(emissiveTexture__data == null) {
                out.emissiveTexture = null;
                break emissiveTexture;
            }
            TextureInfo emissiveTexture;
            emissiveTexture = TextureInfo.create(emissiveTexture__data);
            out.emissiveTexture = emissiveTexture;
        }
        emissiveFactor: {
            Jval emissiveFactor__data = json.get("emissiveFactor");
            if(emissiveFactor__data == null) {
                out.emissiveFactor = Color.clear.cpy();
                break emissiveFactor;
            }
            Color emissiveFactor;
            Jval.JsonArray emissiveFactor__array = emissiveFactor__data.asArray();
            emissiveFactor = new Color(emissiveFactor__array.get(0).asFloat(), emissiveFactor__array.get(1).asFloat(), emissiveFactor__array.get(2).asFloat(), emissiveFactor__array.get(3).asFloat());
            out.emissiveFactor = emissiveFactor;
        }
        alphaMode: {
            Jval alphaMode__data = json.get("alphaMode");
            if(alphaMode__data == null) {
                out.alphaMode = MaterialAlphaMode.opaque;
                break alphaMode;
            }
            MaterialAlphaMode alphaMode;
            alphaMode = MaterialAlphaMode.create(alphaMode__data);
            out.alphaMode = alphaMode;
        }
        alphaCutoff: {
            Jval alphaCutoff__data = json.get("alphaCutoff");
            if(alphaCutoff__data == null) {
                out.alphaCutoff = 0.5f;
                break alphaCutoff;
            }
            float alphaCutoff;
            alphaCutoff = alphaCutoff__data.asFloat();
            out.alphaCutoff = alphaCutoff;
        }
        doubleSided: {
            Jval doubleSided__data = json.get("doubleSided");
            if(doubleSided__data == null) {
                out.doubleSided = false;
                break doubleSided;
            }
            boolean doubleSided;
            doubleSided = doubleSided__data.asBool();
            out.doubleSided = doubleSided;
        }
        return out;
    }
}
