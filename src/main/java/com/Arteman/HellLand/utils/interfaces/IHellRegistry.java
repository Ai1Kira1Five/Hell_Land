package com.Arteman.HellLand.utils.interfaces;

import java.util.Set;

import com.Arteman.HellLand.profiles.ProfileGem;
import com.Arteman.HellLand.profiles.ProfileLantern;
import com.Arteman.HellLand.profiles.ProfileMetal;
import com.Arteman.HellLand.profiles.ProfileWood;

public interface IHellRegistry {
	public abstract void registerColour(String paramString, int paramInt);
	
	public abstract int getColourCode(String paramString);
	
	public abstract boolean hasColour(String paramString);

	public abstract Set<String> getColourList();
	
	/*
	 * METAL
	 */
	
	public abstract void registerMetal(String paramString, ProfileMetal paramProfileMetal);
	
	public abstract ProfileMetal getMetal(String paramString);
	
	public abstract Set<String> getMetalList();
	
	public abstract boolean hasMetal(String paramString);
	
	/*
	 * WOOD
	 */
	
	public abstract void registerWood(String paramString, ProfileWood paramProfileWood);
	
	public abstract ProfileWood getWood(String paramString);
	
	public abstract Set<String> getWoodList();
	
	public abstract boolean hasWood(String paramString);
	
	/*
	 * GEM/CRYSTALL
	 */
	
	public abstract void registerGem(String paramString, ProfileGem paramProfileGem);
	
	public abstract ProfileGem getGem(String paramString);
	
	public abstract Set<String> getGemList();
	
	public abstract boolean hasGem(String paramString);
	
	/*
	 * LANTERN
	 */
	
	public abstract void registerLantern(String paramString, ProfileLantern paramProfileLantern);
	
	public abstract ProfileLantern getLantern(String paramString);
	
	public abstract Set<String> getLanternList();
	
	public abstract boolean hasLantern(String paramString);
}
