package com.Arteman.HellLand.renderer.tiles;

import com.Arteman.HellLand.ModMisc;

import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class ModelPylon {
	private IModelCustom model;
	
	public ModelPylon(){
		this.model = AdvancedModelLoader.loadModel(ModMisc.getTileModel("pylon"));
	}
	
	public void renderInnerCrystal(){
		this.model.renderPart("InnerCrystal");
	}
	
	public void renderOuterCrystal(){
		this.model.renderPart("OuterCrystal");
	}
	
	public void renderRing(){
		this.model.renderAllExcept(new String[] { "InnerCrystal", "OuterCrystal", "Ring_Gem01", "Ring_Gem02", "Ring_Gem03", "Ring_Gem04" });
	}
	
	public void renderGems(){
		for (int i = 1; i < 5; i++) {
			this.model.renderPart("Ring_Gem0" + i);
		}
	}
}
