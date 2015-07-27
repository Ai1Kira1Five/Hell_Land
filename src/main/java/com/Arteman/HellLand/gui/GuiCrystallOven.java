package com.Arteman.HellLand.gui;

import org.lwjgl.opengl.GL11;

import com.Arteman.HellLand.HellLand;
import com.Arteman.HellLand.container.ContainerCrystallOven;
import com.Arteman.HellLand.tileentity.TileEntityCrystallOven;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiCrystallOven extends GuiContainer
{
	public static final ResourceLocation bground = new ResourceLocation(HellLand.MODID + ":" + "textures/gui/GuiCrystallOven.png");
	
	public TileEntityCrystallOven crystallOven;
	
	public GuiCrystallOven(InventoryPlayer inventoryPlayer, TileEntityCrystallOven entity) 
	{
        super(new ContainerCrystallOven(inventoryPlayer, entity));

        this.crystallOven = entity;

        this.xSize = 176;
        this.ySize = 166;
    }
	
	public void drawGuiContainerForegroundLayer(int par1, int par2)
	{
        String name = "Crystall Oven";

        this.fontRendererObj.drawString(name, this.xSize / 2 - this.fontRendererObj.getStringWidth(name) / 2, 6, 4210752);
        this.fontRendererObj.drawString(I18n.format("Container.inventory", new Object[0]), 118, this.ySize - 96 + 2, 4210752);
    }
	
	 @Override
	    protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3)
	 {
	        GL11.glColor4f(1F, 1F, 1F, 1F);

	        Minecraft.getMinecraft().getTextureManager().bindTexture(bground);
	        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);


	        if (this.crystallOven.isBurning())
	        {
	            int k = this.crystallOven.getBurnTimeRemainingScaled(14);
	            int j = 14 - k;
                    drawTexturedModalRect(guiLeft + 43, guiTop + 65 + j, 176, j, 14, k);
                    drawTexturedModalRect(guiLeft + 121, guiTop + 65 + j, 176, j, 14, k);
	        }

	        int k = this.crystallOven.getCookProgressScaled(24);
	        drawTexturedModalRect(guiLeft + 77, guiTop + 35, 176, 14, k, 16);
	        
	 }
}
