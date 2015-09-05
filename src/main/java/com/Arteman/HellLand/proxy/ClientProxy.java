package com.Arteman.HellLand.proxy;

import com.Arteman.HellLand.ModBlocks;
import com.Arteman.HellLand.renderer.RenderCrystal;
import com.Arteman.HellLand.renderer.RenderItemCrystal;
import com.Arteman.HellLand.renderer.TileEntityRenderWire;
import com.Arteman.HellLand.renderer.blockRenderer;
import com.Arteman.HellLand.tileentity.TileEntityCrystalSpawn;
import com.Arteman.HellLand.tileentity.TileEntityWire;
import com.Arteman.HellLand.tileentity.tileEntityWithInventory;
import com.Arteman.HellLand.utils.TEBlockHell;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy {
    public static int renderId = RenderingRegistry.getNextAvailableRenderId();

    public void registerRenderThings() {

    }

    public void registerTileEntitySpecialRender() {

    }

    public void registerProxies() {

        TEBlockHell.renderId = RenderingRegistry.getNextAvailableRenderId();
        blockRenderer machRen = new blockRenderer();
        RenderingRegistry.registerBlockHandler(machRen);
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.crystalOven), machRen);
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.hellOven), machRen);
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.alchemicalTable), machRen);
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.soulCrystallizer), machRen);
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.mMixer), machRen);

        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityWire.class, new TileEntityRenderWire());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCrystalSpawn.class, new RenderCrystal());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.CrystalSpawn), new RenderItemCrystal());
    }
}
