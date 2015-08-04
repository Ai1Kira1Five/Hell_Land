package com.Arteman.HellLand.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class MixedItem extends Item {
    private String mixerItems;

    public Item setMixerItems(String string) {
        this.mixerItems = string;
        return this;
    }

    public String getMixedItem(ItemStack itStack) {
        return this.mixerItems;
    }

    public boolean isMixedIngredient(ItemStack itStack) {
        return this.getMixedItem(itStack) != null;
    }
}
