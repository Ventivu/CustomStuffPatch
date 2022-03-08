package com.ventivu.CustomStuff3Plugin.baubles.Windows;

import cubex2.cs3.ingame.gui.control.ItemDisplay;
import cubex2.cs3.ingame.gui.control.TextField;
import net.minecraft.item.ItemStack;

public class WindowEditInf extends WindowBaubAttribute{
    private final TextField textField = this.textField().top(7).fillWidth(7).height(100).add();
    private final ItemDisplay display;

    public WindowEditInf(WrappedBaubles item) {
        super(item, "information", 33, 150, 170);
        this.textField.disableSyntaxHighlighting();
        this.textField.setText((this.container).information);
        this.display = this.itemDisplay().below(this.textField).add();
        this.display.setItemStack(new ItemStack(wrappedBaubles.item));
        this.display.setDrawSlotBackground();
    }

    protected void applyChanges() {
        (this.container).information = this.getNewInfo();
    }

    private String getNewInfo() {
        String info = this.textField.getText();
        if (info.length() == 0) {
            info = null;
        }

        return info;
    }

    public void drawForeground(int mouseX, int mouseY) {
        String currentInfo = (this.container).information;
        (this.container).information = this.getNewInfo();
        super.drawForeground(mouseX, mouseY);
        (this.container).information = currentInfo;
    }
}
