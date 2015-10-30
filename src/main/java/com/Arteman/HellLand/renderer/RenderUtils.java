package com.Arteman.HellLand.renderer;

import org.lwjgl.opengl.GL11;

import com.Arteman.HellLand.utils.interfaces.IHellRenderBlock;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

public class RenderUtils {
	public static void inventoryBlock(Tessellator tessellator, Block block, ItemStack stack, int modelId, RenderBlocks renderer){
		IHellRenderBlock iBlock = (IHellRenderBlock)block;
		
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		for (int side = 0; side < 6; side++){
			float normX = 0.0F;
		    float normY = 0.0F;
		    float normZ = 0.0F;
		    switch (side){
		    case 0: 
		        normY = -1.0F;
		        break;
		    case 1: 
		        normY = 1.0F;
		        break;
		    case 2: 
		        normZ = -1.0F;
		        break;
		    case 3: 
		        normZ = 1.0F;
		        break;
		    case 4: 
		        normX = -1.0F;
		        break;
		    case 5: 
		        normX = 1.0F;
		    }
		    int layerCount = iBlock.getItemIconLayerCount(side, stack);
		    for (int layer = 0; layer < layerCount; layer++){
		    	IIcon icon = iBlock.getItemIcon(side, stack, layer);
		    	
		    	int color = iBlock.getItemColor(side, stack, layer);
		        float r = (color >> 16 & 0xFF) / 255.0F;
		        float g = (color >> 8 & 0xFF) / 255.0F;
		        float b = (color & 0xFF) / 255.0F;
		        GL11.glColor3f(r * 1.0F, g * 1.0F, b * 1.0F);
		        
		        tessellator.startDrawingQuads();
		        tessellator.setNormal(normX, normY, normZ);
		        switch (side){
		        case 0: 
		            renderer.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, icon);
		            break;
		        case 1: 
		            renderer.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, icon);
		            break;
		        case 2: 
		            renderer.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, icon);
		            break;
		        case 3: 
		            renderer.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, icon);
		            break;
		        case 4: 
		            renderer.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, icon);
		            break;
		        case 5: 
		            renderer.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, icon);
		        }
		        tessellator.draw();
		        
		        GL11.glColor3f(1.0F, 1.0F, 1.0F);
		    }
		}
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
	    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	}
	
	public static void inventorySphere(Tessellator tessellator, Block block, ItemStack stack, int modelId, RenderBlocks renderer){
		IHellRenderBlock iBlock = (IHellRenderBlock)block;
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		for (int side = 0; side < 6; side++){
			float normX = 0.0F;
		    float normY = 0.0F;
		    float normZ = 0.0F;
		    switch (side){
		    case 0: 
		        normY = -1.0F;
		        break;
		    case 1: 
		        normY = 1.0F;
		        break;
		    case 2: 
		        normZ = -1.0F;
		        break;
		    case 3: 
		        normZ = 1.0F;
		        break;
		    case 4: 
		        normX = -1.0F;
		        break;
		    case 5: 
		        normX = 1.0F;
		    }
		    int layerCount = iBlock.getItemIconLayerCount(side, stack);
		    for (int layer = 0; layer < layerCount; layer++){
		    	IIcon icon = iBlock.getItemIcon(side, stack, layer);
		    	
		    	int color = iBlock.getItemColor(side, stack, layer);
		        float r = (color >> 16 & 0xFF) / 255.0F;
		        float g = (color >> 8 & 0xFF) / 255.0F;
		        float b = (color & 0xFF) / 255.0F;
		        GL11.glColor3f(r * 1.0F, g * 1.0F, b * 1.0F);
		        
		        tessellator.startDrawingQuads();
		        tessellator.setNormal(normX, normY, normZ);
		        switch (side){
		        case 0: 
		            SphereUtil.renderSphereYNeg(renderer, block, 0.0D, 0.0D, 0.0D, icon);
		            break;
		        case 1: 
		            SphereUtil.renderSphereYPos(renderer, block, 0.0D, 0.0D, 0.0D, icon);
		            break;
		        case 2: 
		            SphereUtil.renderSphereZNeg(renderer, block, 0.0D, 0.0D, 0.0D, icon);
		            break;
		        case 3: 
		            SphereUtil.renderSphereZPos(renderer, block, 0.0D, 0.0D, 0.0D, icon);
		            break;
		        case 4: 
		            SphereUtil.renderSphereXNeg(renderer, block, 0.0D, 0.0D, 0.0D, icon);
		            break;
		        case 5: 
		            SphereUtil.renderSphereXPos(renderer, block, 0.0D, 0.0D, 0.0D, icon);
		        }
		        tessellator.draw();
		        
		        GL11.glColor3f(1.0F, 1.0F, 1.0F);
		    }
		}
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
	    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	}
	
	public static boolean worldSphere(IBlockAccess world, Block block, RenderBlocks renderer, int x, int y, int z, int doRenderMask){
		if (!(block instanceof IHellRenderBlock)){
			return renderer.renderStandardBlock(block, x, y, z);
		}
		int color = block.colorMultiplier(world, x, y, z);
	    float r = (color >> 16 & 0xFF) / 255.0F;
	    float g = (color >> 8 & 0xFF) / 255.0F;
	    float b = (color & 0xFF) / 255.0F;
	    if (EntityRenderer.anaglyphEnable){
	    	float f3 = (r * 30.0F + g * 59.0F + b * 11.0F) / 100.0F;
	        float f4 = (r * 30.0F + g * 70.0F) / 100.0F;
	        float f5 = (r * 30.0F + b * 70.0F) / 100.0F;
	        r = f3;
	        g = f4;
	        b = f5;
	    }
	    int meta = world.getBlockMetadata(x, y, z);
	    return sphereColor(world, block, renderer, x, y, z, meta, r, g, b, doRenderMask);
	}
	
	public static boolean worldBlock(IBlockAccess world, Block block, RenderBlocks renderer, int x, int y, int z, int doRenderMask){
		if (!(block instanceof IHellRenderBlock)){
			return renderer.renderStandardBlock(block, x, y, z);
		}
		int color = block.colorMultiplier(world, x, y, z);
	    float r = (color >> 16 & 0xFF) / 255.0F;
	    float g = (color >> 8 & 0xFF) / 255.0F;
	    float b = (color & 0xFF) / 255.0F;
	    if (EntityRenderer.anaglyphEnable){
	    	float f3 = (r * 30.0F + g * 59.0F + b * 11.0F) / 100.0F;
	        float f4 = (r * 30.0F + g * 70.0F) / 100.0F;
	        float f5 = (r * 30.0F + b * 70.0F) / 100.0F;
	        r = f3;
	        g = f4;
	        b = f5;
	    }
	    int meta = world.getBlockMetadata(x, y, z);
	    if ((Minecraft.isAmbientOcclusionEnabled()) && (block.getLightValue() == 0)){
	    	if (renderer.partialRenderBounds){
	    		return blockAOPartial(world, block, renderer, x, y, z, meta, r, g, b, doRenderMask);
	    	}
	    	return blockAOSolid(world, block, renderer, x, y, z, meta, r, g, b, doRenderMask);
	    }
	    return blockColor(world, block, renderer, x, y, z, meta, r, g, b, doRenderMask);
	}
	
	private static boolean sphereColor(IBlockAccess world, Block block, RenderBlocks renderer, int x, int y, int z, int meta, float iR, float iG, float iB, int doRenderMask){
		if (!(block instanceof IHellRenderBlock)){
			return renderer.renderStandardBlockWithColorMultiplier(block, x, y, z, iR, iG, iB);
		}
		boolean shouldRenderYPos = (doRenderMask & 0x20) == 32;
	    boolean shouldRenderYNeg = (doRenderMask & 0x10) == 16;
	    boolean shouldRenderZPos = (doRenderMask & 0x8) == 8;
	    boolean shouldRenderZNeg = (doRenderMask & 0x4) == 4;
	    boolean shouldRenderXPos = (doRenderMask & 0x2) == 2;
	    boolean shouldRenderXNeg = (doRenderMask & 0x1) == 1;
	    
	    IHellRenderBlock iBlock = (IHellRenderBlock)block;
	    
	    renderer.enableAO = false;
	    Tessellator tessellator = Tessellator.instance;
	    boolean flag = false;
	    
	    int brightness = block.getLightValue(world, x, y, z);
	    if (shouldRenderYNeg){
	    	tessellator.setBrightness(brightness);
	    	
	    	int layers = iBlock.getBlockIconLayerCount(world, x, y, z, 0, meta);
	        for (int i = 0; i < layers; i++){
	        	int color = iBlock.getBlockColor(world, x, y, z, 0, meta, i);
	            float r = (color >> 16 & 0xFF) / 255.0F;
	            float g = (color >> 8 & 0xFF) / 255.0F;
	            float b = (color & 0xFF) / 255.0F;
	            
	            float selfIllum = iBlock.getBlockSelfIllum(world, x, y, z, 0, meta, i);
	            
	            float r_ = selfIllum * r;
	            float g_ = selfIllum * g;
	            float b_ = selfIllum * b;
	            tessellator.setColorOpaque_F(r_, g_, b_);
	            SphereUtil.renderSphereYNeg(renderer, block, x, y, z, iBlock.getBlockIcon(world, x, y, z, 0, meta, i));
	        }
	        flag = true;
	    }
	    if (shouldRenderYPos){
	    	tessellator.setBrightness(brightness);
	    	
	    	int layers = iBlock.getBlockIconLayerCount(world, x, y, z, 1, meta);
	        for (int i = 0; i < layers; i++){
	        	float selfIllum = iBlock.getBlockSelfIllum(world, x, y, z, 1, meta, i);
	            
	            int color = iBlock.getBlockColor(world, x, y, z, 1, meta, i);
	            float r = (color >> 16 & 0xFF) / 255.0F;
	            float g = (color >> 8 & 0xFF) / 255.0F;
	            float b = (color & 0xFF) / 255.0F;
	            
	            float r_ = selfIllum * r;
	            float g_ = selfIllum * g;
	            float b_ = selfIllum * b;
	            tessellator.setColorOpaque_F(r_, g_, b_);
	            
	            SphereUtil.renderSphereYPos(renderer, block, x, y, z, iBlock.getBlockIcon(world, x, y, z, 1, meta, i));
	        }
	        flag = true;
	    }
	    if (shouldRenderZNeg){
	    	tessellator.setBrightness(brightness);
	        
	        int layers = iBlock.getBlockIconLayerCount(world, x, y, z, 2, meta);
	        for (int i = 0; i < layers; i++){
	        	float selfIllum = iBlock.getBlockSelfIllum(world, x, y, z, 2, meta, i);
	            
	            int color = iBlock.getBlockColor(world, x, y, z, 2, meta, i);
	            float r = (color >> 16 & 0xFF) / 255.0F;
	            float g = (color >> 8 & 0xFF) / 255.0F;
	            float b = (color & 0xFF) / 255.0F;
	            
	            float r_ = selfIllum * r;
	            float g_ = selfIllum * g;
	            float b_ = selfIllum * b;
	            tessellator.setColorOpaque_F(r_, g_, b_);
	            
	            SphereUtil.renderSphereZNeg(renderer, block, x, y, z, iBlock.getBlockIcon(world, x, y, z, 2, meta, i));
	        }
	        flag = true;
	    }
	    if (shouldRenderZPos){
	    	tessellator.setBrightness(brightness);
	        
	        int layers = iBlock.getBlockIconLayerCount(world, x, y, z, 3, meta);
	        for (int i = 0; i < layers; i++){
	        	float selfIllum = iBlock.getBlockSelfIllum(world, x, y, z, 3, meta, i);
	            
	            int color = iBlock.getBlockColor(world, x, y, z, 3, meta, i);
	            float r = (color >> 16 & 0xFF) / 255.0F;
	            float g = (color >> 8 & 0xFF) / 255.0F;
	            float b = (color & 0xFF) / 255.0F;
	            
	            float r_ = selfIllum * r;
	            float g_ = selfIllum * g;
	            float b_ = selfIllum * b;
	            tessellator.setColorOpaque_F(r_, g_, b_);
	            
	            SphereUtil.renderSphereZPos(renderer, block, x, y, z, iBlock.getBlockIcon(world, x, y, z, 3, meta, i));
	        }
	        flag = true;
	    }
	    if (shouldRenderXNeg){
	    	tessellator.setBrightness(brightness);
	        
	        int layers = iBlock.getBlockIconLayerCount(world, x, y, z, 4, meta);
	        for (int i = 0; i < layers; i++){
	        	float selfIllum = iBlock.getBlockSelfIllum(world, x, y, z, 4, meta, i);
	            
	            int color = iBlock.getBlockColor(world, x, y, z, 4, meta, i);
	            float r = (color >> 16 & 0xFF) / 255.0F;
	            float g = (color >> 8 & 0xFF) / 255.0F;
	            float b = (color & 0xFF) / 255.0F;
	            
	            float r_ = selfIllum * r;
	            float g_ = selfIllum * g;
	            float b_ = selfIllum * b;
	            tessellator.setColorOpaque_F(r_, g_, b_);
	            
	            SphereUtil.renderSphereXNeg(renderer, block, x, y, z, iBlock.getBlockIcon(world, x, y, z, 4, meta, i));
	        }
	        flag = true;
	    }
	    if (shouldRenderXPos){
	    	tessellator.setBrightness(brightness);
	        
	        int layers = iBlock.getBlockIconLayerCount(world, x, y, z, 5, meta);
	        for (int i = 0; i < layers; i++){
	        	float selfIllum = iBlock.getBlockSelfIllum(world, x, y, z, 5, meta, i);
	            
	            int color = iBlock.getBlockColor(world, x, y, z, 5, meta, i);
	            float r = (color >> 16 & 0xFF) / 255.0F;
	            float g = (color >> 8 & 0xFF) / 255.0F;
	            float b = (color & 0xFF) / 255.0F;
	            
	            float r_ = selfIllum * r;
	            float g_ = selfIllum * g;
	            float b_ = selfIllum * b;
	            tessellator.setColorOpaque_F(r_, g_, b_);
	            
	            SphereUtil.renderSphereXPos(renderer, block, x, y, z, iBlock.getBlockIcon(world, x, y, z, 5, meta, i));
	        }
	        flag = true;
	    }
	    return flag;
	}
	
	private static boolean blockColor(IBlockAccess world, Block block, RenderBlocks renderer, int x, int y, int z, int meta, float iR, float iG, float iB, int doRenderMask){
		if (!(block instanceof IHellRenderBlock)){
			return renderer.renderStandardBlockWithColorMultiplier(block, x, y, z, iR, iG, iB);
		}
		boolean shouldRenderYPos = (doRenderMask & 0x20) == 32;
	    boolean shouldRenderYNeg = (doRenderMask & 0x10) == 16;
	    boolean shouldRenderZPos = (doRenderMask & 0x8) == 8;
	    boolean shouldRenderZNeg = (doRenderMask & 0x4) == 4;
	    boolean shouldRenderXPos = (doRenderMask & 0x2) == 2;
	    boolean shouldRenderXNeg = (doRenderMask & 0x1) == 1;
	    
	    IHellRenderBlock iBlock = (IHellRenderBlock)block;
	    
	    renderer.enableAO = false;
	    Tessellator tessellator = Tessellator.instance;
	    boolean flag = false;
	    
	    int brightness = block.getLightValue(world, x, y, z);
	    if (shouldRenderYNeg){
	    	tessellator.setBrightness(brightness);
	    	
	    	int layers = iBlock.getBlockIconLayerCount(world, x, y, z, 0, meta);
	        for (int i = 0; i < layers; i++){
	        	int color = iBlock.getBlockColor(world, x, y, z, 0, meta, i);
	            float r = (color >> 16 & 0xFF) / 255.0F;
	            float g = (color >> 8 & 0xFF) / 255.0F;
	            float b = (color & 0xFF) / 255.0F;
	            
	            float selfIllum = iBlock.getBlockSelfIllum(world, x, y, z, 0, meta, i);
	            
	            float r_ = selfIllum * r;
	            float g_ = selfIllum * g;
	            float b_ = selfIllum * b;
	            tessellator.setColorOpaque_F(r_, g_, b_);
	            SphereUtil.renderSphereYNeg(renderer, block, x, y, z, iBlock.getBlockIcon(world, x, y, z, 0, meta, i));
	        }
	        flag = true;
	    }
	    if (shouldRenderYPos){
	    	tessellator.setBrightness(brightness);
	    	
	    	int layers = iBlock.getBlockIconLayerCount(world, x, y, z, 1, meta);
	        for (int i = 0; i < layers; i++){
	        	float selfIllum = iBlock.getBlockSelfIllum(world, x, y, z, 1, meta, i);
	            
	            int color = iBlock.getBlockColor(world, x, y, z, 1, meta, i);
	            float r = (color >> 16 & 0xFF) / 255.0F;
	            float g = (color >> 8 & 0xFF) / 255.0F;
	            float b = (color & 0xFF) / 255.0F;
	            
	            float r_ = selfIllum * r;
	            float g_ = selfIllum * g;
	            float b_ = selfIllum * b;
	            tessellator.setColorOpaque_F(r_, g_, b_);
	            
	            SphereUtil.renderSphereYPos(renderer, block, x, y, z, iBlock.getBlockIcon(world, x, y, z, 1, meta, i));
	        }
	        flag = true;
	    }
	    if (shouldRenderZNeg){
	    	tessellator.setBrightness(brightness);
	        
	        int layers = iBlock.getBlockIconLayerCount(world, x, y, z, 2, meta);
	        for (int i = 0; i < layers; i++){
	        	float selfIllum = iBlock.getBlockSelfIllum(world, x, y, z, 2, meta, i);
	            
	            int color = iBlock.getBlockColor(world, x, y, z, 2, meta, i);
	            float r = (color >> 16 & 0xFF) / 255.0F;
	            float g = (color >> 8 & 0xFF) / 255.0F;
	            float b = (color & 0xFF) / 255.0F;
	            
	            float r_ = selfIllum * r;
	            float g_ = selfIllum * g;
	            float b_ = selfIllum * b;
	            tessellator.setColorOpaque_F(r_, g_, b_);
	            
	            SphereUtil.renderSphereZNeg(renderer, block, x, y, z, iBlock.getBlockIcon(world, x, y, z, 2, meta, i));
	        }
	        flag = true;
	    }
	    if (shouldRenderZPos){
	    	tessellator.setBrightness(brightness);
	        
	        int layers = iBlock.getBlockIconLayerCount(world, x, y, z, 3, meta);
	        for (int i = 0; i < layers; i++){
	        	float selfIllum = iBlock.getBlockSelfIllum(world, x, y, z, 3, meta, i);
	            
	            int color = iBlock.getBlockColor(world, x, y, z, 3, meta, i);
	            float r = (color >> 16 & 0xFF) / 255.0F;
	            float g = (color >> 8 & 0xFF) / 255.0F;
	            float b = (color & 0xFF) / 255.0F;
	            
	            float r_ = selfIllum * r;
	            float g_ = selfIllum * g;
	            float b_ = selfIllum * b;
	            tessellator.setColorOpaque_F(r_, g_, b_);
	            
	            SphereUtil.renderSphereZPos(renderer, block, x, y, z, iBlock.getBlockIcon(world, x, y, z, 3, meta, i));
	        }
	        flag = true;
	    }
	    if (shouldRenderXNeg){
	    	tessellator.setBrightness(brightness);
	        
	        int layers = iBlock.getBlockIconLayerCount(world, x, y, z, 4, meta);
	        for (int i = 0; i < layers; i++){
	        	float selfIllum = iBlock.getBlockSelfIllum(world, x, y, z, 4, meta, i);
	            
	            int color = iBlock.getBlockColor(world, x, y, z, 4, meta, i);
	            float r = (color >> 16 & 0xFF) / 255.0F;
	            float g = (color >> 8 & 0xFF) / 255.0F;
	            float b = (color & 0xFF) / 255.0F;
	            
	            float r_ = selfIllum * r;
	            float g_ = selfIllum * g;
	            float b_ = selfIllum * b;
	            tessellator.setColorOpaque_F(r_, g_, b_);
	            
	            SphereUtil.renderSphereXNeg(renderer, block, x, y, z, iBlock.getBlockIcon(world, x, y, z, 4, meta, i));
	        }
	        flag = true;
	    }
	    if (shouldRenderXPos){
	    	tessellator.setBrightness(brightness);
	        
	        int layers = iBlock.getBlockIconLayerCount(world, x, y, z, 5, meta);
	        for (int i = 0; i < layers; i++){
	        	float selfIllum = iBlock.getBlockSelfIllum(world, x, y, z, 5, meta, i);
	            
	            int color = iBlock.getBlockColor(world, x, y, z, 5, meta, i);
	            float r = (color >> 16 & 0xFF) / 255.0F;
	            float g = (color >> 8 & 0xFF) / 255.0F;
	            float b = (color & 0xFF) / 255.0F;
	            
	            renderer.uvRotateSouth = iBlock.getBlockIconRotation(world, x, y, z, 5, meta, i);
	            float r_ = selfIllum * r;
	            float g_ = selfIllum * g;
	            float b_ = selfIllum * b;
	            tessellator.setColorOpaque_F(r_, g_, b_);
	            FaceUtil.solidXP(renderer, x, y, z, iBlock.getBlockIcon(world, x, y, z, f, meta, i), selfIllum, color, iBlock.getBlockIconRotation(world, x, y, z, f, meta, i));
	            
	            renderer.uvRotateSouth = 0;
	        }
	        flag = true;
	    }
	    return flag;
	}
}
