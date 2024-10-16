package heavyindustry.world.blocks.production;

import arc.graphics.g2d.*;
import arc.math.*;
import arc.math.geom.*;
import arc.util.io.*;
import mindustry.*;
import mindustry.content.*;
import mindustry.gen.*;
import mindustry.world.*;
import mindustry.world.blocks.environment.*;

import static arc.Core.*;
import static heavyindustry.util.HIUtils.*;

public class SporeFarm extends Block {
    protected static final byte[] tileMap = {
            39, 36, 39, 36, 27, 16, 27, 24, 39, 36, 39, 36, 27, 16, 27, 24,
            38, 37, 38, 37, 17, 41, 17, 43, 38, 37, 38, 37, 26, 21, 26, 25,
            39, 36, 39, 36, 27, 16, 27, 24, 39, 36, 39, 36, 27, 16, 27, 24,
            38, 37, 38, 37, 17, 41, 17, 43, 38, 37, 38, 37, 26, 21, 26, 25,
            3,  4,  3,  4, 15, 40, 15, 20,  3,  4,  3,  4, 15, 40, 15, 20,
            5, 28,  5, 28, 29, 10, 29, 23,  5, 28,  5, 28, 31, 11, 31, 32,
            3,  4,  3,  4, 15, 40, 15, 20,  3,  4,  3,  4, 15, 40, 15, 20,
            2, 30,  2, 30,  9, 47,  9, 22,  2, 30,  2, 30, 14, 44, 14,  6,
            39, 36, 39, 36, 27, 16, 27, 24, 39, 36, 39, 36, 27, 16, 27, 24,
            38, 37, 38, 37, 17, 41, 17, 43, 38, 37, 38, 37, 26, 21, 26, 25,
            39, 36, 39, 36, 27, 16, 27, 24, 39, 36, 39, 36, 27, 16, 27, 24,
            38, 37, 38, 37, 17, 41, 17, 43, 38, 37, 38, 37, 26, 21, 26, 25,
            3,  0,  3,  0, 15, 42, 15, 12,  3,  0,  3,  0, 15, 42, 15, 12,
            5,  8,  5,  8, 29, 35, 29, 33,  5,  8,  5,  8, 31, 34, 31,  7,
            3,  0,  3,  0, 15, 42, 15, 12,  3,  0,  3,  0, 15, 42, 15, 12,
            2,  1,  2,  1,  9, 45,  9, 19,  2,  1,  2,  1, 14, 18, 14, 13
    };
    protected static final int frames = 5;

    protected int gTimer;

    public TextureRegion[] sporeRegions, groundRegions, fenceRegions;
    public TextureRegion cageFloor;

    public SporeFarm(String name){
        super(name);

        update = true;
        gTimer = timers++;
    }

    @Override
    public void load(){
        super.load();
        sporeRegions = split(name + "-spore", 32, 0);
        groundRegions = split(name + "-ground", 32, 0);

        fenceRegions = split(name + "-fence", 32, 12, 4);
        cageFloor = atlas.find(name + "-floor");
    }

    @Override
    protected TextureRegion[] icons() {
        return new TextureRegion[]{region};
    }

    public class SporeFarmBuild extends Building{
        public float growth, delay = -1;
        public int tileIndex = -1;
        public boolean needsTileUpdate;

        public boolean randomChk(){
            Tile cTile = Vars.world.tile(tileX() + Mathf.range(3), tileY() + Mathf.range(3));

            return cTile != null && cTile.floor().liquidDrop == Liquids.water;
        }

        public void updateTilings(){
            tileIndex = 0;

            for(int i = 0; i < 8; i++){
                Tile other = tile.nearby(Geometry.d8(i));

                if(other == null || !(other.build instanceof SporeFarmBuild)) continue;
                tileIndex += 1 << i;
            }
        }

        public void updateNeighbours(){
            for(int i = 0; i < 8; i++){
                Tile other = tile.nearby(Geometry.d8(i));

                if(other == null || !(other.build instanceof SporeFarmBuild b)) continue;
                b.needsTileUpdate = true;
            }
        }

        @Override
        public void onProximityRemoved(){
            super.onProximityRemoved();

            updateNeighbours();
        }

        @Override
        public void updateTile(){
            if(tileIndex == -1){
                updateTilings();
                updateNeighbours();
            }
            if(needsTileUpdate){
                updateTilings();
                needsTileUpdate = false;
            }
            if(timer(gTimer, (60f + delay) * 5f)){
                if(delay == -1){
                    delay = (tileX() * 89f + tileY() * 13f) % 21f;
                }else{
                    boolean chk = randomChk();

                    if(growth == 0f && !chk) return;
                    growth += chk ? growth > frames - 2 ? 0.1f : 0.45f : -0.1f;

                    if(growth >= frames){
                        growth = frames - 1f;
                        if(items.total() < 1) offload(Items.sporePod);
                    }
                    if(growth < 0f) growth = 0f;
                }
            }
            if(timer(timerDump, 15f)) dump(Items.sporePod);
        }

        @Override
        public void draw(){
            float rrot = (tileX() * 89f + tileY() * 13f) % 4f;
            float rrot2 = (tileX() * 69f + tileY() * 42f) % 4f;

            if(growth < frames - 0.5f){
                Tile t = Vars.world.tileWorld(x, y);

                if(t != null && t.floor() != Blocks.air){
                    Floor f = t.floor();

                    Mathf.rand.setSeed(t.pos());
                    Draw.rect(f.variantRegions()[Mathf.randomSeed(t.pos(), 0, Math.max(0, variantRegions().length - 1))], x, y);
                }

                Draw.rect(cageFloor, x, y);
            }

            if(growth != 0f){
                Draw.rect(groundRegions[Mathf.floor(growth)], x, y, rrot * 90f);
                Draw.rect(sporeRegions[Mathf.floor(growth)], x, y, rrot2 * 90f);
            }

            Draw.rect(fenceRegions[tileMap[tileIndex]], x, y, 8f, 8f);
            drawTeamTop();
        }

        @Override
        public void write(Writes write){
            super.write(write);
            write.f(growth);
        }

        @Override
        public void read(Reads read, byte revision){
            super.read(read, revision);
            growth = read.f();
        }
    }
}
