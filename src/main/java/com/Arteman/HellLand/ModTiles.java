package com.Arteman.HellLand;

import com.Arteman.HellLand.tileentity.TileEntityCrystalOven;
import com.Arteman.HellLand.tileentity.TileEntityHellOven;
import com.Arteman.HellLand.tileentity.TileEntitySoulCrystallizer;
import com.Arteman.HellLand.tileentity.TileEntityWire;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModTiles {
    public final static void init() {
        GameRegistry.registerTileEntity(TileEntityHellOven.class, HellLandCore.MODID + ":hellOven");
        GameRegistry.registerTileEntity(TileEntityWire.class, HellLandCore.MODID + ":wireOne");
        GameRegistry.registerTileEntity(TileEntitySoulCrystallizer.class, HellLandCore.MODID + ":soulCrystallizer");
        GameRegistry.registerTileEntity(TileEntityCrystalOven.class, HellLandCore.MODID + ":crystalOven");
    }
}
