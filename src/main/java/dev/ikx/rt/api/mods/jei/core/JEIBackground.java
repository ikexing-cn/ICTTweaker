package dev.ikx.rt.api.mods.jei.core;

import youyihj.zenutils.api.zenscript.SidedZenRegister;

import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenProperty;


@SidedZenRegister(modDeps = "jei")
@ZenClass("mods.randomtweaker.jei.JEIBackground")
public class JEIBackground {

    @ZenProperty
    public int u;

    @ZenProperty
    public int v;

    @ZenProperty
    public int width;

    @ZenProperty
    public int height;

    @ZenProperty
    public String resourceName;

    public JEIBackground(int u, int v, int width, int height, String resourceName) {
        this.u = u;
        this.v = v;
        this.width = width;
        this.height = height;
        this.resourceName = resourceName;
    }

    public JEIBackground(int width, int height) {
        this.width = width;
        this.height = height;
    }

}
