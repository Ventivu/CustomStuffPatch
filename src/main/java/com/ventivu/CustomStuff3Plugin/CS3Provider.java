package com.ventivu.CustomStuff3Plugin;

import cubex2.cs3.api.IContentPack;
import cubex2.cs3.api.scripting.IScriptableObjectProvider;
import cubex2.cs3.api.scripting.ITriggerData;

import java.util.ArrayList;
import java.util.List;

public class CS3Provider implements IScriptableObjectProvider {
    @Override
    public List<Object> getGlobalScriptObjects(IContentPack iContentPack) {
        List<Object> objects=new ArrayList<>();
        objects.add("Fix");
        objects.add(new ScriptableFix());
        return objects;
    }

    @Override
    public List<Object> getScriptObjectsForTrigger(ITriggerData iTriggerData, IContentPack iContentPack) {
        return null;
    }
}
