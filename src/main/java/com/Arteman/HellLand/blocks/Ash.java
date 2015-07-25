package com.Arteman.HellLand.blocks;

import com.Arteman.HellLand.HellLand;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class Ash extends Block 
{
	public final String name = "ash";
	public Ash(String name, Material material) 
	{
		super(material.sand);
		this.setCreativeTab(HellLand.HellMCTabDecor);
		this.setBlockName(HellLand.MODID + ":" + name);
		this.setBlockTextureName(HellLand.MODID + ":" + name);
		this.setHardness(50.0f);
		this.setResistance(100.0f);
		this.setStepSound(soundTypeSand);
		this.setHarvestLevel("pickaxe", 3);
		GameRegistry.registerBlock(this, name);
	}
}
