package com.Arteman.HellLand.gui;

import com.Arteman.HellLand.HellLand;
import com.Arteman.HellLand.container.alchemicalTableContainer;
import com.Arteman.HellLand.tileentity.alchemicalTableTE;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class alchemicalTableGui extends GuiContainer {
    public static final ResourceLocation bground = new ResourceLocation(HellLand.MODID + ":" + "textures/gui/alchemicalTableGui.png");

    public alchemicalTableTE tileEntity;

    public alchemicalTableGui(InventoryPlayer inventory, alchemicalTableTE entity) {
        super(new alchemicalTableContainer(inventory,entity));
        this.tileEntity = entity;
        this.xSize = 176;
        this.ySize = 166;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
        GL11.glColor4f(1F, 1F, 1F, 1F);

        Minecraft.getMinecraft().getTextureManager().bindTexture(bground);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

        if (this.tileEntity.isProcessing()) {
            int k = this.tileEntity.getCookProgressScaled(29);
            int j = 29 - k;
            drawTexturedModalRect(guiLeft + 37, guiTop + 23 + j, 176, j, 12, k);
            drawTexturedModalRect(guiLeft + 128, guiTop + 23 + j, 176, j, 12, k);
        }

    }
}
