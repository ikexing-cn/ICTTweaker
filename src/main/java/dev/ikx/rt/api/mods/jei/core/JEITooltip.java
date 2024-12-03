package dev.ikx.rt.api.mods.jei.core;

import youyihj.zenutils.api.zenscript.SidedZenRegister;

import stanhebben.zenscript.annotations.ZenClass;


@SidedZenRegister(modDeps = "jei")
@FunctionalInterface
@ZenClass("mods.randomtweaker.jei.JEITooltip")
public interface JEITooltip {

    String[] action(int mouseX, int mouseY);

}
