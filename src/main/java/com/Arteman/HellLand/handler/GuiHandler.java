package com.Arteman.HellLand.handler;

import com.Arteman.HellLand.container.ContainerCrystalOven;
import com.Arteman.HellLand.container.ContainerHellOven;
import com.Arteman.HellLand.container.ContainerSoulCrystallizer;
import com.Arteman.HellLand.gui.GuiCrystalOven;
import com.Arteman.HellLand.gui.GuiHellOven;
import com.Arteman.HellLand.gui.GuiSoulCrystallizer;
import com.Arteman.HellLand.tileentity.TileEntityCrystalOven;
import com.Arteman.HellLand.tileentity.TileEntityHellOven;
import com.Arteman.HellLand.tileentity.TileEntitySoulCrystallizer;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler
{
	 @Override
	    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	 {
	        TileEntity entity = world.getTileEntity(x, y, z);

	        if(entity != null)
	        {
	            switch(ID)
	            {
	                case 1:
	                    if(entity instanceof TileEntityHellOven)
	                    {
	                        return new ContainerHellOven(player.inventory, (TileEntityHellOven) entity);
	                    }
	                case 2:
	                	if(entity instanceof TileEntitySoulCrystallizer)
	                	{
	                		return new ContainerSoulCrystallizer(player.inventory, (TileEntitySoulCrystallizer) entity);
	                	}
	                case 3:
	                	if(entity instanceof TileEntityCrystalOven)
	                	{
	                		return new ContainerCrystalOven(player.inventory, (TileEntityCrystalOven) entity);
	                	}
	                    return null;
	            }
	        }
	        return null;
	    }

	    @Override
	    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	    {
	        TileEntity entity = world.getTileEntity(x, y, z);

	        if(entity != null)
	        {
	            switch(ID)
	            {
	                case 1:
	                	if(entity instanceof TileEntityHellOven)
	                	{
	                		return new GuiHellOven(player.inventory, (TileEntityHellOven) entity);
	                	}
	                case 2:
	                	if(entity instanceof TileEntitySoulCrystallizer)
		                {
		                    return new GuiSoulCrystallizer(player.inventory, (TileEntitySoulCrystallizer) entity);
		                }
	                case 3:
	                	if(entity instanceof TileEntityCrystalOven)
	                	{
	                		return new GuiCrystalOven(player.inventory, (TileEntityCrystalOven) entity);
	                	}
	                return null;
	            }
	        }
	        return null;
	    }
}
