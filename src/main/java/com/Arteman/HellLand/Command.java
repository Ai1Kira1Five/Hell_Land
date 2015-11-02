package com.Arteman.HellLand;

import java.util.HashMap;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public enum Command {
	craftClear(2, true),  craftSwirl(3, true),  craftBalance(4, true),  craftOpen(5, true),  craftFill(11, true),  glueRightClick(12, false),  glueLeftClick(13, false),  glueSelectNone(14, false);
	  
	  public byte id;
	  
	  static class Names
	  {
	    static HashMap<Byte, Command> map = new HashMap();
	  }
	  
	  boolean executeLocally = false;
	  public Command reverse = this;
	  
	  private Command(int id)
	  {
	    this.id = ((byte)id);
	    Names.map.put(Byte.valueOf(this.id), this);
	  }
	  
	  private Command(int id, boolean executeLocally)
	  {
	    this(id);
	  }
	  
	  void setReverse(Command rev)
	  {
	    rev.reverse = this;
	    this.reverse = rev;
	  }
	  
	  public static void fromNetwork(EntityPlayer player, byte s, byte arg)
	  {
	    Command c = (Command)Names.map.get(Byte.valueOf(s));
	    if (c == null)
	    {
	    	HellLand.logWarning("Received invalid command #" + s, new Object[0]);
	      return;
	    }
	    c.call(player, arg);
	  }
	  
	  public void call(EntityPlayer player)
	  {
	    call(player, (byte)0);
	  }
	  
	  public void call(EntityPlayer player, byte arg)
	  {
	    if (player == null) {
	      return;
	    }
	    if (player.worldObj.isRemote)
	    {
	    	HellLand.network.sendCommand(player, this, arg);
	      if (!this.executeLocally) {
	        return;
	      }
	    }
	    switch (this)
	    {
	    case glueLeftClick: 
	    case glueRightClick: 
	    case glueSelectNone: 
	      if ((player instanceof EntityPlayerMP)) {
	    	  
	      }
	      break;
	    default: 
	      HellLand.logWarning("Command " + this + " is missing handler", new Object[0]);
	    }
	  }
}
