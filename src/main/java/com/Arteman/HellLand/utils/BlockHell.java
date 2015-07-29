package com.Arteman.HellLand.utils;

import com.Arteman.HellLand.HellLand;
import com.Arteman.HellLand.proxy.ClientProxy;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockHell extends Block
{
	public BlockHell(Material material)
	{
		super(material);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderType()
	{
		return ClientProxy.renderId;
	}
}
