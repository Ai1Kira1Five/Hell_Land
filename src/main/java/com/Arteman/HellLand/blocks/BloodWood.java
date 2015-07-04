package com.Arteman.HellLand.blocks;

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

public class BloodWood extends BlockHell
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
	private IIcon iconTop;
	
	@SideOnly(Side.CLIENT)
	private IIcon iconDown;
	
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		this.blockIcon = iconRegister.registerIcon(HellLand.MODID + ":" + "Blood Wood");
		this.iconTop = iconRegister.registerIcon(HellLand.MODID + ":" + "Blood Wood Top");
		this.iconDown = iconRegister.registerIcon(HellLand.MODID + ":" + "Blood Wood Top");
	}
	
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int metadata)
	{
		return side == 1 ? this.iconTop : (side == 1 ? this.iconTop : (side == 0 ? this.iconTop : this.blockIcon));
	}
}
