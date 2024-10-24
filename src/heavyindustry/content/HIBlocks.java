package heavyindustry.content;

import heavyindustry.entities.*;
import heavyindustry.entities.bullet.*;
import heavyindustry.gen.*;
import heavyindustry.graphics.*;
import heavyindustry.world.blocks.environment.*;
import heavyindustry.world.blocks.defense.*;
import heavyindustry.world.blocks.defense.turrets.*;
import heavyindustry.world.blocks.distribution.*;
import heavyindustry.world.blocks.payload.*;
import heavyindustry.world.blocks.power.*;
import heavyindustry.world.blocks.production.*;
import heavyindustry.world.blocks.liquid.*;
import heavyindustry.world.blocks.heat.*;
import heavyindustry.world.blocks.storage.*;
import heavyindustry.world.blocks.units.*;
import heavyindustry.world.blocks.logic.*;
import heavyindustry.world.draw.*;
import heavyindustry.world.meta.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.math.geom.*;
import arc.util.*;
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
import mindustry.world.blocks.logic.*;
import mindustry.world.blocks.power.*;
import mindustry.world.blocks.production.*;
import mindustry.world.blocks.storage.*;
import mindustry.world.blocks.units.*;
import mindustry.world.consumers.*;
import mindustry.world.draw.*;
import mindustry.world.meta.*;

import static heavyindustry.core.HeavyIndustryMod.*;
import static arc.Core.*;
import static mindustry.Vars.*;
import static mindustry.type.ItemStack.*;

/**
 * Defines the {@linkplain Block blocks} this mod offers.
 * @author Wisadell
 */
