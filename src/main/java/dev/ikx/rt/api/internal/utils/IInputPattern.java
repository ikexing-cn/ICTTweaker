package dev.ikx.rt.api.internal.utils;

import crafttweaker.api.item.IIngredient;
import dev.ikx.rt.impl.internal.utils.MCInputPattern;

import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;
import youyihj.zenutils.api.zenscript.SidedZenRegister;

import java.util.Map;

@SidedZenRegister
@ZenClass("mods.randomtweaker.utils.IInputPattern")
public interface IInputPattern {

    @ZenMethod
    static IInputPattern create(String... pattern) {
        return new MCInputPattern(pattern);
    }

    @ZenMethod
    static IInputPattern inputPattern(String... pattern) {
        return create(pattern);
    }

    @ZenMethod
    static IIngredient[][] inputPatternGet(String[] pattern, Map<String, IIngredient> mapping) {
        return create(pattern).transform(mapping).get();
    }

    @ZenMethod
    IInputPattern with(String character, IIngredient ingredient);

    @ZenMethod
    IInputPattern transform(Map<String, IIngredient> mapping);

    @ZenMethod
    IIngredient[][] get();

    @ZenMethod
    IIngredient[] getWithShapeless();

}
