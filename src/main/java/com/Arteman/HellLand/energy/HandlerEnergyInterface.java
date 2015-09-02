package com.Arteman.HellLand.energy;

import net.minecraftforge.common.util.ForgeDirection;

public interface HandlerEnergyInterface extends EnergyProviderInterface, EnergyReceiverInterface{
	
	//add energy to EnergyProvider
	@Override
	int receiveEnergy(ForgeDirection from, int maxReceive);
	
	//remove energy from EnergyProvider
	@Override
	int extractEnergy(ForgeDirection from, int maxExtract);
	
	//current stored
	@Override
	int getEnergyStored(ForgeDirection from);
	
	//max stored
	@Override
	int getMaxEnergyStored(ForgeDirection from);
}
