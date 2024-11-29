package dev.ikx.rt.impl.mods.thaumcraft;

import dev.ikx.rt.impl.internal.config.RTConfig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import thaumcraft.api.capabilities.IPlayerKnowledge;
import thaumcraft.api.capabilities.ThaumcraftCapabilities;
import thaumcraft.common.items.resources.ItemCrystalEssence;

public class DreamJournalEvent {

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onItemPickup(EntityItemPickupEvent event) {
        EntityPlayer player = event.getEntityPlayer();
        World world = player.getEntityWorld();
        if (RTConfig.Thaumcraft.DreamJournal && !world.isRemote && event.getItem() != null) {
            IPlayerKnowledge knowledge = ThaumcraftCapabilities.getKnowledge(player);
            String gotdream = "!gotdream";
            if ((event.getItem().getItem().getItem() instanceof ItemCrystalEssence &&
                !knowledge.isResearchKnown(gotdream))) {
                knowledge.addResearch(gotdream);
            }
        }
    }

}
