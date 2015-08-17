package com.Arteman.HellLand.items;

import com.Arteman.HellLand.HellLand;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class MagicChacram extends Item {
    private String name = "magicChacram";
    public final ToolMaterial toolMaterial;

    public MagicChacram(ToolMaterial enumToolMaterial) {
        super();
        toolMaterial = enumToolMaterial;
        this.setUnlocalizedName(HellLand.MODID + ":" + name);
        this.setTextureName(HellLand.MODID + ":" + name);
        this.setCreativeTab(HellLand.HellMCTab);
        GameRegistry.registerItem(this, name);
    }

}
