package com.Arteman.HellLand.items;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public class ItemBlockHellLand extends ItemBlock
{
	public ItemBlockHellLand(Block b) 
	{
		super(b);
	}

	@Override
	public int getMetadata(int i) 
	{
		return i;
	}
}
