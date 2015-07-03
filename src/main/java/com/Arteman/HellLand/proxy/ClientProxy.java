package com.Arteman.HellLand.proxy;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;

import com.Arteman.HellLand.renderer.TileEntityRenderWire;
import com.Arteman.HellLand.tileentity.TileEntityHellOven;
import com.Arteman.HellLand.tileentity.TileEntityWire;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy
{
	public static int renderId = RenderingRegistry.getNextAvailableRenderId();
	
	public void registerRenderThings()
	{
		
	}
	
	public void registerTileEntitySpecialRender()
	{
		
	}
	
	public void registerProxies()
	{
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityWire.class, new TileEntityRenderWire());
	}
}
