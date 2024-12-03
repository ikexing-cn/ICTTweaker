package dev.ikx.rt.impl.internal.compact.mods;

import com.google.common.collect.BiMap;
import crafttweaker.mods.jei.JEI;
import dev.ikx.rt.api.mods.botania.event.CTEventManager;
import dev.ikx.rt.impl.internal.compact.ICompactEvent;
import dev.ikx.rt.impl.internal.config.RTConfig;
import dev.ikx.rt.impl.mods.botania.module.SubTileOrechidManager;
import dev.ikx.rt.impl.mods.botania.subtile.SubTileHydroangeasModified;
import dev.ikx.rt.impl.mods.botania.subtile.SubTileOrechidModified;
import dev.ikx.rt.impl.mods.botania.event.CustomBotaniaEvent;
import dev.ikx.rt.impl.mods.jei.JEIHydroangeas;
import dev.ikx.rt.impl.mods.jei.JEIOrechid;
import net.minecraft.init.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.subtile.SubTileEntity;
import vazkii.botania.common.lib.LibBlockNames;

import java.lang.reflect.Field;
import java.util.Objects;

public class BotaniaCompactEvent implements ICompactEvent {
    @Override
    public String modid() {
        return "botania";
    }

    @Override
    public void onConstruct(FMLConstructionEvent event) {
        MinecraftForge.EVENT_BUS.register(CTEventManager.Handler.class);
        if (Loader.isModLoaded("contenttweaker")) {
            MinecraftForge.EVENT_BUS.register(CustomBotaniaEvent.class);
        }
    }

    @Override
    public void onPreInit(FMLPreInitializationEvent event) {
        if (RTConfig.Botania.OrechidHasDefault) {
            SubTileOrechidManager.oreWeights.put(Objects.requireNonNull(Blocks.STONE).getDefaultState(), BotaniaAPI.oreWeights);
        }
    }

    @Override
    public void onInit(FMLInitializationEvent event) {
        this.registryFlowerModified();
    }

    @SuppressWarnings({"unchecked", "CallToPrintStackTrace"})
    private void registryFlowerModified() {
        final BiMap<String, Class<? extends SubTileEntity>> subTiles;
        try {
            Field field = BotaniaAPI.class.getDeclaredField("subTiles");
            field.setAccessible(true);
            subTiles = (BiMap<String, Class<? extends SubTileEntity>>) field.get(null);
            if (Objects.nonNull(subTiles)) {
                if (RTConfig.Botania.OrechidModified) {
                    if (Loader.isModLoaded("jei")) {
                        JEIOrechid.init();
                        JEI.hideCategory("botania.orechid");
                    }
                    subTiles.forcePut(LibBlockNames.SUBTILE_ORECHID, SubTileOrechidModified.class);
                }
                if (RTConfig.Botania.HydroangeasModified) {
                    if (Loader.isModLoaded("jei")) {
                        JEIHydroangeas.init();
                    }
                    subTiles.forcePut(LibBlockNames.SUBTILE_HYDROANGEAS, SubTileHydroangeasModified.class);
                }
            }
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
