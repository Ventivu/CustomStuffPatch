package com.ventivu.CustomStuff3Plugin.mixin.mixins;


import cubex2.cs3.ClientProxy;
import cubex2.cs3.util.ClientHelper;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.io.IOException;
import java.io.InputStream;

@Mixin(ClientHelper.class)
public class MixinCS3Stream {
    /**
     * @author Ventivu
     * @reason 重写doc文本搜寻逻辑以加入本身既存doc
     */
    @Overwrite(remap = false)
    public static String loadDocFile(String path) {
        try {
            InputStream stream =path.contains("Fixed")? com.ventivu.CustomStuff3Plugin.ClientProxy.resPack.getInputStream(new ResourceLocation("csp", "docs/" + path)): ClientProxy.resPack.getInputStream(new ResourceLocation("cs3", "docs/" + path));
            String res = IOUtils.toString(stream, Charsets.UTF_8).replace("\r", "");
            stream.close();
            return res;
        } catch (IOException e) {
            e.printStackTrace();
            return "TEXT --{\n[FILE NOT FOUND: " + path + " ]\n}--";
        }
    }
}
