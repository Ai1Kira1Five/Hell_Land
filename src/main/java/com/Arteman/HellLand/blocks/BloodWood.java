package com.Arteman.HellLand.blocks;

import com.Arteman.HellLand.HellLandCore;
import com.Arteman.HellLand.utils.BlockHell;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

public class BloodWood extends BlockHell {

    public BloodWood(String name) {
        super(name,Material.wood,soundTypeWood,HellLandCore.HellMCTab,5.0f,50.0f,0.0f);
        this.setHarvestLevel("axe", 1);
    }

    @SideOnly(Side.CLIENT)
    private IIcon iconTop;

    @SideOnly(Side.CLIENT)
    private IIcon iconDown;

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        this.blockIcon = iconRegister.registerIcon(HellLandCore.MODID + ":" + "bloodWood");
        this.iconTop = iconRegister.registerIcon(HellLandCore.MODID + ":" + "bloodWoodTop");
        this.iconDown = iconRegister.registerIcon(HellLandCore.MODID + ":" + "bloodWoodTop");
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metadata) {
        return side == 1 ? this.iconTop : (side == 1 ? this.iconTop : (side == 0 ? this.iconTop : this.blockIcon));
    }

}
