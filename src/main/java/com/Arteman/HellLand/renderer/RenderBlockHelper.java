package com.Arteman.HellLand.renderer;

import java.nio.FloatBuffer;

import org.lwjgl.opengl.GL11;

import codechicken.lib.colour.Colour;

import com.Arteman.HellLand.HellLand;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class RenderBlockHelper 
{
	@SideOnly(Side.CLIENT)
	private static final Vec3 field_82884_b = Vec3.createVectorHelper(0.20000000298023224D, 1.0D, -0.699999988079071D).normalize();
	
	@SideOnly(Side.CLIENT)
	private static final Vec3 field_82885_c = Vec3.createVectorHelper(-0.20000000298023224D, 1.0D, 0.699999988079071D).normalize();

	@SideOnly(Side.CLIENT)
	private static final FloatBuffer colorBuffer = GLAllocation.createDirectFloatBuffer(16);

	@SideOnly(Side.CLIENT)
	public static void DrawFaces(RenderBlocks renderblocks, Block block, int meta)
	{
		int color = block.getRenderColor(meta);
		float red = (float)(color >> 16 & 255) / 255.0F;
		float green = (float)(color >> 8 & 255) / 255.0F;
		float blue = (float)(color & 255) / 255.0F;
		GL11.glColor3f(red, green, blue);
		DrawFaces(renderblocks, block, block.getIcon(0, meta), block.getIcon(1, meta), block.getIcon(2, meta), block.getIcon(3, meta), block.getIcon(4, meta), block.getIcon(5, meta), false);
		GL11.glColor3f(1.0F, 1.0F, 1.0F);
	}
	
	@SideOnly(Side.CLIENT)
	public static void DrawFaces(RenderBlocks renderblocks, Block block, IIcon i, boolean solidTop)
	{
		DrawFaces(renderblocks, block, i, i, i, i, i, i, solidTop);
	}
	
	@SideOnly(Side.CLIENT)
	public static void DrawFaces(RenderBlocks renderblocks, Block block, IIcon i1, IIcon i2, IIcon i3, IIcon i4, IIcon i5, IIcon i6, boolean solidtop)
	{
			Tessellator tessellator = Tessellator.instance;
			GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
			tessellator.startDrawingQuads();
			tessellator.setNormal(0.0F, -1.0F, 0.0F);
			renderblocks.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, i1);
			tessellator.draw();
			tessellator.startDrawingQuads();
			tessellator.setNormal(0.0F, 1.0F, 0.0F);
			renderblocks.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, i2);
			tessellator.draw();
			tessellator.startDrawingQuads();
			tessellator.setNormal(0.0F, 0.0F, -1.0F);
			renderblocks.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, i3);
			tessellator.draw();
			tessellator.startDrawingQuads();
			tessellator.setNormal(0.0F, 0.0F, 1.0F);
			renderblocks.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, i4);
			tessellator.draw();
			tessellator.startDrawingQuads();
			tessellator.setNormal(-1.0F, 0.0F, 0.0F);
			renderblocks.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, i5);
		tessellator.draw();
	    tessellator.startDrawingQuads();
	    tessellator.setNormal(1.0F, 0.0F, 0.0F);
	    renderblocks.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, i6);
	    tessellator.draw();
	    GL11.glTranslatef(0.5F, 0.5F, 0.5F);
	}
	
	@SideOnly(Side.CLIENT)
	public static void DrawAnimation(RenderBlocks renderer, Block block, int meta)
	{
		DrawAnimation(renderer, block, meta, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	}
	
	@SideOnly(Side.CLIENT)
	public static void DrawAnimation(RenderBlocks renderer, Block block, int meta, float minX, float minY, float minZ, float maxX, float maxY, float maxZ)
	{
		if(block instanceof AnimationSideHandler)
		{
			AnimationSideHandler animatedBlock = (AnimationSideHandler)block;
			HellLand var10000 = HellLand.instance;
			Tessellator tessellator = Tessellator.instance;
			float scale = 0.002F;
			GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
			ForgeDirection side = ForgeDirection.DOWN;
			IIcon icon = block.getIcon(6, meta);
			Colour animatedColor = animatedBlock.getItemColor(meta, side);
			int animatedBrightness = animatedBlock.getAnimationBrightness(meta, side);
			if(animatedColor != null)
			{
				setColorWithBrightness(animatedColor, animatedBrightness);
				tessellator.startDrawingQuads();
				tessellator.setNormal(0.0F, -1.0F, 0.0F);
				renderer.renderFaceYNeg(block, (double)((float)(-side.offsetX) * scale), (double)(minY - (float)side.offsetY * scale), (double)((float)(-side.offsetZ) * scale), icon);
				tessellator.draw();
			}
			
			side = ForgeDirection.UP;
			animatedColor = animatedBlock.getItemColor(meta, side);
			animatedBrightness = animatedBlock.getAnimationBrightness(meta, side);
			if(animatedColor != null)
			{
				setColorWithBrightness(animatedColor, animatedBrightness);
				tessellator.startDrawingQuads();
				tessellator.setNormal(0.0F, 1.0F, 0.0F);
				renderer.renderFaceYPos(block, (double)((float)(-side.offsetX) * scale), (double)(0.0F - (float)side.offsetY * scale), (double)((float)(-side.offsetZ) * scale), icon);
				tessellator.draw();
			}
			
			side = ForgeDirection.SOUTH;
			animatedColor = animatedBlock.getItemColor(meta, side);
			animatedBrightness = animatedBlock.getAnimationBrightness(meta, side);
			if(animatedColor != null)
			{
				setColorWithBrightness(animatedColor, animatedBrightness);
				tessellator.startDrawingQuads();
				tessellator.setNormal(0.0F, 0.0F, 1.0F);
				renderer.renderFaceZPos(block, (double)((float)(-side.offsetX) * scale), (double)((float)(-side.offsetY) * scale), (double)((float)(-side.offsetZ) * scale), icon);
				tessellator.draw();
			}
			
			side = ForgeDirection.NORTH;
			animatedColor = animatedBlock.getItemColor(meta, side);
			animatedBrightness = animatedBlock.getAnimationBrightness(meta, side);
			if(animatedColor != null)
			{
				setColorWithBrightness(animatedColor, animatedBrightness);
				tessellator.startDrawingQuads();
				tessellator.setNormal(0.0F, 0.0F, -1.0F);
				renderer.renderFaceZNeg(block, (double)((float)(-side.offsetX) * scale), (double)((float)(-side.offsetY) * scale), (double)((float)(-side.offsetZ) * scale), icon);
				tessellator.draw();
			}
			
			side = ForgeDirection.EAST;
			animatedColor = animatedBlock.getItemColor(meta, side);
			animatedBrightness = animatedBlock.getAnimationBrightness(meta, side);
			if(animatedColor != null)
			{
				setColorWithBrightness(animatedColor, animatedBrightness);
				tessellator.startDrawingQuads();
				tessellator.setNormal(1.0F, 0.0F, 0.0F);
				renderer.renderFaceXPos(block, (double)((float)(-side.offsetX) * scale), (double)((float)(-side.offsetY) * scale), (double)((float)(-side.offsetZ) * scale), icon);
				tessellator.draw();
			}
			
			side = ForgeDirection.WEST;
			animatedColor = animatedBlock.getItemColor(meta, side);
			animatedBrightness = animatedBlock.getAnimationBrightness(meta, side);
			if(animatedColor != null)
			{
				setColorWithBrightness(animatedColor, animatedBrightness);
				tessellator.startDrawingQuads();
				tessellator.setNormal(-1.0F, 0.0F, 0.0F);
				renderer.renderFaceXNeg(block, (double)((float)(-side.offsetX) * scale), (double)((float)(-side.offsetY) * scale), (double)((float)(-side.offsetZ) * scale), icon);
				tessellator.draw();
			}
			
			GL11.glTranslatef(0.5F, 0.5F, 0.5F);
			RenderHelper.enableStandardItemLighting();

		}
	}
	
	@SideOnly(Side.CLIENT)
	public static void renderAnimation(RenderBlocks renderer, IBlockAccess world, Block block, int x, int y, int z)
	{
		renderAnimation(renderer, world, block, x, y, z, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	}
	
	@SideOnly(Side.CLIENT)
	public static void renderAnimation(RenderBlocks renderer, IBlockAccess iBlockAccess, Block block, int x, int y, int z, float minX, float minY, float minZ, float maxX, float maxY, float maxZ)
	{
		if(block instanceof AnimationSideHandler)
		{
			AnimationSideHandler animatedBlock = (AnimationSideHandler)block;
			HellLand var10000 = HellLand.instance;
			Tessellator tess = Tessellator.instance;
			renderer.setRenderBounds((double)minX, (double)minY, (double)minZ, (double)maxX, (double)maxY, (double)maxZ);
			ForgeDirection[] side = ForgeDirection.VALID_DIRECTIONS;
			int length = side.length;

			for(int i = 0; i < length; ++i)
			{
				ForgeDirection side1 = side[i];
				renderFace(tess, renderer, block, animatedBlock, iBlockAccess, x, y, z, side1, false);
			}
		}
	}
	
	@SideOnly(Side.CLIENT)
	public static boolean renderFace(Tessellator tess, RenderBlocks renderer, Block block, AnimationSideHandler animatedBlock, IBlockAccess iBlockAccess, int x, int y, int z, ForgeDirection side, boolean override)
	{
		IIcon icon = block.getIcon(6, 0);
		Colour color = animatedBlock.getColor(iBlockAccess, x, y, z, side);
	    int brightness = animatedBlock.getAnimationBrightness(iBlockAccess, x, y, z, side);
	    if(color != null && (override || block.shouldSideBeRendered(iBlockAccess, x + side.offsetX, y + side.offsetY, z + side.offsetZ, side.ordinal())))
	    {
	    	tess.setColorOpaque_I(color.rgb());
	    	tess.setBrightness(brightness);
	    	switch(RenderBlockHelper.NamelessClass426710.$SwitchMap$net$minecraftforge$common$ForgeDirection[side.ordinal()])
	    	{
/*down*/   	case 1:
	    		renderer.renderFaceYNeg(block, (double)x, (double)y, (double)z, icon);
	    		break;
/*up*/    	case 2:
	    		renderer.renderFaceYPos(block, (double)x, (double)y, (double)z, icon);
	    		break;
/*north*/  	case 3:
	    		renderer.renderFaceZNeg(block, (double)x, (double)y, (double)z, icon);
	    		break;
/*south*/   	case 4:
	    		renderer.renderFaceZPos(block, (double)x, (double)y, (double)z, icon);
	    		break;
/*west*/	case 5:
				renderer.renderFaceXNeg(block, (double)x, (double)y, (double)z, icon);
				break;
/*east*/	case 6:
				renderer.renderFaceXPos(block, (double)x, (double)y, (double)z, icon);
	    	}
	    	return true;
	    }else{
	    	return false;
	    }
	}
	
	@SideOnly(Side.CLIENT)
	public static void renderTexturedQuad(int par1, int par2, int par3, int par4, int par5, int par6, float zLevel)
	{
		float var7 = 0.00390625F;
	    float var8 = 0.00390625F;
	    Tessellator var9 = Tessellator.instance;
	    var9.startDrawingQuads();
	    var9.addVertexWithUV((double)(par1 + 0), (double)(par2 + par6), (double)zLevel, (double)((float)(par3 + 0) * var7), (double)((float)(par4 + par6) * var8));
	    var9.addVertexWithUV((double)(par1 + par5), (double)(par2 + par6), (double)zLevel, (double)((float)(par3 + par5) * var7), (double)((float)(par4 + par6) * var8));
	    var9.addVertexWithUV((double)(par1 + par5), (double)(par2 + 0), (double)zLevel, (double)((float)(par3 + par5) * var7), (double)((float)(par4 + 0) * var8));
	    var9.addVertexWithUV((double)(par1 + 0), (double)(par2 + 0), (double)zLevel, (double)((float)(par3 + 0) * var7), (double)((float)(par4 + 0) * var8));
	    var9.draw();
	}
	
	@SideOnly(Side.CLIENT)
	public static void setColorWithBrightness(Colour animatedColor, int brightness)
	{
		animatedColor.glColour((int)255);
	    GL11.glEnable(2896);
	    GL11.glEnable(16384);
	    GL11.glEnable(16385);
	    GL11.glEnable(2903);
	    GL11.glColorMaterial(1032, 5634);
	    float var0 = (float)brightness / 255.0F;
	    float var1 = 0.1F;
	    GL11.glLight(16384, 4611, setColorBuffer(field_82884_b.xCoord, field_82884_b.yCoord, field_82884_b.zCoord, 0.0D));
	    GL11.glLight(16384, 4609, setColorBuffer(var1, var1, var1, 1.0F));
	    GL11.glLight(16384, 4608, setColorBuffer(0.0F, 0.0F, 0.0F, 1.0F));
	    GL11.glLight(16384, 4610, setColorBuffer(0.0F, 0.0F, 0.0F, 1.0F));
	    GL11.glLight(16385, 4611, setColorBuffer(field_82885_c.xCoord, field_82885_c.yCoord, field_82885_c.zCoord, 0.0D));
	    GL11.glLight(16385, 4609, setColorBuffer(var1, var1, var1, 1.0F));
	    GL11.glLight(16385, 4608, setColorBuffer(0.0F, 0.0F, 0.0F, 1.0F));
	    GL11.glLight(16385, 4610, setColorBuffer(0.0F, 0.0F, 0.0F, 1.0F));
	    GL11.glShadeModel(7424);
	    GL11.glLightModel(2899, setColorBuffer(var0, var0, var0, 1.0F));
	}
	
	@SideOnly(Side.CLIENT)
	private static FloatBuffer setColorBuffer(double par0, double par2, double par4, double par6)
	{
		return setColorBuffer((float)par0, (float)par2, (float)par4, (float)par6);
	}
	
	@SideOnly(Side.CLIENT)
	private static FloatBuffer setColorBuffer(float par0, float par1, float par2, float par3)
	{
		if(colorBuffer == null)
		{  
		   return null;
		}else{
			colorBuffer.clear();
			colorBuffer.put(par0).put(par1).put(par2).put(par3);
			colorBuffer.flip();
			return colorBuffer;
		}
	}
	
	static class NamelessClass426710
	{
		static final int[] $SwitchMap$net$minecraftforge$common$ForgeDirection = new int[ForgeDirection.values().length];
		
		static
		{
			try
			{
				$SwitchMap$net$minecraftforge$common$ForgeDirection[ForgeDirection.DOWN.ordinal()] = 1;
			}
			catch (NoSuchFieldError var6)
			{
				;
			}
			try
			{
				$SwitchMap$net$minecraftforge$common$ForgeDirection[ForgeDirection.UP.ordinal()] = 2;
			}
			catch (NoSuchFieldError var5)
			{
				;
			}
			try
		    {
		       $SwitchMap$net$minecraftforge$common$ForgeDirection[ForgeDirection.NORTH.ordinal()] = 3;
		    }
		    catch (NoSuchFieldError var4)
		    {
		       ;
		    }
		    try
		    {
		   	   $SwitchMap$net$minecraftforge$common$ForgeDirection[ForgeDirection.SOUTH.ordinal()] = 4;
		    }
		    catch (NoSuchFieldError var3)
		    {
		   	   ;
		    }
		    try
		    {
		   	   $SwitchMap$net$minecraftforge$common$ForgeDirection[ForgeDirection.WEST.ordinal()] = 5;
		    }
		    catch (NoSuchFieldError var2)
		    {
		   	   ;
		    }
		    try
		    {
		  	   $SwitchMap$net$minecraftforge$common$ForgeDirection[ForgeDirection.EAST.ordinal()] = 6;
		    }
		    catch (NoSuchFieldError var1)
		    {
		   	   ;
		    }
		}
	}
}
