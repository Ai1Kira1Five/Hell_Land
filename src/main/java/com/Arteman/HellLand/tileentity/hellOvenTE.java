package com.Arteman.HellLand.tileentity;

import com.Arteman.HellLand.blocks.machines.hellOven;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityFurnace;


public class hellOvenTE extends tileEntityWithInventory {

    private static final int[] inventory_top = new int[]{0};
    private static final int[] inventory_bottom = new int[]{2, 1};
    private static final int[] inventory_side = new int[]{1};

    public int furnaceSpeed = 300;
    public int burnTime;
    public int currentItemBurnTime;
    public int cookTime;

    public hellOvenTE() {
        super(3);
    }

    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemstack) {
        return i != 2 && (i != 1 || isItemFuel(itemstack));
    }

    public static boolean isItemFuel(ItemStack itemstack) {
        return TileEntityFurnace.getItemBurnTime(itemstack) > 0;
    }


    public boolean isBurning() {
        return this.burnTime > 0;
    }

    @Override
    public void updateEntity() {
        boolean flag = this.burnTime > 0;
        boolean flag1 = false;

        if (this.isBurning()) {
            this.burnTime--;
        }
        if (!this.worldObj.isRemote) {
            if (this.burnTime == 0 && this.canSmelt()) {
                this.currentItemBurnTime = this.burnTime = TileEntityFurnace.getItemBurnTime(this.inventory[1]);

                if (this.isBurning()) {
                    flag1 = true;

                    if (this.inventory[1] != null) {
                        this.inventory[1].stackSize--;

                        if (this.inventory[1].stackSize == 0) {
                            this.inventory[1] = this.inventory[1].getItem().getContainerItem(this.inventory[1]);
                        }
                    }
                }
            }
            if (this.isBurning() && this.canSmelt()) {
                this.cookTime++;

                if (this.cookTime == this.furnaceSpeed) {
                    this.cookTime = 0;
                    this.smeltItem();
                    flag1 = true;
                }
            } else {
                this.cookTime = 0;
            }

            if (flag != this.isBurning()) {
                flag1 = true;
            }
        }
        if (flag1) {
            this.markDirty();
        }
        super.updateEntity();
    }

    public boolean canSmelt() {
        if (this.inventory[0] == null) {
            return false;
        } else {
            ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.inventory[0]);

            if (itemstack == null) return false;
            if (this.inventory[2] == null) return true;
            if (!this.inventory[2].isItemEqual(itemstack)) return false;

            int result = this.inventory[2].stackSize + itemstack.stackSize;

            return (result <= getInventoryStackLimit() && result <= itemstack.getMaxStackSize());
        }
    }

    public void smeltItem() {
        if (this.canSmelt()) {
            ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.inventory[0]);

            if (this.inventory[2] == null) {
                this.inventory[2] = itemstack.copy();
            } else if (this.inventory[2].isItemEqual(itemstack)) {
                this.inventory[2].stackSize += itemstack.stackSize;
            }

            this.inventory[0].stackSize--;

            if (this.inventory[0].stackSize <= 0) {
                this.inventory[0] = null;
            }
        }
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int var1) {
        return var1 == 0 ? inventory_bottom : (var1 == 1 ? inventory_top : inventory_side);
    }

    @Override
    public boolean canInsertItem(int i, ItemStack itemstack, int j) {
        return this.isItemValidForSlot(i, itemstack);
    }

    @Override
    public boolean canExtractItem(int i, ItemStack itemstack, int j) {
        return j != 0 || i != 1 || itemstack.getItem() == Items.bucket;
    }

    public int getBurnTimeRemainingScaled(int i) {
        if (this.currentItemBurnTime == 0) {
            this.currentItemBurnTime = this.furnaceSpeed;
        }
        return this.burnTime * i / this.currentItemBurnTime;
    }

    public int getCookProgressScaled(int i) {
        return this.cookTime * i / this.furnaceSpeed;
    }

    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);

        this.burnTime = (int) nbt.getShort("BurnTime");
        this.cookTime = (int) nbt.getShort("CookTime");
        this.currentItemBurnTime = (int) nbt.getShort("CurrentBurnTime");
    }

    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);

        nbt.setShort("BurnTime", (short) this.burnTime);
        nbt.setShort("CookTime", (short) this.cookTime);
        nbt.setShort("CurrentBurnTime", (short) this.currentItemBurnTime);
    }

    @Override
    public boolean isActive() {
        return isBurning();
    }
}
