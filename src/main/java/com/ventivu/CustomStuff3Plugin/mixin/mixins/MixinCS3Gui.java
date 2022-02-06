package com.ventivu.CustomStuff3Plugin.mixin.mixins;


import cubex2.cs3.ingame.docs.ParsedDocFile;
import cubex2.cs3.ingame.gui.GuiBase;
import cubex2.cs3.ingame.gui.Window;
import cubex2.cs3.ingame.gui.common.WindowDocs;
import cubex2.cs3.ingame.gui.control.Button;
import cubex2.cs3.ingame.gui.control.Control;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(cubex2.cs3.ingame.gui.WindowMain.class)
public abstract class MixinCS3Gui extends Window {
    private Button btnFix;


    public MixinCS3Gui(int width, int height) {
        super(width, height);
    }

    @Shadow(remap = false)
    private Button btnJSDoc;

    @Inject(method = "<init>", at = @At("RETURN"), remap = false)
    private void addFixerDocBtn(CallbackInfo ci) {
        this.btnFix = button("CSP修补补丁").top(this.btnJSDoc, 3).fillWidth(7).add();
    }

    @Inject(method = "controlClicked", at = @At("TAIL"), remap = false)
    private void addFixerDocWindow(Control c, int mouseX, int mouseY, CallbackInfo ci) {
        if (c == this.btnFix) {
            GuiBase.openWindow(new WindowDocs("Fix", ParsedDocFile.fromPath("Fixed.txt")));
        }
    }
}
