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

public class alchemicalTableTE extends TileEntity implements ISidedInventory {

    public ItemStack[] inventory = new ItemStack[5];
    public int[] resultSlots = new int[]{4};
    public int[] inputSlots = new int[]{0,1,2,3};
    public String localizedName;
    public int processingTime = 150;
    public int cookTime = 0;

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
        return this.hasCustomInventoryName() ? this.localizedName : "container.alchemicalTable";
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
    public void updateEntity(){
        boolean flag = this.cookTime > 0;

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
    
    public static void setProcessingTime(){
        int slot = getFirstInputSlot();
        if (i!=-1){
            this.processingTime = 150 * countCurrentProcessingLVL(getStackInSlot(i));
        }else{
            this.processingTime = 150;
        }
    }
    
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);

        NBTTagList list = nbt.getTagList("Items", 10);
        this.inventory = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < list.tagCount(); i++) {
            NBTTagCompound compound = (NBTTagCompound) list.getCompoundTagAt(i);
            byte b = compound.getByte("Slot");

            if (b >= 0 && b < this.inventory.length) {
                this.inventory[b] = ItemStack.loadItemStackFromNBT(compound);
            }
        }

        this.cookTime = (int) nbt.getShort("CookTime");

        if (nbt.hasKey("CustomName")) {
            this.localizedName = nbt.getString("CustomName");
        }
    }

    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);

        nbt.setShort("CookTime", (short) this.cookTime);

        NBTTagList list = new NBTTagList();

        for (int i = 0; i < this.inventory.length; i++) {
            if (this.inventory[i] != null) {
                NBTTagCompound compound = new NBTTagCompound();
                compound.setByte("Slot", (byte) i);
                this.inventory[i].writeToNBT(compound);
                list.appendTag(compound);
            }
        }

        nbt.setTag("Items", list);

        if (this.hasCustomInventoryName()) {
            nbt.setString("CustomName", this.localizedName);
        }
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
        return this.cookTime * i / this.processingTime;
    }

    public int countCurrentProcessingLVL(ItemStack itemStack){
        Map<Integer,Integer> enchants = EnchantmentHelper.getEnchantments(itemStack);
        int outputItemStackSize=0;
        for(int i:enchants.values()){
            outputItemStackSize = outputItemStackSize + i;
        }
        return outputItemStackSize;
    }
}
