package com.Arteman.HellLand.resipes;

import com.Arteman.HellLand.ModBlocks;
import com.Arteman.HellLand.ModItems;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModRecipes 
{
	public static void init()
	{
		GameRegistry.addRecipe(new ItemStack(ModBlocks.HellBase, 4), new Object[]{ "XYX", "XZX", "XYX", ('X'), Blocks.obsidian, ('Y'), ModItems.HellCrystal, ('Z'), ModBlocks.HellFragment});
		GameRegistry.addRecipe(new ItemStack(ModItems.HellCrystal, 4), new Object[]{ "XXX", "XYX", "XXX", ('X'), ModItems.BloodDrop, ('Y'), Items.diamond});
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.ToolRobI, 2), new Object[]{ "##X", "#Y#", "X##", ('X'), Blocks.obsidian, ('Y'), ModItems.HellCrystal});
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.BoneBlade, 1), "XY", "XY", 'X', ModItems.BoneFragment, 'Y', Items.diamond);
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.StrongStone, 4), new Object[]{ "X#X", "#Y#", "X#X", ('X'), Blocks.obsidian, ('#'), Blocks.netherrack, ('Y'), Blocks.stone});
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.StrongStone, 2), new Object[]{ "X#X", "#Y#", "X#X", ('X'), Blocks.obsidian, ('#'), Blocks.netherrack, ('Y'), Blocks.cobblestone});
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.BoneSword, 1), "Y", "X", 'X', ModItems.ToolRobI, 'Y', ModItems.BoneBlade);
		GameRegistry.addRecipe(new ItemStack(ModBlocks.HellOvenIdle, 1), new Object[]{"XXX", "X#X", "XXX", ('X'), ModBlocks.StrongStone});
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.BloodWood, 2), new Object[]{"#Y#", "YXY", "#Y#", ('X'), Blocks.planks, ('Y'), ModItems.BloodDrop});
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.AmuletTable, 1), new Object[]{ "XX", "XX", ('X'), ModBlocks.BloodWood});
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.BloodWoodLogs, 4), "X", 'X', ModBlocks.BloodWood);
	}
}