package heavyindustry.gen;

import static arc.Core.atlas;
import static mindustry.gen.Icon.icons;

import arc.scene.style.TextureRegionDrawable;

public class HIIcon {
    public static TextureRegionDrawable kepler;
    public static void load() {
        kepler = atlas.getDrawable("heavy-industry-kepler");
        icons.put("kepler", kepler);
    }
}
