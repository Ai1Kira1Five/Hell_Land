package com.Arteman.HellLand.utils;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.Arteman.HellLand.DefaultProps;
import com.Arteman.HellLand.utils.network.Packet;

import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public final class Utils 
{
	public static final boolean CAULDRON_DETECTED;
	private static final List<ForgeDirection> directions = new ArrayList<ForgeDirection>(Arrays.asList(ForgeDirection.VALID_DIRECTIONS));
	
	static {
		boolean cauldron = false;
		try 
		{
			cauldron = Utils.class.getClassLoader().loadClass("org.spigotmc.SpigotConfig") != null;
		} 
		catch (ClassNotFoundException e) 
		{

		}
		CAULDRON_DETECTED = cauldron;
	}
	
	private Utils() {}
	
	/**
	 * Returns the cardinal direction of the entity depending on its
	 * rotationYaw
	 */
	public static ForgeDirection get2dOrientation(EntityLivingBase entityliving) 
	{
		ForgeDirection[] orientationTable = 
			{ ForgeDirection.SOUTH, 
				ForgeDirection.WEST, 
				ForgeDirection.NORTH, 
				ForgeDirection.EAST };
		int orientationIndex = MathHelper.floor_double((entityliving.rotationYaw + 45.0) / 90.0) & 3;
		return orientationTable[orientationIndex];
	}
	
	public static FMLProxyPacket toPacket (Packet packet, int discriminator) 
	{
		ByteBuf buf = Unpooled.buffer();

		buf.writeByte((byte) discriminator);
		packet.writeData(buf);

		return new FMLProxyPacket(buf, DefaultProps.NET_CHANNEL_NAME + "-CORE");
	}	
}
