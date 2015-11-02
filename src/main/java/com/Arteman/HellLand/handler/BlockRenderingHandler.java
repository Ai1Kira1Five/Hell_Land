package com.Arteman.HellLand.handler;

import java.util.HashMap;

import com.Arteman.HellLand.utils.interfaces.IComplexBlockRenderingHandler;

import cpw.mods.fml.client.registry.RenderingRegistry;

public class BlockRenderingHandler {
	private static final HashMap<Integer, IComplexBlockRenderingHandler> handlers = new HashMap();
	public static int renderSimpleId;
	public static int renderShapeProxyId;
	
	public static IComplexBlockRenderingHandler renderSimple;
	public static IComplexBlockRenderingHandler renderShapeProxy;
	
	public static int renderPylonId;
	public static IComplexBlockRenderingHandler renderPylon;
	public static int renderCoreId;
	public static IComplexBlockRenderingHandler renderCore;
	public static int renderSparkId;
	public static IComplexBlockRenderingHandler renderSpark;
	
	public static void init(){
		renderSimpleId = RenderingRegistry.getNextAvailableRenderId();
		
	}
}
