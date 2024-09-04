package gltfrenzy.data;

import arc.util.Nullable;
import arc.util.serialization.Jval;

public final class Scene {

    public @Nullable Jval extensions;

    public @Nullable Jval extras;

    public String name;

    public int[] nodes;

    private Scene() {}

    public static Scene create(Jval json) {
        Scene out = new Scene();
        out.extensions = json.get("extensions");
        out.extras = json.get("extras");
        out.name = json.getString("name", "");
        nodes: {
            Jval nodes__data = json.get("nodes");
            if(nodes__data == null) {
                out.nodes = new int[0];
                break nodes;
            }
            int[] nodes;
            Jval.JsonArray nodes__array = nodes__data.asArray();
            nodes = new int[nodes__array.size];
            for(int nodes__i = 0, nodes__len = nodes__array.size; nodes__i < nodes__len; nodes__i++) {
                Jval nodes__item = nodes__array.get(nodes__i);
                int nodes__out;
                nodes__out = nodes__item.asInt();
                nodes[nodes__i] = nodes__out;
            }
            out.nodes = nodes;
        }
        return out;
    }
}
