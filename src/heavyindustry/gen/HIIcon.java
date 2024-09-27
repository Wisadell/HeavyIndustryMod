package heavyindustry.gen;

import static arc.Core.atlas;
import static mindustry.gen.Icon.icons;

import arc.scene.style.TextureRegionDrawable;

public class HIIcon {
    public static TextureRegionDrawable kepler,timeIcon;
    public static void load() {
        kepler = atlas.getDrawable("heavy-industry-kepler");
        icons.put("kepler", kepler);
        timeIcon = atlas.getDrawable("heavy-industry-time-icon");
        icons.put("time-icon", timeIcon);
    }
}
