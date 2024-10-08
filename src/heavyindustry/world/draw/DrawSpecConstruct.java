package heavyindustry.world.draw;

import heavyindustry.graphics.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.world.*;
import mindustry.world.draw.*;

import static heavyindustry.util.HIUtils.*;

/**
 * Display multi-layer textures in sequence according to the progress of the building.
 * @author Wisadell
 */
public class DrawSpecConstruct extends DrawBlock {
    /** Color of Item Surface Construction. */
    public Color constructColor1 = Pal.accent;
    /** The color of the constructed lines. */
    public Color constructColor2 = Pal.accent;

    public TextureRegion[] stageRegions;

    @Override
    public void draw(Building build) {
        int stage = (int) (build.progress() * stageRegions.length);
        float stageProgress = (build.progress() * stageRegions.length) % 1f;

        for (int i = 0; i < stage; i++) {
            Draw.rect(stageRegions[i], build.x, build.y);
        }

        Draw.draw(Layer.blockOver, () -> Drawn.construct(build, stageRegions[stage], constructColor1, constructColor2, 0f, stageProgress, build.warmup() * build.efficiency(), build.totalProgress() * 1.6f));
    }

    @Override
    public void load(Block block) {
        super.load(block);
        stageRegions = split(block.name + "-construct", 32, 0);
    }
}
