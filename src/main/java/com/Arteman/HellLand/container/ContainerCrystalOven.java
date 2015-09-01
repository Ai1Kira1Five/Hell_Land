package com.Arteman.HellLand.container;

import com.Arteman.HellLand.tileentity.crystalOvenTE;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import org.apache.commons.lang3.ArrayUtils;

public class ContainerCrystalOven extends Container {
    private crystalOvenTE crystalOven;

    public int lastBurnTime;
    public int lastCurrentItemBurnTime;
    public int lastCookTime;
    private int[] slots_after = new int[]{0, 1, 2};
    private int[] slots_before = new int[]{3, 4, 5, 6};
    private int[] slots_fuel = new int[]{7, 8, 9, 10};
    /*
     * slot 1 - fuel 0, 1, 2
     * 
     * slot 0 - before 3,4,5,6
     * 
     * slot 2 - after 7,8,9,10
     */

    public ContainerCrystalOven(InventoryPlayer inventory, crystalOvenTE tileentity) {
        this.crystalOven = tileentity;
        this.addSlotToContainer(new Slot((IInventory) tileentity, 0, 62, 64));
        this.addSlotToContainer(new Slot((IInventory) tileentity, 1, 80, 64));
        this.addSlotToContainer(new Slot((IInventory) tileentity, 2, 98, 64));
        this.addSlotToContainer(new Slot((IInventory) tileentity, 3, 26, 27));
        this.addSlotToContainer(new Slot((IInventory) tileentity, 4, 44, 27));
        this.addSlotToContainer(new Slot((IInventory) tileentity, 5, 26, 44));
        this.addSlotToContainer(new Slot((IInventory) tileentity, 6, 44, 44));
        this.addSlotToContainer(new SlotFurnace(inventory.player, (IInventory) tileentity, 7, 116, 27));
        this.addSlotToContainer(new SlotFurnace(inventory.player, (IInventory) tileentity, 8, 134, 27));
        this.addSlotToContainer(new SlotFurnace(inventory.player, (IInventory) tileentity, 9, 116, 44));
        this.addSlotToContainer(new SlotFurnace(inventory.player, (IInventory) tileentity, 10, 134, 44));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                this.addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (int i = 0; i < 9; i++) {
            this.addSlotToContainer(new Slot(inventory, i, 8 + i * 18, 142));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return true;
    }

    public void addCraftingToCrafters(ICrafting icrafting) {
        super.addCraftingToCrafters(icrafting);
        icrafting.sendProgressBarUpdate(this, 0, this.crystalOven.cookTime);
        icrafting.sendProgressBarUpdate(this, 1, this.crystalOven.burnTime);
        icrafting.sendProgressBarUpdate(this, 2, this.crystalOven.currentItemBurnTime);
    }

    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        for (int i = 0; i < this.crafters.size(); i++) {
            ICrafting icrafting = (ICrafting) this.crafters.get(i);

            if (this.lastCookTime != this.crystalOven.cookTime) {
                icrafting.sendProgressBarUpdate(this, 0, this.crystalOven.cookTime);
            }

            if (this.lastBurnTime != this.crystalOven.burnTime) {
                icrafting.sendProgressBarUpdate(this, 1, this.crystalOven.burnTime);
            }

            if (this.lastCurrentItemBurnTime != this.crystalOven.currentItemBurnTime) {
                icrafting.sendProgressBarUpdate(this, 2, this.crystalOven.currentItemBurnTime);
            }
        }
        this.lastCookTime = this.crystalOven.cookTime;
        this.lastBurnTime = this.crystalOven.burnTime;
        this.lastCurrentItemBurnTime = this.crystalOven.currentItemBurnTime;
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int par1, int par2) {
        if (par1 == 0) {
            this.crystalOven.cookTime = par2;
        }

        if (par1 == 1) {
            this.crystalOven.burnTime = par2;
        }

        if (par1 == 2) {
            this.crystalOven.currentItemBurnTime = par2;
        }
    }

    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
        ItemStack itemstack = null;
        Slot slot = (Slot) this.inventorySlots.get(par2);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (ArrayUtils.contains(slots_after, par2)) {
                if (!this.mergeItemStack(itemstack1, crystalOven.getSizeInventory(), crystalOven.getSizeInventory() + 36, true)) {
                    return null;
                }
                slot.onSlotChange(itemstack1, itemstack);
            } else if (!ArrayUtils.contains(slots_before, par2) && !ArrayUtils.contains(slots_fuel, par2)) {
                if (FurnaceRecipes.smelting().getSmeltingResult(itemstack1) != null) {
                    if (!this.mergeItemStack(itemstack1, 3, 7, false)) {
                        return null;
                    }
                    slot.onSlotChanged();
                } else if (crystalOven.isItemFuel(itemstack1)) {
                    if (!this.mergeItemStack(itemstack1, 0, 3, false)) {
                        return null;
                    }
                    slot.onSlotChanged();
                } else if (par2 >= crystalOven.getSizeInventory() && par2 < (crystalOven.getSizeInventory() + 27)) {
                    if (!this.mergeItemStack(itemstack1, crystalOven.getSizeInventory() + 27, crystalOven.getSizeInventory() + 36, false)) {
                        return null;
                    }
                    slot.onSlotChanged();
                } else if (par2 >= (crystalOven.getSizeInventory() + 27) && par2 < (crystalOven.getSizeInventory() + 36)) {
                    if (!this.mergeItemStack(itemstack1, crystalOven.getSizeInventory(), crystalOven.getSizeInventory() + 27, false)) {
                        return null;
                    }
                    slot.onSlotChanged();
                }
            } else if (!this.mergeItemStack(itemstack1, crystalOven.getSizeInventory(), crystalOven.getSizeInventory() + 36, false)) {
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
}
