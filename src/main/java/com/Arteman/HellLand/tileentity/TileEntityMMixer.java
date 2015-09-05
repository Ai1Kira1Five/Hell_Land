package com.Arteman.HellLand.tileentity;

import org.apache.commons.lang3.ArrayUtils;

import com.Arteman.HellLand.recipes.MixerRecipes;
import com.Arteman.HellLand.utils.interfaces.IFacing;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityMMixer extends tileEntityWithInventory implements IFacing{
	
    private static final int[] slots_final = new int[]{3};
    private static final int[] slots_ingredients = new int[]{0, 1, 2};
    private static final int[] slots_chest = new int[]{4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};

    public int mixSpeed = 300;
    public int mixTime;
    public int currentItemMixTime;
    
    public TileEntityMMixer(){
    	super(16);
    }
    
    public boolean isItemValidForSlot(int i, ItemStack itemstack) {
        if (ArrayUtils.contains(slots_final, i)) {
            return false;
        } else if (ArrayUtils.contains(slots_ingredients, i)) {
            return TileEntityMMixer.isItemIngredient(itemstack);
        } else if (MixerRecipes.mixering().getMixerResult(itemstack) != null) {
            return true;
        } else if (ArrayUtils.contains(slots_chest, i)){
            return TileEntityMMixer.isItemToChest(itemstack);
        }
        else{
        	return false;
        }
    }
    
    private static boolean isItemToChest(ItemStack itemstack) {
		return false;
	}

	private static boolean isItemIngredient(ItemStack itemstack) {
		return false;
	}

	public boolean isMix(){
    	return this.mixTime > 0;
    }
    
    @Override
    public void updateEntity() {
        boolean flag = this.mixTime > 0;
        boolean flag1 = false;

        if (this.isMix()) {
            this.mixTime--;
        }
        if (!this.worldObj.isRemote) {
            if (this.mixTime == 0 && this.canMix()) {
                int i = getIngredientSlot();
                if (i >= 0) {
                    this.currentItemMixTime = this.mixTime = TileEntityMMixer.getItemMixTime(this.inventory[i]);

                    if (this.isMix()) {
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
            if (flag != this.isMix()) {
                flag1 = true;
            }
        }
        if (flag1) {
            this.markDirty();
        }
        super.updateEntity();
    }
    
    private static int getItemMixTime(ItemStack itemStack) {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean canMix() {

        int i = getInfusionSlot();
        if (i < 0) return false;
        ItemStack itemstack = MixerRecipes.mixering().getMixerResult(this.inventory[i]);
        if (itemstack == null) return false;
        return canMixToSlot(itemstack, 7) || canMixToSlot(itemstack, 8) || canMixToSlot(itemstack, 9) || canMixToSlot(itemstack, 10);
    }

    public boolean canMixToSlot(ItemStack itemstack, int slot) {
        if (this.inventory[slot] == null) return true;
        if (!this.inventory[slot].isItemEqual(itemstack)) return false;
        int result = this.inventory[slot].stackSize + itemstack.stackSize;
        return (result <= getInventoryStackLimit() && result <= itemstack.getMaxStackSize());
    }

    public void mixItem() {
        if (this.canMix()) {
            for(int i=3;i<7;i++) {
                if(this.inventory[i]==null) continue;
                ItemStack itemstack = MixerRecipes.mixering().getMixerResult(this.getStackInSlot(i));
                int s = getResultSlot(itemstack);
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
        return var1 == 0 ? slots_ingredients : (var1 == 1 ? slots_final : slots_chest);
    }

    public boolean canInsertItem(int i, ItemStack itemstack, int j) {
        return false;
    }

    public boolean canExtractItem(int i, ItemStack itemstack, int j) {
        return false;
    }

    public int getBurnTimeRemainingScaled(int i) {
        if (this.currentItemMixTime == 0) {
            this.currentItemMixTime = this.mixSpeed;
        }
        return this.mixTime * i / this.currentItemMixTime;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);

        this.mixTime = (int) nbt.getShort("MixTime");
        this.currentItemMixTime = (int) nbt.getShort("CurrentMixTime");
    }
    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);

        nbt.setShort("BurnTime", (short) this.mixTime);
        nbt.setShort("CurrentBurnTime", (short) this.currentItemMixTime);
    }

    private int getInfusionSlot() {
        for (int i = 0; i < 3; i++) {
            if (TileEntityMMixer.isInfusionItem(this.inventory[i])) {
                return i;
            }
        }
        return -1;
    }

    private static boolean isInfusionItem(ItemStack itemStack) {
		// TODO Auto-generated method stub
		return false;
	}

	private int getIngredientSlot() {
        for (int i = 3; i < 7; i++) {
            if (this.inventory[i] != null && MixerRecipes.mixering().getMixerResult(this.inventory[i]) != null) {
                return i;
            }
        }
        return -1;
    }

    private int getResultSlot(ItemStack itemStack) {
        for (int i = 7; i < 11; i++) {
            if (this.inventory[i] == null || (itemStack.isItemEqual(this.inventory[i]) && (this.inventory[i].stackSize + itemStack.stackSize) <= getInventoryStackLimit())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean isActive() {
        return isMix();
    }
}
