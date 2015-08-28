package com.Arteman.HellLand.utils;

import com.Arteman.HellLand.HellLand;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;

import java.util.List;
import java.util.Random;

public class BlockHell extends Block {

    public boolean hasSub;
    private ItemStack customDrop = null;
    public String name;
    public IIcon[] blockIcons;

    public BlockHell(String name, Material material,SoundType soundType,CreativeTabs tabs, float hardness, float resistance, float lightLevel, boolean hasSub, int maxMeta) {
        super(material);
        this.name = name;
        this.setCreativeTab(tabs);
        this.setBlockName(HellLand.MODID + ":" + name);
        this.setBlockTextureName(HellLand.MODID + ":" + name);
        this.setHardness(hardness);
        this.setResistance(resistance);
        this.setStepSound(soundType);
        this.setHarvestLevel("pickaxe", 3);
        this.setLightLevel(lightLevel);
        this.hasSub = hasSub;
        this.blockIcons = new IIcon[maxMeta+1];
        registerBlock();
    }

    public void setCustomDrop(ItemStack itemStack)
    {
        if(customDrop == null && itemStack!=null)
        {
            customDrop = itemStack;
        }
        else
        {
            customDrop = null;
        }
    }

    public Item getItemDropped(int meta, Random random, int fortuneLvl){
        return (this.customDrop == null) ? Item.getItemFromBlock(this): this.customDrop.getItem();
    }

    public Item getItemDropped()
    {
        return (customDrop!=null)?customDrop.getItem():new ItemBlock(this);
    }

    public int quantityDroppedWithBonus(int min, Random random)
    {
        if(customDrop != null) 
        {
            return MathHelper.clamp_int(this.quantityDropped(random), 1, customDrop.stackSize);
        }
        else
        {
            return 1;
        }
    }

    public int quantityDropped(Random random)
    {
        return random.nextInt(this.customDrop.stackSize);
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister){
        if(hasSub) {
            for (int i = 0; i < this.blockIcons.length; ++i) {
                this.blockIcons[i] = iconRegister.registerIcon(this.getTextureName() + "_" + i);
            }
        }else{
            this.blockIcons[0] = iconRegister.registerIcon(this.getTextureName());
        }
    }

	public boolean isFullCube()
	{
		return true;
	}

   // @Override
   // @SideOnly(Side.CLIENT)
   // public int getRenderType() {
    //    return ClientProxy.renderId;
    //}


    @Override
    public int damageDropped(int meta){
        return (hasSub)?meta:0;
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta){
        if (meta < 0 || meta >= this.blockIcons.length){
            meta = 0;
        }
        return this.blockIcons[meta];
    }

    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list){
        for(int i=0;i<this.blockIcons.length;++i){
            list.add(new ItemStack(item, 1, i));
        }
    }

    public String getName(){
        return name;
    }

    public int getMaxMeta(){
        return this.blockIcons.length-1;
    }

    public void registerBlock()
    {
        if(this.hasSub)
        {
            GameRegistry.registerBlock(this, ItemBlockHell.class, name);
        }
        else
        {
            GameRegistry.registerBlock(this, ItemBlock.class, name);
        }
        System.out.println(String.format("Successfully register block: %s", this.getName()));
    }

    public static class ItemBlockHell extends ItemBlockWithMetadata
    {
        public ItemBlockHell(Block block)
        {
            super(block,block);
            this.hasSubtypes = ((BlockHell) block).hasSub;
            this.maxStackSize = 64;
        }
        
        @SideOnly(Side.CLIENT)
        public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list)
        {
            for(int i=0;i<=this.getMaxDamage();++i)
            {
                list.add(new ItemStack(item, 1, i));
            }
        }
        
        public String getUnlocalizedName(ItemStack itemStack)
        {
            int meta = itemStack.getItemDamage();
            meta = MathHelper.clamp_int(meta, 0, ((BlockHell)this.field_150939_a).getMaxMeta());
            return this.getUnlocalizedName()+"_"+meta;
        }
        
        @SideOnly(Side.CLIENT)
        public IIcon getIconFromDamage(int metadata)
        {
            return ((BlockHell)this.field_150939_a).blockIcons[metadata];
        }
    }
}
