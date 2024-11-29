package dev.ikx.rt.api.internal.file;

import crafttweaker.api.item.IIngredient;
import dev.ikx.rt.compact.DeprecatedCompact;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import java.util.List;

@Deprecated
@ZenClass(IProp.ZEN_CLASS)
public class IProp {

    public static final String ZEN_CLASS = "mods.randomtweaker.file.Prop";

    public static DeprecatedCompact compact = new DeprecatedCompact(ZEN_CLASS, Properties.ZEN_CLASS);

    @ZenMethod
    public static void write(String key, String value) {
        compact.callVoid(() -> Properties.write(key, value));
    }

    @ZenMethod
    public static void write(String key, int value) {
        compact.callVoid(() -> Properties.write(key, value));
    }

    @ZenMethod
    public static void write(String key, float value) {
        compact.callVoid(() -> Properties.write(key, value));
    }

    @ZenMethod
    public static void write(String key, double value) {
        compact.callVoid(() -> Properties.write(key, value));
    }

    @ZenMethod
    public static void write(String key, long value) {
        compact.callVoid(() -> Properties.write(key, value));
    }

    @ZenMethod
    public static void write(String key, boolean value) {
        compact.callVoid(() -> Properties.write(key, value));
    }

    @ZenMethod
    public static void write(String key, IIngredient value) {
        compact.callVoid(() -> Properties.write(key, value));
    }

    @ZenMethod
    public static String read(String key) {
        return compact.call(() -> Properties.read(key));
    }

    @ZenMethod
    public static List<String> getAllKeys() {
        return compact.call(Properties::getAllKeys);
    }

}