public class HIBlocks {
    public static Block
            //environment
            darkPanel7,darkPanel8,darkPanel9,darkPanel10,darkPanel11,darkPanelDamaged,
            stoneVent,basaltVent,shaleVent,basaltWall,snowySand,snowySandWall,arkyciteSand,arkyciteSandWall,arkyciteSandBoulder,darksandBoulder,asphalt,asphaltSide,
            labFloor,labFloorDark,
            nanofluid,
            stoneWater,shaleWater,basaltWater,darkWater,deepDarkWater,mudDarkWater,
            mud,overgrownGrass,overgrownShrubs,overgrownPine,
            corruptedMoss,corruptedSporeMoss,corruptedSporeRocks,corruptedSporePine,corruptedSporeFern,corruptedSporePlant,corruptedSporeTree,
            mycelium,myceliumSpore,myceliumShrubs,myceliumPine,
            softRareEarth,patternRareEarth,softRareEarthWall,
            oreUranium,oreChromium,
            //wall
            copperWallHuge,copperWallGigantic,titaniumWallHuge,titaniumWallGigantic,armoredWall,armoredWallLarge,armoredWallHuge,armoredWallGigantic,uraniumWall,uraniumWallLarge,chromiumWall,chromiumWallLarge,chromiumDoor,chromiumDoorLarge,heavyAlloyWall,heavyAlloyWallLarge,nanoCompositeWall,nanoCompositeWallLarge, shapedWall,
            //wall-erekir
            berylliumWallHuge,berylliumWallGigantic,tungstenWallHuge,tungstenWallGigantic,blastDoorLarge,blastDoorHuge,reinforcedSurgeWallHuge,reinforcedSurgeWallGigantic,carbideWallHuge,carbideWallGigantic,shieldedWallLarge,shieldedWallHuge,
            aparajito,aparajitoLarge,
            //drill
            titaniumDrill,largeWaterExtractor,slagExtractor,oilRig,cuttingDrill,beamDrill,implosionDrill,speedModule,refineModule,deliveryModule,
            //drill-erekir
            largeCliffCrusher,heavyPlasmaBore,minerPoint,minerCenter,
            //distribution
            invertedJunction,itemLiquidJunction,plastaniumRouter,plastaniumBridge,stackHelper,chromiumEfficientConveyor,chromiumArmorConveyor,chromiumStackConveyor,chromiumStackRouter,chromiumStackBridge,chromiumJunction,chromiumInvertedJunction,chromiumRouter,chromiumItemBridge,
            phaseItemNode,rapidDirectionalUnloader,
            //distribution-erekir
            ductJunction,armoredDuctBridge,rapidDuctUnloader,
            //liquid
            turboPump,phaseLiquidNode,chromiumArmorConduit,chromiumLiquidBridge,chromiumArmorLiquidContainer,chromiumArmorLiquidTank,
            //liquid-erekir
            liquidSorter,liquidValve,smallReinforcedPump,largeReinforcedPump,
            //power
            powerNodePhase,powerNodeHuge,uraniumReactor,magneticStormRadiationReactor,hugeBattery,armoredCoatedBattery,
            //power-erekir
            beamDiode,beamInsulator,liquidConsumeGenerator,
            //production
            largeKiln,largePulverizer,largeMelter,largeCryofluidMixer,largePyratiteMixer,largeBlastMixer,largeCultivator,sporeFarm,largePlastaniumCompressor,largeSurgeSmelter,blastSiliconSmelter,
            nanocoreConstructor,nanocorePrinter,nanocoreActivator,largePhaseWeaver,phaseFusionInstrument,uraniumSynthesizer,chromiumSynthesizer,heavyAlloySmelter,metalAnalyzer,nitrificationReactor,nitratedOilSedimentationTank,
            //production-erekir
            ventHeater,chemicalSiliconSmelter,largeElectricHeater,liquidFuelHeater,largeOxidationChamber,largeSurgeCrucible,largeCarbideCrucible,nanocoreConstructorErekir,nanocorePrinterErekir,uraniumFuser,chromiumFuser,
            //defense
            lighthouse,mendDome,assignOverdrive,largeShieldGenerator,detonator,
            //defense-erekir
            largeRadar,
            //storage
            cargo,bin,machineryUnloader,rapidUnloader,coreStorage,
            //storage-erekir
            reinforcedCoreStorage,
            //payload
            payloadJunction,
            //payload-erekir
            reinforcedPayloadJunction,
            //unit
            titanReconstructor,experimentalUnitFactory,
            //unit-erekir
            largeUnitRepairTower,seniorAssemblerModule,
            //logic
            matrixProcessor,hugeLogicDisplay,buffrerdMemoryCell,buffrerdMemoryBank,heatSink,heatFan,heatSinkLarge,
            //turret
            dissipation,rocketLauncher,multipleRocketLauncher,largeRocketLauncher,rocketSilo,coilBlaster,dragonBreath,cloudbreaker,minigun,blaze,
            spike,fissure,
            hurricane,judgement,
            //turret-erekir
            rupture,
            //tbd
            ohNo,wrongTextureFolder,tbd;

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
        shaleVent = new SteamVent("shale-vent"){{
            variants = 3;
            parent = blendGroup = Blocks.shale;
            attributes.set(Attribute.steam, 1f);
        }};
        basaltWall = new StaticWall("basalt-wall"){{
            variants = 3;
            attributes.set(Attribute.sand, 0.7f);
            ((Floor) Blocks.basalt).wall = this;
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
            arkyciteSand.asFloor().decoration = this;
        }};
        darksandBoulder = new Prop("darksand-boulder"){{
            Blocks.darksand.asFloor().decoration = this;
        }};
        asphalt = new Floor("asphalt", 0);
        asphaltSide = new GrooveFloor("asphalt-side"){{
            blendGroup = asphalt;
        }};
        labFloor = new TiledFloor("lab-floor", 8, 1);
        labFloorDark = new TiledFloor("lab-floor-dark", 8, 1);
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
        darkWater = new Floor("darkwater", 0){{
            speedMultiplier = 0.6f;
            liquidDrop = Liquids.water;
            isLiquid = true;
            cacheLayer = CacheLayer.water;
            albedo = 0.9f;
            supportsOverlay = true;
        }};
        deepDarkWater = new Floor("deep-darkwater", 0){{
            speedMultiplier = 0.6f;
            liquidDrop = Liquids.water;
            isLiquid = true;
            cacheLayer = CacheLayer.water;
            albedo = 0.9f;
        }};
        mudDarkWater = new Floor("mud-darkwater", 0){{
            speedMultiplier = 0.5f;
            liquidDrop = Liquids.water;
            isLiquid = true;
            cacheLayer = CacheLayer.water;
            albedo = 0.9f;
        }};
        mud = new Floor("mud", 3){{
            speedMultiplier = 0.8f;
        }};
        overgrownGrass = new Floor("overgrown-grass", 3){{
            speedMultiplier = 0.9f;
            wall = overgrownShrubs;
        }};
        overgrownShrubs = new StaticWall("overgrown-shrubs"){{
            variants = 2;
        }};
        overgrownPine = new StaticWall("overgrown-pine"){{
            variants = 2;
        }};
        corruptedMoss = new Floor("corrupted-moss", 3){{
            speedMultiplier = 0.9f;
            attributes.set(Attribute.water, 0.1f);
        }};
        corruptedSporeMoss = new Floor("corrupted-spore-moss", 3){{
            speedMultiplier = 0.85f;
            attributes.set(Attribute.water, 0.1f);
            wall = corruptedSporeRocks;
        }};
        corruptedSporeRocks = new StaticWall("corrupted-spore-rocks"){{
            variants = 2;
        }};
        corruptedSporePine = new StaticWall("corrupted-spore-pine"){{
            variants = 2;
        }};
        corruptedSporeFern = new TreeBlock("corrupted-spore-fern");
        corruptedSporePlant = new TreeBlock("corrupted-spore-plant");
        corruptedSporeTree = new TreeBlock("corrupted-spore-tree");
        mycelium = new Floor("mycelium", 3){{
            speedMultiplier = 0.9f;
            attributes.set(Attribute.water, 0.1f);
            wall = myceliumShrubs;
        }};
        myceliumSpore = new Floor("mycelium-spore", 3){{
            speedMultiplier = 0.9f;
            attributes.set(Attribute.water, 0.1f);
            wall = myceliumShrubs;
        }};
        myceliumShrubs = new StaticWall("mycelium-shrubs"){{
            variants = 2;
        }};
        myceliumPine = new StaticWall("mycelium-pine"){{
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
        copperWallHuge = new Wall("copper-wall-huge"){{
            requirements(Category.defense, with(Items.copper, 54));
            size = 3;
            health = 2880;
            armor = 1f;
        }};
        copperWallGigantic = new Wall("copper-wall-gigantic"){{
            requirements(Category.defense, with(Items.copper, 96));
            size = 4;
            health = 5120;
            armor = 1f;
        }};
        titaniumWallHuge = new Wall("titanium-wall-huge"){{
            requirements(Category.defense, with(Items.titanium, 54));
            size = 3;
            health = 3960;
            armor = 2f;
        }};
        titaniumWallGigantic = new Wall("titanium-wall-gigantic"){{
            requirements(Category.defense, with(Items.titanium, 96));
            size = 4;
            health = 7040;
            armor = 2f;
        }};
        armoredWall = new Wall("armored-wall"){{
            requirements(Category.defense, with(Items.copper, 5, Items.lead, 3, Items.graphite, 2));
            size = 1;
            health = 360;
            armor = 5f;
        }};
        armoredWallLarge = new Wall("armored-wall-large"){{
            requirements(Category.defense, with(Items.copper, 20, Items.lead, 12, Items.graphite, 8));
            size = 2;
            health = 1440;
            armor = 5f;
        }};
        armoredWallHuge = new Wall("armored-wall-huge"){{
            requirements(Category.defense, with(Items.copper, 45, Items.lead, 27, Items.graphite, 18));
            size = 3;
            health = 3240;
            armor = 5f;
        }};
        armoredWallGigantic = new Wall("armored-wall-gigantic"){{
            requirements(Category.defense, with(Items.copper, 80, Items.lead, 48, Items.graphite, 32));
            size = 4;
            health = 5760;
            armor = 5f;
        }};
        uraniumWall = new Wall("uranium-wall"){{
            requirements(Category.defense, with(HIItems.uranium, 6));
            size = 1;
            health = 1680;
            armor = 24f;
            absorbLasers = true;
        }};
        uraniumWallLarge = new Wall("uranium-wall-large"){{
            requirements(Category.defense, with(HIItems.uranium, 24));
            size = 2;
            health = 6720;
            armor = 24f;
            absorbLasers = true;
        }};
        chromiumWall = new Wall("chromium-wall"){{
            requirements(Category.defense, with(HIItems.chromium, 6));
            size = 1;
            health = 1980;
            armor = 36f;
            absorbLasers = true;
        }};
        chromiumWallLarge = new Wall("chromium-wall-large"){{
            requirements(Category.defense, with(HIItems.chromium, 24));
            size = 2;
            health = 7920;
            armor = 36f;
            absorbLasers = true;
        }};
        chromiumDoor = new AutoDoor("chromium-door"){{
            requirements(Category.defense, with(HIItems.chromium, 6, Items.silicon, 4));
            size = 1;
            health = 1980;
            armor = 36f;
            absorbLasers = true;
        }};
        chromiumDoorLarge = new AutoDoor("chromium-door-large"){{
            requirements(Category.defense, with(HIItems.chromium, 24, Items.silicon, 16));
            size = 2;
            health = 7920;
            armor = 36f;
            absorbLasers = true;
        }};
        heavyAlloyWall = new Wall("heavy-alloy-wall"){{
            requirements(Category.defense, with(HIItems.heavyAlloy, 6, Items.metaglass, 3, Items.plastanium, 4));
            size = 1;
            health = 3220;
            armor = 48f;
            absorbLasers = insulated = true;
        }};
        heavyAlloyWallLarge = new Wall("heavy-alloy-wall-large"){{
            requirements(Category.defense, with(HIItems.heavyAlloy, 24, Items.metaglass, 12, Items.plastanium, 16));
            size = 2;
            health = 12880;
            armor = 48f;
            absorbLasers = insulated = true;
        }};
        nanoCompositeWall = new RegenWall("nano-composite-wall"){{
            requirements(Category.defense, with(HIItems.nanocore, 2, HIItems.heavyAlloy, 6, Items.metaglass, 1, Items.plastanium, 4));
            size = 1;
            health = 2680;
            armor = 42f;
            absorbLasers = insulated = true;
            healPercent = 2f / 60f;
            chanceHeal = 0.15f;
            regenPercent = 0.5f;
        }};
        nanoCompositeWallLarge = new RegenWall("nano-composite-wall-large"){{
            requirements(Category.defense, with(HIItems.nanocore, 8, HIItems.heavyAlloy, 24, Items.metaglass, 4, Items.plastanium, 16));
            size = 2;
            health = 10720;
            armor = 42f;
            absorbLasers = insulated = true;
            healPercent = 2f / 60f;
            chanceHeal = 0.15f;
            regenPercent = 0.5f;
        }};
        shapedWall = new ShapedWall("shaped-wall"){{
            requirements(Category.defense, BuildVisibility.sandboxOnly, with(HIItems.heavyAlloy, 8, Items.phaseFabric, 6));
            health = 4440;
            armor = 16f;
            insulated = absorbLasers = true;
            crushDamageMultiplier = 0.025f;
        }};
        //wall-erekir
        berylliumWallHuge = new Wall("beryllium-wall-huge"){{
            requirements(Category.defense, with(Items.beryllium, 54));
            health = 4680;
            armor = 2f;
            buildCostMultiplier = 3f;
            size = 3;
        }};
        berylliumWallGigantic = new Wall("beryllium-wall-gigantic"){{
            requirements(Category.defense, with(Items.beryllium, 96));
            health = 8320;
            armor = 2f;
            buildCostMultiplier = 2f;
            size = 4;
        }};
        tungstenWallHuge = new Wall("tungsten-wall-huge"){{
            requirements(Category.defense, with(Items.tungsten, 54));
            health = 6480;
            armor = 14f;
            buildCostMultiplier = 3f;
            size = 3;
        }};
        tungstenWallGigantic = new Wall("tungsten-wall-gigantic"){{
            requirements(Category.defense, with(Items.tungsten, 96));
            health = 11520;
            armor = 14f;
            buildCostMultiplier = 2f;
            size = 4;
        }};
        blastDoorLarge = new AutoDoor("blast-door-large"){{
            requirements(Category.defense, with(Items.tungsten, 54, Items.silicon, 54));
            health = 6300;
            armor = 14f;
            size = 3;
        }};
        blastDoorHuge = new AutoDoor("blast-door-huge"){{
            requirements(Category.defense, with(Items.tungsten, 96, Items.silicon, 96));
            health = 11200;
            armor = 14f;
            size = 4;
        }};
        reinforcedSurgeWallHuge = new Wall("reinforced-surge-wall-huge"){{
            requirements(Category.defense, with(Items.surgeAlloy, 54, Items.tungsten, 18));
            health = 9000;
            lightningChance = 0.1f;
            lightningDamage = 30f;
            armor = 20f;
            size = 3;
            researchCost = with(Items.surgeAlloy, 120, Items.tungsten, 600);
        }};
        reinforcedSurgeWallGigantic = new Wall("reinforced-surge-wall-gigantic"){{
            requirements(Category.defense, with(Items.surgeAlloy, 96, Items.tungsten, 32));
            health = 16000;
            lightningChance = 0.1f;
            lightningDamage = 30f;
            armor = 20f;
            size = 4;
            researchCost = with(Items.surgeAlloy, 240, Items.tungsten, 1200);
        }};
        carbideWallHuge = new Wall("carbide-wall-huge"){{
            requirements(Category.defense, with(Items.thorium, 54, Items.carbide, 54));
            health = 9720;
            armor = 16f;
            size = 3;
        }};
        carbideWallGigantic = new Wall("carbide-wall-gigantic"){{
            requirements(Category.defense, with(Items.thorium, 96, Items.carbide, 96));
            health = 17280;
            armor = 16f;
            size = 4;
        }};
        shieldedWallLarge = new ShieldWall("shielded-wall-large"){{
            requirements(Category.defense, with(Items.phaseFabric, 45, Items.surgeAlloy, 27, Items.beryllium, 27));
            outputsPower = false;
            hasPower = true;
            consumesPower = true;
            conductivePower = true;
            chanceDeflect = 8f;
            health = 9360;
            armor = 15f;
            size = 3;
            shieldHealth = 2200f;
            breakCooldown = 60f * 15f;
            regenSpeed = 2f;
            consumePower(4f / 60f);
        }};
        shieldedWallHuge = new ShieldWall("shielded-wall-huge"){{
            requirements(Category.defense, with(Items.phaseFabric, 80, Items.surgeAlloy, 48, Items.beryllium, 48));
            outputsPower = false;
            hasPower = true;
            consumesPower = true;
            conductivePower = true;
            chanceDeflect = 8f;
            health = 16640;
            armor = 15f;
            size = 4;
            shieldHealth = 3600f;
            breakCooldown = 60f * 15f;
            regenSpeed = 2f;
            consumePower(6f / 60f);
        }};
        aparajito = new AparajitoWall("aparajito"){{
            requirements(Category.defense, with(HIItems.chromium, 6, HIItems.nanocoreErekir, 2));
            size = 1;
            health = 2055;
            armor = 44;
            healColor = HIPal.chromiumGrey;
        }};
        aparajitoLarge = new AparajitoWall("aparajito-large"){{
            requirements(Category.defense, with(HIItems.chromium, 24, HIItems.nanocoreErekir, 8));
            size = 2;
            health = 8220;
            armor = 44;
            healColor = HIPal.chromiumGrey;
        }};
        //drill
        titaniumDrill = new Drill("titanium-drill"){{
            requirements(Category.production, with(Items.copper, 20, Items.graphite, 15, Items.titanium, 10));
            size = 2;
            drillTime = 340f;
            tier = 4;
            consumeLiquid(Liquids.water, 0.06f).boost();
        }};
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
        oilRig = new Fracker("oil-rig"){{
            requirements(Category.production, with(Items.lead, 220, Items.graphite, 200, Items.silicon, 100, Items.thorium, 180, Items.plastanium, 120, Items.phaseFabric, 30));
            size = 4;
            itemCapacity = 20;
            liquidCapacity = 100f;
            result = Liquids.oil;
            attribute = Attribute.oil;
            updateEffect = Fx.pulverize;
            updateEffectChance = 0.05f;
            baseEfficiency = 0f;
            itemUseTime = 30f;
            pumpAmount = 1.5f;
            consumePower(8f);
            consumeItem(Items.sand);
            consumeLiquid(Liquids.water, 0.3f);
            buildCostMultiplier = 0.8f;
        }};
        cuttingDrill = new RotatorDrill("cutting-drill"){{
            requirements(Category.production, with(Items.graphite, 100, Items.silicon, 120, Items.thorium, 50, Items.plastanium, 40, Items.surgeAlloy, 30));
            size = 4;
            health = 590;
            armor = 3f;
            mineTier = 8;
            mineSpeed = 3.5f;
            maxOreTileReq = 12;
            updateEffect = Fx.mineBig;
            powerConsBase = 180f;
            rotateSpeed = 6f;
            drawRim = drawMineItem = true;
        }
            @Override
            public float getMineSpeedHardnessMul(Item item) {
                if (item == null) return 0f;
                if (item.hardness == 0) return 2f;
                if (item.hardness <= 3) return 5f / 3;
                if (item.hardness <= 6) return 4f / 3;
                return 1f;
            }
        };
        beamDrill = new LaserBeamDrill("beam-drill"){{
            requirements(Category.production, with(Items.lead, 160, Items.silicon, 120,  HIItems.chromium, 60, HIItems.nanocore, 35,Items.phaseFabric, 25));
            size = 4;
            health = 960;
            mineTier = 11;
            maxOreTileReq = 10;
            buildCostMultiplier = 0.8f;
        }};
        implosionDrill = new ImplosionDrill("implosion-drill"){{
            requirements(Category.production, with(Items.silicon, 180, Items.plastanium, 120, Items.surgeAlloy, 100, HIItems.chromium, 60, HIItems.heavyAlloy, 80));
            size = 5;
            health = 2260;
            mineSpeed = 10f;
            mineCount = 35;
            mineTier = 13;
            maxOreTileReq = 18;
            itemCapacity = 120;
            maxBoost = 2f;
            powerConsBase = 480f;
            drillEffect = new MultiEffect(Fx.mineImpact, Fx.drillSteam, Fx.dynamicSpikes.wrap(Color.white, 30f), Fx.mineImpactWave.wrap(Color.white, 45f));
            shake = 3f;
            arrowOffset = 2f;
            arrowSpacing = 5f;
            arrows = 3;
            glowColor.a = 0.6f;
            fogRadius = 5;
            drawMineItem = true;
        }};
        speedModule = new SpeedModule("speed-module"){{
            requirements(Category.production, with(Items.plastanium, 50, Items.surgeAlloy, 45, HIItems.nanocore, 35));
        }};
        refineModule = new RefineModule("refine-module"){{
            requirements(Category.production, with(Items.silicon, 80, Items.metaglass, 25, Items.plastanium, 60, Items.surgeAlloy, 45));
            convertList.add(new Item[]{Items.sand, Items.silicon}, new Item[]{Items.coal, Items.graphite});
            convertMul.put(Items.sand, -0.3f);
            convertMul.put(Items.coal, -0.2f);
            buildCostMultiplier = 1.2f;
        }};
        deliveryModule = new DeliveryModule("delivery-module"){{
            requirements(Category.production, with(Items.plastanium, 80, HIItems.nanocore, 40, HIItems.chromium, 50, Items.phaseFabric, 35));
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
        minerPoint = new MinerPoint("miner-point"){{
            requirements(Category.production, with(Items.beryllium, 120, Items.graphite, 120, Items.silicon, 85, Items.tungsten, 50));
            blockedItem.add(Items.thorium);
            droneConstructTime = 60 * 10f;
            tier = 5;
            consumePower(2f);
            consumeLiquid(Liquids.hydrogen, 6 / 60f);
            squareSprite = false;
        }};
        minerCenter = new MinerPoint("miner-center"){{
            requirements(Category.production, with(Items.beryllium, 480, Items.tungsten, 360, Items.oxide, 125, Items.carbide, 120, Items.surgeAlloy, 130));
            range = 18;
            dronesCreated = 6;
            droneConstructTime = 60 * 7f;
            tier = 7;
            size = 4;
            itemCapacity = 300;
            MinerUnit = HIUnitTypes.largeMiner;
            consumePower(3);
            consumeLiquid(Liquids.nitrogen, 9 / 60f);
            buildCostMultiplier = 0.8f;
            squareSprite = false;
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
        itemLiquidJunction = new MultiJunction("item-liquid-junction"){{
            requirements(Category.distribution, with(Items.copper, 4, Items.graphite, 6, Items.metaglass, 10));
        }};
        plastaniumRouter = new StackRouter("plastanium-router"){{
            requirements(Category.distribution, with(Items.plastanium, 5, Items.silicon, 5, Items.graphite, 5));
            health = 90;
            speed = 8f;
        }};
        plastaniumBridge = new StackBridge("plastanium-bridge"){{
            requirements(Category.distribution, with(Items.lead, 15, Items.silicon, 12, Items.titanium, 15, Items.plastanium, 10));
            health = 90;
            itemCapacity = 10;
            range = 6;
            bridgeWidth = 8f;
        }};
        stackHelper = new StackHelper("stack-helper"){{
            requirements(Category.distribution, with(Items.silicon, 20, Items.phaseFabric, 10, Items.plastanium, 20));
            health = 90;
        }};
        chromiumEfficientConveyor = new BeltConveyor("chromium-efficient-conveyor"){{
            requirements(Category.distribution, with(Items.lead, 1, HIItems.chromium, 1));
            health = 240;
            armor = 3f;
            speed = 0.18f;
            displayedSpeed = 18;
        }};
        chromiumArmorConveyor = new BeltConveyor("chromium-armor-conveyor"){{
            requirements(Category.distribution, with(Items.metaglass, 1, Items.thorium, 1, Items.plastanium, 1, HIItems.chromium, 1));
            health = 560;
            armor = 5f;
            speed = 0.18f;
            displayedSpeed = 18;
            noSideBlend = true;
        }};
        chromiumStackConveyor = new BeltStackConveyor("chromium-stack-conveyor"){{
            requirements(Category.distribution, with(Items.graphite, 1, Items.silicon, 1, Items.plastanium, 1, HIItems.chromium, 1));
            health = 380;
            armor = 4f;
            speed = 0.125f;
            itemCapacity = 20;
            outputRouter = false;
        }};
        chromiumStackRouter = new StackRouter("chromium-stack-router"){{
            requirements(Category.distribution, with(Items.graphite, 4, Items.silicon, 5, Items.plastanium, 3, HIItems.chromium, 1));
            health = 380;
            armor = 4f;
            speed = 0.125f;
            itemCapacity = 20;
            buildCostMultiplier = 0.8f;
        }};
        chromiumStackBridge = new StackBridge("chromium-stack-bridge"){{
            requirements(Category.distribution, with(Items.lead, 15, Items.silicon, 12, Items.plastanium, 10, HIItems.chromium, 10));
            health = 420;
            armor = 4f;
            itemCapacity = 20;
            range = 8;
            bridgeWidth = 8f;
            buildCostMultiplier = 0.6f;
        }};
        chromiumRouter = new MultiRouter("chromium-router"){{
            requirements(Category.distribution, with(Items.copper, 3, HIItems.chromium, 2));
            health = 420;
            armor = 4f;
            speed = 2;
            itemCapacity = 20;
            liquidCapacity = 64f;
            underBullets = true;
            solid = false;
        }};
        chromiumJunction = new Junction("chromium-junction"){{
            requirements(Category.distribution, with(Items.copper, 2, HIItems.chromium, 2));
            health = 420;
            armor = 4f;
            speed = 12;
            capacity = itemCapacity = 12;
            ((BeltConveyor) chromiumEfficientConveyor).junctionReplacement = this;
            ((BeltConveyor) chromiumArmorConveyor).junctionReplacement = this;
        }};
        chromiumInvertedJunction = new MultiInvertedJunction("chromium-inverted-junction"){{
            requirements(Category.distribution, with(Items.copper, 2, HIItems.chromium, 2));
            health = 420;
            armor = 4f;
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
            armor = 4f;
            hasPower = false;
            transportTime = 3f;
            range = 8;
            arrowSpacing = 6;
            bridgeWidth = 8;
            buildCostMultiplier = 0.8f;
            ((BeltConveyor) chromiumEfficientConveyor).bridgeReplacement = this;
            ((BeltConveyor) chromiumArmorConveyor).bridgeReplacement = this;
        }};
        phaseItemNode = new NodeBridge("phase-item-node"){{
            requirements(Category.distribution, with( Items.lead, 30, HIItems.chromium , 10, Items.silicon, 15, Items.phaseFabric, 10));
            size = 1;
            health = 320;
            armor = 3f;
            squareSprite = false;
            range = 25;
            envEnabled |= Env.space;
            transportTime = 1f;
            consumePower(0.5f);
        }};
        rapidDirectionalUnloader = new AdaptDirectionalUnloader("rapid-directional-unloader"){{
            requirements(Category.distribution, with(Items.silicon, 40, Items.plastanium, 25, HIItems.chromium, 15, Items.phaseFabric, 5));
            speed = 1f;
            squareSprite = false;
            underBullets = true;
            allowCoreUnload = true;
        }};
        //distribution-erekir
        ductJunction = new DuctJunction("duct-junction"){{
            requirements(Category.distribution, with(Items.beryllium, 5));
            health = 75;
            speed = 4f;
        }};
        armoredDuctBridge = new DuctBridge("armored-duct-bridge"){{
            requirements(Category.distribution, with(Items.beryllium, 20, Items.tungsten, 10));
            health = 140;
            range = 6;
            speed = 4;
            buildCostMultiplier = 2;
            ((Duct) Blocks.armoredDuct).bridgeReplacement = this;
        }};
        rapidDuctUnloader = new AdaptDirectionalUnloader("rapid-duct-unloader"){{
            requirements(Category.distribution, with(Items.graphite, 25, Items.silicon, 30, Items.tungsten, 20, Items.oxide, 15));
            health = 240;
            speed = 2f;
            solid = false;
            underBullets = true;
            squareSprite = false;
            regionRotated1 = 1;
        }};
        //liquid
        chromiumArmorConduit = new BeltConduit("chromium-armor-conduit"){{
            requirements(Category.liquid, with(Items.metaglass, 2, HIItems.chromium, 1));
            health = 420;
            armor = 4f;
            liquidCapacity = 32;
            liquidPressure = 3.2f;
            noSideBlend = true;
            leaks = false;
            buildType = () -> new BeltConduitBuild(){
                @Override
                public float moveLiquid(Building next, Liquid liquid) {
                    if (next == null) return 0f;

                    next = next.getLiquidDestination(this, liquid);

                    if (next.team == team && next.block.hasLiquids && liquids.get(liquid) > 0f) {
                        float ofract = next.liquids.get(liquid) / next.block.liquidCapacity;
                        float fract = liquids.get(liquid) / block.liquidCapacity * block.liquidPressure;
                        float flow = Math.min(Mathf.clamp(fract - ofract) * block.liquidCapacity, liquids.get(liquid));
                        flow = Math.min(flow, next.block.liquidCapacity - next.liquids.get(liquid));

                        if (flow > 0f && ofract <= fract && next.acceptLiquid(this, liquid)) {
                            next.handleLiquid(this, liquid, flow);
                            liquids.remove(liquid, flow);
                            return flow;
                        } else if (next.liquids.currentAmount() / next.block.liquidCapacity > 0.1f && fract > 0.1f) {
                            float fx = (x + next.x) / 2f, fy = (y + next.y) / 2f;

                            Liquid other = next.liquids.current();
                            // There was flammability logics, removed
                            if ((liquid.temperature > 0.7f && other.temperature < 0.55f) || (other.temperature > 0.7f && liquid.temperature < 0.55f)) {
                                liquids.remove(liquid, Math.min(liquids.get(liquid), 0.7f * Time.delta));
                                if (Mathf.chance(0.2f * Time.delta)) {
                                    Fx.steam.at(fx, fy);
                                }
                            }
                        }
                    }
                    return 0f;
                }
            };
        }};
        chromiumLiquidBridge = new LiquidBridge("chromium-liquid-bridge"){{
            requirements(Category.liquid, with(Items.metaglass, 10, HIItems.chromium, 6));
            health = 480;
            armor = 5f;
            hasPower = false;
            range = 8;
            arrowSpacing = 6;
            bridgeWidth = 8f;
            ((BeltConduit) chromiumArmorConduit).bridgeReplacement = this;
        }};
        chromiumArmorLiquidContainer = new LiquidRouter("chromium-armor-liquid-container"){{
            requirements(Category.liquid, with(Items.metaglass, 15, HIItems.chromium, 6));
            size = 2;
            health = 950;
            armor = 8f;
            liquidCapacity = 1200;
            underBullets = true;
            buildCostMultiplier = 0.8f;
        }};
        chromiumArmorLiquidTank = new LiquidRouter("chromium-armor-liquid-tank"){{
            requirements(Category.liquid, with(Items.metaglass, 40, HIItems.chromium, 16));
            size = 3;
            health = 2500;
            armor = 8f;
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
        phaseLiquidNode = new NodeBridge("phase-liquid-node"){{
            requirements(Category.liquid, with(Items.metaglass, 20, HIItems.chromium , 10, Items.silicon, 15, Items.phaseFabric, 10));
            size = 1;
            health = 320;
            armor = 3f;
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
        powerNodeHuge = new PowerNode("power-node-huge"){{
            requirements(Category.power, with(Items.lead, 45, Items.titanium, 35, Items.thorium, 15, Items.silicon, 25));
            size = 3;
            health = 370;
            maxNodes = 25;
            laserRange = 22.5f;
        }};
        powerNodePhase = new PowerNode("power-node-phase"){{
            requirements(Category.power, ItemStack.with(Items.plastanium, 5, HIItems.heavyAlloy, 15, Items.phaseFabric, 10));
            size = 1;
            health = 2200;
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
            explodeEffect = new Effect(30, 500f, b -> {
                float intensity = 8f;
                float baseLifetime = 25f + intensity * 15f;
                b.lifetime = 50f + intensity * 64f;

                Draw.color(HIPal.uraniumGrey);
                Draw.alpha(0.8f);
                for(int i = 0; i < 5; i++){
                    Fx.rand.setSeed(b.id * 2L + i);
                    float lenScl = Fx.rand.random(0.25f, 1f);
                    int fi = i;
                    b.scaled(b.lifetime * lenScl, e -> Angles.randLenVectors(e.id + fi - 1, e.fin(Interp.pow10Out), (int)(2.8f * intensity), 25f * intensity, (x, y, in, out) -> {
                        float fout = e.fout(Interp.pow5Out) * Fx.rand.random(0.5f, 1f);
                        float rad = fout * ((2f + intensity) * 2.35f);

                        Fill.circle(e.x + x, e.y + y, rad);
                        Drawf.light(e.x + x, e.y + y, rad * 2.6f, HIPal.uraniumGrey, 0.7f);
                    }));
                }

                b.scaled(baseLifetime, e -> {
                    Draw.color();
                    e.scaled(5 + intensity * 2f, i -> {
                        Lines.stroke((3.1f + intensity/5f) * i.fout());
                        Lines.circle(e.x, e.y, (3f + i.fin() * 14f) * intensity);
                        Drawf.light(e.x, e.y, i.fin() * 14f * 2f * intensity, Color.white, 0.9f * e.fout());
                    });

                    Draw.color(Color.white, HIPal.uraniumGrey, e.fin());
                    Lines.stroke((2f * e.fout()));

                    Draw.z(Layer.effect + 0.001f);
                    Angles.randLenVectors(e.id + 1, e.finpow() + 0.001f, (int)(8 * intensity), 30f * intensity, (x, y, in, out) -> {
                        Lines.lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), 1f + out * 4 * (4f + intensity));
                        Drawf.light(e.x + x, e.y + y, (out * 4 * (3f + intensity)) * 3.5f, Draw.getColor(), 0.8f);
                    });
                });
            });
            ambientSound = Sounds.hum;
            ambientSoundVolume = 0.24f;
            consumeItem(HIItems.uranium);
            consumeLiquid(Liquids.cryofluid, heating / coolantPower).update(false);
            buildType = () -> new NuclearReactorBuild(){
                @Override
                public void draw() {
                    Draw.rect(bottomRegion, x, y);

                    Draw.color(coolColor, hotColor, heat);
                    Fill.rect(x, y, size * tilesize, size * tilesize);

                    Draw.color(liquids.current().color);
                    Draw.alpha(liquids.currentAmount() / liquidCapacity);
                    Draw.rect(topRegion, x, y);
                    Draw.reset();

                    Draw.rect(region, x, y);

                    drawGlow();

                    if(heat > flashThreshold){
                        flash += (1f + ((heat - flashThreshold) / (1f - flashThreshold)) * 5.4f) * Time.delta;
                        Draw.color(Color.red, Color.yellow, Mathf.absin(flash, 9f, 1f));
                        Draw.alpha(0.3f);
                        Draw.rect(lightsRegion, x, y);
                    }

                    Draw.reset();
                }

                public void drawGlow() {
                    if(warmup() <= 0.001f) return;

                    float z = Draw.z();
                    Draw.z(layer);
                    Draw.blend(blending);
                    Draw.color(color);
                    Draw.alpha((Mathf.absin(totalProgress(), glowScale, alpha) * glowIntensity + 1f - glowIntensity) * warmup() * alpha);
                    Draw.rect(glowRegion, x, y, 0f);
                    Draw.reset();
                    Draw.blend();
                    Draw.z(z);
                }
            };
        }
            public TextureRegion bottomRegion, glowRegion;

            public final Blending blending = Blending.additive;
            public final float alpha = 0.9f, glowScale = 10f, glowIntensity = 0.5f, layer = Layer.blockAdditive;
            public final Color color = Color.red.cpy();

            @Override
            public void load() {
                super.load();
                bottomRegion = atlas.find(name + "-bottom");
                glowRegion = atlas.find(name + "-glow");
            }

            @Override
            public TextureRegion[] icons() {
                return new TextureRegion[]{bottomRegion, region};
            }
        };
        magneticStormRadiationReactor = new HyperGenerator("magnetic-storm-radiation-reactor"){{
            requirements(Category.power, with(Items.titanium, 1200, Items.metaglass, 1300, Items.plastanium, 800, Items.silicon, 1600, Items.phaseFabric, 1200, HIItems.chromium, 2500, HIItems.heavyAlloy, 2200));
            size = 6;
            health = 16500;
            powerProduction = 2200f;
            updateLightning = updateLightningRand = 3;
            itemCapacity = 30;
            itemDuration = 180f;
            liquidCapacity = 360f;
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawPlasma(){{
                plasma1 = plasma2 = Pal.techBlue;
            }}, new DrawDefault());
            ambientSound = Sounds.pulse;
            ambientSoundVolume = 0.1f;
            consumePower(65f);
            consumeItem(Items.phaseFabric, 1);
            consumeLiquid(HILiquids.nanofluid, 0.25f);
            buildCostMultiplier = 0.6f;
        }};
        hugeBattery = new Battery("huge-battery"){{
            requirements(Category.power, with(Items.lead, 225, Items.graphite, 125, Items.phaseFabric, 80, Items.thorium, 110, Items.plastanium, 100));
            size = 5;
            health = 1600;
            drawer = new DrawMulti(new DrawDefault(), new DrawPower(){{
                emptyLightColor = Color.valueOf("18473f");
                fullLightColor = Color.valueOf("ffd197");
            }}, new DrawRegion("-top"));
            consumePowerBuffered(750000f);
        }};
        armoredCoatedBattery = new Battery("armored-coated-battery"){{
            requirements(Category.power, with(Items.lead, 150, Items.silicon, 180, Items.plastanium, 120, HIItems.chromium, 100, Items.phaseFabric, 50));
            size = 4;
            health = 6600;
            armor = 28f;
            drawer = new DrawMulti(new DrawDefault(), new DrawPower(){{
                emptyLightColor = Color.valueOf("18473f");
                fullLightColor = Color.valueOf("ffd197");
            }}, new DrawRegion("-top"));
            consumePowerBuffered(425000f);
        }};
        //power-erekir
        beamDiode = new BeamDiode("beam-diode"){{
            requirements(Category.power, with(Items.beryllium, 10, Items.silicon, 10, Items.oxide, 5));
            health = 90;
            range = 10;
            fogRadius = 1;
        }};
        beamInsulator = new InsulationWall("beam-insulator"){{
            requirements(Category.power, with(Items.silicon, 10, Items.oxide, 5));
            health = 90;
        }};
        liquidConsumeGenerator = new ConsumeGenerator("liquid-generator"){{
            requirements(Category.power, with(Items.beryllium, 180, Items.graphite, 120, Items.silicon, 115, Items.tungsten, 80, Items.oxide, 120));
            size = 3;
            hasLiquids = true;
            powerProduction = 660f / 60f;
            drawer = new DrawMulti(new DrawDefault(), new DrawWarmupRegion(){{
                sinMag = 0;
                sinScl = 1;
            }}, new DrawLiquidRegion());
            generateEffect = new RadialEffect(new Effect(160f, e -> {
                Draw.color(Color.valueOf("6e685a"));
                Draw.alpha(0.6f);
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
            squareSprite = false;
        }};
        //production
        largeKiln = new GenericCrafter("large-kiln"){{
            requirements(Category.crafting, with(Items.graphite, 60, Items.silicon, 35, Items.titanium, 50, Items.thorium, 40));
            craftEffect = Fx.smeltsmoke;
            outputItem = new ItemStack(Items.metaglass, 4);
            craftTime = 40f;
            size = 3;
            hasPower = hasItems = true;
            drawer = new DrawMulti(new DrawDefault(), new DrawFlame(Color.valueOf("ffc099")));
            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.14f;
            consumeItems(with(Items.lead, 3, Items.sand, 3));
            consumePower(1.8f);
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
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawRegion("-rotate", 3f, true), /*new DrawFrames(), new DrawArcSmelt(), */new DrawDefault());
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
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawLiquidTile(Liquids.slag), new DrawDefault());
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
            itemCapacity = 20;
            liquidCapacity = 60f;
            craftTime = 50;
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawLiquidTile(Liquids.water), new DrawLiquidTile(Liquids.cryofluid), new DrawDefault());
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
            itemCapacity = 20;
            consumePower(0.5f);
            consumeItems(with(Items.coal, 3, Items.lead, 4, Items.sand, 5));
        }};
        largeBlastMixer = new GenericCrafter("large-blast-mixer"){{
            requirements(Category.crafting, with(Items.lead, 60, Items.titanium, 40, Items.silicon, 20));
            outputItem = new ItemStack(Items.blastCompound, 3);
            size = 3;
            itemCapacity = 20;
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
            itemCapacity = 20;
            liquidCapacity = 60f;
            craftEffect = Fx.none;
            envRequired |= Env.spores;
            attribute = Attribute.spores;
            legacyReadWarmup = true;
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawLiquidTile(Liquids.water), new DrawCultivator(), new DrawDefault());
            maxBoost = 3f;
            consumePower(3f);
            consumeLiquid(Liquids.water, 36f / 60f);
        }};
        sporeFarm = new SporeFarm("spore-farm"){{
            requirements(Category.production, with(Items.copper, 5, Items.lead, 5));
            health = 50;
            rebuildable = false;
            hasItems = true;
            itemCapacity = 2;
            envRequired |= Env.spores;
            breakSound = Sounds.splash;
            buildCostMultiplier = 0.1f;
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
        largeSurgeSmelter = new GenericCrafter("large-surge-smelter"){{
            requirements(Category.crafting, with(Items.lead, 180, Items.silicon, 100, Items.metaglass, 60, Items.thorium, 150, Items.surgeAlloy, 30));
            size = 4;
            itemCapacity = 30;
            craftTime = 90f;
            craftEffect = Fx.smeltsmoke;
            outputItem = new ItemStack(Items.surgeAlloy, 3);
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawDefault(), new DrawPowerLight(Color.valueOf("f3e979")), new DrawFlame(Color.valueOf("ffef99")));
            consumePower(6);
            consumeItems(with(Items.copper, 5, Items.lead, 6, Items.titanium, 5, Items.silicon, 4));
        }};
        blastSiliconSmelter = new GenericCrafter("blast-silicon-smelter"){{
            requirements(Category.crafting, with(Items.graphite, 90, Items.thorium, 70, Items.silicon, 80, Items.plastanium, 50, Items.surgeAlloy, 30));
            health = 660;
            size = 4;
            itemCapacity = 50;
            craftTime = 35f;
            outputItem = new ItemStack(Items.silicon, 10);
            craftEffect = new RadialEffect(Fx.surgeCruciSmoke, 9, 45f, 6f);
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawDefault(), new DrawGlowRegion(){{
                alpha = 0.9f;
                glowScale = 3.14f;
                color = Color.valueOf("ff0000");
            }}, new DrawGlowRegion("-glow1"){{
                alpha = 0.9f;
                glowScale = 3.14f;
                color = Color.valueOf("eb564b");
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
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawSpecConstruct(){{
                constructColor1 = constructColor2 = HIPal.nanocoreGreen;
            }}, new DrawDefault());
            consumePower(2.5f);
            consumeItems(with(Items.titanium, 2, Items.silicon, 3));
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
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawLiquidTile(Liquids.cryofluid, 28f / 4f), new DrawRegion("-mid"), new DrawSpecConstruct(){{
                constructColor1 = constructColor2 = HIPal.nanocoreGreen;
            }}, new DrawDefault());
            consumePower(25f);
            consumeLiquid(Liquids.cryofluid, 6f / 60f);
            consumeItems(with(Items.titanium, 6, Items.silicon, 9));
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
        largePhaseWeaver = new GenericCrafter("large-phase-weaver"){{
            requirements(Category.crafting, with(Items.lead, 160,Items.thorium, 100, Items.silicon, 80, Items.plastanium, 50, Items.phaseFabric, 15));
            size = 3;
            itemCapacity = 40;
            craftTime = 60f;
            outputItem = new ItemStack(Items.phaseFabric, 3);
            updateEffect = HIFx.squareRand(Pal.accent, 5f, 13f);
            craftEffect = new Effect(25f, e -> {
                Draw.color(Pal.accent);
                Angles.randLenVectors(e.id, 4, 24 * e.fout() * e.fout(), (x, y) -> {
                    Lines.stroke(e.fout() * 1.7f);
                    Lines.square(e.x + x, e.y + y, 2f + e.fout() * 6f);
                });
            });
            drawer = new DrawPrinter(outputItem.item){{
                printColor = lightColor = Pal.accent;
                moveLength = 4.2f;
                time = 25f;
            }};
            clipSize = size * tilesize * 2f;
            consumePower(7f);
            consumeItems(with(Items.sand, 15, Items.thorium, 5));
        }};
        phaseFusionInstrument = new GenericCrafter("phase-fusion-instrument"){{
            requirements(Category.crafting, with(Items.silicon, 100, Items.plastanium, 80, Items.phaseFabric, 60, HIItems.chromium, 80, HIItems.nanocore, 40));
            size = 3;
            hasLiquids = true;
            itemCapacity = 30;
            liquidCapacity = 20;
            lightRadius /= 2f;
            craftTime = 80;
            craftEffect = HIFx.crossBlast(Pal.accent, 45f, 45f);
            craftEffect.lifetime *= 1.5f;
            updateEffect = HIFx.squareRand(Pal.accent, 5f, 15f);
            outputItem = new ItemStack(Items.phaseFabric, 5);
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawRegion("-bottom-2"), new DrawCrucibleFlame(){{
                flameColor = Pal.accent;
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
                            Fill.square(build.x + Angles.trnsx(angle, len), build.y + Angles.trnsy(angle, len), particleSize * fin * build.warmup(), 45);
                        }
                        Draw.blend();
                        Draw.color(midColor, build.warmup());
                        Lines.square(build.x, build.y, (flameRad + circleSpace + si) * build.warmup(), 45);
                        Draw.reset();
                    }
                }
            }, new DrawDefault(), new DrawGlowRegion(){{
                color = Pal.accent;
                layer = -1;
                glowIntensity = 1.1f;
                alpha = 1.1f;
            }}, new DrawRotator(1f, "-top"){
                @Override
                public void draw(Building build){
                    Drawf.spinSprite(rotator, build.x + x, build.y + y, Drawn.rotator_90(Drawn.cycle(build.totalProgress() * rotateSpeed, 0, craftTime), 0.15f));
                }
            });
            consumePower(9f);
            consumeItems(with(HIItems.uranium, 3, HIItems.rareEarth, 8));
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
            consumeItems(with(Items.graphite, 1, Items.thorium, 1));
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
            consumeItems(with(Items.titanium, 8, Items.phaseFabric, 1));
        }};
        heavyAlloySmelter = new GenericCrafter("heavy-alloy-smelter"){{
            requirements(Category.crafting, with(Items.lead, 80, Items.silicon, 60, HIItems.chromium, 30, Items.phaseFabric, 10));
            size = 3;
            health = 850;
            craftTime = 80f;
            outputItem = new ItemStack(HIItems.heavyAlloy, 1);
            craftEffect = Fx.smeltsmoke;
            drawer = new DrawMulti(new DrawDefault(), new DrawFlame());
            consumePower(9f);
            consumeItems(with(HIItems.uranium, 1, HIItems.chromium, 1));
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
            armor = 5f;
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
            armor = 8f;
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
            displayEfficiencyScale = 1f / 9;
            minEfficiency = 9 - 0.0001f;
            displayEfficiency = false;
            generateEffect = Fx.turbinegenerate;
            effectChance = 0.04f;
            ambientSound = Sounds.hum;
            ambientSoundVolume = 0.06f;
            drawer = new DrawMulti(new DrawDefault(), new DrawHeatOutput());
            squareSprite = false;
            heatOutput = 15f / 9;
            outputLiquid = new LiquidStack(Liquids.water, 5f / 60f / 9);
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
        liquidFuelHeater = new FuelHeater("liquid-fuel-heater"){{
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
            armor = 13f;
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
            armor = 3f;
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
            armor = 6f;
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
            armor = 10f;
            itemCapacity = 30;
            heatRequirement = 20;
            craftTime = 135;
            outputItem = new ItemStack(Items.carbide, 3);
            ambientSound = Sounds.smelter;
            ambientSoundVolume = 1.8f;
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawCrucibleFlame(), new DrawDefault(), new DrawHeatInput());
            consumeItems(with(Items.graphite, 8, Items.tungsten, 5));
            consumePower(1);
        }};
        nanocoreConstructorErekir = new GenericCrafter("nanocore-constructor-erekir"){{
            requirements(Category.crafting, with( Items.beryllium, 120, Items.tungsten, 100, Items.silicon, 150, Items.oxide, 50));
            size = 2;
            itemCapacity = 15;
            craftTime = 120f;
            outputItem = new ItemStack(HIItems.nanocoreErekir, 1);
            craftEffect = Fx.none;
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawSpecConstruct(){{
                constructColor1 = constructColor2 = HIPal.nanocoreErekirOrange;
            }}, new DrawDefault());
            consumePower(2.5f);
            consumeItems(ItemStack.with(Items.tungsten, 2, Items.silicon, 3));
            squareSprite = false;
        }};
        nanocorePrinterErekir = new HeatCrafter("nanocore-printer-erekir"){{
            requirements(Category.crafting, with( Items.beryllium, 400, Items.tungsten, 300, Items.silicon, 250, Items.oxide, 150, Items.carbide, 50, Items.phaseFabric, 50));
            size = 4;
            health = 2500;
            itemCapacity = 40;
            craftTime = 480f;
            outputItem = new ItemStack(HIItems.nanocoreErekir, 12);
            craftEffect = Fx.none;
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawSpecConstruct(){{
                constructColor1 = constructColor2 = HIPal.nanocoreErekirOrange;
            }}, new DrawDefault(), new DrawHeatInput());
            heatRequirement = 8f;
            maxEfficiency = 4f;
            consumePower(17.5f);
            consumeItems(with(Items.tungsten, 6, Items.silicon, 9));
            buildCostMultiplier = 0.8f;
            squareSprite = false;
        }};
        uraniumFuser = new HeatCrafter("uranium-fuser"){{
            requirements(Category.crafting, with(Items.silicon, 120, Items.graphite, 60, Items.tungsten, 100, Items.oxide, 40, Items.surgeAlloy, 60));
            size = 3;
            itemCapacity = 20;
            heatRequirement = 10f;
            craftTime = 120f;
            liquidCapacity = 300f;
            hasLiquids = true;
            outputItem = new ItemStack(HIItems.uranium, 1);
            craftEffect = new Effect(50f, e -> {
                Draw.color(Liquids.slag.color);
                Draw.alpha(e.fslope() * 0.8f);
                Mathf.rand.setSeed(e.id + 2);
                Angles.randLenVectors(e.id, 5, 3f, 9f * e.fin(), (x, y) -> Fill.circle(e.x + x, e.y + y, Mathf.rand.random(0.65f, 1.5f)));
            }).layer(Layer.bullet - 1f);
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawLiquidTile(Liquids.slag, 3f), new DrawDefault(), new DrawHeatInput(), new DrawGlowRegion("-glow"){{
                color = Color.valueOf("70170b");
            }}, new DrawBlurSpin("-rotator", 12f));
            consumeItem(Items.thorium, 1);
            consumeLiquid(Liquids.slag, 30f / 60f);
            consumePower(2f);
            squareSprite = false;
        }};
        chromiumFuser = new GenericCrafter("chromium-fuser"){{
            requirements(Category.crafting, with(Items.silicon, 120, Items.graphite, 60, Items.tungsten, 100, Items.oxide, 40, Items.surgeAlloy, 60));
            size = 3;
            itemCapacity = 20;
            craftTime = 35f;
            liquidCapacity = 300f;
            hasLiquids = true;
            craftEffect = Fx.none;
            outputItem = new ItemStack(HIItems.chromium, 1);
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawLiquidTile(Liquids.hydrogen, 3f), new DrawDefault(), new DrawHeatInput());
            consumeItem(Items.tungsten, 1);
            consumeLiquids(LiquidStack.with(Liquids.slag, 80f / 60f, Liquids.hydrogen, 12f / 60f));
            consumePower(12f);
            squareSprite = false;
            buildType = () -> new GenericCrafterBuild(){
                private boolean nextFlash;
                public float heatf, warmupf;

                @Override
                public void updateTile(){
                    super.updateTile();
                    if(!nextFlash && heatf < 0.001f && Mathf.chance(0.01f * edelta()) && canConsume() && efficiency() > 0.0001f){
                        nextFlash = true;
                        heatf = 1f;
                    }else if(nextFlash && heatf < 0.001f){
                        nextFlash = false;
                        heatf = 1f;
                    }
                    heatf = Mathf.approachDelta(heatf, 0f, 0.05f);
                    warmupf = Mathf.approachDelta(warmupf, efficiency(), 0.04f);
                }

                @Override
                public void craft(){
                    super.craft();
                    int n = Mathf.random(0, 3) + 5;
                    for(int i = 0; i < n; i++){
                        Tmp.v1.trns(Mathf.random(360f), size * tilesize / 1.414f).clamp(-size * tilesize /2f, -size * tilesize / 2f, size * tilesize /2f, size * tilesize / 2f);
                        Tmp.v2.set(Tmp.v1).scl((size * tilesize / 2f + 4f) / (size * tilesize / 2f));
                        Tile t = world.tileWorld(Tmp.v2.x + x, Tmp.v2.y + y);
                        if(t == null || !t.solid()){
                            HIFx.smolSquare.at(Tmp.v1.x + x, Tmp.v1.y + y, Mathf.chance(0.33f) ? color1 : (Mathf.chance(0.5f) ? color2 : new Color()));
                        }
                    }
                }

                @Override
                public void draw() {
                    super.draw();
                    setFlameColor(Tmp.c4);
                    if(heatf >= 0.001f){
                        Draw.z(Layer.bullet - 0.01f);
                        Draw.color(Color.white, Tmp.c4, Mathf.clamp(heatf * 3f - 2f));
                        Draw.alpha(Mathf.clamp(heatf * 1.5f));
                        Draw.rect(lightRegion, x, y);
                    }
                    Draw.z(Layer.blockOver);
                    Draw.blend(Blending.additive);
                    if(heatf >= 0.001f){
                        Draw.alpha(Mathf.clamp(heatf * 1.5f) * 0.2f);
                        Draw.rect(heatRegion, x, y);
                    }
                    Draw.alpha(Mathf.absin(11f, 0.2f * warmupf));
                    Draw.rect(shadowRegion, x, y, sizeScl * Draw.scl * Draw.xscl, sizeScl * Draw.scl * Draw.yscl);
                    Draw.blend();
                    Draw.color();
                }

                @Override
                public void drawLight(){
                    super.drawLight();
                    setFlameColor(Tmp.c4);
                    Drawf.light(x, y, (lightRadius * (1f + Mathf.clamp(heatf) * 0.1f) + Mathf.absin(10f, 5f)) * warmupf * block.size, Tmp.c4, 0.65f);
                }

                public void setFlameColor(Color tmp){
                    tmp.set(color1).lerp(color2, Mathf.absin(Time.time + Mathf.randomSeed(pos(), 0f, 9f * 6.29f), 9f, 1f));
                }
            };
        }
            public TextureRegion lightRegion, heatRegion, shadowRegion;

            public final float sizeScl = 15 * 6f;
            public final Color color1 = Pal.lancerLaser, color2 = Pal.sapBullet;

            @Override
            public void load() {
                super.load();
                lightRegion = atlas.find(name + "-light");
                heatRegion = atlas.find(name + "-light-heat");
                shadowRegion = atlas.find("circle-shadow");
            }
        };
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
            requirements(Category.effect, BuildVisibility.sandboxOnly, with(Items.silicon, 150,Items.thorium, 120, Items.plastanium, 100, Items.surgeAlloy, 60, HIItems.chromium, 80));
            size = 3;
            range = 240f;
            phaseRangeBoost = 0f;
            speedBoost = 2.75f;
            speedBoostPhase = 1.25f;
            useTime = 400f;
            maxLink = 9;
            hasBoost = true;
            strokeOffset = -0.05f;
            strokeClamp = 0.06f;
            consumePower(14f);
            consumeItem(Items.phaseFabric).boost();
            squareSprite = false;
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
        }
            @Override
            protected TextureRegion[] icons() {
                return teamRegion.found() ? new TextureRegion[]{region, teamRegions[Team.sharded.id]} : new TextureRegion[]{region};
            }
        };
        detonator = new Detonator("detonator"){{
            requirements(Category.effect, HIBuildVisibility.campaignOrSandboxOnly, with(Items.lead, 30, Items.graphite, 20, Items.thorium, 10, Items.blastCompound, 10));
            health = 160;
            size = 3;
            squareSprite = false;
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
        bin = new StorageBlock("bin"){{
            requirements(Category.effect, with(Items.copper, 55, Items.lead, 35));
            size = 1;
            itemCapacity = 60;
            scaledHealth = 55;
        }
            @Override
            protected TextureRegion[] icons() {
                return teamRegion.found() ? new TextureRegion[]{region, teamRegions[Team.sharded.id]} : new TextureRegion[]{region};
            }
        };
        cargo = new StorageBlock("cargo"){{
            requirements(Category.effect, with(Items.titanium, 350, Items.thorium, 250, Items.plastanium, 125));
            size = 4;
            itemCapacity = 3000;
            scaledHealth = 55;
        }
            @Override
            protected TextureRegion[] icons() {
                return teamRegion.found() ? new TextureRegion[]{region, teamRegions[Team.sharded.id]} : new TextureRegion[]{region};
            }
        };
        machineryUnloader = new Unloader("machinery-unloader"){{
            requirements(Category.effect, with(Items.copper, 15, Items.lead, 10));
            health = 40;
            speed = 60f / 4.2f;
            group = BlockGroup.transportation;
        }};
        rapidUnloader = new AdaptUnloader("rapid-unloader"){{
            requirements(Category.effect, with(Items.silicon, 35, Items.plastanium, 15, HIItems.nanocore, 10, HIItems.chromium, 15));
            speed = 1f;
            group = BlockGroup.transportation;
        }};
        coreStorage = new CoreStorageBlock("core-storage"){{
            requirements(Category.effect, with(Items.lead, 600, Items.titanium, 400, Items.silicon, 300, Items.thorium, 150, Items.plastanium, 120));
            size = 3;
        }
            @Override
            protected TextureRegion[] icons() {
                return teamRegion.found() ? new TextureRegion[]{region, teamRegions[Team.sharded.id]} : new TextureRegion[]{region};
            }
        };
        //storage-erekir
        reinforcedCoreStorage = new CoreStorageBlock("reinforced-core-storage"){{
            requirements(Category.effect, BuildVisibility.sandboxOnly, with(Items.beryllium, 300, Items.tungsten, 200, Items.silicon, 220, Items.thorium, 120, Items.surgeAlloy, 80));
            size = 3;
            squareSprite = false;
        }};
        //payload
        payloadJunction = new PayloadJunction("payload-junction"){{
            requirements(Category.units, with(Items.graphite, 15, Items.copper, 20));
        }};
        //payload-erekir
        reinforcedPayloadJunction = new PayloadJunction("reinforced-payload-junction"){{
            requirements(Category.units, with(Items.tungsten, 15, Items.beryllium, 10));
            moveTime = 35f;
            health = 800;
            researchCostMultiplier = 4f;
            underBullets = true;
        }};
        //unit
        titanReconstructor = new Reconstructor("titan-reconstructor"){{
            requirements(Category.units, with(Items.lead, 4000, Items.silicon, 3000, Items.plastanium, 1500, Items.surgeAlloy, 1200, Items.phaseFabric, 300, HIItems.chromium, 800));
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
                    new UnitType[]{UnitTypes.omura, HIUnitTypes.mosasaur},
                    new UnitType[]{UnitTypes.navanax, HIUnitTypes.killerWhale},
                    new UnitType[]{HIUnitTypes.destruction, HIUnitTypes.purgatory}
            );
            consumePower(35f);
            consumeLiquid(Liquids.cryofluid, 4f);
            consumeItems(ItemStack.with(Items.silicon, 1500, HIItems.nanocore, 100, HIItems.uranium, 200, HIItems.chromium, 300));
        }};
        experimentalUnitFactory = new DerivativeUnitFactory("experimental-unit-factory"){{
            requirements(Category.units, with(Items.silicon, 2500, Items.plastanium, 1500, Items.surgeAlloy, 1000, HIItems.nanocore, 800, Items.phaseFabric, 400, HIItems.heavyAlloy, 600));
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
        }
            @Override
            public void init() {
                for(int i = 0; i < content.units().size; i++){
                    UnitType u = content.unit(i);
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
                        plans.add(new UnitPlan(u, time * 2, is));
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
        matrixProcessor = new LogicBlock("matrix-processor"){{
            requirements(Category.logic, with(Items.lead, 500, Items.silicon, 350, Items.surgeAlloy, 125, HIItems.nanocore, 50, HIItems.chromium, 75));
            consumeLiquid(Liquids.cryofluid, 0.12f);
            hasLiquids = true;
            instructionsPerTick = 100;
            range = 8 * 62;
            size = 4;
            squareSprite = false;
        }};
        hugeLogicDisplay = new LogicDisplay("huge-logic-display"){{
            requirements(Category.logic, with(Items.lead, 300, Items.silicon, 250, Items.metaglass, 200, Items.phaseFabric, 150));
            displaySize = 272;
            size = 9;
        }};
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
        heatSink = new ProcessorCooler("heat-sink"){{
            requirements(Category.logic, with(Items.titanium, 70, Items.silicon, 25, Items.plastanium, 65));
            size = 2;
        }};
        heatFan = new ProcessorFan("cooler-fan"){{
            requirements(Category.logic, with(Items.titanium, 90, Items.silicon, 50, Items.plastanium, 50, Items.phaseFabric, 25));
            size = 3;
            boost = 3;
            maxProcessors = 5;
            consumePower(4f);
        }};
        heatSinkLarge = new ProcessorCooler("water-block"){{
            requirements(Category.logic, with(Items.titanium, 110, Items.silicon, 50, Items.metaglass, 40, Items.plastanium, 30, Items.surgeAlloy, 15));
            size = 3;
            boost = 2;
            maxProcessors = 6;
            liquidCapacity = 60;
            acceptCoolant = true;
        }};
        //turret
        dissipation = new PointDefenseTurret("dissipation"){{
            requirements(Category.turret, with(Items.silicon, 220, HIItems.chromium, 80, Items.phaseFabric, 40, Items.plastanium, 60));
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
            ammo(Items.graphite, new MissileBulletType(3.6f, 30f){{
                splashDamage = 15f;
                splashDamageRadius = 18f;
                drag = -0.028f;
                backColor = trailColor = HIPal.brightSteelBlue;
                frontColor = HIPal.lightGrey;
                lifetime = 36;
                homingPower = 0.045f;
                homingRange = 40f;
                width = 4f;
                height = 16f;
                hitEffect = Fx.flakExplosion;
                ammoMultiplier = 2;
            }}, Items.pyratite, new MissileBulletType(3.6f, 18f){{
                splashDamage = 36f;
                splashDamageRadius = 22f;
                drag = -0.028f;
                makeFire = true;
                backColor = trailColor = Color.valueOf("ffb90f");
                frontColor = HIPal.lightGrey;
                lifetime = 36;
                homingPower = 0.03f;
                homingRange = 40f;
                width = 4f;
                height = 16f;
                hitEffect = Fx.flakExplosion;
                ammoMultiplier = 3;
            }}, Items.blastCompound, new MissileBulletType(3.6f, 16f){{
                splashDamage = 47f;
                splashDamageRadius = 32f;
                drag = -0.026f;
                backColor = trailColor = HIPal.orangeBack;
                frontColor = HIPal.lightGrey;
                lifetime = 38;
                homingPower = 0.03f;
                homingRange = 40f;
                width = 4f;
                height = 16f;
                hitEffect = Fx.flakExplosionBig;
                ammoMultiplier = 3;
            }});
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
                        backColor = trailColor = HIPal.brightSteelBlue;
                        frontColor = HIPal.lightGrey;
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
                        frontColor = HIPal.lightGrey;
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
                        backColor = trailColor = HIPal.orangeBack;
                        frontColor = HIPal.lightGrey;
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
            ammo(Items.pyratite, new MissileBulletType(10f, 44f, name("rocket")){{
                shrinkY = 0;
                inaccuracy = 4;
                trailChance = 0.8f;
                homingRange = 80;
                splashDamage = 68f;
                splashDamageRadius = 36f;
                lifetime = 32;
                hitShake = 2;
                backColor = Color.valueOf("ffb90f");
                frontColor = HIPal.lightGrey;
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
                    colorFrom = colorTo = HIPal.darkGrey;
                }}, new ParticleEffect(){{
                    particles = 12;
                    line = true;
                    length = 30;
                    baseLength = 8;
                    lifetime = 22;
                    colorFrom = Color.white;
                    colorTo = HIPal.lightYellow;
                }}, new WaveEffect(){{
                    lifetime = 10;
                    sizeFrom = 1;
                    sizeTo = 36;
                    strokeFrom = 8;
                    strokeTo = 0;
                    colorFrom = colorTo = HIPal.lightYellow;
                }});
            }}, Items.blastCompound, new MissileBulletType(10f, 46f, name("missile")){{
                recoil = 1;
                shrinkY = 0;
                inaccuracy = 4;
                trailChance = 0.8f;
                homingRange = 80;
                splashDamage = 132f;
                splashDamageRadius = 76f;
                lifetime = 32;
                hitShake = 2;
                backColor = trailColor = HIPal.orangeBack;
                frontColor = HIPal.lightGrey;
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
                    colorFrom = HIPal.darkGrey;
                    colorTo = HIPal.darkGrey.cpy().a(0.5f);
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
                    colorTo = HIPal.lightYellow;
                }}, new WaveEffect(){{
                    lifetime = 10f;
                    sizeFrom = 1f;
                    sizeTo = 78f;
                    strokeFrom = 8f;
                    strokeTo = 0f;
                    colorFrom = colorTo = HIPal.lightYellow;
                }});
            }});
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
                colorTo = HIPal.lightGrey.cpy().a(0.45f);
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
                        backColor = HIPal.brightSteelBlue;
                        frontColor = HIPal.lightGrey;
                        trailLength = 15;
                        trailWidth = 1.5f;
                        trailColor = Color.white.cpy().a(0.5f);
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
                        frontColor = HIPal.lightGrey;
                        trailLength = 15;
                        trailWidth = 1.5f;
                        trailColor = Color.white.cpy().a(0.5f);
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
                        frontColor = HIPal.lightGrey;
                        trailLength = 15;
                        trailWidth = 1.5f;
                        trailColor = Color.white.cpy().a(0.5f);
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
                        frontColor = HIPal.lightGrey;
                        trailLength = 16;
                        trailWidth = 2.5f;
                        trailColor = Color.white.cpy().a(0.5f);
                        trailEffect = Fx.none;
                        width = 13f;
                        height = 48f;
                        hitShake = 1f;
                        ammoMultiplier = 3f;
                        smokeEffect = Fx.shootSmallFlame;
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
                parts.addAll(new RegionPart("-cover-top"){{
                    progress = PartProgress.warmup;
                    moveY = -6f;
                }}, new RegionPart("-cover-left"){{
                    progress = PartProgress.warmup;
                    moveX = 6f;
                }}, new RegionPart("-cover-bottom"){{
                    progress = PartProgress.warmup;
                    moveY = 6f;
                }}, new RegionPart("-cover-right"){{
                    progress = PartProgress.warmup;
                    moveX = -6f;
                }});
            }};
            shoot = new ShootBarrel(){{
                shots = 4;
                shotDelay = 5;
                barrels = new float[]{
                        0f, 6f, 0f,
                        -6f, 0f, 90f,
                        0f, -6f, 180f,
                        6f, 0f, -90f
                };
            }};
            ammoUseEffect = new MultiEffect(new ParticleEffect(){{
                particles = 8;
                sizeFrom = 4;
                sizeTo = 1;
                length = 45;
                lifetime = 40;
                lightOpacity = 0;
                colorFrom = Color.white.cpy().a(0.5f);
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
            consumePowerCond(6f, TurretBuild::isActive);
        }};
        coilBlaster = new TeslaTurret("coil-blaster"){{
            requirements(Category.turret, with(Items.copper, 60, Items.lead, 85, Items.graphite, 40, Items.silicon, 55, Items.titanium, 80));
            size = 2;
            scaledHealth = 200f;
            reload = 20f;
            range = 130f;
            maxTargets = 5;
            rings.add(new TeslaRing(2f), new TeslaRing(6f));
            damage = 23f;
            status = StatusEffects.shocked;
            consumePower(4.8f);
            coolant = consumeCoolant(0.2f);
        }};
        dragonBreath = new ItemTurret("dragon-breath"){{
            requirements(Category.turret, with(Items.graphite, 40, Items.silicon, 25, Items.titanium, 60, Items.plastanium, 30));
            ammo(Items.coal, new FlameBulletType(Pal.lightFlame, Pal.darkFlame, Color.gray, range + 8, 14, 60, 22), Items.pyratite, new FlameBulletType(Pal.lightPyraFlame, Pal.darkPyraFlame, Color.gray, range + 8, 20, 72, 22){{
                damage = 98;
                statusDuration = 60 * 6;
                ammoMultiplier = 4;
            }});
            size = 2;
            health = 1160;
            recoil = 0f;
            reload = 8f;
            range = 88f;
            shootCone = 50f;
            targetAir = false;
            ammoUseEffect = Fx.none;
            shootSound = Sounds.flame;
            coolantMultiplier = 1.5f;
            coolant = consumeCoolant(0.1f);
        }};
        cloudbreaker = new ItemTurret("cloudbreaker"){{
            requirements(Category.turret, with(Items.graphite, 230, Items.titanium, 220, Items.thorium, 150));
            ammo(
                    Items.titanium, new CritBulletType(14f, 220f){{
                        lifetime = 25f;
                        knockback = 5f;
                        width = 7f;
                        height = 12f;
                        pierce = pierceArmor = true;
                        pierceCap = 3;
                        critChance = 0.08f;
                        critMultiplier = 2.5f;
                    }},
                    Items.thorium, new CritBulletType(17.5f, 280f){{
                        lifetime = 25f;
                        rangeChange = 70f;
                        knockback = 5f;
                        width = 8f;
                        height = 14f;
                        pierce = pierceArmor = true;
                        pierceCap = 5;
                        critChance = 0.05f;
                        critMultiplier = 4.5f;
                    }},
                    HIItems.uranium, new CritBulletType(20f, 360f){{
                        lifetime = 25f;
                        rangeChange = 120f;
                        knockback = 4f;
                        width = 9f;
                        height = 16f;
                        pierce = pierceArmor = true;
                        pierceCap = 8;
                        critChance = 0.05f;
                        critMultiplier = 3f;
                        despawnHitEffects = false;
                        setDefaults = false;
                        fragOnHit = false;
                        fragBullets = 5;
                        fragVelocityMin = 0.8f;
                        fragVelocityMax = 1.2f;
                        fragRandomSpread = 30f;
                        fragBullet = new CritBulletType(12f, 80f){{
                            lifetime = 5f;
                            knockback = 3f;
                            width = 6f;
                            height = 14f;
                            pierceCap = 3;
                            critMultiplier = 3f;
                            critEffect = HIFx.miniCrit;
                        }};
                    }
                        @Override
                        public void removed(Bullet b){
                            super.removed(b);
                            if(b.fdata != 1f) createFrags(b, b.x, b.y);
                        }
                    }
            );
            size = 3;
            range = 330f;
            hideDetails = false;
            scaledHealth = 120;
            reload = 80f;
            rotateSpeed = 2.5f;
            recoil = 5f;
            cooldownTime = 300f;
            shootSound = Sounds.artillery;
            coolant = consumeCoolant(0.2f);
        }};
        minigun = new MinigunTurret("minigun"){{
            requirements(Category.turret, with(Items.copper, 350, Items.graphite, 300, Items.titanium, 150, Items.plastanium, 175, Items.surgeAlloy, 120));
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
                    }},
                    Items.silicon, new BasicBulletType(12f, 21f){{
                        width = 5f;
                        height = 8f;
                        homingPower = 0.07f;
                        reloadMultiplier = 1.3f;
                        ammoMultiplier = 5;
                        lifetime = 24f;
                    }},
                    Items.thorium, new BasicBulletType(15f, 47f){{
                        width = 6f;
                        height = 11f;
                        shootEffect = Fx.shootBig;
                        smokeEffect = Fx.shootBigSmoke;
                        ammoMultiplier = 4f;
                        lifetime = 20f;
                    }},
                    HIItems.uranium, new BasicBulletType(17f, 65f){{
                        width = 7f;
                        height = 13f;
                        shootEffect = Fx.shootBig;
                        smokeEffect = Fx.shootBigSmoke;
                        status = StatusEffects.melting;
                        ammoMultiplier = 6f;
                        lifetime = 18f;
                    }}
            );
            size = 4;
            range = 280f;
            maxSpeed = 27f;
            scaledHealth = 150;
            shootCone = 35f;
            shootSound = Sounds.shootBig;
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
        blaze = new EruptorTurret("blaze"){{
            requirements(Category.turret, with(Items.lead, 750, Items.graphite, 550, Items.silicon, 600, Items.surgeAlloy, 200, Items.phaseFabric, 200));
            size = 4;
            scaledHealth = 190;
            shootDuration = 120f;
            range = 280f;
            reload = 150f;
            shootY = 0.25f;
            rotateSpeed = 3.5f;
            recoil = 4f;
            beamEffect = HIFx.blazeBeam;
            shootType = new MagmaBulletType(322, 24f){{
                shake = 2f;
            }};
            coolant = consumeCoolant(0.2f);
            consumePower(17f);
        }};
        spike = new ItemTurret("spike"){{
            requirements(Category.turret, with(Items.copper, 30, Items.lead, 60, Items.graphite, 40, Items.titanium, 50));
            health = 960;
            range = 200f;
            smokeEffect = Fx.shootBigSmoke;
            coolant = consumeCoolant(0.1f);
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
            ammo(
                    Items.titanium, new BasicBulletType(5f, 24f){{
                        width = 8f;
                        height = 25f;
                        hitColor = backColor = lightColor = trailColor = Items.titanium.color.cpy().lerp(Color.white, 0.1f);
                        frontColor = backColor.cpy().lerp(Color.white, 0.35f);
                        hitEffect = HIFx.crossBlast(hitColor, height + width);
                        shootEffect = despawnEffect = HIFx.square(hitColor, 20f, 3, 12f, 2f);
                        ammoMultiplier = 8;
                    }},
                    Items.plastanium, new BasicBulletType(5f, 26f){{
                        width = 8f;
                        height = 25f;
                        fragBullets = 4;
                        fragBullet = new BasicBulletType(2f, 26f){{
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
                    }},
                    Items.thorium, new BasicBulletType(5f, 38f){{
                        width = 8f;
                        height = 25f;
                        status = StatusEffects.shocked;
                        statusDuration = 15f;
                        ammoMultiplier = 12;
                        lightningColor = hitColor = backColor = lightColor = trailColor = Items.thorium.color.cpy().lerp(Color.white, 0.1f);
                        frontColor = backColor.cpy().lerp(Color.white, 0.35f);
                        hitEffect = HIFx.crossBlast(hitColor, height + width);
                        shootEffect = despawnEffect = HIFx.square(hitColor, 20f, 3, 20f, 2f);
                    }},
                    Items.pyratite, new BasicBulletType(5f, 18f){{
                        width = 8f;
                        height = 25f;
                        incendAmount = 4;
                        incendChance = 0.25f;
                        incendSpread = 12f;
                        status = StatusEffects.burning;
                        statusDuration = 15f;
                        ammoMultiplier = 12f;
                        hitColor = backColor = lightColor = trailColor = Items.pyratite.color.cpy().lerp(Color.white, 0.1f);
                        frontColor = backColor.cpy().lerp(Color.white, 0.35f);
                        hitEffect = HIFx.crossBlast(hitColor, height + width);
                        despawnEffect = Fx.blastExplosion;
                        shootEffect = HIFx.square(hitColor, 20f, 3, 20f, 2f);
                    }},
                    Items.blastCompound, new BasicBulletType(5f, 22f){{
                        width = 8f;
                        height = 25f;
                        status = StatusEffects.blasted;
                        statusDuration = 15f;
                        splashDamageRadius = 12f;
                        splashDamage = 36f;
                        ammoMultiplier = 8;
                        hitColor = backColor = lightColor = trailColor = Items.blastCompound.color.cpy().lerp(Color.white, 0.1f);
                        frontColor = backColor.cpy().lerp(Color.white, 0.35f);
                        hitEffect = HIFx.crossBlast(hitColor, height + width);
                        despawnEffect = Fx.blastExplosion;
                        shootEffect = HIFx.square(hitColor, 20f, 3, 20f, 2f);
                    }}
            );
            limitRange();
            maxAmmo = 120;
            ammoPerShot = 12;
        }};
        fissure = new ItemTurret("fissure"){{
            requirements(Category.turret, with(Items.titanium, 110, Items.thorium, 90, Items.graphite, 150, Items.silicon, 120, Items.plastanium, 80));
            ammo(
                    Items.titanium, new BasicBulletType(8f, 45f){{
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
                    }},
                    Items.thorium, new BasicBulletType(8f, 65f){{
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
                    }},
                    Items.plastanium, new BasicBulletType(8f, 60f){{
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
                    }},
                    Items.pyratite, new BasicBulletType(8f, 40f){{
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
                    }},
                    Items.blastCompound, new BasicBulletType(8f, 40f){{
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
                    }},
                    HIItems.chromium, new BasicBulletType(8f, 125f){{
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
                    }},
                    Items.phaseFabric, new BasicBulletType(8f, 65f){{
                        lifetime = 48f;
                        width = 8f;
                        height = 42f;
                        shrinkX = 0;
                        trailWidth = 1.7f;
                        trailLength = 9;
                        trailColor = backColor = hitColor = lightColor = lightningColor = Pal.accent;
                        frontColor = Color.white;
                        shootEffect = HIFx.square(backColor, 45f, 5, 38, 4);
                        smokeEffect = Fx.shootBigSmoke;
                        splashDamage = damage;
                        splashDamageRadius = 32f;
                        despawnEffect = hitEffect = new MultiEffect(HIFx.circleOut(backColor, splashDamageRadius * 1.25f), HIFx.hitSparkLarge);
                        ammoMultiplier = 6;
                        reloadMultiplier = 0.9f;
                        status = StatusEffects.melting;
                        statusDuration = 120f;
                    }}
            );
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
        hurricane = new PowerTurret("hurricane"){{
            requirements(Category.turret, with(Items.lead, 80, Items.graphite, 100, Items.silicon, 250, Items.plastanium, 120, Items.phaseFabric, 150));
            size = 3;
            health = 960;
            range = 300f;
            reload = 12f;
            shoot = new ShootAlternate(){{
                spread = 7f;
            }};
            shootCone = 24f;
            shootSound = Sounds.spark;
            shootType = new PositionLightningBulletType(50f){{
                lightningColor = hitColor = Pal.techBlue;
                maxRange = rangeOverride = 250f;
                hitEffect = HIFx.hitSpark;
                smokeEffect = Fx.shootBigSmoke2;
            }};
            drawer = new DrawTurret(){{
                parts.add(new RegionPart(){{
                    drawRegion = false;
                    mirror = true;
                    moveY = -2.75f;
                    progress = PartProgress.recoil;
                    children.add(new RegionPart("-shooter"){{
                        heatLayerOffset = 0.001f;
                        heatColor = HIPal.thurmixRed.cpy().a(0.85f);
                        progress = PartProgress.warmup;
                        mirror = outline = true;
                        moveX = 2f;
                        moveY = 2f;
                        moveRot = 11.25f;
                    }});
                }}, new RegionPart("-up"){{
                    layerOffset = 0.3f;
                    turretHeatLayer += layerOffset + 0.1f;
                    heatColor = HIPal.thurmixRed.cpy().a(0.85f);
                    outline = false;
                }});
            }};
            warmupMaintainTime = 120f;
            rotateSpeed = 3f;
            coolant = new ConsumeCoolant(0.15f);
            consumePowerCond(35f, TurretBuild::isActive);
            canOverdrive = false;
        }};
        judgement = new ContinuousTurret("judgement"){{
            requirements(Category.turret, with(Items.silicon, 1200, Items.metaglass, 400, Items.plastanium, 800, Items.surgeAlloy, 650, Items.phaseFabric, 550, HIItems.heavyAlloy, 400));
            shootType = new PointLaserBulletType(){{
                damage = 100f;
                hitEffect = HIFx.hitSpark;
                beamEffect = Fx.none;
                beamEffectInterval = 0;
                buildingDamageMultiplier = 0.75f;
                damageInterval = 1;
                color = hitColor = Pal.techBlue;
                sprite = "laser-white";
                status = StatusEffects.slow;
                statusDuration = 60;
                oscScl /= 1.77f;
                oscMag /= 1.33f;
                hitShake = 2;
                range = 75 * 8;
                trailLength = 8;
            }
                private final Color tmpColor = new Color();
                private final Color from = color, to = Pal.techBlue;
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
                        if(!headless && b.timer(3, 3)){
                            PosLightning.createEffect(b, Tmp.v1.set(b.aimX, b.aimY), getColor(b), 1, 2);
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
                    Drawn.basicLaser(b.x, b.y, b.aimX, b.aimY, stroke);
                    Draw.color(Color.white);
                    Drawn.basicLaser(b.x, b.y, b.aimX, b.aimY, stroke * 0.64f * (2 + darkenPartWarmup) / 3f);

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
                        Drawn.basicLaser(b.x, b.y, b.aimX, b.aimY, stroke * 0.55f * darkenPartWarmup);
                        Draw.z(HIFx.EFFECT_BOTTOM);
                        Drawn.basicLaser(b.x, b.y, b.aimX, b.aimY, stroke * 0.6f * darkenPartWarmup);
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
            armor = 10f;
            unitSort = UnitSorts.strongest;
            consumePower(16);
            consumeLiquid(HILiquids.nanofluid, 12f / 60f);
        }};
        //turrets-erekir
        rupture = new ItemTurret("rupture"){{
            requirements(Category.turret, with(Items.graphite, 360, Items.silicon, 250, Items.beryllium, 380, Items.tungsten, 180, Items.oxide, 45));
            ammo(
                    Items.beryllium, new BasicBulletType(12f, 72f){{
                        buildingDamageMultiplier = 0.33f;
                        ammoMultiplier = 3f;
                        knockback = 1.1f;
                        lifetime = 56f / 3;
                        width = 13f;
                        height = 22f;
                        pierce = pierceBuilding = true;
                        pierceCap = 3;
                        hitColor = backColor = trailColor = Pal.berylShot;
                        frontColor = Color.white;
                        trailLength = 11;
                        trailWidth = 2.2f;
                        hitEffect = despawnEffect = Fx.hitBulletColor;
                        smokeEffect = Fx.shootBigSmoke;
                        shootEffect = new MultiEffect(Fx.shootSmallColor, Fx.colorSpark);
                    }},
                    Items.tungsten, new BasicBulletType(13f, 96.8f){{
                        buildingDamageMultiplier = 0.33f;
                        ammoMultiplier = 4f;
                        knockback = 1.5f;
                        lifetime = 21.3f;
                        rangeChange = 22f;
                        width = 13f;
                        height = 23f;
                        pierce = pierceBuilding = true;
                        pierceCap = 4;
                        hitColor = backColor = trailColor = Pal.tungstenShot;
                        frontColor = Color.white;
                        trailLength = 11;
                        trailWidth = 2.3f;
                        hitEffect = despawnEffect = Fx.hitBulletColor;
                        smokeEffect = Fx.shootBigSmoke;
                        shootEffect = new MultiEffect(Fx.shootSmallColor, Fx.colorSpark);
                    }},
                    Items.thorium, new BasicBulletType(15f, 126.3f){{
                        buildingDamageMultiplier = 0.33f;
                        ammoMultiplier = 5f;
                        knockback = 1.5f;
                        lifetime = 17.84f;
                        rangeChange = 44f;
                        width = 14f;
                        height = 22.5f;
                        pierce = pierceBuilding = true;
                        pierceCap = 5;
                        hitColor = backColor = trailColor = Pal.thoriumPink;
                        frontColor = Color.white;
                        trailLength = 11;
                        trailWidth = 2.4f;
                        hitEffect = despawnEffect = Fx.hitBulletColor;
                        smokeEffect = Fx.shootBigSmoke;
                        shootEffect = new MultiEffect(Fx.shootSmallColor, Fx.colorSpark);
                    }}
            );
            health = 2330;
            size = 4;
            armor = 8f;
            reload = 44f;
            range = 223.6f;
            coolantMultiplier = 3f;
            minWarmup = 0.6f;
            warmupMaintainTime = 45f;
            shootWarmupSpeed = 0.08f;
            outlineColor = Pal.darkOutline;
            drawer = new DrawTurret("reinforced-");
            shootSound = Sounds.shootAlt;
            consumeAmmoOnce = false;
            shoot = new ShootBarrel(){{
                shots = 6;
                shotDelay = 13;
                barrels = new float[]{
                        0f, 12f, 0f,
                        6.75f, 0f, 0f,
                        -6.75f, 0f, 0f
                };
            }};
            shootY = 0f;
            ammoUseEffect = Fx.none;
            inaccuracy = rotateSpeed = shake = 1f;
            maxAmmo = 24;
            ammoPerShot = 2;
            recoilTime = 22f;
            recoil = 3f;
            coolant = consume(new ConsumeLiquid(Liquids.water, 0.5f));
            squareSprite = false;
        }};
        //tbd
        ohNo = new PlatformTurret("oh-no"){{
            requirements(Category.turret, BuildVisibility.sandboxOnly, with());
            health = 10000;
            armor = 30f;
            size = 5;
            range = 1200f;
            canOverdrive = false;
            shake = 80f;
            shootSound = Sounds.laserblast;
            shootCone = 5f;
            reload = 600f;
            shootType = HIBullets.arc9000hyper;
        }
            @Override
            public void unlock() {
                //haha no
            }
        };
        wrongTextureFolder = new PlatformTurret("wrong-texture-folder"){{
            requirements(Category.turret, BuildVisibility.sandboxOnly, with());
            health = 10000;
            armor = 30f;
            size = 5;
            range = 900f;
            canOverdrive = false;
            shootSound = Sounds.none;
            shootCone = 5f;
            reload = 800f;
            shootType = HIBullets.collapse;
            buildType = () -> new PlatformTurretBuild(){
                @Override
                protected void shoot(BulletType type){
                    //TODO Generate a bullet at the target point.
                    float bulletX = x + Angles.trnsx(rotation - 90, shootX, shootY), bulletY = y + Angles.trnsy(rotation - 90, shootX, shootY);

                    shootSound.at(bulletX, bulletY, Mathf.random(soundPitchMin, soundPitchMax));

                    Tmp.v6.set(targetPos.x, targetPos.y).sub(this);
                    Tmp.v1.set(targetPos.x, targetPos.y).sub(this).nor().scl(Math.min(Tmp.v6.len(), range)).add(this);

                    Bullet b = shootType.create(this, team, Tmp.v1.x, Tmp.v1.y, 0);
                    b.vel.setZero();
                    b.set(Tmp.v1);

                    if(headless)return;
                    Vec2 vec2 = new Vec2().trns(rotation, y).add(this);
                    PosLightning.createEffect(vec2, b, HIPal.thurmixRed, 3, 2.5f);
                    for(int i = 0; i < 5; i++){
                        Time.run(i * 6f, () -> HIFx.chainLightningFade.at(vec2.x, vec2.y, Mathf.random(8, 14), HIPal.thurmixRed, b));
                    }

                    shootType.shootEffect.at(bulletX, bulletY, rotation);
                    shootType.smokeEffect.at(bulletX, bulletY, rotation);
                }
            };
        }
            @Override
            public void unlock() {
                //haha no
            }
        };
        tbd = new RegenWall("tbd"){{
            requirements(Category.defense, BuildVisibility.sandboxOnly, with());
            health = 40000;
            armor = 60f;
            size = 3;
            chanceDeflect = Float.MAX_VALUE;
            absorbLasers = insulated = true;
            chanceHeal = 0.15f;
            regenPercent = 0.5f;
            unlocked = false;
        }
            @Override
            public void unlock() {
                //haha no
            }
        };
    }
}
