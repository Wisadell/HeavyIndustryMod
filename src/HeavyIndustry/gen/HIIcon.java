package HeavyIndustry.gen;

import arc.scene.style.TextureRegionDrawable;

import static HeavyIndustry.HeavyIndustryMod.name;
import static arc.Core.*;
import static mindustry.gen.Icon.*;

public class HIIcon {
    public static TextureRegionDrawable kepler;
    public static void load() {
        kepler = atlas.getDrawable(name("kepler"));
        icons.put("kepler", kepler);
    }
}
