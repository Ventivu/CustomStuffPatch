package com.ventivu.CustomStuff3Plugin.baubles;

import com.ventivu.CustomStuff3Plugin.baubles.Windows.ListBoxBaubleItem;
import com.ventivu.CustomStuff3Plugin.baubles.Windows.WrappedBaubles;
import cubex2.cs3.ingame.gui.control.Anchor;
import cubex2.cs3.ingame.gui.control.Control;
import cubex2.cs3.ingame.gui.control.listbox.ListBoxItem;
import cubex2.cs3.ingame.gui.control.listbox.ListBoxItemProvider;

public class Provider extends ListBoxItemProvider {

    @Override
    public <T> ListBoxItem<?> createListBoxItem(T value, int idx, int meta, int width, int height, Anchor anchor, int offsetX, int offsetY, Control parent) {
        if(value instanceof WrappedBaubles){return new ListBoxBaubleItem((WrappedBaubles) value, meta, width, height, anchor, offsetX, offsetY, parent);}
        return super.createListBoxItem(value, idx, meta, width, height, anchor, offsetX, offsetY, parent);
    }
}
