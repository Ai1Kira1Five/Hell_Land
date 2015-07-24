package com.Arteman.HellLand.items;

import java.util.Set;

import com.Arteman.HellLand.HellLand;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.Item.ToolMaterial;

public class CrystallHoe extends ItemHoe 
{
	private String name = "crystall_hoe";
	
	public final ToolMaterial toolMaterial;
	
	public CrystallHoe(ToolMaterial enumToolMaterial) 
	{
		super(enumToolMaterial);
		toolMaterial = enumToolMaterial;
		this.setUnlocalizedName(HellLand.MODID + ":" + name);
		this.setTextureName(HellLand.MODID + ":" + name);
		this.setCreativeTab(HellLand.HellMCTab);
		GameRegistry.registerItem(this, name);
	}

}
