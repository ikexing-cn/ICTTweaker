package ink.ikx.rt.api.vanilla;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.IAction;
import crafttweaker.api.entity.IEntityEquipmentSlot;
import crafttweaker.api.entity.attribute.IEntityAttributeModifier;
import crafttweaker.api.item.IIngredient;
import crafttweaker.util.IEventHandler;
import ink.ikx.rt.impl.internal.config.RTConfig;
import stanhebben.zenscript.annotations.Optional;
import stanhebben.zenscript.annotations.ZenExpansion;
import stanhebben.zenscript.annotations.ZenMethod;
import youyihj.zenutils.api.zenscript.SidedZenRegister;

/**
 * @author youyihj
 */

@SidedZenRegister
@ZenExpansion("crafttweaker.item.IIngredient")
public class AttributeModificationListeners {
    @ZenMethod
    public static void addGlobalAttributeModifier(IIngredient ingredient, String attributeName, IEntityEquipmentSlot slot, IEntityAttributeModifier modifier, @Optional boolean clearPrevious) {
        CraftTweakerAPI.apply(new AddListenerAction(event -> {
            if (slot.equals(event.getSlot()) && ingredient.matches(event.getItem())) {
                if (clearPrevious) {
                    event.clearModifier(attributeName);
                }
                event.putModifier(attributeName, modifier);
            }
        }));
    }

    @ZenMethod
    public static void removeGlobalAttributeModifier(IIngredient ingredient, String attributeName, IEntityEquipmentSlot slot, String uuid) {
        CraftTweakerAPI.apply(new AddListenerAction(event -> {
            if (slot.equals(event.getSlot()) && ingredient.matches(event.getItem())) {
                event.removeModifier(attributeName, uuid);
            }
        }));
    }

    @ZenMethod
    public static void clearGlobalAttributeModifier(IIngredient ingredient, String attributeName, IEntityEquipmentSlot slot) {
        CraftTweakerAPI.apply(new AddListenerAction(event -> {
            if (slot.equals(event.getSlot()) && ingredient.matches(event.getItem())) {
                event.clearModifier(attributeName);
            }
        }));
    }

    public static class AddListenerAction implements IAction {
        private final IEventHandler<IItemAttributeModificationEvent> handler;

        public AddListenerAction(IEventHandler<IItemAttributeModificationEvent> handler) {
            this.handler = handler;
        }

        @Override
        public void apply() {
            CTEventHandler.onItemAttributeModificationEvent(handler);
        }

        @Override
        public String describe() {
            return "";
        }

        @Override
        public boolean validate() {
            return RTConfig.RandomTweaker.itemAttributeModification;
        }

        @Override
        public String describeInvalid() {
            return "Please change itemAttributeModification config to true";
        }

    }

}
