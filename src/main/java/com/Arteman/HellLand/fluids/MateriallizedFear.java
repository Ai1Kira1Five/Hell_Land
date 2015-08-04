package com.Arteman.HellLand.fluids;

import net.minecraftforge.fluids.Fluid;

public class MateriallizedFear extends Fluid {
    public static String name = "Materiallized Fear";

    public MateriallizedFear(String name) {
        super(name);
        this.setDensity(10);
        this.setViscosity(3000);
    }

}
