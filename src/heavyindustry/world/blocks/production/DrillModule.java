package heavyindustry.world.blocks.production;

import arc.graphics.g2d.*;
import arc.math.Mathf;
import arc.math.geom.*;
import arc.struct.*;
import arc.util.*;
import mindustry.entities.units.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.ui.*;
import mindustry.world.*;
import mindustry.world.meta.*;
import heavyindustry.util.*;
import heavyindustry.world.meta.*;

import static arc.Core.*;
import static mindustry.Vars.*;

/**
 * Basic drill bit expansion module.
 * @author LaoHuaJi
 */
public abstract class DrillModule extends Block {
    public TextureRegion[] topRotRegions;
    public Seq<Item[]> convertList = new Seq<>();
    public ObjectFloatMap<Item> convertMul = new ObjectFloatMap<>();
    public float boostSpeed = 0f;
    public float boostFinalMul = 0f;
    public float powerMul = 0f;
    public float powerExtra = 0f;
    public boolean coreSend = false;
    public boolean stackable = false;

    public DrillModule(String name) {
        super(name);
        size = 2;

        update = false;
        solid = true;
        destructible = true;
        rotate = true;

        drawCracks = false;

        hasItems = false;
        hasLiquids = false;
        hasPower = false;

        canOverdrive = false;
        drawDisabled = false;

        ambientSound = Sounds.drill;
        ambientSoundVolume = 0.018f;

        group = BlockGroup.drills;
        flags = EnumSet.of(BlockFlag.drill);
    }

    @Override
    public void load() {
        super.load();
        topRotRegions = HIUtils.split(name + "-top-full", 80, 0);
    }

    @Override
    public void setStats() {
        super.setStats();
        if (powerMul != 0 || powerExtra != 0) stats.add(HIStat.powerConsModifier, bundle.get("stat.hi-f-power-cons-modifier"), Strings.autoFixed(powerMul * 100, 0), Strings.autoFixed(powerExtra, 0));
        if (boostSpeed != 0 || boostFinalMul != 0) stats.add(HIStat.minerBoosModifier, bundle.get("stat.hi-f-miner-boost-modifier"), Strings.autoFixed(boostSpeed * 100, 0), Strings.autoFixed(boostFinalMul * 100, 0));
        if (convertList.size > 0) stats.add(HIStat.itemConvertList, getConvertList());
    }

    public String getConvertList(){
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < convertList.size; i++){
            Item[] convert = convertList.get(i);
            String cvt = Fonts.getUnicodeStr(convert[0].name) + convert[0].localizedName + " -> " + Fonts.getUnicodeStr(convert[1].name) + convert[1].localizedName + "(" + Strings.autoFixed((convertMul.get(convert[0], boostFinalMul)) * 100, 0) + "%)" + (i == convertList.size - 1?"": "\n");
            builder.append(cvt);
        }
        return builder.toString();
    }

    @Override
    public void drawDefaultPlanRegion(BuildPlan plan, Eachable<BuildPlan> list){
        Draw.rect(region, plan.drawx(), plan.drawy());
        Draw.rect(topRotRegions[plan.rotation], plan.drawx(), plan.drawy());
        drawPlanConfig(plan, list);
    }

    public class DrillModuleBuild extends Building{
        public @Nullable AdaptDrill.AdaptDrillBuild drillBuild;
        public float smoothWarmup, targetWarmup;

        @Override
        public void draw() {
            Draw.rect(region, x, y);
            Draw.z(Layer.blockOver);
            drawTeamTop();
            Draw.rect(topRotRegions[rotation], x, y);

            targetWarmup = (drillBuild != null && drillBuild.modules.contains(this)) ? drillBuild.warmup : 0;
            smoothWarmup = Mathf.lerp(smoothWarmup, targetWarmup, 0.02f);
        }

        @Override
        public void onProximityUpdate() {
            super.onProximityUpdate();
        }

        public boolean canApply(AdaptDrill.AdaptDrillBuild drill){
            for (int i = 0; i < size; i++){
                Point2 p = Edges.getEdges(size)[rotation * size + i];
                Building t = world.build(tileX() + p.x, tileY() + p.y);
                if (t != drill){
                    return false;
                }
            }
            return (drill.boostMul + boostSpeed <= drill.maxBoost() + 1) && checkConvert(drill) && checkSameModule(drill);
        }

        public boolean checkConvert(AdaptDrill.AdaptDrillBuild drill){
            if (convertList.size == 0) return true;
            for (Item[] convert: convertList){
                if (drill.dominantItem == convert[0]){
                    return true;
                }
            }
            return false;
        }

        public boolean checkSameModule(AdaptDrill.AdaptDrillBuild drill){
            if (stackable) return true;
            for (DrillModuleBuild module: drill.modules){
                if (module.block == this.block) return false;
            }
            return true;
        }

        public void apply(AdaptDrill.AdaptDrillBuild drill){
            drill.powerConsMul += powerMul;
            drill.powerConsExtra += powerExtra;
            drill.boostMul += boostSpeed;
            for (Item[] convert: convertList){
                if (drill.dominantItem == convert[0]){
                    drill.convertItem = convert[1];
                    drill.boostFinalMul += convertMul.get(convert[0], boostFinalMul);
                }
            }
            if (coreSend){
                drill.coreSend = true;
            }
        }
    }
}
