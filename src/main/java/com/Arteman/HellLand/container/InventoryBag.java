package com.Arteman.HellLand.container;

import com.Arteman.HellLand.utils.NBTHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.StatCollector;

import java.util.UUID;

public class InventoryBag implements IInventory
{
    public ItemStack parentItemStack;
    protected ItemStack[] inventory;
    protected String customName;
    private int minSize = 18;

    public InventoryBag(ItemStack itemStack){
        parentItemStack = itemStack;
        int size = getSizeFromDamage(itemStack);
        inventory = new ItemStack[size];
        readFromNBT(itemStack.getTagCompound());
    }

    public void onGuiSaved(InventoryPlayer inventoryPlayer){
        parentItemStack = findParentItemStack(inventoryPlayer);

        if (parentItemStack != null){
            save();
        }
    }

    public ItemStack findParentItemStack(InventoryPlayer inventoryPlayer){
        if (NBTHelper.hasUUID(parentItemStack))
        {
            UUID parentItemStackUUID = new UUID(parentItemStack.getTagCompound().getLong(NBTHelper.UUID_MOST_SIG), parentItemStack.getTagCompound().getLong(NBTHelper.UUID_LEAST_SIG));
            for (int i = 0; i < inventoryPlayer.getSizeInventory(); i++)
            {
                ItemStack itemStack = inventoryPlayer.getStackInSlot(i);

                if (NBTHelper.hasUUID(itemStack))
                {
                    if (itemStack.getTagCompound().getLong(NBTHelper.UUID_MOST_SIG) == parentItemStackUUID.getMostSignificantBits() && itemStack.getTagCompound().getLong(NBTHelper.UUID_LEAST_SIG) == parentItemStackUUID.getLeastSignificantBits())
                    {
                        return itemStack;
                    }
                }
            }
        }

        return null;
    }

    public boolean matchesUUID(UUID uuid)
    {
        return NBTHelper.hasUUID(parentItemStack) && parentItemStack.getTagCompound().getLong(NBTHelper.UUID_LEAST_SIG) == uuid.getLeastSignificantBits() && parentItemStack.getTagCompound().getLong(NBTHelper.UUID_MOST_SIG) == uuid.getMostSignificantBits();
    }

    public void save(){
        NBTTagCompound nbtTagCompound = parentItemStack.getTagCompound();

        if (nbtTagCompound == null){
            nbtTagCompound = new NBTTagCompound();

            UUID uuid = UUID.randomUUID();
            nbtTagCompound.setLong(NBTHelper.UUID_MOST_SIG, uuid.getMostSignificantBits());
            nbtTagCompound.setLong(NBTHelper.UUID_LEAST_SIG, uuid.getLeastSignificantBits());
        }

        writeToNBT(nbtTagCompound);
        parentItemStack.setTagCompound(nbtTagCompound);
    }

    @Override
    public int getSizeInventory(){
        return inventory.length;
    }

    @Override
    public ItemStack getStackInSlot(int slotIndex){
        return inventory[slotIndex];
    }

    @Override
    public ItemStack decrStackSize(int slotIndex, int decrementAmount){
        ItemStack itemStack = getStackInSlot(slotIndex);
        if (itemStack != null){
            if (itemStack.stackSize <= decrementAmount){
                setInventorySlotContents(slotIndex, null);
            }else{
                itemStack = itemStack.splitStack(decrementAmount);
                if (itemStack.stackSize == 0){
                    setInventorySlotContents(slotIndex, null);
                }
            }
        }

        return itemStack;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slotIndex){
        if (inventory[slotIndex] != null){
            ItemStack itemStack = inventory[slotIndex];
            inventory[slotIndex] = null;
            return itemStack;
        }else{
            return null;
        }
    }

    @Override
    public void setInventorySlotContents(int slotIndex, ItemStack itemStack){
        inventory[slotIndex] = itemStack;
    }

    @Override
    public String getInventoryName(){
        return this.hasCustomName() ? this.getCustomName() : "container.bag";
    }

    @Override
    public boolean hasCustomInventoryName(){
        return false;
    }

    @Override
    public int getInventoryStackLimit(){
        return 64;
    }

    @Override
    public void markDirty(){}

    @Override
    public boolean isUseableByPlayer(EntityPlayer entityPlayer){
        return true;
    }

    @Override
    public void openInventory(){}

    @Override
    public void closeInventory(){}

    @Override
    public boolean isItemValidForSlot(int slotIndex, ItemStack itemStack){
        return true;
    }


    public void readFromNBT(NBTTagCompound nbtTagCompound){
        if (nbtTagCompound != null && nbtTagCompound.hasKey("items")){
            if (nbtTagCompound.hasKey("items")){
                NBTTagList tagList = nbtTagCompound.getTagList("items", 10);
                inventory = new ItemStack[this.getSizeInventory()];
                for (int i = 0; i < tagList.tagCount(); ++i){
                    NBTTagCompound tagCompound = tagList.getCompoundTagAt(i);
                    byte slotIndex = tagCompound.getByte("Slot");
                    if (slotIndex >= 0 && slotIndex < inventory.length){
                        inventory[slotIndex] = ItemStack.loadItemStackFromNBT(tagCompound);
                    }
                }
            }

            if (nbtTagCompound.hasKey("display") && nbtTagCompound.getTag("display").getClass().equals(NBTTagCompound.class)){
                if (nbtTagCompound.getCompoundTag("display").hasKey("Name")){
                    customName = nbtTagCompound.getCompoundTag("display").getString("Name");
                }
            }
        }
    }

    public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
        NBTTagList tagList = new NBTTagList();
        for (int currentIndex = 0; currentIndex < inventory.length; ++currentIndex)
        {
            if (inventory[currentIndex] != null)
            {
                NBTTagCompound tagCompound = new NBTTagCompound();
                tagCompound.setByte("Slot", (byte) currentIndex);
                inventory[currentIndex].writeToNBT(tagCompound);
                tagList.appendTag(tagCompound);
            }
        }
        nbtTagCompound.setTag("items", tagList);
    }

    public boolean hasCustomName()
    {
        return customName != null && customName.length() > 0;
    }

    public String getCustomName()
    {
        return customName;
    }

    public int getSizeFromDamage(ItemStack itemStack){
        int meta = ((itemStack.getItemDamage()>2)?0:itemStack.getItemDamage())+1;
        return minSize*meta;
    }
}
