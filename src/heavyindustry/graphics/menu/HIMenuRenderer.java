package heavyindustry.graphics.menu;

import arc.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.util.*;
import mindustry.graphics.*;

import static mindustry.Vars.*;

public class HIMenuRenderer extends MenuRenderer {
    private final int viewWidth = !mobile ? 100 : 60;
    private final MenuSlide[] menus = {
            MenuSlides.stone,
            MenuSlides.grass
    };

    public float slideDuration = 60 * 40, transitionTime = 120, scrollSpeed = 1;

    private float time;
    private int width = !mobile ? 100 : 60, height = !mobile ? 50 : 40;
    private int index = 0;

    public HIMenuRenderer() {
        width += (int) Math.ceil((slideDuration * scrollSpeed) / 60) + 5;
        unityGenerate();
    }

    // causes method signature conflicts if i just named it "generate()"
    public void unityGenerate() {
        // shuffle the menus
        for (int i = menus.length - 1; i >= 0; i--) {
            int ii = (int) Mathf.randomSeed(Time.nanos(), i);
            MenuSlide temp = menus[i];
            menus[i] = menus[ii];
            menus[ii] = temp;
        }

        Time.mark();
        for (MenuSlide menu : menus) {
            menu.generateWorld(width, height);
        }
        Log.info("Total " + Time.elapsed());
    }

    @Override
    public void render() {
        time += Time.delta;

        float in = Mathf.curve(time, 0, transitionTime / 2);
        float out = Mathf.curve(time, slideDuration - transitionTime / 2, slideDuration);
        float dark = in - out;

        menus[index].render(time, slideDuration, viewWidth, height);

        Draw.color(0, 0, 0, 1 - dark);
        Fill.crect(0, 0, Core.graphics.getWidth(), Core.graphics.getHeight());

        if (time > slideDuration) {
            if (index + 1 < menus.length) {
                index++;
            } else {
                index = 0;
            }
            time = 0;
        }
    }
}
