package io.github.shardion.joinrandom.minigames;

import javax.annotation.Nullable;
import java.util.*;

public enum Minigames {
    WOOLWARS("Wool Wars", "/lobby woolwars"),
    SKYBLOCK("SkyBlock", "/play sb", true),
    BEDWARS("BedWars", "/lobby bedwars"),
    SKYWARS("SkyWars", "/lobby skywars"),
    MURDERMYSTERY("Murder Mystery", "/lobby murdermystery"),
    BOWSPLEEF("Bow Spleef", "/lobby tntgames"),
    PVPRUN("PVP Run", "/lobby tntgames"),
    TNTRUN("TNT Run", "/lobby tntgames"),
    TNTTAG("TNT Tag", "/lobby tntgames"),
    TNTWIZARDS("TNT Wizards", "/lobby tntgames"),
    ARCADEGAMES("Arcade Games", "/lobby arcade"), // one entry because we can't reliably check which games are featured and non-featured games are always dead
    BUILDBATTLE("Build Battle", "/lobby buildbattle"),
    DUELS("Duels", "/lobby duels", true),
    UHCCHAMPIONS("UHC Champions", "/lobby uhc", true),
    SPEEDUHC("Speed UHC", "/lobby uhc", true),
    VAMPIREZ("VampireZ", "/lobby classic"),
    QUAKECRAFT("Quakecraft", "/lobby classic"),
    PAINTBALLWARFARE("Paintball Warfare", "/lobby classic"),
    ARENABRAWL("Arena Brawl", "/lobby classic"),
    THEWALLS("The Walls", "/lobby classic"),
    TURBOKARTRACERS("Turbo Kart Racers", "/lobby classic"),
    COPSANDCRIMS("Cops And Crims", "/lobby copsandcrims"),
    MEGAWALLS("Mega Walls", "/lobby megawalls"),
    THEHYPIXELPIT("The Hypixel Pit", "/play pit"),
    SMASHHEROS("Smash Heros", "/lobby smash"),
    WARLORDS("Warlords", "/lobby warlords"),
    BLITZSG("Blitz SG", "/lobby blitz"),
    ;
    public static final Map<String, Minigames> MINIGAME_NAME_MAP = new HashMap<>();

    private final String displayName;
    private final String lobbyCommand;
    private final boolean excludeFromDefaultConfig;

    Minigames(String displayName, String lobbyCommand) {
        this.displayName = displayName;
        this.lobbyCommand = lobbyCommand;
        this.excludeFromDefaultConfig = false;
    }

    Minigames(String displayName, String lobbyCommand, boolean excludeFromDefaultConfig) {
        this.displayName = displayName;
        this.lobbyCommand = lobbyCommand;
        this.excludeFromDefaultConfig = excludeFromDefaultConfig;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getLobbyCommand() {
        return lobbyCommand;
    }

    public boolean isExcludedFromDefaultConfig() {
        return excludeFromDefaultConfig;
    }

    public static @Nullable Minigames getMinigame(String name) {
        if (MINIGAME_NAME_MAP.isEmpty()) {
            for (Minigames minigame : Minigames.values()) {
                MINIGAME_NAME_MAP.put(minigame.name().toLowerCase(Locale.ROOT), minigame);
            }
        }
        if (MINIGAME_NAME_MAP.containsKey(name)) {
            return MINIGAME_NAME_MAP.get(name);
        }
        return null;
    }

    public static String[] generateDefaultConfig() {
        List<String> configLines = new ArrayList<>();
        for (Minigames minigame : Minigames.values()) {
            if (!minigame.isExcludedFromDefaultConfig()) {
                configLines.add(minigame.name().toLowerCase(Locale.ROOT) + ":1");
            }
        }
        return configLines.toArray(new String[0]);
    }
}
