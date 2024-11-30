package dev.ikx.rt.impl.internal.mixins.ftbultimine;

import com.feed_the_beast.mods.ftbultimine.client.FTBUltimineClient;
import dev.ikx.rt.impl.internal.compact.mods.FTBUltimineCompactEvent;
import dev.ikx.rt.impl.mods.ftbultimine.capability.CapabilityRegistryHandler;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

@Mixin(value = FTBUltimineClient.class, remap = false)
public abstract class MixinFTBUltimineClient {

    @Unique
    private void randomTweaker$decouplingMethod(CallbackInfo ci) {
        if (FTBUltimineCompactEvent.isOpenFTBUltimineControl()) {
            CapabilityRegistryHandler.FTBUltimineTag capability = Minecraft.getMinecraft().player.getCapability(CapabilityRegistryHandler.FTB_ULTIMINE_CAPABILITY, null);
            if (!Objects.requireNonNull(capability).isAllow()) {
                ci.cancel();
            }
        }
    }

    @Inject(method = "info", at = @At("HEAD"), cancellable = true)
    public void injectInfo(RenderGameOverlayEvent.Text event, CallbackInfo ci) {
        randomTweaker$decouplingMethod(ci);
    }

    @Inject(method = "renderGameOverlay", at = @At("HEAD"), cancellable = true)
    public void injectRenderGameOverlay(RenderGameOverlayEvent.Post list, CallbackInfo ci) {
        randomTweaker$decouplingMethod(ci);
    }

}
