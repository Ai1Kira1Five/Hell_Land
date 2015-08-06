package com.Arteman.HellLand.utils.tools;

import com.Arteman.HellLand.HellLandCore;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemPickaxe;

/**
 * Created by bes on 03.08.2015.
 */
public class PickaxeHell extends ItemPickaxe {

    public PickaxeHell(String name, ToolMaterial toolMaterial, CreativeTabs creativeTab){
        super(toolMaterial);
        this.setUnlocalizedName(HellLandCore.MODID + ":" + name);
        this.setTextureName(HellLandCore.MODID + ":" + name);
        this.setCreativeTab(creativeTab);
        GameRegistry.registerItem(this, name);
    }

}
