package com.ventivu.CustomStuff3Plugin.baubles.Windows;

import com.ventivu.CustomStuff3Plugin.baubles.BaubleAttributes;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.ReflectionHelper;
import cubex2.cs3.api.scripting.ITriggerData;
import cubex2.cs3.api.scripting.TriggerType;
import cubex2.cs3.common.AttributeContent;
import cubex2.cs3.common.BaseContentPack;
import cubex2.cs3.common.attribute.AttributeContainer;
import cubex2.cs3.common.scripting.TriggerData;
import cubex2.cs3.item.ItemCS;
import cubex2.cs3.util.*;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import java.util.*;

public class WrappedBaubles extends AttributeContent implements Comparable<WrappedBaubles> {
    public Item item;
    public BaubleAttributes container;
    private EnumBaubleItemType type;

    public WrappedBaubles(String name,EnumBaubleItemType type, BaseContentPack pack) {
        super(name, pack);
        this.type=type;
    }
    public WrappedBaubles(BaseContentPack pack) {
        super(pack);
    }

    private void initItem() {
        this.item.setUnlocalizedName(this.name);
        if (this.container.icon != null) {
            this.item.setTextureName(this.container.icon.iconString);
        }
        Map<String, Properties> modLangData =ReflectionHelper.getPrivateValue(LanguageRegistry.class, LanguageRegistry.instance(), new String[]{"modLanguageData"});
        Properties p = modLangData.get("en_US");
        p.put("item." + this.name + ".name", this.container.displayName);
    }

    public void apply() {
        if (this.item != null) {
            this.initItem();
        }

        super.apply();
    }

    public void postInit() {
        if (this.item != null && this.item instanceof ItemCS) {
            ((ItemCS)this.item).postInit();
        }

    }

    public boolean canEdit() {
        return this.item != null;
    }

    public void edit() {
        super.edit();
    }

    public void remove() {
        super.remove();
    }



    public boolean readFromNBT(final NBTTagCompound compound) {
        this.name = compound.getString("Name");
        this.type = EnumBaubleItemType.get(compound.getString("Type"));
        this.container = this.type.createAttributeContainer(this);
        container.loadFromNBT(compound.getCompoundTag("Attributes"), false);
        this.pack.postponeHandler.addTask(new PostponableTask() {
            protected boolean doWork() {
                WrappedBaubles.this.container.loadFromNBT(compound.getCompoundTag("Attributes"), true);
                return true;
            }
        });
        this.item = this.type.createItem(this);
        return true;
    }
    public CreativeTabs getCreativeTab() {
        return this.container.creativeTab;
    }

    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        boolean result = false;
        if (this.container.onUse != null && this.container.onUse.script != null) {
            ITriggerData data = (new TriggerData("onUse", TriggerType.ITEM, world, player, stack)).setPosition(x, y, z).setSideAndHit(side, hitX, hitY, hitZ);
            result = JavaScriptHelper.executeTrigger(this.container.onUse.script, data, this.pack, result);
        }

