package com.Arteman.HellLand.utils.network;

import java.io.IOException;

import com.Arteman.HellLand.utils.coord.DeltaCoord;
import com.Arteman.HellLand.utils.coord.Quaternion;
import com.Arteman.HellLand.utils.coord.VectorUV;

import cpw.mods.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class NetworkHL {
	public static final ItemStack EMPTY_ITEMSTACK = new ItemStack(Blocks.air);
	
	private void writeObjects(ByteBuf output, Object... items)
		    throws IOException{
		for (Object item : items){
			if (item == null){
				throw new RuntimeException("Argument is null!");
			}
			if ((item instanceof Integer)){
				output.writeInt(((Integer)item).intValue());
			}
			else if ((item instanceof Byte)){
				output.writeByte(((Byte)item).byteValue());
			}
			else if ((item instanceof Short)){
				output.writeShort(((Short)item).shortValue());
			}
			else if ((item instanceof String)){
				ByteBufUtils.writeUTF8String(output, (String)item);
			}
			else if ((item instanceof Boolean)){
				output.writeBoolean(((Boolean)item).booleanValue());
			}
			else if ((item instanceof Float)){
				output.writeFloat(((Float)item).floatValue());
			}
			else if ((item instanceof Double)){
				output.writeDouble(((Double)item).doubleValue());
			}
			else if ((item instanceof ItemStack)){
				ItemStack is = (ItemStack)item;
		        if (is == EMPTY_ITEMSTACK){
		        	is = null;
		        }
		        ByteBufUtils.writeItemStack(output, is);
			}
			else if ((item instanceof byte[])){
				byte[] b = (byte[])item;
		        output.writeBytes(b);
			}
			else if ((item instanceof NBTTagCompound)){
				NBTTagCompound tag = (NBTTagCompound)item;
		        ByteBufUtils.writeTag(output, tag);
			}
/*я не*/		else if ((item instanceof VectorUV)){
/*знаю,*/		VectorUV v = (VectorUV)item;
/*буду*/		output.writeFloat((float)v.x);
/*ли я*/		output.writeFloat((float)v.y);
/*это делать*/	output.writeFloat((float)v.z);
			}
			else if ((item instanceof DeltaCoord)){
				 DeltaCoord dc = (DeltaCoord)item;
			     dc.write(output);
			}
			else if ((item instanceof Quaternion)){
				Quaternion q = (Quaternion)item;
		        q.write(output);
			}
			else
			{
				throw new RuntimeException("Don't know how to serialize " + item.getClass() + " (" + item + ")");
			}
		}
	}

}
