package dev.ikx.rt.api.mods.contenttweaker.function.subtile;

import crafttweaker.api.item.IItemStack;
import youyihj.zenutils.api.zenscript.SidedZenRegister;

import stanhebben.zenscript.annotations.ZenClass;


@FunctionalInterface
@SidedZenRegister(modDeps = {"contenttweaker", "botania"})
@ZenClass("mods.randomtweaker.cote.PopulateDropStackNBTs")
public interface PopulateDropStackNBTs {

    void call(IItemStack[] drops);

}
