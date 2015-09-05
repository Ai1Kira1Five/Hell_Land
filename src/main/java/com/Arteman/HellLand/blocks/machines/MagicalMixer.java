package com.Arteman.HellLand.blocks.machines;

import com.Arteman.HellLand.HellLand;
import com.Arteman.HellLand.tileentity.TileEntityMMixer;
import com.Arteman.HellLand.utils.TEBlockHell;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public class MagicalMixer extends TEBlockHell {

    public MagicalMixer(String name, CreativeTabs creativeTabs) {
        super(name, creativeTabs);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        return new TileEntityMMixer();
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        if (world.isRemote) {
            FMLNetworkHandler.openGui(player, HellLand.instance, HellLand.guiIDMMixer, world, x, y, z);
        }
        return true;
    }

    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random random) {
        if (isActive((IBlockAccess)world,x,y,z)) {
            int direction = world.getBlockMetadata(x, y, z);

            float x1 = (float) x + 0.5F;
            float y1 = (float) y + random.nextFloat();
            float z1 = (float) z + 0.5F;

            float f = 0.52F;
            float f1 = random.nextFloat() * 0.6F - 0.3F;

            if (direction == 4) {
                world.spawnParticle("smoke", (double) x1 - f, (double) (y1), (double) (z1 + f1), 0D, 0D, 0D);
                world.spawnParticle("flame", (double) x1 - f, (double) (y1), (double) (z1 + f1), 0D, 0D, 0D);
            }

            if (direction == 5) {
                world.spawnParticle("smoke", (double) x1 + f, (double) (y1), (double) (z1 + f1), 0D, 0D, 0D);
                world.spawnParticle("flame", (double) x1 + f, (double) (y1), (double) (z1 + f1), 0D, 0D, 0D);
            }

            if (direction == 2) {
                world.spawnParticle("smoke", (double) x1 + f1, (double) (y1), (double) (z1 - f), 0D, 0D, 0D);
                world.spawnParticle("flame", (double) x1 + f1, (double) (y1), (double) (z1 - f), 0D, 0D, 0D);
            }

            if (direction == 3) {
                world.spawnParticle("smoke", (double) x1 + f1, (double) (y1), (double) (z1 + f), 0D, 0D, 0D);
                world.spawnParticle("flame", (double) x1 + f1, (double) (y1), (double) (z1 + f), 0D, 0D, 0D);
            }
        }
    }
    
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityplayer, ItemStack itemstack){
    	if (itemstack.hasDisplayName()){
    		((TileEntityMMixer) world.getTileEntity(x, y, z)).setGuiDisplayName(itemstack.getDisplayName());
    	}
    }

    @Override
    public String getSideIcon(boolean isActive) {
        return "mmSide";
    }
    @Override
    public String getTopIcon(boolean isActive) {
        return "hellOvenTop";
    }
    @Override
    public String getFrontIcon(boolean isActive) {
        return isActive ? "mmActive" : "mmIdle";
    }
}
