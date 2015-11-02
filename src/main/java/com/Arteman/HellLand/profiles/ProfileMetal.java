package com.Arteman.HellLand.profiles;

import com.Arteman.HellLand.HellLand;

public class ProfileMetal {
	private int moltenMainColour;
	private int moltenHighlightColour;

	public ProfileMetal(int moltenMainColour, int moltenHighlightColour){
		this.moltenMainColour = moltenMainColour;
		this.moltenHighlightColour = moltenHighlightColour;
	}
	
	public float hardness(){
		return 1.0F;
	}
	
	public float explosionResistance(){
		return 1.0F;
	}
	
	public float hardnessModifier(String pattern){
		return 1.0F;
	}
	
	public float explosionResistanceModifier(String pattern){
		return 1.0F;
	}
	
	public boolean usesCustomMoltenIcon(){
		return false;
	}
	
	public HellLand.FluidIcon moltenIcon(){
		return HellLand.FluidIcon.MOLTEN;
	}
	
	public int moltenMainColour(){
		return this.moltenMainColour;
	}
	
	public int moltenHighlightColour(){
		return this.moltenHighlightColour;
	}
}
