package com.Arteman.HellLand.utils;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.ForgeDirection;

public interface IInjectable 
{
	boolean canInjectItems(ForgeDirection from);
	
	int injectItem(ItemStack stack, boolean doAdd, ForgeDirection from);
}
