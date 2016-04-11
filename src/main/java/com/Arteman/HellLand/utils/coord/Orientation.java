package com.Arteman.HellLand.utils.coord;

import com.Arteman.HellLand.utils.SpaceUtil;
import net.minecraft.util.Vec3;
import net.minecraftforge.common.util.ForgeDirection;

public enum Orientation {
	FACE_DOWN_POINT_SOUTH(ForgeDirection.DOWN, ForgeDirection.SOUTH),  
	FACE_DOWN_POINT_NORTH(ForgeDirection.DOWN, ForgeDirection.NORTH),  
	FACE_DOWN_POINT_EAST(ForgeDirection.DOWN, ForgeDirection.EAST),  
	FACE_DOWN_POINT_WEST(ForgeDirection.DOWN, ForgeDirection.WEST),  
	FACE_UP_POINT_SOUTH(ForgeDirection.UP, ForgeDirection.SOUTH),  
	FACE_UP_POINT_NORTH(ForgeDirection.UP, ForgeDirection.NORTH),  
	FACE_UP_POINT_EAST(ForgeDirection.UP, ForgeDirection.EAST),  
	FACE_UP_POINT_WEST(ForgeDirection.UP, ForgeDirection.WEST),  
	FACE_NORTH_POINT_UP(ForgeDirection.NORTH, ForgeDirection.UP),  
	FACE_NORTH_POINT_DOWN(ForgeDirection.NORTH, ForgeDirection.DOWN),  
	FACE_NORTH_POINT_EAST(ForgeDirection.NORTH, ForgeDirection.EAST),  
	FACE_NORTH_POINT_WEST(ForgeDirection.NORTH, ForgeDirection.WEST),  
	FACE_SOUTH_POINT_UP(ForgeDirection.SOUTH, ForgeDirection.UP),  
	FACE_SOUTH_POINT_DOWN(ForgeDirection.SOUTH, ForgeDirection.DOWN),  
	FACE_SOUTH_POINT_EAST(ForgeDirection.SOUTH, ForgeDirection.EAST),  
	FACE_SOUTH_POINT_WEST(ForgeDirection.SOUTH, ForgeDirection.WEST),  
	FACE_WEST_POINT_UP(ForgeDirection.WEST, ForgeDirection.UP),  
	FACE_WEST_POINT_DOWN(ForgeDirection.WEST, ForgeDirection.DOWN),  
	FACE_WEST_POINT_SOUTH(ForgeDirection.WEST, ForgeDirection.SOUTH),  
	FACE_WEST_POINT_NORTH(ForgeDirection.WEST, ForgeDirection.NORTH),  
	FACE_EAST_POINT_UP(ForgeDirection.EAST, ForgeDirection.UP),  
	FACE_EAST_POINT_DOWN(ForgeDirection.EAST, ForgeDirection.DOWN),  
	FACE_EAST_POINT_SOUTH(ForgeDirection.EAST, ForgeDirection.SOUTH),  
	FACE_EAST_POINT_NORTH(ForgeDirection.EAST, ForgeDirection.NORTH),  
	FACE_UNKNOWN_POINT_UNKNOWN(ForgeDirection.UNKNOWN, ForgeDirection.UNKNOWN);
	
	public static final Orientation UNKNOWN;
	public final ForgeDirection facing;
	public final ForgeDirection top;
	private Orientation nextFaceRotation;
	private Orientation prevFaceRotation;
	private int rotation;
	private Orientation swapped;
	private ForgeDirection[] dirRotations = new ForgeDirection[ForgeDirection.values().length];
	private static Orientation[] valuesCache;
	
	private Orientation(ForgeDirection facing, ForgeDirection top){
		this.facing = facing;
	    this.top = top;
	}
	
	static{
		UNKNOWN = FACE_UNKNOWN_POINT_UNKNOWN;
	    
	    valuesCache = values();
	    for (Orientation o : values()) {
	      o.setup();
	    }
	    for (Orientation o : values()) {
	      o.setupRotation();
	    }
	    for (Orientation o : values()) {
	      for (Orientation t : values()) {
	        if ((o.facing == t.top) && (o.top == t.facing))
	        {
	          o.swapped = t;
	          break;
	        }
	      }
	    }
	    for (Orientation o : values()) {
	      o.setupDirectionRotation();
	    }
	    if (valuesCache.length == 0) {
	      throw new RuntimeException("lolwut");
	    }
	}
	
