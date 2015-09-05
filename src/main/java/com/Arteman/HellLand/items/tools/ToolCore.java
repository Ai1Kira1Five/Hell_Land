package com.Arteman.HellLand.items.tools;

import com.Arteman.HellLand.HellLand;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import java.util.Random;

public class ToolCore extends Item {
    protected Random random = new Random();
    protected int damageForEntity;

    public ToolCore(int baseDamage) {
        super();
        this.maxStackSize = 1;
        this.setMaxDamage(200);
        this.setCreativeTab(HellLand.HellMCTab);
    }

    public void onEntityDamaged(World world, EntityLivingBase player, Entity entity) {

    }

    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
        return AbilityHelper.onLeftClickEntity(stack, player, entity, this, 0);
    }
    
    public static void removeBlockWithDrops(EntityPlayer player, ItemStack stack, World world, int x, int y, int z, int bx, int by, int bz, Block block, boolean silk, int fortune, float blockHardness, boolean dispose) {
		removeBlockWithDrops(player, stack, world, x, y, z, bx, by, bz, block, silk, fortune, blockHardness, dispose, true);
	}

	public static void removeBlockWithDrops(EntityPlayer player, ItemStack stack, World world, int x, int y, int z, int bx, int by, int bz, Block block, boolean silk, int fortune, float blockHardness, boolean dispose, boolean particles) {
		if(!world.blockExists(x, y, z))
			return;

		Block blk = world.getBlock(x, y, z);
		int meta = world.getBlockMetadata(x, y, z);

		if(block != null && blk != block)
			return;

		Material mat = world.getBlock(x, y, z).getMaterial();
		if(!world.isRemote && blk != null && !blk.isAir(world, x, y, z) && blk.getPlayerRelativeBlockHardness(player, world, x, y, z) > 0) {
			if(!blk.canHarvestBlock(player, meta))
				return;

			if(!player.capabilities.isCreativeMode) {
				int localMeta = world.getBlockMetadata(x, y, z);
				blk.onBlockHarvested(world, x, y, z, localMeta, player);

				if(blk.removedByPlayer(world, player, x, y, z, true)) {
					blk.onBlockDestroyedByPlayer(world, x, y, z, localMeta);

					if(!dispose)
						blk.harvestBlock(world, player, x, y, z, localMeta);
				}
			} else world.setBlockToAir(x, y, z);

			if(particles && !world.isRemote)
				world.playAuxSFX(2001, x, y, z, Block.getIdFromBlock(blk) + (meta << 12));
		}
	}
	
	public static void removeBlocksInIteration(EntityPlayer player, ItemStack stack, World world, int x, int y, int z, int xs, int ys, int zs, int xe, int ye, int ze, Block block, boolean silk, int fortune, boolean dispose) {
		float blockHardness = block == null ? 1F : block.getBlockHardness(world, x, y, z);

		for(int x1 = xs; x1 < xe; x1++)
			for(int y1 = ys; y1 < ye; y1++)
				for(int z1 = zs; z1 < ze; z1++)
					removeBlockWithDrops(player, stack, world, x1 + x, y1 + y, z1 + z, x, y, z, block, silk, fortune, blockHardness, dispose);
	}
	
	public static MovingObjectPosition raytraceFromEntity(World world, Entity player, boolean par3, double range) {
		float f = 1.0F;
		float f1 = player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch) * f;
		float f2 = player.prevRotationYaw + (player.rotationYaw - player.prevRotationYaw) * f;
		double d0 = player.prevPosX + (player.posX - player.prevPosX) * f;
		double d1 = player.prevPosY + (player.posY - player.prevPosY) * f;
		if (!world.isRemote && player instanceof EntityPlayer)
			d1 += 1.62D;
		double d2 = player.prevPosZ + (player.posZ - player.prevPosZ) * f;
		Vec3 vec3 = Vec3.createVectorHelper(d0, d1, d2);
		float f3 = MathHelper.cos(-f2 * 0.017453292F - (float) Math.PI);
		float f4 = MathHelper.sin(-f2 * 0.017453292F - (float) Math.PI);
		float f5 = -MathHelper.cos(-f1 * 0.017453292F);
		float f6 = MathHelper.sin(-f1 * 0.017453292F);
		float f7 = f4 * f5;
		float f8 = f3 * f5;
		double d3 = range;
		Vec3 vec31 = vec3.addVector(f7 * d3, f6 * d3, f8 * d3);
		return world.rayTraceBlocks(vec3, vec31, par3);
	}
}
