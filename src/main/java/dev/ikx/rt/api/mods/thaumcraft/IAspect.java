package dev.ikx.rt.api.mods.thaumcraft;

import crafttweaker.CraftTweakerAPI;
import youyihj.zenutils.api.zenscript.SidedZenRegister;

import dev.ikx.rt.impl.mods.thaumcraft.MCAspect;
import org.apache.commons.lang3.StringUtils;
import stanhebben.zenscript.annotations.*;
import thaumcraft.api.aspects.Aspect;


@SidedZenRegister(modDeps = "thaumcraft")
@ZenClass("mods.randomtweaker.thaumcraft.IAspect")
public interface IAspect {

    static IAspect of(Aspect aspect) {
        return new MCAspect(aspect);
    }

    @ZenMethod
    static IAspect of(String name) {
        if (StringUtils.isAnyBlank(name) || !Aspect.aspects.containsKey(name)) {
            CraftTweakerAPI.logError("Invalid aspect name: " + name);
            return null;
        }
        return IAspect.of(Aspect.getAspect(name));
    }

    @ZenMethod
    static IAspect[] getAllAspects() {
        return Aspect.aspects.values().stream().map(IAspect::of).toArray(IAspect[]::new);
    }

    @ZenMethod
    @ZenGetter("name")
    String getName();

    @ZenMethod
    @ZenGetter("components")
    IAspect[] getComponents();

    @ZenMethod
    @ZenGetter("chatColor")
    String getChatcolor();

    @ZenOperator(OperatorType.EQUALS)
    boolean equals(IAspect other);

    Aspect getInternal();

}
