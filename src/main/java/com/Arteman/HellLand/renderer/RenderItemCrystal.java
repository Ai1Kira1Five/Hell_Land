package com.Arteman.HellLand.renderer;

import org.lwjgl.opengl.GL11;

import com.Arteman.HellLand.HellLand;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

public class RenderItemCrystal implements IItemRenderer{
	@Override
    public boolean handleRenderType(ItemStack is, ItemRenderType type) {
        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack is, ItemRendererHelper helper) {
        return true;
    }
    public static ResourceLocation texture = new ResourceLocation(HellLand.MODID + ":" + "textures/blocks/crystal1.png");
    @Override
    public void renderItem(ItemRenderType type, ItemStack is, Object... data) {
        GL11.glPushMatrix();
        GL11.glTranslatef(0.5F, 0.0F, 0.5F);
        int meta=is.getItemDamage();
        switch (meta){
            case  0: texture = new ResourceLocation(HellLand.MODID, "textures/blocks/crystal_blank.png"); break;
            case  1: texture = new ResourceLocation(HellLand.MODID, "textures/blocks/crystal_air.png"); break;
            case  2: texture = new ResourceLocation(HellLand.MODID, "textures/blocks/crystal_water.png"); break;
            case  3: texture = new ResourceLocation(HellLand.MODID, "textures/blocks/crystal_fire.png"); break;
            case  4: texture = new ResourceLocation(HellLand.MODID, "textures/blocks/crystal_earth.png"); break;
            case  5: texture = new ResourceLocation(HellLand.MODID, "textures/blocks/crystal_pure.png"); break;
        }

       Minecraft.getMinecraft().renderEngine.bindTexture(texture);
       RenderCrystal.model.renderAll();
        GL11.glPopMatrix();
    }
}
