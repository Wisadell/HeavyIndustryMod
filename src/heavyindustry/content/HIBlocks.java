package heavyindustry.content;

import heavyindustry.core.*;
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

import static heavyindustry.core.HeavyIndustryMod.*;
import static arc.graphics.g2d.Draw.*;
import static mindustry.Vars.*;
import static mindustry.type.ItemStack.*;

/** Defines the {@linkplain Block blocks} this mod offers. */
public class HIBlocks {
    public static Block
            //environment
            darkPanel7,darkPanel8,darkPanel9,darkPanel10,darkPanel11,darkPanelDamaged,
            stoneVent,basaltVent,shaleVent,basaltWall,snowySand,snowySandWall,arkyciteSand,arkyciteSandWall,arkyciteSandBoulder,darksandBoulder,asphalt,asphaltSide,labFloor,
            nanofluid,
            stoneWater,shaleWater,basaltWater,
            softRareEarth,patternRareEarth,softRareEarthWall,
            oreUranium,oreChromium,
            //wall
            armoredWall,armoredWallLarge,uraniumWall,uraniumWallLarge,chromiumWall,chromiumWallLarge,chromiumDoor,chromiumDoorLarge,heavyAlloyWall,heavyAlloyWallLarge,nanoCompositeWall,nanoCompositeWallLarge,
            //wall-erekir
            berylliumWallHuge,berylliumWallGigantic,tungstenWallHuge,tungstenWallGigantic,blastDoorLarge,blastDoorHuge,reinforcedSurgeWallHuge,reinforcedSurgeWallGigantic,carbideWallHuge,carbideWallGigantic,shieldedWallLarge,shieldedWallHuge,
            //drill
            largeWaterExtractor,slagExtractor,reinforcedOilExtractor,cuttingDrill,beamDrill,speedModule,
            //drill-erekir
            largeCliffCrusher,heavyPlasmaBore,unitMinerDepot,
            //distribution
            invertedJunction,cardanItemBridge,itemLiquidJunction,chromiumEfficientConveyor,chromiumArmorConveyor,chromiumTubeConveyor,chromiumStackConveyor,chromiumStackRouter,chromiumJunction,chromiumInvertedJunction,chromiumRouter,chromiumItemBridge,
            stackHelper,highEnergyItemNode,rapidDirectionalUnloader,
            //distribution-erekir
            ductJunction,armoredDuctBridge,waveDuct,waveDuctBridge,waveDuctRouter,overflowWaveDuct,underflowWaveDuct,rapidDuctUnloader,
            //liquid
            cardanLiquidBridge,turboPump,highEnergyLiquidNode,chromiumArmorConduit,chromiumLiquidBridge,chromiumArmorLiquidContainer,chromiumArmorLiquidTank,
            //liquid-erekir
            liquidSorter,liquidValve,smallReinforcedPump,largeReinforcedPump,
            //power
            powerNodeHighEnergy,powerNodeGiant,windTurbine,uraniumReactor, magneticStormRadiationReactor,armoredCoatedBattery,
            //power-erekir
            beamDiode,beamInsulator,liquidConsumeGenerator,
            //production
            largeKiln,largePulverizer,largeMelter,largeCryofluidMixer,largePyratiteMixer,largeBlastMixer,largeCultivator,largePlastaniumCompressor,largeSurgeSmelter,blastSiliconSmelter,
            nanocoreConstructor,nanocorePrinter,nanocoreActivator,highEnergyPhaseWeaver,highEnergyEnergizer,highEnergyReactor,highEnergyFabricFusionInstrument,uraniumSynthesizer,chromiumSynthesizer,heavyAlloySmelter,metalAnalyzer,nitrificationReactor,nitratedOilSedimentationTank,
            //production-erekir
            ventHeater,chemicalSiliconSmelter,largeElectricHeater,liquidFuelHeater,largeOxidationChamber,largeSurgeCrucible,largeCarbideCrucible,
            //defense
            lighthouse,mendDome,assignOverdrive,largeShieldGenerator,
            //defense-erekir
            largeRadar,
            //storage
            cargo,bin,machineryUnloader,rapidUnloader,coreBeStationed,
            //unit
            titanReconstructor,experimentalUnitFactory,
            //payload
            payloadJunction,
            //payload-erekir
            reinforcedPayloadJunction,
            //unit-erekir
            largeUnitRepairTower,seniorAssemblerModule,
            //logic
            buffrerdMemoryCell,buffrerdMemoryBank,
            //turret
            dissipation,rocketLauncher,multipleRocketLauncher,largeRocketLauncher,rocketSilo,dragonBreath,cloudbreaker,minigun,
            spike,fissure,
            hurricane,frost,judgement,spark,fireworks,
            //turret-erekir
            tbd;

