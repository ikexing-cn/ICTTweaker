package dev.ikx.rt.api.mods.thaumcraft.expand;

import com.blamejared.compat.thaumcraft.handlers.aspects.CTAspect;
import com.blamejared.compat.thaumcraft.handlers.aspects.CTAspectStack;
import dev.ikx.rt.api.mods.thaumcraft.IAspect;
import youyihj.zenutils.api.zenscript.SidedZenRegister;

import stanhebben.zenscript.annotations.ZenCaster;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenExpansion;
import stanhebben.zenscript.annotations.ZenMethod;


@SidedZenRegister(modDeps = {"thaumcraft", "modtweaker"})
@ZenExpansion("mods.randomtweaker.thaumcraft.IAspect")
@ZenClass("mods.randomtweaker.thaumcraft.expand.IAspect")
public abstract class ExpandIAspect {

    @ZenMethod
    @ZenCaster
    public static CTAspect asCTAspect(IAspect internal) {
        return new CTAspect(internal.getInternal());
    }

    @ZenMethod
    public static CTAspectStack asCTAspectStack(IAspect internal, int amount) {
        return new CTAspectStack(asCTAspect(internal), amount);
    }

}
