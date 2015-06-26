package com.Arteman.HellLand;

import java.util.Iterator;

import com.Arteman.HellLand.blocks.BloodWood;
import com.Arteman.HellLand.blocks.HellBase;
import com.Arteman.HellLand.blocks.HellFragment;
import com.Arteman.HellLand.blocks.StrongStone;
import com.Arteman.HellLand.blocks.machines.AmuletTable;
import com.Arteman.HellLand.blocks.machines.HellOven;
import com.Arteman.HellLand.blocks.machines.SoulCrystallizer;
import com.Arteman.HellLand.blocks.wire.WireOne;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class ModBlocks{
	
	//blocks
	public static Block HellFragment;
	public static Block HellBase;
	public static Block StrongStone;
	public static Block AmuletTable;
	public static Block BloodWood;

	//machines
	public static Block HellOvenIdle;
	public static Block HellOvenActive;
	public static Block SoulCrystallizerIdle;
	public static Block SoulCrystallizerActive;
	
	//Wires
	public static Block WireOne;
	
	
	
	public final static void init()
	{
		HellFragment = new HellFragment("Hell Fragment", Material.iron);
		HellBase = new HellBase("Hell Base", Material.iron);
		StrongStone = new StrongStone("Strong Stone", Material.rock);
		BloodWood = new BloodWood("Blood Wood", Material.wood);
		AmuletTable = new AmuletTable("Amulet Table", Material.wood);

		//machines
		HellOvenIdle = new HellOven(false).setBlockName("HellOvenIdle").setCreativeTab(HellLand.HellMCTab).setHardness(3.4f);
		HellOvenActive = new HellOven(true).setBlockName("HellOvenActive").setHardness(3.4f).setLightLevel(1.0f);
		SoulCrystallizerIdle = new SoulCrystallizer(false).setCreativeTab(HellLand.HellMCTab);
		SoulCrystallizerActive = new SoulCrystallizer(true).setLightLevel(2.0f);
		
		
		
		//wires
		WireOne = new WireOne().setBlockName("Wire lvl I").setCreativeTab(HellLand.HellMCTab);
		
		//Register bad blocks
		GameRegistry.registerBlock(HellOvenIdle, "HellOvenIdle");
		GameRegistry.registerBlock(HellOvenActive, "HellOvenActive");
		GameRegistry.registerBlock(WireOne, "WireOne");
		GameRegistry.registerBlock(SoulCrystallizerIdle, "SoulCrystallizerIdle");
		GameRegistry.registerBlock(SoulCrystallizerActive, "SoulCrystallizerActive");
	}
}
