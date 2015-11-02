package com.Arteman.HellLand.utils.coord;

import io.netty.buffer.ByteBuf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import com.Arteman.HellLand.HellLand;
import com.Arteman.HellLand.utils.BlockHelper;
import com.Arteman.HellLand.utils.SpaceUtil;
import com.Arteman.HellLand.utils.Utils;
import com.Arteman.HellLand.utils.interfaces.ISaneCoord;
import com.Arteman.HellLand.utils.tools.ItemUtil;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.IFluidBlock;

public class Coord implements IDataSerializable, ISaneCoord, Comparable<Coord>{
	public World w;
	public int x;
	public int y;
	public int z;
	private static final Random rand = new Random();
	private static final ThreadLocal<Coord> staticCoord = new ThreadLocal();
	public static final Coord ZERO = new Coord(null, 0, 0, 0);
	public static final int NOTIFY_NEIGHBORS = 1;
	public static final int UPDATE = 2;
	public static final int ONLY_UPDATE_SERVERSIDE = 4;
	
	public Coord(World w, int x, int y, int z)
	  {
	    this.w = w;
	    this.x = x;
	    this.y = y;
	    this.z = z;
	  }
	  
	  public Coord(TileEntity te)
	  {
	    this(te.getWorldObj(), te.xCoord, te.yCoord, te.zCoord);
	  }
	  
	  public Coord(Entity ent)
	  {
	    this(ent.worldObj, Math.floor(ent.posX), ent.posY + ent.yOffset, Math.floor(ent.posZ));
	  }
	  
	  public Coord(World w, double x, double y, double z)
	  {
	    this(w, (int)x, (int)y, (int)z);
	  }
	  
	  public Coord(World w, Vec3 v)
	  {
	    this(w, v.xCoord, v.yCoord, v.zCoord);
	  }
	  
	  @Deprecated
	  public Coord(World w, MovingObjectPosition mop)
	  {
	    this(w, mop.blockX, mop.blockY, mop.blockZ);
	  }
	  
	  public static Coord fromMop(World world, MovingObjectPosition mop)
	  {
	    if ((mop.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY) && (mop.entityHit != null)) {
	      return new Coord(mop.entityHit);
	    }
	    return new Coord(world, mop.blockX, mop.blockY, mop.blockZ);
	  }
	  
	  public Coord(Chunk chunk)
	  {
	    this(chunk.worldObj, chunk.xPosition * 16, 0, chunk.zPosition * 16);
	  }
	  
	  public World w()
	  {
	    return this.w;
	  }
	  
	  public int x()
	  {
	    return this.x;
	  }
	  
	  public int y()
	  {
	    return this.y;
	  }
	  
	  public int z()
	  {
	    return this.z;
	  }
	  
	  public static Coord tryLoad(World world, Object o)
	  {
	    if ((o instanceof Coord)) {
	      return (Coord)o;
	    }
	    if ((o instanceof Vec3))
	    {
	      Vec3 vec = (Vec3)o;
	      return new Coord(world, vec.xCoord, vec.yCoord, vec.zCoord);
	    }
	    if ((o instanceof Entity))
	    {
	      Entity e = (Entity)o;
	      return new Coord(e);
	    }
	    if ((o instanceof TileEntity))
	    {
	      TileEntity te = (TileEntity)o;
	      return new Coord(te);
	    }
	    return null;
	  }
	  
	  public static Coord of(int x, int y, int z)
	  {
	    return of((World)null, x, y, z);
	  }
	  
	  public static Coord of(double x, double y, double z)
	  {
	    return of((World)null, (int)x, (int)y, (int)z);
	  }
	  
	  public static Coord of(World w, int x, int y, int z)
	  {
	    Coord ret = (Coord)staticCoord.get();
	    if (ret == null)
	    {
	      ret = new Coord(w, x, y, z);
	      staticCoord.set(ret);
	      return ret;
	    }
	    ret.set(w, x, y, z);
	    return ret;
	  }
	  
