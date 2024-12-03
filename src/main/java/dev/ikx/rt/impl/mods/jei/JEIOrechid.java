package dev.ikx.rt.impl.mods.jei;

import crafttweaker.api.block.IBlockState;
import crafttweaker.api.item.IIngredient;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.item.IngredientOr;
import crafttweaker.api.minecraft.CraftTweakerMC;
import crafttweaker.mc1120.brackets.BracketHandlerOre;
import dev.ikx.rt.api.mods.jei.JEIUtils;
import dev.ikx.rt.api.mods.jei.JEIExpansion;
import dev.ikx.rt.impl.internal.utils.InternalUtils;
import dev.ikx.rt.impl.mods.botania.module.SubTileOrechidManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.oredict.OreDictionary;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.item.block.ItemBlockSpecialFlower;
import vazkii.botania.common.lib.LibBlockNames;

import java.util.Map;

public class JEIOrechid {

    private static final String UID = "randomtweaker:jei_orechid";
    private static final String TEXTURE = "botania:textures/gui/pureDaisyOverlay.png";
    private static final IItemStack FLOWER = CraftTweakerMC.getIItemStack(ItemBlockSpecialFlower.ofType(LibBlockNames.SUBTILE_ORECHID));

    public static void init() {
        JEIExpansion.createJEI("randomtweaker:jei_orechid", I18n.translateToLocal("randomtweaker.jei_orechid"))
                .setIcon(FLOWER)
                .setBackground(JEIUtils.createBackground(148, 45))
                .addElement(JEIUtils.createImageElement(48, 0, 64, 46, 0, 0, TEXTURE, 256, 256))
                .addRecipeCatalyst(FLOWER)
                .addRecipeCatalyst(CraftTweakerMC.getIItemStack(ItemBlockSpecialFlower.ofType(new ItemStack(ModBlocks.floatingSpecialFlower), LibBlockNames.SUBTILE_ORECHID)))
                .addSlot(JEIUtils.createItemSlot(40, 13, true, false))
                .addSlot(JEIUtils.createItemSlot(70, 13, true, false))
                .addSlot(JEIUtils.createItemSlot(99, 13, false, false))
                .registerDirect();
        getRecipes();
    }

    private static void getRecipes() {
        SubTileOrechidManager.oreWeights.forEach((k, v) -> {
            IBlockState blockState = CraftTweakerMC.getBlockState(k);
            ItemStack itemStack = new ItemStack(CraftTweakerMC.getBlock(blockState.getBlock()), 64, blockState.getMeta());
            IItemStack input = CraftTweakerMC.getIItemStack(itemStack);

            v.entrySet().stream()
                    .filter(o -> InternalUtils.collIsNotEmpty(OreDictionary.getOres(o.getKey())))
                    .forEach(o -> JEIExpansion.createJEIRecipe(UID).addInput(input).addInput(FLOWER).addOutput(getOutPut(v, o.getKey(), o.getValue())).buildDirect());
        });
    }

    private static IIngredient getOutPut(Map<String, Integer> map, String k, Integer v) {
        return BracketHandlerOre.getOre(k).getItems().stream()
                .map(i -> (IIngredient) i.amount(getInputAmount(map, v)))
                .reduce(IngredientOr::new)
                .orElse(BracketHandlerOre.getOre(k));
    }

    private static int getInputAmount(Map<String, Integer> map, Integer weight) {
        return Math.max(1, Math.round((float) weight * 64 / (map.values().stream().reduce(Integer::sum)).orElse(weight * 64 * 64)));
    }

}
