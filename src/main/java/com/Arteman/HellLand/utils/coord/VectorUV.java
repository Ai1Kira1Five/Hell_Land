package com.Arteman.HellLand.utils.coord;

import java.io.DataInput;
import java.io.IOException;
import java.util.ArrayList;

import net.minecraft.nbt.NBTTagCompound;

public class VectorUV {
	public double x;
	public double y;
	public double z;
	public double u;
	public double v;
	
	public VectorUV()
	{
	  this(0.0D, 0.0D, 0.0D);
	}
	
	public VectorUV(double x, double y, double z)
	{
	  this.x = x;
	  this.y = y;
	  this.z = z;
	  this.u = 0.0D;
	  this.v = 0.0D;
	}
	 
	public VectorUV(double x, double y, double z, double u, double v)
	{
	  this.x = x;
	  this.y = y;
	  this.z = z;
	  this.u = u;
	  this.v = v;
	}
	  
	public boolean equals(VectorUV other)
	{
	   return (this.x == other.x) && (this.y == other.y) && (this.z == other.z) && (this.u == other.u) && (this.v == other.v);
	}
	  
	public void rotate(double a, double b, double c, double argtheta)
	{
	   double theta = Math.toRadians(argtheta);
	   double ox = this.x;double oy = this.y;double oz = this.z;
	   
	   double cos_theta = Math.cos(theta);
	   double sin_theta = Math.sin(theta);
	   double product = (a * ox + b * oy + c * oz) * (1.0D - cos_theta);
	   this.x = (a * product + ox * cos_theta + (-c * oy + b * oz) * sin_theta);
	   this.y = (b * product + oy * cos_theta + (c * ox - a * oz) * sin_theta);
	   this.z = (c * product + oz * cos_theta + (-b * ox + a * oy) * sin_theta);
	}
	  
	public VectorUV add(int dx, int dy, int dz)
	{
	   return new VectorUV(this.x + dx, this.y + dy, this.z + dz, this.u, this.v);
	}
	  
	public VectorUV add(VectorUV o)
	{
	  return new VectorUV(this.x + o.x, this.y + o.y, this.z + o.z, this.u, this.v);
	}
	  
	public VectorUV subtract(VectorUV o)
	{
	   return new VectorUV(this.x - o.x, this.y - o.y, this.z - o.z, this.u, this.v);
	}
	 
	public void scale(double d)
	{
	   this.x *= d;
	   this.y *= d;
	   this.z *= d;
	}
	  
	public void incr(VectorUV d)
	{
	   this.x += d.x;
	   this.y += d.y;
	   this.z += d.z;
	}
	  
	public VectorUV copy()
	{
	   return new VectorUV(this.x, this.y, this.z, this.u, this.v);
	}
	  
	public VectorUV negate()
	{
	   return new VectorUV(-this.x, -this.y, -this.z, this.u, this.v);
	}
	 
	public String toString()
	{
	   return "<" + this.x + ", " + this.y + ", " + this.z + ": " + this.u + ", " + this.v + ">";
	}
	 
	public void writeToTag(NBTTagCompound tag, String prefix)
	{
	  tag.setFloat(prefix + "x", (float)this.x);
	  tag.setFloat(prefix + "y", (float)this.y);
	  tag.setFloat(prefix + "z", (float)this.z);
	}
	 
	public static VectorUV readFromTag(NBTTagCompound tag, String prefix)
	{
	  double x = tag.getFloat(prefix + "x");
	  double y = tag.getFloat(prefix + "y");
	  double z = tag.getFloat(prefix + "z");
	  return new VectorUV(x, y, z);
	}
	  
	public static VectorUV readFromDataInput(DataInput input)
	   throws IOException
	{
	  return new VectorUV(input.readFloat(), input.readFloat(), input.readFloat());
	}
	  
	public void addInfoToArray(ArrayList<Object> args)
	{
	   args.add(Float.valueOf((float)this.x));
	   args.add(Float.valueOf((float)this.y));
	   args.add(Float.valueOf((float)this.z));
	}
	  
	public double get(int axis)
	{
	   switch (axis)
	   {
	   case 0: 
	     return this.x;
	   case 1: 
	     return this.y;
	   case 2: 
	     return this.z;
	  }
	  throw new RuntimeException("Invalid argument");
	}
}
