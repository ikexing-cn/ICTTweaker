package dev.ikx.rt.impl.internal.clazz.vanilla;

import com.google.common.collect.Multimap;
import dev.ikx.rt.api.vanilla.event.CTEventHandler;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;

/**
 * @author youyihj
 */
@SuppressWarnings("unused")
public class ItemStackHook {
    public static void fireAttributeModificationEvent(ItemStack stack, EntityEquipmentSlot equipmentSlot, Multimap<String, AttributeModifier> multimap) {
        CTEventHandler.Holder.publishItemAttributeModificationEvent(stack, equipmentSlot, multimap);
    }
}
