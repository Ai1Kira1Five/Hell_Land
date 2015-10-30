package com.Arteman.HellLand.renderer;

import com.Arteman.HellLand.utils.MathUtil;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;

public class SphereUtil {
	private static final int X = 0;
	private static final int Y = 1;
	private static final int Z = 2;
	private static final int YN = 0;
	private static final int YP = 1;
	private static final int ZN = 2;
	private static final int ZP = 3;
	private static final int XN = 4;
	private static final int XP = 5;
	public static final double[][][][] SPHERE = { { { { 0.1886615D, 0.1886615D, 0.8113385D }, { 0.3188608D, 0.1408456D, 0.8591544D }, { 0.5D, 0.1186894D, 0.8813106D }, { 0.6811392D, 0.1408456D, 0.8591544D }, { 0.8113385D, 0.1886615D, 0.8113385D } }, { { 0.1408456D, 0.1408456D, 0.6811392D }, { 0.3027975D, 0.03846167D, 0.6972025D }, { 0.5D, 0.0042382499999999D, 0.7121691D }, { 0.6972025D, 0.03846167D, 0.6972025D }, { 0.8591544D, 0.1408456D, 0.6811392D } }, { { 0.1186894D, 0.1186894D, 0.5D }, { 0.2878309D, 0.0042382499999999D, 0.5D }, { 0.5D, -0.039254529D, 0.5D }, { 0.7121691D, 0.0042382499999999D, 0.5D }, { 0.8813106D, 0.1186894D, 0.5D } }, { { 0.1408456D, 0.1408456D, 0.3188608D }, { 0.3027975D, 0.03846167D, 0.3027975D }, { 0.5D, 0.0042382499999999D, 0.2878309D }, { 0.6972025D, 0.03846167D, 0.3027975D }, { 0.8591544D, 0.1408456D, 0.3188608D } }, { { 0.1886615D, 0.1886615D, 0.1886615D }, { 0.3188608D, 0.1408456D, 0.1408456D }, { 0.5D, 0.1186894D, 0.1186894D }, { 0.6811392D, 0.1408456D, 0.1408456D }, { 0.8113385D, 0.1886615D, 0.1886615D } } }, { { { 0.8113385D, 0.8113385D, 0.8113385D }, { 0.6811392D, 0.8591544D, 0.8591544D }, { 0.5D, 0.8813106D, 0.8813106D }, { 0.3188608D, 0.8591544D, 0.8591544D }, { 0.1886615D, 0.8113385D, 0.8113385D } }, { { 0.8591544D, 0.8591544D, 0.6811392D }, { 0.6972025D, 0.961538D, 0.6972025D }, { 0.5D, 0.9957623D, 0.7121691D }, { 0.3027975D, 0.961538D, 0.6972025D }, { 0.1408456D, 0.8591544D, 0.6811392D } }, { { 0.8813106D, 0.8813106D, 0.5D }, { 0.7121691D, 0.9957623D, 0.5D }, { 0.5D, 1.0392552D, 0.5D }, { 0.2878309D, 0.9957623D, 0.5D }, { 0.1186894D, 0.8813106D, 0.5D } }, { { 0.8591544D, 0.8591544D, 0.3188608D }, { 0.6972025D, 0.961538D, 0.3027975D }, { 0.5D, 0.9957623D, 0.2878309D }, { 0.3027975D, 0.961538D, 0.3027975D }, { 0.1408456D, 0.8591544D, 0.3188608D } }, { { 0.8113385D, 0.8113385D, 0.1886615D }, { 0.6811392D, 0.8591544D, 0.1408456D }, { 0.5D, 0.8813106D, 0.1186894D }, { 0.3188608D, 0.8591544D, 0.1408456D }, { 0.1886615D, 0.8113385D, 0.1886615D } } }, { { { 0.8113385D, 0.1886615D, 0.1886615D }, { 0.8591544D, 0.3188608D, 0.1408456D }, { 0.8813106D, 0.5D, 0.1186894D }, { 0.8591544D, 0.6811392D, 0.1408456D }, { 0.8113385D, 0.8113385D, 0.1886615D } }, { { 0.6811392D, 0.1408456D, 0.1408456D }, { 0.6972025D, 0.3027975D, 0.03846167D }, { 0.7121691D, 0.5D, 0.0042382499999999D }, { 0.6972025D, 0.6972025D, 0.03846167D }, { 0.6811392D, 0.8591544D, 0.1408456D } }, { { 0.5D, 0.1186894D, 0.1186894D }, { 0.5D, 0.2878309D, 0.0042382499999999D }, { 0.5D, 0.5D, -0.039254595D }, { 0.5D, 0.7121691D, 0.0042382499999999D }, { 0.5D, 0.8813106D, 0.1186894D } }, { { 0.3188608D, 0.1408456D, 0.1408456D }, { 0.3027975D, 0.3027975D, 0.03846167D }, { 0.2878309D, 0.5D, 0.0042382499999999D }, { 0.3027975D, 0.6972025D, 0.03846167D }, { 0.3188608D, 0.8591544D, 0.1408456D } }, { { 0.1886615D, 0.1886615D, 0.1886615D }, { 0.1408456D, 0.3188608D, 0.1408456D }, { 0.1186894D, 0.5D, 0.1186894D }, { 0.1408456D, 0.6811392D, 0.1408456D }, { 0.1886615D, 0.8113385D, 0.1886615D } } }, { { { 0.1886615D, 0.1886615D, 0.8113385D }, { 0.1408456D, 0.3188608D, 0.8591544D }, { 0.1186894D, 0.5D, 0.8813106D }, { 0.1408456D, 0.6811392D, 0.8591544D }, { 0.1886615D, 0.8113385D, 0.8113385D } }, { { 0.3188608D, 0.1408456D, 0.8591544D }, { 0.3027975D, 0.3027975D, 0.961538D }, { 0.2878309D, 0.5D, 0.9957623D }, { 0.3027975D, 0.6972025D, 0.961538D }, { 0.3188608D, 0.8591544D, 0.8591544D } }, { { 0.5D, 0.1186894D, 0.8813106D }, { 0.5D, 0.2878309D, 0.9957623D }, { 0.5D, 0.5D, 1.0392541D }, { 0.5D, 0.7121691D, 0.9957623D }, { 0.5D, 0.8813106D, 0.8813106D } }, { { 0.6811392D, 0.1408456D, 0.8591544D }, { 0.6972025D, 0.3027975D, 0.961538D }, { 0.7121691D, 0.5D, 0.9957623D }, { 0.6972025D, 0.6972025D, 0.961538D }, { 0.6811392D, 0.8591544D, 0.8591544D } }, { { 0.8113385D, 0.1886615D, 0.8113385D }, { 0.8591544D, 0.3188608D, 0.8591544D }, { 0.8813106D, 0.5D, 0.8813106D }, { 0.8591544D, 0.6811392D, 0.8591544D }, { 0.8113385D, 0.8113385D, 0.8113385D } } }, { { { 0.1886615D, 0.1886615D, 0.1886615D }, { 0.1408456D, 0.3188608D, 0.1408456D }, { 0.1186894D, 0.5D, 0.1186894D }, { 0.1408456D, 0.6811392D, 0.1408456D }, { 0.1886615D, 0.8113385D, 0.1886615D } }, { { 0.1408456D, 0.1408456D, 0.3188608D }, { 0.03846167D, 0.3027975D, 0.3027975D }, { 0.0042382499999999D, 0.5D, 0.2878309D }, { 0.03846167D, 0.6972025D, 0.3027975D }, { 0.1408456D, 0.8591544D, 0.3188608D } }, { { 0.1186894D, 0.1186894D, 0.5D }, { 0.0042382499999999D, 0.2878309D, 0.5D }, { -0.039254595D, 0.5D, 0.5D }, { 0.0042382499999999D, 0.7121691D, 0.5D }, { 0.1186894D, 0.8813106D, 0.5D } }, { { 0.1408456D, 0.1408456D, 0.6811392D }, { 0.03846167D, 0.3027975D, 0.6972025D }, { 0.0042382499999999D, 0.5D, 0.7121691D }, { 0.03846167D, 0.6972025D, 0.6972025D }, { 0.1408456D, 0.8591544D, 0.6811392D } }, { { 0.1886615D, 0.1886615D, 0.8113385D }, { 0.1408456D, 0.3188608D, 0.8591544D }, { 0.1186894D, 0.5D, 0.8813106D }, { 0.1408456D, 0.6811392D, 0.8591544D }, { 0.1886615D, 0.8113385D, 0.8113385D } } }, { { { 0.8113385D, 0.1886615D, 0.8113385D }, { 0.8591544D, 0.3188608D, 0.8591544D }, { 0.8813106D, 0.5D, 0.8813106D }, { 0.8591544D, 0.6811392D, 0.8591544D }, { 0.8113385D, 0.8113385D, 0.8113385D } }, { { 0.8591544D, 0.1408456D, 0.6811392D }, { 0.961538D, 0.3027975D, 0.6972025D }, { 0.9957623D, 0.5D, 0.7121691D }, { 0.961538D, 0.6972025D, 0.6972025D }, { 0.8591544D, 0.8591544D, 0.6811392D } }, { { 0.8813106D, 0.1186894D, 0.5D }, { 0.9957623D, 0.2878309D, 0.5D }, { 1.0392541D, 0.5D, 0.5D }, { 0.9957623D, 0.7121691D, 0.5D }, { 0.8813106D, 0.8813106D, 0.5D } }, { { 0.8591544D, 0.1408456D, 0.3188608D }, { 0.961538D, 0.3027975D, 0.3027975D }, { 0.9957623D, 0.5D, 0.2878309D }, { 0.961538D, 0.6972025D, 0.3027975D }, { 0.8591544D, 0.8591544D, 0.3188608D } }, { { 0.8113385D, 0.1886615D, 0.1886615D }, { 0.8591544D, 0.3188608D, 0.1408456D }, { 0.8813106D, 0.5D, 0.1186894D }, { 0.8591544D, 0.6811392D, 0.1408456D }, { 0.8113385D, 0.8113385D, 0.1886615D } } } };
	
