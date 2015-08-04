package com.Arteman.HellLand.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ContainerAmuletTable extends Container {
    public InventoryCrafting craftMatrix;
    public IInventory craftResult;
    private World worldObj;
    private int posX;
    private int posY;
    private int posZ;

    public ContainerAmuletTable(InventoryPlayer invPlayer, World world, int x, int y, int z) {
        craftMatrix = new InventoryCrafting(this, 3, 3);
        craftResult = new InventoryCraftResult();
        worldObj = world;
        posX = x;
        posY = y;
        posZ = z;

        this.addSlotToContainer(new SlotCrafting(invPlayer.player, craftMatrix, craftResult, 0, 131, 36));

        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < 3; k++) {
                this.addSlotToContainer(new Slot(craftMatrix, k + i * 3, 4 + k * 18, 3 + i * 18));
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < 9; k++) {
                this.addSlotToContainer(new Slot(invPlayer, k + i * 9 + 9, 8 + k * 18, 94 + i * 18));
            }
        }

        for (int i = 0; i < 9; i++) {
            this.addSlotToContainer(new Slot(invPlayer, i, 8 + i * 18, 148));
        }

        onCraftMatrixChanged(craftMatrix);
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return false;
    }

    public void onContainerClosed(EntityPlayer player) {
        super.onContainerClosed(player);

        if (!this.worldObj.isRemote) {
            for (int i = 0; i < 9; ++i) {
                ItemStack itemstack = this.craftMatrix.getStackInSlotOnClosing(i);

                if (itemstack != null) {
                    player.dropPlayerItemWithRandomChoice(itemstack, false);
                }
            }
        }
    }
}
