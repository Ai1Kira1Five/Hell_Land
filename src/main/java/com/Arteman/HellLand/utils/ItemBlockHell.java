package com.Arteman.HellLand.utils;

import com.Arteman.HellLand.HellLand;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.MinecraftForgeClient;

public class ItemBlockHell extends ItemBlock{
	public ItemBlockHell(Block block){
		super(block);
/*ץוץו*/
		//MinecraftForgeClient.registerItemRenderer(this, ItemRenderingHandler.blockItemRenderer);
	}
	
	public String getUnlocalizedName(ItemStack stack){
		if ((this.field_150939_a instanceof BlockHell)){
			return ((BlockHell)this.field_150939_a).getName(stack);
		}
		return HellLand.MODID + ":" + "unknown";
	}

}
