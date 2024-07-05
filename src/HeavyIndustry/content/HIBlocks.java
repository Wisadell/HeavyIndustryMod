package HeavyIndustry.content;

import HeavyIndustry.world.blocks.drill.LaserBeamDrill;
import HeavyIndustry.world.blocks.distribution.InvertedJunction;
import HeavyIndustry.world.blocks.distribution.StackHelper;
import HeavyIndustry.world.blocks.distribution.HighEnergyNode;
import HeavyIndustry.world.blocks.liquid.SortLiquidRouter;
import HeavyIndustry.world.blocks.production.LiquidFuelHeater;
import HeavyIndustry.world.blocks.production.ThermalHeater;
import HeavyIndustry.world.blocks.production.GeneratorCrafter;
import HeavyIndustry.world.blocks.storage.AdaptDirectionalUnloader;
import HeavyIndustry.world.blocks.storage.BeStationedCoreBlock;
import HeavyIndustry.world.blocks.turret.MinigunTurret;
import HeavyIndustry.world.blocks.turret.MultiBulletTurret;
import HeavyIndustry.world.blocks.unit.DerivativeUnitFactory;
import HeavyIndustry.world.blocks.logic.CopyMemoryBlock;
import HeavyIndustry.world.blocks.wall.RegenWall;
import HeavyIndustry.world.entity.bullet.CtrlMissileBulletType;
import HeavyIndustry.world.entity.bullet.FireWorkBulletType;
import HeavyIndustry.world.entity.bullet.aimToPosBulletType;
import HeavyIndustry.world.drawer.DrawRotator;
import HeavyIndustry.world.drawer.DrawFunc;
import HeavyIndustry.world.drawer.frostWing;
import HeavyIndustry.world.drawer.BowHalo;
import HeavyIndustry.world.drawer.DrawScanLine;
import HeavyIndustry.world.drawer.DrawPowerLight;
import arc.Core;
import arc.graphics.Blending;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Fill;
import arc.graphics.g2d.Lines;
import arc.graphics.g2d.TextureRegion;
import arc.math.Angles;
import arc.math.Mathf;
import arc.math.Rand;
import arc.math.geom.Position;
import arc.math.geom.Vec2;
import arc.math.Interp;
import arc.util.Time;
import arc.util.Tmp;
import mindustry.Vars;
import mindustry.content.*;
import mindustry.entities.Effect;
import mindustry.entities.UnitSorts;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.*;
import mindustry.entities.part.*;
import mindustry.entities.pattern.*;
import mindustry.gen.*;
import mindustry.graphics.Drawf;
import mindustry.graphics.Layer;
import mindustry.graphics.Pal;
import mindustry.graphics.Trail;
import mindustry.type.*;
import mindustry.world.Block;
import mindustry.world.blocks.defense.AutoDoor;
import mindustry.world.blocks.defense.ForceProjector;
import mindustry.world.blocks.defense.MendProjector;
import mindustry.world.blocks.defense.Radar;
import mindustry.world.blocks.defense.Wall;
import mindustry.world.blocks.defense.turrets.*;
import mindustry.world.blocks.distribution.ArmoredConveyor;
import mindustry.world.blocks.distribution.Conveyor;
import mindustry.world.blocks.distribution.Duct;
import mindustry.world.blocks.distribution.DuctBridge;
import mindustry.world.blocks.distribution.DuctRouter;
import mindustry.world.blocks.distribution.ItemBridge;
import mindustry.world.blocks.distribution.Junction;
import mindustry.world.blocks.distribution.OverflowDuct;
import mindustry.world.blocks.distribution.Router;
import mindustry.world.blocks.distribution.StackConveyor;
import mindustry.world.blocks.environment.Floor;
import mindustry.world.blocks.environment.OreBlock;
import mindustry.world.blocks.environment.Prop;
import mindustry.world.blocks.environment.StaticWall;
import mindustry.world.blocks.environment.SteamVent;
import mindustry.world.blocks.heat.*;
import mindustry.world.blocks.liquid.ArmoredConduit;
import mindustry.world.blocks.liquid.LiquidBridge;
import mindustry.world.blocks.liquid.LiquidRouter;
import mindustry.world.blocks.power.Battery;
import mindustry.world.blocks.power.ConsumeGenerator;
import mindustry.world.blocks.power.NuclearReactor;
import mindustry.world.blocks.power.PowerNode;
import mindustry.world.blocks.production.*;
import mindustry.world.blocks.storage.StorageBlock;
import mindustry.world.blocks.storage.Unloader;
import mindustry.world.blocks.units.*;
import mindustry.world.consumers.ConsumeLiquidFlammable;
import mindustry.world.draw.*;
import mindustry.world.draw.DrawHeatRegion;
import mindustry.world.meta.*;

import static arc.graphics.g2d.Draw.alpha;
import static arc.graphics.g2d.Draw.color;

import static HeavyIndustry.HeavyIndustryMod.name;
import static mindustry.Vars.headless;
import static mindustry.type.ItemStack.with;

