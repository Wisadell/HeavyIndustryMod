package gltfrenzy.data;

import arc.util.Nullable;
import arc.util.serialization.Jval;

public final class AccessorSparse {

    public @Nullable Jval extensions;

    public @Nullable Jval extras;

    public int count;

    public AccessorSparseIndices indices;

    public AccessorSparseValues values;

    private AccessorSparse() {}

    public static AccessorSparse create(Jval json) {
        AccessorSparse out = new AccessorSparse();
        out.extensions = json.get("extensions");
        out.extras = json.get("extras");
        count: {
            Jval count__data = json.get("count");
            if(count__data == null) {
                throw new IllegalArgumentException("Property `count` is required for `AccessorSparse`.");
            }
            int count;
            count = count__data.asInt();
            out.count = count;
        }
        indices: {
            Jval indices__data = json.get("indices");
            if(indices__data == null) {
                throw new IllegalArgumentException("Property `indices` is required for `AccessorSparse`.");
            }
            AccessorSparseIndices indices;
            indices = AccessorSparseIndices.create(indices__data);
            out.indices = indices;
        }
        values: {
            Jval values__data = json.get("values");
            if(values__data == null) {
                throw new IllegalArgumentException("Property `values` is required for `AccessorSparse`.");
            }
            AccessorSparseValues values;
            values = AccessorSparseValues.create(values__data);
            out.values = values;
        }
        return out;
    }
}
