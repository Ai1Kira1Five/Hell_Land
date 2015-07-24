package com.Arteman.HellLand.items;

import java.util.List;

import com.Arteman.HellLand.HellLand;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class Heart extends Item 
{
	public IIcon[] icons = new IIcon[8];
	
	public Heart(String unlocalizedName)
	{
		super();
		this.setHasSubtypes(true);
		this.setUnlocalizedName(unlocalizedName);
		this.setCreativeTab(HellLand.HellMCTabStuff);
	}
	
	@Override
	public void registerIcons(IIconRegister reg)
	{
		for(int i = 0; i < 8; i++)
		{
			this.icons[i] = reg.registerIcon(HellLand.MODID + ":heart_" + i);
		}
	}
	
	@Override
	public IIcon getIconFromDamage(int meta) 
	{
	    if (meta > 7)
	        meta = 0;

	    return this.icons[meta];
	}
	
	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list) 
	{
	    for (int i = 0; i < 8; i ++) 
	    {
	        list.add(new ItemStack(item, 1, i));
	    }
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) 
	{
	    return this.getUnlocalizedName() + "_" + stack.getItemDamage();
	}
}
