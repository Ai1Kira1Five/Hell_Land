package com.Arteman.HellLand.utils.helpers;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;

public class ConnectionHelper {
	public static void addBB(AxisAlignedBB bBox, AxisAlignedBB mask, List list){
		if ((bBox != null) && (mask.intersectsWith(bBox))) {
			list.add(bBox);
		}
	}
	
	public static void addBB(float minX, float minY, float minZ, float maxX, float maxY, float maxZ, AxisAlignedBB mask, List list){
		AxisAlignedBB bBox = AxisAlignedBB.getBoundingBox(minX, minY, minZ, maxX, maxY, maxZ);
	    if ((bBox != null) && (mask.intersectsWith(bBox))) {
	    	list.add(bBox);
	    }
	}
	
	public static enum ConnectionTypes{
		POST_THIN,  POST_THICK,  FENCE_RANCH,  FENCE_SPLIT,  FENCE_PANEL,  FENCE_MIDDLE,  WALL_SIDE,  WALL_TOP,  FLOORING,  PANE,  ROPE,  TABLE;
		
		private ConnectionTypes() {}
	}
	
	public static boolean fallbackConnectToSide(Block thisBlock, Block askingBlock, IBlockAccess world, int myX, int myY, int myZ, ForgeDirection direction, int aX, int aY, int aZ, ConnectionTypes type, int aDir){
		switch (type){
		case FENCE_RANCH: 
	    case FENCE_SPLIT: 
	    case FENCE_PANEL: 
	    case FENCE_MIDDLE: 
	    	return (thisBlock.isOpaqueCube()) || (thisBlock.renderAsNormalBlock());
	    case FLOORING:
	    	return false;
	    case PANE:
	    	return (thisBlock.isOpaqueCube()) || (thisBlock.renderAsNormalBlock());
	    case POST_THICK: 
	    case POST_THIN: 
	    case ROPE: 
	    case WALL_SIDE: 
	    	return (thisBlock.isOpaqueCube()) || (thisBlock.renderAsNormalBlock());
	    case WALL_TOP: 
	        return false;
	    case TABLE: 
	        return false;
		}
		return false;
	}
	
	public static boolean fallbackConnectToCorner(Block targetBlock, Block askingBlock, IBlockAccess world, int tX, int tY, int tZ, ForgeDirection first, ForgeDirection second, int myX, int myY, int myZ, ConnectionTypes type, int aDir){
		return false;
	}
}
