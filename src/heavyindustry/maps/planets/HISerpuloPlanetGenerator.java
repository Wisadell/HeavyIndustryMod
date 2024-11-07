package heavyindustry.maps.planets;

import arc.struct.*;
import mindustry.content.*;
import mindustry.ctype.*;
import mindustry.game.*;
import mindustry.maps.planet.*;
import mindustry.type.*;
import mindustry.type.Weather.*;
import mindustry.world.*;
import heavyindustry.content.*;

import static mindustry.Vars.*;

public class HISerpuloPlanetGenerator extends SerpuloPlanetGenerator {
    @Override
    public void addWeather(Sector sector, Rules rules){
        //apply weather based on terrain
        ObjectIntMap<Block> floorc = new ObjectIntMap<>();
        ObjectSet<UnlockableContent> content = new ObjectSet<>();

        for(Tile tile : world.tiles){
            if(world.getDarkness(tile.x, tile.y) >= 3){
                continue;
            }

            Liquid liquid = tile.floor().liquidDrop;
            if(tile.floor().itemDrop != null) content.add(tile.floor().itemDrop);
            if(tile.overlay().itemDrop != null) content.add(tile.overlay().itemDrop);
            if(liquid != null) content.add(liquid);

            if(!tile.block().isStatic()){
                floorc.increment(tile.floor());
                if(tile.overlay() != Blocks.air){
                    floorc.increment(tile.overlay());
                }
            }
        }

        //sort counts in descending order
        Seq<ObjectIntMap.Entry<Block>> entries = floorc.entries().toArray();
        entries.sort(e -> -e.value);
        //remove all blocks occurring < 30 times - unimportant
        entries.removeAll(e -> e.value < 30);

        Block[] floors = new Block[entries.size];
        for(int i = 0; i < entries.size; i++){
            floors[i] = entries.get(i).key;
        }

        //bad contains() code, but will likely never be fixed
        boolean hasSnow = floors.length > 0 && (floors[0].name.contains("ice") || floors[0].name.contains("snow"));
        boolean hasRain = floors.length > 0 && !hasSnow && content.contains(Liquids.water) && !floors[0].name.contains("sand");
        boolean hasDesert = floors.length > 0 && !hasSnow && !hasRain && floors[0] == Blocks.sand;
        boolean hasSpores = floors.length > 0 && (floors[0].name.contains("spore") || floors[0].name.contains("moss") || floors[0].name.contains("tainted"));

        if (!hasSnow && !hasDesert && !hasSpores) {
            rules.weather.add(new WeatherEntry(HIWeathers.wind));
        }

        if(hasSnow){
            rules.weather.add(new WeatherEntry(Weathers.snow));
            rules.weather.add(new WeatherEntry(HIWeathers.blizzard));
            rules.weather.add(new WeatherEntry(HIWeathers.hailStone));
        }

        if(hasRain){
            rules.weather.add(new WeatherEntry(Weathers.rain));
            rules.weather.add(new WeatherEntry(Weathers.fog));
        }

        if(hasDesert){
            rules.weather.add(new WeatherEntry(Weathers.sandstorm));
        }

        if(hasSpores){
            rules.weather.add(new WeatherEntry(Weathers.sporestorm));
        }
    }
}
