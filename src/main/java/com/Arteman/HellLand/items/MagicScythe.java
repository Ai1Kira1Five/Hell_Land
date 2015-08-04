package com.Arteman.HellLand.items;

import com.Arteman.HellLand.utils.tools.SwordsHell;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class MagicScythe extends SwordsHell {
    public MagicScythe(String name,ToolMaterial enumToolMaterial,CreativeTabs creativeTab) {
        super(name, enumToolMaterial, creativeTab);
    }

    @Override
    public boolean onBlockStartBreak(ItemStack stack, int x, int y, int z, EntityPlayer player) {
        if (!stack.hasTagCompound())
            return false;

        World world = player.worldObj;
        final Block blockB = world.getBlock(x, y, z);
        final int meta = world.getBlockMetadata(x, y, z);
        if (!stack.hasTagCompound())
            return false;
        NBTTagCompound tags = stack.getTagCompound().getCompoundTag("InfiTool");
        boolean butter = EnchantmentHelper.getEnchantmentLevel(Enchantment.silkTouch.effectId, stack) > 0;
        int fortune = EnchantmentHelper.getFortuneModifier(player);
        if (!world.isRemote)
            world.playAuxSFX(2001, x, y, z, Block.getIdFromBlock(blockB) + (meta << 12));
        return super.onBlockStartBreak(stack, x, y, z, player);
    }
}