	public static void renderSphereYNeg(RenderBlocks renderBlocks, Block block, double x, double y, double z, IIcon icon){
		int rot = renderBlocks.uvRotateBottom;
		
		float uMin = icon.getInterpolatedU(renderBlocks.renderMinX * 16.0D);
	    float uMax = icon.getInterpolatedU(renderBlocks.renderMaxX * 16.0D);
	    float vMin = icon.getInterpolatedV(renderBlocks.renderMinZ * 16.0D);
	    float vMax = icon.getInterpolatedV(renderBlocks.renderMaxZ * 16.0D);
	    if (rot == 1){
	    	uMin = icon.getInterpolatedU(16.0D - renderBlocks.renderMaxZ * 16.0D);
	        uMax = icon.getInterpolatedU(16.0D - renderBlocks.renderMinZ * 16.0D);
	        vMin = icon.getInterpolatedV(renderBlocks.renderMinX * 16.0D);
	        vMax = icon.getInterpolatedV(renderBlocks.renderMaxX * 16.0D);
	    }
	    else if (rot == 2){
	    	uMin = icon.getInterpolatedU(16.0D - renderBlocks.renderMinX * 16.0D);
	        uMax = icon.getInterpolatedU(16.0D - renderBlocks.renderMaxX * 16.0D);
	        vMin = icon.getInterpolatedV(16.0D - renderBlocks.renderMinZ * 16.0D);
	        vMax = icon.getInterpolatedV(16.0D - renderBlocks.renderMaxZ * 16.0D);
	    }
	    else if (rot == 3){
	    	uMin = icon.getInterpolatedU(renderBlocks.renderMaxZ * 16.0D);
	        uMax = icon.getInterpolatedU(renderBlocks.renderMinZ * 16.0D);
	        vMin = icon.getInterpolatedV(16.0D - renderBlocks.renderMinX * 16.0D);
	        vMax = icon.getInterpolatedV(16.0D - renderBlocks.renderMaxX * 16.0D);
	    }
	    renderSphereSide(0, x, y, z, uMin, vMin, uMax, vMax);
	}
	
