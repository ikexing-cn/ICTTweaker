package dev.ikx.rt.api.mods.botania.event;

import youyihj.zenutils.api.zenscript.SidedZenRegister;
import crafttweaker.api.event.IEventCancelable;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import dev.ikx.rt.api.mods.botania.ITileAlfPortal;
import dev.ikx.rt.impl.mods.botania.event.ElvenTradeEvent;

import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenGetter;
import stanhebben.zenscript.annotations.ZenMethod;
import stanhebben.zenscript.annotations.ZenSetter;

import java.util.Arrays;
import java.util.stream.Collectors;


@SidedZenRegister(modDeps = "botania")
@ZenClass("mods.randomtweaker.botania.ElvenTradeEvent")
public abstract class CTElvenTradeEvent implements IEventCancelable {

    private final ElvenTradeEvent event;

    protected CTElvenTradeEvent(ElvenTradeEvent event) {
        this.event = event;
    }

    @ZenGetter("alfPortal")
    public ITileAlfPortal getAlfPortal() {
        return event.getAlfPortal();
    }

    @ZenGetter("input")
    public IItemStack[] getInput() {
        return CraftTweakerMC.getIItemStacks(event.getInput());
    }

    @ZenGetter("output")
    public IItemStack[] getOutput() {
        return CraftTweakerMC.getIItemStacks(event.getOutput());
    }

    @ZenSetter("output")
    @ZenMethod
    public void setOutput(IItemStack[] output) {
        event.setOutput(Arrays.stream(CraftTweakerMC.getItemStacks(output)).collect(Collectors.toList()));
    }

    @ZenMethod
    public void addOutput(IItemStack stack) {
        event.addOutput(CraftTweakerMC.getItemStack(stack));
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
