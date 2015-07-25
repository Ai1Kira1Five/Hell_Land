package com.Arteman.HellLand.blocks;

import com.Arteman.HellLand.HellLand;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class CrystallBlock extends Block 
{
	public final String name = "crystall_block";

	public CrystallBlock(String name, Material material)
	{
		super(Material.rock);
		this.setBlockName(HellLand.MODID + ":" + name);
		this.setBlockTextureName(HellLand.MODID + ":" + name);
		this.setCreativeTab(HellLand.HellMCTabDecor);
		this.setHardness(5.0f);
		GameRegistry.registerBlock(this, name);
	}

}
