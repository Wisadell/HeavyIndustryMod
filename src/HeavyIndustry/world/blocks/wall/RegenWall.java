package HeavyIndustry.world.blocks.wall;

import arc.math.Mathf;
import arc.util.Time;
import mindustry.content.Fx;
import mindustry.entities.TargetPriority;
import mindustry.gen.Bullet;
import mindustry.world.meta.BlockGroup;
import mindustry.world.meta.Env;
import mindustry.world.meta.Stat;
import mindustry.world.blocks.defense.RegenProjector;
import HeavyIndustry.world.meta.HIStat;

import static mindustry.Vars.tilesize;

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
        buildType = RegenWallBuild::new;
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
