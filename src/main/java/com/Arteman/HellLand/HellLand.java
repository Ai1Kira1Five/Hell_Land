package com.Arteman.HellLand;


import com.Arteman.HellLand.handler.ConfigurationHandler;
import com.Arteman.HellLand.handler.GuiHandler;
import com.Arteman.HellLand.otherStuff.Enchantments;
import com.Arteman.HellLand.otherStuff.enchantments.EnchantmentHandler;
import com.Arteman.HellLand.proxy.CommonProxy;
import com.Arteman.HellLand.recipes.CrystallizerRecipes;
import com.Arteman.HellLand.recipes.ModRecipes;
import com.Arteman.HellLand.utils.network.HellMessagePipeline;
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
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;
import org.apache.logging.log4j.Logger;

@Mod(modid = HellLand.MODID, version = HellLand.VERSION, guiFactory = "com.Arteman.HellLand.gui.GuiFactory")
public class HellLand {
    public static final String MODID = "HellLand";
    public static final String VERSION = "Alert_ver.0.1";

    public static CreativeTabs HellMCTab;
    public static CreativeTabs HellMCTabStuff;
    public static CreativeTabs HellMCTabDecor;

    public HellMessagePipeline messagePipeline;
    public static Logger modLog;

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


    @SidedProxy(clientSide = "com.Arteman.HellLand.proxy.ClientProxy", serverSide = "com.Arteman.HellLand.proxy.CommonProxy")
    public static CommonProxy artemanProxy;

    public HellLand() {
        messagePipeline = new HellMessagePipeline();
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        //Main stuff
        HellMCTab = new CreativeTabs("hellLand") {
            @Override
            public Item getTabIconItem() {
                return ModItems.HellCrystal;
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

}
