package dev.ikx.rt.impl.mods.naturesaura;

import crafttweaker.api.minecraft.CraftTweakerMC;
import crafttweaker.api.world.IBlockPos;
import crafttweaker.api.world.IWorld;
import dev.ikx.rt.api.mods.naturesaura.IAuraChunk;

public class MCAuraChunk implements IAuraChunk {

    private final de.ellpeck.naturesaura.api.aura.chunk.IAuraChunk auraChunk;

    public MCAuraChunk(IWorld world, IBlockPos pos) {
        this.auraChunk = de.ellpeck.naturesaura.api.aura.chunk.IAuraChunk.getAuraChunk(
                CraftTweakerMC.getWorld(world), CraftTweakerMC.getBlockPos(pos));
    }

    @Override
    public int drainAura(IBlockPos pos, int amount) {
        return auraChunk.drainAura(CraftTweakerMC.getBlockPos(pos), amount);
    }

    @Override
    public int drainAura(IBlockPos pos, int amount, boolean aimForZero, boolean simulate) {
        return auraChunk.drainAura(CraftTweakerMC.getBlockPos(pos), amount, aimForZero, simulate);
    }

    @Override
    public int storeAura(IBlockPos pos, int amount) {
        return auraChunk.storeAura(CraftTweakerMC.getBlockPos(pos), amount);
    }

    @Override
    public int storeAura(IBlockPos pos, int amount, boolean aimForZero, boolean simulate) {
        return auraChunk.storeAura(CraftTweakerMC.getBlockPos(pos), amount, aimForZero, simulate);
    }

    @Override
    public int getDrainSpot(IBlockPos pos) {
        return auraChunk.getDrainSpot(CraftTweakerMC.getBlockPos(pos));
    }

}
