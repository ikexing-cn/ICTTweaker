package dev.ikx.rt.compact;

import com.google.common.collect.Lists;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.util.List;
import java.util.function.Consumer;

public class CompactManager {

    public static final CompactManager INSTANCE = new CompactManager();

    public List<ICompactEvent> modCompactEvents = Lists.newArrayList(
            new AstralSorceryCompactEvent(),
            new BotaniaCompactEvent(),
            new CraftTweakerCompactEvent(),
            new FTBUltimineCompactEvent(),
            new ThaumcraftCompactEvent(),
            new ThaumcraftCompactEvent.ThaumadditionsCompactEvent()
    );

    private void registerEvent(Consumer<ICompactEvent> consumer) {
        this.modCompactEvents.stream()
                .filter(compact -> Loader.isModLoaded(compact.modid()))
                .forEach(consumer);
    }

    public void registerConstructEvent(FMLConstructionEvent event) {
        this.registerEvent(compact -> compact.onConstruct(event));
    }

    public void registerPreInitEvent(FMLPreInitializationEvent event) {
        this.registerEvent(compact -> compact.onPreInit(event));
    }

    public void registerPostInitEvent(FMLPostInitializationEvent event) {
        this.registerEvent(compact -> compact.onPostInit(event));
    }

    public void registerInitEvent(FMLInitializationEvent event) {
        this.registerEvent(compact -> compact.onInit(event));
    }
}
