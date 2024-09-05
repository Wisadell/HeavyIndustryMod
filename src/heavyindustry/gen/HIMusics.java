package heavyindustry.gen;

import arc.*;
import arc.audio.*;
import arc.struct.*;
import arc.util.*;
import mindustry.*;
import mindustry.gen.*;
import mindustry.game.EventType.*;

/** Manages music, including vanilla and custom tracks. */
public class HIMusics {
    private static final ObjectMap<String, Seq<Music>> musicSets = new ObjectMap<>();

    public static Music
            //HeavyIndustry launch
            orbital,
            //Kepler music
            glLand,
            //Ambient
            chained, darkPurity, wisdom, space, sundown,
            //Dark
            fragile, solidFire, soredLuna,
            //Boss
            buryAlive, chaoticFlames, liquefy, piercingLine;

    public static void load() {
        initializeMusics();
        initializeMusicSets();
        setupEventHandlers();
    }

    /** Initializes individual music tracks. */
    private static void initializeMusics() {
        // HeavyIndustry
        orbital = loadMusic("orbital");

        // Kepler
        glLand = loadMusic("landings/kepler-land");

        // Override
        Musics.launch = orbital;
        Musics.land = glLand;

        // Load Kepler musics
        String[] ambientTracks = {"chained", "darkPurity", "wisdom", "space", "sundown"};
        String[] darkTracks = {"fragile", "solidFire", "soredLuna"};
        String[] bossTracks = {"buryAlive", "chaoticFlames", "liquefy", "piercingLine"};

        loadMusicSet("ambient/", ambientTracks);
        loadMusicSet("dark/", darkTracks);
        loadMusicSet("boss/", bossTracks);
    }

    /**
     * Loads a set of music tracks from a specified base path.
     * @param basePath Base path for the music files.
     * @param trackNames Array of track names to load.
     */
    public static void loadMusicSet(String basePath, String[] trackNames) {
        for (String track : trackNames) {
            try {
                Music music = loadMusic(basePath + track);
                HIMusics.class.getField(track).set(null, music);
            } catch (Exception e) {
                Log.err("Failed to load music: " + track, e);
            }
        }
    }

    /** Initializes music sets for different game scenarios. */
    private static void initializeMusicSets() {
        musicSets.put("vanillaAmbient", new Seq<>(Vars.control.sound.ambientMusic));
        musicSets.put("vanillaDark", new Seq<>(Vars.control.sound.darkMusic));
        musicSets.put("vanillaBoss", new Seq<>(Vars.control.sound.bossMusic));

        musicSets.put("keplerAmbient", Seq.with(chained, darkPurity, wisdom, space, sundown));
        musicSets.put("keplerDark", Seq.with(fragile, solidFire, soredLuna));
        musicSets.put("keplerBoss", Seq.with(buryAlive, chaoticFlames, liquefy, piercingLine));
    }

    /** Mixes vanilla and mod music sets. */
    private static void setupEventHandlers() {
        Events.on(WorldLoadEvent.class, e -> {
            mixMusicSets("vanillaAmbient", "keplerAmbient", Vars.control.sound.ambientMusic);
            mixMusicSets("vanillaDark", "keplerDark", Vars.control.sound.darkMusic);
            mixMusicSets("vanillaBoss", "keplerBoss", Vars.control.sound.bossMusic);
        });
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

    /** Loads a music file from the game's asset tree. */
    public static Music loadMusic(String name) {
        return Vars.tree.loadMusic(name);
    }
}
