package heavyindustry.world.blocks.defense;

import heavyindustry.content.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.math.geom.*;
import arc.struct.*;
import mindustry.gen.*;
import mindustry.world.blocks.defense.*;

import static heavyindustry.util.HIUtils.*;
import static mindustry.Vars.*;

/**
 * Shaped Wall
 * @author Yuria
 * @author Wisadell
 */
public class ShapedWall extends Wall {
    public TextureRegion[][] orthogonalRegion;

    protected static final Point2[] orthogonalPos = {
            new Point2(0, 1),
            new Point2(1, 0),
            new Point2(0, -1),
            new Point2(-1, 0)
    };

    protected static final Point2[][] diagonalPos = {
            new Point2[]{new Point2(1, 0), new Point2(1, 1), new Point2(0, 1)},
            new Point2[]{new Point2(1, 0), new Point2(1, -1), new Point2(0, -1)},
            new Point2[]{new Point2(-1, 0), new Point2(-1, -1), new Point2(0, -1)},
            new Point2[]{new Point2(-1, 0), new Point2(-1, 1), new Point2(0, 1)}
    };

    protected static final Point2[] proximityPos = {
            new Point2(0, 1),
            new Point2(1, 0),
            new Point2(0, -1),
            new Point2(-1, 0),

            new Point2(1, 1),
            new Point2(1, -1),
            new Point2(-1, -1),
            new Point2(-1, 1)
    };

    public float linkAlphaLerpDst = 24f, linkAlphaScl = 0.45f, minShareDamage = 70;

    public ShapedWall(String name){
        super(name);
        size = 1;
    }

    @Override
    public void load(){
        super.load();
        orthogonalRegion = splitLayers(name + "-full", 32, 2);
    }

    public class ShapeWallBuild extends WallBuild {
        public Seq<ShapeWallBuild> connectedWalls = new Seq<>();
        public int orthogonalIndex = 0;
        public boolean[] diagonalIndex = new boolean[4];

        public void updateDrawRegion(){
            orthogonalIndex = 0;

            for(int i = 0; i < orthogonalPos.length; i++){
                Point2 pos = orthogonalPos[i];
                Building build = world.build(tileX() + pos.x, tileY() + pos.y);
                if (build instanceof ShapeWallBuild && build.team == team){
                    orthogonalIndex += 1 << i;
                }
            }

            for(int i = 0; i < diagonalPos.length; i++){
                boolean diagonal = true;
                Point2[] posArray = diagonalPos[i];

                for (Point2 pos : posArray) {
                    Building build = world.build(tileX() + pos.x, tileY() + pos.y);
                    if (!(build instanceof ShapeWallBuild && build.team == team)) {
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
                if (other instanceof ShapeWallBuild) {
                    tmpTiles.add(other);
                }
            }
            for (Building tile : tmpTiles) {
                ShapeWallBuild shapeWall = (ShapeWallBuild)tile;
                connectedWalls.add(shapeWall);
            }

            updateDrawRegion();
        }

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
            if(amount > minShareDamage && hitTime <= 0){
                float maxHandOut = amount / 9;
                float haveHandOut = 0;

                for(ShapeWallBuild b : connectedWalls){
                    float damageP = Math.max(maxHandOut, Mathf.curve(b.healthf(), 0.25f, 0.75f) * maxHandOut);
                    haveHandOut += damageP;
                    b.damage(team, damageP);
                    if(damageP > 0.5f) HIFx.shareDamage.at(b.x, b.y, b.block.size * tilesize / 2f, team.color, damageP / Math.max(maxHandOut, minShareDamage));
                }

                HIFx.shareDamage.at(x, y, block.size * tilesize / 2f, team.color, 1f);
                hitTime = Math.max(1.5f, hitTime);
                return amount - haveHandOut;
            }else return super.handleDamage(amount);
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
            Draw.color(team.color);
            for(Building b : connectedWalls){
                Draw.alpha((1 - b.dst(this) / linkAlphaLerpDst) * linkAlphaScl);
                Fill.square(b.x, b.y, b.block.size * tilesize / 2f);
            }

            Draw.alpha(linkAlphaScl);
            Fill.square(x, y, size * tilesize / 2f);
        }

        public void updateProximity() {
            super.updateProximity();

            updateProximityWall();
            for (ShapeWallBuild other : connectedWalls) {
                other.updateProximityWall();
            }
        }

        @Override
        public void onRemoved(){
            for (ShapeWallBuild other : connectedWalls) {
                other.updateProximityWall();
            }
            super.onRemoved();
        }
    }
}
