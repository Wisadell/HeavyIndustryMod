package heavyindustry.world.blocks.production;

import arc.audio.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.util.*;
import heavyindustry.content.*;
import mindustry.entities.*;
import mindustry.gen.*;
import mindustry.graphics.*;

import static arc.Core.*;

/**
 * Draw the animation of the original impact drill bit.
 *
 * @author E-Nightingale
 */
public class ImplosionDrill extends DrillF {
    public float shake = 2f;

    public TextureRegion topInvertRegion, glowRegion, arrowRegion, arrowBlurRegion;

    public float arrowSpacing = 4f, arrowOffset = 0f;
    public int arrows = 3;
    public Color arrowColor = Color.valueOf("feb380"), baseArrowColor = Color.valueOf("6e7080");
    public Color glowColor = arrowColor.cpy();

    public Sound drillSound = Sounds.drillImpact;
    public float drillSoundVolume = 0.6f, drillSoundPitchRand = 0.1f;

    public ImplosionDrill(String name) {
        super(name);
        drillEffectRnd = 0f;
        drillEffect = HIFx.implosion;
        ambientSoundVolume = 0.18f;
        ambientSound = Sounds.drillCharge;
    }

    @Override
    public void load() {
        super.load();
        topInvertRegion = atlas.find(name + "-top-invert");
        glowRegion = atlas.find(name + "-glow");
        arrowRegion = atlas.find(name + "-arrow");
        arrowBlurRegion = atlas.find(name + "-arrow-blur");
    }

    public class ImplosionDrillBuild extends DrillBuildF {
        //used so the lights don't fade out immediately.
        public float smoothProgress = 0f;
        public float invertTime = 0f;

        @Override
        protected void updateProgress() {
            smoothProgress = Mathf.lerpDelta(smoothProgress, progress / (mineInterval() - 20f), 0.1f);
            super.updateProgress();
        }

        @Override
        protected void updateOutput() {
            if (progress > mineInterval()) {
                int outCount = (int) (progress / mineInterval()) * mineCount;
                for (int i = 0; i < outCount; i++) {
                    if (outputItem() != null) {
                        if (coreSend && core() != null && core().acceptItem(this, outputItem())) {
                            core().handleItem(this, outputItem());
                        } else {
                            offload(outputItem());
                        }
                    }
                }
                invertTime = 1f;
                progress %= mineInterval();

                if (wasVisible) {
                    Effect.shake(shake, shake, this);
                    drillSound.at(x, y, 1f + Mathf.range(drillSoundPitchRand), drillSoundVolume);
                    drillEffect.at(x + Mathf.range(drillEffectRnd), y + Mathf.range(drillEffectRnd), dominantItem.color);
                }
            }
        }

        @Override
        public void draw() {
            Draw.rect(baseRegion, x, y);
            if (warmup > 0f) {
                drawMining();
            }

            Draw.z(Layer.block);

            Draw.rect(topRegion, x, y);
            if (invertTime > 0 && topInvertRegion.found()) {
                Draw.alpha(Interp.pow3Out.apply(invertTime));
                Draw.rect(topInvertRegion, x, y);
                Draw.color();
            }
            if (outputItem() != null && drawMineItem) {
                Draw.color(dominantItem.color);
                Draw.rect(oreRegion, x, y);
                Draw.color();
            }

            drawTeamTop();

            float fract = smoothProgress;
            Draw.color(arrowColor);
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < arrows; j++) {
                    float arrowFract = (arrows - 1 - j);
                    float a = Mathf.clamp(fract * arrows - arrowFract);
                    Tmp.v1.trns(i * 90 + 45, j * arrowSpacing + arrowOffset);

                    Draw.z(Layer.blockOver - 4f);
                    Draw.color(baseArrowColor, arrowColor, a);
                    Draw.rect(arrowRegion, x + Tmp.v1.x, y + Tmp.v1.y, i * 90);

                    Draw.color(arrowColor);

                    if (arrowBlurRegion.found()) {
                        Draw.z(Layer.blockAdditive);
                        Draw.blend(Blending.additive);
                        Draw.alpha(Mathf.pow(a, 10f));
                        Draw.rect(arrowBlurRegion, x + Tmp.v1.x, y + Tmp.v1.y, i * 90);
                        Draw.blend();
                    }
                }
            }
            Draw.color();

            if (glowRegion.found()) {
                Drawf.additive(glowRegion, Tmp.c2.set(glowColor).a(Mathf.pow(fract, 3f) * glowColor.a), x, y);
            }
        }
    }
}
