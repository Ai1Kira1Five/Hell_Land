package com.Arteman.HellLand.renderer;

import org.lwjgl.opengl.GL11;

import com.Arteman.HellLand.HellLand;
import com.Arteman.HellLand.tileentity.TileEntityCrystalSpawn;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class RenderCrystal extends TileEntitySpecialRenderer {
	
	public static final IModelCustom model =
            AdvancedModelLoader.loadModel(new ResourceLocation(HellLand.MODID + ":" + "textures/obj/ñrystal.obj"));
	int i;
    public static ResourceLocation texture = new ResourceLocation(HellLand.MODID + ":" + "textures/blocks/crystal1.png");
    
	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float f) {
		render((TileEntityCrystalSpawn)tileEntity,x,y,z,f);
	}
	
	private void render(TileEntityCrystalSpawn tile, double x, double y, double z, float f) {
        GL11.glPushMatrix();
        GL11.glTranslated(x, y, z);
        GL11.glTranslatef(0.5F, 0.0F, 0.5F);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        int meta=tile.getBlockMetadata();
        switch (meta){
            case  0: texture = new ResourceLocation(HellLand.MODID, "textures/blocks/crystal_blank.png"); break;
            case  1: texture = new ResourceLocation(HellLand.MODID, "textures/blocks/crystal_air.png"); break;
            case  2: texture = new ResourceLocation(HellLand.MODID, "textures/blocks/crystal_water.png"); break;
            case  3: texture = new ResourceLocation(HellLand.MODID, "textures/blocks/crystal_fire.png"); break;
            case  4: texture = new ResourceLocation(HellLand.MODID, "textures/blocks/crystal_earth.png"); break;
            case  5: texture = new ResourceLocation(HellLand.MODID, "textures/blocks/crystal_pure.png"); break;
        }
        bindTexture(texture);
        model.renderAll();
        GL11.glPopMatrix();
        GL11.glDisable(GL11.GL_BLEND);
    }
}
