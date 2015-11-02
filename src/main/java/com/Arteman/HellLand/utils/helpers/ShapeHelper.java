package com.Arteman.HellLand.utils.helpers;

import java.util.Arrays;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ShapeHelper {
	public static final byte POST_THIN = 0;
	public static final byte POST_THICK = 1;
	public static final byte SLAB = 2;
	public static final byte FENCE_RANCH = 4;
	public static final byte FENCE_SPLIT = 5;
	public static final byte FENCE_PANEL = 6;
	public static final byte FENCE_MIDDLE = 7;
	public static final byte STAIR = 8;
	public static final byte STAIR_INT = 9;
	public static final byte STAIR_EXT = 10;
	public static final byte RAMP = 13;
	public static final byte OBLIQUE_INT = 14;
	public static final byte OBLIQUE_EXT = 15;
	public static final byte CORNER_INT = 16;
	public static final byte CORNER_EXT = 17;
	public static final byte PRISM = 18;
	public static final byte SPHERE = 19;
	public static final byte[] supportedShapes = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 10, 9, 11, 12, 13, 14, 16, 15, 17, 18, 19 };
	
	public static String getName(byte shape){
		switch (shape){
		case 0: 
	    case 1: 
	    	return "post";
	    case 2: 
	    	return "slab";
	    case 4: 
	    case 5: 
	    case 6: 
	    case 7: 
	    	return "fence";
	    case 8: 
	    case 9: 
	    case 10: 
	    	return "stair";
	    case 19: 
	    	return "sphere";
	    case 13:
	    	return "ramp";
	    case 14:
	    	return "oblique";
	    case 16:
	    	return "furrow";
	    case 15:
	    	return "slant";
	    case 17:
	    	return "corner";
	    case 18:
	    	return "prism";
		}
		return "shape";
	}
	
	public static int getDefaultOrientation(byte shape, ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata){
		int dir = 0;
	    int rot = 0;
	    if (shape == 2){
	    	dir = net.minecraftforge.common.util.ForgeDirection.OPPOSITES[side];
	    }
	    else if ((shape == 8) || (shape == 13)){
	    	if (side == 0){
	    		rot = 2;
	    	}else if (side == 1){
	    		rot = 0;
	    	}else if (hitY > 0.5D){
	    		rot = 2;
	    	}else{
	    		rot = 0;
	    	}
	    	int facing = MathHelper.floor_double(player.rotationYaw * 4.0F / 360.0F + 0.5D) & 0x3;
	        if (facing == 0){
	        	dir = rot == 2 ? 3 : 3;
	        } else if (facing == 1) {
	        	dir = rot == 2 ? 4 : 4;
	        } else if (facing == 2) {
	        	dir = rot == 2 ? 2 : 2;
	        } else if (facing == 3) {
	        	dir = rot == 2 ? 5 : 5;
	        }
	    }
	    else if ((shape == 10) || (shape == 15) || (shape == 17)){
	    	if (side == 0){
	    		dir = 1;
	    	} else if (side == 1) {
	            dir = 0;
	        } else if (hitY > 0.5D) {
	            dir = 1;
	        } else {
	            dir = 0;
	        }
	    	int facing = MathHelper.floor_double(player.rotationYaw * 4.0F / 360.0F) & 0x3;
	        if (facing == 0) {
	            rot = dir == 1 ? 1 : 2;
	        } else if (facing == 1) {
	            rot = dir == 1 ? 0 : 3;
	        } else if (facing == 2) {
	            rot = dir == 1 ? 3 : 0;
	        } else if (facing == 3) {
	            rot = dir == 1 ? 2 : 1;
	        }
	    }
	    else if ((shape == 9) || (shape == 14)){
	    	if (side == 0) {
	            rot = 2;
	        } else if (side == 1) {
	            rot = 0;
	        } else if (hitY > 0.5D) {
	            rot = 2;
	        } else {
	            rot = 0;
	        }
	        int facing = MathHelper.floor_double(player.rotationYaw * 4.0F / 360.0F) & 0x3;
	        if (facing == 0) {
	            dir = rot == 2 ? 4 : 3;
	        } else if (facing == 1) {
	            dir = rot == 2 ? 2 : 4;
	        } else if (facing == 2) {
	            dir = rot == 2 ? 5 : 2;
	        } else if (facing == 3) {
	            dir = rot == 2 ? 3 : 5;
	        }
	    }
	    else if (shape == 16)
	    {
	    	if (side == 0){
	    		dir = 1;
	    	}else if (side == 1){
	        	dir = 0;
	    	} else if (hitY > 0.5D){
	    		dir = 1;
	    	} else {
	         	dir = 0;
	    	}
	    	int facing = MathHelper.floor_double(player.rotationYaw * 4.0F / 360.0F) & 0x3;
	    	if (facing == 0){
	    		rot = dir == 0 ? 2 : 2;
	    	}else if (facing == 1){
	        	rot = dir == 0 ? 3 : 1;
	    	}else if (facing == 2){
	        	rot = dir == 0 ? 0 : 0;
	    	}else if (facing == 3){
	        	rot = dir == 0 ? 1 : 3;
	    	}
	    }
	    else if (shape == 18){
	    	dir = net.minecraftforge.common.util.ForgeDirection.OPPOSITES[side];
	        rot = 0;
	    }
	    return (dir << 2) + rot;
	}
	
	private static final List<String> validShapeSlugs = Arrays.asList(new String[] 
			{ "postThin",
			  "postThick",
			  "slab",
			  "wall",
			  "fenceRanch",
			  "fenceSplit",
			  "fencePanel",
			  "fenceMiddle",
			  "stair",
			  "stairExt",
			  "stairInt",
			  "ramp",
			  "obliqueInt",
			  "cornerInt",
			  "obliqueInt",
			  "cornerExt",
			  "prism",
			  "sphere" });
	
	public static boolean isValidShapeSlug(String slug){
		return validShapeSlugs.contains(slug);
	}
	
	public static byte getShapeValue(String slug){
		return (byte)validShapeSlugs.indexOf(slug);
	}
}
