package com.Arteman.HellLand.energy;

public interface EnergyIntsInterface {
	//insertion
	int receiveEnergy(int maxRec);
	
	//extraction
	int extractEnergy(int maxExtr);
	
	//current level
	int getEnergyStored();
	
	//maximum
	int getMaxEnergyStored();
}
