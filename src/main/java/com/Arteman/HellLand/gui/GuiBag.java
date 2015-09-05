package com.Arteman.HellLand.gui;


import com.Arteman.HellLand.HellLand;
import com.Arteman.HellLand.container.ContainerBag;
import com.Arteman.HellLand.container.InventoryBag;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiBag extends GuiContainer{

    public static ResourceLocation bground;

    private final ItemStack parentItemStack;
    private final InventoryBag inventoryBag;

    public GuiBag(InventoryPlayer inventory, InventoryBag inventoryBag)
    {
        super(new ContainerBag(inventory, inventoryBag));

        this.parentItemStack = inventoryBag.parentItemStack;
        this.inventoryBag = inventoryBag;

        this.bground = new ResourceLocation(HellLand.MODID+":"+"textures/gui/guiBag"+parentItemStack.getItemDamage()+".png");

        xSize = 176;
        switch(parentItemStack.getItemDamage()) {
            case 0:
                ySize = 150;
                break;
            case 1:
                ySize = 186;
                break;
            case 2:
                ySize = 222;
                break;
            default:ySize = 150;
        }


    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y) {
            fontRendererObj.drawString(StatCollector.translateToLocal(inventoryBag.getInventoryName()), 8, 6, Integer.parseInt("ffffff", 16));
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float opacity, int x, int y){
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        this.mc.getTextureManager().bindTexture(bground);


        int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;
        this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
    }

    @Override
    public void onGuiClosed(){
        super.onGuiClosed();

        if (mc.thePlayer != null){
            for (ItemStack itemStack : mc.thePlayer.inventory.mainInventory){
                if (itemStack != null){
                    if (itemStack != null && itemStack.stackTagCompound != null && itemStack.stackTagCompound.hasKey("bagGuiOpen")){
                        itemStack.stackTagCompound.removeTag("bagGuiOpen");
                    }
                }
            }
        }
    }

    @Override
    protected boolean checkHotbarKeys(int key){
        return false;
    }

}