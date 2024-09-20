package heavyindustry.world.draw;

import arc.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import mindustry.gen.*;
import mindustry.world.draw.*;

public class DrawPowerLight extends DrawBlock {
    public Color lightColor;

    public DrawPowerLight(Color lightColor){
        this.lightColor = lightColor;
    }

    @Override
    public void draw(Building build) {
        Draw.color(lightColor);
        Draw.alpha(build.power.status);
        Draw.rect(Core.atlas.find(build.block.name + "-light"), build.x, build.y);
        Draw.alpha(1);
        Draw.color();
    }
}
