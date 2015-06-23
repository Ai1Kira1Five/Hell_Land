package com.Arteman.HellLand;

import com.Arteman.HellLand.items.ModItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabHell extends CreativeTabs 
{

	public CreativeTabHell(String lable)
	{
		super(lable);
	}
	
	@Override
	public Item getTabIconItem() 
	{
		return ModItems.HellCrystal;
	}

}
