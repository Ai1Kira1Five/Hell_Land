package com.Arteman.HellLand;

import com.Arteman.HellLand.fluids.MateriallizedFear;

import net.minecraftforge.fluids.Fluid;

public class ModFluids 
{
	public static Fluid MateriallizedFear;
	
	public final static void init()
	{
		MateriallizedFear = new MateriallizedFear("Materiallized fear");
	}
}
