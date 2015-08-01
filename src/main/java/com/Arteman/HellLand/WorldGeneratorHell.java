package com.Arteman.HellLand;

import java.util.Random;

import com.Arteman.HellLand.GenerateStructures.RuinsOfCult;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;

public class WorldGeneratorHell implements IWorldGenerator 
{
	private static final String __OBFID = "CL_00000429";
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) 
	{
		switch (world.provider.dimensionId)
		{
			case 0: GenerateOverworld(random, chunkX * 16, chunkZ * 16, world); break;
			case 1: GenerateEnd(random, chunkX * 16, chunkZ * 16, world); break;
			case -1: GenerateNether(random, chunkX * 16, chunkZ * 16, world); break;
		}
	}
	
	private void GenerateOverworld(Random random, int x, int z, World world)
	{
		this.addOreSpawn(ModBlocks.HellFragment, world, random, x, z, 2, 15, 15, 0, 25);
		
		BiomeGenBase biome = world.getWorldChunkManager().getBiomeGenAt(x, z);
		if ((biome == BiomeGenBase.desert))
		{
			for (int a = 0; a < 1; a++)
			{
				int i = x + random.nextInt(256);
				int j = z + random.nextInt(256);
				int k = world.getHeightValue(i, j);
				new RuinsOfCult().generate(world, random, i, j, k);
			}
		}
		
		if ((biome == BiomeGenBase.extremeHills))
		{
			
		}
	}
	
	private void GenerateEnd(Random random, int x, int z, World world)
	{
		
	}
	
	private void GenerateNether(Random random, int x, int z, World world)
	{
		this.addOreSpawn(ModBlocks.HellFragment, world, random, x, z, 20, 40, 30, 0, 128);
	}
	
	public void addOreSpawn(Block block, World world, Random random, int blockXPos, int blockZPos, int minVeinSize, int maxVeinSize, int chancesToSpawn, int minY, int maxY )
	   {
	       WorldGenMinable minable = new WorldGenMinable(block, (minVeinSize + random.nextInt(maxVeinSize - minVeinSize)), Blocks.stone);
	       for(int i = 0; i < chancesToSpawn; i++)
	       {
	           int posX = blockXPos + random.nextInt(16);
	           int posY = minY + random.nextInt(maxY - minY);
	           int posZ = blockZPos + random.nextInt(16);
	           minable.generate(world, random, posX, posY, posZ);
	       }
	   }
}
