package com.Arteman.HellLand.items;

import com.Arteman.HellLand.HellLand;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class BoneFragment extends Item 
{
	private String name = "Bone Fragment";
	
	public BoneFragment()
	{
		this.setUnlocalizedName(HellLand.MODID + ":" + name);
		this.setCreativeTab(HellLand.HellMCTab);
		this.setTextureName(HellLand.MODID + ":" + name);
		GameRegistry.registerItem(this, name);
	}
}
