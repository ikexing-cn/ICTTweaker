package dev.ikx.rt.api.mods.thaumicadditions;

import com.google.common.collect.Maps;
import crafttweaker.CraftTweakerAPI;
import crafttweaker.IAction;
import crafttweaker.api.block.IBlockState;
import crafttweaker.api.item.IIngredient;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import dev.ikx.rt.impl.internal.utils.InternalUtils;
import org.zeith.thaumicadditions.api.RecipesFluxConcentrator;
import stanhebben.zenscript.annotations.Optional;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;
import youyihj.zenutils.api.zenscript.SidedZenRegister;

import java.util.Map;


@SidedZenRegister(modDeps = "thaumadditions")
@ZenClass(FluxConcentrator.ZEN_CLASS)
public abstract class FluxConcentrator {

    public static final String ZEN_CLASS = "mods.randomtweaker.thaumadditions.FluxConcentrator";

    public static final Map<IBlockState, Boolean> LATE_REMOVES = Maps.newHashMap();

    @ZenMethod
    public static void addRecipes(IIngredient input, IItemStack output) {
        if (input == null || output == null) {
            CraftTweakerAPI.logError("input or output can't be null!!");
        } else if (!input.getItems().stream().allMatch(IItemStack::isItemBlock) || !output.isItemBlock()) {
            CraftTweakerAPI.logError("input or output is not a block!");
        } else {
            CraftTweakerAPI.apply(new AddRecipeAction(input, CraftTweakerMC.getBlockState(InternalUtils.getStateFromStack(output))));
        }
    }

    @ZenMethod
    public static void addRecipes(IBlockState input, IBlockState output) {
        if (input == null || output == null) {
            CraftTweakerAPI.logError("input or output can't be null!!");
        } else {
            CraftTweakerAPI.apply(new AddRecipeAction(input, output));
        }
    }

    @ZenMethod
    public static void removeRecipes(IItemStack output, @Optional boolean isRemoveAll) {
        if (output == null) {
            CraftTweakerAPI.logError("input can't be null!");
        } else if (!output.isItemBlock()) {
            CraftTweakerAPI.logError("input is not a block!");
        } else {
            FluxConcentrator.removeRecipes(CraftTweakerMC.getBlockState(InternalUtils.getStateFromStack(output)), isRemoveAll);
        }
    }

    @ZenMethod
    public static void removeRecipes(IBlockState output, @Optional boolean isRemoveAll) {
        if (output == null) {
            CraftTweakerAPI.logError("output can't be null!");
        } else {
            CraftTweakerAPI.apply(new RemoveRecipeAction(output, isRemoveAll));
        }
    }

    // Reloading this recipe would be very hack, so we don't support it!
    public static class AddRecipeAction implements IAction {

        private final IBlockState output;
        private IIngredient input;
        private IBlockState input_;

        public AddRecipeAction(IIngredient input, IBlockState output) {
            this.input = input;
            this.output = output;
        }

        public AddRecipeAction(IBlockState input_, IBlockState output) {
            this.input_ = input_;
            this.output = output;
        }

        @Override
        public void apply() {
            if (input_ != null) {
                RecipesFluxConcentrator.handle(CraftTweakerMC.getBlockState(input_),
                        RecipesFluxConcentrator.output(CraftTweakerMC.getBlockState(output)));
            } else {
                for (IItemStack item : input.getItems()) {
                    if (item.getMetadata() == 32767) {
                        for (IItemStack subItem : item.getDefinition().getSubItems()) {
                            RecipesFluxConcentrator.handle(InternalUtils.getStateFromStack(subItem),
                                    RecipesFluxConcentrator.output(CraftTweakerMC.getBlockState(output)));
                        }
                    } else {
                        RecipesFluxConcentrator.handle(InternalUtils.getStateFromStack(item),
                                RecipesFluxConcentrator.output(CraftTweakerMC.getBlockState(output)));
                    }
                }
            }
        }

        @Override
        public String describe() {
            return "Adding flux concentrator recipe for " + (input == null ? input_.toCommandString() : input.toCommandString()) + " -> " + output.toCommandString();
        }

    }

    public static class RemoveRecipeAction implements IAction {

        private final IBlockState output;
        private final boolean isRemoveAll;

        public RemoveRecipeAction(IBlockState output, boolean isRemoveAll) {
            this.output = output;
            this.isRemoveAll = isRemoveAll;
        }

        @Override
        public void apply() {
            FluxConcentrator.LATE_REMOVES.put(output, isRemoveAll);
        }

        @Override
        public String describe() {
            return "Removing flux concentrator recipe for input -> " + output.toCommandString() + " (all: " + isRemoveAll + ")";
        }

    }

}
