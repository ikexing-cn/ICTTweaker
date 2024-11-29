package dev.ikx.rt.impl.internal.mixins.botania;

import dev.ikx.rt.impl.mods.botania.event.PoolTradeEvent;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import vazkii.botania.api.recipe.RecipeManaInfusion;
import vazkii.botania.common.block.tile.TileMod;
import vazkii.botania.common.block.tile.mana.TilePool;

@Pseudo
@Mixin(value = TilePool.class, remap = false)
public abstract class MixinTilePool extends TileMod {

    @Shadow
    public abstract int getCurrentMana();

    @Shadow
    public abstract void recieveMana(int mana);

    @Shadow
    protected abstract void craftingFanciness();
    
    @SuppressWarnings("deprecation")
    @Inject(method = "collideEntityItem", at = @At(value = "INVOKE", target = "Lvazkii/botania/api/recipe/RecipeManaInfusion;getManaToConsume()I", shift = At.Shift.AFTER), locals = LocalCapture.CAPTURE_FAILHARD, cancellable = true)
    public void injectCollideEntityItem(EntityItem item, CallbackInfoReturnable<Boolean> cir, ItemStack stack, RecipeManaInfusion recipe) {
        int mana = recipe.getManaToConsume();
        PoolTradeEvent event = new PoolTradeEvent(mana, getCurrentMana(), recipe.getOutput().copy(), item, recipe.isAlchemy(), recipe.isConjuration(), getWorld(), getPos());
        if (!event.post()) {
            if (event.getMana() > getCurrentMana() && !event.isAllowExceed()) {
                cir.setReturnValue(false);
                return;
            }
            recieveMana(-event.getMana());
            stack.shrink(1);
            EntityItem outputItem = new EntityItem(world, pos.getX() + 0.5, pos.getY() + 1.5, pos.getZ() + 0.5, event.getOutput());
            outputItem.age = 105;
            world.spawnEntity(outputItem);
            craftingFanciness();
            cir.setReturnValue(true);
        }
        cir.setReturnValue(false);
    }

}