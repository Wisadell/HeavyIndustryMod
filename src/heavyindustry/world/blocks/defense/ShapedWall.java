package heavyindustry.world.blocks.defense;

import heavyindustry.content.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.math.geom.*;
import arc.struct.*;
import mindustry.gen.*;
import mindustry.world.blocks.defense.*;

import static heavyindustry.util.HIUtils.*;
import static heavyindustry.util.SpriteUtils.*;
import static mindustry.Vars.*;

/**
 * Shaped Wall
 * @author Yuria
 * @author Wisadell
 */
public class ShapedWall extends Wall {
    public TextureRegion[][] orthogonalRegion;

    public float maxShareStep = 1;

    protected final Seq<Building> toDamage = new Seq<>();
    protected final Queue<Building> queue = new Queue<>();

    public ShapedWall(String name){
        super(name);
        size = 1;
    }

    @Override
    public void load(){
        super.load();
        orthogonalRegion = splitLayers(name + "-full", 32, 2);
    }

    public class ShapedWallBuild extends WallBuild {
        public Seq<ShapedWallBuild> connectedWalls = new Seq<>();
        public int orthogonalIndex = 0;
        public boolean[] diagonalIndex = new boolean[4];

        public boolean linkValid(Building build){
            return checkWall(build) && Mathf.dstm(tileX(), tileY(), build.tileX(), build.tileY()) <= maxShareStep;
        }

        public boolean checkWall(Building build){
            return build != null && build.block == this.block;
        }

        public void findLinkWalls(){
            toDamage.clear();
            queue.clear();

            queue.addLast(this);
            while (queue.size > 0) {
                Building wall = queue.removeFirst();
                toDamage.add(wall);
                for (Building next : wall.proximity) {
                    if (linkValid(next) && !toDamage.contains(next)) {
                        toDamage.add(next);
                        queue.addLast(next);
                    }
                }
            }
        }

        public void updateDrawRegion(){
            orthogonalIndex = 0;

            for(int i = 0; i < orthogonalPos.length; i++){
                Point2 pos = orthogonalPos[i];
                Building build = world.build(tileX() + pos.x, tileY() + pos.y);
                if (build instanceof ShapedWallBuild && build.team == team){
                    orthogonalIndex += 1 << i;
                }
            }

            for(int i = 0; i < diagonalPos.length; i++){
                boolean diagonal = true;
                Point2[] posArray = diagonalPos[i];

                for (Point2 pos : posArray) {
                    Building build = world.build(tileX() + pos.x, tileY() + pos.y);
                    if (!(build instanceof ShapedWallBuild && build.team == team)) {
                        diagonal = false;
                        break;
                    }
                }

                diagonalIndex[i] = diagonal;
            }
        }

        public void updateProximityWall(){
            tmpTiles.clear();
            connectedWalls.clear();

            for (Point2 point : proximityPos) {
                Building other = world.build(tile.x + point.x, tile.y + point.y);
                if (other == null || other.team != team) continue;
                if (other instanceof ShapedWallBuild) {
                    tmpTiles.add(other);
                }
            }
            for (Building tile : tmpTiles) {
                ShapedWallBuild shapeWall = (ShapedWallBuild)tile;
                connectedWalls.add(shapeWall);
            }

            updateDrawRegion();
        }

        @Override
        public void drawTeam() {
            Draw.color(this.team.color);
            Fill.square(x, y, 1.015f, 45);
            Draw.color();
        }

        @Override
        public boolean collision(Bullet other){
            if(other.type.absorbable)other.absorb();
            return super.collision(other);
        }

        @Override
        public float handleDamage(float amount){
            findLinkWalls();
            float shareDamage = amount / toDamage.size;
            for (Building b: toDamage){
                damageShared(b, shareDamage);
            }
            HIFx.shareDamage.at(x, y, block.size * tilesize / 2f, team.color, Mathf.clamp(shareDamage/(block.health * 0.1f)));
            return shareDamage;
        }

        public void damageShared(Building building, float damage) {
            if (building.dead()) return;
            float dm = state.rules.blockHealth(team);
            if (Mathf.zero(dm)) {
                damage = building.health + 1;
            } else {
                damage /= dm;
            }
            if (!net.client()) {
                building.health -= damage;
            }
            healthChanged();
            if (building.health <= 0) {
                Call.buildDestroyed(building);
            }
            HIFx.shareDamage.at(building.x, building.y, building.block.size * tilesize / 2f, team.color, Mathf.clamp(damage/(block.health * 0.1f)));
        }

        @Override
        public void draw(){
            Draw.rect(orthogonalRegion[0][orthogonalIndex], x, y);
            for (int i = 0; i < diagonalIndex.length; i++){
                if (diagonalIndex[i]){
                    Draw.rect(orthogonalRegion[1][i], x, y);
                }
            }
        }

        @Override
        public void drawSelect(){
            super.drawSelect();
            findLinkWalls();
            for (Building wall: toDamage){
                Fill.square(wall.x, wall.y, 2);
            }
        }

        public void updateProximity() {
            super.updateProximity();

            updateProximityWall();
            for (ShapedWallBuild other : connectedWalls) {
                other.updateProximityWall();
            }
        }

        @Override
        public void onRemoved(){
            for (ShapedWallBuild other : connectedWalls) {
                other.updateProximityWall();
            }
            super.onRemoved();
        }
    }
}
