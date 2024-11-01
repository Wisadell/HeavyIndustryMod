package heavyindustry.world.draw;

import arc.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.util.Eachable;
import mindustry.entities.units.BuildPlan;
import mindustry.gen.*;
import mindustry.world.*;
import mindustry.world.draw.*;

import static heavyindustry.util.HIUtils.*;

public class DrawAtlasFrames extends DrawFrames {
    public TextureRegion icon;
    public TextureRegion[] splitRegion;

    /** The pixel size of each atlas section, It must be the same as the source file. */
    public int atlasSize = 32;

    @Override
    public void draw(Building build) {
        Draw.rect(sine ? splitRegion[(int) Mathf.absin(build.totalProgress(), interval, splitRegion.length - 0.001f)] : splitRegion[(int)((build.totalProgress() / interval) % splitRegion.length)], build.x, build.y);
    }

    @Override
    public void drawPlan(Block block, BuildPlan plan, Eachable<BuildPlan> list) {
        Draw.rect(icon, plan.drawx(), plan.drawy());
    }

    @Override
    public TextureRegion[] icons(Block block) {
        return new TextureRegion[]{icon};
    }

    @Override
    public void load(Block block) {
        super.load(block);
        icon = Core.atlas.find(block.name + "-frame");
        splitRegion = split(block.name + "-frame-full", atlasSize, 0);
    }
}
