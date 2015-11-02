package com.Arteman.HellLand.domains;

import java.util.Arrays;
import java.util.List;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import com.Arteman.HellLand.HellLand;
import com.Arteman.HellLand.ModMisc;
import com.Arteman.HellLand.utils.BlockHell;
import com.Arteman.HellLand.utils.ItemHell;
import com.Arteman.HellLand.utils.helpers.ShapeHelper;
import com.Arteman.HellLand.utils.interfaces.IFPDomain;

public class Metal implements IFPDomain{
	public static BlockHell SOLID;
	public static BlockHell SHAPE;
	public static BlockHell ROTSOLID;
	public static BlockHell ROTSHAPE;
	public static List<String> blockPatterns = Arrays.asList(new String[] { "brick", "plate", "bullion", "machined", "panel", "tile" });
	public static List<String> itemPatterns = Arrays.asList(new String[] { "coin", "dust", "gear", "ingot", "nugget", "plate", "rod", "wire" });
	public static List<String> rotPatterns = Arrays.asList(new String[0]);
	public static ItemHell BASIC;

	public List<String> blockPatterns(){
		return blockPatterns;
	}
	
	public List<String> itemPatterns(){
		return itemPatterns;
	}
	
	public List<String> rotPatterns(){
		return rotPatterns;
	}
	
	public ItemHell getItemRefFromSlugs(String[] slugs){
		if((HellLand.registry.hasMetal(slugs[1])) && (itemPatterns.contains(slugs[2]))){
			return BASIC;
		}
		return ModMisc.ITEM_NULL;
	}
	
	public BlockHell getBlockRefFromSlugs(String[] slugs){
		if((HellLand.registry.hasMetal(slugs[1]))){
			if(blockPatterns.contains(slugs[2])){
				if(ShapeHelper.isValidShapeSlug(slugs[3])){
					return SHAPE;
				}
				return SOLID;
			}
			if(rotPatterns.contains(slugs[2])){
				if(ShapeHelper.isValidShapeSlug(slugs[3])){
					return ROTSHAPE;
				}
				return ROTSOLID;
			}
		}
		return ModMisc.BLOCK_NULL;
	}
	
	public void setBlockitemNBT(String[] slugs, NBTTagCompound nbt){
		if(HellLand.registry.hasMetal(slugs[1])){
			nbt.setString("f", slugs[1]);
			if((blockPatterns.contains(slugs[2])) || (rotPatterns.contains(slugs[2]))){
				nbt.setString("p", slugs[2]);
			}
			if(ShapeHelper.isValidShapeSlug(slugs[3])){
				nbt.setByte("s", ShapeHelper.getShapeValue(slugs[3]));
			}
		}
	}
	
	 public void setItemNBT(String[] slugs, NBTTagCompound nbt){
		 if((HellLand.registry.hasMetal(slugs[1])) && (itemPatterns.contains(slugs[2]))){
			 nbt.setString("f", slugs[1]);
		     nbt.setString("p", slugs[2]);
		 }
	 }
	 
	 public void setTileData(String[] slugs, World world, int x, int y, int z) {}
}
