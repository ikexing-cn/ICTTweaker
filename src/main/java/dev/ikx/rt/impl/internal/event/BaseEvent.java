package dev.ikx.rt.impl.internal.event;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.Event;

public class BaseEvent extends Event {

    public boolean post() {
        return MinecraftForge.EVENT_BUS.post(this);
    }

}
