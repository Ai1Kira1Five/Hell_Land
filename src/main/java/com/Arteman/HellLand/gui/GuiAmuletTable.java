package com.Arteman.HellLand.gui;

import com.Arteman.HellLand.HellLand;
import com.Arteman.HellLand.container.ContainerAmuletTable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class GuiAmuletTable extends GuiContainer {
    private ResourceLocation texture = new ResourceLocation(HellLand.MODID + ":" + "textures/gui/GuiAmuletTable.png");

    public GuiAmuletTable(InventoryPlayer invPlayer, World world, int x, int y, int z) {
        super(new ContainerAmuletTable(invPlayer, world, x, y, z));

        this.xSize = 176;
        this.ySize = 166;
    }

    public void onGuiClosed() {
        super.onGuiClosed();
    }

    protected void drawGuiContainerForegraundLayer(int i, int j) {
        this.fontRendererObj.drawString(StatCollector.translateToLocal("Amulet Table"), 100, 5, 0x000000);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
        GL11.glColor4f(1f, 1f, 1f, 1f);

        Minecraft.getMinecraft().getTextureManager().bindTexture(texture);

        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }

}
