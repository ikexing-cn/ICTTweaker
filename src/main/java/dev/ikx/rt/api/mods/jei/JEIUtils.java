package dev.ikx.rt.api.mods.jei;

import dev.ikx.rt.api.mods.jei.core.JEIBackground;
import dev.ikx.rt.api.mods.jei.elements.JEIElements;
import dev.ikx.rt.api.mods.jei.slots.JEISlotItem;
import dev.ikx.rt.api.mods.jei.slots.JEISlotLiquid;
import stanhebben.zenscript.annotations.Optional;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;
import youyihj.zenutils.api.zenscript.SidedZenRegister;


@SidedZenRegister(modDeps = "jei")
@ZenClass("mods.randomtweaker.jei.JEIUtils")
public class JEIUtils {

    @ZenMethod
    public static JEIBackground createBackground(int width, int height) {
        return new JEIBackground(width, height);
    }

    @ZenMethod
    public static JEIBackground createBackground(int u, int v, int width, int height, String resourceName) {
        return new JEIBackground(u, v, width, height, resourceName);
    }

    //-------------------------------------------------------------------------------------------

    @ZenMethod
    public static JEISlotLiquid createLiquidSlot(int x, int y, int width, int height, int capacityMb, boolean showCapacity,
                                                 boolean isInput, @Optional(valueBoolean = true) boolean hasBase) {
        return new JEISlotLiquid(x, y, isInput, hasBase, width, height, capacityMb, showCapacity);
    }

    @ZenMethod
    public static JEISlotLiquid createLiquidSlot(int x, int y, boolean isInput, @Optional(valueBoolean = true) boolean hasBase) {
        return createLiquidSlot(x, y, 16, 16, 1000, false, isInput, hasBase);
    }

    @ZenMethod
    public static JEISlotItem createItemSlot(int x, int y, boolean isInput, @Optional(valueBoolean = true) boolean hasBase) {
        return new JEISlotItem(x, y, isInput, hasBase);
    }

    @ZenMethod
    public static JEISlotLiquid createLiquidSlot(String slotName, int x, int y, int width, int height, int capacityMb, boolean showCapacity,
                                                 boolean isInput, @Optional(valueBoolean = true) boolean hasBase) {
        return new JEISlotLiquid(slotName, x, y, isInput, hasBase, width, height, capacityMb, showCapacity);
    }

    @ZenMethod
    public static JEISlotLiquid createLiquidSlot(String slotName, int x, int y, boolean isInput, @Optional(valueBoolean = true) boolean hasBase) {
        return createLiquidSlot(slotName, x, y, 16, 16, 1000, false, isInput, hasBase);
    }

    @ZenMethod
    public static JEISlotItem createItemSlot(String slotName, int x, int y, boolean isInput, @Optional(valueBoolean = true) boolean hasBase) {
        return new JEISlotItem(slotName, x, y, isInput, hasBase);
    }

    //-------------------------------------------------------------------------------------------

    @ZenMethod
    public static JEIElements.JEIElementItemInput createItemInputElement(int x, int y) {
        return new JEIElements.JEIElementItemInput(x, y);
    }

    @ZenMethod
    public static JEIElements.JEIElementItemOutput createItemOutputElement(int x, int y) {
        return new JEIElements.JEIElementItemOutput(x, y);
    }

    @ZenMethod
    public static JEIElements.JEIElementLiquid createLiquidElement(int x, int y, int width, int height) {
        return new JEIElements.JEIElementLiquid(x, y, width, height);
    }

    @ZenMethod
    public static JEIElements.JEIElementFontInfo createFontInfoElement(String info, int x, int y, int color, @Optional int width, @Optional int height) {
        return new JEIElements.JEIElementFontInfo(x, y, width, height, color, info);
    }

    @ZenMethod
    public static JEIElements.JEIElementArrow createArrowElement(int x, int y, int direction) {
        return new JEIElements.JEIElementArrow(x, y, direction);
    }

    @ZenMethod
    public static JEIElements.JEIElementImage createImageElement(int x, int y, int width, int height, int u, int v,
                                                                 String texture, int textureWidth, int textureHeight) {
        return new JEIElements.JEIElementImage(u, v, x, y, width, height, texture, textureWidth, textureHeight);
    }

    @ZenMethod
    public static JEIElements.JEIElementItemInput createItemInputElement(String elementName, int x, int y) {
        return new JEIElements.JEIElementItemInput(elementName, x, y);
    }

    @ZenMethod
    public static JEIElements.JEIElementItemOutput createItemOutputElement(String elementName, int x, int y) {
        return new JEIElements.JEIElementItemOutput(elementName, x, y);
    }

    @ZenMethod
    public static JEIElements.JEIElementLiquid createLiquidElement(String elementName, int x, int y, int width, int height) {
        return new JEIElements.JEIElementLiquid(elementName, x, y, width, height);
    }

    @ZenMethod
    public static JEIElements.JEIElementFontInfo createFontInfoElement(String elementName, String info, int x, int y, int color, @Optional int width, @Optional int height) {
        return new JEIElements.JEIElementFontInfo(elementName, x, y, width, height, color, info);
    }

    @ZenMethod
    public static JEIElements.JEIElementArrow createArrowElement(String elementName, int x, int y, int direction) {
        return new JEIElements.JEIElementArrow(elementName, x, y, direction);
    }

    @ZenMethod
    public static JEIElements.JEIElementImage createImageElement(String elementName, int x, int y, int width, int height, int u, int v,
                                                                 String texture, int textureWidth, int textureHeight) {
        return new JEIElements.JEIElementImage(elementName, u, v, x, y, width, height, texture, textureWidth, textureHeight);
    }

}
