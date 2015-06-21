package com.Arteman.HellLand.blocks;

import java.util.Iterator;

import com.Arteman.HellLand.HellLand;
import com.Arteman.HellLand.blocks.machines.HellOven;
import com.Arteman.HellLand.blocks.machines.SoulCrystallizer;
import com.Arteman.HellLand.blocks.wire.WireOne;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class ModBlocks {
	
	public static Block HellFragment;
	public static Block HellBase;
	public static Block StrongStone;
	
	//machines
	public static Block HellOvenIdle;
	public static Block HellOvenActive;
	public static Block SoulCrystallizer;
	//public static Block SoulCrystallizerActive;
	
	//Wires
	public static Block WireOne;
	
	
	
	public final static void init()
	{
		HellFragment = new HellFragment("Hell Fragment", Material.iron);
		HellBase = new HellBase("Hell Base", Material.iron);
		StrongStone = new StrongStone("Strong Stone", Material.rock);
		
		//machines
		HellOvenIdle = new HellOven(false).setBlockName("HellOvenIdle").setCreativeTab(HellLand.HellMCTab).setHardness(3.4f);
		HellOvenActive = new HellOven(true).setBlockName("HellOvenActive").setCreativeTab(HellLand.HellMCTab).setHardness(3.4f).setLightLevel(1.0f);
		SoulCrystallizer = new SoulCrystallizer(false);
		SoulCrystallizer = new SoulCrystallizer(true).setLightLevel(2.0f);
		
		
		
		//wires
		WireOne = new WireOne().setBlockName("Wire lvl I").setCreativeTab(HellLand.HellMCTab);
		
		//Register bad blocks
		GameRegistry.registerBlock(HellOvenIdle, "HellOvenIdle");
		GameRegistry.registerBlock(WireOne, "WireOne");
		GameRegistry.registerBlock(SoulCrystallizer, "SoulCrystallizer");
	}
}
