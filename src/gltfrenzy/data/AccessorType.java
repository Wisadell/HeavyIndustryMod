package gltfrenzy.data;

import arc.util.serialization.Jval;

public enum AccessorType {
    scalar, vec2, vec3, vec4, mat2, mat3, mat4;

    public static AccessorType create(Jval json) {
        String name = json.asString();
        return switch(name.toLowerCase()) {
            case "scalar" -> AccessorType.scalar;
            case "vec2" -> AccessorType.vec2;
            case "vec3" -> AccessorType.vec3;
            case "vec4" -> AccessorType.vec4;
            case "mat2" -> AccessorType.mat2;
            case "mat3" -> AccessorType.mat3;
            case "mat4" -> AccessorType.mat4;
            default -> throw new IllegalArgumentException("Invalid discriminator `" + name + "` for type `AccessorType`.");
        } ;
    }
}
