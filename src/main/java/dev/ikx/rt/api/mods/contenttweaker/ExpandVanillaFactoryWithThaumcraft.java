package dev.ikx.rt.api.mods.contenttweaker;

import dev.ikx.rt.api.mods.contenttweaker.aspect.IAspectRepresentation;
import dev.ikx.rt.impl.mods.contenttweaker.aspect.MCAspectRepresentation;
import youyihj.zenutils.api.zenscript.SidedZenRegister;

import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenExpansion;
import stanhebben.zenscript.annotations.ZenMethodStatic;


@SidedZenRegister(modDeps = {"thaumcraft", "contenttweaker"})
@ZenExpansion("mods.contenttweaker.VanillaFactory")
@ZenClass("mods.randomtweaker.cote.VanillaFactoryExpansionWithThaumcraft")
public abstract class ExpandVanillaFactoryWithThaumcraft {

    @ZenMethodStatic
    public static IAspectRepresentation createAspect(String tag, int color) {
        return new MCAspectRepresentation(tag, color);
    }

}
