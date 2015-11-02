package com.Arteman.HellLand.utils.interfaces;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import com.Arteman.HellLand.utils.BlockHell;
import com.Arteman.HellLand.utils.ItemHell;

public abstract interface IDomain {
	public static final String NBT_BLOCK_FLAVOUR = "f";
	public static final String NBT_BLOCK_PATTERN = "p";
	public static final String NBT_BLOCK_COLOUR = "c";
	public static final String NBT_BLOCK_SHAPE = "s";
	public static final String NBT_BLOCK_ORIENTATION = "o";
	public static final String NBT_ITEM_FLAVOUR = "f";
	public static final String NBT_ITEM_PATTERN = "p";
	public static final String NBT_ITEM_COLOUR = "c";
	public static final String NBT_META_PLACED = "xP";
	public static final String NBT_ITEM_FLUID = "l";
	
	public abstract BlockHell getBlockRefFromSlugs(String[] ArrayOfString);
	
	public abstract ItemHell getItemRefFromSlugs(String[] ArrayOfString);
	
	public abstract void setBlockitemNBT(String[] ArrayOfString, NBTTagCompound NBTTagCompound);
	
	public abstract void setItemNBT(String[] ArrayOfString, NBTTagCompound NBTTagCompound);
	
	public abstract void setTileData(String[] ArrayOfString, World world, int Int1, int Int2, int Int3);
}
