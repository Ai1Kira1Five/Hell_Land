package com.Arteman.HellLand.utils.network;

import java.io.IOException;

import com.Arteman.HellLand.Command;
import com.Arteman.HellLand.utils.coord.DeltaCoord;
import com.Arteman.HellLand.utils.coord.Quaternion;
import com.Arteman.HellLand.utils.coord.VectorUV;

import cpw.mods.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.entity.player.EntityPlayer;
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
			else if ((item instanceof byte[])){
				byte[] b = (byte[])item;
		        output.writeBytes(b);
			}
			else if ((item instanceof MessageType)){
				MessageType mt = (MessageType)item;
		        mt.write(output);
			}
			else
			{
				throw new RuntimeException("Don't know how to serialize " + item.getClass() + " (" + item + ")");
			}
		}
	}
	
	public void sendCommand(EntityPlayer player, Command cmd, byte arg){
		ByteBuf out = Unpooled.buffer();
		out.writeByte(cmd.id);
	    out.writeByte(arg);
	}
	
	private static byte message_type_count = 0;

	public static enum MessageType{
		hlCmdChannel,
		PlaySound,
		EntityParticles(true),
		DrawActive,
		DescriptionRequest,
		DatatHelperEdit,
		RedrawOnClient,
		DataHelperEditOnEntity(true),
		OpenDataHelperGui,
		OpenDataHelperGuiOnEntity(true),
		TileEntityMessageOnEntity(true),
		CrystallizerInfo,
		ExtensionInfo,
		GeneratorParticles,
		UtilityGlueState(true);
		
		public boolean isEntityMessage;
		private static final MessageType[] valuesCache = values();
		private final byte id;
		
		private MessageType(){
			this(false);
		}
		
		private MessageType(boolean isEntity){
			this.id = NetworkHL.access$008();
			if (this.id < 0){
				throw new IllegalArgumentException("Too many message types!");
			}
			this.isEntityMessage = isEntity;
		}
		
		private static MessageType fromId(byte id){
			if ((id < 0) || (id >= valuesCache.length)){
				return null;
			}
			return valuesCache[id];
		}
		
		public static MessageType read(ByteBuf in){
			byte b = in.readByte();
		      return fromId(b);
		}
		
		public void write(ByteBuf out){
			out.writeByte(this.id);
		}
	}

	public static byte access$008() {
		// TODO Auto-generated method stub
		return 0;
	}
}
