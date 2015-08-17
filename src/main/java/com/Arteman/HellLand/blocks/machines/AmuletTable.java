package com.Arteman.HellLand.blocks.machines;

import com.Arteman.HellLand.HellLand;
import com.Arteman.HellLand.utils.BlockHell;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class AmuletTable extends BlockHell {

    public AmuletTable(String name) {
        super(name,Material.wood,soundTypeWood,HellLand.HellMCTab,3.5f,5.0f,0.0f);
        this.setHarvestLevel("axe", 2);
    }

    @SideOnly(Side.CLIENT)
    private IIcon iconTop;

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        this.blockIcon = iconRegister.registerIcon(HellLand.MODID + ":" + "amuletTableSide");
        this.iconTop = iconRegister.registerIcon(HellLand.MODID + ":" + "amuletTableTop");
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metadata) {
        return side == 1 ? this.iconTop : this.blockIcon;
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int q, float a, float c) {
        if (!player.isSneaking()) {
            player.openGui(HellLand.MODID, HellLand.guiIDAmuletTable, world, x, y, z);
            return true;
        } else {
            return false;
        }
    }
}
