package com.Arteman.HellLand.blocks;

import java.util.List;

import net.minecraft.block.Block;
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
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import com.Arteman.HellLand.HellLand;
import com.Arteman.HellLand.utils.BlockHell;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class mForge extends BlockHell{

	public static final String[] forgeDamageNames = new String[] {"intact", "slightlyDamaged", "veryDamaged"};
    private static final String[] forgeIconNames = new String[] {"forge_top_damaged_0", "forge_top_damaged_1", "forge_top_damaged_2"};
    @SideOnly(Side.CLIENT)
    public int forgeRenderSide;
    @SideOnly(Side.CLIENT)
    private IIcon[] forgeIcons;
    private static final String __OBFID = "CL_00000192";
    
	public mForge() {
		super("mForge", Material.anvil, Block.soundTypeAnvil, HellLand.HellMCTab, 5.0f, 50.0f, 2.0f, false, 0);		
	}

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }
    
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int p_149691_1_, int p_149691_2_)
    {
        if (this.forgeRenderSide == 3 && p_149691_1_ == 1)
        {
            int k = (p_149691_2_ >> 2) % this.forgeIcons.length;
            return this.forgeIcons[k];
        }
        else
        {
            return this.blockIcon;
        }
    }
    
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister p_149651_1_)
    {
        this.blockIcon = p_149651_1_.registerIcon("forge_base");
        this.forgeIcons = new IIcon[forgeIconNames.length];

        for (int i = 0; i < this.forgeIcons.length; ++i)
        {
            this.forgeIcons[i] = p_149651_1_.registerIcon(forgeIconNames[i]);
        }
    }
    
    public void onBlockPlacedBy(World world, int p_149689_2_, int p_149689_3_, int p_149689_4_, EntityLivingBase entity, ItemStack itemST)
    {
        int l = MathHelper.floor_double((double)(entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
        int i1 = world.getBlockMetadata(p_149689_2_, p_149689_3_, p_149689_4_) >> 2;
        ++l;
        l %= 4;

        if (l == 0)
        {
        	world.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, 2 | i1 << 2, 2);
        }

        if (l == 1)
        {
        	world.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, 3 | i1 << 2, 2);
        }

        if (l == 2)
        {
        	world.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, 0 | i1 << 2, 2);
        }

        if (l == 3)
        {
        	world.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, 1 | i1 << 2, 2);
        }
    }
    
    /*
    public boolean onBlockActivated(World p_149727_1_, int p_149727_2_, int p_149727_3_, int p_149727_4_, EntityPlayer p_149727_5_, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
    {
        if (p_149727_1_.isRemote)
        {
            return true;
        }
        else
        {
            p_149727_5_.displayGUIAnvil(p_149727_2_, p_149727_3_, p_149727_4_);
            return true;
        }
    }
    */
    
    public int getRenderType()
    {
        return 35;
    }
    
    public int damageDropped(int p_149692_1_)
    {
        return p_149692_1_ >> 2;
    }
    
    public void setBlockBoundsBasedOnState(IBlockAccess p_149719_1_, int p_149719_2_, int p_149719_3_, int p_149719_4_)
    {
        int l = p_149719_1_.getBlockMetadata(p_149719_2_, p_149719_3_, p_149719_4_) & 3;

        if (l != 3 && l != 1)
        {
            this.setBlockBounds(0.125F, 0.0F, 0.0F, 0.875F, 1.0F, 1.0F);
        }
        else
        {
            this.setBlockBounds(0.0F, 0.0F, 0.125F, 1.0F, 1.0F, 0.875F);
        }
    }
    
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item p_149666_1_, CreativeTabs p_149666_2_, List p_149666_3_)
    {
        p_149666_3_.add(new ItemStack(p_149666_1_, 1, 0));
        p_149666_3_.add(new ItemStack(p_149666_1_, 1, 1));
        p_149666_3_.add(new ItemStack(p_149666_1_, 1, 2));
    }

    protected void func_149829_a(EntityFallingBlock p_149829_1_)
    {
        p_149829_1_.func_145806_a(true);
    }

    public void func_149828_a(World p_149828_1_, int p_149828_2_, int p_149828_3_, int p_149828_4_, int p_149828_5_)
    {
        p_149828_1_.playAuxSFX(1022, p_149828_2_, p_149828_3_, p_149828_4_, 0);
    }
    
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess p_149646_1_, int p_149646_2_, int p_149646_3_, int p_149646_4_, int p_149646_5_)
    {
        return true;
    }
}
