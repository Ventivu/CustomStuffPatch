package com.ventivu.CustomStuff3Plugin.baubles.Windows;

import com.ventivu.CustomStuff3Plugin.baubles.BaubleEditAttributeContent;
import com.ventivu.CustomStuff3Plugin.baubles.Provider;
import cubex2.cs3.common.BaseContentPack;
import cubex2.cs3.ingame.gui.Window;
import cubex2.cs3.ingame.gui.WindowContentList;
import cubex2.cs3.ingame.gui.control.ToolTipProvider;
import cubex2.cs3.ingame.gui.control.listbox.ListBoxDescription;

public class WindowBauble extends WindowContentList<WrappedBaubles> {
    public WindowBauble(BaseContentPack pack) {
        super(WrappedBaubles.class, "饰品", 263, 160, pack);
        this.btnEdit.toolTipProvider = new ToolTipProvider.SelectedNotEnabled<>(this.listBox, "必须重启游戏以应用物品");
        this.btnDelete.toolTipProvider = new ToolTipProvider.Enabled("重启必须");
    }

    @Override
    protected void modifyListBoxDesc(ListBoxDescription<WrappedBaubles> desc) {
        desc.rows = 5;
        desc.elementHeight = 22;
        desc.sorted = true;
        desc.itemProvider=new Provider();
    }

    @Override
    protected Window createNewContentWindow() {
        return new WindowNewBauble(pack);
    }

    @Override
    protected Window createEditContentWindow(WrappedBaubles wrappedBaubles) {
        return new BaubleEditAttributeContent(wrappedBaubles);
    }
}
