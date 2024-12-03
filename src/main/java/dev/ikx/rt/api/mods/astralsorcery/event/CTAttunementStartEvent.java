package dev.ikx.rt.api.mods.astralsorcery.event;

import youyihj.zenutils.api.zenscript.SidedZenRegister;
import crafttweaker.api.entity.IEntity;
import crafttweaker.api.event.IEntityEvent;
import crafttweaker.api.minecraft.CraftTweakerMC;
import crafttweaker.api.world.IWorld;
import dev.ikx.rt.impl.mods.astralsorcery.event.AttunementStartEvent;

import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenGetter;
import stanhebben.zenscript.annotations.ZenMethod;


@SidedZenRegister(modDeps = "astralsorcery")
@ZenClass("mods.randomtweaker.astralsorcery.AttunementStartEvent")
public class CTAttunementStartEvent implements IEntityEvent {

    private final AttunementStartEvent event;

    public CTAttunementStartEvent(AttunementStartEvent event) {
        this.event = event;
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

    //the entity being attuned
    @Override
    public IEntity getEntity() {
        return CraftTweakerMC.getIEntity(this.event.getInputEntity());
    }

}