	public static void renderSphereYPos(RenderBlocks renderBlocks, Block block, double x, double y, double z, IIcon icon){
		int rot = renderBlocks.uvRotateTop;
	    
	    float uMin = icon.getInterpolatedU(renderBlocks.renderMinX * 16.0D);
	    float uMax = icon.getInterpolatedU(renderBlocks.renderMaxX * 16.0D);
	    float vMin = icon.getInterpolatedV(renderBlocks.renderMinZ * 16.0D);
	    float vMax = icon.getInterpolatedV(renderBlocks.renderMaxZ * 16.0D);
	    if (rot == 1){
	    	uMin = icon.getInterpolatedU(renderBlocks.renderMaxZ * 16.0D);
	        uMax = icon.getInterpolatedU(renderBlocks.renderMinZ * 16.0D);
	        vMin = icon.getInterpolatedV(16.0D - renderBlocks.renderMinX * 16.0D);
	        vMax = icon.getInterpolatedV(16.0D - renderBlocks.renderMaxX * 16.0D);
	    }
	    else if (rot == 2)
	    {
	      uMin = icon.getInterpolatedU(16.0D - renderBlocks.renderMinX * 16.0D);
	      uMax = icon.getInterpolatedU(16.0D - renderBlocks.renderMaxX * 16.0D);
	      vMin = icon.getInterpolatedV(16.0D - renderBlocks.renderMinZ * 16.0D);
	      vMax = icon.getInterpolatedV(16.0D - renderBlocks.renderMaxZ * 16.0D);
	    }
	    else if (rot == 3)
	    {
	      uMin = icon.getInterpolatedU(16.0D - renderBlocks.renderMaxZ * 16.0D);
	      uMax = icon.getInterpolatedU(16.0D - renderBlocks.renderMinZ * 16.0D);
	      vMin = icon.getInterpolatedV(renderBlocks.renderMinX * 16.0D);
	      vMax = icon.getInterpolatedV(renderBlocks.renderMaxX * 16.0D);
	    }
	    renderSphereSide(1, x, y, z, uMin, vMin, uMax, vMax);
	}
	
