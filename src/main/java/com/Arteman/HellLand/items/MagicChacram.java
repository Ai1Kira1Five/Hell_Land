package com.Arteman.HellLand.items;

import com.Arteman.HellLand.HellLandCore;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class MagicChacram extends Item {
    private String name = "magicChacram";
    public final ToolMaterial toolMaterial;

    public MagicChacram(ToolMaterial enumToolMaterial) {
        super();
        toolMaterial = enumToolMaterial;
        this.setUnlocalizedName(HellLandCore.MODID + ":" + name);
        this.setTextureName(HellLandCore.MODID + ":" + name);
        this.setCreativeTab(HellLandCore.HellMCTab);
        GameRegistry.registerItem(this, name);
    }

}
