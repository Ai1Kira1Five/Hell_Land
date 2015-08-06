package com.Arteman.HellLand.gui;

import com.Arteman.HellLand.HellLandCore;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;

import com.Arteman.HellLand.handler.ConfigurationHandler;

import cpw.mods.fml.client.config.GuiConfig;

public class ModGuiConfig extends GuiConfig{

	public ModGuiConfig(GuiScreen parentScreen){
		super(parentScreen, 
				new ConfigElement(ConfigurationHandler.config.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(),
				HellLandCore.MODID,
				false,
				false, 
				GuiConfig.getAbridgedConfigPath(ConfigurationHandler.config.toString()));
	}
	
}
