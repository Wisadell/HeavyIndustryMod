package gltfrenzy.data;

import arc.util.serialization.Jval;

public enum ImageType {
    jpeg, png;

    public static ImageType create(Jval json) {
        String name = json.asString();
        return switch(name.toLowerCase()) {
            case "jpeg" -> ImageType.jpeg;
            case "png" -> ImageType.png;
            default -> throw new IllegalArgumentException("Invalid discriminator `" + name + "` for type `ImageType`.");
        } ;
    }
}
