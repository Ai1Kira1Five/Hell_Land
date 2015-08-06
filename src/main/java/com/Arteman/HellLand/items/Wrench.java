package com.Arteman.HellLand.items;

import java.util.HashSet;
import java.util.Set;

import com.Arteman.HellLand.HellLandCore;
import com.Arteman.HellLand.items.tools.IToolWrench;
import com.Arteman.HellLand.utils.BlockUtils;
import com.Arteman.HellLand.utils.ItemHell;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBed;
import net.minecraft.block.BlockButton;
import net.minecraft.block.BlockChest;
import net.minecraft.block.BlockLever;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class Wrench extends ItemHell implements IToolWrench
{
	private final Set<Class<? extends Block>> shiftRotations = new HashSet<Class<? extends Block>>();
	private final Set<Class<? extends Block>> blacklistedRotations = new HashSet<Class<? extends Block>>();
	
    public Wrench()
    {
        super("wrench", HellLandCore.HellMCTab, false, 0);
        
        setFull3D();
        setMaxStackSize(1);
        shiftRotations.add(BlockLever.class);
		shiftRotations.add(BlockButton.class);
		shiftRotations.add(BlockChest.class);
		blacklistedRotations.add(BlockBed.class);
		setHarvestLevel("wrench", 0);
    }
    
    private boolean isClass(Set<Class<? extends Block>> set, Class<? extends Block> cls)
    {
		for (Class<? extends Block> shift : set) 
		{
			if (shift.isAssignableFrom(cls)) 
			{
				return true;
			}
		}
		return false;
	}
    
    @Override
	public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		Block block = world.getBlock(x, y, z);

		if (block == null || isClass(blacklistedRotations, block.getClass())) {
			return false;
		}

		if (player.isSneaking() != isClass(shiftRotations, block.getClass())) {
			return false;
		}

		// Double chests should NOT be rotated.
		if (block instanceof BlockChest && BlockUtils.getOtherDoubleChest(world.getTileEntity(x, y, z)) != null) {
			return false;
		}

		if (block.rotateBlock(world, x, y, z, ForgeDirection.getOrientation(side))) {
			player.swingItem();
			return !world.isRemote;
		}
		return false;
	}
    
    @Override
	public boolean canWrench(EntityPlayer player, int x, int y, int z) 
    {
		return true;
	}

	@Override
	public void wrenchUsed(EntityPlayer player, int x, int y, int z) 
	{
		player.swingItem();
	}

	@Override
	public boolean doesSneakBypassUse(World world, int x, int y, int z, EntityPlayer player) 
	{
		return true;
	}

    /*
    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float FloatX, float FloatY, float FloatZ) {
        if (!world.isRemote) {
            int ChunkX = world.getChunkFromBlockCoords(x, z).getChunkCoordIntPair().chunkXPos;
            int ChunkZ = world.getChunkFromBlockCoords(x, z).getChunkCoordIntPair().chunkZPos;
            int CoordX = ChunkX * 16;
            int CoordZ = ChunkZ * 16;

            for (int i = 128; i >= 3; i--)
                for (int x1 = 0; x1 < 16; x1++)
                    for (int z1 = 0; z1 < 16; z1++)
                        if (world.getBlock(CoordX + x1, i, CoordZ + z1) == Blocks.grass
                                || world.getBlock(CoordX + x1, i, CoordZ + z1) == Blocks.sand
                                || world.getBlock(CoordX + x1, i, CoordZ + z1) == Blocks.water
                                || world.getBlock(CoordX + x1, i, CoordZ + z1) == Blocks.lava
                                || world.getBlock(CoordX + x1, i, CoordZ + z1) == Blocks.stone
                                || world.getBlock(CoordX + x1, i, CoordZ + z1) == Blocks.dirt
                                || world.getBlock(CoordX + x1, i, CoordZ + z1) == Blocks.gravel
                                || world.getBlock(CoordX + x1, i, CoordZ + z1) == Blocks.sandstone) {
                            world.setBlock(CoordX + x1, i, CoordZ + z1, Blocks.air, 0, 2);
                        }
        }
        return true;
    }
    */
}
