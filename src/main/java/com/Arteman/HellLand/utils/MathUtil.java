package com.Arteman.HellLand.utils;

public class MathUtil {
	public static double lerp(double amt, double start, double end){
		if (amt <= 0.0D){
			return start;
		}
		if (amt >= 1.0D){
			return end;
		}
		return start + (end - start) * amt;
	}
}
