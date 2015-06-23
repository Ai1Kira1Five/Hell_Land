package com.Arteman.HellLand.items;


import java.util.List;

import com.Arteman.HellLand.HellLand;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class BoneSword extends ItemSword
{
	private String name = "Bone Sword";
	
	public final ToolMaterial toolMaterial;
	
	public BoneSword(ToolMaterial enumToolMaterial)
	{
		super(enumToolMaterial);
		toolMaterial = enumToolMaterial;
		this.setUnlocalizedName(HellLand.MODID + ":" + name);
		this.setTextureName(HellLand.MODID + ":" + name);
		this.setCreativeTab(HellLand.HellMCTab);
		GameRegistry.registerItem(this, name);
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack itemSt, EntityPlayer player, List ls, boolean inf)
	{
		ls.add("Not so good weapon, but you are need it...");
	}

	public void onItemUse(EntityPlayer pl, ItemStack itemST, List ls)
	{
		pl.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 500, 4));
		itemST.addEnchantment(Enchantment.looting, 10);
	}
	
	//public void onItemTick(World world, EntityPlayer player, ItemStack itemStack)
	//{
	//	player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 500, 4));
	//	itemStack.addEnchantment(Enchantment.looting, 10);
	//}

	public boolean getIsRepairable(ItemStack itemSt1, ItemStack itemSt2)
	{
		ItemStack mat = this.toolMaterial.getRepairItemStack();
		if (mat != null && net.minecraftforge.oredict.OreDictionary.itemMatches(mat, itemSt2, false)) return true;
		return super.getIsRepairable(itemSt1, itemSt2);
	}
}
