package com.ventivu.CustomStuff3Plugin.baubles.Windows;

import cubex2.cs3.ingame.gui.control.Anchor;
import cubex2.cs3.ingame.gui.control.Control;
import cubex2.cs3.ingame.gui.control.ItemDisplay;
import cubex2.cs3.ingame.gui.control.listbox.ListBoxItem;
import net.minecraft.item.ItemStack;

public class ListBoxBaubleItem extends ListBoxItem<WrappedBaubles> {
    public ListBoxBaubleItem(WrappedBaubles value, int idx, int width, int height, Anchor anchor, int offsetX, int offsetY, Control parent) {
        super(value, idx, width, height, anchor, offsetX, offsetY, parent);
        ItemDisplay display = this.itemDisplay().left(3).centerVert().add();
        if (value.item != null) {
            display.setItemStack(new ItemStack(value.item));
        }

        this.label(value.getName() + " (" + value.getBaublesType().display + ")").left(display, 3).centerVert(1).add();
    }
}
