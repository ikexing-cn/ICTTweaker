package dev.ikx.rt.impl.internal.compact.mods;

import dev.ikx.rt.impl.internal.compact.ICompactEvent;
import dev.ikx.rt.impl.mods.contenttweaker.potion.PotionRegisterEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;

public class ContentTweakerCompactEvent implements ICompactEvent {
    @Override
    public String modid() {
        return "contenttweaker";
    }

    @Override
    public void onConstruct(FMLConstructionEvent event) {
        MinecraftForge.EVENT_BUS.register(PotionRegisterEvent.class);
    }
}
