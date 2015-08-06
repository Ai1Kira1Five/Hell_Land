package com.Arteman.HellLand.utils;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import com.Arteman.HellLand.utils.network.ChannelHandler;
import com.Arteman.HellLand.utils.network.Packet;

import cpw.mods.fml.common.network.FMLEmbeddedChannel;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderServer;

public final class ThreadSafeUtils 
{
	private static final ThreadLocal<Chunk> lastChunk = new ThreadLocal<Chunk>();
	
	private ThreadSafeUtils() {}
	
	public static Chunk getChunk(World world, int x, int z) 
	{
		Chunk chunk;
		chunk = lastChunk.get();

		if (chunk != null) 
		{
			if (chunk.isChunkLoaded)
			{
				if (chunk.worldObj == world && chunk.xPosition == x && chunk.zPosition == z)
				{
					return chunk;
				}
			} 
			else
			{
				lastChunk.set(null);
			}
		}

		IChunkProvider provider = world.getChunkProvider();
		// These probably won't guarantee full thread safety, but it's our best bets.
		if (!Utils.CAULDRON_DETECTED && provider instanceof ChunkProviderServer) 
		{
			chunk = (Chunk) ((ChunkProviderServer) provider).loadedChunkHashMap.getValueByKey(ChunkCoordIntPair.chunkXZ2Int(x, z));
		}
		else 
		{
			chunk = provider.chunkExists(x, z) ? provider.provideChunk(x, z) : null;
		}

		if (chunk != null) 
		{
			lastChunk.set(chunk);
		}
		return chunk;
	}

	public static net.minecraft.network.Packet generatePacketFrom(Packet packet, FMLEmbeddedChannel channel) 
	{
		ByteBuf data = Unpooled.buffer();
		for (io.netty.channel.ChannelHandler h : channel.pipeline().toMap().values()) 
		{
			if (h instanceof ChannelHandler)
			{
				data.writeByte(((ChannelHandler) h).getDiscriminator(packet.getClass()));
				break;
			}
		}
		packet.writeData(data);
		return new FMLProxyPacket(data, channel.attr(NetworkRegistry.FML_CHANNEL).get());
	}
}
