package heavyindustry.world.blocks.distribution

import arc.Core
import arc.graphics.g2d.Draw
import arc.graphics.g2d.TextureRegion
import arc.math.geom.Geometry
import arc.util.Eachable
import arc.util.Time
import arc.util.Tmp
import mindustry.entities.units.BuildPlan
import mindustry.graphics.Layer
import mindustry.world.blocks.Autotiler.SliceMode
import mindustry.world.blocks.distribution.Conveyor

import mindustry.Vars.itemSize
import mindustry.Vars.tilesize
import mindustry.Vars.world

/**
 * Are you still troubled by the 20 sprites of traditional conveyor belts?
 *
 * This Type only sprites 3 textures to handle!
 */
open class BeltConveyor(name: String) : Conveyor(name) {
    lateinit var conveyorPartAtlas: TextureRegion
    lateinit var edgePartAtlas:TextureRegion
    lateinit var conveyorParts: Array<Array<TextureRegion>>
    lateinit var edgeParts: Array<Array<TextureRegion>>

    override fun load() {
        super.load()
        uiIcon = Core.atlas.find("$name-icon")

        conveyorPartAtlas = Core.atlas.find("$name-base")
        edgePartAtlas = Core.atlas.find("$name-edge")

        conveyorParts = conveyorPartAtlas.split(32, 32)
        edgeParts = edgePartAtlas.split(32, 32)
    }

    override fun drawPlanRegion(plan: BuildPlan, list: Eachable<BuildPlan>) {
        Draw.rect(uiIcon, plan.drawx(), plan.drawy(), (plan.rotation * 90).toFloat())
    }

    override fun icons(): Array<TextureRegion> {
        return arrayOf(uiIcon)
    }

    open inner class BeltConveyorBuild : ConveyorBuild() {
        override fun draw() {
            val frame = if (enabled && clogHeat <= 0.5f) (((Time.time * speed * 8f * timeScale * efficiency)) % 4).toInt() else 0

            //draw extra conveyors facing this one for non-square tiling purposes
            Draw.z(Layer.blockUnder)
            for (i in 0..3) {
                if ((blending and (1 shl i)) != 0) {
                    val dir = rotation - i
                    val rot = (if (i == 0) rotation * 90 else (dir) * 90).toFloat()

                    Draw.rect(sliced(conveyorParts[frame][0], if (i != 0) SliceMode.bottom else SliceMode.top), x + Geometry.d4x(dir) * tilesize * 0.75f, y + Geometry.d4y(dir) * tilesize * 0.75f, rot)
                    Draw.rect(sliced(edgeParts[(tile.x + tile.y) % 2][0], if (i != 0) SliceMode.bottom else SliceMode.top), x + Geometry.d4x(dir) * tilesize * 0.75f, y + Geometry.d4y(dir) * tilesize * 0.75f, rot)
                }
            }

            Draw.z(Layer.block - 0.2f)

            Draw.rect(conveyorParts[frame][blendbits], x, y, (tilesize * blendsclx).toFloat(), (tilesize * blendscly).toFloat(), (rotation * 90).toFloat())
            Draw.rect(edgeParts[(tile.x + tile.y) % 2][blendbits], x, y, (tilesize * blendsclx).toFloat(), (tilesize * blendscly).toFloat(), (rotation * 90).toFloat())

            Draw.z(Layer.block - 0.1f)
            val layer = Layer.block - 0.1f
            val wwidth = world.unitWidth().toFloat()
            val wheight = world.unitHeight().toFloat()
            val scaling = 0.01f

            for (i in 0 until len) {
                val item = ids[i]
                Tmp.v1.trns((rotation * 90).toFloat(), tilesize.toFloat(), 0f)
                Tmp.v2.trns((rotation * 90).toFloat(), -tilesize / 2f, xs[i] * tilesize / 2f)

                val ix = (x + Tmp.v1.x * ys[i] + Tmp.v2.x)
                val iy = (y + Tmp.v1.y * ys[i] + Tmp.v2.y)

                //keep draw position deterministic.
                Draw.z(layer + (ix / wwidth + iy / wheight) * scaling)
                Draw.rect(item.fullIcon, ix, iy,itemSize, itemSize)
            }
        }
    }
}
