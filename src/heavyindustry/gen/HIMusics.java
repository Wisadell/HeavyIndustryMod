package heavyindustry.gen;

import arc.audio.*;
import arc.struct.*;
import arc.util.*;

import static mindustry.Vars.*;
import static mindustry.gen.Musics.*;

/**
 * Manages music, including vanilla and custom tracks.
 * @author Wisadell
 */
public class HIMusics {
    private static final ObjectMap<String, Seq<Music>> musicSets = new ObjectMap<>();

    public static Music
            MSRStoryteller = new Music(),
            MSRFireEmbrace = new Music(),
            MSRTheOpening = new Music(),
            MSRFoundingStone = new Music(),
            MSRSilentTales = new Music();

    public static void load() {
        MSRStoryteller = tree.loadMusic("MSRStoryteller");
        MSRSilentTales = tree.loadMusic("MSRSilentTales");

        String[] ambientTracks = {"MSRFoundingStone"};
        String[] darkTracks = {"MSRFireEmbrace", "MSRTheOpening"};

        loadMusicSet("", ambientTracks);
        loadMusicSet("", darkTracks);

        musicSets.put("vanillaAmbient", new Seq<>(control.sound.ambientMusic));
        musicSets.put("vanillaDark", new Seq<>(control.sound.darkMusic));

        musicSets.put("modAmbient", Seq.with(MSRFoundingStone));
        musicSets.put("modDark", Seq.with(MSRFireEmbrace, MSRTheOpening));

        mixMusicSets("vanillaAmbient", "modAmbient", control.sound.ambientMusic);
        mixMusicSets("vanillaDark", "modDark", control.sound.darkMusic);

        menu = MSRStoryteller;
        launch = MSRSilentTales;
    }

    /**
     * Loads a set of music tracks from a specified base path.
     * @param basePath Base path for the music files.
     * @param trackNames Array of track names to load.
     */
    public static void loadMusicSet(String basePath, String[] trackNames) {
        for (String track : trackNames) {
            try {
                Music music = tree.loadMusic(basePath + track);
                HIMusics.class.getField(track).set(null, music);
            } catch (Exception e) {
                Log.err("Failed to load music: " + track, e);
            }
        }
    }

    /**
     * Mixes two music sets and assigns the result to a target set.
     * @param target Target sequence to store the mixed music.
     */
    public static void mixMusicSets(String vanillaSetName, String modSetName, Seq<Music> target) {
        Seq<Music> vanillaSet = musicSets.get(vanillaSetName);
        Seq<Music> modSet = musicSets.get(modSetName);
        if (vanillaSet != null && modSet != null) {
            target.clear();
            target.addAll(vanillaSet);
            target.addAll(modSet);
        }
    }

    /**
     * Sets a music set to a target sequence.
     * @param setName Name of the music set to use.
     * @param target Target sequence to update.
     */
    public static void setMusicSet(String setName, Seq<Music> target) {
        Seq<Music> set = musicSets.get(setName);
        if (set != null) {
            target.set(set);
        }
    }
}
