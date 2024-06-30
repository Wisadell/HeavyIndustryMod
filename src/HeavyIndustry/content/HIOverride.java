package HeavyIndustry.content;

import arc.graphics.Color;
import mindustry.content.Blocks;
import mindustry.content.Fx;
import mindustry.content.Items;
import mindustry.content.Liquids;
import mindustry.content.StatusEffects;
import mindustry.content.UnitTypes;
import mindustry.entities.bullet.BasicBulletType;
import mindustry.entities.bullet.ContinuousFlameBulletType;
import mindustry.entities.bullet.ContinuousLaserBulletType;
import mindustry.entities.bullet.RailBulletType;
import mindustry.entities.bullet.ShrapnelBulletType;
import mindustry.entities.effect.MultiEffect;
import mindustry.graphics.Pal;
import mindustry.type.LiquidStack;
import mindustry.type.PayloadStack;
import mindustry.world.blocks.defense.Wall;
import mindustry.world.blocks.defense.turrets.ContinuousLiquidTurret;
import mindustry.world.blocks.defense.turrets.ItemTurret;
import mindustry.world.blocks.defense.turrets.LaserTurret;
import mindustry.world.blocks.defense.turrets.PowerTurret;
import mindustry.world.blocks.environment.Floor;
import mindustry.world.blocks.payloads.PayloadConveyor;
import mindustry.world.blocks.payloads.PayloadRouter;
import mindustry.world.blocks.power.ConsumeGenerator;
import mindustry.world.blocks.power.NuclearReactor;
import mindustry.world.blocks.power.PowerNode;
import mindustry.world.blocks.power.VariableReactor;
import mindustry.world.blocks.production.AttributeCrafter;
import mindustry.world.blocks.production.BeamDrill;
import mindustry.world.blocks.production.BurstDrill;
import mindustry.world.blocks.production.GenericCrafter;
import mindustry.world.blocks.production.HeatCrafter;
import mindustry.world.blocks.production.Pump;
import mindustry.world.blocks.units.UnitAssembler;
import mindustry.world.meta.Attribute;
import mindustry.world.meta.BuildVisibility;

import static mindustry.type.ItemStack.with;

