package com.Arteman.HellLand.utils;

import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraftforge.common.util.ForgeDirection;

public class FaceUtil {
	public static final int ICON_ROT_0 = 0;
	public static final int ICON_ROT_90 = 1;
	public static final int ICON_ROT_180 = 3;
	public static final int ICON_ROT_270 = 2;
	public static final int ICON_FLIP_V = 4;
	public static final int ICON_FLIP_H = 8;
	private static double xMin;
	private static double xMax;
	private static double yMin;
	private static double yMax;
	private static double zMin;
	private static double zMax;
	private static double fr_uTL;
	private static double fr_vTL;
	private static double fr_uBL;
	private static double fr_vBL;
	private static double fr_uBR;
	private static double fr_vBR;
	private static double fr_uTR;
	private static double fr_vTR;

	public FaceUtil() {
    }

    public static void cross(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot, int cD) {
        prepareFace(renderBlocks, ForgeDirection.NORTH, x, y, z, icon, selfIllum, color, fRot);
        double i = 0.14644665D;
        double o = 0.85355335D;
        if(cD == 0) {
            setupVertex(renderBlocks, x + i, y + 1.0D, z + i, fr_uTL, fr_vTL);
            setupVertex(renderBlocks, x + i, y + 0.0D, z + i, fr_uBL, fr_vBL);
            setupVertex(renderBlocks, x + o, y + 0.0D, z + o, fr_uBR, fr_vBR);
            setupVertex(renderBlocks, x + o, y + 1.0D, z + o, fr_uTR, fr_vTR);
            setupVertex(renderBlocks, x + o, y + 1.0D, z + o, fr_uTR, fr_vTR);
            setupVertex(renderBlocks, x + o, y + 0.0D, z + o, fr_uBR, fr_vBR);
            setupVertex(renderBlocks, x + i, y + 0.0D, z + i, fr_uBL, fr_vBL);
            setupVertex(renderBlocks, x + i, y + 1.0D, z + i, fr_uTL, fr_vTL);
            setupVertex(renderBlocks, x + o, y + 1.0D, z + i, fr_uTL, fr_vTL);
            setupVertex(renderBlocks, x + o, y + 0.0D, z + i, fr_uBL, fr_vBL);
            setupVertex(renderBlocks, x + i, y + 0.0D, z + o, fr_uBR, fr_vBR);
            setupVertex(renderBlocks, x + i, y + 1.0D, z + o, fr_uTR, fr_vTR);
            setupVertex(renderBlocks, x + i, y + 1.0D, z + o, fr_uTR, fr_vTR);
            setupVertex(renderBlocks, x + i, y + 0.0D, z + o, fr_uBR, fr_vBR);
            setupVertex(renderBlocks, x + o, y + 0.0D, z + i, fr_uBL, fr_vBL);
            setupVertex(renderBlocks, x + o, y + 1.0D, z + i, fr_uTL, fr_vTL);
        } else if(cD == 1) {
            setupVertex(renderBlocks, x + i, y + 1.0D, z + i, fr_uBR, fr_vBR);
            setupVertex(renderBlocks, x + i, y + 0.0D, z + i, fr_uTR, fr_vTR);
            setupVertex(renderBlocks, x + o, y + 0.0D, z + o, fr_uTL, fr_vTL);
            setupVertex(renderBlocks, x + o, y + 1.0D, z + o, fr_uBL, fr_vBL);
            setupVertex(renderBlocks, x + o, y + 1.0D, z + o, fr_uBR, fr_vBR);
            setupVertex(renderBlocks, x + o, y + 0.0D, z + o, fr_uTR, fr_vTR);
            setupVertex(renderBlocks, x + i, y + 0.0D, z + i, fr_uTL, fr_vTL);
            setupVertex(renderBlocks, x + i, y + 1.0D, z + i, fr_uBL, fr_vBL);
            setupVertex(renderBlocks, x + o, y + 1.0D, z + i, fr_uBR, fr_vBR);
            setupVertex(renderBlocks, x + o, y + 0.0D, z + i, fr_uTR, fr_vTR);
            setupVertex(renderBlocks, x + i, y + 0.0D, z + o, fr_uTL, fr_vTL);
            setupVertex(renderBlocks, x + i, y + 1.0D, z + o, fr_uBL, fr_vBL);
            setupVertex(renderBlocks, x + i, y + 1.0D, z + o, fr_uBR, fr_vBR);
            setupVertex(renderBlocks, x + i, y + 0.0D, z + o, fr_uTR, fr_vTR);
            setupVertex(renderBlocks, x + o, y + 0.0D, z + i, fr_uTL, fr_vTL);
            setupVertex(renderBlocks, x + o, y + 1.0D, z + i, fr_uBL, fr_vBL);
        } else if(cD == 2) {
            setupVertex(renderBlocks, x + i, y + i, z + 1.0D, fr_uTL, fr_vTL);
            setupVertex(renderBlocks, x + i, y + i, z + 0.0D, fr_uBL, fr_vBL);
            setupVertex(renderBlocks, x + o, y + o, z + 0.0D, fr_uBR, fr_vBR);
            setupVertex(renderBlocks, x + o, y + o, z + 1.0D, fr_uTR, fr_vTR);
            setupVertex(renderBlocks, x + o, y + o, z + 1.0D, fr_uTR, fr_vTR);
            setupVertex(renderBlocks, x + o, y + o, z + 0.0D, fr_uBR, fr_vBR);
            setupVertex(renderBlocks, x + i, y + i, z + 0.0D, fr_uBL, fr_vBL);
            setupVertex(renderBlocks, x + i, y + i, z + 1.0D, fr_uTL, fr_vTL);
            setupVertex(renderBlocks, x + o, y + i, z + 1.0D, fr_uTL, fr_vTL);
            setupVertex(renderBlocks, x + o, y + i, z + 0.0D, fr_uBL, fr_vBL);
            setupVertex(renderBlocks, x + i, y + o, z + 0.0D, fr_uBR, fr_vBR);
            setupVertex(renderBlocks, x + i, y + o, z + 1.0D, fr_uTR, fr_vTR);
            setupVertex(renderBlocks, x + i, y + o, z + 1.0D, fr_uTR, fr_vTR);
            setupVertex(renderBlocks, x + i, y + o, z + 0.0D, fr_uBR, fr_vBR);
            setupVertex(renderBlocks, x + o, y + i, z + 0.0D, fr_uBL, fr_vBL);
            setupVertex(renderBlocks, x + o, y + i, z + 1.0D, fr_uTL, fr_vTL);
        } else if(cD == 3) {
            setupVertex(renderBlocks, x + i, y + i, z + 1.0D, fr_uBR, fr_vBR);
            setupVertex(renderBlocks, x + i, y + i, z + 0.0D, fr_uTR, fr_vTR);
            setupVertex(renderBlocks, x + o, y + o, z + 0.0D, fr_uTL, fr_vTL);
            setupVertex(renderBlocks, x + o, y + o, z + 1.0D, fr_uBL, fr_vBL);
            setupVertex(renderBlocks, x + o, y + o, z + 1.0D, fr_uBR, fr_vBR);
            setupVertex(renderBlocks, x + o, y + o, z + 0.0D, fr_uTR, fr_vTR);
            setupVertex(renderBlocks, x + i, y + i, z + 0.0D, fr_uTL, fr_vTL);
            setupVertex(renderBlocks, x + i, y + i, z + 1.0D, fr_uBL, fr_vBL);
            setupVertex(renderBlocks, x + o, y + i, z + 1.0D, fr_uBR, fr_vBR);
            setupVertex(renderBlocks, x + o, y + i, z + 0.0D, fr_uTR, fr_vTR);
            setupVertex(renderBlocks, x + i, y + o, z + 0.0D, fr_uTL, fr_vTL);
            setupVertex(renderBlocks, x + i, y + o, z + 1.0D, fr_uBL, fr_vBL);
            setupVertex(renderBlocks, x + i, y + o, z + 1.0D, fr_uBR, fr_vBR);
            setupVertex(renderBlocks, x + i, y + o, z + 0.0D, fr_uTR, fr_vTR);
            setupVertex(renderBlocks, x + o, y + i, z + 0.0D, fr_uTL, fr_vTL);
            setupVertex(renderBlocks, x + o, y + i, z + 1.0D, fr_uBL, fr_vBL);
        } else if(cD == 4) {
            setupVertex(renderBlocks, x + 1.0D, y + i, z + i, fr_uTL, fr_vTL);
            setupVertex(renderBlocks, x + 0.0D, y + i, z + i, fr_uBL, fr_vBL);
            setupVertex(renderBlocks, x + 0.0D, y + o, z + o, fr_uBR, fr_vBR);
            setupVertex(renderBlocks, x + 1.0D, y + o, z + o, fr_uTR, fr_vTR);
            setupVertex(renderBlocks, x + 1.0D, y + o, z + o, fr_uTR, fr_vTR);
            setupVertex(renderBlocks, x + 0.0D, y + o, z + o, fr_uBR, fr_vBR);
            setupVertex(renderBlocks, x + 0.0D, y + i, z + i, fr_uBL, fr_vBL);
            setupVertex(renderBlocks, x + 1.0D, y + i, z + i, fr_uTL, fr_vTL);
            setupVertex(renderBlocks, x + 1.0D, y + o, z + i, fr_uTL, fr_vTL);
            setupVertex(renderBlocks, x + 0.0D, y + o, z + i, fr_uBL, fr_vBL);
            setupVertex(renderBlocks, x + 0.0D, y + i, z + o, fr_uBR, fr_vBR);
            setupVertex(renderBlocks, x + 1.0D, y + i, z + o, fr_uTR, fr_vTR);
            setupVertex(renderBlocks, x + 1.0D, y + i, z + o, fr_uTR, fr_vTR);
            setupVertex(renderBlocks, x + 0.0D, y + i, z + o, fr_uBR, fr_vBR);
            setupVertex(renderBlocks, x + 0.0D, y + o, z + i, fr_uBL, fr_vBL);
            setupVertex(renderBlocks, x + 1.0D, y + o, z + i, fr_uTL, fr_vTL);
        } else if(cD == 5) {
            setupVertex(renderBlocks, x + 1.0D, y + i, z + i, fr_uBR, fr_vBR);
            setupVertex(renderBlocks, x + 0.0D, y + i, z + i, fr_uTR, fr_vTR);
            setupVertex(renderBlocks, x + 0.0D, y + o, z + o, fr_uTL, fr_vTL);
            setupVertex(renderBlocks, x + 1.0D, y + o, z + o, fr_uBL, fr_vBL);
            setupVertex(renderBlocks, x + 1.0D, y + o, z + o, fr_uBR, fr_vBR);
            setupVertex(renderBlocks, x + 0.0D, y + o, z + o, fr_uTR, fr_vTR);
            setupVertex(renderBlocks, x + 0.0D, y + i, z + i, fr_uTL, fr_vTL);
            setupVertex(renderBlocks, x + 1.0D, y + i, z + i, fr_uBL, fr_vBL);
            setupVertex(renderBlocks, x + 1.0D, y + o, z + i, fr_uBR, fr_vBR);
            setupVertex(renderBlocks, x + 0.0D, y + o, z + i, fr_uTR, fr_vTR);
            setupVertex(renderBlocks, x + 0.0D, y + i, z + o, fr_uTL, fr_vTL);
            setupVertex(renderBlocks, x + 1.0D, y + i, z + o, fr_uBL, fr_vBL);
            setupVertex(renderBlocks, x + 1.0D, y + i, z + o, fr_uBR, fr_vBR);
            setupVertex(renderBlocks, x + 0.0D, y + i, z + o, fr_uTR, fr_vTR);
            setupVertex(renderBlocks, x + 0.0D, y + o, z + i, fr_uTL, fr_vTL);
            setupVertex(renderBlocks, x + 1.0D, y + o, z + i, fr_uBL, fr_vBL);
        }

    }

