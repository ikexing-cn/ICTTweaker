package dev.ikx.rt.impl.mods.jei.recipe;

import hellfirepvp.astralsorcery.client.util.RenderConstellation;
import hellfirepvp.astralsorcery.common.constellation.IConstellation;
import hellfirepvp.astralsorcery.common.constellation.star.StarLocation;
import hellfirepvp.astralsorcery.common.lib.BlocksAS;
import dev.ikx.rt.impl.mods.jei.impl.core.MCJeiRecipe;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.List;

public class AttunementRecipeWarpper extends DynamicRecipesWrapper {

    private final IConstellation constellation;

    public AttunementRecipeWarpper(MCJeiRecipe recipe) {
        super(recipe);
        JEIAttunementRecipe recipeAttunement = (JEIAttunementRecipe) recipe;
        this.constellation = recipeAttunement.getConstellation();
    }

    @Override
    @ParametersAreNonnullByDefault
    @SideOnly(Side.CLIENT)
    public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
        super.drawInfo(minecraft, recipeWidth, recipeHeight, mouseX, mouseY);
        int constellationOffsetX = 0;
        int constellationOffsetY = 40;
        int constellationScaleX = 5;
        int constellationScaleY = -40;

        if (constellation != null) {
            //GlStateManager.disableAlpha();
            RenderConstellation.renderConstellationIntoGUI(constellation.getConstellationColor(), constellation,
                    constellationOffsetX,
                    constellationOffsetY,
                    0,
                    recipeWidth + constellationScaleX,
                    recipeHeight + constellationScaleY,
                    2.0f,
                    new RenderConstellation.BrightnessFunction() {
                        @Override
                        public float getBrightness() {
                            return 0.5f;
                        }
                    },
                    true, false
            );
            //GlStateManager.enableAlpha();
            RenderHelper.enableGUIStandardItemLighting();
            for (StarLocation location : constellation.getStars()) {
                int x = constellationOffsetX + scaleToLocation(31, recipeWidth + constellationScaleX, location.x);
                int y = constellationOffsetY + scaleToLocation(31, recipeHeight + constellationScaleY, location.y);
                minecraft.getRenderItem().renderItemAndEffectIntoGUI(new ItemStack(BlocksAS.attunementRelay), x - 8, y - 8);
            }
            minecraft.getRenderItem().renderItemAndEffectIntoGUI(new ItemStack(BlocksAS.attunementAltar), 50, 90);
            RenderHelper.disableStandardItemLighting();
        } else {
            RenderHelper.enableGUIStandardItemLighting();
            minecraft.getRenderItem().renderItemAndEffectIntoGUI(new ItemStack(BlocksAS.attunementAltar), 50, 90);
            RenderHelper.disableStandardItemLighting();
        }


    }

    @Nonnull
    public List<String> getTooltipStrings(int mouseX, int mouseY) {
        ArrayList<String> result = new ArrayList<>();
        if (mouseX > 50 && mouseX <= (50 + 16)) {
            if (mouseY > 95 && mouseY < (95 + 16)) {
                if (constellation != null) {
                    result.add(I18n.translateToLocal("text.randomtweaker.attunement_constellation"));
                    result.add(I18n.translateToLocal(constellation.getUnlocalizedName()));
                } else {
                    result.add(I18n.translateToLocal("text.randomtweaker.attunement_any_constellation"));
                }
            }
        }

        return result;
    }

    public int scaleToLocation(int originalSize, int realSize, int originalLocation) {
        return (int) Math.round(((double) originalLocation * realSize) / (double) originalSize);
    }
}


