package com.Arteman.HellLand.otherStuff.enchantments;

import com.Arteman.HellLand.otherStuff.Enchantments;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;

public class EnchantmentsAngels extends Enchantment {

    public EnchantmentsAngels(int id, int rarity, EnumEnchantmentType type) {
        super(id, rarity, type.weapon);
        this.setName("angelsDeath");
    }

    public int getMinEnchantAbility(int par1) {
        return 5 + (par1 - 1) * 10;
    }

    public int getMaxEnchantability(int par1) {
        return this.getMinEnchantability(par1) + 20;
    }

    public int getMaxLevel() {
        return 5;
    }

    public boolean canApplyTogether(Enchantments enchant) {
        return this != enchant.EnchantmentsDevil;
    }
}
