package com.Arteman.HellLand.utils.interfaces;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public interface IComplexBlockRenderingHandler extends ISimpleBlockRenderingHandler{
	public abstract void renderInventoryBlockStack(Block block, ItemStack itemStack, int Int, RenderBlocks renderBlocks);
}
