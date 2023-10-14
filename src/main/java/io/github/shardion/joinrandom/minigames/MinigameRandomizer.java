package io.github.shardion.joinrandom.minigames;

import io.github.shardion.joinrandom.JoinRandom;
import io.github.shardion.joinrandom.minigames.Minigames;
import joptsimple.util.KeyValuePair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MinigameRandomizer {
    private static final Random minigameRandom = new Random();

    public static Minigames getRandomMinigame() {
        ArrayList<Minigames> weightedMinigamesList = new ArrayList<>();
        Map<Minigames, Integer> minigamesMap = JoinRandom.CONFIG.getMinigameWeightMap();
        if (!minigamesMap.isEmpty()) {
            minigamesMap.forEach(
                    (minigame, weight) -> {
                        for (int i = 0; i < weight; i++) {
                            weightedMinigamesList.add(minigame);
                        }
                    }
            );
            return weightedMinigamesList.get(minigameRandom.nextInt(weightedMinigamesList.size()));
        }
        return Minigames.BEDWARS;
    }
}
