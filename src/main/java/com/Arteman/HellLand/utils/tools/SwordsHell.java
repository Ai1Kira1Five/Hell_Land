package com.Arteman.HellLand.utils.tools;

import com.Arteman.HellLand.HellLand;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;


public class SwordsHell extends ItemSword {

    public SwordsHell(String name, ToolMaterial toolMaterial, CreativeTabs creativeTab){
        super(toolMaterial);
        this.setUnlocalizedName(HellLand.MODID + ":" + name);
        this.setTextureName(HellLand.MODID + ":" + name);
        this.setCreativeTab(creativeTab);
        GameRegistry.registerItem(this,name);
    }

	public ItemStack getItemStack(double charge) {
		ItemStack ret = new ItemStack(this);
		
		return ret;
	}

}
