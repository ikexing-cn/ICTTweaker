package dev.ikx.rt.api.vanilla;

import crafttweaker.api.entity.IEntityEquipmentSlot;
import crafttweaker.api.entity.attribute.IEntityAttributeModifier;
import crafttweaker.api.item.IItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenGetter;
import stanhebben.zenscript.annotations.ZenMethod;
import youyihj.zenutils.api.zenscript.SidedZenRegister;

import java.util.List;

/**
 * @author youyihj
 */

@SidedZenRegister
@ZenClass("mods.randomtweaker.vanilla.IItemAttributeModificationEvent")
public interface IItemAttributeModificationEvent {
    @ZenGetter("item")
    @ZenMethod
    IItemStack getItem();

    @ZenGetter("slot")
    @ZenMethod
    IEntityEquipmentSlot getSlot();

    @ZenMethod
    List<IEntityAttributeModifier> getModifier(String name);

    @ZenMethod
    void putModifier(String name, IEntityAttributeModifier modifier);

    @ZenMethod
    void removeModifier(String name, String uuid);

    @ZenMethod
    void clearModifier(String name);
}
