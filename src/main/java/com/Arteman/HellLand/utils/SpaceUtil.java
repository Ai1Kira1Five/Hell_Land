package com.Arteman.HellLand.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import com.Arteman.HellLand.utils.coord.Coord;
import com.Arteman.HellLand.utils.coord.DeltaCoord;
import com.Arteman.HellLand.utils.coord.Orientation;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraftforge.common.util.ForgeDirection;

public final class SpaceUtil {
	public static final byte GET_POINT_MIN = 0;
	public static final byte GET_POINT_MAX = 7;
	private static ThreadLocal<ArrayList<ForgeDirection>> direction_cache = new ThreadLocal();
	
	public static int determineOrientation(EntityPlayer player)
	{
	  if (player.rotationPitch > 75.0F) {
	    return 0;
	  }
	  if (player.rotationPitch <= -75.0F) {
	    return 1;
	  }
	  return determineFlatOrientation(player);
	}
	
	public static int determineFlatOrientation(EntityPlayer player)
	{
	  int var7 = MathHelper.floor_double((180.0F + player.rotationYaw) * 4.0F / 360.0F + 0.5D) & 0x3;
	  return var7 == 3 ? 4 : var7 == 2 ? 3 : var7 == 1 ? 5 : var7 == 0 ? 2 : 0;
	}
	
	public static DeltaCoord getFlatDiagonalFacing(EntityPlayer player)
	{
	   double angle = Math.toRadians(90.0F + player.rotationYaw);
	   int dx = Math.cos(angle) > 0.0D ? 1 : -1;
	   int dz = Math.sin(angle) > 0.0D ? 1 : -1;
	   return new DeltaCoord(dx, 0, dz);
	 }
	 
	 public static byte getOpposite(int dir)
	 {
	   return (byte)ForgeDirection.getOrientation(dir).getOpposite().ordinal();
	 }
	 
	 public static Vec3 copy(Vec3 a)
	 {
	   return Vec3.createVectorHelper(a.xCoord, a.yCoord, a.zCoord);
	 }
	 
	 public static AxisAlignedBB copy(AxisAlignedBB box)
	 {
	   return AxisAlignedBB.getBoundingBox(box.minX, box.minY, box.minZ, box.maxX, box.maxY, box.maxZ);
	 }
	 
	 public static void copyTo(AxisAlignedBB dst, AxisAlignedBB src)
	 {
	   dst.minX = src.minX;
	  dst.maxX = src.maxX;
	  dst.minY = src.minY;
	  dst.maxY = src.maxY;
	  dst.minZ = src.minZ;
	  dst.maxZ = src.maxZ;
	}
	
	public static Vec3 newVec()
	{
	  return Vec3.createVectorHelper(0.0D, 0.0D, 0.0D);
	}
	
	public static Vec3 fromEntPos(Entity ent)
	{
	  return Vec3.createVectorHelper(ent.posX, ent.posY, ent.posZ);
	}
	
	public static Vec3 fromEntVel(Entity ent)
	{
	  return Vec3.createVectorHelper(ent.motionX, ent.motionY, ent.motionZ);
	}
	
	public static void toEntVel(Entity ent, Vec3 vec)
	{
	  ent.motionX = vec.xCoord;
	  ent.motionY = vec.yCoord;
	  ent.motionZ = vec.zCoord;
	}
	
	public static Vec3 fromPlayerEyePos(EntityPlayer ent)
	{
	  if (ent.worldObj.isRemote) {
	    return Vec3.createVectorHelper(ent.posX, ent.posY + (ent.getEyeHeight() - ent.getDefaultEyeHeight()), ent.posZ);
	  }
	  return Vec3.createVectorHelper(ent.posX, ent.posY + ent.getEyeHeight(), ent.posZ);
	}
	
	public static void toEntPos(Entity ent, Vec3 pos)
	{
	  ent.posX = pos.xCoord;
	  ent.posY = pos.yCoord;
	  ent.posZ = pos.zCoord;
	}
	
	public static Vec3 set(Vec3 dest, Vec3 orig)
	{
	  dest.xCoord = orig.xCoord;
	  dest.yCoord = orig.yCoord;
	  dest.zCoord = orig.zCoord;
	  return dest;
	}
	  
	  public static void setMin(AxisAlignedBB aabb, Vec3 v)
	  {
	    aabb.minX = v.xCoord;
	    aabb.minY = v.yCoord;
	    aabb.minZ = v.zCoord;
	  }
	  
