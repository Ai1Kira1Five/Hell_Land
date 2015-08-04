package com.Arteman.HellLand.container;

import com.Arteman.HellLand.tileentity.TileEntitySoulCrystallizer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

public class ContainerSoulCrystallizer extends Container {
    private TileEntitySoulCrystallizer soulcrystallizer;

    public int lastBurnTime;
    public int lastCurrentItemBurnTime;
    public int lastCookTime;

    public ContainerSoulCrystallizer(InventoryPlayer inventory, TileEntitySoulCrystallizer tileentity) {
        this.soulcrystallizer = tileentity;

        this.addSlotToContainer(new Slot((IInventory) tileentity, 0, 56, 35));
        this.addSlotToContainer(new Slot((IInventory) tileentity, 1, 8, 62));
        this.addSlotToContainer(new SlotFurnace(inventory.player, (IInventory) tileentity, 2, 116, 35));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                this.addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (int i = 0; i < 9; i++) {
            this.addSlotToContainer(new Slot(inventory, i, 8 + i * 18, 142));
        }
    }

    public void addCraftingToCrafters(ICrafting icrafting) {
        super.addCraftingToCrafters(icrafting);
        icrafting.sendProgressBarUpdate(this, 0, this.soulcrystallizer.cookTime);
        icrafting.sendProgressBarUpdate(this, 1, this.soulcrystallizer.burnTime);
        icrafting.sendProgressBarUpdate(this, 2, this.soulcrystallizer.currentItemBurnTime);
    }

    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        for (int i = 0; i < this.crafters.size(); i++) {
            ICrafting icrafting = (ICrafting) this.crafters.get(i);

            if (this.lastCookTime != this.soulcrystallizer.cookTime) {
                icrafting.sendProgressBarUpdate(this, 0, this.soulcrystallizer.cookTime);
            }

            if (this.lastBurnTime != this.soulcrystallizer.burnTime) {
                icrafting.sendProgressBarUpdate(this, 1, this.soulcrystallizer.burnTime);
            }

            if (this.lastCurrentItemBurnTime != this.soulcrystallizer.currentItemBurnTime) {
                icrafting.sendProgressBarUpdate(this, 2, this.soulcrystallizer.currentItemBurnTime);
            }
        }

        this.lastCookTime = this.soulcrystallizer.cookTime;
        this.lastBurnTime = this.soulcrystallizer.burnTime;
        this.lastCurrentItemBurnTime = this.soulcrystallizer.currentItemBurnTime;
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int par1, int par2) {
        if (par1 == 0) {
            this.soulcrystallizer.cookTime = par2;
        }

        if (par1 == 1) {
            this.soulcrystallizer.burnTime = par2;
        }

        if (par1 == 2) {
            this.soulcrystallizer.currentItemBurnTime = par2;
        }
    }

    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
        ItemStack itemstack = null;
        Slot slot = (Slot) this.inventorySlots.get(par2);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (par2 == 2) {
                if (!this.mergeItemStack(itemstack1, 3, 39, true)) {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            } else if (par2 != 1 && par2 != 0) {
                if (FurnaceRecipes.smelting().getSmeltingResult(itemstack1) != null) {
                    if (!this.mergeItemStack(itemstack1, 0, 1, false)) {
                        return null;
                    }
                } else if (TileEntitySoulCrystallizer.isItemFuel(itemstack1)) {
                    if (!this.mergeItemStack(itemstack1, 1, 2, false)) {
                        return null;
                    }
                } else if (par2 >= 3 && par2 < 30) {
                    if (!this.mergeItemStack(itemstack1, 30, 39, false)) {
                        return null;
                    }
                } else if (par2 >= 30 && par2 < 39 && !this.mergeItemStack(itemstack1, 3, 30, false)) {
                    return null;
                }
            } else if (!this.mergeItemStack(itemstack1, 3, 39, false)) {
                return null;
            }

            if (itemstack1.stackSize == 0) {
                slot.putStack((ItemStack) null);
            } else {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize == itemstack.stackSize) {
                return null;
            }

            slot.onPickupFromSlot(par1EntityPlayer, itemstack1);
        }
        return itemstack;
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityplayer) {
        return true;
    }
}
