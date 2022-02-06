package com.ventivu.CustomStuff3Plugin.mixin.mixins;

import com.ventivu.CustomStuff3Plugin.CSP;
import ru.timeconqueror.spongemixins.MinecraftURLClassPath;

import java.io.File;
import java.io.FileNotFoundException;

public class ModMixinManager {
    public static boolean loadModJar(final String jarName) {
        try {
            File jar = MinecraftURLClassPath.getJarInModPath(jarName);
            if(jar == null) {
                CSP.warn("目标jar不存在"+jarName);
                return false;
            }

            if(!jar.exists()) {
                throw new FileNotFoundException(jar.toString());
            }
            MinecraftURLClassPath.addJar(jar);
            return true;
        }
        catch (Exception e) {
            System.out.println("$$$$$$$$$$$$$$$$$$error$$$$$$$$$$$$$$$$$$");
            e.printStackTrace();
            return false;
        }
    }
}
