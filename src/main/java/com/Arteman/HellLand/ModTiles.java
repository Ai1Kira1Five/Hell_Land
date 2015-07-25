package com.Arteman.HellLand;

import com.Arteman.HellLand.tileentity.TileEntityCrystallOven;
import com.Arteman.HellLand.tileentity.TileEntityHellOven;
import com.Arteman.HellLand.tileentity.TileEntitySoulCrystallizer;
import com.Arteman.HellLand.tileentity.TileEntityWire;

import cpw.mods.fml.common.registry.GameRegistry;

public class ModTiles 
{
	public final static void init()
	{
		GameRegistry.registerTileEntity(TileEntityHellOven.class, "HellOven");
    	GameRegistry.registerTileEntity(TileEntityWire.class, HellLand.MODID + ":" + "WireOne");
    	GameRegistry.registerTileEntity(TileEntitySoulCrystallizer.class, "SoulCrystallizer");
    	GameRegistry.registerTileEntity(TileEntityCrystallOven.class, "Crystall Oven");
	}
}
