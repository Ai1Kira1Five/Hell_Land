package com.Arteman.HellLand.proxy;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;

import com.Arteman.HellLand.renderer.TileEntityRenderWire;
import com.Arteman.HellLand.tileentity.TileEntityHellOven;
import com.Arteman.HellLand.tileentity.TileEntityWire;

import cpw.mods.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy
{
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
