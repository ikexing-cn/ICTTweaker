package dev.ikx.rt.impl.internal.event;

import dev.ikx.rt.Main;
import dev.ikx.rt.impl.internal.capability.CapabilityRegistryHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Objects;

public class FTBUltimineEvent {

    @SubscribeEvent
    public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof EntityPlayer) {
            CapabilityRegistryHandler.FTBUltimineTagProvider provider = new CapabilityRegistryHandler.FTBUltimineTagProvider();
            event.addCapability(new ResourceLocation(Main.MODID + ":ftb_ultimine_tag"), provider);
        }
    }

    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {
        CapabilityRegistryHandler.FTBUltimineTag original = event.getOriginal().getCapability(CapabilityRegistryHandler.FTB_ULTIMINE_CAPABILITY, null);
        CapabilityRegistryHandler.FTBUltimineTag instance = event.getEntityPlayer().getCapability(CapabilityRegistryHandler.FTB_ULTIMINE_CAPABILITY, null);

        Objects.requireNonNull(instance).setAllow(Objects.requireNonNull(original).isAllow());
    }

}
