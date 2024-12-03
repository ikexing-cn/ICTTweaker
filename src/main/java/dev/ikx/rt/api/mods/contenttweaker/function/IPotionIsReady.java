package dev.ikx.rt.api.mods.contenttweaker.function;

import youyihj.zenutils.api.zenscript.SidedZenRegister;

import stanhebben.zenscript.annotations.ZenClass;


@FunctionalInterface
@SidedZenRegister(modDeps = "contenttweaker")
@ZenClass("mods.randomtweaker.cote.IPotionIsReady")
public interface IPotionIsReady {

    boolean call(int duration, int amplifier);

}
