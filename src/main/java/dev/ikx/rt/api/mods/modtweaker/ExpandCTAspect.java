package dev.ikx.rt.api.mods.modtweaker;

import com.blamejared.compat.thaumcraft.handlers.aspects.CTAspect;
import dev.ikx.rt.api.mods.thaumcraft.IAspect;
import youyihj.zenutils.api.zenscript.SidedZenRegister;

import stanhebben.zenscript.annotations.ZenCaster;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenExpansion;
import stanhebben.zenscript.annotations.ZenMethod;


@SidedZenRegister(modDeps = {"thaumcraft", "modtweaker"})
@ZenExpansion("thaumcraft.aspect.CTAspect")
@ZenClass("mods.randomtweaker.modtweaker.CTAspect")
public  class ExpandCTAspect {

    @ZenMethod
    @ZenCaster
    public static IAspect asIAstral(CTAspect aspect) {
        return IAspect.of(aspect.getInternal());
    }

}
