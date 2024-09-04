package gltfrenzy.data;

import arc.graphics.Gl;
import arc.util.Nullable;
import arc.util.serialization.Jval;

public final class Sampler {

    public @Nullable Jval extensions;

    public @Nullable Jval extras;

    public String name;

    public int magFilter;

    public int minFilter;

    public int wrapS;

    public int wrapT;

    private Sampler() {}

    public static Sampler create(Jval json) {
        Sampler out = new Sampler();
        out.extensions = json.get("extensions");
        out.extras = json.get("extras");
        out.name = json.getString("name", "");
        magFilter: {
            Jval magFilter__data = json.get("magFilter");
            if(magFilter__data == null) {
                out.magFilter = -1;
                break magFilter;
            }
            int magFilter;
            magFilter = magFilter__data.asInt();
            out.magFilter = magFilter;
        }
        minFilter: {
            Jval minFilter__data = json.get("minFilter");
            if(minFilter__data == null) {
                out.minFilter = -1;
                break minFilter;
            }
            int minFilter;
            minFilter = minFilter__data.asInt();
            out.minFilter = minFilter;
        }
        wrapS: {
            Jval wrapS__data = json.get("wrapS");
            if(wrapS__data == null) {
                out.wrapS = Gl.repeat;
                break wrapS;
            }
            int wrapS;
            wrapS = wrapS__data.asInt();
            out.wrapS = wrapS;
        }
        wrapT: {
            Jval wrapT__data = json.get("wrapT");
            if(wrapT__data == null) {
                out.wrapT = Gl.repeat;
                break wrapT;
            }
            int wrapT;
            wrapT = wrapT__data.asInt();
            out.wrapT = wrapT;
        }
        return out;
    }
}
