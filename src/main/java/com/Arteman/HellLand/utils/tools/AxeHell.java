package com.Arteman.HellLand.utils.tools;

import com.Arteman.HellLand.HellLand;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemAxe;

public class AxeHell extends ItemAxe {

    public AxeHell(String name,ToolMaterial toolMaterial, CreativeTabs creativeTab){
        super(toolMaterial);
        this.setUnlocalizedName(HellLand.MODID + ":" + name);
        this.setTextureName(HellLand.MODID + ":" + name);
        this.setCreativeTab(creativeTab);
        GameRegistry.registerItem(this, name);
    }
}
