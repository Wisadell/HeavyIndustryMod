package gltfrenzy.data;

import arc.util.Nullable;
import arc.util.serialization.Jval;

public final class Asset {

    public @Nullable Jval extensions;

    public @Nullable Jval extras;

    public @Nullable String copyright;

    public @Nullable String generator;

    public String version;

    public @Nullable String minVersion;

    private Asset() {}

    public static Asset create(Jval json) {
        Asset out = new Asset();
        out.extensions = json.get("extensions");
        out.extras = json.get("extras");
        copyright: {
            Jval copyright__data = json.get("copyright");
            if(copyright__data == null) {
                out.copyright = null;
                break copyright;
            }
            String copyright;
            copyright = copyright__data.asString();
            out.copyright = copyright;
        }
        generator: {
            Jval generator__data = json.get("generator");
            if(generator__data == null) {
                out.generator = null;
                break generator;
            }
            String generator;
            generator = generator__data.asString();
            out.generator = generator;
        }
        version: {
            Jval version__data = json.get("version");
            if(version__data == null) {
                throw new IllegalArgumentException("Property `version` is required for `Asset`.");
            }
            String version;
            version = version__data.asString();
            out.version = version;
        }
        minVersion: {
            Jval minVersion__data = json.get("minVersion");
            if(minVersion__data == null) {
                out.minVersion = null;
                break minVersion;
            }
            String minVersion;
            minVersion = minVersion__data.asString();
            out.minVersion = minVersion;
        }
        return out;
    }
}
