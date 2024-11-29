package dev.ikx.rt.api.mods.jei.slots;

import youyihj.zenutils.api.zenscript.SidedZenRegister;

import stanhebben.zenscript.annotations.ZenClass;


@SidedZenRegister(modDeps = "jei")
@ZenClass("mods.randomtweaker.jei.IJeiSlotItem")
public abstract class IJeiSlotItem extends IJeiSlot {

    protected IJeiSlotItem(int x, int y, boolean isInput, boolean hasBase) {
        super(x, y, isInput, hasBase);
    }

    protected IJeiSlotItem(String slotName, int x, int y, boolean isInput, boolean hasBase) {
        super(slotName, x, y, isInput, hasBase);
    }

}
