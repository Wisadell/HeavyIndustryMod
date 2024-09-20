package heavyindustry.world.draw;

import heavyindustry.world.blocks.distribution.NodeBridge.*;
import arc.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.util.*;
import mindustry.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.world.*;
import mindustry.world.draw.*;

public class DrawNodeBridge extends DrawBlock {
    public TextureRegion bridgeRegion;
    public TextureRegion endRegion;

    @Override
    public void draw(Building build) {
        if(!(build instanceof NodeBridgeBuild)) return;
        drawR((NodeBridgeBuild) build);
    }

    public void drawR(NodeBridgeBuild build){
        Draw.z(Layer.power);
        Building other = Vars.world.build(build.link);
        if(other == null) return;
        float op = Core.settings.getInt("bridgeopacity") / 100f;
        if(Mathf.zero(op)) return;

        Draw.color(Color.white);
        if(build.block.hasPower) Draw.alpha(Math.max(build.power.status, 0.25f) * op);
        else Draw.alpha(op);

        Draw.rect(endRegion, build.x, build.y);
        Draw.rect(endRegion, other.x, other.y);

        Lines.stroke(8);

        Tmp.v1.set(build.x, build.y).sub(other.x, other.y).setLength(Vars.tilesize/2f).scl(-1);

        Lines.line(bridgeRegion, build.x, build.y, other.x, other.y, false);
        Draw.reset();
    }

    @Override
    public void load(Block block) {
        bridgeRegion = Core.atlas.find(block.name + "-bridge");
        endRegion = Core.atlas.find(block.name + "-end");
    }
}
