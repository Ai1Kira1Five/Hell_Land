package com.Arteman.HellLand.gui;

import com.Arteman.HellLand.HellLand;
import com.Arteman.HellLand.container.ContainerCrystalOven;
import com.Arteman.HellLand.tileentity.TileEntityCrystalOven;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiCrystalOven extends GuiContainer {
    public static final ResourceLocation bground = new ResourceLocation(HellLand.MODID + ":" + "textures/gui/GuiCrystalOven.png");

    public TileEntityCrystalOven crystalOven;

    public GuiCrystalOven(InventoryPlayer inventoryPlayer, TileEntityCrystalOven entity) {
        super(new ContainerCrystalOven(inventoryPlayer, entity));

        this.crystalOven = entity;

        this.xSize = 176;
        this.ySize = 166;
    }

    public void drawGuiContainerForegroundLayer(int par1, int par2) {
        String name = "Crystal Oven";

        this.fontRendererObj.drawString(name, this.xSize / 2 - this.fontRendererObj.getStringWidth(name) / 2, 6, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
        GL11.glColor4f(1F, 1F, 1F, 1F);

        Minecraft.getMinecraft().getTextureManager().bindTexture(bground);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

        if (this.crystalOven.isBurning()) {
            int k = this.crystalOven.getBurnTimeRemainingScaled(14);
            int j = 14 - k;
            drawTexturedModalRect(guiLeft + 43, guiTop + 65 + j, 176, j, 14, k);
            drawTexturedModalRect(guiLeft + 121, guiTop + 65 + j, 176, j, 14, k);
            //drawTexturedModalRect(guiLeft + 29, guiTop + 65, 176, 0, 40 - j, 10);
        }

        int k = this.crystalOven.getCookProgressScaled(24);
        drawTexturedModalRect(guiLeft + 77, guiTop + 35, 176, 14, k, 16);


    }
}
