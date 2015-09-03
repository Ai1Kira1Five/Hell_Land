package com.Arteman.HellLand.tileentity;

import com.Arteman.HellLand.ModItems;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Map;

public class alchemicalTableTE extends tileEntityWithInventory{

   public int[] resultSlots = new int[]{4};
    public int[] inputSlots = new int[]{0,1,2,3};
    public int processingTime = 150;
    public int cookTime = 0;

    public alchemicalTableTE() {
        super(5);
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int side) {
        return (side==1||side==0)?resultSlots:inputSlots;
    }

    @Override
    public boolean canInsertItem(int slot, ItemStack itemStack, int side) {
        return ArrayUtils.contains(getAccessibleSlotsFromSide(side), slot) && isItemValidForSlot(slot, itemStack);
    }

    @Override
    public boolean canExtractItem(int slot, ItemStack itemStack, int side) {
        return ArrayUtils.contains(getAccessibleSlotsFromSide(side),slot);
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemStack) {
        return (!EnchantmentHelper.getEnchantments(itemStack).isEmpty() && ArrayUtils.contains(inputSlots, slot));
    }

    @Override
    public void updateEntity(){
        if (!this.worldObj.isRemote) {
            if (this.canProcess()) {
                this.cookTime++;
                this.setProcessingTime();
                if (this.cookTime >= this.processingTime) {
                    this.cookTime = 0;
                    this.processing();
                }
            } else {
                this.cookTime = 0;
            }
        }
        super.updateEntity();
    }
    
    public void setProcessingTime(){
        int slot = this.getFirstInputSlot();
        if (slot!=-1){
            this.processingTime = 150 * countCurrentProcessingLVL(getStackInSlot(slot));
        }else{
            this.processingTime = 150;
        }
    }
    
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);

        this.cookTime = (int) nbt.getShort("CookTime");
    }

    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);

        nbt.setShort("CookTime", (short) this.cookTime);
    }
    public boolean isProcessing() {
        return this.cookTime!=0;
    }

    public boolean canProcess(){
        return ((getFirstInputSlot() != -1 && (getStackInSlot(4) == null || getStackInSlot(4).stackSize + countCurrentProcessingLVL(getStackInSlot(getFirstInputSlot()))<=getInventoryStackLimit())));
    }

    private int getFirstInputSlot() {
        for(int i=0;i<4;i++){
            ItemStack itemStack = getStackInSlot(i);
            if(itemStack!=null && (getStackInSlot(4) == null || getStackInSlot(4).stackSize + countCurrentProcessingLVL(getStackInSlot(i))<=getInventoryStackLimit())){
                return i;
            }
        }
        return -1;
    }

    public void processing(){
        int slot = getFirstInputSlot();
        if(slot!=-1){
            int outputItemStackSize=countCurrentProcessingLVL(getStackInSlot(slot));
            ItemStack outputStack = getStackInSlot(4);
            if(outputStack == null){
                outputStack = new ItemStack(ModItems.recidiumDust, outputItemStackSize);
            }else{
                outputStack.stackSize = MathHelper.clamp_int(outputStack.stackSize+outputItemStackSize,outputStack.stackSize+outputItemStackSize,getInventoryStackLimit());
            }
            this.setInventorySlotContents(4,outputStack);
            this.setInventorySlotContents(slot, null);
        }
    }

    public int getCookProgressScaled(int i) {
        this.setProcessingTime();
        return this.cookTime * i / this.processingTime;
    }

    public int countCurrentProcessingLVL(ItemStack itemStack){
        Map enchants = EnchantmentHelper.getEnchantments(itemStack);
        int outputItemStackSize=0;
        for(Object i:enchants.values()){
            outputItemStackSize = outputItemStackSize + (Integer)i;
        }
        return outputItemStackSize;
    }
}
