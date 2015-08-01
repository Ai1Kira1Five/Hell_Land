package com.Arteman.HellLand.utils.network;

import java.util.Comparator;
import java.util.EnumMap;
import java.util.LinkedList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetHandler;
import net.minecraft.network.NetHandlerPlayServer;
import akka.util.Collections;

import com.Arteman.HellLand.HellLand;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.FMLEmbeddedChannel;
import cpw.mods.fml.common.network.FMLOutboundHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;

@ChannelHandler.Sharable
public class HellMessegePipeline extends MessageToMessageCodec<FMLProxyPacket, HellMessege>
{
	private EnumMap<Side, FMLEmbeddedChannel> channels;
	private LinkedList<Class<? extends HellMessege>> packets = new LinkedList<Class<? extends HellMessege>>();
	private boolean	isPostInitialized = false;

	public boolean registerPacket(Class<? extends HellMessege> class0)
	{
		if (packets.size() > 256)
		{
			HellLand.modLog.error("More than 256 packets registered");
			return false;
		}
		
		if (packets.contains(class0))
		{
			HellLand.modLog.warn("Packet already registered");
			return false;
		}
		
		if (isPostInitialized)
		{
			HellLand.modLog.error("Already post-initialized");
			return false;
		}
		
		packets.add(class0);
		return true;
	}
	
	@Override
	protected void encode(ChannelHandlerContext ctx, HellMessege msg, List<Object> out) throws Exception
	{
		ByteBuf buffer = Unpooled.buffer();
		Class<? extends HellMessege> clazz = msg.getClass();
		if (!packets.contains(msg.getClass()))
		{
			throw new NullPointerException("No Packet Registered for: " + msg.getClass().getCanonicalName());
		}
		
		byte discriminator = (byte) packets.indexOf(clazz);
		buffer.writeByte(discriminator);
		msg.encodeInto(ctx, buffer);
		FMLProxyPacket proxyPacket = new FMLProxyPacket(buffer, ctx.channel().attr(NetworkRegistry.FML_CHANNEL).get());
		out.add(proxyPacket);
	}
	
	@Override
	protected void decode(ChannelHandlerContext ctx, FMLProxyPacket msg, List<Object> out) throws Exception
	{
		ByteBuf payload = msg.payload();
		byte discriminator = payload.readByte();
		Class<? extends HellMessege> clazz = packets.get(discriminator);
		if (clazz == null)
		{
			throw new NullPointerException("No packet registered for discriminator: " + discriminator);
		}
		
		HellMessege pkt = clazz.newInstance();
		pkt.decodeInto(ctx, payload.slice());
		
		EntityPlayer player;
		switch (FMLCommonHandler.instance().getEffectiveSide())
		{
		case CLIENT:
			player = getClientPlayer();
			pkt.handleClientSide(player);
			break;
		
		case SERVER:
			INetHandler netHandler = ctx.channel().attr(NetworkRegistry.NET_HANDLER).get();
			player = ((NetHandlerPlayServer) netHandler).playerEntity;
			pkt.handleServerSide(player);
			break;
		
		default:
		}
		
		out.add(pkt);
	}
	
	public void initalize()
	{
		channels = NetworkRegistry.INSTANCE.newChannel("HellLand", this);
	}
	
	public void postInitialize()
	{
		if (isPostInitialized)
		{
			return;
		}
		
		isPostInitialized = true;
		Collections.sort(packets, new Comparator<Class<? extends HellMessege>>()
		{
			
			@Override
			public int compare(Class<? extends HellMessege> clazz1, Class<? extends HellMessege> clazz2)
			{
				int com = String.CASE_INSENSITIVE_ORDER.compare(clazz1.getCanonicalName(), clazz2.getCanonicalName());
				if (com == 0)
				{
					com = clazz1.getCanonicalName().compareTo(clazz2.getCanonicalName());
				}
				
				return com;
			}
		});
	}
	
	@SideOnly(Side.CLIENT)
	private EntityPlayer getClientPlayer()
	{
		return Minecraft.getMinecraft().thePlayer;
	}
	
	public void sendToAll(HellMessege message)
	{
		channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.ALL);
		channels.get(Side.SERVER).writeAndFlush(message);
	}
}
