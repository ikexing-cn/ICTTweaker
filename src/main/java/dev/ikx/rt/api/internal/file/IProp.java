package dev.ikx.rt.api.internal.file;

import crafttweaker.api.item.IIngredient;
import dev.ikx.rt.impl.internal.compact.DeprecatedCompact;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import java.util.List;

@Deprecated
@ZenClass(IProp.ZEN_CLASS)
public class IProp {

    public static final String ZEN_CLASS = "mods.randomtweaker.file.Prop";

    public static DeprecatedCompact compact = new DeprecatedCompact(ZEN_CLASS, Props.ZEN_CLASS);

    @ZenMethod
    public static void write(String key, String value) {
        compact.callVoid(() -> Props.write(key, value));
    }

    @ZenMethod
    public static void write(String key, int value) {
        compact.callVoid(() -> Props.write(key, value));
    }

    @ZenMethod
    public static void write(String key, float value) {
        compact.callVoid(() -> Props.write(key, value));
    }

    @ZenMethod
    public static void write(String key, double value) {
        compact.callVoid(() -> Props.write(key, value));
    }

    @ZenMethod
    public static void write(String key, long value) {
        compact.callVoid(() -> Props.write(key, value));
    }

    @ZenMethod
    public static void write(String key, boolean value) {
        compact.callVoid(() -> Props.write(key, value));
    }

    @ZenMethod
    public static void write(String key, IIngredient value) {
        compact.callVoid(() -> Props.write(key, value));
    }

    @ZenMethod
    public static String read(String key) {
        return compact.call(() -> Props.read(key));
    }

    @ZenMethod
    public static List<String> getAllKeys() {
        return compact.call(Props::getAllKeys);
    }

}
