package com.Arteman.HellLand.utils;

import com.Arteman.HellLand.ModMisc;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class HellTab extends CreativeTabs{
	ItemStack stack;

	public HellTab(String name){
		super(CreativeTabs.getNextID(), name);
	    setBackgroundImageName("item_search.png");
	}
	
	public Item getTabIconItem(){
		return ModMisc.ITEM_NULL;
	}
	
	public void setItemStack(ItemStack stack){
		this.stack = stack;
	}
	
	@SideOnly(Side.CLIENT)
	public ItemStack getIconItemStack(){
		if (this.stack == null){
			return new ItemStack(getTabIconItem(), 1, 0);
		}
		return this.stack;
	}
	
	public boolean hasSearchBar(){
		return true;
	}
}
