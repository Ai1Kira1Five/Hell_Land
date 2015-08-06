package com.Arteman.HellLand.proxy;

import java.lang.ref.WeakReference;
import java.util.List;

import com.Arteman.HellLand.HellCommonEventHandler;
import com.Arteman.HellLand.HellLandCore;
import com.Arteman.HellLand.Hooks;
import com.Arteman.HellLand.entity.projectile.EntityBlock;
import com.Arteman.HellLand.items.ItemBlockHellLand;
import com.Arteman.HellLand.utils.network.HellMessagePipeline;
import com.Arteman.HellLand.utils.network.MSGExplosion;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.network.INetHandler;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.FakePlayerFactory;

public class CommonProxy implements ICoreProxy
{
	public static CommonProxy proxy;
	
	protected static WeakReference<EntityPlayer> hellLandPlayer = new WeakReference<EntityPlayer>(null);
	
	public String getMinecraftVersion()
	{
		return Loader.instance().getMinecraftModContainer().getVersion();
	}
	
	public Object getClient()
	{
		return null;
	}
	
	public World getClientWorld()
	{
		return null;
	}

	public void removeEntity(Entity entity)
	{
		entity.worldObj.removeEntity(entity);
	}
	
	@SuppressWarnings("rawtypes")
	public void feedSubBlocks(Block block, CreativeTabs tab, List itemList) {}
	
	public String getItemDisplayName(ItemStack newStack)
	{
		return "";
	}
	
    public void registerEventHandlers() 
    {
        MinecraftForge.EVENT_BUS.register(new HellCommonEventHandler());
    }

    public void registerPackets(HellMessagePipeline pipeline) 
    {
        pipeline.registerPacket(MSGExplosion.class);
    }

    public void registerRenderThings() {}

    public void registerTileEntitySpecialRender() {}

    public void registerProxies() {}
    
    public void registerBlock(Block block)
    {
		registerBlock(block, ItemBlockHellLand.class);
	}
    
    public void registerBlock(Block block, Class<? extends ItemBlock> item)
    {
		GameRegistry.registerBlock(block, item, block.getUnlocalizedName().replace("tile.", ""));
	}
    
    public void registerItem(Item item) {
		GameRegistry.registerItem(item, item.getUnlocalizedName().replace("item.", ""));
	}

	@SuppressWarnings({"rawtypes", "unchecked"})
	public void registerTileEntity(Class clas, String ident)
	{
		GameRegistry.registerTileEntity(Hooks.INSTANCE.getTile(clas), ident);
	}
	
	public void onCraftingPickup(World world, EntityPlayer player, ItemStack stack) 
	{
		stack.onCrafting(world, player, stack.stackSize);
	}
	
	private WeakReference<EntityPlayer> createNewPlayer(WorldServer world) {
		EntityPlayer player = FakePlayerFactory.get(world, HellLandCore.gameProfile);

		return new WeakReference<EntityPlayer>(player);
	}

	private WeakReference<EntityPlayer> createNewPlayer(WorldServer world, int x, int y, int z) {
		EntityPlayer player = FakePlayerFactory.get(world, HellLandCore.gameProfile);
		player.posX = x;
		player.posY = y;
		player.posZ = z;
		return new WeakReference<EntityPlayer>(player);
	}

	@Override
	public final WeakReference<EntityPlayer> getHellPlayer(WorldServer world) 
	{
		if (CommonProxy.hellLandPlayer.get() == null) 
		{
			CommonProxy.hellLandPlayer = createNewPlayer(world);
		} 
		else 
		{
			CommonProxy.hellLandPlayer.get().worldObj = world;
		}

		return CommonProxy.hellLandPlayer;
	}

	public final WeakReference<EntityPlayer> getBuildCraftPlayer(WorldServer world, int x, int y, int z) 
	{
		if (CommonProxy.hellLandPlayer.get() == null) 
		{
			CommonProxy.hellLandPlayer = createNewPlayer(world, x, y, z);
		} 
		else 
		{
			CommonProxy.hellLandPlayer.get().worldObj = world;
			CommonProxy.hellLandPlayer.get().posX = x;
			CommonProxy.hellLandPlayer.get().posY = y;
			CommonProxy.hellLandPlayer.get().posZ = z;
		}

		return CommonProxy.hellLandPlayer;
	}
	
	public EntityBlock newEntityBlock(World world, double i, double j, double k, double iSize, double jSize, double kSize) 
	{
		return new EntityBlock(world, i, j, k, iSize, jSize, kSize);
	}
	
	public EntityPlayer getPlayerFromNetHandler (INetHandler handler) 
	{
		if (handler instanceof NetHandlerPlayServer) 
		{
			return ((NetHandlerPlayServer) handler).playerEntity;
		} 
		else 
		{
			return null;
		}
	}

	public EntityPlayer getClientPlayer() 
	{
		return null;
	}

	@Override
	public WeakReference<EntityPlayer> getHellLandPlayer(WorldServer world) {
		// TODO Auto-generated method stub
		return null;
	}
}
