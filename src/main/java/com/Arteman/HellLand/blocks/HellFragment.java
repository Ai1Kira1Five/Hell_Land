package com.Arteman.HellLand.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;

import com.Arteman.HellLand.HellLand;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class HellFragment extends Block 
{
	public final String name = "Hell Fragment";
	public HellFragment(String name, Material material)
	{
		super(Material.iron);
		this.setCreativeTab(HellLand.HellMCTab);
		this.setBlockName(HellLand.MODID + ":" + name);
		this.setBlockTextureName(HellLand.MODID + ":" + name);
		this.setHardness(50.0f);
		this.setResistance(10000.0f);
		this.setStepSound(soundTypeMetal);
		this.setHarvestLevel("pickaxe", 3);
		GameRegistry.registerBlock(this, name);
	}
}

