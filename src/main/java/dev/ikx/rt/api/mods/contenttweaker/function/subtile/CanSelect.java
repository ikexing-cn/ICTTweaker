package dev.ikx.rt.api.mods.contenttweaker.function.subtile;

import com.teamacronymcoders.contenttweaker.api.ctobjects.blockpos.IBlockPos;
import com.teamacronymcoders.contenttweaker.api.ctobjects.entity.player.ICTPlayer;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.world.IFacing;
import youyihj.zenutils.api.zenscript.SidedZenRegister;

import stanhebben.zenscript.annotations.ZenClass;


@FunctionalInterface
@SidedZenRegister(modDeps = {"contenttweaker", "botania"})
@ZenClass("mods.randomtweaker.cote.CanSelect")
public interface CanSelect {

    boolean call(ICTPlayer player, IItemStack wand, IBlockPos pos, IFacing side);

}
