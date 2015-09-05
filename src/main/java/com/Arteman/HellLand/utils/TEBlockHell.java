package com.Arteman.HellLand.utils;

import com.Arteman.HellLand.HellLand;
import com.Arteman.HellLand.tileentity.tileEntityWithInventory;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import sun.java2d.pipe.RenderingEngine;

import javax.swing.*;
import java.util.Random;

public abstract class TEBlockHell extends BlockHell implements ITileEntityProvider {

    public static boolean keepInventory;
    @SideOnly(Side.CLIENT)
    private IIcon[][] iconBuffer = new IIcon[1][12];
    public static int renderId;
    public int[][] sideAndFacingToSpriteOffset = new int[][] {
            { 3, 2, 0, 0, 0, 0 },
            { 2, 3, 1, 1, 1, 1 },
            { 1, 1, 3, 2, 5, 4 },
            { 0, 0, 2, 3, 4, 5 },
            { 4, 5, 4, 5, 3, 2 },
            { 5, 4, 5, 4, 2, 3 }
    };

    public TEBlockHell(String name, CreativeTabs creativeTabs){
        super(name, Material.rock, Block.soundTypeStone, creativeTabs, 1.0f,100.0f,0.0f,false,0);
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        String side = getSideIcon(false);
        this.iconBuffer[0][0] = iconRegister.registerIcon(HellLand.MODID+":"+getBottomIcon(false));
        this.iconBuffer[0][1] = iconRegister.registerIcon(HellLand.MODID+":"+getTopIcon(false));
        this.iconBuffer[0][2] = iconRegister.registerIcon(HellLand.MODID+":"+side);
        this.iconBuffer[0][3] = iconRegister.registerIcon(HellLand.MODID+":"+getFrontIcon(false));
        this.iconBuffer[0][4] = iconRegister.registerIcon(HellLand.MODID+":"+side);
        this.iconBuffer[0][5] = iconRegister.registerIcon(HellLand.MODID+":"+side);

        side = getSideIcon(true);
        this.iconBuffer[0][6] = iconRegister.registerIcon(HellLand.MODID+":"+getBottomIcon(true));
        this.iconBuffer[0][7] = iconRegister.registerIcon(HellLand.MODID+":"+getTopIcon(true));
        this.iconBuffer[0][8] = iconRegister.registerIcon(HellLand.MODID+":"+side);
        this.iconBuffer[0][9] = iconRegister.registerIcon(HellLand.MODID+":"+getFrontIcon(true));
        this.iconBuffer[0][10] = iconRegister.registerIcon(HellLand.MODID+":"+side);
        this.iconBuffer[0][11] = iconRegister.registerIcon(HellLand.MODID+":"+side);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(IBlockAccess worldObj, int xCoord, int yCoord, int zCoord, int side) {
        TileEntity te = worldObj.getTileEntity(xCoord, yCoord, zCoord);
        int facing = 0;
        if(te instanceof tileEntityWithInventory) {
            tileEntityWithInventory me = (tileEntityWithInventory) te;
            facing = me.getFacing();
        }
        if(this.isActive(worldObj, xCoord, yCoord, zCoord)) {
            return iconBuffer[0][this.sideAndFacingToSpriteOffset[side][facing] + 6];
        } else {
            return iconBuffer[0][this.sideAndFacingToSpriteOffset[side][facing]];
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int side, int meta) {
        return this.iconBuffer[0][side];
    }

    public boolean isActive(IBlockAccess worldObj, int xCoord, int yCoord, int zCoord){
        TileEntity te = worldObj.getTileEntity(xCoord,yCoord,zCoord);
        return (te instanceof tileEntityWithInventory) && ((tileEntityWithInventory) te).isActive();
    }

    @Override
    public void onBlockAdded(World world, int xCoord, int yCoord, int zCoord){
        super.onBlockAdded(world, xCoord, yCoord, zCoord);
    }

    @Override
    public void breakBlock(World world, int xCoord, int yCoord, int zCoord, Block block, int meta) {
        Random rand = new Random();

        if (!keepInventory) {
            TileEntity tileEntity = world.getTileEntity(xCoord, yCoord, zCoord);

            if (tileEntity != null && tileEntity instanceof tileEntityWithInventory) {
                tileEntityWithInventory te = (tileEntityWithInventory)tileEntity;
                for (int i = 0; i < te.getSizeInventory(); i++) {
                    ItemStack itemstack = te.getStackInSlot(i);

                    if (itemstack != null) {
                        float f = rand.nextFloat() * 0.8F + 0.1F;
                        float f1 = rand.nextFloat() * 0.8F + 0.1F;
                        float f2 = rand.nextFloat() * 0.8F + 0.1F;

                        while (itemstack.stackSize > 0) {
                            int j = rand.nextInt(21) + 10;

                            if (j > itemstack.stackSize) {
                                j = itemstack.stackSize;
                            }

                            itemstack.stackSize -= j;

                            EntityItem item = new EntityItem(world, (double) ((float) xCoord + f), (double) ((float) yCoord + f1), (double) ((float) zCoord + f2), new ItemStack(itemstack.getItem(), j, itemstack.getItemDamage()));

                            if (itemstack.hasTagCompound()) {
                                item.getEntityItem().setTagCompound((NBTTagCompound) itemstack.getTagCompound().copy());
                            }

                            world.spawnEntityInWorld(item);
                        }
                    }
                }

                world.func_147453_f(xCoord, yCoord, zCoord, block);
            }
        }
        super.breakBlock(world, xCoord, yCoord, zCoord, block, meta);
    }

    public boolean onBlockEventReceived(World world, int xCoord, int yCoord, int zCoord, int event, int args){
        super.onBlockEventReceived(world, xCoord, yCoord, zCoord, event, args);
        TileEntity tileentity = world.getTileEntity(xCoord, yCoord, zCoord);
        return tileentity != null && tileentity.receiveClientEvent(event, args);
    }


    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityplayer, ItemStack itemstack) {
        int l = MathHelper.floor_double((double) (entityplayer.rotationYaw * 4.0F / 360.F) + 0.5D) & 3;

        tileEntityWithInventory te = (tileEntityWithInventory)world.getTileEntity(x,y,z);
        if (l == 0) {
            te.setFacing(2);
        }
        if (l == 1) {
            te.setFacing(5);
        }
        if (l == 2) {
            te.setFacing(3);
        }
        if (l == 3) {
            te.setFacing(4);
        }
        if (itemstack.hasDisplayName()) {
            te.setGuiDisplayName(itemstack.getDisplayName());
        }
    }

    public abstract String getSideIcon(boolean isActive);
    public abstract String getFrontIcon(boolean isActive);
    public abstract String getTopIcon(boolean isActive);
    public String getBottomIcon(boolean isActive){
        return getSideIcon(isActive);
    }


}
