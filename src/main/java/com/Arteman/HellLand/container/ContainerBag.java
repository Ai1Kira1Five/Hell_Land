package com.Arteman.HellLand.container;
import com.Arteman.HellLand.items.Bag;
import com.Arteman.HellLand.utils.NBTHelper;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerBag extends Container
{
    private final InventoryPlayer inventoryPlayer;
    public final InventoryBag inventoryBag;

    private int bagInventoryRows;
    private int bagInventoryColumns;

    public ContainerBag(InventoryPlayer inventory, InventoryBag inventoryBag)
    {
        this.inventoryPlayer = inventory;
        this.inventoryBag = inventoryBag;
        bagInventoryRows = 3;
        bagInventoryColumns = 9;

        for (int bagRowIndex = 0; bagRowIndex < bagInventoryRows; ++bagRowIndex)
        {
            for (int bagColumnIndex = 0; bagColumnIndex < bagInventoryColumns; ++bagColumnIndex)
            {
                this.addSlotToContainer(new SlotBag(this, inventoryBag, inventoryPlayer, bagColumnIndex + bagRowIndex * bagInventoryColumns, 8 + bagColumnIndex * 18, 18 + bagRowIndex * 18));

            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                this.addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 87 + i * 18));
            }
        }

        //for (int i = 0; i < 9; i++) {
        //    this.addSlotToContainer(new Slot(inventory, i, 8 + i * 18, 142));
        //}
  /*
        for (int inventoryRowIndex = 0; inventoryRowIndex < 3; ++inventoryRowIndex){
            for (int inventoryColumnIndex = 0; inventoryColumnIndex < 9; ++inventoryColumnIndex){
                this.addSlotToContainer(new Slot(inventoryPlayer, inventoryColumnIndex + inventoryRowIndex * 9 + 9, 35 + inventoryColumnIndex * 18, 104 + inventoryRowIndex * 18));
            }
        }
*/
        for (int actionBarSlotIndex = 0; actionBarSlotIndex < 9; ++actionBarSlotIndex){
                if(!(inventoryPlayer.getStackInSlot(actionBarSlotIndex)==inventoryBag.parentItemStack)){
                    this.addSlotToContainer(new Slot(inventoryPlayer, actionBarSlotIndex, 8 + actionBarSlotIndex * 18, 144));
                }else{
                    this.addSlotToContainer(new Slot(inventoryPlayer, actionBarSlotIndex, 8 + actionBarSlotIndex * 18, 144){
                        public boolean canTakeStack(EntityPlayer p_82869_1_){
                            return false;
                        }
                    });
                }
        }
    }

    @Override
    public void onContainerClosed(EntityPlayer entityPlayer)
    {
        super.onContainerClosed(entityPlayer);

        if (!entityPlayer.worldObj.isRemote){
            InventoryPlayer invPlayer = entityPlayer.inventory;
            for (ItemStack itemStack : invPlayer.mainInventory)
            {
                if (itemStack != null)
                {
                    if (NBTHelper.hasTag(itemStack, "bagGuiOpen"))
                    {
                        NBTHelper.removeTag(itemStack, "bagGuiOpen");
                    }
                }
            }

            saveInventory(invPlayer);
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer){
        return true;
    }

    public boolean isItemStackParent(ItemStack itemStack){
        return false;
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer entityPlayer, int slotIndex){
        ItemStack newItemStack = null;
        Slot slot = (Slot) inventorySlots.get(slotIndex);

        if (slot != null && slot.getHasStack()){
            ItemStack itemStack = slot.getStack();
            newItemStack = itemStack.copy();

            if (slotIndex < 27){
                if (!this.mergeItemStack(itemStack, 27, inventorySlots.size(), false)){
                    return null;
                }
            }else if (itemStack.getItem() instanceof Bag){
                if (slotIndex < 54){
                    if (!this.mergeItemStack(itemStack, 54, inventorySlots.size(), false)){
                        return null;
                    }
                }else if (!this.mergeItemStack(itemStack, 27, 54, false)){
                    return null;
                }
            }else if (!this.mergeItemStack(itemStack, 0, 27, false)) {
                return null;
            }


            if (itemStack.stackSize == 0){
                slot.putStack(null);
            }else{
                slot.onSlotChanged();
            }
        }
        return newItemStack;
    }

    public void saveInventory(InventoryPlayer inventoryPlayer){
        inventoryBag.onGuiSaved(inventoryPlayer);
    }

    private class SlotBag extends Slot{
        private final InventoryPlayer inventoryPlayer;
        private ContainerBag containerBag;

        public SlotBag(ContainerBag containerBag, IInventory inventory, InventoryPlayer inventoryPlayer1, int slotIndex, int x, int y){
            super(inventory, slotIndex, x, y);
            this.inventoryPlayer = inventoryPlayer1;
            this.containerBag = containerBag;
        }

        @Override
        public void onSlotChanged(){
            super.onSlotChanged();

            if (FMLCommonHandler.instance().getEffectiveSide().isServer())
            {
                containerBag.saveInventory(inventoryPlayer);
            }
        }
        @Override
        public boolean isItemValid(ItemStack itemStack){
            return !(itemStack.getItem() instanceof Bag);
        }
    }
}