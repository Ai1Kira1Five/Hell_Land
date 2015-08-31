package com.Arteman.HellLand.items;

import java.util.Set;

import com.Arteman.HellLand.HellLand;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;

public class MagicHammer extends ItemPickaxe {
    private String name = "magicHammer";

    public final ToolMaterial toolMaterial;

    public MagicHammer(ToolMaterial enumToolMaterial) {
        super(enumToolMaterial);
        toolMaterial = enumToolMaterial;
        this.setUnlocalizedName(HellLand.MODID + ":" + name);
        this.setTextureName(HellLand.MODID + ":" + name);
        this.setCreativeTab(HellLand.HellMCTab);
        GameRegistry.registerItem(this, name);
    }
    
    @Override
    public Set<String> getToolClasses(ItemStack stack) {
        return ImmutableSet.of("pickaxe", "sword");
    }
    
    /*
    private static Set effectiveAgainst = 
    		Sets.newHashSet(new Block[] {
    	    Blocks.planks,
    	    Blocks.bookshelf,
    	    Blocks.log,
    	    Blocks.log2, 
    	    Blocks.chest,
    	    Blocks.pumpkin,
    	    Blocks.lit_pumpkin});
    	    */
}
