package dev.ikx.rt.api.mods.botania.render;

import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;
import vazkii.botania.common.Botania;
import youyihj.zenutils.api.zenscript.SidedZenRegister;


@SidedZenRegister(modDeps = "botania")
@ZenClass(BotaniaFXHelper.ZEN_CLASS)
public class BotaniaFXHelper {

    public static final String ZEN_CLASS = "mods.randomtweaker.botania.BotaniaFXHelper";
    @ZenMethod
    public static void setWispFXDistanceLimit(boolean limit) {
        Botania.proxy.setWispFXDistanceLimit(limit);
    }

    @ZenMethod
    public static void setWispFXDepthTest(boolean depth) {
        Botania.proxy.setWispFXDepthTest(depth);
    }

    @ZenMethod
    public static void setSparkleFXNoClip(boolean noclip) {
        Botania.proxy.setSparkleFXNoClip(noclip);
    }

    @ZenMethod
    public static void setSparkleFXCorrupt(boolean corrupt) {
        Botania.proxy.setSparkleFXCorrupt(corrupt);
    }

    @ZenMethod
    public static void sparkleFX(double x, double y, double z, float r, float g, float b, float size, int m) {
        sparkleFX(x, y, z, r, g, b, size, m, false);
    }

    @ZenMethod
    public static void sparkleFX(double x, double y, double z, float r, float g, float b, float size, int m, boolean fake) {
        Botania.proxy.sparkleFX(x, y, z, r, g, b, size, m, fake);
    }

    @ZenMethod
    public static void wispFX(double x, double y, double z, float r, float g, float b, float size) {
        wispFX(x, y, z, r, g, b, size, 0F);
    }

    @ZenMethod
    public static void wispFX(double x, double y, double z, float r, float g, float b, float size, float gravity) {
        wispFX(x, y, z, r, g, b, size, gravity, 1F);
    }

    @ZenMethod
    public static void wispFX(double x, double y, double z, float r, float g, float b, float size, float gravity, float maxAgeMul) {
        wispFX(x, y, z, r, g, b, size, 0, -gravity, 0, maxAgeMul);
    }

    @ZenMethod
    public static void wispFX(double x, double y, double z, float r, float g, float b, float size, float motionX, float motionY, float motionZ) {
        wispFX(x, y, z, r, g, b, size, motionX, motionY, motionZ, 1F);
    }

    @ZenMethod
    public static void wispFX(double x, double y, double z, float r, float g, float b, float size, float motionX, float motionY, float motionZ, float maxAgeMul) {
        Botania.proxy.wispFX(x, y, z, r, g, b, size, motionX, motionY, motionZ, maxAgeMul);
    }

}
