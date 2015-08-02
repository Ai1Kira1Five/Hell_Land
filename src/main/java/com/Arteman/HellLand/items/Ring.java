package com.Arteman.HellLand.items;

import java.util.List;

import com.Arteman.HellLand.HellLand;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.BlockDispenser;
import net.minecraft.command.IEntitySelector;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBehaviorDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.ISpecialArmor;

public class Ring extends ItemArmor implements ISpecialArmor
{
	
	/*
	 *		\\					  //	||		||=====\\
	 *		 \\					 //				||		\\
	 *		  \\      //\\      //		||		||		//
	 *		   \\	 //	 \\    //		||		||=====//
	 *		    \\  //	  \\  //		||		||
	 *		     \\//	   \\//			||		||
	 */
	
	private String name = "ring";
	private static final int[] maxDamageArray = new int[] {11, 16, 15, 13};
	public final int armorType;
	public final int damageReduceAmount;
	public final int renderIndex;
	private ArmorMaterial material;
	
	private static final String __OBFID = "CL_00001766";
	
	private static final IBehaviorDispenseItem dispenserBehavior = new BehaviorDefaultDispenseItem()
	{
		private static final String __OBFID = "CL_00001767";
		
		protected ItemStack dispenseStack(IBlockSource blSource, ItemStack itemStack)
		{
			EnumFacing enumfacing = BlockDispenser.func_149937_b(blSource.getBlockMetadata());
            int i = blSource.getXInt() + enumfacing.getFrontOffsetX();
            int j = blSource.getYInt() + enumfacing.getFrontOffsetY();
            int k = blSource.getZInt() + enumfacing.getFrontOffsetZ();
            AxisAlignedBB axisalignedbb = AxisAlignedBB.getBoundingBox((double)i, (double)j, (double)k, (double)(i + 1), (double)(j + 1), (double)(k + 1));
            List list = blSource.getWorld().selectEntitiesWithinAABB(EntityLivingBase.class, axisalignedbb, new IEntitySelector.ArmoredMob(itemStack));

            if (list.size() > 0)
            {
                EntityLivingBase entitylivingbase = (EntityLivingBase)list.get(0);
                int l = entitylivingbase instanceof EntityPlayer ? 1 : 0;
                int i1 = EntityLiving.getArmorPosition(itemStack);
                ItemStack itemstack1 = itemStack.copy();
                itemstack1.stackSize = 1;
                entitylivingbase.setCurrentItemOrArmor(i1 - l, itemstack1);

                if (entitylivingbase instanceof EntityLiving)
                {
                    ((EntityLiving)entitylivingbase).setEquipmentDropChance(i1, 2.0F);
                }

                --itemStack.stackSize;
                return itemStack;
            }
            else
            {
                return super.dispenseStack(blSource, itemStack);
            }
        }
	};
	
	public Ring(ArmorMaterial material, int renderIndex, int armorType)
	{
		super(material, renderIndex, armorType);
		this.material = material;
        this.armorType = armorType;
        this.renderIndex = renderIndex;
        this.setMaxStackSize(1);
        this.setCreativeTab(HellLand.HellMCTab);
        this.setFull3D();
        this.setMaxDamage(material.getDurability(armorType));
        this.damageReduceAmount = material.getDamageReductionAmount(armorType);
        BlockDispenser.dispenseBehaviorRegistry.putObject(this, dispenserBehavior);
	}

	@Override
	public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot)
	{
		return null;
	}

	@Override
	public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot)
	{
		return 0;
	}

	@Override
	public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot)
	{
		
	}
	
	public int getItemEnchantability()
    {
        return this.material.getEnchantability();
    }
	
	public ItemArmor.ArmorMaterial getArmorMaterial()
    {
        return this.material;
    }
}
