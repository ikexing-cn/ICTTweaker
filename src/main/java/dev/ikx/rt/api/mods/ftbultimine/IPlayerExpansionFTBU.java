package dev.ikx.rt.api.mods.ftbultimine;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.IAction;
import youyihj.zenutils.api.zenscript.SidedZenRegister;
import crafttweaker.api.minecraft.CraftTweakerMC;
import crafttweaker.api.player.IPlayer;
import dev.ikx.rt.impl.internal.capability.CapabilityRegistryHandler;
import dev.ikx.rt.impl.internal.capability.CapabilityRegistryHandler.FTBUltimineTag;
import dev.ikx.rt.impl.mods.ftbultimine.network.NetworkManager;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenExpansion;
import stanhebben.zenscript.annotations.ZenMethod;
import stanhebben.zenscript.annotations.ZenMethodStatic;

import java.util.Objects;


@SidedZenRegister(modDeps = "ftbultimine")
@ZenExpansion("crafttweaker.player.IPlayer")
@ZenClass("mods.randomtweaker.ftbultimine.IPlayer")
public class IPlayerExpansionFTBU {

    public static String langKey = null;

    @ZenMethod
    public static void setAllowFTBUltimine(IPlayer player, boolean flag) {
        EntityPlayer mcPlayer = CraftTweakerMC.getPlayer(player);
        if (!(mcPlayer instanceof EntityPlayerMP)) {
            CraftTweakerAPI.logError("The IPlayer instance is not an EntityPlayerMP instance.");
            return;
        }
        FTBUltimineTag capability = mcPlayer.getCapability(CapabilityRegistryHandler.FTB_ULTIMINE_CAPABILITY, null);
        Objects.requireNonNull(capability).setAllow(flag);
        NetworkManager.FTBUltimineTag.sendClientCustomPacket(mcPlayer); // send to client
    }

    @ZenMethod
    public static boolean isAllowFTBUltimine(IPlayer player) {
        EntityPlayer mcPlayer = CraftTweakerMC.getPlayer(player);
        if (!(mcPlayer instanceof EntityPlayerMP)) {
            CraftTweakerAPI.logError("The IPlayer instance is not an EntityPlayerMP instance.");
            return false;
        }
        FTBUltimineTag capability = mcPlayer.getCapability(CapabilityRegistryHandler.FTB_ULTIMINE_CAPABILITY, null);
        return Objects.requireNonNull(capability).isAllow();
    }

    @ZenMethodStatic
    public static void setFTBUReminderMessage(String langKey) {
        CraftTweakerAPI.apply(new ActionReminderMessage(langKey));
    }

    public static class ActionReminderMessage implements IAction {

        private final String langKey;

        public ActionReminderMessage(String langKey) {
            this.langKey = langKey;
        }

        @Override
        public void apply() {
            IPlayerExpansionFTBU.langKey = langKey;
        }

        @Override
        public String describe() {
            return "Setting FTBUltimine reminder message to " + langKey;
        }

    }

}