	  public String toString()
	  {
	    String ret = "(" + this.x + ", " + this.y + ", " + this.z + ")";
	    if (this.w == null)
	    {
	      ret = ret + " null world";
	    }
	    else
	    {
	      ret = ret + " (Dimension " + Utils.getWorldDimension(this.w) + ")";
	      if (!blockExists())
	      {
	        ret = ret + " not loaded";
	      }
	      else
	      {
	        Block b = getBlock();
	        if (b != null)
	        {
	          ret = ret + " " + getBlock().getClass().getSimpleName();
	          ret = ret + " " + b.getUnlocalizedName();
	        }
	        else
	        {
	          ret = ret + " null";
	        }
	        int md = getMd();
	        ret = ret + "#" + md;
	        TileEntity te = getTE();
	        if (te != null) {
	          ret = ret + " " + te.getClass().getSimpleName();
	        }
	      }
	    }
	    return ret;
	  }
	  
	  public String toShortString()
	  {
	    String ws = "[" + Utils.getWorldDimension(this.w) + "]";
	    return ws + " " + this.x + "," + this.y + "," + this.z;
	  }
	  
	  public void set(World w, int x, int y, int z)
	  {
	    this.w = w;
	    this.x = x;
	    this.y = y;
	    this.z = z;
	  }
	  
	  public void set(ChunkCoordinates cc)
	  {
	    set(this.w, cc.posX, cc.posY, cc.posZ);
	  }
	  
	  public void set(Coord c)
	  {
	    set(c.w, c.x, c.y, c.z);
	  }
	  
	  public void set(Vec3 v)
	  {
	    set(this.w, (int)v.xCoord, (int)v.yCoord, (int)v.zCoord);
	  }
	  
	  public void set(DeltaCoord dc)
	  {
	    set(this.w, dc.x, dc.y, dc.z);
	  }
	  
	  public void set(TileEntity te)
	  {
	    set(te.getWorldObj(), te.xCoord, te.yCoord, te.zCoord);
	  }
	  
	  public int hashCode()
	  {
	    return (this.x * 11 % 71 << 7) + this.z * 7 % 479 + this.y;
	  }
	  
	  public boolean equals(Object obj)
	  {
	    if ((obj instanceof Coord))
	    {
	      Coord other = (Coord)obj;
	      return (this.x == other.x) && (this.y == other.y) && (this.z == other.z) && (this.w == other.w);
	    }
	    return false;
	  }
	  
	  public Coord copy()
	  {
	    return new Coord(this.w, this.x, this.y, this.z);
	  }
	  
	  public int get(int axis)
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
	  
	  public void set(int axis, int value)
	  {
	    switch (axis)
	    {
	    case 0: 
	      this.x = value;return;
	    case 1: 
	      this.y = value;return;
	    case 2: 
	      this.z = value;return;
	    }
	    throw new RuntimeException("Invalid argument");
	  }
	  
	  public Vec3 createVector()
	  {
	    return Vec3.createVectorHelper(this.x, this.y, this.z);
	  }
	  
	  public MovingObjectPosition createMop(ForgeDirection side, Vec3 hitVec)
	  {
	    return new MovingObjectPosition(this.x, this.y, this.z, side.ordinal(), hitVec);
	  }
	  
	  public boolean parity()
	  {
	    return (this.x + this.y + this.z & 0x1) == 0;
	  }
	  
	  public int seed()
	  {
	    return (this.x << 4 + this.z << 8) + this.y;
	  }
	  
	  public DeltaCoord difference(Coord b)
	  {
	    return new DeltaCoord(this.x - b.x, this.y - b.y, this.z - b.z);
	  }
	  
	  public DeltaCoord asDeltaCoord()
	  {
	    return new DeltaCoord(this.x, this.y, this.z);
	  }
	  
	  public double distance(Coord o)
	  {
	    return Math.sqrt(distanceSq(o));
	  }
	  
	  public int distanceSq(Coord o)
	  {
	    if (o == null) {
	      return 0;
	    }
	    int dx = this.x - o.x;int dy = this.y - o.y;int dz = this.z - o.z;
	    return dx * dx + dy * dy + dz * dz;
	  }
	  
	  public int distanceManhatten(Coord o)
	  {
	    if (o == null) {
	      return 0;
	    }
	    int dx = this.x - o.x;int dy = this.y - o.y;int dz = this.z - o.z;
	    return Math.abs(dx) + Math.abs(dy) + Math.abs(dz);
	  }
	  
