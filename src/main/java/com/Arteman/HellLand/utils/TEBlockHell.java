package com.Arteman.HellLand.utils;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TEBlockHell extends BlockHell implements ITileEntityProvider {

    public TileEntity tileEntity;
    private static boolean keepInventory;

    public TEBlockHell(String name, TileEntity tileEntity, CreativeTabs creativeTabs){
        super(name, Material.rock, Block.soundTypeStone, creativeTabs, 1.0f,100.0f,0.0f,false,0);
        this.tileEntity = tileEntity;
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return tileEntity;
    }

    @Override
    public void onBlockAdded(World world, int xCoord, int yCoord, int zCoord){
        super.onBlockAdded(world, xCoord, yCoord, zCoord);
    }

    @Override
    public void breakBlock(World world, int xCoord, int yCoord, int zCoord, Block block, int meta) {
        super.breakBlock(world, xCoord, yCoord, zCoord, block, meta);
        world.removeTileEntity(xCoord,yCoord,zCoord);
    }

    public boolean onBlockEventReceived(World world, int xCoord, int yCoord, int zCoord, int event, int args){
        super.onBlockEventReceived(world, xCoord, yCoord, zCoord, event, args);
        TileEntity tileentity = world.getTileEntity(xCoord, yCoord, zCoord);
        return tileentity != null ? tileentity.receiveClientEvent(event, args) : false;
    }
}
