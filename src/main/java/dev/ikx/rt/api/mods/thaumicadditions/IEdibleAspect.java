package dev.ikx.rt.api.mods.thaumicadditions;

import crafttweaker.api.item.IItemStack;
import dev.ikx.rt.api.mods.thaumcraft.IAspect;
import dev.ikx.rt.api.mods.thaumcraft.IAspectList;
import dev.ikx.rt.impl.internal.compact.DeprecatedCompact;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;
import youyihj.zenutils.api.zenscript.SidedZenRegister;


@Deprecated
@SidedZenRegister(modDeps = "thaumadditions")
@ZenClass(IEdibleAspect.ZEN_CLASS)
public abstract class IEdibleAspect {

    public static final String ZEN_CLASS = "mods.randomtweaker.thaumadditions.IEdibleAspect";
    public static DeprecatedCompact compact = new DeprecatedCompact(ZEN_CLASS, MCEdibleAspect.ZEN_CLASS);

    @ZenMethod
    public static void addEatCall(IAspect aspect, MCEdibleAspect.EatFunction function) {
        compact.callVoid(() -> MCEdibleAspect.addEatCall(aspect, function));
    }

    @ZenMethod
    public static void addAdvancedEatCall(IAspect aspect, MCEdibleAspect.EatFunctionWithEntity function) {
        compact.callVoid(() -> MCEdibleAspect.addAdvancedEatCall(aspect, function));
    }

    @ZenMethod
    public static void removeEatCall(IAspect aspect) {
        compact.callVoid(() -> MCEdibleAspect.removeEatCall(aspect));
    }

    @ZenMethod
    public static IItemStack applyToFoodStack(IItemStack stack, IAspectList aspects) {
        return compact.call(() -> MCEdibleAspect.applyToFoodStack(stack, aspects));
    }

    @ZenMethod
    public static IAspectList getSalt(IItemStack stack) {
        return compact.call(() -> MCEdibleAspect.getSalt(stack));
    }

}
