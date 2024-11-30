package dev.ikx.rt.api.mods.contenttweaker.potion;

import dev.ikx.rt.impl.internal.compact.DeprecatedCompact;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenGetter;
import stanhebben.zenscript.annotations.ZenMethod;
import stanhebben.zenscript.annotations.ZenSetter;
import youyihj.zenutils.api.zenscript.SidedZenRegister;


@Deprecated
@SidedZenRegister(modDeps = "contenttweaker")
@ZenClass(IPotionTypeRepresentation.ZEN_CLASS)
public class IPotionTypeRepresentation {

    public static final String ZEN_CLASS = "mods.randomtweaker.cote.IPotionType";
    public static DeprecatedCompact compact = new DeprecatedCompact(ZEN_CLASS, CTPotionRepresentation.ZEN_CLASS);

    public CTPotionTypeRepresentation ctPotionType;

    public IPotionTypeRepresentation(String unlocalizedName, IPotionRepresentation potion) {
        this.ctPotionType = new CTPotionTypeRepresentation(unlocalizedName, potion.ctPotion);
    }

    @ZenGetter("duration")
    public int getDuration() {
        return compact.call(() -> ctPotionType.duration);
    }

    @ZenSetter("duration")
    public void setDuration(int duration) {
        compact.callVoid(() -> ctPotionType.duration = duration);
    }

    @ZenGetter("amplifier")
    public int getAmplifier() {
        return compact.call(() -> ctPotionType.amplifier);
    }

    @ZenSetter("amplifier")
    public void setAmplifier(int amplifier) {
        compact.callVoid(() -> ctPotionType.amplifier = amplifier);
    }

    @ZenMethod
    public void register() {
        compact.callVoid(() -> ctPotionType.register(), "For compatibility reasons, please use the `createMcPotionType` method in version 1.5.x");
    }

}
