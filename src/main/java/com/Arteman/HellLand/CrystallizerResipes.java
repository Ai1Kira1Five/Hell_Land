package com.Arteman.HellLand;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class CrystallizerResipes
{
	
	private static final CrystallizerResipes crystallBase = new CrystallizerResipes();
	private Map crystallList = new HashMap();
	private Map experienceList = new HashMap();
	private static final String __OBFID = "CL_00000085";
	
	public static CrystallizerResipes crystallizing()
	{
		return crystallBase;
	}
	
	private CrystallizerResipes()
	{
		this.Items(ModItems.BloodDrop, new ItemStack(ModItems.HellCrystal), 15.0F);
		this.Blocks(ModBlocks.HellFragment, new ItemStack(ModBlocks.HellBase), 4.0F);
	}

	public void Blocks(Block block, ItemStack itemStack, float fl)
    {
        this.Items(Item.getItemFromBlock(block), itemStack, fl);
    }
	
	public void Items(Item item, ItemStack itemStack, float fl)
    {
        this.Base(new ItemStack(item, 1, 32767), itemStack, fl);
    }
	
	public void Base(ItemStack itemStack, ItemStack itemStack1, float fl)
    {
        this.crystallList.put(itemStack, itemStack1);
        this.experienceList.put(itemStack1, Float.valueOf(fl));
    }
	
	public static CrystallizerResipes init() 
	{
		return crystallizing();
	}
	
	public ItemStack getCrystallResult(ItemStack itemStack)
    {
        Iterator iterator = this.crystallList.entrySet().iterator();
        Entry entry;

        do
        {
            if (!iterator.hasNext())
            {
                return null;
            }

            entry = (Entry)iterator.next();
        }
        while (!this.Stacks(itemStack, (ItemStack)entry.getKey()));

        return (ItemStack)entry.getValue();
    }
	
	public Map getCrystallList()
    {
        return this.crystallList;
    }
	
	private boolean Stacks(ItemStack itemStack, ItemStack itemStack1)
    {
        return itemStack1.getItem() == itemStack.getItem() && (itemStack1.getItemDamage() == 32767 || itemStack1.getItemDamage() == itemStack.getItemDamage());
    }
	
	public float Exp(ItemStack itemStack)
    {
        float ret = itemStack.getItem().getSmeltingExperience(itemStack);
        if (ret != -1) return ret;

        Iterator iterator = this.experienceList.entrySet().iterator();
        Entry entry;

        do
        {
            if (!iterator.hasNext())
            {
                return 0.0F;
            }

            entry = (Entry)iterator.next();
        }
        while (!this.Stacks(itemStack, (ItemStack)entry.getKey()));

        return ((Float)entry.getValue()).floatValue();
    }
}
