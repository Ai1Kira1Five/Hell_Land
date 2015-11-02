package com.Arteman.HellLand;

import java.util.HashMap;
import java.util.Iterator;

import com.Arteman.HellLand.domains.Metal;
import com.Arteman.HellLand.utils.BlockHell;
import com.Arteman.HellLand.utils.HellTab;
import com.Arteman.HellLand.utils.ItemBlockHell;
import com.Arteman.HellLand.utils.ItemHell;
import com.Arteman.HellLand.utils.interfaces.IDomain;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

public class ModMisc {
	public static ResourceLocation NULL_TILE_TEXTURE = new ResourceLocation(HellLand.MODID + ":textures/tiles/meta/null.png");
	public static final IDomain DOMAIN_METAL = new Metal();
	public static IIcon NULL_BLOCK_ICON;
	public static IIcon NULL_ITEM_ICON;
	
	//возможно будет так...
	public static HellTab TAB_MAIN;
	
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
	
	public static ResourceLocation getTileTexture(String designation){
		if (tileTextures.containsKey(designation)){
			return (ResourceLocation)tileTextures.get(designation);
		}
		return NULL_TILE_TEXTURE;
	}
	
	public static ResourceLocation getTileModel(String designation){
		return (ResourceLocation)tileModels.get(designation);
	}
	
	public static void initTileTextures(){
		for(Iterator i$ = HellLand.registry.getMetalList().iterator(); i$.hasNext();){
			String metal;
			metal = (String)i$.next();
			tileTextures.put("pylon_" + metal, new ResourceLocation(HellLand.MODID + ":" + "textures/tiles/metal/" + metal + "/pylon.png"));
			tileTextures.put("core_" + metal, new ResourceLocation(HellLand.MODID + ":" + "textures/tiles/metal/" + metal + "/core.png"));
		}
		for(String gem : HellLand.registry.getGemList()){
			tileTextures.put("pylon_" + gem, new ResourceLocation(HellLand.MODID + ":" + "textures/tiles/gem/" + gem + "/pylon.png"));
			tileTextures.put("core_" + gem, new ResourceLocation(HellLand.MODID + ":" + "textures/tiles/gem/" + gem + "/core.png"));
		}
	}
	
	public static void initTileModels(){
		tileModels.put("pylon", new ResourceLocation(HellLand.MODID + ":" + "textures/obj/tiles/pylon.obj"));
	    tileModels.put("core", new ResourceLocation(HellLand.MODID + ":" + "textures/obj/tiles/core.obj"));
	}
}
