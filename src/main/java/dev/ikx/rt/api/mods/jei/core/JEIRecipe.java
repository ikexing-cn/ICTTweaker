package dev.ikx.rt.api.mods.jei.core;

import youyihj.zenutils.api.zenscript.SidedZenRegister;
import crafttweaker.api.item.IIngredient;
import dev.ikx.rt.api.mods.jei.elements.JEIElement;

import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;


@SidedZenRegister(modDeps = "jei")
@ZenClass("mods.randomtweaker.jei.JEIRecipe")
public interface JEIRecipe {

    @ZenMethod
    JEIRecipe setInputs(IIngredient[] inputs);

    @ZenMethod
    JEIRecipe setOutputs(IIngredient[] outputs);

    @ZenMethod
    JEIRecipe setElements(JEIElement[] elements);

    @ZenMethod
    JEIRecipe addInput(IIngredient input);

    @ZenMethod
    JEIRecipe addOutput(IIngredient output);

    @ZenMethod
    JEIRecipe addElement(JEIElement element);

    @ZenMethod
    JEIRecipe onJEITooltip(JEITooltip tooltip);

    @ZenMethod
    void build();

    void buildDirect();

    String getUid();

}
