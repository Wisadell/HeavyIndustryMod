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
import heavyindustry.world.blocks.environment.*;
import heavyindustry.world.blocks.heat.*;
import heavyindustry.world.blocks.liquid.*;
import heavyindustry.world.blocks.logic.*;
import heavyindustry.world.blocks.payload.*;
import heavyindustry.world.blocks.units.*;
import heavyindustry.world.blocks.power.*;
import heavyindustry.world.blocks.production.*;
import heavyindustry.world.blocks.storage.*;
import heavyindustry.world.consumers.*;
import heavyindustry.world.draw.*;

import static mindustry.mod.ClassMap.*;

/** Generated class. Maps simple class names to concrete classes. For use in JSON attached mods. */
public final class HIClassMap {
    public static void load(){
        //ai
        classes.put("SniperAI", SniperAI.class);
        classes.put("SurroundAI", SurroundAI.class);
        classes.put("MinerPointAI", MinerPointAI.class);
        //ability
        classes.put("BatteryAbility", BatteryAbility.class);
        classes.put("JavelinAbility", JavelinAbility.class);
        classes.put("TerritoryFieldAbility", TerritoryFieldAbility.class);
        classes.put("DeathAbility", DeathAbility.class);
        //bullets
        classes.put("AccelBulletType", AccelBulletType.class);
        classes.put("AdaptedLightningBulletType", AdaptedLightningBulletType.class);
        classes.put("AimToPosBulletType", AimToPosBulletType.class);
        classes.put("CritBulletType", CritBulletType.class);
        classes.put("CtrlMissileBulletType", CtrlMissileBulletType.class);
        classes.put("DelayedPointBulletType", DelayedPointBulletType.class);
        classes.put("EffectBulletType", EffectBulletType.class);
        classes.put("FallingBulletType", FallingBulletType.class);
        classes.put("FireWorkBulletType", FireWorkBulletType.class);
        classes.put("ColorFireBulletType", FireWorkBulletType.ColorFireBulletType.class);
        classes.put("SpriteBulletType", FireWorkBulletType.SpriteBulletType.class);
        classes.put("FlameBulletType", FlameBulletType.class);
        classes.put("HailStoneBulletType", HailStoneBulletType.class);
        classes.put("LightningLinkerBulletType", LightningLinkerBulletType.class);
        classes.put("PositionLightningBulletType", PositionLightningBulletType.class);
        classes.put("TrailFadeBulletType", TrailFadeBulletType.class);
        classes.put("MagmaBulletType", MagmaBulletType.class);
        classes.put("LiquidBulletData", LiquidMassDriver.LiquidBulletData.class);
        classes.put("LiquidMassDriverBolt", LiquidMassDriverBolt.class);
        //effects
        classes.put("LightningEffect", LightningEffect.class);
        classes.put("EffectWrapper", EffectWrapper.class);
        //parts
        classes.put("HIDrawPart", HIPartProgress.class);
        classes.put("ConstructPart", ConstructPart.class);
        //types
        classes.put("BetterPlanet", BetterPlanet.class);
        classes.put("EffectWeather", EffectWeather.class);
        classes.put("HailStormWeather", HailStormWeather.class);
        classes.put("SpawnerWeather", SpawnerWeather.class);
        //blocks
        classes.put("AtlasFloor", AtlasFloor.class);
        classes.put("AtlasProp", AtlasProp.class);
        classes.put("AtlasTallBlock", AtlasTallBlock.class);
        classes.put("TileOreBlock", TileOreBlock.class);
        classes.put("TileStaticWall", TileStaticWall.class);
        classes.put("GrooveFloor", GrooveFloor.class);
        classes.put("TiledFloor", TiledFloor.class);
        classes.put("SizedVent", TileVent.class);
        classes.put("ArmorFloor", ArmorFloor.class);
        classes.put("TallTreeBlock", TallTreeBlock.class);
        classes.put("AssignOverdrive", AssignOverdrive.class);
        classes.put("AssignOverdriveBuild", AssignOverdrive.AssignOverdriveBuild.class);
        classes.put("InsulationWall", InsulationWall.class);
        classes.put("InsulationWallBuild", InsulationWall.InsulationWallBuild.class);
        classes.put("RegenWall", RegenWall.class);
        classes.put("RegenWallBuild", RegenWall.RegenWallBuild.class);
        classes.put("ShapedWall", ShapedWall.class);
        classes.put("ShapedWallBuild", ShapedWall.ShapedWallBuild.class);
        classes.put("ShapedWallF", ShapedWallF.class);
        classes.put("ShapeWallBuildF", ShapedWallF.ShapedWallBuildF.class);
        classes.put("AparajitoWall", AparajitoWall.class);
        classes.put("AparajitoWallBuild", AparajitoWall.AparajitoWallBuild.class);
        classes.put("Thorns", Thorns.class);
        classes.put("ThornsBuild", Thorns.ThornsBuild.class);
        classes.put("Detonator", Detonator.class);
        classes.put("DetonatorBuild", Detonator.DetonatorBuild.class);
        classes.put("BombLauncher", BombLauncher.class);
        classes.put("BombLauncherBuild", BombLauncher.BombLauncherBuild.class);
        classes.put("MultiBulletTurret", MultiBulletTurret.class);
        classes.put("MultiBulletTurretBuild", MultiBulletTurret.MultiBulletTurretBuild.class);
        classes.put("ShootMatchTurret", ShootMatchTurret.class);
        classes.put("ShootMatchTurretBuild", ShootMatchTurret.ShootMatchTurretBuild.class);
        classes.put("MultiTractorBeamTurret", MultiTractorBeamTurret.class);
        classes.put("MultiTractorBeamBuild", MultiTractorBeamTurret.MultiTractorBeamBuild.class);
        classes.put("MinigunTurret", MinigunTurret.class);
        classes.put("MinigunTurretBuild", MinigunTurret.MinigunTurretBuild.class);
        classes.put("PlatformTurret", PlatformTurret.class);
        classes.put("PlatformTurretBuild", PlatformTurret.PlatformTurretBuild.class);
        classes.put("EruptorTurret", EruptorTurret.class);
        classes.put("EruptorTurretBuild", EruptorTurret.EruptorTurretBuild.class);
        classes.put("TeslaTurret", TeslaTurret.class);
        classes.put("TeslaTurretBuild", TeslaTurret.TeslaTurretBuild.class);
        classes.put("AdaptDirectionalUnloader", AdaptDirectionalUnloader.class);
        classes.put("AdaptDirectionalUnloaderBuild", AdaptDirectionalUnloader.AdaptDirectionalUnloaderBuild.class);
        classes.put("BeltConveyor", BeltConveyor.class);
        classes.put("BeltConveyorBuild", BeltConveyor.BeltConveyorBuild.class);
        classes.put("BeltStackConveyor", BeltStackConveyor.class);
        classes.put("BeltStackConveyorBuild", BeltStackConveyor.BeltStackConveyorBuild.class);
        classes.put("CoveredConveyor", CoveredConveyor.class);
        classes.put("CoveredConveyorBuild", CoveredConveyor.CoveredConveyorBuild.class);
        classes.put("TubeConveyor", TubeConveyor.class);
        classes.put("TubeConveyorBuild", TubeConveyor.TubeConveyorBuild.class);
        classes.put("TubeDistributor", TubeDistributor.class);
        classes.put("TubeDistributorBuild", TubeDistributor.TubeDistributorBuild.class);
        classes.put("DuctJunction", DuctJunction.class);
        classes.put("DuctJunctionBuild", DuctJunction.DuctJunctionBuild.class);
        classes.put("InvertedJunction", InvertedJunction.class);
        classes.put("InvertedJunctionBuild", InvertedJunction.InvertedJunctionBuild.class);
        classes.put("MultiInvertedJunction", MultiInvertedJunction.class);
        classes.put("MultiInvertedJunctionBuild", MultiInvertedJunction.MultiInvertedJunctionBuild.class);
        classes.put("MultiJunction", MultiJunction.class);
        classes.put("MultiJunctionBuild", MultiJunction.MultiJunctionBuild.class);
        classes.put("MultiRouter", MultiRouter.class);
        classes.put("MultiRouterBuild", MultiRouter.MultiRouterBuild.class);
        classes.put("MultiSorter", MultiSorter.class);
        classes.put("MultiSorterBuild", MultiSorter.MultiSorterBuild.class);
        classes.put("TubeItemBridge", TubeItemBridge.class);
        classes.put("TubeItemBridgeBuild", TubeItemBridge.TubeItemBridgeBuild.class);
        classes.put("NodeBridge", NodeBridge.class);
        classes.put("NodeBridgeBuild", NodeBridge.NodeBridgeBuild.class);
        classes.put("StackHelper", StackHelper.class);
        classes.put("StackHelperBuild", StackHelper.StackHelperBuild.class);
        classes.put("StackBridge", StackBridge.class);
        classes.put("StackBridgeBuild", StackBridge.StackBridgeBuild.class);
        classes.put("TubeLiquidBridge", TubeLiquidBridge.class);
        classes.put("TubeLiquidBridgeBuild", TubeLiquidBridge.TubeLiquidBridgeBuild.class);
        classes.put("FuelHeater", FuelHeater.class);
        classes.put("FuelHeaterBuild", FuelHeater.FuelHeaterBuild.class);
        classes.put("ThermalHeater", ThermalHeater.class);
        classes.put("ThermalHeaterBuild", ThermalHeater.ThermalHeaterBuild.class);
        classes.put("SortLiquidRouter", SortLiquidRouter.class);
        classes.put("SortLiquidRouterBuild", SortLiquidRouter.SortLiquidRouterBuild.class);
        classes.put("BeltConduit", BeltConduit.class);
        classes.put("BeltConduitBuild", BeltConduit.BeltConduitBuild.class);
        classes.put("LiquidOverflowValve", LiquidOverflowValve.class);
        classes.put("LiquidOverfloatValveBuild", LiquidOverflowValve.LiquidOverfloatValveBuild.class);
        classes.put("LiquidUnloader", LiquidUnloader.class);
        classes.put("LiquidUnloaderBuild", LiquidUnloader.LiquidUnloaderBuild.class);
        classes.put("LiquidDirectionalUnloader", LiquidDirectionalUnloader.class);
        classes.put("LiquidDirectionalUnloaderBuild", LiquidDirectionalUnloader.LiquidDirectionalUnloaderBuild.class);
        classes.put("LiquidMassDriver", LiquidMassDriver.class);
        classes.put("LiquidMassDriverBuild", LiquidMassDriver.LiquidMassDriverBuild.class);
        classes.put("CopyMemoryBlock", CopyMemoryBlock.class);
        classes.put("CopyMemoryBuild", CopyMemoryBlock.CopyMemoryBuild.class);
        classes.put("ProcessorCooler", ProcessorCooler.class);
        classes.put("ProcessorCoolerBuild", ProcessorCooler.ProcessorCoolerBuild.class);
        classes.put("ProcessorFan", ProcessorFan.class);
        classes.put("ProcessorFanBuild", ProcessorFan.ProcessorFanBuild.class);
        classes.put("PayloadJunction", PayloadJunction.class);
        classes.put("PayloadJunctionBuild", PayloadJunction.PayloadJunctionBuild.class);
        classes.put("PayloadRail", PayloadRail.class);
        classes.put("PayloadRailBuild", PayloadRail.PayloadRailBuild.class);
        classes.put("DerivativeUnitFactory", DerivativeUnitFactory.class);
        classes.put("DerivativeUnitFactoryBuild", DerivativeUnitFactory.DerivativeUnitFactoryBuild.class);
        classes.put("AdaptPayloadSource", AdaptPayloadSource.class);
        classes.put("AdaptPayloadSourceBuild", AdaptPayloadSource.AdaptPayloadSourceBuild.class);
        classes.put("PowerAnalyzer", PowerAnalyzer.class);
        classes.put("PowerAnalyzerBuild", PowerAnalyzer.PowerAnalyzerBuild.class);
        classes.put("BeamDiode", BeamDiode.class);
        classes.put("BeamDiodeBuild", BeamDiode.BeamDiodeBuild.class);
        classes.put("SmartBeamNode", SmartBeamNode.class);
        classes.put("SmartBeamNodeBuild", SmartBeamNode.SmartBeamNodeBuild.class);
        classes.put("SmartPowerNode", SmartPowerNode.class);
        classes.put("SmartPowerNodeBuild", SmartPowerNode.SmartPowerNodeBuild.class);
        classes.put("SpaceGenerator", SpaceGenerator.class);
        classes.put("SpaceGeneratorBuild", SpaceGenerator.SpaceGeneratorBuild.class);
        classes.put("HyperGenerator", HyperGenerator.class);
        classes.put("HyperGeneratorBuild", HyperGenerator.HyperGeneratorBuild.class);
        classes.put("GeneratorCrafter", GeneratorCrafter.class);
        classes.put("GeneratorCrafterBuild", GeneratorCrafter.GeneratorCrafterBuild.class);
        classes.put("AttributeGenerator", AttributeGenerator.class);
        classes.put("AttributeGeneratorBuild", AttributeGenerator.AttributeGeneratorBuild.class);
        classes.put("AccelerationCrafter", AccelerationCrafter.class);
        classes.put("AcceleratingCrafterBuild", AccelerationCrafter.AcceleratingCrafterBuild.class);
        classes.put("MultiCrafter", MultiCrafter.class);
        classes.put("MultiCrafterBuild", MultiCrafter.MultiCrafterBuild.class);
        classes.put("Recipe", MultiCrafter.Recipe.class);
        classes.put("SporeFarm", SporeFarm.class);
        classes.put("SporeFarmBuild", SporeFarm.SporeFarmBuild.class);
        classes.put("DrawerDrill", DrawerDrill.class);
        classes.put("DrawerDrillBuild", DrawerDrill.DrawerDrillBuild.class);
        classes.put("LaserBeamDrill", LaserBeamDrill.class);
        classes.put("LaserBeamDrillBuild", LaserBeamDrill.LaserBeamDrillBuild.class);
        classes.put("RotatorDrill", RotatorDrill.class);
        classes.put("RotatorDrillBuild", RotatorDrill.RotatorDrillBuild.class);
        classes.put("ImplosionDrill", ImplosionDrill.class);
        classes.put("ImplosionDrillBuild", ImplosionDrill.ImplosionDrillBuild.class);
        classes.put("HammerDrill", HammerDrill.class);
        classes.put("HammerDrillBuild", HammerDrill.HammerDrillBuild.class);
        classes.put("SpeedModule", SpeedModule.class);
        classes.put("SpeedModuleBuild", SpeedModule.SpeedModuleBuild.class);
        classes.put("RefineModule", RefineModule.class);
        classes.put("RefineModuleBuild", RefineModule.RefineModuleBuild.class);
        classes.put("DeliveryModule", DeliveryModule.class);
        classes.put("DeliveryModuleBuild", DeliveryModule.DeliveryModuleBuild.class);
        classes.put("MinerPoint", MinerPoint.class);
        classes.put("MinerPointBuild", MinerPoint.MinerPointBuild.class);
        classes.put("AdaptUnloader", AdaptUnloader.class);
        classes.put("AdaptUnloaderBuild", AdaptUnloader.AdaptUnloaderBuild.class);
        classes.put("CoreStorageBlock", CoreStorageBlock.class);
        classes.put("CoreStorageBuild", CoreStorageBlock.CoreStorageBuild.class);
        //consume
        classes.put("ConsumeItemDynamicF", ConsumeItemDynamicF.class);
        classes.put("ConsumeLiquidDynamicF", ConsumeLiquidDynamicF.class);
        classes.put("ConsumerPowerF", ConsumerPowerF.class);
        classes.put("ConsumeShowStat", ConsumeShowStat.class);
        //part
        classes.put("AimPart", AimPart.class);
        classes.put("BowHalo", BowHalo.class);
        classes.put("PartBow", PartBow.class);
        classes.put("RunningLight", RunningLight.class);
        classes.put("ArcCharge", ArcCharge.class);
        //draw
        classes.put("DrawFactories", DrawFactories.class);
        classes.put("DrawPowerLight", DrawPowerLight.class);
        classes.put("DrawPrinter", DrawPrinter.class);
        classes.put("DrawRotator", DrawRotator.class);
        classes.put("DrawScanLine", DrawScanLine.class);
        classes.put("DrawAtlasFrames", DrawAtlasFrames.class);
        classes.put("DrawSpecConstruct", DrawSpecConstruct.class);
        classes.put("DrawInvertedJunction", InvertedJunction.DrawInvertedJunction.class);
        classes.put("DrawTubeDistributor", TubeDistributor.DrawTubeDistributor.class);
        classes.put("DrawNodeBridge", NodeBridge.DrawNodeBridge.class);
        classes.put("DrawLiquidMassDriver", LiquidMassDriver.DrawLiquidMassDriver.class);
        classes.put("DrawMinigun", MinigunTurret.DrawMinigun.class);
    }
}
