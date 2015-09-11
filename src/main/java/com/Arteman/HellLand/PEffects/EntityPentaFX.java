package com.Arteman.HellLand.PEffects;

import org.lwjgl.opengl.GL11;

import com.Arteman.HellLand.HellLand;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

//Yep, it's will be... I hate minecraft render...
import static org.lwjgl.opengl.GL11.*;

public class EntityPentaFX extends EntityFX{

	public static final ResourceLocation texture = new ResourceLocation(HellLand.MODID + "textures/particles/penta.png");
	
	public EntityPentaFX(World world, double x, double y, double z){
		super(world, x, y, z);
		setGravity(1);
	}

	public void renderParticle(Tessellator tess,
								float particalTicks,
								float par3,
								float par4,
								float par5,
								float par6,
								float par7){
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		GL11.glDepthMask(false);
		GL11.glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glAlphaFunc(GL_GREATER, 0.003921569F);
		tess.startDrawingQuads();
		tess.setBrightness(getBrightnessForRender(particalTicks));
		float scale = 0.1F*particleScale;
		float x = (float)(prevPosX + (posX - prevPosX) * particalTicks - interpPosX);
		float y = (float)(prevPosY + (posY - prevPosY) * particalTicks - interpPosY);
		float z = (float)(prevPosZ + (posZ - prevPosZ) * particalTicks - interpPosZ);
				//4 corners; 0 & 1 - UV 
/*left*/		tess.addVertexWithUV(x - par3 * scale - par6 * scale, y - par4 * scale, z - par5 * scale - par7 * scale, 0, 0);
/*left*/		tess.addVertexWithUV(x - par3 * scale + par6 * scale, y + par4 * scale, z - par5 * scale + par7 * scale, 1, 0);
/*right*/		tess.addVertexWithUV(x + par3 * scale + par6 * scale, y + par4 * scale, z - par5 * scale + par7 * scale, 1, 1);
/*right*/		tess.addVertexWithUV(x + par3 * scale - par6 * scale, y - par4 * scale, z - par5 * scale - par7 * scale, 0, 1);
		tess.draw();
		glDisable(GL_BLEND);
		glDepthMask(true);
		glAlphaFunc(GL_GREATER, 0.1F);
	}
	
	public int getFXLayer(){
		return 3;
	}
	
	public EntityPentaFX setMaxAge(int maxAge){
		particleMaxAge = maxAge;
		return this;
	}
	
	public EntityPentaFX setGravity(int gravity){
		particleGravity = gravity;
		return this;
	}
	
	public EntityPentaFX setScale(int scale){
		particleScale = scale;
		return this;
	}
}
