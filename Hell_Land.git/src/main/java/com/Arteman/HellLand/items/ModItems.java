package com.Arteman.HellLand.items;

import com.Arteman.HellLand.HellLand;

import net.minecraft.item.Item;

public class ModItems 
{
	
	public static Item HellCrystal;
	public static Item BloodDrop;
	public static Item BoneFragment;
	public static Item ToolRobI;
	public static Item BoneBlade;
	public static Item BoneSword;
	
	public static void init()
	{
		HellCrystal = new HellCrystal();
		BloodDrop = new BloodDrop();
		BoneFragment = new BoneFragment();
		ToolRobI = new ToolRodI();
		BoneBlade = new BoneBlade();
		BoneSword = new BoneSword(HellLand.Bone);
	}
}
