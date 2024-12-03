package dev.ikx.rt.impl.mods.jei.recipe;

import crafttweaker.api.minecraft.CraftTweakerMC;
import dev.ikx.rt.api.mods.jei.slots.JEISlot;
import dev.ikx.rt.api.mods.jei.slots.JEISlotItem;
import dev.ikx.rt.api.mods.jei.slots.JEISlotLiquid;
import dev.ikx.rt.impl.mods.jei.impl.core.MCJeiPanel;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiFluidStackGroup;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@ParametersAreNonnullByDefault
public class DynamicRecipesCategory implements IRecipeCategory<DynamicRecipesWrapper> {

    private final IDrawable icon;
    private final MCJeiPanel panel;
    private final IDrawable background;

    public DynamicRecipesCategory(IGuiHelper guiHelper, MCJeiPanel panel) {
        this.panel = panel;
        this.icon = guiHelper.createDrawableIngredient(CraftTweakerMC.getItemStack(panel.icon));
        if (StringUtils.isBlank(panel.background.resourceName)) {
            this.background = guiHelper.createBlankDrawable(panel.background.width, panel.background.height);
        } else {
            ResourceLocation location = new ResourceLocation(panel.background.resourceName);
            this.background = guiHelper.createDrawable(location, panel.background.u, panel.background.v, panel.background.width, panel.background.height);
        }
    }

    @Nonnull
    @Override
    public String getUid() {
        return panel.uid;
    }

    @Nonnull
    @Override
    public String getTitle() {
        return panel.title;
    }

    @Nonnull
    @Override
    public String getModName() {
        return panel.modid;
    }

    @Nonnull
    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Nullable
    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void drawExtras(Minecraft minecraft) {
        GlStateManager.enableAlpha();
        panel.slots.forEach(s -> s.render(minecraft));
        panel.elements.forEach(e -> e.render(minecraft));
        GlStateManager.disableAlpha();
    }

    @Nonnull
    @Override
    public List<String> getTooltipStrings(int mouseX, int mouseY) {
        if (Objects.nonNull(panel.tooltip)) {
            return Arrays.stream(panel.tooltip.action(mouseX, mouseY)).collect(Collectors.toList());
        }
        return IRecipeCategory.super.getTooltipStrings(mouseX, mouseY);
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, DynamicRecipesWrapper recipeWrapper, IIngredients ingredients) {
        IGuiItemStackGroup group = recipeLayout.getItemStacks();
        IGuiFluidStackGroup fGroup = recipeLayout.getFluidStacks();
        for (int i = 0; i < panel.slots.size(); i++) {
            JEISlot slot = panel.slots.get(i);
            if (slot instanceof JEISlotItem) {
                group.init(i, slot.isInput, slot.x, slot.y);
            } else {
                JEISlotLiquid f = ((JEISlotLiquid) slot);
                fGroup.init(i, f.isInput, f.x, f.y, f.width, f.height, f.capacityMb, f.showCapacity, null);
            }
        }
        group.set(ingredients);
        fGroup.set(ingredients);
    }

}
