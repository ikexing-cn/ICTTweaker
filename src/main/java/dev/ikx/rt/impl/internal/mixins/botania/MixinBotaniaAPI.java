package dev.ikx.rt.impl.internal.mixins.botania;

import dev.ikx.rt.Main;
import dev.ikx.rt.impl.mods.contenttweaker.subtile.MCSubTileEntityFunctionalContent;
import dev.ikx.rt.impl.mods.contenttweaker.subtile.MCSubTileEntityGeneratingContent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.subtile.SubTileEntity;
import vazkii.botania.api.subtile.signature.BasicSignature;
import vazkii.botania.api.subtile.signature.SubTileSignature;

import java.util.HashMap;
import java.util.Map;

@Pseudo
@Mixin(value = BotaniaAPI.class, remap = false)
public abstract class MixinBotaniaAPI {

    private static final Map<String, SubTileSignature> customSubTileSignatures = new HashMap<>();

    @Inject(method = "getSignatureForName", at = @At(value = "HEAD"), cancellable = true)
    private static void injectGetSignatureForName(String name, CallbackInfoReturnable<SubTileSignature> cir) {
        if (Main.SUB_TILE_GENERATING_MAP.containsKey(name)) {
            if (!customSubTileSignatures.containsKey(name))
                customSubTileSignatures.put(name, new BasicSignature(name));
            cir.setReturnValue(customSubTileSignatures.get(name));
        }
    }

    @Inject(method = "getSubTileMapping", at = @At(value = "HEAD"), cancellable = true)
    private static void injectGetSubTileMapping(String key, CallbackInfoReturnable<Class<? extends SubTileEntity>> cir) {
        if (Main.SUB_TILE_GENERATING_MAP.containsKey(key)) {
            if (Main.SUB_TILE_GENERATING_MAP.get(key).getKey().equals("generating")) {
                cir.setReturnValue(MCSubTileEntityGeneratingContent.class);
            } else {
                cir.setReturnValue(MCSubTileEntityFunctionalContent.class);
            }
        }
    }

}
