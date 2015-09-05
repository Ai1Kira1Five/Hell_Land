package com.Arteman.HellLand.renderer.customRender;

import java.util.List;

import com.Arteman.HellLand.renderer.blockRenderer;
import com.Arteman.HellLand.renderer.customRender.vecmath.Vector3d;
import com.Arteman.HellLand.renderer.customRender.vecmath.Vertex;
import com.Arteman.HellLand.tileentity.tileEntityWithInventory;
import com.Arteman.HellLand.utils.BlockCoord;
import com.Arteman.HellLand.utils.ForgeDirectionOffsets;
import com.Arteman.HellLand.utils.TEBlockHell;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraftforge.common.util.ForgeDirection;

public class OverlayRenderer implements IRenderFace {

    private static final CustomCubeRenderer ccr = CustomCubeRenderer.instance;
    private tileEntityWithInventory te;

    public void setTile(tileEntityWithInventory te) {
        this.te = te;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void renderFace(CustomRenderBlocks rb, ForgeDirection face, Block par1Block, double x, double y, double z, IIcon texture, List<Vertex> refVertices,
                           boolean translateToXyz) {

        if(te != null && par1Block instanceof TEBlockHell) {
            BlockCoord bc = new BlockCoord(x, y, z);
            if(par1Block.isOpaqueCube()) {
                bc = bc.getLocation(face);
            }
            blockRenderer.setTesselatorBrightness(Minecraft.getMinecraft().theWorld, bc.x, bc.y, bc.z);
            Vector3d offset = ForgeDirectionOffsets.offsetScaled(face, 0.001);
            Tessellator.instance.addTranslation((float) offset.x, (float) offset.y, (float) offset.z);
            Tessellator.instance.addTranslation(-(float) offset.x, -(float) offset.y, -(float) offset.z);
        }
    }
}