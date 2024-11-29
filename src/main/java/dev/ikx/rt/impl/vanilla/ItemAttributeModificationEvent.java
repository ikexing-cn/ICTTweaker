package dev.ikx.rt.impl.vanilla;

import com.google.common.collect.Multimap;
import crafttweaker.api.entity.IEntityEquipmentSlot;
import crafttweaker.api.entity.attribute.IEntityAttributeModifier;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import dev.ikx.rt.api.vanilla.IItemAttributeModificationEvent;
import net.minecraft.entity.ai.attributes.AttributeModifier;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author youyihj
 */
public class ItemAttributeModificationEvent implements IItemAttributeModificationEvent {
    private final IItemStack item;
    private final IEntityEquipmentSlot slot;
    private final Multimap<String, AttributeModifier> modifierMultimap;

    public ItemAttributeModificationEvent(IItemStack item, IEntityEquipmentSlot slot, Multimap<String, AttributeModifier> modifierMultimap) {
        this.item = item;
        this.slot = slot;
        this.modifierMultimap = modifierMultimap;
    }

    @Override
    public IItemStack getItem() {
        return item;
    }

    @Override
    public IEntityEquipmentSlot getSlot() {
        return slot;
    }

    @Override
    public List<IEntityAttributeModifier> getModifier(String name) {
        return modifierMultimap.get(name).stream().map(CraftTweakerMC::getIEntityAttributeModifier).collect(Collectors.toList());
    }

    @Override
    public void putModifier(String name, IEntityAttributeModifier modifier) {
        modifierMultimap.put(name, CraftTweakerMC.getAttributeModifier(modifier));
    }

    @Override
    public void removeModifier(String name, String uuid) {
        UUID standard = UUID.fromString(uuid);
        modifierMultimap.get(name).removeIf(it -> it.getID().equals(standard));
    }

    @Override
    public void clearModifier(String name) {
        modifierMultimap.removeAll(name);
    }
}
