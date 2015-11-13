package com.Arteman.HellLand.container;

import com.Arteman.HellLand.tileentity.mMixerTE;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

public class mMixerContainer extends Container{
	private mMixerTE mMixer;
	
	private IInventory lowerChestInventory;
    private int numRows;
	public int lastMixTime;
    public int lastCurrentItemMixTime;
    private int[] slots_final 			= new int[]{3};
    private int[] slots_ingredients 	= new int[]{0, 1, 2};
    private int[] slotsInput_chest 		= new int[]{4, 5, 6, 7, 8, 9};
    private int[] slotsOutput_chest		= new int[]{10, 11, 12, 13, 14, 15};
    

    public mMixerContainer(InventoryPlayer inventory, IInventory inv1, IInventory inv2, mMixerTE tileentity) {
    	this.mMixer = tileentity;
    	this.lowerChestInventory = inv2;
        this.numRows = inv2.getSizeInventory() / 9;
        inv2.openInventory();
        int i = (this.numRows - 4) * 2;
        int j;
        int k;
        
    	this.addSlotToContainer(new ingredientsSlot((IInventory) tileentity, 0, 57, 39));
    	this.addSlotToContainer(new ingredientsSlot((IInventory) tileentity, 1, 80, 46));
    	this.addSlotToContainer(new ingredientsSlot((IInventory) tileentity, 2, 103, 39));
    	this.addSlotToContainer(new finalSlot((IInventory) tileentity, 3, 80, 10));
    	
    	for (j = 0; j < this.numRows; ++j)
        {
            for (k = 0; k < 2; ++k)
            {
                this.addSlotToContainer(new Slot(inv2, k + j * 2, 8 + k * 18, 18 + j * 18));
            }
        }
        
        for (j = 0; j < 3; ++j)
        {
            for (k = 0; k < 2; ++k)
            {
                this.addSlotToContainer(new Slot(inv1, k + j * 2 + 2, 8 + k * 18, 103 + j * 18 + i));
            }
        }
        
        for (j = 0; j < 2; ++j)
        {
            this.addSlotToContainer(new Slot(inv1, j, 8 + j * 18, 161 + i));
        }    	
    }


	@Override
	public boolean canInteractWith(EntityPlayer p_75145_1_) {
		// TODO Auto-generated method stub
		return false;
	}

}
