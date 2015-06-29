package com.Arteman.HellLand.blocks.machines;

import com.Arteman.HellLand.HellLand;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class AmuletTable extends Block 
{
	@SideOnly(Side.CLIENT)
	private IIcon iconTop;
	
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		this.blockIcon = iconRegister.registerIcon(HellLand.MODID + ":" + "AmuletTableSide");
		this.iconTop = iconRegister.registerIcon(HellLand.MODID + ":" + "AmuletTableTop");
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int metadata)
	{
		return side == 1 ? this.iconTop : this.blockIcon;
	}
	
	public final String name = "Amulet Table";
	public AmuletTable(String name, Material material)
	{
		super(Material.wood);
		this.setCreativeTab(HellLand.HellMCTab);
		this.setBlockName(HellLand.MODID + ":" + name);
		this.setHardness(3.5f);
		this.setResistance(5.0f);
		this.setStepSound(soundTypeWood);
		this.setHarvestLevel("axe", 2);
		GameRegistry.registerBlock(this, name);
	}
	
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int q, float a, float c)
	{
		if (!player.isSneaking()){
			player.openGui(HellLand.MODID, HellLand.guiIDAmuletTable, world, x, y, z);
			return true;
		}else{
			return false;
		}
	}
}
