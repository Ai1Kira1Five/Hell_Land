package com.Arteman.HellLand.utils.coord;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;
import com.Arteman.HellLand.utils.DataUtil;
import com.Arteman.HellLand.utils.Share;
import com.Arteman.HellLand.utils.coord.Orientation;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;
import net.minecraftforge.fluids.FluidTank;

public abstract class DataHelper {
	protected String name;
	protected boolean valid;
	
	public DataHelper as(Share share, String set_name){
		this.name = set_name;
	    this.valid = shouldStore(share);
	    return this;
	}
	
	public DataHelper asSameShare(String set_name){
		this.name = set_name;
	    return this;
	}
	
	protected abstract boolean shouldStore(Share paramShare);
	
	public abstract boolean isReader();
	
	public boolean isWriter(){
		return !isReader();
	}

	public NBTTagCompound getTag(){
		return null;
	}
	
	public boolean isNBT(){
		return false;
	}
	
	public <E> E put(E o)
			throws IOException{
		if (!this.valid) {
		      return o;
		}
		if ((o instanceof IDataSerializable)) {
		      return (E) ((IDataSerializable)o).serialize(this.name, this);
		}
		if ((o instanceof Enum))
	    {
	      Enum value = (Enum)o;
	      int i = putInt(value.ordinal());
	      if (isWriter()) {
	        return (E) value;
	      }
	      return (E)value.getDeclaringClass().getEnumConstants()[i];
	    }
		if ((o instanceof ItemStack))
	    {
	      ItemStack value = (ItemStack)o;
	      NBTTagCompound writtenTag = value.writeToNBT(new NBTTagCompound());
	      if (isReader()) {
	        return (E) ItemStack.loadItemStackFromNBT((NBTTagCompound)put(writtenTag));
	      }
	      put(writtenTag);
	      return o;
	    }
		if ((o instanceof UUID))
	    {
	      UUID uuid = (UUID)o;
	      String base_name = this.name;
	      if (isReader())
	      {
	        long msb = asSameShare(base_name + "MSB").putLong(0L);
	        long lsb = asSameShare(base_name + "LSB").putLong(0L);
	        return (E) new UUID(msb, lsb);
	      }
	      asSameShare(base_name + "MSB").putLong(uuid.getMostSignificantBits());
	      asSameShare(base_name + "LSB").putLong(uuid.getLeastSignificantBits());
	      return (E) uuid;
	    }
	    return (E)putImplementation(o);
	}
	
	protected abstract <E> E putImplementation(E paramE)
		    throws IOException;
	
	public final boolean putBoolean(boolean value) throws IOException
		{
			return ((Boolean)put(Boolean.valueOf(value))).booleanValue();
		}
	
	public final byte putByte(byte value) throws IOException
		{
		    return ((Byte)put(Byte.valueOf(value))).byteValue();
		}
	
	public final short putShort(short value) throws IOException
	{
		return ((Short)put(Short.valueOf(value))).shortValue();
	}
	
	public final int putInt(int value)
		    throws IOException
		  {
		    return ((Integer)put(Integer.valueOf(value))).intValue();
		  }
		  
		  public final long putLong(long value)
		    throws IOException
		  {
		    return ((Long)put(Long.valueOf(value))).longValue();
		  }
		  
		  public final float putFloat(float value)
		    throws IOException
		  {
		    return ((Float)put(Float.valueOf(value))).floatValue();
		  }
		  
		  public final double putDouble(double value)
		    throws IOException
		  {
		    return ((Double)put(Double.valueOf(value))).doubleValue();
		  }
		  
		  public final UUID putUUID(UUID value)
		    throws IOException
		  {
		    return (UUID)put(value);
		  }
		  
		  public final String putString(String value)
		    throws IOException
		  {
		    return (String)put(value);
		  }
		  
		  public final Orientation putFzOrientation(Orientation value)
		    throws IOException
		  {
		    return (Orientation)put(value);
		  }
		  
		  public final ItemStack putItemStack(ItemStack value)
		    throws IOException
		  {
		    if (value == null) {
		      value = DataUtil.NULL_ITEM;
		    }
		    ItemStack ret = (ItemStack)put(value);
		    if ((ret != null) && (ret.getItem() == null)) {
		      return null;
		    }
		    return ret;
		  }
		  
		  public final ArrayList<ItemStack> putItemArray(ArrayList<ItemStack> value)
		    throws IOException
		  {
		    if (!this.valid) {
		      return value;
		    }
		    if ((isReader()) && (hasLegacy(this.name + "_len"))) {
		      return putItemArray_legacy(value);
		    }
		    return putItemArray_efficient(value);
		  }
		  
		  protected ArrayList<ItemStack> putItemArray_efficient(ArrayList<ItemStack> value)
		    throws IOException
		  {
		    return putItemArray_legacy(value);
		  }
		  
		  @Deprecated
		  public final ArrayList<ItemStack> putItemArray_legacy(ArrayList<ItemStack> value)
		    throws IOException
		  {
		    String prefix = this.name;
		    int len = asSameShare(prefix + "_len").putInt(value.size());
		    if (isReader())
		    {
		      value.clear();
		      value.ensureCapacity(len);
		      for (int i = 0; i < len; i++) {
		        value.add(asSameShare(prefix + "_" + i).putItemStack(null));
		      }
		    }
		    else
		    {
		      for (int i = 0; i < len; i++) {
		        asSameShare(prefix + "_" + i).putItemStack((ItemStack)value.get(i));
		      }
		    }
		    return value;
		  }
		  
		  public final NBTTagCompound putTag(NBTTagCompound value)
		    throws IOException
		  {
		    return (NBTTagCompound)put(value);
		  }
		  
		  public final FluidTank putTank(FluidTank tank)
		    throws IOException
		  {
		    if (isWriter())
		    {
		      NBTTagCompound tag = new NBTTagCompound();
		      tank.writeToNBT(tag);
		      putTag(tag);
		      return tank;
		    }
		    NBTTagCompound tag = putTag(new NBTTagCompound());
		    tank.readFromNBT(tag);
		    return tank;
		  }
		  
		  public AxisAlignedBB putBox(AxisAlignedBB box)
		    throws IOException
		  {
		    box.minX = asSameShare(this.name + ".minX").putDouble(box.minX);
		    box.maxX = asSameShare(this.name + ".maxX").putDouble(box.maxX);
		    box.minY = asSameShare(this.name + ".minY").putDouble(box.minY);
		    box.maxY = asSameShare(this.name + ".maxY").putDouble(box.maxY);
		    box.minZ = asSameShare(this.name + ".minZ").putDouble(box.minZ);
		    box.maxZ = asSameShare(this.name + ".maxZ").putDouble(box.maxZ);
		    return box;
		  }
		  
		  public Vec3 putVec3(Vec3 val)
		    throws IOException
		  {
		    String prefix = this.name;
		    val.xCoord = asSameShare(prefix + ".x").putDouble(val.xCoord);
		    val.yCoord = asSameShare(prefix + ".y").putDouble(val.yCoord);
		    val.zCoord = asSameShare(prefix + ".z").putDouble(val.zCoord);
		    this.name = prefix;
		    return val;
		  }
		  
		  public final <E extends Enum> E putEnum(E value)
		    throws IOException
		  {
		    return (E)put(value);
		  }
		  
		  public void log(String message) {}
		  
		  public boolean hasLegacy(String name)
		  {
		    return false;
		  }
		  
		  public boolean isValid()
		  {
		    return this.valid;
		  }
}
