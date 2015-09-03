package com.Arteman.HellLand.gui;

import com.Arteman.HellLand.HellLand;
import com.Arteman.HellLand.container.hellOvenContainer;
import com.Arteman.HellLand.tileentity.hellOvenTE;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class hellOvenGui extends GuiContainer {
    public static final ResourceLocation bground = new ResourceLocation(HellLand.MODID + ":" + "textures/gui/hellOvenGui.png");

    public hellOvenTE hellOven;

    public hellOvenGui(InventoryPlayer inventoryPlayer, hellOvenTE entity) {
        super(new hellOvenContainer(inventoryPlayer, entity));

        this.hellOven = entity;

        this.xSize = 176;
        this.ySize = 166;
    }


    public void drawGuiContainerForegroundLayer(int par1, int par2) {
        String name = "Hell Oven";
        this.fontRendererObj.drawString(name, this.xSize / 2 - this.fontRendererObj.getStringWidth(name) / 2, 6, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
        GL11.glColor4f(1F, 1F, 1F, 1F);

        Minecraft.getMinecraft().getTextureManager().bindTexture(bground);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

        if (this.hellOven.isBurning()) {
            int k = this.hellOven.getBurnTimeRemainingScaled(40);
            int j = 40 - k;
            drawTexturedModalRect(guiLeft + 29, guiTop + 65, 176, 0, 40 - j, 10);
        }

        int k = this.hellOven.getCookProgressScaled(24);
        drawTexturedModalRect(guiLeft + 79, guiTop + 34, 176, 10, k + 1, 16);
    }
}
