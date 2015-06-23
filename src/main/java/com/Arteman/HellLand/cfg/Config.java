package com.Arteman.HellLand.cfg;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class Config 
{
	public static void init(File configHellFile)
	{
		Configuration config = new Configuration(configHellFile);
		try{
			//LOAD
			config.load();
			//READ that
		}catch(Exception e){
			
		}finally{
			config.save();
		}
	}
}
