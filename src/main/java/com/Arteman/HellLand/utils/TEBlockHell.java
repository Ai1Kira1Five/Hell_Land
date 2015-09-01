package com.Arteman.HellLand.utils;

import com.Arteman.HellLand.tileentity.crystalOvenTE;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import java.util.Random;

public abstract class TEBlockHell extends BlockHell implements ITileEntityProvider {

    public static boolean keepInventory;

    public TEBlockHell(String name, CreativeTabs creativeTabs){
        super(name, Material.rock, Block.soundTypeStone, creativeTabs, 1.0f,100.0f,0.0f,false,0);
    }

    @Override
    public void onBlockAdded(World world, int xCoord, int yCoord, int zCoord){
        super.onBlockAdded(world, xCoord, yCoord, zCoord);
    }

    @Override
    public void breakBlock(World world, int xCoord, int yCoord, int zCoord, Block block, int meta) {
        Random rand = new Random();

        if (!keepInventory) {
            IInventory tileentity = (IInventory) world.getTileEntity(xCoord, yCoord, zCoord);

            if (tileentity != null) {
                for (int i = 0; i < tileentity.getSizeInventory(); i++) {
                    ItemStack itemstack = tileentity.getStackInSlot(i);

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
        return tileentity != null ? tileentity.receiveClientEvent(event, args) : false;
    }


    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityplayer, ItemStack itemstack) {
        int l = MathHelper.floor_double((double) (entityplayer.rotationYaw * 4.0F / 360.F) + 0.5D) & 3;

        if (l == 0) {
            world.setBlockMetadataWithNotify(x, y, z, 2, 2);
        }
        if (l == 1) {
            world.setBlockMetadataWithNotify(x, y, z, 5, 2);
        }
        if (l == 2) {
            world.setBlockMetadataWithNotify(x, y, z, 3, 2);
        }
        if (l == 3) {
            world.setBlockMetadataWithNotify(x, y, z, 4, 2);
        }

        if (itemstack.hasDisplayName()) {
            ((crystalOvenTE) world.getTileEntity(x, y, z)).setGuiDisplayName(itemstack.getDisplayName());
        }
    }


}
