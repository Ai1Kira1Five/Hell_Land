package com.Arteman.HellLand.otherStuff;

import com.Arteman.HellLand.otherStuff.enchantments.EnchantmentsAngels;
import com.Arteman.HellLand.otherStuff.enchantments.EnchantmentsDevils;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;

public class Enchantments{
    public static Enchantment EnchantmentsDevil, EnchantmentsAngel;

    public static void init() {
        EnchantmentsDevil = new EnchantmentsDevils(84, 5, EnumEnchantmentType.weapon);
        EnchantmentsAngel = new EnchantmentsAngels(85, 5, EnumEnchantmentType.weapon);
    }
}
