package com.Arteman.HellLand.blocks;

import com.Arteman.HellLand.HellLand;
import com.Arteman.HellLand.utils.BlockHell;
import net.minecraft.block.material.Material;

public class NecroticLamp extends BlockHell {

    public NecroticLamp(String name) {
        super(name,Material.rock,soundTypeGlass,HellLand.HellMCTabDecor,0.5f,5.0f,15.0f);
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
}