	private void setup(){
		if (this == UNKNOWN)
	    {
	      this.prevFaceRotation = this;this.nextFaceRotation = this;
	    }
	    this.nextFaceRotation = find(this.facing, this.top.getRotation(this.facing));
	    this.prevFaceRotation = find(this.facing, this.top.getRotation(this.facing).getRotation(this.facing).getRotation(this.facing));
	}
	
	private void setupRotation(){
		if (this == UNKNOWN) {
		      return;
		    }
		    int rcount = 0;
		    Orientation head = fromDirection(this.facing);
		    for (int i = 0; i < 5; i++)
		    {
		      if (head == this) {
		        this.rotation = rcount;
		      }
		      rcount++;
		      head = head.nextFaceRotation;
		    }
	}
	
	private void setupDirectionRotation(){
		for (ForgeDirection dir : dirRotations)
	    {
	      Vec3 v = SpaceUtil.fromDirection(dir);
	      Quaternion.fromOrientation(this).applyRotation(v);
	      this.dirRotations[dir.ordinal()] = SpaceUtil.round(v, ForgeDirection.UNKNOWN);
	    }
	}
	
	private static Orientation find(ForgeDirection f, ForgeDirection t){
		for (Orientation o : valuesCache) {
		      if ((o.facing == f) && (o.top == t)) {
		        return o;
		      }
		    }
		return UNKNOWN;
	}
	
	public Orientation rotateOnFace(int count){
		count %= 4;
	    if (count > 0)
	    {
	      Orientation here = this;
	      while (count > 0)
	      {
	        count--;
	        here = here.nextFaceRotation;
	      }
	      return here;
	    }
	    if (count < 0)
	    {
	      Orientation here = this;
	      while (count < 0)
	      {
	        count++;
	        here = here.prevFaceRotation;
	      }
	      return here;
	    }
	    return this;
	}
	
	public Orientation getNextRotationOnFace(){
		return this.nextFaceRotation;
	}
	
	public Orientation getPrevRotationOnFace(){
		return this.prevFaceRotation;
	}
	
	public Orientation getNextRotationOnTop(){
		return getSwapped().getNextRotationOnFace().getSwapped();
	}
	
	public Orientation getPrevRotationOnTop(){
		return getSwapped().getPrevRotationOnFace().getSwapped();
	}
	
	public Orientation rotateOnTop(int count){
		return getSwapped().rotateOnFace(count).getSwapped();
	}
	
	public static Orientation getOrientation(int index){
		if ((index >= 0) && (index < valuesCache.length)) {
		      return valuesCache[index];
		    }
		    return UNKNOWN;
	}
	
	public static Orientation fromDirection(ForgeDirection dir)
	  {
	    if (dir == ForgeDirection.UNKNOWN) {
	      return UNKNOWN;
	    }
	    return valuesCache[(dir.ordinal() * 4)];
	  }
	  
	  public Orientation pointTopTo(ForgeDirection newTop)
	  {
	    Orientation orient = this;
	    for (int i = 0; i < 4; i++)
	    {
	      if (orient.top == newTop) {
	        return orient;
	      }
	      orient = orient.nextFaceRotation;
	    }
	    return UNKNOWN;
	  }
	  
	  public int getRotation()
	  {
	    return this.rotation;
	  }
	  
	  public void setDiagonalVector(Vec3 vec)
	  {
	    vec.xCoord = this.facing.offsetX;
	    vec.yCoord = this.facing.offsetY;
	    vec.zCoord = this.facing.offsetZ;
	    vec.xCoord += this.top.offsetX;
	    vec.yCoord += this.top.offsetY;
	    vec.zCoord += this.top.offsetZ;
	  }
	  
	  public Vec3 getDiagonalVector()
	  {
	    Vec3 ret = Vec3.createVectorHelper(0.0D, 0.0D, 0.0D);
	    setDiagonalVector(ret);
	    return ret;
	  }
	  
	  public Orientation getSwapped()
	  {
	    return this.swapped;
	  }
	  
	  public ForgeDirection applyRotation(ForgeDirection dir)
	  {
	    return this.dirRotations[dir.ordinal()];
	  }
}
