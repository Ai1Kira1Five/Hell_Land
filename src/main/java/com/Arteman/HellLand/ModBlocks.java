package com.Arteman.HellLand;

import com.Arteman.HellLand.blocks.*;
import com.Arteman.HellLand.blocks.machines.AmuletTable;
import com.Arteman.HellLand.blocks.machines.CrystalOven;
import com.Arteman.HellLand.blocks.machines.HellOven;
import com.Arteman.HellLand.blocks.machines.MagicalMixer;
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
		public static Block BloodWoodLogs;
		public static Block Basalt;
		public static Block Marble;
		public static Block Ash;
		public static Block NecroticLamp;
		public static Block CrystallBlock;
		public static Block ArcaneGoldBlock;
		public static Block ArcaneSteelBlock;

		//machines
		public static Block HellOvenIdle;
		public static Block HellOvenActive;
		public static Block CrystalOvenIdle;
		public static Block CrystalOvenActive;
		public static Block SoulCrystallizerIdle;
		public static Block SoulCrystallizerActive;
		public static Block MMixer;
		
		//Wires
		public static Block WireOne;
	
	
	
	public final static void init()
	{
		HellFragment = new HellFragment("Hell Fragment", Material.iron);
		HellBase = new HellBase("Hell Base", Material.iron);
		StrongStone = new StrongStone("Strong Stone", Material.rock);
		BloodWood = new BloodWood("Blood Wood", Material.wood);
		AmuletTable = new AmuletTable("Amulet Table", Material.wood);
		BloodWoodLogs = new BloodWoodLogs("Blood Planks", Material.wood);
		Basalt = new Basalt("Basalt", Material.rock);
		Marble = new Marble("Marble", Material.rock);
		Ash = new Ash("Ash", Material.sand);
		NecroticLamp = new NecroticLamp("necrotic_lamp", Material.rock);
		CrystallBlock = new CrystallBlock("crystall_block", Material.rock);
		ArcaneGoldBlock = new ArcaneGoldBlock("arcane_golg_block", Material.iron);
		ArcaneSteelBlock = new ArcaneSteelBlock("arcane_steel_block", Material.iron);
 
		//machines
		HellOvenIdle = new HellOven(false).setBlockName("HellOvenIdle").setCreativeTab(HellLand.HellMCTab).setHardness(3.4f);
		HellOvenActive = new HellOven(true).setBlockName("HellOvenActive").setHardness(3.4f).setLightLevel(1.0f);
		SoulCrystallizerIdle = new SoulCrystallizer(false).setCreativeTab(HellLand.HellMCTab);
		SoulCrystallizerActive = new SoulCrystallizer(true).setLightLevel(2.0f);
		CrystalOvenIdle = new CrystalOven(false).setBlockName("CrystallOvenIdle").setCreativeTab(HellLand.HellMCTab).setHardness(3.4f);
		CrystalOvenActive = new CrystalOven(true).setBlockName("CrystallOvenActive").setHardness(3.4f).setLightLevel(2.0f);
		MMixer = new MagicalMixer();
		
		
		//wires
		WireOne = new WireOne().setBlockName("Wire lvl I").setCreativeTab(HellLand.HellMCTab);
		
		//Register bad blocks
		GameRegistry.registerBlock(CrystalOvenIdle, "CrystalOvenIdle");
		GameRegistry.registerBlock(CrystalOvenActive, "CrystalOvenActive");
		GameRegistry.registerBlock(HellOvenIdle, "HellOvenIdle");
		GameRegistry.registerBlock(HellOvenActive, "HellOvenActive");
		GameRegistry.registerBlock(WireOne, "WireOne");
		GameRegistry.registerBlock(SoulCrystallizerIdle, "SoulCrystallizerIdle");
		GameRegistry.registerBlock(SoulCrystallizerActive, "SoulCrystallizerActive");
	}
}
