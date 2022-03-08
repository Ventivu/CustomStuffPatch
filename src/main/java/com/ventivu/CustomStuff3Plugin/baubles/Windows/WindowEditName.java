package com.ventivu.CustomStuff3Plugin.baubles.Windows;

import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.ReflectionHelper;
import cubex2.cs3.ingame.gui.control.IValidityProvider;
import cubex2.cs3.ingame.gui.control.TextBox;
import cubex2.cs3.util.ClientHelper;

import java.util.Map;
import java.util.Properties;

public class WindowEditName extends WindowBaubAttribute implements IValidityProvider {
    private final TextBox textBox = this.textBox().at(7, 7).height(16).fillWidth(7).add();

    public WindowEditName(WrappedBaubles item) {
        super(item,"显示名称",150,60);
        this.textBox.setText((this.container).displayName);
        this.textBox.setValidityProvider(this);
    }

    protected void applyChanges() {
        this.container.displayName = this.textBox.getText().trim();
        Map modLangData = ReflectionHelper.getPrivateValue(LanguageRegistry.class, LanguageRegistry.instance(), new String[]{"modLanguageData"});
        Properties p = (Properties)modLangData.get("en_US");
        p.put("item." + this.wrappedBaubles.getName() + ".name", (this.container).displayName);
        ClientHelper.refreshResources(this.mc);
    }

    public String checkValidity(TextBox tb) {
        String message = null;
        String text = tb.getText().trim();
        if (text.length() == 0) {
            message = "你忘了输入内容了";
        }

        this.btnEdit.setEnabled(message == null);
        return message;
    }
}
