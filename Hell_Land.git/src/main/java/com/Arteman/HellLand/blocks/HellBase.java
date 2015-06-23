package com.Arteman.HellLand.blocks;

import java.util.List;

import com.Arteman.HellLand.HellLand;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

public class HellBase extends Block 
{
	public final String name = "Hell Base";
	public HellBase(String name, Material material)
	{
		super(Material.iron);
		this.setCreativeTab(HellLand.HellMCTab);
		this.setBlockName(HellLand.MODID + ":" + name);
		this.setBlockTextureName(HellLand.MODID + ":" + name);
		this.setHardness(30.0f);
		this.setResistance(1000.0f);
		this.setStepSound(soundTypeMetal);
		this.setHarvestLevel("pickaxe", 3);
		GameRegistry.registerBlock(this, name);
	}
}
