package dev.ikx.rt.impl.mods.astralsorcery.event;

import hellfirepvp.astralsorcery.common.constellation.IConstellation;
import dev.ikx.rt.impl.internal.event.BaseEvent;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.Cancelable;

@Cancelable
public class AttunementStartEvent extends BaseEvent {

    private final Entity inputEntity;
    private final World world;
    private final IConstellation constellation;

    public AttunementStartEvent(Entity entity, World world, IConstellation constellation) {
        this.inputEntity = entity;
        this.world = world;
        this.constellation = constellation;
    }

    public World getWorld() {
        return world;
    }

    public IConstellation getConstellation() {
        return constellation;
    }

    public String getConstellationString() {
        return constellation.getUnlocalizedName();
    }

    public Entity getInputEntity() {
        return inputEntity;
    }

}
