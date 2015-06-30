package com.Arteman.HellLand.otherStuff.enchantments;

import com.Arteman.HellLand.otherStuff.Enchantments;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class EnchantmentHandler 
{
	@SubscribeEvent
	public void onMobDeath(LivingDeathEvent event)
	{
		if(event.entityLiving != null && !event.entityLiving.worldObj.isRemote && event.source != null && event.source.getSourceOfDamage() != null && event.source.getSourceOfDamage() instanceof EntityPlayer)
		{
			EntityPlayer player = EntityPlayer.class.cast(event.source.getSourceOfDamage());
			EntityLivingBase dyingMob = event.entityLiving;
			ItemStack currentItem = player.getCurrentEquippedItem();
			if(currentItem != null)
			{
				if(EnchantmentHelper.getEnchantmentLevel(Enchantments.EnchantmentsDevil.effectId, currentItem) > 0)
				{
					int enchLevel = EnchantmentHelper.getEnchantmentLevel(Enchantments.EnchantmentsDevil.effectId, currentItem);
				}
			}
		}
	}
	
	@SubscribeEvent
	public void onMobDamage(LivingHurtEvent event)
	{
		
	}
}
