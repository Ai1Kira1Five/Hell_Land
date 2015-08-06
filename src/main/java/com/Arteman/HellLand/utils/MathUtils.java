package com.Arteman.HellLand.utils;

public final class MathUtils 
{
	private MathUtils() {}

	public static int clamp(int value, int min, int max) 
	{
		return value < min ? min : (value > max ? max : value);
	}

	public static float clamp(float value, float min, float max) 
	{
		return value < min ? min : (value > max ? max : value);
	}

	public static double clamp(double value, double min, double max) 
	{
		return value < min ? min : (value > max ? max : value);
	}
}
