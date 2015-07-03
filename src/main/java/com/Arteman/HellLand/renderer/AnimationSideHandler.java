package com.Arteman.HellLand.renderer;

import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;
import codechicken.lib.colour.Colour;

public interface AnimationSideHandler 
{
	Colour getItemColor(int meta, ForgeDirection side);
	
	int getAnimationIndex(int meta, ForgeDirection side);
	
	int getAnimationBrightness(int meta, ForgeDirection side);

	Colour getColor(IBlockAccess iBlockAccess, int x, int y, int z, ForgeDirection side);

	int getAnimationIndex(IBlockAccess iBlockAccess, int x, int y, int z, ForgeDirection side);

	int getAnimationBrightness(IBlockAccess iBlockAccess, int x, int y, int z, ForgeDirection side);
}
