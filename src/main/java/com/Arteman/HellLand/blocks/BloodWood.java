package com.Arteman.HellLand.blocks;

import codechicken.lib.colour.Colour;

import com.Arteman.HellLand.HellLand;
import com.Arteman.HellLand.renderer.AnimationSideHandler;
import com.Arteman.HellLand.utils.BlockHell;
import com.Arteman.HellLand.utils.HellNames;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;

public class BloodWood extends BlockHell implements AnimationSideHandler
{
	public final String name = "Blood Wood";
	
	public BloodWood(String name, Material material)
	{
		super(Material.wood);
		this.setCreativeTab(HellLand.HellMCTab);
		this.setBlockName(HellLand.MODID + ":" + name);
		this.setStepSound(soundTypeWood);
		this.setHarvestLevel("axe", 1);
		GameRegistry.registerBlock(this, name);
	}

	@SideOnly(Side.CLIENT)
	private IIcon iconTop, animation;
	
	//@SideOnly(Side.CLIENT)
	//private IIcon iconDown;
	
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		this.blockIcon = iconRegister.registerIcon(HellLand.MODID + ":" + "Blood Wood");
		this.iconTop = iconRegister.registerIcon(HellLand.MODID + ":" + "Blood Wood Top");
		//this.iconDown = iconRegister.registerIcon(HellLand.MODID + ":" + "Blood Wood Top");
		this.animation = iconRegister.registerIcon(HellLand.MODID + ":" + "animation_blood");
	}
	
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int metadata)
	{
		if(side == 0 || side == 1)
		{
			return iconTop;
		}else
			if(side < 6)
			{
				return blockIcon;
			}else{
				return animation;
			}
		//return side == 1 ? this.iconTop : (side == 1 ? this.iconTop : (side == 0 ? this.iconTop : this.blockIcon));
	}

	@Override
	public Colour getItemColor(int meta, ForgeDirection side) 
	{
		return HellNames.HellColors.HellColors[2];
	}

	@Override
	public int getAnimationIndex(int meta, ForgeDirection side) 
	{
		return 255;
	}

	@Override
	public int getAnimationBrightness(int meta, ForgeDirection side) 
	{
		return 220;
	}

	@Override
	public Colour getColor(IBlockAccess iBlockAccess, int x, int y, int z, ForgeDirection side) 
	{
		return HellNames.HellColors.HellColors[2];
	}

	@Override
	public int getAnimationIndex(IBlockAccess iBlockAccess, int x, int y, int z, ForgeDirection side) 
	{
		return 255;
	}

	@Override
	public int getAnimationBrightness(IBlockAccess iBlockAccess, int x, int y, int z, ForgeDirection side) 
	{
		return 220;
	}
}
