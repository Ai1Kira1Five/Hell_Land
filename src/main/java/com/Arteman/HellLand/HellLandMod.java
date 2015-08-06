package com.Arteman.HellLand;

import java.util.EnumMap;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.network.NetworkManager;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

import com.Arteman.HellLand.utils.ThreadSafeUtils;
import com.Arteman.HellLand.utils.network.HLLog;
import com.Arteman.HellLand.utils.network.Packet;

import cpw.mods.fml.common.network.FMLEmbeddedChannel;
import cpw.mods.fml.common.network.FMLOutboundHandler;
import cpw.mods.fml.common.network.FMLOutboundHandler.OutboundTarget;
import cpw.mods.fml.relauncher.Side;

public class HellLandMod 
{
	private static PacketSender sender = new PacketSender();
	private static Thread senderThread = new Thread(sender);
	
	public EnumMap<Side, FMLEmbeddedChannel> channels;
	
	abstract static class SendRequest 
	{
		final Packet packet;
		final HellLandMod source;

		SendRequest(HellLandMod source, Packet packet) 
		{
			this.packet = packet;
			this.source = source;
		}

		abstract boolean isValid(EntityPlayer player);
	}
	
	class PlayerSendRequest extends SendRequest 
	{
		EntityPlayer player;

		PlayerSendRequest(HellLandMod source, Packet packet, EntityPlayer player)
		{
			super(source, packet);
			this.player = player;
		}

		boolean isValid(EntityPlayer player) 
		{
			return this.player.equals(player);
		}
	}

	class EntitySendRequest extends SendRequest 
	{
		Entity entity;

		EntitySendRequest(HellLandMod source, Packet packet, Entity entity) 
		{
			super(source, packet);
			this.entity = entity;
		}

		boolean isValid(EntityPlayer player)
		{
			if (player.worldObj.equals(entity.worldObj)) 
			{
				if (player.worldObj instanceof WorldServer) 
				{
					return ((WorldServer) player.worldObj).getEntityTracker().getTrackingPlayers(entity).contains(player);
				}
				else
				{
					return true;
				}
			}
			else 
			{
				return false;
			}
		}
	}

	class WorldSendRequest extends SendRequest 
	{
		final int dimensionId;

		WorldSendRequest(HellLandMod source, Packet packet, int dimensionId)
		{
			super(source, packet);
			this.dimensionId = dimensionId;
		}

		boolean isValid(EntityPlayer player)
		{
			return player.worldObj.provider.dimensionId == dimensionId;
		}
	}

	class LocationSendRequest extends SendRequest
	{
		final int dimensionId;
		final int x, y, z, md;

		LocationSendRequest(HellLandMod source, Packet packet, int dimensionId, int x, int y, int z, int md)
		{
			super(source, packet);
			this.dimensionId = dimensionId;
			this.x = x;
			this.y = y;
			this.z = z;
			this.md = md * md;
		}

		boolean isValid(EntityPlayer player)
		{
			return dimensionId == player.worldObj.provider.dimensionId
					&& player.getDistanceSq(x, y, z) <= md;
		}
	}

	static class PacketSender implements Runnable 
	{
		private Queue<SendRequest> packets = new ConcurrentLinkedQueue<SendRequest>();

		@Override
		public void run() 
		{
			while (true) 
			{
				try 
				{
					Thread.sleep(10);
				}
				catch (Exception e) 
				{

				}

				while (!packets.isEmpty()) 
				{
					try 
					{
						SendRequest r = packets.remove();
						net.minecraft.network.Packet p = ThreadSafeUtils.generatePacketFrom(r.packet, r.source.channels.get(Side.SERVER));
						List<EntityPlayerMP> playerList = MinecraftServer.getServer().getConfigurationManager().playerEntityList;
						for (EntityPlayerMP player : playerList.toArray(new EntityPlayerMP[playerList.size()])) 
						{
							if (r.isValid(player)) 
							{
								NetHandlerPlayServer handler = player.playerNetServerHandler;
								if (handler == null) 
								{
									continue;
								}

								NetworkManager manager = handler.netManager;
								if (manager == null || !manager.isChannelOpen()) 
								{
									continue;
								}

								manager.scheduleOutboundPacket(p);
							}
						}
					} catch (Exception e) 
					{
						HLLog.logger.error("The HellLand packet sender thread raised an exception! Please report to GitHub.");
						e.printStackTrace();
					}
				}
			}
		}

		public boolean add(SendRequest r) 
		{
			return packets.offer(r);
		}
	}

	static
	{
		senderThread.start();
	}

	public void sendToPlayers(Packet packet, World world, int x, int y, int z, int maxDistance)
	{
		sender.add(new LocationSendRequest(this, packet, world.provider.dimensionId, x, y, z, maxDistance));
	}

	public void sendToPlayersNear(Packet packet, TileEntity tileEntity, int maxDistance) 
	{
		sender.add(new LocationSendRequest(this, packet, tileEntity.getWorldObj().provider.dimensionId, tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord, maxDistance));
	}

	public void sendToPlayersNear(Packet packet, TileEntity tileEntity)
	{
		sendToPlayersNear(packet, tileEntity, DefaultProps.NETWORK_UPDATE_RANGE);
	}

	public void sendToWorld(Packet packet, World world)
	{
		sender.add(new WorldSendRequest(this, packet, world.provider.dimensionId));
	}

	public void sendToEntity(Packet packet, Entity entity)
	{
		sender.add(new EntitySendRequest(this, packet, entity));
	}
	
	public void sendToPlayer(EntityPlayer entityplayer, Packet packet)
	{
		sender.add(new PlayerSendRequest(this, packet, entityplayer));
	}

	public void sendToServer(Packet packet) 
	{
		try 
		{
			channels.get(Side.CLIENT).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(OutboundTarget.TOSERVER);
			channels.get(Side.CLIENT).writeOutbound(packet);
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