	public static void renderSphereZNeg(RenderBlocks renderBlocks, Block block, double x, double y, double z, IIcon icon){
		int rot = renderBlocks.uvRotateEast;
	    
	    float uMin = icon.getInterpolatedU(16.0D - renderBlocks.renderMaxZ * 16.0D);
	    float uMax = icon.getInterpolatedU(16.0D - renderBlocks.renderMinZ * 16.0D);
	    float vMax = icon.getInterpolatedV(16.0D - renderBlocks.renderMaxY * 16.0D);
	    float vMin = icon.getInterpolatedV(16.0D - renderBlocks.renderMinY * 16.0D);
	    if (rot == 1)
	    {
	      uMin = icon.getInterpolatedU(16.0D - renderBlocks.renderMaxY * 16.0D);
	      uMax = icon.getInterpolatedU(16.0D - renderBlocks.renderMinY * 16.0D);
	      vMin = icon.getInterpolatedV(renderBlocks.renderMaxZ * 16.0D);
	      vMax = icon.getInterpolatedV(renderBlocks.renderMinZ * 16.0D);
	    }
	    else if (rot == 2)
	    {
	      uMin = icon.getInterpolatedU(renderBlocks.renderMaxZ * 16.0D);
	      uMax = icon.getInterpolatedU(renderBlocks.renderMinZ * 16.0D);
	      vMin = icon.getInterpolatedV(renderBlocks.renderMinY * 16.0D);
	      vMax = icon.getInterpolatedV(renderBlocks.renderMaxY * 16.0D);
	    }
	    else if (rot == 3)
	    {
	      uMin = icon.getInterpolatedU(renderBlocks.renderMaxY * 16.0D);
	      uMax = icon.getInterpolatedU(renderBlocks.renderMinY * 16.0D);
	      vMin = icon.getInterpolatedV(16.0D - renderBlocks.renderMaxZ * 16.0D);
	      vMax = icon.getInterpolatedV(16.0D - renderBlocks.renderMinZ * 16.0D);
	    }
	    renderSphereSide(2, x, y, z, uMin, vMin, uMax, vMax);
	}
	
