package dev.ikx.rt.api.mods.botania.event;

import youyihj.zenutils.api.zenscript.SidedZenRegister;
import crafttweaker.api.entity.IEntityItem;
import crafttweaker.api.event.IEventCancelable;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import crafttweaker.api.world.IBlockPos;
import crafttweaker.api.world.IWorld;
import dev.ikx.rt.impl.mods.botania.event.PoolTradeEvent;

import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenGetter;
import stanhebben.zenscript.annotations.ZenMethod;
import stanhebben.zenscript.annotations.ZenSetter;


@SidedZenRegister(modDeps = "botania")
@ZenClass("mods.randomtweaker.botania.PoolTradeEvent")
public class CTPoolTradeEvent implements IEventCancelable {

    private final PoolTradeEvent event;

    protected CTPoolTradeEvent(PoolTradeEvent event) {
        this.event = event;
    }

    @ZenGetter("mana")
    public int getMana() {
        return event.getMana();
    }

    @ZenMethod
    @ZenSetter("mana")
    public void setMana(int mana) {
        event.setMana(mana);
    }

    @ZenGetter("output")
    public IItemStack getOutput() {
        return CraftTweakerMC.getIItemStack(event.getOutput());
    }

    @ZenMethod
    @ZenSetter("output")
    public void setOutput(IItemStack output) {
        event.setOutput(CraftTweakerMC.getItemStack(output));
    }

    @ZenGetter("allowExceed")
    public boolean isAllowExceed() {
        return event.isAllowExceed();
    }

    @ZenMethod
    @ZenSetter("allowExceed")
    public void setAllowExceed(boolean allowExceed) {
        event.setAllowExceed(allowExceed);
    }

    @ZenGetter("currentMana")
    public int getCurrentMana() {
        return event.getCurrentMana();
    }

    @ZenGetter("input")
    public IEntityItem getInput() {
        return CraftTweakerMC.getIEntityItem(event.getInput());
    }

    @ZenGetter("world")
    public IWorld getWorld() {
        return CraftTweakerMC.getIWorld(event.getWorld());
    }

    @ZenGetter("blockPos")
    public IBlockPos getBlockPos() {
        return CraftTweakerMC.getIBlockPos(event.getBlockPos());
    }

    @ZenGetter("alchemy")
    public boolean isAlchemy() {
        return event.isAlchemy();
    }

    @ZenGetter("conjuration")
    public boolean isConjuration() {
        return event.isConjuration();
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