	  public static Vec3 getMax(AxisAlignedBB aabb)
	  {
	    return Vec3.createVectorHelper(aabb.maxX, aabb.maxY, aabb.maxZ);
	  }
	  
	  public static Vec3 getMin(AxisAlignedBB aabb)
	  {
	    return Vec3.createVectorHelper(aabb.minX, aabb.minY, aabb.minZ);
	  }
	  
	  public static void getMax(AxisAlignedBB box, Vec3 target)
	  {
	    target.xCoord = box.maxX;
	    target.yCoord = box.maxY;
	    target.zCoord = box.maxZ;
	  }
	  
	  public static void getMin(AxisAlignedBB box, Vec3 target)
	  {
	    target.xCoord = box.minX;
	    target.yCoord = box.minY;
	    target.zCoord = box.minZ;
	  }
	  
	  public static void setMax(AxisAlignedBB aabb, Vec3 v)
	  {
	    aabb.maxX = v.xCoord;
	    aabb.maxY = v.yCoord;
	    aabb.maxZ = v.zCoord;
	  }
	  
	  public static void setMiddle(AxisAlignedBB ab, Vec3 v)
	  {
	    v.xCoord = ((ab.minX + ab.maxX) / 2.0D);
	    v.yCoord = ((ab.minY + ab.maxY) / 2.0D);
	    v.zCoord = ((ab.minZ + ab.maxZ) / 2.0D);
	  }
	  
	  public static Vec3 getMiddle(AxisAlignedBB ab)
	  {
	    Vec3 ret = newVec();
	    ret.xCoord = ((ab.minX + ab.maxX) / 2.0D);
	    ret.yCoord = ((ab.minY + ab.maxY) / 2.0D);
	    ret.zCoord = ((ab.minZ + ab.maxZ) / 2.0D);
	    return ret;
	  }
	  
	  public static void incrContract(AxisAlignedBB box, double dx, double dy, double dz)
	  {
	    box.minX += dx;
	    box.minY += dy;
	    box.minZ += dz;
	    box.maxX -= dx;
	    box.maxY -= dy;
	    box.maxZ -= dz;
	  }
	  
