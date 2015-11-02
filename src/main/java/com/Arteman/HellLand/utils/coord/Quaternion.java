package com.Arteman.HellLand.utils.coord;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import org.lwjgl.opengl.GL11;

import io.netty.buffer.ByteBuf;

import com.Arteman.HellLand.utils.SpaceUtil;
import com.google.common.io.ByteArrayDataOutput;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Vec3;
import net.minecraftforge.common.util.ForgeDirection;

public class Quaternion implements IDataSerializable{
	public double w;
	public double x;
	public double y;
	public double z;
	 
	public Quaternion()
	{
	  this(1.0D, 0.0D, 0.0D, 0.0D);
	}
	 
	public Quaternion(double w, double x, double y, double z)
	{
	  this.w = w;
	  this.x = x;
	  this.y = y;
	  this.z = z;
	}
	 
	public Quaternion(Quaternion orig)
	{
	  this.w = orig.w;
	  this.x = orig.x;
	  this.y = orig.y;
	  this.z = orig.z;
	}
	  
	public Quaternion(double[] init)
	{
	  this(init[0], init[1], init[2], init[3]);
	  assert (init.length == 4);
	}
	  
	public void loadFrom(VectorUV v)
	{
	  this.w = 1.0D;
	  this.x = v.x;
	  this.y = v.y;
	  this.z = v.z;
	}
	 
	public boolean equals(Object obj)
	{
	  if ((obj instanceof Quaternion))
	  {
	    Quaternion other = (Quaternion)obj;
	    return (this.w == other.w) && (this.x == other.x) && (this.y == other.y) && (this.z == other.z);
	  }
	  return false;
	}
	  
	public String toString()
	{
	  String m = "";
	  double mag = magnitude();
	  if (mag != 1.0D) {
	    m = " MAG=" + mag;
	  }
	  return "Q<w=" + this.w + ", " + this.x + ", " + this.y + ", " + this.z + ">" + m;
	}
	 
	public void writeToTag(NBTTagCompound tag, String prefix)
	{
	  tag.setDouble(prefix + "w", this.w);
	  tag.setDouble(prefix + "x", this.x);
	  tag.setDouble(prefix + "y", this.y);
	  tag.setDouble(prefix + "z", this.z);
	}
	
	public static Quaternion loadFromTag(NBTTagCompound tag, String prefix)
	{
	  return new Quaternion(tag.getDouble(prefix + "w"), tag.getDouble(prefix + "x"), tag.getDouble(prefix + "y"), tag.getDouble(prefix + "z"));
	}
	 
	public void write(ByteArrayDataOutput out)
	{
	  double[] d = toStaticArray();
	  for (int i = 0; i < d.length; i++) {
	    out.writeDouble(d[i]);
	  }
	}
	  
	public void write(ByteBuf out)
	{
	  double[] d = toStaticArray();
	  for (int i = 0; i < d.length; i++) {
	    out.writeDouble(d[i]);
	  }
	}
	 
	public void write(DataOutputStream out)
	  throws IOException
	{
	  double[] d = toStaticArray();
	  for (int i = 0; i < d.length; i++) {
	    out.writeDouble(d[i]);
	  }
	}
	 
	public static Quaternion read(DataInput in)
	  throws IOException
	{
	  double[] d = (double[])localStaticArray.get();
	  for (int i = 0; i < d.length; i++) {
	    d[i] = in.readDouble();
	  }
	  return new Quaternion(d);
	}
	  
	public static Quaternion read(ByteBuf in)
	  throws IOException
	{
	  double[] d = (double[])localStaticArray.get();
	  for (int i = 0; i < d.length; i++) {
	    d[i] = in.readDouble();
	  }
	  return new Quaternion(d);
	}
	  
