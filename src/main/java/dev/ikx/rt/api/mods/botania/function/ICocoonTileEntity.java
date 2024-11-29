package dev.ikx.rt.api.mods.botania.function;

import youyihj.zenutils.api.zenscript.SidedZenRegister;
import crafttweaker.api.data.IData;
import crafttweaker.api.world.IBlockPos;
import crafttweaker.api.world.IWorld;

import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenGetter;
import stanhebben.zenscript.annotations.ZenMethod;


@SidedZenRegister(modDeps = "botania")
@ZenClass("mods.randomtweaker.botania.ICocoonTileEntity")
public interface ICocoonTileEntity {

    @ZenMethod
    @ZenGetter("world")
    IWorld getIWorld();

    @ZenMethod
    @ZenGetter("pos")
    IBlockPos getIBlockPos();

    @ZenMethod
    @ZenGetter("data")
    IData getData();

    @ZenMethod
    void updateData(IData data);

}
