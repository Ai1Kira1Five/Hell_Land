package com.Arteman.HellLand.renderer;

import org.lwjgl.opengl.GL11;

import com.Arteman.HellLand.HellLand;
import com.Arteman.HellLand.tileentity.mForgeTE;

import net.minecraft.client.model.ModelBook;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityEnchantmentTable;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class renderMForgeBook  extends TileEntitySpecialRenderer{
	private static final ResourceLocation texture = new ResourceLocation(HellLand.MODID + ":" + ":textures/entity/forge_book.png");
    private ModelBook model = new ModelBook();
    private static final String __OBFID = "CL_00000966";
    
    public void renderTileEntityAt(mForgeTE te, double x, double y, double z, float fl)
    {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)x + 0.5F, (float)y + 0.75F, (float)z + 0.5F);
        float f1 = (float)te.field_145926_a + fl;
        GL11.glTranslatef(0.0F, 0.1F + MathHelper.sin(f1 * 0.1F) * 0.01F, 0.0F);
        float f2;

        for (f2 = te.field_145928_o - te.field_145925_p; f2 >= (float)Math.PI; f2 -= ((float)Math.PI * 2F))
        {
            ;
        }

        while (f2 < -(float)Math.PI)
        {
            f2 += ((float)Math.PI * 2F);
        }

        float f3 = te.field_145925_p + f2 * fl;
        GL11.glRotatef(-f3 * 180.0F / (float)Math.PI, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(80.0F, 0.0F, 0.0F, 1.0F);
        this.bindTexture(texture);
        float f4 = te.field_145931_j + (te.field_145933_i - te.field_145931_j) * fl + 0.25F;
        float f5 = te.field_145931_j + (te.field_145933_i - te.field_145931_j) * fl + 0.75F;
        f4 = (f4 - (float)MathHelper.truncateDoubleToInt((double)f4)) * 1.6F - 0.3F;
        f5 = (f5 - (float)MathHelper.truncateDoubleToInt((double)f5)) * 1.6F - 0.3F;

        if (f4 < 0.0F)
        {
            f4 = 0.0F;
        }

        if (f5 < 0.0F)
        {
            f5 = 0.0F;
        }

        if (f4 > 1.0F)
        {
            f4 = 1.0F;
        }

        if (f5 > 1.0F)
        {
            f5 = 1.0F;
        }

        float f6 = te.field_145927_n + (te.field_145930_m - te.field_145927_n) * fl;
        GL11.glEnable(GL11.GL_CULL_FACE);
        this.model.render((Entity)null, f1, f4, f5, f6, 0.0F, 0.0625F);
        GL11.glPopMatrix();
    }

    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float fl)
    {
        this.renderTileEntityAt((mForgeTE)te, x, y, z, fl);
    }
}
