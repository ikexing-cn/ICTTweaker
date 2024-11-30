package dev.ikx.rt.api.mods.botania;

import com.google.common.collect.Maps;
import crafttweaker.CraftTweakerAPI;
import crafttweaker.api.entity.IEntityDefinition;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import dev.ikx.rt.api.mods.botania.function.DynamicSpawnTable;
import dev.ikx.rt.api.mods.botania.function.ICocoonTileEntity;
import dev.ikx.rt.impl.mods.botania.module.BotaniaManager;
import dev.ikx.rt.impl.mods.botania.cocoon.MCCocoon;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.EntityEntry;
import stanhebben.zenscript.annotations.*;
import youyihj.zenutils.api.zenscript.SidedZenRegister;

import javax.annotation.Nullable;
import java.util.Map;

@SidedZenRegister(modDeps = "botania")
@ZenClass("mods.randomtweaker.botania.ICocoon")
public interface ICocoon {

    @ZenMethod
    static ICocoon registerSpawn(@NotNull String name, IItemStack stack, Map<IEntityDefinition, Double> spawnTab, @Optional DynamicSpawnTable dynamicSpawnTab) {
        Map<EntityEntry, Double> tab = convertToEntityEntry(spawnTab);
        ICocoon cocoon = MCCocoon.create(name, CraftTweakerMC.getItemStack(stack), tab, dynamicSpawnTab);

        if (cocoon == null) {
            CraftTweakerAPI.logError("Registering " + name + " failed");
            return null;
        }
        BotaniaManager.INSTANCE.registerCocoonSpawn(name, cocoon);
        return cocoon;
    }

    @Nullable
    @ZenMethod
    static ICocoon getInstanceByStack(IItemStack stack) {
        return getInstanceByStack(CraftTweakerMC.getItemStack(stack));
    }

    @ZenMethod
    static ICocoon getInstanceByName(String name) {
        return BotaniaManager.INSTANCE.getCocoonSpawn(name);
    }

    @Nullable
    static ICocoon getInstanceByStack(ItemStack stack) {
        return BotaniaManager.INSTANCE.getCocoonsSpawnMap()
                .values()
                .stream()
                .filter(cocoon -> cocoon.match(stack))
                .findFirst()
                .orElse(null);
    }

    static Map<EntityEntry, Double> convertToEntityEntry(Map<IEntityDefinition, Double> originalMap) {
        Map<EntityEntry, Double> tab = Maps.newHashMap();
        originalMap.forEach((entity, probably) -> {
            if (entity != null) {
                if (entity.getInternal() instanceof EntityEntry) {
                    tab.put((EntityEntry) entity.getInternal(), probably);
                } else {
                    CraftTweakerAPI.logError("The internal type of " + entity.getId() + " is not EntityEntry!");
                }
            }
        });
        return tab;
    }

    ItemStack getStack();

    boolean match(ItemStack stack);

    DynamicSpawnTable getDynamicSpawnTable();

    Map<EntityEntry, Double> getSpawnTab();

    double getProbabilityByEntity(EntityEntry entity);

    String getDynamicResult(ItemStack stack, EntityPlayer player, ICocoonTileEntity tile);

    @ZenMethod
    @ZenGetter("name")
    String getName();

}
