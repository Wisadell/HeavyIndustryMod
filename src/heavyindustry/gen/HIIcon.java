package heavyindustry.gen;

import arc.scene.style.*;

import static arc.Core.*;
import static mindustry.gen.Icon.*;
import static heavyindustry.core.HeavyIndustryMod.*;

public final class HIIcon {
    public static TextureRegionDrawable
            keplerIcon,aboutIcon,artistIcon,configureIcon,contributeIcon,databaseIcon,debuggingIcon,defaultShowIcon,gasesIcon,
            holdIcon,matrixIcon,nuclearIcon,programIcon,publicInfoIcon,reactionIcon,showInfosIcon,showRangeIcon,soundsIcon,
            startIcon,telegramIcon,timeIcon,translateIcon,unShowInfosIcon,
            //small
            resetIconSmall,timeIconSmall;
    public static void load() {
        keplerIcon = getModDrawable("kepler-icon");
        icons.put("kepler-icon", keplerIcon);
        aboutIcon = getModDrawable("about-icon");
        icons.put("about-icon", aboutIcon);
        artistIcon = getModDrawable("artist-icon");
        icons.put("artist-icon", artistIcon);
        configureIcon = getModDrawable("configure-icon");
        icons.put("configure-icon", configureIcon);
        contributeIcon = getModDrawable("contribute-icon");
        icons.put("contribute-icon", contributeIcon);
        databaseIcon = getModDrawable("database-icon");
        icons.put("database-icon", databaseIcon);
        debuggingIcon = getModDrawable("debugging-icon");
        icons.put("debugging-icon", debuggingIcon);
        defaultShowIcon = getModDrawable("default-show-icon");
        icons.put("default-show-icon", defaultShowIcon);
        gasesIcon = getModDrawable("gases-icon");
        icons.put("gases-icon", gasesIcon);
        holdIcon = getModDrawable("hold-icon");
        icons.put("hold-icon", holdIcon);
        matrixIcon = getModDrawable("matrix-icon");
        icons.put("matrix-icon", matrixIcon);
        nuclearIcon = getModDrawable("nuclear-icon");
        icons.put("nuclear-icon", nuclearIcon);
        programIcon = getModDrawable("program-icon");
        icons.put("program-icon", programIcon);
        publicInfoIcon = getModDrawable("public-info-icon");
        icons.put("public-info-icon", publicInfoIcon);
        reactionIcon = getModDrawable("reaction-icon");
        icons.put("reaction-icon", reactionIcon);
        showInfosIcon = getModDrawable("show-infos-icon");
        icons.put("show-infos-icon", showInfosIcon);
        showRangeIcon = getModDrawable("show-range-icon");
        icons.put("show-range-icon", showRangeIcon);
        soundsIcon = getModDrawable("sounds-icon");
        icons.put("sounds-icon", soundsIcon);
        startIcon = getModDrawable("start-icon");
        icons.put("start-icon", startIcon);
        telegramIcon = getModDrawable("telegram-icon");
        icons.put("telegram-icon", telegramIcon);
        timeIcon = getModDrawable("time-icon");
        icons.put("time-icon", timeIcon);
        translateIcon = getModDrawable("translate-icon");
        icons.put("translate-icon", translateIcon);
        unShowInfosIcon = getModDrawable("un-show-infos-icon");
        icons.put("un-show-infos-icon", unShowInfosIcon);
        //small
        resetIconSmall = getModDrawable("reset-icon-small");
        icons.put("reset-icon-small", resetIconSmall);
        timeIconSmall = getModDrawable("time-icon-small");
        icons.put("time-icon-small", timeIconSmall);
    }

    private static <T extends Drawable> T getModDrawable(String name){
        return atlas.getDrawable(modName + "-" + name);
    }
}
