package com.Arteman.HellLand.blocks.machines;

import com.Arteman.HellLand.HellLand;
import com.Arteman.HellLand.ModBlocks;
import com.Arteman.HellLand.tileentity.hellOvenTE;
import com.Arteman.HellLand.utils.TEBlockHell;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.Random;

public class hellOven extends TEBlockHell {
    private final boolean isActive;

    @SideOnly(Side.CLIENT)
    private IIcon iconFront;
    private IIcon iconTop;

    public hellOven(String name, CreativeTabs creativeTabs, boolean isActive) {
        super(name, creativeTabs);
        this.isActive = isActive;
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        this.blockIcon = iconRegister.registerIcon(HellLand.MODID + ":" + "hellOvenSide");
        this.iconFront = iconRegister.registerIcon(HellLand.MODID + ":" + (this.isActive ? "hellOvenFrontOn" : "hellOvenFrontOff"));
        this.iconTop = iconRegister.registerIcon(HellLand.MODID + ":" + "hellOvenTop");
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metadata) {
        return side == 1 ? this.iconTop : ((side == 0 ? this.iconTop : (side != metadata ? this.blockIcon : this.iconFront)));
    }

    private void setDefaultDirection(World world, int x, int y, int z) {
        if (!world.isRemote) {
            Block b1 = world.getBlock(x, y, z - 1);
            Block b2 = world.getBlock(x, y, z + 1);
            Block b3 = world.getBlock(x - 1, y, z);
            Block b4 = world.getBlock(x + 1, y, z);

            byte b0 = 3;

            if (b1.func_149730_j() && !b2.func_149730_j()) {
                b0 = 3;
            }
            if (b2.func_149730_j() && !b1.func_149730_j()) {
                b0 = 2;
            }
            if (b3.func_149730_j() && !b4.func_149730_j()) {
                b0 = 5;
            }
            if (b4.func_149730_j() && !b3.func_149730_j()) {
                b0 = 4;
            }

            world.setBlockMetadataWithNotify(x, y, z, b0, 2);
        }
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
            FMLNetworkHandler.openGui(player, HellLand.instance, HellLand.guiIDHellOven, world, x, y, z);
        }
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        return new hellOvenTE();
    }


    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random random) {
        if (this.isActive) {
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

    public static void updateHellOvenBlockState(boolean active, World worldObj, int xCoord, int yCoord, int zCoord) {
        int i = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);

        TileEntity tileentity = worldObj.getTileEntity(xCoord, yCoord, zCoord);
        keepInventory = true;

        if (active) {
            worldObj.setBlock(xCoord, yCoord, zCoord, ModBlocks.hellOvenActive);
        } else {
            worldObj.setBlock(xCoord, yCoord, zCoord, ModBlocks.hellOvenIdle);
        }

        keepInventory = false;

        worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, i, 2);

        if (tileentity != null) {
            tileentity.validate();
            worldObj.setTileEntity(xCoord, yCoord, zCoord, tileentity);
        }
    }
}
