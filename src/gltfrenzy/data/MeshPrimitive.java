package gltfrenzy.data;

import arc.graphics.Gl;
import arc.util.Nullable;
import arc.util.serialization.Jval;

public final class MeshPrimitive {

    public @Nullable Jval extensions;

    public @Nullable Jval extras;

    public Jval.JsonMap attributes;

    public int indices;

    public int material;

    public int mode;

    public Jval.JsonMap[] targets;

    private MeshPrimitive() {}

    public static MeshPrimitive create(Jval json) {
        MeshPrimitive out = new MeshPrimitive();
        out.extensions = json.get("extensions");
        out.extras = json.get("extras");
        attributes: {
            Jval attributes__data = json.get("attributes");
            if(attributes__data == null) {
                throw new IllegalArgumentException("Property `attributes` is required for `MeshPrimitive`.");
            }
            Jval.JsonMap attributes;
            attributes = attributes__data.asObject();
            out.attributes = attributes;
        }
        indices: {
            Jval indices__data = json.get("indices");
            if(indices__data == null) {
                out.indices = -1;
                break indices;
            }
            int indices;
            indices = indices__data.asInt();
            out.indices = indices;
        }
        material: {
            Jval material__data = json.get("material");
            if(material__data == null) {
                out.material = -1;
                break material;
            }
            int material;
            material = material__data.asInt();
            out.material = material;
        }
        mode: {
            Jval mode__data = json.get("mode");
            if(mode__data == null) {
                out.mode = Gl.triangles;
                break mode;
            }
            int mode;
            mode = mode__data.asInt();
            out.mode = mode;
        }
        targets: {
            Jval targets__data = json.get("targets");
            if(targets__data == null) {
                out.targets = new Jval.JsonMap[0];
                break targets;
            }
            Jval.JsonMap[] targets;
            targets = targets__data.asArray().map(targets__item -> {
                Jval.JsonMap targets__out;
                targets__out = targets__item.asObject();
                return targets__out;
            } ).toArray(Jval.JsonMap.class);
            out.targets = targets;
        }
        return out;
    }
}
