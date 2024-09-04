package gltfrenzy.data;

import arc.util.serialization.Jval;

public enum CameraProjection {
    perspective, orthographic;

    public static CameraProjection create(Jval json) {
        String name = json.asString();
        return switch(name.toLowerCase()) {
            case "perspective" -> CameraProjection.perspective;
            case "orthographic" -> CameraProjection.orthographic;
            default -> throw new IllegalArgumentException("Invalid discriminator `" + name + "` for type `CameraProjection`.");
        } ;
    }
}
