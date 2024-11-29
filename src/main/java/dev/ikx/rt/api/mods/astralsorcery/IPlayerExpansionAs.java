package dev.ikx.rt.api.mods.astralsorcery;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.api.minecraft.CraftTweakerMC;
import crafttweaker.api.player.IPlayer;
import hellfirepvp.astralsorcery.common.constellation.IMajorConstellation;
import hellfirepvp.astralsorcery.common.data.research.ResearchManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.MathHelper;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenExpansion;
import stanhebben.zenscript.annotations.ZenMethod;
import youyihj.zenutils.api.zenscript.SidedZenRegister;

import java.util.List;


@SidedZenRegister(modDeps = "astralsorcery")
@ZenExpansion("crafttweaker.player.IPlayer")
@ZenClass("mods.randomtweaker.astralsorcery.IPlayer")
public class IPlayerExpansionAs {

    @ZenMethod
    public static float getPerkPercentToNextLevel(IPlayer player) {
        EntityPlayer mcPlayer = CraftTweakerMC.getPlayer(player);
        return ResearchManager.getProgress(mcPlayer).getPercentToNextLevel(mcPlayer);
    }

    @ZenMethod
    public static int getPerkLevel(IPlayer player) {
        EntityPlayer mcPlayer = CraftTweakerMC.getPlayer(player);
        return ResearchManager.getProgress(mcPlayer).getPerkLevel(mcPlayer);
    }

    @ZenMethod
    public static double getPerkExp(IPlayer player) {
        return ResearchManager.getProgress(CraftTweakerMC.getPlayer(player)).getPerkExp();
    }

    @ZenMethod
    public static String getAttunedConstellation(IPlayer player) {
        IMajorConstellation attunedConstellation =
                ResearchManager.getProgress(CraftTweakerMC.getPlayer(player)).getAttunedConstellation();
        return attunedConstellation == null ? null : attunedConstellation.getUnlocalizedName();
    }

    @ZenMethod
    public static List<String> getKnownConstellations(IPlayer player) {

        return ResearchManager.getProgress(CraftTweakerMC.getPlayer(player)).getKnownConstellations();
    }

    @ZenMethod
    public static List<String> getSeenConstellations(IPlayer player) {
        return ResearchManager.getProgress(CraftTweakerMC.getPlayer(player)).getSeenConstellations();
    }

    @ZenMethod
    public static boolean modifyPerkExp(IPlayer player, double exp) {
        EntityPlayer mcPlayer = CraftTweakerMC.getPlayer(player);
        if (IPlayerExpansionAs.getAttunedConstellation(player) == null) {
            CraftTweakerAPI.logInfo("The Player has not constellations.");
            return false;
        }
        return mcPlayer instanceof EntityPlayerMP && ResearchManager.modifyExp(mcPlayer, exp);
    }

    @ZenMethod
    public static boolean setPerkExp(IPlayer player, double exp) {
        EntityPlayer mcPlayer = CraftTweakerMC.getPlayer(player);
        if (IPlayerExpansionAs.getAttunedConstellation(player) == null) {
            CraftTweakerAPI.logInfo("The Player is not constellations");
            return false;
        }
        return mcPlayer instanceof EntityPlayerMP && ResearchManager.setExp(mcPlayer, MathHelper.lfloor(exp));
    }

}
