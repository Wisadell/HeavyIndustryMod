package HeavyIndustry.gen;

import arc.scene.style.TextureRegionDrawable;

import static HeavyIndustry.HeavyIndustryMod.name;
import static arc.Core.*;
import static mindustry.gen.Icon.*;

public class HIIcon {
    public static TextureRegionDrawable punkHazard;
    public static void load() {
        punkHazard = atlas.getDrawable(name("punk-hazard"));
        icons.put("punk-hazard", punkHazard);
    }
}