	public IDataSerializable serialize(String name_prefix, DataHelper data)
	  throws IOException
	{
	  this.w = ((Double)data.asSameShare(name_prefix + "w").put(Double.valueOf(this.w))).doubleValue();
	  this.x = ((Double)data.asSameShare(name_prefix + "x").put(Double.valueOf(this.x))).doubleValue();
	  this.y = ((Double)data.asSameShare(name_prefix + "y").put(Double.valueOf(this.y))).doubleValue();
	  this.z = ((Double)data.asSameShare(name_prefix + "z").put(Double.valueOf(this.z))).doubleValue();
	  return this;
	}
	 
	public double[] fillArray(double[] out)
	{
	  out[0] = this.w;
	  out[1] = this.x;
	  out[2] = this.y;
	  out[3] = this.z;
	  return out;
	}
	 
	public double[] toArray()
	{
	  return fillArray(new double[4]);
	}
	
	private static ThreadLocal<double[]> localStaticArray = new ThreadLocal()
	{
	  protected double[] initialValue()
	  {
	    return new double[4];
	  }
	};
	  
	public double[] toStaticArray()
	{
	  return fillArray((double[])localStaticArray.get());
	}
	
	public boolean isZero()
	{
	  return (this.x == 0.0D) && (this.y == 0.0D) && (this.z == 0.0D);
	}
	 
	public void update(double nw, double nx, double ny, double nz)
	{
	  this.w = nw;
	  this.x = nx;
	  this.y = ny;
	  this.z = nz;
	}
	  
	public void update(Quaternion other)
	{
	  update(other.w, other.x, other.y, other.z);
	}
	
	public void update(ForgeDirection dir)
	{
	  update(this.w, dir.offsetX, dir.offsetY, dir.offsetZ);
	}
	
	public void update(Vec3 v)
	{
	  update(0.0D, v.xCoord, v.yCoord, v.zCoord);
	}
	
	public void copyToVector(Vec3 v)
	{
	  v.xCoord = this.x;
	  v.yCoord = this.y;
	  v.zCoord = this.z;
	}
	
	public Vec3 toVector()
	{
	  return Vec3.createVectorHelper(this.x, this.y, this.z);
	}
	
	public Vec3 toRotationVector()
	{
	  Vec3 rotVec = toVector().normalize();
	  SpaceUtil.incrScale(rotVec, getAngleRadians());
	  return rotVec;
	}
	
	public double getAngleRadians()
	{
	  return 2.0D * Math.acos(this.w);
	}
	
	public void incrNormalize()
	{
	  double normSquared = magnitudeSquared();
	  if ((normSquared == 1.0D) || (normSquared == 0.0D)) {
	    return;
	  }
	  double norm = Math.sqrt(normSquared);
	  this.w /= norm;
	  this.x /= norm;
	  this.y /= norm;
	  this.z /= norm;
	}
	 
	@Deprecated
	public static Quaternion getRotationQuaternion(Orientation orient)
	{
	  return getRotationQuaternionRadians(Math.toRadians(orient.getRotation() * 90), orient.facing);
	}
	  
	public static Quaternion getRotationQuaternionRadians(double angle, Vec3 axis)
	{
	  double halfAngle = angle / 2.0D;
	  double sin = Math.sin(halfAngle);
	  return new Quaternion(Math.cos(halfAngle), axis.xCoord * sin, axis.yCoord * sin, axis.zCoord * sin);
	}
	
	public static Quaternion getRotationQuaternionRadians(double angle, ForgeDirection axis)
	{
	  double halfAngle = angle / 2.0D;
	  double sin = Math.sin(halfAngle);
	  return new Quaternion(Math.cos(halfAngle), axis.offsetX * sin, axis.offsetY * sin, axis.offsetZ * sin);
	}
	
	public static Quaternion getRotationQuaternionRadians(double angle, double ax, double ay, double az)
	{
	  double halfAngle = angle / 2.0D;
	  double sin = Math.sin(halfAngle);
	  return new Quaternion(Math.cos(halfAngle), ax * sin, ay * sin, az * sin);
	}
	
	private static Quaternion[] quat_cache = new Quaternion[25];
	
