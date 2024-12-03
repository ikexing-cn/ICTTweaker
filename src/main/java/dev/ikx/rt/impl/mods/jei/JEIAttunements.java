package dev.ikx.rt.impl.mods.jei;

import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import dev.ikx.rt.api.mods.jei.JEIExpansion;
import dev.ikx.rt.api.mods.jei.JEIUtils;
import dev.ikx.rt.impl.mods.astralsorcery.CustomAttunementRecipe;
import dev.ikx.rt.impl.mods.jei.recipe.AttunementRecipeWarpper;
import hellfirepvp.astralsorcery.common.lib.BlocksAS;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentTranslation;

public class JEIAttunements {

    public static final String UID = "randomtweaker:jei_attunements";
    private static final String TEXTURE = "randomtweaker:textures/gui/jei/altarattunement.png";
    private static final IItemStack ALTAR = CraftTweakerMC.getIItemStack(new ItemStack(BlocksAS.attunementAltar));

    public static void init() {
        JEIExpansion.createJEI(UID, new TextComponentTranslation("randomtweaker.jei_attunements").getUnformattedComponentText())
                .setIcon(ALTAR)
                .setBackground(JEIUtils.createBackground(0, 0, 115, 161, TEXTURE))
                .addRecipeCatalyst(ALTAR)
                .addSlot(JEIUtils.createItemSlot(49, 76, true, false))
                .addSlot(JEIUtils.createItemSlot(49, 17, false, false))
                .registerDirect();
        getRecipes();
    }

    private static void getRecipes() {
        for (CustomAttunementRecipe recipe : CustomAttunementRecipe.allRecipes) {
            AttunementRecipeWarpper.JEIAttunementRecipe JEIRecipe = (AttunementRecipeWarpper.JEIAttunementRecipe)
                    new AttunementRecipeWarpper.JEIAttunementRecipe(UID)
                            .setConstellation(recipe.getConstellation())
                            .addInput(recipe.getOriginal().amount(recipe.getOriginal().getAmount()))
                            .addOutput(CraftTweakerMC.getIItemStack(recipe.getResult()));
            JEIRecipe.buildDirect();
        }
    }
}
