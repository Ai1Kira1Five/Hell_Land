package com.Arteman.HellLand.energy;

import net.minecraft.nbt.NBTTagCompound;

public class Storage implements EnergyIntsInterface{
	
	protected int energy;
	protected int capacity;
	protected int maxReceive;
	protected int maxExtract;
	
	public Storage(int capacity){
		this(capacity, capacity, capacity);
	}
	
	public Storage(int capacity, int maxTransfer) {
		this(capacity, maxTransfer, maxTransfer);
	}
	
	public Storage(int capacity, int maxReceive, int maxExtract) {
		this.capacity = capacity;
		this.maxReceive = maxReceive;
		this.maxExtract = maxExtract;
	}
	
	public Storage readFromNBT(NBTTagCompound nbt) {

		this.energy = nbt.getInteger("Energy");

		if (energy > capacity) {
			energy = capacity;
		}
		return this;
	}

	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {

		if (energy < 0) {
			energy = 0;
		}
		nbt.setInteger("Energy", energy);
		return nbt;
	}
	
	public void setCapacity(int capacity) {

		this.capacity = capacity;

		if (energy > capacity) {
			energy = capacity;
		}
	}
	
	public void setMaxTransfer(int maxTransfer) {

		setMaxReceive(maxTransfer);
		setMaxExtract(maxTransfer);
	}

	public void setMaxReceive(int maxReceive) {

		this.maxReceive = maxReceive;
	}

	public void setMaxExtract(int maxExtract) {

		this.maxExtract = maxExtract;
	}
	
	public int getMaxReceive() {

		return maxReceive;
	}

	public int getMaxExtract() {

		return maxExtract;
	}
	
	public void setEnergyStored(int energy) {

		this.energy = energy;

		if (this.energy > capacity) {
			this.energy = capacity;
		} else if (this.energy < 0) {
			this.energy = 0;
		}
	}
	
	@Override
	public int receiveEnergy(int maxRec) {
		int energyReceived = Math.min(capacity - energy, Math.min(this.maxReceive, maxReceive));
		return energyReceived;
	}
	
	@Override
	public int extractEnergy(int maxExtr) {
		int energyExtracted = Math.min(energy, Math.min(this.maxExtract, maxExtract));
		return energyExtracted;
	}
	
	@Override
	public int getEnergyStored() {
		return energy;
	}
	@Override
	public int getMaxEnergyStored() {
		return capacity;
	}

}
