package com.Arteman.HellLand.utils.coord;

import java.io.IOException;

import com.google.common.base.Splitter;

import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Vec3;
import net.minecraftforge.common.util.ForgeDirection;

public class DeltaCoord implements IDataSerializable{
	public int x;
	public int y;
	public int z;
	public static final DeltaCoord ZERO = new DeltaCoord();
	
	public DeltaCoord()
	{
	   this.x = (this.y = this.z = 0);
	}
	
	public DeltaCoord(int x, int y, int z)
	{
	   this.x = x;
	   this.y = y;
	   this.z = z;
	}
	
	public DeltaCoord(DeltaCoord orig)
	{
	   this.x = orig.x;
	   this.y = orig.y;
	   this.z = orig.z;
	}
	
	public DeltaCoord(ForgeDirection dir)
	{
	   this(dir.offsetX, dir.offsetY, dir.offsetZ);
	}
	  
	public DeltaCoord add(DeltaCoord o)
	{
	   return new DeltaCoord(this.x + o.x, this.y + o.y, this.z + o.z);
	}
	
	public DeltaCoord add(int dx, int dy, int dz)
	{
	   return new DeltaCoord(this.x + dx, this.y + dy, this.z + dz);
	}
	
	public DeltaCoord scale(double d)
	{
	   return new DeltaCoord((int)(this.x * d), (int)(this.y * d), (int)(this.z * d));
	}
	
	public DeltaCoord incrScale(int s)
	{
	   this.x *= s;
	   this.y *= s;
	   this.z *= s;
	   return this;
	}
	  
	public boolean isZero()
	{
	   return (this.x == 0) && (this.y == 0) && (this.z == 0);
	}
	  
	public String toString()
	{
	   return "DeltaCoord(" + this.x + ", " + this.y + ", " + this.z + ")";
	}
	  
	private static DeltaCoord d(int x, int y, int z)
	{
	   return new DeltaCoord(x, y, z);
	}
	 
	public static DeltaCoord[] directNeighbors = { d(1, 0, 0), d(-1, 0, 0), d(0, -1, 0), d(0, 1, 0), d(0, 0, -1), d(0, 0, 1) };
	public static DeltaCoord[] planeNeighbors = { d(1, 0, 0), d(-1, 0, 0), d(0, 0, -1), d(0, 0, 1) };
	
	public double getAngleHorizontal()
	{
	   return Math.atan2(this.z, -this.x);
	}
	 
	public ForgeDirection getDirection()
	{
	  ForgeDirection[] values = ForgeDirection.VALID_DIRECTIONS;
	  for (int i = 0; i < values.length; i++)
	  {
	    ForgeDirection d = values[i];
	    if ((d.offsetX == this.x) && (d.offsetY == this.y) && (d.offsetZ == this.z)) {
	      return d;
	    }
	  }
	  return ForgeDirection.UNKNOWN;
	}
	 
	public int getFaceSide()
	{
	  if ((this.x == 0) && (this.z == 0))
	  {
	    if (this.y == -1) {
	      return 0;
	    }
	    if (this.y == 1) {
	      return 1;
	    }
	  }
	  else if ((this.y == 0) && (this.x == 0))
	  {
	    if (this.z == -1) {
	      return 2;
	    }
	    if (this.z == 1) {
	      return 3;
	    }
	  }
	  else if ((this.y == 0) && (this.z == 0))
	  {
	    if (this.x == -1) {
	      return 4;
	    }
	    if (this.x == 1) {
	      return 5;
	    }
	  }
	  return -1;
	}
	  
	public DeltaCoord reverse()
	{
	  return new DeltaCoord(-this.x, -this.y, -this.z);
	}
	
	public boolean isSubmissive()
	{
	  return (this.x < 0) || (this.y < 0) || (this.z < 0);
	}
	
	public boolean equals(DeltaCoord o)
	{
	  return (this.x == o.x) && (this.y == o.y) && (this.z == o.z);
	}
	
	public void alignToAxis()
	{
	  int ax = Math.abs(this.x);
	  int ay = Math.abs(this.y);
	  int az = Math.abs(this.z);
	  if ((ax >= ay) && (ax >= az))
	  {
	    this.x = ((int)Math.signum(this.x));
	    return;
	  }
	  if ((ay >= ax) && (ay >= az))
	  {
	    this.y = ((int)Math.signum(this.y));
	    return;
	  }
	  if ((az >= ay) && (az >= ax))
	  {
	    this.z = ((int)Math.signum(this.z));
	    return;
	  }
	  this.x = (this.y = this.z = 0);
	}
	
	public int get(int id)
	{
	  switch (id)
	  {
	  case 0: 
	    return this.x;
	  case 1: 
	    return this.y;
	  case 2: 
	    return this.z;
	  }
	  throw new RuntimeException("not an dimension index");
	}
	
	public void set(int id, int val)
	{
	  switch (id)
	  {
	  case 0: 
	    this.x = val; break;
	  case 1: 
	    this.y = val; break;
	  case 2: 
	    this.z = val; break;
	    default: 
	    throw new RuntimeException("not an dimension index");
	  }
	}
	
	public void init(int x, int y, int z)
	{
	  this.x = x;
	  this.y = y;
	  this.z = z;
	}
	 
	public void writeToTag(String prefix, NBTTagCompound tag)
	{
	  tag.setInteger(prefix + "dx", this.x);
	  tag.setInteger(prefix + "dy", this.y);
	  tag.setInteger(prefix + "dz", this.z);
	}
	  
	public static DeltaCoord readFromTag(String prefix, NBTTagCompound tag)
	{
	  return new DeltaCoord(tag.getInteger(prefix + "dx"), tag.getInteger(prefix + "dy"), tag.getInteger(prefix + "dz"));
	}
	
	public static DeltaCoord read(ByteBuf di)
	  throws IOException
	{
	  return new DeltaCoord(di.readInt(), di.readInt(), di.readInt());
	}
	
	public void write(ByteBuf out)
	  throws IOException
	{
	  for (int i = 0; i < 3; i++) {
	    out.writeInt(get(i));
	  }
	}
	
	private static Splitter COMMA_SPLITTER = Splitter.on(',');
	
	public static DeltaCoord parse(String input)
	{
	  DeltaCoord ret = new DeltaCoord();
	  int i = 0;
	  for (String s : COMMA_SPLITTER.split(input))
	  {
	    ret.set(i, Integer.parseInt(s));
	     i++;
	  }
	  return ret;
	}
	
	public IDataSerializable serialize(String prefix, DataHelper data)
	  throws IOException
	{
	  this.x = ((Integer)data.asSameShare(prefix + "dx").put(Integer.valueOf(this.x))).intValue();
	  this.y = ((Integer)data.asSameShare(prefix + "dy").put(Integer.valueOf(this.y))).intValue();
	  this.z = ((Integer)data.asSameShare(prefix + "dz").put(Integer.valueOf(this.z))).intValue();
	  return this;
	}
	
	public double magnitude()
	{
	  return Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
	}
	
	public Vec3 toVector()
	{
	  return Vec3.createVectorHelper(this.x, this.y, this.z);
	}
	
	public void move(ForgeDirection dir)
	{
	  this.x += dir.offsetX;
	  this.y += dir.offsetY;
	  this.z += dir.offsetZ;
	}
}