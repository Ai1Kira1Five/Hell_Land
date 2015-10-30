package com.Arteman.HellLand.utils.interfaces;

import com.Arteman.HellLand.utils.helpers.ConnectionHelper;

import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;

public abstract interface IBlockConnectionReceiver {
	public abstract boolean doesAcceptConnectionSide(IBlockAccess paramIBlockAccess, int paramInt1, int paramInt2, int paramInt3, ForgeDirection paramForgeDirection, int paramInt4, int paramInt5, int paramInt6, ConnectionHelper.ConnectionTypes paramConnectionTypes, int paramInt7);

	public abstract boolean doesAcceptConnectionCorner(IBlockAccess paramIBlockAccess, int paramInt1, int paramInt2, int paramInt3, ForgeDirection paramForgeDirection1, ForgeDirection paramForgeDirection2, int paramInt4, int paramInt5, int paramInt6, ConnectionHelper.ConnectionTypes paramConnectionTypes, int paramInt7);
}
