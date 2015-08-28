package com.Arteman.HellLand.blocks;

import com.Arteman.HellLand.HellLand;
import com.Arteman.HellLand.utils.BlockHell;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

public class BloodWood extends BlockHell {

    public BloodWood(String name) {
        super(name,Material.wood,soundTypeWood,HellLand.HellMCTab,5.0f,50.0f,0.0f,false,0);
        this.setHarvestLevel("axe", 1);
    }

    @SideOnly(Side.CLIENT)
    private IIcon iconTop;

    @SideOnly(Side.CLIENT)
    private IIcon iconDown;

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        this.blockIcon = iconRegister.registerIcon(HellLand.MODID + ":" + "bloodWood");
        this.iconTop = iconRegister.registerIcon(HellLand.MODID + ":" + "bloodWoodTop");
        this.iconDown = iconRegister.registerIcon(HellLand.MODID + ":" + "bloodWoodTop");
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metadata) {
        return side == 1 ? this.iconTop : (side == 1 ? this.iconTop : (side == 0 ? this.iconTop : this.blockIcon));
    }

}
