package com.ventivu.CustomStuff3Plugin;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemSearchFix {
    public static ItemStack getItemStack(String ItemName, int amount, int meta) {
        Object block = Block.getBlockFromName(ItemName);
        if (block == null) {
            block = Item.itemRegistry.getObject(ItemName);
        } else {
            block = Item.getItemFromBlock((Block)block);
        }
        return new ItemStack((Item)block, amount, meta);
    }

    public static String getItemName(ItemStack stack){
        return getItemName(stack.getItem());
    }

    public static String getItemName(Object item){
        return Item.itemRegistry.getNameForObject(item);
    }


    public static String getNameFromStackForced(ItemStack stack){
        String[] name=stack.getItem().getUnlocalizedName().split("\\.");
        return name[name.length-1];
    }
}
