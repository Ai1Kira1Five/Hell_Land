package com.Arteman.HellLand.blocks;

import com.Arteman.HellLand.HellLand;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class StrongStone extends Block 
{
	public final String name = "Strong Stone";
	public StrongStone(String name, Material material)
	{
		super(Material.rock);
		this.setCreativeTab(HellLand.HellMCTab);
		this.setBlockName(HellLand.MODID + ":" + name);
		this.setBlockTextureName(HellLand.MODID + ":" + name);
		this.setHardness(20.0f);
		this.setResistance(10000.0f);
		this.setStepSound(soundTypePiston);
		this.setHarvestLevel("pickaxe", 3);
		GameRegistry.registerBlock(this, name);
	}
}
