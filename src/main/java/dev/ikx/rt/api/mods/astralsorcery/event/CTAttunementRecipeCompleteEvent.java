package dev.ikx.rt.api.mods.astralsorcery.event;

import crafttweaker.api.entity.IEntity;
import crafttweaker.api.entity.IEntityItem;
import crafttweaker.api.event.IEntityEvent;
import crafttweaker.api.event.IEventCancelable;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import crafttweaker.api.world.IWorld;
import dev.ikx.rt.impl.mods.astralsorcery.event.AttunementRecipeCompleteEvent;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenGetter;
import stanhebben.zenscript.annotations.ZenMethod;
import stanhebben.zenscript.annotations.ZenSetter;
import youyihj.zenutils.api.zenscript.SidedZenRegister;

import java.util.List;


@SidedZenRegister(modDeps = "astralsorcery")
@ZenClass("mods.randomtweaker.astralsorcery.AttunementRecipeCompleteEvent")
public class CTAttunementRecipeCompleteEvent implements IEventCancelable, IEntityEvent {

    private final AttunementRecipeCompleteEvent event;

    public CTAttunementRecipeCompleteEvent(AttunementRecipeCompleteEvent event) {
        this.event = event;
    }

    @ZenGetter("input")
    @ZenMethod
    public IItemStack getInput() {
        return CraftTweakerMC.getIItemStack(this.event.getInput());
    }

    @ZenGetter("output")
    @ZenMethod
    public IItemStack getOutput() {
        return CraftTweakerMC.getIItemStack(this.event.getOutput());
    }

    @ZenSetter("output")
    @ZenMethod
    public void setOutput(IItemStack output) {
        this.event.setOutput(CraftTweakerMC.getItemStack(output));
    }

    @ZenGetter("world")
    @ZenMethod
    public IWorld getWorld() {
        return CraftTweakerMC.getIWorld(this.event.getWorld());
    }

    @ZenGetter("constellation")
    @ZenMethod
    public String getConstellation() {
        return this.event.getConstellation().getUnlocalizedName();
    }

    //the entity item being attuned
    //this is final for attunement complete event
    @Override
    public IEntity getEntity() {
        return this.getItemEntity();
    }

    @ZenGetter("itemEntity")
    @ZenMethod
    public IEntityItem getItemEntity() {
        return CraftTweakerMC.getIEntityItem(this.event.getInputEntity());
    }

    @ZenMethod
    public void addAdditionalOutput(IItemStack additionalOutput) {
        this.event.addAdditionalOutput(CraftTweakerMC.getItemStack(additionalOutput));
    }

    @ZenMethod
    public List<ItemStack> getAdditionalOutput() {
        return this.event.getAdditionalOutput();
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
