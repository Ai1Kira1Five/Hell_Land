package com.Arteman.HellLand.proxy;

import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;

import com.Arteman.HellLand.ModBlocks;
import com.Arteman.HellLand.renderer.RenderCrystal;
import com.Arteman.HellLand.renderer.RenderItemCrystal;
import com.Arteman.HellLand.renderer.TileEntityRenderWire;
import com.Arteman.HellLand.tileentity.TileEntityCrystal;
import com.Arteman.HellLand.tileentity.TileEntityWire;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {
    public static int renderId = RenderingRegistry.getNextAvailableRenderId();

    public void registerRenderThings() {

    }

    public void registerTileEntitySpecialRender() {

    }

    public void registerProxies() {
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityWire.class, new TileEntityRenderWire());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCrystal.class, new RenderCrystal());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.Crystal), new RenderItemCrystal());
    }
}
