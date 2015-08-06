package com.Arteman.HellLand.blocks;

import com.Arteman.HellLand.HellLandCore;
import com.Arteman.HellLand.ModBlocks;
import com.Arteman.HellLand.utils.BlockHell;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class NecroticLamp extends BlockHell {

    public NecroticLamp(String name) {
        super(name,Material.rock,soundTypeGlass,HellLandCore.HellMCTabDecor,0.5f,5.0f,15.0f);
    }

    /*
    public MapColor getMapColor(int i)
    {
        return MapColor.redColor;
    }
    */
    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }
    
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
    {
    	world.setBlock(x-2, y+2, z, ModBlocks.Marble);
    	world.setBlock(x+2, y+2, z, ModBlocks.Marble);
    	world.setBlock(x-1, y+3, z, ModBlocks.Marble);
    	world.setBlock(x+1, y+3, z, ModBlocks.Marble);
    	world.setBlock(x, y+4, z, ModBlocks.CrystallBlock);
    	world.setBlock(x+1, y+5, z, ModBlocks.Marble);
    	world.setBlock(x-1, y+5, z, ModBlocks.Marble);
    	world.setBlock(x+2, y+6, z, ModBlocks.Marble);
    	world.setBlock(x-2, y+6, z, ModBlocks.Marble);
    	
    	return true;
    }
}
