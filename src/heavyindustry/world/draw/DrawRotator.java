package heavyindustry.world.draw;

import arc.*;
import arc.graphics.g2d.*;
import arc.util.*;
import mindustry.entities.units.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.world.*;
import mindustry.world.draw.*;

public class DrawRotator extends DrawBlock {
    public String suffix = "-rotator";
    public TextureRegion rotator;

    public float x, y;
    public float rotateSpeed = 1.25f;
    public float primaryRotation = 0;
    public boolean usesSpinDraw = false;

    public DrawRotator(boolean usesSpinDraw) {
        this.usesSpinDraw = usesSpinDraw;
    }

    public DrawRotator(float rotateSpeed, boolean usesSpinDraw) {
        this.rotateSpeed = rotateSpeed;
        this.usesSpinDraw = usesSpinDraw;
    }

    public DrawRotator(float rotateSpeed, String suffix) {
        this.suffix = suffix;
        this.rotateSpeed = rotateSpeed;
    }

    public DrawRotator(float rotateSpeed, float primaryRotation, String suffix) {
        this.suffix = suffix;
        this.rotateSpeed = rotateSpeed;
        this.primaryRotation = primaryRotation;
    }

    public DrawRotator(float rotateSpeed) {
        this.rotateSpeed = rotateSpeed;
    }

    public DrawRotator(float rotateSpeed, float primaryRotation) {
        this.rotateSpeed = rotateSpeed;
        this.primaryRotation = primaryRotation;
    }

    public DrawRotator() {
    }

    @Override
    public void draw(Building build) {
        if (usesSpinDraw)
            Drawf.spinSprite(rotator, build.x + x, build.y + y, build.totalProgress() * rotateSpeed + primaryRotation);
        else Draw.rect(rotator, build.x + x, build.y + y, build.totalProgress() * rotateSpeed + primaryRotation);
    }

    @Override
    public void drawPlan(Block block, BuildPlan plan, Eachable<BuildPlan> list) {
        Draw.rect(rotator, plan.drawx() + x, plan.drawy() + y, primaryRotation);
    }

    @Override
    public TextureRegion[] icons(Block block) {
        return new TextureRegion[]{rotator};
    }

    @Override
    public void load(Block block) {
        rotator = Core.atlas.find(block.name + suffix);
    }
}
