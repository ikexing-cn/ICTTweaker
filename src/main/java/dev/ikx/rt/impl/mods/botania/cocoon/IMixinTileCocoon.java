package dev.ikx.rt.impl.mods.botania.cocoon;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IMixinTileCocoon {

	int randomTweaker$getMapSize();

	int randomTweaker$getAmount(World world, BlockPos pos, ItemStack stack, EntityPlayer player);

	void randomTweaker$setAmount(World world, BlockPos pos, ItemStack stack, EntityPlayer player, int amount);

}
