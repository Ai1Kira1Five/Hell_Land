package com.Arteman.HellLand.energy;

import net.minecraftforge.common.util.ForgeDirection;

public interface EnergyProviderInterface extends EnergyConnectInterface{
	
	int extractEnergy(ForgeDirection from, int maxExtract);
	
	int getEnergyStored(ForgeDirection from);
	
	int getMaxEnergyStored(ForgeDirection from);
}
