package com.Arteman.HellLand.items;

import com.Arteman.HellLand.HellLand;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class MagicHammer extends Item {
    private String name = "magicHammer";

    public final ToolMaterial toolMaterial;

    public MagicHammer(ToolMaterial enumToolMaterial) {
        super();
        toolMaterial = enumToolMaterial;
        this.setUnlocalizedName(HellLand.MODID + ":" + name);
        this.setTextureName(HellLand.MODID + ":" + name);
        this.setCreativeTab(HellLand.HellMCTab);
        GameRegistry.registerItem(this, name);
    }

}
