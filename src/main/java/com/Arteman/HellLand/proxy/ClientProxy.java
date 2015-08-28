package com.Arteman.HellLand.proxy;

import com.Arteman.HellLand.ModBlocks;
import com.Arteman.HellLand.renderer.TileEntityRenderWire;
import com.Arteman.HellLand.tileentity.TileEntityWire;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {
    public static int renderId = RenderingRegistry.getNextAvailableRenderId();

    @Override
    public void registerRenders() 
    {
    	ModBlocks.registerRenders();
    }

    public void registerTileEntitySpecialRender() {

    }

    public void registerProxies() 
    {
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityWire.class, new TileEntityRenderWire());
    }
}
