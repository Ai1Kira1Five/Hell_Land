package com.Arteman.HellLand.utils;

import com.Arteman.HellLand.HellLand;
import com.Arteman.HellLand.ModMisc;
import com.Arteman.HellLand.handler.BlockRenderingHandler;
import com.Arteman.HellLand.utils.helpers.ConnectionHelper.ConnectionTypes;
import com.Arteman.HellLand.utils.interfaces.IBlockBase;
import com.Arteman.HellLand.utils.interfaces.IBlockConnectionReceiver;
import com.Arteman.HellLand.utils.interfaces.IHellRenderBlock;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.List;
import java.util.Random;

public class BlockHell extends Block implements IHellRenderBlock, IBlockBase, IBlockConnectionReceiver{

    public boolean hasSub;
    private ItemStack customDrop = null;
    public String name;
    public IIcon[] blockIcons;

    public BlockHell(String name, Material material,SoundType soundType,CreativeTabs tabs, float hardness, float resistance, float lightLevel, boolean hasSub, int maxMeta) {
        super(material);
        this.name = name;
        this.setCreativeTab(tabs);
        this.setBlockName(HellLand.MODID + ":" + name);
        this.setBlockTextureName(HellLand.MODID + ":" + name);
        this.setHardness(hardness);
        this.setResistance(resistance);
        this.setStepSound(soundType);
        this.setHarvestLevel("pickaxe", 3);
        this.setLightLevel(lightLevel);
        this.hasSub = hasSub;
        this.blockIcons = new IIcon[maxMeta+1];
        registerBlock();
    }
    
    public int getRenderType(){
    	return BlockRenderingHandler.renderSimpleId;
    }

    public void setCustomDrop(ItemStack itemStack){
        if(itemStack!=null){
            customDrop = itemStack;
        }else{
            customDrop = null;
        }
    }

    public Item getItemDropped(int meta, Random random, int fortuneLvl){
        return (this.customDrop == null) ? Item.getItemFromBlock(this): this.customDrop.getItem();
    }
    
    /*
     * 
     * public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune){
     *    return new ArrayList();
     * }
     * 
     * 
     * public List<ItemStack> getBreakDrops(World world, int x, int y, int z){
     *    List<ItemStack> ret = new ArrayList();
     *    ret.add(getStack(world, x, y, z));
     *    return ret;
     * }
     */
    

