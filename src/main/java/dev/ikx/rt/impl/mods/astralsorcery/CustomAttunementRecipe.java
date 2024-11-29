package dev.ikx.rt.impl.mods.astralsorcery;

import crafttweaker.api.item.IIngredient;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import hellfirepvp.astralsorcery.common.constellation.IConstellation;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;

public class CustomAttunementRecipe {

    public static ArrayList<CustomAttunementRecipe> allRecipes = new ArrayList<>();

    final IConstellation constellation;
    final IIngredient original;
    final ItemStack result;

    public CustomAttunementRecipe(IConstellation c, IIngredient o, IItemStack r) {
        this.constellation = c;
        this.original = o;
        this.result = CraftTweakerMC.getItemStack(r);
    }

    public boolean canDoRecipe(IConstellation c, ItemStack stack) {
        //System.out.println(this.original+": checking against " + stack.toString());
        if (this.constellation == null) {
            return this.original.matches(CraftTweakerMC.getIItemStack(stack));
        } else {
            return (this.original.matches(CraftTweakerMC.getIItemStack(stack)) &&
                    c.equals(this.constellation));
        }
    }

    public IConstellation getConstellation() {
        return constellation;
    }

    public IIngredient getOriginal() {
        return original;
    }

    public ItemStack getResult() {
        return result;
    }
}
