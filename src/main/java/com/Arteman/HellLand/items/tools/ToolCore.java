package com.Arteman.HellLand.items.tools;

import com.Arteman.HellLand.HellLandCore;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.Random;

public class ToolCore extends Item {
    protected Random random = new Random();
    protected int damageForEntity;

    public ToolCore(int baseDamage) {
        super();
        this.maxStackSize = 1;
        this.setMaxDamage(200);
        this.setCreativeTab(HellLandCore.HellMCTab);
    }

    public void onEntityDamaged(World world, EntityLivingBase player, Entity entity) {

    }

    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
        return AbilityHelper.onLeftClickEntity(stack, player, entity, this, 0);
    }
}
