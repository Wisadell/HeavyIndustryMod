package gltfrenzy.data;

import arc.util.Nullable;
import arc.util.serialization.Jval;

public final class Accessor {

    public @Nullable Jval extensions;

    public @Nullable Jval extras;

    public String name;

    public int bufferView;

    public int byteOffset;

    public int componentType;

    public boolean normalized;

    public int count;

    public AccessorType type;

    public float[] max;

    public float[] min;

    public @Nullable AccessorSparse sparse;

    private Accessor() {
    }

    public static Accessor create(Jval json) {
        Accessor out = new Accessor();
        out.extensions = json.get("extensions");
        out.extras = json.get("extras");
        out.name = json.getString("name", "");
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
        componentType: {
            Jval componentType__data = json.get("componentType");
            if(componentType__data == null) {
                throw new IllegalArgumentException("Property `componentType` is required for `Accessor`.");
            }
            int componentType;
            componentType = componentType__data.asInt();
            out.componentType = componentType;
        }
        normalized: {
            Jval normalized__data = json.get("normalized");
            if(normalized__data == null) {
                out.normalized = false;
                break normalized;
            }
            boolean normalized;
            normalized = normalized__data.asBool();
            out.normalized = normalized;
        }
        count: {
            Jval count__data = json.get("count");
            if(count__data == null) {
                throw new IllegalArgumentException("Property `count` is required for `Accessor`.");
            }
            int count;
            count = count__data.asInt();
            out.count = count;
        }
        type: {
            Jval type__data = json.get("type");
            if(type__data == null) {
                throw new IllegalArgumentException("Property `type` is required for `Accessor`.");
            }
            AccessorType type;
            type = AccessorType.create(type__data);
            out.type = type;
        }
        max: {
            Jval max__data = json.get("max");
            if(max__data == null) {
                out.max = new float[0];
                break max;
            }
            float[] max;
            Jval.JsonArray max__array = max__data.asArray();
            max = new float[max__array.size];
            for(int max__i = 0, max__len = max__array.size; max__i < max__len; max__i++) {
                Jval max__item = max__array.get(max__i);
                float max__out;
                max__out = max__item.asFloat();
                max[max__i] = max__out;
            }
            out.max = max;
        }
        min: {
            Jval min__data = json.get("min");
            if(min__data == null) {
                out.min = new float[0];
                break min;
            }
            float[] min;
            Jval.JsonArray min__array = min__data.asArray();
            min = new float[min__array.size];
            for(int min__i = 0, min__len = min__array.size; min__i < min__len; min__i++) {
                Jval min__item = min__array.get(min__i);
                float min__out;
                min__out = min__item.asFloat();
                min[min__i] = min__out;
            }
            out.min = min;
        }
        sparse: {
            Jval sparse__data = json.get("sparse");
            if(sparse__data == null) {
                out.sparse = null;
                break sparse;
            }
            AccessorSparse sparse;
            sparse = AccessorSparse.create(sparse__data);
            out.sparse = sparse;
        }
        return out;
    }
}
