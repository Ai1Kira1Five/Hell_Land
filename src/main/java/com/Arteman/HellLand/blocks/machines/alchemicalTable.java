package com.Arteman.HellLand.blocks.machines;

import com.Arteman.HellLand.HellLand;
import com.Arteman.HellLand.tileentity.alchemicalTableTE;
import com.Arteman.HellLand.tileentity.tileEntityWithInventory;
import com.Arteman.HellLand.utils.TEBlockHell;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public class alchemicalTable extends TEBlockHell{

    public alchemicalTable(String name, CreativeTabs creativeTabs) {
        super(name, creativeTabs);
    }

    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityplayer, ItemStack itemstack) {
        if (itemstack.hasDisplayName()) {
            ((alchemicalTableTE) world.getTileEntity(x, y, z)).setGuiDisplayName(itemstack.getDisplayName());
        }
    }

    @Override
    public String getSideIcon(boolean isActive) {
        return "alchemicalTableSide";
    }

    @Override
    public String getTopIcon(boolean isActive) {
        return "alchemicalTableTop";
    }

    @Override
    public String getFrontIcon(boolean isActive) {
        return "alchemicalTableSide";
    }

    @Override
    public String getBottomIcon(boolean isActive) {
        return "bloodPlanks";
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
            player.openGui(HellLand.instance, HellLand.guiIDAlchemicalTable, world, x, y, z);
        }
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new alchemicalTableTE();
    }

    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random random) {
        if (this.isActive((IBlockAccess) world, x, y, z)) {
            float x1 = (float) x + random.nextFloat();
            float y1 = (float) y + 1F;
            float z1 = (float) z + random.nextFloat();

            float f1 = random.nextFloat() * 0.6F - 0.3F;

            world.spawnParticle("enchantmenttable", (double) x1 + f1, (double) (y1), (double) (z1 + f1), 0D, 0D, 0D);
            world.spawnParticle("enchantmenttable", (double) x1 - f1, (double) (y1), (double) (z1 - f1), 0D, 0D, 0D);
        }
    }
}