public class HIOverride {
    public static void overrideBlocks(){
        //environment
        Blocks.sandWater.itemDrop = Items.sand;
        Blocks.sandWater.playerUnmineable = true;
        Blocks.darksandWater.itemDrop = Items.sand;
        Blocks.darksandWater.playerUnmineable = true;
        Blocks.darksandTaintedWater.itemDrop = Items.sand;
        Blocks.darksandTaintedWater.playerUnmineable = true;
        ((Floor) Blocks.deepTaintedWater).liquidMultiplier = 1.5f;
        Blocks.oxidationChamber.canOverdrive = true;
        Blocks.neoplasiaReactor.canOverdrive = true;
        Blocks.slag.attributes.set(Attribute.heat, 1);
        Blocks.yellowStonePlates.attributes.set(Attribute.water, -1);
        Blocks.beryllicStone.attributes.set(HIAttribute.arkycite, 0.7f);
        Blocks.arkyicStone.attributes.set(HIAttribute.arkycite, 1);
        //wall
        Blocks.copperWall.armor = 1;
        Blocks.copperWallLarge.armor = 1;
        Blocks.titaniumWall.armor = 2;
        Blocks.titaniumWallLarge.armor = 2;
        Blocks.plastaniumWall.armor = 2;
        Blocks.plastaniumWallLarge.armor = 2;
        Blocks.thoriumWall.armor = 8;
        Blocks.thoriumWallLarge.armor = 8;
        Blocks.phaseWall.armor = 3;
        Blocks.phaseWallLarge.armor = 3;
        Blocks.surgeWall.armor = 12;
        Blocks.surgeWallLarge.armor = 12;
        ((Wall) Blocks.surgeWall).lightningChance = 0.1f;
        ((Wall) Blocks.surgeWall).lightningDamage = 25f;
        ((Wall) Blocks.surgeWallLarge).lightningChance = 0.1f;
        ((Wall) Blocks.surgeWallLarge).lightningDamage = 25f;
        ((Wall) Blocks.reinforcedSurgeWallLarge).lightningChance = 0.1f;
        //liquid
        ((Pump) Blocks.impulsePump).pumpAmount = 0.3f;
        Blocks.phaseConduit.liquidCapacity = 16;
        //liquid-erekir
        Blocks.reinforcedLiquidRouter.liquidCapacity = 40;
        //drill-erekir
        ((BurstDrill) Blocks.impactDrill).drillMultipliers.put(Items.sand, 4f);
        ((BurstDrill) Blocks.impactDrill).drillMultipliers.put(Items.scrap, 4f);
        ((BurstDrill) Blocks.impactDrill).drillMultipliers.put(Items.copper, 3.5f);
        ((BurstDrill) Blocks.impactDrill).drillMultipliers.put(Items.lead, 3.5f);
        ((BurstDrill) Blocks.impactDrill).drillMultipliers.put(Items.coal, 3f);
        ((BurstDrill) Blocks.impactDrill).drillMultipliers.put(Items.titanium, 2.5f);
        ((BurstDrill) Blocks.eruptionDrill).drillMultipliers.put(Items.sand, 4f);
        ((BurstDrill) Blocks.eruptionDrill).drillMultipliers.put(Items.scrap, 4f);
        ((BurstDrill) Blocks.eruptionDrill).drillMultipliers.put(Items.copper, 3.5f);
        ((BurstDrill) Blocks.eruptionDrill).drillMultipliers.put(Items.lead, 3.5f);
        ((BurstDrill) Blocks.eruptionDrill).drillMultipliers.put(Items.coal, 3f);
        ((BurstDrill) Blocks.eruptionDrill).drillMultipliers.put(Items.titanium, 2.5f);
        ((BurstDrill) Blocks.eruptionDrill).drillMultipliers.put(Items.tungsten, 1.5f);
        ((BeamDrill) Blocks.largePlasmaBore).drillMultipliers.put(Items.beryllium, 1.5f);
        ((BeamDrill) Blocks.largePlasmaBore).drillMultipliers.put(Items.graphite, 1.5f);
        //power
        ((PowerNode) Blocks.surgeTower).laserRange = 60f;
        ((PowerNode) Blocks.surgeTower).maxNodes = 3;
        ((ConsumeGenerator) Blocks.differentialGenerator).powerProduction = 28f;
        ((NuclearReactor) Blocks.thoriumReactor).powerProduction = 18f;
        Blocks.impactReactor.liquidCapacity = 80f;
        Blocks.neoplasiaReactor.canOverdrive = true;
        //power-erekir
        ((VariableReactor) Blocks.fluxReactor).powerProduction = 180f;
        //production
        //production-erekir
        Blocks.oxidationChamber.canOverdrive = true;
        Blocks.heatReactor.buildVisibility = BuildVisibility.shown;
        //production-erekir
        ((AttributeCrafter) Blocks.ventCondenser).maxBoost = 3f;
        ((GenericCrafter) Blocks.electrolyzer).outputLiquids = LiquidStack.with(Liquids.ozone, 4f / 60f, Liquids.hydrogen, 8f / 60f);
        ((HeatCrafter) Blocks.cyanogenSynthesizer).outputLiquid = new LiquidStack(Liquids.cyanogen, 4f / 60f);
        //storage
        Blocks.coreShard.buildVisibility = BuildVisibility.shown;
        Blocks.reinforcedContainer.itemCapacity = 160;
        //turret
        ((ItemTurret) Blocks.fuse).ammo(
                Items.titanium, new ShrapnelBulletType(){{
                    length = 100;
                    damage = 66f;
                    ammoMultiplier = 4f;
                    width = 17f;
                    reloadMultiplier = 1.3f;
                }},
                Items.thorium, new ShrapnelBulletType(){{
                    length = 100;
                    damage = 105f;
                    ammoMultiplier = 5f;
                    toColor = Pal.thoriumPink;
                    shootEffect = smokeEffect = Fx.thoriumShoot;
                }},
                HIItems.uranium, new ShrapnelBulletType(){{
                    length = 100;
                    damage = 135f;
                    ammoMultiplier = 6f;
                    toColor = Color.valueOf("a5b2c2");
                    shootEffect = smokeEffect = HIFx.uraniumShoot;
                }}
        );
        ((ItemTurret) Blocks.foreshadow).ammo(
                Items.surgeAlloy, new RailBulletType(){{
                    shootEffect = Fx.railShoot;
                    length = 600;
                    pointEffectSpace = 60;
                    pierceEffect = Fx.railHit;
                    pointEffect = Fx.railTrail;
                    hitEffect = Fx.massiveExplosion;
                    ammoMultiplier = 1;
                    smokeEffect = Fx.smokeCloud;
                    damage = 1450f;
                    pierceDamageFactor = 0.5f;
                    buildingDamageMultiplier = 0.3f;
                }}
        );
        ((ItemTurret) Blocks.spectre).range = 280f;
        ((ItemTurret) Blocks.spectre).ammo(
                Items.graphite, new BasicBulletType(7.5f, 60){{
                    hitSize = 4.8f;
                    width = 15f;
                    height = 21f;
                    shootEffect = Fx.shootBig;
                    ammoMultiplier = 4;
                    reloadMultiplier = 1.7f;
                    knockback = 0.3f;
                }},
                Items.thorium, new BasicBulletType(8f, 90){{
                    hitSize = 5;
                    width = 16f;
                    height = 23f;
                    shootEffect = Fx.shootBig;
                    pierceCap = 2;
                    pierceBuilding = true;
                    knockback = 0.7f;
                    ammoMultiplier = 1;
                }},
                HIItems.uranium, new BasicBulletType(9f, 120){{
                    hitSize = 5;
                    width = 16f;
                    height = 23f;
                    shootEffect = Fx.shootBig;
                    pierceCap = 2;
                    pierceBuilding = true;
                    knockback = 0.7f;
                    ammoMultiplier = 1;
                }},
                Items.pyratite, new BasicBulletType(7f, 80){{
                    hitSize = 5;
                    width = 16f;
                    height = 21f;
                    frontColor = Pal.lightishOrange;
                    backColor = Pal.lightOrange;
                    status = StatusEffects.burning;
                    hitEffect = new MultiEffect(Fx.hitBulletSmall, Fx.fireHit);
                    shootEffect = Fx.shootBig;
                    makeFire = true;
                    pierceCap = 2;
                    pierceBuilding = true;
                    knockback = 0.6f;
                    ammoMultiplier = 3;
                    splashDamage = 20f;
                    splashDamageRadius = 25f;
                }}
        );
        ((LaserTurret) Blocks.meltdown).range = 245;
        ((LaserTurret) Blocks.meltdown).shootType = new ContinuousLaserBulletType(96){{
            length = 250f;
            hitEffect = Fx.hitMeltdown;
            hitColor = Pal.meltdownHit;
            status = StatusEffects.melting;
            drawSize = 420f;
            incendChance = 0.4f;
            incendSpread = 5f;
            incendAmount = 1;
            ammoMultiplier = 1f;
        }};
        Blocks.breach.armor = 2;
        Blocks.diffuse.armor = 3;
        Blocks.sublimate.armor = 4;
        ((ContinuousLiquidTurret) Blocks.sublimate).ammo(
                Liquids.hydrogen, new ContinuousFlameBulletType(){{
                    damage = 60f;
                    length = 130;
                    knockback = 1f;
                    pierceCap = 2;
                    buildingDamageMultiplier = 0.3f;
                    colors = new Color[]{Color.valueOf("92abff7f"), Color.valueOf("92abffa2"), Color.valueOf("92abffd3"), Color.valueOf("92abff"), Color.valueOf("d4e0ff")};
                    lightColor = hitColor = flareColor = Color.valueOf("92abff");
                }},
                Liquids.cyanogen, new ContinuousFlameBulletType(){{
                    damage = 130f;
                    rangeChange = 70f;
                    length = 200;
                    knockback = 2f;
                    pierceCap = 3;
                    buildingDamageMultiplier = 0.3f;
                    colors = new Color[]{Color.valueOf("465ab888"), Color.valueOf("66a6d2a0"), Color.valueOf("89e8b6b0"), Color.valueOf("cafcbe"), Color.white};
                    lightColor = hitColor = flareColor = Color.valueOf("89e8b6");
                }}
        );
        Blocks.titan.armor = 13;
        Blocks.titan.researchCost = with(Items.thorium, 4000, Items.silicon, 3000, Items.tungsten, 2500);
        Blocks.disperse.armor = 9;
        Blocks.afflict.armor = 16;
        Blocks.lustre.armor = 15;
        Blocks.scathe.armor = 15;
        Blocks.smite.armor = 21;
        ((ItemTurret) Blocks.smite).minWarmup = 0.98f;
        ((ItemTurret) Blocks.smite).warmupMaintainTime = 45f;
        Blocks.malign.armor = 19;
        ((PowerTurret) Blocks.malign).minWarmup = 0.98f;
        ((PowerTurret) Blocks.malign).warmupMaintainTime = 45f;
        //units-erekir
        ((PayloadConveyor)Blocks.payloadConveyor).payloadLimit = 3.25f;
        ((PayloadRouter)Blocks.payloadRouter).payloadLimit = 3.25f;
        ((PayloadConveyor)Blocks.reinforcedPayloadConveyor).payloadLimit = 3.25f;
        ((PayloadRouter)Blocks.reinforcedPayloadRouter).payloadLimit = 3.25f;
        ((UnitAssembler) Blocks.tankAssembler).plans.add(new UnitAssembler.AssemblerUnitPlan(HIUnitTypes.dominate, 60f * 90f * 4f, PayloadStack.list(UnitTypes.precept, 4, Blocks.carbideWallLarge, 20, Blocks.reinforcedSurgeWallLarge, 10)));
        ((UnitAssembler) Blocks.shipAssembler).plans.add(new UnitAssembler.AssemblerUnitPlan(HIUnitTypes.havoc, 60f * 90f * 4f, PayloadStack.list(UnitTypes.obviate, 4, Blocks.carbideWallLarge, 20, Blocks.reinforcedSurgeWallLarge, 5)));
        ((UnitAssembler) Blocks.mechAssembler).plans.add(new UnitAssembler.AssemblerUnitPlan(HIUnitTypes.oracle, 60f * 90f * 4f, PayloadStack.list(UnitTypes.anthicus, 4, Blocks.carbideWallLarge, 20, Blocks.reinforcedSurgeWallLarge, 15)));
    }
    public static void overrideUnit(){
        UnitTypes.quell.targetAir = true;
        UnitTypes.quell.weapons.get(0).bullet.collidesAir = true;
        UnitTypes.quell.weapons.get(0).bullet.spawnUnit.targetAir = true;
        UnitTypes.quell.weapons.get(0).bullet.spawnUnit.weapons.get(0).bullet.collidesAir = true;
        UnitTypes.disrupt.targetAir = true;
        UnitTypes.disrupt.weapons.get(0).bullet.collidesAir = true;
        UnitTypes.disrupt.weapons.get(0).bullet.spawnUnit.targetAir = true;
        UnitTypes.disrupt.weapons.get(0).bullet.spawnUnit.weapons.get(0).bullet.collidesAir = true;
        UnitTypes.tecta.armor = 11;
        UnitTypes.collaris.armor = 13;
    }
    public static void overrideLiquids(){
        Liquids.hydrogen.flammability = 1.5f;
        Liquids.hydrogen.explosiveness = 0.5f;
        Liquids.ozone.flammability = 0;
        Liquids.ozone.explosiveness = 0;
    }
    public static void overrideItem(){
        Items.graphite.hardness = 2;
        Items.metaglass.hardness = 2;
        Items.silicon.hardness = 2;
        Items.plastanium.hardness = 3;
        Items.surgeAlloy.hardness = 6;
        Items.phaseFabric.hardness = 3;
        Items.carbide.hardness = 6;
        Items.serpuloItems.addAll(HIItems.nanocore, HIItems.chromium, HIItems.uranium, HIItems.heavyAlloy, HIItems.highEnergyFabric);
    }
}
