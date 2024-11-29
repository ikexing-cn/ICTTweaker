package dev.ikx.rt.impl.internal.mixins.ftbultimine;

import com.feed_the_beast.mods.ftbultimine.FTBUltimine;
import dev.ikx.rt.api.mods.ftbultimine.IPlayerExpansionFTBU;
import dev.ikx.rt.impl.internal.capability.CapabilityRegistryHandler;
import dev.ikx.rt.impl.internal.utils.InternalUtils;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.event.world.BlockEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

@Pseudo
@Mixin(value = FTBUltimine.class, remap = false)
public abstract class MixinFTBUltimine {

    @SuppressWarnings("deprecation")
    @Inject(
            method = "blockBroken",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/feed_the_beast/mods/ftbultimine/FTBUltimine;rayTrace(Lnet/minecraft/entity/player/EntityPlayer;)Lnet/minecraft/util/math/RayTraceResult;",
                    shift = At.Shift.BEFORE
            ),
            cancellable = true)
    public void injectBlockBroken(BlockEvent.BreakEvent event, CallbackInfo ci) {
        if (InternalUtils.isOpenFTBUltimineControl() && event.getPlayer() != null) {
            CapabilityRegistryHandler.FTBUltimineTag capability = event.getPlayer().getCapability(CapabilityRegistryHandler.FTB_ULTIMINE_CAPABILITY, null);
            if (!Objects.requireNonNull(capability).isAllow()) {
                if (IPlayerExpansionFTBU.langKey != null) {
                    event.getPlayer().sendMessage(new TextComponentString(I18n.translateToLocal(IPlayerExpansionFTBU.langKey)));
                }
                ci.cancel();
            }
        }
    }

}
