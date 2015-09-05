package com.Arteman.HellLand.renderer;


import com.Arteman.HellLand.renderer.customRender.vecmath.Vertex;
import com.Arteman.HellLand.tileentity.tileEntityWithInventory;
import com.Arteman.HellLand.utils.TEBlockHell;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.common.util.ForgeDirection;
import com.Arteman.HellLand.renderer.customRender.*;

import java.util.List;

public class blockRenderer implements ISimpleBlockRenderingHandler, IItemRenderer {

    private OverlayRenderer overlayRenderer = new OverlayRenderer() {
        @Override
        public void renderFace(CustomRenderBlocks rb, ForgeDirection face, Block par1Block, double x, double y, double z, IIcon texture, List<Vertex> refVertices,
                               boolean translateToXyz) {

            ccr.getCustomRenderBlocks().doDefaultRenderFace(face, par1Block, x, y, z, texture);
            super.renderFace(rb, face, par1Block, x, y, z, texture, refVertices, translateToXyz);
        }
    };

    private CustomCubeRenderer ccr = new CustomCubeRenderer();

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return true;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
        Block block;
             renderInventoryBlock(Block.getBlockFromItem(item.getItem()), item.getItemDamage(), 0, (RenderBlocks) data[0]);
    }

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {

        BoundingBox bb = BoundingBox.UNIT_CUBE;
        bb = bb.translate(0, -0.1f, 0);

        Tessellator.instance.startDrawingQuads();

        IIcon[] textures = getBlockTextures(block, metadata);

        float[] brightnessPerSide = new float[6];
        for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
            brightnessPerSide[dir.ordinal()] = Math.max(getColorMultiplierForFace(dir) + 0.1f, 1f);
        }
        CubeRenderer.render(bb, textures, null, brightnessPerSide);
        Tessellator.instance.draw();
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        TileEntity te = world.getTileEntity(x, y, z);
        tileEntityWithInventory machine = null;

        if(te instanceof tileEntityWithInventory) {
            machine = (tileEntityWithInventory) te;
        }
        overlayRenderer.setTile(machine);
        ccr.setOverrideTexture(renderer.overrideBlockTexture);
        ccr.renderBlock(world, block, x, y, z, overlayRenderer);
        ccr.setOverrideTexture(null);

        return true;
    }

    @Override
    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }

    @Override
    public int getRenderId() {
        return TEBlockHell.renderId;
    }

    public static IIcon[] getBlockTextures(Block block, int meta) {
        IIcon[] icons = new IIcon[6];
        int i = 0;
        for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
            icons[i] = block.getIcon(dir.ordinal(), meta);
            i++;
        }
        return icons;
    }

    public static IIcon[] getBlockTextures(IBlockAccess world, int x, int y, int z) {
        Block block = world.getBlock(x, y, z);
        IIcon[] icons = new IIcon[6];
        int i = 0;
        for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
            icons[i] = block.getIcon(world, x, y, z, dir.ordinal());
            i++;
        }
        return icons;
    }

    public static float getColorMultiplierForFace(ForgeDirection face) {
        if (face == ForgeDirection.UP) {
            return 1;
        }
        if (face == ForgeDirection.DOWN) {
            return 0.5f;
        }
        if (face.offsetX != 0) {
            return 0.6f;
        }
        return 0.8f; // z
    }

    public static int setTesselatorBrightness(IBlockAccess world, int x, int y, int z) {
        Block block = world.getBlock(x, y, z);
        int res = block == null ? world.getLightBrightnessForSkyBlocks(x, y, z, 0) : block.getMixedBrightnessForBlock(world, x, y, z);
        Tessellator.instance.setBrightness(res);
        Tessellator.instance.setColorRGBA_F(1, 1, 1, 1);
        return res;
    }

}