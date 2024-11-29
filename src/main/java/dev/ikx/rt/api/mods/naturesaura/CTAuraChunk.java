package dev.ikx.rt.api.mods.naturesaura;

import crafttweaker.api.minecraft.CraftTweakerMC;
import crafttweaker.api.world.IBlockPos;
import crafttweaker.api.world.IWorld;
import de.ellpeck.naturesaura.api.aura.chunk.IAuraChunk;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;
import youyihj.zenutils.api.zenscript.SidedZenRegister;

@ZenClass(CTAuraChunk.ZEN_CLASS)
@SidedZenRegister(modDeps = "naturesaura")
public class CTAuraChunk {

    public static final String ZEN_CLASS = "mods.randomtweaker.naturesaura.AuraChunk";

    private final IAuraChunk auraChunk;

    public CTAuraChunk(IWorld world, IBlockPos pos) {
        this.auraChunk = IAuraChunk.getAuraChunk(CraftTweakerMC.getWorld(world), CraftTweakerMC.getBlockPos(pos));
    }

    @ZenMethod
    public int drainAura(IBlockPos pos, int amount) {
        return auraChunk.drainAura(CraftTweakerMC.getBlockPos(pos), amount);
    }

    @ZenMethod
    public int drainAura(IBlockPos pos, int amount, boolean aimForZero, boolean simulate) {
        return auraChunk.drainAura(CraftTweakerMC.getBlockPos(pos), amount, aimForZero, simulate);
    }

    @ZenMethod
    public int storeAura(IBlockPos pos, int amount) {
        return auraChunk.storeAura(CraftTweakerMC.getBlockPos(pos), amount);
    }

    @ZenMethod
    public int storeAura(IBlockPos pos, int amount, boolean aimForZero, boolean simulate) {
        return auraChunk.storeAura(CraftTweakerMC.getBlockPos(pos), amount, aimForZero, simulate);
    }

    @ZenMethod
    public int getDrainSpot(IBlockPos pos) {
        return auraChunk.getDrainSpot(CraftTweakerMC.getBlockPos(pos));
    }
}
