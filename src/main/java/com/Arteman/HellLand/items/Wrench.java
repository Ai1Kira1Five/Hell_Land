package com.Arteman.HellLand.items;

import com.Arteman.HellLand.HellLand;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemTool;

public class Wrench extends Item
{
	private String name = "wrench";
	
	public Wrench()
	{
		super();
		this.setTextureName(HellLand.MODID + ":" + name);
		this.setUnlocalizedName(name);
		this.setMaxStackSize(1);
		this.setHarvestLevel("wrench", 0);
		this.setFull3D();
		this.setCreativeTab(HellLand.HellMCTab);
		GameRegistry.registerItem(this, name);
	}
}
