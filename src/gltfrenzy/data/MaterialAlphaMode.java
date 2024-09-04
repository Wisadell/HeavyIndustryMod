package gltfrenzy.data;

import arc.util.serialization.Jval;

public enum MaterialAlphaMode {
    opaque, mask, blend;

    public static MaterialAlphaMode create(Jval json) {
        String name = json.asString();
        return switch(name.toLowerCase()) {
            case "opaque" -> MaterialAlphaMode.opaque;
            case "mask" -> MaterialAlphaMode.mask;
            case "blend" -> MaterialAlphaMode.blend;
            default -> throw new IllegalArgumentException("Invalid discriminator `" + name + "` for type `MaterialAlphaMode`.");
        } ;
    }
}
