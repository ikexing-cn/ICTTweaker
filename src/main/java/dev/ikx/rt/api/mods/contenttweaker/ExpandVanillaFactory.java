package dev.ikx.rt.api.mods.contenttweaker;

import dev.ikx.rt.api.mods.contenttweaker.potion.CTPotionRepresentation;
import dev.ikx.rt.api.mods.contenttweaker.potion.CTPotionTypeRepresentation;
import dev.ikx.rt.api.mods.contenttweaker.potion.IPotionRepresentation;
import dev.ikx.rt.api.mods.contenttweaker.potion.IPotionTypeRepresentation;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenExpansion;
import stanhebben.zenscript.annotations.ZenMethodStatic;
import youyihj.zenutils.api.zenscript.SidedZenRegister;


@SidedZenRegister(modDeps = "contenttweaker")
@ZenExpansion("mods.contenttweaker.VanillaFactory")
@ZenClass("mods.randomtweaker.cote.VanillaFactoryExpansion")
public abstract class ExpandVanillaFactory {

    @Deprecated
    @ZenMethodStatic
    public static IPotionRepresentation createPotion(String unlocalizedName, int color) {
        return new IPotionRepresentation(color, unlocalizedName);
    }

    @Deprecated
    @ZenMethodStatic
    public static IPotionTypeRepresentation createPotionType(String unlocalizedName, IPotionRepresentation potion) {
        return new IPotionTypeRepresentation(unlocalizedName, potion);
    }

    @ZenMethodStatic
    public static CTPotionRepresentation createMcPotion(String unlocalizedName, int color) {
        return new CTPotionRepresentation(color, unlocalizedName);
    }

    @ZenMethodStatic
    public static CTPotionTypeRepresentation createMcPotionType(String unlocalizedName, CTPotionRepresentation potion) {
        return new CTPotionTypeRepresentation(unlocalizedName, potion);
    }

}
