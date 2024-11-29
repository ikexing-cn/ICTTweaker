package dev.ikx.rt.api.mods.thaumicadditions;

import crafttweaker.api.block.IBlockState;
import crafttweaker.api.item.IIngredient;
import crafttweaker.api.item.IItemStack;
import dev.ikx.rt.compact.DeprecatedCompact;
import stanhebben.zenscript.annotations.Optional;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;
import youyihj.zenutils.api.zenscript.SidedZenRegister;


@de
@SidedZenRegister(modDeps = "thaumadditions")
@ZenClass(IFluxConcentrator.ZEN_CLASS)
public abstract class IFluxConcentrator {

    public static final String ZEN_CLASS = "mods.randomtweaker.thaumadditions.IFluxConcentrator";
    public static DeprecatedCompact compact = new DeprecatedCompact(ZEN_CLASS, FluxConcentrator.ZEN_CLASS);

    @ZenMethod
    public static void addRecipes(IIngredient input, IItemStack output) {
        compact.callVoid(() -> FluxConcentrator.addRecipes(input, output));
    }

    @ZenMethod
    public static void addRecipes(IBlockState input, IBlockState output) {
        compact.callVoid(() -> FluxConcentrator.addRecipes(input, output));
    }

    @ZenMethod
    public static void removeRecipes(IItemStack output, @Optional boolean isRemoveAll) {
        compact.callVoid(() -> FluxConcentrator.removeRecipes(output, isRemoveAll));
    }

    @ZenMethod
    public static void removeRecipes(IBlockState output, @Optional boolean isRemoveAll) {
        compact.callVoid(() -> FluxConcentrator.removeRecipes(output, isRemoveAll));
    }

}
