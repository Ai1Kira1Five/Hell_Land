package com.Arteman.HellLand.handler;

import com.Arteman.HellLand.HellLand;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ConfigurationHandler {
    public static Configuration config;
    public static boolean enableSwords = true;
    public static boolean enableTools = true;
    public static boolean allCanPickup = true;
    public static boolean add_branding = false;

    public static void init(File configFile){
        if (config == null){
            config = new Configuration(configFile);
            loadConfiguration();
        }

    }

    private static void loadConfiguration(){
        enableSwords = config.getBoolean("enableSwords",Configuration.CATEGORY_GENERAL,  enableSwords,   "Enable swords stuff");
        enableTools = config.getBoolean("enableTools", Configuration.CATEGORY_GENERAL, enableTools,  "Enable tool stuff");
        allCanPickup= config.getBoolean("allCanPickup", Configuration.CATEGORY_GENERAL, allCanPickup,  "Enable pickUp stuff not owner");
        
        if(config.hasChanged()){
            config.save();
        }

    }

    @SubscribeEvent
    public void OnConfigChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event){
        if(event.modID.equalsIgnoreCase(HellLand.MODID)){
            loadConfiguration();
        }
    }


}
