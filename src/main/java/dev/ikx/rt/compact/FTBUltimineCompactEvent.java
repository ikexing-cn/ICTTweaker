package dev.ikx.rt.compact;

import dev.ikx.rt.impl.internal.capability.CapabilityRegistryHandler;
import dev.ikx.rt.impl.internal.event.FTBUltimineEvent;
import dev.ikx.rt.impl.internal.network.NetworkManager;
import dev.ikx.rt.impl.internal.utils.InternalUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class FTBUltimineCompactEvent implements ICompactEvent {
    @Override
    public String modid() {
        return "ftbultimine";
    }

    @Override
    public void onPreInit(FMLPreInitializationEvent event) {
        if (InternalUtils.isOpenFTBUltimineControl()) {
            NetworkManager.registerFTBUltimineTag();
            CapabilityRegistryHandler.registerFTBUltimineTag();
            MinecraftForge.EVENT_BUS.register(FTBUltimineEvent.class);
        }
    }
}
