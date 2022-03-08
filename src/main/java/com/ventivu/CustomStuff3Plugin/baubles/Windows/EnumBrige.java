package com.ventivu.CustomStuff3Plugin.baubles.Windows;

import cubex2.cs3.common.attribute.AttributeBridge;
import net.minecraft.nbt.NBTTagCompound;

public class EnumBrige extends AttributeBridge<EnumBaubleItemType> {
    @Override
    public EnumBaubleItemType loadValueFromNBT(NBTTagCompound nbtTagCompound) {
        return EnumBaubleItemType.valueOf(nbtTagCompound.getString("Type"));
    }

    @Override
    public void writeValueToNBT(NBTTagCompound nbtTagCompound, EnumBaubleItemType enumBaubleItemType) {
        nbtTagCompound.setString("Type", enumBaubleItemType.name);
    }
}
