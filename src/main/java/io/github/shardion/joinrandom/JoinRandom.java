package io.github.shardion.joinrandom;

import io.github.shardion.joinrandom.keybinds.KeybindHandler;
import io.github.shardion.joinrandom.keybinds.Keybinds;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

import java.io.File;

@Mod(modid = "joinrandom", name = "JoinRandom", clientSideOnly = true, useMetadata = true)
public class JoinRandom {
    public static Logger LOGGER;
    public static JoinRandomConfig CONFIG;

    @Mod.EventHandler
    public void init(FMLPreInitializationEvent event) {
        Keybinds.register();
        LOGGER = event.getModLog();
        File configPath = new File(event.getModConfigurationDirectory().toString() + File.separator + "JoinRandom.cfg");
        CONFIG = new JoinRandomConfig(new Configuration(configPath));
    }
    @Mod.EventHandler
    public void init(final FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new KeybindHandler());
    }
}