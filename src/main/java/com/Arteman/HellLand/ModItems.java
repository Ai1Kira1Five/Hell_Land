package com.Arteman.HellLand;

import com.Arteman.HellLand.items.BloodDrop;
import com.Arteman.HellLand.items.BoneBlade;
import com.Arteman.HellLand.items.BoneFragment;
import com.Arteman.HellLand.items.BoneSword;
import com.Arteman.HellLand.items.HellCrystal;
import com.Arteman.HellLand.items.MagicBIGDADYSword;
import com.Arteman.HellLand.items.MagicDoubleSword;
import com.Arteman.HellLand.items.MagicKatana;
import com.Arteman.HellLand.items.MagicScythe;
import com.Arteman.HellLand.items.MagicSword;
import com.Arteman.HellLand.items.ToolRodI;

import net.minecraft.item.Item;

public class ModItems 
{
	
	public static Item HellCrystal;
	public static Item BloodDrop;
	public static Item BoneFragment;
	public static Item ToolRobI;
	public static Item BoneBlade;
	public static Item BoneSword;
	public static Item MagicSword;
	public static Item MagicDoubleSword;
	public static Item MagicKatana;
	public static Item MagicSkyth;
	public static Item MagicBIGDADYSword;
	
	public static void init()
	{
		HellCrystal = new HellCrystal();
		BloodDrop = new BloodDrop();
		BoneFragment = new BoneFragment();
		ToolRobI = new ToolRodI();
		BoneBlade = new BoneBlade();
		BoneSword = new BoneSword(HellLand.Bone);
		MagicSword = new MagicSword(HellLand.MagicSteel);
		MagicDoubleSword = new MagicDoubleSword(HellLand.MagicSteel);
		MagicKatana = new MagicKatana(HellLand.MagicSteel);
		MagicSkyth = new MagicScythe(HellLand.MagicSteel);
		MagicBIGDADYSword = new MagicBIGDADYSword(HellLand.MagicSteel);
	}
}
