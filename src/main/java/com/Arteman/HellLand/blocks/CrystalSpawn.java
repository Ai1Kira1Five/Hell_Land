package com.Arteman.HellLand.blocks;

import com.Arteman.HellLand.HellLand;
import com.Arteman.HellLand.tileentity.TileEntityCrystalSpawn;
import com.Arteman.HellLand.utils.BlockHell;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class CrystalSpawn extends BlockHell implements ITileEntityProvider
{
	public CrystalSpawn(){
		super("crystalSpawn", Material.glass, Block.soundTypeGlass, HellLand.HellMCTab, 5.0f, 50.0f, 1.0f, true, 5);
	}
	
	@Override
    public boolean canPlaceBlockAt(World world, int x, int y, int z) {
        if (!world.getBlock(x,y-1,z).isOpaqueCube())
            return false;
        return true;
    }

	@Override
	public TileEntity createNewTileEntity(World world, int i){
		return new TileEntityCrystalSpawn();
	}
	
	public int getRenderType() {
        return -1;
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }
}
