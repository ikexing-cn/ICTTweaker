package dev.ikx.rt.impl.internal.capability;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.INBTSerializable;

public class CapabilityRegistryHandler {

    @CapabilityInject(FTBUltimineTag.class)
    public static Capability<FTBUltimineTag> FTB_ULTIMINE_CAPABILITY;

    public static void registerFTBUltimineTag() {
        CapabilityManager.INSTANCE.register(FTBUltimineTag.class, new Capability.IStorage<FTBUltimineTag>() {
            @Nullable
            @Override
            public NBTBase writeNBT(Capability<FTBUltimineTag> capability, FTBUltimineTag instance, EnumFacing side) {
                return instance.serializeNBT();
            }

            @Override
            public void readNBT(Capability<FTBUltimineTag> capability, FTBUltimineTag instance, EnumFacing side, NBTBase nbt) {
                instance.deserializeNBT((NBTTagCompound) nbt);
            }
        }, FTBUltimineTag::new);
    }

    public static class FTBUltimineTagProvider implements ICapabilitySerializable<NBTTagCompound> {

        private final FTBUltimineTag instance = new FTBUltimineTag();
        private final Capability<FTBUltimineTag> capability = CapabilityRegistryHandler.FTB_ULTIMINE_CAPABILITY;

        @Override
        public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
            return this.capability.equals(capability);
        }

        @Nullable
        @Override
        public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
            return this.capability.equals(capability) ? this.capability.cast(instance) : null;
        }

        @Override
        public NBTTagCompound serializeNBT() {
            return this.instance.serializeNBT();
        }

        @Override
        public void deserializeNBT(NBTTagCompound nbt) {
            this.instance.deserializeNBT(nbt);
        }

    }

    public static class FTBUltimineTag implements INBTSerializable<NBTTagCompound> {

        private boolean allow = false;

        public boolean isAllow() {
            return allow;
        }

        public void setAllow(boolean allow) {
            this.allow = allow;
        }

        @Override
        public NBTTagCompound serializeNBT() {
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setBoolean("isAllow", isAllow());
            return nbt;
        }

        @Override
        public void deserializeNBT(NBTTagCompound nbt) {
            setAllow(nbt.getBoolean("isAllow"));
        }

    }

}
