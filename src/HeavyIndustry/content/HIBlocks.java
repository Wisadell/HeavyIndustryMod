package HeavyIndustry.content;

import HeavyIndustry.entities.*;
import HeavyIndustry.entities.bullet.*;
import HeavyIndustry.entities.effect.*;
import HeavyIndustry.gen.*;
import HeavyIndustry.graphics.*;
import HeavyIndustry.world.blocks.defense.*;
import HeavyIndustry.world.blocks.distribution.*;
import HeavyIndustry.world.blocks.power.*;
import HeavyIndustry.world.blocks.production.*;
import HeavyIndustry.world.blocks.liquid.*;
import HeavyIndustry.world.blocks.heat.*;
import HeavyIndustry.world.blocks.storage.*;
import HeavyIndustry.world.blocks.defense.turrets.*;
import HeavyIndustry.world.blocks.units.*;
import HeavyIndustry.world.blocks.logic.*;
import HeavyIndustry.world.draw.*;
import HeavyIndustry.world.meta.*;
import arc.Core;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.math.geom.*;
import arc.util.*;
import mindustry.Vars;
import mindustry.content.*;
import mindustry.entities.*;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.*;
import mindustry.entities.part.*;
import mindustry.entities.pattern.*;
import mindustry.game.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.blocks.defense.*;
import mindustry.world.blocks.defense.turrets.*;
import mindustry.world.blocks.distribution.*;
import mindustry.world.blocks.environment.*;
import mindustry.world.blocks.heat.*;
import mindustry.world.blocks.liquid.*;
import mindustry.world.blocks.power.*;
import mindustry.world.blocks.production.*;
import mindustry.world.blocks.storage.*;
import mindustry.world.blocks.units.*;
import mindustry.world.consumers.*;
import mindustry.world.draw.*;
import mindustry.world.meta.*;

import static arc.graphics.g2d.Draw.*;

import static HeavyIndustry.HeavyIndustryMod.*;
import static arc.math.Angles.*;
import static mindustry.Vars.*;
import static mindustry.type.ItemStack.*;

