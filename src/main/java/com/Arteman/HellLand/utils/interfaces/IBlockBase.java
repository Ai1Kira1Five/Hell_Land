package com.Arteman.HellLand.utils.interfaces;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public abstract interface IBlockBase {
	public abstract ItemStack getStack(World paramWorld, int paramInt1, int paramInt2, int paramInt3);
}
