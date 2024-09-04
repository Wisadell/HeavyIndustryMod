package gltfrenzy.data;

import arc.util.serialization.Jval;

public enum AnimationInterpolation {
    linear, step, spline;

    public static AnimationInterpolation create(Jval json) {
        String name = json.asString();
        return switch(name.toLowerCase()) {
            case "linear" -> AnimationInterpolation.linear;
            case "step" -> AnimationInterpolation.step;
            case "cubicspline" -> AnimationInterpolation.spline;
            default -> throw new IllegalArgumentException("Invalid discriminator `" + name + "` for type `AnimationInterpolation`.");
        } ;
    }
}
