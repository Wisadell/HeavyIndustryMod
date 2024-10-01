package heavyindustry.world.blocks.defense

import heavyindustry.world.meta.HIStat
import arc.graphics.g2d.Draw
import arc.graphics.g2d.Fill
import arc.math.Mathf
import arc.util.Time
import mindustry.content.Fx
import mindustry.entities.TargetPriority
import mindustry.gen.Building
import mindustry.gen.Bullet
import mindustry.gen.Unit
import mindustry.world.Block
import mindustry.world.blocks.defense.RegenProjector
import mindustry.world.meta.BlockGroup
import mindustry.world.meta.Env
import mindustry.world.meta.Stat
import mindustry.world.meta.StatUnit
import kotlin.math.abs

import mindustry.Vars.tilesize

/**
 * A wall that can self restore life.
 * @author Wisadell
 */
open class RegenWall(name: String) : RegenProjector(name) {
    @JvmField var chanceHeal = -1f
    @JvmField var chanceDeflect = -1f
    @JvmField var regenPercent = 0.1f

    init {
        group = BlockGroup.walls
        priority = TargetPriority.wall
        buildCostMultiplier = 6f
        crushDamageMultiplier = 5f
        update = true
        hasPower = false
        hasItems = false
        canOverdrive = false
        range = 1
        effect = Fx.none
        envEnabled = Env.any
    }

    override fun setStats() {
        super.setStats()

        stats.remove(Stat.range)
        stats.addPercent(HIStat.baseHealChance, chanceHeal)
        if (chanceDeflect > 0f) stats.add(Stat.baseDeflectChance, chanceDeflect)
    }

    override fun drawPlace(x: Int, y: Int, rotation: Int, valid: Boolean) {
        drawPotentialLinks(x, y)
        drawOverlay(x * tilesize + offset, y * tilesize + offset, rotation)
    }

    open inner class RegenWallBuild : RegenProjectorBuild() {
        @JvmField var healAmount = 0f
        @JvmField var hit = 0f
        @JvmField var heals = false

        override fun updateTile() {
            super.updateTile()

            hit = Mathf.clamp(hit - Time.delta / 10f)

            if (damaged() && heals) {
                heals = false
                heal(healAmount)
            }
        }

        override fun collision(bullet: Bullet): Boolean {
            super.collision(bullet)
            hit = 1f

            if (Mathf.chance(chanceHeal.toDouble())) {
                healAmount = bullet.damage * regenPercent
                heals = true
            }

            if (chanceDeflect > 0f && bullet.vel.len() > 0.1f && bullet.type.reflectable && Mathf.chance((chanceDeflect / bullet.damage).toDouble())
            ) {
                bullet.trns(-bullet.vel.x, -bullet.vel.y)

                if (abs((x - bullet.x).toDouble()) > abs((y - bullet.y).toDouble())) {
                    bullet.vel.x *= -1f
                } else {
                    bullet.vel.y *= -1f
                }

                bullet.owner = this
                bullet.team = team
                bullet.time += 1f
                return false
            }

            return true
        }

        override fun drawSelect() {
            block.drawOverlay(x, y, rotation)
        }
    }
}

/**
 * Causing damage to units walking on it.
 * @author Wisadell
 */
open class Thorns(name: String) : Block(name) {
    @JvmField val timerDamage = timers++

    @JvmField var cooldown = 30f
    @JvmField var damage = 8f

    override fun setStats() {
        super.setStats()
        stats.add(Stat.damage, 60f / cooldown * damage, StatUnit.perSecond)
    }

    open inner class ThornsBuild : Building() {
        override fun draw() {
            super.draw()
            Draw.color(team.color)
            Draw.alpha(0.22f)
            Fill.rect(x, y, 2f, 2f)
            Draw.color()
        }

        override fun unitOn(unit: Unit?) {
            if (timer.get(timerDamage, cooldown)) {
                unit!!.damage(damage)
                damage(damage)
            }
        }
    }
}
