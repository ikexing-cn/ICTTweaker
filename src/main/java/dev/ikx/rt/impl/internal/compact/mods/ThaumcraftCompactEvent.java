package dev.ikx.rt.impl.internal.compact.mods;

import com.google.common.collect.Lists;
import com.zeitheron.hammercore.utils.OnetimeCaller;
import crafttweaker.CraftTweakerAPI;
import crafttweaker.api.block.IBlockState;
import crafttweaker.api.minecraft.CraftTweakerMC;
import dev.ikx.rt.api.mods.thaumicadditions.FluxConcentrator;
import dev.ikx.rt.impl.internal.compact.ICompactEvent;
import dev.ikx.rt.impl.mods.thaumcraft.DreamJournalEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.zeith.thaumicadditions.api.RecipesFluxConcentrator;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ThaumcraftCompactEvent implements ICompactEvent {
    @Override
    public String modid() {
        return "thaumcraft";
    }

    @Override
    public void onPreInit(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(DreamJournalEvent.class);
    }

    @Override
    public void onPostInit(FMLPostInitializationEvent event) {
        CraftTweakerAPI.tweaker.loadScript(false, "thaumcraft");
    }

    public static class ThaumadditionsCompactEvent implements ICompactEvent {
        @Override
        public String modid() {
            return "thaumadditions";
        }

        @Override
        public void onPostInit(FMLPostInitializationEvent event) {
            if (!FluxConcentrator.LATE_REMOVES.isEmpty()) OnetimeCaller.of(this::removeRecipeLate).call();
        }

        @SuppressWarnings({"rawtypes", "unchecked", "CallToPrintStackTrace"})
        private void removeRecipeLate() {
            for (Map.Entry<IBlockState, Boolean> entry : FluxConcentrator.LATE_REMOVES.entrySet()) {
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
    }
}