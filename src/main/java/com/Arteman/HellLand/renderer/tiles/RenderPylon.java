package com.Arteman.HellLand.renderer.tiles;

import org.lwjgl.opengl.GL11;

import com.Arteman.HellLand.handler.BlockRenderingHandler;
import com.Arteman.HellLand.tileentity.TilePylon;
import com.Arteman.HellLand.utils.interfaces.IComplexBlockRenderingHandler;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;

public class RenderPylon 
				extends TileEntitySpecialRenderer
				implements IComplexBlockRenderingHandler
{
	ModelPylon model;
	
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {}

	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer){
		return false;
	}
	
	public boolean shouldRender3DInInventory(int modelId){
		return false;
	}
	
	public int getRenderId(){
		return BlockRenderingHandler.renderPylonId;
	}
	
	public void renderInventoryBlockStack(Block block, ItemStack stack, int modelId, RenderBlocks renderer){
		GL11.glPushMatrix();
	    GL11.glTranslatef(-0.5F, -0.7F, -0.5F);
	    TilePylon pylon = new TilePylon();
	    
	}

	@Override
	public void renderTileEntityAt(TileEntity entity, double p_147500_2_, double p_147500_4_, double p_147500_6_, float p_147500_8_) {
		// TODO Auto-generated method stub
		
	}
}
