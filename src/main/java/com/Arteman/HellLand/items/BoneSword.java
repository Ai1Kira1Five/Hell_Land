package com.Arteman.HellLand.items;

import com.Arteman.HellLand.utils.tools.SwordsHell;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

import java.util.List;

public class BoneSword extends SwordsHell {

    public BoneSword(String name, ToolMaterial toolMaterial, CreativeTabs creativeTab) {
        super(name,toolMaterial,creativeTab);
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemSt, EntityPlayer player, List ls, boolean inf) {
        ls.add("Not so good weapon, but you are need it...");
    }

    public void isItemEnchanted(ItemStack itSt){
        if (!itSt.isItemEnchanted()){
            itSt.addEnchantment(Enchantment.sharpness, 4);
            itSt.addEnchantment(Enchantment.looting, 5);
        }

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
