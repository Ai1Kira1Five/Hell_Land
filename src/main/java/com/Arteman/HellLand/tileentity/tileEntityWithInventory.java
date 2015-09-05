package com.Arteman.HellLand.tileentity;

import com.Arteman.HellLand.utils.interfaces.IFacing;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public abstract class tileEntityWithInventory extends TileEntity implements IInventory,ISidedInventory,IFacing{

    public ItemStack[] inventory;
    public String localizedName;
    public int facing = 3;
    private boolean lastActivity = false;

    public tileEntityWithInventory(int slots){
        this.inventory = new ItemStack[slots];
    }

    public void setGuiDisplayName(String displayName) {
        this.localizedName = displayName;
    }

    //abstract ISidedInventory
    public abstract int[] getAccessibleSlotsFromSide(int slot);
    public abstract boolean canInsertItem(int slot, ItemStack itemStack, int side);
    public abstract boolean canExtractItem(int slot, ItemStack itemStack, int side);

    //IInventory
    public String getInventoryName() {
        return this.hasCustomInventoryName() ? this.localizedName : "container.abstractTile";
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
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) == this && player.getDistanceSq((double) this.xCoord + 0.5D, (double) this.yCoord + 0.5D, (double) this.zCoord + 0.5D) <= 64.0D;
    }

    public void openInventory() {
    }
    public void closeInventory() {
    }

    @Override
    public abstract boolean isItemValidForSlot(int i, ItemStack itemstack);

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
            } else {
                itemstack = this.inventory[slot].splitStack(amount);
                if (this.inventory[slot].stackSize == 0) {
                    this.inventory[slot] = null;
                }
            }
            this.markDirty();
            return itemstack;
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
    public void setInventorySlotContents(int slot, ItemStack itemstack) {
        this.inventory[slot] = itemstack;

        if (itemstack != null && itemstack.stackSize > this.getInventoryStackLimit()) {
            itemstack.stackSize = this.getInventoryStackLimit();
        }
    }
    @Override
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
        this.facing = nbt.getShort("facing");
        if (nbt.hasKey("CustomName")) {
            this.localizedName = nbt.getString("CustomName");
        }
    }
    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);

        nbt.setShort("facing",(short)this.getFacing());

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

    @Override
    public void updateEntity() {
        boolean curActivity = isActive();
        if(this.lastActivity != curActivity){
            this.lastActivity = curActivity;
            this.worldObj.markBlocksDirtyVertical(this.xCoord, this.yCoord, this.zCoord, this.zCoord+1);
            this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
        }
        super.updateEntity();
    }

    public void setFacing(int facing) {
        this.facing = facing;
    }

    public int getFacing() {
        return this.facing;
    }

    public abstract boolean isActive();

}
