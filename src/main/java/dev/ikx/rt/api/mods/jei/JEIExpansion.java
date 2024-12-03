package dev.ikx.rt.api.mods.jei;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import dev.ikx.rt.impl.mods.jei.JEIPlugin;
import youyihj.zenutils.api.zenscript.SidedZenRegister;
import dev.ikx.rt.api.mods.jei.core.JEIPanel;
import dev.ikx.rt.api.mods.jei.core.JEIRecipe;
import net.minecraft.item.ItemStack;

import dev.ikx.rt.impl.mods.jei.impl.core.MCJeiPanel;
import dev.ikx.rt.impl.mods.jei.impl.core.MCJeiRecipe;
import stanhebben.zenscript.annotations.ZenExpansion;
import stanhebben.zenscript.annotations.ZenMethodStatic;

import java.util.Objects;

@SidedZenRegister(modDeps = "jei")
@ZenExpansion("mods.jei.JEI")
public class JEIExpansion {

    @ZenMethodStatic
    public static JEIPanel createJEI(String uid, String title) {
        return new MCJeiPanel(uid, title);
    }

    @ZenMethodStatic
    public static JEIRecipe createJEIRecipe(String uid) {
        return new MCJeiRecipe(uid);
    }

    @Deprecated
    @ZenMethodStatic
    public static void addItemNBTSubtype(IItemStack stack) {
        createItemNBTSubtype(stack);
        CraftTweakerAPI.logWarning("Please use `createItemNBTSubtype` instead of `addItemNBTSubtype`, it will be removed in the version 1.6.");
    }

    @ZenMethodStatic
    public static void createItemNBTSubtype(IItemStack stack) {
        ItemStack mcStack = CraftTweakerMC.getItemStack(stack);
        Objects.requireNonNull(mcStack.getItem());
        JEIPlugin.subtypesToRegister.add(mcStack.getItem());
    }

}
