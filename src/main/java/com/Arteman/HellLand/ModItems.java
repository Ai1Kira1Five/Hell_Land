package com.Arteman.HellLand;

import com.Arteman.HellLand.handler.ConfigurationHandler;
import com.Arteman.HellLand.items.*;
import com.Arteman.HellLand.utils.ItemHell;
import com.Arteman.HellLand.utils.tools.*;

import net.minecraft.item.Item;

public class ModItems {
    //crafting parts
    public static Item BoneBlade;
    public static Item HellCrystal;
    public static Item ToolRod1;
    public static Item WeaponiumIngot;
    public static Item ArcaneGoldIngot;
    public static Item ArcaneSteelIngot;

    //mob drop
    public static Item BloodDrop;
    public static Item BoneFragment;
    public static Item Heart;

    //tools and weapons
    public static SwordsHell BoneSword;
    public static SwordsHell MagicSword;
    public static SwordsHell MagicDoubleSword;
    public static SwordsHell MagicKatana;
    public static SwordsHell MagicSkyth;
    public static SwordsHell MagicBIGDADYSword;
    public static Item MagicHammer;
    public static Item MagicChacram;
    public static HoeHell CrystalHoe;
    public static AxeHell CrystalAxe;
    public static PickaxeHell CrystalPickaxe;
    public static ShovelHell CrystalShovel;
    public static Item Wrench;

    //ore's drop
    public static Item Crystal;
    public static Item AshDust;

    //other stuff
    public static Item Bag;
    public static Item CrystalLatern;
    public static Item Ring;
    public static Item Urn;

    public static void init() {
        //mob drop
        BloodDrop = new ItemHell("bloodDrop", HellLandCore.HellMCTab, false, 0);
        BoneFragment = new ItemHell("boneFragment", HellLandCore.HellMCTab, false, 0);
        Heart = new ItemHell("heart", HellLandCore.HellMCTabStuff, true, 7);
        Crystal = new ItemHell("crystal", HellLandCore.HellMCTabStuff, true, 9);

        //ore's drop
        AshDust = new ItemHell("ashDust", HellLandCore.HellMCTabStuff, false, 0);
        
        //crafting parts
        ToolRod1 = new ItemHell("toolRod1", HellLandCore.HellMCTab, false, 0);
        HellCrystal = new ItemHell("hellCrystal", HellLandCore.HellMCTab, false, 0);
        BoneBlade = new ItemHell("boneBlade", HellLandCore.HellMCTab, false, 0);
        WeaponiumIngot = new ItemHell("weaponiumIngot", HellLandCore.HellMCTabStuff, false, 0);
        ArcaneGoldIngot = new ItemHell("arcaneGoldIngot", HellLandCore.HellMCTabStuff, false, 0);
        ArcaneSteelIngot = new ItemHell("arcaneSteelIngot", HellLandCore.HellMCTabStuff, false, 0);

        //other stuff
        Bag = new Bag("bag", HellLandCore.HellMCTab, false, 0);
        //tools and weapons
        if (ConfigurationHandler.enableTools) {
            //axe
            CrystalAxe = new AxeHell("crystalAxe", HellLandCore.Crystal, HellLandCore.HellMCTab);

            //pickaxe
            CrystalPickaxe = new PickaxeHell("crystalPickaxe", HellLandCore.Crystal, HellLandCore.HellMCTab);

            //hoe
            CrystalHoe = new HoeHell("crystalHoe", HellLandCore.Crystal, HellLandCore.HellMCTab);

            //shovel
            CrystalShovel = new ShovelHell("crystalShovel", HellLandCore.Crystal, HellLandCore.HellMCTab);
        }
        if (ConfigurationHandler.enableSwords) {
            //swords
            MagicSword = new SwordsHell("magicSword", HellLandCore.MagicSteel, HellLandCore.HellMCTab);
            MagicDoubleSword = new SwordsHell("magicDoubleSword", HellLandCore.MagicSteel, HellLandCore.HellMCTab);
            MagicKatana = new SwordsHell("magicKatana", HellLandCore.MagicSteel, HellLandCore.HellMCTab);
            MagicBIGDADYSword = new SwordsHell("magicBIGDADYSword", HellLandCore.MagicSteel, HellLandCore.HellMCTab);

            MagicSkyth = new MagicScythe("magicScythe", HellLandCore.MagicSteel, HellLandCore.HellMCTab);
            BoneSword = new BoneSword("boneSword", HellLandCore.Bone, HellLandCore.HellMCTab);

            MagicHammer = new MagicHammer(HellLandCore.MagicSteel);
            MagicChacram = new MagicChacram(HellLandCore.MagicSteel);
        }

        Wrench = new Wrench();

        //other stuff
        //Ring = new Ring(null, 0, 0);

    }
}