	public static void renderSphereZPos(RenderBlocks renderBlocks, Block block, double x, double y, double z, IIcon icon){
	  int rot = renderBlocks.uvRotateWest;
	  
	  float uMin = icon.getInterpolatedU(renderBlocks.renderMinZ * 16.0D);
	  float uMax = icon.getInterpolatedU(renderBlocks.renderMaxZ * 16.0D);
	  float vMax = icon.getInterpolatedV(16.0D - renderBlocks.renderMaxY * 16.0D);
	  float vMin = icon.getInterpolatedV(16.0D - renderBlocks.renderMinY * 16.0D);
	  if (rot == 1)
	  {
	    uMin = icon.getInterpolatedU(16.0D - renderBlocks.renderMaxY * 16.0D);
	    uMax = icon.getInterpolatedU(16.0D - renderBlocks.renderMinY * 16.0D);
	    vMin = icon.getInterpolatedV(16.0D - renderBlocks.renderMinZ * 16.0D);
	    vMax = icon.getInterpolatedV(16.0D - renderBlocks.renderMaxZ * 16.0D);
	  }
	  else if (rot == 2)
	  {
	    uMin = icon.getInterpolatedU(16.0D - renderBlocks.renderMinZ * 16.0D);
	    uMax = icon.getInterpolatedU(16.0D - renderBlocks.renderMaxZ * 16.0D);
	    vMin = icon.getInterpolatedV(renderBlocks.renderMinY * 16.0D);
	    vMax = icon.getInterpolatedV(renderBlocks.renderMaxY * 16.0D);
	  }
	  else if (rot == 3)
	  {
	    uMin = icon.getInterpolatedU(renderBlocks.renderMaxY * 16.0D);
	    uMax = icon.getInterpolatedU(renderBlocks.renderMinY * 16.0D);
	    vMin = icon.getInterpolatedV(renderBlocks.renderMinZ * 16.0D);
	    vMax = icon.getInterpolatedV(renderBlocks.renderMaxZ * 16.0D);
	  }
	  renderSphereSide(3, x, y, z, uMin, vMin, uMax, vMax);
	}
	
	public static void renderSphereXNeg(RenderBlocks renderBlocks, Block block, double x, double y, double z, IIcon icon){
		int rot = renderBlocks.uvRotateNorth;
	    
	    float uMin = icon.getInterpolatedU(16.0D - renderBlocks.renderMaxX * 16.0D);
	    float uMax = icon.getInterpolatedU(16.0D - renderBlocks.renderMinX * 16.0D);
	    float vMin = icon.getInterpolatedV(16.0D - renderBlocks.renderMinY * 16.0D);
	    float vMax = icon.getInterpolatedV(16.0D - renderBlocks.renderMaxY * 16.0D);
	    if (rot == 1)
	    {
	      uMin = icon.getInterpolatedU(16.0D - renderBlocks.renderMaxY * 16.0D);
	      uMax = icon.getInterpolatedU(16.0D - renderBlocks.renderMinY * 16.0D);
	      vMin = icon.getInterpolatedV(renderBlocks.renderMaxX * 16.0D);
	      vMax = icon.getInterpolatedV(renderBlocks.renderMinX * 16.0D);
	    }
	    else if (rot == 2)
	    {
	      uMin = icon.getInterpolatedU(renderBlocks.renderMaxX * 16.0D);
	      uMax = icon.getInterpolatedU(renderBlocks.renderMinX * 16.0D);
	      vMin = icon.getInterpolatedV(renderBlocks.renderMinY * 16.0D);
	      vMax = icon.getInterpolatedV(renderBlocks.renderMaxY * 16.0D);
	    }
	    else if (rot == 3)
	    {
	      uMin = icon.getInterpolatedU(renderBlocks.renderMaxY * 16.0D);
	      uMax = icon.getInterpolatedU(renderBlocks.renderMinY * 16.0D);
	      vMin = icon.getInterpolatedV(16.0D - renderBlocks.renderMaxX * 16.0D);
	      vMax = icon.getInterpolatedV(16.0D - renderBlocks.renderMinX * 16.0D);
	    }
	    renderSphereSide(4, x, y, z, uMin, vMin, uMax, vMax);
	}
	
	public static void renderSphereXPos(RenderBlocks renderBlocks, Block block, double x, double y, double z, IIcon icon){
		int rot = renderBlocks.uvRotateSouth;
	    
	    float uMin = icon.getInterpolatedU(renderBlocks.renderMinX * 16.0D);
	    float uMax = icon.getInterpolatedU(renderBlocks.renderMaxX * 16.0D);
	    float vMin = icon.getInterpolatedV(16.0D - renderBlocks.renderMinY * 16.0D);
	    float vMax = icon.getInterpolatedV(16.0D - renderBlocks.renderMaxY * 16.0D);
	    if (rot == 1)
	    {
	      uMin = icon.getInterpolatedU(16.0D - renderBlocks.renderMaxY * 16.0D);
	      uMax = icon.getInterpolatedU(16.0D - renderBlocks.renderMinY * 16.0D);
	      vMin = icon.getInterpolatedV(16.0D - renderBlocks.renderMinX * 16.0D);
	      vMax = icon.getInterpolatedV(16.0D - renderBlocks.renderMaxX * 16.0D);
	    }
	    else if (rot == 2)
	    {
	      uMin = icon.getInterpolatedU(16.0D - renderBlocks.renderMinX * 16.0D);
	      uMax = icon.getInterpolatedU(16.0D - renderBlocks.renderMaxX * 16.0D);
	      vMin = icon.getInterpolatedV(renderBlocks.renderMinY * 16.0D);
	      vMax = icon.getInterpolatedV(renderBlocks.renderMaxY * 16.0D);
	    }
	    else if (rot == 3)
	    {
	      uMin = icon.getInterpolatedU(renderBlocks.renderMaxY * 16.0D);
	      uMax = icon.getInterpolatedU(renderBlocks.renderMinY * 16.0D);
	      vMin = icon.getInterpolatedV(renderBlocks.renderMinX * 16.0D);
	      vMax = icon.getInterpolatedV(renderBlocks.renderMaxX * 16.0D);
	    }
	    renderSphereSide(5, x, y, z, uMin, vMin, uMax, vMax);
	}
	
