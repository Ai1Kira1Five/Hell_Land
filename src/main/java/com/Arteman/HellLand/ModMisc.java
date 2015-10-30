package com.Arteman.HellLand;

import java.util.HashMap;

import com.Arteman.HellLand.utils.BlockHell;
import com.Arteman.HellLand.utils.ItemBlockHell;
import com.Arteman.HellLand.utils.ItemHell;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

public class ModMisc {
	public static ResourceLocation NULL_TILE_TEXTURE = new ResourceLocation(HellLand.MODID + ":textures/tiles/meta/null.png");
	public static IIcon NULL_BLOCK_ICON;
	public static IIcon NULL_ITEM_ICON;
	private static final HashMap<String, IIcon> blockIcons = new HashMap();
	private static final HashMap<String, IIcon> itemIcons = new HashMap();
	static final HashMap<String, ResourceLocation> tileTextures = new HashMap();
	static final HashMap<String, ResourceLocation> tileModels = new HashMap();
	public static BlockHell BLOCK_NULL;
	public static ItemHell ITEM_NULL;
	private static boolean hasColour;
	private static boolean hasCrystal;
	private static boolean hasLantern;
	private static boolean hasGlass;
	
	public static void registerBlock(BlockHell block, Class<? extends ItemBlock> itemclass, String name){
		GameRegistry.registerBlock(block, itemclass, name);
	}
	
	// ItemHell тоже под переработку, если по этой схеме делать
	public static void registerItem(ItemHell item, String name){
		GameRegistry.registerItem(item, name);
	}
	
	public static void initBlocks(){
		BLOCK_NULL = new BlockHell(null, null, null, null, 0, 0, 0, hasColour, 0);
		GameRegistry.registerBlock(BLOCK_NULL, ItemBlockHell.class, "null_block");
	}
}
