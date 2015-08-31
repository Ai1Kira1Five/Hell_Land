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
    public static ResourceLocation texture = new ResourceLocation(HellLand.MODID + ":textures/blocks/crystalSpawn_0.png");
    @Override
    public void renderItem(ItemRenderType type, ItemStack is, Object... data) {
        GL11.glPushMatrix();
        GL11.glTranslatef(0.5F, 0.0F, 0.5F);
        int meta=is.getItemDamage();
        switch (meta){
            case  0: texture = new ResourceLocation(HellLand.MODID+":textures/blocks/crystalSpawn_0.png"); break;
            case  1: texture = new ResourceLocation(HellLand.MODID+":textures/blocks/crystalSpawn_1.png"); break;
            case  2: texture = new ResourceLocation(HellLand.MODID+":textures/blocks/crystalSpawn_2.png"); break;
            case  3: texture = new ResourceLocation(HellLand.MODID+":textures/blocks/crystalSpawn_3.png"); break;
            case  4: texture = new ResourceLocation(HellLand.MODID+":textures/blocks/crystalSpawn_4.png"); break;
            case  5: texture = new ResourceLocation(HellLand.MODID+":textures/blocks/crystalSpawn_5.png"); break;
        }

       Minecraft.getMinecraft().renderEngine.bindTexture(texture);
       RenderCrystal.model.renderAll();
        GL11.glPopMatrix();
    }
}
