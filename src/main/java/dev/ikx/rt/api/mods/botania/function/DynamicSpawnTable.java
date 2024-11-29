package dev.ikx.rt.api.mods.botania.function;

import youyihj.zenutils.api.zenscript.SidedZenRegister;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.player.IPlayer;

import stanhebben.zenscript.annotations.ZenClass;


@SidedZenRegister(modDeps = "botania")
@ZenClass("mods.randomtweaker.botania.DynamicSpawnTable")
@FunctionalInterface
public interface DynamicSpawnTable {

    String call(IItemStack stack, IPlayer player, ICocoonTileEntity tile);

}
