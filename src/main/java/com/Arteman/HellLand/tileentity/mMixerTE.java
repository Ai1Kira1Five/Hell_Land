package com.Arteman.HellLand.tileentity;

import com.Arteman.HellLand.recipes.mMixerRecipes;
import org.apache.commons.lang3.ArrayUtils;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class mMixerTE extends tileEntityWithInventory{
	
	private static final int[] slots_final = new int[]{3};
	private static final int[] slots_ingredients = new int[]{0, 1, 2};
	private static final int[] slotsInput_Chest = new int[]{4, 5, 6, 7, 8, 9};
	private static final int[] slotsOutput_Chest = new int[]{10, 11, 12, 13, 14, 15};

    public int mixSpeed = 300;
    public int mixTime;
    public int currentItemMixTime;
    
    public mMixerTE(){
    	super(16);
    }
    
    public boolean isItemValidForSlot(int slot, ItemStack itemstack) {
        return ArrayUtils.contains(slotsInput_Chest,slot);
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int side) {
        return side == 0||side==1 ? slotsOutput_Chest : slotsInput_Chest;
    }
    @Override
    public boolean canInsertItem(int slot, ItemStack itemStack, int side) {
        return false;
    }
    @Override
    public boolean canExtractItem(int slot, ItemStack itemStack, int side) {
        return false;
    }


    @Override
    public void updateEntity() {

        super.updateEntity();
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
    @Override
    public boolean isActive() {
        return false;
    }

    private int getFirstRecipeItemSlot(){

        for(int i:slotsInput_Chest){
            ItemStack tmp = getStackInSlot(i);
            if(tmp!=null&&mMixerRecipes.mixing().getResult(tmp)!=null){
                return i;
            }
        }
        return -1;

    }

}
