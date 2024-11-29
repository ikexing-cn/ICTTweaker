package dev.ikx.rt.compact;

import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public interface ICompactEvent {

    String modid();

    default void onConstruct(FMLConstructionEvent event) {
    }

    default void onPreInit(FMLPreInitializationEvent event) {
    }

    default void onInit(FMLInitializationEvent event) {
    }

    default void onPostInit(FMLPostInitializationEvent event) {
    }

}
