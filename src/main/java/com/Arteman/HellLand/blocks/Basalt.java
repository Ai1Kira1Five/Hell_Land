package com.Arteman.HellLand.blocks;

import java.util.List;

import com.Arteman.HellLand.HellLand;
import com.Arteman.HellLand.ModBlocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class Basalt extends Block 
{
	//public final String name = "basalt";
	
	public Basalt(String name, Material material) 
	{
		super(material.rock);
		this.setCreativeTab(HellLand.HellMCTabDecor);
		this.setBlockName(HellLand.MODID + ":" + name);
		//this.setBlockTextureName(HellLand.MODID + ":" + name);
		this.setHardness(50.0f);
		this.setResistance(100.0f);
		this.setStepSound(soundTypeStone);
		this.setHarvestLevel("pickaxe", 3);
		//GameRegistry.registerBlock(this, name);
	}
	
	@Override
	public void registerBlockIcons(IIconRegister reg)
	{
		
	}
	
	@Override
	public IIcon getIcon(int side, int meta)
	{
		if(meta >  5)
			meta = 0;
		
		return ModBlocks.Basalt.getIcon(meta, 0);
	}
	
	@Override
	public int damageDropped(int meta)
	{
	    return meta;
	}
	
	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List list)
	{
	    for (int i = 0; i < 6; i ++) 
	    {
	        list.add(new ItemStack(item, 1, i));
	    }
	}
}
