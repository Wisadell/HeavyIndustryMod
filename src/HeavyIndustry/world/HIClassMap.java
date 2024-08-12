package HeavyIndustry.world;

import HeavyIndustry.entities.bullet.*;
import HeavyIndustry.entities.part.*;
import HeavyIndustry.type.weather.*;
import HeavyIndustry.world.blocks.defense.*;
import HeavyIndustry.world.blocks.defense.turrets.*;
import HeavyIndustry.world.blocks.distribution.*;
import HeavyIndustry.world.blocks.heat.*;
import HeavyIndustry.world.blocks.liquid.*;
import HeavyIndustry.world.blocks.logic.*;
import HeavyIndustry.world.blocks.payload.*;
import HeavyIndustry.world.blocks.units.*;
import HeavyIndustry.world.blocks.power.*;
import HeavyIndustry.world.blocks.production.*;
import HeavyIndustry.world.blocks.storage.*;
import HeavyIndustry.world.draw.*;
import mindustry.mod.ClassMap;

public class HIClassMap {
    public static void load(){
        //bullet
        ClassMap.classes.put("AccelBulletType", AccelBulletType.class);
        ClassMap.classes.put("AdaptedLightningBulletType", AdaptedLightningBulletType.class);
        ClassMap.classes.put("AimToPosBulletType", AimToPosBulletType.class);
        ClassMap.classes.put("BallistaBulletType", BallistaBulletType.class);
        ClassMap.classes.put("CritBulletType", CritBulletType.class);
        ClassMap.classes.put("CtrlMissileBulletType", CtrlMissileBulletType.class);
        ClassMap.classes.put("DelayedPointBulletType", DelayedPointBulletType.class);
        ClassMap.classes.put("EffectBulletType", EffectBulletType.class);
        ClassMap.classes.put("FallingBulletType", FallingBulletType.class);
        ClassMap.classes.put("FireWorkBulletType", FireWorkBulletType.class);
        ClassMap.classes.put("FlameBulletType", FlameBulletType.class);
        ClassMap.classes.put("HailStoneBulletType", HailStoneBulletType.class);
        ClassMap.classes.put("LightningLinkerBulletType", LightningLinkerBulletType.class);
        ClassMap.classes.put("PositionLightningBulletType", PositionLightningBulletType.class);
        ClassMap.classes.put("TrailFadeBulletType", TrailFadeBulletType.class);
        //part
        ClassMap.classes.put("HIPartProgress", HIPartProgress.class);
        //type
        ClassMap.classes.put("EffectWeather", EffectWeather.class);
        ClassMap.classes.put("HailStormWeather", HailStormWeather.class);
        ClassMap.classes.put("SpawnerWeather", SpawnerWeather.class);
        //blocks
        ClassMap.classes.put("AssignOverdrive", AssignOverdrive.class);
        ClassMap.classes.put("AssignOverdriveBuild", AssignOverdrive.AssignOverdriveBuild.class);
        ClassMap.classes.put("InsulationWall", InsulationWall.class);
        ClassMap.classes.put("InsulationWallBuild", InsulationWall.InsulationWallBuild.class);
        ClassMap.classes.put("RegenWall", RegenWall.class);
        ClassMap.classes.put("RegenWallBuild", RegenWall.RegenWallBuild.class);
        ClassMap.classes.put("ShapedWall", ShapedWall.class);
        ClassMap.classes.put("ShapeWallBuild", ShapedWall.ShapeWallBuild.class);
        ClassMap.classes.put("MinigunTurret", MinigunTurret.class);
        ClassMap.classes.put("MinigunTurretBuild", MinigunTurret.MinigunTurretBuild.class);
        ClassMap.classes.put("AdaptDirectionalUnloader", AdaptDirectionalUnloader.class);
        ClassMap.classes.put("AdaptDirectionalUnloaderBuild", AdaptDirectionalUnloader.AdaptDirectionalUnloaderBuild.class);
        ClassMap.classes.put("DuctJunction", DuctJunction.class);
        ClassMap.classes.put("DuctJunctionBuild", DuctJunction.DuctJunctionBuild.class);
        ClassMap.classes.put("InvertedJunction", InvertedJunction.class);
        ClassMap.classes.put("InvertedJunctionBuild", InvertedJunction.InvertedJunctionBuild.class);
        ClassMap.classes.put("MultiJunction", MultiJunction.class);
        ClassMap.classes.put("MultiJunctionBuild", MultiJunction.MultiJunctionBuild.class);
        ClassMap.classes.put("MultiRouter", MultiRouter.class);
        ClassMap.classes.put("MultiRouterBuild", MultiRouter.MultiRouterBuild.class);
        ClassMap.classes.put("NodeBridge", NodeBridge.class);
        ClassMap.classes.put("NodeBridgeBuild", NodeBridge.NodeBridgeBuild.class);
        ClassMap.classes.put("StackHelper", StackHelper.class);
        ClassMap.classes.put("StackHelperBuild", StackHelper.StackHelperBuild.class);
        ClassMap.classes.put("LiquidFuelHeater", LiquidFuelHeater.class);
        ClassMap.classes.put("LiquidFuelHeaterBuild", LiquidFuelHeater.LiquidFuelHeaterBuild.class);
        ClassMap.classes.put("ThermalHeater", ThermalHeater.class);
        ClassMap.classes.put("ThermalHeaterBuild", ThermalHeater.ThermalHeaterBuild.class);
        ClassMap.classes.put("SortLiquidRouter", SortLiquidRouter.class);
        ClassMap.classes.put("SortLiquidRouterBuild", SortLiquidRouter.SortLiquidRouterBuild.class);
        ClassMap.classes.put("CopyMemoryBlock", CopyMemoryBlock.class);
        ClassMap.classes.put("CopyMemoryBuild", CopyMemoryBlock.CopyMemoryBuild.class);
        ClassMap.classes.put("PayloadJunction", PayloadJunction.class);
        ClassMap.classes.put("PayloadJunctionBuild", PayloadJunction.PayloadJunctionBuild.class);
        ClassMap.classes.put("DerivativeUnitFactory", DerivativeUnitFactory.class);
        ClassMap.classes.put("DerivativeUnitFactoryBuild", DerivativeUnitFactory.DerivativeUnitFactoryBuild.class);
        ClassMap.classes.put("BeamDiode", BeamDiode.class);
        ClassMap.classes.put("BeamDiodeBuild", BeamDiode.BeamDiodeBuild.class);
        ClassMap.classes.put("WindGenerator", WindGenerator.class);
        ClassMap.classes.put("WindGeneratorBuild", WindGenerator.WindGeneratorBuild.class);
        ClassMap.classes.put("GeneratorCrafter", GeneratorCrafter.class);
        ClassMap.classes.put("GeneratorCrafterBuild", GeneratorCrafter.GeneratorCrafterBuild.class);
        ClassMap.classes.put("LaserBeamDrill", LaserBeamDrill.class);
        ClassMap.classes.put("BeamDrillBuild", LaserBeamDrill.BeamDrillBuild.class);
        ClassMap.classes.put("AdaptUnloader", AdaptUnloader.class);
        ClassMap.classes.put("AdaptUnloaderBuild", AdaptUnloader.AdaptUnloaderBuild.class);
        //draw
        ClassMap.classes.put("AimPart", AimPart.class);
        ClassMap.classes.put("BowHalo", BowHalo.class);
        ClassMap.classes.put("PartBow", PartBow.class);
        ClassMap.classes.put("RunningLight", RunningLight.class);
        ClassMap.classes.put("DrawFactories", DrawFactories.class);
        ClassMap.classes.put("DrawFrostWing", DrawFrostWing.class);
        ClassMap.classes.put("DrawPowerLight", DrawPowerLight.class);
        ClassMap.classes.put("DrawPrinter", DrawPrinter.class);
        ClassMap.classes.put("DrawRotator", DrawRotator.class);
        ClassMap.classes.put("DrawScanLine", DrawScanLine.class);
    }
}
