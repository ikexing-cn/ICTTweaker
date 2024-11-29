package dev.ikx.rt.api.mods.botania.event;

import crafttweaker.api.entity.IEntityItem;
import crafttweaker.api.event.IEventCancelable;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import dev.ikx.rt.api.mods.botania.ITileAlfPortal;
import dev.ikx.rt.impl.internal.utils.InternalUtils;
import dev.ikx.rt.impl.mods.botania.event.AlfPortalDroppedEvent;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenGetter;
import stanhebben.zenscript.annotations.ZenMethod;
import stanhebben.zenscript.annotations.ZenSetter;
import youyihj.zenutils.api.zenscript.SidedZenRegister;

import java.util.Arrays;
import java.util.stream.Collectors;


@SidedZenRegister(modDeps = "botania")
@ZenClass("mods.randomtweaker.botania.AlfPortalDroppedEvent")
public abstract class CTAlfPortalDroppedEvent implements IEventCancelable {

    private final AlfPortalDroppedEvent event;

    protected CTAlfPortalDroppedEvent(AlfPortalDroppedEvent event) {
        this.event = event;
    }

    @ZenGetter("alfPortal")
    public ITileAlfPortal getAlfPortal() {
        return event.getAlfPortal();
    }

    @ZenGetter("input")
    public IEntityItem getInput() {
        return CraftTweakerMC.getIEntityItem(event.getInput());
    }

    @ZenGetter("output")
    public IItemStack[] getOutput() {
        return CraftTweakerMC.getIItemStacks(InternalUtils.getItemStackListCopy(event.getOutput()));
    }

    @ZenMethod
    public void addOutput(IItemStack output) {
        event.addOutput(CraftTweakerMC.getItemStack(output));
    }

    @ZenMethod
    public void setOutput(IItemStack[] output) {
        event.setOutput(Arrays.stream(CraftTweakerMC.getItemStacks(output)).collect(Collectors.toList()));
    }

    @ZenGetter("isDead")
    public boolean isDead() {
        return event.isDead();
    }

    @ZenMethod
    @ZenSetter("isDead")
    public void setDead(boolean dead) {
        event.setDead(dead);
    }

    @Override
    public boolean isCanceled() {
        return event.isCanceled();
    }

    @Override
    public void setCanceled(boolean canceled) {
        event.setCanceled(canceled);
    }

}
