package com.Arteman.HellLand.blocks.machines;

import com.Arteman.HellLand.HellLand;
import com.Arteman.HellLand.tileentity.crystalOvenTE;
import com.Arteman.HellLand.tileentity.tileEntityWithInventory;
import com.Arteman.HellLand.utils.TEBlockHell;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public class crystalOven extends TEBlockHell {

    public crystalOven(String name, CreativeTabs creativeTabs) {
        super(name, creativeTabs);
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
            FMLNetworkHandler.openGui(player, HellLand.instance, HellLand.guiIDCrystalOven, world, x, y, z);
        }
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        return new crystalOvenTE();
    }

    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random random) {
        if (this.isActive((IBlockAccess) world, x, y, z)) {
            int facing = ((tileEntityWithInventory)world.getTileEntity(x,y,z)).getFacing();

            float x1 = (float) x + 0.5F;
            float y1 = (float) y + random.nextFloat();
            float z1 = (float) z + 0.5F;

            float f = 0.52F;
            float f1 = random.nextFloat() * 0.6F - 0.3F;

            if (facing == 4) {
                world.spawnParticle("smoke", (double) x1 - f, (double) (y1), (double) (z1 + f1), 0D, 0D, 0D);
                world.spawnParticle("flame", (double) x1 - f, (double) (y1), (double) (z1 + f1), 0D, 0D, 0D);
            }

            if (facing == 5) {
                world.spawnParticle("smoke", (double) x1 + f, (double) (y1), (double) (z1 + f1), 0D, 0D, 0D);
                world.spawnParticle("flame", (double) x1 + f, (double) (y1), (double) (z1 + f1), 0D, 0D, 0D);
            }

            if (facing == 2) {
                world.spawnParticle("smoke", (double) x1 + f1, (double) (y1), (double) (z1 - f), 0D, 0D, 0D);
                world.spawnParticle("flame", (double) x1 + f1, (double) (y1), (double) (z1 - f), 0D, 0D, 0D);
            }

            if (facing == 3) {
                world.spawnParticle("smoke", (double) x1 + f1, (double) (y1), (double) (z1 + f), 0D, 0D, 0D);
                world.spawnParticle("flame", (double) x1 + f1, (double) (y1), (double) (z1 + f), 0D, 0D, 0D);
            }

        }
    }
    @Override
    public String getSideIcon(boolean isActive) {
        return "soulCrystallizerSide";
    }

    public String getTopIcon(boolean isActive) { return "soulCrystallizerSide"; }

    @Override
    public String getFrontIcon(boolean isActive) {
        if(isActive){
            return "crystalOvenFrontOn";
        }else{
            return "crystalOvenFrontOff";
        }
    }

}
