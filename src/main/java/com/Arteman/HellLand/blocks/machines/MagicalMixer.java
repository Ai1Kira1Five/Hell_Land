package com.Arteman.HellLand.blocks.machines;

import com.Arteman.HellLand.HellLand;
import com.Arteman.HellLand.tileentity.TileEntityMMixer;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.util.Random;

public class MagicalMixer extends BlockContainer {
    private Random rand = new Random();
    private static final String __OBFID = "CL_00000207";

    public MagicalMixer() {
        super(Material.iron);
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
}