	  public ArrayList<Coord> getNeighborsAdjacent()
	  {
	    ArrayList<Coord> ret = new ArrayList(6);
	    for (DeltaCoord d : DeltaCoord.directNeighbors) {
	      ret.add(add(d));
	    }
	    return ret;
	  }
	  
	  public <T> Iterable<T> getAdjacentTEs(Class<T> clazz)
	  {
	    ArrayList<T> ret = new ArrayList(6);
	    for (Coord n : getNeighborsAdjacent())
	    {
	      T toAdd = n.getTE(clazz);
	      if (toAdd != null) {
	        ret.add(toAdd);
	      }
	    }
	    return ret;
	  }
	  
	  public ArrayList<Coord> getNeighborsDiagonal()
	  {
	    ArrayList<Coord> ret = new ArrayList(26);
	    for (int dx = -1; dx <= 1; dx++) {
	      for (int dy = -1; dy <= 1; dy++) {
	        for (int dz = -1; dz <= 1; dz++) {
	          if ((dx != 0) || (dy != 0) || (dz != 0)) {
	            ret.add(add(dx, dy, dz));
	          }
	        }
	      }
	    }
	    return ret;
	  }
	  
	  public ArrayList<Coord> getRandomNeighborsAdjacent()
	  {
	    ArrayList<Coord> ret = getNeighborsAdjacent();
	    Collections.shuffle(ret);
	    return ret;
	  }
	  
	  public ArrayList<Coord> getRandomNeighborsDiagonal()
	  {
	    ArrayList<Coord> ret = getNeighborsDiagonal();
	    Collections.shuffle(ret);
	    return ret;
	  }
	  
	  public Coord getSingleRandomNeighborAdjacent()
	  {
	    int r = rand.nextInt(DeltaCoord.directNeighbors.length);
	    return add(DeltaCoord.directNeighbors[r]);
	  }
	  
	  public Coord[] getNeighborsInPlane(int side)
	  {
	    switch (side)
	    {
	    case 0: 
	    case 1: 
	      return new Coord[] { add(-1, 0, 0), add(1, 0, 0), add(0, 0, -1), add(0, 0, 1) };
	    case 2: 
	    case 3: 
	      return new Coord[] { add(-1, 0, 0), add(1, 0, 0), add(0, -1, 0), add(0, 1, 0) };
	    case 4: 
	    case 5: 
	      return new Coord[] { add(0, 0, -1), add(0, 0, 1), add(0, -1, 0), add(0, 1, 0) };
	    }
	    return null;
	  }
	  
	  public Coord[] getNeighborsOutOfPlane(int side)
	  {
	    switch (side)
	    {
	    case 0: 
	    case 1: 
	      return new Coord[] { add(0, -1, 0), add(0, 1, 0) };
	    case 2: 
	    case 3: 
	      return new Coord[] { add(0, 0, -1), add(0, 0, 1) };
	    case 4: 
	    case 5: 
	      return new Coord[] { add(-1, 0, 0), add(1, 0, 0) };
	    }
	    return null;
	  }
	  
	  public int compareTo(Coord o)
	  {
	    int d = this.y - o.y;
	    if (d == 0)
	    {
	      d = this.x - o.x;
	      if (d == 0) {
	        d = this.z - o.z;
	      }
	    }
	    return d;
	  }
	  
	  public boolean isSubmissiveTo(Coord o)
	  {
	    return (this.y < o.y) || (this.x < o.x) || (this.z < o.z);
	  }
	  
	  public boolean inside(Coord lower, Coord upper)
	  {
	    return (lower.lesserOrEqual(this)) && (lesserOrEqual(upper));
	  }
	  
	  public boolean lesserOrEqual(Coord o)
	  {
	    return (this.x <= o.x) && (this.y <= o.y) && (this.z <= o.z);
	  }
	  
	  public void setWorld(World newWorld)
	  {
	    this.w = newWorld;
	  }
	  
	  public Coord add(DeltaCoord d)
	  {
	    return add(d.x, d.y, d.z);
	  }
	  
	  public Coord add(ForgeDirection d)
	  {
	    return add(d.offsetX, d.offsetY, d.offsetZ);
	  }
	  
	  public Coord add(int x, int y, int z)
	  {
	    return new Coord(this.w, this.x + x, this.y + y, this.z + z);
	  }
	  
