package com.Arteman.HellLand.items;

import com.Arteman.HellLand.HellLand;

import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class BloodDrop extends Item 
{
	private String name = "Blood Drop";
	
	public BloodDrop()
	{
		this.setUnlocalizedName(HellLand.MODID + ":" + name);
		this.setCreativeTab(HellLand.HellMCTab);
		this.setTextureName(HellLand.MODID + ":" + name);
		GameRegistry.registerItem(this, name);
	}
}
