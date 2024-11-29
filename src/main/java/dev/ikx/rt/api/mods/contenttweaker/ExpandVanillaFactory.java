package dev.ikx.rt.api.mods.contenttweaker;

import youyihj.zenutils.api.zenscript.SidedZenRegister;
import dev.ikx.rt.api.mods.contenttweaker.potion.IPotionRepresentation;
import dev.ikx.rt.api.mods.contenttweaker.potion.IPotionTypeRepresentation;
import dev.ikx.rt.impl.mods.contenttweaker.potion.MCPotionRepresentation;
import dev.ikx.rt.impl.mods.contenttweaker.potion.MCPotionTypeRepresentation;

import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenExpansion;
import stanhebben.zenscript.annotations.ZenMethodStatic;


@SidedZenRegister(modDeps = "contenttweaker")
@ZenExpansion("mods.contenttweaker.VanillaFactory")
@ZenClass("mods.randomtweaker.cote.VanillaFactoryExpansion")
public abstract class ExpandVanillaFactory {

    @ZenMethodStatic
    public static IPotionRepresentation createPotion(String unlocalizedName, int color) {
        return new MCPotionRepresentation(color, unlocalizedName);
    }

    @ZenMethodStatic
    public static IPotionTypeRepresentation createPotionType(String unlocalizedName, IPotionRepresentation potion) {
        return new MCPotionTypeRepresentation(unlocalizedName, potion);
    }

}
