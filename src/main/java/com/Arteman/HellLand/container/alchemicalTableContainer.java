package com.Arteman.HellLand.container;

import com.Arteman.HellLand.tileentity.alchemicalTableTE;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import org.apache.commons.lang3.ArrayUtils;

public class alchemicalTableContainer extends Container {
    public alchemicalTableTE tileEntity;
    public int lastCookTime = 0;

    public alchemicalTableContainer(InventoryPlayer inventory, alchemicalTableTE entity) {
        this.tileEntity = entity;

        this.addSlotToContainer(new Slot((IInventory)tileEntity, 0, 80, 10));
        this.addSlotToContainer(new Slot((IInventory)tileEntity, 1, 104, 34));
        this.addSlotToContainer(new Slot((IInventory)tileEntity, 2, 80, 59));
        this.addSlotToContainer(new Slot((IInventory)tileEntity, 3, 56, 34));
        this.addSlotToContainer(new SlotFurnace(inventory.player, (IInventory)tileEntity, 4, 80, 34));

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
        icrafting.sendProgressBarUpdate(this, 0, this.tileEntity.cookTime);
    }

    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        for (Object crafter : this.crafters) {
            ICrafting icrafting = (ICrafting) crafter;
            if (this.lastCookTime != this.tileEntity.cookTime) {
                icrafting.sendProgressBarUpdate(this, 0, this.tileEntity.cookTime);
            }
        }

        this.lastCookTime = this.tileEntity.cookTime;
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int par1, int par2) {
        if (par1 == 0) {
            this.tileEntity.cookTime = par2;
        }
    }

    public ItemStack transferStackInSlot(EntityPlayer player, int parSlot) {
        ItemStack itemstack = null;
        Slot slot = (Slot) this.inventorySlots.get(parSlot);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (ArrayUtils.contains(this.tileEntity.resultSlots, parSlot)) {
                if (!this.mergeItemStack(itemstack1, this.tileEntity.getSizeInventory(), this.tileEntity.getSizeInventory()+36, true)) {
                    return null;
                }
                slot.onSlotChange(itemstack1, itemstack);
            } else if (!ArrayUtils.contains(this.tileEntity.inputSlots, parSlot)) {
                if (!EnchantmentHelper.getEnchantments(itemstack1).isEmpty()) {
                    if (!this.mergeItemStack(itemstack1, 0, this.tileEntity.getSizeInventory()-1, false)) {
                        return null;
                    }
                    slot.onSlotChanged();
                } else if (parSlot >= this.tileEntity.getSizeInventory() && parSlot < this.tileEntity.getSizeInventory()+27) {
                    if (!this.mergeItemStack(itemstack1, this.tileEntity.getSizeInventory()+27, this.tileEntity.getSizeInventory()+36, false)) {
                        return null;
                    }
                    slot.onSlotChanged();
                } else if (parSlot >= this.tileEntity.getSizeInventory()+27 && parSlot < this.tileEntity.getSizeInventory()+36 && !this.mergeItemStack(itemstack1, this.tileEntity.getSizeInventory(), this.tileEntity.getSizeInventory()+27, false)) {
                    return null;
                }
                slot.onSlotChanged();
            } else if (!this.mergeItemStack(itemstack1, this.tileEntity.getSizeInventory(), this.tileEntity.getSizeInventory()+36, false)) {
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

            slot.onPickupFromSlot(player, itemstack1);
        }
        return itemstack;
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return true;
    }

    private class outputSlot extends Slot{

        public outputSlot(IInventory inventory, int index, int x, int y) {
            super(inventory, index, x, y);
        }
        @Override
        public boolean isItemValid(ItemStack itemStack) {
            return false;
        }
    }
}
