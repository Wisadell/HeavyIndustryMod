package heavyindustry.world.blocks.defense.turrets

import arc.Core
import arc.graphics.Color
import arc.graphics.g2d.Draw
import arc.graphics.g2d.Lines
import arc.graphics.g2d.TextureRegion
import arc.math.Mathf
import arc.struct.Seq
import arc.util.Time
import arc.util.Tmp
import arc.util.io.Reads
import arc.util.io.Writes
import heavyindustry.core.HeavyIndustryMod
import heavyindustry.util.HIUtils
import mindustry.Vars
import mindustry.graphics.Drawf
import mindustry.graphics.Layer
import mindustry.ui.Bar
import mindustry.world.Block
import mindustry.world.blocks.defense.turrets.ItemTurret
import mindustry.world.blocks.defense.turrets.Turret
import mindustry.world.consumers.ConsumeLiquidFilter
import mindustry.world.draw.DrawTurret
import mindustry.world.meta.Stat
import mindustry.world.meta.StatUnit
import kotlin.math.max

/** Realize muzzle rotation and firing of Minigun. */
open class MinigunTurret(name: String) : ItemTurret(name) {
    @JvmField var windupSpeed = 0.15f
    @JvmField var windDownSpeed = 0.05f
    @JvmField var minFiringSpeed = 3f
    @JvmField var logicSpeedScl = 0.25f
    @JvmField var maxSpeed = 30f
    @JvmField var barX = 0f
    @JvmField var barY = 0f
    @JvmField var barStroke = 0f
    @JvmField var barLength = 0f
    @JvmField var barWidth = 1.5f
    @JvmField var barHeight = 0.75f

    init {
        drawer = object : DrawTurret() {
            lateinit var barrel: TextureRegion
            lateinit var barrelOutline: TextureRegion

            override fun getRegionsToOutline(block: Block, out: Seq<TextureRegion>) {
                super.getRegionsToOutline(block, out)
                out.add(barrel)
            }

            override fun load(block: Block) {
                super.load(block)

                barrel = Core.atlas.find(block.name + "-barrel")
                barrelOutline = Core.atlas.find(block.name + "-barrel-outline")
            }

            override fun drawTurret(block: Turret, build: TurretBuild) {
                if (build !is MinigunTurretBuild) return

                val v = Tmp.v1

                Draw.z(Layer.turret - 0.01f)
                Draw.rect(outline, build.x + build.recoilOffset.x, build.y + build.recoilOffset.y, build.drawrot())
                for (i in 0..3) {
                    Draw.z(Layer.turret - 0.01f)
                    v.trns(build.rotation - 90f, barWidth * Mathf.cosDeg(build.spin - 90 * i), barHeight * Mathf.sinDeg(build.spin - 90 * i)).add(build.recoilOffset)
                    Draw.rect(barrelOutline, build.x + v.x, build.y + v.y, build.drawrot())
                    Draw.z(Layer.turret - 0.005f - Mathf.sinDeg(build.spin - 90 * i) / 1000f)
                    Draw.rect(barrel, build.x + v.x, build.y + v.y, build.drawrot())
                    if (build.heats[i] > 0.001f) {
                        Drawf.additive(heat, heatColor.write(Tmp.c1).a(build.heats[i]), build.x + v.x, build.y + v.y, build.drawrot(), Draw.z())
                    }
                }

                Draw.z(Layer.turret)
                super.drawTurret(block, build)

                if (build.speedf() > 0.0001f) {
                    Draw.color(build.barColor())
                    Lines.stroke(barStroke)
                    for (i in 0..1) {
                        v.trns(build.drawrot(), barX * Mathf.signs[i], barY).add(build.recoilOffset)
                        Lines.lineAngle(build.x + v.x, build.y + v.y, build.rotation, barLength * Mathf.clamp(build.speedf()), false)
                    }
                }
            }

            override fun drawHeat(block: Turret, build: TurretBuild) {
                //Don't
            }
        }
    }

    override fun setStats() {
        super.setStats()

        stats.remove(Stat.reload)
        val minValue = minFiringSpeed / 90f * 60f * shoot.shots
        val maxValue = maxSpeed / 90f * 60f * shoot.shots
        stats.add(Stat.reload, HIUtils.stringsFixed(minValue) + " - " + HIUtils.stringsFixed(maxValue) + StatUnit.perSecond.localized())
    }

    override fun setBars() {
        super.setBars()
        addBar(HeavyIndustryMod.name("minigunSpeed")) { entity: MinigunTurretBuild ->
            Bar(
                { Core.bundle.format("bar." + HeavyIndustryMod.name("minigunSpeed"), HIUtils.stringsFixed(entity.speedf() * 100f)) },
                { entity.barColor() },
                { entity.speedf() }
            )
        }
    }

    open inner class MinigunTurretBuild : ItemTurretBuild() {
        var heats: FloatArray = floatArrayOf(0f, 0f, 0f, 0f)
        protected var spinSpeed: Float = 0f
        var spin: Float = 0f

        fun barColor(): Color {
            return if (spinSpeed > minFiringSpeed) team.color else team.palette[2]
        }

        override fun updateTile() {
            val notShooting = !hasAmmo() || !isShooting || !isActive
            if (notShooting) {
                spinSpeed = Mathf.approachDelta(spinSpeed, 0f, windDownSpeed)
            }

            if (spinSpeed > getMaxSpeed()) {
                spinSpeed = Mathf.approachDelta(spinSpeed, getMaxSpeed(), windDownSpeed)
            }

            for (i in 0..3) {
                heats[i] = max((heats[i] - Time.delta / cooldownTime).toDouble(), 0.0).toFloat()
            }

            super.updateTile()
        }

        override fun updateShooting() {
            if (!hasAmmo()) return

            spinSpeed = Mathf.approachDelta(spinSpeed, getMaxSpeed(), windupSpeed * peekAmmo().reloadMultiplier * timeScale)

            if (reloadCounter >= 90 && spinSpeed > minFiringSpeed) {
                val type = peekAmmo()

                shoot(type)

                reloadCounter = spin % 90

                heats[Mathf.floor(spin - 90) % 360 / 90] = 1f
            }
        }

        override fun updateReload() {
            val shooting = hasAmmo() && isShooting && isActive
            val multiplier = if (hasAmmo()) peekAmmo().reloadMultiplier else 1f
            var add = spinSpeed * multiplier * Time.delta
            if (shooting && coolant != null && coolant.efficiency(this) > 0 && efficiency > 0) {
                val capacity = (coolant as? ConsumeLiquidFilter)?.getConsumed(this)?.heatCapacity ?: 1f
                coolant.update(this)
                add += coolant.amount * edelta() * capacity * coolantMultiplier

                if (Mathf.chance(0.06 * coolant.amount)) {
                    coolEffect.at(x + Mathf.range(size * Vars.tilesize / 2f), y + Mathf.range(size * Vars.tilesize / 2f))
                }
            }
            spin += add
            reloadCounter += add
        }

        protected fun getMaxSpeed(): Float {
            return maxSpeed * (if (!isControlled && logicControlled() && logicShooting) logicSpeedScl else 1f)
        }

        fun speedf(): Float {
            return spinSpeed / maxSpeed
        }

        override fun write(write: Writes) {
            super.write(write)
            write.f(spinSpeed)
            write.f(spin % 360f)
        }

        override fun read(read: Reads, revision: Byte) {
            super.read(read, revision)

            if (revision >= 2) {
                spinSpeed = read.f()

                if (revision >= 3) {
                    spin = read.f()
                }
            }
        }

        override fun version(): Byte {
            return 3
        }
    }
}
