package ink.ikx.rt.mixins.jei;

import com.feed_the_beast.mods.ftbultimine.FTBUltimine;
import com.google.common.base.Stopwatch;
import ink.ikx.rt.Main;
import mezz.jei.api.ingredients.IIngredientRegistry;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.api.recipe.IVanillaRecipeFactory;
import mezz.jei.plugins.vanilla.VanillaRecipeFactory;
import mezz.jei.plugins.vanilla.anvil.AnvilRecipeMaker;
import mezz.jei.util.ErrorUtil;
import mezz.jei.util.Log;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerRepair;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Pseudo
@Mixin(value = AnvilRecipeMaker.class, remap = false)
public class MixinAnvilRecipeMaker {

    @Inject(
    method = "getAnvilRecipes",
    at = @At(
            value = "RETURN",
            target = "Lmezz/jei/plugins/vanilla/anvil/AnvilRecipeMaker;getAnvilRecipes(Lmezz/jei/api/recipe/IVanillaRecipeFactory;Lmezz/jei/api/ingredients/IIngredientRegistry;)Ljava/util/List;"
    ),
    cancellable = true)
    private static void getAnvilRecipes(IVanillaRecipeFactory vanillaRecipeFactory, IIngredientRegistry ingredientRegistry, CallbackInfoReturnable<List<IRecipeWrapper>> cir) {
        List<IRecipeWrapper> recipes = cir.getReturnValue();
        VanillaRecipeFactory modRegistry = new VanillaRecipeFactory();

        for (int i = 0; i < Main.ANVIL_RECIPE_RIGHT_LIST.size(); i++) {
            IRecipeWrapper recipeWrapper = modRegistry.createAnvilRecipe(Main.ANVIL_RECIPE_LEFT_LIST.get(i), Collections.singletonList(Main.ANVIL_RECIPE_RIGHT_LIST.get(i)), Collections.singletonList(Main.ANVIL_RECIPE_OUT_LIST.get(i)));
            recipes.add(recipeWrapper);
        }

        cir.setReturnValue(recipes);
    }


    @Inject(
            method = "findLevelsCost",
            at = @At(
                    value = "RETURN",
                    target = "Lmezz/jei/plugins/vanilla/anvil/AnvilRecipeMaker;findLevelsCost(Lnet/minecraft/item/ItemStack;Lnet/minecraft/item/ItemStack;)I"
            ),
            cancellable = true)
    private static void findLevelsCost(ItemStack leftStack, ItemStack rightStack, CallbackInfoReturnable<Integer> cir) {
        EntityPlayer player = Minecraft.getMinecraft().player;
        if (player == null) {
            cir.setReturnValue(-1);
        } else {
            InventoryPlayer fakeInventory = new InventoryPlayer(player);
            ContainerRepair repair = new ContainerRepair(fakeInventory, player.world, player);

            for (int i = 0; i < Main.ANVIL_RECIPE_COST_LIST.size(); i++) {
                if (leftStack.equals(Main.ANVIL_RECIPE_LEFT_LIST.get(i)) && rightStack.equals(Main.ANVIL_RECIPE_RIGHT_LIST.get(i))) {
                    cir.setReturnValue(Main.ANVIL_RECIPE_COST_LIST.get(i));
                }
            }
        }
    }
}
