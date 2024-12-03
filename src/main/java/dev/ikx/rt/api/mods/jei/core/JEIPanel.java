package dev.ikx.rt.api.mods.jei.core;

import youyihj.zenutils.api.zenscript.SidedZenRegister;
import crafttweaker.api.item.IItemStack;
import dev.ikx.rt.api.mods.jei.elements.JEIElement;
import dev.ikx.rt.api.mods.jei.slots.JEISlot;

import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;


@SidedZenRegister(modDeps = "jei")
@ZenClass("mods.randomtweaker.jei.JEIPanel")
public interface JEIPanel {

    @ZenMethod
    JEIPanel setModid(String modid);

    @ZenMethod
    JEIPanel setIcon(IItemStack icon);

    @ZenMethod
    JEIPanel addSlot(JEISlot slot);

    @ZenMethod
    JEIPanel setSlots(JEISlot[] slots);

    @ZenMethod
    JEIPanel onTooltip(JEITooltip tooltip);

    @ZenMethod
    JEIPanel addElement(JEIElement elements);

    @ZenMethod
    JEIPanel setElements(JEIElement[] elements);

    @ZenMethod
    JEIPanel addRecipeCatalyst(IItemStack stack);

    @ZenMethod
    JEIPanel setRecipeCatalysts(IItemStack[] stacks);

    @ZenMethod
    JEIPanel setBackground(JEIBackground background);

    @ZenMethod
    JEISlot[] getJeiSlots();

    @ZenMethod
    JEISlot getJeiSlot(String slotName);

    @ZenMethod
    JEIElement[] getJeiElements();

    @ZenMethod
    JEIElement getJeiElement(String elementName);

    @ZenMethod
    void register();

    void registerDirect();
}