	  public Coord add(Vec3 v)
	  {
	    return new Coord(this.w, this.x + v.xCoord, this.y + v.yCoord, this.z + v.zCoord);
	  }
	  
	  public Coord center(Coord o)
	  {
	    return new Coord(this.w, (this.x + o.x) / 2, (this.y + o.y) / 2, (this.z + o.z) / 2);
	  }
	  
	  public Vec3 centerVec(Coord o)
	  {
	    return Vec3.createVectorHelper((this.x + o.x) / 2.0D, (this.y + o.y) / 2.0D, (this.z + o.z) / 2.0D);
	  }
	  
	  public Coord towardSide(int side)
	  {
	    switch (side)
	    {
	    case 0: 
	      this.y -= 1;
	      break;
	    case 1: 
	      this.y += 1;
	      break;
	    case 2: 
	      this.z -= 1;
	      break;
	    case 3: 
	      this.z += 1;
	      break;
	    case 4: 
	      this.x -= 1;
	      break;
	    case 5: 
	      this.x += 1;
	    }
	    return this;
	  }
	  
	  public Coord adjust(DeltaCoord dc)
	  {
	    this.x += dc.x;
	    this.y += dc.y;
	    this.z += dc.z;
	    return this;
	  }
	  
	  public Coord adjust(ForgeDirection dc)
	  {
	    this.x += dc.offsetX;
	    this.y += dc.offsetY;
	    this.z += dc.offsetZ;
	    return this;
	  }
	  
	  public Coord adjust(int dx, int dy, int dz)
	  {
	    this.x += dx;
	    this.y += dy;
	    this.z += dz;
	    return this;
	  }
	  
	  public void markBlockForUpdate()
	  {
	    this.w.markBlockForUpdate(this.x, this.y, this.z);
	  }
	  
	  public void redraw()
	  {
	    if (this.w.isRemote) {
	      this.w.markBlockForUpdate(this.x, this.y, this.z);
	    }
	  }
	  
	  public void notifyNeighbors()
	  {
	    this.w.notifyBlocksOfNeighborChange(this.x, this.y, this.z, getBlock());
	  }
	  
	  public void updateLight()
	  {
	    this.w.func_147451_t(this.x, this.y, this.z);
	  }
	  
	  public void updateBlockLight()
	  {
	    this.w.updateLightByType(EnumSkyBlock.Block, this.x, this.y, this.z);
	  }
	  
	  public int getCombinedLight()
	  {
	    return this.w.getBlockLightValue(this.x, this.y, this.z);
	  }
	  
	  public int getLightLevelBlock()
	  {
	    return this.w.getSavedLightValue(EnumSkyBlock.Block, this.x, this.y, this.z);
	  }
	  
	  public int getLightLevelSky()
	  {
	    return this.w.getSavedLightValue(EnumSkyBlock.Sky, this.x, this.y, this.z);
	  }
	  
	  public void setLightLevelBlock(int light)
	  {
	    getChunk().setLightValue(EnumSkyBlock.Block, this.x & 0xF, this.y & 0xF, this.z & 0xF, light);
	  }
	  
	  public void setLightLevelSky(int light)
	  {
	    getChunk().setLightValue(EnumSkyBlock.Sky, this.x & 0xF, this.y & 0xF, this.z & 0xF, light);
	  }
	  
	  public void setTE(TileEntity te)
	  {
	    this.w.setTileEntity(this.x, this.y, this.z, te);
	  }
	  
	  public void rmTE()
	  {
	    this.w.removeTileEntity(this.x, this.y, this.z);
	  }
	  
	  public TileEntity getTE()
	  {
	    if (this.w == null) {
	      return null;
	    }
	    if (!blockExists()) {
	      return null;
	    }
	    return this.w.getTileEntity(this.x, this.y, this.z);
	  }
	  
	  public TileEntity forceGetTE()
	  {
	    if (this.w == null) {
	      return null;
	    }
	    return this.w.getTileEntity(this.x, this.y, this.z);
	  }
	  
	  public boolean blockHasTE()
	  {
	    return getBlock().hasTileEntity(getMd());
	  }
	  
	  public <T> T getTE(Class<T> clazz)
	  {
	    TileEntity te = getTE();
	    if (clazz.isInstance(te)) {
	      return (T) te;
	    }
	    return null;
	  }
	  
