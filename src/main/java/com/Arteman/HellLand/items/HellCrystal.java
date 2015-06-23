package com.Arteman.HellLand.items;

import java.util.List;

import com.Arteman.HellLand.HellLand;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class HellCrystal extends Item 
{
	private String name = "Hell Crystal";
	
	public HellCrystal()
	{
		this.setUnlocalizedName(HellLand.MODID + ":" + name);
		this.setCreativeTab(HellLand.HellMCTab);
		this.setTextureName(HellLand.MODID + ":" + name);
		GameRegistry.registerItem(this, name);
	}
}
