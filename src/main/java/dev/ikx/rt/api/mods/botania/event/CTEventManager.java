package dev.ikx.rt.api.mods.botania.event;

import youyihj.zenutils.api.zenscript.SidedZenRegister;
import crafttweaker.api.event.IEventHandle;
import crafttweaker.api.event.IEventManager;
import crafttweaker.util.EventList;
import crafttweaker.util.IEventHandler;
import dev.ikx.rt.impl.mods.botania.event.AbstractClassImplement;
import dev.ikx.rt.impl.mods.botania.event.AlfPortalDroppedEvent;
import dev.ikx.rt.impl.mods.botania.event.ElvenTradeEvent;
import dev.ikx.rt.impl.mods.botania.event.PoolTradeEvent;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenExpansion;
import stanhebben.zenscript.annotations.ZenMethod;


@SidedZenRegister(modDeps = "botania")
@ZenClass("mods.randomtweaker.botania.IEventManager")
@ZenExpansion("crafttweaker.events.IEventManager")
public class CTEventManager {

    private static final EventList<CTPoolTradeEvent> poolTradeEventList = new EventList<>();
    private static final EventList<CTElvenTradeEvent> elvenTradeEventList = new EventList<>();
    private static final EventList<CTAlfPortalDroppedEvent> alfPortalDroppedEventList = new EventList<>();

    @ZenMethod
    public static IEventHandle onPoolTrade(IEventManager manager, IEventHandler<CTPoolTradeEvent> event) {
        return poolTradeEventList.add(event);
    }

    @ZenMethod
    public static IEventHandle onElvenTrade(IEventManager manager, IEventHandler<CTElvenTradeEvent> event) {
        return elvenTradeEventList.add(event);
    }

    @ZenMethod
    public static IEventHandle onAlfPortalDropped(IEventManager manager, IEventHandler<CTAlfPortalDroppedEvent> event) {
        return alfPortalDroppedEventList.add(event);
    }

    public static final class Handler {

        @SubscribeEvent
        public static void onPoolTrade(PoolTradeEvent event) {
            if (poolTradeEventList.hasHandlers()) {
                poolTradeEventList.publish(new AbstractClassImplement.CTPoolTradeEventImpl(event));
            }
        }

        @SubscribeEvent
        public static void onElvenTrade(ElvenTradeEvent event) {
            if (elvenTradeEventList.hasHandlers()) {
                elvenTradeEventList.publish(new AbstractClassImplement.CTElvenTradeEventImpl(event));
            }
        }

        @SubscribeEvent
        public static void onAlfPortalDropped(AlfPortalDroppedEvent event) {
            if (alfPortalDroppedEventList.hasHandlers()) {
                alfPortalDroppedEventList.publish(new AbstractClassImplement.CTAlfPortalDroppedEventImpl(event));
            }
        }

    }

}
