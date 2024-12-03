package dev.ikx.rt.api.mods.contenttweaker.function;

import youyihj.zenutils.api.zenscript.SidedZenRegister;
import crafttweaker.api.entity.IEntityLivingBase;

import stanhebben.zenscript.annotations.ZenClass;


@FunctionalInterface
@SidedZenRegister(modDeps = "contenttweaker")
@ZenClass("mods.randomtweaker.cote.IPotionPerformEffect")
public interface IPotionPerformEffect {

    void call(IEntityLivingBase living, int amplifier);

}
