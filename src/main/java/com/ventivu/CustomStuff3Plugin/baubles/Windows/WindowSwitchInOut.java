package com.ventivu.CustomStuff3Plugin.baubles.Windows;

import cubex2.cs3.ingame.gui.control.CheckBox;

public class WindowSwitchInOut extends WindowBaubAttribute{
    private final CheckBox canPlaceIn;

    private final CheckBox CanTakeOut;

    public WindowSwitchInOut(WrappedBaubles item) {
        super(item, "In/Out", 150, 100);
        this.canPlaceIn = checkBox("允许置入").checked((this.container).canPlaceIn).at(7, 7).add();
        this.CanTakeOut = checkBox("允许拿出").checked((this.container).CanTakeOut).below(this.canPlaceIn, 7).add();
    }

    protected void applyChanges() {
        (this.container).canPlaceIn = this.canPlaceIn.getIsChecked();
        (this.container).CanTakeOut = this.CanTakeOut.getIsChecked();
    }
}
