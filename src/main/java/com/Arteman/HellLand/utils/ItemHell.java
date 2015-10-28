package com.Arteman.HellLand.utils;

import com.Arteman.HellLand.HellLand;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.List;

public class ItemHell extends Item {

    private boolean hasType = false;
    private IIcon[] icons = new IIcon[16];
    private int maxMeta = 0;
    private int spriteNumber = 1;

    public ItemHell(String name, CreativeTabs creativeTabs, boolean hasType,int maxMeta){
        this.setFull3D();
        this.setCreativeTab(creativeTabs);
        this.setUnlocalizedName(HellLand.MODID + ":" + name);
        GameRegistry.registerItem(this, name);
        this.setHasType(hasType, maxMeta);
    }

    public void setHasType(boolean hasType, int maxMeta){
        this.hasType = hasType;
        this.setHasSubtypes(this.hasType);
        this.maxMeta = maxMeta;
    }



    @Override
    public void registerIcons(IIconRegister reg) {
        if(hasSubtypes) {
            for (int i = 0; i < this.maxMeta+1; i++) {
                this.icons[i] = reg.registerIcon(HellLand.MODID + ":"+getUnlocalizedName().substring(getUnlocalizedName().indexOf(":")+1)+"_"+i);
            }
        }else{
            this.icons[0] = reg.registerIcon(HellLand.MODID + ":"+getUnlocalizedName().substring(getUnlocalizedName().indexOf(":")+1));;
        }
    }

    @Override
    public IIcon getIconFromDamage(int meta) {
        if(hasSubtypes) {
            if (meta > 15)
                meta = 0;
        }
        return this.icons[meta];
    }

    @SuppressWarnings("unchecked")
    @Override
    public void getSubItems(Item item, CreativeTabs tab, List list) {
        if(hasSubtypes) {
            for (int i = 0; i < this.maxMeta+1; i++) {
                list.add(new ItemStack(item, 1, i));
            }
        }else{
            list.add(new ItemStack(item,1,0));
        }
    }

    @SuppressWarnings("unused")
	@Override
    public String getUnlocalizedName(ItemStack stack)
    {
        if(hasSubtypes) {
            for (int i = 0; i < this.maxMeta+1; i++) {
                return this.getUnlocalizedName() + "_" + stack.getItemDamage();
            }
        }
            return this.getUnlocalizedName();
    }
    
    @SideOnly(Side.CLIENT)
    public final void addInformation(ItemStack is, EntityPlayer player, List list, boolean verbose)
    {
      HellLand.brand(is, player, list, verbose);
    }
    
    public void addExtraInformation(ItemStack is, EntityPlayer player, List list, boolean verbose) {}
    
    @SideOnly(Side.CLIENT)
    public int getSpriteNumber()
    {
      return this.spriteNumber;
    }
    
    public void setSpriteNumber(int num)
    {
      this.spriteNumber = num;
    }
}