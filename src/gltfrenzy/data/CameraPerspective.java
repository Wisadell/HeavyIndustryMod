package gltfrenzy.data;

import arc.util.Nullable;
import arc.util.serialization.Jval;

public final class CameraPerspective {
    public @Nullable Jval extensions;

    public @Nullable Jval extras;

    public float aspectRatio;

    public float yfov;

    public float zfar;

    public float znear;

    private CameraPerspective() {
    }

    public static CameraPerspective create(Jval json) {
        CameraPerspective out = new CameraPerspective();
        out.extensions = json.get("extensions");
        out.extras = json.get("extras");
        aspectRatio: {
            Jval aspectRatio__data = json.get("aspectRatio");
            if(aspectRatio__data == null) {
                out.aspectRatio = Float.NaN;
                break aspectRatio;
            }
            float aspectRatio;
            aspectRatio = aspectRatio__data.asFloat();
            out.aspectRatio = aspectRatio;
        }
        yfov: {
            Jval yfov__data = json.get("yfov");
            if(yfov__data == null) {
                throw new IllegalArgumentException("Property `yfov` is required for `CameraPerspective`.");
            }
            float yfov;
            yfov = yfov__data.asFloat();
            out.yfov = yfov;
        }
        zfar: {
            Jval zfar__data = json.get("zfar");
            if(zfar__data == null) {
                out.zfar = Float.NaN;
                break zfar;
            }
            float zfar;
            zfar = zfar__data.asFloat();
            out.zfar = zfar;
        }
        znear: {
            Jval znear__data = json.get("znear");
            if(znear__data == null) {
                throw new IllegalArgumentException("Property `znear` is required for `CameraPerspective`.");
            }
            float znear;
            znear = znear__data.asFloat();
            out.znear = znear;
        }
        return out;
    }
}
