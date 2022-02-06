package com.ventivu.CustomStuff3Plugin;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class TestBlock extends Block {
    protected TestBlock(Material p_i45394_1_) {
        super(p_i45394_1_);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitx, float hity, float hitz) {
        player.addChatComponentMessage(new ChatComponentText(world.getTileEntity(x, y - 1, z).toString()) {
        });
        return true;
    }
}
