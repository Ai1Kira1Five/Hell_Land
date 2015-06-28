package com.Arteman.HellLand.otherStuff.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;

public class EnchantmentsDevils extends Enchantment
{
	public EnchantmentsDevils(int id, int rarity, EnumEnchantmentType type) 
	{
		super(id, rarity, type.weapon);
		this.setName("Devil's strike");
	}
	
	public int getMinEnchantAbility(int par1)
	{
		return 5 + (par1 - 1) * 10;
	}
	
	public int getMaxEnchantability(int par1)
    {
        return this.getMinEnchantability(par1) + 20;
    }
	
	public int getMaxLevel()
	{
		return 5;
	}
}
