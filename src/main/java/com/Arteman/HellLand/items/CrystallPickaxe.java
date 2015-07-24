package com.Arteman.HellLand.items;

import com.Arteman.HellLand.HellLand;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.Item.ToolMaterial;

public class CrystallPickaxe extends ItemPickaxe 
{
	private String name = "crystall_pickaxe";
	
	public final ToolMaterial toolMaterial;

	public CrystallPickaxe(ToolMaterial enumToolMaterial)
	{
		super(enumToolMaterial);
		toolMaterial = enumToolMaterial;
		this.setUnlocalizedName(HellLand.MODID + ":" + name);
		this.setTextureName(HellLand.MODID + ":" + name);
		this.setCreativeTab(HellLand.HellMCTab);
		GameRegistry.registerItem(this, name);
	}
}