	  public Chunk getChunk()
	  {
	    return this.w.getChunkFromBlockCoords(this.x, this.z);
	  }
	  
	  public BiomeGenBase getBiome()
	  {
	    return this.w.getBiomeGenForCoords(this.x, this.z);
	  }
	  
	  public Block getBlock()
	  {
	    return getId();
	  }
	  
	  public Block getId()
	  {
	    return this.w.getBlock(this.x, this.y, this.z);
	  }
	  
	  public int getMd()
	  {
	    return this.w.getBlockMetadata(this.x, this.y, this.z);
	  }
	  
	  public int getRawId()
	  {
	    return Block.getIdFromBlock(this.w.getBlock(this.x, this.y, this.z));
	  }
	  
	  public boolean isAir()
	  {
	    return this.w.isAirBlock(this.x, this.y, this.z);
	  }
	  
	  public boolean isSolid()
	  {
	    Block b = getBlock();
	    if (b == null) {
	      return false;
	    }
	    return b.isNormalCube(this.w, this.x, this.y, this.z);
	  }
	  
	  public float getHardness()
	  {
	    Block b = getBlock();
	    if (b == null) {
	      return 0.0F;
	    }
	    return b.getBlockHardness(this.w, this.x, this.y, this.z);
	  }
	  
	  public boolean isBedrock()
	  {
	    return getHardness() < 0.0F;
	  }
	  
	  public boolean isSolidOnSide(int side)
	  {
	    return this.w.isSideSolid(this.x, this.y, this.z, ForgeDirection.getOrientation(side));
	  }
	  
	  public boolean isSolidOnSide(ForgeDirection side)
	  {
	    return this.w.isSideSolid(this.x, this.y, this.z, side);
	  }
	  
	  public boolean isBlockBurning()
	  {
	    Block b = getBlock();
	    if (b == null) {
	      return false;
	    }
	    return (b == Blocks.fire) || (b.isBurning(this.w, this.x, this.y, this.z));
	  }
	  
	  public boolean blockExists()
	  {
	    if (this.w == null) {
	      return false;
	    }
	    return this.w.blockExists(this.x, this.y, this.z);
	  }
	  
	  public boolean isReplacable()
	  {
	    Block b = getBlock();
	    if (b == null) {
	      return true;
	    }
	    return b.isReplaceable(this.w, this.x, this.y, this.z);
	  }
	  
	  public boolean isTop()
	  {
	    return this.w.getHeightValue(this.x, this.z) == this.y;
	  }
	  
	  public int getColumnHeight()
	  {
	    return this.w.getHeightValue(this.x, this.z);
	  }
	  
	  public boolean canBeSeenThrough()
	  {
	    return getBlock().getLightOpacity() == 0;
	  }
	  
	  public boolean canSeeSky()
	  {
	    Coord skyLook = copy();
	    for (int i = this.y + 1; i < this.w.getHeight(); i++)
	    {
	      skyLook.y = i;
	      if (!skyLook.canBeSeenThrough()) {
	        return false;
	      }
	    }
	    return true;
	  }
	  
	  public boolean is(Block b)
	  {
	    return getId() == b;
	  }
	  
	  public boolean is(Block b, int md)
	  {
	    return (getId() == b) && (getMd() == md);
	  }
	  
	  public boolean setId(Block block, boolean notify)
	  {
	    int notifyFlag = notify ? 3 : 0;
	    return this.w.setBlock(this.x, this.y, this.z, block, 0, notifyFlag);
	  }
	  
	  public boolean setMd(int md, boolean notify)
	  {
	    int notifyFlag = notify ? 3 : 0;
	    return this.w.setBlockMetadataWithNotify(this.x, this.y, this.z, md, notifyFlag);
	  }
	  
	  public boolean setIdMd(Block block, int md, boolean notify)
	  {
	    int notifyFlag = notify ? 3 : 0;
	    return this.w.setBlock(this.x, this.y, this.z, block, md, notifyFlag);
	  }
	  
	  public void setAir()
	  {
	    this.w.setBlockToAir(this.x, this.y, this.z);
	  }
	  
	  public boolean setId(Block id)
	  {
	    return setId(id, true);
	  }
	  
	  public boolean setMd(int md)
	  {
	    return setMd(md, true);
	  }
	  
