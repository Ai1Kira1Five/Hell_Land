package com.Arteman.HellLand.recipes;

import com.Arteman.HellLand.ModItems;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class MixerRecipes {
    private static final MixerRecipes mixerBase = new MixerRecipes();
    private Map mixerList = new HashMap();
    private Map experienceList = new HashMap();
    private static final String __OBFID = "CL_00000085";

    public static MixerRecipes mixering() {
        return mixerBase;
    }

    private MixerRecipes() {
        this.Items(Items.redstone, Items.diamond, ModItems.Crystal, new ItemStack(ModItems.Crystal, 1, 7), 0.1f);
    }

    public void Blocks(Block block1, Block block2, Block block3, ItemStack itemstack, float fl) {
        this.Items(Item.getItemFromBlock(block1), Item.getItemFromBlock(block2), Item.getItemFromBlock(block3), itemstack, fl);
    }

    public void Items(Item item1, Item item2, Item item3, ItemStack itemstack, float fl) {
        this.Base(new ItemStack(item1), new ItemStack(item2), new ItemStack(item3), itemstack, fl);
    }

    public void Base(ItemStack itemstack, ItemStack itemstack1, ItemStack itemstack2, ItemStack itemstack3, float fl) {
        this.mixerList.put(itemstack, itemstack1);
        this.mixerList.put(itemstack2, itemstack3);
        this.experienceList.put(itemstack1, Float.valueOf(fl));
    }

    public static MixerRecipes init() {
        return mixering();
    }

    public ItemStack getMixerResult(ItemStack itemStack) {
        Iterator iterator = this.mixerList.entrySet().iterator();
        Entry entry;

        do {
            if (!iterator.hasNext()) {
                return null;
            }

            entry = (Entry) iterator.next();
        }
        while (!this.Stacks(itemStack, (ItemStack) entry.getKey()));

        return (ItemStack) entry.getValue();
    }

    public Map getMixerList() {
        return this.mixerList;
    }

    private boolean Stacks(ItemStack itemStack, ItemStack itemStack1) {
        return itemStack1.getItem() == itemStack.getItem() && (itemStack1.getItemDamage() == 32767 || itemStack1.getItemDamage() == itemStack.getItemDamage());
    }

    public float Exp(ItemStack itemStack) {
        float ret = itemStack.getItem().getSmeltingExperience(itemStack);
        if (ret != -1) return ret;

        Iterator iterator = this.experienceList.entrySet().iterator();
        Entry entry;

        do {
            if (!iterator.hasNext()) {
                return 0.0F;
            }

            entry = (Entry) iterator.next();
        }
        while (!this.Stacks(itemStack, (ItemStack) entry.getKey()));

        return ((Float) entry.getValue()).floatValue();
    }
}
