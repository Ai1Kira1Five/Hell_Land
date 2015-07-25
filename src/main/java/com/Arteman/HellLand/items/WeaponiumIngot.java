package com.Arteman.HellLand.items;

import com.Arteman.HellLand.HellLand;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class WeaponiumIngot extends Item 
{
	public String name = "weaponium_ingot";
	
	public WeaponiumIngot()
	{
		this.setCreativeTab(HellLand.HellMCTabStuff);
		this.setTextureName(HellLand.MODID + ":" + name);
		this.setUnlocalizedName(HellLand.MODID + ":" + name);
		GameRegistry.registerItem(this, name);
	}
}
