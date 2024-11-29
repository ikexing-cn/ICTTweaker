package dev.ikx.rt.compact;

import dev.ikx.rt.api.mods.astralsorcery.event.CTEventManagerAS;
import dev.ikx.rt.impl.mods.jei.JeiAttunements;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

public class AstralSorceryCompactEvent implements ICompactEvent {
    @Override
    public String modid() {
        return "astralsorcery";
    }

    @Override
    public void onConstruct(FMLConstructionEvent event) {
        MinecraftForge.EVENT_BUS.register(CTEventManagerAS.Handler.class);
    }

    @Override
    public void onInit(FMLInitializationEvent event) {
        if (Loader.isModLoaded("jei")) JeiAttunements.init();
    }
}
