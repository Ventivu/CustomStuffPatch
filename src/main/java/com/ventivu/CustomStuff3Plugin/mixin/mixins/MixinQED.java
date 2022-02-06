package com.ventivu.CustomStuff3Plugin.mixin.mixins;


import com.rwtema.extrautils.tileentity.enderconstructor.TileEnderConstructor;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TileEnderConstructor.class)
public abstract class MixinQED extends TileEntity {
    public boolean forceActive = false;
/**
 * @author Ventivu
 * @reason 加入额外的判断数据
 * */
    @Overwrite(remap = false)
    public boolean isActive() {
        return (
                (getBlockMetadata() == 1 && (getWorldObj()).isRemote) ||
                        (!(getWorldObj()).isRemote && canAddMorez()) ||
                        forceActive
        );
    }

    @Shadow(remap = false)
    public abstract boolean canAddMorez();

    @Inject(method = "func_145839_a",at = @At("TAIL"),remap = false)
    private void readnbt(NBTTagCompound par1NBTTagCompound, CallbackInfo ci){
        this.forceActive=par1NBTTagCompound.getBoolean("ForceActive");
    }
    @Inject(method = "func_145841_b",at = @At("TAIL"),remap = false)
    private void writenbt(NBTTagCompound par1NBTTagCompound, CallbackInfo ci){
        par1NBTTagCompound.setBoolean("ForceActive",forceActive);
    }
}
