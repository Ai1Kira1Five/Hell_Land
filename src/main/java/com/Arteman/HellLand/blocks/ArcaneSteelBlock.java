package com.Arteman.HellLand.blocks;

import com.Arteman.HellLand.HellLand;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class ArcaneSteelBlock extends Block
{
	public final String name = "arcane_steel_block";
	
	public ArcaneSteelBlock(String string, Material material)
	{
		super(material.iron);
		this.setCreativeTab(HellLand.HellMCTab);
		this.setBlockName(HellLand.MODID + ":" + name);
		this.setBlockTextureName(HellLand.MODID + ":" + name);
		this.setStepSound(soundTypeMetal);
		GameRegistry.registerBlock(this, name);
	}
}
