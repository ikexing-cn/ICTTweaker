package dev.ikx.rt.impl.mods.botania.cocoon;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.api.minecraft.CraftTweakerMC;
import dev.ikx.rt.Main;
import dev.ikx.rt.api.mods.botania.ICocoon;
import dev.ikx.rt.api.mods.botania.function.DynamicSpawnTable;
import dev.ikx.rt.api.mods.botania.function.ICocoonTileEntity;
import dev.ikx.rt.impl.internal.utils.InternalUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.EntityEntry;

import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class MCCocoon implements ICocoon {

    private final String name;
    private final ItemStack giveStack;
    private final Map<EntityEntry, Double> spawnTab;
	private final DynamicSpawnTable dynamicSpawn;

	private MCCocoon(String name, ItemStack giveStack, Map<EntityEntry, Double> spawnTab, DynamicSpawnTable dynamicSpawn) {
        this.name = name;
        this.spawnTab = spawnTab;
        this.giveStack = giveStack;
		this.dynamicSpawn = dynamicSpawn;
    }

	public static ICocoon create(@Nonnull String name, ItemStack giveStack, @Nonnull Map<EntityEntry, Double> spawnTab) {
		return create(name, giveStack, spawnTab, (stack, player, tile) -> name);
    }

    public static ICocoon create(@Nonnull String name, @Nonnull ItemStack giveStack, @Nonnull Map<EntityEntry, Double> spawnTab, DynamicSpawnTable dynamicSpawn) {
	    if (check(name, spawnTab)) {
		    if (dynamicSpawn == null) {
			    dynamicSpawn = (stack, player, tile) -> name;
		    }
		    return new MCCocoon(name, giveStack, spawnTab, dynamicSpawn);
        }
	    return null;
    }

	private static boolean check(String name, Map<EntityEntry, Double> spawnTab) {

        if (Main.CUSTOM_COCOONS_SPAWN.containsKey(name)) {
	        CraftTweakerAPI.logError("The name must be unique!");
            return false;
        }

        for (EntityEntry entity : spawnTab.keySet()) {
	        if (entity == null) {
                CraftTweakerAPI.logError("The entity cannot be null!", new IllegalArgumentException());
                return false;
            }
	        if (spawnTab.get(entity) <= 0.0f) {
		        CraftTweakerAPI.logError("Probability less than 0!", new IllegalArgumentException());
		        return false;
	        }
        }

        return true;
    }

    public ItemStack getStack() {
        return giveStack;
    }

    public Map<EntityEntry, Double> getSpawnTab() {
        return spawnTab;
    }

    @Nullable
    @Override
    public DynamicSpawnTable getDynamicSpawnTable() {
        return this.dynamicSpawn;
    }

    @Override
    public double getProbabilityByEntity(EntityEntry entity) {
        return spawnTab.get(entity);
    }

    public boolean match(ItemStack stack) {
        return InternalUtils.areItemStacksEqual(this.giveStack, stack);
    }

    @Override
    public String getDynamicResult(ItemStack stack, EntityPlayer player, ICocoonTileEntity tile) {
	    return this.dynamicSpawn.call(
            CraftTweakerMC.getIItemStack(stack),
            CraftTweakerMC.getIPlayer(player),
            tile
	    );
    }

    @Override
    public String getName() {
        return this.name;
    }

}
