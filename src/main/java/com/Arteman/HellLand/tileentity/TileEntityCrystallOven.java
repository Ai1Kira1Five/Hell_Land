package com.Arteman.HellLand.tileentity;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class TileEntityCrystallOven extends TileEntity 
{
	private String localizedName;
	
	private static final int[] slots_top = new int[]{0};
	private static final int[] slots_bottom = new int[]{2, 1};
	private static final int[] slots_side = new int[]{1};
	private ItemStack[] slots = new ItemStack[3];
	
	public int furnaceSpeed = 300;
	
	public int burnTime;
	
	public int currentItemBurnTime;
	
	public int cookTime;
	
	public void setGuiDisplayName(String displayName)
	{
		this.localizedName = displayName;
	}
	
	public String getInventoryName()
	{
		return this.hasCustomInventoryName() ? this.localizedName : "container.ContainerCrystallOven";
	}
	
	public boolean hasCustomInventoryName()
	{
		return this.localizedName != null && this.localizedName.length() > 0;
	}
}
