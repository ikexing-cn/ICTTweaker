package dev.ikx.rt.impl.mods.jei;

import crafttweaker.api.item.IIngredient;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import dev.ikx.rt.api.mods.jei.JEIUtils;
import dev.ikx.rt.api.mods.jei.IJeiUtilsWithBotania;
import dev.ikx.rt.api.mods.jei.JEIExpansion;
import dev.ikx.rt.api.mods.jei.core.JEIRecipe;
import dev.ikx.rt.api.mods.jei.core.JEITooltip;
import dev.ikx.rt.api.mods.jei.elements.JEIElements;
import dev.ikx.rt.impl.mods.botania.module.SubTileHydroangeasManager;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.item.block.ItemBlockSpecialFlower;
import vazkii.botania.common.lib.LibBlockNames;

import java.util.ArrayList;
import java.util.List;

public class JEIHydroangeas {

    private static final String UID = "randomtweaker:jei_hydroangeas";
    private static final String TEXTURE = "randomtweaker:textures/gui/jei/hydroangeas.png";
    private static final IItemStack FLOWER = CraftTweakerMC.getIItemStack(ItemBlockSpecialFlower.ofType(LibBlockNames.SUBTILE_HYDROANGEAS));

    @SuppressWarnings("deprecation")
    public static void init() {
        JEIExpansion.createJEI(UID, I18n.translateToLocal("randomtweaker.jei_hydroangeas"))
                .setIcon(FLOWER)
                .setBackground(JEIUtils.createBackground(0, 0, 105, 64, TEXTURE))
                .addRecipeCatalyst(FLOWER)
                .addRecipeCatalyst(CraftTweakerMC.getIItemStack(ItemBlockSpecialFlower.ofType(new ItemStack(ModBlocks.floatingSpecialFlower), LibBlockNames.SUBTILE_HYDROANGEAS)))
                .addSlot(JEIUtils.createLiquidSlot(77, 9, true, false))
                .addSlot(JEIUtils.createLiquidSlot(77, 34, true, false))
                .registerDirect();
        getHydroangeasRecipes();
    }

    private static void getHydroangeasRecipes() {
        for (SubTileHydroangeasManager.HydroangeasHandler handler : SubTileHydroangeasManager.handlerList) {
            FluidStack blockInput = new FluidStack(FluidRegistry.lookupFluidForBlock(handler.getBlockLiquid()), 1000);
            int mana = handler.getManaGen();
            JEIElements.JEIElementManaBar manaBar = IJeiUtilsWithBotania.createJEIManaBarElement(2, 60, mana, 1);
            JEITooltip tooltip = (mouseX, mouseY) -> {
                List<String> text = new ArrayList<>();
                if (mouseX >= 2 && mouseX <= 103 && mouseY >= 60 && mouseY <= 64) {
                    text.add("mana: " + mana);
                }
                return text.toArray(new String[0]);
            };
            IIngredient iBlockInput = CraftTweakerMC.getIIngredient(blockInput);
            JEIRecipe recipe = JEIExpansion.createJEIRecipe(UID)
                    .addInput(iBlockInput)
                    .addElement(manaBar)
                    .onJEITooltip(tooltip);

            if (handler.getBlockLiquidCatalyst() != Blocks.AIR) {
                FluidStack fluidFactor = new FluidStack(FluidRegistry.lookupFluidForBlock(handler.getBlockLiquidCatalyst()), 1000);
                IIngredient iFluidFactor = CraftTweakerMC.getIIngredient(fluidFactor);
                recipe.addInput(iFluidFactor);
            }

            recipe.buildDirect();
        }
    }

}
