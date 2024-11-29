package dev.ikx.rt.api.mods.astralsorcery;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.IAction;
import youyihj.zenutils.api.zenscript.SidedZenRegister;
import crafttweaker.api.item.IIngredient;
import crafttweaker.api.item.IItemStack;
import hellfirepvp.astralsorcery.common.constellation.ConstellationRegistry;
import hellfirepvp.astralsorcery.common.constellation.IConstellation;
import dev.ikx.rt.impl.internal.config.RTConfig;
import dev.ikx.rt.impl.mods.astralsorcery.CustomAttunementRecipe;

import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;


@SidedZenRegister(modDeps = "astralsorcery")
@ZenClass("mods.randomtweaker.astralsorcery.AttunementAltar")
public class AttunementAltar {

    @ZenMethod
    public static void addRecipe(IIngredient input, IItemStack output, String constellationString) {
        CraftTweakerAPI.apply(
                new ActionAttunementAltar(input, output, constellationString)
        );
    }

    @ZenMethod
    public static void addRecipe(IIngredient input, IItemStack output) {
        CraftTweakerAPI.apply(
                new ActionAttunementAltar(input, output)
        );
    }

    private static class ActionAttunementAltar implements IAction {

        IIngredient ingredient;
        IItemStack output;
        String constellationString;

        public ActionAttunementAltar(IIngredient input, IItemStack output, String constellationString) {
            this.ingredient = input;
            this.output = output;
            this.constellationString = constellationString;
        }

        public ActionAttunementAltar(IIngredient input, IItemStack output) {
            this.ingredient = input;
            this.output = output;
            this.constellationString = null;
        }

        @Override
        public void apply() {
            if (constellationString == null) {
                CustomAttunementRecipe.allRecipes.add(
                        new CustomAttunementRecipe(null, ingredient, output)
                );
            } else {
                CustomAttunementRecipe.allRecipes.add(
                        new CustomAttunementRecipe(ConstellationRegistry.getConstellationByName(constellationString),
                                ingredient, output));
            }
        }

        @Override
        public String describe() {
            return "Add attunement recipe " +
                    ingredient.toString() + (constellationString != null ? ", constellation : " + constellationString : "")
                    + " -> " + output.toString();
        }

        @Override
        public boolean validate() {
            if (!RTConfig.Astralsorcery.attunementModification) {
                return false;
            }
            if (constellationString != null) {
                IConstellation constellation = ConstellationRegistry.getConstellationByName(constellationString);
                return constellation != null;
            }
            return true;
        }

        @Override
        public String describeInvalid() {
            if (!RTConfig.Astralsorcery.attunementModification) {
                return "Change \"attunementModification\" to true in the configuration file";
            }
            return "Unknown constellation for attunement altar recipe.";
        }
    }

}
