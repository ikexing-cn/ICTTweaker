package dev.ikx.rt.api.mods.contenttweaker.potion;

import dev.ikx.rt.api.mods.contenttweaker.function.IPotionIsReady;
import dev.ikx.rt.api.mods.contenttweaker.function.IPotionPerformEffect;
import dev.ikx.rt.impl.internal.compact.mods.DeprecatedCompact;
import net.minecraft.potion.Potion;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenGetter;
import stanhebben.zenscript.annotations.ZenMethod;
import stanhebben.zenscript.annotations.ZenSetter;
import youyihj.zenutils.api.zenscript.SidedZenRegister;

@Deprecated
@SidedZenRegister(modDeps = "contenttweaker")
@ZenClass(IPotionRepresentation.ZEN_CLASS)
public class IPotionRepresentation {

    public static final String ZEN_CLASS = "mods.randomtweaker.cote.IPotion";
    public static final DeprecatedCompact compact = new DeprecatedCompact(ZEN_CLASS, CTPotionRepresentation.ZEN_CLASS);

    public final CTPotionRepresentation ctPotion;

    public IPotionRepresentation(int liquidColor, String unlocalizedName) {
        this.ctPotion = new CTPotionRepresentation(liquidColor, unlocalizedName);
    }

    @ZenGetter("instant")
    public boolean isInstant() {
        return compact.call(() -> this.ctPotion.instant);
    }

    @ZenSetter("instant")
    public void setInstant(boolean instant) {
        compact.callVoid(() -> this.ctPotion.instant = instant);
    }

    @ZenGetter("badEffectIn")
    public boolean isBadEffectIn() {
        return compact.call(() -> this.ctPotion.badEffectIn);
    }

    @ZenSetter("badEffectIn")
    public void setBadEffectIn(boolean badEffectIn) {
        compact.callVoid(() -> this.ctPotion.badEffectIn = badEffectIn);
    }

    @ZenGetter("beneficial")
    public boolean isBeneficial() {
        return compact.call(() -> this.ctPotion.beneficial);
    }

    @ZenSetter("beneficial")
    public void setBeneficial(boolean beneficial) {
        compact.callVoid(() -> this.ctPotion.beneficial = beneficial);
    }

    @ZenGetter("shouldRender")
    public boolean isShouldRender() {
        return compact.call(() -> this.ctPotion.shouldRender);
    }

    @ZenSetter("shouldRender")
    public void setShouldRender(boolean shouldRender) {
        compact.callVoid(() -> this.ctPotion.shouldRender = shouldRender);
    }

    @ZenGetter("shouldRenderHUD")
    public boolean isShouldRenderHUD() {
        return compact.call(() -> this.ctPotion.shouldRenderHUD);
    }

    @ZenSetter("shouldRenderHUD")
    public void setShouldRenderHUD(boolean shouldRenderHUD) {
        compact.callVoid(() -> this.ctPotion.shouldRenderHUD = shouldRenderHUD);
    }

    @ZenGetter("isReady")
    public IPotionIsReady getIsReady() {
        return compact.call(() -> this.ctPotion.isReady);
    }

    @ZenSetter("isReady")
    public void setIsReady(IPotionIsReady isReady) {
        compact.callVoid(() -> this.ctPotion.isReady = isReady);
    }

    @ZenGetter("performEffect")
    public IPotionPerformEffect getPerformEffect() {
        return compact.call(() -> this.ctPotion.performEffect);
    }

    @ZenSetter("performEffect")
    public void setPerformEffect(IPotionPerformEffect performEffect) {
        compact.callVoid(() -> this.ctPotion.performEffect = performEffect);
    }

    @ZenGetter("affectEntity")
    public IPotionPerformEffect getAffectEntity() {
        return compact.call(() -> this.ctPotion.affectEntity);
    }

    @ZenSetter("affectEntity")
    public void setAffectEntity(IPotionPerformEffect affectEntity) {
        compact.callVoid(() -> this.ctPotion.affectEntity = affectEntity);
    }

    @ZenMethod
    public void register() {
        compact.callVoid(this.ctPotion::register, "For compatibility reasons, please use the `createMcPotion` method in version 1.5.x");
    }

    public Potion getInternal() {
        return compact.call(this.ctPotion::getInternal);
    }

}
