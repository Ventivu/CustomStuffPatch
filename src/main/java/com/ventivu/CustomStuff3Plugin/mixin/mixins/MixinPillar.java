package com.ventivu.CustomStuff3Plugin.mixin.mixins;


import com.rwtema.extrautils.tileentity.enderconstructor.IEnderFluxHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(com.rwtema.extrautils.tileentity.enderconstructor.TileEnderPillar.class)
public abstract class MixinPillar {
@Redirect(method = "updateEntity", at = @At(value = "INVOKE",target = "Lcom/rwtema/extrautils/tileentity/enderconstructor/IEnderFluxHandler;isActive()Z"),remap = false)
    public boolean ForceActive(IEnderFluxHandler Handler) {
        return true;
    }
}
