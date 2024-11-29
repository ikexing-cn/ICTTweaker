package dev.ikx.rt.impl.mods.naturesaura;

import crafttweaker.api.world.IBlockPos;
import crafttweaker.api.world.IWorld;
import dev.ikx.rt.api.mods.naturesaura.CTAuraChunk;
import dev.ikx.rt.api.mods.naturesaura.IAuraChunk;
import dev.ikx.rt.impl.internal.compact.DeprecatedCompact;

@Deprecated
public class MCAuraChunk implements IAuraChunk {

    private final CTAuraChunk auraChunk;

    public static DeprecatedCompact compact = new DeprecatedCompact("mods.randomtweaker.naturesaura.IAuraChunk", CTAuraChunk.ZEN_CLASS);

    public MCAuraChunk(IWorld world, IBlockPos pos) {
        this.auraChunk = compact.call(() -> new CTAuraChunk(world, pos), "`getAuraChunk` will be removed in the 1.6, please use `getAuraChunkFromWorld` instead.");
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
