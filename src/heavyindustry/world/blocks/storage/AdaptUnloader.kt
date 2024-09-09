package heavyindustry.world.blocks.storage

import arc.func.Prov
import arc.graphics.Color
import arc.graphics.g2d.Draw
import mindustry.world.blocks.storage.Unloader

/**
 * The ultimate dream of Saturated Firepower.
 */
open class AdaptUnloader(name: String) : Unloader(name) {
    init {
        buildType = Prov { AdaptUnloaderBuild() }
    }

    inner class AdaptUnloaderBuild : UnloaderBuild() {
        private var counter = 0f

        override fun updateTile() {
            counter += edelta()
            val limit = speed

            while (counter >= limit) {
                unloadTimer = speed
                super.updateTile()
                counter -= limit
            }
        }

        override fun draw() {
            Draw.rect(region, x, y, if (block.rotate) rotdeg() else 0f)
            drawTeamTop()
            Draw.color(if (sortItem == null) Color.clear else sortItem.color)
            Draw.rect("$name-center", x, y)
            Draw.color()
        }
    }
}
