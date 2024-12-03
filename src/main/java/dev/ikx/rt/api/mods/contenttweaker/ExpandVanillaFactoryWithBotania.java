package dev.ikx.rt.api.mods.contenttweaker;

import dev.ikx.rt.api.mods.contenttweaker.mana.bauble.CTManaBaubleRepresentation;
import dev.ikx.rt.api.mods.contenttweaker.mana.item.CTManaItemRepresentation;
import dev.ikx.rt.api.mods.contenttweaker.mana.item.tool.CTUsageManaItemRepresentation;
import dev.ikx.rt.api.mods.contenttweaker.subtile.functional.CTSubTileEntityFunctionalRepresentation;
import dev.ikx.rt.api.mods.contenttweaker.subtile.generating.CTSubTileEntityGeneratingRepresentation;
import stanhebben.zenscript.annotations.Optional;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenExpansion;
import stanhebben.zenscript.annotations.ZenMethodStatic;
import youyihj.zenutils.api.zenscript.SidedZenRegister;


@SidedZenRegister(modDeps = {"botania", "contenttweaker"})
@ZenExpansion("mods.contenttweaker.VanillaFactory")
@ZenClass("mods.randomtweaker.cote.VanillaFactoryExpansionWithBotania")
public class ExpandVanillaFactoryWithBotania {

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
    public static CTSubTileEntityGeneratingRepresentation createSubTileGenerating(String unlocalizedName, @Optional int color) {
        return new CTSubTileEntityGeneratingRepresentation(color, unlocalizedName);
    }

    @ZenMethodStatic
    public static CTSubTileEntityFunctionalRepresentation createSubTileFunctional(String unlocalizedName, @Optional int color) {
        return new CTSubTileEntityFunctionalRepresentation(color, unlocalizedName);
    }

}
