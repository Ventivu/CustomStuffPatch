package com.ventivu.CustomStuff3Plugin.baubles.Windows;

import cubex2.cs3.ingame.gui.control.IconTextBox;
import cubex2.cs3.ingame.gui.control.Label;
import cubex2.cs3.util.ClientHelper;
import cubex2.cs3.util.GuiHelper;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Rectangle;

public class WindowEditIcon extends WindowBaubAttribute{
    private final IconTextBox textBox;

    public WindowEditIcon(WrappedBaubles item) {
        super(item, "图标", 150, 100);
        Label lblIcon = this.label("图标名称:").at(7, 7).add();
        this.infoButton("The format is [modId]:[texture]|[modId] is the mod's ID in all lowercase|  this has to match the name of folder|  located in the assets folder of the mod|[texture] is name of the file without \".png\"||Example: If the texture is located at| \"assets/mymod/textures/items/myItem.png\"|you have to enter| \"mymod:myItem\"").rightTo(lblIcon).add();
        this.textBox = this.iconTextBox(item.getPack(), "items").below(lblIcon).height(16).fillWidth(7).add();
        this.textBox.setMaxLength(256);
        String iconString = (this.container).icon.iconString;
        if (iconString.contains(":") && iconString.split(":")[0].equals(wrappedBaubles.getPack().id.toLowerCase())) {
            iconString = iconString.split(":")[1];
        }

        this.textBox.setText(iconString);
    }

    protected void applyChanges() {
        String text = this.textBox.getText().trim();
        String modId = text.contains(":") ? text.split(":")[0] : this.wrappedBaubles.getPack().id.toLowerCase();
        String textureName = text.contains(":") && text.indexOf(58) != text.length() - 1 ? text.split(":")[1] : text;
        (this.container).icon.iconString = modId + ":" + textureName;
            this.wrappedBaubles.item.setTextureName(modId + ":" + textureName);
        ClientHelper.refreshResources(this.mc);
    }

    public void draw(int mouseX, int mouseY, float renderTick) {
        super.draw(mouseX, mouseY, renderTick);
        if (this.textBox.getLocation() != null) {
            this.mc.renderEngine.bindTexture(this.textBox.getLocation());
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glScalef(0.0625F, 0.0625F, 1.0F);
            this.drawTexturedModalRect((this.getX() + 7 + 1) * 16, (this.getY() + 40 + 1) * 16, 0, 0, 256, 256);
            GL11.glScalef(16.0F, 16.0F, 1.0F);
        }

        if ((new Rectangle(this.getX() + 7, this.getY() + 40, 18, 18)).contains(mouseX, mouseY)) {
            GuiHelper.drawItemToolTip(new ItemStack(this.wrappedBaubles.item), mouseX, mouseY, this.mc.fontRenderer);
        }

        GuiHelper.drawBorder(this.getX() + 7, this.getY() + 40, this.getX() + 7 + 18, this.getY() + 40 + 18, -11842741);
    }
}
