package com.Arteman.HellLand.blocks.machines;

import com.Arteman.HellLand.tileentity.alchemicalTableTE;
import com.Arteman.HellLand.utils.TEBlockHell;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.world.World;

import java.util.Random;

public class alchemicalTable extends TEBlockHell{

    public alchemicalTable(String name, CreativeTabs creativeTabs) {
        super(name, new alchemicalTableTE(), creativeTabs);
    }

    public void randomDisplayTick(World world, int x, int y, int z, Random random) {
        if (((alchemicalTableTE)this.tileEntity).isProcessing()) {
            float x1 = (float) x + 0.5f;
            float y1 = (float) y + 0.5f;
            float z1 = (float) z + 0.5F;

            float f = 0.52F;
            float f1 = random.nextFloat() * 0.6F - 0.3F;
                world.spawnParticle("fireworkSpark", (double) x1 - f, (double) (y1)+f1, (double) (z1 + f1), 0D, 0D, 0D);
                world.spawnParticle("fireworkSpark", (double) x1 - f, (double) (y1)+f, (double) (z1 + f1), 0D, 0D, 0D);
                world.spawnParticle("fireworkSpark", (double) x1 + f, (double) (y1)-f1, (double) (z1 + f1), 0D, 0D, 0D);
                world.spawnParticle("fireworkSpark", (double) x1 + f, (double) (y1)-f, (double) (z1 + f1), 0D, 0D, 0D);
        }
    }
}
