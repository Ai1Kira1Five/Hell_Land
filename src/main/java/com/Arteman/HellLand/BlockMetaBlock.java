package com.Arteman.HellLand;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;

public class BlockMetaBlock extends ItemBlockWithMetadata {

    public BlockMetaBlock(Block block) {
        super(block, block);
    }

    public String getUnloclalizedName(ItemStack stack) {
        return this.getUnlocalizedName() + "_" + stack.getItemDamage();
    }
}
