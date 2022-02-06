package com.ventivu.CustomStuff3Plugin;

import net.minecraft.world.World;

public class SoundsHelper {
    public static void PlaySound(World world, double x, double y, double z, String FileLocation, float Vaule, float tones) {
        world.playSound(x, y, z, FileLocation, Vaule, tones, false);
    }

    public static void PlaySound(World world, double x, double y, double z, String FileLocation) {
        PlaySound(world, x, y, z, FileLocation, 1.0F, 1.0F);
    }
}
