package com.Arteman.HellLand.handler;

import com.Arteman.HellLand.HellLand;
import com.Arteman.HellLand.container.*;
import com.Arteman.HellLand.gui.*;
import com.Arteman.HellLand.tileentity.soulCrystallizerTE;
import com.Arteman.HellLand.tileentity.alchemicalTableTE;
import com.Arteman.HellLand.tileentity.crystalOvenTE;
import com.Arteman.HellLand.tileentity.hellOvenTE;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler {
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity entity = world.getTileEntity(x, y, z);

                if (entity != null) {
                    switch (ID) {
                        case HellLand.guiIDHellOven:
                            if (entity instanceof hellOvenTE) {
                                return new hellOvenContainer(player.inventory, (hellOvenTE) entity);
                            }
                        case HellLand.guiIDSoulCrystallizer:
                            if (entity instanceof soulCrystallizerTE) {
                                return new soulCrystallizerContainer(player.inventory, (soulCrystallizerTE) entity);
                            }
                        case HellLand.guiIDCrystalOven:
                            if (entity instanceof crystalOvenTE) {
                                return new crystalOvenContainer(player.inventory, (crystalOvenTE) entity);
                            }
                        case HellLand.guiIDAlchemicalTable:
                            if(entity instanceof alchemicalTableTE){
                                return new alchemicalTableContainer(player.inventory, (alchemicalTableTE) entity);
                            }
                    }
                }
                switch (ID){
                    case HellLand.guiIDBag:
                        return new ContainerBag(player.inventory, new InventoryBag(player.getHeldItem()));

                }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity entity = world.getTileEntity(x, y, z);

        if (entity != null) {
            switch (ID) {
                case HellLand.guiIDHellOven:
                    if (entity instanceof hellOvenTE) {
                        return new hellOvenGui(player.inventory, (hellOvenTE) entity);
                    }
                case HellLand.guiIDSoulCrystallizer:
                    if (entity instanceof soulCrystallizerTE) {
                        return new soulCrystallizerGui(player.inventory, (soulCrystallizerTE) entity);
                    }
                case HellLand.guiIDCrystalOven:
                    if (entity instanceof crystalOvenTE) {
                        return new crystalOvenGui(player.inventory, (crystalOvenTE) entity);
                    }
                case HellLand.guiIDAlchemicalTable:
                    if(entity instanceof alchemicalTableTE){
                        return new alchemicalTableGui(player.inventory, (alchemicalTableTE) entity);
                    }
            }
        }
        switch (ID) {
            case HellLand.guiIDBag:
                return new GuiBag(player.inventory, new InventoryBag(player.getHeldItem()));
        }
        return null;
    }
}
