package com.Arteman.HellLand;

import com.Arteman.HellLand.items.ArcaneGoldIngot;
import com.Arteman.HellLand.items.ArcaneSteelIngot;
import com.Arteman.HellLand.items.BloodDrop;
import com.Arteman.HellLand.items.BoneBlade;
import com.Arteman.HellLand.items.BoneFragment;
import com.Arteman.HellLand.items.BoneSword;
import com.Arteman.HellLand.items.Crystall;
import com.Arteman.HellLand.items.CrystallAxe;
import com.Arteman.HellLand.items.CrystallHoe;
import com.Arteman.HellLand.items.CrystallPickaxe;
import com.Arteman.HellLand.items.CrystallShovel;
import com.Arteman.HellLand.items.Heart;
import com.Arteman.HellLand.items.HellCrystal;
import com.Arteman.HellLand.items.MagicBIGDADYSword;
import com.Arteman.HellLand.items.MagicChacram;
import com.Arteman.HellLand.items.MagicDoubleSword;
import com.Arteman.HellLand.items.MagicHammer;
import com.Arteman.HellLand.items.MagicKatana;
import com.Arteman.HellLand.items.MagicScythe;
import com.Arteman.HellLand.items.MagicSword;
import com.Arteman.HellLand.items.Ring;
import com.Arteman.HellLand.items.ToolRodI;
import com.Arteman.HellLand.items.WeaponiumIngot;
import com.Arteman.HellLand.items.Wrench;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class ModItems 
{
	//crafting parts
	public static Item BoneBlade;
	public static Item HellCrystal;
	public static Item ToolRobI;
	public static Item WeaponiumIngot;
	public static Item ArcaneGoldIngot;
	public static Item ArcaneSteelIngot;
	
	//mob drop
	public static Item BloodDrop;
	public static Item BoneFragment;
	public static Item Heart;
	
	//tools and weapons
	public static Item BoneSword;
	public static Item MagicSword;
	public static Item MagicDoubleSword;
	public static Item MagicKatana;
	public static Item MagicSkyth;
	public static Item MagicBIGDADYSword;
	public static Item MagicHammer;
	public static Item MagicChacram;
	public static Item CrystallHoe;
	public static Item CrystallAxe;
	public static Item CrystallPickaxe;
	public static Item CrystallShovel;
	public static Item Wrench;
	
	//ore's drop
	public static Item Crystall;
	
	//other stuff
	public static Item Bag;
	public static Item CrystallLatern;
	public static Item Ring;
	public static Item Urn;
	
	public static void init()
	{
		//mob drop
		BloodDrop = new BloodDrop();
		BoneFragment = new BoneFragment();
		GameRegistry.registerItem(Heart = new Heart("heart"), "heart");
		
		//crafting parts
		ToolRobI = new ToolRodI();
		HellCrystal = new HellCrystal();
		BoneBlade = new BoneBlade();
		WeaponiumIngot = new WeaponiumIngot();
		ArcaneGoldIngot = new ArcaneGoldIngot();
		ArcaneSteelIngot = new ArcaneSteelIngot();
		
		//tools and weapons
		BoneSword = new BoneSword(HellLand.Bone);
		MagicSword = new MagicSword(HellLand.MagicSteel);
		MagicDoubleSword = new MagicDoubleSword(HellLand.MagicSteel);
		MagicKatana = new MagicKatana(HellLand.MagicSteel);
		MagicSkyth = new MagicScythe(HellLand.MagicSteel);
		MagicBIGDADYSword = new MagicBIGDADYSword(HellLand.MagicSteel);
		MagicHammer = new MagicHammer(HellLand.MagicSteel);
		MagicChacram = new MagicChacram(HellLand.MagicSteel);
		CrystallHoe = new CrystallHoe(HellLand.Crystall);
		CrystallAxe = new CrystallAxe(HellLand.Crystall);
		CrystallPickaxe = new CrystallPickaxe(HellLand.Crystall);
		CrystallShovel = new CrystallShovel(HellLand.Crystall);
		Wrench = new Wrench();
		
		//other stuff
		//Ring = new Ring(null, 0, 0);
		
		//ore's drop
		GameRegistry.registerItem(Crystall = new Crystall("crystall"), "crystall");
	}
}
