package com.Arteman.HellLand;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabHellStuff extends CreativeTabs 
{
	public CreativeTabHellStuff(String lable)
	{
		super(lable);
	}
	
	@Override
	public Item getTabIconItem() 
	{
		return ModItems.Heart;
	}

}
