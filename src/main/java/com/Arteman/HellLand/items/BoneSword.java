package com.Arteman.HellLand.items;

import com.Arteman.HellLand.utils.tools.SwordsHell;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoneSword extends SwordsHell {

    public BoneSword(String name, ToolMaterial toolMaterial, CreativeTabs creativeTab) {
        super(name,toolMaterial,creativeTab);
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemSt, EntityPlayer player, List ls, boolean inf) {
        ls.add("Not so good weapon, but you are need it...");
    }

    public ItemStack getItemStack(double charge)
    {
      ItemStack ret = super.getItemStack(charge);
      
      Map<Integer, Integer> enchantmentMap = new HashMap();
      enchantmentMap.put(Integer.valueOf(Enchantment.fortune.effectId), Integer.valueOf(3));
      
      EnchantmentHelper.setEnchantments(enchantmentMap, ret);
      
      return ret;
    }

    @Override
    public void onUpdate(ItemStack itSt, World world, Entity pl, int i1, boolean ch) {
        super.onUpdate(itSt, world, pl, i1, ch);
            EntityPlayer player = (EntityPlayer) pl;
            ItemStack equipped = player.getCurrentEquippedItem();
            if (equipped == itSt) {
                player.addPotionEffect(new PotionEffect(Potion.moveSpeed.getId(), 100, 2));
            }
    }

    public boolean getIsRepairable(ItemStack itemSt1, ItemStack itemSt2) {
        ItemStack mat = ToolMaterial.valueOf(this.getToolMaterialName()).getRepairItemStack();
        if (mat != null && net.minecraftforge.oredict.OreDictionary.itemMatches(mat, itemSt2, false)) return true;
        return super.getIsRepairable(itemSt1, itemSt2);
    }
}
