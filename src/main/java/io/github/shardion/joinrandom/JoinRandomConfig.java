package io.github.shardion.joinrandom;

import io.github.shardion.joinrandom.minigames.Minigames;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class JoinRandomConfig {
    private final Configuration config;

    private Map<Minigames, Integer> cachedMinigameWeightMap = null;

    public JoinRandomConfig(Configuration config) {
        this.config = config;
        getMinigameWeightMap();
        config.save();
    }

    public Map<Minigames, Integer> getMinigameWeightMap() {
        if (cachedMinigameWeightMap == null || cachedMinigameWeightMap.isEmpty()) {
            cachedMinigameWeightMap = parseMinigameWeightStringMap(this.config.getStringList(
                    "Minigames",
                    "General",
                    Minigames.generateDefaultConfig(),
                    "The list of minigames to choose from, and their weights, separated by the : character."
            ));
        }
        return cachedMinigameWeightMap;
    }

    private void invalidateCachedMinigameWeightMap() {
        cachedMinigameWeightMap = null;
    }

    private Map<Minigames, Integer> parseMinigameWeightStringMap(String[] minigameWeightStringMap) {
        HashMap<Minigames, Integer> minigameWeightMap = new HashMap<>(minigameWeightStringMap.length);
        for (String minigameWeightString : minigameWeightStringMap) {
            String[] splitMinigameString = minigameWeightString.split(":", 2);
            @Nullable Minigames minigame = Minigames.getMinigame(splitMinigameString[0]);
            if (minigame == null) {
                JoinRandom.LOGGER.error("Error while parsing config: " + splitMinigameString[0] + " is not a valid minigame!");
                continue;
            }
            if (splitMinigameString.length < 2) {
                minigameWeightMap.put(minigame, 1);
            } else {
                try {
                    int minigameWeight = Integer.parseInt(splitMinigameString[1]);
                    minigameWeightMap.put(minigame, minigameWeight);
                } catch (NumberFormatException exception) {
                    JoinRandom.LOGGER.error("Error while parsing config: " + splitMinigameString[1] + " is not a valid integer!");
                    minigameWeightMap.put(minigame, 1);
                }
            }
        }
        return minigameWeightMap;
    }

    @SubscribeEvent
    public void onConfigurationChangeEvent(ConfigChangedEvent.OnConfigChangedEvent event) {
        this.config.save();
        if (event.getModID().equalsIgnoreCase("joinrandom")) {
            invalidateCachedMinigameWeightMap();
        }
    }
}
