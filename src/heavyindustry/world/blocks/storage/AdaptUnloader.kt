package heavyindustry.world.blocks.storage

import mindustry.world.blocks.storage.Unloader

open class AdaptUnloader(name: String) : Unloader(name) {
    open inner class AdaptUnloaderBuild : UnloaderBuild() {
        private var counter = 0f

        /** Make its uninstallation speed no longer affected by frame rate. */
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
