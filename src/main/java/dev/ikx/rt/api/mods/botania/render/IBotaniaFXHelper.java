package dev.ikx.rt.api.mods.botania.render;

import dev.ikx.rt.impl.internal.compact.mods.DeprecatedCompact;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;
import youyihj.zenutils.api.zenscript.SidedZenRegister;

@Deprecated
@SidedZenRegister(modDeps = "botania")
@ZenClass(IBotaniaFXHelper.ZEN_CLASS)
public class IBotaniaFXHelper {

    public static final String ZEN_CLASS = "mods.randomtweaker.botania.IBotaniaFXHelper";
    public static DeprecatedCompact compact = new DeprecatedCompact(ZEN_CLASS, BotaniaFXHelper.ZEN_CLASS);

    @ZenMethod
    public static void setWispFXDistanceLimit(boolean limit) {
        compact.callVoid(() -> BotaniaFXHelper.setWispFXDistanceLimit(limit));
    }

    @ZenMethod
    public static void setWispFXDepthTest(boolean depth) {
        compact.callVoid(() -> BotaniaFXHelper.setWispFXDepthTest(depth));
    }

    @ZenMethod
    public static void setSparkleFXNoClip(boolean noclip) {
        compact.callVoid(() -> BotaniaFXHelper.setSparkleFXNoClip(noclip));
    }

    @ZenMethod
    public static void setSparkleFXCorrupt(boolean corrupt) {
        compact.callVoid(() -> BotaniaFXHelper.setSparkleFXCorrupt(corrupt));
    }

    @ZenMethod
    public static void sparkleFX(double x, double y, double z, float r, float g, float b, float size, int m) {
        compact.callVoid(() -> BotaniaFXHelper.sparkleFX(x, y, z, r, g, b, size, m));
    }

    @ZenMethod
    public static void sparkleFX(double x, double y, double z, float r, float g, float b, float size, int m, boolean fake) {
        compact.callVoid(() -> BotaniaFXHelper.sparkleFX(x, y, z, r, g, b, size, m, fake));
    }

    @ZenMethod
    public static void wispFX(double x, double y, double z, float r, float g, float b, float size) {
        compact.callVoid(() -> BotaniaFXHelper.wispFX(x, y, z, r, g, b, size));
    }

    @ZenMethod
    public static void wispFX(double x, double y, double z, float r, float g, float b, float size, float gravity) {
        compact.callVoid(() -> BotaniaFXHelper.wispFX(x, y, z, r, g, b, size, gravity));
    }

    @ZenMethod
    public static void wispFX(double x, double y, double z, float r, float g, float b, float size, float gravity, float maxAgeMul) {
        compact.callVoid(() -> BotaniaFXHelper.wispFX(x, y, z, r, g, b, size, gravity, maxAgeMul));
    }

    @ZenMethod
    public static void wispFX(double x, double y, double z, float r, float g, float b, float size, float motionX, float motionY, float motionZ) {
        compact.callVoid(() -> BotaniaFXHelper.wispFX(x, y, z, r, g, b, size, motionX, motionY, motionZ));
    }

    @ZenMethod
    public static void wispFX(double x, double y, double z, float r, float g, float b, float size, float motionX, float motionY, float motionZ, float maxAgeMul) {
        compact.callVoid(() -> BotaniaFXHelper.wispFX(x, y, z, r, g, b, size, motionX, motionY, motionZ, maxAgeMul));
    }

}
