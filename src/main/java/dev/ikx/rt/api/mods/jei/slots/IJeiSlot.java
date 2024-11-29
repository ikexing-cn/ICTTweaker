package dev.ikx.rt.api.mods.jei.slots;

import youyihj.zenutils.api.zenscript.SidedZenRegister;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenProperty;


@SidedZenRegister(modDeps = "jei")
@ZenClass("mods.randomtweaker.jei.IJeiSlot")
public abstract class IJeiSlot {

    @ZenProperty
    public int x;

    @ZenProperty
    public int y;

    @ZenProperty
    public boolean isInput;

    @ZenProperty
    public boolean hasBase;

    @ZenProperty
    public String slotName;

    protected IJeiSlot(int x, int y, boolean isInput, boolean hasBase) {
        this.x = x;
        this.y = y;
        this.isInput = isInput;
        this.hasBase = hasBase;
    }

    protected IJeiSlot(String slotName, int x, int y, boolean isInput, boolean hasBase) {
        this.slotName = slotName;

        this.x = x;
        this.y = y;
        this.isInput = isInput;
        this.hasBase = hasBase;
    }

    @SideOnly(Side.CLIENT)
    public abstract void render(Minecraft minecraft);
}
