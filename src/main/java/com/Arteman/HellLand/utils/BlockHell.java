package com.Arteman.HellLand.utils;

import com.Arteman.HellLand.HellLandCore;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;

import java.util.Random;

public class BlockHell extends Block {

    private ItemStack customDrop;

    public BlockHell(String name, Material material,SoundType soundType,CreativeTabs tabs, float hardness, float resistance, float lightLevel) 
    {
        super(material);
        this.setCreativeTab(tabs);
        this.setBlockName(HellLandCore.MODID + ":" + name);
        this.setBlockTextureName(HellLandCore.MODID + ":" + name);
        this.setHardness(hardness);
        this.setResistance(resistance);
        this.setStepSound(soundType);
        this.setHarvestLevel("pickaxe", 3);
        this.setLightLevel(lightLevel);
        GameRegistry.registerBlock(this, name);
        this.setCustomDrop(new ItemStack(this,1));
    }


    public void setCustomDrop(ItemStack itemStack)
    {
        if(itemStack!=null)
        {
        	customDrop = itemStack;
        }
        else
        {
            customDrop = null;
        }
    }

    @Override
    public Item getItemDropped(int par1, Random random, int fortuneLvl){
        return (this.customDrop==null)?new ItemBlock(this):this.customDrop.getItem();
    }

    public int quantityDroppedWithBonus(int min, Random random)
    {
        if(customDrop != null) {
            return MathHelper.clamp_int(this.quantityDropped(random), 1, customDrop.stackSize);
        }else{
            return 1;
        }
    }

    public int quantityDropped(Random random)
    {
        return random.nextInt(this.customDrop.stackSize);
    }

   // @Override
   // @SideOnly(Side.CLIENT)
   // public int getRenderType() {
    //    return ClientProxy.renderId;
    //}

}