	public static Quaternion fromOrientation(Orientation orient)
	{
	  int ord = orient.ordinal();
	  if (quat_cache[ord] != null) {
	    return quat_cache[ord];
	  }
	  if (orient == Orientation.UNKNOWN) {
	    return quat_cache[ord] = new Quaternion();
	  }
	  double quart = Math.toRadians(90.0D);
	  int rotation = orient.getRotation();
	  Quaternion q1;
	  switch (orient.facing)
	  {
	    case UP: 
	      q1 = getRotationQuaternionRadians(0.0D * quart, ForgeDirection.WEST);
	      rotation = 5 - rotation;
	      break;
	    case DOWN: 
	      q1 = getRotationQuaternionRadians(2.0D * quart, ForgeDirection.WEST);
	      rotation = 3 - rotation;
	      break;
	    case NORTH: 
	      q1 = getRotationQuaternionRadians(1.0D * quart, ForgeDirection.WEST);
	      rotation = 5 - rotation;
	      break;
	    case SOUTH: 
	      q1 = getRotationQuaternionRadians(-1.0D * quart, ForgeDirection.WEST);
	      rotation = 3 - rotation;
	      break;
	    case EAST: 
	      q1 = getRotationQuaternionRadians(1.0D * quart, ForgeDirection.NORTH);
	      
	      rotation += Math.abs(orient.top.offsetZ) * 2;
	      break;
	    case WEST: 
	      q1 = getRotationQuaternionRadians(-1.0D * quart, ForgeDirection.NORTH);
	      rotation += Math.abs(orient.top.offsetY) * 2;
	      break;
	    default: 
	      return quat_cache[ord] = new Quaternion();
	  }
	  Quaternion q2 = getRotationQuaternionRadians(rotation * quart, orient.facing);
	  q2.incrMultiply(q1);
	  return quat_cache[ord] = q2;
	}
	  
	public double setVector(Vec3 axis)
	{
	  double halfAngle = Math.acos(this.w);
	  double sin = Math.sin(halfAngle);
	  axis.xCoord = (this.x / sin);
	  axis.yCoord = (this.y / sin);
	  axis.zCoord = (this.z / sin);
	  return halfAngle * 2.0D;
	}
	  
	@SideOnly(Side.CLIENT)
	public void glRotate()
	{
	  double halfAngle = Math.acos(this.w);
	  double sin = Math.sin(halfAngle);
	  GL11.glRotatef((float)Math.toDegrees(halfAngle * 2.0D), (float)(this.x / sin), (float)(this.y / sin), (float)(this.z / sin));
	}
	  
	public double dotProduct(Quaternion other)
	{
	  return this.w * other.w + this.x * other.x + this.y * other.y + this.z * other.z;
	}
	  
	public void incrLerp(Quaternion other, double t)
	{
	   other.incrAdd(this, -1.0D);
	   other.incrScale(t);
	   incrAdd(other);
	   incrNormalize();
	}
	  
	public Quaternion lerp(Quaternion other, double t)
	{
	  Quaternion ret = new Quaternion(this);
	  ret.incrLerp(other, t);
	  return ret;
	}
	  
	public void incrShortFor(Quaternion other)
	{
	  double cosom = dotProduct(other);
	  if (cosom < 0.0D) {
	    incrScale(-1.0D);
	  }
	}
	  
	public void incrLongFor(Quaternion other)
	{
	  double cosom = dotProduct(other);
	  if (cosom > 0.0D) {
	    incrScale(-1.0D);
	  }
	}
	  
	/*фак, надо что-то тут исправить*/	  
	public Quaternion slerp(Quaternion other, double t)
	{
	  double cosom = dotProduct(other);
	  double sc2;
	  double sc1;
	  //double sc2;
	  if (1.0D - cosom > 9.999999747378752E-5D)
	  {
	    double omega = Math.acos(cosom);
	    double sinom = Math.sin(omega);
	    //double sc1 = Math.sin((1.0D - t) * omega) / sinom;
	    sc2 = Math.sin(t * omega) / sinom;
	  }
	  else
	  {
	    sc1 = 1.0D - t;
	    sc2 = t;
	  }
	  return new Quaternion(sc1 * this.w + sc2 * other.w, sc1 * this.x + sc2 * other.x, sc1 * this.y + sc2 * other.y, sc1 * this.z + sc2 * other.z);
	}
	  
