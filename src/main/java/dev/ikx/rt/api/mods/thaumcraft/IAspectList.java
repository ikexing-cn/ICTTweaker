package dev.ikx.rt.api.mods.thaumcraft;

import youyihj.zenutils.api.zenscript.SidedZenRegister;

import dev.ikx.rt.impl.mods.thaumcraft.MCAspectList;
import stanhebben.zenscript.annotations.*;
import thaumcraft.api.aspects.AspectList;


@SidedZenRegister(modDeps = "thaumcraft")
@ZenClass("mods.randomtweaker.thaumcraft.IAspectList")
@IterableSimple("mods.randomtweaker.thaumcraft.IAspect")
public interface IAspectList extends Iterable<IAspect> {

    static IAspectList of(AspectList list) {
        return new MCAspectList(list);
    }

    @ZenMethod
    static IAspectList of() {
        return IAspectList.of(new AspectList());
    }

    @ZenMethod
    @ZenGetter("size")
    int getSize();

    @ZenMethod
    @ZenGetter("visSize")
    int getVisSize();

    @ZenMethod
    int getAmount(IAspect key);

    @ZenMethod
    IAspectList copy();

    @ZenMethod
    IAspect[] getAspects();

    @ZenMethod
    IAspect[] getAspectsSortedByName();

    @ZenMethod
    IAspect[] getAspectsSortedByAmount();

    @ZenMethod
    IAspectList add(IAspectList in);

    @ZenMethod
    IAspectList add(IAspect key, @Optional(valueLong = 1) int amount);

    @ZenMethod
    boolean reduce(IAspect key, @Optional(valueLong = 1) int amount);

    @ZenMethod
    IAspectList remove(IAspect key);

    @ZenMethod
    IAspectList remove(IAspect key, int amount);

    @ZenMethod
    IAspectList remove(IAspectList in);

    @ZenMethod
    IAspectList merge(IAspectList in);

    @ZenMethod
    IAspectList merge(IAspect key, @Optional(valueLong = 1) int amount);

    @ZenMethod
    @ZenMemberGetter
    @ZenOperator(OperatorType.INDEXGET)
    IAspect get(int index);

    @ZenMethod
    @ZenOperator(OperatorType.CONTAINS)
    boolean contains(IAspect aspect);

    AspectList getInternal();

}
