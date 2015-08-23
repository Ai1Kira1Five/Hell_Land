package com.Arteman.HellLand.handler;

import com.Arteman.HellLand.HellLand;
import com.Arteman.HellLand.container.*;
import com.Arteman.HellLand.gui.GuiBag;
import com.Arteman.HellLand.gui.GuiCrystalOven;
import com.Arteman.HellLand.gui.GuiHellOven;
import com.Arteman.HellLand.gui.GuiSoulCrystallizer;
import com.Arteman.HellLand.tileentity.TileEntityCrystalOven;
import com.Arteman.HellLand.tileentity.TileEntityHellOven;
import com.Arteman.HellLand.tileentity.TileEntitySoulCrystallizer;
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
                            if (entity instanceof TileEntityHellOven) {
                                return new ContainerHellOven(player.inventory, (TileEntityHellOven) entity);
                            }
                        case HellLand.guiIDSoulCrystallizer:
                            if (entity instanceof TileEntitySoulCrystallizer) {
                                return new ContainerSoulCrystallizer(player.inventory, (TileEntitySoulCrystallizer) entity);
                            }
                        case HellLand.guiIDCrystalOven:
                            if (entity instanceof TileEntityCrystalOven) {
                                return new ContainerCrystalOven(player.inventory, (TileEntityCrystalOven) entity);
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
                    if (entity instanceof TileEntityHellOven) {
                        return new GuiHellOven(player.inventory, (TileEntityHellOven) entity);
                    }
                case HellLand.guiIDSoulCrystallizer:
                    if (entity instanceof TileEntitySoulCrystallizer) {
                        return new GuiSoulCrystallizer(player.inventory, (TileEntitySoulCrystallizer) entity);
                    }
                case HellLand.guiIDCrystalOven:
                    if (entity instanceof TileEntityCrystalOven) {
                        return new GuiCrystalOven(player.inventory, (TileEntityCrystalOven) entity);
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
