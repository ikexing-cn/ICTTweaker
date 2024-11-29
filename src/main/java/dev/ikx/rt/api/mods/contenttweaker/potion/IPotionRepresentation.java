package dev.ikx.rt.api.mods.contenttweaker.potion;

import youyihj.zenutils.api.zenscript.SidedZenRegister;
import dev.ikx.rt.api.mods.contenttweaker.function.IPotionIsReady;
import dev.ikx.rt.api.mods.contenttweaker.function.IPotionPerformEffect;

import net.minecraft.potion.Potion;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;
import stanhebben.zenscript.annotations.ZenProperty;


@SidedZenRegister(modDeps = "contenttweaker")
@ZenClass("mods.randomtweaker.cote.IPotion")
public abstract class IPotionRepresentation {

    public final int liquidColor;
    public final String unlocalizedName;

    @ZenProperty
    public boolean instant;
    @ZenProperty
    public boolean badEffectIn;
    @ZenProperty
    public boolean beneficial = true;
    @ZenProperty
    public boolean shouldRender = true;
    @ZenProperty
    public boolean shouldRenderHUD = true;
    @ZenProperty
    public IPotionIsReady isReady = null;
    @ZenProperty
    public IPotionPerformEffect performEffect;
    @ZenProperty
    public IPotionPerformEffect affectEntity;

    protected IPotionRepresentation(int liquidColor, String unlocalizedName) {
        this.liquidColor = liquidColor;
        this.unlocalizedName = unlocalizedName;
    }

    @ZenMethod
    public abstract void register();

    public abstract Potion getInternal();

}
