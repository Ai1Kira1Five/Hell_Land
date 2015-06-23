package com.Arteman.HellLand.items;

import com.Arteman.HellLand.HellLand;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class BoneBlade extends Item 
{
	private String name = "Bone Blade";
	
	public BoneBlade()
	{
		this.setUnlocalizedName(HellLand.MODID + ":" + name);
		this.setTextureName(HellLand.MODID + ":" + name);
		this.setCreativeTab(HellLand.HellMCTab);
		GameRegistry.registerItem(this, name);
	}
}
