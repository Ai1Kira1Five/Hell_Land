package com.Arteman.HellLand.utils;

import com.Arteman.HellLand.HellLand;
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

    private static ItemStack customDrop;

    public BlockHell(String name, Material material,SoundType soundType,CreativeTabs tabs, float hardness, float resistance, float lightLevel) {
        super(material);
        this.setCreativeTab(tabs);
        this.setBlockName(HellLand.MODID + ":" + name);
        this.setBlockTextureName(HellLand.MODID + ":" + name);
        this.setHardness(hardness);
        this.setResistance(resistance);
        this.setStepSound(soundType);
        this.setHarvestLevel("pickaxe", 3);
        GameRegistry.registerBlock(this, name);
    }

    public void setCustomDrop(ItemStack itemStack){
        if(customDrop == null && itemStack!=null){
            customDrop = itemStack;
        }else{
            customDrop = null;
        }
    }

    public Item getItemDropped(){
        return (customDrop!=null)?customDrop.getItem():new ItemBlock(this);
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
