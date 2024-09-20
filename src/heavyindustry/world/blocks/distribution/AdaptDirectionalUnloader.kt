package heavyindustry.world.blocks.distribution

import mindustry.world.blocks.distribution.DirectionalUnloader

/**
 * The ultimate dream of Saturated Firepower.
 */
open class AdaptDirectionalUnloader(name: String) : DirectionalUnloader(name) {
    inner class AdaptDirectionalUnloaderBuild : DirectionalUnloaderBuild() {
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
    }
}
