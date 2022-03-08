package com.ventivu.CustomStuff3Plugin.baubles;

import com.ventivu.CustomStuff3Plugin.baubles.Windows.WrappedBaubles;
import cubex2.cs3.common.AttributeContent;
import cubex2.cs3.common.attribute.AttributeData;
import cubex2.cs3.ingame.gui.GuiBase;
import cubex2.cs3.ingame.gui.Window;
import cubex2.cs3.ingame.gui.common.*;
import cubex2.cs3.ingame.gui.control.listbox.ListBox;

public class BaubleEditAttributeContent extends WindowEditAttributeContent {
    public BaubleEditAttributeContent(AttributeContent content) {
        super(content);
    }

    public void itemClicked(AttributeData item, ListBox<AttributeData> listBox, int button) {
        System.out.println(this.content.getContainer().getWindowClass(item));
        try {
            Class<? extends Window> windowClass = this.content.getContainer().getWindowClass(item);
            if (windowClass == WindowEditScript.class) {
                GuiBase.openWindow(new WindowEditScript(item.field.getName(), this.content.getContainer()));
            } else if (windowClass == WindowEditInteger.class) {
                GuiBase.openWindow(new WindowEditInteger(item, this.content.getContainer()));
            } else if (windowClass == WindowEditFloat.class) {
                GuiBase.openWindow(new WindowEditFloat(item, this.content.getContainer()));
            } else if (windowClass == WindowEditBoolean.class) {
                GuiBase.openWindow(new WindowEditBoolean(item, this.content.getContainer()));
            } else {
                GuiBase.openWindow(windowClass.getConstructor(WrappedBaubles.class).newInstance(this.content));
            }
        } catch (Exception var5) {
            var5.printStackTrace();
        }

    }
}
