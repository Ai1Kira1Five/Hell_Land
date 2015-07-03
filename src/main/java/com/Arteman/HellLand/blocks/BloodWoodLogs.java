package com.Arteman.HellLand.blocks;

import com.Arteman.HellLand.HellLand;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BloodWoodLogs extends Block 
{
	public final String name = "Blood Planks";
	
	public BloodWoodLogs(String name, Material material)
	{
		super(Material.wood);
		this.setCreativeTab(HellLand.HellMCTab);
		this.setBlockName(HellLand.MODID + ":" + name);
		this.setStepSound(soundTypeWood);
		this.setHarvestLevel("axe", 1);
		this.setBlockTextureName(HellLand.MODID + ":" + name);
		GameRegistry.registerBlock(this, name);
	}
}
