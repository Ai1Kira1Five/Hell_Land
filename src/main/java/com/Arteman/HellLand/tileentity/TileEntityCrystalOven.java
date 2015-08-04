package com.Arteman.HellLand.tileentity;

import com.Arteman.HellLand.blocks.machines.CrystalOven;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import org.apache.commons.lang3.ArrayUtils;

public class TileEntityCrystalOven extends TileEntity implements ISidedInventory {
    private String localizedName;

    private static final int[] slots_top = new int[]{3, 4, 5, 6};
    private static final int[] slots_bottom = new int[]{0, 1, 2};
    private static final int[] slots_side = new int[]{7, 8, 9, 10};
    private ItemStack[] slots = new ItemStack[11];

    public int furnaceSpeed = 50;

    public int burnTime;

    public int currentItemBurnTime;

    public int cookTime;

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
    public boolean isUseableByPlayer(EntityPlayer entityplayer) {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : entityplayer.getDistanceSq((double) this.xCoord + 0.5D, (double) this.yCoord + 0.5D, (double) this.zCoord + 0.5D) <= 64.0D;
    }

    public void openInventory() {
    }

    public void closeInventory() {
    }

    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemstack) {
        if (ArrayUtils.contains(slots_side, i)) {
            return false;
        } else if (ArrayUtils.contains(slots_bottom, i)) {
            return isItemFuel(itemstack);
        } else if (FurnaceRecipes.smelting().getSmeltingResult(itemstack) != null) {
            return true;
        } else {
            return false;
        }
    }

    public int getSizeInventory() {
        return this.slots.length;
    }

    @Override
    public ItemStack getStackInSlot(int var1) {
        return this.slots[var1];
    }

    @Override
    public ItemStack decrStackSize(int var1, int var2) {
        if (this.slots[var1] != null) {
            ItemStack itemstack;
            if (this.slots[var1].stackSize <= var2) {
                itemstack = this.slots[var1];
                this.slots[var1] = null;
                this.markDirty();
                return itemstack;
            } else {
                itemstack = this.slots[var1].splitStack(var2);
                if (this.slots[var1].stackSize == 0) {
                    this.slots[var1] = null;
                }
                this.markDirty();
                return itemstack;
            }
        }
        return null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int i) {
        if (this.slots[i] != null) {
            ItemStack itemstack = this.slots[i];
            this.slots[i] = null;
            return itemstack;
        }
        return null;
    }

    @Override
    public void setInventorySlotContents(int i, ItemStack itemstack) {
        this.slots[i] = itemstack;

        if (itemstack != null && itemstack.stackSize > this.getInventoryStackLimit()) {
            itemstack.stackSize = this.getInventoryStackLimit();
        }
    }

    public static boolean isItemFuel(ItemStack itemstack) {
        return getItemBurnTime(itemstack) > 0;
    }

    private static int getItemBurnTime(ItemStack itemstack) {
        if (itemstack == null) {
            return 0;
        } else {
            Item item = itemstack.getItem();

            if (item instanceof ItemBlock && Block.getBlockFromItem(item) != Blocks.air) {
                Block block = Block.getBlockFromItem(item);

                if (block == Blocks.sapling) return 100;
                if (block == Blocks.coal_block) return 14400;
                if (block == Blocks.log) return 400;
                if (block == Blocks.log2) return 400;
                if (block == Blocks.planks) return 300;
                if (block == Blocks.wooden_button) return 100;

            }

            if (item == Items.coal) return 1600;
            if (item == Items.stick) return 100;
            if (item == Items.lava_bucket) return 20000;
            if (item == Items.blaze_rod) return 2400;
        }
        return GameRegistry.getFuelValue(itemstack);
    }

    public boolean isBurning() {
        return this.burnTime > 0;
    }

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
                    this.currentItemBurnTime = this.burnTime = getItemBurnTime(this.slots[i]);

                    if (this.isBurning()) {
                        flag1 = true;

                        if (this.slots[i] != null) {
                            this.slots[i].stackSize--;

                            if (this.slots[i].stackSize == 0) {
                                this.slots[i] = this.slots[i].getItem().getContainerItem(this.slots[i]);
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
                CrystalOven.updateCrystalOvenBlockState(this.burnTime > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
            }
        }
        if (flag1) {
            this.markDirty();
        }
    }

    public boolean canSmelt() {

        int i = getFirstSmeltSlot();
        if (i < 0) return false;
        ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.slots[i]);
        if (itemstack == null) return false;
        return canSmeltToSlot(itemstack, 7) || canSmeltToSlot(itemstack, 8) || canSmeltToSlot(itemstack, 9) || canSmeltToSlot(itemstack, 10);
    }

    public boolean canSmeltToSlot(ItemStack itemstack, int slot) {
        if (this.slots[slot] == null) return true;
        if (!this.slots[slot].isItemEqual(itemstack)) return false;
        int result = this.slots[slot].stackSize + itemstack.stackSize;
        return (result <= getInventoryStackLimit() && result <= itemstack.getMaxStackSize());
    }

    public void smeltItem() {
        if (this.canSmelt()) {
            for(int i=3;i<7;i++) {
                if(this.slots[i]==null) continue;
                ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.slots[i]);
                int s = getFirstResultSlot(itemstack);
                if(s<0)continue;
                if (this.slots[s] == null) {
                    this.slots[s] = itemstack.copy();
                } else if (this.slots[s].isItemEqual(itemstack)) {
                    this.slots[s].stackSize += itemstack.stackSize;
                }

                this.slots[i].stackSize--;

                if (this.slots[i].stackSize <= 0) {
                    this.slots[i] = null;
                }
            }
        }
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int var1) {
        return var1 == 0 ? slots_bottom : (var1 == 1 ? slots_top : slots_side);
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

        NBTTagList list = nbt.getTagList("Items", 10);
        this.slots = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < list.tagCount(); i++) {
            NBTTagCompound compound = (NBTTagCompound) list.getCompoundTagAt(i);
            byte b = compound.getByte("Slot");

            if (b >= 0 && b < this.slots.length) {
                this.slots[b] = ItemStack.loadItemStackFromNBT(compound);
            }
        }

        this.burnTime = (int) nbt.getShort("BurnTime");
        this.cookTime = (int) nbt.getShort("CookTime");
        this.currentItemBurnTime = (int) nbt.getShort("CurrentBurnTime");

        if (nbt.hasKey("CustomName")) {
            this.localizedName = nbt.getString("CustomName");
        }
    }

    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);

        nbt.setShort("BurnTime", (short) this.burnTime);
        nbt.setShort("CookTime", (short) this.cookTime);
        nbt.setShort("CurrentBurnTime", (short) this.currentItemBurnTime);

        NBTTagList list = new NBTTagList();

        for (int i = 0; i < this.slots.length; i++) {
            if (this.slots[i] != null) {
                NBTTagCompound compound = new NBTTagCompound();
                compound.setByte("Slot", (byte) i);
                this.slots[i].writeToNBT(compound);
                list.appendTag(compound);
            }
        }

        nbt.setTag("Items", list);

        if (this.hasCustomInventoryName()) {
            nbt.setString("CustomName", this.localizedName);
        }
    }

    private int getFirstFuelSlot() {
        for (int i = 0; i < 3; i++) {
            if (isItemFuel(this.slots[i])) {
                return i;
            }
        }
        return -1;
    }

    private int getFirstSmeltSlot() {
        for (int i = 3; i < 7; i++) {
            if (this.slots[i] != null && FurnaceRecipes.smelting().getSmeltingResult(this.slots[i]) != null) {
                return i;
            }
        }
        return -1;
    }

    private int getFirstResultSlot(ItemStack itemStack) {
        for (int i = 7; i < 11; i++) {
            if (this.slots[i] == null || (itemStack.isItemEqual(this.slots[i]) && (this.slots[i].stackSize + itemStack.stackSize) <= getInventoryStackLimit())) {
                return i;
            }
        }
        return -1;
    }
}
