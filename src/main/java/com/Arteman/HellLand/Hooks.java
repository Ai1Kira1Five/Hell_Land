package com.Arteman.HellLand;

import com.Arteman.HellLand.utils.IInjectable;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.common.Loader;

public class Hooks 
{
	public static final Hooks INSTANCE;
	
	static {
		Hooks i = null;
		if (Loader.isModLoaded("HellLand|Compat")) 
		{
			try 
			{
				i = (Hooks) Hooks.class.getClassLoader().loadClass("HellLand.compat.HooksImpl").newInstance();
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}

		if (i == null) 
		{
			i = new Hooks();
		}

		INSTANCE = i;
	}
	
	public Hooks() {}
	
	public IInjectable getInjectableWrapper(TileEntity tile, ForgeDirection side) 
	{
		return null;
	}

	public Block getVisualBlock(IBlockAccess world, int x, int y, int z, ForgeDirection side) 
	{
		return null;
	}
	
	public int getVisualMeta(IBlockAccess world, int x, int y, int z, ForgeDirection side) 
	{
		return -1;
	}
	
	public Block getBlock(Class<? extends Block> klazz) 
	{
		Block block = null;

		if (Loader.isModLoaded("HellLand|Compat")) 
		{
			try {
				block = (Block) Hooks.class.getClassLoader().loadClass(klazz.getName() + "Compat").newInstance();
			} 
			catch (ClassNotFoundException e) 
			{
				
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}

		if (block == null)
		{
			try 
			{
				block = klazz.newInstance();
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}

		return block;
	}
	
	public Class<? extends TileEntity> getTile(Class<? extends TileEntity> klazz) 
	{
		Class<? extends TileEntity> tileClass = klazz;

		if (Loader.isModLoaded("HellLand|Compat")) 
		{
			try 
			{
				tileClass = (Class<? extends TileEntity>) Hooks.class.getClassLoader().loadClass(klazz.getName() + "Compat");
			}
			catch (ClassNotFoundException e) 
			{
				tileClass = klazz;
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
		}

		return tileClass;
	}

	public Object getEnergyProvider(TileEntity tile) 
	{
		return tile;
	}
}
