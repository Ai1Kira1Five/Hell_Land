package com.Arteman.HellLand;


import java.util.ArrayList;
import java.util.List;

import com.Arteman.HellLand.handler.ConfigurationHandler;
import com.Arteman.HellLand.handler.GuiHandler;
import com.Arteman.HellLand.otherStuff.Enchantments;
import com.Arteman.HellLand.otherStuff.enchantments.EnchantmentHandler;
import com.Arteman.HellLand.proxy.CommonProxy;
import com.Arteman.HellLand.recipes.CrystallizerRecipes;
import com.Arteman.HellLand.recipes.ModRecipes;
import com.Arteman.HellLand.utils.ItemHell;
import com.Arteman.HellLand.utils.interfaces.IHellRegistry;
import com.Arteman.HellLand.utils.network.HellMessagePipeline;
import com.Arteman.HellLand.utils.network.NetworkHL;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = HellLand.MODID, version = HellLand.VERSION, guiFactory = "com.Arteman.HellLand.gui.GuiFactory")
public class HellLand {
	
    public HellLand() {
        messagePipeline = new HellMessagePipeline();
        network = new NetworkHL();
    }
	
    public static final String MODID = "HellLand";
    public static final String VERSION = "Alert_ver.0.1";

    public static CreativeTabs HellMCTab;
    public static CreativeTabs HellMCTabStuff;
    public static CreativeTabs HellMCTabDecor;

    public HellMessagePipeline messagePipeline;
    public static NetworkHL network;
    public static Logger modLog = LogManager.getLogger("HellLand-init");
    public static List<Item> hammers = new ArrayList<Item>();
    public static final boolean debug_network = false;
    public static final boolean show_fine_logging = false;
    
    public static final boolean cheat = dev_only(false);
    public static final boolean dev_environ = Launch.blackboard != null ? ((Boolean)Launch.blackboard.get("fml.deobfuscatedEnvironment")).booleanValue() : false;
    
    public static IHellRegistry registry;
    
    //���� �������
    public static enum FluidIcon{
    	OPAQUE,  TRANSLUCENT,  MOLTEN,  GLITTERING;
    	
    	private FluidIcon() {}
    }
    
    private void initializeLogging(Logger logger){
    	modLog = logger;
    	logInfo("This is Hell Land %s", new Object[] { "Alert_ver.0.1" });
    }
    
    public static void logWarning(String format, Object... formatParameters){
    	modLog.warn(String.format(format, formatParameters));
    }
    
    public static void logInfo(String format, Object... formatParameters){
    	modLog.info(String.format(format, formatParameters));
    }
    
    public static void logSevere(String format, Object... formatParameters){
    	modLog.error(String.format(format, formatParameters));
    }
    
    private static boolean dev_only(boolean a)
    {
      if (!dev_environ) {
        return false;
      }
      return a;
    }

    //Materials
    public static final Item.ToolMaterial Bone = EnumHelper.addToolMaterial("BONE", 3, 38, 2.0f, 15.5f, 20);
    public static final Item.ToolMaterial MagicSteel = EnumHelper.addToolMaterial("MagicSteel", 5, 700, 3.0f, 16.0f, 40);
    public static final ArmorMaterial ArcaneSteel = EnumHelper.addArmorMaterial("ArcaneSteel", 250, new int[]{3, 7, 5, 2}, 40);
    public static final Item.ToolMaterial Crystal = EnumHelper.addToolMaterial("Crystal", 3, 120, 5.0f, 10.0f, 50);

    @Mod.Instance(MODID)
    public static HellLand instance;
    public static final int guiIDHellOven = 1;
    public static final int guiIDSoulCrystallizer = 2;
    public static final int guiIDCrystalOven = 3;
    public static final int guiIDAmuletTable = 4;
    public static final int guiIDMMixer = 5;
    public static final int guiIDBag = 6;
    public static final int guiIDAlchemicalTable = 7;