	  public static AxisAlignedBB newBox()
	  {
	    return AxisAlignedBB.getBoundingBox(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
	  }
	  
	  @Deprecated
	  public static Vec3 newVec3()
	  {
	    return Vec3.createVectorHelper(0.0D, 0.0D, 0.0D);
	  }
	  
	  public static Vec3 fromDirection(ForgeDirection dir)
	  {
	    return Vec3.createVectorHelper(dir.offsetX, dir.offsetY, dir.offsetZ);
	  }
	  
	  public static void sort(Vec3 min, Vec3 max)
	  {
	    if (min.xCoord > max.xCoord)
	    {
	      double big = min.xCoord;
	      min.xCoord = max.xCoord;
	      max.xCoord = big;
	    }
	    if (min.yCoord > max.yCoord)
	    {
	      double big = min.yCoord;
	      min.yCoord = max.yCoord;
	      max.yCoord = big;
	    }
	    if (min.zCoord > max.zCoord)
	    {
	      double big = min.zCoord;
	      min.zCoord = max.zCoord;
	      max.zCoord = big;
	    }
	  }
	  
	  public static void getPoint(AxisAlignedBB box, byte pointFlags, Vec3 target)
	  {
	    boolean xSide = (pointFlags & 0x1) == 1;
	    boolean ySide = (pointFlags & 0x2) == 2;
	    boolean zSide = (pointFlags & 0x4) == 4;
	    target.xCoord = (xSide ? box.minX : box.maxX);
	    target.yCoord = (ySide ? box.minY : box.maxY);
	    target.zCoord = (zSide ? box.minZ : box.maxZ);
	  }
	  
	  public static AxisAlignedBB flatten(AxisAlignedBB box, ForgeDirection face)
	  {
	    Vec3 min = getMin(box);Vec3 max = getMax(box);
	    Vec3 mv = sign(face) == 1 ? min : max;
	    Vec3 base = mv == min ? max : min;
	    assert (mv != base);
	    if (face.offsetX != 0) {
	      mv.xCoord = base.xCoord;
	    }
	    if (face.offsetY != 0) {
	      mv.yCoord = base.yCoord;
	    }
	    if (face.offsetZ != 0) {
	      mv.zCoord = base.zCoord;
	    }
	    return createAABB(min, max);
	  }
	  
	  public static double getDiagonalLength(AxisAlignedBB ab)
	  {
	    double x = ab.maxX - ab.minX;
	    double y = ab.maxY - ab.minY;
	    double z = ab.maxZ - ab.minZ;
	    return Math.sqrt(x * x + y * y + z * z);
	  }
	  
	  public static Vec3 averageVec(Vec3 a, Vec3 b)
	  {
	    return Vec3.createVectorHelper((a.xCoord + b.xCoord) / 2.0D, (a.yCoord + b.yCoord) / 2.0D, (a.zCoord + b.zCoord) / 2.0D);
	  }
	  
	  public static void assignVecFrom(Vec3 dest, Vec3 orig)
	  {
	    dest.xCoord = orig.xCoord;
	    dest.yCoord = orig.yCoord;
	    dest.zCoord = orig.zCoord;
	  }
	  
	  public static Vec3 incrAdd(Vec3 base, Vec3 add)
	  {
	    base.xCoord += add.xCoord;
	    base.yCoord += add.yCoord;
	    base.zCoord += add.zCoord;
	    return base;
	  }
	  
	  public static Vec3 add(Vec3 a, Vec3 b)
	  {
	    Vec3 ret = Vec3.createVectorHelper(a.xCoord, a.yCoord, a.zCoord);
	    incrAdd(ret, b);
	    return ret;
	  }
	  
	  public static Vec3 incrSubtract(Vec3 base, Vec3 sub)
	  {
	    base.xCoord -= sub.xCoord;
	    base.yCoord -= sub.yCoord;
	    base.zCoord -= sub.zCoord;
	    return base;
	  }
	  
	  public static Vec3 subtract(Vec3 a, Vec3 b)
	  {
	    Vec3 ret = copy(a);
	    incrSubtract(ret, b);
	    return ret;
	  }
	  
	  public static double getAngle(Vec3 a, Vec3 b)
	  {
	    double dot = a.dotProduct(b);
	    double mags = a.lengthVector() * b.lengthVector();
	    double div = dot / mags;
	    if (div > 1.0D) {
	      div = 1.0D;
	    }
	    if (div < -1.0D) {
	      div = -1.0D;
	    }
	    return Math.acos(div);
	  }
	  
	  public static void setAABB(AxisAlignedBB target, Vec3 min, Vec3 max)
	  {
	    target.minX = min.xCoord;
	    target.minY = min.yCoord;
	    target.minZ = min.zCoord;
	    target.maxX = max.xCoord;
	    target.maxY = max.yCoord;
	    target.maxZ = max.zCoord;
	  }
	  
	  public static Vec3 incrScale(Vec3 base, double s)
	  {
	    base.xCoord *= s;
	    base.yCoord *= s;
	    base.zCoord *= s;
	    return base;
	  }
	  
	  public static Vec3 scale(Vec3 base, double s)
	  {
	    Vec3 ret = copy(base);
	    incrScale(ret, s);
	    return ret;
	  }
	  
	  public static Vec3 componentMultiply(Vec3 a, Vec3 b)
	  {
	    return incrComponentMultiply(copy(a), b);
	  }
	  
	  public static Vec3 incrComponentMultiply(Vec3 base, Vec3 scale)
	  {
	    base.xCoord *= scale.xCoord;
	    base.yCoord *= scale.yCoord;
	    base.zCoord *= scale.zCoord;
	    return base;
	  }
	  
	  public static AxisAlignedBB createAABB(Vec3 min, Vec3 max)
	  {
	    double minX = Math.min(min.xCoord, max.xCoord);
	    double minY = Math.min(min.yCoord, max.yCoord);
	    double minZ = Math.min(min.zCoord, max.zCoord);
	    double maxX = Math.max(min.xCoord, max.xCoord);
	    double maxY = Math.max(min.yCoord, max.yCoord);
	    double maxZ = Math.max(min.zCoord, max.zCoord);
	    return AxisAlignedBB.getBoundingBox(minX, minY, minZ, maxX, maxY, maxZ);
	  }
	  
	  public static AxisAlignedBB createAABB(Coord min, Coord max)
	  {
	    return AxisAlignedBB.getBoundingBox(min.x, min.y, min.z, max.x, max.y, max.z);
	  }
	  
	  public static void updateAABB(AxisAlignedBB box, Vec3 min, Vec3 max)
	  {
	    box.minX = min.xCoord;
	    box.minY = min.yCoord;
	    box.minZ = min.zCoord;
	    
	    box.maxX = max.xCoord;
	    box.maxY = max.yCoord;
	    box.maxZ = max.zCoord;
	  }
	  
	  public static void assignBoxFrom(AxisAlignedBB dest, AxisAlignedBB orig)
	  {
	    dest.setBB(orig);
	  }
	  
	  public static void incrAddCoord(AxisAlignedBB box, Vec3 vec)
	  {
	    if (vec.xCoord < box.minX) {
	      box.minX = vec.xCoord;
	    }
	    if (box.maxX < vec.xCoord) {
	      box.maxX = vec.xCoord;
	    }
	    if (vec.yCoord < box.minY) {
	      box.minY = vec.yCoord;
	    }
	    if (box.maxY < vec.yCoord) {
	      box.maxY = vec.yCoord;
	    }
	    if (vec.zCoord < box.minZ) {
	      box.minZ = vec.zCoord;
	    }
	    if (box.maxZ < vec.zCoord) {
	      box.maxZ = vec.zCoord;
	    }
	  }
	  
	  public static Vec3[] getCorners(AxisAlignedBB box)
	  {
	    return new Vec3[] { Vec3.createVectorHelper(box.minX, box.minY, box.minZ), Vec3.createVectorHelper(box.minX, box.maxY, box.minZ), Vec3.createVectorHelper(box.maxX, box.maxY, box.minZ), Vec3.createVectorHelper(box.maxX, box.minY, box.minZ), Vec3.createVectorHelper(box.minX, box.minY, box.maxZ), Vec3.createVectorHelper(box.minX, box.maxY, box.maxZ), Vec3.createVectorHelper(box.maxX, box.maxY, box.maxZ), Vec3.createVectorHelper(box.maxX, box.minY, box.maxZ) };
	  }
	  
	  public static ArrayList<ForgeDirection> getRandomDirections(Random rand)
	  {
	    ArrayList<ForgeDirection> ret = (ArrayList)direction_cache.get();
	    if (ret == null)
	    {
	      ret = new ArrayList(6);
	      for (int i = 0; i < 6; i++) {
	        ret.add(ForgeDirection.getOrientation(i));
	      }
	      direction_cache.set(ret);
	    }
	    Collections.shuffle(ret, rand);
	    return ret;
	  }
	  
	  public static int getAxis(ForgeDirection fd)
	  {
	    if (fd.offsetX != 0) {
	      return 1;
	    }
	    if (fd.offsetY != 0) {
	      return 2;
	    }
	    if (fd.offsetZ != 0) {
	      return 3;
	    }
	    return 0;
	  }
	  
	  public static boolean isZero(Vec3 vec)
	  {
	    return (vec.xCoord == 0.0D) && (vec.yCoord == 0.0D) && (vec.zCoord == 0.0D);
	  }
	  
	  public static double lineDistance(Vec3 lineVec, Vec3 point)
	  {
	    double mag = lineVec.lengthVector();
	    Vec3 nPoint = scale(point, -1.0D);
	    return lineVec.crossProduct(nPoint).lengthVector() / mag;
	  }
	  
	  public static Orientation getOrientation(EntityPlayer player, int side, float hitX, float hitY, float hitZ)
	  {
	    ForgeDirection facing = ForgeDirection.getOrientation(side);
	    double u = 0.5D;double v = 0.5D;
	    switch (facing)
	    {
	    case UNKNOWN: 
	    case DOWN: 
	      u = 1.0F - hitX;
	      v = hitZ;
	      break;
	    case UP: 
	      u = hitX;
	      v = hitZ;
	      break;
	    case NORTH: 
	      u = hitX;
	      v = hitY;
	      break;
	    case SOUTH: 
	      u = 1.0F - hitX;
	      v = hitY;
	      break;
	    case WEST: 
	      u = 1.0F - hitZ;
	      v = hitY;
	      break;
	    case EAST: 
	      u = hitZ;
	      v = hitY;
	    }
	    u -= 0.5D;
	    v -= 0.5D;
	    double angle = Math.toDegrees(Math.atan2(v, u)) + 180.0D;
	    angle = (angle + 45.0D) % 360.0D;
	    int pointy = (int)(angle / 90.0D);
	    pointy = (pointy + 1) % 4;
	    
	    Orientation fo = Orientation.fromDirection(facing);
	    for (int X = 0; X < pointy; X++) {
	      fo = fo.getNextRotationOnFace();
	    }
	    if ((determineOrientation(player) >= 2) && (side < 2))
	    {
	      side = determineOrientation(player);
	      fo = Orientation.fromDirection(ForgeDirection.getOrientation(side).getOpposite());
	      Orientation perfect = fo.pointTopTo(ForgeDirection.UP);
	      if (perfect != Orientation.UNKNOWN) {
	        fo = perfect;
	      }
	    }
	    double dist = Math.max(Math.abs(u), Math.abs(v));
	    if (dist < 0.33D)
	    {
	      Orientation perfect = fo.pointTopTo(ForgeDirection.UP);
	      if (perfect != Orientation.UNKNOWN) {
	        fo = perfect;
	      }
	    }
	    return fo;
	  }
	  
	  public static int sign(ForgeDirection dir)
	  {
	    return dir.offsetX + dir.offsetY + dir.offsetZ;
	  }
	  
	  public static double sum(Vec3 vec)
	  {
	    return vec.xCoord + vec.yCoord + vec.zCoord;
	  }
	  
	  public static ForgeDirection round(Vec3 vec, ForgeDirection not)
	  {
	    if (isZero(vec)) {
	      return ForgeDirection.UNKNOWN;
	    }
	    Vec3 work = newVec();
	    double bestAngle = Double.POSITIVE_INFINITY;
	    ForgeDirection closest = ForgeDirection.UNKNOWN;
	    for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
	      if (dir != not)
	      {
	        work.xCoord = dir.offsetX;
	        work.yCoord = dir.offsetY;
	        work.zCoord = dir.offsetZ;
	        double dot = getAngle(vec, work);
	        if (dot < bestAngle)
	        {
	          bestAngle = dot;
	          closest = dir;
	        }
	      }
	    }
	    return closest;
	  }
	  
