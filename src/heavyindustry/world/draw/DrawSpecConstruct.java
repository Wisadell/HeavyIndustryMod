package heavyindustry.world.draw;

import heavyindustry.graphics.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.world.*;
import mindustry.world.draw.*;

import static heavyindustry.util.Utils.*;

/**
 * Display multi-layer textures in sequence according to the progress of the building.
 * @author Wisadell
 */
public class DrawSpecConstruct extends DrawBlock {
    /** Color of Item Surface Construction. */
    public Color constructColor1 = Pal.accent;
    /** The color of the constructed lines. */
    public Color constructColor2 = Pal.accent;

    public int size = 32;

    public TextureRegion[] stageRegions;

    public DrawSpecConstruct() {}

    public DrawSpecConstruct(Color color) {
        constructColor1 = constructColor2 = color;
    }

    public DrawSpecConstruct(Color color1, Color color2) {
        constructColor1 = color1;
        constructColor2 = color2;
    }

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
        if (size < 1) size = 32;
        stageRegions = split(block.name + "-construct", size, 0);
    }
}
