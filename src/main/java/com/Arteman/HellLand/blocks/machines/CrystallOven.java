package com.Arteman.HellLand.blocks.machines;

import java.util.Random;

import com.Arteman.HellLand.ModBlocks;
import com.Arteman.HellLand.tileentity.TileEntityCrystallOven;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class CrystallOven extends BlockContainer 
{
	private final boolean isActive;
	
	public CrystallOven(boolean isActive)
	{
		super(Material.rock);
		this.isActive = isActive;
	}
	
	public Item getItemDropped(int i, Random random, int j)
	{
		return Item.getItemFromBlock(ModBlocks.CrystallOvenIdle);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int i) 
	{
		return new TileEntityCrystallOven;
	}
}
