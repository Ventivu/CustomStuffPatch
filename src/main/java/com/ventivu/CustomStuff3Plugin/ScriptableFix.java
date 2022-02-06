package com.ventivu.CustomStuff3Plugin;

import net.minecraft.item.ItemStack;

public class ScriptableFix {
    public ItemStack getItemStack(String ItemName, int amount, int meta) {
        return ItemSearchFix.getItemStack(ItemName, amount, meta);
    }

    public String getItemName(ItemStack stack) {
        return ItemSearchFix.getItemName(stack);
    }

    public String getItemName(Object item) {
        return ItemSearchFix.getItemName(item);
    }

    public String getNameFromStackForced(ItemStack stack) {
        return ItemSearchFix.getNameFromStackForced(stack);
    }
}
