package com.Arteman.HellLand.utils.network;

import java.lang.reflect.Method;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class HLLog
{
	public static final Logger logger = LogManager.getLogger("HellLand");


	private HLLog() {
	}

	public static void initLog() {

		logger.info("Starting HellLand " + getVersion());
		logger.info("Copyright (c) Arteman1607, 2015-201x");
		logger.info("vk.com/isartyomad");
	}

	public static void logErrorAPI(String mod, Throwable error, Class<?> classFile) 
	{
		StringBuilder msg = new StringBuilder(mod);
		msg.append(" API error, please update your mods. Error: ").append(error);
		StackTraceElement[] stackTrace = error.getStackTrace();
		if (stackTrace.length > 0) 
		{
			msg.append(", ").append(stackTrace[0]);
		}

		logger.log(Level.ERROR, msg.toString());

		if (classFile != null) 
		{
			msg = new StringBuilder(mod);
			msg.append(" API error: ").append(classFile.getSimpleName()).append(" is loaded from ").append(classFile.getProtectionDomain().getCodeSource().getLocation());
			logger.log(Level.ERROR, msg.toString());
		}
	}

    public static String getVersion()
    {
        try 
        {
            Class<?> clazz = Class.forName("buildcraft.core.Version");
            Method method = clazz.getDeclaredMethod("getVersion");
            return String.valueOf(method.invoke(null));
        }
        catch (Exception e)
        {
            return "UNKNOWN VERSION";
        }
    }
}