	  public static void incrFloor(Vec3 v)
	  {
	    v.xCoord = Math.floor(v.xCoord);
	    v.yCoord = Math.floor(v.yCoord);
	    v.zCoord = Math.floor(v.zCoord);
	  }
	  
	  public static Vec3 floor(Vec3 v)
	  {
	    Vec3 ret = copy(v);
	    incrFloor(ret);
	    return ret;
	  }
	  
	  public static Vec3 normalize(Vec3 v)
	  {
	    double length = v.lengthVector();
	    if (length == 0.0D) {
	      return newVec();
	    }
	    double inv = 1.0D / length;
	    if ((Double.isNaN(inv)) || (Double.isInfinite(inv))) {
	      return newVec();
	    }
	    return scale(v, inv);
	  }
	  
	  public static void include(AxisAlignedBB box, Coord at)
	  {
	    if (at.x < box.minX) {
	      box.minX = at.x;
	    }
	    if (at.x + 1 > box.maxX) {
	      box.maxX = (at.x + 1);
	    }
	    if (at.y < box.minY) {
	      box.minY = at.y;
	    }
	    if (at.y + 1 > box.maxY) {
	      box.maxY = (at.y + 1);
	    }
	    if (at.z < box.minZ) {
	      box.minZ = at.z;
	    }
	    if (at.z + 1 > box.maxZ) {
	      box.maxZ = (at.z + 1);
	    }
	  }
	  
