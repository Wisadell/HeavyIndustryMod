package gltfrenzy.data;

import arc.util.Nullable;
import arc.util.serialization.Jval;

public final class CameraOrthographic {

    public @Nullable Jval extensions;

    public @Nullable Jval extras;

    public float xmag;

    public float ymag;

    public float zfar;

    public float znear;

    private CameraOrthographic() {
    }

    public static CameraOrthographic create(Jval json) {
        CameraOrthographic out = new CameraOrthographic();
        out.extensions = json.get("extensions");
        out.extras = json.get("extras");
        xmag: {
            Jval xmag__data = json.get("xmag");
            if(xmag__data == null) {
                throw new IllegalArgumentException("Property `xmag` is required for `CameraOrthographic`.");
            }
            float xmag;
            xmag = xmag__data.asFloat();
            out.xmag = xmag;
        }
        ymag: {
            Jval ymag__data = json.get("ymag");
            if(ymag__data == null) {
                throw new IllegalArgumentException("Property `ymag` is required for `CameraOrthographic`.");
            }
            float ymag;
            ymag = ymag__data.asFloat();
            out.ymag = ymag;
        }
        zfar: {
            Jval zfar__data = json.get("zfar");
            if(zfar__data == null) {
                throw new IllegalArgumentException("Property `zfar` is required for `CameraOrthographic`.");
            }
            float zfar;
            zfar = zfar__data.asFloat();
            out.zfar = zfar;
        }
        znear: {
            Jval znear__data = json.get("znear");
            if(znear__data == null) {
                throw new IllegalArgumentException("Property `znear` is required for `CameraOrthographic`.");
            }
            float znear;
            znear = znear__data.asFloat();
            out.znear = znear;
        }
        return out;
    }
}
