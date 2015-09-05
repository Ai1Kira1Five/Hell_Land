package com.Arteman.HellLand.items;

import com.Arteman.HellLand.HellLand;
import com.Arteman.HellLand.items.tools.ToolCore;
import com.google.common.collect.ImmutableSet;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.Random;
import java.util.Set;

public class MagicHammer extends ItemPickaxe {
    private String name = "magicHammer";

    public final ToolMaterial toolMaterial;

    public MagicHammer(ToolMaterial enumToolMaterial) {
        super(enumToolMaterial);
        toolMaterial = enumToolMaterial;
        this.setUnlocalizedName(HellLand.MODID + ":" + name);
        this.setTextureName(HellLand.MODID + ":" + name);
        this.setCreativeTab(HellLand.HellMCTab);
        GameRegistry.registerItem(this, name);
    }
    
    @Override
    public Set<String> getToolClasses(ItemStack stack) {
        return ImmutableSet.of("pickaxe", "sword");
    }
    
    
    public void onItemUse(World world, int x, int y, int z, Random rand, Item item, ItemStack itemStack, EntityPlayer player, EntityLivingBase entity){
    	if (!world.isRemote && item.onLeftClickEntity(itemStack, player, entity))
    	{
    	    double motionX = rand.nextGaussian() * 0.02D;
    	    double motionY = rand.nextGaussian() * 0.02D;
    	    double motionZ = rand.nextGaussian() * 0.02D;
    	    world.spawnParticle(
    	          "magicCrit", 
    	          x + rand.nextFloat(), 
    	          y + 0.5D + rand.nextFloat(), 
    	          z + rand.nextFloat(), 
    	          motionX, 
    	          motionY, 
    	          motionZ);
    	}
    }
    
    //wip короче что-то я в конец запутался в расчётах... надо переключиться...
    @Override
	public boolean onBlockStartBreak(ItemStack stack, int x, int y, int z, EntityPlayer player) {
		MovingObjectPosition raycast = ToolCore.raytraceFromEntity(player.worldObj, player, true, 10);
		if(raycast != null) {
			breakOtherBlock(player, stack, x, y, z, x, y, z, raycast.sideHit);
		}

		return false;
	}
    
    public void breakOtherBlock(EntityPlayer player, ItemStack stack, int x, int y, int z, int originX, int originY, int originZ, int side) {

		World world = player.worldObj;
		Material mat = world.getBlock(x, y, z).getMaterial();
		
		if(world.isAirBlock(x, y, z))
			return;

		ForgeDirection direction = ForgeDirection.getOrientation(side);
		int fortune = EnchantmentHelper.getFortuneModifier(player);
		boolean silk = EnchantmentHelper.getSilkTouchModifier(player);

		//ToolCore.removeBlocksInIteration(player, stack, world, x, y, z, fortune, fortune, fortune, fortune, fortune, fortune, null, null, silk, fortune, silk);
	}
}
