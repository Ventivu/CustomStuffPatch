package com.ventivu.CustomStuff3Plugin.baubles.Windows;

import com.ventivu.CustomStuff3Plugin.baubles.BaubleAttributes;
import cubex2.cs3.ingame.gui.common.WindowEditContainerAttribute;

public class WindowBaubAttribute extends WindowEditContainerAttribute<BaubleAttributes> {
    protected final WrappedBaubles wrappedBaubles;
    public WindowBaubAttribute(WrappedBaubles item, String title, int usedControls, int width, int height) {
        super(item.container, title, usedControls, width, height);
        wrappedBaubles= item;
    }

    public WindowBaubAttribute(WrappedBaubles item, String title, int width, int height) {
        super(item.container, title, 34, width, height);
        wrappedBaubles= item;
    }
}
