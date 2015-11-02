package com.Arteman.HellLand.utils;

import java.util.Random;

import net.minecraft.util.Vec3;

public final class NumUtil {
	public static Random rand = new Random();
	private static ThreadLocal<Random> random_cache = new ThreadLocal();
	
	public static boolean significantChange(double a, double b, double threshold){
		if (a == b){
			return false;
		}
		if((a == 0.0D) || (b == 0.0D)){
			return true;
		}
		double thresh = Math.abs(a - b) / Math.max(a, b);
		return thresh > threshold;
	}
	
	public static boolean significantChange(double a, double b){
		return significantChange(a, b, 0.05D);
	}
	
	public static float interp(float oldValue, float newValue, float partial){
		return oldValue * (1.0F - partial) + newValue * partial;
	}
	
	public static double interp(double oldValue, double newValue, double partial){
		return oldValue * (1.0D - partial) + newValue * partial;
	}
	
	public static void interp(Vec3 oldVal, Vec3 newVal, float partial, Vec3 dest){
		dest.xCoord = interp(oldVal.xCoord, newVal.xCoord, partial);
	    dest.yCoord = interp(oldVal.yCoord, newVal.yCoord, partial);
	    dest.zCoord = interp(oldVal.zCoord, newVal.zCoord, partial);
	}
	
	public static float uninterp(float lowValue, float highValue, float currentValue){
		if (currentValue < lowValue){
			return 0.0F;
		}
		if (currentValue > highValue){
			return 1.0F;
		}
		return (currentValue - lowValue) / (highValue - lowValue);
	}
	
	public static double uninterp(double lowValue, double highValue, double currentValue){
		if (currentValue < lowValue){
			return 0.0D;
		}
		if (currentValue > highValue){
			return 1.0D;
		}
		return (currentValue - lowValue) / (highValue - lowValue);
	}
	
	public static double roundDown(double value, double units){
		double scaled = value / units;
	    return Math.floor(scaled) * units;
	}
	
	public static boolean intersect(double la, double ha, double lb, double hb){
		return (ha >= lb) && (hb >= la);
	}
	
	public static Random dirtyRandomCache(){
		Random ret = (Random)random_cache.get();
	    if (ret == null){
	    	ret = new Random();
	        random_cache.set(ret);
	    }
	    return ret;
	}
	
	public static double clip(double v, double min, double max){
		if (v < min){
			return min;
		}
		if (v > max){
			return max;
		}
		return v;
	}
	
	public static int clip(int v, int min, int max){
		if (v < min){
			return min;
		}
		if (v > max){
			return max;
		}
		return v;
	}
}
