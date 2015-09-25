package com.Arteman.HellLand.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockAnvil;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.Arteman.HellLand.HellLand;
import com.Arteman.HellLand.utils.BlockHell;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class mForge extends BlockAnvil{
    private static final String[] forgeIconNames = new String[] {"forge_top_damaged_0", "forge_top_damaged_1", "forge_top_damaged_2"};
    
    @SideOnly(Side.CLIENT)
    private IIcon[] forgeIcons;
    
    public mForge(){
    	super();
    	this.setHardness(5.0f);
    	this.setStepSound(soundTypeAnvil);
    	this.setResistance(2000.0f);
    	this.setBlockName(HellLand.MODID + ":" + "mForge");
    	this.setCreativeTab(HellLand.HellMCTab);
    	GameRegistry.registerBlock(this, HellLand.MODID + ":" + "mForge");
    }
    
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
      if(this.anvilRenderSide == 3 && side == 1)
      {
        int k = (meta >> 2) % this.forgeIcons.length;
        return this.forgeIcons[k];
      }
      else
      {
        return this.blockIcon;
      }
    }
    
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register)
    {
      this.blockIcon = register.registerIcon(HellLand.MODID + ":" + "forge_base");
      this.forgeIcons = new IIcon[forgeIconNames.length];

      for (int i = 0; i < this.forgeIcons.length; ++i)
      {
        this.forgeIcons[i] = register.registerIcon(HellLand.MODID + ":" + forgeIconNames[i]);
      }
    }

    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
      return new ItemStack(this, 1, world.getBlockMetadata(x, y, z) >> 2);
    }
}
