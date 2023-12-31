package io.github.shardion.joinrandom.keybinds;

import io.github.shardion.joinrandom.minigames.MinigameRandomizer;
import io.github.shardion.joinrandom.minigames.Minigames;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.relauncher.FMLInjectionData;

import java.io.File;

public class KeybindHandler {
    private static final int LOCKOUT_DURATION = 20;

    private static int lockTicks;
    private static boolean held;

    @SubscribeEvent
    public void onInput(InputEvent event) {
        if (Keybinds.JOIN_RANDOM_MINIGAME.isPressed()) {
            if (lockTicks <= 0 && !held) {
                Minigames randomMinigame = MinigameRandomizer.getRandomMinigame();
                Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new ChatComponentText("§3JoinRandom:§r §7Joining lobby for§r §l§n" + randomMinigame.getDisplayName() + "§r§7.§r"));
                Minecraft.getMinecraft().thePlayer.sendChatMessage(randomMinigame.getLobbyCommand());
            }
            lockTicks = LOCKOUT_DURATION;
            held = true;
        } else {
            held = false;
        }
    }

    @SubscribeEvent
    public void onTick(ClientTickEvent event) {
        if (!Keybinds.JOIN_RANDOM_MINIGAME.isPressed()) {
            if (lockTicks > 0) {
                lockTicks--;
            }
        } else {
            lockTicks = LOCKOUT_DURATION;
        }
    }
}
