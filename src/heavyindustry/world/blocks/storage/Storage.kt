package heavyindustry.world.blocks.storage

import arc.Core
import arc.graphics.g2d.TextureRegion
import arc.math.Mathf
import arc.util.Time
import arc.util.io.Reads
import arc.util.io.Writes
import mindustry.game.Team
import mindustry.graphics.Pal
import mindustry.ui.Bar
import mindustry.world.blocks.storage.CoreBlock.CoreBuild
import mindustry.world.blocks.storage.StorageBlock
import mindustry.world.blocks.storage.Unloader
import mindustry.world.meta.Stat
import mindustry.world.modules.ItemModule

/**
 * Connect the core warehouse.
 * @author Wisadell
 */
open class CoreLinkBlock(name: String) : StorageBlock(name) {
    private var tmpCoreBuild: CoreBuild? = null

    init {
        update = true
        hasItems = true
        itemCapacity = 0
        configurable = true
        replaceable = false
    }

    override fun icons(): Array<TextureRegion> {
        return if (teamRegion.found()) arrayOf(region, teamRegions[Team.sharded.id]) else arrayOf(region)
    }

    override fun isAccessible(): Boolean {
        return true
    }

    override fun setStats() {
        super.setStats()
        stats.remove(Stat.itemCapacity)
    }

    override fun setBars() {
        super.setBars()
        if (consPower != null) addBar("warmup") { entity: CoreLinkBuild -> Bar(
            { if (Mathf.equal(entity.warmup, 1f, 0.015f)) Core.bundle["done"] else Core.bundle["research.load"] },
            { if (Mathf.equal(entity.warmup, 1f, 0.015f)) Pal.heal else Pal.redderDust },
            { entity.warmup }
        ) }
        removeBar("items")
        addBar("items") { entity: CoreLinkBuild -> Bar(
            { Core.bundle.format("bar.items", entity.items.total()) },
            { Pal.items },
            { (entity.items.total() / (if ((entity.core().also { tmpCoreBuild = it }) == null) Int.MAX_VALUE else tmpCoreBuild!!.storageCapacity)).toFloat() }
        ) }
    }

    open inner class CoreLinkBuild : StorageBuild() {
        @JvmField var warmup = 0f
        @JvmField var progress = 0f

        override fun updateTile() {
            warmup = if (efficiency() > 0 && core() != null) {
                if (Mathf.equal(warmup, 1f, 0.015f)) 1f
                else Mathf.lerpDelta(warmup, 1f, 0.01f)
            } else {
                if (Mathf.equal(warmup, 0f, 0.015f)) 0f
                else Mathf.lerpDelta(warmup, 0f, 0.03f)
            }

            progress += warmup * efficiency() * Time.delta

            if (Mathf.equal(warmup, 1f, 0.015f)) {
                if (linkedCore == null || !linkedCore.isValid && core() != null) {
                    linkedCore = core()
                    items = linkedCore.items
                }
            } else {
                linkedCore = null
                items = ItemModule()
            }
        }

        override fun canPickup(): Boolean {
            return false
        }

        override fun drawSelect() {}

        override fun write(write: Writes) {
            super.write(write)
            write.f(warmup)
            write.f(progress)
        }

        override fun read(read: Reads, revision: Byte) {
            super.read(read, revision)
            warmup = read.f()
            progress = read.f()
        }
    }
}

/**
 * A unloader that is not affected by game frame rates.
 * @author Wisadell
 */
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

/**
 * An electrically powered and accelerated unloader is one that adjusts the acceleration parameters of the original version and assigns them back after acceleration.
 * @author Wisadell
 * @author guiY
 */
open class OverchargeUnloader(name: String) : AdaptUnloader(name) {
    init {
        hasPower = true
    }

    open inner class OverchargeUnloaderBuild : AdaptUnloaderBuild() {
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
