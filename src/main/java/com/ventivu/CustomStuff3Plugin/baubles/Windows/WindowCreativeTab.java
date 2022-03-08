package com.ventivu.CustomStuff3Plugin.baubles.Windows;

import cubex2.cs3.ingame.gui.control.DropBox;
import cubex2.cs3.ingame.gui.control.IStringProvider;
import net.minecraft.creativetab.CreativeTabs;

public class WindowCreativeTab extends WindowBaubAttribute implements IStringProvider<CreativeTabs> {
    private final DropBox<CreativeTabs> dbTabs;

    public WindowCreativeTab(WrappedBaubles item) {
        super(item, "创造模式物品栏", 34, 150, 55);
        this.dbTabs = this.dropBox(CreativeTabs.creativeTabArray).top(7).fillWidth(7).add();
        this.dbTabs.setStringProvider(this);
        this.dbTabs.setSelectedValue((this.container).creativeTab);
    }

    protected void applyChanges() {
        (this.container).creativeTab = this.dbTabs.getSelectedValue();
    }

    public String getStringFor(CreativeTabs value) {
        return value.getTabLabel();
    }
}
