package heavyindustry.world.blocks.distribution

import arc.Core
import arc.graphics.g2d.Draw
import arc.graphics.g2d.TextureRegion
import arc.util.Eachable
import mindustry.content.Blocks
import mindustry.entities.units.BuildPlan
import mindustry.gen.Building
import mindustry.graphics.Drawf
import mindustry.graphics.Layer
import mindustry.type.Item
import mindustry.world.Tile
import mindustry.world.blocks.distribution.Router
import mindustry.world.draw.DrawBlock
import mindustry.world.draw.DrawDefault
import kotlin.math.sin

import heavyindustry.util.HIUtils.reverse
import mindustry.Vars.itemSize
import mindustry.Vars.state

open class TubeDistributor(name: String) : Router(name) {
    @JvmField var drawer: DrawBlock = DrawDefault()

    lateinit var rotorRegion: TextureRegion
    lateinit var lockedRegion1: TextureRegion
    lateinit var lockedRegion2: TextureRegion

    init {
        rotate = true
    }

    override fun load() {
        super.load()
        drawer.load(this)
        rotorRegion = Core.atlas.find("$name-rotator")
        lockedRegion1 = Core.atlas.find("$name-locked-side1")
        lockedRegion2 = Core.atlas.find("$name-locked-side2")
        uiIcon = Core.atlas.find("$name-icon")
    }

    override fun drawPlanRegion(plan: BuildPlan, list: Eachable<BuildPlan>) {
        super.drawPlanRegion(plan, list)
        Draw.rect(Core.atlas.find("$name-bottom"), plan.drawx(), plan.drawy())
        Draw.rect(rotorRegion, plan.drawx(), plan.drawy())
        Draw.rect(region, plan.drawx(), plan.drawy())
        Draw.rect(
            if (plan.rotation > 1) lockedRegion2 else lockedRegion1,
            plan.drawx(),
            plan.drawy(),
            (plan.rotation * 90).toFloat()
        )
    }

    public override fun icons(): Array<TextureRegion> {
        return arrayOf(Core.atlas.find("$name-icon"))
    }

    open inner class TubeDistributorBuild : RouterBuild() {
        @JvmField var lastTargetAngle = 0
        @JvmField var lastSourceAngle = 0
        @JvmField var time = 0f
        @JvmField var rot = 0f
        @JvmField var angle = 0f
        @JvmField var lastRot = 0f
        @JvmField var lastRotation = rotation

        override fun updateTile() {
            if (lastItem == null && items.any()) {
                lastItem = items.first()
            }

            if (lastItem != null) {
                time += 1f / speed * delta()

                val target = getTileTarget(lastItem, lastInput, false)

                if (target == null && time >= 0.7f) {
                    rot = lastRot
                    time = 0.7f
                }

                if (target != null && (time >= 1f)) {
                    getTileTarget(lastItem, lastInput, true)
                    target.handleItem(this, lastItem)
                    items.remove(lastItem, 1)
                    lastItem = null
                }

                if (lastInput != null && lastItem != null) {
                    val sa = sourceAngle()
                    val ta = targetAngle()

                    angle = (if ((sa == 0)) (if (ta == 2) 1 else if ((ta == 0 || ta == 3)) -1 else 1) else if ((sa == 2)) if ((ta == 0 || ta == 1)) -1 else 1 else if ((sa == 1)) if ((ta == 0 || ta == 3)) -1 else 1 else if ((ta == 0 || ta == 1)) 1 else -1).toFloat()
                }

                if (items.total() > 0 && !state.isPaused) {
                    lastRot = rot
                    rot += speed * angle * delta()
                }
            }
        }

        override fun acceptItem(source: Building, item: Item): Boolean {
            return team === source.team && lastItem == null && items.total() == 0 && front() !== source
        }

        override fun handleItem(source: Building, item: Item) {
            items.add(item, 1)
            lastItem = item
            time = 0f
            lastInput = source.tile()
        }

        override fun removeStack(item: Item, amount: Int): Int {
            val result = super.removeStack(item, amount)
            if (result != 0 && item === lastItem) {
                lastItem = null
            }
            return result
        }

        fun sourceAngle(): Int {
            for (sourceAngle in 0..3) {
                if (nearby(sourceAngle) === lastInput.build) {
                    lastSourceAngle = sourceAngle
                    return sourceAngle
                }
            }
            return lastSourceAngle
        }

        fun targetAngle(): Int {
            val target = getTileTarget(lastItem, lastInput, false)
            if (target != null) {
                for (targetAngle in 0..3) {
                    if (nearby(targetAngle) === target) {
                        lastTargetAngle = targetAngle
                        return targetAngle
                    }
                }
            }
            return lastTargetAngle
        }

        fun drawItem() {
            if (lastInput != null && lastInput.build != null && lastItem != null) {
                val isf = reverse(sourceAngle()) == targetAngle() || sourceAngle() == targetAngle()
                val alignment = targetAngle() == 0 || targetAngle() == 2
                val ox: Float
                val oy: Float
                val s = (size * 4).toFloat()
                val s2 = s * 2
                val linearMove = sin(Math.PI * time).toFloat() / 2.4f * s

                if (alignment) {
                    if (isf) {
                        if (sourceAngle() == targetAngle()) {
                            oy = if (time >= 0.5f) linearMove else -linearMove
                            ox = if (time >= 0.5f) (time * s2 - s) * (if (targetAngle() == 0) 1 else -1) else (time * s2 - s) * (if (targetAngle() == 0) -1 else 1)
                        } else {
                            oy = linearMove
                            ox = (time * s2 - s) * (if (targetAngle() == 0) 1 else -1)
                        }
                    } else {
                        oy = if (sourceAngle() == 1) (time * -s + s) else (time * s - s)
                        ox = time * s * (if (targetAngle() == 0) 1 else -1)
                    }
                } else {
                    if (isf) {
                        if (sourceAngle() == targetAngle()) {
                            ox = if (time >= 0.5f) linearMove else -linearMove
                            oy = if (time >= 0.5f) (time * s2 - s) * (if (targetAngle() == 1) 1 else -1) else (time * s2 - s) * (if (targetAngle() == 1) -1 else 1)
                        } else {
                            ox = sin(Math.PI * time).toFloat() / 2.4f * s
                            oy = (time * s2 - s) * (if (targetAngle() == 1) 1 else -1)
                        }
                    } else {
                        ox = if (sourceAngle() == 0) (time * -s + s) else (time * s - s)
                        oy = time * s * (if (targetAngle() == 1) 1 else -1)
                    }
                }

                Draw.rect(lastItem.fullIcon, x + ox, y + oy, itemSize, itemSize)
            }
        }

        override fun draw() {
            drawer.draw(this)
            Draw.z(Layer.block - 0.2f)
            drawItem()
            Draw.z(Layer.block - 0.15f)
            Drawf.spinSprite(rotorRegion, x, y, rot % 360)
            Draw.rect(region, x, y)
            Draw.rect(if (rotation > 1) lockedRegion2 else lockedRegion1, x, y, rotdeg())
        }

        override fun getTileTarget(item: Item, from: Tile, set: Boolean): Building? {
            val counter = lastRotation
            for (i in 0 until proximity.size) {
                val other = proximity[(i + counter) % proximity.size]
                if (set) lastRotation = ((lastRotation + 1) % proximity.size).toByte().toInt()
                if (other.tile === from && from.block() === Blocks.overflowGate) continue
                if (other.acceptItem(this, item)) {
                    return other
                }
            }
            return null
        }
    }
}
