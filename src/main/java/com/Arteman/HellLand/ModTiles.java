package com.Arteman.HellLand;

import com.Arteman.HellLand.tileentity.TileEntityCrystalOven;
import com.Arteman.HellLand.tileentity.TileEntityHellOven;
import com.Arteman.HellLand.tileentity.TileEntitySoulCrystallizer;
import com.Arteman.HellLand.tileentity.TileEntityWire;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModTiles {
    public final static void init() {
        GameRegistry.registerTileEntity(TileEntityHellOven.class, HellLand.MODID + ":hellOven");
        GameRegistry.registerTileEntity(TileEntityWire.class, HellLand.MODID + ":wireOne");
        GameRegistry.registerTileEntity(TileEntitySoulCrystallizer.class, HellLand.MODID + ":soulCrystallizer");
        GameRegistry.registerTileEntity(TileEntityCrystalOven.class, HellLand.MODID + ":crystalOven");
    }
}
