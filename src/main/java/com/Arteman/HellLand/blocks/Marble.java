package com.Arteman.HellLand.blocks;

import com.Arteman.HellLand.HellLand;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class Marble extends Block 
{
	public final String name = "marble";
	public Marble(String name, Material material) 
	{
		super(material.rock);
		this.setCreativeTab(HellLand.HellMCTab);
		this.setBlockName(HellLand.MODID + ":" + name);
		this.setBlockTextureName(HellLand.MODID + ":" + name);
		this.setHardness(50.0f);
		this.setResistance(100.0f);
		this.setStepSound(soundTypeStone);
		this.setHarvestLevel("pickaxe", 3);
		GameRegistry.registerBlock(this, name);
	}
}
