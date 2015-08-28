package com.Arteman.HellLand.blocks;

import com.Arteman.HellLand.utils.BlockHell;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class LightningTotem extends BlockHell
{
	public LightningTotem(String string, Material iron, SoundType soundtypestone, CreativeTabs hellMCTab, float f, float g, float h)
	{
		super(string, iron, soundtypestone, hellMCTab, f, g, h);
	}
	
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	public boolean isFullCube()
	{
		return false;
	}
}
