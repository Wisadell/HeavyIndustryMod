package gltfrenzy.data;

import arc.util.Nullable;
import arc.util.serialization.Jval;

public final class Mesh {

    public @Nullable Jval extensions;

    public @Nullable Jval extras;

    public String name;

    public MeshPrimitive[] primitives;

    public float[] weights;

    private Mesh() {}

    public static Mesh create(Jval json) {
        Mesh out = new Mesh();
        out.extensions = json.get("extensions");
        out.extras = json.get("extras");
        out.name = json.getString("name", "");
        primitives: {
            Jval primitives__data = json.get("primitives");
            if(primitives__data == null) {
                throw new IllegalArgumentException("Property `primitives` is required for `Mesh`.");
            }
            MeshPrimitive[] primitives;
            primitives = primitives__data.asArray().map(primitives__item -> {
                MeshPrimitive primitives__out;
                primitives__out = MeshPrimitive.create(primitives__item);
                return primitives__out;
            } ).toArray(MeshPrimitive.class);
            out.primitives = primitives;
        }
        weights: {
            Jval weights__data = json.get("weights");
            if(weights__data == null) {
                out.weights = new float[0];
                break weights;
            }
            float[] weights;
            Jval.JsonArray weights__array = weights__data.asArray();
            weights = new float[weights__array.size];
            for(int weights__i = 0, weights__len = weights__array.size; weights__i < weights__len; weights__i++) {
                Jval weights__item = weights__array.get(weights__i);
                float weights__out;
                weights__out = weights__item.asFloat();
                weights[weights__i] = weights__out;
            }
            out.weights = weights;
        }
        return out;
    }
}
