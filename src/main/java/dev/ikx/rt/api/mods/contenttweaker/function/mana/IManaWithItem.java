package dev.ikx.rt.api.mods.contenttweaker.function.mana;

import crafttweaker.api.item.IItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import youyihj.zenutils.api.zenscript.SidedZenRegister;

/**
 * @author superhelo
 */

@SidedZenRegister(modDeps = {"contenttweaker", "botania"})
@ZenClass("mods.randomtweaker.cote.IManaWithItem")
public interface IManaWithItem {

    boolean call(IItemStack stack, IItemStack otherStack);

}