	  public static void include(AxisAlignedBB box, Vec3 at)
	  {
	    if (at.xCoord < box.minX) {
	      box.minX = at.xCoord;
	    }
	    if (at.xCoord > box.maxX) {
	      box.maxX = at.xCoord;
	    }
	    if (at.yCoord < box.minY) {
	      box.minY = at.yCoord;
	    }
	    if (at.yCoord > box.maxY) {
	      box.maxY = at.yCoord;
	    }
	    if (at.zCoord < box.minZ) {
	      box.minZ = at.zCoord;
	    }
	    if (at.zCoord > box.maxZ) {
	      box.maxZ = at.zCoord;
	    }
	  }
	  
	  public static boolean contains(AxisAlignedBB box, Coord at)
	  {
	    return (NumUtil.intersect(box.minX, box.maxX, at.x, at.x + 1)) && (NumUtil.intersect(box.minY, box.maxY, at.y, at.y + 1)) && (NumUtil.intersect(box.minZ, box.maxZ, at.z, at.z + 1));
	  }
	  
	  public static double getVolume(AxisAlignedBB box)
	  {
	    if (box == null) {
	      return 0.0D;
	    }
	    double x = box.maxX - box.minX;
	    double y = box.maxY - box.minY;
	    double z = box.maxZ - box.minZ;
	    double volume = x * y * z;
	    if (volume < 0.0D) {
	      return 0.0D;
	    }
	    return volume;
	  }
}
