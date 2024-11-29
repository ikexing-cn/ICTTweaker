package dev.ikx.rt.impl.mods.botania.module;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.liquid.ILiquidStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubTileHydroangeasManager {

    public static List<HydroangeasHandler> handlerList = new ArrayList<>();
    public static Map<IItemStack, Double> blockFactorList = new HashMap<>();
    public static int burnTime = 40;

    public static void setBlockBelowFactor(IItemStack block, double factor) {
        if (block.isItemBlock()) {
            blockFactorList.put(block, factor);
        } else {
            CraftTweakerAPI.getLogger().logError(block + " is not a block.");
        }
    }

    public static Block getBlock(ILiquidStack inputFluid) {
        return CraftTweakerMC.getLiquidStack(inputFluid).getFluid().getBlock();
    }

    public static class HydroangeasHandler {

        ILiquidStack liquidConsume;
        ILiquidStack liquidCatalyst;
        double fluidFactor;
        int manaGen;

        public HydroangeasHandler(ILiquidStack input, int manaGen, ILiquidStack liquidCatalyst, double factor) {
            this.liquidConsume = input;
            this.manaGen = manaGen;
            this.fluidFactor = factor;
            this.liquidCatalyst = liquidCatalyst;
        }

        public Block getBlockLiquid() {
            return getBlock(liquidConsume);
        }

        public int getManaGen() {
            return this.manaGen;
        }

        public double getFluidFactor() {
            return this.fluidFactor;
        }

        public Block getBlockLiquidCatalyst() {
            if (liquidCatalyst == null) {
                return Blocks.AIR;
            }
            return getBlock(liquidCatalyst);
        }

    }

}