public class HIBlocks {
    public static Block
            //environment
            darkPanel7,darkPanel8,darkPanel9,darkPanel10,darkPanel11,darkPanelDamaged,
            stoneVent,basaltVent,basaltWall,basaltGraphiteWall,snowySand,snowySandWall,arkyciteSand,arkyciteSandWall,arkyciteSandBoulder,darksandBoulder,
            nanofluid,
            stoneWater,shaleWater,basaltWater,frozenWater,aghatiteWater,greniteWater,albasterWater,quartzSandWater,
            deadGrass,frozenSoil,albaster,albasterTiles,albasterCrater,aghatite,aghatitePebbles,quartzSand,grenite,coastalGrenite,blueIce,blueIcePieces,blueSnow,blueSnowdrifts,
            deadThickets,frozenSoilWall,albasterWall,aghatiteWall,quartzSandWall,greniteWall,blueIceWall,blueSnowWall,
            deadShrub,frozenSoilBoulder,albasterBoulder,aghatiteBoulder, quartzSandBoulder, greniteBoulder,blueBoulder,
            fallenDeadTree,fallenDeadTreeTopHalf,fallenDeadTreeBottomHalf,standingDeadTree,deadTreeStump,deadTreeRoots,
            softRareEarth,patternRareEarth,softRareEarthWall,
            oreUranium,oreChromium,
            //wall
            armoredWall,armoredWallLarge,uraniumWall,uraniumWallLarge,chromiumWall,chromiumWallLarge,chromiumDoor,chromiumDoorLarge,heavyAlloyWall,heavyAlloyWallLarge,nanoCompositeWall,nanoCompositeWallLarge,
            //drill
            largeWaterExtractor,slagExtractor,reinforcedOilExtractor,cuttingDrill,beamDrill,
            //drill-erekir
            largeCliffCrusher,heavyPlasmaBore,
            //distribution
            invertedJunction,chromiumEfficientConveyor,chromiumArmorConveyor,chromiumStackConveyor,chromiumStackRouter,chromiumJunction,chromiumInvertedJunction,chromiumRouter,chromiumItemBridge,
            stackHelper,highEnergyItemNode,rapidDirectionalUnloader,
            //distribution-erekir
            armoredDuctBridge,waveDuct,waveDuctBridge,waveDuctRouter,overflowWaveDuct,underflowWaveDuct,rapidDuctUnloader,
            //liquid
            turboPump,highEnergyLiquidNode,chromiumArmorConduit,chromiumLiquidBridge,chromiumArmorLiquidContainer,chromiumArmorLiquidTank,
            //liquid-erekir
            liquidSorter,liquidValve,smallReinforcedPump,largeReinforcedPump,
            //power
            powerNodeHighEnergy,powerNodeGiant,windTurbine,uraniumReactor,armoredCoatedBattery,
            //power-erekir
            liquidConsumeGenerator,
            //production
            largeKiln,largePulverizer,largeMelter,largeCryofluidMixer,largePyratiteMixer,largeBlastMixer,largeCultivator,largePlastaniumCompressor,largeSurgeSmelter,largeCoalCentrifuge,blastSiliconSmelter,
            nanocoreConstructor,nanocorePrinter,nanocoreActivator,highEnergyPhaseWeaver,highEnergyEnergizer,highEnergyReactor,highEnergyFabricFusionInstrument,uraniumSynthesizer,chromiumSynthesizer,heavyAlloySmelter,metalAnalyzer,nitrificationReactor,nitratedOilSedimentationTank,
            //production-erekir
            ventHeater,chemicalSiliconSmelter,largeElectricHeater,liquidFuelHeater,largeOxidationChamber,largeSurgeCrucible,largeCarbideCrucible,
            //defense
            lighthouse,mendDome,assignOverdrive,largeShieldGenerator,
            //defense-erekir
            largeRadar,
            //storage
            coreBeStationed,cargo,bin,rapidUnloader,
            //unit
            titanReconstructor,experimentalUnitFactory,
            //unit-erekir
            largeUnitRepairTower,seniorAssemblerModule,
            //logic
            buffrerdMemoryCell,buffrerdMemoryBank,
            //turret
            dissipation,rocketLauncher,multipleRocketLauncher,largeRocketLauncher,rocketSilo,cloudbreaker,minigun,
            rend,fissure,
            frost,judgement,fiammetta,wisadel,spark,fireworks,
            //turret-erekir
            tracer,shadow;
    public static void load(){
        //environment
        darkPanel7 = new Floor("dark-panel-7", 0);
        darkPanel8 = new Floor("dark-panel-8", 0);
        darkPanel9 = new Floor("dark-panel-9", 0);
        darkPanel10 = new Floor("dark-panel-10", 0);
        darkPanel11 = new Floor("dark-panel-11", 0);
        darkPanelDamaged = new Floor("dark-panel-damaged", 3);
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
        basaltWall = new StaticWall("basalt-wall"){{
            variants = 3;
            attributes.set(Attribute.sand, 0.7f);
        }};
        basaltGraphiteWall = new StaticWall("basalt-graphite-wall"){{
            variants = 3;
            itemDrop = Items.graphite;
        }};
        snowySand = new Floor("snowy-sand", 3){{
            itemDrop = Items.sand;
            attributes.set(Attribute.water, 0.2f);
            attributes.set(Attribute.oil, 0.5f);
            playerUnmineable = true;
        }};
        snowySandWall = new StaticWall("snowy-sand-wall"){{
            variants = 2;
            attributes.set(Attribute.sand, 2f);
        }};
        arkyciteSand = new Floor("arkycite-sand", 3){{
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
            arkyciteSand.asFloor().decoration = this;
        }};
        darksandBoulder = new Prop("darksand-boulder"){{
            variants = 2;
            Blocks.darksand.asFloor().decoration = this;
        }};
        softRareEarth = new Floor("soft-rare-earth", 3){{
            itemDrop = HIItems.rareEarth;
            playerUnmineable = true;
            wall = softRareEarthWall;
        }};
        patternRareEarth = new Floor("pattern-rare-earth", 4){{
            itemDrop = HIItems.rareEarth;
            playerUnmineable = true;
            wall = softRareEarthWall;
        }};
        softRareEarthWall = new StaticWall("soft-rare-earth-wall"){{
            variants = 2;
            itemDrop = HIItems.rareEarth;
        }};
        nanofluid = new Floor("pooled-nanofluid", 0){{
            status = HIStatusEffects.repair;
            statusDuration = 60f;
            drownTime = 160f;
            speedMultiplier = 0.6f;
            liquidDrop = HILiquids.nanofluid;
            isLiquid = true;
            cacheLayer = HICacheLayer.nanofluid;
            liquidMultiplier = 0.5f;
            emitLight = true;
            lightRadius = 30f;
            lightColor = Color.green.cpy().a(0.19f);
        }};
        stoneWater = new Floor("stone-water", 3){{
            speedMultiplier = 0.6f;
            liquidDrop = Liquids.water;
            isLiquid = true;
            cacheLayer = CacheLayer.water;
            albedo = 0.9f;
            supportsOverlay = true;
        }};
        shaleWater = new Floor("shale-water", 3){{
            speedMultiplier = 0.6f;
            liquidDrop = Liquids.water;
            isLiquid = true;
            cacheLayer = CacheLayer.water;
            albedo = 0.9f;
            supportsOverlay = true;
        }};
        basaltWater = new Floor("basalt-water", 3){{
            speedMultiplier = 0.6f;
            liquidDrop = Liquids.water;
            isLiquid = true;
            cacheLayer = CacheLayer.water;
            albedo = 0.9f;
            supportsOverlay = true;
        }};
        frozenWater = new Floor("frozen-water", 4){{
            speedMultiplier = 0.6f;
            liquidDrop = Liquids.water;
            isLiquid = true;
            cacheLayer = CacheLayer.water;
            albedo = 0.9f;
            supportsOverlay = true;
        }};
        aghatiteWater = new Floor("aghatite-water", 3){{
            speedMultiplier = 0.6f;
            liquidDrop = Liquids.water;
            isLiquid = true;
            cacheLayer = CacheLayer.water;
            albedo = 0.9f;
            supportsOverlay = true;
        }};
        greniteWater = new Floor("grenite-water", 4){{
            speedMultiplier = 0.6f;
            liquidDrop = Liquids.water;
            isLiquid = true;
            cacheLayer = CacheLayer.water;
            albedo = 0.9f;
            supportsOverlay = true;
        }};
        albasterWater = new Floor("albaster-water", 3){{
            speedMultiplier = 0.6f;
            liquidDrop = Liquids.water;
            isLiquid = true;
            cacheLayer = CacheLayer.water;
            albedo = 0.9f;
            supportsOverlay = true;
        }};
        quartzSandWater = new Floor("quartz-sand-water", 3){{
            speedMultiplier = 0.6f;
            liquidDrop = Liquids.water;
            isLiquid = true;
            cacheLayer = CacheLayer.water;
            albedo = 0.9f;
            supportsOverlay = true;
        }};
        deadGrass = new Floor("dead-grass", 4);
        deadThickets = new StaticWall("dead-thickets"){{
            variants = 2;
        }};
        deadShrub = new Prop("dead-shrub"){{
            customShadow = true;
            variants = 3;
            deadGrass.asFloor().decoration = this;
        }};
        frozenSoil = new Floor("frozen-soil", 4){{
            wall = frozenSoilWall;
        }};
        frozenSoilWall = new StaticWall("frozen-soil-wall"){{
            variants = 4;
        }};
        frozenSoilBoulder = new Prop("frozen-soil-boulder"){{
            variants = 3;
            frozenSoil.asFloor().decoration = this;
        }};
        albaster = new Floor("albaster", 3){{
            wall = albasterWall;
        }};
        albasterTiles = new Floor("albaster-tiles", 3){{
            wall = albasterWall;
        }};
        albasterCrater = new Floor("albaster-craters", 3){{
            blendGroup = albaster;
            wall = albasterWall;
        }};
        albasterWall = new StaticWall("albaster-wall"){{
            variants = 3;
            attributes.set(Attribute.sand, 1.5f);
        }};
        albasterBoulder = new Prop("albaster-boulder"){{
            variants = 3;
            albaster.asFloor().decoration = this;
            albasterTiles.asFloor().decoration = this;
            albasterCrater.asFloor().decoration = this;
        }};
        aghatite = new Floor("aghatite", 3){{
            wall = aghatiteWall;
        }};
        aghatitePebbles = new Floor("aghatite-pebbles", 4){{
            wall = aghatiteWall;
        }};
        aghatiteWall = new StaticWall("aghatite-wall"){{
            variants = 4;
            attributes.set(Attribute.sand, 1.2f);
        }};
        aghatiteBoulder = new Prop("aghatite-boulder"){{
            variants = 2;
            aghatite.asFloor().decoration = this;
            aghatitePebbles.asFloor().decoration = this;
        }};
        quartzSand = new Floor("quartz-sand-floor", 3){{
            wall = albasterWall;
            itemDrop = Items.sand;
        }};
        quartzSandWall = new StaticWall("quartz-sand-wall"){{
            variants = 4;
            attributes.set(Attribute.sand, 2f);
        }};
        quartzSandBoulder = new Prop("quartz-sand-boulder"){{
            variants = 3;
            quartzSand.asFloor().decoration = this;
        }};
        grenite = new Floor("grenite", 4){{
            wall = albasterWall;
        }};
        coastalGrenite = new Floor("coastal-grenite", 3){{
            wall = albasterWall;
        }};
        greniteWall = new StaticWall("grenite-wall"){{
            variants = 2;
            attributes.set(Attribute.sand, 1f);
        }};
        greniteBoulder = new Prop("grenite-boulder"){{
            variants = 3;
            grenite.asFloor().decoration = this;
            coastalGrenite.asFloor().decoration = this;
        }};
        blueIce = new Floor("blue-ice", 3){{
            mapColor = Color.valueOf("5195ab");
            wall = blueIceWall;
            albedo = 0.9f;
            attributes.set(Attribute.water, 0.4f);
        }};
        blueIcePieces = new OverlayFloor("blue-ice-pieces");
        blueIceWall = new StaticWall("blue-ice-wall"){{
            mapColor = Color.valueOf("b3e7fb");
            variants = 2;
        }};
        blueSnow = new Floor("blue-snow", 3){{
            mapColor = Color.valueOf("9fd3e7");
            wall = blueIceWall;
            albedo = 0.7f;
            attributes.set(Attribute.water, 0.2f);
        }};
        blueSnowdrifts = new OverlayFloor("blue-snowdrifts");
        blueSnowWall = new StaticWall("blue-snow-wall"){{
            mapColor = Color.valueOf("d4f2ff");
            variants = 2;
        }};
        blueBoulder = new Prop("blue-boulder"){{
            variants = 3;
            blueIce.asFloor().decoration = this;
            blueSnow.asFloor().decoration = this;
        }};
        fallenDeadTree = new TallBlock("fallen-dead-tree"){{
            clipSize = 72f;
            variants = 8;
        }};
        fallenDeadTreeTopHalf = new TallBlock("fallen-dead-tree-top-half"){{
            clipSize = 40f;
            variants = 8;
        }};
        fallenDeadTreeBottomHalf = new TallBlock("fallen-dead-tree-bottom-half"){{
            clipSize = 32f;
            variants = 8;
        }};
        standingDeadTree = new TallBlock("standing-dead-tree"){{
            clipSize = 32f;
            variants = 1;
        }};
        deadTreeStump = new TallBlock("dead-tree-stump"){{
            clipSize = 16f;
            variants = 1;
        }};
        deadTreeRoots = new TallBlock("dead-tree-roots"){{
            clipSize = 16f;
            variants = 1;
        }};
        oreUranium = new OreBlock("ore-uranium", HIItems.uranium){{
            variants = 5;
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
            health = 3220;
            armor = 48;
            absorbLasers = insulated = true;
        }};
        heavyAlloyWallLarge = new Wall("heavy-alloy-wall-large"){{
            requirements(Category.defense, with(HIItems.heavyAlloy, 24, Items.metaglass, 12, Items.plastanium, 16));
            size = 2;
            health = 12880;
            armor = 48;
            absorbLasers = insulated = true;
        }};
        nanoCompositeWall = new RegenWall("nano-composite-wall"){{
            requirements(Category.defense, with(HIItems.nanocore, 2, HIItems.heavyAlloy, 6, Items.metaglass, 1, Items.plastanium, 4));
            size = 1;
            health = 2680;
            armor = 42;
            absorbLasers = insulated = true;
            healPercent = 2f / 60f;
            chanceHeal = 0.15f;
            regenPercent = 0.5f;
        }};
        nanoCompositeWallLarge = new RegenWall("nano-composite-wall-large"){{
            requirements(Category.defense, with(HIItems.nanocore, 8, HIItems.heavyAlloy, 24, Items.metaglass, 4, Items.plastanium, 16));
            size = 2;
            health = 10720;
            armor = 42;
            absorbLasers = insulated = true;
            healPercent = 2f / 60f;
            chanceHeal = 0.15f;
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
            consumePower(3.5f);
        }};
        reinforcedOilExtractor = new Fracker("reinforced-oil-extractor"){{
            requirements(Category.production, with(Items.graphite, 175, Items.lead, 115, HIItems.chromium, 135, Items.silicon, 75));
            size = 3;
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
            buildCostMultiplier = 0.8f;
        }};
        cuttingDrill = new Drill("cutting-drill"){{
            requirements(Category.production, with(Items.graphite, 100, Items.silicon, 120, Items.thorium, 50, Items.plastanium, 40, Items.surgeAlloy, 30));
            size = 3;
            health = 590;
            armor = 3;
            tier = 8;
            warmupSpeed = 0.06f;
            itemCapacity = 10;
            drillTime = 250f;
            updateEffect = Fx.mineBig;
            hardnessDrillMultiplier = 15f;
            consumePower(4f);
            consumeLiquid(Liquids.water, 0.1f).boost();
        }};
        beamDrill = new LaserBeamDrill("beam-drill"){{
            requirements(Category.production, with(Items.lead, 160, Items.silicon, 120,  HIItems.chromium, 60, HIItems.nanocore, 35,HIItems.highEnergyFabric, 25));
            size = 4;
            health = 960;
            tier = 11;
            drillTime = 140f;
            warmupSpeed = 0.02f;
            consumePower(6f);
            consumeLiquid(Liquids.water, 0.1f).boost();
            buildCostMultiplier = 0.8f;
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
        chromiumStackConveyor = new StackConveyor("chromium-stack-conveyor"){{
            requirements(Category.distribution, with(Items.graphite, 1, Items.silicon, 1, Items.plastanium, 1, HIItems.chromium, 1));
            health = 380;
            armor = 4;
            speed = 0.125f;
            itemCapacity = 20;
            outputRouter = false;
            loadEffect = unloadEffect = new Effect(30f, e -> {
                Lines.stroke(1.5f * e.fout(Interp.pow2Out), HIPal.chromiumGrey);
                Lines.square(e.x, e.y, tilesize / 8f * Mathf.sqrt2 * (e.fin(Interp.pow2Out) * 3 + 1f), 45f);
            });
        }};
        chromiumStackRouter = new StackRouter("chromium-stack-router"){{
            requirements(Category.distribution, with(Items.graphite, 4, Items.silicon, 5, Items.plastanium, 3, HIItems.chromium, 1));
            health = 380;
            armor = 4;
            speed = 0.125f;
            itemCapacity = 20;
            buildCostMultiplier = 0.8f;
        }};
        chromiumRouter = new MultiRouter("chromium-router"){{
            requirements(Category.distribution, with(Items.copper, 3, HIItems.chromium, 2));
            health = 420;
            armor = 4;
            speed = 2;
            itemCapacity = 20;
            liquidCapacity = 64f;
            underBullets = true;
            solid = false;
        }};
        chromiumJunction = new Junction("chromium-junction"){{
            requirements(Category.distribution, with(Items.copper, 2, HIItems.chromium, 2));
            health = 420;
            armor = 4;
            speed = 12;
            capacity = itemCapacity = 12;
            ((Conveyor) chromiumEfficientConveyor).junctionReplacement = this;
            ((ArmoredConveyor) chromiumArmorConveyor).junctionReplacement = this;
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
            requirements(Category.distribution, with(Items.graphite, 6, Items.silicon, 8, Items.plastanium, 4, HIItems.chromium, 3));
            health = 420;
            armor = 4;
            hasPower = false;
            transportTime = 3f;
            range = 8;
            arrowSpacing = 6;
            bridgeWidth = 8;
            buildCostMultiplier = 0.8f;
            ((Conveyor) chromiumEfficientConveyor).bridgeReplacement = this;
            ((ArmoredConveyor) chromiumArmorConveyor).bridgeReplacement = this;
        }};
        stackHelper = new StackHelper("stack-helper"){{
            requirements(Category.distribution, with(Items.silicon, 20, Items.phaseFabric, 10, Items.plastanium, 20));
            size = 1;
            health = 60;
        }};
        highEnergyItemNode = new NodeBridge("high-energy-item-node"){{
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
        rapidDirectionalUnloader = new AdaptDirectionalUnloader("rapid-directional-unloader"){{
            requirements(Category.distribution, with(Items.silicon, 40, Items.plastanium, 25, HIItems.chromium, 15, HIItems.highEnergyFabric, 5));
            speed = 60.0f;
            squareSprite = false;
            underBullets = true;
            allowCoreUnload = true;
        }};
        //distribution-erekir
        armoredDuctBridge = new DuctBridge("armored-duct-bridge"){{
            requirements(Category.distribution, with(Items.beryllium, 20, Items.tungsten, 10));
            health = 140;
            range = 6;
            speed = 4;
            buildCostMultiplier = 2;
            ((Duct) Blocks.armoredDuct).bridgeReplacement = this;
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
            ((Duct) waveDuct).bridgeReplacement = this;
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
            requirements(Category.liquid, with(Items.metaglass, 2, HIItems.chromium, 1));
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
            ((ArmoredConduit) chromiumArmorConduit).bridgeReplacement = this;
        }};
        chromiumArmorLiquidContainer = new LiquidRouter("chromium-armor-liquid-container"){{
            requirements(Category.liquid, with(Items.metaglass, 15, HIItems.chromium, 6));
            size = 2;
            health = 950;
            armor = 8;
            liquidCapacity = 1200;
            underBullets = true;
            buildCostMultiplier = 0.8f;
        }};
        chromiumArmorLiquidTank = new LiquidRouter("chromium-armor-liquid-tank"){{
            requirements(Category.liquid, with(Items.metaglass, 40, HIItems.chromium, 16));
            size = 3;
            health = 2500;
            armor = 8;
            liquidCapacity = 3200;
            underBullets = true;
            buildCostMultiplier = 0.8f;
        }};
        turboPump = new Pump("turbo-pump"){{
            requirements(Category.liquid, with(Items.titanium, 40,Items.thorium, 50, Items.metaglass, 80, Items.silicon, 60, HIItems.chromium, 30));
            size = 2;
            consumePower(1.75f);
            pumpAmount = 0.8f;
            liquidCapacity = 200f;
            squareSprite = false;
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawPumpLiquid(), new DrawDefault());
            buildCostMultiplier = 0.8f;
        }};
        highEnergyLiquidNode = new NodeBridge("high-energy-liquid-node"){{
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
        powerNodeHighEnergy = new PowerNode("power-node-high-energy"){{
            requirements(Category.power, ItemStack.with(Items.plastanium, 5, HIItems.heavyAlloy, 15, HIItems.highEnergyFabric, 10));
            size = 1;
            health = 3000;
            armor = 30;
            absorbLasers = true;
            maxNodes = 20;
            laserRange = 30;
            timers ++;
            update = true;
            buildType = () -> new PowerNodeBuild(){
                @Override
                public void updateTile(){
                    if(damaged() && power.graph.getSatisfaction() > 0.5f){
                        if(timer.get(90f)){
                            Fx.healBlockFull.at(x, y, 0, Pal.powerLight, this.block);
                            healFract(5 * power.graph.getSatisfaction());
                        }
                    }
                }
            };
        }};
        windTurbine = new WindGenerator("wind-turbine"){{
            requirements(Category.power, with(Items.copper, 60, Items.lead, 40, Items.graphite, 20));
            drawer = new DrawMulti(
                    new DrawDefault(),
                    new Draw3dSpin("-holder", "-rotator"){{
                        baseOffset.x = Vars.tilesize / 2f;
                        axis = Vec3.Y;
                        rotationProvider(WindGeneratorBuild::baseRotation);
                        rotationAroundAxis = -55f;
                        rotateSpeed = baseRotateSpeed = 3.3f;
                        scale.set(0.5f, 1f, 0f);
                    }}
            );
            size = 1;
            powerProduction = 3.6f;
        }};
        uraniumReactor = new NuclearReactor("uranium-reactor"){{
            requirements(Category.power, with(Items.lead, 400, Items.metaglass, 120, Items.graphite, 350, Items.silicon, 300, HIItems.uranium, 100));
            size = 3;
            health = 1450;
            outputsPower = true;
            powerProduction = 58f;
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
            requirements(Category.power, with(Items.lead, 150, Items.silicon, 180, Items.plastanium, 120, HIItems.chromium, 100, HIItems.highEnergyFabric, 30));
            size = 4;
            health = 4200;
            armor = 16;
            consumePowerBuffered(625000);
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
            consume(new ConsumeLiquidFlammable(0.4f, 0.2f));
        }};
        //production
        largeKiln = new GenericCrafter("large-kiln"){{
            requirements(Category.crafting, with(Items.lead, 85, Items.graphite, 55, Items.thorium, 30, Items.silicon, 65));
            size = 3;
            itemCapacity = 20;
            craftTime = 45;
            outputItem = new ItemStack(Items.metaglass, 5);
            drawer = new DrawMulti(new DrawDefault(), new DrawFlame(Color.valueOf("ffc099")));
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
            craftTime = 35f;
            updateEffect = Fx.pulverizeSmall;
            craftEffect = HIFx.hugeSmokeGray;
            outputItem = new ItemStack(Items.sand, 3);
            updateEffect = new Effect(80f, e -> {
                Fx.rand.setSeed(e.id);
                Draw.color(Color.lightGray, Color.gray, e.fin());
                Angles.randLenVectors(e.id, 4, 2.0F + 12.0F * e.fin(Interp.pow3Out), (x, y) ->
                        Fill.circle(e.x + x, e.y + y, e.fout() * Fx.rand.random(1, 2.5f))
                );
            }).layer(Layer.blockOver + 1);
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawFrames(), new DrawArcSmelt(), new DrawDefault());
            ambientSound = Sounds.grinding;
            ambientSoundVolume = 0.12f;
            consumePower(1.5f);
            consumeItem(Items.scrap, 2);
        }};
        largeMelter = new GenericCrafter("large-melter"){{
            requirements(Category.crafting, with(Items.lead, 60, Items.graphite, 45, Items.silicon, 30, Items.titanium, 20));
            size = 2;
            hasLiquids = true;
            itemCapacity = 20;
            liquidCapacity = 30;
            craftTime = 12;
            outputLiquid = new LiquidStack(Liquids.slag, 36f / 60f);
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawLiquidTile(Liquids.slag), new DrawRegion("-spinner", 2, true), new DrawDefault(), new DrawRegion("-top"));
            consumePower(1.5f);
            consumeItem(Items.scrap, 2);
        }};
        largeCryofluidMixer = new GenericCrafter("large-cryofluid-mixer"){{
            requirements(Category.crafting, with(Items.lead, 120, Items.silicon, 60, Items.titanium, 150, Items.thorium, 110));
            outputLiquid = new LiquidStack(Liquids.cryofluid, 36f / 60f);
            size = 3;
            hasLiquids = true;
            rotate = false;
            solid = true;
            outputsLiquid = true;
            envEnabled = Env.any;
            liquidCapacity = 54f;
            craftTime = 50;
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawLiquidTile(Liquids.water), new DrawLiquidTile(Liquids.cryofluid), new DrawDefault(), new DrawRegion("-top"));
            lightLiquid = Liquids.cryofluid;
            consumePower(2f);
            consumeItem(Items.titanium);
            consumeLiquid(Liquids.water, 36f / 60f);
        }};
        largePyratiteMixer = new GenericCrafter("large-pyratite-mixer"){{
            requirements(Category.crafting, with(Items.copper, 100, Items.lead, 50, Items.titanium, 25, Items.silicon, 20));
            outputItem = new ItemStack(Items.pyratite, 3);
            envEnabled |= Env.space;
            size = 3;
            consumePower(0.5f);
            consumeItems(with(Items.coal, 3, Items.lead, 4, Items.sand, 5));
        }};
        largeBlastMixer = new GenericCrafter("large-blast-mixer"){{
            requirements(Category.crafting, with(Items.lead, 60, Items.titanium, 40, Items.silicon, 20));
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
            requirements(Category.crafting, with(Items.silicon, 150, Items.lead, 220, Items.graphite, 120, Items.titanium, 150, Items.thorium, 100));
            hasLiquids = true;
            itemCapacity = 20;
            liquidCapacity = 80f;
            craftTime = 60f;
            outputItem = new ItemStack(Items.plastanium, 3);
            size = 3;
            health = 640;
            craftEffect = Fx.formsmoke;
            updateEffect = Fx.plasticburn;
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawLiquidTile(Liquids.oil), new DrawPistons(){{
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
            hasLiquids = true;
            liquidCapacity = 60;
            itemCapacity =  20;
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawLiquidTile(), new DrawRegion("-spinner", 6, true), new DrawDefault());
            craftTime = 20f;
            outputItem = new ItemStack(Items.coal, 1);
            consumeLiquid(Liquids.oil, 0.3f);
            consumePower(1.8f);
        }};
        largeSurgeSmelter = new GenericCrafter("large-surge-smelter"){{
            requirements(Category.crafting, with(Items.lead, 180, Items.silicon, 100, Items.metaglass, 60, Items.thorium, 150, Items.surgeAlloy, 30));
            size = 4;
            itemCapacity = 30;
            craftTime = 90f;
            craftEffect = Fx.smeltsmoke;
            outputItem = new ItemStack(Items.surgeAlloy, 3);
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawDefault(), new DrawPowerLight(Color.valueOf("f3e979")), new DrawFlame(Color.valueOf("ffef99")));
            consumePower(6);
            consumeItems(ItemStack.with(Items.copper, 5, Items.lead, 6, Items.titanium, 5, Items.silicon, 4));
        }};
        blastSiliconSmelter = new GenericCrafter("blast-silicon-smelter"){{
            requirements(Category.crafting, with(Items.graphite, 90, Items.thorium, 70, Items.silicon, 80, Items.plastanium, 50, Items.surgeAlloy, 30));
            health = 660;
            size = 4;
            itemCapacity = 50;
            craftTime = 30f;
            outputItem = new ItemStack(Items.silicon, 10);
            craftEffect = new RadialEffect(Fx.surgeCruciSmoke, 9, 45, 6);
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawDefault(), new DrawGlowRegion("-glow1"){{
                alpha = 1f;
                glowScale = 3f;
            }}, new DrawGlowRegion("-glow2"){{
                alpha = 0.9f;
                glowScale = 3f;
            }});
            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.21f;
            consumeItems(with(Items.coal, 5, Items.sand, 8, Items.blastCompound, 1));
            consumePower(4f);
        }};
        nanocoreConstructor = new GenericCrafter("nanocore-constructor"){{
            requirements(Category.crafting, with(Items.copper, 120, Items.lead, 110, Items.titanium, 45, Items.silicon, 35));
            size = 2;
            itemCapacity = 15;
            craftTime = 100f;
            outputItem = new ItemStack(HIItems.nanocore, 1);
            craftEffect = Fx.none;
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawArcSmelt(){{
                midColor = flameColor = HIPal.nanocoreGreen;
                flameRad /= 1.585f;
                particleStroke /= 1.35f;
                particleLen /= 1.25f;
            }}, new DrawDefault());
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
            liquidCapacity = 60f;
            craftTime = 150f;
            outputItem = new ItemStack(HIItems.nanocore, 12);
            craftEffect = Fx.none;
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawScanLine(){{
                colorFrom = Color.valueOf("7cf389b6");
                lineLength = 73 / 4f;
                scanLength = 73 / 4f;
                scanScl = 15f;
                scanAngle = 90f;
                lineStroke -= 0.15f;
                totalProgressMultiplier = 1.25f;
                phaseOffset = Mathf.random() * 5f;
            }}, new DrawScanLine(){{
                colorFrom = Color.valueOf("7cf389b6");
                lineLength = 73 / 4f;
                scanLength = 73 / 4f;
                scanScl = 15f;
                scanAngle = 0f;
                totalProgressMultiplier = 1.55f;
                phaseOffset = Mathf.random() * 5f;
            }}, new DrawScanLine(){{
                colorFrom = Color.valueOf("7cf389b6");
                lineLength = 73 / 4f;
                scanLength = 73 / 4f;
                scanScl = 15f;
                scanAngle = 90f;
                totalProgressMultiplier = 1.35f;
                phaseOffset = Mathf.random() * 5f;
            }}, new DrawScanLine(){{
                colorFrom = Color.valueOf("7cf389b6");
                lineLength = 73 / 4f;
                scanLength = 73 / 4f;
                scanScl = 8f;
                scanAngle = 0f;
                lineStroke -= 0.15f;
                totalProgressMultiplier = 1.65f;
                phaseOffset = Mathf.random() * 5f;
            }}, new DrawRegion("-mid"), new DrawLiquidTile(Liquids.cryofluid, 54 / 4f), new DrawDefault(), new DrawGlowRegion("-glow1"){{
                alpha = 1f;
                color = Color.valueOf("7cf389b6");
            }}, new DrawGlowRegion("-glow2"){{
                alpha = 1f;
                color = Color.valueOf("7cf389b6");
            }}, new DrawGlowRegion("-glow3"){{
                alpha = 0.76f;
                color = Color.valueOf("7cf389b6");
            }});
            consumePower(25f);
            consumeLiquid(Liquids.cryofluid, 6f / 60f);
            consumeItems(ItemStack.with(Items.titanium, 6, Items.silicon, 9));
        }};
        nanocoreActivator = new GenericCrafter("nanocore-activator"){{
            requirements(Category.crafting, with(Items.titanium, 90, Items.silicon, 80, HIItems.nanocore, 30, Items.plastanium, 60));
            size = 2;
            health = 360;
            hasLiquids = outputsLiquid = true;
            rotate = false;
            solid = true;
            itemCapacity = 15;
            liquidCapacity = 24f;
            craftTime = 100f;
            craftEffect = Fx.smeltsmoke;
            outputLiquid = new LiquidStack(HILiquids.nanofluid, 18f / 60f);
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawLiquidTile(HILiquids.nanofluid), new DrawDefault(), new DrawRegion("-top"), new DrawFrames(){{
                frames = 5;
                sine = true;
            }});
            lightLiquid = HILiquids.nanofluid;
            consumePower(3f);
            consumeItem(HIItems.nanocore, 1);
        }};
        highEnergyPhaseWeaver = new GenericCrafter("high-energy-phase-weaver"){{
            requirements(Category.crafting, with(Items.lead, 160,Items.thorium, 100, Items.silicon, 80, Items.plastanium, 50, HIItems.highEnergyFabric, 15));
            size = 3;
            itemCapacity = 40;
            craftTime = 60f;
            outputItem = new ItemStack(Items.phaseFabric, 3);
            updateEffect = HIFx.squareRand(HIPal.highEnergyYellow, 5f, 13f);
            craftEffect = new Effect(25f, e -> {
                Draw.color(HIPal.highEnergyYellow);
                Angles.randLenVectors(e.id, 4, 24 * e.fout() * e.fout(), (x, y) -> {
                    Lines.stroke(e.fout() * 1.7f);
                    Lines.square(e.x + x, e.y + y, 2f + e.fout() * 6f);
                });
            });
            drawer = new DrawPrinter(outputItem.item){{
                printColor = lightColor = HIPal.highEnergyYellow;
                moveLength = 4.2f;
                time = 25f;
            }};
            clipSize = size * tilesize * 2f;
            consumePower(7f);
            consumeItems(ItemStack.with(Items.sand, 15, Items.thorium, 5));
        }};
        highEnergyEnergizer = new GenericCrafter("high-energy-energizer"){{
            requirements(Category.crafting, with(Items.lead, 40, Items.silicon, 65, Items.plastanium, 30, Items.surgeAlloy, 20));
            size = 3;
            outputItem = new ItemStack(HIItems.highEnergyFabric, 1);
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawCrucibleFlame(){{
                flameColor = midColor = HIPal.highEnergyYellow;
                particleRad += 0.25f;
                circleSpace = 1f;
                alpha = 0.3f;
                particles = 18;
            }}, new DrawDefault(), new DrawRegion("-mid"), new DrawRegion("-top"));
            updateEffectChance = 0.1f;
            lightColor = HIPal.highEnergyYellow;
            updateEffect = HIFx.squareRand(lightColor, 4f, 8f);
            craftEffect = new Effect(90f, e -> {
                Draw.color(lightColor, Color.white, e.fin() * 0.7f);
                Lines.stroke(e.fout() * 1.12f);
                Lines.square(e.x, e.y, e.finpow() * 12f, 45f);
            });
            craftTime = 80f;
            consumePower(6);
            consumeItem(Items.phaseFabric, 1);
            buildCostMultiplier = 0.8f;
        }};
        highEnergyReactor = new GeneratorCrafter("high-energy-reactor"){{
            requirements(Category.crafting, with(HIItems.chromium, 260, Items.silicon, 220, Items.plastanium, 180, Items.surgeAlloy, 140, HIItems.nanocore, 120, HIItems.highEnergyFabric, 80));
            size = 5;
            hasLiquids = true;
            itemCapacity = 40;
            liquidCapacity = 30f;
            powerProduction = 90f;
            lightColor = HIPal.highEnergyYellow;
            craftEffect = Fx.plasticExplosionFlak;
            craftTime = 100f;
            outputItems = new ItemStack[]{new ItemStack(HIItems.highEnergyFabric, 10), new ItemStack(Items.thorium, 2)};
            drawer = new DrawFactories(){{
                liquidColor = HILiquids.nanofluid.color;
                drawRotator = 1f;
                drawTop = false;
                pressorSet = new float[]{(craftTime / 6f), -4.2f, 45, 0};
            }};
            lightLiquid = HILiquids.nanofluid;
            consumeItems(ItemStack.with(Items.phaseFabric, 10, HIItems.uranium, 1));
            consumeLiquid(HILiquids.nanofluid, 18f / 60f);
            buildCostMultiplier = 0.4f;
        }};
        highEnergyFabricFusionInstrument = new GenericCrafter("high-energy-fabric-fusion-instrument"){{
            requirements(Category.crafting, with(Items.silicon, 100, Items.plastanium, 80, Items.phaseFabric, 60, HIItems.chromium, 80, HIItems.nanocore, 40));
            size = 3;
            hasLiquids = true;
            itemCapacity = 30;
            liquidCapacity = 20;
            lightRadius /= 2f;
            craftTime = 60;
            craftEffect = HIFx.crossBlast(HIPal.highEnergyYellow, 45f, 45f);
            craftEffect.lifetime *= 1.5f;
            updateEffect = HIFx.squareRand(HIPal.highEnergyYellow, 5f, 15f);
            outputItem = new ItemStack(HIItems.highEnergyFabric, 3);
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawRegion("-bottom-2"), new DrawCrucibleFlame(){{
                flameColor = HIPal.highEnergyYellow;
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
                color = HIPal.highEnergyYellow;
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
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawDefault(), new DrawGlowRegion(){{
                alpha = 1f;
                color = HIPal.uraniumGrey.cpy().lerp(Color.white, 0.1f);
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
            liquidCapacity = 30f;
            craftTime = 120f;
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
            requirements(Category.crafting, with(Items.lead, 80, Items.silicon, 60, HIItems.chromium, 30, HIItems.highEnergyFabric, 10));
            size = 3;
            health = 850;
            craftTime = 80f;
            outputItem = new ItemStack(HIItems.heavyAlloy, 1);
            craftEffect = Fx.smeltsmoke;
            drawer = new DrawMulti(new DrawDefault(), new DrawFlame());
            consumePower(9f);
            consumeItems(ItemStack.with(HIItems.uranium, 1, HIItems.chromium, 1));
        }};
        metalAnalyzer = new Separator("metal-analyzer"){{
            requirements(Category.crafting, with(Items.titanium, 120, Items.silicon, 180, Items.plastanium, 80, HIItems.nanocore, 30));
            size = 3;
            itemCapacity = 20;
            liquidCapacity = 30f;
            craftTime = 20f;
            results = with(Items.titanium, 2, Items.thorium, 2, HIItems.uranium, 1, HIItems.chromium, 1);
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawPistons(){{
                sides = 4;
                sinMag = 3.9f;
                lenOffset = -1.785f;
                angleOffset = 45f;
            }}, new DrawRegion("-shade"), new DrawDefault(), new DrawLiquidTile(Liquids.water, 39f / 4f), new DrawRegion("-top"));
            consumePower(3.5f);
            consumeItem(HIItems.rareEarth, 1);
            consumeLiquid(Liquids.water, 6f / 60f);
        }};
        nitrificationReactor = new GenericCrafter("nitrification-reactor"){{
            requirements(Category.crafting, with(Items.metaglass, 80, Items.silicon, 20, Items.plastanium, 30, HIItems.chromium, 40));
            size = 2;
            health = 380;
            armor = 5;
            hasLiquids = true;
            itemCapacity = 10;
            liquidCapacity = 24;
            craftTime = 60;
            outputLiquid = new LiquidStack(HILiquids.nitratedOil, 12f / 60f);
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawLiquidTile(Liquids.oil), new DrawLiquidTile(HILiquids.nitratedOil), new DrawDefault());
            consumePower(5f);
            consumeLiquid(Liquids.oil, 12f / 60f);
            consumeItem(Items.sporePod, 1);
        }};
        nitratedOilSedimentationTank = new Separator("nitrated-oil-sedimentation-tank"){{
            requirements(Category.crafting, with(Items.copper, 160, Items.graphite, 120, Items.plastanium, 40, HIItems.chromium, 60));
            size = 3;
            health = 680;
            armor = 8;
            itemCapacity = 15;
            liquidCapacity = 54;
            results = with(Items.pyratite, 1, Items.blastCompound, 4);
            craftTime = 12f;
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawLiquidTile(HILiquids.nitratedOil), new DrawDefault());
            ambientSound = HISounds.largeBeam;
            ambientSoundVolume = 0.24f;
            consumePower(4f);
            consumeLiquid(HILiquids.nitratedOil, 36f / 60f);
        }};
        //production-erekir
        ventHeater = new ThermalHeater("vent-heater"){{
            requirements(Category.crafting, with(Items.beryllium, 60, Items.graphite, 70, Items.tungsten, 80, Items.oxide, 50));
            size = 3;
            hasPower = false;
            hasLiquids = true;
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
            outputLiquid = new LiquidStack(Liquids.water, 5f / 60f / 9f);
            buildCostMultiplier = 0.8f;
        }
            @Override
            public void setStats(){
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
        //defense
        lighthouse = new LightBlock("lighthouse"){{
            requirements(Category.effect, BuildVisibility.lightingOnly, with(Items.graphite, 20, Items.silicon, 10, Items.lead, 30, Items.titanium, 15));
            size = 2;
            brightness = 1f;
            radius = 220f;
            consumePower(0.15f);
            buildCostMultiplier = 0.8f;
        }};
        mendDome = new MendProjector("mend-dome"){{
            requirements(Category.effect, with(Items.lead, 200, Items.titanium, 130, Items.silicon, 120, Items.plastanium, 60, Items.surgeAlloy, 40));
            size = 3;
            reload = 200f;
            range = 150f;
            healPercent = 15f;
            phaseBoost = 25f;
            phaseRangeBoost = 75;
            scaledHealth = 240;
            consumePower(2.5f);
            consumeItem(Items.phaseFabric, 1).boost();
        }};
        assignOverdrive = new AssignOverdrive("assign-overdrive"){{
            requirements(Category.effect, with(Items.silicon, 150,Items.thorium, 120, Items.plastanium, 100, Items.surgeAlloy, 60, HIItems.chromium, 80));
            size = 3;
            range = 240f;
            phaseRangeBoost = 0f;
            speedBoost = 2.75f;
            speedBoostPhase = 1.25f;
            useTime = 400f;
            maxLink = 9;
            hasBoost = true;
            squareSprite = false;
            strokeOffset = -0.05f;
            strokeClamp = 0.06f;
            consumePower(14f);
            consumeItem(Items.phaseFabric).boost();
        }};
        largeShieldGenerator = new ForceProjector("large-shield-generator") {{
            requirements(Category.effect, with(Items.silicon, 120, Items.lead, 250, Items.graphite, 180, Items.plastanium, 150, Items.phaseFabric, 40, HIItems.chromium, 60));
            size = 4;
            radius = 220f;
            shieldHealth = 20000f;
            cooldownNormal = 12f;
            cooldownLiquid = 6f;
            cooldownBrokenBase = 9f;
            itemConsumer = consumeItem(Items.phaseFabric).boost();
            phaseUseTime = 180f;
            phaseRadiusBoost = 100f;
            phaseShieldBoost = 15000f;
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
        coreBeStationed = new CoreBlock("core-be-stationed"){{
            requirements(Category.effect, with(Items.copper, 500, Items.lead, 350, Items.silicon, 200));
            size = 2;
            health = 900;
            itemCapacity = 1000;
            unitCapModifier = 4;
        }
            @Override
            public boolean canBreak(Tile tile){
                return Vars.state.teams.cores(tile.team()).size > 1;
            }

            @Override
            public boolean canReplace(Block other) {
                return other.alwaysReplace;
            }

            @Override
            public boolean canPlaceOn(Tile tile, Team team, int rotation){
                return Vars.state.teams.cores(team).size < 5;
            }
        };
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
        rapidUnloader = new AdaptUnloader("rapid-unloader"){{
            requirements(Category.effect, with(Items.silicon, 35, Items.plastanium, 15, Items.phaseFabric, 5));
            health = 60;
            speed = 60f / 30f;
        }};
        //unit
        titanReconstructor = new Reconstructor("titan-reconstructor"){{
            requirements(Category.units, with(Items.lead, 4000, Items.silicon, 3000, Items.plastanium, 1500, Items.surgeAlloy, 1200, HIItems.highEnergyFabric, 300, HIItems.chromium, 800));
            size = 11;
            liquidCapacity = 360f;
            scaledHealth = 100f;
            constructTime = 60f * 60f * 5f;
            upgrades.addAll(
                    new UnitType[]{UnitTypes.eclipse, HIUnitTypes.sunlit},
                    new UnitType[]{UnitTypes.toxopid, HIUnitTypes.cancer},
                    new UnitType[]{UnitTypes.reign, HIUnitTypes.suzerain},
                    new UnitType[]{UnitTypes.oct, HIUnitTypes.windstorm},
                    new UnitType[]{UnitTypes.corvus, HIUnitTypes.supernova},
                    new UnitType[]{UnitTypes.omura, HIUnitTypes.harpoon},
                    new UnitType[]{UnitTypes.navanax, HIUnitTypes.killerWhale}
            );
            consumePower(35f);
            consumeLiquid(Liquids.cryofluid, 4f);
            consumeItems(ItemStack.with(Items.silicon, 1500, HIItems.highEnergyFabric, 100, HIItems.uranium, 200, HIItems.chromium, 300));
        }};
        experimentalUnitFactory = new DerivativeUnitFactory("experimental-unit-factory"){{
            requirements(Category.units, with(Items.silicon, 2500, Items.plastanium, 1500, Items.surgeAlloy, 1000, HIItems.nanocore, 800, HIItems.highEnergyFabric, 400, HIItems.heavyAlloy, 600));
            size = 5;
            liquidCapacity = 60f;
            floating = true;
            config(Integer.class, (UnitFactoryBuild tile, Integer i) -> {
                tile.currentPlan = i < 0 || i >= plans.size ? -1 : i;
                tile.progress = 0f;
                tile.payload = null;
            });
            config(UnitType.class, (UnitFactoryBuild tile, UnitType val) -> {
                tile.currentPlan = plans.indexOf(p -> p.unit == val);
                tile.progress = 0f;
                tile.payload = null;
            });
            consumePower(40f);
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
                        if(u.armor < 55) plans.add(new UnitPlan(u, time * 2, os));
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
                        drag = -0.028f;
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
                        lifetime = 38;
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
            requirements(Category.turret, with(Items.copper, 100, Items.lead, 80, Items.graphite, 60, Items.titanium, 30));
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
                    Items.pyratite, new MissileBulletType(10f, 44f, name("rocket")){{
                        shrinkY = 0;
                        inaccuracy = 4;
                        trailChance = 0.8f;
                        homingRange = 80;
                        splashDamage = 68f;
                        splashDamageRadius = 36f;
                        lifetime = 32;
                        hitShake = 2;
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
                    Items.blastCompound, new MissileBulletType(10f, 46f, name("missile")){{
                        recoil = 1;
                        shrinkY = 0;
                        inaccuracy = 4;
                        trailChance = 0.8f;
                        homingRange = 80;
                        splashDamage = 132f;
                        splashDamageRadius = 76f;
                        lifetime = 32;
                        hitShake = 2;
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
                    Items.graphite, new MissileBulletType(8f, 22f, name("missile")){{
                        buildingDamageMultiplier = 0.3f;
                        splashDamage = 15f;
                        splashDamageRadius = 18f;
                        knockback = 0.7f;
                        lifetime = 135f;
                        homingDelay = 10f;
                        homingRange = 800f;
                        homingPower = 0.15f;
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
                    Items.pyratite, new MissileBulletType(7f, 14f, name("missile")){{
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
                    Items.blastCompound, new MissileBulletType(7f, 17f, name("missile")){{
                        buildingDamageMultiplier = 0.3f;
                        splashDamage = 55f;
                        splashDamageRadius = 45f;
                        knockback = 3;
                        status = StatusEffects.blasted;
                        lifetime = 145f;
                        homingPower = 0.15f;
                        homingDelay = 10f;
                        homingRange = 800f;
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
                    Items.surgeAlloy, new MissileBulletType(9f, 47f, name("missile")){{
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
            consumePower(6f);
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
            requirements(Category.turret, with(Items.copper, 350, Items.graphite, 300, Items.titanium, 150, Items.plastanium, 175, Items.surgeAlloy, 120));
            ammo(Items.copper, new BasicBulletType(11f, 19f){{
                width = 5f;
                height = 7f;
                lifetime = 25f;
                ammoMultiplier = 2;
            }}, Items.graphite, new BasicBulletType(13f, 37f){{
                width = 5.5f;
                height = 9f;
                reloadMultiplier = 0.6f;
                ammoMultiplier = 4;
                lifetime = 23f;
            }}, Items.pyratite, new BasicBulletType(13f, 24f){{
                width = 5f;
                height = 8f;
                frontColor = Pal.lightishOrange;
                backColor = Pal.lightOrange;
                status = StatusEffects.burning;
                hitEffect = new MultiEffect(Fx.hitBulletSmall, Fx.fireHit);
                homingPower = 0.07f;
                reloadMultiplier = 1.3f;
                ammoMultiplier = 5;
                lifetime = 23f;
                makeFire = true;
            }}, Items.silicon, new BasicBulletType(12f, 21f){{
                width = 5f;
                height = 8f;
                homingPower = 0.07f;
                reloadMultiplier = 1.3f;
                ammoMultiplier = 5;
                lifetime = 24f;
            }}, Items.thorium, new BasicBulletType(15f, 47f){{
                width = 6f;
                height = 11f;
                shootEffect = Fx.shootBig;
                smokeEffect = Fx.shootBigSmoke;
                ammoMultiplier = 4f;
                lifetime = 20f;
            }}, HIItems.uranium, new BasicBulletType(17f, 65f){{
                width = 7f;
                height = 13f;
                shootEffect = Fx.shootBig;
                smokeEffect = Fx.shootBigSmoke;
                status = StatusEffects.melting;
                ammoMultiplier = 6f;
                lifetime = 18f;
            }});
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
            shootEffect = smokeEffect = Fx.none;
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
            ammoUseEffect = HIFx.casing(32f);
        }};
        rend = new ItemTurret("rend"){{
            requirements(Category.turret, with(Items.copper, 30, Items.lead, 60, Items.graphite, 40, Items.titanium, 50));
            health = 960;
            range = 200;
            smokeEffect = Fx.shootBigSmoke;
            coolant = consumeCoolant(0.1F);
            shoot = new ShootSpread(){{
                shots = 12;
                shotDelay = 2f;
                spread = 0.55f;
            }};
            reload = 90f;
            recoil = 3f;
            shootCone = 30f;
            inaccuracy = 4f;
            size = 2;
            shootSound = Sounds.shootSnap;
            shake = 3f;
            ammo(Items.titanium, new BasicBulletType(5, 24){{
                width = 8f;
                height = 25f;
                hitColor = backColor = lightColor = trailColor = Items.titanium.color.cpy().lerp(Color.white, 0.1f);
                frontColor = backColor.cpy().lerp(Color.white, 0.35f);
                hitEffect = HIFx.crossBlast(hitColor, height + width);
                shootEffect = despawnEffect = HIFx.square(hitColor, 20f, 3, 12f, 2f);
                ammoMultiplier = 8;
                pierceArmor = true;
            }}, Items.plastanium, new BasicBulletType(5, 26){{
                width = 8f;
                height = 25f;
                fragBullets = 4;
                fragBullet = new BasicBulletType(2, 26){{
                    width = 3f;
                    lifetime = 10f;
                    height = 12f;
                    ammoMultiplier = 12;
                    hitColor = backColor = lightColor = trailColor = Items.plastanium.color.cpy().lerp(Color.white, 0.1f);
                    frontColor = backColor.cpy().lerp(Color.white, 0.35f);
                    hitEffect = HIFx.lightningHitSmall(backColor);
                    shootEffect = despawnEffect = HIFx.square45_4_45;
                }};
                fragAngle = 130f;
                fragVelocityMax = 1.1f;
                fragVelocityMin = 0.5f;
                fragLifeMax = 1.25f;
                fragLifeMin = 0.25f;
                ammoMultiplier = 12;
                hitColor = backColor = lightColor = trailColor = Items.plastanium.color.cpy().lerp(Color.white, 0.1f);
                frontColor = backColor.cpy().lerp(Color.white, 0.35f);
                hitEffect = HIFx.crossBlast(hitColor, height + width);
                shootEffect = despawnEffect = HIFx.square(hitColor, 20f, 3, 20f, 2f);
            }}, Items.thorium, new BasicBulletType(5, 18){{
                width = 8f;
                height = 25f;
                lightning = 2;
                lightningLength = 2;
                lightningLengthRand = 6;
                lightningDamage = damage;
                status = StatusEffects.shocked;
                statusDuration = 15f;
                ammoMultiplier = 12;
                lightningColor = hitColor = backColor = lightColor = trailColor = Items.thorium.color.cpy().lerp(Color.white, 0.1f);
                frontColor = backColor.cpy().lerp(Color.white, 0.35f);
                hitEffect = HIFx.crossBlast(hitColor, height + width);
                shootEffect = despawnEffect = HIFx.square(hitColor, 20f, 3, 20f, 2f);
            }}, Items.pyratite, new BasicBulletType(5, 18){{
                width = 8f;
                height = 25f;
                incendAmount = 4;
                incendChance = 0.25f;
                incendSpread = 12f;
                status = StatusEffects.burning;
                statusDuration = 15f;
                ammoMultiplier = 12;
                hitColor = backColor = lightColor = trailColor = Items.pyratite.color.cpy().lerp(Color.white, 0.1f);
                frontColor = backColor.cpy().lerp(Color.white, 0.35f);
                hitEffect = HIFx.crossBlast(hitColor, height + width);
                despawnEffect = Fx.blastExplosion;
                shootEffect = HIFx.square(hitColor, 20f, 3, 20f, 2f);
            }}, Items.blastCompound, new BasicBulletType(5, 22){{
                width = 8f;
                height = 25f;
                status = StatusEffects.blasted;
                statusDuration = 15f;
                splashDamageRadius = 12f;
                splashDamage = damage;
                ammoMultiplier = 8;
                hitColor = backColor = lightColor = trailColor = Items.blastCompound.color.cpy().lerp(Color.white, 0.1f);
                frontColor = backColor.cpy().lerp(Color.white, 0.35f);
                hitEffect = HIFx.crossBlast(hitColor, height + width);
                despawnEffect = Fx.blastExplosion;
                shootEffect = HIFx.square(hitColor, 20f, 3, 20f, 2f);
            }});
            limitRange();
            maxAmmo = 120;
            ammoPerShot = 12;
        }};
        fissure = new ItemTurret("fissure"){{
            requirements(Category.turret, with(Items.titanium, 110, Items.thorium, 90, Items.graphite, 150, Items.silicon, 120, Items.plastanium, 80));
            ammo(Items.titanium, new BasicBulletType(8f, 45f){{
                lifetime = 48f;
                width = 8f;
                height = 42f;
                shrinkX = 0;
                trailWidth = 1.7f;
                trailLength = 9;
                trailColor = backColor = hitColor = lightColor = lightningColor = Items.titanium.color;
                frontColor = backColor.cpy().lerp(Color.white, 0.35f);
                shootEffect = HIFx.square(backColor, 45f, 5, 38, 4);
                smokeEffect = Fx.shootBigSmoke;
                despawnEffect = HIFx.square(backColor, 85f, 5, 52, 5);
                hitEffect = HIFx.hitSparkLarge;
                ammoMultiplier = 4;
            }}, Items.thorium, new BasicBulletType(8f, 65f){{
                lifetime = 48f;
                width = 8f;
                height = 42f;
                shrinkX = 0;
                trailWidth = 1.7f;
                trailLength = 9;
                trailColor = backColor = hitColor = lightColor = lightningColor = Items.thorium.color;
                frontColor = backColor.cpy().lerp(Color.white, 0.35f);
                shootEffect = HIFx.square(backColor, 45f, 5, 38, 4);
                smokeEffect = Fx.shootBigSmoke;
                despawnEffect = HIFx.square(backColor, 85f, 5, 52, 5);
                hitEffect = HIFx.hitSparkLarge;
                ammoMultiplier = 4;
            }}, Items.plastanium, new BasicBulletType(8f, 60f){{
                lifetime = 48f;
                width = 8f;
                height = 42f;
                shrinkX = 0;
                splashDamage = 25f;
                splashDamageRadius = 20f;
                trailWidth = 1.7f;
                trailLength = 9;
                trailColor = backColor = hitColor = lightColor = lightningColor = Pal.plastanium;
                frontColor = backColor.cpy().lerp(Color.white, 0.35f);
                shootEffect = HIFx.square(backColor, 45f, 5, 38, 4);
                smokeEffect = Fx.shootBigSmoke;
                despawnEffect = HIFx.square(backColor, 85f, 5, 52, 5);
                hitEffect = HIFx.hitSparkLarge;
                ammoMultiplier = 4;
            }}, Items.pyratite, new BasicBulletType(8f, 40f){{
                lifetime = 48f;
                width = 8f;
                height = 42f;
                shrinkX = 0;
                splashDamage = 35f;
                splashDamageRadius = 25f;
                trailWidth = 1.7f;
                trailLength = 9;
                status = StatusEffects.burning;
                trailColor = backColor = hitColor = lightColor = lightningColor = Items.pyratite.color;
                frontColor = backColor.cpy().lerp(Color.white, 0.35f);
                shootEffect = HIFx.square(backColor, 45f, 5, 38, 4);
                smokeEffect = Fx.shootBigSmoke;
                despawnEffect = HIFx.square(backColor, 85f, 5, 52, 5);
                hitEffect = HIFx.hitSparkLarge;
                ammoMultiplier = 4;
            }}, Items.blastCompound, new BasicBulletType(8f, 40f){{
                lifetime = 48f;
                width = 8f;
                height = 42f;
                shrinkX = 0;
                splashDamage = 55f;
                splashDamageRadius = 35f;
                trailWidth = 1.7f;
                trailLength = 9;
                status = StatusEffects.blasted;
                trailColor = backColor = hitColor = lightColor = lightningColor = Items.blastCompound.color;
                frontColor = backColor.cpy().lerp(Color.white, 0.35f);
                shootEffect = HIFx.square(backColor, 45f, 5, 38, 4);
                smokeEffect = Fx.shootBigSmoke;
                despawnEffect = HIFx.square(backColor, 85f, 5, 52, 5);
                hitEffect = HIFx.hitSparkLarge;
                ammoMultiplier = 4;
            }}, HIItems.chromium, new BasicBulletType(8f, 125f){{
                lifetime = 48f;
                width = 8f;
                height = 42f;
                shrinkX = 0;
                pierce = true;
                pierceCap = 3;
                pierceArmor = true;
                trailWidth = 1.7f;
                trailLength = 9;
                status = HIStatusEffects.armorReduction;
                trailColor = backColor = hitColor = lightColor = lightningColor = HIPal.chromiumGrey;
                frontColor = backColor.cpy().lerp(Color.white, 0.35f);
                shootEffect = HIFx.square(backColor, 45f, 5, 38, 4);
                smokeEffect = Fx.shootBigSmoke;
                despawnEffect = HIFx.square(backColor, 85f, 5, 52, 5);
                hitEffect = HIFx.hitSparkLarge;
                ammoMultiplier = 4;
            }});
            size = 3;
            health = 1420;
            reload = 12f;
            inaccuracy = 0.75f;
            recoil = 0.5f;
            coolant = consumeCoolant(0.2f);
            drawer = new DrawTurret(){{
                parts.add(new RegionPart("-shooter"){{
                    under = true;
                    outline = true;
                    moveY = -3f;
                    progress = PartProgress.recoil;
                }});
            }};
            coolantMultiplier = 2f;
            velocityRnd = 0.075f;
            unitSort = UnitSorts.weakest;
            range = 360f;
            shootSound = HISounds.fissure;
            shoot = new ShootMulti(new ShootPattern(), new ShootBarrel(){{
                barrels = new float[]{-6.5f, 3f, 0f};
            }}, new ShootBarrel(){{
                barrels = new float[]{6.5f, 3f, 0f};
            }});
        }};
        frost = new PowerTurret("frost"){{
            requirements(Category.turret, with(Items.silicon, 600, Items.metaglass, 400, Items.plastanium, 350, HIItems.chromium, 220, HIItems.highEnergyFabric, 120));
            consumePower(12f);
            coolant = consumeCoolant(0.5f);
            coolantMultiplier = 1.2f;
            range = 55 * 8;
            reload = 120;
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
            outlineColor = Pal.darkOutline;
            drawer = new DrawTurret("reinforced-"){{parts.add(new DrawFrostWing(){{
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
            BulletType frostFragBullrt = new AimToPosBulletType(){{
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
                        Position p1 = HIPal.pos(tb.x, tb.y);
                        Position p2 = HIPal.pos(b.x, b.y);
                        Position[] pos = {p1, p2};
                        frostFragBullrt.create(tb, tb.team, tb.x, tb.y, tb.rotation - 180 + angleOffset + Mathf.random(-5, 5), -1, 1, 1, pos);
                    }
                }
                @Override
                public void createFrags(Bullet b, float x, float y) {}
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
            squareSprite = false;
            buildCostMultiplier = 0.8f;
        }};
        judgement = new ContinuousTurret("judgement"){{
            requirements(Category.turret, with(Items.silicon, 1200, Items.metaglass, 400, Items.plastanium, 800, Items.surgeAlloy, 650, HIItems.highEnergyFabric, 550, HIItems.heavyAlloy, 400));
            shootType = new PointLaserBulletType(){{
                damage = 100f;
                hitEffect = HIFx.hitSpark;
                beamEffect = Fx.none;
                beamEffectInterval = 0;
                buildingDamageMultiplier = 0.75f;
                damageInterval = 1;
                color = hitColor = Pal.techBlue;
                sprite = "laser-white";
                status = StatusEffects.melting;
                statusDuration = 60;
                oscScl /= 1.77f;
                oscMag /= 1.33f;
                hitShake = 2;
                range = 75 * 8;
                trailLength = 8;
            }
                private final Color tmpColor = new Color();
                private final Color from = color, to = HIPal.highEnergyYellow;
                private final static float chargeReload = 65f;
                private final static float lerpReload = 10f;

                private boolean charged(Bullet b){
                    return b.fdata > chargeReload;
                }

                private Color getColor(Bullet b){
                    return tmpColor.set(from).lerp(to, warmup(b));
                }

                private float warmup(Bullet b){
                    return Mathf.curve(b.fdata, chargeReload - lerpReload / 2f, chargeReload + lerpReload / 2f);
                }

                @Override
                public void update(Bullet b){
                    super.update(b);

                    float maxDamageMultiplier = 1.5f;
                    b.damage = damage * (1 + warmup(b) * maxDamageMultiplier);

                    boolean cool = false;

                    if(b.data == null)cool = true;
                    else if(b.data instanceof Healthc h){
                        if(!h.isValid() || !h.within(b.aimX, b.aimY, ((Sized)h).hitSize() + 4)){
                            b.data = null;
                            cool = true;
                        }
                    }

                    if(cool){
                        b.fdata = Mathf.approachDelta(b.fdata, 0, 1);
                    }else b.fdata = Math.min(b.fdata, chargeReload + lerpReload / 2f + 1f);

                    if(charged(b)){
                        if(!Vars.headless && b.timer(3, 3)){
                            PositionLightning.createEffect(b, Tmp.v1.set(b.aimX, b.aimY), getColor(b), 1, 2);
                            if(Mathf.chance(0.25)) HIFx.hitSparkLarge.at(b.x, b.y, tmpColor);
                        }

                        if(b.timer(4, 2.5f)){
                            Lightning.create(b, getColor(b), b.damage() / 2f, b.aimX, b.aimY, b.rotation() + Mathf.range(34f), Mathf.random(5, 12));
                        }
                    }
                }

                @Override
                public void draw(Bullet b){
                    float darkenPartWarmup = warmup(b);
                    float stroke =  b.fslope() * (1f - oscMag + Mathf.absin(Time.time, oscScl, oscMag)) * (darkenPartWarmup + 1) * 5;

                    if(trailLength > 0 && b.trail != null){
                        float z = Draw.z();
                        Draw.z(z - 0.0001f);
                        b.trail.draw(getColor(b), stroke);
                        Draw.z(z);
                    }

                    Draw.color(getColor(b));
                    DrawFunc.basicLaser(b.x, b.y, b.aimX, b.aimY, stroke);
                    Draw.color(Color.white);
                    DrawFunc.basicLaser(b.x, b.y, b.aimX, b.aimY, stroke * 0.64f * (2 + darkenPartWarmup) / 3f);

                    Drawf.light(b.aimX, b.aimY, b.x, b.y, stroke, tmpColor, 0.76f);
                    Drawf.light(b.x, b.y, stroke * 4, tmpColor, 0.76f);
                    Drawf.light(b.aimX, b.aimY, stroke * 3, tmpColor, 0.76f);

                    Draw.color(tmpColor);
                    if(charged(b)){
                        float qW = Mathf.curve(warmup(b), 0.5f, 0.7f);

                        for(int s : Mathf.signs){
                            Drawf.tri(b.x, b.y, 6, 21 * qW, 90 * s + Time.time * 1.8f);
                        }

                        for(int s : Mathf.signs){
                            Drawf.tri(b.x, b.y, 7.2f, 25 * qW, 90 * s + Time.time * -1.1f);
                        }
                    }

                    int particles = 44;
                    float particleLife = 74f;
                    float particleLen = 7.5f;
                    Rand rand = new Rand(b.id);

                    float base = (Time.time / particleLife);
                    for(int i = 0; i < particles; i++){
                        float fin = (rand.random(1f) + base) % 1f, fout = 1f - fin, fslope = HIFx.fslope(fin);
                        float len = rand.random(particleLen * 0.7f, particleLen * 1.3f) * Mathf.curve(fin, 0.2f, 0.9f) * (darkenPartWarmup / 2.5f + 1);
                        float centerDeg = rand.random(Mathf.pi);

                        Tmp.v1.trns(b.rotation(), Interp.pow3In.apply(fin) * rand.random(44, 77) - rand.range(11) - 8, (((rand.random(22, 35) * (fout + 1) / 2 + 2) / (3 * fin / 7 + 1.3f) - 1) + rand.range(4)) * Mathf.cos(centerDeg));
                        float angle = Mathf.slerp(Tmp.v1.angle() - 180, b.rotation(), Interp.pow2Out.apply(fin));
                        Tmp.v1.scl(darkenPartWarmup / 3.7f + 1);
                        Tmp.v1.add(b);

                        Draw.color(Tmp.c2.set(tmpColor), Color.white, fin * 0.7f);
                        Lines.stroke(Mathf.curve(fslope, 0, 0.42f) * 1.4f * b.fslope() * Mathf.curve(fin, 0, 0.6f));
                        Lines.lineAngleCenter(Tmp.v1.x, Tmp.v1.y, angle, len);
                    }

                    if(darkenPartWarmup > 0.005f){
                        tmpColor.lerp(Color.black, 0.86f);
                        Draw.color(tmpColor);
                        DrawFunc.basicLaser(b.x, b.y, b.aimX, b.aimY, stroke * 0.55f * darkenPartWarmup);
                        Draw.z(HIFx.EFFECT_BOTTOM);
                        DrawFunc.basicLaser(b.x, b.y, b.aimX, b.aimY, stroke * 0.6f * darkenPartWarmup);
                        Draw.z(Layer.bullet);
                    }

                    Draw.reset();
                }

                @Override
                public void hit(Bullet b, float x, float y){
                    if(Mathf.chance(0.4))hitEffect.at(x, y, b.rotation(), getColor(b));
                    hitSound.at(x, y, hitSoundPitch, hitSoundVolume);

                    Effect.shake(hitShake, hitShake, b);

                    if(fragOnHit){
                        createFrags(b, x, y);
                    }
                }

                @Override
                public void hitEntity(Bullet b, Hitboxc entity, float health){
                    if(entity instanceof Healthc h){
                        if(charged(b)){
                            h.damagePierce(b.damage);
                        }else{
                            h.damage(b.damage);
                        }
                    }

                    if(charged(b) && entity instanceof Unit unit){
                        unit.apply(status, statusDuration);
                    }

                    if(entity == b.data)b.fdata += Time.delta;
                    else b.fdata = 0;
                    b.data = entity;
                }

                @Override
                public void hitTile(Bullet b, Building build, float x, float y, float initialHealth, boolean direct){
                    super.hitTile(b, build, x, y, initialHealth, direct);
                    if(build == b.data)b.fdata += Time.delta;
                    else b.fdata = 0;
                    b.data = build;
                }
            };
            drawer = new DrawTurret(){{parts.addAll(new RegionPart("-charger"){{
                mirror = true;
                under = true;
                moveRot = 10;
                moveX = 4.677f;
                moveY = 6.8f;
            }}, new RegionPart("-side"){{
                mirror = true;
                under = true;
                moveRot = 10;
                moveX = 2.75f;
                moveY = 2;
            }}, new RegionPart("-barrel"){{
                moveY = -7.5f;
                progress = progress.curve(Interp.pow2Out);
            }});
            }};
            shootSound = Sounds.none;
            loopSoundVolume = 1f;
            loopSound = HISounds.largeBeam;
            shootWarmupSpeed = 0.08f;
            shootCone = 360f;
            aimChangeSpeed = 1.75f;
            rotateSpeed = 1.45f;
            canOverdrive = false;
            shootY = 16f;
            minWarmup = 0.8f;
            warmupMaintainTime = 45;
            shootWarmupSpeed /= 2;
            outlineColor = Pal.darkOutline;
            size = 5;
            range = 420f;
            scaledHealth = 300;
            armor = 10;
            unitSort = UnitSorts.strongest;
            squareSprite = false;
            consumePower(16);
            consumeLiquid(HILiquids.nanofluid, 12f / 60f);
        }};
        fiammetta = new ItemTurret("fiammetta"){{
            requirements(Category.turret, with(Items.silicon, 800, Items.plastanium, 650, Items.surgeAlloy, 550, HIItems.highEnergyFabric, 350, HIItems.chromium, 400));
            BulletType fall = new BulletType(0f, 0f){{
                lifetime = 20f;
                collides = hittable = absorbable = false;
                collidesTiles = collidesAir = collidesGround = true;
                pierce = pierceBuilding = true;
                splashDamage = 1500;
                splashDamageRadius = 14f * 8f;
                despawnEffect = hitEffect = new MultiEffect(HIFx.expFtEffect(10, 15, splashDamageRadius, 30, 0.2f), HIFx.fiammettaExp(splashDamageRadius), new Effect(20, e -> {
                    Lines.stroke(16 * e.fout(), HIItems.highEnergyFabric.color);
                    Lines.circle(e.x, e.y, (splashDamageRadius + 56) * e.fin());
                }));
                keepVelocity = false;
                buildingDamageMultiplier = 0.6f;
                hitSound = despawnSound = Sounds.largeExplosion;
                despawnShake = hitShake = 8;
                status = StatusEffects.blasted;
                makeFire = true;
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
                    Items.surgeAlloy, new ArtilleryBulletType(10f,0f){{
                        ammoMultiplier = 3;
                        splashDamage = 1500;
                        splashDamageRadius = 14 * 8f;
                        collides = hittable = absorbable = false;
                        collidesTiles = collidesAir = collidesGround = true;
                        pierce = pierceBuilding = true;
                        despawnEffect = Fx.none;
                        hitEffect = Fx.none;
                        trailEffect = Fx.none;
                        fragOnHit = false;
                        rangeChange = 10 * 8;
                        trailLength = 20;
                        trailWidth = 12;
                        trailColor = HIItems.highEnergyFabric.color.cpy().a(0.6f);
                        buildingDamageMultiplier = 0.3f;
                        status = StatusEffects.blasted;
                        makeFire = true;
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
                    },
                    Items.phaseFabric, new BulletType(15f, 150){{
                        chargeEffect = HIFx.aimEffect(40, HIItems.highEnergyFabric.color, 1.5f, range, 13);
                        ammoMultiplier = 2;
                        splashDamageRadius = 10 * 8;
                        splashDamage = 400;
                        lifetime = 30;
                        pierce = pierceBuilding = true;
                        hittable = absorbable = false;
                        reflectable = false;
                        intervalBullet = new BulletType(0f, 100){{
                            lifetime = 32;
                            despawnEffect = hitEffect = new MultiEffect(new Effect(30, e -> {
                                float r = Math.min(10 * 8 * e.fin(), 6 * 8);
                                Draw.color(HIItems.highEnergyFabric.color.cpy().a(e.fout()));
                                Fill.circle(e.x, e.y, r);
                                float ww = r * 2f, hh = r * 2f;
                                Draw.color(HIItems.highEnergyFabric.color.cpy().a(e.fout()));
                                Draw.rect(Core.atlas.find(name("firebird-light")), e.x, e.y, ww, hh);
                            }), HIFx.expFtEffect(5, 12, 6 * 4, 30, 0.2f));
                            despawnSound = hitSound = Sounds.explosion;
                            absorbable = hittable = false;
                            pierce = pierceBuilding = true;
                            splashDamageRadius = 6 * 8;
                            splashDamage = 300;
                            status = StatusEffects.burning;
                            makeFire = true;
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
                        status = StatusEffects.burning;
                        makeFire = true;
                    }
                        @Override
                        public void draw(Bullet b) {
                            super.draw(b);
                            Draw.color(HIItems.highEnergyFabric.color);
                            Draw.rect(Core.atlas.find(name("phx")), b.x, b.y,48, 48,  b.rotation() - 90);
                        }
                    }
            );
            drawer = new DrawTurret("reinforced-");
            outlineColor = Pal.darkOutline;
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
            squareSprite = false;
            buildCostMultiplier = 0.5f;
        }};
        wisadel = new ItemTurret("wisadel"){{
            requirements(Category.turret, with(Items.silicon, 1500, Items.plastanium, 1000, Items.surgeAlloy, 900, HIItems.highEnergyFabric, 500, HIItems.heavyAlloy, 550));
            ammo(HIItems.highEnergyFabric, new TrailFadeBulletType(60f, 350f, "missile-large"){{
                ammoMultiplier = 1f;
                shootEffect = smokeEffect = Fx.none;
                width = 16f;
                height = 50f;
                shrinkY = 0f;
                splashDamage = 2250f;
                splashDamageRadius = 12 * 8f;
                buildingDamageMultiplier = 0.5f;
                pierceArmor = true;
                homingPower = 1f;
                homingRange = 80f;
                lifetime = 12.8f;
                trailWidth = 3f;
                trailLength = 9;
                trailChance = 1f;
                frontColor = lightningColor = hitColor = backColor = trailColor = lightColor = HIPal.wisadelRed;
                lightning = 4;
                lightningLength = 6;
                lightningLengthRand = 10;
                hitSound = despawnSound = Sounds.largeExplosion;
                hitShake = 3f;
                trailEffect = new Effect(50, e -> {
                    Draw.color(e.color);
                    Fill.circle(e.x + Mathf.randomSeed(e.id, -5, 5), e.y + Mathf.randomSeed(e.id, -5, 5), e.rotation * 2 * e.fout());
                }).layer(Layer.bullet - 1e-2f);
                fragBullets = 9;
                fragRandomSpread = 0f;
                fragSpread = 360 / 9f;
                fragLifeMin = 0.1f;
                fragBullet = new ArtilleryBulletType(6f, 120f){{
                    buildingDamageMultiplier = 0.3f;
                    drag = 0.02f;
                    hitEffect = despawnEffect = new MultiEffect(Fx.scatheSlash, Fx.massiveExplosion, new ExplosionEffect(){{
                        waveLife = 12f;
                        waveRad = 48f;
                        sparks = 8;
                        smokes = 8;
                        lifetime = 30f;
                        sparkColor = HIPal.wisadelRed;
                        sparkLen = 10f;
                        sparkStroke = 2f;
                        sparkRad = 48f;
                        smokeSize = 4f;
                        smokeRad = 48f;
                    }});
                    knockback = 0.8f;
                    lifetime = 18f;
                    width = height = 18f;
                    collidesTiles = false;
                    splashDamageRadius = 48f;
                    splashDamage = 220f;
                    backColor = trailColor = hitColor = HIPal.wisadelRed;
                    frontColor = Color.white;
                    despawnShake = 7f;
                    lightRadius = 30f;
                    lightColor = HIPal.wisadelRed;
                    lightOpacity = 0.5f;
                    trailLength = 20;
                    trailWidth = 3.5f;
                    trailEffect = Fx.none;
                }};
                chargeEffect = new MultiEffect(HIFx.aimEffect(180, HIPal.wisadelRed, 1f, 45 * 8f, 16), new Effect(180, e -> {
                    Draw.color(HIPal.wisadelRed);
                    Fill.circle(e.x, e.y, 8.5f * e.finpow());
                    float z = Draw.z();
                    Draw.z(Layer.max - 8);
                    Draw.color(Color.black);
                    Fill.circle(e.x, e.y, 7.8f * e.finpow());
                    Draw.z(z);
                    Angles.randLenVectors(e.id, 8, 36 * e.foutpow(), Mathf.randomSeed(e.id, 360), 360, (x, y) -> {
                        Draw.color(HIPal.wisadelRed);
                        Fill.circle(e.x + x, e.y + y, 5f * e.foutpow());
                        float zs = Draw.z();
                        Draw.z(Layer.max - 10);
                        Draw.color(Color.black);
                        Fill.circle(e.x + x, e.y + y, 5f * e.foutpow());
                        Draw.z(zs);
                    });
                }), EffectWrapper.wrap(HIFx.square45_6_45_Charge, backColor));
                chargeSound = Sounds.lasercharge;
                despawnEffect = hitEffect = new MultiEffect(new Effect(60, 100, e -> {
                    float rad = splashDamageRadius;
                    Lines.stroke(e.foutpow() * 3, HIPal.wisadelRed);
                    Lines.circle(e.x, e.y, rad * e.finpow());
                }), new ExplosionEffect(){{
                    waveLife = 0f;
                    waveRad = 0f;
                    sparks = 8;
                    smokes = 8;
                    lifetime = 50f;
                    sparkColor = HIPal.wisadelRed;
                    sparkLen = 10f;
                    sparkStroke = 4f;
                    sparkRad = splashDamageRadius;
                    smokeSize = 9f;
                    smokeRad = splashDamageRadius;
                }});
                status = StatusEffects.blasted;
                intervalBullets = 2;
                bulletInterval = 5f;
                intervalBullet = new AdaptedLightningBulletType(){{
                    lightningColor = trailColor = hitColor = lightColor = HIPal.wisadelRed;
                    lightningLength = 4;
                    lightningLengthRand = 15;
                    damage = 200;
                }};
            }});
            size = 5;
            armor = 36;
            health = 18700;
            drawer = new DrawMulti(new DrawTurret(){{parts.addAll(new RegionPart("-behind"){{
                progress = PartProgress.warmup;
                moveY = 7f;
                mirror = false;
                moves.add(new PartMove(PartProgress.recoil, 0f, -3f, 0f));
                under = true;
            }}, new RegionPart("-mid"){{
                mirror = false;
            }}, new RegionPart("-front"){{
                progress = PartProgress.warmup;
                moveY = 7f;
                mirror = false;
                moves.add(new PartMove(PartProgress.recoil, 0f, -3f, 0f));
            }}, new PartBow(){{
                color = HIPal.wisadelRed;
                turretTk = 6;
                bowFY = -4;
                bowMoveY = -33 - bowFY;
                bowTk = 6;
                bowWidth = 28;
                bowHeight = 12f;
            }}, new BowHalo(){{
                color = HIPal.wisadelRed;
                stroke = 3;
                radius = 9;
                w1 = 2.8f;
                h1 = 6;
                w2 = 4;
                h2 = 13;
                y = -21;
                sinWave = false;
            }});
            }}, new RunningLight(6){{
                color = HIPal.wisadelRed;
            }});
            range = 660;
            ammoPerShot = 10;
            maxAmmo = 30;
            shake = 6f;
            recoil = 4f;
            reload = 200f;
            shootY = 0f;
            rotateSpeed = 1.2f;
            minWarmup = 0.95f;
            shootWarmupSpeed = 0.04f;
            warmupMaintainTime = 60f;
            shootSound = Sounds.release;
            coolant = new ConsumeCoolant(0.5f);
            coolantMultiplier = 1f;
            canOverdrive = false;
            squareSprite = false;
            buildCostMultiplier = 0.5f;
        }};
        spark = new MultiBulletTurret("spark"){{
            requirements(Category.turret, with(Items.graphite, 120, Items.silicon, 160, Items.titanium, 140, Items.thorium, 80));
            ammo(
                    Items.blastCompound, new BulletType[]{
                            new FireWorkBulletType(100, 4, name("mb-fireworks"), Color.valueOf("ea8878"), 6 * 8),
                            new FireWorkBulletType(100, 4, Color.valueOf("5cfad5")),
                            new FireWorkBulletType(100, 4){{
                                colorful = true;
                                fire = new colorFire(false, 3f, 60){{
                                    stopFrom = 0f;
                                    stopTo = 0f;
                                    trailLength = 9;
                                }};
                                splashDamageRadius = 10 * tilesize;
                                trailInterval = 0;
                                trailWidth = 2;
                                trailLength = 8;
                            }}
                    },
                    Items.plastanium, new BulletType[]{
                            new FireWorkBulletType(88, 4, name("mb-fireworks"), Color.valueOf("ea8878"), 6 * 8){{
                                status = StatusEffects.none;
                            }},
                            new FireWorkBulletType(88, 4, Color.valueOf("5cfad5")){{
                                status = StatusEffects.none;
                            }},
                            new FireWorkBulletType(88, 4, Items.plastanium.color){{
                                fire = new colorFire(true, 5f, 60){{
                                    trailLength = 9;
                                    stopFrom = 0.1f;
                                    stopTo = 0.7f;
                                }};
                                splashDamageRadius = 8 * tilesize;
                            }}
                    }
            );
            drawer = new DrawTurret("reinforced-");
            outlineColor = Pal.darkOutline;
            shoot = new ShootSpread(2, 4);
            inaccuracy = 3;
            scaledHealth = 180;
            size = 3;
            range = 27f * 8;
            shake = 2f;
            recoil = 1f;
            reload = 60f;
            shootY = 12f;
            rotateSpeed = 3.2f;
            coolant = consumeCoolant(0.3f);
            shootSound = Sounds.missile;
            squareSprite = false;
        }};
        fireworks = new MultiBulletTurret("fireworks"){{
            requirements(Category.turret, with(Items.silicon, 600, Items.graphite, 800, Items.thorium, 500, HIItems.uranium, 400, HIItems.heavyAlloy, 300));
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
                    new FireWorkBulletType(120, 5f, name("mb-fireworks"), Color.valueOf("FFF980"), 6 * 8){{
                        outline = true;
                        trailInterval = 0f;
                        trailWidth = 2f;
                        trailLength = 10;
                        weaveMag = 8f;
                        weaveScale = 2f;
                        fire = new colorFire(false, 2.3f, 60){{
                            stopFrom = 0.55f;
                            stopTo = 0.55f;
                            rotSpeed = 666f;
                            hittable = true;
                        }};
                        textFire = new spriteBullet(name("fire-fireworks2"));
                        status = StatusEffects.none;
                        num = 18;
                    }},
                    new FireWorkBulletType(120, 5, name("mb-fireworks"), Color.valueOf("80FF9D"), 6 * 8){{
                        outline = true;
                        trailInterval = 0f;
                        trailWidth = 2.4f;
                        trailLength = 10;
                        homingPower = 1f;
                        homingRange = 32 * 8;
                        width = 10f;
                        height = 10f;
                        status = StatusEffects.electrified;
                        fire = new colorFire(false, 2.3f, 60){{
                            stopFrom = 0.55f;
                            stopTo = 0.55f;
                            rotSpeed = 666f;
                        }};
                        num = 10;
                    }},
                    new BulletType(){{
                        ammoMultiplier = 1;
                        damage = 0f;
                        speed = 0f;
                        lifetime = 0f;
                        fragBullet = new FireWorkBulletType(110, 6, name("mb-fireworks"), Color.valueOf("80B5FF"), 8 * 8){{
                            outline = true;
                            trailInterval = 0f;
                            trailWidth = 3f;
                            trailLength = 10;
                            width = 19f;
                            height = 19f;
                            status = StatusEffects.wet;
                            weaveMag = 8f;
                            weaveScale = 6f;
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
                        damage = 0f;
                        speed = 0f;
                        lifetime = 0f;
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
                        textFire = new spriteBullet(name("fire-fireworks3"), 128f, 128f);
                        fire = new colorFire(false, 3f, 60f){{
                            stopFrom = 0.6f;
                            stopTo = 0.6f;
                            rotSpeed = 666f;
                            hittable = true;
                        }};
                    }}
            });
            drawer = new DrawTurret("reinforced-");
            outlineColor = Pal.darkOutline;
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
            squareSprite = false;
            buildCostMultiplier = 0.6f;
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
                        smokeEffect = new Effect(32f, 64f, e -> {
                            color(HIPal.tracerBlue.cpy().lerp(Color.white, 0.2f), HIPal.tracerBlue, Color.gray, e.fin());
                            randLenVectors(e.id, 8, e.finpow() * 60f, e.rotation, 10f, (x, y) ->
                                Fill.circle(e.x + x, e.y + y, 0.65f + e.fout() * 1.5f)
                            );
                        });
                        despawnEffect = Fx.none;
                        trailColor = HIPal.tracerBlue;
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
                            colorFrom = HIPal.tracerBlue;
                            colorTo = HIPal.tracerBlue.cpy().a(0f);
                        }}, new WaveEffect(){{
                            lifetime = 11;
                            sizeFrom = strokeTo = 0;
                            sizeTo = 75;
                            strokeFrom = 5;
                            colorFrom = colorTo = HIPal.tracerBlue;
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
                                colorFrom = colorTo = HIPal.tracerBlue;
                            }}, new WaveEffect(){{
                                lifetime = 15f;
                                sizeFrom = strokeTo = 0f;
                                sizeTo = 35f;
                                strokeFrom = 2f;
                                colorFrom = colorTo = HIPal.tracerBlue;
                            }});
                            despawnEffect = new ParticleEffect(){{
                                particles = 1;
                                sizeFrom = 3f;
                                sizeTo = length = baseLength = 0f;
                                lifetime = 8f;
                                colorFrom = colorTo = HIPal.tracerBlue;
                            }};
                            sprite = name("tracer-bullet");
                            frontColor = Color.white;
                            backColor = trailColor = HIPal.tracerBlue;
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
            outlineColor = Pal.darkOutline;
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
                    colorTo = HIPal.tracerBlue;
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
                    color = Color.white.cpy().a(0f);
                    colorTo = HIPal.tracerBlue;
                    layer = 110;
                    tri = true;
                    radiusTo = 10;
                    triLengthTo = 16;
                }}, new ShapePart(){{
                    progress = PartProgress.warmup;
                    colorTo = color = HIPal.tracerBlue;
                    layer = 110;
                    stroke = radius = 0;
                    strokeTo = 2;
                    circle = hollow = true;
                    radiusTo = 55;
                }}, new HaloPart(){{
                    progress = PartProgress.warmup;
                    shapes = 4;
                    color = Color.white.cpy().a(0f);
                    colorTo = HIPal.tracerBlue;
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
                    color = Color.white.cpy().a(0f);
                    colorTo = HIPal.tracerBlue;
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
                    color = Color.white.cpy().a(0f);
                    colorTo = HIPal.tracerBlue;
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
            outlineColor = Pal.darkOutline;
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
