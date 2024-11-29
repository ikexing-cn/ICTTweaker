package dev.ikx.rt.impl.mods.naturesaura;

import crafttweaker.api.world.IBlockPos;
import crafttweaker.api.world.IWorld;
import dev.ikx.rt.api.mods.naturesaura.IAuraChunk;
import dev.ikx.rt.compact.DeprecatedCompact;

@Deprecated
public class MCAuraChunk implements IAuraChunk {

    private final dev.ikx.rt.api.mods.naturesaura.MCAuraChunk auraChunk;

    public static DeprecatedCompact compact = new DeprecatedCompact("mods.randomtweaker.naturesaura.IAuraChunk", dev.ikx.rt.api.mods.naturesaura.MCAuraChunk.ZEN_CLASS);

    public MCAuraChunk(IWorld world, IBlockPos pos) {
        this.auraChunk = compact.call(() -> new dev.ikx.rt.api.mods.naturesaura.MCAuraChunk(world, pos), "`getAuraChunk` will be removed in the 1.6, please use `getAuraChunkFromWorld` instead.");
    }

    @Override
    public int drainAura(IBlockPos pos, int amount) {
        return compact.call(() -> auraChunk.drainAura(pos, amount));
    }

    @Override
    public int drainAura(IBlockPos pos, int amount, boolean aimForZero, boolean simulate) {
        return compact.call(() -> auraChunk.drainAura(pos, amount, aimForZero, simulate));
    }

    @Override
    public int storeAura(IBlockPos pos, int amount) {
        return compact.call(() -> auraChunk.storeAura(pos, amount));
    }

    @Override
    public int storeAura(IBlockPos pos, int amount, boolean aimForZero, boolean simulate) {
        return compact.call(() -> auraChunk.storeAura(pos, amount, aimForZero, simulate));
    }

    @Override
    public int getDrainSpot(IBlockPos pos) {
        return compact.call(() -> auraChunk.getDrainSpot(pos));
    }

}
