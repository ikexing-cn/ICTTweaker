package dev.ikx.rt.api.mods.contenttweaker;

import dev.ikx.rt.api.mods.contenttweaker.mana.bauble.IManaBaubleRepresentation;
import dev.ikx.rt.api.mods.contenttweaker.mana.item.IManaItemRepresentation;
import dev.ikx.rt.api.mods.contenttweaker.mana.item.tool.IIsUsesManaItemRepresentation;
import dev.ikx.rt.api.mods.contenttweaker.subtile.functional.ISubTileEntityFunctionalRepresentation;
import dev.ikx.rt.api.mods.contenttweaker.subtile.generating.ISubTileEntityGeneratingRepresentation;
import dev.ikx.rt.impl.mods.contenttweaker.mana.bauble.MCManaBaubleRepresentation;
import dev.ikx.rt.impl.mods.contenttweaker.mana.item.MCManaItemRepresentation;
import dev.ikx.rt.impl.mods.contenttweaker.mana.item.tool.MCIsUsesManaItemRepresentation;
import dev.ikx.rt.impl.mods.contenttweaker.subtile.MCSubTileEntityRepresentation;
import youyihj.zenutils.api.zenscript.SidedZenRegister;

import stanhebben.zenscript.annotations.Optional;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenExpansion;
import stanhebben.zenscript.annotations.ZenMethodStatic;


@SidedZenRegister(modDeps = {"botania", "contenttweaker"})
@ZenExpansion("mods.contenttweaker.VanillaFactory")
@ZenClass("mods.randomtweaker.cote.VanillaFactoryExpansionWithBotania")
public abstract class ExpandVanillaFactoryWithBotania {

    @ZenMethodStatic
    public static IManaItemRepresentation createManaItem(String unlocalizedName, @Optional(valueLong = 500000) int maxMana) {
        return new MCManaItemRepresentation(unlocalizedName, maxMana);
    }

    @ZenMethodStatic
    public static IIsUsesManaItemRepresentation createManaUsingItem(String unlocalizedName, @Optional(valueLong = 500000) int maxMana) {
        return new MCIsUsesManaItemRepresentation(unlocalizedName, maxMana);
    }

    @ZenMethodStatic
    public static IManaBaubleRepresentation createManaBauble(String unlocalizedName, String baubleType, @Optional(valueLong = 500000) int maxMana) {
        return new MCManaBaubleRepresentation(unlocalizedName, maxMana, baubleType);
    }

    @ZenMethodStatic
    public static ISubTileEntityGeneratingRepresentation createSubTileGenerating(String unlocalizedName, @Optional int color) {
        return new MCSubTileEntityRepresentation.MCSubTileEntityGeneratingRepresentation(color, unlocalizedName);
    }

    @ZenMethodStatic
    public static ISubTileEntityFunctionalRepresentation createSubTileFunctional(String unlocalizedName, @Optional int color) {
        return new MCSubTileEntityRepresentation.MCSubTileEntityFunctionalRepresentation(color, unlocalizedName);
    }

}
