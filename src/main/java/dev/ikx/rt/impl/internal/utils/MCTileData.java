package dev.ikx.rt.impl.internal.utils;

import crafttweaker.api.data.IData;
import crafttweaker.mc1120.data.NBTConverter;
import dev.ikx.rt.api.internal.utils.ITileData;
import net.minecraft.nbt.NBTTagCompound;

public class MCTileData implements ITileData {

    private final NBTTagCompound nbtTagCompound = new NBTTagCompound();

    public void readFromNBT(NBTTagCompound nbt) {
        this.nbtTagCompound.merge(nbt);
    }

    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        nbt.merge(this.nbtTagCompound);
        return nbt;
    }

    @Override
    public IData getData() {
        return NBTConverter.from(this.writeToNBT(new NBTTagCompound()), true);
    }

    @Override
    public void setData(IData data) {
        ITileData.checkDataMap(data);
        this.readFromNBT((NBTTagCompound) NBTConverter.from(data));
    }

    @Override
    public NBTTagCompound serializeNBT() {
        return nbtTagCompound;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        this.nbtTagCompound.merge(nbt);
    }

}
