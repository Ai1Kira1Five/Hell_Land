package com.Arteman.HellLand;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityEvent;

public class HellCommonEventHandler {
    @SubscribeEvent
    public void onEntityConstructed(EntityEvent.EntityConstructing event) {
        if (event.entity instanceof EntityPlayer) {
            PlayerHellData.initPlayerWeaponData((EntityPlayer) event.entity);
        }
    }
}
