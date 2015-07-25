package com.Arteman.HellLand.blocks;

import com.Arteman.HellLand.HellLand;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class NecroticLamp extends Block 
{
	public final String name = "necrotic_lamp";
	
	public NecroticLamp(String name, Material material) 
	{
		super(Material.rock);
		this.setCreativeTab(HellLand.HellMCTabDecor);
		this.setBlockName(HellLand.MODID + ":" + name);
		this.setBlockTextureName(HellLand.MODID + ":" + name);
		this.setLightLevel(15.0f);
		this.setHardness(0.5f);
		this.setStepSound(soundTypeGlass);
		GameRegistry.registerBlock(this, name);
	}
	/*
	public MapColor getMapColor(int i)
	{
		return MapColor.redColor;
	}
	*/
	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}
}
