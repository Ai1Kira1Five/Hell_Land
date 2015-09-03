package com.Arteman.HellLand.blocks.machines;

import com.Arteman.HellLand.HellLand;
import com.Arteman.HellLand.tileentity.alchemicalTableTE;
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
import net.minecraft.world.World;

public class alchemicalTable extends TEBlockHell{

    public IIcon[] blockIcons = new IIcon[3];

    public alchemicalTable(String name, CreativeTabs creativeTabs) {
        super(name, creativeTabs);
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        this.blockIcons[0] = iconRegister.registerIcon(HellLand.MODID + ":" + "alchemicalTableTop");
        this.blockIcons[1] = iconRegister.registerIcon(HellLand.MODID + ":" + "alchemicalTableSide");
        this.blockIcons[2] = iconRegister.registerIcon(HellLand.MODID + ":" + "bloodPlanks");
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metadata) {
        return (side==1)?this.blockIcons[0]:(side==0)?this.blockIcons[2]:this.blockIcons[1];
    }
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityplayer, ItemStack itemstack) {
        if (itemstack.hasDisplayName()) {
            ((alchemicalTableTE) world.getTileEntity(x, y, z)).setGuiDisplayName(itemstack.getDisplayName());
        }
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
}
