package dev.ikx.rt.impl.mods.jei;

import com.google.common.collect.Lists;
import crafttweaker.api.minecraft.CraftTweakerMC;
import dev.ikx.rt.Main;
import dev.ikx.rt.impl.mods.jei.impl.core.MCJeiPanel;
import dev.ikx.rt.impl.mods.jei.impl.core.MCJeiRecipe;
import dev.ikx.rt.impl.mods.jei.recipe.AttunementRecipeWarpper;
import dev.ikx.rt.impl.mods.jei.recipe.DynamicRecipesCategory;
import dev.ikx.rt.impl.mods.jei.recipe.DynamicRecipesWrapper;
import mezz.jei.Internal;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.ISubtypeRegistry;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import mezz.jei.gui.GuiHelper;
import net.minecraft.item.Item;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.stream.Collectors;

@mezz.jei.api.JEIPlugin
@ParametersAreNonnullByDefault
public class JEIPlugin implements IModPlugin {

    public static List<Item> subtypesToRegister = Lists.newArrayList();
    public static final String DEFAULT_TEXTURE = "randomtweaker:textures/gui/jei/jei_default.png";

    @Override
    public void registerItemSubtypes(ISubtypeRegistry subtypeRegistry) {
        subtypeRegistry.useNbtForSubtypes(subtypesToRegister.toArray(new Item[0]));
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        GuiHelper guiHelper = Internal.getHelpers().getGuiHelper();
        Main.JEI_PANEL_SET.forEach(p -> registry.addRecipeCategories(new DynamicRecipesCategory(guiHelper, (MCJeiPanel) p)));
    }

    @Override
    public void register(IModRegistry registry) {
        Main.JEI_PANEL_SET.forEach(p -> {
            MCJeiPanel panel = (MCJeiPanel) p;
            panel.recipeCatalysts.forEach(c -> registry.addRecipeCatalyst(CraftTweakerMC.getItemStack(c), panel.uid));
            List<MCJeiRecipe> recipeList = Main.JEI_RECIPE_SET.stream()
                    .filter(r -> r.getUid().equals(panel.uid))
                    .map(r -> (MCJeiRecipe) r)
                    .collect(Collectors.toList());

            if (panel.uid.equals(JEIAttunements.UID)) {
                registry.addRecipes(recipeList.stream().map(AttunementRecipeWarpper::new).collect(Collectors.toList()), panel.uid);
            } else {
                registry.addRecipes(recipeList.stream().map(DynamicRecipesWrapper::new).collect(Collectors.toList()), panel.uid);
            }
        });
    }

}