package dev.ikx.rt.impl.internal.mixins.botania;

import dev.ikx.rt.impl.mods.botania.cocoon.IMixinTileCocoon;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vazkii.botania.common.block.BlockCocoon;

@Pseudo
@Mixin(value = BlockCocoon.class, remap = false)
public abstract class MixinBlockCocoon {

    @Inject(method = "onBlockActivated", at = @At(value = "HEAD"), cancellable = true, remap = true)
    private void injectOnBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing s, float xs, float ys, float zs, CallbackInfoReturnable<Boolean> cir) {
	    randomTweaker$logic(world, pos, player.getHeldItem(hand), player.capabilities.isCreativeMode, player, cir);
    }

    @Inject(method = "addStack", at = @At(value = "HEAD"), cancellable = true)
    private void injectAddStack(World world, BlockPos pos, ItemStack stack, boolean creative, CallbackInfoReturnable<Boolean> cir) {
	    randomTweaker$logic(world, pos, stack, creative, null, cir);
    }

	@Unique
	private void randomTweaker$logic(World world, BlockPos pos, ItemStack stack, boolean creative, EntityPlayer player, CallbackInfoReturnable<Boolean> cir) {
        Item item = stack.getItem();
        TileEntity cocoon = world.getTileEntity(pos);
        if (!(cocoon instanceof IMixinTileCocoon) || item == Items.EMERALD || item == Items.CHORUS_FRUIT) {
            return;
        }

        IMixinTileCocoon mixinCocoon = ((IMixinTileCocoon) cocoon);
		if (!world.isRemote && !stack.isEmpty()) {

			int amount = mixinCocoon.randomTweaker$getAmount(world, pos, stack, player);
			mixinCocoon.randomTweaker$setAmount(world, pos, stack, player, amount + 1);

            if (!creative) {
                stack.shrink(1);
            }

		}
        cir.setReturnValue(true);
    }

}
