package heavyindustry.world.blocks.defense;

import arc.math.*;
import arc.util.*;
import mindustry.content.*;
import mindustry.entities.*;
import mindustry.gen.*;
import mindustry.world.meta.*;
import mindustry.world.blocks.defense.*;
import heavyindustry.world.meta.*;

import static mindustry.Vars.*;

public class RegenWall extends RegenProjector {
    protected float chanceHeal = -1f;
    protected float chanceDeflect = -1f;
    protected float regenPercent = 0.1f;

    public RegenWall(String name){
        super(name);
        group = BlockGroup.walls;
        priority = TargetPriority.wall;
        buildCostMultiplier = 6f;
        crushDamageMultiplier = 5f;
        update = true;
        hasPower = false;
        hasItems = false;
        canOverdrive = false;
        range = 1;
        effect = Fx.none;
        envEnabled = Env.any;
    }

    @Override
    public void setStats(){
        super.setStats();

        stats.remove(Stat.range);
        stats.addPercent(HIStat.baseHealChance, chanceHeal);
        if(chanceDeflect > 0f) stats.add(Stat.baseDeflectChance, chanceDeflect);
    }

    @Override
    public void drawPlace(int x, int y, int rotation, boolean valid){
        drawPotentialLinks(x, y);
        drawOverlay(x * tilesize + offset, y * tilesize + offset, rotation);
    }

    public class RegenWallBuild extends RegenProjectorBuild{

        private float healAmount, hit;
        private boolean heals;

        @Override
        public void updateTile(){
            super.updateTile();

            hit = Mathf.clamp(hit - Time.delta / 10f);

            if(damaged() && heals){
                heals = false;
                heal(healAmount);
            }
        }

        @Override
        public boolean collision(Bullet bullet){
            super.collision(bullet);
            hit = 1f;

            if(Mathf.chance(chanceHeal)){
                healAmount = bullet.damage * regenPercent;
                heals = true;
            }

            if(
                    chanceDeflect > 0f && bullet.vel.len() > 0.1f
                            && bullet.type.reflectable && Mathf.chance(chanceDeflect / bullet.damage)
            ){
                bullet.trns(-bullet.vel.x, -bullet.vel.y);

                if(Math.abs(x - bullet.x) > Math.abs(y - bullet.y)){
                    bullet.vel.x *= -1f;
                }else{
                    bullet.vel.y *= -1f;
                }

                bullet.owner = this;
                bullet.team = team;
                bullet.time += 1f;
                return false;
            }

            return true;
        }

        @Override
        public void drawSelect(){
            block.drawOverlay(x, y, rotation);
        }
    }
}
