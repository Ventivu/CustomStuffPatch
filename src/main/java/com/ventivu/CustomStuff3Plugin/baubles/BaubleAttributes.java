package com.ventivu.CustomStuff3Plugin.baubles;

import com.ventivu.CustomStuff3Plugin.baubles.Windows.*;
import cubex2.cs3.common.BaseContentPack;
import cubex2.cs3.common.attribute.Attribute;
import cubex2.cs3.common.attribute.AttributeContainer;
import cubex2.cs3.ingame.gui.Window;
import cubex2.cs3.ingame.gui.common.WindowEditScript;
import cubex2.cs3.util.IconWrapper;
import cubex2.cs3.util.ScriptWrapper;
import net.minecraft.creativetab.CreativeTabs;

public class BaubleAttributes extends AttributeContainer {
    @Attribute(windowClass = WindowEditScript.class)
    public ScriptWrapper onWornTick;

    @Attribute(windowClass = WindowEditScript.class)
    public ScriptWrapper onEquipped;

    @Attribute(windowClass = WindowEditScript.class)
    public ScriptWrapper onUnequipped;

    @Attribute(windowClass = WindowEditName.class)
    public String displayName = "未命名";
    @Attribute(windowClass = WindowEditInf.class)
    public String information = null;
    @Attribute(windowClass = WindowEditIcon.class)
    public IconWrapper icon = new IconWrapper("");
    public int maxUsingDuration = 0;
    @Attribute(windowClass = WindowCreativeTab.class, loadOnPostInit = true)
    public CreativeTabs creativeTab;
    @Attribute(windowClass = WindowSwitchInOut.class)
    public boolean CanTakeOut;
    @Attribute(windowClass = Window.class,hasOwnWindow = false)
    public boolean canPlaceIn;
    @Attribute(windowClass = WindowEditScript.class)
    public ScriptWrapper onRightClick;
    @Attribute(windowClass = WindowEditScript.class)
    public ScriptWrapper onUse;
    @Attribute(windowClass = WindowEditScript.class)
    public ScriptWrapper onUseFirst;
    @Attribute(windowClass = WindowEditScript.class)
    public ScriptWrapper onUseOnEntity;
    @Attribute(windowClass = WindowEditScript.class)
    public ScriptWrapper onUseOnPlayer;
    @Attribute(windowClass = WindowEditScript.class)
    public ScriptWrapper onHitEntity;
    @Attribute(windowClass = WindowEditScript.class)
    public ScriptWrapper onLeftClickLiving;
    @Attribute(windowClass = WindowEditScript.class)
    public ScriptWrapper onLeftClickPlayer;
    @Attribute(windowClass = WindowEditScript.class)
    public ScriptWrapper onUpdate;
    @Attribute(windowClass = WindowEditScript.class)
    public ScriptWrapper onCreated;
    @Attribute(windowClass = WindowEditScript.class)
    public ScriptWrapper onDroppedByPlayer;

    public BaubleAttributes(BaseContentPack pack) {
        super(pack);
    }
}
