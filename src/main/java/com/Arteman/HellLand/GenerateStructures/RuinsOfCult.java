package com.Arteman.HellLand.GenerateStructures;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class RuinsOfCult extends WorldGenerator
{
	protected Block[] GetValidSpawnBlocks()
	{
		return new Block[]
		{
			Blocks.dirt,
			Blocks.grass,
			Blocks.stone,
			Blocks.sand,
		};
	}

	public boolean LocationIsValidSpawn(World world, int x, int y, int z)
	{
		int distanceToAir = 0;
		Block checkBlock = world.getBlock(x, y, z);

		while (checkBlock != Blocks.air)
		{
			distanceToAir++;
			checkBlock = world.getBlock(x, y + distanceToAir, z);
		}

		if (distanceToAir > 1)
		{
			return false;
		}

		y += distanceToAir - 1;

		Block block = world.getBlock(x, y, z);
		Block blockAbove = world.getBlock(x, y + 1, z);
		Block blockBelow = world.getBlock(x, y - 1, z);

		for (Block i : GetValidSpawnBlocks())
		{
			if (blockAbove != Blocks.air)
			{
				return false;
			}
			if (block == i)
			{
				return true;
			}
			else if (block == Blocks.snow_layer && blockBelow == i)
			{
				return true;
			}
			else if (block.getMaterial() == Material.plants && blockBelow == i)
			{
				return true;
			}
		}
		return false;
	}

	public boolean generate(World world, Random rand, int x, int y, int z)
	{
		int i = rand.nextInt(1);

		if(i == 0)
		{
		    generate_r0(world, rand, x, y, z);
		}

       return true;

	}

	public boolean generate_r0(World world, Random rand, int x, int y, int z)
	{
		if
		(
		    !LocationIsValidSpawn(world, x + 0, y, z +0) ||
		    !LocationIsValidSpawn(world, x + 1, y, z +0) ||
		    !LocationIsValidSpawn(world, x + 2, y, z +0) ||
		    !LocationIsValidSpawn(world, x + 3, y, z +0) ||
		    !LocationIsValidSpawn(world, x + 4, y, z +0) ||
		    !LocationIsValidSpawn(world, x + 5, y, z +0) ||
		    !LocationIsValidSpawn(world, x + 6, y, z +0) ||
		    !LocationIsValidSpawn(world, x + 7, y, z +0) ||
		    !LocationIsValidSpawn(world, x + 0, y, z +1) ||
		    !LocationIsValidSpawn(world, x + 1, y, z +1) ||
		    !LocationIsValidSpawn(world, x + 2, y, z +1) ||
		    !LocationIsValidSpawn(world, x + 3, y, z +1) ||
		    !LocationIsValidSpawn(world, x + 4, y, z +1) ||
		    !LocationIsValidSpawn(world, x + 5, y, z +1) ||
		    !LocationIsValidSpawn(world, x + 6, y, z +1) ||
		    !LocationIsValidSpawn(world, x + 7, y, z +1) ||
		    !LocationIsValidSpawn(world, x + 0, y, z +2) ||
		    !LocationIsValidSpawn(world, x + 1, y, z +2) ||
		    !LocationIsValidSpawn(world, x + 2, y, z +2) ||
		    !LocationIsValidSpawn(world, x + 3, y, z +2) ||
		    !LocationIsValidSpawn(world, x + 4, y, z +2) ||
		    !LocationIsValidSpawn(world, x + 5, y, z +2) ||
		    !LocationIsValidSpawn(world, x + 6, y, z +2) ||
		    !LocationIsValidSpawn(world, x + 7, y, z +2) ||
		    !LocationIsValidSpawn(world, x + 0, y, z +3) ||
		    !LocationIsValidSpawn(world, x + 1, y, z +3) ||
		    !LocationIsValidSpawn(world, x + 2, y, z +3) ||
		    !LocationIsValidSpawn(world, x + 3, y, z +3) ||
		    !LocationIsValidSpawn(world, x + 4, y, z +3) ||
		    !LocationIsValidSpawn(world, x + 5, y, z +3) ||
		    !LocationIsValidSpawn(world, x + 6, y, z +3) ||
		    !LocationIsValidSpawn(world, x + 7, y, z +3) ||
		    !LocationIsValidSpawn(world, x + 0, y, z +4) ||
		    !LocationIsValidSpawn(world, x + 1, y, z +4) ||
		    !LocationIsValidSpawn(world, x + 2, y, z +4) ||
		    !LocationIsValidSpawn(world, x + 3, y, z +4) ||
		    !LocationIsValidSpawn(world, x + 4, y, z +4) ||
		    !LocationIsValidSpawn(world, x + 5, y, z +4) ||
		    !LocationIsValidSpawn(world, x + 6, y, z +4) ||
		    !LocationIsValidSpawn(world, x + 7, y, z +4) ||
		    !LocationIsValidSpawn(world, x + 0, y, z +5) ||
		    !LocationIsValidSpawn(world, x + 1, y, z +5) ||
		    !LocationIsValidSpawn(world, x + 2, y, z +5) ||
		    !LocationIsValidSpawn(world, x + 3, y, z +5) ||
		    !LocationIsValidSpawn(world, x + 4, y, z +5) ||
		    !LocationIsValidSpawn(world, x + 5, y, z +5) ||
		    !LocationIsValidSpawn(world, x + 6, y, z +5) ||
		    !LocationIsValidSpawn(world, x + 7, y, z +5) ||
		    !LocationIsValidSpawn(world, x + 0, y, z +6) ||
		    !LocationIsValidSpawn(world, x + 1, y, z +6) ||
		    !LocationIsValidSpawn(world, x + 2, y, z +6) ||
		    !LocationIsValidSpawn(world, x + 3, y, z +6) ||
		    !LocationIsValidSpawn(world, x + 4, y, z +6) ||
		    !LocationIsValidSpawn(world, x + 5, y, z +6) ||
		    !LocationIsValidSpawn(world, x + 6, y, z +6) ||
		    !LocationIsValidSpawn(world, x + 7, y, z +6) ||
		    !LocationIsValidSpawn(world, x + 0, y, z +7) ||
		    !LocationIsValidSpawn(world, x + 1, y, z +7) ||
		    !LocationIsValidSpawn(world, x + 2, y, z +7) ||
		    !LocationIsValidSpawn(world, x + 3, y, z +7) ||
		    !LocationIsValidSpawn(world, x + 4, y, z +7) ||
		    !LocationIsValidSpawn(world, x + 5, y, z +7) ||
		    !LocationIsValidSpawn(world, x + 6, y, z +7) ||
		    !LocationIsValidSpawn(world, x + 7, y, z +7) ||
		    !LocationIsValidSpawn(world, x + 0, y, z +8) ||
		    !LocationIsValidSpawn(world, x + 1, y, z +8) ||
		    !LocationIsValidSpawn(world, x + 2, y, z +8) ||
		    !LocationIsValidSpawn(world, x + 3, y, z +8) ||
		    !LocationIsValidSpawn(world, x + 4, y, z +8) ||
		    !LocationIsValidSpawn(world, x + 5, y, z +8) ||
		    !LocationIsValidSpawn(world, x + 6, y, z +8) ||
		    !LocationIsValidSpawn(world, x + 7, y, z +8)
       )

		world.setBlock(x + 3, y + 0, z + 0, Blocks.obsidian, 0, 3);
		world.setBlock(x + 7, y + 0, z + 2, Blocks.obsidian, 0, 3);
		world.setBlock(x + 3, y + 0, z + 3, Blocks.nether_brick, 0, 3);
		world.setBlock(x + 5, y + 0, z + 3, Blocks.nether_brick, 0, 3);
		world.setBlock(x + 0, y + 0, z + 4, Blocks.obsidian, 0, 3);
		world.setBlock(x + 4, y + 0, z + 4, Blocks.gold_block, 0, 3);
		world.setBlock(x + 3, y + 0, z + 5, Blocks.nether_brick, 0, 3);
		world.setBlock(x + 5, y + 0, z + 5, Blocks.nether_brick, 0, 3);
		world.setBlock(x + 7, y + 0, z + 6, Blocks.obsidian, 0, 3);
		world.setBlock(x + 3, y + 0, z + 8, Blocks.obsidian, 0, 3);
		world.setBlock(x + 3, y + 1, z + 3, Blocks.nether_brick_fence, 0, 3);
		world.setBlock(x + 5, y + 1, z + 3, Blocks.nether_brick_fence, 0, 3);
		world.setBlock(x + 3, y + 1, z + 5, Blocks.nether_brick_fence, 0, 3);
		world.setBlock(x + 5, y + 1, z + 5, Blocks.nether_brick_fence, 0, 3);
		world.setBlock(x + 3, y + 2, z + 3, Blocks.skull, 1, 3);
		world.setBlock(x + 5, y + 2, z + 3, Blocks.skull, 1, 3);
		world.setBlock(x + 3, y + 2, z + 5, Blocks.skull, 1, 3);
		world.setBlock(x + 5, y + 2, z + 5, Blocks.skull, 1, 3);
		return true;

	}
}
