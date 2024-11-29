package dev.ikx.rt.api.mods.jei;

import youyihj.zenutils.api.zenscript.SidedZenRegister;
import dev.ikx.rt.api.mods.jei.core.IJeiBackground;
import dev.ikx.rt.api.mods.jei.elements.IJeiElements;
import dev.ikx.rt.api.mods.jei.slots.IJeiSlotItem;
import dev.ikx.rt.api.mods.jei.slots.IJeiSlotLiquid;

import dev.ikx.rt.impl.mods.jei.impl.MCJeiSlots;
import dev.ikx.rt.impl.mods.jei.impl.core.MCJeiBackground;
import dev.ikx.rt.impl.mods.jei.impl.elemenet.MCJeiElementArrow;
import dev.ikx.rt.impl.mods.jei.impl.elemenet.MCJeiElementLiquid;
import dev.ikx.rt.impl.mods.jei.impl.elemenet.MCJeiElements;
import stanhebben.zenscript.annotations.Optional;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;


@SidedZenRegister(modDeps = "jei")
@ZenClass("mods.randomtweaker.jei.IJeiUtils")
public abstract class IJeiUtils {

    @ZenMethod
    public static IJeiBackground createBackground(int width, int height) {
        return new MCJeiBackground(width, height);
    }

    @ZenMethod
    public static IJeiBackground createBackground(int u, int v, int width, int height, String resourceName) {
        return new MCJeiBackground(u, v, width, height, resourceName);
    }

    //-------------------------------------------------------------------------------------------

    @ZenMethod
    public static IJeiSlotLiquid createLiquidSlot(int x, int y, int width, int height, int capacityMb, boolean showCapacity,
                                                  boolean isInput, @Optional(valueBoolean = true) boolean hasBase) {
        return new MCJeiSlots.MCJeiSlotLiquid(x, y, width, height, capacityMb, showCapacity, isInput, hasBase);
    }

    @ZenMethod
    public static IJeiSlotLiquid createLiquidSlot(int x, int y, boolean isInput, @Optional(valueBoolean = true) boolean hasBase) {
        return createLiquidSlot(x, y, 16, 16, 1000, false, isInput, hasBase);
    }

    @ZenMethod
    public static IJeiSlotItem createItemSlot(int x, int y, boolean isInput, @Optional(valueBoolean = true) boolean hasBase) {
        return new MCJeiSlots.MCJeiSlotItem(x, y, isInput, hasBase);
    }

    @ZenMethod
    public static IJeiSlotLiquid createLiquidSlot(String slotName, int x, int y, int width, int height, int capacityMb, boolean showCapacity,
                                                  boolean isInput, @Optional(valueBoolean = true) boolean hasBase) {
        return new MCJeiSlots.MCJeiSlotLiquid(slotName, x, y, isInput, hasBase, width, height, capacityMb, showCapacity);
    }

    @ZenMethod
    public static IJeiSlotLiquid createLiquidSlot(String slotName, int x, int y, boolean isInput, @Optional(valueBoolean = true) boolean hasBase) {
        return createLiquidSlot(slotName, x, y, 16, 16, 1000, false, isInput, hasBase);
    }

    @ZenMethod
    public static IJeiSlotItem createItemSlot(String slotName, int x, int y, boolean isInput, @Optional(valueBoolean = true) boolean hasBase) {
        return new MCJeiSlots.MCJeiSlotItem(slotName, x, y, isInput, hasBase);
    }

    //-------------------------------------------------------------------------------------------

    @ZenMethod
    public static IJeiElements.IJeiElementItemInput createItemInputElement(int x, int y) {
        return new MCJeiElements.MCJeiElementItemInput(x, y);
    }

    @ZenMethod
    public static IJeiElements.IJeiElementItemOutput createItemOutputElement(int x, int y) {
        return new MCJeiElements.MCJeiElementItemOutput(x, y);
    }

    @ZenMethod
    public static IJeiElements.IJeiElementLiquid createLiquidElement(int x, int y, int width, int height) {
        return new MCJeiElementLiquid(x, y, width, height);
    }

    @ZenMethod
    public static IJeiElements.IJeiElementFontInfo createFontInfoElement(String info, int x, int y, int color, @Optional int width, @Optional int height) {
        return new MCJeiElements.MCJeiElementFontInfo(x, y, width, height, color, info);
    }

    @ZenMethod
    public static IJeiElements.IJeiElementArrow createArrowElement(int x, int y, int direction) {
        return new MCJeiElementArrow(x, y, direction);
    }

    @ZenMethod
    public static IJeiElements.IJeiElementImage createImageElement(int x, int y, int width, int height, int u, int v,
                                                                   String texture, int textureWidth, int textureHeight) {
        return new MCJeiElements.MCJeiElementImage(u, v, x, y, width, height, texture, textureWidth, textureHeight);
    }

    @ZenMethod
    public static IJeiElements.IJeiElementItemInput createItemInputElement(String elementName, int x, int y) {
        return new MCJeiElements.MCJeiElementItemInput(elementName, x, y);
    }

    @ZenMethod
    public static IJeiElements.IJeiElementItemOutput createItemOutputElement(String elementName, int x, int y) {
        return new MCJeiElements.MCJeiElementItemOutput(elementName, x, y);
    }

    @ZenMethod
    public static IJeiElements.IJeiElementLiquid createLiquidElement(String elementName, int x, int y, int width, int height) {
        return new MCJeiElementLiquid(elementName, x, y, width, height);
    }

    @ZenMethod
    public static IJeiElements.IJeiElementFontInfo createFontInfoElement(String elementName, String info, int x, int y, int color, @Optional int width, @Optional int height) {
        return new MCJeiElements.MCJeiElementFontInfo(elementName, x, y, width, height, color, info);
    }

    @ZenMethod
    public static IJeiElements.IJeiElementArrow createArrowElement(String elementName, int x, int y, int direction) {
        return new MCJeiElementArrow(elementName, x, y, direction);
    }

    @ZenMethod
    public static IJeiElements.IJeiElementImage createImageElement(String elementName, int x, int y, int width, int height, int u, int v,
                                                                   String texture, int textureWidth, int textureHeight) {
        return new MCJeiElements.MCJeiElementImage(elementName, u, v, x, y, width, height, texture, textureWidth, textureHeight);
    }

}
