package com.Arteman.HellLand.utils;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;

import com.Arteman.HellLand.HellLand;
import com.google.common.collect.Multimap;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.BaseAttributeMap;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.ai.attributes.ServersideAttributeMap;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;

public class Utils {
	public static <E extends Enum> E shiftEnum(E current, E[] values, int delta){
		int next = current.ordinal() + delta;
	    if (next < 0) {
	      return values[(values.length - 1)];
	    }
	    if (next >= values.length) {
	      return values[0];
	    }
	    return values[next];
	}
	
	public static int getWorldDimension(World world)
	  {
	    return world.provider.dimensionId;
	  }
	  
	  public static World getWorld(int dimensionId)
	  {
	    return DimensionManager.getWorld(dimensionId);
	  }
	  
	  @SideOnly(Side.CLIENT)
	  public static void copyStringToClipboard(String text)
	  {
	    StringSelection stringselection = new StringSelection(text);
	    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringselection, null);
	  }
	  
	  public static <E> ArrayList<E> copyWithoutNull(Collection<E> orig)
	  {
	    ArrayList<E> ret = new ArrayList();
	    if (orig == null) {
	      return ret;
	    }
	    for (E e : orig) {
	      if (e != null) {
	        ret.add(e);
	      }
	    }
	    return ret;
	  }
	  
	  public static void closeNoisily(String msg, InputStream is)
	  {
	    if (is == null) {
	      return;
	    }
	    try
	    {
	      is.close();
	    }
	    catch (IOException e)
	    {
/*нужна переделка*/	      //HellLand.modLog(msg, new Object[0]);
	      e.printStackTrace();
	    }
	  }
	  
	  public static boolean stringsEqual(String a, String b)
	  {
	    if (a == b) {
	      return true;
	    }
	    if ((a == null) || (b == null)) {
	      return false;
	    }
	    return a.equals(b);
	  }
	  
	  public static void spawn(Entity ent)
	  {
	    if (ent == null) {
	      return;
	    }
	    ent.worldObj.spawnEntityInWorld(ent);
	  }
	  
	  public static double rateDamage(ItemStack is)
	  {
	    if (is == null) {
	      return 0.0D;
	    }
	    Multimap attrs = is.getItem().getAttributeModifiers(is);
	    if (attrs == null) {
	      return 0.0D;
	    }
	    BaseAttributeMap test = new ServersideAttributeMap();
	    test.applyAttributeModifiers(attrs);
	    IAttributeInstance attr = test.getAttributeInstance(SharedMonsterAttributes.attackDamage);
	    if (attr == null) {
	      return 0.0D;
	    }
	    return attr.getAttributeValue();
	  }
	  
	  public static String toRpm(double velocity)
	  {
	    return (int)(Math.toDegrees(velocity) * 10.0D / 3.0D) + " RPM";
	  }
	  
	  private static class UnitBase
	  {
	    final long ratio;
	    final String unit;
	    
	    private UnitBase(long ratio, String unit, Object object)
	    {
	      this.ratio = ratio;
	      this.unit = unit;
	    }
	  }
	  
	  public static UnitBase[] unit_time = { new UnitBase(630720000000000000L, "long eons", null), new UnitBase(630720000000L, "long millenia", null), new UnitBase(63072000000L, "long centuries", null), new UnitBase(630720000L, "long years", null), new UnitBase(51840000L, "long months", null), new UnitBase(12096000L, "long weeks", null), new UnitBase(1728000L, "long days", null), new UnitBase(72000L, "long hours", null), new UnitBase(24000L, "days", null), new UnitBase(1200L, "minutes", null), new UnitBase(20L, "seconds", null), new UnitBase(1L, "ticks", null) };
	  public static UnitBase[] unit_distance_px = { new UnitBase(16000L, "kilometers", null), new UnitBase(256L, "chunks", null), new UnitBase(16L, "blocks", null), new UnitBase(1L, "pixels", null) };
	  
	  private static UnitBase best(UnitBase[] bases, long value)
	  {
	    boolean wasAbove = false;
	    for (UnitBase base : bases)
	    {
	      if ((base.ratio <= value) && (wasAbove)) {
	        return base;
	      }
	      if (base.ratio >= value) {
	        wasAbove = true;
	      }
	    }
	    return bases[(bases.length - 1)];
	  }
	  
	  public static String unitify(UnitBase[] bases, long value, int max_len)
	  {
	    String r = "";
	    while (max_len-- != 0)
	    {
	      UnitBase best = best(bases, value);
	      long l = value / best.ratio;
	      value -= best.ratio * l;
	      if (l > 0L)
	      {
	        if (!r.isEmpty()) {
	          r = r + " ";
	        }
	        r = r + l + " " + best.unit;
	      }
	      else if ((value == 0L) && (!r.isEmpty()))
	      {
	        return r;
	      }
	      if ((best.ratio == 1L) || (max_len == 0)) {
	        break;
	      }
	    }
	    return r;
	  }
	  
	  public static void debugBytes(String header, byte[] d)
	  {
	    System.out.println(header + " #" + d.length);
	    for (byte b : d) {
	      System.out.print(" " + Integer.toString(b));
	    }
	    System.out.println();
	  }
}
