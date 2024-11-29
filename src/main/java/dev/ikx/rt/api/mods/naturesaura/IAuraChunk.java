package dev.ikx.rt.api.mods.naturesaura;

import youyihj.zenutils.api.zenscript.SidedZenRegister;
import crafttweaker.api.world.IBlockPos;

import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;


@SidedZenRegister(modDeps = "naturesaura")
@ZenClass("mods.randomtweaker.naturesaura.IAuraChunk")
public interface IAuraChunk {

    @ZenMethod
    int drainAura(IBlockPos pos, int amount);

    @ZenMethod
    int drainAura(IBlockPos pos, int amount, boolean aimForZero, boolean simulate);

    @ZenMethod
    int storeAura(IBlockPos pos, int amount);

    @ZenMethod
    int storeAura(IBlockPos pos, int amount, boolean aimForZero, boolean simulate);

    @ZenMethod
    int getDrainSpot(IBlockPos pos);

}
