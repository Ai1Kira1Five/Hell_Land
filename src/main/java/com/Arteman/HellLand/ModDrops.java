package com.Arteman.HellLand;

import java.util.Random;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;

public class ModDrops 
{
	public static Random random;
	public static int drop;
	
	@SubscribeEvent
	public void onEntityDrop(LivingDropsEvent event)
	{
		random = new Random();
		drop = random.nextInt(2) + 1;
		
		if(event.entityLiving instanceof EntityZombie)
		{
			event.entityLiving.entityDropItem(new ItemStack(ModItems.BloodDrop), drop);
		}
		
		if(event.entityLiving instanceof EntitySkeleton)
		{
			event.entityLiving.entityDropItem(new ItemStack(ModItems.BoneFragment), drop);
		}
	}
}
