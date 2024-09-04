package gltfrenzy.data;

import arc.util.Nullable;
import arc.util.serialization.Jval;

public final class Camera {

    public @Nullable Jval extensions;

    public @Nullable Jval extras;

    public String name;

    public @Nullable CameraPerspective perspective;

    public @Nullable CameraOrthographic orthographic;

    public @Nullable CameraProjection type;

    private Camera() {}

    public static Camera create(Jval json) {
        Camera out = new Camera();
        out.extensions = json.get("extensions");
        out.extras = json.get("extras");
        out.name = json.getString("name", "");
        perspective: {
            Jval perspective__data = json.get("perspective");
            if(perspective__data == null) {
                out.perspective = null;
                break perspective;
            }
            CameraPerspective perspective;
            perspective = CameraPerspective.create(perspective__data);
            out.perspective = perspective;
        }
        orthographic: {
            Jval orthographic__data = json.get("orthographic");
            if(orthographic__data == null) {
                out.orthographic = null;
                break orthographic;
            }
            CameraOrthographic orthographic;
            orthographic = CameraOrthographic.create(orthographic__data);
            out.orthographic = orthographic;
        }
        type: {
            Jval type__data = json.get("type");
            if(type__data == null) {
                out.type = null;
                break type;
            }
            CameraProjection type;
            type = CameraProjection.create(type__data);
            out.type = type;
        }
        return out;
    }
}
