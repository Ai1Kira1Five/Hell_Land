package com.Arteman.HellLand;

import com.Arteman.HellLand.blocks.BloodWood;
import com.Arteman.HellLand.blocks.BloodWoodLogs;
import com.Arteman.HellLand.blocks.NecroticLamp;
import com.Arteman.HellLand.blocks.machines.*;
import com.Arteman.HellLand.blocks.wire.WireOne;
import com.Arteman.HellLand.utils.BlockHell;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import com.Arteman.HellLand.ModItems;

public class ModBlocks {

    //blocks
    public static BlockHell HellFragment;
    public static BlockHell HellBase;
    public static BlockHell StrongStone;
    public static BlockHell AmuletTable;
    public static BlockHell BloodWood;
    public static BlockHell BloodWoodLogs;
    public static BlockHell Basalt;
    public static BlockHell Marble;
    public static BlockHell Ash;
    public static BlockHell NecroticLamp;
    public static BlockHell CrystallBlock;
    public static BlockHell ArcaneGoldBlock;
    public static BlockHell ArcaneSteelBlock;

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


    public final static void init() {

        HellFragment = new BlockHell("hellFragment", Material.iron, Block.soundTypeMetal, HellLand.HellMCTab, 10.0f, 1000.0f, 0.0f);
        HellBase = new BlockHell("hellBase", Material.iron, Block.soundTypeMetal, HellLand.HellMCTab, 10.0f, 1000.0f, 0.0f);
        StrongStone = new BlockHell("strongStone", Material.rock, Block.soundTypePiston, HellLand.HellMCTab, 10.0f, 10000.0f, 0.0f);
        Marble = new BlockHell("marble", Material.rock, Block.soundTypeStone, HellLand.HellMCTabDecor, 10.0f, 100.0f, 0.0f);
        CrystallBlock = new BlockHell("crystalBlock", Material.rock, Block.soundTypeGlass, HellLand.HellMCTabDecor, 10.0f, 50.0f, 1.0f);
        ArcaneGoldBlock = new BlockHell("arcaneGoldBlock", Material.iron, Block.soundTypeMetal, HellLand.HellMCTab, 10.0f, 50.0f, 0.0f);
        ArcaneSteelBlock = new BlockHell("arcaneSteelBlock", Material.iron, Block.soundTypeMetal, HellLand.HellMCTab, 10.0f, 50.0f, 0.0f);
        Basalt = new BlockHell("basalt", Material.rock, Block.soundTypeStone, HellLand.HellMCTabDecor, 10.0f, 100.0f, 0.0f);
        Ash = new BlockHell("ash", Material.sand, Block.soundTypeSand, HellLand.HellMCTabDecor, 3.0f, 100.0f, 0.0f);
            Ash.setCustomDrop(new ItemStack(ModItems.AshDust,4));
            Ash.setHarvestLevel("shovel",1);

        BloodWood = new BloodWood("bloodWood");
        AmuletTable = new AmuletTable("amuletTable");
        BloodWoodLogs = new BloodWoodLogs("bloodPlanks");
        NecroticLamp = new NecroticLamp("necroticLamp");

        //machines
        HellOvenIdle = new HellOven(false).setCreativeTab(HellLand.HellMCTab).setHardness(3.4f);
        HellOvenActive = new HellOven(true).setHardness(3.4f).setLightLevel(1.0f);
        SoulCrystallizerIdle = new SoulCrystallizer(false).setCreativeTab(HellLand.HellMCTab);
        SoulCrystallizerActive = new SoulCrystallizer(true).setLightLevel(2.0f);
        CrystalOvenIdle = new CrystalOven(false).setCreativeTab(HellLand.HellMCTab).setHardness(3.4f);
        CrystalOvenActive = new CrystalOven(true).setHardness(3.4f).setLightLevel(2.0f);
        MMixer = new MagicalMixer();


        //wires
        WireOne = new WireOne().setCreativeTab(HellLand.HellMCTab);

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
