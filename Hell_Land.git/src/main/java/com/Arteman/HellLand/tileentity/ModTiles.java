package com.Arteman.HellLand.tileentity;

import com.Arteman.HellLand.HellLand;

import cpw.mods.fml.common.registry.GameRegistry;

public class ModTiles 
{
	public final static void init()
	{
		GameRegistry.registerTileEntity(TileEntityHellOven.class, "HellOven");
    	GameRegistry.registerTileEntity(TileEntityWire.class, HellLand.MODID + ":" + "WireOne");
    	GameRegistry.registerTileEntity(TileEntitySoulCrystallizer.class, "SoulCrystallizer");
	}
}
