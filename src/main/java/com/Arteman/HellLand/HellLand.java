package com.Arteman.HellLand;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;

import com.Arteman.HellLand.handler.GuiHandler;
import com.Arteman.HellLand.otherStuff.Enchantments;
import com.Arteman.HellLand.otherStuff.enchantments.EnchantmentHandler;
import com.Arteman.HellLand.proxy.CommonProxy;
import com.Arteman.HellLand.tileentity.TileEntityHellOven;
import com.Arteman.HellLand.tileentity.TileEntitySoulCrystallizer;
import com.Arteman.HellLand.tileentity.TileEntityWire;
import com.Arteman.HellLand.utils.Config;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = HellLand.MODID, version = HellLand.VERSION)
public class HellLand
{
    public static final String MODID = "HellLand";
    public static final String VERSION = "Alert_ver.0.1";
    
    public static CreativeTabs HellMCTab = new CreativeTabHell("Hell Land");
    public static CreativeTabs HellMCTabStuff = new CreativeTabHellStuff("Hell Land Stuff");
    
    //Materials
    public static final Item.ToolMaterial Bone = EnumHelper.addToolMaterial("BONE", 3, 38, 2.0f, 15.5f, 20);
    public static final Item.ToolMaterial MagicSteel = EnumHelper.addToolMaterial("MagicSteel", 5, 700, 3.0f, 16.0f, 40);
    public static final Item.ToolMaterial Crystall = EnumHelper.addToolMaterial("Crystall", 3, 120, 5.0f, 10.0f, 50);
    
    @Mod.Instance(MODID)
    public static HellLand instance;
    public static final int guiIDHellOven = 1;
    public static final int guiIDSoulCrystallizer = 2;
    public static final int guiIDCrystallOven = 3;
    public static final int guiIDAmuletTable = 4;

    
    @SidedProxy(clientSide = "com.Arteman.HellLand.proxy.ClientProxy", serverSide = "com.Arteman.HellLand.proxy.CommonProxy")
    public static CommonProxy artemanProxy;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	//Main stuff
    	ModBlocks.init();
    	ModItems.init();
    	ModFluids.init();
    	Config.init(event.getSuggestedConfigurationFile());
    	FMLCommonHandler.instance().bus().register(new Config());
    	Enchantments.init();
    	
    	//Recipes
    	ModRecipes.init();
    	CrystallizerResipes.init();
    	
    	//Other stuff like proxy and worldGen
    	GameRegistry.registerWorldGenerator(new WorldGeneratorHell(), 1);
    	artemanProxy.registerRenderThings();
    	artemanProxy.registerTileEntitySpecialRender();
    	artemanProxy.registerProxies();
    }
    
    @EventHandler
    public void Init(FMLInitializationEvent event)
    {
    	//Different
    	MinecraftForge.EVENT_BUS.register(new ModDrops());
    	NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
    	MinecraftForge.EVENT_BUS.register(new EnchantmentHandler());
    	
    	//TileEntity
    	ModTiles.init();
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
    	
    }
}
