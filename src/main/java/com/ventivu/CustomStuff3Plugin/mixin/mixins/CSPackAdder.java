package com.ventivu.CustomStuff3Plugin.mixin.mixins;

import com.ventivu.CustomStuff3Plugin.baubles.BaublesRegistry;
import com.ventivu.CustomStuff3Plugin.baubles.Windows.WrappedBaubles;
import cubex2.cs3.common.BaseContentPack;
import cubex2.cs3.common.Content;
import cubex2.cs3.registry.ContentRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.File;
@SuppressWarnings("all")
@Mixin(value = BaseContentPack.class, remap = false)
public abstract class CSPackAdder {
    @Inject(method = "<init>", at = @At("RETURN"))
    public void baublesReg(File directory, String name, String id, CallbackInfo info) {
        registerContentRegistry(new BaublesRegistry((BaseContentPack)(Object) this), WrappedBaubles.class);
    }

    @Shadow
    protected abstract void registerContentRegistry(ContentRegistry registry, Class<? extends Content>... classes);
}
