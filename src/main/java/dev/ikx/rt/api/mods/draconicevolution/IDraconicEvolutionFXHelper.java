package dev.ikx.rt.api.mods.draconicevolution;

import com.brandon3055.brandonscore.client.particle.BCEffectHandler;
import com.brandon3055.brandonscore.lib.Vec3D;
import crafttweaker.CraftTweakerAPI;
import youyihj.zenutils.api.zenscript.SidedZenRegister;
import crafttweaker.api.minecraft.CraftTweakerMC;
import crafttweaker.api.world.IVector3d;

import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

/**
 * @author youyihj
 */

@SidedZenRegister(modDeps = "draconicevolution")
@ZenClass("mods.randomtweaker.draconicevolution.IDraconicEvolutionFXHelper")
public class IDraconicEvolutionFXHelper {
    @ZenMethod
    public static void spawnFX(int particleID, IVector3d pos, IVector3d speed, double viewRange, int... args) {
        Vec3D posA = new Vec3D(pos.getX(), pos.getY(), pos.getZ());
        Vec3D speedA = new Vec3D(speed.getX(), speed.getY(), speed.getZ());
        BCEffectHandler.spawnFX(particleID, CraftTweakerMC.getWorld(CraftTweakerAPI.client.getPlayer().getWorld()), posA, speedA, viewRange, args);
    }

    @ZenMethod
    public static void spawnFX(int particleID, IVector3d pos, IVector3d speed, int... args) {
        spawnFX(particleID, pos, speed, 32, args);
    }
}
