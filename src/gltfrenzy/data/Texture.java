package gltfrenzy.data;

import arc.util.Nullable;
import arc.util.serialization.Jval;

public final class Texture {

    public @Nullable Jval extensions;

    public @Nullable Jval extras;

    public String name;

    public int sampler;

    public int source;

    private Texture() {
    }

    public static Texture create(Jval json) {
        Texture out = new Texture();
        out.extensions = json.get("extensions");
        out.extras = json.get("extras");
        out.name = json.getString("name", "");
        sampler: {
            Jval sampler__data = json.get("sampler");
            if(sampler__data == null) {
                out.sampler = -1;
                break sampler;
            }
            int sampler;
            sampler = sampler__data.asInt();
            out.sampler = sampler;
        }
        source: {
            Jval source__data = json.get("source");
            if(source__data == null) {
                out.source = -1;
                break source;
            }
            int source;
            source = source__data.asInt();
            out.source = source;
        }
        return out;
    }
}
