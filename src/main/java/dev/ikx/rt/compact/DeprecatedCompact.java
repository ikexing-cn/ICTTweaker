package dev.ikx.rt.compact;

import crafttweaker.CraftTweakerAPI;

import java.util.function.Supplier;

public class DeprecatedCompact {

    private final String clazz;

    private final String replacementClass;

    public DeprecatedCompact(String clazz, String replacementClass) {
        this.clazz = clazz;
        this.replacementClass = replacementClass;
    }

    public <T> T call(Supplier<T> supplier) {
        return this.call(supplier, "This `" + clazz + "` is deprecated, it will be removed in 1.6, please use `" + replacementClass + "` instead.");
    }

    public <T> T call(Supplier<T> supplier, String message) {
        T result = supplier.get();
        CraftTweakerAPI.logWarning("[RandomTweaker] " + message);
        return result;
    }

    public void callVoid(Runnable runnable) {
        this.callVoid(runnable, "This `" + clazz + "` is deprecated, it will be removed in 1.6, please use `" + replacementClass + "` instead.");
    }

    public void callVoid(Runnable runnable, String message) {
        runnable.run();
        CraftTweakerAPI.logWarning("[RandomTweaker] " + message);
    }
}
