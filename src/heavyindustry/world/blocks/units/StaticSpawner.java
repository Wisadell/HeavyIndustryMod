package heavyindustry.world.blocks.units;

import arc.*;
import arc.graphics.g2d.*;
import arc.math.*;
import mindustry.content.*;
import mindustry.entities.*;
import mindustry.game.EventType.*;
import mindustry.gen.*;
import mindustry.type.*;
import mindustry.world.blocks.payloads.*;
import mindustry.world.blocks.units.*;

import static mindustry.Vars.*;

public class StaticSpawner extends UnitBlock {
    public UnitType spawnedUnit = UnitTypes.renale;
    public float spawnTime = 700;
    public int spawnAmount = 1;
    public Effect effect = Fx.none;

    public StaticSpawner(String name) {
        super(name);
        canOverdrive = false;
        targetable = false;
        underBullets = true;
        update = true;
        hasPower = false;
        hasItems = false;
        solid = false;
        configurable = false;
        clearOnDoubleTap = false;
        outputsPayload = false;
        rotate = false;
        commandable = false;
        ambientSound = Sounds.respawning;
        customShadow = true;
        destructible = false;
    }

    public class StaticSpawnerBuild extends UnitBuild {
        @Override
        public void updateTile() {
            super.updateTile();
            if(efficiency > 0){
                time += edelta() * speedScl * state.rules.unitBuildSpeed(team);
                progress += edelta() * state.rules.unitBuildSpeed(team);
                speedScl = Mathf.lerpDelta(speedScl, 1f, 0.05f);
            }else{
                speedScl = Mathf.lerpDelta(speedScl, 0f, 0.05f);
            }

            if(progress >= spawnTime){
                progress %= 1f;

                for (int i = 0; i < spawnAmount + Mathf.random(-5, 5); i++) {
                    Unit unit = spawnedUnit.spawn(team, tile.worldx(), tile.worldy());
                    unit.speedMultiplier = Mathf.randomSeed(unit.id, -0.5f, 1.5f);
                    payload = new UnitPayload(unit);
                    payVector.setZero();
                    Events.fire(new UnitCreateEvent(payload.unit, this));

                    effect.at(x, y);

                    if (payload.dump()) {
                        Call.unitBlockSpawn(tile);
                    }
                }
            }

            progress = Mathf.clamp(progress, 0, spawnTime);
        }

        @Override
        public void draw() {
            Draw.alpha(0.5f);
            Draw.rect(customShadowRegion, x, y);
            Draw.alpha(1);
            Draw.rect(region, x, y);
        }

        @Override
        public void damage(float damage) {}

        @Override
        public boolean canPickup() {
            return false;
        }
    }
}