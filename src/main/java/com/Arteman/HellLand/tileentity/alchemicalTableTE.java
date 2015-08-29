package com.Arteman.HellLand.tileentity;

import com.Arteman.HellLand.ModItems;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Map;

public class alchemicalTableTE extends TileEntity implements ISidedInventory {

    public ItemStack[] inventory = new ItemStack[5];
    private int[] resultSlots = new int[]{4};
    private int[] inputSlots = new int[]{0,1,2,3};
    public String localizedName;
    public int processingTime = 150;
    public static int cookTime;

    @Override
    public int[] getAccessibleSlotsFromSide(int side) {
        return (side==1||side==0)?resultSlots:inputSlots;
    }

    @Override
    public boolean canInsertItem(int slot, ItemStack itemStack, int side) {
        return ArrayUtils.contains(getAccessibleSlotsFromSide(side),slot)?isItemValidForSlot(slot,itemStack):false;
    }

    @Override
    public boolean canExtractItem(int slot, ItemStack itemStack, int side) {
        return ArrayUtils.contains(getAccessibleSlotsFromSide(side),slot);
    }

    @Override
    public int getSizeInventory() {
        return this.inventory.length;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return this.inventory[slot];
    }

    @Override
    public ItemStack decrStackSize(int slot, int amount) {
        if (this.inventory[slot] != null) {
            ItemStack itemstack;
            if (this.inventory[slot].stackSize <= amount) {
                itemstack = this.inventory[slot];
                this.inventory[slot] = null;
                this.markDirty();
                return itemstack;
            } else {
                itemstack = this.inventory[slot].splitStack(amount);
                if (this.inventory[slot].stackSize == 0) {
                    this.inventory[slot] = null;
                }
                this.markDirty();
                return itemstack;
            }
        }
        return null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {
        if (this.inventory[slot] != null) {
            ItemStack itemstack = this.inventory[slot];
            this.inventory[slot] = null;
            return itemstack;
        }
        return null;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack itemStack) {
        this.inventory[slot] = itemStack;
        if (itemStack != null && itemStack.stackSize > this.getInventoryStackLimit()) {
            itemStack.stackSize = this.getInventoryStackLimit();
        }
    }

    public void setGuiDisplayName(String displayName) {
        this.localizedName = displayName;
    }

    public String getInventoryName() {
        return this.hasCustomInventoryName() ? this.localizedName : "container.ContainerCrystalOven";
    }

    public boolean hasCustomInventoryName() {
        return this.localizedName != null && this.localizedName.length() > 0;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : player.getDistanceSq((double) this.xCoord + 0.5D, (double) this.yCoord + 0.5D, (double) this.zCoord + 0.5D) <= 64.0D;
    }

    @Override
    public void openInventory(){}

    @Override
    public void closeInventory(){}

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemStack) {
        return (!EnchantmentHelper.getEnchantments(itemStack).isEmpty() && ArrayUtils.contains(inputSlots,slot))?true:false;
    }

    @Override
    public void updateEntity() {
        if(canProcess()){
            boolean flag = this.cookTime > 0;
            boolean flag1 = false;

            if (!this.worldObj.isRemote) {
                if (this.canProcess()) {
                    this.cookTime++;

                    if (this.cookTime >= this.processingTime) {
                        this.cookTime = 0;
                        this.processing();
                        flag1 = true;
                    }
                } else {
                    this.cookTime = 0;
                }

                if (flag != this.isProcessing()) {
                    flag1 = true;
                }
            }
            if (flag1) {
                this.markDirty();
            }
        }
        super.updateEntity();
    }

    public boolean isProcessing() {
        return this.cookTime!=0;
    }

    public boolean canProcess(){
        return((getFirstInputSlot()!=-1 && (getStackInSlot(4)==null||getStackInSlot(4).stackSize<getInventoryStackLimit())))?true:false;
    }

    private int getFirstInputSlot() {
        for(int i=0;i<4;i++){
            ItemStack itemStack = getStackInSlot(i);
            if(itemStack!=null && !EnchantmentHelper.getEnchantments(itemStack).isEmpty()){
                return i;
            }
        }
        return -1;
    }

    public void processing(){
        int slot = getFirstInputSlot();
        if(slot!=1){
            ItemStack itemStack = getStackInSlot(slot);
            Map<Integer,Integer> enchants = EnchantmentHelper.getEnchantments(itemStack);
            int outputItemStackSize=0;
            for(int i:enchants.values()){
                outputItemStackSize = outputItemStackSize + i;
            }
            ItemStack outputStack = getStackInSlot(4);
            if(outputStack == null){
                outputStack = new ItemStack(ModItems.recidiumDust, outputItemStackSize);
            }else{
                outputStack.stackSize = MathHelper.clamp_int(outputStack.stackSize+outputItemStackSize,outputStack.stackSize+outputItemStackSize,getInventoryStackLimit());
            }
            this.setInventorySlotContents(4,outputStack);
        }
    }
}
