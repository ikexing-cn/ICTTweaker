package dev.ikx.rt.impl.internal.compact;

import crafttweaker.CraftTweakerAPI;

import java.util.function.Supplier;

public class DeprecatedCompact {

    private final String clazz;

    private final String replacementClass;

    private boolean triggered = false;

    public DeprecatedCompact(String clazz, String replacementClass) {
        this.clazz = clazz;
        this.replacementClass = replacementClass;
    }

    public <T> T call(Supplier<T> supplier) {
        return this.call(supplier, "`" + clazz + "` is deprecated, it will be removed in 1.6, please use `" + replacementClass + "` instead.");
    }

    public <T> T call(Supplier<T> supplier, String message) {
        triggered = true;
        T result = supplier.get();
        if (!triggered) CraftTweakerAPI.logWarning("[RandomTweaker] " + message);
        return result;
    }

    public void callVoid(Runnable runnable) {
        this.callVoid(runnable, "`" + clazz + "` is deprecated, it will be removed in 1.6, please use `" + replacementClass + "` instead.");
    }

    public void callVoid(Runnable runnable, String message) {
        triggered = true;
        runnable.run();
        if (!triggered) CraftTweakerAPI.logWarning("[RandomTweaker] " + message);
    }
}
