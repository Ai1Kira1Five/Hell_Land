package com.Arteman.HellLand;

import com.Arteman.HellLand.blocks.BloodWood;
import com.Arteman.HellLand.blocks.BloodWoodLogs;
import com.Arteman.HellLand.blocks.CrystalSpawn;
import com.Arteman.HellLand.blocks.NecroticLamp;
import com.Arteman.HellLand.blocks.machines.*;
import com.Arteman.HellLand.blocks.wire.WireOne;
import com.Arteman.HellLand.utils.BlockHell;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;

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
    public static BlockHell CrystalBlock;
    public static BlockHell ArcaneGoldBlock;
    public static BlockHell ArcaneSteelBlock;
    public static BlockHell recidiumBrick;
    public static BlockHell alchemicalTable;

    //machines
    public static BlockHell hellOvenIdle;
    public static BlockHell hellOvenActive;
    public static BlockHell crystalOvenIdle;
    public static BlockHell crystalOvenActive;
    public static Block SoulCrystallizerIdle;
    public static Block SoulCrystallizerActive;
    public static BlockHell MMixerIdle;
    public static BlockHell MMixerActive;
    public static Block CrystalSpawn;

    //Wires
    public static Block WireOne;


    public static void init() {

        HellFragment = new BlockHell("hellFragment", Material.iron, Block.soundTypeMetal, HellLand.HellMCTab, 5.0f, 1000.0f, 0.0f,false,0);
        HellBase = new BlockHell("hellBase", Material.iron, Block.soundTypeMetal, HellLand.HellMCTab, 5.0f, 1000.0f, 0.0f,false,0);
        StrongStone = new BlockHell("strongStone", Material.rock, Block.soundTypePiston, HellLand.HellMCTab, 5.0f, 10000.0f, 0.0f,false,0);
        ArcaneGoldBlock = new BlockHell("arcaneGoldBlock", Material.iron, Block.soundTypeMetal, HellLand.HellMCTab, 5.0f, 50.0f, 0.0f,false,0);
        ArcaneSteelBlock = new BlockHell("arcaneSteelBlock", Material.iron, Block.soundTypeMetal, HellLand.HellMCTab, 5.0f, 50.0f, 0.0f,false,0);
        recidiumBrick = new BlockHell("recidiumBrick", Material.iron, Block.soundTypeStone, HellLand.HellMCTab, 5.0f, 50.0f, 0.0f,false,0);
        CrystalBlock = new BlockHell("crystalBlock", Material.rock, Block.soundTypeGlass, HellLand.HellMCTabDecor, 5.0f, 50.0f, 1.0f,true,8);
        Basalt = new BlockHell("basalt", Material.rock, Block.soundTypeStone, HellLand.HellMCTabDecor, 5.0f, 100.0f, 0.0f,true,4);
        Marble = new BlockHell("marble", Material.rock, Block.soundTypeStone, HellLand.HellMCTabDecor, 5.0f, 100.0f, 0.0f,true,4);
        Ash = new BlockHell("ash", Material.sand, Block.soundTypeSand, HellLand.HellMCTabDecor, 2.0f, 100.0f, 0.0f,false,0);
            Ash.setCustomDrop(new ItemStack(ModItems.AshDust,4));
            Ash.setHarvestLevel("shovel",1);

        BloodWood = new BloodWood("bloodWood");
        AmuletTable = new AmuletTable("amuletTable");
        BloodWoodLogs = new BloodWoodLogs("bloodPlanks");
        NecroticLamp = new NecroticLamp("necroticLamp");

        //machines
        hellOvenIdle = new hellOven("hellOvenIdle",HellLand.HellMCTab,false);
        hellOvenActive = new hellOven("hellOvenActive",null,true);
            hellOvenActive.setCustomDrop(new ItemStack(hellOvenIdle));
        SoulCrystallizerIdle = new SoulCrystallizer(false).setCreativeTab(HellLand.HellMCTab);
        SoulCrystallizerActive = new SoulCrystallizer(true).setLightLevel(2.0f);
        crystalOvenIdle = new crystalOven("crystalOvenIdle", HellLand.HellMCTab,false);
        crystalOvenActive = new crystalOven("crystalOvenActive", null ,true);
            crystalOvenActive.setCustomDrop(new ItemStack(crystalOvenIdle));
        CrystalSpawn = new CrystalSpawn();
        alchemicalTable = new alchemicalTable("alchemicalTable",HellLand.HellMCTab);
        MMixerIdle = new MagicalMixer("magicalMixerIdle", HellLand.HellMCTab, false);
        MMixerActive = new MagicalMixer("magicalMixerActive", null, true);
            MMixerActive.setCustomDrop(new ItemStack(MMixerIdle));

        //wires
        WireOne = new WireOne().setCreativeTab(HellLand.HellMCTab);

        GameRegistry.registerBlock(WireOne, "WireOne");
        GameRegistry.registerBlock(SoulCrystallizerIdle, "SoulCrystallizerIdle");
        GameRegistry.registerBlock(SoulCrystallizerActive, "SoulCrystallizerActive");
    }

}
