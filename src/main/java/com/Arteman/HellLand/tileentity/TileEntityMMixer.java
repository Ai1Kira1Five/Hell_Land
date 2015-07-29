package com.Arteman.HellLand.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

import com.Arteman.HellLand.items.MixedItem;

public class TileEntityMMixer extends TileEntity implements ISidedInventory
{
	private static final int[] slots_final = new int[] {3};
	private static final int[] slots_ingredients = new int[] {0, 1, 2};
	private static final int[] slots_chest = new int[] {4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};

	private ItemStack[] mixerItemStacks = new ItemStack[16];
	
	private int mixTime;
	private int filledSlots;
	private Item ingrID;
	private String unlocalizedName;
	private static final String __OBFID = "CL_00000345";
	
	public void setGuiDisplayName(String displayName)
	{
		this.unlocalizedName = displayName;
	}
	
	public String getInventoryName()
	{
		return this.hasCustomInventoryName() ? this.unlocalizedName : "container.mixering";
	}
	
	@Override
	public boolean hasCustomInventoryName()
	{
		return this.unlocalizedName != null && this.unlocalizedName.length() > 0;
	}
	
	@Override
	public int getSizeInventory()
	{
		return this.mixerItemStacks.length;
	}
	
	public void updateEntity()
	{
		if(this.mixTime > 0)
		{
			--this.mixTime;
			
			if(this.mixTime == 0)
			{
				this.mixItems();
				this.markDirty();
			}
			else if(!this.canMix())
			{
				this.mixTime = 0;
				this.markDirty();
			}
			else if (this.ingrID != this.mixerItemStacks[3].getItem())
			{
				this.mixTime = 0;
				this.markDirty();
			}
		}
		else if(this.canMix())
		{
			this.mixTime = 200;
			this.ingrID = this.mixerItemStacks[3].getItem();
		}
		
		int i = this.getFilledSlots();
		
		if(i != this.filledSlots)
		{
			this.filledSlots = i;
			this.worldObj.setBlockMetadataWithNotify(this.xCoord, this.yCoord, this.zCoord, i, 2);
		}
		
		super.updateEntity();
	}

	public boolean canMix()
	{
		if()
		{
			return true;
		}
		else if()
		{
			return false;
		}
	}
	
	public int getMixTime()
	{
		return this.mixTime;
	}
	
	public int getFilledSlots()
	{
		return 0;
	}

	public void mixItems()
	{
		
	}
	
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		NBTTagList nbttaglist = nbt.getTagList("Items", 10);
		this.mixerItemStacks = new ItemStack[this.getSizeInventory()];
		
		for(int i = 0; i < nbttaglist.tagCount(); i++)
		{
			NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
			byte b0 = nbttagcompound1.getByte("Slot");
			
			if(b0 >= 0 && b0 < this.mixerItemStacks.length)
			{
				this.mixerItemStacks[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
			}
		}
		
		this.mixTime = nbt.getShort("MixTime");
		
		if(nbt.hasKey("CustomName", 8))
		{
			this.unlocalizedName = nbt.getString("CustomName");
		}
	}

	public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        nbt.setShort("BrewTime", (short)this.mixTime);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.mixerItemStacks.length; ++i)
        {
            if (this.mixerItemStacks[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                this.mixerItemStacks[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        nbt.setTag("Items", nbttaglist);

        if (this.hasCustomInventoryName())
        {
            nbt.setString("CustomName", this.unlocalizedName);
        }
    }
	
	@Override
	public ItemStack getStackInSlot(int p_70301_1_)
	{
		return null;
	}

	@Override
	public ItemStack decrStackSize(int p_70298_1_, int p_70298_2_) 
	{
		return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int p_70304_1_) 
	{
		return null;
	}

	@Override
	public void setInventorySlotContents(int p_70299_1_, ItemStack p_70299_2_) 
	{
		
	}

	@Override
	public int getInventoryStackLimit() 
	{
		return 0;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer p_70300_1_)
	{
		return false;
	}

	@Override
	public void openInventory() {}

	@Override
	public void closeInventory() {}

	@Override
	public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_)
	{
		return false;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int p_94128_1_)
	{
		return null;
	}

	@Override
	public boolean canInsertItem(int p_102007_1_, ItemStack p_102007_2_, int p_102007_3_)
	{
		return false;
	}

	@Override
	public boolean canExtractItem(int p_102008_1_, ItemStack p_102008_2_, int p_102008_3_)
	{
		return false;
	}

}
