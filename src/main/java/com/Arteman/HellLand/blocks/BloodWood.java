package com.Arteman.HellLand.blocks;

import com.Arteman.HellLand.HellLand;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

public class BloodWood extends Block 
{
	@SideOnly(Side.CLIENT)
	private IIcon iconTop;
	
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		this.blockIcon = iconRegister.registerIcon(HellLand.MODID + ":" + "Blood Wood");
	}
	
	public final String name = "Blood Wood";
	
	public BloodWood(String name, Material material)
	{
		super(Material.wood);
		this.setCreativeTab(HellLand.HellMCTab);
		this.setBlockName(HellLand.MODID + ":" + name);
		this.setBlockTextureName(HellLand.MODID + ":" + name);
		this.setHardness(30.0f);
		this.setResistance(1000.0f);
		this.setStepSound(soundTypeWood);
		this.setHarvestLevel("axe", 1);
		GameRegistry.registerBlock(this, name);
	}
}