    public int quantityDroppedWithBonus(int min, Random random){
        if(customDrop != null) {
            return MathHelper.clamp_int(this.quantityDropped(random), 1, customDrop.stackSize);
        }else{
            return 1;
        }
    }
    
    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z, EntityPlayer player){
    	return getStack(world, x, y, z);
    }
    
    public void onPostPostBlockPlaced(World world, int x, int y, int z, int meta) {}

    public int quantityDropped(Random random)
    {
        return random.nextInt(this.customDrop.stackSize);
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister){
        if(hasSub) {
            for (int i = 0; i < this.blockIcons.length; ++i) {
                this.blockIcons[i] = iconRegister.registerIcon(this.getTextureName() + "_" + i);
            }
        }else{
            this.blockIcons[0] = iconRegister.registerIcon(this.getTextureName());
        }
    }
    
    @SideOnly(Side.CLIENT)
    public int getBlockIconLayerCount(IBlockAccess world, int x, int y, int z, int side, int meta){
    	return 1;
    }
    
    @SideOnly(Side.CLIENT)
    public int getItemIconLayerCount(int side, ItemStack stack){
    	return 1;
    }

    @Override
    public int damageDropped(int meta){
        return (hasSub)?meta:0;
    }

    //ну вот хз, как это красивее сделать
    @SideOnly(Side.CLIENT)
    public IIcon getBlockIcon(int side, int meta){
        if (meta < 0 || meta >= this.blockIcons.length){
            meta = 0;
            return this.blockIcons[meta];
        }
        //return this.blockIcons[meta];
        else{
        	return ModMisc.NULL_BLOCK_ICON;
        }
    }
    
    @SideOnly(Side.CLIENT)
    public IIcon getItemIcon(IBlockAccess world, int x, int y, int z, int side, int meta, int layer){
    	return ModMisc.NULL_BLOCK_ICON;
    }
    
    @SideOnly(Side.CLIENT)
    public int getBlockColor(IBlockAccess world, int x, int y, int z, int side, int meta, int layer){
    	//не спрашивайте
    	return 16777215;
    }
    
    @SideOnly(Side.CLIENT)
    public int getItemColor(int side, ItemStack stack, int layer){
    	//не спрашивайте[2]
    	return 16777215;
    }
    
    @SideOnly(Side.CLIENT)
    public int getBlockIconRotation(IBlockAccess world, int x, int y, int z, int side, int meta, int layer){
    	return 0;
    }
    
    @SideOnly(Side.CLIENT)
    public int getItemIconRotation(int side, ItemStack stack, int layer){
    	return 0;
    }
    
    @SideOnly(Side.CLIENT)
    public float getItemSelfIllum(int side, ItemStack stack, int layer){
    	switch (side){
    	case 0: 
    	    return 0.5F;
    	case 1: 
    	    return 1.0F;
    	case 2: 
    	case 3: 
    	    return 0.8F;
    	case 4: 
    	case 5: 
    	    return 0.6F;
    	}
    	return 1.0F;
    }
    
    @SideOnly(Side.CLIENT)
    public float getBlockSelfIllum(IBlockAccess world, int x, int y, int z, int side, int meta, int layer){
    	switch (side){
    	case 0: 
    	    return 0.5F;
    	case 1: 
    	    return 1.0F;
    	case 2: 
    	case 3: 
    	    return 0.8F;
    	case 4: 
    	case 5: 
    	    return 0.6F;
    	}
    	return 1.0F;
    }
    
    public void setItemBounds(ItemStack stack){
    	setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }
    
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side){
    	Block target = world.getBlock(x, y, z);
        if (target.isAir(world, x, y, z)){
        	return true;
        }
        ForgeDirection oppSide = ForgeDirection.values()[ForgeDirection.OPPOSITES[side]];
        if (target.isSideSolid(world, x, y, z, oppSide)){
        	return false;
        }
        return true;
    }
    
    public int getOrientation(IBlockAccess world, int x, int y, int z){
    	return 0;
    }
    
    public boolean shouldMySideByRendererd(IBlockAccess world, int myX, int myY, int myZ, ForgeDirection side){
    	if (isSideSolid(world, myX, myY, myZ, side)){
    		int tX = myX + side.offsetX;
    	    int tY = myY + side.offsetY;
    	    int tZ = myZ + side.offsetZ;
    	    Block target = world.getBlock(tX, tY, tZ);
    	    if (target.isSideSolid(world, tX, tY, tZ, side.getOpposite())){
    	    	return false;
    	    }
    	}
    	return true;
    }

    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list){
        for(int i=0;i<this.blockIcons.length;++i){
            list.add(new ItemStack(item, 1, i));
        }
    }

    public String getName(ItemStack stack){
        return name;
    }

    public int getMaxMeta(){
        return this.blockIcons.length-1;
    }

    public void registerBlock(){
        if(this.hasSub){
            GameRegistry.registerBlock(this, ItemBlockHell.class, name);
            System.out.println(String.format("Register MetaBlock: %s", name));
        }else{
            GameRegistry.registerBlock(this, ItemBlock.class, name);
            System.out.println(String.format("Register Block: %s", name));
        }
    }

    public static class ItemBlockHell extends ItemBlockWithMetadata{
        public ItemBlockHell(Block block){
            super(block,block);
            this.hasSubtypes = ((BlockHell) block).hasSub;
            this.maxStackSize = 64;
        }
        @SideOnly(Side.CLIENT)
        public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list){
            for(int i=0;i<=this.getMaxDamage();++i){
                list.add(new ItemStack(item, 1, i));
            }
        }
        public String getUnlocalizedName(ItemStack itemStack){
            int meta = itemStack.getItemDamage();
            meta = MathHelper.clamp_int(meta, 0, ((BlockHell)this.field_150939_a).getMaxMeta());
            return this.getUnlocalizedName()+"_"+meta;
        }
        @SideOnly(Side.CLIENT)
        public IIcon getIconFromDamage(int metadata){
            return ((BlockHell)this.field_150939_a).blockIcons[metadata];
        }
    }

    // далее пока не доработанно
	@Override
	public boolean doesAcceptConnectionSide(IBlockAccess paramIBlockAccess,
			int paramInt1, int paramInt2, int paramInt3,
			ForgeDirection paramForgeDirection, int paramInt4, int paramInt5,
			int paramInt6, ConnectionTypes paramConnectionTypes, int paramInt7) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doesAcceptConnectionCorner(IBlockAccess paramIBlockAccess,
			int paramInt1, int paramInt2, int paramInt3,
			ForgeDirection paramForgeDirection1,
			ForgeDirection paramForgeDirection2, int paramInt4, int paramInt5,
			int paramInt6, ConnectionTypes paramConnectionTypes, int paramInt7) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ItemStack getStack(World paramWorld, int paramInt1, int paramInt2,
			int paramInt3) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IIcon getBlockIcon(IBlockAccess paramIBlockAccess, int paramInt1,
			int paramInt2, int paramInt3, int paramInt4, int paramInt5,
			int paramInt6) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IIcon getItemIcon(int paramInt1, ItemStack paramItemStack,
			int paramInt2) {
		// TODO Auto-generated method stub
		return null;
	}
}
