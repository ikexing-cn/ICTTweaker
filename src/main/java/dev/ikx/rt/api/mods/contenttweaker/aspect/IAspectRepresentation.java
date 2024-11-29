package dev.ikx.rt.api.mods.contenttweaker.aspect;

import crafttweaker.CraftTweakerAPI;
import youyihj.zenutils.api.zenscript.SidedZenRegister;

import java.util.Arrays;
import java.util.Objects;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;
import stanhebben.zenscript.annotations.ZenProperty;
import thaumcraft.api.aspects.Aspect;


@SidedZenRegister(modDeps = {"thaumcraft", "contenttweaker"})
@ZenClass("mods.randomtweaker.cote.Aspect")
public abstract class IAspectRepresentation {

    @ZenProperty
    public int color;
    @ZenProperty
    public String tag;
    @ZenProperty
    public int blend = 1;
    @ZenProperty
    public String[] components;
    @ZenProperty
    public String image;
    @ZenProperty
    public String chatcolor;

    public Aspect[] asAspects() {
        if (Objects.nonNull(components) && components.length != 0) {
            if (components.length != 2) {
                CraftTweakerAPI.logError("components' length must be two", new IllegalArgumentException());
                return null;
            }
            return Arrays.stream(components).map(Aspect::getAspect).toArray(Aspect[]::new);
        }
        return null;
    }

    @ZenMethod
    public String getTag() {
        return tag;
    }

    @ZenMethod
    public void setTag(String tag) {
        this.tag = tag;
    }

    @ZenMethod
    public int getColor() {
        return color;
    }

    @ZenMethod
    public void setColor(int color) {
        this.color = color;
    }

    @ZenMethod
    public String getImage() {
        return image;
    }

    @ZenMethod
    public void setImage(String image) {
        this.image = image;
    }

    @ZenMethod
    public int getBlend() {
        return blend;
    }

    @ZenMethod
    public void setBlend(int blend) {
        this.blend = blend;
    }

    @ZenMethod
    public String getChatColor() {
        return chatcolor;
    }

    @ZenMethod
    public void setChatColor(String chatcolor) {
        this.chatcolor = chatcolor;
    }

    @ZenMethod
    public abstract void register();

}
