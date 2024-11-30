package dev.ikx.rt.api.mods.contenttweaker;

import dev.ikx.rt.api.mods.contenttweaker.aspect.CTAspectRepresentation;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenExpansion;
import stanhebben.zenscript.annotations.ZenMethodStatic;
import youyihj.zenutils.api.zenscript.SidedZenRegister;


@SidedZenRegister(modDeps = {"thaumcraft", "contenttweaker"})
@ZenExpansion("mods.contenttweaker.VanillaFactory")
@ZenClass("mods.randomtweaker.cote.VanillaFactoryExpansionWithThaumcraft")
public abstract class ExpandVanillaFactoryWithThaumcraft {

    @ZenMethodStatic
    public static CTAspectRepresentation createAspect(String tag, int color) {
        return new CTAspectRepresentation(tag, color);
    }

}
