package ink.ikx.rt.api.vanilla;

import com.google.common.collect.Multimap;
import crafttweaker.api.event.IEventHandle;
import crafttweaker.api.minecraft.CraftTweakerMC;
import crafttweaker.util.EventList;
import crafttweaker.util.IEventHandler;
import ink.ikx.rt.impl.vanilla.ItemAttributeModificationEvent;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenExpansion;
import stanhebben.zenscript.annotations.ZenMethod;
import youyihj.zenutils.api.zenscript.SidedZenRegister;

/**
 * @author youyihj
 */

@SidedZenRegister
@ZenExpansion("crafttweaker.events.IEventManager")
public class CTEventHandler {
    public static final EventList<IItemAttributeModificationEvent> elAttributeModification = new EventList<>();

    @ZenMethod
    public static IEventHandle onItemAttributeModificationEvent(IEventHandler<IItemAttributeModificationEvent> handler) {
        Holder.used = true;
        return elAttributeModification.add(handler);
    }

    // another holder class to avoid early class loading issues
    public static class Holder {
        private static boolean used = false;

        public static void publishItemAttributeModificationEvent(ItemStack stack, EntityEquipmentSlot equipmentSlot, Multimap<String, AttributeModifier> multimap) {
            if (used) {
                elAttributeModification.publish(new ItemAttributeModificationEvent(CraftTweakerMC.getIItemStack(stack), CraftTweakerMC.getIEntityEquipmentSlot(equipmentSlot), multimap));
            }
        }

    }

}
