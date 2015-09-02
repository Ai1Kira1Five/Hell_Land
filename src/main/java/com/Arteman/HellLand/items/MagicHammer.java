package com.Arteman.HellLand.items;

import java.util.Random;
import java.util.Set;

import com.Arteman.HellLand.HellLand;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

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
    
    /*в данный момент не работает, раюотаю в этом направлении*/
    public void onItemUse(World world, int x, int y, int z, Random rand, Item item, ItemStack itemStack, EntityPlayer player, EntityLivingBase entity){
    	if (!world.isRemote && item.onLeftClickEntity(itemStack, player, entity))
    	{
    	    double motionX = rand.nextGaussian() * 0.02D;
    	    double motionY = rand.nextGaussian() * 0.02D;
    	    double motionZ = rand.nextGaussian() * 0.02D;
    	    world.spawnParticle(
    	          "magicCrit", 
    	          x + rand.nextFloat(), 
    	          y + 0.5D + rand.nextFloat(), 
    	          z + rand.nextFloat(), 
    	          motionX, 
    	          motionY, 
    	          motionZ);
    	}
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
