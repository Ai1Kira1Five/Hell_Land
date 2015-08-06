package com.Arteman.HellLand.utils.tools;

import com.Arteman.HellLand.HellLandCore;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemSpade;

public class ShovelHell extends ItemSpade {

    public ShovelHell(String name, ToolMaterial toolMaterial, CreativeTabs creativeTab){
        super(toolMaterial);
        this.setUnlocalizedName(HellLandCore.MODID + ":" + name);
        this.setTextureName(HellLandCore.MODID + ":" + name);
        this.setCreativeTab(creativeTab);
        GameRegistry.registerItem(this, name);
    }
}
