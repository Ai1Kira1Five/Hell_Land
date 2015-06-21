package com.Arteman.HellLand.blocks.wire;

import com.Arteman.HellLand.HellLand;
import com.Arteman.HellLand.tileentity.TileEntityWire;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class WireOne extends BlockContainer
{
	
	float pixel = 1F/16F;
	
	public WireOne()
	{
		super(Material.glass);
		
		this.setBlockTextureName(HellLand.MODID + ":" + "WireOne");
		this.setBlockBounds(11*pixel/2, 11*pixel/2, 11*pixel/2, 1-11*pixel/2, 1-11*pixel/2, 1-11*pixel/2);
		this.useNeighborBrightness=true;
	}
	
	/*@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		this.blockIcon = iconRegister.registerIcon(HellLand.MODID + ":" + "WireOne");
	}
	*/
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
	{
		TileEntityWire wire = (TileEntityWire)world.getTileEntity(x, y, z);

		if(wire != null)
		{
			float minX = 11*pixel/2-(wire.connections[5] !=null?(11*pixel/2) :0);
			float minY = 11*pixel/2-(wire.connections[1] !=null?(11*pixel/2) :0);
			float minZ = 11*pixel/2-(wire.connections[2] !=null?(11*pixel/2) :0);
			float maxX = 1-11*pixel/2+(wire.connections[3] !=null?(11*pixel/2) :0);
			float maxY = 1-11*pixel/2+(wire.connections[0] !=null?(11*pixel/2) :0);
			float maxZ = 1-11*pixel/2+(wire.connections[4] !=null?(11*pixel/2) :0);
			
			this.setBlockBounds(minX, minY, minZ, maxX, maxY, maxZ);
		}
		return AxisAlignedBB.getBoundingBox(x+this.minX, y+this.minY, z+this.minZ, x+this.maxX, y+this.maxY, z+this.maxZ);
	}
	
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z)
	{
		TileEntityWire wire = (TileEntityWire)world.getTileEntity(x, y, z);

		if(wire != null)
		{
			//System.out.println(wire.connections[2]);
			float minX = 11*pixel/2-(wire.connections[5] !=null?(11*pixel/2) :0);
			float minY = 11*pixel/2-(wire.connections[1] !=null?(11*pixel/2) :0);
			float minZ = 11*pixel/2-(wire.connections[2] !=null?(11*pixel/2) :0);
			float maxX = 1-11*pixel/2+(wire.connections[3] !=null?(11*pixel/2) :0);
			float maxY = 1-11*pixel/2+(wire.connections[0] !=null?(11*pixel/2) :0);
			float maxZ = 1-11*pixel/2+(wire.connections[4] !=null?(11*pixel/2) :0);
			
			this.setBlockBounds(minX, minY, minZ, maxX, maxY, maxZ);
		}
		return AxisAlignedBB.getBoundingBox(x+this.minX, y+this.minY, z+this.minZ, x+this.maxX, y+this.maxY, z+this.maxZ);
	}
	
	public int getRenderType()
	{
		return -1;
	}
	
	public boolean isOpaqueCube()
	{
		return false;
	}
	
	public boolean renderAsNormalBlock()
	{
		return false;
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int var1)
	{
		return new TileEntityWire();
	}
}