	  public void notifyBlockChange()
	  {
	    this.w.notifyBlockChange(this.x, this.y, this.z, getId());
	  }
	  
	  public void writeToNBT(String prefix, NBTTagCompound tag)
	  {
	    tag.setInteger(prefix + "x", this.x);
	    tag.setInteger(prefix + "y", this.y);
	    tag.setInteger(prefix + "z", this.z);
	  }
	  
	  public void readFromNBT(String prefix, NBTTagCompound tag)
	  {
	    this.x = tag.getInteger(prefix + "x");
	    this.y = tag.getInteger(prefix + "y");
	    this.z = tag.getInteger(prefix + "z");
	  }
	  
	  public void writeToStream(ByteArrayDataOutput dos)
	  {
	    dos.writeInt(this.x);
	    dos.writeInt(this.y);
	    dos.writeInt(this.z);
	  }
	  
	  public void writeToStream(ByteBuf dos)
	  {
	    dos.writeInt(this.x);
	    dos.writeInt(this.y);
	    dos.writeInt(this.z);
	  }
	  
	  public void readFromStream(ByteArrayDataInput dis)
	  {
	    this.x = dis.readInt();
	    this.y = dis.readInt();
	    this.z = dis.readInt();
	  }
	  
	  public void readFromStream(ByteBuf dis)
	  {
	    this.x = dis.readInt();
	    this.y = dis.readInt();
	    this.z = dis.readInt();
	  }
	  
	  public IDataSerializable serialize(String prefix, DataHelper data)
	    throws IOException
	  {
	    this.x = ((Integer)data.asSameShare(prefix + "x").put(Integer.valueOf(this.x))).intValue();
	    this.y = ((Integer)data.asSameShare(prefix + "y").put(Integer.valueOf(this.y))).intValue();
	    this.z = ((Integer)data.asSameShare(prefix + "z").put(Integer.valueOf(this.z))).intValue();
	    return this;
	  }
	  
	  public void mark()
	  {
	    World use_world = this.w;
	    use_world.spawnParticle("reddust", this.x + 0.5D, this.y + 0.5D, this.z + 0.5D, 0.0D, 0.0D, 0.0D);
	  }
	  
	  public boolean remote()
	  {
	    return this.w.isRemote;
	  }
	  
	  public boolean local()
	  {
	    return !this.w.isRemote;
	  }
	  
	  public Entity spawnItem(ItemStack is)
	  {
	    Entity ent = new EntityItem(this.w, this.x + 0.5D, this.y + 0.5D, this.z + 0.5D, is);
	    Item item = is.getItem();
	    if (item.hasCustomEntity(is)) {
	      ent = item.createEntity(this.w, ent, is);
	    }
	    this.w.spawnEntityInWorld(ent);
	    return ent;
	  }
	  
	  public Entity spawnItem(Item it)
	  {
	    return spawnItem(new ItemStack(it));
	  }
	  
	  public AxisAlignedBB getCollisionBoundingBoxFromPool()
	  {
	    Block b = getBlock();
	    if (b == null) {
	      return null;
	    }
	    return b.getCollisionBoundingBoxFromPool(this.w, this.x, this.y, this.z);
	  }
	  
	  @SideOnly(Side.CLIENT)
	  public AxisAlignedBB getSelectedBoundingBoxFromPool()
	  {
	    Block b = getBlock();
	    if (b == null) {
	      return null;
	    }
	    return b.getSelectedBoundingBoxFromPool(this.w, this.x, this.y, this.z);
	  }
	  
	  public AxisAlignedBB getBlockBounds()
	  {
	    Block block = getBlock();
	    double minX = block.getBlockBoundsMinX();
	    double maxX = block.getBlockBoundsMaxX();
	    double minY = block.getBlockBoundsMinY();
	    double maxY = block.getBlockBoundsMaxY();
	    double minZ = block.getBlockBoundsMinZ();
	    double maxZ = block.getBlockBoundsMaxZ();
	    return AxisAlignedBB.getBoundingBox(this.x + minX, this.y + minY, this.z + minZ, this.x + maxX, this.y + maxY, this.z + maxZ);
	  }
	  
	  public static AxisAlignedBB aabbFromRange(Coord min, Coord max)
	  {
	    sort(min, max);
	    return AxisAlignedBB.getBoundingBox(min.x, min.y, min.z, max.x, max.y, max.z);
	  }
	  