    /** Instantiates all contents. Called in the main thread in {@link HeavyIndustryMod#loadContent()}. */
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
            variants = 2;
            arkyciteSand.asFloor().decoration = this;
        }};
        darksandBoulder = new Prop("darksand-boulder"){{
            variants = 2;
            Blocks.darksand.asFloor().decoration = this;
        }};
        asphalt = new Floor("asphalt", 0);
        asphaltSide = new SideFloor("asphalt-side", 16){{
            blendGroup = asphalt;
        }};
        labFloor = new TiledFloor("lab-floor", 8, 1);
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
        //wall-erekir
        berylliumWallHuge = new Wall("beryllium-wall-huge"){{
            requirements(Category.defense, BuildVisibility.sandboxOnly, with(Items.beryllium, 54));
            health = 4680;
            armor = 2f;
            buildCostMultiplier = 3f;
            size = 3;
        }};
        berylliumWallGigantic = new Wall("beryllium-wall-gigantic"){{
            requirements(Category.defense, BuildVisibility.sandboxOnly, with(Items.beryllium, 96));
            health = 8320;
            armor = 2f;
            buildCostMultiplier = 2f;
            size = 4;
        }};
        tungstenWallHuge = new Wall("tungsten-wall-huge"){{
            requirements(Category.defense, BuildVisibility.sandboxOnly, with(Items.tungsten, 54));
            health = 6480;
            armor = 14f;
            buildCostMultiplier = 3f;
            size = 3;
        }};
        tungstenWallGigantic = new Wall("tungsten-wall-gigantic"){{
            requirements(Category.defense, BuildVisibility.sandboxOnly, with(Items.tungsten, 96));
            health = 11520;
            armor = 14f;
            buildCostMultiplier = 2f;
            size = 4;
        }};
        blastDoorLarge = new AutoDoor("blast-door-large"){{
            requirements(Category.defense, BuildVisibility.sandboxOnly, with(Items.tungsten, 54, Items.silicon, 54));
            health = 6300;
            armor = 14f;
            size = 3;
        }};
        blastDoorHuge = new AutoDoor("blast-door-huge"){{
            requirements(Category.defense, BuildVisibility.sandboxOnly, with(Items.tungsten, 96, Items.silicon, 96));
            health = 11200;
            armor = 14f;
            size = 4;
        }};
        reinforcedSurgeWallHuge = new Wall("reinforced-surge-wall-huge"){{
            requirements(Category.defense, BuildVisibility.sandboxOnly, with(Items.surgeAlloy, 54, Items.tungsten, 18));
            health = 9000;
            lightningChance = 0.1f;
            lightningDamage = 30f;
            armor = 20f;
            size = 3;
            //researchCost = with(Items.surgeAlloy, 120, Items.tungsten, 600);
        }};
        reinforcedSurgeWallGigantic = new Wall("reinforced-surge-wall-gigantic"){{
            requirements(Category.defense, BuildVisibility.sandboxOnly, with(Items.surgeAlloy, 96, Items.tungsten, 32));
            health = 16000;
            lightningChance = 0.1f;
            lightningDamage = 30f;
            armor = 20f;
            size = 4;
            //researchCost = with(Items.surgeAlloy, 240, Items.tungsten, 1200);
        }};
        carbideWallHuge = new Wall("carbide-wall-huge"){{
            requirements(Category.defense, BuildVisibility.sandboxOnly, with(Items.thorium, 54, Items.carbide, 54));
            health = 9720;
            armor = 16f;
            size = 3;
        }};
        carbideWallGigantic = new Wall("carbide-wall-gigantic"){{
            requirements(Category.defense, BuildVisibility.sandboxOnly, with(Items.thorium, 96, Items.carbide, 96));
            health = 17280;
            armor = 16f;
            size = 4;
        }};
        shieldedWallLarge = new ShieldWall("shielded-wall-large"){{
            requirements(Category.defense, BuildVisibility.sandboxOnly, with(Items.phaseFabric, 45, Items.surgeAlloy, 27, Items.beryllium, 27));
            consumePower(6.75f / 60f);
            outputsPower = false;
            hasPower = true;
            consumesPower = true;
            conductivePower = true;
            chanceDeflect = 8f;
            health = 9360;
            armor = 15f;
            size = 3;
            shieldHealth = 2025f;
            breakCooldown = 60f * 15f;
            regenSpeed = 2f;
        }};
        shieldedWallHuge = new ShieldWall("shielded-wall-huge"){{
            requirements(Category.defense, BuildVisibility.sandboxOnly, with(Items.phaseFabric, 80, Items.surgeAlloy, 48, Items.beryllium, 48));
            consumePower(12f / 60f);
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
        cuttingDrill = new AdaptDrill("cutting-drill"){{
            requirements(Category.production, with(Items.graphite, 100, Items.silicon, 120, Items.thorium, 50, Items.plastanium, 40, Items.surgeAlloy, 30));
            size = 3;
            health = 590;
            armor = 3;
            tier = 8;
            maxOreTileReq = 8;
            itemCapacity = 10;
            updateEffect = Fx.mineBig;
        }};
        beamDrill = new LaserBeamDrill("beam-drill"){{
            requirements(Category.production, with(Items.lead, 160, Items.silicon, 120,  HIItems.chromium, 60, HIItems.nanocore, 35,Items.phaseFabric, 25));
            size = 4;
            health = 960;
            tier = 11;
            maxOreTileReq = 10;
            buildCostMultiplier = 0.8f;
        }};
        speedModule = new DrillModule("speed-module"){{
            requirements(Category.production, with(Items.titanium, 35, Items.plastanium, 30, Items.phaseFabric, 25));
            size = 2;
            boostSpeed = 1f;
            powerMul = 1.2f;
            powerExtra = 180f;
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
        unitMinerDepot = new UnitMinerDepot("unit-miner-depot"){{
            requirements(Category.production, with(Items.beryllium, 200, Items.graphite, 120, Items.silicon, 80, Items.tungsten, 100, Items.oxide, 30));
            size = 3;
            buildTime = 60f * 8f;
            consumePower(8f / 60f);
            consumeLiquid(Liquids.nitrogen, 10f / 60f);
            itemCapacity = 100;
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
        cardanItemBridge = new CardanItemBridge("cardan-item-bridge"){{
            requirements(Category.distribution, with(Items.copper, 5, Items.lead, 5, Items.titanium, 5));
            transportTime = 4.5f;
            fadeIn = moveArrows = false;
            range = 4;
            arrowSpacing = 6f;
            bufferCapacity = 14;
        }};
        itemLiquidJunction = new MultiJunction("item-liquid-junction"){{
            requirements(Category.distribution, with(Items.copper, 4, Items.graphite, 6, Items.metaglass, 10));
        }};
        chromiumEfficientConveyor = new BeltConveyor("chromium-efficient-conveyor"){{
            requirements(Category.distribution, with(Items.lead, 1, HIItems.chromium, 1));
            health = 240;
            armor = 3;
            speed = 0.18f;
            displayedSpeed = 18;
        }};
        chromiumArmorConveyor = new BeltArmoredConveyor("chromium-armor-conveyor"){{
            requirements(Category.distribution, with(Items.metaglass, 1, Items.thorium, 1, Items.plastanium, 1, HIItems.chromium, 1));
            health = 560;
            armor = 5;
            speed = 0.18f;
            displayedSpeed = 18;
        }};
        chromiumTubeConveyor = new TubeConveyor("chromium-tube-conveyor"){{
            requirements(Category.distribution, BuildVisibility.sandboxOnly, with(Items.metaglass, 1, Items.silicon, 1, HIItems.chromium, 2));
            health = 720;
            armor = 8;
            speed = 0.18f;
            displayedSpeed = 18f;
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
            ((BeltConveyor) chromiumEfficientConveyor).junctionReplacement = this;
            ((BeltArmoredConveyor) chromiumArmorConveyor).junctionReplacement = this;
            ((TubeConveyor) chromiumTubeConveyor).junctionReplacement = this;
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
            ((BeltConveyor) chromiumEfficientConveyor).bridgeReplacement = this;
            ((BeltArmoredConveyor) chromiumArmorConveyor).bridgeReplacement = this;
            ((TubeConveyor) chromiumTubeConveyor).bridgeReplacement = this;
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
            speed = 2f;
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
            buildType = () -> new ArmoredConduitBuild(){
                @Override
                public float moveLiquid(Building next, Liquid liquid) {
                    if (next == null) return 0f;
                    float hotLine = 0.7f;
                    float coldLine = 0.55f;
                    next = next.getLiquidDestination(this, liquid);
                    if (next.team == this.team && next.block.hasLiquids && this.liquids.get(liquid) > 0f) {
                        float ofract = next.liquids.get(liquid) / next.block.liquidCapacity;
                        float fract = this.liquids.get(liquid) / this.block.liquidCapacity * this.block.liquidPressure;
                        float flow = Math.min(Mathf.clamp(fract - ofract) * this.block.liquidCapacity, this.liquids.get(liquid));
                        flow = Math.min(flow, next.block.liquidCapacity - next.liquids.get(liquid));
                        if (flow > 0 && ofract <= fract && next.acceptLiquid(this, liquid)) {
                            next.handleLiquid(this, liquid, flow);
                            this.liquids.remove(liquid, flow);
                            return flow;
                        } else if (next.liquids.currentAmount() / next.block.liquidCapacity > 0.1f && fract > 0.1f) {
                            float fx = (this.x + next.x) / 2f;
                            float fy = (this.y + next.y) / 2f;
                            Liquid other = next.liquids.current();
                            // There was flammability logics, removed
                            if ((liquid.temperature > hotLine && other.temperature < coldLine) || (other.temperature > hotLine && liquid.temperature < coldLine)) {
                                this.liquids.remove(liquid, Math.min(this.liquids.get(liquid), hotLine * Time.delta));
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
        cardanLiquidBridge = new CardanLiquidBridge("cardan-liquid-bridge"){{
            requirements(Category.liquid, with(Items.lead, 5, Items.metaglass, 5, Items.titanium, 5));
            liquidCapacity = 16f;
            range = 4;
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
            requirements(Category.power, with(Items.copper, 60, Items.lead, 40, Items.graphite, 25));
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
            powerProduction = 2f;
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
        magneticStormRadiationReactor = new HyperGenerator("magnetic-storm-radiation-reactor"){{
            requirements(Category.power, with(Items.titanium, 1200, Items.metaglass, 1300, Items.plastanium, 800, Items.silicon, 1600, HIItems.highEnergyFabric, 1200, HIItems.chromium, 2500, HIItems.heavyAlloy, 2200));
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
            consumeItem(HIItems.highEnergyFabric);
            consumeLiquid(HILiquids.nanofluid, 0.25f);
            buildCostMultiplier = 0.6f;
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
            craftTime = 35f;
            outputItem = new ItemStack(Items.silicon, 10);
            craftEffect = Fx.smeltsmoke;
            drawer = new DrawMulti(new DrawDefault(), new DrawFlame(Color.valueOf("ffef99")));
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
                            Fill.square(build.x + Angles.trnsx(angle, len), build.y + Angles.trnsy(angle, len), particleSize * fin * build.warmup(), 45);
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
                    Drawf.spinSprite(rotator, build.x + x, build.y + y, Drawn.rotator_90(Drawn.cycle(build.totalProgress() * rotateSpeed, 0, craftTime), 0.15f));
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
        machineryUnloader = new Unloader("machinery-unloader"){{
            requirements(Category.effect, with(Items.copper, 15, Items.lead, 10));
            health = 40;
            speed = 60f / 4.2f;
        }};
        rapidUnloader = new AdaptUnloader("rapid-unloader"){{
            requirements(Category.effect, with(Items.silicon, 35, Items.plastanium, 15, HIItems.nanocore, 10, HIItems.chromium, 15));
            speed = 1f;
        }};
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
                    new UnitType[]{UnitTypes.omura, HIUnitTypes.mosasaur},
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
            ammo(Items.graphite, new MissileBulletType(3.6f, 30f){{
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
            }}, Items.pyratite, new MissileBulletType(3.6f, 18f){{
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
            }}, Items.blastCompound, new MissileBulletType(3.6f, 16f){{
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
                        frontColor = Color.valueOf("e3e3e3");
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
                        frontColor = Color.valueOf("e3e3e3");
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
                        frontColor = Color.valueOf("e3e3e3");
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
        dragonBreath = new ItemTurret("dragon-breath"){{
            requirements(Category.turret, with(Items.copper, 60, Items.graphite, 40, Items.silicon, 25, Items.titanium, 30));
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
        spike = new ItemTurret("spike"){{
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
            ammo(
                    Items.titanium, new BasicBulletType(5, 24){{
                        width = 8f;
                        height = 25f;
                        hitColor = backColor = lightColor = trailColor = Items.titanium.color.cpy().lerp(Color.white, 0.1f);
                        frontColor = backColor.cpy().lerp(Color.white, 0.35f);
                        hitEffect = HIFx.crossBlast(hitColor, height + width);
                        shootEffect = despawnEffect = HIFx.square(hitColor, 20f, 3, 12f, 2f);
                        ammoMultiplier = 8;
                        pierceArmor = true;
                    }},
                    Items.plastanium, new BasicBulletType(5, 26){{
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
                    }},
                    Items.thorium, new BasicBulletType(5, 18){{
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
                    }},
                    Items.pyratite, new BasicBulletType(5, 18){{
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
                    }},
                    Items.blastCompound, new BasicBulletType(5, 22){{
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
                    HIItems.highEnergyFabric, new BasicBulletType(8f, 65f){{
                        lifetime = 48f;
                        width = 8f;
                        height = 42f;
                        shrinkX = 0;
                        trailWidth = 1.7f;
                        trailLength = 9;
                        trailColor = backColor = hitColor = lightColor = lightningColor = HIPal.highEnergyYellow;
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
                lightningColor = hitColor = HIPal.lightSkyBack;
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
            shootEffect = HIFx.ellipse(30, 30, 15, HIPal.iceBlue);
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
                color = HIPal.iceBlue;
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
                    sparkColor = HIPal.iceBlue;
                    waveRad = smokeSize = smokeSizeBase = 0f;
                    smokes = 0;
                    sparks = 5;
                    sparkRad = 4 * 8;
                    sparkLen = 5f;
                    sparkStroke = 2f;
                }}, new Effect(60, e -> Drawn.drawSnow(e.x, e.y, 2 * 8 * e.foutpow(), 0, HIPal.iceBlue)));
                trailInterval = 0.5f;
                trailEffect = new Effect(120, e -> {
                    Draw.color(HIPal.iceBlue);
                    Fill.circle(e.x, e.y, 3 * e.foutpow());
                });
                trailLength = 16;
                trailWidth = 3;
                trailColor = HIPal.iceBlue;
                buildingDamageMultiplier = 0.5f;
            }
                @Override
                public void draw(Bullet b) {
                    super.draw(b);
                    Draw.color(HIPal.iceBlue);
                    Drawf.tri(b.x, b.y, 5, 12, b.rotation());
                    Drawf.tri(b.x, b.y, 5, 5, b.rotation() - 180);
                    Lines.stroke(1, HIPal.iceBlueDark);
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
                trailColor = HIPal.iceBlue;
                trailLength = 8;
                trailWidth = 5;
                trailEffect = new Effect(40, e -> Drawn.drawSnow(e.x, e.y, 12 * e.fout(), 360 * e.fin(), HIPal.iceBlue));
                trailInterval = 3;
                fragBullets = 4;
                fragBullet = frostFragBullrt;
                status = StatusEffects.freezing;
                hitEffect = despawnEffect = new ExplosionEffect(){{
                    lifetime = 40f;
                    waveStroke = 5f;
                    waveLife = 8f;
                    waveColor = HIPal.iceBlueDark;
                    sparkColor = HIPal.iceBlue;
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
                    Draw.color(HIPal.iceBlue);
                    Drawf.tri(b.x, b.y, 15, 18, b.rotation());
                    Drawf.tri(b.x, b.y, 15, 6, b.rotation() - 180);
                    Lines.stroke(1, HIPal.iceBlueDark);
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
                        if(!Vars.headless && b.timer(3, 3)){
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
            armor = 10;
            unitSort = UnitSorts.strongest;
            squareSprite = false;
            consumePower(16);
            consumeLiquid(HILiquids.nanofluid, 12f / 60f);
        }};
        spark = new MultiBulletTurret("spark"){{
            requirements(Category.turret, with(Items.graphite, 120, Items.silicon, 160, Items.titanium, 140, Items.thorium, 80));
            ammo(
                    Items.blastCompound, new BulletType[]{
                            new FireWorkBulletType(100, 4.5f, name("mb-fireworks"), Color.valueOf("ea8878"), 6 * 8),
                            new FireWorkBulletType(100, 4.5f, Color.valueOf("5cfad5")),
                            new FireWorkBulletType(100, 4.5f){{
                                colorful = true;
                                fire = new ColorFireBulletType(false, 3f, 60){{
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
                            new FireWorkBulletType(88, 4.5f, name("mb-fireworks"), Color.valueOf("ea8878"), 6 * 8){{
                                status = StatusEffects.none;
                            }},
                            new FireWorkBulletType(88, 4.5f, Color.valueOf("5cfad5")){{
                                status = StatusEffects.none;
                            }},
                            new FireWorkBulletType(88, 4.5f, Items.plastanium.color){{
                                fire = new ColorFireBulletType(true, 5f, 60){{
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
            range = 270f;
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
            ammo(
                    Items.blastCompound, new BulletType[]{
                            new FireWorkBulletType(120f, 5f, name("mb-fireworks"), Color.valueOf("FF8097"), 6 * 8){{
                                outline = true;
                                trailInterval = 20f;
                                trailEffect = new ExplosionEffect(){{
                                    lifetime = 60f;
                                    waveStroke = 5f;
                                    waveLife = 8f;
                                    waveColor = Color.white;
                                    sparkColor = Pal.lightOrange;
                                    smokeColor = Pal.darkerGray;
                                    waveRad = 0f;
                                    smokeSize = 4f;
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
                                fire = new ColorFireBulletType(false, 2.3f, 60){{
                                    stopFrom = 0.55f;
                                    stopTo = 0.55f;
                                    rotSpeed = 666;
                                }};
                                num = 15;
                            }},
                            new BulletType(){{
                                ammoMultiplier = 1;
                                damage = 0f;
                                speed = 0f;
                                lifetime = 0f;
                                fragBullet = new FireWorkBulletType(150, 6.7f, name("mb-fireworks"), Color.valueOf("FFD080"), 12 * 8){{
                                    outline = true;
                                    trailWidth = 3.5f;
                                    trailLength = 10;
                                    trailInterval = 0;
                                    width = 22;
                                    height = 22;
                                    fire = new ColorFireBulletType(false, 3.6f, 60){{
                                        stopFrom = 0.7f;
                                        stopTo = 0.7f;
                                        rotSpeed = 666;
                                        hittable = true;
                                    }};
                                    textFire = new SpriteBulletType(name("fire-fireworks1"));
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
                            new FireWorkBulletType(120f, 5f, name("mb-fireworks"), Color.valueOf("FFF980"), 6 * 8){{
                                outline = true;
                                trailInterval = 0f;
                                trailWidth = 2f;
                                trailLength = 10;
                                weaveMag = 8f;
                                weaveScale = 2f;
                                fire = new ColorFireBulletType(false, 2.3f, 60){{
                                    stopFrom = 0.55f;
                                    stopTo = 0.55f;
                                    rotSpeed = 666f;
                                    hittable = true;
                                }};
                                textFire = new SpriteBulletType(name("fire-fireworks2"));
                                status = StatusEffects.none;
                                num = 18;
                            }},
                            new FireWorkBulletType(120f, 5f, name("mb-fireworks"), Color.valueOf("80FF9D"), 6 * 8){{
                                outline = true;
                                trailInterval = 0f;
                                trailWidth = 2.4f;
                                trailLength = 10;
                                homingPower = 1f;
                                homingRange = 32 * 8;
                                width = 10f;
                                height = 10f;
                                status = StatusEffects.electrified;
                                fire = new ColorFireBulletType(false, 2.3f, 60){{
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
                                fragBullet = new FireWorkBulletType(110f, 6f, name("mb-fireworks"), Color.valueOf("80B5FF"), 8 * 8){{
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
                                    fire = new ColorFireBulletType(false, 2.8f, 60f){{
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
                                    fire = new ColorFireBulletType(true, 4, 60){{
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
                            new FireWorkBulletType(125f, 5f, name("mb-fireworks"), Color.valueOf("FF7DF4"), 10 * 8){{
                                outline = true;
                                trailInterval = 0;
                                trailWidth = 2.4f;
                                trailLength = 10;
                                status = StatusEffects.none;
                                textFire = new SpriteBulletType(name("fire-fireworks3"), 128f, 128f);
                                fire = new ColorFireBulletType(false, 3f, 60f){{
                                    stopFrom = 0.6f;
                                    stopTo = 0.6f;
                                    rotSpeed = 666f;
                                    hittable = true;
                                }};
                            }
                    }
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

        //TODO A placeholder that indicates that more content will be added in the future later. Not researchable.
        tbd = new Block("tbd"){{
            requirements(Category.defense, BuildVisibility.sandboxOnly, with());
            unlocked = false;
        }
            @Override
            public void unlock() {
                //haha no
            }

            @Override
            public boolean unlocked() {
                //haha no
                return false;
            }

            @Override
            public boolean unlockedNow() {
                //haha no
                return false;
            }

            @Override
            public boolean unlockedNowHost() {
                //haha no
                return false;
            }
        };
    }
}
