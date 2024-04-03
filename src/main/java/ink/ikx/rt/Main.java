package ink.ikx.rt;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Lists;
import com.zeitheron.hammercore.utils.OnetimeCaller;
import crafttweaker.CraftTweakerAPI;
import crafttweaker.api.block.IBlockState;
import crafttweaker.api.minecraft.CraftTweakerMC;
import crafttweaker.mc1120.commands.CTChatCommand;
import crafttweaker.mods.jei.JEI;
import ink.ikx.rt.api.mods.astralsorcery.event.CTEventManagerAS;
import ink.ikx.rt.api.mods.botania.ICocoon;
import ink.ikx.rt.api.mods.botania.event.CTEventManager;
import ink.ikx.rt.api.mods.contenttweaker.subtile.ISubTileEntityRepresentation;
import ink.ikx.rt.api.mods.jei.core.IJeiPanel;
import ink.ikx.rt.api.mods.jei.core.IJeiRecipe;
import ink.ikx.rt.api.mods.thaumicadditions.IFluxConcentrator;
import ink.ikx.rt.impl.internal.capability.CapabilityRegistryHandler;
import ink.ikx.rt.impl.internal.config.RTConfig;
import ink.ikx.rt.impl.internal.event.FTBUltimineEvent;
import ink.ikx.rt.impl.internal.network.NetworkManager;
import ink.ikx.rt.impl.internal.proxy.IProxy;
import ink.ikx.rt.impl.internal.utils.InternalUtils;
import ink.ikx.rt.impl.mods.botania.module.SubTileOrechidManager;
import ink.ikx.rt.impl.mods.botania.subtile.SubTileHydroangeasModified;
import ink.ikx.rt.impl.mods.botania.subtile.SubTileOrechidModified;
import ink.ikx.rt.impl.mods.contenttweaker.MCBotaniaContentEvent;
import ink.ikx.rt.impl.mods.crafttweaker.CraftTweakerExtension;
import ink.ikx.rt.impl.mods.crafttweaker.command.AttributeCommand;
import ink.ikx.rt.impl.mods.jei.JeiAttunements;
import ink.ikx.rt.impl.mods.jei.JeiHydroangeas;
import ink.ikx.rt.impl.mods.jei.JeiOrechid;
import ink.ikx.rt.impl.mods.thaumcraft.DreamJournalEvent;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.commons.lang3.tuple.Pair;
import org.zeith.thaumicadditions.api.RecipesFluxConcentrator;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.subtile.SubTileEntity;
import vazkii.botania.common.lib.LibBlockNames;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Mod(
        modid = Main.MODID,
        name = Main.NAME,
        version = Main.VERSION,
        dependencies = Main.DESPENDENCIES
)
public class Main {

    public static final String MODID = "randomtweaker";
    public static final String NAME = "RandomTweaker";
    public static final String VERSION = "1.4.5";
    public static final String DESPENDENCIES = "required-after:crafttweaker;" +
            "required-after:mixinbooter@[4.2,);" +
            "required-after:zenutils@[1.17,);" +
            "required-after:configanytime@[2.0,);" +
            "after:contenttweaker;after:thaumcraft";

    public static final Set<IJeiPanel> JEI_PANEL_SET = new HashSet<>();
    public static final Set<IJeiRecipe> JEI_RECIPE_SET = new HashSet<>();
    public static final Map<String, ICocoon> CUSTOM_COCOONS_SPAWN = new HashMap<>();
    public static final BiMap<String, Pair<String, ISubTileEntityRepresentation>> SUB_TILE_GENERATING_MAP = HashBiMap.create();

    public static final List<String> HIDDEN_MATERIAL_LIST = new ArrayList<>();
    public static final Map<String, Integer> MATERIAL_PRIORITY_MAP = new HashMap<>();
    public static final Map<String, ItemStack> MATERIAL_SHOW_ITEM_MAP = new HashMap<>();

    @SidedProxy(clientSide = "ink.ikx.rt.impl.internal.proxy.ClientProxy", serverSide = "ink.ikx.rt.impl.internal.proxy.ServerProxy")
    public static IProxy proxy;

    @EventHandler
    public void onConstruct(FMLConstructionEvent event) {
        CraftTweakerExtension.registerAllClass();
        if (Loader.isModLoaded("astralsorcery"))
            MinecraftForge.EVENT_BUS.register(CTEventManagerAS.Handler.class);
        if (Loader.isModLoaded("botania")) {
            MinecraftForge.EVENT_BUS.register(CTEventManager.Handler.class);
            if (Loader.isModLoaded("contenttweaker")) {
                MinecraftForge.EVENT_BUS.register(MCBotaniaContentEvent.class);
            }
        }
    }

