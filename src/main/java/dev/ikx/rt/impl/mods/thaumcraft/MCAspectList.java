package dev.ikx.rt.impl.mods.thaumcraft;

import dev.ikx.rt.api.mods.thaumcraft.IAspect;
import dev.ikx.rt.api.mods.thaumcraft.IAspectList;
import thaumcraft.api.aspects.AspectList;

import java.util.Arrays;
import java.util.Iterator;

public class MCAspectList implements IAspectList {

    private final AspectList internal;

    public MCAspectList(AspectList internal) {
        this.internal = internal;
    }

    @Override
    public int getSize() {
        return internal.size();
    }

    @Override
    public int getVisSize() {
        return internal.visSize();
    }

    @Override
    public int getAmount(IAspect key) {
        return internal.getAmount(key.getInternal());
    }

    @Override
    public IAspectList copy() {
        return IAspectList.of(internal.copy());
    }

    @Override
    public IAspect[] getAspects() {
        return Arrays.stream(internal.getAspects()).map(IAspect::of).toArray(IAspect[]::new);
    }

    @Override
    public IAspect[] getAspectsSortedByName() {
        return Arrays.stream(internal.getAspectsSortedByName()).map(IAspect::of).toArray(IAspect[]::new);
    }

    @Override
    public IAspect[] getAspectsSortedByAmount() {
        return Arrays.stream(internal.getAspectsSortedByAmount()).map(IAspect::of).toArray(IAspect[]::new);
    }

    @Override
    public IAspectList add(IAspectList in) {
        internal.add(in.getInternal());
        return this;
    }

    @Override
    public boolean reduce(IAspect key, int amount) {
        return internal.reduce(key.getInternal(), amount);
    }

    @Override
    public IAspectList add(IAspect key, int amount) {
        internal.add(key.getInternal(), amount);
        return this;
    }

    @Override
    public IAspectList remove(IAspect key) {
        internal.remove(key.getInternal());
        return this;
    }

    @Override
    public IAspectList remove(IAspect key, int amount) {
        internal.remove(key.getInternal(), amount);
        return this;
    }

    @Override
    public IAspectList remove(IAspectList in) {
        internal.remove(in.getInternal());
        return this;
    }

    @Override
    public IAspectList merge(IAspectList in) {
        internal.merge(in.getInternal());
        return this;
    }

    @Override
    public IAspectList merge(IAspect key, int amount) {
        internal.merge(key.getInternal(), amount);
        return this;
    }

    @Override
    public AspectList getInternal() {
        return internal;
    }

    @Override
    public IAspect get(int index) {
        return IAspect.of(internal.getAspects()[index]);
    }

    @Override
    public boolean contains(IAspect aspect) {
        return aspect != null && internal.aspects.containsKey(aspect.getInternal());
    }

    @Override
    public Iterator<IAspect> iterator() {
        return internal.aspects.keySet().stream().map(IAspect::of).iterator();
    }

}
