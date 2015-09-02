package com.Arteman.HellLand.proxy;

import com.Arteman.HellLand.renderer.blockRenderer;

import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;

import com.Arteman.HellLand.ModBlocks;
import com.Arteman.HellLand.renderer.RenderCrystal;
import com.Arteman.HellLand.renderer.RenderItemCrystal;
import com.Arteman.HellLand.renderer.TileEntityRenderWire;
import com.Arteman.HellLand.tileentity.TileEntityCrystalSpawn;
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
        RenderingRegistry.registerBlockHandler(new blockRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityWire.class, new TileEntityRenderWire());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCrystalSpawn.class, new RenderCrystal());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.CrystalSpawn), new RenderItemCrystal());
    }
}