    public static void solidYN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.DOWN, x, y, z, icon, selfIllum, color, fRot);
        if(renderBlocks.enableAO) {
            Tessellator.instance.setColorOpaque_F(renderBlocks.colorRedTopLeft, renderBlocks.colorGreenTopLeft, renderBlocks.colorBlueTopLeft);
            Tessellator.instance.setBrightness(renderBlocks.brightnessTopLeft);
            setupVertex(renderBlocks, xMin, yMin, zMax, fr_uTL, fr_vTL);
            Tessellator.instance.setColorOpaque_F(renderBlocks.colorRedBottomLeft, renderBlocks.colorGreenBottomLeft, renderBlocks.colorBlueBottomLeft);
            Tessellator.instance.setBrightness(renderBlocks.brightnessBottomLeft);
            setupVertex(renderBlocks, xMin, yMin, zMin, fr_uBL, fr_vBL);
            Tessellator.instance.setColorOpaque_F(renderBlocks.colorRedBottomRight, renderBlocks.colorGreenBottomRight, renderBlocks.colorBlueBottomRight);
            Tessellator.instance.setBrightness(renderBlocks.brightnessBottomRight);
            setupVertex(renderBlocks, xMax, yMin, zMin, fr_uBR, fr_vBR);
            Tessellator.instance.setColorOpaque_F(renderBlocks.colorRedTopRight, renderBlocks.colorGreenTopRight, renderBlocks.colorBlueTopRight);
            Tessellator.instance.setBrightness(renderBlocks.brightnessTopRight);
            setupVertex(renderBlocks, xMax, yMin, zMax, fr_uTR, fr_vTR);
        } else {
            setupVertex(renderBlocks, xMin, yMin, zMax, fr_uTL, fr_vTL);
            setupVertex(renderBlocks, xMin, yMin, zMin, fr_uBL, fr_vBL);
            setupVertex(renderBlocks, xMax, yMin, zMin, fr_uBR, fr_vBR);
            setupVertex(renderBlocks, xMax, yMin, zMax, fr_uTR, fr_vTR);
        }

    }

    public static void solidYP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.UP, x, y, z, icon, selfIllum, color, fRot);
        if(renderBlocks.enableAO) {
            Tessellator.instance.setColorOpaque_F(renderBlocks.colorRedTopLeft, renderBlocks.colorGreenTopLeft, renderBlocks.colorBlueTopLeft);
            Tessellator.instance.setBrightness(renderBlocks.brightnessTopLeft);
            setupVertex(renderBlocks, xMax, yMax, zMax, fr_uTL, fr_vTL);
            Tessellator.instance.setColorOpaque_F(renderBlocks.colorRedBottomLeft, renderBlocks.colorGreenBottomLeft, renderBlocks.colorBlueBottomLeft);
            Tessellator.instance.setBrightness(renderBlocks.brightnessBottomLeft);
            setupVertex(renderBlocks, xMax, yMax, zMin, fr_uBL, fr_vBL);
            Tessellator.instance.setColorOpaque_F(renderBlocks.colorRedBottomRight, renderBlocks.colorGreenBottomRight, renderBlocks.colorBlueBottomRight);
            Tessellator.instance.setBrightness(renderBlocks.brightnessBottomRight);
            setupVertex(renderBlocks, xMin, yMax, zMin, fr_uBR, fr_vBR);
            Tessellator.instance.setColorOpaque_F(renderBlocks.colorRedTopRight, renderBlocks.colorGreenTopRight, renderBlocks.colorBlueTopRight);
            Tessellator.instance.setBrightness(renderBlocks.brightnessTopRight);
            setupVertex(renderBlocks, xMin, yMax, zMax, fr_uTR, fr_vTR);
        } else {
            setupVertex(renderBlocks, xMax, yMax, zMax, fr_uTL, fr_vTL);
            setupVertex(renderBlocks, xMax, yMax, zMin, fr_uBL, fr_vBL);
            setupVertex(renderBlocks, xMin, yMax, zMin, fr_uBR, fr_vBR);
            setupVertex(renderBlocks, xMin, yMax, zMax, fr_uTR, fr_vTR);
        }

    }

    public static void solidZN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.NORTH, x, y, z, icon, selfIllum, color, fRot);
        if(renderBlocks.enableAO) {
            Tessellator.instance.setColorOpaque_F(renderBlocks.colorRedTopLeft, renderBlocks.colorGreenTopLeft, renderBlocks.colorBlueTopLeft);
            Tessellator.instance.setBrightness(renderBlocks.brightnessTopLeft);
            setupVertex(renderBlocks, xMax, yMax, zMin, fr_uTL, fr_vTL);
            Tessellator.instance.setColorOpaque_F(renderBlocks.colorRedBottomLeft, renderBlocks.colorGreenBottomLeft, renderBlocks.colorBlueBottomLeft);
            Tessellator.instance.setBrightness(renderBlocks.brightnessBottomLeft);
            setupVertex(renderBlocks, xMax, yMin, zMin, fr_uBL, fr_vBL);
            Tessellator.instance.setColorOpaque_F(renderBlocks.colorRedBottomRight, renderBlocks.colorGreenBottomRight, renderBlocks.colorBlueBottomRight);
            Tessellator.instance.setBrightness(renderBlocks.brightnessBottomRight);
            setupVertex(renderBlocks, xMin, yMin, zMin, fr_uBR, fr_vBR);
            Tessellator.instance.setColorOpaque_F(renderBlocks.colorRedTopRight, renderBlocks.colorGreenTopRight, renderBlocks.colorBlueTopRight);
            Tessellator.instance.setBrightness(renderBlocks.brightnessTopRight);
            setupVertex(renderBlocks, xMin, yMax, zMin, fr_uTR, fr_vTR);
        } else {
            setupVertex(renderBlocks, xMax, yMax, zMin, fr_uTL, fr_vTL);
            setupVertex(renderBlocks, xMax, yMin, zMin, fr_uBL, fr_vBL);
            setupVertex(renderBlocks, xMin, yMin, zMin, fr_uBR, fr_vBR);
            setupVertex(renderBlocks, xMin, yMax, zMin, fr_uTR, fr_vTR);
        }

    }

    public static void solidZP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.SOUTH, x, y, z, icon, selfIllum, color, fRot);
        if(renderBlocks.enableAO) {
            Tessellator.instance.setColorOpaque_F(renderBlocks.colorRedTopLeft, renderBlocks.colorGreenTopLeft, renderBlocks.colorBlueTopLeft);
            Tessellator.instance.setBrightness(renderBlocks.brightnessTopLeft);
            setupVertex(renderBlocks, xMin, yMax, zMax, fr_uTL, fr_vTL);
            Tessellator.instance.setColorOpaque_F(renderBlocks.colorRedBottomLeft, renderBlocks.colorGreenBottomLeft, renderBlocks.colorBlueBottomLeft);
            Tessellator.instance.setBrightness(renderBlocks.brightnessBottomLeft);
            setupVertex(renderBlocks, xMin, yMin, zMax, fr_uBL, fr_vBL);
            Tessellator.instance.setColorOpaque_F(renderBlocks.colorRedBottomRight, renderBlocks.colorGreenBottomRight, renderBlocks.colorBlueBottomRight);
            Tessellator.instance.setBrightness(renderBlocks.brightnessBottomRight);
            setupVertex(renderBlocks, xMax, yMin, zMax, fr_uBR, fr_vBR);
            Tessellator.instance.setColorOpaque_F(renderBlocks.colorRedTopRight, renderBlocks.colorGreenTopRight, renderBlocks.colorBlueTopRight);
            Tessellator.instance.setBrightness(renderBlocks.brightnessTopRight);
            setupVertex(renderBlocks, xMax, yMax, zMax, fr_uTR, fr_vTR);
        } else {
            setupVertex(renderBlocks, xMin, yMax, zMax, fr_uTL, fr_vTL);
            setupVertex(renderBlocks, xMin, yMin, zMax, fr_uBL, fr_vBL);
            setupVertex(renderBlocks, xMax, yMin, zMax, fr_uBR, fr_vBR);
            setupVertex(renderBlocks, xMax, yMax, zMax, fr_uTR, fr_vTR);
        }

    }

    public static void solidXN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.WEST, x, y, z, icon, selfIllum, color, fRot);
        if(renderBlocks.enableAO) {
            Tessellator.instance.setColorOpaque_F(renderBlocks.colorRedTopLeft, renderBlocks.colorGreenTopLeft, renderBlocks.colorBlueTopLeft);
            Tessellator.instance.setBrightness(renderBlocks.brightnessTopLeft);
            setupVertex(renderBlocks, xMin, yMax, zMin, fr_uTL, fr_vTL);
            Tessellator.instance.setColorOpaque_F(renderBlocks.colorRedBottomLeft, renderBlocks.colorGreenBottomLeft, renderBlocks.colorBlueBottomLeft);
            Tessellator.instance.setBrightness(renderBlocks.brightnessBottomLeft);
            setupVertex(renderBlocks, xMin, yMin, zMin, fr_uBL, fr_vBL);
            Tessellator.instance.setColorOpaque_F(renderBlocks.colorRedBottomRight, renderBlocks.colorGreenBottomRight, renderBlocks.colorBlueBottomRight);
            Tessellator.instance.setBrightness(renderBlocks.brightnessBottomRight);
            setupVertex(renderBlocks, xMin, yMin, zMax, fr_uBR, fr_vBR);
            Tessellator.instance.setColorOpaque_F(renderBlocks.colorRedTopRight, renderBlocks.colorGreenTopRight, renderBlocks.colorBlueTopRight);
            Tessellator.instance.setBrightness(renderBlocks.brightnessTopRight);
            setupVertex(renderBlocks, xMin, yMax, zMax, fr_uTR, fr_vTR);
        } else {
            setupVertex(renderBlocks, xMin, yMax, zMin, fr_uTL, fr_vTL);
            setupVertex(renderBlocks, xMin, yMin, zMin, fr_uBL, fr_vBL);
            setupVertex(renderBlocks, xMin, yMin, zMax, fr_uBR, fr_vBR);
            setupVertex(renderBlocks, xMin, yMax, zMax, fr_uTR, fr_vTR);
        }

    }

    public static void solidXP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.EAST, x, y, z, icon, selfIllum, color, fRot);
        if(renderBlocks.enableAO) {
            Tessellator.instance.setColorOpaque_F(renderBlocks.colorRedTopLeft, renderBlocks.colorGreenTopLeft, renderBlocks.colorBlueTopLeft);
            Tessellator.instance.setBrightness(renderBlocks.brightnessTopLeft);
            setupVertex(renderBlocks, xMax, yMax, zMax, fr_uTL, fr_vTL);
            Tessellator.instance.setColorOpaque_F(renderBlocks.colorRedBottomLeft, renderBlocks.colorGreenBottomLeft, renderBlocks.colorBlueBottomLeft);
            Tessellator.instance.setBrightness(renderBlocks.brightnessBottomLeft);
            setupVertex(renderBlocks, xMax, yMin, zMax, fr_uBL, fr_vBL);
            Tessellator.instance.setColorOpaque_F(renderBlocks.colorRedBottomRight, renderBlocks.colorGreenBottomRight, renderBlocks.colorBlueBottomRight);
            Tessellator.instance.setBrightness(renderBlocks.brightnessBottomRight);
            setupVertex(renderBlocks, xMax, yMin, zMin, fr_uBR, fr_vBR);
            Tessellator.instance.setColorOpaque_F(renderBlocks.colorRedTopRight, renderBlocks.colorGreenTopRight, renderBlocks.colorBlueTopRight);
            Tessellator.instance.setBrightness(renderBlocks.brightnessTopRight);
            setupVertex(renderBlocks, xMax, yMax, zMin, fr_uTR, fr_vTR);
        } else {
            setupVertex(renderBlocks, xMax, yMax, zMax, fr_uTL, fr_vTL);
            setupVertex(renderBlocks, xMax, yMin, zMax, fr_uBL, fr_vBL);
            setupVertex(renderBlocks, xMax, yMin, zMin, fr_uBR, fr_vBR);
            setupVertex(renderBlocks, xMax, yMax, zMin, fr_uTR, fr_vTR);
        }

    }

    public static void slantYPZN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.UP, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMax, yMin, zMin, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMin, yMin, zMin, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uTR, fr_vTR);
    }

    public static void slantYPZP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.UP, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMax, yMin, zMax, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMax, yMax, zMin, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMin, yMax, zMin, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMin, yMin, zMax, fr_uTR, fr_vTR);
    }

    public static void slantYPXN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.UP, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMax, yMax, zMin, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMin, yMin, zMin, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMin, yMin, zMax, fr_uTR, fr_vTR);
    }

    public static void slantYPXP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.UP, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMax, yMin, zMax, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMax, yMin, zMin, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMin, yMax, zMin, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uTR, fr_vTR);
    }

    public static void slantYNZN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.DOWN, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMin, yMin, zMax, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMin, yMax, zMin, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMax, yMax, zMin, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMax, yMin, zMax, fr_uTL, fr_vTL);
    }

    public static void slantYNZP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.DOWN, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMin, yMin, zMin, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMax, yMin, zMin, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uTL, fr_vTL);
    }

    public static void slantYNXN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.DOWN, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMin, yMax, zMin, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMax, yMin, zMin, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMax, yMin, zMax, fr_uTL, fr_vTL);
    }

    public static void slantYNXP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.DOWN, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMin, yMin, zMax, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMin, yMin, zMin, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMax, yMax, zMin, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uTL, fr_vTL);
    }

    public static void slantZNYP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.NORTH, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMax, yMin, zMin, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMin, yMin, zMin, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uTR, fr_vTR);
    }

    public static void slantZNYN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.NORTH, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMin, yMin, zMax, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMin, yMax, zMin, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMax, yMax, zMin, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMax, yMin, zMax, fr_uBL, fr_vBL);
    }

    public static void slantZPYN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.SOUTH, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMin, yMin, zMin, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMax, yMin, zMin, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uTR, fr_vTR);
    }

    public static void slantZPYP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.SOUTH, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMax, yMin, zMax, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMax, yMax, zMin, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMin, yMax, zMin, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMin, yMin, zMax, fr_uBL, fr_vBL);
    }

    public static void slantXNYP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.WEST, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMax, yMax, zMin, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMin, yMin, zMin, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMin, yMin, zMax, fr_uBR, fr_vBR);
    }

    public static void slantXPYP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.EAST, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMax, yMin, zMax, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMax, yMin, zMin, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMin, yMax, zMin, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uTL, fr_vTL);
    }

    public static void slantXNYN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.WEST, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMin, yMax, zMin, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMax, yMin, zMin, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMax, yMin, zMax, fr_uBR, fr_vBR);
    }

    public static void slantXPYN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.EAST, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMin, yMin, zMax, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMin, yMin, zMin, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMax, yMax, zMin, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uTL, fr_vTL);
    }

    public static void slantXPZP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.SOUTH, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMin, yMin, zMax, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMax, yMin, zMin, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMax, yMax, zMin, fr_uTR, fr_vTR);
    }

    public static void slantXPZN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.NORTH, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMax, yMin, zMax, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMin, yMin, zMin, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMin, yMax, zMin, fr_uTR, fr_vTR);
    }

    public static void slantXNZP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.SOUTH, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMin, yMax, zMin, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMin, yMin, zMin, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMax, yMin, zMax, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uTR, fr_vTR);
    }

    public static void slantXNZN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.NORTH, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMax, yMax, zMin, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMax, yMin, zMin, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMin, yMin, zMax, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uTR, fr_vTR);
    }

    public static void flatTriYNtoXNZN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.DOWN, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMin, yMin, zMax, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMax, yMin, zMin, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMax, yMin, zMax, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMax, yMin, zMax, fr_uTL, fr_vTL);
    }

    public static void flatTriYNtoXPZN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.DOWN, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMin, yMin, zMax, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMin, yMin, zMin, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMax, yMin, zMax, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMax, yMin, zMax, fr_uTL, fr_vTL);
    }

    public static void flatTriYNtoXNZP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.DOWN, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMin, yMin, zMin, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMax, yMin, zMin, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMax, yMin, zMax, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMax, yMin, zMax, fr_uTL, fr_vTL);
    }

    public static void flatTriYNtoXPZP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.DOWN, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMin, yMin, zMax, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMin, yMin, zMin, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMax, yMin, zMin, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMax, yMin, zMin, fr_uBL, fr_vBL);
    }

    public static void flatTriYPtoXNZN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.UP, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMax, yMax, zMin, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uTR, fr_vTR);
    }

    public static void flatTriYPtoXPZN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.UP, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMin, yMax, zMin, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uTR, fr_vTR);
    }

    public static void flatTriYPtoXNZP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.UP, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMax, yMax, zMin, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMin, yMax, zMin, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMin, yMax, zMin, fr_uBR, fr_vBR);
    }

    public static void flatTriYPtoXPZP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.UP, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMax, yMax, zMin, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMin, yMax, zMin, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uTR, fr_vTR);
    }

    public static void flatTriZNtoXNYP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.NORTH, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMax, yMax, zMin, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMax, yMin, zMin, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMin, yMin, zMin, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMin, yMin, zMin, fr_uBR, fr_vBR);
    }

    public static void flatTriZNtoXPYP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.NORTH, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMax, yMin, zMin, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMin, yMin, zMin, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMin, yMax, zMin, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMin, yMax, zMin, fr_uTR, fr_vTR);
    }

    public static void flatTriZNtoXNYN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.NORTH, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMax, yMax, zMin, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMax, yMin, zMin, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMin, yMax, zMin, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMin, yMax, zMin, fr_uTR, fr_vTR);
    }

    public static void flatTriZNtoXPYN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.NORTH, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMax, yMax, zMin, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMin, yMin, zMin, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMin, yMax, zMin, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMin, yMax, zMin, fr_uTR, fr_vTR);
    }

    public static void flatTriZPtoXNYP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.SOUTH, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMin, yMin, zMax, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMax, yMin, zMax, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uTR, fr_vTR);
    }

    public static void flatTriZPtoXPYP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.SOUTH, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMin, yMin, zMax, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMax, yMin, zMax, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMax, yMin, zMax, fr_uBR, fr_vBR);
    }

    public static void flatTriZPtoXNYN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.SOUTH, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMax, yMin, zMax, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uTR, fr_vTR);
    }

    public static void flatTriZPtoXPYN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.SOUTH, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMin, yMin, zMax, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uTR, fr_vTR);
    }

    public static void flatTriXNtoZNYP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.SOUTH, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMin, yMin, zMin, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMin, yMin, zMax, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uTR, fr_vTR);
    }

    public static void flatTriXNtoZPYP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.SOUTH, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMin, yMax, zMin, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMin, yMin, zMin, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMin, yMin, zMax, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMin, yMin, zMax, fr_uBR, fr_vBR);
    }

    public static void flatTriXNtoZNYN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.SOUTH, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMin, yMax, zMin, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMin, yMin, zMax, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMin, yMin, zMax, fr_uBR, fr_vBR);
    }

    public static void flatTriXNtoZPYN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.SOUTH, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMin, yMax, zMin, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMin, yMin, zMin, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMin, yMin, zMin, fr_uBL, fr_vBL);
    }

    public static void flatTriXPtoZNYP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.SOUTH, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMax, yMin, zMax, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMax, yMin, zMin, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMax, yMin, zMin, fr_uBR, fr_vBR);
    }

    public static void flatTriXPtoZPYP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.SOUTH, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMax, yMin, zMax, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMax, yMin, zMin, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMax, yMax, zMin, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMax, yMax, zMin, fr_uTR, fr_vTR);
    }

    public static void flatTriXPtoZNYN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.SOUTH, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMax, yMin, zMax, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMax, yMax, zMin, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uTL, fr_vTL);
    }

    public static void flatTriXPtoZPYN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.SOUTH, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMax, yMin, zMin, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMax, yMax, zMin, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uTL, fr_vTL);
    }

    public static void extoTriYNtoXNZN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.DOWN, x, y, z, icon, selfIllum, color, fRot);
        double uM = fr_uTR - (fr_uTR - fr_uTL) / 2.0D;
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMax, yMax, zMin, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMax, yMin, zMax, uM, fr_vTL);
        setupVertex(renderBlocks, xMax, yMin, zMax, uM, fr_vTL);
    }

    public static void extoTriYNtoXPZN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.DOWN, x, y, z, icon, selfIllum, color, fRot);
        double uM = fr_uTR - (fr_uTR - fr_uTL) / 2.0D;
        setupVertex(renderBlocks, xMin, yMin, zMax, uM, fr_vTR);
        setupVertex(renderBlocks, xMin, yMax, zMin, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uBL, fr_vBL);
    }

    public static void extoTriYNtoXNZP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.DOWN, x, y, z, icon, selfIllum, color, fRot);
        double uM = fr_uTR - (fr_uTR - fr_uTL) / 2.0D;
        setupVertex(renderBlocks, xMin, yMax, zMin, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMax, yMin, zMin, uM, fr_vTL);
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uBR, fr_vBR);
    }

    public static void extoTriYNtoXPZP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.DOWN, x, y, z, icon, selfIllum, color, fRot);
        double uM = fr_uTR - (fr_uTR - fr_uTL) / 2.0D;
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMin, yMin, zMin, uM, fr_vTL);
        setupVertex(renderBlocks, xMax, yMax, zMin, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMax, yMax, zMin, fr_uBR, fr_vBR);
    }

    public static void extoTriYPtoXNZN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.UP, x, y, z, icon, selfIllum, color, fRot);
        double uM = fr_uTR - (fr_uTR - fr_uTL) / 2.0D;
        setupVertex(renderBlocks, xMax, yMax, zMax, uM, fr_vBL);
        setupVertex(renderBlocks, xMax, yMin, zMin, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMin, yMin, zMax, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMin, yMin, zMax, fr_uTL, fr_vTL);
    }

    public static void extoTriYPtoXPZN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.UP, x, y, z, icon, selfIllum, color, fRot);
        double uM = fr_uTR - (fr_uTR - fr_uTL) / 2.0D;
        setupVertex(renderBlocks, xMax, yMin, zMax, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMin, yMin, zMin, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMin, yMax, zMax, uM, fr_vBR);
        setupVertex(renderBlocks, xMin, yMax, zMax, uM, fr_vBR);
    }

    public static void extoTriYPtoXNZP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.UP, x, y, z, icon, selfIllum, color, fRot);
        double uM = fr_uTR - (fr_uTR - fr_uTL) / 2.0D;
        setupVertex(renderBlocks, xMax, yMin, zMax, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMax, yMax, zMin, uM, fr_vBR);
        setupVertex(renderBlocks, xMin, yMin, zMin, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMin, yMin, zMin, fr_uTR, fr_vTR);
    }

    public static void extoTriYPtoXPZP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.UP, x, y, z, icon, selfIllum, color, fRot);
        double uM = fr_uTR - (fr_uTR - fr_uTL) / 2.0D;
        setupVertex(renderBlocks, xMax, yMin, zMin, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMin, yMax, zMin, uM, fr_vBR);
        setupVertex(renderBlocks, xMin, yMin, zMax, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMin, yMin, zMax, fr_uTR, fr_vTR);
    }

    public static void extoTriZNtoXNYP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.NORTH, x, y, z, icon, selfIllum, color, fRot);
        double uM = fr_uBR - (fr_uBR - fr_uBL) / 2.0D;
        setupVertex(renderBlocks, xMax, yMax, zMax, uM, fr_vTL);
        setupVertex(renderBlocks, xMax, yMin, zMin, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMin, yMin, zMax, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMin, yMin, zMax, fr_uBR, fr_vBR);
    }

    public static void extoTriZNtoXPYP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.NORTH, x, y, z, icon, selfIllum, color, fRot);
        double uM = fr_uBR - (fr_uBR - fr_uBL) / 2.0D;
        setupVertex(renderBlocks, xMax, yMin, zMax, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMin, yMin, zMin, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMin, yMax, zMax, uM, fr_vTR);
        setupVertex(renderBlocks, xMin, yMax, zMax, uM, fr_vTR);
    }

    public static void extoTriZNtoXNYN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.NORTH, x, y, z, icon, selfIllum, color, fRot);
        double uM = fr_uBR - (fr_uBR - fr_uBL) / 2.0D;
        setupVertex(renderBlocks, xMax, yMax, zMin, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMax, yMin, zMax, uM, fr_vBL);
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uTR, fr_vTR);
    }

    public static void extoTriZNtoXPYN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.NORTH, x, y, z, icon, selfIllum, color, fRot);
        double uM = fr_uBR - (fr_uBR - fr_uBL) / 2.0D;
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMin, yMin, zMax, uM, fr_vBR);
        setupVertex(renderBlocks, xMin, yMax, zMin, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMin, yMax, zMin, fr_uTR, fr_vTR);
    }

    public static void extoTriZPtoXNYP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.SOUTH, x, y, z, icon, selfIllum, color, fRot);
        double uM = fr_uBR - (fr_uBR - fr_uBL) / 2.0D;
        setupVertex(renderBlocks, xMin, yMin, zMin, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMax, yMin, zMax, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMax, yMax, zMin, uM, fr_vTR);
        setupVertex(renderBlocks, xMax, yMax, zMin, uM, fr_vTR);
    }

    public static void extoTriZPtoXPYP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.SOUTH, x, y, z, icon, selfIllum, color, fRot);
        double uM = fr_uBR - (fr_uBR - fr_uBL) / 2.0D;
        setupVertex(renderBlocks, xMin, yMax, zMin, uM, fr_vTL);
        setupVertex(renderBlocks, xMin, yMin, zMax, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMax, yMin, zMin, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMax, yMin, zMin, fr_uBR, fr_vBR);
    }

    public static void extoTriZPtoXNYN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.SOUTH, x, y, z, icon, selfIllum, color, fRot);
        double uM = fr_uBR - (fr_uBR - fr_uBL) / 2.0D;
        setupVertex(renderBlocks, xMin, yMax, zMin, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMax, yMin, zMin, uM, fr_vBR);
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uTR, fr_vTR);
    }

    public static void extoTriZPtoXPYN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.SOUTH, x, y, z, icon, selfIllum, color, fRot);
        double uM = fr_uBR - (fr_uBR - fr_uBL) / 2.0D;
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMin, yMin, zMin, uM, fr_vBL);
        setupVertex(renderBlocks, xMax, yMax, zMin, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMax, yMax, zMin, fr_uTR, fr_vTR);
    }

    public static void extoTriXNtoZNYP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.SOUTH, x, y, z, icon, selfIllum, color, fRot);
        double uM = fr_uBR - (fr_uBR - fr_uBL) / 2.0D;
        setupVertex(renderBlocks, xMax, yMin, zMin, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMin, yMin, zMax, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMax, yMax, zMax, uM, fr_vTR);
        setupVertex(renderBlocks, xMax, yMax, zMax, uM, fr_vTR);
    }

    public static void extoTriXNtoZPYP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.SOUTH, x, y, z, icon, selfIllum, color, fRot);
        double uM = fr_uBR - (fr_uBR - fr_uBL) / 2.0D;
        setupVertex(renderBlocks, xMax, yMax, zMin, uM, fr_vTL);
        setupVertex(renderBlocks, xMin, yMin, zMin, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMax, yMin, zMax, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMax, yMin, zMax, fr_uBR, fr_vBR);
    }

    public static void extoTriXNtoZNYN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.SOUTH, x, y, z, icon, selfIllum, color, fRot);
        double uM = fr_uBR - (fr_uBR - fr_uBL) / 2.0D;
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMax, yMax, zMin, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMax, yMin, zMax, uM, fr_vBR);
        setupVertex(renderBlocks, xMax, yMin, zMax, uM, fr_vBR);
    }

    public static void extoTriXNtoZPYN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.SOUTH, x, y, z, icon, selfIllum, color, fRot);
        double uM = fr_uBR - (fr_uBR - fr_uBL) / 2.0D;
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMin, yMax, zMin, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMax, yMin, zMin, uM, fr_vBL);
        setupVertex(renderBlocks, xMax, yMin, zMin, uM, fr_vBL);
    }

    public static void extoTriXPtoZNYP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.SOUTH, x, y, z, icon, selfIllum, color, fRot);
        double uM = fr_uBR - (fr_uBR - fr_uBL) / 2.0D;
        setupVertex(renderBlocks, xMin, yMax, zMax, uM, fr_vTL);
        setupVertex(renderBlocks, xMax, yMin, zMax, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMin, yMin, zMin, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMin, yMin, zMin, fr_uBR, fr_vBR);
    }

    public static void extoTriXPtoZPYP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.SOUTH, x, y, z, icon, selfIllum, color, fRot);
        double uM = fr_uBR - (fr_uBR - fr_uBL) / 2.0D;
        setupVertex(renderBlocks, xMin, yMin, zMax, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMax, yMin, zMin, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMin, yMax, zMin, uM, fr_vTR);
        setupVertex(renderBlocks, xMin, yMax, zMin, uM, fr_vTR);
    }

    public static void extoTriXPtoZNYN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.SOUTH, x, y, z, icon, selfIllum, color, fRot);
        double uM = fr_uBR - (fr_uBR - fr_uBL) / 2.0D;
        setupVertex(renderBlocks, xMin, yMin, zMax, uM, fr_vBL);
        setupVertex(renderBlocks, xMin, yMax, zMin, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uTL, fr_vTL);
    }

    public static void extoTriXPtoZPYN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.SOUTH, x, y, z, icon, selfIllum, color, fRot);
        double uM = fr_uBR - (fr_uBR - fr_uBL) / 2.0D;
        setupVertex(renderBlocks, xMin, yMin, zMin, uM, fr_vBR);
        setupVertex(renderBlocks, xMax, yMax, zMin, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uTL, fr_vTL);
    }

    public static void intoTriYNtoXNZN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.DOWN, x, y, z, icon, selfIllum, color, fRot);
        double uBM = fr_uBR - (fr_uBR - fr_uBL) / 2.0D;
        setupVertex(renderBlocks, xMin, yMin, zMax, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMax, yMin, zMin, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMax, yMin, zMin, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMax, yMax, zMax, uBM, fr_vBL);
    }

    public static void intoTriYNtoXPZN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.DOWN, x, y, z, icon, selfIllum, color, fRot);
        double uBM = fr_uBR - (fr_uBR - fr_uBL) / 2.0D;
        setupVertex(renderBlocks, xMin, yMax, zMax, uBM, fr_vBR);
        setupVertex(renderBlocks, xMin, yMin, zMin, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMin, yMin, zMin, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMax, yMin, zMax, fr_uTL, fr_vTL);
    }

    public static void intoTriYNtoXNZP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.DOWN, x, y, z, icon, selfIllum, color, fRot);
        double uBM = fr_uBR - (fr_uBR - fr_uBL) / 2.0D;
        setupVertex(renderBlocks, xMax, yMax, zMin, uBM, fr_vBL);
        setupVertex(renderBlocks, xMax, yMin, zMax, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMax, yMin, zMax, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMin, yMin, zMin, fr_uTL, fr_vTL);
    }

    public static void intoTriYNtoXPZP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.DOWN, x, y, z, icon, selfIllum, color, fRot);
        double uBM = fr_uBR - (fr_uBR - fr_uBL) / 2.0D;
        setupVertex(renderBlocks, xMin, yMin, zMax, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMin, yMin, zMax, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMin, yMax, zMin, uBM, fr_vBR);
        setupVertex(renderBlocks, xMax, yMin, zMin, fr_uTR, fr_vTR);
    }

    public static void intoTriYPtoXNZN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.UP, x, y, z, icon, selfIllum, color, fRot);
        double uTM = fr_uTR - (fr_uTR - fr_uTL) / 2.0D;
        setupVertex(renderBlocks, xMax, yMin, zMax, uTM, fr_vTR);
        setupVertex(renderBlocks, xMax, yMax, zMin, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMax, yMax, zMin, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uBR, fr_vBR);
    }

    public static void intoTriYPtoXPZN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.UP, x, y, z, icon, selfIllum, color, fRot);
        double uM = fr_uBL - (fr_uBL - fr_uTR) / 2.0D;
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMin, yMax, zMin, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMin, yMax, zMin, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMin, yMin, zMax, uM, fr_vTL);
    }

    public static void intoTriYPtoXNZP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.UP, x, y, z, icon, selfIllum, color, fRot);
        double uM = fr_uBL - (fr_uBL - fr_uBR) / 2.0D;
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMax, yMin, zMin, uM, fr_vTR);
        setupVertex(renderBlocks, xMin, yMax, zMin, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMin, yMax, zMin, fr_uBL, fr_vBL);
    }

    public static void intoTriYPtoXPZP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.UP, x, y, z, icon, selfIllum, color, fRot);
        double uM = fr_uBL - (fr_uBL - fr_uBR) / 2.0D;
        setupVertex(renderBlocks, xMax, yMax, zMin, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMin, yMin, zMin, uM, fr_vTR);
        setupVertex(renderBlocks, xMin, yMin, zMin, uM, fr_vTR);
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uBL, fr_vBL);
    }

    public static void intoTriZNtoXNYP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.NORTH, x, y, z, icon, selfIllum, color, fRot);
        double uTM = fr_uTR - (fr_uTR - fr_uTL) / 2.0D;
        setupVertex(renderBlocks, xMin, yMin, zMin, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMax, yMax, zMin, uTM, fr_vTL);
        setupVertex(renderBlocks, xMax, yMax, zMin, uTM, fr_vTL);
        setupVertex(renderBlocks, xMax, yMin, zMax, fr_uBL, fr_vBL);
    }

    public static void intoTriZNtoXPYP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.NORTH, x, y, z, icon, selfIllum, color, fRot);
        double uTM = fr_uTR - (fr_uTR - fr_uTL) / 2.0D;
        setupVertex(renderBlocks, xMin, yMin, zMax, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMin, yMax, zMin, uTM, fr_vTR);
        setupVertex(renderBlocks, xMin, yMax, zMin, uTM, fr_vTR);
        setupVertex(renderBlocks, xMax, yMin, zMin, fr_uBL, fr_vBL);
    }

    public static void intoTriZNtoXNYN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.NORTH, x, y, z, icon, selfIllum, color, fRot);
        double uBM = fr_uBR - (fr_uBR - fr_uBL) / 2.0D;
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMax, yMin, zMin, uBM, fr_vBL);
        setupVertex(renderBlocks, xMax, yMin, zMin, uBM, fr_vBL);
        setupVertex(renderBlocks, xMin, yMax, zMin, fr_uTR, fr_vTR);
    }

    public static void intoTriZNtoXPYN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.NORTH, x, y, z, icon, selfIllum, color, fRot);
        double uBM = fr_uBR - (fr_uBR - fr_uBL) / 2.0D;
        setupVertex(renderBlocks, xMax, yMax, zMin, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMin, yMin, zMin, uBM, fr_vBR);
        setupVertex(renderBlocks, xMin, yMin, zMin, uBM, fr_vBR);
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uTR, fr_vTR);
    }

    public static void intoTriZPtoXNYP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.SOUTH, x, y, z, icon, selfIllum, color, fRot);
        double uTM = fr_uTR - (fr_uTR - fr_uTL) / 2.0D;
        setupVertex(renderBlocks, xMax, yMin, zMin, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMax, yMax, zMax, uTM, fr_vTR);
        setupVertex(renderBlocks, xMax, yMax, zMax, uTM, fr_vTR);
        setupVertex(renderBlocks, xMin, yMin, zMax, fr_uBL, fr_vBL);
    }

    public static void intoTriZPtoXPYP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.SOUTH, x, y, z, icon, selfIllum, color, fRot);
        double uTM = fr_uTR - (fr_uTR - fr_uTL) / 2.0D;
        setupVertex(renderBlocks, xMax, yMin, zMax, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMin, yMax, zMax, uTM, fr_vTL);
        setupVertex(renderBlocks, xMin, yMax, zMax, uTM, fr_vTL);
        setupVertex(renderBlocks, xMin, yMin, zMin, fr_uBL, fr_vBL);
    }

    public static void intoTriZPtoXNYN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.SOUTH, x, y, z, icon, selfIllum, color, fRot);
        double uBM = fr_uBR - (fr_uBR - fr_uBL) / 2.0D;
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMax, yMin, zMax, uBM, fr_vBR);
        setupVertex(renderBlocks, xMax, yMin, zMax, uBM, fr_vBR);
        setupVertex(renderBlocks, xMax, yMax, zMin, fr_uTR, fr_vTR);
    }

    public static void intoTriZPtoXPYN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.SOUTH, x, y, z, icon, selfIllum, color, fRot);
        double uBM = fr_uBR - (fr_uBR - fr_uBL) / 2.0D;
        setupVertex(renderBlocks, xMin, yMax, zMin, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMin, yMin, zMax, uBM, fr_vBL);
        setupVertex(renderBlocks, xMin, yMin, zMax, uBM, fr_vBL);
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uTR, fr_vTR);
    }

    public static void intoTriXNtoZNYP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.SOUTH, x, y, z, icon, selfIllum, color, fRot);
        double uTM = fr_uTR - (fr_uTR - fr_uTL) / 2.0D;
        setupVertex(renderBlocks, xMax, yMin, zMax, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMin, yMax, zMax, uTM, fr_vTR);
        setupVertex(renderBlocks, xMin, yMax, zMax, uTM, fr_vTR);
        setupVertex(renderBlocks, xMin, yMin, zMin, fr_uBL, fr_vBL);
    }

    public static void intoTriXNtoZPYP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.SOUTH, x, y, z, icon, selfIllum, color, fRot);
        double uTM = fr_uTR - (fr_uTR - fr_uTL) / 2.0D;
        setupVertex(renderBlocks, xMin, yMin, zMax, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMin, yMax, zMin, uTM, fr_vTL);
        setupVertex(renderBlocks, xMin, yMax, zMin, uTM, fr_vTL);
        setupVertex(renderBlocks, xMax, yMin, zMin, fr_uBL, fr_vBL);
    }

    public static void intoTriXNtoZNYN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.SOUTH, x, y, z, icon, selfIllum, color, fRot);
        double uBM = fr_uBR - (fr_uBR - fr_uBL) / 2.0D;
        setupVertex(renderBlocks, xMin, yMax, zMin, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMin, yMin, zMax, uBM, fr_vBR);
        setupVertex(renderBlocks, xMin, yMin, zMax, uBM, fr_vBR);
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uTR, fr_vTR);
    }

    public static void intoTriXNtoZPYN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.SOUTH, x, y, z, icon, selfIllum, color, fRot);
        double uBM = fr_uBR - (fr_uBR - fr_uBL) / 2.0D;
        setupVertex(renderBlocks, xMax, yMax, zMin, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMin, yMin, zMin, uBM, fr_vBL);
        setupVertex(renderBlocks, xMin, yMin, zMin, uBM, fr_vBL);
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uTR, fr_vTR);
    }

    public static void intoTriXPtoZNYP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.SOUTH, x, y, z, icon, selfIllum, color, fRot);
        double uTM = fr_uTR - (fr_uTR - fr_uTL) / 2.0D;
        setupVertex(renderBlocks, xMax, yMin, zMin, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMax, yMax, zMax, uTM, fr_vTL);
        setupVertex(renderBlocks, xMax, yMax, zMax, uTM, fr_vTL);
        setupVertex(renderBlocks, xMin, yMin, zMax, fr_uBL, fr_vBL);
    }

    public static void intoTriXPtoZPYP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.SOUTH, x, y, z, icon, selfIllum, color, fRot);
        double uTM = fr_uTR - (fr_uTR - fr_uTL) / 2.0D;
        setupVertex(renderBlocks, xMax, yMin, zMax, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMin, yMin, zMin, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMax, yMax, zMin, uTM, fr_vTR);
        setupVertex(renderBlocks, xMax, yMax, zMin, uTM, fr_vTR);
    }

    public static void intoTriXPtoZNYN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.SOUTH, x, y, z, icon, selfIllum, color, fRot);
        double uBM = fr_uBR - (fr_uBR - fr_uBL) / 2.0D;
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMax, yMin, zMax, uBM, fr_vBL);
        setupVertex(renderBlocks, xMax, yMin, zMax, uBM, fr_vBL);
        setupVertex(renderBlocks, xMax, yMax, zMin, fr_uTR, fr_vTR);
    }

    public static void intoTriXPtoZPYN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.SOUTH, x, y, z, icon, selfIllum, color, fRot);
        double uBM = fr_uBR - (fr_uBR - fr_uBL) / 2.0D;
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMax, yMin, zMin, uBM, fr_vBR);
        setupVertex(renderBlocks, xMax, yMin, zMin, uBM, fr_vBR);
        setupVertex(renderBlocks, xMin, yMax, zMin, fr_uTR, fr_vTR);
    }

    public static void intcTriYNtoXNZN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.DOWN, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMax, yMin, zMin, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMax, yMin, zMax, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMax, yMin, zMax, fr_uTL, fr_vTL);
    }

    public static void intcTriYNtoZNXN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.DOWN, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMin, yMin, zMax, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMax, yMax, zMin, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMax, yMin, zMax, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMax, yMin, zMax, fr_uTR, fr_vTR);
    }

    public static void intcTriYNtoXPZN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.DOWN, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMin, yMin, zMax, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMin, yMin, zMin, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uBR, fr_vBR);
    }

    public static void intcTriYNtoZNXP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.DOWN, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMin, yMin, zMax, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMin, yMax, zMin, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMax, yMin, zMax, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMax, yMin, zMax, fr_uTR, fr_vTR);
    }

    public static void intcTriYNtoXNZP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.DOWN, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMin, yMax, zMin, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMax, yMin, zMin, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMax, yMin, zMax, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMax, yMin, zMax, fr_uTL, fr_vTL);
    }

    public static void intcTriYNtoZPXN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.DOWN, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMin, yMin, zMin, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMax, yMin, zMin, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uBL, fr_vBL);
    }

    public static void intcTriYNtoXPZP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.DOWN, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMin, yMin, zMax, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMin, yMin, zMin, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMax, yMax, zMin, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMax, yMax, zMin, fr_uBL, fr_vBL);
    }

    public static void intcTriYNtoZPXP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.DOWN, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMin, yMin, zMin, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMax, yMin, zMin, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMax, yMin, zMin, fr_uTL, fr_vTL);
    }

    public static void intcTriYPtoXNZN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.UP, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uTL, fr_vBL);
        setupVertex(renderBlocks, xMax, yMax, zMin, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMin, yMin, zMax, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMin, yMin, zMax, fr_uTL, fr_vTL);
    }

    public static void intcTriYPtoZNXN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.UP, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMax, yMin, zMin, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uBL, fr_vBL);
    }

    public static void intcTriYPtoXPZN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.UP, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMax, yMin, zMax, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMin, yMax, zMin, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uBR, fr_vBR);
    }

    public static void intcTriYPtoZNXP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.UP, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMin, yMin, zMin, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uBL, fr_vBL);
    }

    public static void intcTriYPtoXNZP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.UP, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMax, yMax, zMin, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMin, yMin, zMin, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMin, yMin, zMin, fr_uTR, fr_vTR);
    }

    public static void intcTriYPtoZPXN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.UP, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMax, yMin, zMax, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMax, yMax, zMin, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMin, yMax, zMin, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMin, yMax, zMin, fr_uBR, fr_vBR);
    }

    public static void intcTriYPtoXPZP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.UP, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMax, yMin, zMin, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMin, yMax, zMin, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uBR, fr_vBR);
    }

    public static void intcTriYPtoZPXP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.UP, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMax, yMax, zMin, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMin, yMax, zMin, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMin, yMin, zMax, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMin, yMin, zMax, fr_uTR, fr_vTR);
    }

    public static void intcTriZNtoXNYP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.NORTH, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMax, yMax, zMin, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMax, yMin, zMin, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMin, yMin, zMax, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMin, yMin, zMax, fr_uBR, fr_vBR);
    }

    public static void intcTriZNtoYPXN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.NORTH, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMax, yMin, zMin, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMin, yMin, zMin, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMin, yMin, zMin, fr_uBR, fr_vBR);
    }

    public static void intcTriZNtoXPYP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.NORTH, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMax, yMin, zMax, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMin, yMin, zMin, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMin, yMax, zMin, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMin, yMax, zMin, fr_uTR, fr_vTR);
    }

    public static void intcTriZNtoYPXP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.NORTH, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMax, yMin, zMin, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMin, yMin, zMin, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uTR, fr_vTR);
    }

    public static void intcTriZNtoXNYN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.NORTH, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMax, yMax, zMin, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMax, yMin, zMin, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uTR, fr_vTR);
    }

    public static void intcTriZNtoYNXN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.NORTH, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMax, yMax, zMin, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMax, yMin, zMax, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMin, yMax, zMin, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMin, yMax, zMin, fr_uTR, fr_vTR);
    }

    public static void intcTriZNtoXPYN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.NORTH, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMin, yMin, zMin, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMin, yMax, zMin, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMin, yMax, zMin, fr_uTR, fr_vTR);
    }

    public static void intcTriZNtoYNXP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.NORTH, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMax, yMax, zMin, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMin, yMin, zMax, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMin, yMax, zMin, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMin, yMax, zMin, fr_uTR, fr_vTR);
    }

    public static void intcTriZPtoXNYP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.SOUTH, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMin, yMin, zMin, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMax, yMin, zMax, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uTR, fr_vTR);
    }

    public static void intcTriZPtoYPXN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.SOUTH, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMin, yMin, zMax, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMax, yMin, zMax, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMax, yMax, zMin, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMax, yMax, zMin, fr_uTR, fr_vTR);
    }

    public static void intcTriZPtoXPYP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.SOUTH, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMin, yMin, zMax, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMax, yMin, zMin, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMax, yMin, zMin, fr_uBR, fr_vBR);
    }

    public static void intcTriZPtoYPXP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.SOUTH, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMin, yMax, zMin, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMin, yMin, zMax, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMax, yMin, zMax, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMax, yMin, zMax, fr_uBR, fr_vBR);
    }

    public static void intcTriZPtoXNYN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.SOUTH, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMin, yMax, zMin, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMax, yMin, zMax, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uTR, fr_vTR);
    }

    public static void intcTriZPtoYNXN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.SOUTH, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMax, yMin, zMin, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uTR, fr_vTR);
    }

    public static void intcTriZPtoXPYN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.SOUTH, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMin, yMin, zMax, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMax, yMax, zMin, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMax, yMax, zMin, fr_uTR, fr_vTR);
    }

    public static void intcTriZPtoYNXP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.SOUTH, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMin, yMin, zMin, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uTR, fr_vTR);
    }

    public static void intcTriXNtoZNYP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.SOUTH, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMin, yMin, zMin, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMin, yMin, zMax, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uTR, fr_vTR);
    }

    public static void intcTriXNtoYPZN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.SOUTH, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMax, yMin, zMin, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMin, yMin, zMax, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uTR, fr_vTR);
    }

    public static void intcTriXNtoZPYP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.SOUTH, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMax, yMax, zMin, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMin, yMin, zMin, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMin, yMin, zMax, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMin, yMin, zMax, fr_uBR, fr_vBR);
    }

    public static void intcTriXNtoYPZP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.SOUTH, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMin, yMax, zMin, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMin, yMin, zMin, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMax, yMin, zMax, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMax, yMin, zMax, fr_uBR, fr_vBR);
    }

    public static void intcTriXNtoZNYN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.SOUTH, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMin, yMax, zMin, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMax, yMin, zMax, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMax, yMin, zMax, fr_uBR, fr_vBR);
    }

    public static void intcTriXNtoYNZN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.SOUTH, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMax, yMax, zMin, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMin, yMin, zMax, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMin, yMin, zMax, fr_uBR, fr_vBR);
    }

    public static void intcTriXNtoZPYN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.SOUTH, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMin, yMax, zMin, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMax, yMin, zMin, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMax, yMin, zMin, fr_uBL, fr_vBL);
    }

    public static void intcTriXNtoYNZP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.SOUTH, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMin, yMax, zMin, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMin, yMin, zMin, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMin, yMin, zMin, fr_uBL, fr_vBL);
    }

    public static void intcTriXPtoZNYP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.SOUTH, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMax, yMin, zMax, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMin, yMin, zMin, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMin, yMin, zMin, fr_uBR, fr_vBR);
    }

    public static void intcTriXPtoYPZN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.SOUTH, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMax, yMin, zMax, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMax, yMin, zMin, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMax, yMin, zMin, fr_uBR, fr_vBR);
    }

    public static void intcTriXPtoZPYP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.SOUTH, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMin, yMin, zMax, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMax, yMin, zMin, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMax, yMax, zMin, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMax, yMax, zMin, fr_uTR, fr_vTR);
    }

    public static void intcTriXPtoYPZP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.SOUTH, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMax, yMin, zMax, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMax, yMin, zMin, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMin, yMax, zMin, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMin, yMax, zMin, fr_uTR, fr_vTR);
    }

    public static void intcTriXPtoZNYN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.SOUTH, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMax, yMin, zMax, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMin, yMax, zMin, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uTL, fr_vTL);
    }

    public static void intcTriXPtoYNZN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.SOUTH, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMin, yMin, zMax, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMax, yMax, zMin, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uTL, fr_vTL);
    }

    public static void intcTriXPtoZPYN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.SOUTH, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMax, yMin, zMin, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMax, yMax, zMin, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uTL, fr_vTL);
    }

    public static void intcTriXPtoYNZP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.SOUTH, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMin, yMin, zMin, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMax, yMax, zMin, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uTL, fr_vTL);
    }

    public static void extcTriYNtoXNZN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.DOWN, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMax, yMin, zMin, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uBL, fr_vBL);
    }

    public static void extcTriYNtoXPZN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.DOWN, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMin, yMin, zMin, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uBL, fr_vBL);
    }

    public static void extcTriYNtoXNZP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.DOWN, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMin, yMax, zMin, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMax, yMax, zMin, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMax, yMin, zMax, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMax, yMin, zMax, fr_uTR, fr_vTR);
    }

    public static void extcTriYNtoXPZP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.DOWN, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMin, yMin, zMax, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMin, yMax, zMin, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMax, yMax, zMin, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMax, yMax, zMin, fr_uBR, fr_vBR);
    }

    public static void extcTriYPtoXNZN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.UP, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMax, yMin, zMax, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMax, yMax, zMin, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMin, yMin, zMax, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMin, yMin, zMax, fr_uTR, fr_vTR);
    }

    public static void extcTriYPtoXPZN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.UP, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMin, yMin, zMin, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMin, yMin, zMax, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMin, yMin, zMax, fr_uTL, fr_vTL);
    }

    public static void extcTriYPtoXNZP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.UP, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMax, yMin, zMax, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMax, yMin, zMin, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMin, yMax, zMin, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMin, yMax, zMin, fr_uBL, fr_vBL);
    }

    public static void extcTriYPtoXPZP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.UP, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMax, yMin, zMin, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMin, yMin, zMin, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uBL, fr_vBL);
    }

    public static void extcTriZNtoXNYP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.NORTH, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMax, yMin, zMax, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMin, yMin, zMin, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMin, yMin, zMin, fr_uBR, fr_vBR);
    }

    public static void extcTriZNtoXPYP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.NORTH, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMax, yMin, zMax, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMin, yMin, zMax, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMin, yMax, zMin, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMin, yMax, zMin, fr_uTR, fr_vTR);
    }

    public static void extcTriZNtoXNYN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.NORTH, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMax, yMin, zMin, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uTR, fr_vTR);
    }

    public static void extcTriZNtoXPYN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.NORTH, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMax, yMax, zMin, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMin, yMin, zMax, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uTR, fr_vTR);
    }

    public static void extcTriZPtoXNYP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.SOUTH, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMin, yMin, zMin, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMax, yMin, zMin, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uTR, fr_vTR);
    }

    public static void extcTriZPtoXPYP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.SOUTH, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMin, yMin, zMin, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMax, yMin, zMin, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMax, yMin, zMin, fr_uBR, fr_vBR);
    }

    public static void extcTriZPtoXNYN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.SOUTH, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMin, yMax, zMin, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMax, yMin, zMax, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMax, yMax, zMin, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMax, yMax, zMin, fr_uTR, fr_vTR);
    }

    public static void extcTriZPtoXPYN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.SOUTH, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMin, yMax, zMin, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMin, yMin, zMax, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMax, yMax, zMin, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMax, yMax, zMin, fr_uTR, fr_vTR);
    }

    public static void extcTriXNtoZNYP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.SOUTH, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMax, yMin, zMin, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMax, yMin, zMax, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uTR, fr_vTR);
    }

    public static void extcTriXNtoZPYP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.SOUTH, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMin, yMax, zMin, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMax, yMin, zMin, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMax, yMin, zMax, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMax, yMin, zMax, fr_uBR, fr_vBR);
    }

    public static void extcTriXNtoZNYN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.SOUTH, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMax, yMax, zMin, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMin, yMin, zMax, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMin, yMin, zMax, fr_uBR, fr_vBR);
    }

    public static void extcTriXNtoZPYN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.SOUTH, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMax, yMax, zMin, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMin, yMin, zMin, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMin, yMin, zMin, fr_uBL, fr_vBL);
    }

    public static void extcTriXPtoZNYP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.SOUTH, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMin, yMin, zMax, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMin, yMin, zMin, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMin, yMin, zMin, fr_uBR, fr_vBR);
    }

    public static void extcTriXPtoZPYP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.SOUTH, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMin, yMin, zMax, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMin, yMin, zMin, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMax, yMax, zMin, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMax, yMax, zMin, fr_uTR, fr_vTR);
    }

    public static void extcTriXPtoZNYN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.SOUTH, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMax, yMin, zMax, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMin, yMax, zMin, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uTL, fr_vTL);
    }

    public static void extcTriXPtoZPYN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.SOUTH, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMax, yMin, zMin, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMin, yMax, zMin, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uTL, fr_vTL);
    }

    public static void extcTriYNtoZNXN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.DOWN, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMin, yMin, zMax, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMax, yMax, zMin, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uBR, fr_vBR);
    }

    public static void extcTriYNtoZNXP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.DOWN, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMin, yMax, zMin, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMax, yMin, zMax, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMax, yMin, zMax, fr_uTL, fr_vTL);
    }

    public static void extcTriYNtoZPXN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.DOWN, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMin, yMin, zMin, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMax, yMax, zMin, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uBR, fr_vBR);
    }

    public static void extcTriYNtoZPXP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.DOWN, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMin, yMax, zMin, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMax, yMin, zMin, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMax, yMin, zMin, fr_uTR, fr_vTR);
    }

    public static void extcTriYPtoZNXN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.UP, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMax, yMin, zMax, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMax, yMin, zMin, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uBR, fr_vBR);
    }

    public static void extcTriYPtoZNXP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.UP, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMax, yMin, zMax, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMin, yMax, zMin, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMin, yMin, zMax, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMin, yMin, zMax, fr_uTR, fr_vTR);
    }

    public static void extcTriYPtoZPXN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.UP, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMax, yMin, zMin, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMin, yMin, zMin, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMin, yMin, zMin, fr_uTL, fr_vTL);
    }

    public static void extcTriYPtoZPXP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.UP, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMax, yMax, zMin, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMin, yMin, zMin, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMin, yMin, zMax, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMin, yMin, zMax, fr_uTL, fr_vTL);
    }

    public static void extcTriZNtoYPXN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.NORTH, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMax, yMax, zMin, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMax, yMin, zMax, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMin, yMin, zMax, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMin, yMin, zMax, fr_uBR, fr_vBR);
    }

    public static void extcTriZNtoYPXP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.NORTH, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMax, yMin, zMin, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMin, yMin, zMax, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uTR, fr_vTR);
    }

    public static void extcTriZNtoYNXN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.NORTH, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMax, yMin, zMax, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMin, yMax, zMin, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMin, yMax, zMin, fr_uTR, fr_vTR);
    }

    public static void extcTriZNtoYNXP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.NORTH, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMin, yMin, zMin, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uTR, fr_vTR);
    }

    public static void extcTriZPtoYPXN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.SOUTH, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMin, yMin, zMax, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMax, yMin, zMin, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMax, yMax, zMin, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMax, yMax, zMin, fr_uTR, fr_vTR);
    }

    public static void extcTriZPtoYPXP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.SOUTH, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMin, yMax, zMin, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMin, yMin, zMin, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMax, yMin, zMax, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMax, yMin, zMax, fr_uBR, fr_vBR);
    }

    public static void extcTriZPtoYNXN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.SOUTH, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMax, yMin, zMin, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMax, yMax, zMin, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMax, yMax, zMin, fr_uTR, fr_vTR);
    }

    public static void extcTriZPtoYNXP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.SOUTH, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMin, yMax, zMin, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMin, yMin, zMin, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uTR, fr_vTR);
    }

    public static void extcTriXNtoYPZN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.SOUTH, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMin, yMin, zMin, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMax, yMin, zMax, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uTR, fr_vTR);
    }

    public static void extcTriXNtoYPZP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.SOUTH, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMax, yMax, zMin, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMax, yMin, zMin, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMin, yMin, zMax, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMin, yMin, zMax, fr_uBR, fr_vBR);
    }

    public static void extcTriXNtoYNZN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.SOUTH, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMin, yMax, zMin, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMax, yMin, zMax, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMax, yMin, zMax, fr_uBR, fr_vBR);
    }

    public static void extcTriXNtoYNZP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.SOUTH, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMax, yMax, zMin, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMax, yMin, zMin, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMax, yMin, zMin, fr_uBL, fr_vBL);
    }

    public static void extcTriXPtoYPZN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.SOUTH, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMin, yMin, zMax, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMax, yMin, zMin, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMax, yMin, zMin, fr_uBR, fr_vBR);
    }

    public static void extcTriXPtoYPZP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.SOUTH, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMax, yMin, zMax, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMin, yMin, zMin, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMin, yMax, zMin, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMin, yMax, zMin, fr_uTR, fr_vTR);
    }

    public static void extcTriXPtoYNZN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.SOUTH, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMin, yMin, zMax, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMax, yMax, zMin, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uTL, fr_vTL);
    }

    public static void extcTriXPtoYNZP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.SOUTH, x, y, z, icon, selfIllum, color, fRot);
        setupVertex(renderBlocks, xMin, yMin, zMin, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMin, yMax, zMin, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uTL, fr_vTL);
    }

    public static void prismYNXN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.DOWN, x, y, z, icon, selfIllum, color, fRot);
        double uBM = fr_uBR - (fr_uBR - fr_uBL) / 2.0D;
        double zMid = zMax - (zMax - zMin) / 2.0D;
        double xMid = xMax - (xMax - xMin) / 2.0D;
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uTR, fr_vBL);
        setupVertex(renderBlocks, xMid, yMin, zMid, uBM, fr_vTR);
        setupVertex(renderBlocks, xMax, yMax, zMin, fr_uTL, fr_vBR);
        setupVertex(renderBlocks, xMax, yMax, zMin, fr_uTL, fr_vBR);
    }

    public static void prismYNZN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.DOWN, x, y, z, icon, selfIllum, color, fRot);
        double uBM = fr_uBR - (fr_uBR - fr_uBL) / 2.0D;
        double zMid = zMax - (zMax - zMin) / 2.0D;
        double xMid = xMax - (xMax - xMin) / 2.0D;
        setupVertex(renderBlocks, xMax, yMax, zMin, fr_uTR, fr_vBL);
        setupVertex(renderBlocks, xMid, yMin, zMid, uBM, fr_vTR);
        setupVertex(renderBlocks, xMin, yMax, zMin, fr_uTL, fr_vBR);
        setupVertex(renderBlocks, xMin, yMax, zMin, fr_uTL, fr_vBR);
    }

    public static void prismYNXP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.DOWN, x, y, z, icon, selfIllum, color, fRot);
        double uBM = fr_uBR - (fr_uBR - fr_uBL) / 2.0D;
        double zMid = zMax - (zMax - zMin) / 2.0D;
        double xMid = xMax - (xMax - xMin) / 2.0D;
        setupVertex(renderBlocks, xMin, yMax, zMin, fr_uTR, fr_vBL);
        setupVertex(renderBlocks, xMid, yMin, zMid, uBM, fr_vTR);
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uTL, fr_vBR);
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uTL, fr_vBR);
    }

    public static void prismYNZP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.DOWN, x, y, z, icon, selfIllum, color, fRot);
        double uBM = fr_uBR - (fr_uBR - fr_uBL) / 2.0D;
        double zMid = zMax - (zMax - zMin) / 2.0D;
        double xMid = xMax - (xMax - xMin) / 2.0D;
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uTR, fr_vBL);
        setupVertex(renderBlocks, xMid, yMin, zMid, uBM, fr_vTR);
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uTL, fr_vBR);
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uTL, fr_vBR);
    }

    public static void prismYPXN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.UP, x, y, z, icon, selfIllum, color, fRot);
        double uTM = fr_uTR - (fr_uTR - fr_uTL) / 2.0D;
        double zMid = zMax - (zMax - zMin) / 2.0D;
        double xMid = xMax - (xMax - xMin) / 2.0D;
        setupVertex(renderBlocks, xMid, yMax, zMid, uTM, fr_vBR);
        setupVertex(renderBlocks, xMax, yMin, zMax, fr_uTR, fr_vTL);
        setupVertex(renderBlocks, xMax, yMin, zMin, fr_uTL, fr_vTR);
        setupVertex(renderBlocks, xMax, yMin, zMin, fr_uTL, fr_vTR);
    }

    public static void prismYPZN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.UP, x, y, z, icon, selfIllum, color, fRot);
        double uTM = fr_uTR - (fr_uTR - fr_uTL) / 2.0D;
        double xMid = xMax - (xMax - xMin) / 2.0D;
        double zMid = zMax - (zMax - zMin) / 2.0D;
        setupVertex(renderBlocks, xMid, yMax, zMid, uTM, fr_vBL);
        setupVertex(renderBlocks, xMax, yMin, zMin, fr_uBR, fr_vTL);
        setupVertex(renderBlocks, xMin, yMin, zMin, fr_uBL, fr_vTR);
        setupVertex(renderBlocks, xMin, yMin, zMin, fr_uBL, fr_vTR);
    }

    public static void prismYPXP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.UP, x, y, z, icon, selfIllum, color, fRot);
        double uTM = fr_uTR - (fr_uTR - fr_uTL) / 2.0D;
        double zMid = zMax - (zMax - zMin) / 2.0D;
        double xMid = xMax - (xMax - xMin) / 2.0D;
        setupVertex(renderBlocks, xMid, yMax, zMid, uTM, fr_vBL);
        setupVertex(renderBlocks, xMin, yMin, zMin, fr_uBR, fr_vTL);
        setupVertex(renderBlocks, xMin, yMin, zMax, fr_uBL, fr_vTR);
        setupVertex(renderBlocks, xMin, yMin, zMax, fr_uBL, fr_vTR);
    }

    public static void prismYPZP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.UP, x, y, z, icon, selfIllum, color, fRot);
        double uTM = fr_uTR - (fr_uTR - fr_uTL) / 2.0D;
        double xMid = xMax - (xMax - xMin) / 2.0D;
        double zMid = zMax - (zMax - zMin) / 2.0D;
        setupVertex(renderBlocks, xMid, yMax, zMid, uTM, fr_vBL);
        setupVertex(renderBlocks, xMin, yMin, zMax, fr_uBR, fr_vTL);
        setupVertex(renderBlocks, xMax, yMin, zMax, fr_uBL, fr_vTR);
        setupVertex(renderBlocks, xMax, yMin, zMax, fr_uBL, fr_vTR);
    }

    public static void prismXPZN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.EAST, x, y, z, icon, selfIllum, color, fRot);
        double yMid = yMax - (yMax - yMin) / 2.0D;
        double zMid = zMax - (zMax - zMin) / 2.0D;
        double vML = fr_vTL - (fr_vTL - fr_vBL) / 2.0D;
        setupVertex(renderBlocks, xMax, yMid, zMid, fr_uTL, vML);
        setupVertex(renderBlocks, xMin, yMin, zMin, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMin, yMax, zMin, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMin, yMax, zMin, fr_uTR, fr_vTR);
    }

    public static void prismXPYP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.EAST, x, y, z, icon, selfIllum, color, fRot);
        double yMid = yMax - (yMax - yMin) / 2.0D;
        double zMid = zMax - (zMax - zMin) / 2.0D;
        double uBM = fr_uBR - (fr_uBR - fr_uBL) / 2.0D;
        setupVertex(renderBlocks, xMax, yMid, zMid, uBM, fr_vBR);
        setupVertex(renderBlocks, xMin, yMax, zMin, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uTL, fr_vTL);
    }

    public static void prismXPZP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.EAST, x, y, z, icon, selfIllum, color, fRot);
        double yMid = yMax - (yMax - yMin) / 2.0D;
        double zMid = zMax - (zMax - zMin) / 2.0D;
        double vMR = fr_vTR - (fr_vTR - fr_vBR) / 2.0D;
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMin, yMin, zMax, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMax, yMid, zMid, fr_uBR, vMR);
        setupVertex(renderBlocks, xMax, yMid, zMid, fr_uBR, vMR);
    }

    public static void prismXPYN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.EAST, x, y, z, icon, selfIllum, color, fRot);
        double yMid = yMax - (yMax - yMin) / 2.0D;
        double zMid = zMax - (zMax - zMin) / 2.0D;
        double uTM = fr_uTR - (fr_uTR - fr_uTL) / 2.0D;
        setupVertex(renderBlocks, xMin, yMin, zMax, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMin, yMin, zMin, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMax, yMid, zMid, uTM, fr_vTL);
        setupVertex(renderBlocks, xMax, yMid, zMid, uTM, fr_vTL);
    }

    public static void prismXNZN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.WEST, x, y, z, icon, selfIllum, color, fRot);
        double yMid = yMax - (yMax - yMin) / 2.0D;
        double zMid = zMax - (zMax - zMin) / 2.0D;
        double vML = fr_vTL - (fr_vTL - fr_vBL) / 2.0D;
        setupVertex(renderBlocks, xMin, yMid, zMid, fr_uBL, vML);
        setupVertex(renderBlocks, xMax, yMin, zMax, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uTR, fr_vTR);
    }

    public static void prismXNYP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.WEST, x, y, z, icon, selfIllum, color, fRot);
        double uBM = fr_uBR - (fr_uBR - fr_uBL) / 2.0D;
        double zMid = zMax - (zMax - zMin) / 2.0D;
        double yMid = yMax - (yMax - yMin) / 2.0D;
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMax, yMax, zMin, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMin, yMid, zMid, uBM, fr_vBL);
        setupVertex(renderBlocks, xMin, yMid, zMid, uBM, fr_vBL);
    }

    public static void prismXNZP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.WEST, x, y, z, icon, selfIllum, color, fRot);
        double yMid = yMax - (yMax - yMin) / 2.0D;
        double zMid = zMax - (zMax - zMin) / 2.0D;
        double vMR = fr_vTR - (fr_vTR - fr_vBR) / 2.0D;
        setupVertex(renderBlocks, xMax, yMax, zMin, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMax, yMin, zMin, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMin, yMid, zMid, fr_uBR, vMR);
        setupVertex(renderBlocks, xMin, yMid, zMid, fr_uBR, vMR);
    }

    public static void prismXNYN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.WEST, x, y, z, icon, selfIllum, color, fRot);
        double uTM = fr_uTR - (fr_uTR - fr_uTL) / 2.0D;
        double zMid = zMax - (zMax - zMin) / 2.0D;
        double yMid = yMax - (yMax - yMin) / 2.0D;
        setupVertex(renderBlocks, xMin, yMid, zMid, uTM, fr_vTR);
        setupVertex(renderBlocks, xMax, yMin, zMin, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMax, yMin, zMax, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMax, yMin, zMax, fr_uBR, fr_vBR);
    }

    public static void prismZPXN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.SOUTH, x, y, z, icon, selfIllum, color, fRot);
        double xMid = xMax - (xMax - xMin) / 2.0D;
        double yMid = yMax - (yMax - yMin) / 2.0D;
        double vMR = fr_vTR - (fr_vTR - fr_vBR) / 2.0D;
        setupVertex(renderBlocks, xMin, yMax, zMin, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMin, yMin, zMin, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMid, yMid, zMax, fr_uBR, vMR);
        setupVertex(renderBlocks, xMid, yMid, zMax, fr_uBR, vMR);
    }

    public static void prismZPYP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.SOUTH, x, y, z, icon, selfIllum, color, fRot);
        double uBM = fr_uBR - (fr_uBR - fr_uBL) / 2.0D;
        double yMid = yMax - (yMax - yMin) / 2.0D;
        double xMid = xMax - (xMax - xMin) / 2.0D;
        setupVertex(renderBlocks, xMid, yMid, zMax, uBM, fr_vBR);
        setupVertex(renderBlocks, xMax, yMax, zMin, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMin, yMax, zMin, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMin, yMax, zMin, fr_uTL, fr_vTL);
    }

    public static void prismZPXP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.SOUTH, x, y, z, icon, selfIllum, color, fRot);
        double yMid = yMax - (yMax - yMin) / 2.0D;
        double xMid = xMax - (xMax - xMin) / 2.0D;
        double vML = fr_vTL - (fr_vTL - fr_vBL) / 2.0D;
        setupVertex(renderBlocks, xMid, yMid, zMax, fr_uTL, vML);
        setupVertex(renderBlocks, xMax, yMin, zMin, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMax, yMax, zMin, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMax, yMax, zMin, fr_uTR, fr_vTR);
    }

    public static void prismZPYN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.SOUTH, x, y, z, icon, selfIllum, color, fRot);
        double uTM = fr_uTR - (fr_uTR - fr_uTL) / 2.0D;
        double yMid = yMax - (yMax - yMin) / 2.0D;
        double xMid = xMax - (xMax - xMin) / 2.0D;
        setupVertex(renderBlocks, xMid, yMid, zMax, uTM, fr_vTL);
        setupVertex(renderBlocks, xMin, yMin, zMin, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMax, yMin, zMin, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMax, yMin, zMin, fr_uBR, fr_vBR);
    }

    public static void prismZNXN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.NORTH, x, y, z, icon, selfIllum, color, fRot);
        double xMid = xMax - (xMax - xMin) / 2.0D;
        double yMid = yMax - (yMax - yMin) / 2.0D;
        double vML = fr_vTL - (fr_vTL - fr_vBL) / 2.0D;
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMax, yMin, zMax, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMid, yMid, zMin, fr_uTR, vML);
        setupVertex(renderBlocks, xMid, yMid, zMin, fr_uTR, vML);
    }

    public static void prismZNYP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.NORTH, x, y, z, icon, selfIllum, color, fRot);
        double uBM = fr_uBR - (fr_uBR - fr_uBL) / 2.0D;
        double xMid = xMax - (xMax - xMin) / 2.0D;
        double yMid = yMax - (yMax - yMin) / 2.0D;
        setupVertex(renderBlocks, xMid, yMid, zMin, uBM, fr_vBR);
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uTL, fr_vTL);
        setupVertex(renderBlocks, xMax, yMax, zMax, fr_uTL, fr_vTL);
    }

    public static void prismZNXP(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.NORTH, x, y, z, icon, selfIllum, color, fRot);
        double xMid = xMax - (xMax - xMin) / 2.0D;
        double yMid = yMax - (yMax - yMin) / 2.0D;
        double vML = fr_vTL - (fr_vTL - fr_vBL) / 2.0D;
        setupVertex(renderBlocks, xMid, yMid, zMin, fr_uBL, vML);
        setupVertex(renderBlocks, xMin, yMin, zMax, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uTR, fr_vTR);
        setupVertex(renderBlocks, xMin, yMax, zMax, fr_uTR, fr_vTR);
    }

    public static void prismZNYN(RenderBlocks renderBlocks, double x, double y, double z, IIcon icon, float selfIllum, int color, int fRot) {
        prepareFace(renderBlocks, ForgeDirection.NORTH, x, y, z, icon, selfIllum, color, fRot);
        double uTM = fr_uTR - (fr_uTR - fr_uTL) / 2.0D;
        double xMid = xMax - (xMax - xMin) / 2.0D;
        double yMid = yMax - (yMax - yMin) / 2.0D;
        setupVertex(renderBlocks, xMid, yMid, zMin, uTM, fr_vTR);
        setupVertex(renderBlocks, xMax, yMin, zMax, fr_uBL, fr_vBL);
        setupVertex(renderBlocks, xMin, yMin, zMax, fr_uBR, fr_vBR);
        setupVertex(renderBlocks, xMin, yMin, zMax, fr_uBR, fr_vBR);
    }

    private static void setupVertex(RenderBlocks renderBlocks, double x, double y, double z, double u, double v) {
        Tessellator.instance.addVertexWithUV(x, y, z, u, v);
    }

    private static void prepareFace(RenderBlocks renderBlocks, ForgeDirection side, double x, double y, double z, IIcon icon, float selfIllum, int color, int faceRotation) {
        float r = (float)(color >> 16 & 255) / 255.0F;
        float g = (float)(color >> 8 & 255) / 255.0F;
        float b = (float)(color & 255) / 255.0F;
        
        Tessellator.instance.setColorOpaque_F(r * selfIllum, g * selfIllum, b * selfIllum);
        
        boolean flipV = (faceRotation & 4) == 4;
        
        boolean flipH = (faceRotation & 8) == 8;
        xMin = x + renderBlocks.renderMinX;
        xMax = x + renderBlocks.renderMaxX;
        yMin = y + renderBlocks.renderMinY;
        yMax = y + renderBlocks.renderMaxY;
        zMin = z + renderBlocks.renderMinZ;
        zMax = z + renderBlocks.renderMaxZ;
        
        float theL = 0.0F;
        float theR = 0.0F;
        float theT = 0.0F;
        float theB = 0.0F;
        
        int fRot = faceRotation % 4;
        switch(side) {
        case DOWN:
            switch(fRot) {
            case 0:
                theL = icon.getInterpolatedU(renderBlocks.renderMaxX * 16.0D);
                theR = icon.getInterpolatedU(renderBlocks.renderMinX * 16.0D);
                theT = icon.getInterpolatedV(16.0D - renderBlocks.renderMinZ * 16.0D);
                theB = icon.getInterpolatedV(16.0D - renderBlocks.renderMaxZ * 16.0D);
                prepareUVs((double)theR, (double)theB, (double)theR, (double)theT, (double)theL, (double)theT, (double)theL, (double)theB);
                return;
            case 1:
                theL = icon.getInterpolatedU(16.0D - renderBlocks.renderMaxZ * 16.0D);
                theR = icon.getInterpolatedU(16.0D - renderBlocks.renderMinZ * 16.0D);
                theT = icon.getInterpolatedV(16.0D - renderBlocks.renderMaxX * 16.0D);
                theB = icon.getInterpolatedV(16.0D - renderBlocks.renderMinX * 16.0D);
                prepareUVs((double)theL, (double)theB, (double)theR, (double)theB, (double)theR, (double)theT, (double)theL, (double)theT);
                return;
            case 2:
                theL = icon.getInterpolatedU(16.0D - renderBlocks.renderMaxX * 16.0D);
                theR = icon.getInterpolatedU(16.0D - renderBlocks.renderMinX * 16.0D);
                theT = icon.getInterpolatedV(renderBlocks.renderMinZ * 16.0D);
                theB = icon.getInterpolatedV(renderBlocks.renderMaxZ * 16.0D);
                prepareUVs((double)theR, (double)theB, (double)theR, (double)theT, (double)theL, (double)theT, (double)theL, (double)theB);
                return;
            case 3:
                theL = icon.getInterpolatedU(renderBlocks.renderMaxZ * 16.0D);
                theR = icon.getInterpolatedU(renderBlocks.renderMinZ * 16.0D);
                theT = icon.getInterpolatedV(renderBlocks.renderMaxX * 16.0D);
                theB = icon.getInterpolatedV(renderBlocks.renderMinX * 16.0D);
                prepareUVs((double)theL, (double)theB, (double)theR, (double)theB, (double)theR, (double)theT, (double)theL, (double)theT);
                return;
            default:
                return;
            }
        case UP:
            switch(fRot) {
            case 0:
                theL = icon.getInterpolatedU(16.0D - renderBlocks.renderMinX * 16.0D);
                theR = icon.getInterpolatedU(16.0D - renderBlocks.renderMaxX * 16.0D);
                theT = icon.getInterpolatedV(16.0D - renderBlocks.renderMinZ * 16.0D);
                theB = icon.getInterpolatedV(16.0D - renderBlocks.renderMaxZ * 16.0D);
                prepareUVs((double)theR, (double)theB, (double)theR, (double)theT, (double)theL, (double)theT, (double)theL, (double)theB);
                return;
            case 1:
                theL = icon.getInterpolatedU(16.0D - renderBlocks.renderMaxZ * 16.0D);
                theR = icon.getInterpolatedU(16.0D - renderBlocks.renderMinZ * 16.0D);
                theT = icon.getInterpolatedV(renderBlocks.renderMinX * 16.0D);
                theB = icon.getInterpolatedV(renderBlocks.renderMaxX * 16.0D);
                prepareUVs((double)theL, (double)theB, (double)theR, (double)theB, (double)theR, (double)theT, (double)theL, (double)theT);
                return;
            case 2:
                theL = icon.getInterpolatedU(renderBlocks.renderMinX * 16.0D);
                theR = icon.getInterpolatedU(renderBlocks.renderMaxX * 16.0D);
                theT = icon.getInterpolatedV(renderBlocks.renderMinZ * 16.0D);
                theB = icon.getInterpolatedV(renderBlocks.renderMaxZ * 16.0D);
                prepareUVs((double)theR, (double)theB, (double)theR, (double)theT, (double)theL, (double)theT, (double)theL, (double)theB);
                return;
            case 3:
                theL = icon.getInterpolatedU(renderBlocks.renderMaxZ * 16.0D);
                theR = icon.getInterpolatedU(renderBlocks.renderMinZ * 16.0D);
                theT = icon.getInterpolatedV(16.0D - renderBlocks.renderMinX * 16.0D);
                theB = icon.getInterpolatedV(16.0D - renderBlocks.renderMaxX * 16.0D);
                prepareUVs((double)theL, (double)theB, (double)theR, (double)theB, (double)theR, (double)theT, (double)theL, (double)theT);
                return;
            default:
                return;
            }
        case NORTH:
            switch(fRot) {
            case 0:
                theL = icon.getInterpolatedU(16.0D - renderBlocks.renderMaxX * 16.0D);
                theR = icon.getInterpolatedU(16.0D - renderBlocks.renderMinX * 16.0D);
                theT = icon.getInterpolatedV(16.0D - renderBlocks.renderMinY * 16.0D);
                theB = icon.getInterpolatedV(16.0D - renderBlocks.renderMaxY * 16.0D);
                prepareUVs((double)theL, (double)theB, (double)theL, (double)theT, (double)theR, (double)theT, (double)theR, (double)theB);
                return;
            case 1:
                theL = icon.getInterpolatedU(16.0D - renderBlocks.renderMaxY * 16.0D);
                theR = icon.getInterpolatedU(16.0D - renderBlocks.renderMinY * 16.0D);
                theT = icon.getInterpolatedV(renderBlocks.renderMaxX * 16.0D);
                theB = icon.getInterpolatedV(renderBlocks.renderMinX * 16.0D);
                prepareUVs((double)theL, (double)theT, (double)theR, (double)theT, (double)theR, (double)theB, (double)theL, (double)theB);
                return;
            case 2:
                theL = icon.getInterpolatedU(renderBlocks.renderMaxX * 16.0D);
                theR = icon.getInterpolatedU(renderBlocks.renderMinX * 16.0D);
                theT = icon.getInterpolatedV(renderBlocks.renderMinY * 16.0D);
                theB = icon.getInterpolatedV(renderBlocks.renderMaxY * 16.0D);
                prepareUVs((double)theL, (double)theB, (double)theL, (double)theT, (double)theR, (double)theT, (double)theR, (double)theB);
                return;
            case 3:
                theL = icon.getInterpolatedU(renderBlocks.renderMaxY * 16.0D);
                theR = icon.getInterpolatedU(renderBlocks.renderMinY * 16.0D);
                theT = icon.getInterpolatedV(16.0D - renderBlocks.renderMaxX * 16.0D);
                theB = icon.getInterpolatedV(16.0D - renderBlocks.renderMinX * 16.0D);
                prepareUVs((double)theL, (double)theT, (double)theR, (double)theT, (double)theR, (double)theB, (double)theL, (double)theB);
                return;
            default:
                return;
            }
        case SOUTH:
            switch(fRot) {
            case 0:
                theL = icon.getInterpolatedU(renderBlocks.renderMinX * 16.0D);
                theR = icon.getInterpolatedU(renderBlocks.renderMaxX * 16.0D);
                theT = icon.getInterpolatedV(16.0D - renderBlocks.renderMinY * 16.0D);
                theB = icon.getInterpolatedV(16.0D - renderBlocks.renderMaxY * 16.0D);
                prepareUVs((double)theL, (double)theB, (double)theL, (double)theT, (double)theR, (double)theT, (double)theR, (double)theB);
                return;
            case 1:
                theL = icon.getInterpolatedU(16.0D - renderBlocks.renderMaxY * 16.0D);
                theR = icon.getInterpolatedU(16.0D - renderBlocks.renderMinY * 16.0D);
                theT = icon.getInterpolatedV(16.0D - renderBlocks.renderMinX * 16.0D);
                theB = icon.getInterpolatedV(16.0D - renderBlocks.renderMaxX * 16.0D);
                prepareUVs((double)theL, (double)theT, (double)theR, (double)theT, (double)theR, (double)theB, (double)theL, (double)theB);
                return;
            case 2:
                theL = icon.getInterpolatedU(16.0D - renderBlocks.renderMinX * 16.0D);
                theR = icon.getInterpolatedU(16.0D - renderBlocks.renderMaxX * 16.0D);
                theT = icon.getInterpolatedV(renderBlocks.renderMinY * 16.0D);
                theB = icon.getInterpolatedV(renderBlocks.renderMaxY * 16.0D);
                prepareUVs((double)theL, (double)theB, (double)theL, (double)theT, (double)theR, (double)theT, (double)theR, (double)theB);
                return;
            case 3:
                theL = icon.getInterpolatedU(renderBlocks.renderMaxY * 16.0D);
                theR = icon.getInterpolatedU(renderBlocks.renderMinY * 16.0D);
                theT = icon.getInterpolatedV(renderBlocks.renderMinX * 16.0D);
                theB = icon.getInterpolatedV(renderBlocks.renderMaxX * 16.0D);
                prepareUVs((double)theL, (double)theT, (double)theR, (double)theT, (double)theR, (double)theB, (double)theL, (double)theB);
                return;
            default:
                return;
            }
        case WEST:
            switch(fRot) {
            case 0:
                theL = icon.getInterpolatedU(renderBlocks.renderMinZ * 16.0D);
                theR = icon.getInterpolatedU(renderBlocks.renderMaxZ * 16.0D);
                theB = icon.getInterpolatedV(16.0D - renderBlocks.renderMaxY * 16.0D);
                theT = icon.getInterpolatedV(16.0D - renderBlocks.renderMinY * 16.0D);
                prepareUVs((double)theL, (double)theB, (double)theL, (double)theT, (double)theR, (double)theT, (double)theR, (double)theB);
                return;
            case 1:
                theL = icon.getInterpolatedU(16.0D - renderBlocks.renderMaxY * 16.0D);
                theR = icon.getInterpolatedU(16.0D - renderBlocks.renderMinY * 16.0D);
                theT = icon.getInterpolatedV(16.0D - renderBlocks.renderMinZ * 16.0D);
                theB = icon.getInterpolatedV(16.0D - renderBlocks.renderMaxZ * 16.0D);
                prepareUVs((double)theL, (double)theT, (double)theR, (double)theT, (double)theR, (double)theB, (double)theL, (double)theB);
                return;
            case 2:
                theL = icon.getInterpolatedU(16.0D - renderBlocks.renderMinZ * 16.0D);
                theR = icon.getInterpolatedU(16.0D - renderBlocks.renderMaxZ * 16.0D);
                theT = icon.getInterpolatedV(renderBlocks.renderMinY * 16.0D);
                theB = icon.getInterpolatedV(renderBlocks.renderMaxY * 16.0D);
                prepareUVs((double)theL, (double)theB, (double)theL, (double)theT, (double)theR, (double)theT, (double)theR, (double)theB);
                return;
            case 3:
                theL = icon.getInterpolatedU(renderBlocks.renderMaxY * 16.0D);
                theR = icon.getInterpolatedU(renderBlocks.renderMinY * 16.0D);
                theT = icon.getInterpolatedV(renderBlocks.renderMinZ * 16.0D);
                theB = icon.getInterpolatedV(renderBlocks.renderMaxZ * 16.0D);
                prepareUVs((double)theL, (double)theT, (double)theR, (double)theT, (double)theR, (double)theB, (double)theL, (double)theB);
                return;
            default:
                return;
            }
        case EAST:
            switch(fRot) {
            case 0:
                theL = icon.getInterpolatedU(16.0D - renderBlocks.renderMaxZ * 16.0D);
                theR = icon.getInterpolatedU(16.0D - renderBlocks.renderMinZ * 16.0D);
                theB = icon.getInterpolatedV(16.0D - renderBlocks.renderMaxY * 16.0D);
                theT = icon.getInterpolatedV(16.0D - renderBlocks.renderMinY * 16.0D);
                prepareUVs((double)theL, (double)theB, (double)theL, (double)theT, (double)theR, (double)theT, (double)theR, (double)theB);
                break;
            case 1:
                theL = icon.getInterpolatedU(16.0D - renderBlocks.renderMaxY * 16.0D);
                theR = icon.getInterpolatedU(16.0D - renderBlocks.renderMinY * 16.0D);
                theT = icon.getInterpolatedV(renderBlocks.renderMaxZ * 16.0D);
                theB = icon.getInterpolatedV(renderBlocks.renderMinZ * 16.0D);
                prepareUVs((double)theL, (double)theT, (double)theR, (double)theT, (double)theR, (double)theB, (double)theL, (double)theB);
                break;
            case 2:
                theL = icon.getInterpolatedU(renderBlocks.renderMaxZ * 16.0D);
                theR = icon.getInterpolatedU(renderBlocks.renderMinZ * 16.0D);
                theT = icon.getInterpolatedV(renderBlocks.renderMinY * 16.0D);
                theB = icon.getInterpolatedV(renderBlocks.renderMaxY * 16.0D);
                prepareUVs((double)theL, (double)theB, (double)theL, (double)theT, (double)theR, (double)theT, (double)theR, (double)theB);
                break;
            case 3:
                theL = icon.getInterpolatedU(renderBlocks.renderMaxY * 16.0D);
                theR = icon.getInterpolatedU(renderBlocks.renderMinY * 16.0D);
                theT = icon.getInterpolatedV(16.0D - renderBlocks.renderMaxZ * 16.0D);
                theB = icon.getInterpolatedV(16.0D - renderBlocks.renderMinZ * 16.0D);
                prepareUVs((double)theL, (double)theT, (double)theR, (double)theT, (double)theR, (double)theB, (double)theL, (double)theB);
            }
        }

    }

    private static void prepareUVs(double t_uTL, double t_vTL, double t_uBL, double t_vBL, double t_uBR, double t_vBR, double t_uTR, double t_vTR) {
        fr_uTL = t_uTL;
        fr_vTL = t_vTL;
        fr_uBL = t_uBL;
        fr_vBL = t_vBL;
        fr_uBR = t_uBR;
        fr_vBR = t_vBR;
        fr_uTR = t_uTR;
        fr_vTR = t_vTR;
    }
}
