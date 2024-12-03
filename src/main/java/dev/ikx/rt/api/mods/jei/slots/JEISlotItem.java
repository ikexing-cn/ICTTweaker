package dev.ikx.rt.api.mods.jei.slots;

import dev.ikx.rt.api.mods.jei.JEIUtils;
import net.minecraft.client.Minecraft;
import youyihj.zenutils.api.zenscript.SidedZenRegister;

import stanhebben.zenscript.annotations.ZenClass;


@SidedZenRegister(modDeps = "jei")
@ZenClass("mods.randomtweaker.jei.JEISlotItem")
public class JEISlotItem extends JEISlot {

    public JEISlotItem(int x, int y, boolean isInput, boolean hasBase) {
        super(x, y, isInput, hasBase);
    }

    public JEISlotItem(String slotName, int x, int y, boolean isInput, boolean hasBase) {
        super(slotName, x, y, isInput, hasBase);
    }

    @Override
    public void render(Minecraft minecraft) {
        if (!hasBase) return;
        if (isInput) {
            JEIUtils.createItemInputElement(x, y).render(minecraft);
        } else {
            JEIUtils.createItemOutputElement(x, y).render(minecraft);
        }
    }

}
