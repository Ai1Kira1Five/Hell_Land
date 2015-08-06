package com.Arteman.HellLand.proxy;

import java.lang.ref.WeakReference;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.WorldServer;

public interface ICoreProxy 
{
	WeakReference<EntityPlayer> getHellLandPlayer(WorldServer world);

	WeakReference<EntityPlayer> getHellPlayer(WorldServer world);
}