public class HIBlocks {
    public static Block
            //environment
            darkPanel7,darkPanel8,darkPanel9,darkPanel10,darkPanel11,darkPanelDamaged,
            stoneVent,basaltVent,snowySand,snowySandWall,arkyciteSand,arkyciteSandWall,arkyciteSandBoulder,darksandBoulder,tundra,tundraWall,
            softRareEarth,softRareEarthWall,
            oreUranium,oreChromium,
            //wall
            armoredWall,armoredWallLarge,uraniumWall,uraniumWallLarge,chromiumWall,chromiumWallLarge,chromiumDoor,chromiumDoorLarge,heavyAlloyWall,heavyAlloyWallLarge,nanoCompositeWall,nanoCompositeWallLarge,
            //drill
            largeWaterExtractor,slagExtractor,reinforcedOilExtractor,reinforcedDrill,beamDrill,
            //drill-erekir
            largeCliffCrusher,heavyPlasmaBore,
            //distribution
            invertedJunction,chromiumEfficientConveyor,chromiumArmorConveyor,chromiumConveyor,chromiumJunction,chromiumInvertedJunction,chromiumRouter,chromiumItemBridge,
            stackHelper,highEnergyItemNode,
            //distribution-erekir
            armoredDuctBridge,waveDuct,waveDuctBridge,waveDuctRouter,overflowWaveDuct,underflowWaveDuct,rapidDuctUnloader,
            //liquid
            highEnergyLiquidNode,chromiumArmorConduit,chromiumLiquidBridge,chromiumArmorLiquidTank,
            //liquid-erekir
            liquidSorter,liquidValve,smallReinforcedPump, largeReinforcedPump,
            //power
            powerNodeGiant,uraniumReactor,armoredCoatedBattery,
            //power-erekir
            liquidConsumeGenerator,
            //production
            largeKiln,largePulverizer,largeMelter,largeCryofluidMixer,largePyratiteMixer,largeBlastMixer,largeCultivator,largePlastaniumCompressor,largeSurgeSmelter,largeCoalCentrifuge,blastSiliconSmelter,
            nanocoreConstructor,nanocorePrinter,activator,highEnergyPhaseSynthesizer,energizer,largeEnergizer,highEnergyFabricFusionInstrument,uraniumSynthesizer,chromiumSynthesizer,heavyAlloySmelter,metalAnalyzer,nitrificationReactor,nitratedOilSedimentationTank,
            //production-erekir
            ventHeater,chemicalSiliconSmelter,largeElectricHeater,liquidFuelHeater,largeOxidationChamber,largeSurgeCrucible,largeCarbideCrucible,largePhaseSynthesizer,
            //defense
            mendDome,largeShieldGenerator,
            //defense-erekir
            largeRadar,
            //storage
            coreBeStationed,cargo,bin,rapidUnloader,highEnergyDirectionalUnloader,
            //unit
            titanReconstructor,experimentalUnitFactory,
            //unit-erekir
            largeUnitRepairTower,seniorAssemblerModule,
            //logic
            buffrerdMemoryCell,buffrerdMemoryBank,
            //turret
            dissipation,rocketLauncher,multipleRocketLauncher,largeRocketLauncher,rocketSilo,caelum,cloudbreaker,minigun,frost,thermoelectricIon,fiammetta,fireworks,
            //turret-erekir
            tracer,shadow;
    public static void load(){
        //environment
        darkPanel7 = new Floor("dark-panel-7"){{
            variants = 0;
        }};
        darkPanel8 = new Floor("dark-panel-8"){{
            variants = 0;
        }};
        darkPanel9 = new Floor("dark-panel-9"){{
            variants = 0;
        }};
        darkPanel10 = new Floor("dark-panel-10"){{
            variants = 0;
        }};
        darkPanel11 = new Floor("dark-panel-11"){{
            variants = 0;
        }};
        darkPanelDamaged = new Floor("dark-panel-damaged"){{
            variants = 3;
        }};
        stoneVent = new SteamVent("stone-vent"){{
            variants = 2;
            parent = blendGroup = Blocks.stone;
            attributes.set(Attribute.steam, 1f);
        }};
        basaltVent = new SteamVent("basalt-vent"){{
            variants = 2;
            parent = blendGroup = Blocks.basalt;
            attributes.set(Attribute.steam, 1f);
        }};
        snowySand = new Floor("snowy-sand"){{
            variants = 3;
            itemDrop = Items.sand;
            attributes.set(Attribute.water, 0.2f);
            attributes.set(Attribute.oil, 0.5f);
            playerUnmineable = true;
        }};
        snowySandWall = new StaticWall("snowy-sand-wall"){{
            variants = 2;
            attributes.set(Attribute.sand, 2f);
        }};
        arkyciteSand = new Floor("arkycite-sand"){{
            variants = 3;
            itemDrop = Items.sand;
            attributes.set(HIAttribute.arkycite, 1);
            playerUnmineable = true;
        }};
        arkyciteSandWall = new StaticWall("arkycite-sand-wall"){{
            variants = 2;
            attributes.set(Attribute.sand, 2f);
        }};
        arkyciteSandBoulder = new Prop("arkycite-sand-boulder"){{
            variants = 2;
        }};
        darksandBoulder = new Prop("darksand-boulder"){{
            variants = 2;
        }};
        tundra = new Floor("tundra"){{
            variants = 3;
            attributes.set(Attribute.water, 0.5f);
        }};
        tundraWall = new StaticWall("tundra-wall"){{
            variants = 2;
        }};
        softRareEarth = new Floor("soft-rare-earth"){{
            itemDrop = HIItems.rareEarth;
            playerUnmineable = true;
            variants = 3;
        }};
        softRareEarthWall = new StaticWall("soft-rare-earth-wall"){{
            variants = 2;
        }};
        oreUranium = new OreBlock("ore-uranium", HIItems.uranium){{
            variants = 3;
            oreDefault = true;
            oreThreshold = 0.89f;
            oreScale = 22;
        }};
        oreChromium = new OreBlock("ore-chromium", HIItems.chromium){{
            variants = 3;
            oreDefault = true;
            oreThreshold = 0.9f;
            oreScale = 20;
        }};
        //wall
        armoredWall = new Wall("armored-wall"){{
            requirements(Category.defense, with(Items.copper, 5, Items.lead, 3, Items.graphite, 2));
            size = 1;
            health = 360;
            armor = 5;
        }};
        armoredWallLarge = new Wall("armored-wall-large"){{
            requirements(Category.defense, with(Items.copper, 20, Items.lead, 12, Items.graphite, 8));
            size = 2;
            health = 1440;
            armor = 5;
        }};
        uraniumWall = new Wall("uranium-wall"){{
            requirements(Category.defense, with(HIItems.uranium, 6));
            size = 1;
            health = 1680;
            armor = 24;
            absorbLasers = true;
        }};
        uraniumWallLarge = new Wall("uranium-wall-large"){{
            requirements(Category.defense, with(HIItems.uranium, 24));
            size = 2;
            health = 6720;
            armor = 24;
            absorbLasers = true;
        }};
        chromiumWall = new Wall("chromium-wall"){{
            requirements(Category.defense, with(HIItems.chromium, 6));
            size = 1;
            health = 1980;
            armor = 36;
            absorbLasers = true;
        }};
        chromiumWallLarge = new Wall("chromium-wall-large"){{
            requirements(Category.defense, with(HIItems.chromium, 24));
            size = 2;
            health = 7920;
            armor = 36;
            absorbLasers = true;
        }};
        chromiumDoor = new AutoDoor("chromium-door"){{
            requirements(Category.defense, with(HIItems.chromium, 6, Items.silicon, 4));
            size = 1;
            health = 1980;
            armor = 36;
            absorbLasers = true;
        }};
        chromiumDoorLarge = new AutoDoor("chromium-door-large"){{
            requirements(Category.defense, with(HIItems.chromium, 24, Items.silicon, 16));
            size = 2;
            health = 7920;
            armor = 36;
            absorbLasers = true;
        }};
        heavyAlloyWall = new Wall("heavy-alloy-wall"){{
            requirements(Category.defense, with(HIItems.heavyAlloy, 6, Items.metaglass, 3, Items.plastanium, 4));
            size = 1;
            health = 2680;
            armor = 48;
            absorbLasers = true;
            insulated = true;
        }};
        heavyAlloyWallLarge = new Wall("heavy-alloy-wall-large"){{
            requirements(Category.defense, with(HIItems.heavyAlloy, 24, Items.metaglass, 12, Items.plastanium, 16));
            size = 2;
            health = 10720;
            armor = 48;
            absorbLasers = true;
            insulated = true;
        }};
        nanoCompositeWall = new RegenWall("nano-composite-wall"){{
            requirements(Category.defense, with(HIItems.nanocore, 3, HIItems.highEnergyFabric, 2, HIItems.heavyAlloy, 6, Items.plastanium, 2));
            size = 1;
            health = 2240;
            armor = 42;
            absorbLasers = true;
            insulated = true;
            healPercent = 5f / 60f;
            optionalMultiplier = 2f;
            chanceHeal = 0.15f;
            chanceDeflect = 12f;
            regenPercent = 0.5f;
        }};
        nanoCompositeWallLarge = new RegenWall("nano-composite-wall-large"){{
            requirements(Category.defense, with(HIItems.nanocore, 12, HIItems.highEnergyFabric, 8, HIItems.heavyAlloy, 24, Items.plastanium, 8));
            size = 2;
            health = 8960;
            armor = 42;
            absorbLasers = true;
            insulated = true;
            healPercent = 5f / 60f;
            optionalMultiplier = 2f;
            chanceHeal = 0.15f;
            chanceDeflect = 12f;
            regenPercent = 0.5f;
        }};
        //drill
        largeWaterExtractor = new SolidPump("large-water-extractor"){{
            requirements(Category.production, with(Items.lead, 60, Items.titanium, 40, Items.thorium, 20, Items.graphite, 80, Items.metaglass, 50));
            result = Liquids.water;
            pumpAmount = 0.33f;
            size = 3;
            liquidCapacity = 80f;
            rotateSpeed = 1.6f;
            attribute = Attribute.water;
            envRequired |= Env.groundWater;
            consumePower(2.5f);
        }};
        slagExtractor = new SolidPump("slag-extractor"){{
            requirements(Category.production, with(Items.graphite, 60, Items.titanium, 35, Items.metaglass, 80, Items.silicon, 80, Items.thorium, 45));
            size = 3;
            liquidCapacity = 30;
            result = Liquids.slag;
            attribute = Attribute.heat;
            updateEffect = Fx.redgeneratespark;
            rotateSpeed = 8.6f;
            baseEfficiency = 0;
            pumpAmount = 0.9f;
            envRequired |= Env.none;
            consumePower(7);
        }};
        reinforcedOilExtractor = new Fracker("reinforced-oil-extractor"){{
            requirements(Category.production, with(Items.graphite, 175, Items.lead, 115, HIItems.chromium, 135, Items.silicon, 75));
            size = 3;
            hasItems = true;
            itemCapacity = 10;
            liquidCapacity = 30;
            result = Liquids.oil;
            attribute = Attribute.oil;
            updateEffect = Fx.pulverize;
            updateEffectChance = 0.05f;
            baseEfficiency = 0;
            itemUseTime = 30;
            pumpAmount = 0.9f;
            consumePower(5);
            consumeItem(Items.sand);
            consumeLiquid(Liquids.water, 0.3f);
        }};
        reinforcedDrill = new Drill("reinforced-drill"){{
            requirements(Category.production, with(Items.titanium, 110, Items.graphite, 80, Items.silicon, 60, Items.thorium, 50, Items.plastanium, 40));
            size = 3;
            health = 590;
            armor = 3;
            hasPower = true;
            tier = 8;
            rotateSpeed = 1.5f;
            warmupSpeed = 0.06f;
            itemCapacity = 10;
            drillTime = 200;
            updateEffect = Fx.mineBig;
            hardnessDrillMultiplier = 12;
            consumePower(5.5f);
            consumeLiquid(Liquids.water, 0.1f).boost();
        }};
        beamDrill = new LaserBeamDrill("beam-drill"){{
            requirements(Category.production, with(Items.lead, 160, Items.silicon, 120,  HIItems.chromium, 60, HIItems.nanocore, 35,HIItems.highEnergyFabric, 25));
            size = 4;
            health = 960;
            tier = 9;
            drillTime = 150f;
            warmupSpeed = 0.02f;
            drawMineItem = false;
            consumePower(6f);
            consumeLiquid(Liquids.water, 0.1f).boost();
        }};
        //drill-erekir
        largeCliffCrusher = new WallCrafter("large-cliff-crusher"){{
            requirements(Category.production, with(Items.graphite, 80, Items.silicon, 30, Items.beryllium, 60, Items.tungsten, 25));
            health = 860;
            size = 3;
            itemCapacity = 20;
            drillTime = 75;
            attribute = Attribute.sand;
            output = Items.sand;
            fogRadius = 3;
            ambientSound = Sounds.drill;
            ambientSoundVolume = 0.1f;
            consumePower(54f / 60f);
        }};
        heavyPlasmaBore = new BeamDrill("heavy-plasma-bore"){{
            requirements(Category.production, with(Items.silicon, 300, Items.oxide, 150, Items.beryllium, 350, Items.tungsten, 250, Items.carbide, 100));
            squareSprite = false;
            itemCapacity = 30;
            liquidCapacity = 20f;
            health = 3220;
            size = 4;
            range = 7;
            tier = 6;
            fogRadius = 5;
            drillTime = 63f;
            drillMultipliers.put(Items.beryllium, 1.5f);
            drillMultipliers.put(Items.graphite, 1.5f);
            consumePower(390f / 60f);
            consumeLiquid(Liquids.hydrogen, 1.5f / 60f);
            consumeLiquid(Liquids.nitrogen, 7.5f / 60f).boost();
        }};
        //distribution
        invertedJunction = new InvertedJunction("inverted-junction"){{
            requirements(Category.distribution, with(Items.copper, 2));
            placeSprite = "junction";
            sync = true;
            speed = 26f;
            capacity = 6;
            configurable = true;
            buildCostMultiplier = 6f;
        }};
        chromiumEfficientConveyor = new Conveyor("chromium-efficient-conveyor"){{
            requirements(Category.distribution, with(Items.copper, 1, Items.lead, 1, HIItems.chromium, 1));
            health = 240;
            armor = 3;
            speed = 0.18f;
            displayedSpeed = 18;
        }};
        chromiumArmorConveyor = new ArmoredConveyor("chromium-armor-conveyor"){{
            requirements(Category.distribution, with(Items.metaglass, 1, Items.thorium, 1, Items.plastanium, 1, HIItems.chromium, 1));
            health = 560;
            armor = 5;
            speed = 0.18f;
            displayedSpeed = 18;
        }};
        chromiumConveyor = new StackConveyor("chromium-conveyor"){{
            requirements(Category.distribution, with(Items.graphite, 1, Items.silicon, 1, Items.plastanium, 1, HIItems.chromium, 1));
            health = 380;
            armor = 4;
            speed = 0.125f;
            itemCapacity = 20;
        }};
        chromiumRouter = new Router("chromium-router"){{
            requirements(Category.distribution, with(Items.lead, 2, HIItems.chromium, 2));
            health = 420;
            armor = 4;
            speed = 2;
            itemCapacity = 20;
        }};
        chromiumJunction = new Junction("chromium-junction"){{
            requirements(Category.distribution, with(Items.copper, 2, HIItems.chromium, 2));
            health = 420;
            armor = 4;
            speed = 12;
            capacity = 12;
            itemCapacity = 12;
        }};
        chromiumInvertedJunction = new InvertedJunction("chromium-inverted-junction"){{
            requirements(Category.distribution, with(Items.copper, 2, HIItems.chromium, 2));
            health = 420;
            armor = 4;
            placeSprite = name("chromium-junction");
            sync = true;
            speed = 12;
            capacity = 12;
            itemCapacity = 12;
            configurable = true;
        }};
        chromiumItemBridge = new ItemBridge("chromium-item-bridge"){{
            requirements(Category.distribution, with(Items.graphite, 1, Items.silicon, 1, Items.plastanium, 1, HIItems.chromium, 1));
            health = 420;
            armor = 4;
            hasPower = false;
            transportTime = 3f;
            range = 8;
            arrowSpacing = 6;
            bridgeWidth = 8;
        }};
        stackHelper = new StackHelper("stack-helper"){{
            requirements(Category.distribution, with(Items.silicon, 20, Items.phaseFabric, 10, Items.plastanium, 20));
            size = 1;
            health = 60;
        }};
        highEnergyItemNode = new HighEnergyNode("high-energy-item-node"){{
            requirements(Category.distribution, with( Items.lead, 30, HIItems.chromium , 10, Items.silicon, 15, HIItems.highEnergyFabric, 10));
            size = 1;
            health = 320;
            armor = 3;
            squareSprite = false;
            range = 25;
            envEnabled |= Env.space;
            transportTime = 1f;
            consumePower(0.5f);
        }};
        //distribution-erekir
        armoredDuctBridge = new DuctBridge("armored-duct-bridge"){{
            requirements(Category.distribution, with(Items.beryllium, 20, Items.tungsten, 10));
            health = 140;
            range = 6;
            speed = 4;
            buildCostMultiplier = 2;
        }};
        waveDuct = new Duct("wave-duct"){{
            requirements(Category.distribution, with(Items.beryllium, 2, Items.tungsten, 1, Items.surgeAlloy, 1));
            health = 240;
            speed = 2;
        }};
        waveDuctBridge = new DuctBridge("wave-duct-bridge"){{
            requirements(Category.distribution, with(Items.beryllium, 20, Items.tungsten, 10, Items.surgeAlloy, 5));
            health = 240;
            range = 8;
            speed = 2;
            buildCostMultiplier = 2;
        }};
        waveDuctRouter = new DuctRouter("wave-duct-router"){{
            requirements(Category.distribution, with(Items.beryllium, 15, Items.tungsten, 10, Items.surgeAlloy, 5));
            health = 240;
            speed = 2f;
            regionRotated1 = 1;
            solid = false;
        }};
        overflowWaveDuct = new OverflowDuct("overflow-wave-duct"){{
            requirements(Category.distribution, with(Items.graphite, 8, Items.beryllium, 8, Items.tungsten, 6, Items.surgeAlloy, 4));
            health = 240;
            speed = 2f;
            solid = false;
            researchCostMultiplier = 1.5f;
        }};
        underflowWaveDuct = new OverflowDuct("underflow-wave-duct"){{
            requirements(Category.distribution, with(Items.graphite, 8, Items.beryllium, 8, Items.tungsten, 6, Items.surgeAlloy, 4));
            health = 240;
            speed = 2f;
            solid = false;
            invert = true;
            researchCostMultiplier = 1.5f;
        }};
        rapidDuctUnloader = new AdaptDirectionalUnloader("rapid-duct-unloader"){{
            requirements(Category.distribution, with(Items.graphite, 25, Items.silicon, 30, Items.tungsten, 20, Items.oxide, 15));
            health = 240;
            speed = 30.0f;
            solid = false;
            underBullets = true;
            squareSprite = false;
            regionRotated1 = 1;
        }};
        //liquid
        chromiumArmorConduit = new ArmoredConduit("chromium-armor-conduit"){{
            requirements(Category.liquid, with(Items.metaglass, 1, HIItems.chromium, 1));
            health = 420;
            armor = 4;
            liquidCapacity = 32;
            liquidPressure = 3.2f;
        }};
        chromiumLiquidBridge = new LiquidBridge("chromium-liquid-bridge"){{
            requirements(Category.liquid, with(Items.metaglass, 10, HIItems.chromium, 6));
            health = 480;
            armor = 5;
            hasPower = false;
            range = 8;
            arrowSpacing = 6;
            bridgeWidth = 8;
        }};
        chromiumArmorLiquidTank = new LiquidRouter("chromium-armor-liquid-tank"){{
            requirements(Category.liquid, with(Items.metaglass, 40, HIItems.chromium, 16));
            size = 3;
            health = 2500;
            armor = 8;
            liquidCapacity = 2500;
            underBullets = true;
        }};
        highEnergyLiquidNode = new HighEnergyNode("high-energy-liquid-node"){{
            requirements(Category.liquid, with(Items.metaglass, 20, HIItems.chromium , 10, Items.silicon, 15, HIItems.highEnergyFabric, 10));
            size = 1;
            health = 320;
            armor = 3;
            squareSprite = false;
            hasItems = false;
            hasLiquids = true;
            liquidCapacity = 32;
            canOverdrive = false;
            outputsLiquid = true;
            range = 25;
            consumePower(0.5f);
        }};
        //liquid-erekir
        liquidSorter = new SortLiquidRouter("liquid-sorter"){{
            requirements(Category.liquid, with(Items.silicon, 8, Items.beryllium, 4));
            liquidCapacity = 40f;
            liquidPadding = 3f / 4f;
            researchCostMultiplier = 3;
            underBullets = true;
            rotate = false;
            solid = false;
            squareSprite = false;
        }};
        liquidValve = new SortLiquidRouter("liquid-valve"){{
            requirements(Category.liquid, with(Items.graphite, 6, Items.beryllium, 6));
            liquidCapacity = 40f;
            liquidPadding = 3f / 4f;
            researchCostMultiplier = 3;
            underBullets = true;
            configurable = false;
            solid = false;
            squareSprite = false;
        }};
        smallReinforcedPump = new Pump("small-reinforced-pump"){{
            requirements(Category.liquid, with(Items.graphite, 35, Items.beryllium, 40));
            pumpAmount = 15f / 60f;
            liquidCapacity = 40f;
            size = 1;
            squareSprite = false;
        }};
        largeReinforcedPump = new Pump("large-reinforced-pump"){{
            requirements(Category.liquid, with(Items.beryllium, 105, Items.thorium, 65, Items.silicon, 50, Items.tungsten, 75));
            consumeLiquid(Liquids.hydrogen, 3f / 60f);
            pumpAmount = 240f / 60f / 9f;
            liquidCapacity = 360f;
            size = 3;
            squareSprite = false;
        }};
        //power
        powerNodeGiant = new PowerNode("power-node-giant"){{
            requirements(Category.power, with(Items.lead, 45, Items.titanium, 35, Items.thorium, 15, Items.silicon, 25));
            size = 3;
            health = 370;
            maxNodes = 25;
            laserRange = 22.5f;
        }};
        uraniumReactor = new NuclearReactor("uranium-reactor"){{
            requirements(Category.power, with(Items.lead, 400, Items.metaglass, 120, Items.graphite, 350, Items.silicon, 300, HIItems.uranium, 100));
            size = 3;
            health = 1450;
            outputsPower = true;
            powerProduction = 62.5f;
            itemDuration = 360;
            itemCapacity = 30;
            liquidCapacity = 60;
            fuelItem = HIItems.uranium;
            coolantPower = 0.45f;
            heating = 0.06f;
            lightColor = Color.white;
            explosionShake = 9;
            explosionShakeDuration = 120;
            explosionRadius = 35;
            explosionDamage = 7200;
            explodeSound = HISounds.dbz1;
            explodeEffect = new MultiEffect(new WaveEffect(){{
                lifetime = 20;
                sizeFrom = strokeTo = 0f;
                sizeTo = 320f;
                strokeFrom = 30f;
                colorFrom = HIPal.highEnergyYellow;
                colorTo = Color.white;
            }}, new ParticleEffect(){{
                particles = 35;
                interp = Interp.pow10Out;
                sizeInterp = Interp.pow5In;
                sizeFrom = 30f;
                sizeTo = baseLength = 0f;
                length = 290f;
                lifetime = 300f;
                colorFrom = colorTo = HIPal.uraniumGrey;
            }}, new ParticleEffect(){{
                particles = 40;
                interp = Interp.pow10Out;
                sizeInterp = Interp.pow5In;
                sizeFrom = 20f;
                sizeTo = baseLength = 0f;
                length = 300f;
                lifetime = 350f;
                colorFrom = colorTo = HIPal.uraniumGrey;
            }}, new ParticleEffect(){{
                particles = 25;
                interp = Interp.pow10Out;
                sizeInterp = Interp.pow3In;
                sizeFrom = 3f;
                sizeTo = lenTo = 0;
                lenFrom = 150f;
                length = 220f;
                baseLength = 60f;
                lifetime = 60f;
                colorFrom = colorTo = HIPal.uraniumGrey;
            }});
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawLiquidRegion(Liquids.cryofluid), new DrawDefault(), new DrawGlowRegion());
            ambientSound = Sounds.hum;
            ambientSoundVolume = 0.24f;
            consumeItem(HIItems.uranium);
            consumeLiquid(Liquids.cryofluid, heating / coolantPower).update(false);
        }};
        armoredCoatedBattery = new Battery("armored-coated-battery"){{
            requirements(Category.power, with(Items.lead, 150,Items.silicon, 180, Items.plastanium, 120, HIItems.chromium, 100, HIItems.highEnergyFabric, 30));
            size = 4;
            health = 4200;
            armor = 16;
            consumePowerBuffered(625000);
            baseExplosiveness = 15;
            drawer = new DrawMulti(new DrawDefault(), new DrawPower(){{
                emptyLightColor = Color.valueOf("18473f");
                fullLightColor = Color.valueOf("ffd197");
            }}, new DrawRegion("-top"));
        }};
        //power-erekir
        liquidConsumeGenerator = new ConsumeGenerator("liquid-generator"){{
            requirements(Category.power, with(Items.beryllium, 180, Items.graphite, 120, Items.silicon, 115, Items.tungsten, 80, Items.oxide, 120));
            squareSprite = false;
            size = 3;
            powerProduction = 660f / 60f;
            drawer = new DrawMulti(new DrawDefault(), new DrawWarmupRegion(){{
                sinMag = 0;
                sinScl = 1;
            }}, new DrawLiquidRegion());
            consume(new ConsumeLiquidFlammable(0.4f, 0.2f));
            hasLiquids = true;
            generateEffect = new RadialEffect(new Effect(160f, e -> {
                color(Color.valueOf("6e685a"));
                alpha(0.6f);
                Rand rand = Fx.rand;
                Vec2 v = Fx.v;
                rand.setSeed(e.id);
                for(int i = 0; i < 3; i++){
                    float len = rand.random(6f), rot = rand.range(40f) + e.rotation;
                    e.scaled(e.lifetime * rand.random(0.3f, 1f), b -> {
                        v.trns(rot, len * b.finpow());
                        Fill.circle(e.x + v.x, e.y + v.y, 2f * b.fslope() + 0.2f);
                    });
                }
            }), 4, 90, 8f);
            effectChance = 0.2f;
        }};
        //production
        largeKiln = new GenericCrafter("large-kiln"){{
            requirements(Category.crafting, with(Items.lead, 85, Items.graphite, 55, Items.titanium, 30, Items.silicon, 65));
            size = 3;
            itemCapacity = 20;
            craftTime = 45;
            outputItem = new ItemStack(Items.metaglass, 5);
            craftEffect = Fx.smeltsmoke;
            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.21f;
            consumePower(1.8f);
            consumeItems(ItemStack.with(Items.lead, 4, Items.sand, 4));
        }};
        largePulverizer = new GenericCrafter("large-pulverizer"){{
            requirements(Category.crafting, with(Items.copper, 25, Items.lead, 25, Items.graphite, 15, Items.titanium, 10));
            size = 2;
            health = 160;
            itemCapacity = 20;
            outputItem = new ItemStack(Items.sand, 3);
            craftTime = 35f;
            updateEffect = Fx.pulverizeSmall;
            craftEffect = HIFx.hugeSmokeGray;
            updateEffect = new Effect(80f, e -> {
                Fx.rand.setSeed(e.id);
                Draw.color(Color.lightGray, Color.gray, e.fin());
                Angles.randLenVectors(e.id, 4, 2.0F + 12.0F * e.fin(Interp.pow3Out), (x, y) -> Fill.circle(e.x + x, e.y + y, e.fout() * Fx.rand.random(1, 2.5f)));
            }).layer(Layer.blockOver + 1);
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawFrames(), new DrawArcSmelt(), new DrawDefault());
            ambientSound = Sounds.grinding;
            ambientSoundVolume = 0.12f;
            consumeItem(Items.scrap, 2);
            consumePower(1.5f);
        }};
        largeMelter = new GenericCrafter("large-melter"){{
            requirements(Category.crafting, with(Items.lead, 60, Items.graphite, 45, Items.silicon, 30, Items.titanium, 20));
            size = 2;
            hasPower = true;
            hasLiquids = true;
            itemCapacity = 20;
            liquidCapacity = 30;
            craftTime = 12;
            outputLiquid = new LiquidStack(Liquids.slag, 36f / 60f);
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawLiquidTile(), new DrawDefault());
            consumePower(1.5f);
            consumeItem(Items.scrap, 2);
        }};
        largeCryofluidMixer = new GenericCrafter("large-cryofluid-mixer"){{
            requirements(Category.crafting, with(Items.lead, 120, Items.silicon, 60, Items.titanium, 150, Items.thorium, 110));
            outputLiquid = new LiquidStack(Liquids.cryofluid, 36f / 60f);
            size = 3;
            hasPower = true;
            hasItems = true;
            hasLiquids = true;
            rotate = false;
            solid = true;
            outputsLiquid = true;
            envEnabled = Env.any;
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawLiquidTile(Liquids.water), new DrawLiquidTile(Liquids.cryofluid){{
                drawLiquidLight = true;
            }}, new DrawDefault());
            liquidCapacity = 54f;
            craftTime = 50;
            lightLiquid = Liquids.cryofluid;
            consumePower(2f);
            consumeItem(Items.titanium);
            consumeLiquid(Liquids.water, 36f / 60f);
        }};
        largePyratiteMixer = new GenericCrafter("large-pyratite-mixer"){{
            requirements(Category.crafting, with(Items.copper, 100, Items.lead, 50, Items.titanium, 25, Items.silicon, 20));
            hasItems = true;
            hasPower = true;
            outputItem = new ItemStack(Items.pyratite, 3);
            envEnabled |= Env.space;
            size = 3;
            consumePower(0.5f);
            consumeItems(with(Items.coal, 3, Items.lead, 4, Items.sand, 5));
        }};
        largeBlastMixer = new GenericCrafter("large-blast-mixer"){{
            requirements(Category.crafting, with(Items.lead, 60, Items.titanium, 40, Items.silicon, 20));
            hasItems = true;
            hasPower = true;
            outputItem = new ItemStack(Items.blastCompound, 3);
            size = 3;
            envEnabled |= Env.space;
            consumeItems(with(Items.pyratite, 3, Items.sporePod, 3));
            consumePower(1f);
        }};
        largeCultivator = new AttributeCrafter("large-cultivator"){{
            requirements(Category.production, with(Items.lead, 40, Items.titanium, 30, Items.plastanium, 20, Items.silicon, 30, Items.metaglass, 60));
            outputItem = new ItemStack(Items.sporePod, 1);
            craftTime = 20;
            health = 360;
            size = 3;
            hasLiquids = true;
            hasPower = true;
            hasItems = true;
            craftEffect = Fx.none;
            envRequired |= Env.spores;
            attribute = Attribute.spores;
            legacyReadWarmup = true;
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawLiquidTile(Liquids.water), new DrawCultivator(), new DrawDefault());
            maxBoost = 3f;
            consumePower(3f);
            consumeLiquid(Liquids.water, 36f / 60f);
        }};
        largePlastaniumCompressor = new GenericCrafter("large-plastanium-compressor"){{
            requirements(Category.crafting, with(Items.silicon, 150, Items.lead, 220, Items.graphite, 120, Items.titanium, 150, Items.thorium, 80));
            hasItems = hasPower = hasLiquids = true;
            itemCapacity = 20;
            liquidCapacity = 80f;
            craftTime = 60f;
            outputItem = new ItemStack(Items.plastanium, 3);
            size = 3;
            health = 640;
            craftEffect = Fx.formsmoke;
            updateEffect = Fx.plasticburn;
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawLiquidTile(Liquids.oil, 54f / 4f), new DrawPistons(){{
                sinMag = 1;
            }}, new DrawDefault(), new DrawFade());
            consumeLiquid(Liquids.oil, 0.5f);
            consumeItem(Items.titanium, 5);
            consumePower(7f);
        }};
        largeCoalCentrifuge = new GenericCrafter("large-coal-centrifuge"){{
            requirements(Category.crafting, with(Items.titanium, 70, Items.graphite, 80, Items.silicon, 30, Items.lead, 60));
            health = 360;
            size = 3;
            hasPower = hasItems = hasLiquids = true;
            liquidCapacity = 60;
            itemCapacity =  20;
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawLiquidTile(), new DrawRegion("-spinner", 6, true), new DrawDefault());
            craftTime = 20f;
            outputItem = new ItemStack(Items.coal, 1);
            consumeLiquid(Liquids.oil, 0.3f);
            consumePower(1.8f);
        }};
        largeSurgeSmelter = new GenericCrafter("large-surge-smelter"){{
            requirements(Category.crafting, with(Items.lead, 120, Items.silicon, 100, HIItems.uranium, 80, Items.surgeAlloy, 10));
            size = 4;
            itemCapacity = 30;
            craftTime = 90f;
            craftEffect = Fx.smeltsmoke;
            outputItem = new ItemStack(Items.surgeAlloy, 5);
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawDefault(), new DrawPowerLight(Color.valueOf("f3e979")), new DrawFlame(Color.valueOf("ffef99")));
            consumePower(6);
            consumeItems(ItemStack.with(Items.copper, 5, Items.lead, 6, Items.titanium, 5, Items.silicon, 4));
        }};
        blastSiliconSmelter = new GenericCrafter("blast-silicon-smelter"){{
            requirements(Category.crafting, with(Items.graphite, 90, Items.thorium, 70, Items.silicon, 80, Items.plastanium, 50, Items.surgeAlloy, 30));
            health = 660;
            size = 4;
            hasItems = hasPower = true;
            hasLiquids = false;
            itemCapacity = 50;
            craftTime = 30;
            outputItem = new ItemStack(Items.silicon, 10);
            craftEffect = new RadialEffect(Fx.surgeCruciSmoke, 9, 45, 6);
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawDefault(), new DrawGlowRegion(){{
                alpha = 0.9f;
                glowScale = 3.1415926f;
                color = Color.valueOf("ff0000ff");
            }}, new DrawGlowRegion("-glow1"){{
                alpha = 0.9f;
                glowScale = 3.1415926f;
                color = Color.valueOf("eb564bff");
            }});
            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.21f;
            consumeItems(with(Items.coal, 5, Items.sand, 8, Items.blastCompound, 1));
            consumePower(9.5f);
        }};
        nanocoreConstructor = new GenericCrafter("nanocore-constructor"){{
            requirements(Category.crafting, with(Items.copper, 120, Items.lead, 110, Items.titanium, 45, Items.silicon, 35));
            size = 2;
            itemCapacity = 15;
            craftTime = 100;
            outputItem = new ItemStack(HIItems.nanocore, 1);
            craftEffect = Fx.none;
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawDefault(), new DrawFlame(){{
                flameRadius = 0;
                flameRadiusIn = 0;
                flameRadiusMag = 0;
                flameRadiusInMag = 0;
            }});
            consumePower(2.5f);
            consumeItems(ItemStack.with(Items.titanium, 2, Items.silicon, 3));
        }};
        nanocorePrinter = new GenericCrafter("nanocore-printer"){{
            requirements(Category.crafting, with(Items.titanium, 600, Items.silicon, 400, Items.plastanium, 350, Items.surgeAlloy, 250, HIItems.chromium, 200, HIItems.nanocore, 150));
            size = 4;
            health = 1500;
            squareSprite = false;
            hasLiquids = true;
            itemCapacity = 40;
            liquidCapacity = 60;
            craftTime = 150;
            outputItem = new ItemStack(HIItems.nanocore, 12);
            craftEffect = Fx.none;
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawScanLine(){{
                colorFrom = Color.valueOf("7cf389b6");
                lineLength = 73 / 4f;
                scanLength = 73 / 4f;
                scanScl = 15f;
                scanAngle = 90;
                lineStroke -= 0.15f;
                totalProgressMultiplier = 1.25f;
                phaseOffset = Mathf.random() * 5f;
            }}, new DrawScanLine(){{
                colorFrom = Color.valueOf("7cf389b6");
                lineLength = 73 / 4f;
                scanLength = 73 / 4f;
                scanScl = 15f;
                scanAngle = 0;
                totalProgressMultiplier = 1.55f;
                phaseOffset = Mathf.random() * 5f;
            }}, new DrawScanLine(){{
                colorFrom = Color.valueOf("7cf389b6");
                lineLength = 73 / 4f;
                scanLength = 73 / 4f;
                scanScl = 15f;
                scanAngle = 90;
                totalProgressMultiplier = 1.35f;
                phaseOffset = Mathf.random() * 5f;
            }}, new DrawScanLine(){{
                colorFrom = Color.valueOf("7cf389b6");
                lineLength = 73 / 4f;
                scanLength = 73 / 4f;
                scanScl = 8f;
                scanAngle = 0;
                lineStroke -= 0.15f;
                totalProgressMultiplier = 1.65f;
                phaseOffset = Mathf.random() * 5f;
            }}, new DrawRegion("-mid"), new DrawLiquidTile(Liquids.cryofluid, 54f / 4f), new DrawDefault(), new DrawGlowRegion("-glow1"){{
                alpha = 1;
                color = Color.valueOf("7cf389b6");
            }}, new DrawGlowRegion("-glow2"){{
                alpha = 1;
                color = Color.valueOf("7cf389b6");
            }}, new DrawGlowRegion("-glow3"){{
                alpha = 0.76f;
                color = Color.valueOf("7cf389b6");
            }});
            consumePower(25);
            consumeLiquid(Liquids.cryofluid, 6f / 60f);
            consumeItems(ItemStack.with(Items.titanium, 6, Items.silicon, 9));
        }};
        activator = new GenericCrafter("activator"){{
            requirements(Category.crafting, with(Items.titanium, 90, Items.silicon, 80, HIItems.nanocore, 30, Items.plastanium, 60));
            size = 2;
            health = 360;
            hasLiquids = outputsLiquid = true;
            rotate = false;
            solid = true;
            itemCapacity = 15;
            liquidCapacity = 24;
            craftTime = 100;
            craftEffect = Fx.smeltsmoke;
            outputLiquid = new LiquidStack(HILiquids.nanofluid, 18f / 60f);
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawLiquidTile(HILiquids.nanofluid), new DrawDefault(), new DrawRegion("-top"), new DrawFrames(){{
                frames = 5;
                sine = true;
            }});
            lightLiquid = HILiquids.nanofluid;
            consumePower(3);
            consumeItem(HIItems.nanocore, 1);
        }};
        highEnergyPhaseSynthesizer = new GenericCrafter("high-energy-phase-synthesizer"){{
            requirements(Category.crafting, with(Items.lead, 160,Items.thorium, 100, Items.silicon, 80, Items.plastanium, 50, HIItems.highEnergyFabric, 15));
            size = 3;
            itemCapacity = 40;
            craftTime = 50f;
            outputItem = new ItemStack(Items.phaseFabric, 2);
            updateEffect = new WaveEffect(){{
                lifetime = 25f;
                sizeFrom = strokeTo = 0f;
                sizeTo = 10f;
                strokeFrom = 6f;
                colorFrom = Color.valueOf("fff1d2");
                colorTo = Color.valueOf("ffd197");
            }};
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawWeave(), new DrawDefault());
            consumePower(7);
            consumeItems(ItemStack.with(Items.sand, 15, Items.thorium, 5));
        }};
        energizer = new GenericCrafter("energizer"){{
            requirements(Category.crafting, with(Items.lead, 40,Items.silicon, 65, Items.plastanium, 30));
            size = 2;
            craftTime = 120f;
            outputItem = new ItemStack(HIItems.highEnergyFabric, 1);
            drawer = new DrawMulti(new DrawDefault(), new DrawFade(){{
                scale = 2f;
                alpha = 0.98f;
            }});
            updateEffect = new WaveEffect(){{
                lifetime = 10;
                sizeFrom = strokeTo = 0f;
                sizeTo = 12f;
                strokeFrom = 2f;
                colorFrom = Color.valueOf("fff1d2");
                colorTo = Color.valueOf("fff197");
            }};
            consumePower(6);
            consumeItem(Items.phaseFabric, 1);
        }};
        largeEnergizer = new GeneratorCrafter("large-energizer"){{
            requirements(Category.crafting, with(HIItems.chromium, 260, Items.silicon, 220, Items.plastanium, 180, Items.surgeAlloy, 140, HIItems.nanocore, 120, HIItems.highEnergyFabric, 80));
            size = 4;
            hasLiquids = true;
            outputsPower = true;
            itemCapacity = 40;
            liquidCapacity = 30;
            powerProduction = 36f;
            outputItems = new ItemStack[]{new ItemStack(HIItems.highEnergyFabric, 10), new ItemStack(Items.thorium, 2)};
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawLiquidTile(HILiquids.nanofluid), new DrawArcSmelt(){{
                midColor = Color.valueOf("fff1d2");
                flameColor = Color.valueOf("eec591");
                flameRad = 8;
                circleSpace = 2;
                flameRadiusScl = 8;
                flameRadiusMag = 0.6f;
                circleStroke = 1.5f;
                alpha = 0.6f;
                particles = 23;
                particleLife = 13f;
                particleRad = 15f;
                particleStroke = 0.5f;
                particleLen = 6;
            }}, new DrawSpikes(){{
                rotateSpeed = 16;
                amount = 5;
                stroke = 0.6f;
                length = 14f;
                radius = 1f;
                color = Color.valueOf("ffd197");
            }}, new DrawSpikes(){{
                rotateSpeed = -6f;
                amount = 6;
                stroke = 0.9f;
                length = 14f;
                radius = 1f;
                color = Color.valueOf("ffd197");
            }}, new DrawSpikes(){{
                rotateSpeed = 1f;
                amount = 2;
                stroke = 1.3f;
                length = 14f;
                radius = 1f;
                color = Color.valueOf("ffd197");
            }}, new DrawDefault(), new DrawLiquidRegion(HILiquids.nanofluid){{
                suffix = "-liquid";
            }}, new DrawFade(){{
                scale = 8f;
                alpha = 0.8f;
            }});
            craftTime = 100;
            craftEffect = new MultiEffect(new WaveEffect(){{
                interp = Interp.circleOut;
                lifetime = 50;
                sizeFrom = strokeTo = 0;
                sizeTo = 46;
                strokeFrom = 9;
                colorFrom = Color.valueOf("fff1d2a8");
                colorTo = Color.valueOf("ffd197a8");
            }}, new WaveEffect(){{
                interp = Interp.circleOut;
                startDelay = 8;
                lifetime = 30;
                sizeFrom = strokeTo = 0;
                sizeTo = 46;
                strokeFrom = 9;
                colorFrom = Color.valueOf("fff1d2a8");
                colorTo = Color.valueOf("ffd197a8");
            }});
            updateEffect = new WaveEffect(){{
                interp = Interp.circleOut;
                lifetime = 45;
                sizeFrom = strokeTo = 0;
                sizeTo = 12;
                strokeFrom = 9;
                colorFrom = Color.valueOf("fff1d2");
                colorTo = Color.valueOf("ffd197");
            }};
            destroyBullet = new BasicBulletType(12, 0){{
                instantDisappear = pierceArmor = true;
                collides = hittable = absorbable = scaledSplashDamage = false;
                splashDamageRadius = 65;
                splashDamage = 200;
                lightningDamage = 83;
                lightning = 6;
                lightningLength = 14;
                lightningLengthRand = 8;
                lifetime = 90;
                hitShake = 8;
                hitSound = Sounds.plasmaboom;
                hitSoundVolume = 3;
                hitColor = Color.valueOf("ffd19788");
                hitEffect = new MultiEffect(new WrapEffect(){{
                    effect = Fx.dynamicSpikes;
                    color = Color.valueOf("ffd197");
                    rotation = 70;
                }}, Fx.titanExplosion, Fx.titanSmoke);
                fragBullets = 1;
                fragRandomSpread = 0;
                fragSpread = 90;
                fragBullet = new ShrapnelBulletType(){{
                    serrationLenScl = 7;
                    serrationSpaceOffset = 20;
                    serrationFadeOffset = 0;
                    serrationWidth = serrations = 6;
                    width = 16;
                    length = 76;
                    fromColor = toColor = HIPal.highEnergyYellow;
                    despawnEffect = Fx.none;
                    lifetime = 15;
                    damage = 35;
                }};
            }};
            consumeItems(ItemStack.with(Items.phaseFabric, 10, HIItems.uranium, 1));
            consumeLiquid(HILiquids.nanofluid, 18f / 60f);
            ambientSound = Sounds.electricHum;
            ambientSoundVolume = 0.68f;
        }};
        highEnergyFabricFusionInstrument = new GenericCrafter("high-energy-fabric-fusion-instrument"){{
            requirements(Category.crafting, with(Items.silicon, 220, Items.plastanium, 120, Items.phaseFabric, 60, HIItems.chromium, 100, HIItems.nanocore, 40));
            size = 3;
            hasLiquids = true;
            itemCapacity = 30;
            liquidCapacity = 20;
            lightRadius /= 2f;
            craftTime = 60;
            craftEffect = HIFx.crossBlast(HIPal.breakdownYellow, 45f, 45f);
            craftEffect.lifetime *= 1.5f;
            updateEffect = HIFx.squareRand(HIPal.breakdownYellow, 5f, 15f);
            outputItem = new ItemStack(HIItems.highEnergyFabric, 3);
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawRegion("-bottom-2"), new DrawCrucibleFlame(){{
                flameColor = HIPal.breakdownYellow;
                midColor = Color.valueOf("2e2f34");
                circleStroke = 1.05f;
                circleSpace = 2.65f;
            }
                @Override
                public void draw(Building build){
                    if(build.warmup() > 0f && flameColor.a > 0.001f){
                        Lines.stroke(circleStroke * build.warmup());
                        float si = Mathf.absin(flameRadiusScl, flameRadiusMag);
                        float a = alpha * build.warmup();
                        Draw.blend(Blending.additive);
                        Draw.color(flameColor, a);
                        float base = (Time.time / particleLife);
                        rand.setSeed(build.id);
                        for(int i = 0; i < particles; i++){
                            float fin = (rand.random(1f) + base) % 1f, fout = 1f - fin;
                            float angle = rand.random(360f) + (Time.time / rotateScl) % 360f;
                            float len = particleRad * particleInterp.apply(fout);
                            Draw.alpha(a * (1f - Mathf.curve(fin, 1f - fadeMargin)));
                            Fill.square(
                                    build.x + Angles.trnsx(angle, len),
                                    build.y + Angles.trnsy(angle, len),
                                    particleSize * fin * build.warmup(), 45
                            );
                        }
                        Draw.blend();
                        Draw.color(midColor, build.warmup());
                        Lines.square(build.x, build.y, (flameRad + circleSpace + si) * build.warmup(), 45);
                        Draw.reset();
                    }
                }
            }, new DrawDefault(), new DrawGlowRegion(){{
                color = HIPal.breakdownYellow;
                layer = -1;
                glowIntensity = 1.1f;
                alpha = 1.1f;
            }}, new DrawRotator(1f, "-top"){
                @Override
                public void draw(Building build){
                    Drawf.spinSprite(rotator, build.x + x, build.y + y, DrawFunc.rotator_90(DrawFunc.cycle(build.totalProgress() * rotateSpeed, 0, craftTime), 0.15f));
                }
            });
            consumePower(9f);
            consumeItems(ItemStack.with(HIItems.uranium, 2, HIItems.rareEarth, 8));
        }};
        uraniumSynthesizer = new GenericCrafter("uranium-synthesizer"){{
            requirements(Category.crafting, with(Items.graphite, 50, Items.silicon, 40, Items.plastanium, 30, Items.phaseFabric, 15));
            size = 2;
            health = 350;
            craftTime = 30;
            outputItem = new ItemStack(HIItems.uranium, 1);
            craftEffect = Fx.smeltsmoke;
            drawer = new DrawMulti(new DrawDefault(), new DrawFlame(){{
                flameRadius = 0;
                flameRadiusIn = 0;
                flameRadiusMag = 0;
                flameRadiusInMag = 0;
            }});
            consumePower(5);
            consumeItems(ItemStack.with(Items.graphite, 1, Items.thorium, 1));
        }};
        chromiumSynthesizer = new GenericCrafter("chromium-synthesizer"){{
            requirements(Category.crafting, with(Items.metaglass, 30, Items.silicon, 40, Items.plastanium, 50, Items.phaseFabric, 35));
            size = 3;
            health = 650;
            hasLiquids = true;
            itemCapacity = 20;
            liquidCapacity = 30;
            craftTime = 120;
            outputItem = new ItemStack(HIItems.chromium, 5);
            craftEffect = Fx.smeltsmoke;
            drawer = new DrawMulti(new DrawDefault(), new DrawLiquidRegion(Liquids.slag), new DrawGlowRegion(){{
                glowScale = 8;
                alpha = 0.8f;
            }}, new DrawFlame(){{
                flameRadius = 0;
                flameRadiusIn = 0;
                flameRadiusMag = 0;
                flameRadiusInMag = 0;
            }});
            consumePower(7.5f);
            consumeLiquid(Liquids.slag, 12f / 60f);
            consumeItems(ItemStack.with(Items.titanium, 8, Items.phaseFabric, 1));
        }};
        heavyAlloySmelter = new GenericCrafter("heavy-alloy-smelter"){{
            requirements(Category.crafting, with(Items.lead, 80, Items.graphite, 120, Items.silicon, 60, HIItems.chromium, 30));
            size = 3;
            health = 850;
            craftTime = 80;
            outputItem = new ItemStack(HIItems.heavyAlloy, 2);
            craftEffect = Fx.smeltsmoke;
            drawer = new DrawMulti(new DrawDefault(), new DrawFlame());
            consumePower(9);
            consumeItems(ItemStack.with(HIItems.uranium, 1, HIItems.chromium, 1));
        }};
        metalAnalyzer = new Separator("metal-analyzer"){{
            requirements(Category.crafting, with(Items.titanium, 120, Items.silicon, 180, Items.plastanium, 80, HIItems.nanocore, 30));
            size = 3;
            itemCapacity = 20;
            liquidCapacity = 30;
            craftTime = 20f;
            results = with(Items.titanium, 2, Items.thorium, 2, HIItems.uranium, 1, HIItems.chromium, 1);
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawPistons(){{
                sides = 4;
                sinMag = 3.9f;
                lenOffset = -1.785f;
                angleOffset = 45;
            }}, new DrawRegion("-shade"), new DrawDefault(), new DrawLiquidTile(Liquids.water, 39f / 4f), new DrawRegion("-top"));
            consumePower(3.5f);
            consumeItem(HIItems.rareEarth, 1);
            consumeLiquid(Liquids.water, 6f / 60f);
        }};
        nitrificationReactor = new GenericCrafter("nitrification-reactor"){{
            requirements(Category.crafting, with(Items.metaglass, 80, Items.silicon, 20, Items.plastanium, 30, HIItems.chromium, 60));
            size = 2;
            health = 380;
            armor = 5;
            hasLiquids = true;
            itemCapacity = 10;
            liquidCapacity = 24;
            craftTime = 60;
            outputLiquid = new LiquidStack(HILiquids.nitratedOil, 18f / 60f);
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawLiquidTile(Liquids.oil), new DrawLiquidTile(HILiquids.nitratedOil), new DrawDefault());
            consumePower(5f);
            consumeLiquid(Liquids.oil, 18f / 60f);
            consumeItem(Items.sporePod, 1);
        }};
        nitratedOilSedimentationTank = new Separator("nitrated-oil-sedimentation-tank"){{
            requirements(Category.crafting, with(Items.copper, 160, Items.graphite, 120, Items.plastanium, 60, HIItems.chromium, 80));
            size = 3;
            health = 680;
            armor = 8;
            itemCapacity = 15;
            liquidCapacity = 54;
            results = with(Items.pyratite, 1, Items.blastCompound, 4);
            craftTime = 9f;
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawLiquidTile(HILiquids.nitratedOil), new DrawDefault());
            ambientSound = HISounds.largeBeam;
            ambientSoundVolume = 0.24f;
            consumePower(4f);
            consumeLiquid(HILiquids.nitratedOil, 54f / 60f);
        }};
        //production-erekir
        ventHeater = new ThermalHeater("vent-heater"){{
            requirements(Category.crafting, with(Items.graphite, 70, Items.tungsten, 80, Items.oxide, 50));
            size = 3;
            hasPower = false;
            attribute = Attribute.steam;
            group = BlockGroup.liquids;
            displayEfficiencyScale = 1f / 9f;
            minEfficiency = 9f - 0.0001f;
            displayEfficiency = false;
            generateEffect = Fx.turbinegenerate;
            effectChance = 0.04f;
            ambientSound = Sounds.hum;
            ambientSoundVolume = 0.06f;
            drawer = new DrawMulti(new DrawDefault(), new DrawHeatOutput());
            squareSprite = false;
            outputHeat = 15 / 9f;
            outputsLiquid = true;
            outputLiquid = new LiquidStack(Liquids.water, 5f / 60f / 9f);
            sec = 9;
        }
            @Override
            public void setStats() {
                super.setStats();
                stats.remove(Stat.basePowerGeneration);
            }
        };
        chemicalSiliconSmelter = new GenericCrafter("chemical-silicon-smelter"){{
            requirements(Category.crafting, with(Items.graphite, 140, Items.silicon, 50, Items.tungsten, 120, Items.oxide, 100));
            size = 4;
            hasLiquids = true;
            itemCapacity = 30;
            liquidCapacity = 20;
            craftTime = 50;
            outputItem = new ItemStack(Items.silicon, 8);
            consumeItems(with(Items.sand, 8));
            consumeLiquids(LiquidStack.with(Liquids.hydrogen, 3f / 60f));
            consumePower(3f);
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawLiquidRegion(Liquids.hydrogen), new DrawCrucibleFlame(){{
                midColor = Color.valueOf("97a5f7");
                flameColor = Color.valueOf("d1e4ff");
                flameRad = 4.45f;
                circleSpace = 3;
                flameRadiusScl = 16;
                flameRadiusMag = 3;
                circleStroke = 0.6f;
                particles = 33;
                particleLife = 107;
                particleRad = 16;
                particleSize = 2.68f;
                rotateScl = 1.7f;
            }}, new DrawDefault());
            squareSprite = false;
        }};
        liquidFuelHeater = new LiquidFuelHeater("liquid-fuel-heater"){{
            requirements(Category.crafting, with(Items.graphite, 160, Items.beryllium, 120, Items.tungsten, 80, Items.oxide, 30));
            size = 3;
            heatOutput = 10;
            liquidCapacity = 60;
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawLiquidTile(), new DrawDefault(), new DrawHeatOutput());
            consume(new ConsumeLiquidFlammable(7.5f / 60f));
        }};
        largeElectricHeater = new HeatProducer("large-electric-heater"){{
            requirements(Category.crafting, with(Items.tungsten, 150, Items.oxide, 120, Items.carbide, 50));
            size = 5;
            health = 4160;
            armor = 13;
            drawer = new DrawMulti(new DrawDefault(), new DrawHeatOutput());
            squareSprite = false;
            rotateDraw = false;
            heatOutput = 25f;
            regionRotated1 = 1;
            ambientSound = Sounds.hum;
            consumePower(550f / 60f);
        }};
        largeOxidationChamber = new HeatProducer("large-oxidation-chamber"){{
            requirements(Category.crafting, with(Items.tungsten, 180, Items.graphite, 220, Items.silicon, 220, Items.beryllium, 320, Items.oxide, 120, Items.carbide, 70));
            size = 5;
            health = 2650;
            armor = 3;
            squareSprite = false;
            hasItems = hasLiquids = hasPower = true;
            outputItem = new ItemStack(Items.oxide, 5);
            researchCostMultiplier = 1.1f;
            consumeLiquid(Liquids.ozone, 8f / 60f);
            consumeItem(Items.beryllium, 5);
            consumePower(1.5f);
            rotateDraw = false;
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawLiquidRegion(Liquids.ozone), new DrawDefault(), new DrawRegion("-top"), new DrawHeatOutput());
            ambientSound = Sounds.extractLoop;
            ambientSoundVolume = 0.3f;
            regionRotated1 = 2;
            craftTime = 60f * 2f;
            itemCapacity = 30;
            liquidCapacity = 50f;
            heatOutput = 35f;
            canOverdrive = true;
        }};
        largeSurgeCrucible = new HeatCrafter("large-surge-crucible"){{
            requirements(Category.crafting, with(Items.graphite, 220, Items.silicon, 200, Items.tungsten, 240, Items.oxide, 120, Items.surgeAlloy, 80));
            size = 4;
            health = 1650;
            armor = 6;
            hasLiquids = true;
            itemCapacity = 30;
            liquidCapacity = 600;
            heatRequirement = 15;
            craftTime = 180;
            outputItem = new ItemStack(Items.surgeAlloy, 3);
            ambientSound = Sounds.smelter;
            ambientSoundVolume = 1.8f;
            craftEffect = new RadialEffect(Fx.surgeCruciSmoke, 4, 90, 5);
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawCircles(){{
                color = Color.valueOf("ffc073");
                strokeMax = 2.5f;
                radius = 10;
                amount = 3;
            }}, new DrawLiquidRegion(Liquids.slag), new DrawDefault(), new DrawHeatInput(), new DrawHeatRegion(){{
                color = Color.valueOf("ff6060");
            }}, new DrawHeatRegion("-vents"));
            consumeItem(Items.silicon, 8);
            consumeLiquid(Liquids.slag, 80f / 60f);
            consumePower(1);
        }};
        largeCarbideCrucible = new HeatCrafter("large-carbide-crucible"){{
            requirements(Category.crafting, with( Items.thorium, 300, Items.tungsten, 400, Items.oxide, 100, Items.carbide, 60));
            size = 5;
            health = 2950;
            armor = 10;
            itemCapacity = 30;
            heatRequirement = 20;
            craftTime = 135;
            outputItem = new ItemStack(Items.carbide, 3);
            ambientSound = Sounds.smelter;
            ambientSoundVolume = 1.8f;
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawCrucibleFlame(), new DrawDefault(), new DrawHeatInput());
            consumeItems(ItemStack.with(Items.graphite, 8, Items.tungsten, 5));
            consumePower(1);
        }};
        largePhaseSynthesizer = new HeatCrafter("large-phase-synthesizer"){{
            requirements(Category.crafting, with(Items.carbide, 250, Items.phaseFabric, 120, Items.silicon, 350, Items.thorium, 300, Items.tungsten, 400));
            size = 4;
            itemCapacity = 60;
            heatRequirement = 16f;
            craftTime = 80f;
            liquidCapacity = 60f;
            ambientSound = Sounds.techloop;
            ambientSoundVolume = 0.06f;
            outputItem = new ItemStack(Items.phaseFabric, 2);
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawSpikes(){{
                color = Color.valueOf("ffd59e");
                stroke = 1.5f;
                layers = 2;
                amount = 12;
                rotateSpeed = 0.5f;
                layerSpeed = -0.9f;
            }}, new DrawMultiWeave(){{
                glowColor = new Color(1f, 0.4f, 0.4f, 0.8f);
            }}, new DrawDefault(), new DrawHeatInput(), new DrawHeatRegion("-vents"){{
                color = new Color(1f, 0.4f, 0.3f, 1f);
            }});
            consumeItems(with(Items.thorium, 3, Items.sand, 9));
            consumeLiquid(Liquids.ozone, 4f / 60f);
            consumePower(8f);
        }};
        //defense
        mendDome = new MendProjector("mend-dome"){{
            requirements(Category.effect, with(Items.copper, 150, Items.lead, 200, Items.titanium, 130, Items.silicon, 120, Items.plastanium, 60));
            size = 3;
            reload = 250f;
            range = 150f;
            healPercent = 15f;
            phaseBoost = 25f;
            phaseRangeBoost = 75;
            scaledHealth = 240;
            consumePower(2.5f);
            consumeItem(Items.phaseFabric, 1).boost();
        }};
        largeShieldGenerator = new ForceProjector("large-shield-generator") {{
            requirements(Category.effect, with(Items.silicon, 120, Items.lead, 250, Items.graphite, 180, Items.plastanium, 150, Items.phaseFabric, 120, HIItems.chromium, 50));
            size = 4;
            radius = 220f;
            shieldHealth = 20000f;
            cooldownNormal = 12f;
            cooldownLiquid = 6f;
            cooldownBrokenBase = 9f;
            itemConsumer = consumeItem(Items.phaseFabric).boost();
            phaseUseTime = 180.0F;
            phaseRadiusBoost = 100.0f;
            phaseShieldBoost = 15000.0f;
            consumePower(15f);
        }};
        //defense-erekir
        largeRadar = new Radar("large-radar"){{
            requirements(Category.effect, BuildVisibility.fogOnly, with(Items.graphite, 180, Items.silicon, 160, Items.beryllium, 30, Items.tungsten, 10, Items.oxide, 20));
            health = 180;
            size = 3;
            outlineColor = Color.valueOf("4a4b53");
            fogRadius = 86;
            consumePower(1.2f);
        }};
        //storage
        coreBeStationed = new BeStationedCoreBlock("core-be-stationed"){{
            requirements(Category.effect, with(Items.copper, 500, Items.lead, 350, Items.silicon, 200));
            size = 2;
            health = 300;
            itemCapacity = 1000;
            unitCapModifier = 4;
            maxCoreQuantity = 8;
        }};
        bin = new StorageBlock("bin"){{
            requirements(Category.effect, with(Items.copper, 55, Items.lead, 35));
            size = 1;
            itemCapacity = 60;
            scaledHealth = 55;
        }};
        cargo = new StorageBlock("cargo"){{
            requirements(Category.effect, with(Items.titanium, 350, Items.thorium, 250, Items.plastanium, 125));
            size = 4;
            itemCapacity = 3000;
            scaledHealth = 55;
        }};
        rapidUnloader = new Unloader("rapid-unloader"){{
            requirements(Category.effect, with(Items.silicon, 35, Items.plastanium, 15, Items.phaseFabric, 5));
            health = 60;
            speed = 60f / 30f;
        }};
        highEnergyDirectionalUnloader = new AdaptDirectionalUnloader("high-energy-directional-unloader"){{
            requirements(Category.effect, with(Items.silicon, 40, Items.plastanium, 25, HIItems.chromium, 15, HIItems.highEnergyFabric, 5));
            speed = 60.0f;
            squareSprite = false;
            underBullets = true;
            allowCoreUnload = true;
        }};
        //unit
        titanReconstructor = new Reconstructor("titan-reconstructor"){{
            requirements(Category.units, with(Items.lead, 4000, Items.silicon, 3000, Items.plastanium, 1500, Items.surgeAlloy, 1200, HIItems.highEnergyFabric, 300, HIItems.chromium, 800, HIItems.heavyAlloy, 400));
            size = 11;
            liquidCapacity = 360;
            scaledHealth = 100;
            constructTime = 60f * 60f * 4.2f;
            upgrades.addAll(
                    new UnitType[]{UnitTypes.eclipse, HIUnitTypes.sunlit},
                    new UnitType[]{UnitTypes.toxopid, HIUnitTypes.cancer},
                    new UnitType[]{UnitTypes.reign, HIUnitTypes.suzerain},
                    new UnitType[]{UnitTypes.oct, HIUnitTypes.windstorm},
                    new UnitType[]{UnitTypes.corvus, HIUnitTypes.supernova},
                    new UnitType[]{UnitTypes.omura, HIUnitTypes.harpoon},
                    new UnitType[]{UnitTypes.navanax, HIUnitTypes.killerWhale}
            );
            consumePower(35);
            consumeLiquid(Liquids.cryofluid, 192f / 60f);
            consumeItems(ItemStack.with(Items.silicon, 1500, HIItems.highEnergyFabric, 100, HIItems.uranium, 300, HIItems.heavyAlloy, 200));
        }};
        experimentalUnitFactory = new DerivativeUnitFactory("experimental-unit-factory"){{
            requirements(Category.units, with(Items.silicon, 2500, Items.plastanium, 1500, Items.surgeAlloy, 1000, HIItems.nanocore, 800, HIItems.highEnergyFabric, 400, HIItems.heavyAlloy, 600));
            size = 5;
            liquidCapacity = 60;
            floating = true;
            config(Integer.class, (UnitFactoryBuild tile, Integer i) -> {
                tile.currentPlan = i < 0 || i >= plans.size ? -1 : i;
                tile.progress = 0;
                tile.payload = null;
            });
            config(UnitType.class, (UnitFactoryBuild tile, UnitType val) -> {
                tile.currentPlan = plans.indexOf(p -> p.unit == val);
                tile.progress = 0;
                tile.payload = null;
            });
            consumePower(40);
            consumeLiquid(HILiquids.nanofluid, 18f / 60f);
        }
            @Override
            public void init() {
                for(int i = 0; i < Vars.content.units().size; i++){
                    UnitType u = Vars.content.unit(i);
                    if(u != null && u.getFirstRequirements() != null){
                        ItemStack[] is = u.getFirstRequirements();
                        ItemStack[] os = new ItemStack[is.length];
                        for (int a = 0; a < is.length; a++) {
                            os[a] = new ItemStack(is[a].item, is[a].amount >= 40 ? (int) (is[a].amount * (1.0)) : is[a].amount);
                        }
                        float time = 0;
                        if(u.getFirstRequirements().length > 0) {
                            for (ItemStack itemStack : os) {
                                time += itemStack.amount * itemStack.item.cost;
                            }
                        }
                        if(u.armor < 55) plans.add(new UnitPlan(u, time * 3, os));
                        else plans.add(new UnitPlan(u, time * 2, is));
                    }
                }
                super.init();
            }
        };
        //unit-erekir
        largeUnitRepairTower = new RepairTower("large-unit-repair-tower"){{
            requirements(Category.units, with(Items.graphite, 120, Items.silicon, 150, Items.tungsten, 180, Items.oxide, 60, Items.carbide, 30));
            size = 4;
            health = 650;
            squareSprite = false;
            liquidCapacity = 30;
            range = 220;
            healAmount = 10;
            consumePower(3);
            consumeLiquid(Liquids.ozone, 8f / 60f);
        }};
        seniorAssemblerModule = new UnitAssemblerModule("senior-assembler-module"){{
            requirements(Category.units, with(Items.thorium, 800, Items.phaseFabric, 600, Items.surgeAlloy, 400, Items.oxide, 300, Items.carbide, 400));
            size = 5;
            tier = 2;
            regionSuffix = "-dark";
            researchCostMultiplier = 0.75f;
            consumePower(5.5f);
        }};
        //logic
        buffrerdMemoryCell = new CopyMemoryBlock("buffrerd-memory-cell"){{
            requirements(Category.logic, with(Items.titanium, 40, Items.graphite, 40, Items.silicon, 40));
            size = 1;
            memoryCapacity = 64;
        }};
        buffrerdMemoryBank = new CopyMemoryBlock("buffrerd-memory-bank"){{
            requirements(Category.logic, with(Items.titanium, 40 ,Items.graphite, 90, Items.silicon, 90, Items.phaseFabric, 30));
            size = 2;
            memoryCapacity = 512;
        }};
        //turret
        dissipation = new PointDefenseTurret("dissipation"){{
            requirements(Category.turret, with(Items.silicon, 220, HIItems.chromium, 80, HIItems.highEnergyFabric, 40, Items.plastanium, 60));
            size = 3;
            hasPower = true;
            scaledHealth = 250;
            range = 240f;
            shootLength = 5f;
            bulletDamage = 110f;
            retargetTime = 6f;
            reload = 6f;
            envEnabled |= Env.space;
            consumePower(12f);
        }};
        rocketLauncher = new ItemTurret("rocket-launcher"){{
            requirements(Category.turret, with(Items.copper, 60, Items.lead, 40, Items.graphite, 30));
            ammo(
                    Items.graphite, new MissileBulletType(3.6f, 30f){{
                        splashDamage = 15f;
                        splashDamageRadius = 18f;
                        backColor = trailColor = Color.valueOf("b0c4de");
                        frontColor = Color.valueOf("e3e3e3");
                        lifetime = 36;
                        homingPower = 0.045f;
                        homingRange = 40f;
                        width = 4f;
                        height = 16f;
                        hitEffect = Fx.flakExplosion;
                        ammoMultiplier = 2;
                    }},
                    Items.pyratite, new MissileBulletType(3.6f, 18f){{
                        splashDamage = 36f;
                        splashDamageRadius = 22f;
                        drag = -0.028f;
                        makeFire = true;
                        backColor = trailColor = Color.valueOf("ffb90f");
                        frontColor = Color.valueOf("e3e3e3");
                        lifetime = 36;
                        homingPower = 0.03f;
                        homingRange = 40f;
                        width = 4f;
                        height = 16f;
                        hitEffect = Fx.flakExplosion;
                        ammoMultiplier = 3;
                    }},
                    Items.blastCompound, new MissileBulletType(3.6f, 16f){{
                        splashDamage = 47f;
                        splashDamageRadius = 32f;
                        drag = -0.026f;
                        backColor = trailColor = Color.valueOf("ff7f24");
                        frontColor = Color.valueOf("e3e3e3");
                        lifetime = 40;
                        homingPower = 0.03f;
                        homingRange = 40f;
                        width = 4f;
                        height = 16f;
                        hitEffect = Fx.flakExplosionBig;
                        ammoMultiplier = 3;
                    }}
            );
            size = 1;
            health = 300;
            range = 210;
            reload = 100f;
            maxAmmo = 20;
            shoot = new ShootAlternate(){{
                shots = 2;
                shotDelay = 10;
                barrels = 2;
            }};
            rotateSpeed = 8f;
            inaccuracy = 0f;
            shootSound = Sounds.missile;
        }};
        multipleRocketLauncher = new ItemTurret("multiple-rocket-launcher"){{
            requirements(Category.turret, with(Items.copper, 40, Items.lead, 25, Items.graphite, 20));
            ammo(
                    Items.graphite, new MissileBulletType(5f, 24f){{
                        splashDamage = 12f;
                        splashDamageRadius = 18f;
                        backColor = trailColor = Color.valueOf("b0c4de");
                        frontColor = Color.valueOf("e3e3e3");
                        lifetime = 55;
                        homingPower = 0.36f;
                        homingRange = 40f;
                        hitEffect = Fx.flakExplosion;
                        ammoMultiplier = 2f;
                    }},
                    Items.pyratite, new MissileBulletType(6f, 12f){{
                        splashDamage = 32f;
                        splashDamageRadius = 22f;
                        backColor = trailColor = Color.valueOf("ffb90f");
                        frontColor = Color.valueOf("e3e3e3");
                        status = StatusEffects.burning;
                        statusDuration = 360f;
                        lifetime = 45;
                        homingPower = 0.036f;
                        homingRange = 40f;
                        hitEffect = Fx.flakExplosion;
                        ammoMultiplier = 3f;
                    }},
                    Items.blastCompound, new MissileBulletType(4.5f, 12f){{
                        splashDamage = 40f;
                        splashDamageRadius = 32f;
                        backColor = trailColor = Color.valueOf("ff7f24");
                        frontColor = Color.valueOf("e3e3e3");
                        status = StatusEffects.blasted;
                        lifetime = 60;
                        homingPower = 0.036f;
                        homingRange = 40f;
                        hitEffect = Fx.flakExplosionBig;
                        ammoMultiplier = 3f;
                    }}
            );
            size = 2;
            health = 720;
            range = 270;
            reload = 40f;
            shake = 1f;
            consumeAmmoOnce = false;
            maxAmmo = 24;
            shoot = new ShootAlternate(){{
                shots = 4;
                shotDelay = 3;
                barrels = 3;
            }};
            rotateSpeed = 6f;
            inaccuracy = 8f;
            shootSound = Sounds.missile;
        }};
        largeRocketLauncher = new ItemTurret("large-rocket-launcher"){{
            requirements(Category.turret, with(Items.graphite, 360, Items.titanium, 220, Items.thorium, 100, Items.silicon, 110, Items.plastanium, 70));
            ammo(
                    Items.pyratite, new MissileBulletType(10f, 55f){{
                        shrinkY = 0;
                        inaccuracy = 4;
                        trailChance = 0.8f;
                        homingRange = 80;
                        splashDamage = 85f;
                        splashDamageRadius = 36f;
                        lifetime = 32;
                        hitShake = 2;
                        sprite = name("rocket");
                        backColor = Color.valueOf("ffb90f");
                        frontColor = Color.valueOf("e3e3e3");
                        trailColor = Color.valueOf("ffb90f");
                        status = StatusEffects.burning;
                        statusDuration = 600;
                        width = 16;
                        height = 40;
                        ammoMultiplier = 3;
                        shootEffect = new MultiEffect(Fx.shootBig2, Fx.shootPyraFlame, Fx.shootPyraFlame);
                        despawnEffect = Fx.flakExplosion;
                        hitEffect = new MultiEffect(new ParticleEffect(){{
                            particles = 8;
                            sizeFrom = 8;
                            sizeTo = 0;
                            length = 15;
                            baseLength = 15;
                            lifetime = 35;
                            colorFrom = colorTo = Color.valueOf("737373");
                        }}, new ParticleEffect(){{
                            particles = 12;
                            line = true;
                            length = 30;
                            baseLength = 8;
                            lifetime = 22;
                            colorFrom = Color.white;
                            colorTo = Color.valueOf("ffe176");
                        }}, new WaveEffect(){{
                            lifetime = 10;
                            sizeFrom = 1;
                            sizeTo = 36;
                            strokeFrom = 8;
                            strokeTo = 0;
                            colorFrom = Color.valueOf("ffe176");
                            colorTo = Color.valueOf("ffe176");
                        }});
                    }},
                    Items.blastCompound, new MissileBulletType(10f, 58f){{
                        recoil = 1;
                        shrinkY = 0;
                        inaccuracy = 4;
                        trailChance = 0.8f;
                        homingRange = 80;
                        splashDamage = 166f;
                        splashDamageRadius = 76f;
                        lifetime = 32;
                        hitShake = 2;
                        sprite = name("missile");
                        backColor = Color.valueOf("ff7f24");
                        frontColor = Color.valueOf("e3e3e3");
                        trailColor = Color.valueOf("ff7f24");
                        status = StatusEffects.burning;
                        statusDuration = 600;
                        width = 14;
                        height = 50;
                        hitSound = Sounds.explosion;
                        ammoMultiplier = 3;
                        shootEffect = new MultiEffect(Fx.shootBig2, Fx.shootPyraFlame, Fx.shootPyraFlame);
                        despawnEffect = Fx.flakExplosion;
                        hitEffect = new MultiEffect(new ParticleEffect(){{
                            particles = 8;
                            sizeFrom = 8f;
                            sizeTo = 0f;
                            length = lifetime = 35f;
                            baseLength = 33f;
                            colorFrom = Color.valueOf("737373");
                            colorTo = Color.valueOf("73737388");
                        }}, new ParticleEffect(){{
                            particles = 12;
                            line = true;
                            interp = Interp.pow10Out;
                            strokeFrom = 2f;
                            strokeTo = 1.5f;
                            lenFrom = 16f;
                            lenTo = 0;
                            length = 43f;
                            baseLength = 23f;
                            lifetime = 22f;
                            colorFrom = Color.white;
                            colorTo = Color.valueOf("ffe176");
                        }}, new WaveEffect(){{
                            lifetime = 10f;
                            sizeFrom = 1f;
                            sizeTo = 78f;
                            strokeFrom = 8f;
                            strokeTo = 0f;
                            colorFrom = colorTo = Color.valueOf("ffe176");
                        }});
                    }}
            );
            size = 3;
            health = 350;
            range = 400;
            canOverdrive = false;
            reload = 35f;
            shake = 2f;
            maxAmmo = 25;
            rotateSpeed = 5f;
            inaccuracy = 0f;
            shootY = 8;
            shootWarmupSpeed = 0.04f;
            warmupMaintainTime = 45;
            minWarmup = 0.8f;
            shootSound = Sounds.missileSmall;
            drawer = new DrawTurret(){{
                parts.add(new RegionPart(){{
                    mirror = true;
                    moveX = 1.5f;
                    moveY = 0f;
                    moveRot = 0f;
                }});
            }};
            smokeEffect = new ParticleEffect(){{
                particles = 8;
                sizeFrom = 3;
                sizeTo = 0;
                length = -35;
                baseLength = -15;
                lifetime = 65;
                colorFrom = Color.white;
                colorTo = Color.valueOf("e3e3e378");
                layer = 49;
                interp = Interp.pow5Out;
                sizeInterp = Interp.pow10In;
                cone = 10;
            }};
            shoot = new ShootAlternate(){{
                barrels = 2;
                spread = 11;
                shots = 3;
                shotDelay = 8;
            }};
            consumeAmmoOnce = false;
            maxAmmo = 24;
            ammoPerShot = 3;
        }};
        rocketSilo = new ItemTurret("rocket-silo"){{
            requirements(Category.turret, with(Items.lead, 300, Items.graphite, 150, Items.titanium, 120, Items.silicon, 120, Items.plastanium, 50));
            ammo(
                    Items.graphite, new MissileBulletType(8f, 22f){{
                        buildingDamageMultiplier = 0.3f;
                        splashDamage = 15f;
                        splashDamageRadius = 18f;
                        knockback = 0.7f;
                        lifetime = 135f;
                        homingDelay = 10f;
                        homingRange = 800f;
                        homingPower = 0.15f;
                        sprite = name("missile");
                        backColor = Color.valueOf("b0c4de");
                        frontColor = Color.valueOf("e3e3e3");
                        trailLength = 15;
                        trailWidth = 1.5f;
                        trailColor = Color.valueOf("ffffff80");
                        trailEffect = Fx.none;
                        width = 10f;
                        height = 40f;
                        hitShake = 1f;
                        ammoMultiplier = 2f;
                        smokeEffect = Fx.shootSmallFlame;
                        hitEffect = Fx.flakExplosion;
                    }},
                    Items.pyratite, new MissileBulletType(7f, 14f){{
                        buildingDamageMultiplier = 0.3f;
                        splashDamage = 39f;
                        splashDamageRadius = 32f;
                        status = StatusEffects.burning;
                        statusDuration = 600;
                        makeFire = true;
                        lifetime = 145f;
                        homingPower = 0.15f;
                        homingDelay = 10f;
                        homingRange = 800f;
                        sprite = name("missile");
                        backColor = Color.valueOf("ffb90f");
                        frontColor = Color.valueOf("e3e3e3");
                        trailLength = 15;
                        trailWidth = 1.5f;
                        trailColor = Color.valueOf("ffffff80");
                        trailEffect = Fx.none;
                        width = 10f;
                        height = 40f;
                        hitShake = 1f;
                        ammoMultiplier = 2f;
                        smokeEffect = Fx.shootSmallFlame;
                        hitEffect = Fx.flakExplosionBig;
                    }},
                    Items.blastCompound, new MissileBulletType(7f, 17f){{
                        buildingDamageMultiplier = 0.3f;
                        splashDamage = 55f;
                        splashDamageRadius = 45f;
                        knockback = 3;
                        status = StatusEffects.blasted;
                        lifetime = 145f;
                        homingPower = 0.15f;
                        homingDelay = 10f;
                        homingRange = 800f;
                        sprite = name("missile");
                        backColor = Color.valueOf("ff7055");
                        frontColor = Color.valueOf("e3e3e3");
                        trailLength = 15;
                        trailWidth = 1.5f;
                        trailColor = Color.valueOf("ffffff80");
                        trailEffect = Fx.none;
                        width = 10f;
                        height = 40f;
                        hitShake = 1f;
                        ammoMultiplier = 2f;
                        smokeEffect = Fx.shootSmallFlame;
                        hitEffect = Fx.flakExplosionBig;
                    }},
                    Items.surgeAlloy, new MissileBulletType(9f, 47f){{
                        buildingDamageMultiplier = 0.3f;
                        splashDamage = 75f;
                        splashDamageRadius = 45f;
                        lightningDamage = 17;
                        lightning = 3;
                        lightningLength = 8;
                        knockback = 3;
                        status = StatusEffects.blasted;
                        lifetime = 125f;
                        homingPower = 0.15f;
                        homingDelay = 10f;
                        homingRange = 800f;
                        sprite = name("missile");
                        backColor = Color.valueOf("f2e770");
                        frontColor = Color.valueOf("e3e3e3");
                        trailLength = 16;
                        trailWidth = 2.5f;
                        trailColor = Color.valueOf("ffffff80");
                        trailEffect =Fx.none;
                        width = 13f;
                        height = 48f;
                        hitShake = 1f;
                        ammoMultiplier = 3f;
                        smokeEffect =Fx.shootSmallFlame;
                        hitEffect = Fx.flakExplosionBig;
                    }}
            );
            size = 3;
            health = 1850;
            range = 760;
            reload = 150f;
            shake = 1f;
            targetAir = true;
            targetGround = true;
            consumeAmmoOnce = false;
            customShadow = false;
            maxAmmo = 32;
            rotateSpeed = 0f;
            inaccuracy = 5f;
            recoil = 0f;
            shootY = 0f;
            shootCone = 360;
            cooldownTime = 110f;
            minWarmup = 0.8f;
            shootWarmupSpeed = 0.055f;
            warmupMaintainTime = 120f;
            solid = false;
            underBullets = true;
            elevation = 0f;
            unitSort = UnitSorts.weakest;
            drawer = new DrawTurret(){{
                parts.addAll(new RegionPart("-1"){{
                    mirror = true;
                    x = 8f;
                    y = 8f;
                    moveX = -8f;
                    moveY = -8f;
                }}, new RegionPart("-2"){{
                    mirror = true;
                    x = 8f;
                    y = -8f;
                    moveX = -8f;
                    moveY = 8f;
                }}, new RegionPart("-3"){{
                    mirror = true;
                    x = 8f;
                    y = 0f;
                    moveX = -8f;
                    moveY = 0f;
                }}, new RegionPart("-s"){{
                    mirror = false;
                    x = 0f;
                    y = 8f;
                    moveX = 0f;
                    moveY = -8f;
                }}, new RegionPart("-x"){{
                    mirror = false;
                    x = 0f;
                    y = -8f;
                    moveX = 0f;
                    moveY = 8f;
                }}, new RegionPart("-top"){{
                    mirror = false;
                    x = 0f;
                    y = 0f;
                    moveX = 0f;
                    moveY = 0f;
                    moveRot = 720f;
                }});
            }};
            shoot = new ShootBarrel(){{
                shots = 8;
                shotDelay = 6;
                barrels = new float[]{
                    7, 7, -45,
                    -7, -7, 135,
                    7, -7, -135,
                    -7, 7, 45,
                    -8, 0, 90,
                    8, 0, -90,
                    0, 8, 0,
                    0, -8, 180
                };
            }};
            ammoUseEffect = new MultiEffect(new ParticleEffect(){{
                particles = 8;
                sizeFrom = 4;
                sizeTo = 1;
                length = 45;
                lifetime = 40;
                lightOpacity = 0;
                colorFrom = Color.valueOf("ffffff80");
                colorTo = Color.valueOf("78787870");
                cone = 80;
            }}, new WaveEffect(){{
                lifetime = 10;
                sizeFrom = 0;
                sizeTo = 28;
                strokeFrom = 2;
                strokeTo = 0;
                colorFrom = Color.valueOf("eec59180");
                colorTo = Color.white;
            }});
            canOverdrive = false;
            shootSound = HISounds.dd1;
            hasPower = true;
            consumePower(6f);
        }};
        caelum = new ItemTurret("caelum"){{
            requirements(Category.turret, with(Items.copper, 600, Items.graphite, 500, Items.titanium, 200, Items.silicon, 200, Items.plastanium, 300));
            ammo(
                    Items.blastCompound, new FlakBulletType(6f, 33f){{
                        splashDamageRadius = 64f;
                        splashDamage = 255f;
                        knockback = 10;
                        hitSize = 50;
                        shrinkY = 0;
                        hitSound = Sounds.explosionbig;
                        hitSoundVolume = 2;
                        lifetime = 145;
                        status = StatusEffects.blasted;
                        homingDelay = 15;
                        homingPower = 0.08f;
                        homingRange = 120;
                        width = 15;
                        height = 55;
                        sprite = name("missile");
                        backColor = Color.valueOf("ff7055");
                        frontColor = Color.valueOf("e3e3e3");
                        trailLength = 40;
                        trailWidth = 2;
                        trailColor = Color.valueOf("ffffff80");
                        trailChance = 1;
                        trailInterval = 16;
                        trailEffect = new ParticleEffect(){{
                            particles = 3;
                            length = 16;
                            baseLength = 1;
                            lifetime = 45;
                            colorFrom = colorTo = Color.valueOf("e3e3e380");
                            sizeFrom = 3;
                            sizeTo = 0;
                        }};
                        hitShake = 5;
                        ammoMultiplier = 4;
                        reloadMultiplier = 1.7f;
                        shootEffect = Fx.shootTitan;
                        smokeEffect = Fx.shootPyraFlame;
                        hitEffect = new MultiEffect(new ParticleEffect(){{
                            particles = 18;
                            sizeFrom = 10;
                            sizeTo = 0;
                            length = 35;
                            baseLength = 43;
                            lifetime = 35;
                            colorFrom = colorTo = Color.valueOf("737373");
                        }}, new ParticleEffect(){{
                            particles = 32;
                            line = true;
                            sizeFrom = 9;
                            sizeTo = 0;
                            length = 43;
                            baseLength = 33;
                            lifetime = 22;
                            colorFrom = Color.white;
                            colorTo = Color.valueOf("ffe176");
                        }}, new WaveEffect(){{
                            lifetime = 15;
                            sizeFrom = 1;
                            sizeTo = 70;
                            strokeFrom = 8;
                            strokeTo = 0;
                            colorFrom = Color.valueOf("ffe176");
                            colorTo = Color.white;
                        }});
                    }},
                    HIItems.uranium, new BasicBulletType(10f, 360f){{
                        collidesGround = false;
                        status = StatusEffects.melting;
                        statusDuration = 180;
                        pierceArmor = true;
                        hitShake = 2;
                        homingDelay = 15;
                        homingPower = 0.12f;
                        homingRange = 160;
                        lifetime = 98;
                        hitSound = Sounds.shotgun;
                        hitSoundVolume = 0.2f;
                        width = 15;
                        height = 53;
                        shrinkY = 0;
                        ammoMultiplier = 1;
                        sprite = name("rocket");
                        backColor = Color.valueOf("a5b2c2");
                        frontColor = Color.valueOf("e3e3e3");
                        trailLength = 20;
                        trailWidth = 1.7f;
                        trailColor = Color.white;
                        trailChance = 1f;
                        trailInterval = 1f;
                        trailRotation = true;
                        trailEffect = new ParticleEffect(){{
                            particles = 2;
                            length = -40f;
                            baseLength = 0f;
                            lifetime = 15f;
                            cone = 20f;
                            colorFrom = Color.valueOf("e3e3e380");
                            colorTo = Color.valueOf("e3e3e300");
                            sizeFrom = 1f;
                            sizeTo = 5f;
                        }};
                        shootEffect = Fx.shootTitan;
                        smokeEffect = Fx.shootPyraFlame;
                        hitEffect = new MultiEffect(new ParticleEffect(){{
                            particles = 9;
                            sizeFrom = 3f;
                            sizeTo = 0f;
                            length = 33f;
                            baseLength = 6f;
                            lifetime = 25f;
                            colorFrom = colorTo = Color.valueOf("737373");
                        }}, new ParticleEffect(){{
                            particles = 15;
                            line = true;
                            sizeFrom = 4f;
                            sizeTo = lenFrom = 0f;
                            lenTo = 10f;
                            length = 83f;
                            baseLength = 3f;
                            lifetime = 10f;
                            colorFrom = Color.white;
                            colorTo = Color.valueOf("ffe176");
                        }}, new WaveEffect(){{
                            lifetime = 10f;
                            sizeFrom = 1f;
                            sizeTo = strokeFrom = strokeTo = 0f;
                            colorFrom = Color.valueOf("ffe176");
                            colorTo = Color.white;
                        }});
                    }}
            );
            size = 4;
            health = 3500;
            reload = 26f;
            range = 640f;
            maxAmmo = 20;
            ammoPerShot = 2;
            recoil = 4f;
            ammoUseEffect = new ParticleEffect(){{
                particles = 9;
                interp = Interp.pow10Out;
                sizeInterp = Interp.pow5In;
                sizeFrom = 5.5f;
                sizeTo = 0f;
                length = 30f;
                baseLength = 0;
                lifetime = 55f;
                colorFrom = colorTo = Color.valueOf("73737360");
                layer = 49f;
            }};
            canOverdrive = false;
            shoot = new ShootAlternate(18){{
                barrels = 2;
            }};
            xRand = 4;
            liquidCapacity = 30;
            coolant = consumeCoolant(0.5f);
            coolantMultiplier = 1f;
            shootSound = HISounds.dd1;
            targetGround = false;
            targetAir = true;
            inaccuracy = 7.77f;
            shootCone = 270;
            shake = 4;
            rotateSpeed = 1.65f;
        }};
        cloudbreaker = new ItemTurret("cloudbreaker"){{
            requirements(Category.turret, with(Items.graphite, 230, Items.titanium, 220, Items.thorium, 150));
            ammo(
                    Items.titanium, new BasicBulletType(14f, 220f){{
                        lifetime = 25f;
                        width = 14f;
                        height = 25f;
                        trailLength = 5;
                        trailWidth = 1.5f;
                        trailColor = Color.valueOf("8da1e350");
                        trailChance = 0.5f;
                        trailRotation = true;
                        trailEffect = new ParticleEffect(){{
                            sizeInterp = Interp.pow3In;
                            particles = 3;
                            strokeFrom = 1f;
                            strokeTo = lenTo = baseLength = 0f;
                            line = true;
                            lenFrom = 8f;
                            length = 15f;
                            lifetime = 10f;
                            colorFrom = Color.white;
                            colorTo = Color.valueOf("8da1e3");
                            cone = 10f;
                        }};
                        pierceArmor = true;
                        pierce = true;
                        pierceBuilding = false;
                        pierceCap = 3;
                        shootEffect = Fx.bigShockwave;
                        smokeEffect = Fx.shootBigSmoke;
                        hitEffect = new ParticleEffect(){{
                            particles = 15;
                            line = true;
                            strokeFrom = 1f;
                            strokeTo = lenTo = baseLength = 0f;
                            lenFrom = 10f;
                            length = 50f;
                            lifetime = 10f;
                            colorFrom = Color.valueOf("ffe176");
                            colorTo = Color.white;
                            cone = 60f;
                        }};
                        despawnEffect = Fx.none;
                        ammoMultiplier = 2;
                    }},
                    Items.thorium, new BasicBulletType(17.5f, 280f){{
                        lifetime = 25f;
                        rangeChange = 70f;
                        width = 15f;
                        height = 25f;
                        trailLength = 6;
                        trailWidth = 1.8f;
                        trailColor = Color.valueOf("f9a3c750");
                        trailChance = 0.8f;
                        trailRotation = true;
                        trailEffect = new ParticleEffect(){{
                            sizeInterp = Interp.pow3In;
                            particles = 3;
                            strokeFrom = 1;
                            strokeTo = lenTo = baseLength = 0;
                            line = true;
                            lenFrom = 8;
                            length = 15;
                            lifetime = 15;
                            colorFrom = Color.white;
                            colorTo = Color.valueOf("f9a3c7");
                            cone = 10;
                        }};
                        pierceArmor = true;
                        pierce = true;
                        pierceBuilding = false;
                        pierceCap = 5;
                        shootEffect = Fx.bigShockwave;
                        smokeEffect = Fx.shootBigSmoke;
                        hitEffect = new ParticleEffect(){{
                            particles = 15;
                            line = true;
                            strokeFrom = 1;
                            strokeTo = lenTo = baseLength = 0;
                            lenFrom = 10;
                            length = 70;
                            lifetime = 10;
                            colorFrom = Color.valueOf("ffe176");
                            colorTo = Color.white;
                            cone = 60;
                        }};
                        despawnEffect = Fx.none;
                        ammoMultiplier = 3;
                    }},
                    HIItems.uranium, new BasicBulletType(20f, 360f){{
                        lifetime = 25f;
                        rangeChange = 120f;
                        width = 15f;
                        height = 26f;
                        trailLength = 6;
                        trailWidth = 1.8f;
                        trailColor = Color.valueOf("a5b2c250");
                        trailChance = 0.8f;
                        trailRotation = true;
                        status = StatusEffects.melting;
                        statusDuration = 240f;
                        trailEffect = new ParticleEffect(){{
                            sizeInterp = Interp.pow3In;
                            particles = 3;
                            strokeFrom = 1.1f;
                            strokeTo = lenTo = baseLength = 0f;
                            line = true;
                            lenFrom = 8f;
                            length = 45f;
                            lifetime = 10f;
                            colorFrom = Color.white;
                            colorTo = Color.valueOf("a5b2c2");
                            cone = 10f;
                        }};
                        pierceArmor = true;
                        pierce = true;
                        pierceBuilding = false;
                        pierceCap = 10;
                        shootEffect = Fx.bigShockwave;
                        smokeEffect = Fx.shootBigSmoke;
                        hitEffect = new ParticleEffect(){{
                            particles = 15;
                            line = true;
                            strokeFrom = 5;
                            strokeTo = lenTo = baseLength = 0;
                            lenFrom = 16;
                            length = 100;
                            lifetime = 10;
                            colorFrom = Color.valueOf("ffe176");
                            colorTo = Color.white;
                            cone = 60;
                        }};
                        despawnEffect = Fx.none;
                        ammoMultiplier = 4;
                    }}
            );
            size = 3;
            health = 2160;
            range = 320;
            reload = 80;
            shake = 3.5f;
            hasLiquids = true;
            liquidCapacity = 40;
            recoilTime = 30;
            recoil = 1;
            targetAir = true;
            targetGround = true;
            rotateSpeed = 5;
            inaccuracy = 0;
            shootSound = Sounds.artillery;
            ammoUseEffect = Fx.casing3Double;
            maxAmmo = 24;
            ammoPerShot = 6;
            drawer = new DrawTurret(){{
                parts.addAll(new RegionPart("-barrel"){{
                    mirror = false;
                    under = true;
                    progress = PartProgress.recoil;
                    moveY = -4;
                }});
            }};
            coolant = consumeCoolant(0.3f);
        }};
        minigun = new MinigunTurret("minigun"){{
            requirements(Category.turret, with(Items.copper, 350, Items.graphite, 300, Items.titanium, 150, Items.plastanium, 175, Items.thorium, 170));
            ammo(
                    Items.copper, new BasicBulletType(11f, 19f){{
                        width = 5f;
                        height = 7f;
                        lifetime = 25f;
                        ammoMultiplier = 2;
                    }},
                    Items.graphite, new BasicBulletType(13f, 37f){{
                        width = 5.5f;
                        height = 9f;
                        reloadMultiplier = 0.6f;
                        ammoMultiplier = 4;
                        lifetime = 23f;
                    }},
                    Items.pyratite, new BasicBulletType(13f, 24f){{
                        width = 4f;
                        height = 6f;
                        homingPower = 0.07f;
                        reloadMultiplier = 1.3f;
                        ammoMultiplier = 5;
                        lifetime = 23f;
                    }},
                    Items.silicon, new BasicBulletType(13f, 21f){{
                        width = 5f;
                        height = 8f;
                        frontColor = Pal.lightishOrange;
                        backColor = Pal.lightOrange;
                        status = StatusEffects.burning;
                        inaccuracy = 5f;
                        makeFire = true;
                        lifetime = 23f;
                        hitEffect = despawnEffect = new MultiEffect(Fx.hitBulletSmall, Fx.fireHit);
                    }},
                    Items.thorium, new BasicBulletType(15f, 54f){{
                        width = 6f;
                        height = 11f;
                        shootEffect = Fx.shootBig;
                        smokeEffect = Fx.shootBigSmoke;
                        ammoMultiplier = 4f;
                        lifetime = 20f;
                    }},
                    HIItems.uranium, new BasicBulletType(17f, 72f){{
                        width = 7f;
                        height = 13f;
                        shootEffect = Fx.shootBig;
                        smokeEffect = Fx.shootBigSmoke;
                        status = StatusEffects.melting;
                        ammoMultiplier = 4f;
                        lifetime = 18f;
                    }}
            );
            size = 4;
            range = 280f;
            maxSpeed = 27f;
            scaledHealth = 150;
            shootCone = 35f;
            shootSound = Sounds.shoot;
            targetAir = targetGround = true;
            recoil = 3f;
            recoilTime = 90f;
            cooldownTime = 10f;
            inaccuracy = 2f;
            shootEffect = smokeEffect = ammoUseEffect = Fx.none;
            heatColor = Pal.turretHeat;
            barX = 4f;
            barY = -10f;
            barStroke = 1f;
            barLength = 9f;
            shoot = new ShootBarrel(){{
                shots = 2;
                barrels = new float[]{
                        -4f, 0f, 0f,
                        4f, 0f, 0f
                };
            }};
            coolant = consumeCoolant(0.2f);
        }};
        frost = new PowerTurret("frost"){{
            requirements(Category.turret, with(Items.silicon, 600, Items.metaglass, 400, Items.plastanium, 350, HIItems.chromium, 220, HIItems.highEnergyFabric, 120));
            consumePower(12f);
            consumeLiquid(Liquids.cryofluid, 1f);
            range = 55 * 8;
            reload = 80;
            size = 5;
            shootSound = Sounds.release;
            shootWarmupSpeed = 0.06f;
            minWarmup = 0.9f;
            health = 4160;
            smokeEffect = Fx.none;
            rotateSpeed = 2.5f;
            recoil = 2f;
            recoilTime = 60f;
            shootEffect = HIFx.ellipse(30, 30, 15, Color.valueOf("c0ecff"));
            drawer = new DrawTurret(){{
                parts.add(new frostWing(){{
                    x = 0;
                    y = -7;
                    layer = Layer.effect;
                }}, new BowHalo(){{
                    progress = PartProgress.warmup.delay(0.8f);
                    x = 0;
                    y = 18;
                    stroke = 3;
                    w2 = h2 = 0;
                    w1 = 3;
                    h1 = 6;
                    radius = 4;
                    color = Color.valueOf("c0ecff");
                }});
            }};
            BulletType frostFragBullrt = new aimToPosBulletType(){{
                damage = 180;
                splashDamage = 180;
                splashDamageRadius = 3 * 8;
                speed = 10;
                lifetime = 140;
                hitEffect = despawnEffect = new MultiEffect(new ExplosionEffect(){{
                    lifetime = 40f;
                    sparkColor = Color.valueOf("c0ecff");
                    waveRad = smokeSize = smokeSizeBase = 0f;
                    smokes = 0;
                    sparks = 5;
                    sparkRad = 4 * 8;
                    sparkLen = 5f;
                    sparkStroke = 2f;
                }}, new Effect(60, e -> DrawFunc.drawSnow(e.x, e.y, 2 * 8 * e.foutpow(), 0, Color.valueOf("c0ecff"))));
                trailInterval = 0.5f;
                trailEffect = new Effect(120, e -> {
                    Draw.color(Color.valueOf("c0ecff"));
                    Fill.circle(e.x, e.y, 3 * e.foutpow());
                });
                trailLength = 16;
                trailWidth = 3;
                trailColor = Color.valueOf("c0ecff");
                buildingDamageMultiplier = 0.5f;
            }
                @Override
                public void draw(Bullet b) {
                    super.draw(b);
                    Draw.color(Color.valueOf("c0ecff"));
                    Drawf.tri(b.x, b.y, 5, 12, b.rotation());
                    Drawf.tri(b.x, b.y, 5, 5, b.rotation() - 180);
                    Lines.stroke(1, Color.valueOf("6d90bc"));
                    Lines.lineAngle(b.x, b.y, b.rotation(), 9f);
                    Lines.lineAngle(b.x, b.y, b.rotation() - 180, 3f);
                }
                @Override
                public void update(Bullet b) {
                    super.update(b);
                    if(b.timer.get(1, 6)) HIFx.normalIceTrail.at(b.x + Mathf.random(-6, 6), b.y + Mathf.random(-6, 6), 7, Color.valueOf("c0ecff"));
                }
            };
            shootType = new BulletType(){{
                reflectable = false;
                speed = 20;
                lifetime = 22;
                damage = 550;
                splashDamage = 400;
                splashDamageRadius = 64;
                trailColor = Color.valueOf("c0ecff");
                trailLength = 8;
                trailWidth = 5;
                trailEffect = new Effect(40, e -> DrawFunc.drawSnow(e.x, e.y, 12 * e.fout(), 360 * e.fin(), Color.valueOf("c0ecff")));
                trailInterval = 3;
                fragBullets = 4;
                fragBullet = frostFragBullrt;
                status = StatusEffects.freezing;
                hitEffect = despawnEffect = new ExplosionEffect(){{
                    lifetime = 40f;
                    waveStroke = 5f;
                    waveLife = 8f;
                    waveColor = Color.valueOf("6d90bc");
                    sparkColor = Color.valueOf("c0ecff");
                    waveRad = 64;
                    smokeSize = smokes = 0;
                    smokeSizeBase = 0f;
                    sparks = 6;
                    sparkRad = 80;
                    sparkLen = 7f;
                    sparkStroke = 3f;
                }};
                shootEffect = smokeEffect = Fx.none;
                buildingDamageMultiplier = 0.5f;
            }
                @Override
                public void hitEntity(Bullet b, Hitboxc entity, float health) {
                    if(!pierce || b.collided.size >= pierceCap) explode(b);
                    super.hitEntity(b, entity, health);
                }
                @Override
                public void hit(Bullet b) {
                    explode(b);
                    super.hit(b);
                }
                public void explode(Bullet b){
                    if(!(b.owner instanceof PowerTurretBuild tb)) return;
                    for(int i = 0; i < 4; i++){
                        float angleOffset = i * 40 - (4 - 1) * 40 / 2f;
                        Position p1 = HIGet.pos(tb.x, tb.y);
                        Position p2 = HIGet.pos(b.x, b.y);
                        Position[] pos = {p1, p2};
                        frostFragBullrt.create(tb, tb.team, tb.x, tb.y, tb.rotation - 180 + angleOffset + Mathf.random(-5, 5), -1, 1, 1, pos);
                    }
                }
                @Override
                public void createFrags(Bullet b, float x, float y) { }
                @Override
                public void draw(Bullet b) {
                    super.draw(b);
                    Draw.color(Color.valueOf("c0ecff"));
                    Drawf.tri(b.x, b.y, 15, 18, b.rotation());
                    Drawf.tri(b.x, b.y, 15, 6, b.rotation() - 180);
                    Lines.stroke(1, Color.valueOf("6d90bc"));
                    Lines.lineAngle(b.x, b.y, b.rotation(), 15f);
                    Lines.lineAngle(b.x, b.y, b.rotation() - 180, 4f);
                }
            };
        }};
        thermoelectricIon = new PowerTurret("thermoelectric-ion"){{
            requirements(Category.turret, with(Items.lead, 1100,Items.silicon, 1200, Items.plastanium, 600, Items.surgeAlloy, 220, HIItems.highEnergyFabric, 200));
            size = 5;
            health = 3650;
            armor = 8;
            accurateDelay = false;
            shake = 20f;
            inaccuracy = 0;
            recoil = 5f;
            cooldownTime = 80;
            liquidCapacity = 120;
            canOverdrive = false;
            recoilTime = 80f;
            unitSort = UnitSorts.strongest;
            shootY = 8f;
            shoot.firstShotDelay = 60f;
            shootSound = Sounds.laser;
            shootEffect = new ParticleEffect(){{
                particles = 1;
                sizeFrom = 10f;
                sizeTo = length = baseLength = cone = 0f;
                lifetime = 55f;
                colorFrom = colorTo = HIPal.echoFlameYellow;
            }};
            chargeSound = Sounds.lasercharge;
            shootType = new PointBulletType(){{
                lifetime = 50f;
                speed = 50f;
                damage = 3220f;
                splashDamageRadius = 64f;
                splashDamage = 5660f;
                ammoMultiplier = 1;
                hitSound = Sounds.plasmaboom;
                chargeEffect = new MultiEffect(new ParticleEffect(){{
                    particles = 25;
                    sizeInterp = Interp.pow5In;
                    sizeFrom = 0f;
                    sizeTo = 4f;
                    length = 100f;
                    baseLength = -100f;
                    lifetime = 60f;
                    colorFrom = Color.valueOf("f2ff9c40");
                    colorTo = HIPal.echoFlameYellow;
                }}, new ParticleEffect(){{
                    sizeInterp = Interp.pow3In;
                    particles = 1;
                    sizeFrom = length = baseLength = 0f;
                    sizeTo = 15f;
                    lifetime = 56f;
                    colorFrom = colorTo = HIPal.echoFlameYellow;
                }}, new ParticleEffect(){{
                    particles = 1;
                    sizeFrom = 15f;
                    sizeTo = 10f;
                    length = baseLength = 0f;
                    startDelay = 55f;
                    lifetime = 21f;
                    colorFrom = colorTo = HIPal.echoFlameYellow;
                }});
                smokeEffect = new ParticleEffect(){{
                    particles = 10;
                    sizeFrom = 8f;
                    interp = Interp.pow5Out;
                    sizeInterp = Interp.pow10In;
                    sizeTo = baseLength = 0f;
                    length = 60f;
                    lifetime = 125f;
                    colorFrom = Color.valueOf("f2ff9c80");
                    colorTo = Color.valueOf("f2ff9c40");
                }};
                despawnEffect = new ParticleEffect(){{
                    particles = 15;
                    sizeFrom = 3f;
                    sizeTo = baseLength = 0f;
                    length = 100f;
                    lifetime = 10f;
                    colorFrom = colorTo = HIPal.echoFlameYellow;
                    cone = 60f;
                }};
                hitEffect = new MultiEffect(new ParticleEffect(){{
                    particles = 1;
                    sizeFrom = 20f;
                    sizeTo = length = baseLength = 0f;
                    lifetime = 55f;
                    colorFrom = colorTo = HIPal.echoFlameYellow;
                }}, new WaveEffect(){{
                    lifetime = 13;
                    sizeFrom = 5f;
                    sizeTo = 150f;
                    strokeFrom = 50f;
                    strokeTo = 0f;
                    colorFrom = HIPal.echoFlameYellow;
                    colorTo = Color.valueOf("f2ff9c40");
                }}, new ParticleEffect(){{
                    particles = 30;
                    sizeFrom = 8f;
                    sizeTo = baseLength = 0f;
                    length = 70f;
                    lifetime = 20f;
                    colorFrom = colorTo = HIPal.echoFlameYellow;
                }}, Fx.titanExplosion, Fx.titanSmoke);
                hitColor = HIPal.echoFlameYellow;
                trailSpacing = 12;
                trailEffect = new MultiEffect(new ParticleEffect(){{
                    sizeInterp = Interp.pow5Out;
                    particles = 1;
                    length = strokeFrom = cone = 0f;
                    baseLength = 0.2f;
                    lifetime = 11f;
                    line = true;
                    randLength = false;
                    lenFrom = lenTo = 13f;
                    strokeTo = 8.5f;
                    colorFrom = colorTo = HIPal.echoFlameYellow;
                }}, new ParticleEffect(){{
                    particles = 1;
                    length = strokeTo = cone = 0f;
                    baseLength = 0.2f;
                    startDelay = 8.5f;
                    lifetime = 41.5f;
                    line = true;
                    randLength = false;
                    lenFrom = lenTo = 13f;
                    strokeFrom = 8.5f;
                    colorFrom = colorTo = HIPal.echoFlameYellow;
                }}, new ParticleEffect(){{
                    particles = 2;
                    sizeFrom = 3f;
                    interp = Interp.pow10Out;
                    sizeInterp = Interp.pow5In;
                    sizeTo = baseLength = 0f;
                    length = 13f;
                    lifetime = 55f;
                    colorFrom = colorTo = HIPal.echoFlameYellow;
                }});
                fragBullets = 4;
                fragBullet = new PointBulletType(){{
                    trailSpacing = 9;
                    trailEffect = new MultiEffect(new ParticleEffect(){{
                        sizeInterp = Interp.pow5Out;
                        particles = 1;
                        length = strokeFrom = cone = 0f;
                        baseLength = 1f;
                        lifetime = 10f;
                        line = true;
                        randLength = false;
                        lenFrom = lenTo = 10f;
                        strokeTo = 5f;
                        colorFrom = colorTo = HIPal.echoFlameYellow;
                    }}, new ParticleEffect(){{
                        particles = 1;
                        length = strokeTo = cone = 0f;
                        baseLength = 1f;
                        startDelay = 8.5f;
                        lifetime = 11.5f;
                        line = true;
                        randLength = false;
                        lenFrom = lenTo = 10f;
                        strokeFrom = 8.5f;
                        colorFrom = colorTo = HIPal.echoFlameYellow;
                    }});
                    lifetime = 8f;
                    speed = 10f;
                    status = HIStatusEffects.echoFlame;
                    statusDuration = 0.25f;
                    buildingDamageMultiplier = 0.8f;
                    damage = 54f;
                    splashDamageRadius = 8f;
                    splashDamage = 7f;
                    hitShake = 1f;
                    hitSound = Sounds.laser;
                    hitEffect = new MultiEffect(new ParticleEffect(){{
                        particles = 1;
                        sizeFrom = 8f;
                        sizeTo = length = baseLength = 0f;
                        lifetime = 21f;
                        colorFrom = colorTo = HIPal.echoFlameYellow;
                    }}, new ParticleEffect(){{
                        particles = 8;
                        length = 30f;
                        baseLength = lenTo = strokeTo = 0f;
                        lifetime = 15f;
                        line = true;
                        lenFrom = 30f;
                        strokeFrom = 3.3f;
                        colorFrom = colorTo = HIPal.echoFlameYellow;
                    }});
                    despawnEffect = Fx.none;
                }};
            }};
            consumePower(55);
            coolant = consumeCoolant(1f);
            coolantMultiplier = 1f;
            reload = 500f;
            rotateSpeed = 2.4f;
            range = 550f;
            researchCostMultiplier = 0.6f;
        }};
        fiammetta = new ItemTurret("fiammetta"){{
            requirements(Category.turret, with(Items.silicon, 800, Items.plastanium, 650, Items.surgeAlloy, 550, HIItems.highEnergyFabric, 350, HIItems.chromium, 400));
            BulletType fall = new BulletType(){{
                speed = 0;
                lifetime = 20;
                collides = collidesTiles = hittable = absorbable = false;
                collidesAir = collidesGround = true;
                splashDamage = 3500;
                splashDamageRadius = 14 * 8f;
                despawnEffect = hitEffect = new MultiEffect(HIFx.expFtEffect(10, 15, splashDamageRadius, 30, 0.2f), HIFx.fiammettaExp(splashDamageRadius), new Effect(20, e -> {
                    Lines.stroke(16 * e.fout(), HIItems.highEnergyFabric.color);
                    Lines.circle(e.x, e.y, (splashDamageRadius + 56) * e.fin());
                }));
                keepVelocity = false;
                buildingDamageMultiplier = 0.6f;
                hitSound = despawnSound = Sounds.largeExplosion;
                despawnShake = hitShake = 8;
            }
                @Override
                public void draw(Bullet b) {
                    TextureRegion region = Core.atlas.find(name("mb-fireworks"));
                    if(b.time < 10){
                        float fin = b.time/10, fout = 1 - fin;
                        float ww = 15 * 8, hh = 15 * 8 * fout;
                        Draw.color(HIItems.highEnergyFabric.color);
                        Draw.alpha(fin);
                        Draw.rect(region, b.x, b.y, ww, hh, b.rotation() - 90);
                    }
                    Draw.color(HIItems.highEnergyFabric.color);
                    Draw.alpha(b.fin());
                    Fill.circle(b.x, b.y, 20 * (b.time < 10 ? b.fin() * 2 : b.fout() * 2));
                }
            };
            ammo(
                    Items.phaseFabric, new BulletType(){{
                        chargeEffect = HIFx.aimEffect(40, HIItems.highEnergyFabric.color, 1.5f, range, 13);
                        ammoMultiplier = 2;
                        damage = 0;
                        collides = collidesTiles = false;
                        splashDamageRadius = 10 * 8;
                        splashDamage = 400;
                        lifetime = 30;
                        speed = 15;
                        pierce = true;
                        pierceBuilding = true;
                        hittable = false;
                        absorbable = false;
                        reflectable = false;
                        intervalBullet = new BulletType(){{
                            lifetime = 32;
                            speed = 0;
                            despawnEffect = hitEffect = new MultiEffect(new Effect(30, e -> {
                                float r = Math.min(10 * 8 * e.fin(), 6 * 8);
                                Draw.color(HIItems.highEnergyFabric.color.cpy().a(e.fout()));
                                Fill.circle(e.x, e.y, r);
                                float ww = r * 2f, hh = r * 2f;
                                Draw.color(HIItems.highEnergyFabric.color.cpy().a(e.fout()));
                                Draw.rect(Core.atlas.find(name("firebird-light")), e.x, e.y, ww, hh);
                            }), HIFx.expFtEffect(5, 12, 6 * 4, 30, 0.2f));
                            despawnSound = hitSound = Sounds.explosion;
                            collides = absorbable = hittable = false;
                            splashDamageRadius = 6 * 8;
                            splashDamage = 300;
                        }
                            @Override
                            public void draw(Bullet b) {
                                super.draw(b);
                                float ft = (b.time > 16 ? b.fout() * 2 : 1);
                                Lines.stroke(5, HIItems.highEnergyFabric.color);
                                Lines.circle(b.x, b.y, 12 * ft);
                                Lines.poly(b.x, b.y, 3, 12 * ft, b.fout() * 180);
                            }
                        };
                        intervalDelay = 4;
                        intervalSpread = intervalRandomSpread = 0;
                        bulletInterval = 4;
                        hitSize = 20;
                        despawnEffect = new MultiEffect(new Effect(30, e -> {
                            float r = Math.min(16 * 8 * e.fin(), 10 * 8);
                            Draw.color(HIItems.highEnergyFabric.color.cpy().a(e.fout()));
                            Fill.circle(e.x, e.y, r);
                            float ww = r * 2f, hh = r * 2f;
                            Draw.color(HIItems.highEnergyFabric.color.cpy().a(e.fout()));
                            Draw.rect(Core.atlas.find(name("firebird-light")), e.x, e.y, ww, hh);
                        }), HIFx.expFtEffect(6, 15, 10 * 4, 30, 0.2f));
                        despawnSound = Sounds.explosion;
                        hitEffect = Fx.none;
                        trailLength = 15;
                        trailColor = HIItems.highEnergyFabric.color;
                        trailWidth = 4;
                        trailRotation = true;
                        trailEffect = new Effect(15, e ->{
                            color(e.color);
                            for(int x : new int[]{-20, 20}){
                                Tmp.v1.set(x, -10).rotate(e.rotation - 90);
                                Fill.circle(e.x + Tmp.v1.x, e.y + Tmp.v1.y, 4 * e.foutpow());
                            }
                        });
                        trailInterval = 0.1f;
                    }
                        @Override
                        public void draw(Bullet b) {
                            super.draw(b);
                            Draw.color(HIItems.highEnergyFabric.color);
                            Draw.rect(Core.atlas.find(name("phx")), b.x, b.y,48, 48,  b.rotation() - 90);
                        }
                    },
                    HIItems.highEnergyFabric, new ArtilleryBulletType(){{
                        speed = 10;
                        ammoMultiplier = 2;
                        splashDamage = 3500;
                        splashDamageRadius = 14 * 8f;
                        hittable = absorbable = false;
                        collides = collidesTiles = false;
                        collidesAir = collidesGround = false;
                        despawnEffect = Fx.none;
                        hitEffect = Fx.none;
                        trailEffect = Fx.none;
                        fragOnHit = false;
                        rangeChange = 10 * 8;
                        trailLength = 20;
                        trailWidth = 12;
                        trailColor = HIItems.highEnergyFabric.color.cpy().a(0.6f);
                        buildingDamageMultiplier = 0.3f;
                    }
                        @Override
                        public void update(Bullet b) {
                            super.update(b);
                            HIFx.normalTrail.at(b.x + Mathf.random(-10, 10), b.y + Mathf.random(-10, 10), 15 * b.fin(), HIItems.highEnergyFabric.color.cpy().a(0.6f));
                        }
                        @Override
                        public void updateTrail(Bullet b) {
                            if(!headless && trailLength > 0){
                                if(b.trail == null){
                                    b.trail = new Trail(trailLength);
                                }
                                b.trail.length = 2 + (int) (trailLength * b.fin());
                                b.trail.update(b.x, b.y, trailInterp.apply(b.fin()) * (1f + (trailSinMag > 0 ? Mathf.absin(Time.time, trailSinScl, trailSinMag) : 0f)));
                            }
                        }
                        @Override
                        public void draw(Bullet b) {
                            TextureRegion region = Core.atlas.find(name("mb-fireworks"));
                            float ww = 15 * 8 * b.fin(), hh = 15 * 8 * b.fin();
                            Draw.color(HIItems.highEnergyFabric.color);
                            Draw.rect(region, b.x, b.y, ww, hh, b.rotation() - 90);
                            drawTrail(b);
                        }
                        @Override
                        public void drawTrail(Bullet b) {
                            if(trailLength > 0 && b.trail != null){
                                float z = Draw.z();
                                Draw.z(z - 0.0001f);
                                b.trail.draw(trailColor, 2 + trailWidth * b.fin());
                                Draw.z(z);
                            }
                        }
                        @Override
                        public void createFrags(Bullet b, float x, float y) {
                            fall.create(b, b.x, b.y, b.rotation());
                        }
                    }
            );
            size = 5;
            health = 4350;
            shake = 10;
            reload = 180;
            ammoPerShot = 5;
            maxAmmo = 15;
            range = 460f;
            minRange = 160f;
            shootSound = Sounds.artillery;
            recoil = 5;
            coolant = consumeCoolant(30f / 60f);
            coolantMultiplier = 1f;
            canOverdrive = false;
        }};
        fireworks = new MultiBulletTurret("fireworks"){{
            requirements(Category.turret, with(Items.silicon, 600, Items.graphite, 600, Items.plastanium, 550, HIItems.uranium, 400, HIItems.heavyAlloy, 300));
            ammo(Items.blastCompound, new BulletType[]{
                    new FireWorkBulletType(120, 5, name("mb-fireworks"), Color.valueOf("FF8097"), 6 * 8){{
                        outline = true;
                        trailInterval = 20;
                        trailEffect = new ExplosionEffect(){{
                            lifetime = 60f;
                            waveStroke = 5f;
                            waveLife = 8f;
                            waveColor = Color.white;
                            sparkColor = Pal.lightOrange;
                            smokeColor = Pal.darkerGray;
                            waveRad = 0;
                            smokeSize = 4;
                            smokes = 7;
                            smokeSizeBase = 0f;
                            sparks = 10;
                            sparkRad = 3 * 8;
                            sparkLen = 6f;
                            sparkStroke = 2f;
                        }};
                        trailWidth = 2.4f;
                        trailLength = 10;
                        pierce = true;
                        pierceCap = 3;
                        fire = new colorFire(false, 2.3f, 60){{
                            stopFrom = 0.55f;
                            stopTo = 0.55f;
                            rotSpeed = 666;
                        }};
                        num = 15;
                    }},
                    new BulletType(){{
                        ammoMultiplier = 1;
                        damage = 0;
                        speed = 0;
                        lifetime = 0;
                        fragBullet = new FireWorkBulletType(150, 6.7f, name("mb-fireworks"), Color.valueOf("FFD080"), 12 * 8){{
                            outline = true;
                            trailWidth = 3.5f;
                            trailLength = 10;
                            trailInterval = 0;
                            width = 22;
                            height = 22;
                            fire = new colorFire(false, 3.6f, 60){{
                                stopFrom = 0.7f;
                                stopTo = 0.7f;
                                rotSpeed = 666;
                                hittable = true;
                            }};
                            textFire = new spriteBullet(name("fire-fireworks1"));
                            status = StatusEffects.none;
                            num = 18;
                        }
                            @Override
                            public void update(Bullet b) {
                                super.update(b);
                                b.rotation(b.rotation() + Time.delta * 2.2f);
                                if(b.timer.get(3, 6)) HIFx.ellipse(14, 8/2, 40, color).at(b.x, b.y, b.rotation());
                            }
                        };
                        fragBullets = 1;
                        collides = false;
                        absorbable = false;
                        hittable = false;
                        despawnEffect = hitEffect = Fx.none;
                    }
                        public void createFrags(Bullet b, float x, float y){
                            if(fragBullet != null && (fragOnAbsorb || !b.absorbed)){
                                fragBullet.create(b, b.x, b.y, b.rotation() - 60);
                            }
                        }
                    },
                    new FireWorkBulletType(120, 5, name("mb-fireworks"), Color.valueOf("FFF980"), 6 * 8){{
                        outline = true;
                        trailInterval = 0;
                        trailWidth = 2f;
                        trailLength = 10;
                        weaveMag = 8f;
                        weaveScale = 2f;
                        fire = new colorFire(false, 2.3f, 60){{
                            stopFrom = 0.55f;
                            stopTo = 0.55f;
                            rotSpeed = 666;
                            hittable = true;
                        }};
                        textFire = new spriteBullet(name("fire-fireworks2"));
                        status = StatusEffects.none;
                        num = 18;
                    }},
                    new FireWorkBulletType(120, 5, name("mb-fireworks"), Color.valueOf("80FF9D"), 6 * 8){{
                        outline = true;
                        trailInterval = 0;
                        trailWidth = 2.4f;
                        trailLength = 10;
                        homingPower = 1;
                        homingRange = 32 * 8;
                        width = 10;
                        height = 10;
                        status = StatusEffects.electrified;
                        fire = new colorFire(false, 2.3f, 60){{
                            stopFrom = 0.55f;
                            stopTo = 0.55f;
                            rotSpeed = 666;
                        }};
                        num = 10;
                    }},
                    new BulletType(){{
                        ammoMultiplier = 1;
                        damage = 0;
                        speed = 0;
                        lifetime = 0;
                        fragBullet = new FireWorkBulletType(110, 6, name("mb-fireworks"), Color.valueOf("80B5FF"), 8 * 8){{
                            outline = true;
                            trailInterval = 0;
                            trailWidth = 3f;
                            trailLength = 10;
                            width = 19;
                            height = 19;
                            status = StatusEffects.wet;
                            weaveMag = 8;
                            weaveScale = 6;
                            weaveRandom = false;
                            fire = new colorFire(false, 2.8f, 60){{
                                stopFrom = 0.55f;
                                stopTo = 0.55f;
                                rotSpeed = 666;
                            }};
                            num = 20;
                        }
                            public void updateWeaving(Bullet b){
                                if(weaveMag != 0 && b.data instanceof Integer){
                                    b.vel.rotateRadExact((float)Math.sin((b.time + Math.PI * weaveScale/2f) / weaveScale) * weaveMag * Time.delta * Mathf.degRad * (int)b.data);
                                }
                            }
                        };
                        fragBullets = 2;
                        collides = false;
                        absorbable = false;
                        hittable = false;
                        despawnEffect = hitEffect = Fx.none;
                    }
                        public void createFrags(Bullet b, float x, float y){
                            if(fragBullet != null && (fragOnAbsorb || !b.absorbed)){
                                for(int i : new int[]{-1, 1}) fragBullet.create(b, b.team, b.x, b.y, b.rotation() - 10 * i, -1, 1, 1, i);
                            }
                        }
                    },
                    new BulletType(){{
                        ammoMultiplier = 1;
                        damage = 0;
                        speed = 0;
                        lifetime = 0;
                        fragBullet = new FireWorkBulletType(100, 5, name("mb-fireworks"), Color.valueOf("D580FF"), 4 * 8){{
                            outline = true;
                            trailInterval = 0;
                            trailWidth = 2f;
                            trailLength = 10;
                            width = 9;
                            height = 9;
                            status = StatusEffects.sapped;
                            fire = new colorFire(true, 4, 60){{
                                hittable = true;
                            }};
                        }

                            @Override
                            public void update(Bullet b) {
                                super.update(b);
                                if(b.data instanceof Float){
                                    if(b.time > 10) b.rotation(Angles.moveToward(b.rotation(), (float) b.data, Time.delta * 0.5f));
                                }
                            }
                        };
                        fragBullets = 3;
                        collides = false;
                        absorbable = false;
                        hittable = false;
                        despawnEffect = hitEffect = Fx.none;
                    }
                        public void createFrags(Bullet b, float x, float y){
                            if(fragBullet != null && (fragOnAbsorb || !b.absorbed)){
                                for(int i : new int[]{-1, 0, 1}) fragBullet.create(b, b.team, b.x, b.y, b.rotation() - 10 * i, -1, 1, 1, b.rotation());
                            }
                        }
                    },
                    new FireWorkBulletType(125, 5, name("mb-fireworks"), Color.valueOf("FF7DF4"), 10 * 8){{
                        outline = true;
                        trailInterval = 0;
                        trailWidth = 2.4f;
                        trailLength = 10;
                        status = StatusEffects.none;
                        textFire = new spriteBullet(name("fire-fireworks3"), 128, 128);
                        fire = new colorFire(false, 3f, 60){{
                            stopFrom = 0.6f;
                            stopTo = 0.6f;
                            rotSpeed = 666;
                            hittable = true;
                        }};
                    }}
            });
            size = 5;
            inaccuracy = 3;
            shootEffect = HIFx.fireworksShoot(90);
            smokeEffect = Fx.none;
            scaledHealth = 180;
            range = 320;
            shake = 2f;
            recoil = 2f;
            reload = 10;
            shootY = 20;
            rotateSpeed = 2.6f;
            coolant = consumeCoolant(36f / 60f);
            coolantMultiplier = 0.85f;
            shootSound = Sounds.missile;
            shootCone = 16;
            canOverdrive = false;
            maxAmmo = 10;
        }};
        //turrets-erekir
        tracer = new ItemTurret("tracer"){{
            requirements(Category.turret, with(Items.silicon, 550, Items.beryllium, 300, Items.oxide, 220, Items.tungsten, 300, Items.thorium, 400));
            ammo(
                    Items.silicon, new CtrlMissileBulletType(name("tracer-missile"), -1, -1){{
                        collidesGround = targetGround = false;
                        speed = 5;
                        lifetime = 180;
                        damage = 16;
                        ammoMultiplier = 1;
                        splashDamageRadius = 50;
                        splashDamage = 45;
                        hitShake = 3;
                        shootEffect = Fx.none;
                        smokeEffect = Fx.shootSmallFlame;
                        despawnEffect = Fx.none;
                        trailColor = Color.valueOf("c0ecff");
                        trailLength = 8;
                        hitEffect = new MultiEffect(new ParticleEffect(){{
                            particles = 23;
                            sizeFrom = 13;
                            sizeTo = 0;
                            length = 45;
                            baseLength = 30;
                            interp = sizeInterp = Interp.pow10Out;
                            region = name("diamond");
                            lifetime = 85;
                            colorFrom = Color.valueOf("c0ecff");
                            colorTo = Color.valueOf("c0ecff00");
                        }}, new WaveEffect(){{
                            lifetime = 11;
                            sizeFrom = strokeTo = 0;
                            sizeTo = 75;
                            strokeFrom = 5;
                            colorFrom = colorTo = Color.valueOf("c0ecff");
                        }});
                        fragLifeMin = 0.3f;
                        fragBullets = 4;
                        fragBullet = new FlakBulletType(4f, 11){{
                            status = StatusEffects.slow;
                            statusDuration = 6;
                            homingRange = 300;
                            homingPower = 0.15f;
                            splashDamageRadius = 35;
                            splashDamage = 48;
                            hitSize = 12;
                            hitSound = Sounds.explosion;
                            shootEffect = Fx.none;
                            smokeEffect = Fx.none;
                            hitEffect = new MultiEffect(new ParticleEffect(){{
                                particles = 8;
                                sizeFrom = 5f;
                                sizeTo = baseLength = 0f;
                                length = 35f;
                                region = name("diamond");
                                lifetime = 25f;
                                colorFrom = colorTo = Color.valueOf("c0ecff");
                            }}, new WaveEffect(){{
                                lifetime = 15f;
                                sizeFrom = strokeTo = 0f;
                                sizeTo = 35f;
                                strokeFrom = 2f;
                                colorFrom = colorTo = Color.valueOf("c0ecff");
                            }});
                            despawnEffect = new ParticleEffect(){{
                                particles = 1;
                                sizeFrom = 3f;
                                sizeTo = length = baseLength = 0f;
                                lifetime = 8f;
                                colorFrom = colorTo = Color.valueOf("c0ecff");
                            }};
                            sprite = name("tracer-bullet");
                            frontColor = Color.white;
                            backColor = trailColor = Color.valueOf("c0ecff");
                            trailLength = 11;
                            trailWidth = 2f;
                            shrinkX = shrinkY = 0f;
                            width = height = 4f;
                            lifetime = 30f;
                        }};

                    }}
            );
            size = 5;
            health = 2780;
            armor = 5;
            reload = 120;
            shootSound = Sounds.missileLarge;
            shake = 2;
            consumePower(10);
            minWarmup = 0.8f;
            warmupMaintainTime = 30;
            shootWarmupSpeed = 0.06f;
            shootY = 0;
            outlineColor = Color.valueOf("2d2f39");
            shoot = new ShootBarrel(){{
                shots = 8;
                shotDelay = 4;
                barrels = new float[]{
                        12, 11, -45,
                        12, -13, -135,
                        -12, 11, 45,
                        -12, -13, 135
                };
            }};
            targetGround = false;
            range = 600;
            recoil = 0;
            drawer = new DrawTurret("reinforced-"){{
                parts.addAll(new RegionPart("-front"){{
                    mirror = true;
                    under = true;
                    x = 12;
                    y = 12;
                    moveX = -11;
                    moveY = -10;
                }}, new RegionPart("-back"){{
                    mirror = true;
                    under = true;
                    x = 12;
                    y = -12;
                    moveX = -11;
                    moveY = 12;
                }}, new HaloPart(){{
                    progress = PartProgress.warmup;
                    moveX = -16;
                    moveY = 15;
                    rotateSpeed = radius = triLength = haloRadius = haloRotation = 0;
                    shapeRotation = 45;
                    shapes = 1;
                    color = Color.white;
                    colorTo = Color.valueOf("c0ecff");
                    layer = 110;
                    tri = true;
                    radiusTo = 10;
                    triLengthTo = 16;
                }}, new HaloPart(){{
                    progress = PartProgress.warmup;
                    moveX = 16;
                    moveY = 15;
                    rotateSpeed = radius = triLength = haloRadius = haloRotation = 0;
                    shapeRotation = -45;
                    shapes = 1;
                    color = Color.valueOf("ffffff00");
                    colorTo = Color.valueOf("c0ecff");
                    layer = 110;
                    tri = true;
                    radiusTo = 10;
                    triLengthTo = 16;
                }}, new ShapePart(){{
                    progress = PartProgress.warmup;
                    colorTo = color = Color.valueOf("c0ecff");
                    layer = 110;
                    stroke = radius = 0;
                    strokeTo = 2;
                    circle = hollow = true;
                    radiusTo = 55;
                }}, new HaloPart(){{
                    progress = PartProgress.warmup;
                    shapes = 4;
                    color = Color.valueOf("ffffff00");
                    colorTo = Color.valueOf("c0ecff");
                    layer = 110;
                    tri = true;
                    radius = triLength = haloRadius = haloRotation = 0;
                    radiusTo = 8;
                    triLengthTo = 30;
                    haloRadiusTo = 55;
                    haloRotateSpeed = -0.9f;
                }}, new HaloPart(){{
                    progress = PartProgress.warmup;
                    moveX = 16;
                    moveY = -17;
                    rotateSpeed = radius = triLength = haloRadius = haloRotation = 0;
                    shapeRotation = -135;
                    shapes = 1;
                    color = Color.valueOf("ffffff00");
                    colorTo = Color.valueOf("c0ecff");
                    layer = 110;
                    tri = true;
                    radiusTo = 10;
                    triLengthTo = 16;
                }}, new HaloPart(){{
                    progress = PartProgress.warmup;
                    moveX = -16;
                    moveY = -17;
                    rotateSpeed = radius = triLength = haloRadius = haloRotation = 0;
                    shapeRotation = 135;
                    shapes = 1;
                    color = Color.valueOf("ffffff00");
                    colorTo = Color.valueOf("c0ecff");
                    layer = 110;
                    tri = true;
                    radiusTo = 10;
                    triLengthTo = 16;
                }});
            }};
            inaccuracy = 20;
            shootCone = 360;
            rotateSpeed = 1.66f;
            maxAmmo = 24;
            ammoPerShot = 2;
            consumeAmmoOnce = false;
            squareSprite = false;
            researchCostMultiplier = 0.6f;
        }};
        shadow = new ItemTurret("shadow"){{
            requirements(Category.turret, with(Items.graphite, 900, Items.silicon, 500, Items.surgeAlloy, 800, Items.tungsten, 1200, Items.carbide, 400));
            ammo(
                    Items.tungsten, new BasicBulletType(15, 420){{
                        inaccuracy = 1;
                        splashDamageRadius = 38;
                        splashDamage = 180;
                        buildingDamageMultiplier = 0.3f;
                        lifetime = 36;
                        width = 20;
                        height = 33;
                        pierce = true;
                        pierceCap = 3;
                        pierceArmor = true;
                        status = StatusEffects.slow;
                        statusDuration = 33;
                        backColor = frontColor = trailColor = Color.valueOf("ffe176");
                        trailLength = 13;
                        trailWidth = 4.5f;
                        trailChance = 1;
                        trailInterval = 8;
                        trailEffect = new ParticleEffect(){{
                            region = name("diamond");
                            particles = 2;
                            sizeFrom = 6;
                            sizeTo = 0;
                            length = 10;
                            lifetime = 30;
                            interp = Interp.pow10Out;
                            sizeInterp = Interp.pow5In;
                            colorFrom = Color.valueOf("ffa166A0");
                            colorTo = Color.valueOf("ffa16650");
                        }};
                        hitShake = 3;
                        hitEffect = new MultiEffect(new ParticleEffect(){{
                            particles = 16;
                            strokeFrom = 2.22f;
                            strokeTo = lenTo = baseLength = 0;
                            lenFrom = 35;
                            sizeInterp = Interp.pow5In;
                            line = true;
                            length = 80;
                            lifetime = 30;
                            colorFrom = colorTo = Color.valueOf("ffa166");
                            cone = 23;
                        }}, new ParticleEffect(){{
                            particles = 18;
                            strokeFrom = 2;
                            strokeTo = lenTo = baseLength = 0;
                            lenFrom = 24;
                            interp = Interp.pow5Out;
                            sizeInterp = Interp.pow5In;
                            line = true;
                            length = -60;
                            lifetime = 10;
                            colorFrom = colorTo = Color.valueOf("ffa166");
                            cone = 50;
                        }}, new ParticleEffect(){{
                            particles = 6;
                            strokeFrom = 9;
                            strokeTo = 0;
                            region = name("diamond");
                            interp = Interp.pow5Out;
                            sizeInterp = Interp.pow5In;
                            length = 45;
                            baseLength = 8;
                            lifetime = 43;
                            colorFrom = Color.valueOf("ffa166a0");
                            colorTo = Color.valueOf("ffa166d0");
                        }}, new WaveEffect(){{
                            lifetime = 18;
                            sizeFrom = strokeTo = 0;
                            sizeTo = 38;
                            strokeFrom = 5;
                            colorFrom = colorTo = Color.valueOf("ffa166");
                        }});
                        shootEffect = new ParticleEffect(){{
                            particles = 6;
                            line = true;
                            strokeFrom = 3;
                            strokeTo = lenTo = baseLength = 0;
                            lenFrom = 16;
                            length = 55;
                            lifetime = 25;
                            colorFrom = colorTo = Color.valueOf("ffa166");
                            cone = 16;
                        }};
                        despawnEffect = Fx.none;
                        smokeEffect = new ParticleEffect(){{
                            particles = 6;
                            lenTo = baseLength = 0;
                            interp = Interp.pow10Out;
                            sizeInterp = Interp.pow5In;
                            length = 55;
                            lifetime = 25;
                            colorFrom = colorTo = Color.valueOf("ffa166A0");
                            cone = 20;
                        }};
                        fragBullets = 18;
                        fragRandomSpread = 60;
                        fragBullet = new LiquidBulletType(Liquids.slag){{
                            damage = 46;
                            puddleSize = 16f;
                            orbSize = 5f;
                            knockback = 1.75f;
                            statusDuration = 600f;
                            status = StatusEffects.melting;
                            pierceArmor = true;
                            pierce = true;
                            pierceCap = 2;
                            speed = 8f;
                            lifetime = 20f;
                            hitEffect = new ParticleEffect(){{
                                particles = 5;
                                interp = Interp.pow5Out;
                                region = name("diamond");
                                sizeFrom = 5f;
                                sizeTo = baseLength = 0f;
                                length = 20f;
                                lifetime = 18f;
                                colorFrom = colorTo = Color.valueOf("ffa166");
                                cone = 60f;
                            }};
                        }};
                        ammoMultiplier = 2;
                    }},
                    Items.carbide, new BasicBulletType(28f, 2160f){{
                        rangeChange = 160f;
                        buildingDamageMultiplier = 0.5f;
                        lifetime = 28f;
                        width = 20f;
                        height = 34f;
                        pierceArmor = true;
                        pierce = true;
                        pierceCap = 5;
                        status = StatusEffects.melting;
                        statusDuration = 360f;
                        backColor = trailColor = Color.valueOf("feebb3");
                        frontColor = Color.valueOf("ffa166");
                        shrinkY = 0f;
                        trailLength = 13;
                        trailWidth = 5f;
                        trailChance = 0f;
                        trailInterval = 0.2f;
                        trailEffect = new ParticleEffect(){{
                            region = name("diamond");
                            particles = 3;
                            sizeFrom = 5;
                            sizeTo = 0;
                            length = 16;
                            lifetime = 22;
                            interp = Interp.pow10Out;
                            sizeInterp = Interp.pow5In;
                            colorFrom = Color.valueOf("ffa166");
                            colorTo = Color.valueOf("feebb3a0");
                        }};
                        hitShake = 3;
                        hitEffect = new MultiEffect(new ParticleEffect(){{
                            particles = 16;
                            strokeFrom = 2.22f;
                            strokeTo = lenTo = baseLength = 0;
                            lenFrom = 35;
                            sizeInterp = Interp.pow5In;
                            line = true;
                            length = 80;
                            lifetime = 30;
                            colorFrom = colorTo = Color.valueOf("feebb3");
                            cone = 23;
                        }}, new ParticleEffect(){{
                            particles = 18;
                            strokeFrom = 2;
                            strokeTo = lenTo = baseLength = 0;
                            lenFrom = 24;
                            interp = Interp.pow5Out;
                            sizeInterp = Interp.pow5In;
                            line = true;
                            length = -60;
                            lifetime = 10;
                            colorFrom = colorTo = Color.valueOf("feebb3");
                            cone = 50;
                        }}, new ParticleEffect(){{
                            particles = 6;
                            strokeFrom = 9;
                            strokeTo = 0;
                            region = name("diamond");
                            interp = Interp.pow5Out;
                            sizeInterp = Interp.pow5In;
                            length = 45;
                            baseLength = 8;
                            lifetime = 43;
                            colorFrom = Color.valueOf("ffa166a0");
                            colorTo = Color.valueOf("feebb3d0");
                        }}, new WaveEffect(){{
                            lifetime = 8;
                            sizeFrom = strokeTo = 0;
                            sizeTo = 30;
                            strokeFrom = 10;
                            colorFrom = Color.white;
                            colorTo = Color.valueOf("feebb3");
                        }});
                        shootEffect = new ParticleEffect(){{
                            particles = 6;
                            line = true;
                            strokeFrom = 3;
                            strokeTo = lenTo = baseLength = 0;
                            lenFrom = 16;
                            length = 55;
                            lifetime = 25;
                            colorFrom = colorTo = Color.valueOf("feebb3");
                            cone = 16;
                        }};
                        despawnEffect = Fx.none;
                        smokeEffect = new ParticleEffect(){{
                            particles = 6;
                            lenTo = baseLength = 0;
                            interp = Interp.pow10Out;
                            sizeInterp = Interp.pow5In;
                            length = 55;
                            lifetime = 25;
                            colorFrom = colorTo = Color.valueOf("feebb3");
                            cone = 20;
                        }};
                        fragBullets = 1;
                        fragLifeMin = 0f;
                        fragRandomSpread = 30f;
                        fragBullet = new BasicBulletType(16f, 210){{
                            width = 10f;
                            height = 14f;
                            pierceArmor = true;
                            pierce = true;
                            pierceBuilding = true;
                            status = StatusEffects.slow;
                            statusDuration = 33;
                            pierceCap = 3;
                            lifetime = 15f;
                            hitEffect = Fx.flakExplosion;
                            backColor = trailColor = Color.valueOf("feebb3");
                            frontColor = Color.valueOf("ffa166");
                            trailWidth = 1.7f;
                            trailLength = 3;
                            splashDamage = 50f;
                            splashDamageRadius = 11f;
                        }};
                        ammoMultiplier = 3;
                    }}
            );
            size = 5;
            health = 8830;
            armor = 16;
            reload = 80;
            range = 550;
            heatRequirement = 60;
            maxHeatEfficiency = 2f;
            warmupMaintainTime = 60;
            shootWarmupSpeed = 0.02f;
            minWarmup = 0.76f;
            outlineColor = Color.valueOf("2d2f39");
            drawer = new DrawTurret("reinforced-"){{
                parts.addAll(new RegionPart("-side"){{
                    mirror = true;
                    under = false;
                    x = 0;
                    heatProgress = PartProgress.recoil;
                    heatColor = Color.valueOf("ffa166");
                    moveY = -4;
                    children.add(new RegionPart("-barrel"){{
                        mirror = true;
                        under = true;
                        x = 3;
                        heatProgress = PartProgress.recoil;
                        heatColor = Color.valueOf("ffa166");
                        moves.add(new PartMove(PartProgress.recoil, 0f, 3f, 0f));
                        moveX = -3f;
                        moveY = 9.25f;
                    }});
                }}, new RegionPart("-top"){{
                    mirror = false;
                    heatProgress = PartProgress.warmup;
                    heatColor = Color.valueOf("ffa166");
                    progress = PartProgress.recoil;
                    moveY = -2;
                }}, new ShapePart(){{
                    progress = PartProgress.warmup;
                    y = -17;
                    color = Color.valueOf("ffa166");
                    stroke = radius = 0;
                    strokeTo = 1.6f;
                    circle = true;
                    hollow = true;
                    radiusTo = 10;
                    layer = 110;
                }}, new HaloPart(){{
                    shapeRotation = 45;
                    progress = PartProgress.warmup;
                    shapes = 1;
                    sides = 3;
                    x = 10;
                    y = -27;
                    color = Color.valueOf("ffa166");
                    layer = 110;
                    tri = true;
                    radius = triLength = haloRadius = haloRadiusTo = haloRotateSpeed = 0;
                    radiusTo = 5;
                    triLengthTo = 28;
                }}, new HaloPart(){{
                    shapeRotation = -135;
                    progress = PartProgress.warmup;
                    shapes = 1;
                    sides = 3;
                    x = 10;
                    y = -27;
                    color = Color.valueOf("ffa166");
                    layer = 110;
                    tri = true;
                    radius = triLength = haloRadius = haloRadiusTo = haloRotateSpeed = 0;
                    radiusTo = 5;
                    triLengthTo = 28;
                }}, new HaloPart(){{
                    shapeRotation = -45;
                    progress = PartProgress.warmup;
                    shapes = 1;
                    sides = 3;
                    x = -10;
                    y = -27;
                    color = Color.valueOf("ffa166");
                    layer = 110;
                    tri = true;
                    radius = triLength = haloRadius = haloRadiusTo = haloRotateSpeed = 0;
                    radiusTo = 5;
                    triLengthTo = 12;
                }}, new HaloPart(){{
                    shapeRotation = 135;
                    progress = PartProgress.warmup;
                    shapes = 1;
                    sides = 3;
                    x = -10;
                    y = -27;
                    color = Color.valueOf("ffa166");
                    layer = 110;
                    tri = true;
                    radius = triLength = haloRadius = haloRadiusTo = haloRotateSpeed = 0;
                    radiusTo = 5;
                    triLengthTo = 28;
                }}, new ShapePart(){{
                    progress = PartProgress.warmup;
                    y = -23;
                    color = Color.valueOf("ffa166");
                    stroke = radius = 0;
                    strokeTo = 2;
                    circle = true;
                    hollow = true;
                    radiusTo = 16;
                    layer = 110;
                }}, new HaloPart(){{
                    progress = PartProgress.warmup;
                    sides = 3;
                    shapes = 3;
                    y = -23;
                    color = Color.valueOf("ffa166");
                    layer = 110;
                    tri = true;
                    radius = triLength = haloRadius = haloRotation = 0;
                    radiusTo = 5;
                    triLengthTo = 8;
                    haloRadiusTo = 21;
                    haloRotateSpeed = -0.9f;
                }}, new HaloPart(){{
                    shapeRotation = 180;
                    progress = PartProgress.warmup;
                    shapes = 3;
                    sides = 3;
                    y = -23;
                    color = Color.valueOf("ffa166");
                    layer = 110;
                    tri = true;
                    radius = triLength = haloRadius = haloRotation = 0;
                    radiusTo = 5;
                    triLengthTo = 5;
                    haloRadiusTo = 21;
                    haloRotateSpeed = -0.9f;
                }}, new HaloPart(){{
                    progress = PartProgress.warmup;
                    shapes = 1;
                    sides = 3;
                    x = 0;
                    y = -35;
                    color = Color.valueOf("ffa166");
                    layer = 110;
                    tri = true;
                    radius = triLength = haloRadius = haloRadiusTo = haloRotateSpeed = 0;
                    radiusTo = 6;
                    triLengthTo = 15;
                }}, new HaloPart(){{
                    shapeRotation = -180;
                    progress = PartProgress.warmup;
                    shapes = 1;
                    sides = 3;
                    x = 0;
                    y = -35;
                    color = Color.valueOf("ffa166");
                    layer = 110;
                    tri = true;
                    radius = triLength = haloRadius = haloRadiusTo = haloRotateSpeed = 0;
                    radiusTo = 6;
                    triLengthTo = 33;
                }});
            }};
            shootSound = Sounds.shootSmite;
            shootY = 4;
            ammoUseEffect = Fx.none;
            shootCone = 8;
            rotateSpeed = 1.12f;
            shake = 6;
            maxAmmo = 26;
            ammoPerShot = 13;
            heatColor = Color.valueOf("ffa166");
            cooldownTime = 100;
            recoil = 5;
            recoilTime = 45;
            squareSprite = false;
            buildCostMultiplier = 0.8f;
            researchCostMultiplier = 0.6f;
        }};
    }
}
