package heavyindustry.world.blocks.distribution

import arc.math.geom.Geometry
import arc.math.geom.Point2
import mindustry.gen.Building
import mindustry.type.Item
import mindustry.world.Block
import mindustry.world.Edges
import mindustry.world.Tile
import mindustry.world.blocks.distribution.Conveyor

open class BeltArmoredConveyor(name: String) : BeltConveyor(name) {
    init {
        noSideBlend = true
    }

    override fun blends(tile: Tile, rotation: Int, otherx: Int, othery: Int, otherrot: Int, otherblock: Block): Boolean {
        return (otherblock.outputsItems() && blendsArmored(tile, rotation, otherx, othery, otherrot, otherblock)) || (lookingAt(tile, rotation, otherx, othery, otherblock) && otherblock.hasItems)
    }

    override fun blendsArmored(tile: Tile, rotation: Int, otherx: Int, othery: Int, otherrot: Int, otherblock: Block): Boolean {
        return (Point2.equals(tile.x + Geometry.d4(rotation).x, tile.y + Geometry.d4(rotation).y, otherx, othery) || ((!otherblock.rotatedOutput(otherx, othery) && Edges.getFacingEdge(otherblock, otherx, othery, tile) != null && Edges.getFacingEdge(otherblock, otherx, othery, tile).relativeTo(tile).toInt() == rotation) || (otherblock is Conveyor && otherblock.rotatedOutput(otherx, othery) && Point2.equals(otherx + Geometry.d4(otherrot).x, othery + Geometry.d4(otherrot).y, tile.x.toInt(), tile.y.toInt()))))
    }

    open inner class BeltArmoredConveyorBuild : BeltConveyorBuild() {
        override fun acceptItem(source: Building, item: Item): Boolean {
            return super.acceptItem(source, item) && (source.block is Conveyor || Edges.getFacingEdge(source.tile(), tile).relativeTo(tile).toInt() == rotation)
        }
    }
}
