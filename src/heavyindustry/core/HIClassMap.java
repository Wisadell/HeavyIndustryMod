package heavyindustry.core;

import heavyindustry.ai.*;
import heavyindustry.entities.abilities.*;
import heavyindustry.entities.bullet.*;
import heavyindustry.entities.effect.*;
import heavyindustry.entities.part.*;
import heavyindustry.type.*;
import heavyindustry.type.weather.*;
import heavyindustry.world.blocks.defense.*;
import heavyindustry.world.blocks.defense.turrets.*;
import heavyindustry.world.blocks.distribution.*;
import heavyindustry.world.blocks.heat.*;
import heavyindustry.world.blocks.liquid.*;
import heavyindustry.world.blocks.logic.*;
import heavyindustry.world.blocks.payload.*;
import heavyindustry.world.blocks.units.*;
import heavyindustry.world.blocks.power.*;
import heavyindustry.world.blocks.production.*;
import heavyindustry.world.blocks.storage.*;
import heavyindustry.world.draw.*;

import static mindustry.mod.ClassMap.classes;

/** Generated class. Maps simple class names to concrete classes. For use in JSON attached mods. */
public class HIClassMap {
    static {
        //ai
        classes.put("DepotMinerAI", DepotMinerAI.class);
        //ability
        classes.put("JavelinAbility", JavelinAbility.class);
        //bullet
        classes.put("AccelBulletType", AccelBulletType.class);
        classes.put("AdaptedLightningBulletType", AdaptedLightningBulletType.class);
        classes.put("AimToPosBulletType", AimToPosBulletType.class);
        classes.put("BallistaBulletType", BallistaBulletType.class);
        classes.put("CritBulletType", CritBulletType.class);
        classes.put("CtrlMissileBulletType", CtrlMissileBulletType.class);
        classes.put("DelayedPointBulletType", DelayedPointBulletType.class);
        classes.put("EffectBulletType", EffectBulletType.class);
        classes.put("FallingBulletType", FallingBulletType.class);
        classes.put("FireWorkBulletType", FireWorkBulletType.class);
        classes.put("colorFire", FireWorkBulletType.colorFire.class);
        classes.put("spriteBullet", FireWorkBulletType.spriteBullet.class);
        classes.put("FlameBulletType", FlameBulletType.class);
        classes.put("HailStoneBulletType", HailStoneBulletType.class);
        classes.put("LightningLinkerBulletType", LightningLinkerBulletType.class);
        classes.put("PositionLightningBulletType", PositionLightningBulletType.class);
        classes.put("TrailFadeBulletType", TrailFadeBulletType.class);
        //effect
        classes.put("EffectWrapper", EffectWrapper.class);
        //part
        classes.put("HIDrawPart", HIDrawPart.class);
        //type
        classes.put("BetterPlanet", BetterPlanet.class);
        classes.put("EffectWeather", EffectWeather.class);
        classes.put("HailStormWeather", HailStormWeather.class);
        classes.put("SpawnerWeather", SpawnerWeather.class);
        //blocks
        classes.put("AssignOverdrive", AssignOverdrive.class);
        classes.put("AssignOverdriveBuild", AssignOverdrive.AssignOverdriveBuild.class);
        classes.put("InsulationWall", InsulationWall.class);
        classes.put("InsulationWallBuild", InsulationWall.InsulationWallBuild.class);
        classes.put("RegenWall", RegenWall.class);
        classes.put("RegenWallBuild", RegenWall.RegenWallBuild.class);
        classes.put("ShapedWall", ShapedWall.class);
        classes.put("ShapeWallBuild", ShapedWall.ShapeWallBuild.class);
        classes.put("MultiBulletTurret", MultiBulletTurret.class);
        classes.put("MultiBulletTurretBuild", MultiBulletTurret.MultiBulletTurretBuild.class);
        classes.put("ShootMatchTurret", ShootMatchTurret.class);
        classes.put("ShootMatchTurretBuild", ShootMatchTurret.ShootMatchTurretBuild.class);
        classes.put("MinigunTurret", MinigunTurret.class);
        classes.put("MinigunTurretBuild", MinigunTurret.MinigunTurretBuild.class);
        classes.put("HackTurret", HackTurret.class);
        classes.put("HackTurretBuild", HackTurret.HackTurretBuild.class);
        classes.put("AdaptDirectionalUnloader", AdaptDirectionalUnloader.class);
        classes.put("AdaptDirectionalUnloaderBuild", AdaptDirectionalUnloader.AdaptDirectionalUnloaderBuild.class);
        classes.put("TubeConveyor", TubeConveyor.class);
        classes.put("TubeConveyorBuild", TubeConveyor.TubeConveyorBuild.class);
        classes.put("DuctJunction", DuctJunction.class);
        classes.put("DuctJunctionBuild", DuctJunction.DuctJunctionBuild.class);
        classes.put("InvertedJunction", InvertedJunction.class);
        classes.put("InvertedJunctionBuild", InvertedJunction.InvertedJunctionBuild.class);
        classes.put("MultiJunction", MultiJunction.class);
        classes.put("MultiJunctionBuild", MultiJunction.MultiJunctionBuild.class);
        classes.put("MultiRouter", MultiRouter.class);
        classes.put("MultiRouterBuild", MultiRouter.MultiRouterBuild.class);
        classes.put("CardanItemBridge", CardanItemBridge.class);
        classes.put("CardanItemBridgeBuild", CardanItemBridge.CardanItemBridgeBuild.class);
        classes.put("NodeBridge", NodeBridge.class);
        classes.put("NodeBridgeBuild", NodeBridge.NodeBridgeBuild.class);
        classes.put("StackHelper", StackHelper.class);
        classes.put("StackHelperBuild", StackHelper.StackHelperBuild.class);
        classes.put("CardanLiquidBridge", CardanLiquidBridge.class);
        classes.put("CardanLiquidBridgeBuild", CardanLiquidBridge.CardanLiquidBridgeBuild.class);
        classes.put("LiquidFuelHeater", LiquidFuelHeater.class);
        classes.put("LiquidFuelHeaterBuild", LiquidFuelHeater.LiquidFuelHeaterBuild.class);
        classes.put("ThermalHeater", ThermalHeater.class);
        classes.put("ThermalHeaterBuild", ThermalHeater.ThermalHeaterBuild.class);
        classes.put("SortLiquidRouter", SortLiquidRouter.class);
        classes.put("SortLiquidRouterBuild", SortLiquidRouter.SortLiquidRouterBuild.class);
        classes.put("CopyMemoryBlock", CopyMemoryBlock.class);
        classes.put("CopyMemoryBuild", CopyMemoryBlock.CopyMemoryBuild.class);
        classes.put("PayloadJunction", PayloadJunction.class);
        classes.put("PayloadJunctionBuild", PayloadJunction.PayloadJunctionBuild.class);
        classes.put("DerivativeUnitFactory", DerivativeUnitFactory.class);
        classes.put("DerivativeUnitFactoryBuild", DerivativeUnitFactory.DerivativeUnitFactoryBuild.class);
        classes.put("BeamDiode", BeamDiode.class);
        classes.put("BeamDiodeBuild", BeamDiode.BeamDiodeBuild.class);
        classes.put("WindGenerator", WindGenerator.class);
        classes.put("WindGeneratorBuild", WindGenerator.WindGeneratorBuild.class);
        classes.put("GeneratorCrafter", GeneratorCrafter.class);
        classes.put("GeneratorCrafterBuild", GeneratorCrafter.GeneratorCrafterBuild.class);
        classes.put("LaserBeamDrill", LaserBeamDrill.class);
        classes.put("BeamDrillBuild", LaserBeamDrill.BeamDrillBuild.class);
        classes.put("AdaptUnloader", AdaptUnloader.class);
        classes.put("AdaptUnloaderBuild", AdaptUnloader.AdaptUnloaderBuild.class);
        //draw
        classes.put("AimPart", AimPart.class);
        classes.put("BowHalo", BowHalo.class);
        classes.put("PartBow", PartBow.class);
        classes.put("RunningLight", RunningLight.class);
        classes.put("DrawFactories", DrawFactories.class);
        classes.put("DrawFrostWing", DrawFrostWing.class);
        classes.put("DrawPowerLight", DrawPowerLight.class);
        classes.put("DrawPrinter", DrawPrinter.class);
        classes.put("DrawRotator", DrawRotator.class);
        classes.put("DrawScanLine", DrawScanLine.class);
    }
}