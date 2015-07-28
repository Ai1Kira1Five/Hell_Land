package com.Arteman.HellLand.items;

import java.util.List;

import com.Arteman.HellLand.HellLand;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class Crystall extends Item 
{
	public IIcon[] icons = new IIcon[10];
	
	public Crystall(String unlocalizedName)
	{
		super();
		this.setHasSubtypes(true);
		this.setUnlocalizedName(unlocalizedName);
		this.setCreativeTab(HellLand.HellMCTabStuff);
	}
	
	@Override
	public void registerIcons(IIconRegister reg)
	{
		for(int i = 0; i < 10; i++)
		{
			this.icons[i] = reg.registerIcon(HellLand.MODID + ":crystall_" + i);
		}
	}
	
	@Override
	public IIcon getIconFromDamage(int meta) 
	{
	    if (meta > 10)
	        meta = 0;

	    return this.icons[meta];
	}
	
	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list) 
	{
	    for (int i = 0; i < 10; i ++)
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
