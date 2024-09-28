package heavyindustry.ai

import arc.math.Rand
import arc.math.geom.Position
import mindustry.ai.types.FlyingAI
import mindustry.content.Blocks
import mindustry.gen.Groups

import mindustry.Vars.player
import mindustry.Vars.state

/**
 * Attack around the target. When the blood volume is low, a repair device will be sought.
 * @author Alon
 */
open class CircleAi : FlyingAI() {
    var rand: Float = 0.0f

    //timer.
    var i = 0
    private var reversal = false

    //inversion time.
    var reversalTime = Rand().random(10, 20)
    override fun init() {
        rand = Rand().random(4f, unit.range())
    }

    override fun circle(target: Position?, circleLength: Float) {
        circle(target, circleLength, unit.speed())
    }

    override fun circle(target: Position?, circleLength: Float, speed: Float) {
        if (target == null) return
        i++
        if (i / 60 >= reversalTime) {
            i = 0
            reversal = !reversal
        }
        vec.set(target).sub(unit)
        if (vec.len() < circleLength) {
            vec.rotate((if (reversal) -1 else 1) * (circleLength - vec.len()) / circleLength * 180f)
        }
        vec.setLength(speed)
        unit.moveAt(vec)
    }

    override fun updateMovement() {
        unloadPayloads()
        if (target == null) {
            //Find a target.
            target = Groups.unit.find { it.team != unit.team }
        } else if (unit.health <= unit.maxHealth * 0.2) {
            //Determine blood volume.
            target = null
        }
        if (target != null && unit.hasWeapons()) {
            //Attack if there is a target.
            circle(target, rand)
        }
        // If not, go home.
        if (target == null && state.rules.waves && unit.team === state.rules.defaultTeam) {
            val find = Groups.build.find { it.block == Blocks.repairTurret || it.block == Blocks.repairPoint }
            val core = player.core()
            if (unit.health == unit.maxHealth || find == null) {
                moveTo(/*closestSpawner*/core, state.rules.dropZoneRadius + 130f)
            } else {
                moveTo(/*closestSpawner*/find, 0f)
            }
        }

    }
}