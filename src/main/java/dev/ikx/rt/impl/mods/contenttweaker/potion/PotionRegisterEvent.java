package dev.ikx.rt.impl.mods.contenttweaker.potion;

import com.google.common.collect.Maps;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.Map;

public class PotionRegisterEvent {

    public static final Map<String, MCPotionContent> POTION_MAP = Maps.newHashMap();
    public static final Map<String, PotionType> POTION_TYPE_MAP = Maps.newHashMap();

    @SubscribeEvent
    public static void onPotionRegister(RegistryEvent.Register<Potion> event) {
        IForgeRegistry<Potion> registry = event.getRegistry();
        POTION_MAP.values().forEach(registry::register);
    }

    @SubscribeEvent
    public static void onPotionTypeReg(RegistryEvent.Register<PotionType> event) {
        IForgeRegistry<PotionType> registry = event.getRegistry();
        POTION_TYPE_MAP.values().forEach(registry::register);
    }

}
