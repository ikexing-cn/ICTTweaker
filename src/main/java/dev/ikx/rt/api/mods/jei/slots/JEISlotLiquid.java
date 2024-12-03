package dev.ikx.rt.api.mods.jei.slots;

import dev.ikx.rt.api.mods.jei.JEIUtils;
import net.minecraft.client.Minecraft;
import youyihj.zenutils.api.zenscript.SidedZenRegister;

import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenProperty;


@SidedZenRegister(modDeps = "jei")
@ZenClass("mods.randomtweaker.jei.JEISlotLiquid")
public class JEISlotLiquid extends JEISlot {

    @ZenProperty
    public int width;

    @ZenProperty
    public int height;

    @ZenProperty
    public int capacityMb;

    @ZenProperty
    public boolean showCapacity;

    public JEISlotLiquid(int x, int y, boolean isInput, boolean hasBase, int width,
                         int height, int capacityMb, boolean showCapacity) {
        super(x, y, isInput, hasBase);
        this.width = width;
        this.height = height;
        this.capacityMb = capacityMb;
        this.showCapacity = showCapacity;
    }

    public JEISlotLiquid(String slotName, int x, int y, boolean isInput, boolean hasBase, int width,
                         int height, int capacityMb, boolean showCapacity) {
        super(slotName, x, y, isInput, hasBase);

        this.width = width;
        this.height = height;
        this.capacityMb = capacityMb;
        this.showCapacity = showCapacity;
    }

    @Override
    public void render(Minecraft minecraft) {
        if (!hasBase) return;
        JEIUtils.createLiquidElement(x, y, width, height).render(minecraft);
    }

}
