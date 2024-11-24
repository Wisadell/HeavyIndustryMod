package heavyindustry.world.blocks.production;

import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.util.*;
import mindustry.graphics.*;
import mindustry.type.*;
import heavyindustry.content.*;

import static arc.Core.*;

public class LaserBeamDrill extends DrillF {
    public TextureRegion laser;
    public TextureRegion laserEnd;

    public float shooterOffset = 12f;
    public float shooterExtendOffset = 1.8f;
    public float shooterMoveRange = 5.2f;
    public float shootY = 1.55f;

    public float moveScale = 60f;
    public float moveScaleRand = 20f;
    public float laserScl = 0.2f;

    public Color laserColor = Color.valueOf("f58349");

    public float laserAlpha = 0.75f;
    public float laserAlphaSine = 0.2f;

    public int particles = 25;
    public float particleLife = 40f, particleRad = 9.75f, particleLen = 4f;

    public LaserBeamDrill(String name) {
        super(name);
        mineSpeed = 7.5f;
        mineCount = 5;

        powerConsBase = 330f;
        itemCapacity = 80;

        maxBoost = 1f;

        updateEffect = HIFx.laserBeam;
        updateEffectChance = 0.01f;
    }

    public void load() {
        super.load();
        laser = atlas.find("minelaser");
        laserEnd = atlas.find("minelaser-end");

    }

    @Override
    public float getMineSpeedHardnessMul(Item item) {
        if (item == null) return 0f;
        return switch (item.hardness) {
            case 0 -> 2f;
            case 1, 2 -> 1.6f;
            case 3, 4 -> 1.2f;
            default -> 1f;
        };
    }

    public class LaserBeamDrillBuild extends DrillBuildF {
        public Rand rand = new Rand();

        @Override
        public void draw() {
            Draw.rect(baseRegion, x, y);

            if (warmup > 0f){
                drawMining();
            }

            Draw.z(Layer.blockOver - 4f);
            Draw.rect(topRegion, x, y);
            drawTeamTop();
        }

        @Override
        public void drawMining() {
            float timeDrilled = Time.time / 2.5f;
            float
                    moveX = Mathf.sin(timeDrilled, moveScale + Mathf.randomSeed(id, -moveScaleRand, moveScaleRand), shooterMoveRange) + x,
                    moveY = Mathf.sin(timeDrilled + Mathf.randomSeed(id >> 1, moveScale), moveScale + Mathf.randomSeed(id >> 2, -moveScaleRand, moveScaleRand), shooterMoveRange) + y;

            float stroke = laserScl * warmup;
            Draw.mixcol(laserColor, Mathf.absin(4f, 0.6f));
            Draw.alpha(laserAlpha + Mathf.absin(8f, laserAlphaSine));
            Draw.blend(Blending.additive);
            Drawf.laser(laser, laserEnd, x + (-shooterOffset + warmup * shooterExtendOffset + shootY), moveY, x - (-shooterOffset + warmup * shooterExtendOffset + shootY), moveY, stroke);
            Drawf.laser(laser, laserEnd, moveX, y + (-shooterOffset + warmup * shooterExtendOffset + shootY), moveX, y - (-shooterOffset + warmup * shooterExtendOffset + shootY), stroke);

            Draw.color(dominantItem.color);

            float sine = 1f + Mathf.sin(6f, 0.1f);

            Lines.stroke(stroke / laserScl / 2f);
            Lines.circle(moveX, moveY, stroke * 12f * sine);
            Fill.circle(moveX, moveY, stroke * 8f * sine);

            rand.setSeed(id);
            float base = (Time.time / particleLife);
            for (int i = 0; i < particles; i++) {
                float fin = (rand.random(1f) + base) % 1f, fout = 1f - fin;
                float angle = rand.random(360f);
                float len = Mathf.randomSeed(rand.nextLong(), particleRad * 0.8f, particleRad * 1.1f) * Interp.pow2Out.apply(fin);
                Lines.lineAngle(moveX + Angles.trnsx(angle, len), moveY + Angles.trnsy(angle, len), angle, particleLen * fout * stroke / laserScl);
            }

            Draw.blend();
            Draw.reset();
        }
    }
}
