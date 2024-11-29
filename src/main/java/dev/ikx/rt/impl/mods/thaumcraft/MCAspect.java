package dev.ikx.rt.impl.mods.thaumcraft;

import dev.ikx.rt.api.mods.thaumcraft.IAspect;
import thaumcraft.api.aspects.Aspect;

import java.util.Arrays;
import java.util.Objects;

public class MCAspect implements IAspect {

    private final Aspect internal;

    public MCAspect(Aspect aspect) {
        this.internal = aspect;
    }

    @Override
    public String getName() {
        return this.internal.getName();
    }

    @Override
    public IAspect[] getComponents() {
        return Objects.nonNull(internal.getComponents()) ? Arrays.stream(this.internal.getComponents()).map(IAspect::of).toArray(IAspect[]::new) : null;
    }

    @Override
    public String getChatcolor() {
        return internal.getChatcolor();
    }

    @Override
    public Aspect getInternal() {
        return this.internal;
    }

    @Override
    public boolean equals(IAspect other) {
        return Objects.equals(other.getName(), this.getName());
    }

}