    @EventHandler
    public void onPreInit(FMLPreInitializationEvent event) {
        if (RTConfig.Botania.OrechidHasDefault && Loader.isModLoaded("botania")) {
            SubTileOrechidManager.oreWeights.put(Blocks.STONE.getDefaultState(), BotaniaAPI.oreWeights);
        }
        if (Loader.isModLoaded("thaumcraft")) {
            MinecraftForge.EVENT_BUS.register(DreamJournalEvent.class);
        }
        if (InternalUtils.isOpenFTBUltimineControl()) {
            NetworkManager.registerFTBUltimineTag();
            CapabilityRegistryHandler.registerFTBUltimineTag();
            MinecraftForge.EVENT_BUS.register(FTBUltimineEvent.class);
        }
    }

    @EventHandler
    public void onInit(FMLInitializationEvent event) {
        registryFlowerModified();
        registerAttuAltarRecipe();
    }

    @EventHandler
    public void onPostInit(FMLPostInitializationEvent event) {
        CTChatCommand.registerCommand(new AttributeCommand());
        if (Loader.isModLoaded("thaumcraft")) {
            CraftTweakerAPI.tweaker.loadScript(false, "thaumcraft");
            if (Loader.isModLoaded("thaumadditions")) {
                if (!IFluxConcentrator.LATE_REMOVES.isEmpty()) OnetimeCaller.of(this::removeRecipeLate).call();
            }
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private void removeRecipeLate() {
        for (Map.Entry<IBlockState, Boolean> entry : IFluxConcentrator.LATE_REMOVES.entrySet()) {
            Boolean isRemoveAll = entry.getValue();
            IBlockState lateRemove = entry.getKey();
            List<net.minecraft.block.state.IBlockState> toRemoveForPassInBlocks = Lists.newArrayList();

            try {
                Field field = RecipesFluxConcentrator.class.getDeclaredField("HANDLERS");
                field.setAccessible(true);
                Map<net.minecraft.block.state.IBlockState, RecipesFluxConcentrator.FluxConcentratorOutput> handlers =
                        (Map) field.get(RecipesFluxConcentrator.class);
                handlers.entrySet().removeIf(next -> {
                    boolean toReturn = false;

                    if (isRemoveAll && next.getValue().getOutState().getBlock().equals(CraftTweakerMC.getBlockState(lateRemove).getBlock())) {
                        toReturn = true;
                        toRemoveForPassInBlocks.add(next.getKey());
                    } else if (next.getValue().getOutState().equals(CraftTweakerMC.getBlockState(lateRemove))) {
                        toReturn = true;
                        toRemoveForPassInBlocks.add(next.getKey());
                    }

                    return toReturn;
                });

                Field field1 = RecipesFluxConcentrator.class.getDeclaredField("PASS_IN_BLOCKS");
                field1.setAccessible(true);
                Set passInBlocks = (Set) field1.get(RecipesFluxConcentrator.class);
                for (net.minecraft.block.state.IBlockState toRemoveForPassInBlock : toRemoveForPassInBlocks) {
                    passInBlocks.remove(CraftTweakerMC.getBlockState(toRemoveForPassInBlock));
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    @SuppressWarnings("unchecked")
    private void registryFlowerModified() {
        if (!Loader.isModLoaded("botania")) return;
        final BiMap<String, Class<? extends SubTileEntity>> subTiles;
        try {
            Field field = BotaniaAPI.class.getDeclaredField("subTiles");
            field.setAccessible(true);
            subTiles = (BiMap<String, Class<? extends SubTileEntity>>) field.get(null);
            if (Objects.nonNull(subTiles)) {
                if (RTConfig.Botania.OrechidModified) {
                    if (Loader.isModLoaded("jei")) {
                        JeiOrechid.init();
                        JEI.hideCategory("botania.orechid");
                    }
                    subTiles.forcePut(LibBlockNames.SUBTILE_ORECHID, SubTileOrechidModified.class);
                }
                if (RTConfig.Botania.HydroangeasModified) {
                    if (Loader.isModLoaded("jei")) {
                        JeiHydroangeas.init();
                    }
                    subTiles.forcePut(LibBlockNames.SUBTILE_HYDROANGEAS, SubTileHydroangeasModified.class);
                }
            }
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    private void registerAttuAltarRecipe() {
        if (Loader.isModLoaded("astralsorcery") && Loader.isModLoaded("jei")) {
            JeiAttunements.init();
        }
    }

}
