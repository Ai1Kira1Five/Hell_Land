package com.Arteman.HellLand.utils.interfaces;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;

public abstract interface IHellRenderBlock {
	@SideOnly(Side.CLIENT)
	public abstract int getBlockIconLayerCount(IBlockAccess paramIBlockAccess, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5);
	
	@SideOnly(Side.CLIENT)
	public abstract int getItemIconLayerCount(int paramInt, ItemStack paramItemStack);
	
	@SideOnly(Side.CLIENT)
	public abstract IIcon getBlockIcon(IBlockAccess paramIBlockAccess, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6);
	
	@SideOnly(Side.CLIENT)
	public abstract IIcon getItemIcon(int paramInt1, ItemStack paramItemStack, int paramInt2);
	
	@SideOnly(Side.CLIENT)
	public abstract int getBlockColor(IBlockAccess paramIBlockAccess, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6);
	
	@SideOnly(Side.CLIENT)
	public abstract int getItemColor(int paramInt1, ItemStack paramItemStack, int paramInt2);
	
	@SideOnly(Side.CLIENT)
	public abstract int getBlockIconRotation(IBlockAccess paramIBlockAccess, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6);
	
	@SideOnly(Side.CLIENT)
	public abstract int getItemIconRotation(int paramInt1, ItemStack paramItemStack, int paramInt2);
	
	@SideOnly(Side.CLIENT)
	public abstract float getBlockSelfIllum(IBlockAccess paramIBlockAccess, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6);
	
	@SideOnly(Side.CLIENT)
	public abstract float getItemSelfIllum(int paramInt1, ItemStack paramItemStack, int paramInt2);
	
	@SideOnly(Side.CLIENT)
	public abstract void setItemBounds(ItemStack paramItemStack);
	
	public abstract int getOrientation(IBlockAccess paramIBlockAccess, int paramInt1, int paramInt2, int paramInt3);
	
	@SideOnly(Side.CLIENT)
	public abstract boolean shouldMySideByRendererd(IBlockAccess paramIBlockAccess, int paramInt1, int paramInt2, int paramInt3, ForgeDirection paramForgeDirection);
}
