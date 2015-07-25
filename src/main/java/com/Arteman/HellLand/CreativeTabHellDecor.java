package com.Arteman.HellLand;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabHellDecor extends CreativeTabs 
{
	public CreativeTabHellDecor(String lable)
	{
		super(lable);
	}

	@Override
	public Item getTabIconItem() 
	{
		return Item.getItemFromBlock(ModBlocks.Marble);
	}

}