	  public void scheduleUpdate(int delay)
	  {
	    this.w.scheduleBlockUpdate(this.x, this.y, this.z, getId(), delay);
	  }
	  
	  public void setAsEntityLocation(Entity ent)
	  {
	    ent.worldObj = this.w;
	    ent.setLocationAndAngles(this.x + 0.5D, this.y, this.z + 0.5D, ent.rotationYaw, ent.rotationPitch);
	  }
	  
	  public void setAsEntityLocationUnsafe(Entity ent)
	  {
	    ent.worldObj = this.w;
	    ent.posX = (this.x + 0.5D);
	    ent.posY = this.y;
	    ent.posZ = (this.z + 0.5D);
	  }
	  
	  public void setAsTileEntityLocation(TileEntity te)
	  {
	    te.setWorldObj(this.w);
	    te.xCoord = this.x;
	    te.yCoord = this.y;
	    te.zCoord = this.z;
	  }
	  
	  public void setAsVector(Vec3 vec)
	  {
	    vec.xCoord = this.x;
	    vec.yCoord = this.y;
	    vec.zCoord = this.z;
	  }
	  
	  public Vec3 toVector()
	  {
	    Vec3 vec = SpaceUtil.newVec();
	    setAsVector(vec);
	    return vec;
	  }
	  
	  public Vec3 toMiddleVector()
	  {
	    Vec3 vec = toVector();
	    vec.xCoord += 0.5D;
	    vec.yCoord += 0.5D;
	    vec.zCoord += 0.5D;
	    return vec;
	  }
	  
	  public static void sort(Coord lower, Coord upper)
	  {
	    Coord a = lower.copy();
	    Coord b = upper.copy();
	    lower.x = Math.min(a.x, b.x);
	    lower.y = Math.min(a.y, b.y);
	    lower.z = Math.min(a.z, b.z);
	    upper.x = Math.max(a.x, b.x);
	    upper.y = Math.max(a.y, b.y);
	    upper.z = Math.max(a.z, b.z);
	  }
	  
	  public void moveToTopBlock()
	  {
	    for (int dx = -1; dx <= 1; dx++) {
	      for (int dz = -1; dz <= 1; dz++) {
	        this.y = Math.max(this.y, this.w.getTopSolidOrLiquidBlock(this.x + dx, this.z + dz));
	      }
	    }
	  }
	  
	  public boolean isPowered()
	  {
	    return this.w.getBlockPowerInput(this.x, this.y, this.z) > 0;
	  }
	  
	  public boolean isWeaklyPowered()
	  {
	    return this.w.isBlockIndirectlyGettingPowered(this.x, this.y, this.z);
	  }
	  
	  public int getPowerInput()
	  {
	    return this.w.getBlockPowerInput(this.x, this.y, this.z);
	  }
	  
	  public static void iterateCube(Coord a, Coord b, ICoordFunction func)
	  {
	    a = a.copy();
	    b = b.copy();
	    sort(a, b);
	    Coord here = a.copy();
	    for (int x = a.x; x <= b.x; x++) {
	      for (int y = a.y; y <= b.y; y++) {
	        for (int z = a.z; z <= b.z; z++)
	        {
	          here.set(here.w, x, y, z);
	          func.handle(here);
	        }
	      }
	    }
	  }
	  
	  public static void iterateEmptyBox(Coord min, Coord max, ICoordFunction func)
	  {
	    min = min.copy();
	    max = max.copy();
	    sort(min, max);
	    Coord here = min.copy();
	    Coord food = here.copy();
	    for (here.y = min.y; here.y <= max.y; here.y += 1) {
	      if ((here.y == min.y) || (here.y == max.y)) {
	        for (here.x = min.x; here.x <= max.x;)
	        {
	          for (here.z = min.z; here.z <= max.z; here.z += 1)
	          {
	            food.set(here);
	            func.handle(food);
	          }
	          here.x += 1; continue;
	        }
	      }
	    }
	  }
	  
	  public static void iterateChunks(Coord min, Coord max, ICoordFunction func)
	  {
	    min = min.copy();
	    max = max.copy();
	    sort(min, max);
	    Coord here = min.copy();
	    for (int x = min.x; x <= max.x; x += 16) {
	      for (int z = min.z; z <= max.z; z += 16)
	      {
	        here.x = x;
	        here.z = z;
	        func.handle(here);
	      }
	    }
	  }
	  
