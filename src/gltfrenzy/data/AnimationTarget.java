package gltfrenzy.data;

import arc.util.Nullable;
import arc.util.serialization.Jval;

public final class AnimationTarget {

    public @Nullable Jval extensions;

    public @Nullable Jval extras;

    public int node;

    public AnimationPath path;

    private AnimationTarget() {}

    public static AnimationTarget create(Jval json) {
        AnimationTarget out = new AnimationTarget();
        out.extensions = json.get("extensions");
        out.extras = json.get("extras");
        node: {
            Jval node__data = json.get("node");
            if(node__data == null) {
                out.node = -1;
                break node;
            }
            int node;
            node = node__data.asInt();
            out.node = node;
        }
        path: {
            Jval path__data = json.get("path");
            if(path__data == null) {
                throw new IllegalArgumentException("Property `path` is required for `AnimationTarget`.");
            }
            AnimationPath path;
            path = AnimationPath.create(path__data);
            out.path = path;
        }
        return out;
    }
}
