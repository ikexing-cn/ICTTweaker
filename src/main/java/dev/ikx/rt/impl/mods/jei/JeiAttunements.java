package dev.ikx.rt.impl.mods.jei;

import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import hellfirepvp.astralsorcery.common.lib.BlocksAS;
import dev.ikx.rt.api.mods.jei.IJeiUtils;
import dev.ikx.rt.api.mods.jei.JEIExpansion;
import dev.ikx.rt.impl.mods.astralsorcery.CustomAttunementRecipe;
import dev.ikx.rt.impl.mods.jei.recipe.JEIAttunementRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentTranslation;

public class JeiAttunements {

    public static final String UID = "randomtweaker:jei_attunements";
    private static final String TEXTURE = "randomtweaker:textures/gui/jei/altarattunement.png";
    private static final IItemStack ALTAR = CraftTweakerMC.getIItemStack(new ItemStack(BlocksAS.attunementAltar));

    public static void init() {
        JEIExpansion.createJei(UID, new TextComponentTranslation("randomtweaker.jei_attunements").getUnformattedComponentText())
                .setIcon(ALTAR)
                .setBackground(IJeiUtils.createBackground(0, 0, 115, 161, TEXTURE))
                .addRecipeCatalyst(ALTAR)
                .addSlot(IJeiUtils.createItemSlot(49, 76, true, false))
                .addSlot(IJeiUtils.createItemSlot(49, 17, false, false))
                .register_();
        getRecipes();
    }

    private static void getRecipes() {
        for (CustomAttunementRecipe recipe : CustomAttunementRecipe.allRecipes) {
            JEIAttunementRecipe JEIRecipe = (JEIAttunementRecipe)
                    new JEIAttunementRecipe(UID)
                            .setConstellation(recipe.getConstellation())
                            .addInput(recipe.getOriginal().amount(recipe.getOriginal().getAmount()))
                            .addOutput(CraftTweakerMC.getIItemStack(recipe.getResult()));
            JEIRecipe.build_();
        }
    }
}
