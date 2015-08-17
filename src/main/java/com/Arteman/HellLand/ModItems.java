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

    //other stuff
    public static Item Bag;
    public static Item CrystalLatern;
    public static Item Ring;
    public static Item Urn;

    public static void init() {
        //mob drop
        BloodDrop = new ItemHell("bloodDrop", HellLand.HellMCTab, false, 0);
        BoneFragment = new ItemHell("boneFragment", HellLand.HellMCTab, false, 0);
        Heart = new ItemHell("heart", HellLand.HellMCTabStuff, true, 7);
        Crystal = new ItemHell("crystal", HellLand.HellMCTabStuff, true, 9);

        //crafting parts
        ToolRod1 = new ItemHell("toolRod1", HellLand.HellMCTab, false, 0);
        HellCrystal = new ItemHell("hellCrystal", HellLand.HellMCTab, false, 0);
        BoneBlade = new ItemHell("boneBlade", HellLand.HellMCTab, false, 0);
        WeaponiumIngot = new ItemHell("weaponiumIngot", HellLand.HellMCTabStuff, false, 0);
        ArcaneGoldIngot = new ItemHell("arcaneGoldIngot", HellLand.HellMCTabStuff, false, 0);
        ArcaneSteelIngot = new ItemHell("arcaneSteelIngot", HellLand.HellMCTabStuff, false, 0);

        //tools and weapons
        if (ConfigurationHandler.enableTools) {
            //axe
            CrystalAxe = new AxeHell("crystalAxe", HellLand.Crystal, HellLand.HellMCTab);

            //pickaxe
            CrystalPickaxe = new PickaxeHell("crystalPickaxe", HellLand.Crystal, HellLand.HellMCTab);

            //hoe
            CrystalHoe = new HoeHell("crystalHoe", HellLand.Crystal, HellLand.HellMCTab);

            //shovel
            CrystalShovel = new ShovelHell("crystalShovel", HellLand.Crystal, HellLand.HellMCTab);
        }
        if (ConfigurationHandler.enableSwords) {
            //swords
            MagicSword = new SwordsHell("magicSword", HellLand.MagicSteel, HellLand.HellMCTab);
            MagicDoubleSword = new SwordsHell("magicDoubleSword", HellLand.MagicSteel, HellLand.HellMCTab);
            MagicKatana = new SwordsHell("magicKatana", HellLand.MagicSteel, HellLand.HellMCTab);
            MagicBIGDADYSword = new SwordsHell("magicBIGDADYSword", HellLand.MagicSteel, HellLand.HellMCTab);

            MagicSkyth = new MagicScythe("magicScythe", HellLand.MagicSteel, HellLand.HellMCTab);
            BoneSword = new BoneSword("boneSword", HellLand.Bone, HellLand.HellMCTab);

            MagicHammer = new MagicHammer(HellLand.MagicSteel);
            MagicChacram = new MagicChacram(HellLand.MagicSteel);
        }

        Wrench = new Wrench();

        //other stuff
        //Ring = new Ring(null, 0, 0);

    }
}
