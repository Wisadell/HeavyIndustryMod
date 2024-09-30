package heavyindustry.world.blocks.storage

import arc.util.Time
import mindustry.world.blocks.distribution.DirectionalUnloader

/**
 * A directional unloader that is not affected by game frame rates.
 * @author Wisadell
 */
open class AdaptDirectionalUnloader(name: String) : DirectionalUnloader(name) {
    open inner class AdaptDirectionalUnloaderBuild : DirectionalUnloaderBuild() {
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

/**
 * An electrically powered and accelerated unloader is one that adjusts the acceleration parameters of the original version and assigns them back after acceleration.
 * @author Wisadell
 * @author guiY
 */
open class OverchargeDirectionalUnloader(name: String) : AdaptDirectionalUnloader(name) {
    init {
        hasPower = true
    }

    open inner class OverchargeDirectionalUnloaderBuild : AdaptDirectionalUnloaderBuild() {
        var ts = 1f
        var td = 0f
        var powers = 0f

        override fun delta(): Float {
            return Time.delta * ts
        }

        override fun applyBoost(intensity: Float, duration: Float) {
            if (intensity >= ts) td = td.coerceAtLeast(duration)
            ts = ts.coerceAtLeast(intensity)
        }

        override fun updateTile() {
            powers = power.status

            if (td > 0) {
                td -= Time.delta
                if (td <= 0) ts = 1f
            }

            timeScale = (ts * powers).coerceAtLeast(0.001f)
            timeScaleDuration = (td / powers).coerceAtLeast(0.001f)

            if (powers >= 0.999) super.updateTile()
        }
    }
}
