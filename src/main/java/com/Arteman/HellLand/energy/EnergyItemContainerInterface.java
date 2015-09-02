package com.Arteman.HellLand.energy;

import net.minecraft.item.ItemStack;

public interface EnergyItemContainerInterface {
	int receiveEnergy(ItemStack container, int maxReceive);
	
	int extractEnergy(ItemStack container, int maxExtract);
	
	int getEnergyStored(ItemStack container);
	
	int getMaxEnergyStored(ItemStack container);
}
