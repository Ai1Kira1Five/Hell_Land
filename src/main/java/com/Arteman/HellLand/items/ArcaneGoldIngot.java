package com.Arteman.HellLand.items;

import com.Arteman.HellLand.HellLand;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class ArcaneGoldIngot extends Item
{
	private String name = "arcane_gold_ingot";
	
	public ArcaneGoldIngot()
	{
		this.setCreativeTab(HellLand.HellMCTabStuff);
		this.setFull3D();
		this.setTextureName(HellLand.MODID + ":" + name);
		this.setUnlocalizedName(HellLand.MODID + ":" + name);
		GameRegistry.registerItem(this, name);
	}
}
