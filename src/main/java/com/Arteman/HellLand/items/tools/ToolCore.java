package com.Arteman.HellLand.items.tools;

import java.util.Random;

import com.Arteman.HellLand.HellLand;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ToolCore extends Item
{
	protected Random random = new Random();
	protected int damageForEntity;
	
	public ToolCore(int baseDamage)
	{
		super();
		this.maxStackSize = 1;
		this.setMaxDamage(200);
		this.setCreativeTab(HellLand.HellMCTab);
	}
	
	public void onEntityDamaged(World world, EntityLivingBase player, Entity entity)
	{
		
	}
	
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
    {
        return AbilityHelper.onLeftClickEntity(stack, player, entity, this, 0);
    }
}