	public Quaternion shortSlerp(Quaternion other, double t)
	{
	  double cosom = dotProduct(other);
	  boolean rev = cosom < 0.0D;
	  if (rev)
	  {
	    cosom = -cosom;
	    other.incrScale(-1.0D);
	  }
	  double sc2;
	  double sc1;
	  double sc2;
	  if (1.0D - cosom > 9.999999747378752E-5D)
	  {
	    double omega = Math.acos(cosom);
	    double sinom = Math.sin(omega);
	    double sc1 = Math.sin((1.0D - t) * omega) / sinom;
	    sc2 = Math.sin(t * omega) / sinom;
	  }
	  else
	  {
	    sc1 = 1.0D - t;
	    sc2 = t;
	  }
	  Quaternion ret = new Quaternion(sc1 * this.w + sc2 * other.w, sc1 * this.x + sc2 * other.x, sc1 * this.y + sc2 * other.y, sc1 * this.z + sc2 * other.z);
	  if (rev) {
	    other.incrScale(-1.0D);
	  }
	  return ret;
	}
	 
	public double getAngleBetween(Quaternion other)
	{
	  double dot = dotProduct(other);
	  dot = Math.max(-1.0D, Math.min(1.0D, dot));
	  return Math.acos(dot);
	}
	
	public double magnitude()
	{
	  return Math.sqrt(this.w * this.w + this.x * this.x + this.y * this.y + this.z * this.z);
	}
	
	public double magnitudeSquared()
	{
	  return this.w * this.w + this.x * this.x + this.y * this.y + this.z * this.z;
	}
	  
	public double incrDistance(Quaternion other)
	{
	  incrAdd(other);
	  return magnitude();
	}
	 
	public void incrConjugate()
	{
	  this.x = (-this.x);
	  this.y = (-this.y);
	  this.z = (-this.z);
	}
	  
	public void incrAdd(Quaternion other)
	{
	  this.w += other.w;
	  this.x += other.x;
	  this.y += other.y;
	  this.z += other.z;
	}
	
	public void incrAdd(Quaternion other, double scale)
	{
	  this.w += other.w * scale;
	  this.x += other.x * scale;
	  this.y += other.y * scale;
	  this.z += other.z * scale;
	}
	 
	public void incrMultiply(Quaternion other)
	{
	  double nw = this.w * other.w - this.x * other.x - this.y * other.y - this.z * other.z;
	  double nx = this.w * other.x + this.x * other.w + this.y * other.z - this.z * other.y;
	  double ny = this.w * other.y - this.x * other.z + this.y * other.w + this.z * other.x;
	  double nz = this.w * other.z + this.x * other.y - this.y * other.x + this.z * other.w;
	  update(nw, nx, ny, nz);
	}
	  
	public void incrToOtherMultiply(Quaternion other)
	{
	  double nw = this.w * other.w - this.x * other.x - this.y * other.y - this.z * other.z;
	  double nx = this.w * other.x + this.x * other.w + this.y * other.z - this.z * other.y;
	  double ny = this.w * other.y - this.x * other.z + this.y * other.w + this.z * other.x;
	  double nz = this.w * other.z + this.x * other.y - this.y * other.x + this.z * other.w;
	  other.update(nw, nx, ny, nz);
	}
	  
	public void incrScale(double scaler)
	{
	  this.w *= scaler;
	  this.x *= scaler;
	  this.y *= scaler;
	  this.z *= scaler;
	}
	  
	public void incrUnit()
	{
	  incrScale(1.0D / magnitude());
	}
	 
	public void incrReciprocal()
	{
	  double m = magnitude();
	  incrConjugate();
	  incrScale(1.0D / (m * m));
	}
	 
