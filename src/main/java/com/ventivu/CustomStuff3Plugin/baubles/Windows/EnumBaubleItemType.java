package com.ventivu.CustomStuff3Plugin.baubles.Windows;

import baubles.api.BaubleType;
import com.google.common.collect.Maps;
import com.ventivu.CustomStuff3Plugin.baubles.BaubleAttributes;
import com.ventivu.CustomStuff3Plugin.baubles.BaublesItem;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cubex2.cs3.common.BaseContentPack;
import net.minecraft.item.Item;

import java.util.Map;

public enum EnumBaubleItemType {
    RING("Ring","戒指",BaubleType.RING),
    AMULET("AMULET","项链",BaubleType.AMULET),
    BELT("BELT","腰带",BaubleType.BELT);
    public final BaubleType type;

    public final String name;
    public final String display;
    public final Class<? extends Item> itemClass;
    public final Class<? extends BaubleAttributes> attributesClass;
    private static final Map<String, EnumBaubleItemType> map = Maps.newHashMap();

    EnumBaubleItemType(String name, String Display, BaubleType type) {
        this.name = name;
        this.display = Display;
        this.itemClass = BaublesItem.class;
        this.attributesClass = BaubleAttributes.class;
        this.type=type;
    }

    public Item createItem(WrappedBaubles wrappedItem) {
        try {
            Item item = this.itemClass.getConstructor(WrappedBaubles.class).newInstance(wrappedItem);
            GameRegistry.registerItem(item, wrappedItem.getName());
            if (FMLCommonHandler.instance().getSide() == Side.CLIENT) {
                this.registerRender(item, wrappedItem);
            }

            return item;
        } catch (Exception var3) {
            var3.printStackTrace();
            return null;
        }
    }

    @SideOnly(Side.CLIENT)
    private void registerRender(Item item, WrappedBaubles wrappedItem) {
        //MinecraftForgeClient.registerItemRenderer(item, new BauItemRender(wrappedItem));
    }

    public BaubleAttributes createAttributeContainer(WrappedBaubles item) {
        try {
            return this.attributesClass.getConstructor(BaseContentPack.class).newInstance(item.getPack());
        } catch (Exception var3) {
            var3.printStackTrace();
            return null;
        }
    }

    public static EnumBaubleItemType get(String name) {
        if (map.isEmpty()) {
            EnumBaubleItemType[] var1 = values();

            for (EnumBaubleItemType e : var1) {
                map.put(e.name, e);
            }
        }

        return map.get(name);
    }

    public static String[] getNames() {
        String[] names = new String[values().length];

        for(int i = 0; i < names.length; ++i) {
            names[i] = values()[i].display;
        }

        return names;
    }
}
