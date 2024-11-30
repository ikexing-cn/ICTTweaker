package dev.ikx.rt.api.mods.contenttweaker;

import dev.ikx.rt.api.mods.contenttweaker.mana.bauble.CTManaBaubleRepresentation;
import dev.ikx.rt.api.mods.contenttweaker.mana.item.CTManaItemRepresentation;
import dev.ikx.rt.api.mods.contenttweaker.mana.item.tool.CTUsageManaItemRepresentation;
import dev.ikx.rt.api.mods.contenttweaker.subtile.functional.ISubTileEntityFunctionalRepresentation;
import dev.ikx.rt.api.mods.contenttweaker.subtile.generating.ISubTileEntityGeneratingRepresentation;
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
    public static CTManaItemRepresentation createManaItem(String unlocalizedName, @Optional(valueLong = 500000) int maxMana) {
        return new CTManaItemRepresentation(unlocalizedName, maxMana);
    }

    @ZenMethodStatic
    public static CTUsageManaItemRepresentation createManaUsingItem(String unlocalizedName, @Optional(valueLong = 500000) int maxMana) {
        return new CTUsageManaItemRepresentation(unlocalizedName, maxMana);
    }

    @ZenMethodStatic
    public static CTManaBaubleRepresentation createManaBauble(String unlocalizedName, String baubleType, @Optional(valueLong = 500000) int maxMana) {
        return new CTManaBaubleRepresentation(unlocalizedName, maxMana, baubleType);
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
