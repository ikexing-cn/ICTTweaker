package dev.ikx.rt.api.mods.botania.subtile;

import crafttweaker.api.item.IItemStack;
import crafttweaker.api.liquid.ILiquidStack;
import dev.ikx.rt.compact.DeprecatedCompact;
import stanhebben.zenscript.annotations.Optional;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;
import youyihj.zenutils.api.zenscript.SidedZenRegister;

@Deprecated
@SidedZenRegister(modDeps = "botania")
@ZenClass(IHydroangeas.ZEN_CLASS)
public class IHydroangeas {

    public static final String ZEN_CLASS = "mods.randomtweaker.botania.IHydroangeas";
    public static DeprecatedCompact compact = new DeprecatedCompact(ZEN_CLASS, Hydroangeas.ZEN_CLASS);

    @ZenMethod
    public static void addManaRecipe(ILiquidStack inputFluid, int mana) {
        compact.callVoid(() -> Hydroangeas.addManaRecipe(inputFluid, mana));
    }

    @ZenMethod
    public static void addManaRecipe(ILiquidStack inputFluid, int mana, ILiquidStack liquidCatalyst, double factor) {
        compact.callVoid(() -> Hydroangeas.addManaRecipe(inputFluid, mana, liquidCatalyst, factor));
    }

    @ZenMethod
    public static void setBlockBelowFactor(IItemStack block, @Optional(valueDouble = 2.0D) double factor) {
        compact.callVoid(() -> Hydroangeas.setBlockBelowFactor(block, factor));
    }

}
