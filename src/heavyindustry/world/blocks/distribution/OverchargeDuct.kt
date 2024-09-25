package heavyindustry.world.blocks.distribution

import heavyindustry.util.HIUtils
import heavyindustry.world.meta.HIStat
import arc.graphics.Blending
import arc.graphics.Color
import arc.graphics.g2d.Draw
import arc.graphics.g2d.TextureRegion
import arc.math.Mathf
import arc.math.geom.Geometry
import arc.util.Eachable
import arc.util.Tmp
import mindustry.entities.units.BuildPlan
import mindustry.graphics.Layer
import mindustry.graphics.Pal
import mindustry.world.blocks.Autotiler.SliceMode
import mindustry.world.blocks.distribution.Duct
import mindustry.world.meta.StatUnit

import mindustry.Vars.itemSize
import mindustry.Vars.tilesize

/**
 * Like BeltConversor, it only requires two or three sprites.
 *
 * At the same time, it has added a consumable power acceleration function.
 */
open class OverchargeDuct(name: String) : Duct(name) {
    lateinit var topAtlas: Array<TextureRegion>
    lateinit var botAtlas: Array<TextureRegion>
    lateinit var glowAtlas: Array<TextureRegion>

    @JvmField var glowAlpha = 1f
    @JvmField var glowColor: Color = Pal.redLight

    @JvmField var baseEfficiency = 0f

    init {
        noUpdateDisabled = false
    }

    override fun setStats() {
        super.setStats()
        stats.add(HIStat.itemsMovedBoost, 60f / (speed / (1f + baseEfficiency)), StatUnit.itemsSecond)
    }

    override fun load() {
        super.load()
        botAtlas = HIUtils.split("$name-bot", 32, 0)
        topAtlas = HIUtils.split("$name-top", 32, 0)
        glowAtlas = HIUtils.split("$name-glow", 32, 0)
    }

    override fun drawPlanRegion(plan: BuildPlan, list: Eachable<BuildPlan>) {
        val bits = getTiling(plan, list) ?: return

        Draw.scl(bits[1].toFloat(), bits[2].toFloat())
        Draw.alpha(0.5f)
        Draw.rect(botAtlas[bits[0]], plan.drawx(), plan.drawy(), (plan.rotation * 90).toFloat())
        Draw.color()
        Draw.rect(topAtlas[bits[0]], plan.drawx(), plan.drawy(), (plan.rotation * 90).toFloat())
        Draw.scl()
    }

    override fun icons(): Array<TextureRegion> {
        return arrayOf(botAtlas[0], topAtlas[0])
    }

    open inner class OverchargeDuctBuild : DuctBuild() {
        override fun draw() {
            val rotation = rotdeg()
            val r = this.rotation

            //draw extra ducts facing this one for tiling purposes
            for (i in 0..3) {
                if ((blending and (1 shl i)) != 0) {
                    val dir = r - i
                    val rot = if (i == 0) rotation else ((dir) * 90).toFloat()
                    drawAt(x + Geometry.d4x(dir) * tilesize * 0.75f, y + Geometry.d4y(dir) * tilesize * 0.75f, 0, rot, if (i != 0) SliceMode.bottom else SliceMode.top)
                }
            }

            //draw item
            if (current != null) {
                Draw.z(Layer.blockUnder + 0.1f)
                Tmp.v1.set(Geometry.d4x(recDir) * tilesize / 2f, Geometry.d4y(recDir) * tilesize / 2f).lerp(Geometry.d4x(r) * tilesize / 2f, Geometry.d4y(r) * tilesize / 2f, Mathf.clamp((progress + 1f) / 2f))

                Draw.rect(current.fullIcon, x + Tmp.v1.x, y + Tmp.v1.y, itemSize, itemSize)
            }

            Draw.scl(xscl.toFloat(), yscl.toFloat())
            drawAt(x, y, blendbits, rotation, SliceMode.none)
            Draw.reset()
        }

        override fun drawAt(x: Float, y: Float, bits: Int, rotation: Float, slice: SliceMode) {
            Draw.z(Layer.blockUnder)
            Draw.rect(sliced(botAtlas[bits], slice), x, y, rotation)

            Draw.z(Layer.blockUnder + 0.2f)
            Draw.color(transparentColor)
            Draw.rect(sliced(botAtlas[bits], slice), x, y, rotation)
            Draw.color()
            Draw.rect(sliced(topAtlas[bits], slice), x, y, rotation)

            if (sliced(glowAtlas[bits], slice).found() && power != null && power.status > 0f) {
                Draw.z(Layer.blockAdditive)
                Draw.color(glowColor, glowAlpha * power.status)
                Draw.blend(Blending.additive)
                Draw.rect(sliced(glowAtlas[bits], slice), x, y, rotation)
                Draw.blend()
                Draw.color()
            }
        }

        override fun updateTile() {
            val eff = if (power.status > 0f) (power.status + baseEfficiency) else 1f
            progress += this.delta() * eff / speed * 2f
            super.updateTile()
        }
    }
}
