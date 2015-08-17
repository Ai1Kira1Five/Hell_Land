package com.Arteman.HellLand.blocks;

import com.Arteman.HellLand.HellLand;
import com.Arteman.HellLand.utils.BlockHell;
import net.minecraft.block.material.Material;

public class BloodWoodLogs extends BlockHell {

    public BloodWoodLogs(String name) {
        super(name,Material.wood,soundTypeWood,HellLand.HellMCTab,3.5f,25.0f,0.0f);
         this.setHarvestLevel("axe", 1);
   }
}
