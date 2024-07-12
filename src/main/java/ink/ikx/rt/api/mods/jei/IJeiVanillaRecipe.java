package ink.ikx.rt.api.mods.jei;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.IAction;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import de.ellpeck.naturesaura.recipes.ModRecipes;
import ink.ikx.rt.Main;
import ink.ikx.rt.api.mods.jei.elements.IJeiElements;
import ink.ikx.rt.api.mods.tconstruct.IBook;
import ink.ikx.rt.impl.internal.config.RTConfig;
import ink.ikx.rt.impl.mods.jei.impl.elemenet.MCJeiElementManaBar;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.api.recipe.IVanillaRecipeFactory;
import mezz.jei.plugins.vanilla.VanillaRecipeFactory;
import mezz.jei.startup.ModRegistry;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.*;
import youyihj.zenutils.api.zenscript.SidedZenRegister;

import java.util.Collections;

@SidedZenRegister(modDeps = "jei")
@ZenClass("mods.jei.IJeiVanillaRecipe")
public abstract class IJeiVanillaRecipe {
    @ZenMethod
    public static void addAnvilRecipe(IItemStack left, IItemStack right, IItemStack out, @Optional(valueLong = 1) int cost) {
        CraftTweakerAPI.apply(new AddAnvilRecipeAction(left, right, out, cost));
    }

    public static class AddAnvilRecipeAction implements IAction {


        private final ItemStack left;
        private final ItemStack right;
        private final ItemStack out;
        private final int cost;

        public AddAnvilRecipeAction(IItemStack left, IItemStack right, IItemStack out, @Optional int cost) {
            this.left = CraftTweakerMC.getItemStack(left);
            this.right = CraftTweakerMC.getItemStack(right);
            this.out = CraftTweakerMC.getItemStack(out);
            this.cost = cost;
        }

        @Override
        public void apply() {
//            VanillaRecipeFactory modRegistry = new VanillaRecipeFactory();
//            IRecipeWrapper recipeWrapper = modRegistry.createAnvilRecipe(left, Collections.singletonList(right), Collections.singletonList(out));
            Main.ANVIL_RECIPE_OUT_LIST.add(out);
            Main.ANVIL_RECIPE_LEFT_LIST.add(left);
            Main.ANVIL_RECIPE_RIGHT_LIST.add(right);
            Main.ANVIL_RECIPE_COST_LIST.add(cost);
        }

        @Override
        public String describe() {
            return "";
        }

        @Override
        public boolean validate() {
            return true;
        }

        @Override
        public String describeInvalid() {
            return "";
        }
    }
}
