package com.Arteman.HellLand.energy;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TEEnergyHandler extends TileEntity implements HandlerEnergyInterface{
	
	protected Storage storage = new Storage(666000);
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {

		super.readFromNBT(nbt);
		storage.readFromNBT(nbt);
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {

		super.writeToNBT(nbt);
		storage.writeToNBT(nbt);
	}
	
	@Override
	public boolean canConnectEnergy(ForgeDirection from) {

		return true;
	}
	
	@Override
	public int receiveEnergy(ForgeDirection from, int maxReceive) {

		return storage.receiveEnergy(maxReceive);
	}
	
	@Override
	public int extractEnergy(ForgeDirection from, int maxExtract) {

		return storage.extractEnergy(maxExtract);
	}
	
	@Override
	public int getEnergyStored(ForgeDirection from) {

		return storage.getEnergyStored();
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection from) {

		return storage.getMaxEnergyStored();
	}
}