	private static void renderSphereSide(int s, double x, double y, double z, double uS, double vS, double uE, double vE){
		Tessellator tessellator = Tessellator.instance;
	    
	    double u0 = uS;
	    double u1 = MathUtil.lerp(0.25D, uS, uE);
	    double u2 = MathUtil.lerp(0.5D, uS, uE);
	    double u3 = MathUtil.lerp(0.75D, uS, uE);
	    double u4 = uE;
	    
	    double v0 = vS;
	    double v1 = MathUtil.lerp(0.25D, vS, vE);
	    double v2 = MathUtil.lerp(0.5D, vS, vE);
	    double v3 = MathUtil.lerp(0.75D, vS, vE);
	    double v4 = vE;
	    
	    tessellator.addVertexWithUV(x + SPHERE[s][0][0][0], y + SPHERE[s][0][0][1], z + SPHERE[s][0][0][2], u0, v0);
	    tessellator.addVertexWithUV(x + SPHERE[s][1][0][0], y + SPHERE[s][1][0][1], z + SPHERE[s][1][0][2], u1, v0);
	    tessellator.addVertexWithUV(x + SPHERE[s][1][1][0], y + SPHERE[s][1][1][1], z + SPHERE[s][1][1][2], u1, v1);
	    tessellator.addVertexWithUV(x + SPHERE[s][0][1][0], y + SPHERE[s][0][1][1], z + SPHERE[s][0][1][2], u0, v1);
	    
	    tessellator.addVertexWithUV(x + SPHERE[s][1][0][0], y + SPHERE[s][1][0][1], z + SPHERE[s][1][0][2], u1, v0);
	    tessellator.addVertexWithUV(x + SPHERE[s][2][0][0], y + SPHERE[s][2][0][1], z + SPHERE[s][2][0][2], u2, v0);
	    tessellator.addVertexWithUV(x + SPHERE[s][2][1][0], y + SPHERE[s][2][1][1], z + SPHERE[s][2][1][2], u2, v1);
	    tessellator.addVertexWithUV(x + SPHERE[s][1][1][0], y + SPHERE[s][1][1][1], z + SPHERE[s][1][1][2], u1, v1);
	    
	    tessellator.addVertexWithUV(x + SPHERE[s][2][0][0], y + SPHERE[s][2][0][1], z + SPHERE[s][2][0][2], u2, v0);
	    tessellator.addVertexWithUV(x + SPHERE[s][3][0][0], y + SPHERE[s][3][0][1], z + SPHERE[s][3][0][2], u3, v0);
	    tessellator.addVertexWithUV(x + SPHERE[s][3][1][0], y + SPHERE[s][3][1][1], z + SPHERE[s][3][1][2], u3, v1);
	    tessellator.addVertexWithUV(x + SPHERE[s][2][1][0], y + SPHERE[s][2][1][1], z + SPHERE[s][2][1][2], u2, v1);
	    
	    tessellator.addVertexWithUV(x + SPHERE[s][3][0][0], y + SPHERE[s][3][0][1], z + SPHERE[s][3][0][2], u3, v0);
	    tessellator.addVertexWithUV(x + SPHERE[s][4][0][0], y + SPHERE[s][4][0][1], z + SPHERE[s][4][0][2], u4, v0);
	    tessellator.addVertexWithUV(x + SPHERE[s][4][1][0], y + SPHERE[s][4][1][1], z + SPHERE[s][4][1][2], u4, v1);
	    tessellator.addVertexWithUV(x + SPHERE[s][3][1][0], y + SPHERE[s][3][1][1], z + SPHERE[s][3][1][2], u3, v1);
	    
	    tessellator.addVertexWithUV(x + SPHERE[s][0][1][0], y + SPHERE[s][0][1][1], z + SPHERE[s][0][1][2], u0, v1);
	    tessellator.addVertexWithUV(x + SPHERE[s][1][1][0], y + SPHERE[s][1][1][1], z + SPHERE[s][1][1][2], u1, v1);
	    tessellator.addVertexWithUV(x + SPHERE[s][1][2][0], y + SPHERE[s][1][2][1], z + SPHERE[s][1][2][2], u1, v2);
	    tessellator.addVertexWithUV(x + SPHERE[s][0][2][0], y + SPHERE[s][0][2][1], z + SPHERE[s][0][2][2], u0, v2);
	    
	    tessellator.addVertexWithUV(x + SPHERE[s][1][1][0], y + SPHERE[s][1][1][1], z + SPHERE[s][1][1][2], u1, v1);
	    tessellator.addVertexWithUV(x + SPHERE[s][2][1][0], y + SPHERE[s][2][1][1], z + SPHERE[s][2][1][2], u2, v1);
	    tessellator.addVertexWithUV(x + SPHERE[s][2][2][0], y + SPHERE[s][2][2][1], z + SPHERE[s][2][2][2], u2, v2);
	    tessellator.addVertexWithUV(x + SPHERE[s][1][2][0], y + SPHERE[s][1][2][1], z + SPHERE[s][1][2][2], u1, v2);
	    
	    tessellator.addVertexWithUV(x + SPHERE[s][2][1][0], y + SPHERE[s][2][1][1], z + SPHERE[s][2][1][2], u2, v1);
	    tessellator.addVertexWithUV(x + SPHERE[s][3][1][0], y + SPHERE[s][3][1][1], z + SPHERE[s][3][1][2], u3, v1);
	    tessellator.addVertexWithUV(x + SPHERE[s][3][2][0], y + SPHERE[s][3][2][1], z + SPHERE[s][3][2][2], u3, v2);
	    tessellator.addVertexWithUV(x + SPHERE[s][2][2][0], y + SPHERE[s][2][2][1], z + SPHERE[s][2][2][2], u2, v2);
	    
	    tessellator.addVertexWithUV(x + SPHERE[s][3][1][0], y + SPHERE[s][3][1][1], z + SPHERE[s][3][1][2], u3, v1);
	    tessellator.addVertexWithUV(x + SPHERE[s][4][1][0], y + SPHERE[s][4][1][1], z + SPHERE[s][4][1][2], u4, v1);
	    tessellator.addVertexWithUV(x + SPHERE[s][4][2][0], y + SPHERE[s][4][2][1], z + SPHERE[s][4][2][2], u4, v2);
	    tessellator.addVertexWithUV(x + SPHERE[s][3][2][0], y + SPHERE[s][3][2][1], z + SPHERE[s][3][2][2], u3, v2);
	    
	    tessellator.addVertexWithUV(x + SPHERE[s][0][2][0], y + SPHERE[s][0][2][1], z + SPHERE[s][0][2][2], u0, v2);
	    tessellator.addVertexWithUV(x + SPHERE[s][1][2][0], y + SPHERE[s][1][2][1], z + SPHERE[s][1][2][2], u1, v2);
	    tessellator.addVertexWithUV(x + SPHERE[s][1][3][0], y + SPHERE[s][1][3][1], z + SPHERE[s][1][3][2], u1, v3);
	    tessellator.addVertexWithUV(x + SPHERE[s][0][3][0], y + SPHERE[s][0][3][1], z + SPHERE[s][0][3][2], u0, v3);
	    
	    tessellator.addVertexWithUV(x + SPHERE[s][1][2][0], y + SPHERE[s][1][2][1], z + SPHERE[s][1][2][2], u1, v2);
	    tessellator.addVertexWithUV(x + SPHERE[s][2][2][0], y + SPHERE[s][2][2][1], z + SPHERE[s][2][2][2], u2, v2);
	    tessellator.addVertexWithUV(x + SPHERE[s][2][3][0], y + SPHERE[s][2][3][1], z + SPHERE[s][2][3][2], u2, v3);
	    tessellator.addVertexWithUV(x + SPHERE[s][1][3][0], y + SPHERE[s][1][3][1], z + SPHERE[s][1][3][2], u1, v3);
	    
	    tessellator.addVertexWithUV(x + SPHERE[s][2][2][0], y + SPHERE[s][2][2][1], z + SPHERE[s][2][2][2], u2, v2);
	    tessellator.addVertexWithUV(x + SPHERE[s][3][2][0], y + SPHERE[s][3][2][1], z + SPHERE[s][3][2][2], u3, v2);
	    tessellator.addVertexWithUV(x + SPHERE[s][3][3][0], y + SPHERE[s][3][3][1], z + SPHERE[s][3][3][2], u3, v3);
	    tessellator.addVertexWithUV(x + SPHERE[s][2][3][0], y + SPHERE[s][2][3][1], z + SPHERE[s][2][3][2], u2, v3);
	    
	    tessellator.addVertexWithUV(x + SPHERE[s][3][2][0], y + SPHERE[s][3][2][1], z + SPHERE[s][3][2][2], u3, v2);
	    tessellator.addVertexWithUV(x + SPHERE[s][4][2][0], y + SPHERE[s][4][2][1], z + SPHERE[s][4][2][2], u4, v2);
	    tessellator.addVertexWithUV(x + SPHERE[s][4][3][0], y + SPHERE[s][4][3][1], z + SPHERE[s][4][3][2], u4, v3);
	    tessellator.addVertexWithUV(x + SPHERE[s][3][3][0], y + SPHERE[s][3][3][1], z + SPHERE[s][3][3][2], u3, v3);
	    
	    tessellator.addVertexWithUV(x + SPHERE[s][0][3][0], y + SPHERE[s][0][3][1], z + SPHERE[s][0][3][2], u0, v3);
	    tessellator.addVertexWithUV(x + SPHERE[s][1][3][0], y + SPHERE[s][1][3][1], z + SPHERE[s][1][3][2], u1, v3);
	    tessellator.addVertexWithUV(x + SPHERE[s][1][4][0], y + SPHERE[s][1][4][1], z + SPHERE[s][1][4][2], u1, v4);
	    tessellator.addVertexWithUV(x + SPHERE[s][0][4][0], y + SPHERE[s][0][4][1], z + SPHERE[s][0][4][2], u0, v4);
	    
	    tessellator.addVertexWithUV(x + SPHERE[s][1][3][0], y + SPHERE[s][1][3][1], z + SPHERE[s][1][3][2], u1, v3);
	    tessellator.addVertexWithUV(x + SPHERE[s][2][3][0], y + SPHERE[s][2][3][1], z + SPHERE[s][2][3][2], u2, v3);
	    tessellator.addVertexWithUV(x + SPHERE[s][2][4][0], y + SPHERE[s][2][4][1], z + SPHERE[s][2][4][2], u2, v4);
	    tessellator.addVertexWithUV(x + SPHERE[s][1][4][0], y + SPHERE[s][1][4][1], z + SPHERE[s][1][4][2], u1, v4);
	    
	    tessellator.addVertexWithUV(x + SPHERE[s][2][3][0], y + SPHERE[s][2][3][1], z + SPHERE[s][2][3][2], u2, v3);
	    tessellator.addVertexWithUV(x + SPHERE[s][3][3][0], y + SPHERE[s][3][3][1], z + SPHERE[s][3][3][2], u3, v3);
	    tessellator.addVertexWithUV(x + SPHERE[s][3][4][0], y + SPHERE[s][3][4][1], z + SPHERE[s][3][4][2], u3, v4);
	    tessellator.addVertexWithUV(x + SPHERE[s][2][4][0], y + SPHERE[s][2][4][1], z + SPHERE[s][2][4][2], u2, v4);
	    
	    tessellator.addVertexWithUV(x + SPHERE[s][3][3][0], y + SPHERE[s][3][3][1], z + SPHERE[s][3][3][2], u3, v3);
	    tessellator.addVertexWithUV(x + SPHERE[s][4][3][0], y + SPHERE[s][4][3][1], z + SPHERE[s][4][3][2], u4, v3);
	    tessellator.addVertexWithUV(x + SPHERE[s][4][4][0], y + SPHERE[s][4][4][1], z + SPHERE[s][4][4][2], u4, v4);
	    tessellator.addVertexWithUV(x + SPHERE[s][3][4][0], y + SPHERE[s][3][4][1], z + SPHERE[s][3][4][2], u3, v4);
	}
}
