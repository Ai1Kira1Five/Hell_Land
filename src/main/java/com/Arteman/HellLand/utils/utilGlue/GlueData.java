package com.Arteman.HellLand.utils.utilGlue;

import java.util.ArrayList;
import java.util.WeakHashMap;
import java.io.File;

import org.apache.commons.lang3.ArrayUtils;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;

public class GlueData<Coord> extends WorldSavedData{
	int dimensionId;
	
	 int[] coords = new int[0];
	 int change_counts = 0;
	 WeakHashMap<Entity, Integer> player_updates = new WeakHashMap();
	 int last_traced_index = -1;
	 static final String hl_glue = "hl_glue";
	
	public GlueData(String dataName){
		super(dataName);
	}
	
	public void readFromNBT(NBTTagCompound tag){
	    if (!tag.hasKey("dimensionId")){
	      return;
	    }
	    this.dimensionId = tag.getInteger("dimensionId");
	    this.coords = tag.getIntArray("coordData");
	}
	  
	public void writeToNBT(NBTTagCompound tag){
	    tag.setString("mapname", this.mapName);
	    tag.setInteger("dimensionId", this.dimensionId);
	    tag.setIntArray("coordData", this.coords);
	}
	
	public void setDirty(boolean dirty){
	    super.setDirty(dirty);
	    if (dirty){
	      this.change_counts += 1;
	    }
	}
	
	boolean isPlayerOutOfDate(Entity player)
	{
	   if (!(player instanceof EntityPlayer)) {
	      return false;
	   }
	   Integer update_count = (Integer)this.player_updates.get(player);
	   if ((update_count == null) || (update_count.intValue() != this.change_counts))
	   {
	     this.player_updates.put(player, Integer.valueOf(this.change_counts));
	     return true;
	   }
	   return false;
	}
	  
	void wipe(ItemStack is, World world){
	   this.coords = new int[0];
	   this.dimensionId = 0;
	   if (!is.hasDisplayName()){
	     deleteDataFile(world);
	     is.setItemDamage(0);
	   }
	   else
	   {
	     markDirty();
	   }
	}
	  
	static String getGlueName(ItemStack is){
	    return "hl_glue_" + is.getItemDamage();
	}
	  
	static GlueData getGooData(ItemStack is, World world){
		GlueData data = (GlueData)world.loadItemData(GlueData.class, getGlueName(is));
/*ошибочка где-то*/	    if ((data == null) && (!world.isClient)){
	      int unique_id = world.getUniqueDataId("hl_glue");
	      if (unique_id == 0){
	        unique_id = world.getUniqueDataId("hl_glue");
	      }
	      is.setItemDamage(unique_id);
	      String name = getGlueName(is);
	      data = new GlueData(name);
	      data.markDirty();
	      world.setItemData(name, data);
	   } 
	   return data;
	}
	  
	static GlueData getNullGlueData(ItemStack is, World world){
	    if (is == null) {
	      return null;
	    }
	    if (!(is.getItem() instanceof ItemGlue)) {
	      return null;
	    }
	    if (is.getItemDamage() == 0) {
	      return null;
	    }
	    return (GlueData)world.loadItemData(GlueData.class, getGlueName(is));
	  }
	  
	  private void deleteDataFile(World world)
	  {
	    File file = world.getSaveHandler().getMapFileFromName(this.mapName);
	    if ((file != null) && (file.exists())) {
	      file.delete();
	    }
	    world.mapStorage.loadedDataList.remove(this);
	    world.mapStorage.loadedDataMap.remove(this);
	  }
	  
	  void removeIndices(ArrayList<Integer> indices, ItemStack is, World world)
	  {
	    int[] all = new int[indices.size()];
	    int i = 0;
	    for (Integer index : indices) {
	      all[(i++)] = index.intValue();
	    }
	    this.coords = ArrayUtils.removeAll(this.coords, all);
	    if (this.coords.length == 0) {
	      wipe(is, world);
	    } else {
	      markDirty();
	    }
	  }
	  
	  boolean checkWorld(EntityPlayer player, Coord complainAt)
	  {
	    if (this.coords.length == 0) {
	      return false;
	    }
	    if (this.dimensionId == player.worldObj.provider.dimensionId) {
	      return false;
	    }
	    return true;
	  }

}
