package com.Arteman.HellLand.items;

import com.Arteman.HellLand.HellLand;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.Item.ToolMaterial;

public class CrystallShovel extends ItemSpade 
{
	private String name = "crystall_shovel";
	
	public final ToolMaterial toolMaterial;

	public CrystallShovel(ToolMaterial enumToolMaterial) 
	{
		super(enumToolMaterial);
		toolMaterial = enumToolMaterial;
		this.setUnlocalizedName(HellLand.MODID + ":" + name);
		this.setTextureName(HellLand.MODID + ":" + name);
		this.setCreativeTab(HellLand.HellMCTab);
		GameRegistry.registerItem(this, name);
	}

}
