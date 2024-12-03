package dev.ikx.rt.impl.internal.compact.mods;

import dev.ikx.rt.impl.internal.compact.ICompactEvent;
import dev.ikx.rt.impl.internal.config.RTConfig;
import dev.ikx.rt.impl.mods.ftbultimine.capability.CapabilityRegistryHandler;
import dev.ikx.rt.impl.mods.ftbultimine.event.FTBUltimineEvent;
import dev.ikx.rt.impl.mods.ftbultimine.network.NetworkManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class FTBUltimineCompactEvent implements ICompactEvent {
    @Override
    public String modid() {
        return "ftbultimine";
    }

    @Override
    public void onPreInit(FMLPreInitializationEvent event) {
        if (FTBUltimineCompactEvent.isOpenFTBUltimineControl()) {
            NetworkManager.registerFTBUltimineTag();
            CapabilityRegistryHandler.registerFTBUltimineTag();
            MinecraftForge.EVENT_BUS.register(FTBUltimineEvent.class);
        }
    }

    public static boolean isOpenFTBUltimineControl() {
        return RTConfig.FTBUltimine.AllowCrTControl;
    }

}
