package dev.ikx.rt.api.mods.botania.subtile;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.IAction;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.liquid.ILiquidStack;
import crafttweaker.mods.jei.JEI;
import crafttweaker.mods.jei.actions.DescribeAction;
import dev.ikx.rt.impl.internal.utils.InternalUtils;
import dev.ikx.rt.impl.mods.botania.module.SubTileHydroangeasManager;
import net.minecraft.client.resources.I18n;
import stanhebben.zenscript.annotations.Optional;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;
import youyihj.zenutils.api.reload.Reloadable;
import youyihj.zenutils.api.util.ReflectionInvoked;
import youyihj.zenutils.api.zenscript.SidedZenRegister;

import java.util.Collections;
import java.util.Objects;

@SidedZenRegister(modDeps = "botania")
@ZenClass(Hydroangeas.ZEN_CLASS)
public class Hydroangeas {

    public static final String ZEN_CLASS = "mods.randomtweaker.botania.Hydroangeas";

    @ZenMethod
    public static void addManaRecipe(ILiquidStack inputFluid, int mana) {
        addManaRecipe(inputFluid, mana, null, 1.0D);
    }

    @ZenMethod
    public static void addManaRecipe(ILiquidStack inputFluid, int mana, ILiquidStack liquidCatalyst, double factor) {
        CraftTweakerAPI.apply(new Hydroangeas.ActionAddManaRecipe(mana, factor, inputFluid, liquidCatalyst));
    }

    @ZenMethod
    public static void setBlockBelowFactor(IItemStack block, @Optional(valueDouble = 2.0D) double factor) {
        CraftTweakerAPI.apply(new Hydroangeas.ActionBlockBelowFactor(block, factor));
    }

    @Reloadable
    public static class ActionAddManaRecipe implements IAction {
        int mana;
        double factor;
        ILiquidStack inputFluid;
        ILiquidStack liquidCatalyst;

        public ActionAddManaRecipe(int mana, double factor, ILiquidStack inputFluid, ILiquidStack liquidCatalyst) {
            this.mana = mana;
            this.factor = factor;
            this.inputFluid = inputFluid;
            this.liquidCatalyst = liquidCatalyst;
        }


        @ReflectionInvoked
        public void undo() {
            SubTileHydroangeasManager.handlerList.removeIf(handler ->
                    inputFluid.matches(handler.liquidConsume) && handler.manaGen == mana &&
                            factor == handler.fluidFactor && liquidCatalyst.matches(handler.liquidCatalyst));
        }

        @Override
        public void apply() {
            SubTileHydroangeasManager.handlerList.add(new SubTileHydroangeasManager.HydroangeasHandler(inputFluid, mana, liquidCatalyst, factor));
        }

        @Override
        public String describe() {
            if (Objects.isNull(liquidCatalyst))
                return "Adding IHydroangeas Recipes for input -> " + inputFluid.toCommandString() + " : " + mana;
            return "Adding IHydroangeas Recipes for input -> " + inputFluid.toCommandString() + " : " + mana +
                    ", liquidCatalyst -> " + liquidCatalyst.toCommandString() + ":" + factor;
        }
    }

    @Reloadable
    public static class ActionBlockBelowFactor implements IAction {
        private final double factor;
        private final IItemStack block;

        private IAction _instance;


        public ActionBlockBelowFactor(IItemStack block, double factor) {
            this.block = block;
            this.factor = factor;
        }

        @ReflectionInvoked
        public void undo() {
            JEI.DESCRIPTIONS.remove(_instance);
        }

        @Override
        public void apply() {
            SubTileHydroangeasManager.setBlockBelowFactor(block, factor);
            String desc = I18n.format("text.randomtweaker.hydroangeas_factorblock_description");
            String s_factor = I18n.format("text.randomtweaker.factor") + factor;
            DescribeAction describeAction = new DescribeAction(Collections.singletonList(block), new String[]{desc, s_factor}, block.toCommandString());
            _instance = describeAction;
            JEI.DESCRIPTIONS.add(describeAction);
        }

        @Override
        public String describe() {
            return "Setting IHydroangeas Block Below Factor is " + block.toCommandString() + ", factor: " + factor;
        }

        @Override
        public boolean validate() {
            return InternalUtils.isItemBlock(block);
        }

        @Override
        public String describeInvalid() {
            return "The IItemStack is not ItemBlock";
        }
    }

}
