package com.Arteman.HellLand.blocks;

import java.util.Random;

import com.Arteman.HellLand.HellLand;
import com.Arteman.HellLand.PEffects.EntityPentaFX;
import com.Arteman.HellLand.utils.BlockHell;

import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.world.World;

public class NecroticLamp extends BlockHell {

    public NecroticLamp(String name) {
        super(name,Material.rock,soundTypeGlass,HellLand.HellMCTabDecor,0.5f,5.0f,15.0f,false,0);
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
    
    public void randomDisplayTick(World world, int x, int y, int z, Random random){
    	if(world.isRemote){
    		Minecraft.getMinecraft().effectRenderer.addEffect(new EntityPentaFX(world, x, y + 1, z));
    	}
    }
}
