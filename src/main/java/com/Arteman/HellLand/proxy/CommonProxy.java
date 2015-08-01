package com.Arteman.HellLand.proxy;

import com.Arteman.HellLand.HellCommonEventHandler;
import com.Arteman.HellLand.utils.network.HellMessagePipeline;
import com.Arteman.HellLand.utils.network.MSGExplosion;

import net.minecraftforge.common.MinecraftForge;

public class CommonProxy 
{
	public void registerEventHandlers()
	{
		MinecraftForge.EVENT_BUS.register(new HellCommonEventHandler());
	}
	
	public void registerPackets(HellMessagePipeline pipeline)
	{
		pipeline.registerPacket(MSGExplosion.class);
	}
	
	public void registerRenderThings()
	{
	 
	}
	
	public void registerTileEntitySpecialRender()
	{
	 
	}

	public void registerProxies() 
	{

	}
}
