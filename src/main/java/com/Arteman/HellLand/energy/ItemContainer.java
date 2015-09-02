package com.Arteman.HellLand.energy;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import cofh.api.energy.IEnergyContainerItem;
import cofh.api.energy.ItemEnergyContainer;

public class ItemContainer extends Item implements EnergyItemContainerInterface{
	
	protected int capacity;
	protected int maxReceive;
	protected int maxExtract;

	public ItemContainer() {

	}
	
	public ItemContainer(int capacity) {

		this(capacity, capacity, capacity);
	}
	
	public ItemContainer(int capacity, int maxTransfer) {

		this(capacity, maxTransfer, maxTransfer);
	}

	public ItemContainer(int capacity, int maxReceive, int maxExtract) {

		this.capacity = capacity;
		this.maxReceive = maxReceive;
		this.maxExtract = maxExtract;
	}

	public ItemContainer setCapacity(int capacity) {

		this.capacity = capacity;
		return this;
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

	@Override
	public int receiveEnergy(ItemStack container, int maxReceive) {

		if (container.stackTagCompound == null) {
			container.stackTagCompound = new NBTTagCompound();
		}
		int energy = container.stackTagCompound.getInteger("Energy");
		int energyReceived = Math.min(capacity - energy, Math.min(this.maxReceive, maxReceive));

		return energyReceived;
	}

	@Override
	public int extractEnergy(ItemStack container, int maxExtract) {

		if (container.stackTagCompound == null || !container.stackTagCompound.hasKey("Energy")) {
			return 0;
		}
		int energy = container.stackTagCompound.getInteger("Energy");
		int energyExtracted = Math.min(energy, Math.min(this.maxExtract, maxExtract));

		return energyExtracted;
	}

	@Override
	public int getEnergyStored(ItemStack container) {

		if (container.stackTagCompound == null || !container.stackTagCompound.hasKey("Energy")) {
			return 0;
		}
		return container.stackTagCompound.getInteger("Energy");
	}

	@Override
	public int getMaxEnergyStored(ItemStack container) {

		return capacity;
	}
}