    public static void loadBus(Object obj)
    {
      FMLCommonHandler.instance().bus().register(obj);
      MinecraftForge.EVENT_BUS.register(obj);
    }
    
    @SidedProxy(clientSide = "com.Arteman.HellLand.proxy.ClientProxy", serverSide = "com.Arteman.HellLand.proxy.CommonProxy")
    public static CommonProxy artemanProxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        //Main stuff
        HellMCTab = new CreativeTabs("hellLand") {        	
            @Override
            public Item getTabIconItem() {
                return ModItems.HellCrystal;
            }
            
            public boolean hasSearchBar(){
            	return true;
            }
        };
        HellMCTabStuff = new CreativeTabs("hellLandStuff") {
            @Override
            public Item getTabIconItem() {
                return ModItems.Heart;
            }
        };
        HellMCTabDecor = new CreativeTabs("hellLandDecorative") {
            @Override
            public Item getTabIconItem() {
                return Item.getItemFromBlock(ModBlocks.Marble);
            }
        };
        ConfigurationHandler.init(event.getSuggestedConfigurationFile());
        FMLCommonHandler.instance().bus().register(new ConfigurationHandler());

        ModItems.init();
        ModBlocks.init();
        ModFluids.init();
        Enchantments.init();

        //Recipes
        ModRecipes.init();
        CrystallizerRecipes.init();

        //Other stuff like proxy and worldGen
        GameRegistry.registerWorldGenerator(new WorldGeneratorHell(), 0);
        artemanProxy.registerRenderThings();
        artemanProxy.registerTileEntitySpecialRender();
        artemanProxy.registerProxies();
        modLog = event.getModLog();
    }

    @EventHandler
    public void Init(FMLInitializationEvent event) {
        //Different
        messagePipeline.initalize();
        artemanProxy.registerPackets(messagePipeline);
        artemanProxy.registerEventHandlers();
        MinecraftForge.EVENT_BUS.register(new ModDrops());
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
        MinecraftForge.EVENT_BUS.register(new EnchantmentHandler());

        //TileEntity
        ModTiles.init();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        messagePipeline.postInitialize();
    }
    
    private static void addTranslationHints(String hint_key, List list, String prefix)
    {
      if (StatCollector.canTranslate(hint_key))
      {
        String hint = StatCollector.translateToLocal(hint_key);
        if (hint != null)
        {
          hint = hint.trim();
          if (hint.length() > 0) {
            for (String s : hint.split("\\\\n")) {
              list.add(prefix + s);
            }
          }
        }
      }
    }
    
    public static final String hintFormat = "" + EnumChatFormatting.DARK_PURPLE;
    public static final String shiftFormat = "" + EnumChatFormatting.DARK_GRAY + EnumChatFormatting.ITALIC;
    @SidedProxy(clientSide="com.Arteman.HellLand.proxy.ClientProxy", serverSide="com.Arteman.HellLand.proxy.CommonProxy")
    public static CommonProxy proxy;
    
    public static void brand(ItemStack is, EntityPlayer player, List list, boolean verbose)
    {
      Item it = is.getItem();
      String name = it.getUnlocalizedName(is);
      addTranslationHints(name + ".hint", list, hintFormat);
      if ((player != null) && (proxy.isClientHoldingShift())){
    	  addTranslationHints(name + ".shift", list, shiftFormat);
      }
      ArrayList<String> untranslated = new ArrayList();
      if ((it instanceof ItemHell)) {
    	  ((ItemHell)it).addExtraInformation(is, player, untranslated, verbose);
      }
      String brand = "";
      if (ConfigurationHandler.add_branding) {
    	  brand = brand + "Hell Land";
      }
      if (cheat) {
    	  brand = brand + " Cheat mode!";
      }
      if (dev_environ) {
    	  brand = brand + " Development!";
      }
      if (brand.length() > 0) {
    	  untranslated.add(EnumChatFormatting.BLUE + brand.trim());
      }
      for (String s : untranslated) {
    	  list.add(StatCollector.translateToLocal(s));
      }
    }

}
