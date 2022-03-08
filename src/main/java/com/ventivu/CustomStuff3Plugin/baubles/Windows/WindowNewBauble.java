package com.ventivu.CustomStuff3Plugin.baubles.Windows;

import cubex2.cs3.common.BaseContentPack;
import cubex2.cs3.common.WrappedItem;
import cubex2.cs3.ingame.gui.GuiBase;
import cubex2.cs3.ingame.gui.Window;
import cubex2.cs3.ingame.gui.control.*;

import java.util.Iterator;

public class WindowNewBauble extends Window implements IValidityProvider, IStringProvider<EnumBaubleItemType> {
    private final BaseContentPack pack;
    private final TextBox tbName;
    private final Label lblName;
    private final Label lblType;
    private final Label lblInfo;
    private final DropBox<EnumBaubleItemType> dbType;

    public WindowNewBauble(BaseContentPack pack) {
        super("新建饰品",10,180,201);
        this.pack=pack;
        lblName = this.label("名称:").at(7, 7).add();
        tbName = this.textBox().below(lblName).size(166, 17).add();
        this.lblType = this.label("饰品类型:").below(this.tbName, 5).add();
        this.dbType = this.dropBox(EnumBaubleItemType.values()).below(this.lblType).size(100, 15).add();
        this.lblInfo = this.label("重启Minecraft必需").below(this.dbType, 10).add();
        tbName.setValidityProvider(this);
        this.dbType.setSelectedValue(EnumBaubleItemType.RING);
        this.dbType.setStringProvider(this);
    }

    protected void controlClicked(Control c, int mouseX, int mouseY) {
        if (c == this.btnCreate) {
            WrappedBaubles item = new WrappedBaubles(this.tbName.getText().trim(), this.dbType.getSelectedValue(), this.pack);
            item.apply();
            GuiBase.openPrevWindow();
        } else {
            this.handleDefaultButtonClick(c);
        }

    }

    @Override
    public String getStringFor(EnumBaubleItemType enumBaubleItemType) {
        return enumBaubleItemType.display;
    }

    @Override
    public String checkValidity(TextBox tb) {
        String message = null;
        String text = tb.getText().trim();
        if (text.length() == 0) {
            message = "输入名称";
        } else {
            Iterator<WrappedItem> var4 = this.pack.getContentRegistry(WrappedItem.class).getContentList().iterator();
            Iterator<WrappedBaubles> var5 = this.pack.getContentRegistry(WrappedBaubles.class).getContentList().iterator();
            while(var4.hasNext()) {
                WrappedItem item = var4.next();
                if (item.getName().equals(text)) {
                    message = "已有同名称的物品！";
                    break;
                }
            }
            while(var5.hasNext()) {
                WrappedBaubles item = var5.next();
                if (item.getName().equals(text)) {
                    message = "已有同名称的物品！";
                    break;
                }
            }
        }

        this.btnCreate.setEnabled(message == null);
        return message;
    }
}
