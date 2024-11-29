package dev.ikx.rt.impl.internal.mixins.tconstruct;

import dev.ikx.rt.Main;
import dev.ikx.rt.impl.internal.clazz.mods.tconstruct.AbstractMaterialSectionTransformerHooks;
import dev.ikx.rt.impl.internal.config.RTConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import slimeknights.mantle.client.gui.book.element.BookElement;
import slimeknights.tconstruct.library.book.content.ContentMaterial;
import slimeknights.tconstruct.library.book.elements.ElementTinkerItem;
import slimeknights.tconstruct.library.materials.Material;

import java.util.ArrayList;
import java.util.List;

@Mixin(value = ContentMaterial.class, remap = false)
public class MixinContentMaterial {

	@Shadow
	private Material material;

	@SuppressWarnings(value = {"rawtypes, unchecked"})
	@Inject(method = "addDisplayItems", at = @At(value = "INVOKE", target = "Lslimeknights/tconstruct/library/materials/Material;isCraftable()Z", shift = Shift.BEFORE), locals = LocalCapture.CAPTURE_FAILHARD)
	private void injectAddDisplayItems(ArrayList<BookElement> list, int x, CallbackInfo ci, List displayTools) {
		if (RTConfig.Tconstruct.iconModification && AbstractMaterialSectionTransformerHooks.isMaterialInShowItemMap(material)) {
			displayTools.remove(0);
			displayTools.add(new ElementTinkerItem(Main.MATERIAL_SHOW_ITEM_MAP.get(material.getIdentifier())));
		}
	}

}