	public void incrCross(Quaternion other)
	{
	  double X = this.y * other.z - this.z * other.y;
	  double Y = this.z * other.x - this.x * other.z;
	  double Z = this.x * other.y - this.y * other.x;
	  this.x = X;
	  this.y = Y;
	  this.z = Z;
	}
	  
	public Quaternion cross(Quaternion other)
	{
	  Quaternion m = new Quaternion(this);
	  m.incrCross(other);
	  return m;
	}
	 
	public void incrRotateBy(Quaternion rotation)
	{
	  rotation.incrToOtherMultiply(this);
	  rotation.incrConjugate();
	  incrMultiply(rotation);
	  rotation.incrConjugate();
	}
	
	public void applyRotation(Vec3 p)
	{
	  if (isZero()) {
	     return;
	  }
	  if (this._vector_conversion_cache == null) {
	    this._vector_conversion_cache = new Quaternion();
	  }
	  Quaternion point = this._vector_conversion_cache;
	  point.update(p);
	  incrToOtherMultiply(point);
	  incrConjugate();
	  point.incrMultiply(this);
	  incrConjugate();
	  point.copyToVector(p);
	}
	  
	private Quaternion _vector_conversion_cache = null;
	
	public void applyReverseRotation(Vec3 p)
	{
	  incrConjugate();
	  applyRotation(p);
	  incrConjugate();
	}
	
	private static Vec3 uvCache = Vec3.createVectorHelper(0.0D, 0.0D, 0.0D);
	
	public void applyRotation(VectorUV vec)
	{
	  uvCache.xCoord = vec.x;
	  uvCache.yCoord = vec.y;
	  uvCache.zCoord = vec.z;
	  applyRotation(uvCache);
	  vec.x = uvCache.xCoord;
	  vec.y = uvCache.yCoord;
	  vec.z = uvCache.zCoord;
	}
	  
	public double distance(Quaternion other)
	{
	  return add(other).magnitude();
	}
	
	public Quaternion conjugate()
	{
	  Quaternion ret = new Quaternion(this);
	  ret.incrConjugate();
	  return ret;
	}
	 
	public Quaternion add(Quaternion other)
	{
	  Quaternion ret = new Quaternion(this);
	  ret.incrAdd(other);
	  return ret;
	}
	
	public Quaternion add(Quaternion other, double scale)
	{
	  Quaternion ret = new Quaternion(this);
	  ret.incrAdd(other, scale);
	  return ret;
	 }
	  
	public Quaternion multiply(Quaternion other)
	{
	  Quaternion a = new Quaternion(this);
	  a.incrMultiply(other);
	  return a;
	}
	  
	public Quaternion scale(double scaler)
	{
	  Quaternion a = new Quaternion(this);
	  a.incrScale(scaler);
	  return a;
	}
	
	public Quaternion unit()
	{
	  Quaternion r = new Quaternion(this);
	  r.incrUnit();
	  return r;
	}
	  
	public Quaternion reciprocal()
	{
	  Quaternion r = new Quaternion(this);
	  r.incrReciprocal();
	  return r;
	}
	  
	public Quaternion power(double alpha)
	{
	  double norm = magnitude();
	  double theta = Math.acos(this.w / norm);
	  double qa = Math.pow(norm, alpha);
	  double alphaTheta = alpha * theta;
	  double W = qa * Math.cos(alpha * theta);
	  double sat = Math.sin(alphaTheta);
	  return new Quaternion(W, this.x * sat, this.y * sat, this.z * sat);
	}
	  
	public boolean hasNaN()
	{
	  return (Double.isNaN(this.w)) || (Double.isNaN(this.x)) || (Double.isNaN(this.y)) || (Double.isNaN(this.z));
	}
	
	public boolean hasInf()
	{
	  return (Double.isInfinite(this.w)) || (Double.isInfinite(this.x)) || (Double.isInfinite(this.y)) || (Double.isInfinite(this.z));
	}
	 
	public Quaternion cleanAbnormalNumbers()
	{
	  if ((hasNaN()) || (hasInf())) {
	    return new Quaternion();
	  }
	  return this;
	}
}
