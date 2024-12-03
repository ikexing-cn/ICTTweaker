package dev.ikx.rt.impl.mods.jei.impl.core;

import com.google.common.collect.Lists;
import crafttweaker.CraftTweakerAPI;
import crafttweaker.IAction;
import crafttweaker.api.item.IIngredient;
import dev.ikx.rt.Main;
import dev.ikx.rt.api.mods.jei.core.JEIRecipe;
import dev.ikx.rt.api.mods.jei.core.JEITooltip;
import dev.ikx.rt.api.mods.jei.elements.JEIElement;
import youyihj.zenutils.api.reload.Reloadable;
import youyihj.zenutils.api.util.ReflectionInvoked;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

// TODO
public class MCJeiRecipe implements JEIRecipe {

    public final List<IIngredient> inputs = Lists.newArrayList();
    public final List<IIngredient> outputs = Lists.newArrayList();
    public final List<JEIElement> elements = Lists.newArrayList();
    public String uid;
    public JEITooltip tooltip;

    public MCJeiRecipe(String uid) {
        this.uid = uid;
    }

    @Override
    public JEIRecipe setInputs(IIngredient[] inputs) {
        this.inputs.clear();
        Arrays.stream(inputs).forEach(this::addInput);
        return this;
    }

    @Override
    public JEIRecipe setOutputs(IIngredient[] outputs) {
        this.outputs.clear();
        Arrays.stream(outputs).forEach(this::addOutput);
        return this;
    }

    @Override
    public JEIRecipe setElements(JEIElement[] elements) {
        this.elements.clear();
        Arrays.stream(elements).forEach(this::addElement);
        return this;
    }

    @Override
    public JEIRecipe addInput(IIngredient input) {
        this.inputs.add(input);
        return this;
    }

    @Override
    public JEIRecipe addOutput(IIngredient output) {
        this.outputs.add(output);
        return this;
    }

    @Override
    public JEIRecipe addElement(JEIElement element) {
        this.elements.add(element);
        return this;
    }

    @Override
    public JEIRecipe onJEITooltip(JEITooltip tooltip) {
        this.tooltip = tooltip;
        return this;
    }

    @Override
    public void build() {
        CraftTweakerAPI.apply(new ActionAddJeiRecipe(this));
    }

    @Override
    public void buildDirect() {
        Main.JEI_RECIPE_SET.add(this);
    }

    @Override
    public String getUid() {
        return uid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MCJeiRecipe that = (MCJeiRecipe) o;

        return Objects.equals(serialize(), that.serialize());
    }

    protected String serialize() {
        StringJoiner input = new StringJoiner(", ", "input -> [", "]");
        StringJoiner output = new StringJoiner(", ", ", output -> [", "]");
        this.inputs.stream().map(Objects::toString).forEach(input::add);
        this.outputs.stream().map(Objects::toString).forEach(output::add);
        return input.toString() + output;
    }

    @Override
    public int hashCode() {
        return serialize().hashCode();
    }

    @Override
    public String toString() {
        return serialize();
    }

    @Reloadable
    public static class ActionAddJeiRecipe implements IAction {

        private final MCJeiRecipe recipe;

        public ActionAddJeiRecipe(MCJeiRecipe recipe) {
            this.recipe = recipe;
        }

        @Override
        public void apply() {
            Main.JEI_RECIPE_SET.add(recipe);
        }

        @ReflectionInvoked
        public void undo() {
            Main.JEI_RECIPE_SET.remove(recipe);
        }

        @Override
        public String describe() {
            return "Adding JeiRecipe to " + recipe.uid + ", " + recipe;
        }

        @Override
        public boolean validate() {
            return !Main.JEI_RECIPE_SET.contains(recipe);
        }

        @Override
        public String describeInvalid() {
            return "The JeiRecipe already exists, don't add it repeatedly.";
        }

    }

}
