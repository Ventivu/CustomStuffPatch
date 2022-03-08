package com.ventivu.CustomStuff3Plugin.baubles;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import com.ventivu.CustomStuff3Plugin.baubles.Windows.WrappedBaubles;
import cubex2.cs3.common.BaseContentPack;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.List;

public class BaublesItem extends Item implements IBauble {
    private final BaubleAttributes attributes;
    protected BaseContentPack pack;
    protected WrappedBaubles wrappedItem;
    public BaublesItem(WrappedBaubles item) {
        this.attributes= item.container;
        this.pack = item.getPack();
        this.wrappedItem = item;
    }

    public void postInit() {
    }

    public void setHarvestLevel(String toolClass, int level) {
        if (!toolClass.equals("noHarvest") && !toolClass.equals("all")) {
            super.setHarvestLevel(toolClass, level);
        }
    }

    @Override
    public void registerIcons(IIconRegister p_94581_1_) {
        super.registerIcons(p_94581_1_);
    }


    public int getItemStackLimit(ItemStack stack) {
        return 1;
    }

    @Override
    public CreativeTabs getCreativeTab() {
        return this.wrappedItem.getCreativeTab();
    }

    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        return this.wrappedItem.onItemRightClick(stack, world, player);
    }

    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        return this.wrappedItem.onItemUse(stack, player, world, x, y, z, side, hitX, hitY, hitZ);
    }

    public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        return this.wrappedItem.onItemUseFirst(stack, player, world, x, y, z, side, hitX, hitY, hitZ);
    }

    public boolean useItemOnEntity(ItemStack stack, EntityPlayer player, EntityLivingBase target) {
        return this.wrappedItem.useItemOnEntity(stack, player, target);
    }

    public boolean hitEntity(ItemStack stack, EntityLivingBase living1, EntityLivingBase living2) {
        return this.wrappedItem.hitEntity(stack, living1, living2);
    }

    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
        return this.wrappedItem.onLeftClickEntity(stack, player, entity);
    }

    public void onUpdate(ItemStack stack, World world, Entity entity, int slotId, boolean isCurrentItem) {
        this.wrappedItem.onUpdate(stack, world, entity, slotId, isCurrentItem);
    }

    public void onCreated(ItemStack stack, World world, EntityPlayer player) {
        this.wrappedItem.onCreated(stack, world, player);
    }

    public boolean onDroppedByPlayer(ItemStack stack, EntityPlayer player) {
        return this.wrappedItem.onDroppedByPlayer(stack, player);
    }

    public int getMaxItemUseDuration(ItemStack stack) {
        return this.wrappedItem.getMaxItemUseDuration(stack);
    }

    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean advanced) {
        this.wrappedItem.addInformation(stack, player, list, advanced);
    }

    public boolean doesContainerItemLeaveCraftingGrid(ItemStack stack) {
        return this.wrappedItem.doesContainerItemLeaveCraftingGrid(stack);
    }

    @Override
    public BaubleType getBaubleType(ItemStack itemStack) {
        return wrappedItem.getBaublesType().type;
    }

    @Override
    public void onWornTick(ItemStack itemStack, EntityLivingBase entityLivingBase) {
        this.wrappedItem.onWornTick(itemStack, entityLivingBase);
    }

    @Override
    public void onEquipped(ItemStack itemStack, EntityLivingBase entityLivingBase) {
        this.wrappedItem.onEquipped(itemStack, entityLivingBase);
    }

    @Override
    public void onUnequipped(ItemStack itemStack, EntityLivingBase entityLivingBase) {
        this.wrappedItem.onUnequipped(itemStack, entityLivingBase);
    }

    @Override
    public boolean canEquip(ItemStack itemStack, EntityLivingBase entityLivingBase) {
        return wrappedItem.caninput();
    }

    @Override
    public boolean canUnequip(ItemStack itemStack, EntityLivingBase entityLivingBase) {
        return wrappedItem.canoutput();
    }
}
