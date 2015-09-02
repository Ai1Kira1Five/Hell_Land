package com.Arteman.HellLand.energy;

import net.minecraftforge.common.util.ForgeDirection;

public interface EnergyReceiverInterface extends EnergyConnectInterface{
	int receiveEnergy(ForgeDirection from, int maxReceive);
	
	int getEnergyStored(ForgeDirection from);
	
	int getMaxEnergyStored(ForgeDirection from);
}
