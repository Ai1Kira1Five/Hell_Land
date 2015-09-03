package com.Arteman.HellLand;

import com.Arteman.HellLand.tileentity.*;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModTiles {
    public final static void init() {
        GameRegistry.registerTileEntity(hellOvenTE.class, HellLand.MODID + ":hellOven");
        GameRegistry.registerTileEntity(TileEntityWire.class, HellLand.MODID + ":wireOne");
        GameRegistry.registerTileEntity(TileEntitySoulCrystallizer.class, HellLand.MODID + ":soulCrystallizer");
        GameRegistry.registerTileEntity(crystalOvenTE.class, HellLand.MODID + ":crystalOven");
        GameRegistry.registerTileEntity(alchemicalTableTE.class, HellLand.MODID+":alchemicalTable");
    }
}
