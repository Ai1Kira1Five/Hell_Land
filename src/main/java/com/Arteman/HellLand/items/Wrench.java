package com.Arteman.HellLand.items;

import com.Arteman.HellLand.HellLand;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.world.World;

public class Wrench extends Item
{
	private String name = "wrench";
	
	public Wrench()
	{
		super();
		this.setTextureName(HellLand.MODID + ":" + name);
		this.setUnlocalizedName(name);
		this.setMaxStackSize(1);
		this.setHarvestLevel("wrench", 0);
		this.setFull3D();
		this.setCreativeTab(HellLand.HellMCTab);
		GameRegistry.registerItem(this, name);
	}
	
	@Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float FloatX, float FloatY, float FloatZ)
	{
       if (!world.isRemote) 
       {
           int ChunkX = world.getChunkFromBlockCoords(x, z).getChunkCoordIntPair().chunkXPos;
           int ChunkZ = world.getChunkFromBlockCoords(x, z).getChunkCoordIntPair().chunkZPos;
           int CoordX = ChunkX * 16;
           int CoordZ = ChunkZ * 16;

           for (int i = 128; i >= 3; i--)
               for (int x1 = 0; x1 < 16; x1++)
                   for (int z1 = 0; z1 < 16; z1++)
                   if(world.getBlock(CoordX + x1, i, CoordZ + z1)==Blocks.grass
                      ||world.getBlock(CoordX + x1, i, CoordZ + z1)==Blocks.sand
                      ||world.getBlock(CoordX + x1, i, CoordZ + z1) == Blocks.water
                      ||world.getBlock(CoordX + x1, i, CoordZ + z1) == Blocks.lava
                      ||world.getBlock(CoordX + x1, i, CoordZ + z1) == Blocks.stone
                      ||world.getBlock(CoordX + x1, i, CoordZ + z1) == Blocks.dirt
                      ||world.getBlock(CoordX + x1, i, CoordZ + z1) == Blocks.gravel
                      ||world.getBlock(CoordX + x1, i, CoordZ + z1) == Blocks.sandstone)
                   {
                       world.setBlock(CoordX + x1, i, CoordZ + z1, Blocks.air,0,2);
                   }
       }
        return true;
    }
}
