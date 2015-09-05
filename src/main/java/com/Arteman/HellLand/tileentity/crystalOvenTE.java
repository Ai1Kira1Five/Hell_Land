package com.Arteman.HellLand.tileentity;

import com.Arteman.HellLand.blocks.machines.crystalOven;
import com.Arteman.HellLand.utils.interfaces.IFacing;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityFurnace;
import org.apache.commons.lang3.ArrayUtils;

public class crystalOvenTE extends tileEntityWithInventory implements IFacing{

    private static final int[] slots_top = new int[]{3, 4, 5, 6};
    private static final int[] slots_bottom = new int[]{0, 1, 2};
    private static final int[] slots_side = new int[]{7, 8, 9, 10};

    public int furnaceSpeed = 150;
    public int burnTime;
    public int currentItemBurnTime;
    public int cookTime;

    public crystalOvenTE() {
        super(11);
    }

    public boolean isItemValidForSlot(int i, ItemStack itemstack) {
        if (ArrayUtils.contains(slots_side, i)) {
            return false;
        } else if (ArrayUtils.contains(slots_bottom, i)) {
            return TileEntityFurnace.isItemFuel(itemstack);
        } else if (FurnaceRecipes.smelting().getSmeltingResult(itemstack) != null) {
            return true;
        } else {
            return false;
        }
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
                int i = getFirstFuelSlot();
                if (i >= 0) {
                    this.currentItemBurnTime = this.burnTime = TileEntityFurnace.getItemBurnTime(this.inventory[i]);

                    if (this.isBurning()) {
                        flag1 = true;

                        if (this.inventory[i] != null) {
                            this.inventory[i].stackSize--;

                            if (this.inventory[i].stackSize == 0) {
                                this.inventory[i] = this.inventory[i].getItem().getContainerItem(this.inventory[i]);
                            }
                        }
                    }
                }
            }
            if (this.isBurning() && this.canSmelt()) {
                this.cookTime++;

                if (this.cookTime >= this.furnaceSpeed) {
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

        int i = getFirstSmeltSlot();
        if (i < 0) return false;
        ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.inventory[i]);
        if (itemstack == null) return false;
        return canSmeltToSlot(itemstack, 7) || canSmeltToSlot(itemstack, 8) || canSmeltToSlot(itemstack, 9) || canSmeltToSlot(itemstack, 10);
    }

    public boolean canSmeltToSlot(ItemStack itemstack, int slot) {
        if (this.inventory[slot] == null) return true;
        if (!this.inventory[slot].isItemEqual(itemstack)) return false;
        int result = this.inventory[slot].stackSize + itemstack.stackSize;
        return (result <= getInventoryStackLimit() && result <= itemstack.getMaxStackSize());
    }

    public void smeltItem() {
        if (this.canSmelt()) {
            for(int i=3;i<7;i++) {
                if(this.inventory[i]==null) continue;
                ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.getStackInSlot(i));
                int s = getFirstResultSlot(itemstack);
                if(s<0)continue;
                if (this.inventory[s] == null) {
                    this.inventory[s] = itemstack.copy();
                } else if (this.inventory[s].isItemEqual(itemstack)) {
                    this.inventory[s].stackSize += itemstack.stackSize;
                }
                this.inventory[i].stackSize--;
                if (this.inventory[i].stackSize <= 0) {
                    this.inventory[i] = null;
                }
            }
        }
    }

    public int[] getAccessibleSlotsFromSide(int var1) {
        return var1 == 0 ? slots_bottom : (var1 == 1 ? slots_top : slots_side);
    }

    public boolean canInsertItem(int i, ItemStack itemstack, int j) {
        return this.isItemValidForSlot(i, itemstack);
    }

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
    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);

        this.burnTime = (int) nbt.getShort("BurnTime");
        this.cookTime = (int) nbt.getShort("CookTime");
        this.currentItemBurnTime = (int) nbt.getShort("CurrentBurnTime");
    }
    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);

        nbt.setShort("BurnTime", (short) this.burnTime);
        nbt.setShort("CookTime", (short) this.cookTime);
        nbt.setShort("CurrentBurnTime", (short) this.currentItemBurnTime);
    }

    private int getFirstFuelSlot() {
        for (int i = 0; i < 3; i++) {
            if (TileEntityFurnace.isItemFuel(this.inventory[i])) {
                return i;
            }
        }
        return -1;
    }

    private int getFirstSmeltSlot() {
        for (int i = 3; i < 7; i++) {
            if (this.inventory[i] != null && FurnaceRecipes.smelting().getSmeltingResult(this.inventory[i]) != null) {
                return i;
            }
        }
        return -1;
    }

    private int getFirstResultSlot(ItemStack itemStack) {
        for (int i = 7; i < 11; i++) {
            if (this.inventory[i] == null || (itemStack.isItemEqual(this.inventory[i]) && (this.inventory[i].stackSize + itemStack.stackSize) <= getInventoryStackLimit())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean isActive() {
        return isBurning();
    }
}
