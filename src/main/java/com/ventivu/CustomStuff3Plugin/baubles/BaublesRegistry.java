package com.ventivu.CustomStuff3Plugin.baubles;

import com.ventivu.CustomStuff3Plugin.baubles.Windows.WindowBauble;
import com.ventivu.CustomStuff3Plugin.baubles.Windows.WrappedBaubles;
import cubex2.cs3.common.BaseContentPack;
import cubex2.cs3.ingame.gui.Window;
import cubex2.cs3.registry.ContentRegistry;

public class BaublesRegistry extends ContentRegistry<WrappedBaubles> {
    public BaublesRegistry(BaseContentPack pack) {
        super(pack);
    }

    @Override
    public WrappedBaubles newDataInstance() {
        return new WrappedBaubles(pack);
    }

    @Override
    public Window createListWindow() {
        return new WindowBauble(pack);
    }

    @Override
    public String getNameForEditPack() {
        return "饰品";
    }

    @Override
    public String getName() {
        return "饰品注册表";
    }

}
