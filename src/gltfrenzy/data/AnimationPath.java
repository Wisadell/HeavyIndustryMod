package gltfrenzy.data;

import arc.util.serialization.Jval;

public enum AnimationPath {
    translation, rotation, scale, weights;

    public static AnimationPath create(Jval json) {
        String name = json.asString();
        return switch(name.toLowerCase()) {
            case "translation" -> AnimationPath.translation;
            case "rotation" -> AnimationPath.rotation;
            case "scale" -> AnimationPath.scale;
            case "weights" -> AnimationPath.weights;
            default -> throw new IllegalArgumentException("Invalid discriminator `" + name + "` for type `AnimationPath`.");
        } ;
    }
}
