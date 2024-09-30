package heavyindustry.world.blocks.production

import arc.Core
import arc.graphics.Blending
import arc.graphics.Color
import arc.graphics.g2d.Draw
import arc.graphics.g2d.TextureRegion
import arc.math.Mathf
import arc.util.Time
import mindustry.content.Fx
import mindustry.game.Team
import mindustry.graphics.Drawf
import mindustry.graphics.Layer

/**
 * Draw the original drill bit rotation.
 * @author Wisadell
 */
open class RotatorDrill(name: String) : AdaptDrill(name) {
    lateinit var rotatorRegion: TextureRegion
    lateinit var rimRegion: TextureRegion

    /** Speed the drill bit rotates at.  */
    @JvmField var rotateSpeed: Float = 2f

    @JvmField var heatColor: Color = Color.valueOf("ff5512")

    @JvmField var drawRim: Boolean = false
    @JvmField var drawSpinSprite: Boolean = true

    init {
        drillEffect = Fx.mine
    }

    override fun load() {
        super.load()
        rotatorRegion = Core.atlas.find("$name-rotator")
        rimRegion = Core.atlas.find("$name-rim")
    }

    override fun icons(): Array<TextureRegion> {
        return if (teamRegion.found()) arrayOf(baseRegion, rotatorRegion, topRegion, teamRegions[Team.sharded.id]) else arrayOf(baseRegion, rotatorRegion, topRegion)
    }

    open inner class RotatorDrillBuild : AdaptDrillBuild() {
        @JvmField var timeDrilled: Float = 0f

        override fun updateTile() {
            super.updateTile()
            timeDrilled += warmup * delta()
        }

        override fun draw() {
            val s = 0.3f
            val ts = 0.6f

            Draw.rect(baseRegion, x, y)
            if (warmup > 0f) {
                drawMining()
            }

            Draw.z(Layer.blockOver - 4f)

            if (drawSpinSprite) {
                Drawf.spinSprite(rotatorRegion, x, y, timeDrilled * rotateSpeed)
            } else {
                Draw.rect(rotatorRegion, x, y, timeDrilled * rotateSpeed)
            }

            Draw.rect(topRegion, x, y)
            if (outputItem() != null && drawMineItem) {
                Draw.color(dominantItem.color)
                Draw.rect(oreRegion, x, y)
                Draw.color()
            }

            if (drawRim) {
                Draw.color(heatColor)
                Draw.alpha(warmup * ts * (1f - s + Mathf.absin(Time.time, 3f, s)))
                Draw.blend(Blending.additive)
                Draw.rect(rimRegion, x, y)
                Draw.blend()
                Draw.color()
            }

            drawTeamTop()
        }
    }
}
