package heavyindustry.world.blocks.production

import heavyindustry.content.HIFx
import arc.Core
import arc.audio.Sound
import arc.graphics.Blending
import arc.graphics.Color
import arc.graphics.g2d.Draw
import arc.graphics.g2d.TextureRegion
import arc.math.Interp
import arc.math.Mathf
import arc.util.Tmp
import mindustry.entities.Effect
import mindustry.gen.Sounds
import mindustry.graphics.Drawf
import mindustry.graphics.Layer

/**
 * Draw the animation of the original impact drill bit.
 * @author Wisadell
 */
open class ImplosionDrill(name: String) : AdaptDrill(name) {
    @JvmField var shake = 2f

    lateinit var topInvertRegion: TextureRegion
    lateinit var glowRegion: TextureRegion
    lateinit var arrowRegion: TextureRegion
    lateinit var arrowBlurRegion: TextureRegion

    @JvmField var arrowSpacing = 4f
    @JvmField var arrowOffset = 0f
    @JvmField var arrows = 3
    @JvmField var arrowColor: Color = Color.valueOf("feb380")
    @JvmField var baseArrowColor: Color = Color.valueOf("6e7080")
    @JvmField var glowColor: Color = arrowColor.cpy()

    @JvmField var drillSound: Sound = Sounds.drillImpact
    @JvmField var drillSoundVolume = 0.6f
    @JvmField var drillSoundPitchRand = 0.1f

    init {
        drillEffectRnd = 0f
        drillEffect = HIFx.implosion
        ambientSoundVolume = 0.18f
        ambientSound = Sounds.drillCharge
    }

    override fun load() {
        super.load()
        topInvertRegion = Core.atlas.find("$name-top-invert")
        glowRegion = Core.atlas.find("$name-glow")
        arrowRegion = Core.atlas.find("$name-arrow")
        arrowBlurRegion = Core.atlas.find("$name-arrow-blur")
    }

    open inner class ImplosionDrillBuild : AdaptDrillBuild() {
        @JvmField var smoothProgress = 0f
        @JvmField var invertTime = 0f

        override fun updateProgress() {
            smoothProgress = Mathf.lerpDelta(smoothProgress, progress / (mineInterval() - 20f), 0.1f)
            super.updateProgress()
        }

        override fun updateOutput() {
            if (progress > mineInterval()) {
                val outCount = (progress / mineInterval()).toInt() * mineCount
                for (i in 0 until outCount) {
                    if (outputItem() != null) {
                        if (coreSend && core() != null && core().acceptItem(this, outputItem())) {
                            core().handleItem(this, outputItem())
                        } else {
                            offload(outputItem())
                        }
                    }
                }
                invertTime = 1f
                progress %= mineInterval()

                if (wasVisible) {
                    Effect.shake(shake, shake, this)
                    drillSound.at(x, y, 1f + Mathf.range(drillSoundPitchRand), drillSoundVolume)
                    drillEffect.at(x + Mathf.range(drillEffectRnd), y + Mathf.range(drillEffectRnd), dominantItem.color)
                }
            }
        }

        override fun draw() {
            Draw.rect(baseRegion, x, y)
            if (warmup > 0f) {
                drawMining()
            }

            Draw.z(Layer.blockOver - 4f)

            Draw.rect(topRegion, x, y)

            if (invertTime > 0f && topInvertRegion.found()) {
                Draw.alpha(Interp.pow3Out.apply(invertTime))
                Draw.rect(topInvertRegion, x, y)
                Draw.color()
            }

            if (outputItem() != null && drawMineItem) {
                Draw.color(dominantItem.color)
                Draw.rect(oreRegion, x, y)
                Draw.color()
            }

            drawTeamTop()

            val fract = smoothProgress
            Draw.color(arrowColor)
            for (i in 0..3) {
                for (j in 0 until arrows) {
                    val arrowFract = (arrows - 1 - j).toFloat()
                    val a = Mathf.clamp(fract * arrows - arrowFract)
                    Tmp.v1.trns((i * 90 + 45).toFloat(), j * arrowSpacing + arrowOffset)

                    Draw.z(Layer.block)
                    Draw.color(baseArrowColor, arrowColor, a)
                    Draw.rect(arrowRegion, x + Tmp.v1.x, y + Tmp.v1.y, (i * 90).toFloat())

                    Draw.color(arrowColor)

                    if (arrowBlurRegion.found()) {
                        Draw.z(Layer.blockAdditive)
                        Draw.blend(Blending.additive)
                        Draw.alpha(Mathf.pow(a, 10f))
                        Draw.rect(arrowBlurRegion, x + Tmp.v1.x, y + Tmp.v1.y, (i * 90).toFloat())
                        Draw.blend()
                    }
                }
            }
            Draw.color()

            if (glowRegion.found()) {
                Drawf.additive(glowRegion, Tmp.c2.set(glowColor).a(Mathf.pow(fract, 3f) * glowColor.a), x, y)
            }
        }
    }
}
