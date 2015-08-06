package com.Arteman.HellLand.items;

import com.Arteman.HellLand.HellLandCore;
import com.Arteman.HellLand.utils.ItemHell;

import com.Arteman.HellLand.utils.NBTHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class Bag extends ItemHell
{

	public Bag(String name, CreativeTabs creativeTabs, boolean hasType, int maxMeta)
	{
		super(name, creativeTabs, hasType, maxMeta);
        this.setMaxStackSize(1);
	}

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer)
    {
        if (!world.isRemote)
        {
            if (itemStack.stackTagCompound == null)
            {
                itemStack.setTagCompound(new NBTTagCompound());
            }
            NBTHelper.setUUID(itemStack);
            NBTHelper.setBoolean(itemStack, "bagGuiOpen", true);
            entityPlayer.openGui(HellLandCore.instance, HellLandCore.guiIDBag, entityPlayer.worldObj, (int) entityPlayer.posX, (int) entityPlayer.posY, (int) entityPlayer.posZ);
        }

        return itemStack;
    }

}