	  public static void drawLine(Coord start, Coord end, ICoordFunction func)
	  {
	    Coord at = start.copy();
	    double len = start.distance(end);
	    double t = 0.0D;
	    double dt = 1.0D / len;
	    int elsewhere = (int)(len * 3.0D);
	    Coord last = end.add(elsewhere, elsewhere, elsewhere);
	    DeltaCoord d = end.difference(start);
	    while (t <= 1.0D)
	    {
	      at.x = ((int)(d.x * t) + start.x);
	      at.y = ((int)(d.y * t) + start.y);
	      at.z = ((int)(d.z * t) + start.z);
	      if (!at.equals(last))
	      {
	        func.handle(at);
	        last.set(at);
	      }
	      t += dt;
	    }
	  }
	  
	  public boolean hasSimilarCoordinate(Coord other)
	  {
	    return (this.x == other.x) || (this.y == other.y) || (this.z == other.z);
	  }
	  
	  public int getComparatorOverride(ForgeDirection side)
	  {
	    Block b = getBlock();
	    if ((b == null) || (!b.hasComparatorInputOverride())) {
	      return 0;
	    }
	    return b.getComparatorInputOverride(this.w, this.x, this.y, this.z, side.ordinal());
	  }
	  
	  private static Vec3 nullVec = Vec3.createVectorHelper(0.0D, 0.0D, 0.0D);
	  private static boolean spam = false;
	  
	  public ItemStack getPickBlock(ForgeDirection dir)
	  {
	    Block b = getBlock();
	    if (b == null) {
	      return null;
	    }
	    MovingObjectPosition mop = createMop(dir, nullVec);
	    try
	    {
	      return b.getPickBlock(mop, this.w, this.x, this.y, this.z);
	    }
	    catch (NoSuchMethodError t)
	    {
	      if (!spam) {
	        spam = true;
	      }
	    }
	    return BlockHelper.getPlacingItem(b, mop, this.w, this.x, this.y, this.z);
	  }
	  
	  public ItemStack getPickBlock(MovingObjectPosition mop)
	  {
	    Block b = getBlock();
	    if (b == null) {
	      return null;
	    }
	    return b.getPickBlock(mop, this.w, this.x, this.y, this.z);
	  }
	  
	  public ItemStack getBrokenBlock()
	  {
	    Block b = getBlock();
	    if (b == null) {
	      return null;
	    }
	    ArrayList<ItemStack> dropped = b.getDrops(this.w, this.x, this.y, this.z, getMd(), 0);
	    if ((dropped == null) || (dropped.isEmpty())) {
	      return null;
	    }
	    ItemStack main = (ItemStack)dropped.remove(0);
	    for (int i = 0; i < dropped.size(); i++)
	    {
	      ItemStack other = (ItemStack)dropped.get(i);
	      if (!ItemUtil.couldMerge(main, other)) {
	        return null;
	      }
	      main.stackSize += other.stackSize;
	    }
	    return main;
	  }
	  
	  public Fluid getFluid()
	  {
	    Block b = getBlock();
	    if ((b instanceof IFluidBlock)) {
	      return ((IFluidBlock)b).getFluid();
	    }
	    if ((b == Blocks.water) || (b == Blocks.flowing_water)) {
	      return FluidRegistry.WATER;
	    }
	    if ((b == Blocks.lava) || (b == Blocks.flowing_lava)) {
	      return FluidRegistry.LAVA;
	    }
	    return null;
	  }
	  
	  public int getDimensionID()
	  {
	    return this.w.provider.dimensionId;
	  }
	  
	  public void breakBlock()
	  {
	    Block b = getBlock();
	    int md = getMd();
	    b.dropBlockAsItem(this.w, this.x, this.y, this.z, md, 0);
	  }
	  
	  public boolean isAt(TileEntity te)
	  {
	    return (this.x == te.xCoord) && (this.y == te.yCoord) && (this.z == te.zCoord) && (this.w == te.getWorldObj());
	  }
	  
	  public boolean isNormalCube()
	  {
	    return this.w.getBlock(this.x, this.y, this.z).isNormalCube(this.w, this.x, this.y, this.z);
	  }
}
