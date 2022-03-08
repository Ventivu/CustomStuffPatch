
package com.ventivu.CustomStuff3Plugin.mixin;

import com.ventivu.CustomStuff3Plugin.mixin.mixins.*;
import com.ventivu.CustomStuff3Plugin.mixin.mixins.MixinCS3Gui;
import com.ventivu.CustomStuff3Plugin.mixin.mixins.MixinCS3Stream;
import com.ventivu.CustomStuff3Plugin.mixin.mixins.MixinExtendSlots;
import org.spongepowered.asm.lib.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class mixin implements IMixinConfigPlugin{
    @Override
    public void onLoad(String s) {
    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        return false;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {

    }

    @Override
    public List<String> getMixins() {
        List<String> mixins=new ArrayList<>();
        if(ModMixinManager.loadModJar("extrautilities")) {
            mixins.add(MixinQED.class.getSimpleName());
        }
        if(ModMixinManager.loadModJar("CustomStuff3")) {
            mixins.add(MixinCS3Stream.class.getSimpleName());
            mixins.add(MixinCS3Gui.class.getSimpleName());
            mixins.add(CSPackAdder.class.getSimpleName());
        }
        if(ModMixinManager.loadModJar("Baubles")){
            mixins.add(MixinExtendSlots.class.getSimpleName());
        }

        return mixins;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

    }
}
