package com.ventivu.CustomStuff3Plugin.mixin.mixins;


import baubles.common.container.InventoryBaubles;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = InventoryBaubles.class,remap = false)
public class MixinExtendSlots {
    private static final int slotCount=4;
    @Shadow
    public ItemStack[] stackList;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void extend(CallbackInfo info){
        this.stackList=new ItemStack[slotCount];
    }
}
