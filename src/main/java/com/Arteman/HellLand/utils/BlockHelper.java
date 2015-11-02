package com.Arteman.HellLand.utils;

import java.util.ArrayList;

import net.minecraft.block.*;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class BlockHelper {
	public static enum BlockStyle
	  {
	    UNDECIDED,  NOTHING,  USE_GET_DAMAGE_VALUE,  USE_ID_DROPPED,  USE_GET_BLOCK_DROPPED,  CLONE_MD,  STEM,  SLAB,  CAKE,  CROP,  DOOR,  REDSTONE_ORE,  PISTON_EXTENSION,  BED;
	    
	    private BlockStyle() {}
	  }
	  
	  static BlockStyle getBlockStyle(Block block)
	  {
	    return classifyBlock(block);
	  }
	  
	  private static BlockStyle classifyBlock(Block block)
	  {
	    if (block == Blocks.cake) {
	      return BlockStyle.CAKE;
	    }
	    if ((block == Blocks.redstone_ore) || (block == Blocks.lit_redstone_ore)) {
	      return BlockStyle.REDSTONE_ORE;
	    }
	    if (block == Blocks.piston_extension) {
	      return BlockStyle.PISTON_EXTENSION;
	    }
	    if ((block == Blocks.melon_stem) || (block == Blocks.pumpkin_stem)) {
	      return BlockStyle.STEM;
	    }
	    if (((block instanceof BlockSign)) || ((block instanceof BlockFlowerPot)) || ((block instanceof BlockRedstoneWire)) || ((block instanceof BlockBrewingStand)) || ((block instanceof BlockReed)) || ((block instanceof BlockTripWire)) || ((block instanceof BlockCauldron)) || ((block instanceof BlockRedstoneRepeater)) || ((block instanceof BlockRedstoneComparator)) || ((block instanceof BlockRedstoneTorch)) || ((block instanceof BlockFarmland)) || ((block instanceof BlockFurnace)) || ((block instanceof BlockHugeMushroom)) || ((block instanceof BlockRedstoneLight))) {
	      return BlockStyle.USE_ID_DROPPED;
	    }
	    if (((block instanceof BlockCocoa)) || ((block instanceof BlockNetherWart)) || ((block instanceof BlockSkull))) {
	      return BlockStyle.USE_GET_BLOCK_DROPPED;
	    }
	    if (((block instanceof BlockPistonMoving)) || ((block instanceof BlockPortal)) || ((block instanceof BlockEndPortal)) || ((block instanceof BlockSilverfish)) || ((block instanceof BlockMobSpawner))) {
	      return BlockStyle.NOTHING;
	    }
	    if ((block instanceof BlockOre)) {
	      return BlockStyle.CLONE_MD;
	    }
	    if ((block instanceof BlockSlab)) {
	      return BlockStyle.SLAB;
	    }
	    if ((block instanceof BlockCrops)) {
	      return BlockStyle.CROP;
	    }
	    if ((block instanceof BlockBed)) {
	      return BlockStyle.BED;
	    }
	    if ((block instanceof BlockDoor)) {
	      return BlockStyle.DOOR;
	    }
	    return BlockStyle.USE_GET_DAMAGE_VALUE;
	  }
	  
	  private static ItemStack makeItemStack(Item itemId, int stackSize, int damage)
	  {
	    if (itemId == null) {
	      return null;
	    }
	    return new ItemStack(itemId, stackSize, damage);
	  }
	  
	  public static ItemStack getPlacingItem(Block block, MovingObjectPosition target, World world, int x, int y, int z)
	  {
	    int md;
	    switch (classifyBlock(block))
	    {
	    case UNDECIDED: 
	    case NOTHING: 
	    case PISTON_EXTENSION: 
	    default: 
	      return null;
	    case USE_GET_DAMAGE_VALUE: 
	      return new ItemStack(block, 1, block.getDamageValue(world, x, y, z));
	    case USE_ID_DROPPED: 
	    case BED: 
	      md = world.getBlockMetadata(x, y, z);
	      return makeItemStack(block.getItemDropped(md, world.rand, 0), 1, 0);
	    case USE_GET_BLOCK_DROPPED: 
	      md = world.getBlockMetadata(x, y, z);
	      ArrayList<ItemStack> drops = block.getDrops(world, x, y, z, md, 0);
	      if (drops.isEmpty()) {
	        return null;
	      }
	      return (ItemStack)drops.get(0);
	    case CLONE_MD: 
	      md = world.getBlockMetadata(x, y, z);
	      return new ItemStack(block, 1, md);
	    case STEM: 
	      if (block == Blocks.pumpkin_stem) {
	        return new ItemStack(Items.pumpkin_seeds);
	      }
	      if (block == Blocks.melon_stem) {
	        return new ItemStack(Items.melon_seeds);
	      }
	      return null;
	    case SLAB: 
	      md = world.getBlockMetadata(x, y, z);
	      int dropped = block.quantityDropped(world.rand);
	      return makeItemStack(block.getItemDropped(md, world.rand, 0), dropped, block.damageDropped(md));
	    case CAKE: 
	      md = world.getBlockMetadata(x, y, z);
	      return md == 0 ? new ItemStack(Items.cake) : null;
	    case CROP: 
	      return new ItemStack(block.getItemDropped(0, world.rand, 0), 1, block.getDamageValue(world, x, y, z));
	    case DOOR: 
	      md = world.getBlockMetadata(x, y, z);
	      Item doorId = block.getItemDropped(md, world.rand, 0);
	      if (doorId == null) {
	        return null;
	      }
	      return new ItemStack(doorId, 1, 0);
	    }
	   // return new ItemStack(Blocks.redstone_ore);
	  }
}
