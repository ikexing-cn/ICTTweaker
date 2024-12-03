package dev.ikx.rt.api.mods.jei.elements;

import youyihj.zenutils.api.zenscript.SidedZenRegister;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenProperty;


@SidedZenRegister(modDeps = "jei")
@ZenClass("mods.randomtweaker.jei.JEIElement")
public abstract class JEIElement {

    @ZenProperty
    public int u;

    @ZenProperty
    public int v;

    @ZenProperty
    public int x;

    @ZenProperty
    public int y;

    @ZenProperty
    public int width;

    @ZenProperty
    public int height;

    @ZenProperty
    public String elementName;

    protected JEIElement(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    protected JEIElement(int u, int v, int x, int y, int width, int height) {
        this.u = u;
        this.v = v;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    protected JEIElement(String elementName, int x, int y, int width, int height) {
        this.elementName = elementName;

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    protected JEIElement(String elementName, int u, int v, int x, int y, int width, int height) {
        this.elementName = elementName;

        this.u = u;
        this.v = v;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @SideOnly(Side.CLIENT)
    public abstract void render(Minecraft minecraft);

}
