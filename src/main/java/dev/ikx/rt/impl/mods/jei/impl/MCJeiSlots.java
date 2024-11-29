package dev.ikx.rt.impl.mods.jei.impl;

import dev.ikx.rt.api.mods.jei.IJeiUtils;
import dev.ikx.rt.api.mods.jei.slots.IJeiSlotItem;
import dev.ikx.rt.api.mods.jei.slots.IJeiSlotLiquid;
import net.minecraft.client.Minecraft;

public class MCJeiSlots {

    public static class MCJeiSlotItem extends IJeiSlotItem {

        public MCJeiSlotItem(int x, int y, boolean isInput, boolean hasBase) {
            super(x, y, isInput, hasBase);
        }

        public MCJeiSlotItem(String slotName, int x, int y, boolean isInput, boolean hasBase) {
            super(slotName, x, y, isInput, hasBase);
        }

        @Override
        public void render(Minecraft minecraft) {
            if (!hasBase) return;
            if (isInput) {
                IJeiUtils.createItemInputElement(x, y).render(minecraft);
            } else {
                IJeiUtils.createItemOutputElement(x, y).render(minecraft);
            }
        }

    }

    public static class MCJeiSlotLiquid extends IJeiSlotLiquid {

        public MCJeiSlotLiquid(int x, int y, int width, int height, int capacityMb,
                               boolean showCapacity, boolean isInput, boolean hasBase) {
            super(x, y, isInput, hasBase, width, height, capacityMb, showCapacity);
        }

        public MCJeiSlotLiquid(String slotName, int x, int y, boolean isInput, boolean hasBase,
                               int width, int height, int capacityMb, boolean showCapacity) {
            super(slotName, x, y, isInput, hasBase, width, height, capacityMb, showCapacity);
        }

        @Override
        public void render(Minecraft minecraft) {
            if (!hasBase) return;
            IJeiUtils.createLiquidElement(x, y, width, height).render(minecraft);
        }

    }

}
