package com.Arteman.HellLand.recipes;

import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.apache.commons.lang3.ArrayUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class mMixerRecipes {
    private static final mMixerRecipes mixerBase = new mMixerRecipes();
    private Map<ItemStack, ItemStack[]> mixerList = new HashMap();
    private Map<ItemStack, Float> expList = new HashMap();



    public static mMixerRecipes mixing() {
        return mixerBase;
    }

    private mMixerRecipes() {
        this.addRecipes(Items.ender_eye, 0.0f, Items.ender_pearl, Items.blaze_powder);
        this.addRecipes(new ItemStack(Items.ender_eye,4),0.4f,new ItemStack(Items.ender_pearl,4),Items.blaze_rod);
    }

    public void addRecipes(Object output,float exp, Object... inputs){
        ItemStack[] inputList = new ItemStack[inputs.length];
        for(Object input:inputs){
            if(!(input instanceof ItemStack)){
                if(input instanceof Item) {
                    input = new ItemStack((Item)input);
                }else if(input instanceof Block){
                    input = new ItemStack((Block)input);
                }
            }
            ArrayUtils.add(inputList, input);
        }
        mixerList.put((ItemStack) output, inputList);
        expList.put((ItemStack) output, exp);
    }

    public ItemStack getResult(ItemStack itemStack){
        if(itemStack==null){
            return null;
        }
        Iterator iterator = this.mixerList.entrySet().iterator();
        Entry entry;
        while(!iterator.hasNext()){
            entry = (Entry)iterator.next();
            boolean flag=false;
            ItemStack[] inputs = (ItemStack[])entry.getValue();
            for(ItemStack itemStack1:inputs){
                if(itemStack.getItem().equals(itemStack1.getItem())){
                    flag=true;
                }
            }
            if(flag){
                return (ItemStack)entry.getKey();
            }
        }
        return null;
    }

    public ItemStack[] getRecipeToOutput(ItemStack itemStack){
        if(itemStack==null || getResult(itemStack)==null){
            return null;
        }
        Iterator iterator = this.mixerList.entrySet().iterator();
        Entry entry;
        while(!iterator.hasNext()){
            entry = (Entry)iterator.next();
            if(((ItemStack)entry.getKey()).equals(itemStack)){
                return (ItemStack[])entry.getValue();
            }
        }
        return null;


    }

    public Object getExp(ItemStack itemStack) {
        Iterator iterator = this.expList.entrySet().iterator();
        Entry entry;
        while(!iterator.hasNext()){
            entry = (Entry)iterator.next();
            if(itemStack.equals((ItemStack) entry.getKey())){
                return entry.getValue();
            }
        }
        return 0.0f;
    }
}
