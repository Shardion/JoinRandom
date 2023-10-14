package io.github.shardion.joinrandom.keybinds;

import org.lwjgl.input.Keyboard;
import net.minecraft.client.settings.KeyBinding;
import static net.minecraftforge.fml.client.registry.ClientRegistry.registerKeyBinding;

public class Keybinds {
    public static final KeyBinding JOIN_RANDOM_MINIGAME = new KeyBinding("Join Random Minigame", Keyboard.KEY_J, "JoinRandom");
    public static void register() {
        registerKeyBinding(JOIN_RANDOM_MINIGAME);
    }
}