package dev.ikx.rt;

import dev.ikx.rt.api.mods.jei.core.IJeiPanel;
import dev.ikx.rt.api.mods.jei.core.IJeiRecipe;
import dev.ikx.rt.impl.internal.compact.CompactManager;
import dev.ikx.rt.impl.internal.proxy.IProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.util.HashSet;
import java.util.Set;

@Mod(
        modid = Constant.MODID,
        name = Constant.NAME,
        version = Constant.VERSION,
        dependencies = Constant.DESPENDENCIES
)
public class Main {
    public static final Set<IJeiPanel> JEI_PANEL_SET = new HashSet<>();
    public static final Set<IJeiRecipe> JEI_RECIPE_SET = new HashSet<>();

    @SidedProxy(clientSide = "dev.ikx.rt.impl.internal.proxy.ClientProxy", serverSide = "dev.ikx.rt.impl.internal.proxy.ServerProxy")
    public static IProxy proxy;

    @EventHandler
    public void onConstruct(FMLConstructionEvent event) {
        CompactManager.INSTANCE.registerConstructEvent(event);
    }

    @EventHandler
    public void onPreInit(FMLPreInitializationEvent event) {
        CompactManager.INSTANCE.registerPreInitEvent(event);
    }

    @EventHandler
    public void onInit(FMLInitializationEvent event) {
        CompactManager.INSTANCE.registerInitEvent(event);
    }

    @EventHandler
    public void onPostInit(FMLPostInitializationEvent event) {
        CompactManager.INSTANCE.registerPostInitEvent(event);
    }

}