        return result;
    }

    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (this.container.onRightClick != null && this.container.onRightClick.script != null) {
            ITriggerData data = new TriggerData("onRightClick", TriggerType.ITEM, world, player, stack);
            stack = JavaScriptHelper.executeTrigger(this.container.onRightClick.script, data, this.pack, stack);
        }

        return stack;
    }

    public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        boolean result = false;
        if (this.container.onUseFirst != null && this.container.onUseFirst.script != null) {
            ITriggerData data = (new TriggerData("onUseFirst", TriggerType.ITEM, world, player, stack)).setPosition(x, y, z).setSideAndHit(side, hitX, hitY, hitZ);
            result = JavaScriptHelper.executeTrigger(this.container.onUseFirst.script, data, this.pack, result);
        }

        return result;
    }

    public boolean useItemOnEntity(ItemStack stack, EntityPlayer player, EntityLivingBase target) {
        boolean result = false;
        TriggerData data;
        if (target instanceof EntityPlayer && this.container.onUseOnPlayer != null && this.container.onUseOnPlayer.script != null) {
            data = (new TriggerData("onUseOnPlayer", TriggerType.ITEM, player.worldObj, player, stack)).setInteractPlayer((EntityPlayer)target);
            result = JavaScriptHelper.executeTrigger(this.container.onUseOnPlayer.script, data, this.pack, result);
        } else if (this.container.onUseOnEntity != null && this.container.onUseOnEntity.script != null) {
            data = (new TriggerData("onUseOnEntity", TriggerType.ITEM, player.worldObj, player, stack)).setLiving(target);
            result = JavaScriptHelper.executeTrigger(this.container.onUseOnEntity.script, data, this.pack, result);
        }

        return result;
    }

    public boolean hitEntity(ItemStack stack, EntityLivingBase living1, EntityLivingBase living2) {
        boolean result = false;
        if (this.container.onHitEntity != null && this.container.onHitEntity.script != null && living2 instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)living2;
            ITriggerData data = (new TriggerData("onHitEntity", TriggerType.ITEM, player.worldObj, player, stack)).setLiving(living1);
            result = JavaScriptHelper.executeTrigger(this.container.onHitEntity.script, data, this.pack, result);
        }

        return result;
    }

    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
        boolean result = false;
        TriggerData data;
        if (this.container.onLeftClickLiving != null && this.container.onLeftClickLiving.script != null && entity instanceof EntityLiving) {
            EntityLiving living = (EntityLiving)entity;
            data = (new TriggerData("onLeftClickLiving", TriggerType.ITEM, player.worldObj, player, stack)).setLiving(living);
            JavaScriptHelper.executeTrigger(this.container.onLeftClickLiving.script, data, this.pack);
        } else if (this.container.onLeftClickPlayer != null && this.container.onLeftClickPlayer.script != null && entity instanceof EntityPlayer) {
            EntityPlayer interactPlayer = (EntityPlayer)entity;
            data = (new TriggerData("onLeftClickPlayer", TriggerType.ITEM, player.worldObj, player, stack)).setInteractPlayer(interactPlayer);
            result = JavaScriptHelper.executeTrigger(this.container.onLeftClickPlayer.script, data, this.pack, result);
        }

        return result;
    }

    public void onUpdate(ItemStack stack, World world, Entity entity, int slotId, boolean isCurrentItem) {
        if (this.container.onUpdate != null && this.container.onUpdate.script != null && entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)entity;
            ITriggerData data = (new TriggerData("onUpdate", TriggerType.ITEM, player.worldObj, player, stack)).setSlotId(slotId).setIsCurrentItem(isCurrentItem);
            JavaScriptHelper.executeTrigger(this.container.onUpdate.script, data, this.pack);
        }

    }

    public void onCreated(ItemStack stack, World world, EntityPlayer player) {
        if (this.container.onCreated != null && this.container.onCreated.script != null) {
            ITriggerData data = new TriggerData("onCreated", TriggerType.ITEM, player.worldObj, player, stack);
            JavaScriptHelper.executeTrigger(this.container.onCreated.script, data, this.pack);
        }

    }

    public boolean onDroppedByPlayer(ItemStack stack, EntityPlayer player) {
        boolean result = false;
        if (this.container.onDroppedByPlayer != null && this.container.onDroppedByPlayer.script != null) {
            ITriggerData data = new TriggerData("onDroppedByPlayer", TriggerType.ITEM, player.worldObj, player, stack);
            result = JavaScriptHelper.executeTrigger(this.container.onDroppedByPlayer.script, data, this.pack, result);
        }

        return result;
    }

    public int getMaxItemUseDuration(ItemStack stack) {
        return this.container.maxUsingDuration;
    }

    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean advanced) {
        if (this.container.information != null) {
            String[] split = this.container.information.split("\n");
            Collections.addAll(list, split);
        }
    }

    public boolean doesContainerItemLeaveCraftingGrid(ItemStack stack) {
        return false;
    }

    public String getTypeString() {
        return this.type.name;
    }

    public EnumBaubleItemType getBaublesType() {
        return this.type;
    }

    public AttributeContainer getContainer() {
        return this.container;
    }

    public void onWornTick(ItemStack itemStack, EntityLivingBase entityLivingBase) {
        if (container.onWornTick != null && container.onWornTick.script != null) {
            ITriggerData data = new TriggerData("onWornTick", TriggerType.ITEM, entityLivingBase.worldObj, (EntityPlayer) entityLivingBase, itemStack);
            JavaScriptHelper.executeTrigger(this.container.onWornTick.script, data, this.pack);
        }
    }

    public void onEquipped(ItemStack itemStack, EntityLivingBase entityLivingBase) {
        if (container.onEquipped != null && container.onEquipped.script != null) {
            ITriggerData data = new TriggerData("onEquipped", TriggerType.ITEM, entityLivingBase.worldObj, (EntityPlayer) entityLivingBase, itemStack);
            JavaScriptHelper.executeTrigger(this.container.onEquipped.script, data, this.pack);
        }
    }

    public void onUnequipped(ItemStack itemStack, EntityLivingBase entityLivingBase) {
        if (container.onUnequipped != null && container.onUnequipped.script != null) {
            ITriggerData data = new TriggerData("onUnequipped", TriggerType.ITEM, entityLivingBase.worldObj, (EntityPlayer) entityLivingBase, itemStack);
            JavaScriptHelper.executeTrigger(this.container.onUnequipped.script, data, this.pack);
        }
    }

    public boolean caninput(){return container.canPlaceIn;}
    public boolean canoutput(){return container.CanTakeOut;}

    @Override
    public int compareTo(WrappedBaubles o) {
        return this.name.compareTo(o.name);
    }
}
