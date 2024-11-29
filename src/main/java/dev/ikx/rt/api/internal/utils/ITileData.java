package dev.ikx.rt.api.internal.utils;

import crafttweaker.CraftTweakerAPI;
import youyihj.zenutils.api.zenscript.SidedZenRegister;
import crafttweaker.api.data.DataMap;
import crafttweaker.api.data.IData;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenGetter;
import stanhebben.zenscript.annotations.ZenSetter;

/**
 * @author youyihj
 * <br /> Mit LICENSE
 * <br /> by -> https://github.com/friendlyhj/ZenUtils/blob/master/src/main/java/youyihj/zenutils/api/cotx/tile/TileData.java
 */

@SidedZenRegister(modDeps = "contenttweaker")
@ZenClass("mods.randomtweaker.utils.ITileData")
public interface ITileData extends INBTSerializable<NBTTagCompound> {

    static void checkDataMap(IData data) {
        if (!(data instanceof DataMap)) {
            CraftTweakerAPI.logError("data argument must be DataMap", new IllegalArgumentException());
        }
    }

    void readFromNBT(NBTTagCompound nbt);

    NBTTagCompound writeToNBT(NBTTagCompound nbt);

    @ZenGetter("data")
    IData getData();

    @ZenSetter("data")
    void setData(IData data);

}
